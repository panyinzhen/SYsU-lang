#include "Generator.hpp"
#include <llvm/IR/IRBuilder.h>
#include <llvm/Transforms/Utils/ModuleUtils.h>

#define self (*this)

namespace asg {

llvm::Module&
Generator::operator()(const asg::TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
  return _mod;
}

//============================================================================
// 表达式
//============================================================================

//============================================================================
// 语句
//============================================================================

//============================================================================
// 声明
//============================================================================

void
Generator::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  ASG_ABORT();
}

void
Generator::operator()(VarDecl* obj)
{
  auto ty = self(obj->type);
  auto init = trans_static_init(obj->init);
  auto gvar = new llvm::GlobalVariable(
    _mod, ty, false, llvm::GlobalVariable::ExternalLinkage, init, obj->name);
}

void
Generator::operator()(FunctionDecl* obj)
{
  // 创建函数
  auto fty = llvm::dyn_cast<llvm::FunctionType>(self(obj->type));
  _curFunc = llvm::Function::Create(
    fty, llvm::GlobalVariable::ExternalLinkage, obj->name, _mod);

  // 设置参数
  auto argIter = _curFunc->arg_begin();
  for (auto&& param : obj->params)
    argIter->setName(obj->name), ++argIter;

  // 翻译函数体
  if (obj->body == nullptr)
    return;

  auto bb = llvm::BasicBlock::Create(_ctx, "", _curFunc);
  for (auto&& stmt : obj->body->subs)
    bb = self(stmt, bb);
}

llvm::Constant*
Generator::trans_static_init(Expr* obj)
{
  // https://zh.cppreference.com/w/c/language/constant_expression

  if (auto p = obj->dcast<IntegerLiteral>()) {
    llvm::Type* ty;
    switch (p->type.specs.base) {
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

    return llvm::ConstantInt::get(ty, p->val);
  }

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

  if (auto p = obj->dcast<ImplicitInitExpr>()) {
  }

  if (auto p = obj->dcast<ImplicitCastExpr>()) {
  }

  // TODO

  ASG_ABORT();
}

}
