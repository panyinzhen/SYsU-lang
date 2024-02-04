#include "Typing.hpp"
#include <cassert>

#define self (*this)

namespace asg {

void
Typing::operator()(TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
}

//==============================================================================
// 表达式
//==============================================================================

Expr*
Typing::operator()(Expr* obj)
{
  if (auto p = obj->dcst<IntegerLiteral>())
    return self(p);

  if (auto p = obj->dcst<StringLiteral>())
    return self(p);

  if (auto p = obj->dcst<DeclRefExpr>())
    return self(p);

  if (auto p = obj->dcst<ParenExpr>())
    return self(p);

  if (auto p = obj->dcst<UnaryExpr>())
    return self(p);

  if (auto p = obj->dcst<BinaryExpr>())
    return self(p);

  if (auto p = obj->dcst<CallExpr>())
    return self(p);

  if (auto p = obj->dcst<ImplicitCastExpr>())
    return self(p->sub);

  ABORT();
}

Expr*
Typing::operator()(IntegerLiteral* obj)
{
  if (obj->val <= INT32_MAX)
    obj->type.spec = Type::Spec::kInt;
  else
    obj->type.spec = Type::Spec::kLongLong;

  obj->type.qual = Type::Qual::kConst;
  obj->type.texp = nullptr;

  obj->cate = Expr::Cate::kRValue;
  return obj;
}

Expr*
Typing::operator()(StringLiteral* obj)
{
  obj->type.spec = Type::Spec::kChar;
  obj->type.qual = Type::Qual::kConst;

  if (obj->type.texp == nullptr ||
      typeid(*obj->type.texp) != typeid(ArrayType)) {
    auto& t = make<ArrayType>();
    obj->type.texp = &t;
  }
  auto arrTy = obj->type.texp->scst<ArrayType>();
  arrTy->sub = nullptr;
  arrTy->len = obj->val.size() + 1;

  obj->cate = Expr::Cate::kRValue;
  return obj;
}

Expr*
Typing::operator()(DeclRefExpr* obj)
{
  ASSERT(obj->decl);
  Obj::Walked walked(obj);

  // C语言要求符号先声明后使用，所以此处无需再进入类型检查。
  // 另外，如果真的进入了，可能会导致无限递归。
  // self(obj->decl);

  obj->type = obj->decl->type;
  obj->cate = Expr::Cate::kLValue;
  return obj;
}

Expr*
Typing::operator()(ParenExpr* obj)
{
  ASSERT(obj->sub);
  obj->sub = self(obj->sub);
  obj->type = obj->sub->type;
  obj->cate = obj->sub->cate;
  return obj;
}

Expr*
Typing::operator()(UnaryExpr* obj)
{
  ASSERT(obj->sub);
  Obj::Walked walked(obj);

  auto sub = self(obj->sub);
  // 左值要先转成右值，然后进行整数提升
  sub = ensure_rvalue(sub);
  sub = promote_integer(sub);
  obj->sub = sub;

  obj->type = sub->type;
  switch (obj->op) {
    case UnaryExpr::kNeg:
    case UnaryExpr::kPos:
      break;

    case UnaryExpr::kNot:
      obj->type.spec = Type::Spec::kInt;
      break;

    default:
      ABORT();
  }
  obj->type.qual = Type::Qual::kNone;

  obj->cate = Expr::Cate::kRValue;
  return obj;
}

Expr*
Typing::operator()(BinaryExpr* obj)
{
  ASSERT(obj->lft && obj->rht);
  Obj::Walked walked(obj);

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
      lft = promote_integer(lft, rht->type.spec);
      rht = promote_integer(rht, lft->type.spec);

      obj->type = lft->type;
      obj->cate = Expr::Cate::kRValue;
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
      lft = promote_integer(lft, rht->type.spec);
      rht = promote_integer(rht, lft->type.spec);

      // 关系运算符的结果一定是 int 类型
      obj->type.spec = Type::Spec::kInt;
      obj->type.qual = Type::Qual::kNone;
      obj->type.texp = nullptr;
      obj->cate = Expr::Cate::kRValue;
    } break;

    case BinaryExpr::kAnd:
    case BinaryExpr::kOr: {
      // 只要进行右值转换
      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      // 关系运算符的结果一定是 int 类型
      obj->type.spec = Type::Spec::kInt;
      obj->type.qual = Type::Qual::kNone;
      obj->type.texp = nullptr;
      obj->cate = Expr::Cate::kRValue;
    } break;

    case BinaryExpr::kAssign: {
      if (lft->type.qual == Type::Qual::kConst)
        ABORT();
      rht = assigment_cast(lft, rht);

      obj->type = rht->type;
      obj->cate = rht->cate;
    } break;

    case BinaryExpr::kComma: {
      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->type = rht->type;
      obj->cate = rht->cate;
    } break;

    case BinaryExpr::kIndex: {
      auto arrayType = lft->type.texp->dcst<ArrayType>();
      if (arrayType == nullptr)
        ABORT();

      if (rht->type.texp != nullptr)
        ABORT();
      switch (rht->type.spec) {
        case Type::Spec::kChar:
        case Type::Spec::kInt:
        case Type::Spec::kLong:
        case Type::Spec::kLongLong:
          break;

        default:
          ABORT();
      }

      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->type.spec = lft->type.spec;
      obj->type.qual = Type::Qual::kNone;
      obj->type.texp = arrayType->sub;
      obj->cate = Expr::Cate::kLValue; // 数组的索引是左值
    } break;

    default:
      ABORT();
  }

  obj->lft = lft;
  obj->rht = rht;
  return obj;
}

Expr*
Typing::operator()(CallExpr* obj)
{
  ASSERT(obj->head);

  obj->head = self(obj->head);
  auto fexp = dynamic_cast<FunctionType*>(obj->head->type.texp);
  if (fexp == nullptr)
    ABORT();

  auto& f2p = make<ImplicitCastExpr>();
  f2p.kind = f2p.kFunctionToPointerDecay;
  f2p.type = obj->head->type;
  f2p.sub = obj->head;
  obj->head = &f2p;

  if (fexp->params.size() != obj->args.size())
    ABORT();

  for (int i = fexp->params.size(); --i != -1;) {
    Expr lft;
    lft.type = fexp->params[i]->type;
    lft.cate = Expr::Cate::kLValue;
    obj->args[i] = assigment_cast(&lft, self(obj->args[i]));
  }

  obj->type.spec = obj->head->type.spec;
  obj->type.qual = Type::Qual::kNone;
  obj->type.texp = fexp->sub;
  obj->cate = Expr::Cate::kRValue;

  return obj;
}

//==============================================================================
// 语句
//==============================================================================

void
Typing::operator()(Stmt* obj)
{
  if (auto p = obj->dcst<DeclStmt>())
    return self(p);

  if (auto p = obj->dcst<ExprStmt>())
    return self(p);

  if (auto p = obj->dcst<CompoundStmt>())
    return self(p);

  if (auto p = obj->dcst<IfStmt>())
    return self(p);

  if (auto p = obj->dcst<WhileStmt>())
    return self(p);

  if (auto p = obj->dcst<DoStmt>())
    return self(p);

  if (auto p = obj->dcst<BreakStmt>())
    return self(p);

  if (auto p = obj->dcst<ContinueStmt>())
    return self(p);

  if (auto p = obj->dcst<ReturnStmt>())
    return self(p);

  if (typeid(*obj) == typeid(Stmt))
    return;

  ABORT();
}

void
Typing::operator()(DeclStmt* obj)
{
  for (auto&& i : obj->decls)
    self(i);
}

void
Typing::operator()(ExprStmt* obj)
{
  obj->expr = ensure_rvalue(self(obj->expr));
}

void
Typing::operator()(CompoundStmt* obj)
{
  for (auto&& i : obj->subs)
    self(i);
}

void
Typing::operator()(IfStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->then);
  if (obj->else_)
    self(obj->else_);
}

void
Typing::operator()(WhileStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
Typing::operator()(DoStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
Typing::operator()(BreakStmt* obj)
{
}

void
Typing::operator()(ContinueStmt* obj)
{
}

void
Typing::operator()(ReturnStmt* obj)
{
  auto& ftype = obj->func->type;
  auto ftexp = dynamic_cast<FunctionType*>(ftype.texp);
  if (ftexp == nullptr || ftexp->sub != nullptr)
    ABORT();

  switch (ftype.spec) {
    case Type::Spec::kVoid: {
      if (obj->expr != nullptr)
        ABORT();
    } break;

    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong: {
      if (obj->expr == nullptr)
        ABORT();

      Expr lft;
      lft.type = ftype;
      lft.type.texp = nullptr;
      lft.cate = Expr::Cate::kLValue;
      obj->expr = assigment_cast(&lft, ensure_rvalue(self(obj->expr)));
    } break;

    default:
      ABORT();
  }
}

//==============================================================================
// 声明
//==============================================================================

void
Typing::operator()(Decl* obj)
{
  if (auto p = obj->dcst<VarDecl>())
    return self(p);

  if (auto p = obj->dcst<FunctionDecl>())
    return self(p);

  ABORT();
}

void
Typing::operator()(VarDecl* obj)
{
  switch (obj->type.spec) {
    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong:
      break;

    default:
      ABORT();
  }

  // 最多只能声明数值类型
  if (obj->init) {
    Expr ty;
    ty.type = obj->type;
    ty.cate = Expr::Cate::kLValue;
    obj->init = infer_init(obj->init, obj->type);
  }
}

void
Typing::operator()(FunctionDecl* obj)
{
  switch (obj->type.spec) {
    case Type::Spec::kVoid:
    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong:
      break;

    default:
      ABORT();
  }

  // 必须为函数类型
  if (obj->type.texp == nullptr)
    ABORT();
  auto funcType = obj->type.texp->dcst<FunctionType>();
  if (funcType == nullptr)
    ABORT();

  funcType->params.resize(obj->params.size());
  for (int i = obj->params.size(); --i != -1;) {
    self(obj->params[i]);
    funcType->params[i] = obj->params[i];
  }

  if (obj->body) {
    for (auto&& i : obj->body->subs)
      self(i);
  }
}

//==============================================================================
// 其它
//==============================================================================

Expr*
Typing::ensure_rvalue(Expr* exp)
{
  if (exp->type.texp->dcst<ArrayType>()) {
    auto& cst = make<ImplicitCastExpr>();
    cst.kind = cst.kArrayToPointerDecay;

    cst.type = exp->type;
    cst.type.qual = Type::Qual::kNone;
    cst.cate = Expr::Cate::kRValue;

    cst.sub = exp;
    return &cst;
  }

  switch (exp->cate) {
    case Expr::Cate::kLValue: {
      auto& cst = make<ImplicitCastExpr>();
      cst.kind = cst.kLValueToRValue;

      cst.type = exp->type;
      cst.type.qual = Type::Qual::kNone;
      cst.cate = Expr::Cate::kRValue;

      cst.sub = exp;
      return &cst;
    }

    case Expr::Cate::kRValue: {
      exp->type.qual = Type::Qual::kNone;
      return exp;
    }

    default:
      ABORT();
  }
}

Expr*
Typing::promote_integer(Expr* exp, Type::Spec to)
{
  if (exp->type.texp != nullptr)
    ABORT();

  switch (exp->type.spec) {
    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong: {
      if (int(exp->type.spec) >= int(to))
        return exp;

      auto& cst = make<ImplicitCastExpr>();
      cst.kind = cst.kIntegralCast;
      cst.type = exp->type;
      cst.type.spec = to;
      cst.type.qual = Type::Qual::kNone;
      cst.sub = exp;
      return &cst;
    }

    default:
      ABORT();
  }
}

static bool
type_equal(Type& a, const Type& b);

static bool
typeexpr_equal(TypeExpr* a, TypeExpr* b)
{
  if (a == b)
    return true;
  if (a == nullptr)
    return b == nullptr;
  if (b == nullptr)
    return false;
  if (typeid(*a) != typeid(*b))
    return false;

  if (auto at = a->dcst<PointerType>()) {
    auto bt = b->scst<PointerType>();
    if (at->qual != bt->qual)
      return false;
    return typeexpr_equal(at->sub, bt->sub);
  }

  if (auto at = a->dcst<ArrayType>()) {
    auto bt = b->scst<ArrayType>();
    return typeexpr_equal(at->sub, bt->sub);
  }

  if (auto at = a->dcst<FunctionType>()) {
    auto bt = b->dcst<FunctionType>();
    if (at->params.size() != bt->params.size())
      return false;

    for (int i = at->params.size(); --i != -1;) {
      if (!type_equal(at->params[i]->type, bt->params[i]->type))
        return false;
    }
    return true;
  }

  ABORT(); // 未知的 Type 子类型
}

static bool
type_equal(Type& a, const Type& b)
{
  if (&a == &b)
    return true;

  if (a.spec != b.spec || a.qual != b.qual)
    return false;

  if (!typeexpr_equal(a.texp, b.texp))
    return false;

  a.texp = b.texp;
  return true;
}

Expr*
Typing::assigment_cast(Expr* lft, Expr* rht)
{
  if (lft->cate != Expr::Cate::kLValue)
    ABORT();

  switch (lft->type.spec) {
    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong:
      break;

    default:
      ABORT();
  }
  switch (rht->type.spec) {
    case Type::Spec::kChar:
    case Type::Spec::kInt:
    case Type::Spec::kLong:
    case Type::Spec::kLongLong:
      break;

    default:
      ABORT();
  }

  rht = ensure_rvalue(rht); // 只能赋右值给变量

  if (lft->type.texp != nullptr) {
    if (!lft->type.texp->dcst<ArrayType>())
      ABORT(); // 最多只支持数组类型被赋值

    if (lft->type.qual == Type::Qual::kConst) {
      auto& ccst = make<ImplicitCastExpr>();
      ccst.kind = ccst.kNoOp;
      ccst.type = rht->type;
      ccst.type.qual = Type::Qual::kConst;
      ccst.sub = rht;
      rht = &ccst;
    }

    // 除了 const 可兼容之外，赋值类型必须相同
    if (!type_equal(lft->type, rht->type))
      ABORT();
  }

  else if (rht->type.texp != nullptr)
    ABORT();

  else if (rht->type.spec != lft->type.spec) {
    auto& cst = make<ImplicitCastExpr>();
    cst.kind = cst.kIntegralCast;
    cst.type = lft->type;
    cst.sub = rht;
    rht = &cst;
  }

  return rht;
}

Expr*
Typing::infer_init(Expr* init, const Type& to)
{
  // https://zh.cppreference.com/w/c/language/scalar_initialization
  if (to.texp == nullptr) {
    if (auto p = init->dcst<ImplicitInitExpr>()) {
      p->type = to;
      return p;
    }

    if (auto p = init->dcst<InitListExpr>()) {
      // 用多个值初始化一个变量时，只有第一个有用，其余的被忽略。
      if (!p->list.empty())
        return infer_init(p->list[0], to);

      // 空初始化列表即为 ImplicitInitExpr。
      auto& ret = make<ImplicitInitExpr>();
      ret.type = to;
      return &ret;
    }

    Expr lft;
    lft.type = to;
    lft.cate = Expr::Cate::kLValue;
    return assigment_cast(&lft, self(init));
  }

  // https://zh.cppreference.com/w/c/language/array_initialization
  if (auto arrTy = to.texp->dcst<ArrayType>()) {
    if (auto p = init->dcst<ImplicitInitExpr>()) {
      p->type = to;
      return p;
    }

    // 从花括号环绕列表初始化
    if (auto initList = init->dcst<InitListExpr>()) {
      auto [ret, _] = infer_initlist(initList->list, 0, to);
      return ret;
    }

    // 从字符串初始化
    if (to.spec == Type::Spec::kChar) {
      init = self(init);

      auto p = init->type.texp->dcst<ArrayType>();
      if (!p || p->sub != nullptr || init->type.spec != Type::Spec::kChar)
        ABORT();
      if (arrTy->len == -1)
        arrTy->len = p->len;
      else
        p->len = arrTy->len;

      return init;
    }

    ABORT();
  }

  ABORT();
}

std::pair<Expr*, std::size_t>
Typing::infer_initlist(const std::vector<Expr*>& list,
                       std::size_t begin,
                       const Type& to)
{
  if (to.texp == nullptr) {
    if (begin == list.size())
      return { nullptr, begin };

    auto ret = infer_init(list[begin], to);
    return { ret, begin + 1 };
  }

  if (auto arrTy = to.texp->dcst<ArrayType>()) {
    auto& ret = make<InitListExpr>();
    ret.type = to;
    ret.type.qual = Type::Qual::kNone;
    ret.cate = Expr::Cate::kRValue;

    Type elemTy = to;
    elemTy.texp = arrTy->sub;

    if (arrTy->len == ArrayType::kUnLen) {
      arrTy->len = 0;
      while (begin < list.size()) {
        auto [expr, next] = infer_initlist(list, begin, elemTy);
        ret.list.push_back(expr);
        begin = next;
        ++arrTy->len;
      }
    }

    else {
      for (int i = 0; i < arrTy->len; ++i) {
        if (begin == list.size())
          break;
        auto [expr, next] = infer_initlist(list, begin, elemTy);
        ret.list.push_back(expr);
        begin = next;
      }
    }

    return { &ret, begin };
  }

  ABORT();
}

} // namespace asg
