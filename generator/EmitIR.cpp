#include "EmitIR.hpp"
#include <llvm/Transforms/Utils/ModuleUtils.h>

#define self (*this)

namespace asg {

EmitIR::EmitIR(llvm::LLVMContext& ctx, llvm::StringRef mid)
  : _mod(mid, ctx)
  , _ctx(ctx)
  , _intTy(llvm::Type::getInt32Ty(ctx))
  , _ctorTy(llvm::FunctionType::get(llvm::Type::getVoidTy(ctx), false))
{
}

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
  auto p = obj->type.texp->dcast<ArrayType>();
  if (!p)
    ASG_ABORT();

  auto size = obj->val.size();
  obj->val.resize(p->len - 1);

  auto val = new llvm::GlobalVariable(
    _mod,
    self(obj->type),
    true,
    llvm::GlobalVariable::PrivateLinkage,
    llvm::ConstantDataArray::getString(_ctx, obj->val));

  obj->val.resize(size);

  return val;
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
      // return irb.CreateNot(subVal);
      return irb.CreateZExt(
        irb.CreateICmpEQ(subVal, llvm::ConstantInt::get(subVal->getType(), 0)),
        _intTy);

    default:
      ASG_ABORT();
  }
}

llvm::Value*
EmitIR::operator()(BinaryExpr* obj)
{
  llvm::Value *lftVal, *rhtVal;

  lftVal = self(obj->lft);

  // 逻辑运算要进行短路求值
  switch (obj->op) {
    case BinaryExpr::kAnd: {
      auto lftBb = _curIrb->GetInsertBlock();
      auto rhtBb = llvm::BasicBlock::Create(_ctx, "and_rht", _curFunc);
      auto exitBb = llvm::BasicBlock::Create(_ctx, "and_exit", _curFunc);

      _curIrb->CreateCondBr(trans_bool(lftVal), rhtBb, exitBb);

      _curIrb = std::make_unique<llvm::IRBuilder<>>(rhtBb);
      rhtVal = self(obj->rht);
      _curIrb->CreateBr(exitBb);
      rhtBb = _curIrb->GetInsertBlock();

      _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
      auto phi = _curIrb->CreatePHI(_intTy, 2, "and_ans");
      phi->addIncoming(llvm::ConstantInt::get(_intTy, 0), lftBb);
      phi->addIncoming(rhtVal, rhtBb);
      return phi;
    }

    case BinaryExpr::kOr: {
      auto lftBb = _curIrb->GetInsertBlock();
      auto rhtBb = llvm::BasicBlock::Create(_ctx, "or_rht", _curFunc);
      auto exitBb = llvm::BasicBlock::Create(_ctx, "or_exit", _curFunc);

      _curIrb->CreateCondBr(trans_bool(lftVal), exitBb, rhtBb);

      _curIrb = std::make_unique<llvm::IRBuilder<>>(rhtBb);
      rhtVal = self(obj->rht);
      _curIrb->CreateBr(exitBb);
      rhtBb = _curIrb->GetInsertBlock();

      _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
      auto phi = _curIrb->CreatePHI(_intTy, 2, "or_ans");
      phi->addIncoming(llvm::ConstantInt::get(_intTy, 1), lftBb);
      phi->addIncoming(rhtVal, rhtBb);
      return phi;
    }

    default:
      break;
  }

  auto& irb = *_curIrb;
  rhtVal = self(obj->rht);
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
      return irb.CreateZExt(irb.CreateICmpSGT(lftVal, rhtVal), _intTy);

    case BinaryExpr::kLt:
      return irb.CreateZExt(irb.CreateICmpSLT(lftVal, rhtVal), _intTy);

    case BinaryExpr::kGe:
      return irb.CreateZExt(irb.CreateICmpSGE(lftVal, rhtVal), _intTy);

    case BinaryExpr::kLe:
      return irb.CreateZExt(irb.CreateICmpSLE(lftVal, rhtVal), _intTy);

    case BinaryExpr::kEq:
      return irb.CreateZExt(irb.CreateICmpEQ(lftVal, rhtVal), _intTy);

    case BinaryExpr::kNe:
      return irb.CreateZExt(irb.CreateICmpNE(lftVal, rhtVal), _intTy);

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

void
EmitIR::trans_init(llvm::Value* val, Expr* obj)
{
  auto& irb = *_curIrb;

  if (auto p = obj->dcast<InitListExpr>()) {
    irb.CreateStore(
      llvm::ConstantAggregateZero::get(val->getType()->getPointerElementType()),
      val);

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
  if (auto p = obj->dcast<StringLiteral>())
    irb.CreateStore(
      irb.CreateLoad(val->getType()->getPointerElementType(), initVal), val);
  else
    irb.CreateStore(initVal, val);
}

llvm::Value*
EmitIR::trans_bool(llvm::Value* cond)
{
  // if (auto p = llvm::dyn_cast_or_null<llvm::IntegerType>(cond))
  //   if (p->getBitWidth() == 1)
  //     return cond;

  return _curIrb->CreateICmpNE(cond,
                               llvm::ConstantInt::get(cond->getType(), 0));
}

//==============================================================================
// 语句
//==============================================================================

void
EmitIR::operator()(Stmt* obj)
{
  if (auto p = obj->dcast<NullStmt>())
    return;

  if (auto p = obj->dcast<DeclStmt>())
    return self(p);

  if (auto p = obj->dcast<ExprStmt>())
    return self(p);

  if (auto p = obj->dcast<CompoundStmt>())
    return self(p);

  if (auto p = obj->dcast<IfStmt>())
    return self(p);

  if (auto p = obj->dcast<WhileStmt>())
    return self(p);

  if (auto p = obj->dcast<DoStmt>())
    return self(p);

  if (auto p = obj->dcast<BreakStmt>())
    return self(p);

  if (auto p = obj->dcast<ContinueStmt>())
    return self(p);

  if (auto p = obj->dcast<ReturnStmt>())
    return self(p);

  ASG_ABORT();
}

void
EmitIR::operator()(DeclStmt* obj)
{
  auto& irb = *_curIrb;

  for (auto&& decl : obj->decls) {
    auto p = decl->dcast<VarDecl>();
    if (!p)
      ASG_ABORT();

    auto val = irb.CreateAlloca(self(p->type), nullptr, decl->name);
    decl->any = std::make_any<llvm::Value*>(val);

    if (p->init != nullptr)
      trans_init(val, p->init);
  }
}

void
EmitIR::operator()(ExprStmt* obj)
{
  self(obj->expr);
}

void
EmitIR::operator()(CompoundStmt* obj)
{
  auto sp =
    _curIrb->CreateIntrinsic(llvm::Intrinsic::stacksave, {}, {}, nullptr, "sp");
  for (auto&& stmt : obj->subs)
    self(stmt);
  _curIrb->CreateIntrinsic(llvm::Intrinsic::stackrestore, {}, { sp });
}

void
EmitIR::operator()(IfStmt* obj)
{
  auto condVal = trans_bool(self(obj->cond));
  auto condIrb = std::move(_curIrb);

  auto exitBb = llvm::BasicBlock::Create(_ctx, "if_exit", _curFunc);

  auto thenBb = llvm::BasicBlock::Create(_ctx, "if_then", _curFunc);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(thenBb);
  self(obj->then);
  _curIrb->CreateBr(exitBb);

  if (obj->else_ == nullptr)
    condIrb->CreateCondBr(condVal, thenBb, exitBb);

  else {
    auto elseBb = llvm::BasicBlock::Create(_ctx, "if_else", _curFunc);
    _curIrb = std::make_unique<llvm::IRBuilder<>>(elseBb);
    self(obj->else_);
    _curIrb->CreateBr(exitBb);

    condIrb->CreateCondBr(condVal, thenBb, elseBb);
  }

  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
}

void
EmitIR::operator()(WhileStmt* obj)
{
  auto condBb = llvm::BasicBlock::Create(_ctx, "while_cond", _curFunc);
  auto loopBb = llvm::BasicBlock::Create(_ctx, "while_loop", _curFunc);
  auto exitBb = llvm::BasicBlock::Create(_ctx, "while_exit", _curFunc);

  LoopAny loopAny;
  loopAny.continue_ = condBb;
  loopAny.break_ = exitBb;
  obj->any = loopAny;

  _curIrb->CreateBr(condBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(condBb);
  auto condVal = trans_bool(self(obj->cond));
  _curIrb->CreateCondBr(condVal, loopBb, exitBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(loopBb);
  self(obj->body);
  _curIrb->CreateBr(condBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
}

void
EmitIR::operator()(DoStmt* obj)
{
  auto loopBb = llvm::BasicBlock::Create(_ctx, "do_loop", _curFunc);
  auto condBb = llvm::BasicBlock::Create(_ctx, "do_cond", _curFunc);
  auto exitBb = llvm::BasicBlock::Create(_ctx, "do_exit", _curFunc);

  LoopAny loopAny;
  loopAny.continue_ = condBb;
  loopAny.break_ = exitBb;
  obj->any = loopAny;

  _curIrb->CreateBr(condBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(loopBb);
  self(obj->body);
  _curIrb->CreateBr(condBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(condBb);
  auto condVal = trans_bool(self(obj->cond));
  _curIrb->CreateCondBr(condVal, loopBb, exitBb);

  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
}

void
EmitIR::operator()(BreakStmt* obj)
{
  auto& loopAny = std::any_cast<LoopAny&>(obj->loop->any);

  _curIrb->CreateBr(loopAny.break_);

  auto exitBb = llvm::BasicBlock::Create(_ctx, "break_exit", _curFunc);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
}

void
EmitIR::operator()(ContinueStmt* obj)
{
  auto& loopAny = std::any_cast<LoopAny&>(obj->loop->any);

  _curIrb->CreateBr(loopAny.continue_);

  auto exitBb = llvm::BasicBlock::Create(_ctx, "continue_exit", _curFunc);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
}

void
EmitIR::operator()(ReturnStmt* obj)
{
  auto& irb = *_curIrb;

  llvm::Value* retVal;
  if (!obj->expr)
    retVal = nullptr;
  else
    retVal = self(obj->expr);

  _curIrb->CreateRet(retVal);

  auto exitBb = llvm::BasicBlock::Create(_ctx, "return_exit", _curFunc);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(exitBb);
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
  auto gvar = new llvm::GlobalVariable(
    _mod, ty, false, llvm::GlobalVariable::ExternalLinkage, nullptr, obj->name);

  obj->any = std::make_any<llvm::Value*>(gvar);

  gvar->setInitializer(llvm::ConstantAggregateZero::get(ty));
  if (obj->init == nullptr)
    return;

  _curFunc = llvm::Function::Create(
    _ctorTy, llvm::GlobalVariable::PrivateLinkage, "ctor_" + obj->name, _mod);
  llvm::appendToGlobalCtors(_mod, _curFunc, 65535);

  auto entryBb = llvm::BasicBlock::Create(_ctx, "entry", _curFunc);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(entryBb);
  trans_init(gvar, obj->init);
  _curIrb->CreateRet(nullptr);
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
  auto entryBb = llvm::BasicBlock::Create(_ctx, "entry", func);
  _curIrb = std::make_unique<llvm::IRBuilder<>>(entryBb);
  auto& entryIrb = *_curIrb;

  // 设置参数
  auto argIter = func->arg_begin();
  for (auto&& param : obj->params) {
    auto val = entryIrb.CreateAlloca(argIter->getType());
    entryIrb.CreateStore(argIter, val);
    param->any = std::make_any<llvm::Value*>(val);

    argIter->setName(param->name);
    ++argIter;
  }

  // 翻译函数体
  _curFunc = func;
  self(obj->body);
  auto& exitIrb = *_curIrb;

  if (fty->getReturnType()->isVoidTy())
    exitIrb.CreateRetVoid();
  else
    exitIrb.CreateUnreachable();
}

}

/**
 * 若干关键点：
 *
 * 1. 左值翻译为 alloca 指令并传递指针
 *
 * 2. 表达式有短路求值的问题，因此一行表达式可能翻译为多个基本块
 *
 * 3. 全局变量初始化如何翻译（编译时常量表达式求值 或 global_ctor）
 *
 * 4. 初始化表达式的规范化（语义分析）
 *
 * 5. 局部变量表达式直接零初始化
 *
 * 6. 反复调用 alloca 指令会重复消耗栈空间，要记得使用 stackrestore 释放！
 */

/**
 * 距离一个完整的 C 语言编译器还差：
 *
 * 1. 指针
 * 2. 结构体
 * 3. for 语句
 * 4. 调整 ASG 结构（匹配clang）
 */
