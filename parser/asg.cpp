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
    t.len = nullptr;
    obj->type.texp = &t;
  }

  auto texp = reinterpret_cast<ArrayType*>(obj->type.texp);
  if (texp->len == nullptr || typeid(*texp->len) != typeid(IntegerLiteral)) {
    auto& t = make<IntegerLiteral>();
    texp->len = &t;
  }

  auto len = reinterpret_cast<IntegerLiteral*>(texp->len);
  len->val = obj->val.size() + 1;

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
      rht = assigment_cast(lft, rht);

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
}

void
TypeInfer::operator()(ExprStmt* obj)
{
}

void
TypeInfer::operator()(CompoundStmt* obj)
{
}

void
TypeInfer::operator()(IfStmt* obj)
{
}

void
TypeInfer::operator()(WhileStmt* obj)
{
}

void
TypeInfer::operator()(DoStmt* obj)
{
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
}

void
TypeInfer::operator()(FunctionDecl* obj)
{
}

void
TypeInfer::operator()(Obj::Ptr<Expr, InitList> obj)
{
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
TypeInfer::promote_integer(Expr* exp, int to = Type::Specs::kInt)
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
TypeInfer::assigment_cast(Expr* lft, Expr* rht)
{
  if (lft->type.cate != Type::kLValue || lft->type.specs.isConst)
    abort();

  switch (lft->type.specs.base) {
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

  if (lft->type.texp != nullptr) {
    if (!lft->type.texp->dcast<ArrayType>())
      abort();

    if (!type_equal(lft->type, rht->type))
      abort();
  }

  else if (rht->type.specs.base != lft->type.specs.base) {
    auto& cst = make<ImplicitCastExpr>();
    cst.kind = cst.kIntegralCast;
    cst.sub = rht;
    cst.type = lft->type;
    rht = &cst;
  }

  return rht;
}

}
