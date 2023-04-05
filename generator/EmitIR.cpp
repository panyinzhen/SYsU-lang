#include "EmitIR.hpp"
#include <llvm/Transforms/Utils/ModuleUtils.h>

#define self (*this)

namespace asg {

llvm::Module&
EmitIR::operator()(const asg::TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
  return _mod;
}

//==============================================================================
// 类型
//==============================================================================

llvm::Type*
EmitIR::operator()(const Type& type)
{
  if (type.texp == nullptr) {
    switch (type.specs.base) {
      case Type::Specs::kVoid:
        return llvm::Type::getVoidTy(_ctx);

      case Type::Specs::kChar:
        return llvm::Type::getInt8Ty(_ctx);

      case Type::Specs::kInt:
      case Type::Specs::kLong:
        return llvm::Type::getInt32Ty(_ctx);

      case Type::Specs::kLongLong:
        return llvm::Type::getInt64Ty(_ctx);

      default:
        ASG_ABORT();
    }
  }

  Type subt;
  subt.cate = type.cate;
  subt.specs = type.specs;
  subt.texp = type.texp->sub;

  if (auto p = type.texp->dcast<ArrayType>()) {
    if (p->len == -1)
      return self(subt)->getPointerTo();
    return llvm::ArrayType::get(self(subt), p->len);
  }

  if (auto p = type.texp->dcast<FunctionType>()) {
    std::vector<llvm::Type*> pty;
    for (auto&& i : p->params)
      pty.push_back(self(i->type));
    return llvm::FunctionType::get(self(subt), std::move(pty), false);
  }

  ASG_ABORT();
}

//==============================================================================
// 表达式
//==============================================================================

llvm::Value*
EmitIR::operator()(Expr* obj)
{
  if (auto p = obj->dcast<IntegerLiteral>())
    return self(p);

  if (auto p = obj->dcast<StringLiteral>())
    return self(p);

  if (auto p = obj->dcast<DeclRefExpr>())
    return self(p);

  if (auto p = obj->dcast<UnaryExpr>())
    return self(p);

  if (auto p = obj->dcast<BinaryExpr>())
    return self(p);

  if (auto p = obj->dcast<CallExpr>())
    return self(p);

  if (auto p = obj->dcast<ImplicitCastExpr>())
    return self(p);

  ASG_ABORT();
}

llvm::Constant*
EmitIR::operator()(IntegerLiteral* obj)
{
  return llvm::ConstantInt::get(self(obj->type), obj->val);
}

llvm::Constant*
EmitIR::operator()(StringLiteral* obj)
{
  return llvm::ConstantDataArray::getString(_ctx, obj->val);
}

llvm::Value*
EmitIR::operator()(DeclRefExpr* obj)
{
  // 在LLVM IR层面，左值体现为返回指向值的指针
  // 在ImplicitCastExpr::kLValueToRValue中发射load指令从而变成右值
  return std::any_cast<llvm::Value*>(obj->decl->any);
}

llvm::Value*
EmitIR::operator()(UnaryExpr* obj)
{
  auto& irb = *_curIrb;

  auto subVal = self(obj->sub);

  switch (obj->op) {
    case UnaryExpr::kPos:
      return subVal;

    case UnaryExpr::kNeg:
      return irb.CreateNeg(subVal);

    case UnaryExpr::kNot:
      return irb.CreateNot(subVal);

    default:
      ASG_ABORT();
  }
}

llvm::Value*
EmitIR::operator()(BinaryExpr* obj)
{
  auto& irb = *_curIrb;

  auto lftVal = self(obj->lft);
  auto rhtVal = self(obj->rht);

  switch (obj->op) {
    case BinaryExpr::kMul:
      return irb.CreateMul(lftVal, rhtVal);

    case BinaryExpr::kDiv: // C99 开始定义为向 0 方向截断
      return irb.CreateSDiv(lftVal, rhtVal);

    case BinaryExpr::kMod:
      return irb.CreateSRem(lftVal, rhtVal);

    case BinaryExpr::kAdd:
      return irb.CreateAdd(lftVal, rhtVal);

    case BinaryExpr::kSub:
      return irb.CreateSub(lftVal, rhtVal);

    case BinaryExpr::kGt:
      return irb.CreateICmpSGT(lftVal, rhtVal);

    case BinaryExpr::kLt:
      return irb.CreateICmpSLT(lftVal, rhtVal);

    case BinaryExpr::kGe:
      return irb.CreateICmpSGE(lftVal, rhtVal);

    case BinaryExpr::kLe:
      return irb.CreateICmpSLE(lftVal, rhtVal);

    case BinaryExpr::kEq:
      return irb.CreateICmpEQ(lftVal, rhtVal);

    case BinaryExpr::kNe:
      return irb.CreateICmpNE(lftVal, rhtVal);

    case BinaryExpr::kAnd:
      return irb.CreateAnd(lftVal, rhtVal);

    case BinaryExpr::kOr:
      return irb.CreateOr(lftVal, rhtVal);

    case BinaryExpr::kAssign:
      irb.CreateStore(rhtVal, lftVal);
      return rhtVal;

    case BinaryExpr::kComma:
      return rhtVal;

    case BinaryExpr::kIndex: {
      auto ty = lftVal->getType()->getPointerElementType();
      auto ptrVal = irb.CreateGEP(ty, lftVal, rhtVal);
      return ptrVal;
    }

    default:
      ASG_ABORT();
  }
}

llvm::Value*
EmitIR::operator()(CallExpr* obj)
{
  auto& irb = *_curIrb;

  auto head = self(obj->head);

  std::vector<llvm::Value*> args(obj->args.size());
  for (unsigned i = 0; i < obj->args.size(); ++i)
    args[i] = self(obj->args[i]);

  auto func = llvm::dyn_cast<llvm::Function>(head);
  return irb.CreateCall(func->getFunctionType(), func, args);
}

llvm::Value*
EmitIR::operator()(ImplicitCastExpr* obj)
{
  auto sub = self(obj->sub);

  auto& irb = *_curIrb;
  switch (obj->kind) {
    case ImplicitCastExpr::kLValueToRValue: {
      auto loadVal =
        irb.CreateLoad(sub->getType()->getPointerElementType(), sub);
      return loadVal;
    }

    case ImplicitCastExpr::kIntegralCast:
      if (obj->type.specs.base < obj->sub->type.specs.base)
        return irb.CreateTrunc(sub, self(obj->type));
      return irb.CreateSExt(sub, self(obj->type));

    case ImplicitCastExpr::kArrayToPointerDecay:
      return irb.CreateBitCast(sub,
                               sub->getType()
                                 ->getPointerElementType()
                                 ->getArrayElementType()
                                 ->getPointerTo());

    case ImplicitCastExpr::kFunctionToPointerDecay:
      // return irb.CreateBitCast(sub, sub->getType()->getPointerTo());
      return sub;

    case ImplicitCastExpr::kNoOp:
      return sub;

    default:
      ASG_ABORT();
  }
}

llvm::Constant*
EmitIR::operator()(ImplicitInitExpr* obj)
{
  auto& type = obj->type;
  if (type.texp == nullptr) {
    llvm::Type* ty;
    switch (type.specs.base) {
      case Type::Specs::kChar:
        ty = llvm::Type::getInt8Ty(_ctx);
        break;

      case Type::Specs::kInt:
      case Type::Specs::kLong:
        ty = llvm::Type::getInt32Ty(_ctx);
        break;

      case Type::Specs::kLongLong:
        ty = llvm::Type::getInt64Ty(_ctx);
        break;

      default:
        ASG_ABORT();
    }
    return llvm::ConstantInt::get(ty, 0);
  }
  return llvm::ConstantAggregateZero::get(self(type));
}

void
EmitIR::trans_init(llvm::Value* val, Expr* obj)
{
  auto& irb = *_curIrb;

  if (auto p = obj->dcast<ImplicitInitExpr>()) {
    irb.CreateStore(self(p), val);
    return;
  }

  if (auto p = obj->dcast<InitListExpr>()) {
    for (int i = 0; i < p->list.size(); ++i) {
      auto ptrVal = irb.CreateBitCast(val,
                                      val->getType()
                                        ->getPointerElementType()
                                        ->getArrayElementType()
                                        ->getPointerTo());
      auto elemVal =
        irb.CreateGEP(ptrVal->getType()->getPointerElementType(),
                      ptrVal,
                      llvm::ConstantInt::get(llvm::Type::getInt32Ty(_ctx), i));
      trans_init(elemVal, p->list[i]);
    }
    return;
  }

  auto initVal = self(obj);
  irb.CreateStore(initVal, val);
}

llvm::Constant*
EmitIR::trans_static_init(Expr* obj)
{
  // https://zh.cppreference.com/w/c/language/constant_expression

  if (auto p = obj->dcast<IntegerLiteral>())
    return self(p);

  if (auto p = obj->dcast<StringLiteral>())
    return llvm::ConstantDataArray::getString(_ctx, p->val);

  if (auto p = obj->dcast<UnaryExpr>()) {
    auto sub = llvm::dyn_cast<llvm::ConstantInt>(trans_static_init(p->sub));
    if (sub == nullptr)
      ASG_ABORT();

    switch (p->op) {
      case UnaryExpr::kPos:
        return sub;

      case UnaryExpr::kNeg:
        return llvm::ConstantInt::get(sub->getType(), -sub->getValue());

      case UnaryExpr::kNot:
        return llvm::ConstantInt::get(sub->getType(), !sub->getValue());

      default:
        ASG_ABORT();
    }
  }

  if (auto p = obj->dcast<BinaryExpr>()) {
    auto lft = llvm::dyn_cast<llvm::ConstantInt>(trans_static_init(p->lft));
    auto rht = llvm::dyn_cast<llvm::ConstantInt>(trans_static_init(p->rht));
    if (lft == nullptr || rht == nullptr)
      ASG_ABORT();

    switch (p->op) {
      case BinaryExpr::kMul:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue() * rht->getValue());

      case BinaryExpr::kDiv: // C99 开始定义为向 0 方向截断
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().sdiv(rht->getValue()));

      case BinaryExpr::kMod:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().srem(rht->getValue()));

      case BinaryExpr::kAdd:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue() + rht->getValue());

      case BinaryExpr::kSub:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue() - rht->getValue());

      case BinaryExpr::kGt:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().sgt(rht->getValue()));

      case BinaryExpr::kLt:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().slt(rht->getValue()));

      case BinaryExpr::kGe:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().sge(rht->getValue()));

      case BinaryExpr::kLe:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue().sle(rht->getValue()));

      case BinaryExpr::kEq:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue() == rht->getValue());

      case BinaryExpr::kNe:
        return llvm::ConstantInt::get(lft->getType(),
                                      lft->getValue() != rht->getValue());

      case BinaryExpr::kAnd:
        return llvm::ConstantInt::get(lft->getType(),
                                      !lft->getValue().isZero() &&
                                        !rht->getValue().isZero());

      case BinaryExpr::kOr:
        return llvm::ConstantInt::get(lft->getType(),
                                      !lft->getValue().isZero() ||
                                        !rht->getValue().isZero());

      default:
        ASG_ABORT();
    }
  }

  if (auto p = obj->dcast<InitListExpr>()) {
    auto ty = llvm::dyn_cast<llvm::ArrayType>(self(p->type));
    std::vector<llvm::Constant*> list;
    for (auto&& i : p->list)
      list.push_back(trans_static_init(i));
    return llvm::ConstantArray::get(ty, std::move(list));
  }

  if (auto p = obj->dcast<ImplicitInitExpr>())
    return self(p);

  if (auto p = obj->dcast<ImplicitCastExpr>()) {
    auto sub = trans_static_init(p->sub);

    switch (p->kind) {
      case ImplicitCastExpr::kLValueToRValue:
      case ImplicitCastExpr::kNoOp:
        return sub;

      case ImplicitCastExpr::kIntegralCast: {
        auto ity = llvm::dyn_cast<llvm::ConstantInt>(sub);
        return llvm::ConstantInt::get(self(p->type), ity->getValue());
      }

        // case ImplicitCastExpr::kArrayToPointerDecay:
        // case ImplicitCastExpr::kFunctionToPointerDecay:

      default:
        ASG_ABORT();
    }
  }

  ASG_ABORT();
}

//==============================================================================
// 语句
//==============================================================================

llvm::BasicBlock*
EmitIR::operator()(Stmt* obj, llvm::BasicBlock* enter)
{
  if (auto p = obj->dcast<NullStmt>())
    return enter;

  if (auto p = obj->dcast<DeclStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<ExprStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<CompoundStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<IfStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<WhileStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<DoStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<BreakStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<ContinueStmt>())
    return self(p, enter);

  if (auto p = obj->dcast<ReturnStmt>())
    return self(p, enter);

  ASG_ABORT();
}

llvm::BasicBlock*
EmitIR::operator()(DeclStmt* obj, llvm::BasicBlock* enter)
{
  llvm::IRBuilder<> irb(enter);

  for (auto&& decl : obj->decls) {
    auto p = decl->dcast<VarDecl>();
    if (!p)
      ASG_ABORT();

    auto val = irb.CreateAlloca(self(p->type), nullptr, decl->name);
    decl->any = std::make_any<llvm::Value*>(val);

    if (p->init != nullptr) {
      _curIrb = &irb;
      trans_init(val, p->init);
    }
  }

  return enter;
}

llvm::BasicBlock*
EmitIR::operator()(ExprStmt* obj, llvm::BasicBlock* enter)
{
  llvm::IRBuilder<> irb(enter);
  _curIrb = &irb;
  self(obj->expr);
  return enter;
}

llvm::BasicBlock*
EmitIR::operator()(CompoundStmt* obj, llvm::BasicBlock* enter)
{
  for (auto&& stmt : obj->subs)
    enter = self(stmt, enter);
  return enter;
}

llvm::BasicBlock*
EmitIR::operator()(IfStmt* obj, llvm::BasicBlock* enter)
{
  llvm::IRBuilder<> irb(enter);
  _curIrb = &irb;
  auto condVal = boolize_cond(self(obj->cond));

  auto nextBb = llvm::BasicBlock::Create(_ctx, "if_next", _curFunc);

  auto thenBb = llvm::BasicBlock::Create(_ctx, "if_then", _curFunc);
  llvm::IRBuilder<> thenIrb(self(obj->then, thenBb));
  thenIrb.CreateBr(nextBb);

  if (obj->else_ == nullptr)
    irb.CreateCondBr(condVal, thenBb, nextBb);

  else {
    auto elseBb = llvm::BasicBlock::Create(_ctx, "if_else", _curFunc);
    elseBb = self(obj->else_, elseBb);
    llvm::IRBuilder<> elseIrb(elseBb);
    elseIrb.CreateBr(nextBb);

    irb.CreateCondBr(condVal, thenBb, elseBb);
  }

  return nextBb;
}

llvm::BasicBlock*
EmitIR::operator()(WhileStmt* obj, llvm::BasicBlock* enter)
{
  auto condBb = llvm::BasicBlock::Create(_ctx, "while_cond", _curFunc);
  auto loopBb = llvm::BasicBlock::Create(_ctx, "while_loop", _curFunc);
  auto nextBb = llvm::BasicBlock::Create(_ctx, "while_next", _curFunc);

  LoopAny loopAny;
  loopAny.continue_ = condBb;
  loopAny.break_ = nextBb;
  obj->any = loopAny;

  llvm::IRBuilder<> irb(enter);
  irb.CreateBr(condBb);

  llvm::IRBuilder<> condIrb(condBb);
  _curIrb = &condIrb;
  auto condVal = boolize_cond(self(obj->cond));
  condIrb.CreateCondBr(condVal, loopBb, nextBb);

  llvm::IRBuilder<> loopIrb(self(obj->body, loopBb));
  loopIrb.CreateBr(condBb);

  return nextBb;
}

llvm::BasicBlock*
EmitIR::operator()(DoStmt* obj, llvm::BasicBlock* enter)
{
  auto loopBb = llvm::BasicBlock::Create(_ctx, "do_loop", _curFunc);
  auto condBb = llvm::BasicBlock::Create(_ctx, "do_cond", _curFunc);
  auto nextBb = llvm::BasicBlock::Create(_ctx, "do_next", _curFunc);

  LoopAny loopAny;
  loopAny.continue_ = condBb;
  loopAny.break_ = nextBb;
  obj->any = loopAny;

  llvm::IRBuilder<> irb(enter);
  irb.CreateBr(loopBb);

  llvm::IRBuilder<> loopIrb(self(obj->body, loopBb));
  loopIrb.CreateBr(condBb);

  llvm::IRBuilder<> condIrb(condBb);
  _curIrb = &condIrb;
  auto condVal = boolize_cond(self(obj->cond));
  condIrb.CreateCondBr(condVal, loopBb, nextBb);

  return nextBb;
}

llvm::BasicBlock*
EmitIR::operator()(BreakStmt* obj, llvm::BasicBlock* enter)
{
  auto& loopAny = std::any_cast<LoopAny&>(obj->loop->any);

  llvm::IRBuilder<> irb(enter);
  irb.CreateBr(loopAny.break_);

  return llvm::BasicBlock::Create(_ctx, "break_next", _curFunc);
}

llvm::BasicBlock*
EmitIR::operator()(ContinueStmt* obj, llvm::BasicBlock* enter)
{
  auto& loopAny = std::any_cast<LoopAny&>(obj->loop->any);

  llvm::IRBuilder<> irb(enter);
  irb.CreateBr(loopAny.continue_);

  return llvm::BasicBlock::Create(_ctx, "continue_next", _curFunc);
}

llvm::BasicBlock*
EmitIR::operator()(ReturnStmt* obj, llvm::BasicBlock* enter)
{
  llvm::IRBuilder<> irb(enter);

  llvm::Value* retVal;
  if (!obj->expr)
    retVal = nullptr;
  else {
    _curIrb = &irb;
    retVal = self(obj->expr);
  }

  irb.CreateRet(retVal);
  return llvm::BasicBlock::Create(_ctx, "return_next", _curFunc);
}

//==============================================================================
// 声明
//==============================================================================

void
EmitIR::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  ASG_ABORT();
}

void
EmitIR::operator()(VarDecl* obj)
{
  auto ty = self(obj->type);
  auto init = obj->init != nullptr ? trans_static_init(obj->init)
                                   : llvm::ConstantAggregateZero::get(ty);
  auto gvar = new llvm::GlobalVariable(
    _mod, ty, false, llvm::GlobalVariable::ExternalLinkage, init, obj->name);

  obj->any = std::make_any<llvm::Value*>(gvar);
}

void
EmitIR::operator()(FunctionDecl* obj)
{
  // 创建函数
  auto fty = llvm::dyn_cast<llvm::FunctionType>(self(obj->type));
  auto func = llvm::Function::Create(
    fty, llvm::GlobalVariable::ExternalLinkage, obj->name, _mod);

  obj->any = std::make_any<llvm::Value*>(func);

  if (obj->body == nullptr)
    return;

  // 设置参数
  auto bb = llvm::BasicBlock::Create(_ctx, "entry", func);
  llvm::IRBuilder<> irb(bb);

  auto argIter = func->arg_begin();
  for (auto&& param : obj->params) {
    auto val = irb.CreateAlloca(argIter->getType());
    irb.CreateStore(argIter, val);
    param->any = std::make_any<llvm::Value*>(val);

    argIter->setName(param->name);
    ++argIter;
  }

  // 翻译函数体
  _curFunc = func;
  bb = self(obj->body, bb);
  if (bb->empty()) {
    llvm::IRBuilder<> irb(bb);
    irb.CreateUnreachable();
  }
}

}
