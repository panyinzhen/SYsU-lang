#include "asg.hpp"
#include <cassert>

#define self (*this)

namespace asg {

void
TypeInfer::operator()(TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
}

//==============================================================================
// 表达式
//==============================================================================

Expr*
TypeInfer::operator()(Expr* obj)
{
  if (auto p = obj->dcast<IntegerLiteral>())
    return self(p);

  if (auto p = obj->dcast<StringLiteral>())
    return self(p);

  if (auto p = obj->dcast<DeclRefExpr>())
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
    return self(p->sub);

  abort();
}

Expr*
TypeInfer::operator()(IntegerLiteral* obj)
{
  obj->type.cate = Type::kRValue;
  // obj->type.specs.isConst = 1;
  obj->type.specs.base = Type::Specs::kInt;
  obj->type.texp = nullptr;

  return obj;
}

Expr*
TypeInfer::operator()(StringLiteral* obj)
{
  obj->type.cate = Type::kRValue;
  // obj->type.specs.isConst = 1;
  obj->type.specs.base = Type::Specs::kChar;

  if (obj->type.texp == nullptr ||
      typeid(*obj->type.texp) != typeid(ArrayType)) {
    auto& t = make<ArrayType>();
    t.sub = nullptr;
    t.lexp = nullptr;
    obj->type.texp = &t;
  }

  auto texp = reinterpret_cast<ArrayType*>(obj->type.texp);
  if (texp->lexp == nullptr || typeid(*texp->lexp) != typeid(IntegerLiteral)) {
    auto& t = make<IntegerLiteral>();
    texp->lexp = &t;
  }

  auto lexp = reinterpret_cast<IntegerLiteral*>(texp->lexp);
  lexp->val = obj->val.size() + 1;

  return obj;
}

Expr*
TypeInfer::operator()(DeclRefExpr* obj)
{
  assert(obj->decl);
  WalkedGuard walked(self, obj);

  self(obj->decl);
  obj->type = obj->decl->type;

  obj->type.cate = Type::kLValue;

  return obj;
}

Expr*
TypeInfer::operator()(UnaryExpr* obj)
{
  assert(obj->sub);
  WalkedGuard walked(self, obj);

  auto sub = self(obj->sub);

  // 左值要先转成右值，然后进行整数提升
  sub = ensure_rvalue(sub);
  sub = promote_integer(sub);

  obj->type = sub->type;
  obj->type.cate = Type::kRValue;
  obj->type.specs.isConst = 0;

  switch (obj->op) {
    case UnaryExpr::kNeg:
    case UnaryExpr::kPos:
      break;

    case UnaryExpr::kNot:
      obj->type.specs.base = Type::Specs::kInt;
      break;

    default:
      abort();
  }

  return obj;
}

Expr*
TypeInfer::operator()(BinaryExpr* obj)
{
  assert(obj->lft && obj->rht);
  WalkedGuard walked(self, obj);

  auto lft = self(obj->lft);
  auto rht = self(obj->rht);

  switch (obj->op) {
    case BinaryExpr::kMul:
    case BinaryExpr::kDiv:
    case BinaryExpr::kMod:
    case BinaryExpr::kAdd:
    case BinaryExpr::kSub: {
      // 左值要先转成右值，整数提升到 int
      lft = promote_integer(ensure_rvalue(lft));
      rht = promote_integer(ensure_rvalue(rht));

      // 然后再提升到相同
      lft = promote_integer(lft, rht->type.specs.base);
      rht = promote_integer(rht, lft->type.specs.base);

      obj->lft = lft;
      obj->rht = rht;
      obj->type = lft->type;
    } break;

    case BinaryExpr::kGt:
    case BinaryExpr::kLt:
    case BinaryExpr::kGe:
    case BinaryExpr::kLe:
    case BinaryExpr::kEq:
    case BinaryExpr::kNe: {
      // 左值要先转成右值，整数提升到 int
      lft = promote_integer(ensure_rvalue(lft));
      rht = promote_integer(ensure_rvalue(rht));

      // 然后再提升到相同
      lft = promote_integer(lft, rht->type.specs.base);
      rht = promote_integer(rht, lft->type.specs.base);

      obj->lft = lft;
      obj->rht = rht;

      // 关系运算符的结果一定是 int 类型
      obj->type.specs.isConst = 0;
      obj->type.specs.base = Type::Specs::kInt;
      obj->type.cate = Type::kRValue;
      obj->type.texp = nullptr;
    } break;

    case BinaryExpr::kAnd:
    case BinaryExpr::kOr: {
      // 只要进行右值转换
      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->lft = lft;
      obj->rht = rht;

      // 关系运算符的结果一定是 int 类型
      obj->type.specs.isConst = 0;
      obj->type.specs.base = Type::Specs::kInt;
      obj->type.cate = Type::kRValue;
      obj->type.texp = nullptr;
    } break;

    case BinaryExpr::kAssign: {
      rht = assigment_cast(lft->type, rht);

      obj->lft = lft;
      obj->rht = rht;
      obj->type = rht->type;
    } break;

    case BinaryExpr::kComma: {
      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->lft = lft;
      obj->rht = rht;
      obj->type = rht->type;
    } break;

    case BinaryExpr::kIndex: {
      auto arrayType = lft->type.texp->dcast<ArrayType>();
      if (arrayType == nullptr)
        abort();

      auto& a2p = make<ImplicitCastExpr>();
      a2p.kind = a2p.kArrayToPointerDecay;
      a2p.sub = lft;
      a2p.type = lft->type;
      a2p.type.cate = Type::kRValue;
      a2p.type.specs.isConst = 0;
      lft = &a2p;

      if (rht->type.texp != nullptr)
        abort();
      switch (rht->type.specs.base) {
        case Type::Specs::kChar:
        case Type::Specs::kInt:
        case Type::Specs::kLong:
        case Type::Specs::kLongLong:
          break;

        default:
          abort();
      }
      rht = ensure_rvalue(rht);

      obj->lft = lft;
      obj->rht = rht;
      obj->type.cate = Type::kRValue;
      obj->type.specs = lft->type.specs;
      obj->type.texp = arrayType->sub;
    } break;

    default:
      abort();
  }

  return obj;
}

Expr*
TypeInfer::operator()(CallExpr* obj)
{
  assert(obj->head);

  auto fexp = dynamic_cast<FunctionType*>(obj->head->type.texp);
  if (fexp == nullptr)
    abort();

  auto& f2p = make<ImplicitCastExpr>();
  f2p.kind = f2p.kFunctionToPointerDecay;
  f2p.type = obj->head->type;
  f2p.sub = obj->head;
  obj->head = &f2p;

  if (fexp->params.size() != obj->args.size())
    abort();

  for (int i = fexp->params.size(); --i != -1;)
    obj->args[i] = assigment_cast(fexp->params[i], obj->args[i]);

  obj->type.cate = Type::kRValue;
  obj->type.specs.isConst = 0;
  obj->type.specs.base = obj->head->type.specs.base;
  obj->type.texp = fexp->sub;

  return obj;
}

void
TypeInfer::operator()(InitListExpr* obj, const Type& to)
{
  obj->type = to;
  // TODO
}

//==============================================================================
// 语句
//==============================================================================

void
TypeInfer::operator()(Stmt* obj)
{
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

  if (typeid(*obj) == typeid(Stmt))
    return;

  abort();
}

void
TypeInfer::operator()(DeclStmt* obj)
{
  for (auto&& i : obj->decls)
    self(i);
}

void
TypeInfer::operator()(ExprStmt* obj)
{
  obj->expr = self(obj->expr);
}

void
TypeInfer::operator()(CompoundStmt* obj)
{
  for (auto&& i : obj->subs)
    self(i);
}

void
TypeInfer::operator()(IfStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->then);
  if (obj->else_)
    self(obj->else_);
}

void
TypeInfer::operator()(WhileStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
TypeInfer::operator()(DoStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
TypeInfer::operator()(BreakStmt* obj)
{
}

void
TypeInfer::operator()(ContinueStmt* obj)
{
}

void
TypeInfer::operator()(ReturnStmt* obj)
{
  auto& ftype = obj->func->type;
  auto ftexp = dynamic_cast<FunctionType*>(ftype.texp);
  if (ftexp == nullptr || ftexp->sub != nullptr)
    abort();

  switch (ftype.specs.base) {
    case Type::Specs::kVoid: {
      if (obj->expr != nullptr)
        abort();
    } break;

    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong: {
      if (obj->expr == nullptr)
        abort();

      Type retType;
      retType.cate = Type::kLValue;
      retType.specs = ftype.specs;
      retType.texp = ftexp->sub;
      obj->expr = assigment_cast(retType, ensure_rvalue(self(obj->expr)));
    } break;

    default:
      abort();
  }
}

//==============================================================================
// 声明
//==============================================================================

void
TypeInfer::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  abort();
}

void
TypeInfer::operator()(VarDecl* obj)
{
  obj->type.cate = Type::kLValue;

  switch (obj->type.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      abort();
  }

  // 最多只能声明数值类型
  if (obj->type.texp->sub) {
    auto arrType = obj->type.texp->sub->dcast<ArrayType>();
    if (arrType == nullptr)
      abort();
    
    // TODO arrType 长度编译期求值
  }

  if (obj->init) {
    if (auto p = obj->init->dcast<InitListExpr>())
      self(p, obj->type);
    else
      obj->init = assigment_cast(obj->type, obj->init);
  }
}

void
TypeInfer::operator()(FunctionDecl* obj)
{
  obj->type.cate = Type::kINVALID;

  switch (obj->type.specs.base) {
    case Type::Specs::kVoid:
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      abort();
  }

  // 必须为函数类型
  if (!obj->type.texp->sub || !obj->type.texp->sub->dcast<FunctionType>())
    abort();

  for (auto&& i : obj->params)
    self(i);
}

//==============================================================================
// 其它
//==============================================================================

Expr*
TypeInfer::ensure_rvalue(Expr* exp)
{
  switch (exp->type.cate) {
    case Type::kLValue: {
      auto& cst = make<ImplicitCastExpr>();
      cst.kind = cst.kLValueToRValue;

      cst.type = exp->type;
      cst.type.cate = Type::kRValue;
      cst.type.specs.isConst = 0;

      cst.sub = exp;
      return &cst;
    }

    case Type::kRValue: {
      exp->type.specs.isConst = 0;
      return exp;
    }

    default:
      abort();
  }
}

Expr*
TypeInfer::promote_integer(Expr* exp, int to)
{
  if (exp->type.texp != nullptr)
    abort();

  switch (Type::Specs::kVoid) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong: {
      if (exp->type.specs.base >= to)
        return exp;

      auto& cst = make<ImplicitCastExpr>();
      cst.kind = cst.kIntegralCast;

      cst.type = exp->type;
      cst.type.specs.isConst = 0;
      cst.type.specs.base = to;

      cst.sub = exp;
      return &cst;
    }

    default:
      abort();
  }
}

static bool
type_equal(const Type& a, const Type& b);

static bool
typeexpr_equal(TypeExpr* a, TypeExpr* b)
{
  if (a == b)
    return true;

  if (a == nullptr)
    return b == nullptr;
  else if (b == nullptr)
    return false;

  if (typeid(*a) != typeid(*b))
    return false;

  if (auto at = dynamic_cast<ArrayType*>(a)) {
    auto bt = static_cast<ArrayType*>(b);
    return typeexpr_equal(at->sub, bt->sub);
  }

  if (auto at = dynamic_cast<FunctionType*>(a)) {
    auto bt = static_cast<FunctionType*>(b);
    if (at->params.size() != bt->params.size())
      return false;

    for (int i = at->params.size(); --i != -1;) {
      if (!type_equal(at->params[i], bt->params[i]))
        return false;
    }
    return true;
  }

  abort();
}

static bool
type_equal(const Type& a, const Type& b)
{
  if (a.specs.base != b.specs.base || a.specs.isConst != b.specs.isConst)
    return false;
  return typeexpr_equal(a.texp, b.texp);
}

Expr*
TypeInfer::assigment_cast(const Type& lft, Expr* rht)
{
  if (lft.cate != Type::kLValue || lft.specs.isConst)
    abort();

  switch (lft.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      abort();
  }
  switch (rht->type.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      abort();
  }

  rht = ensure_rvalue(rht);

  if (lft.texp != nullptr) {
    if (!lft.texp->dcast<ArrayType>())
      abort();

    if (!type_equal(lft, rht->type))
      abort();
  }

  else if (rht->type.specs.base != lft.specs.base) {
    auto& cst = make<ImplicitCastExpr>();
    cst.kind = cst.kIntegralCast;
    cst.sub = rht;
    cst.type = lft;
    rht = &cst;
  }

  return rht;
}

}
