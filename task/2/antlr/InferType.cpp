#include "InferType.hpp"
#include <cassert>

#define self (*this)

namespace asg {

void
InferType::operator()(TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
}

//==============================================================================
// 表达式
//==============================================================================

Expr*
InferType::operator()(Expr* obj)
{
  if (auto p = obj->dcast<IntegerLiteral>())
    return self(p);

  if (auto p = obj->dcast<StringLiteral>())
    return self(p);

  if (auto p = obj->dcast<DeclRefExpr>())
    return self(p);

  if (auto p = obj->dcast<ParenExpr>())
    return self(p);

  if (auto p = obj->dcast<UnaryExpr>())
    return self(p);

  if (auto p = obj->dcast<BinaryExpr>())
    return self(p);

  if (auto p = obj->dcast<CallExpr>())
    return self(p);

  if (auto p = obj->dcast<ImplicitCastExpr>())
    return self(p->sub);

  ASG_ABORT();
}

Expr*
InferType::operator()(IntegerLiteral* obj)
{
  obj->type.cate = Type::kRValue;
  // obj->type.specs.isConst = 1;

  if (obj->val <= INT32_MAX)
    obj->type.specs.base = Type::Specs::kInt;
  else
    obj->type.specs.base = Type::Specs::kLongLong;

  obj->type.texp = nullptr;

  return obj;
}

Expr*
InferType::operator()(StringLiteral* obj)
{
  obj->type.cate = Type::kRValue;
  obj->type.specs.isConst = 1;
  obj->type.specs.base = Type::Specs::kChar;

  if (obj->type.texp == nullptr ||
      typeid(*obj->type.texp) != typeid(ArrayType)) {
    auto& t = make<ArrayType>();
    obj->type.texp = &t;
  }

  auto& arrType = obj->type.texp->rcast<ArrayType>();
  arrType.sub = nullptr;
  arrType.len = obj->val.size() + 1;

  return obj;
}

Expr*
InferType::operator()(DeclRefExpr* obj)
{
  assert(obj->decl);
  WalkedGuard walked(self, obj);

  // self(obj->decl);
  obj->type = obj->decl->type;
  obj->type.cate = Type::kLValue;

  return obj;
}

Expr*
InferType::operator()(ParenExpr* obj)
{
  if (!obj->sub)
    ASG_ABORT();
  obj->sub = self(obj->sub);
  obj->type = obj->sub->type;
  return obj;
}

Expr*
InferType::operator()(UnaryExpr* obj)
{
  assert(obj->sub);
  WalkedGuard walked(self, obj);

  auto sub = self(obj->sub);

  // 左值要先转成右值，然后进行整数提升
  sub = ensure_rvalue(sub);
  sub = promote_integer(sub);

  obj->sub = sub;
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
      ASG_ABORT();
  }

  return obj;
}

Expr*
InferType::operator()(BinaryExpr* obj)
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

      // 关系运算符的结果一定是 int 类型
      obj->type.specs.isConst = 0;
      obj->type.specs.base = Type::Specs::kInt;
      obj->type.cate = Type::kRValue;
      obj->type.texp = nullptr;
    } break;

    case BinaryExpr::kAssign: {
      if (lft->type.specs.isConst)
        ASG_ABORT();
      rht = assigment_cast(lft->type, rht);

      obj->type = rht->type;
    } break;

    case BinaryExpr::kComma: {
      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->type = rht->type;
    } break;

    case BinaryExpr::kIndex: {
      auto arrayType = lft->type.texp->dcast<ArrayType>();
      if (arrayType == nullptr)
        ASG_ABORT();

      if (rht->type.texp != nullptr)
        ASG_ABORT();
      switch (rht->type.specs.base) {
        case Type::Specs::kChar:
        case Type::Specs::kInt:
        case Type::Specs::kLong:
        case Type::Specs::kLongLong:
          break;

        default:
          ASG_ABORT();
      }

      lft = ensure_rvalue(lft);
      rht = ensure_rvalue(rht);

      obj->type.cate = Type::kLValue; // !
      obj->type.specs = lft->type.specs;
      obj->type.texp = arrayType->sub;
    } break;

    default:
      ASG_ABORT();
  }

  obj->lft = lft;
  obj->rht = rht;
  return obj;
}

Expr*
InferType::operator()(CallExpr* obj)
{
  assert(obj->head);

  obj->head = self(obj->head);
  auto fexp = dynamic_cast<FunctionType*>(obj->head->type.texp);
  if (fexp == nullptr)
    ASG_ABORT();

  auto& f2p = make<ImplicitCastExpr>();
  f2p.kind = f2p.kFunctionToPointerDecay;
  f2p.type = obj->head->type;
  f2p.sub = obj->head;
  obj->head = &f2p;

  if (fexp->params.size() != obj->args.size())
    ASG_ABORT();

  for (int i = fexp->params.size(); --i != -1;)
    obj->args[i] = assigment_cast(fexp->params[i]->type, self(obj->args[i]));

  obj->type.cate = Type::kRValue;
  obj->type.specs.isConst = 0;
  obj->type.specs.base = obj->head->type.specs.base;
  obj->type.texp = fexp->sub;

  return obj;
}

//==============================================================================
// 语句
//==============================================================================

void
InferType::operator()(Stmt* obj)
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

  ASG_ABORT();
}

void
InferType::operator()(DeclStmt* obj)
{
  for (auto&& i : obj->decls)
    self(i);
}

void
InferType::operator()(ExprStmt* obj)
{
  obj->expr = ensure_rvalue(self(obj->expr));
}

void
InferType::operator()(CompoundStmt* obj)
{
  for (auto&& i : obj->subs)
    self(i);
}

void
InferType::operator()(IfStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->then);
  if (obj->else_)
    self(obj->else_);
}

void
InferType::operator()(WhileStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
InferType::operator()(DoStmt* obj)
{
  obj->cond = ensure_rvalue(self(obj->cond));
  self(obj->body);
}

void
InferType::operator()(BreakStmt* obj)
{
}

void
InferType::operator()(ContinueStmt* obj)
{
}

void
InferType::operator()(ReturnStmt* obj)
{
  auto& ftype = obj->func->type;
  auto ftexp = dynamic_cast<FunctionType*>(ftype.texp);
  if (ftexp == nullptr || ftexp->sub != nullptr)
    ASG_ABORT();

  switch (ftype.specs.base) {
    case Type::Specs::kVoid: {
      if (obj->expr != nullptr)
        ASG_ABORT();
    } break;

    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong: {
      if (obj->expr == nullptr)
        ASG_ABORT();

      Type retType;
      retType.cate = Type::kLValue;
      retType.specs = ftype.specs;
      retType.texp = ftexp->sub;
      obj->expr = assigment_cast(retType, ensure_rvalue(self(obj->expr)));
    } break;

    default:
      ASG_ABORT();
  }
}

//==============================================================================
// 声明
//==============================================================================

void
InferType::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  ASG_ABORT();
}

void
InferType::operator()(VarDecl* obj)
{
  obj->type.cate = Type::kLValue;

  switch (obj->type.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      ASG_ABORT();
  }

  // 最多只能声明数值类型
  if (obj->init)
    obj->init = infer_init(obj->init, obj->type);
}

void
InferType::operator()(FunctionDecl* obj)
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
      ASG_ABORT();
  }

  // 必须为函数类型
  if (obj->type.texp == nullptr)
    ASG_ABORT();
  auto funcType = obj->type.texp->dcast<FunctionType>();
  if (funcType == nullptr)
    ASG_ABORT();

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
InferType::ensure_rvalue(Expr* exp)
{
  if (exp->type.texp->dcast<ArrayType>()) {
    auto& cst = make<ImplicitCastExpr>();
    cst.kind = cst.kArrayToPointerDecay;

    cst.type = exp->type;
    cst.type.cate = Type::kRValue;
    cst.type.specs.isConst = 0;

    cst.sub = exp;
    return &cst;
  }

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
      ASG_ABORT();
  }
}

Expr*
InferType::promote_integer(Expr* exp, int to)
{
  if (exp->type.texp != nullptr)
    ASG_ABORT();

  switch (exp->type.specs.base) {
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
      ASG_ABORT();
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
      if (!type_equal(at->params[i]->type, bt->params[i]->type))
        return false;
    }
    return true;
  }

  ASG_ABORT();
}

static bool
type_equal(const Type& a, const Type& b)
{
  if (a.specs.base != b.specs.base || a.specs.isConst != b.specs.isConst)
    return false;
  return typeexpr_equal(a.texp, b.texp);
}

Expr*
InferType::assigment_cast(const Type& lft, Expr* rht)
{
  if (lft.cate != Type::kLValue)
    ASG_ABORT();

  switch (lft.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      ASG_ABORT();
  }
  switch (rht->type.specs.base) {
    case Type::Specs::kChar:
    case Type::Specs::kInt:
    case Type::Specs::kLong:
    case Type::Specs::kLongLong:
      break;

    default:
      ASG_ABORT();
  }

  rht = ensure_rvalue(rht);

  if (lft.texp != nullptr) {
    if (!lft.texp->dcast<ArrayType>())
      ASG_ABORT();

    if (lft.specs.isConst) {
      auto& ccst = make<ImplicitCastExpr>();
      ccst.kind = ccst.kNoOp;
      ccst.sub = rht;
      ccst.type = rht->type;
      ccst.type.specs.isConst = 1;
      rht = &ccst;
    }

    if (!type_equal(lft, rht->type))
      ASG_ABORT();
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

Expr*
InferType::infer_init(Expr* init, const Type& to)
{
  // https://zh.cppreference.com/w/c/language/scalar_initialization
  if (to.texp == nullptr) {
    if (auto p = init->dcast<ImplicitInitExpr>()) {
      p->type = to;
      return p;
    }

    if (auto p = init->dcast<InitListExpr>()) {
      if (!p->list.empty())
        return infer_init(p->list[0], to);

      auto& ret = make<ImplicitInitExpr>();
      ret.type = to;
      return &ret;
    }

    return assigment_cast(to, self(init));
  }

  // https://zh.cppreference.com/w/c/language/array_initialization
  if (auto arrType = to.texp->dcast<ArrayType>()) {
    if (auto p = init->dcast<ImplicitInitExpr>()) {
      p->type = to;
      return p;
    }

    // 从花括号环绕列表初始化
    if (auto initList = init->dcast<InitListExpr>()) {
      auto [ret, _] = infer_initlist(initList->list, 0, to);
      return ret;
    }

    // 从字符串初始化
    if (to.specs.base == Type::Specs::kChar) {
      init = self(init);

      auto p = init->type.texp->dcast<ArrayType>();
      if (!p || p->sub != nullptr ||
          init->type.specs.base != Type::Specs::kChar)
        ASG_ABORT();
      if (arrType->len == -1)
        arrType->len = p->len;
      else
        p->len = arrType->len;

      return init;
    }

    ASG_ABORT();
  }

  ASG_ABORT();
}

std::pair<Expr*, std::size_t>
InferType::infer_initlist(const std::vector<Expr*>& list,
                          std::size_t begin,
                          const Type& to)
{
  if (to.texp == nullptr) {
    if (begin == list.size())
      return { nullptr, begin };

    auto ret = infer_init(list[begin], to);
    return { ret, begin + 1 };
  }

  if (auto arrType = to.texp->dcast<ArrayType>()) {
    auto& ret = make<InitListExpr>();
    ret.type = to;
    ret.type.specs.isConst = 0;

    Type subType;
    subType.cate = to.cate;
    subType.specs = to.specs;
    subType.texp = arrType->sub;
    ret.type.cate = Type::kRValue;

    if (arrType->len == -1) {
      arrType->len = 0;
      while (begin < list.size()) {
        auto [expr, next] = infer_initlist(list, begin, subType);
        ret.list.push_back(expr);
        begin = next;
        ++arrType->len;
      }
    }

    else {
      for (int i = 0; i < arrType->len; ++i) {
        if (begin == list.size())
          break;
        auto [expr, next] = infer_initlist(list, begin, subType);
        ret.list.push_back(expr);
        begin = next;
      }
    }

    return { &ret, begin };
  }

  ASG_ABORT();
}

}