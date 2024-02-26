#include "Json2Asg.hpp"

namespace asg {

namespace {

std::size_t
jobj_id(const llvm::json::Object& jobj)
{
  auto id = jobj.getString("id");
  ASSERT(id);
  ASSERT(id->starts_with("0x"));
  auto hexStr = id->substr(2).str();
  return std::stoull(hexStr, nullptr, 16);
}

} // namespace

TranslationUnit
Json2Asg::operator()(const llvm::json::Value& jval)
{
  auto jobj = jval.getAsObject();
  ASSERT(jobj);
  ASSERT(jobj->getString("kind") == "TranslationUnitDecl");

  auto inner = jobj->getArray("inner");
  ASSERT(inner);

  TranslationUnit ret;
  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);
    if (auto p = decl(*jobj))
      ret.push_back(p);
  }

  return ret;
}

Type
Json2Asg::gety(const llvm::json::Object& jobj)
{
  auto a = jobj.getObject("type");
  ASSERT(a);
  auto b = a->getString("qualType");
  ASSERT(b);
  auto texpStr = b->str();

  auto iter = mTyMap.find(texpStr);
  if (iter != mTyMap.end())
    return iter->second;

  Type ty;
  auto s = parse_type(texpStr.c_str(), ty);
  ASSERT(s && *s == '\0');
  mTyMap.emplace(texpStr, ty);
  return ty;
}

Decl*
Json2Asg::decl(const llvm::json::Object& jobj)
{
  auto kind = jobj.getString("kind");
  ASSERT(kind);

  if (kind == "TypedefDecl")
    return nullptr;

  if (kind == "VarDecl")
    return var_decl(jobj);

  if (kind == "FunctionDecl")
    return function_decl(jobj);

  ABORT(); // 未知的节点种类
}

VarDecl*
Json2Asg::var_decl(const llvm::json::Object& jobj)
{
  auto obj = make<VarDecl>(jobj_id(jobj));
  obj->name = jobj.getString("name").value();
  obj->type = gety(jobj);

  auto inner = jobj.getArray("inner");
  if (inner) {
    for (auto&& jval : *inner) {
      auto jobj = jval.getAsObject();
      ASSERT(jobj);
      if (auto p = expr(*jobj))
        obj->init = p;
    }
  } else
    obj->init = nullptr;

  return obj;
}

FunctionDecl*
Json2Asg::function_decl(const llvm::json::Object& jobj)
{
  auto obj = make<FunctionDecl>(jobj_id(jobj));
  obj->name = jobj.getString("name").value();
  obj->type = gety(jobj);

  auto inner = jobj.getArray("inner");
  // if(!inner)
  //   return nullptr;
  ASSERT(inner);
  cur_func = obj;

  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);

    auto kind = jobj->getString("kind");
    if (kind == "ParmVarDecl") {
      obj->params.push_back(var_decl(*jobj));
      continue;
    }

    if (kind == "CompoundStmt") {
      ASSERT(obj->body == nullptr);
      obj->body = compound_stmt(*jobj);
      continue;
    }

    ABORT();
  }

  return obj;
}

Expr*
Json2Asg::expr(const llvm::json::Object& jobj)
{
  auto kind = jobj.getString("kind");
  ASSERT(kind);

  if (kind == "BinaryOperator")
    return binary_expr(jobj);

  if (kind == "ImplicitCastExpr")
    return implicit_cast_expr(jobj);

  if (kind == "DeclRefExpr")
    return declref_expr(jobj);

  if (kind == "IntegerLiteral")
    return integer_literal(jobj);

  return nullptr;
}

BinaryExpr*
Json2Asg::binary_expr(const llvm::json::Object& jobj)
{
  auto obj = make<BinaryExpr>(jobj_id(jobj));
  obj->type = gety(jobj);
  obj->cate = getvc(jobj);

  auto a = jobj.getString("opcode");
  ASSERT(a);
  auto op = a->str();
  if (op == "*")
    obj->op = BinaryExpr::Op::kMul;
  else if (op == "/")
    obj->op = BinaryExpr::Op::kDiv;
  else if (op == "%")
    obj->op = BinaryExpr::Op::kMod;
  else if (op == "+")
    obj->op = BinaryExpr::Op::kAdd;
  else if (op == "-")
    obj->op = BinaryExpr::Op::kSub;
  else if (op == ">")
    obj->op = BinaryExpr::Op::kGt;
  else if (op == "<")
    obj->op = BinaryExpr::Op::kLt;
  else if (op == ">=")
    obj->op = BinaryExpr::Op::kGe;
  else if (op == "<=")
    obj->op = BinaryExpr::Op::kLe;
  else if (op == "==")
    obj->op = BinaryExpr::Op::kEq;
  else if (op == "!=")
    obj->op = BinaryExpr::Op::kNe;
  else if (op == "&&")
    obj->op = BinaryExpr::Op::kAnd;
  else if (op == "||")
    obj->op = BinaryExpr::Op::kOr;
  else if (op == "=")
    obj->op = BinaryExpr::Op::kAssign;
  else if (op == ",")
    obj->op = BinaryExpr::Op::kComma;
  else
    obj->op = BinaryExpr::Op::kINVALID;
  // TODO: BinaryExpr::Op::kIndex

  auto inner = jobj.getArray("inner");
  ASSERT(inner);

  int index = 0;
  for (auto&& jval : *inner) {
    if (auto p = jval.getAsObject()) {
      if (index == 0)
        obj->lft = expr(*p);
      else
        obj->rht = expr(*p);
      index++;
    }
  }
  return obj;
}

Expr::Cate
Json2Asg::getvc(const llvm::json::Object& jobj)
{
  auto p = jobj.getString("valueCategory");
  ASSERT(p);
  auto valueCategory = p->str();
  if (valueCategory == "lvalue")
    return Expr::Cate::kLValue;
  return Expr::Cate::kRValue;
}

ImplicitCastExpr*
Json2Asg::implicit_cast_expr(const llvm::json::Object& jobj)
{
  auto obj = make<ImplicitCastExpr>(jobj_id(jobj));
  obj->type = gety(jobj);
  obj->cate = getvc(jobj);

  auto a = jobj.getString("castKind");
  ASSERT(a);
  auto castkind = a->str();
  if (castkind == "LValueToRValue")
    obj->kind = ImplicitCastExpr::kLValueToRValue;
  else if (castkind == "IntegralCast")
    obj->kind = ImplicitCastExpr::kIntegralCast;
  else if (castkind == "ArrayToPointerDecay")
    obj->kind = ImplicitCastExpr::kArrayToPointerDecay;
  else if (castkind == "FunctionToPointerDecay")
    obj->kind = ImplicitCastExpr::kFunctionToPointerDecay;
  else if (castkind == "NoOp")
    obj->kind = ImplicitCastExpr::kNoOp;
  else
    obj->kind = ImplicitCastExpr::kINVALID;

  auto inner = jobj.getArray("inner");
  ASSERT(inner);
  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);
    if (auto p = expr(*jobj))
      obj->sub = p;
  }
  return obj;
}

DeclRefExpr*
Json2Asg::declref_expr(const llvm::json::Object& jobj)
{
  auto obj = make<DeclRefExpr>(jobj_id(jobj));
  obj->type = gety(jobj);
  obj->cate = getvc(jobj);

  auto a = jobj.getObject("referencedDecl");
  ASSERT(a);
  auto id = jobj_id(*a);
  obj->decl = dynamic_cast<Decl*>(mIdMap[id]);

  return obj;
}

IntegerLiteral*
Json2Asg::integer_literal(const llvm::json::Object& jobj)
{
  auto obj = make<IntegerLiteral>(jobj_id(jobj));
  obj->type = gety(jobj);
  obj->cate = getvc(jobj);

  auto a = jobj.getString("value");
  ASSERT(a);
  auto val = a->str();
  obj->val = strtoll(val.c_str(), NULL, 10);
  ASSERT(errno != ERANGE);
  return obj;
}

ExprStmt*
Json2Asg::expr_stmt(const llvm::json::Object& jobj)
{
  auto obj = make<ExprStmt>(jobj_id(jobj));
  obj->expr = expr(jobj);
  return obj;
}

Stmt*
Json2Asg::stmt(const llvm::json::Object& jobj)
{
  auto kind = jobj.getString("kind");
  ASSERT(kind);

  if (kind == "DeclStmt")
    return decl_stmt(jobj);

  if (kind == "ExprStmt")
    return nullptr;

  if (kind == "ReturnStmt")
    return return_stmt(jobj);

  if (kind == "BinaryOperator")
    return expr_stmt(jobj);

  return nullptr;
}

CompoundStmt*
Json2Asg::compound_stmt(const llvm::json::Object& jobj)
{
  auto obj = make<CompoundStmt>(jobj_id(jobj));
  auto inner = jobj.getArray("inner");
  if (!inner)
    return nullptr;

  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);
    if (auto p = stmt(*jobj))
      obj->subs.emplace_back(p);
  }

  return obj;
}

DeclStmt*
Json2Asg::decl_stmt(const llvm::json::Object& jobj)
{
  auto obj = make<DeclStmt>(jobj_id(jobj));
  auto inner = jobj.getArray("inner");
  if (!inner)
    return nullptr;

  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);
    if (auto p = decl(*jobj))
      obj->decls.emplace_back(p);
  }

  return obj;
}

ReturnStmt*
Json2Asg::return_stmt(const llvm::json::Object& jobj)
{
  auto obj = make<ReturnStmt>(jobj_id(jobj));
  auto inner = jobj.getArray("inner");
  ASSERT(inner);
  // if(!inner)
  //   return nullptr;

  for (auto&& jval : *inner) {
    auto jobj = jval.getAsObject();
    ASSERT(jobj);
    if (auto p = expr(*jobj))
      obj->expr = p;
  }
  obj->func = cur_func;

  return obj;
}

// ========================================================================== //
// 类型字符串解析
// ========================================================================== //

/**
 * 本实验中 `clang -cc1 -ast-dump=json` 输出的类型字符串语法：
 *
 *  type
 *    : base+ texp '\0'
 *    ;
 *  base
 *    : 'void' | 'char' | 'int' | 'long' | 'const'
 *    ;
 *  texp
 *    : texp_2
 *    ;
 *  texp_0
 *    : %empty
 *    | '[' oct ']'
 *    | '(' args ')'
 *    | '(' texp ')'
 *    ;
 *  texp_1
 *    ; texp_0+
 *    ;
 *  texp_2
 *    : texp_1
 *    | '*' texp_2
 *    ;
 *  args
 *    : %empty
 *    | type (',' type)*
 *    ;
 *  oct
 *    : [0-9]+
 *    ;
 */

namespace {

const char*
match(const char* s, const char* p)
{
  while (*p) {
    if (*s != *p)
      return nullptr;
    ++s, ++p;
  }
  return s;
}

const char*
skip_spaces(const char* s)
{
  while (*s == ' ')
    ++s;
  return s;
}

const char*
parse_base(const char* s, Type& v)
{
  if (auto p = match(s, "void")) {
    ASSERT(v.spec == Type::Spec::kINVALID);
    v.spec = Type::Spec::kVoid;
    return p;
  }
  if (auto p = match(s, "char")) {
    ASSERT(v.spec == Type::Spec::kINVALID);
    v.spec = Type::Spec::kChar;
    return p;
  }
  if (auto p = match(s, "int")) {
    ASSERT(v.spec == Type::Spec::kINVALID);
    v.spec = Type::Spec::kInt;
    return p;
  }
  if (auto p = match(s, "long")) {
    if (v.spec == Type::Spec::kINVALID)
      v.spec = Type::Spec::kLong;
    else if (v.spec == Type::Spec::kLong)
      v.spec = Type::Spec::kLongLong;
    else
      ABORT();
    return p;
  }
  if (auto p = match(s, "const")) {
    v.qual = Type::Qual::kConst;
    return p;
  }
  return nullptr;
}

const char*
parse_oct(const char* s, std::uint32_t& v)
{
  if (*s < '0' || *s > '9')
    return nullptr;
  std::uint32_t num = 0;
  while ('0' <= *s && *s <= '9') {
    num = num * 10 + (*s - '0');
    ++s;
  }
  v = num;
  return s;
}

/// 把 \p texp 的里面翻出来，因为 C 的类型表达式与表达式内外顺序是相反的。
TypeExpr*
turn_texp(TypeExpr* texp)
{
  if (texp->sub == nullptr)
    return texp;
  auto innerMost = turn_texp(texp->sub);
  innerMost->sub = texp;
  texp->sub = nullptr;
  return innerMost;
}

} // namespace

const char*
Json2Asg::parse_type(const char* s, Type& v)
{
  s = parse_base(s, v);
  if (!s)
    return nullptr;

  while (true) {
    auto p = parse_base(skip_spaces(s), v);
    if (!p)
      break;
    s = p;
  }

  s = parse_texp(skip_spaces(s), v.texp);
  if (v.texp)
    v.texp = turn_texp(v.texp); // 将类型表达式内外翻转

  return s;
}

const char*
Json2Asg::parse_texp(const char* s, TypeExpr*& v)
{
  return parse_texp_2(s, v);
}

const char*
Json2Asg::parse_texp_0(const char* s, TypeExpr*& v)
{
RULE_1:
  if (auto p = match(s, "[")) {
    std::uint32_t len;
    p = parse_oct(skip_spaces(p), len);
    if (!p)
      goto RULE_2;
    p = match(skip_spaces(p), "]");
    if (!p)
      goto RULE_2;
    auto& arrTy = mMgr.make<ArrayType>();
    arrTy.len = len;
    v = &arrTy;
    return p;
  }

RULE_2:
  if (auto p = match(s, "(")) {
    std::vector<Decl*> params;
    p = parse_args(skip_spaces(p), params);
    if (!p)
      goto RULE_3;
    p = match(skip_spaces(p), ")");
    if (!p)
      goto RULE_3;
    auto& funTy = mMgr.make<FunctionType>();
    funTy.params = std::move(params);
    v = &funTy;
    return p;
  }

RULE_3:
  if (auto p = match(s, "(")) {
    p = parse_texp(skip_spaces(p), v);
    if (!p)
      goto EMPTY;
    p = match(skip_spaces(p), ")");
    if (!p)
      goto EMPTY;
    return p;
  }

EMPTY:
  v = nullptr;
  return s;
}

const char*
Json2Asg::parse_texp_1(const char* s, TypeExpr*& v)
{
  TypeExpr* texp;
  auto p = parse_texp_0(s, texp);
  if (!p)
    return nullptr;
  if (s != p) {
    while (true) {
      s = p;
      TypeExpr* sup;
      p = parse_texp_0(skip_spaces(p), sup);
      if (!p)
        return nullptr;
      if (s == p)
        break;
      sup->sub = texp;
      texp = sup;
    }
  }
  v = texp;
  return s;
}

const char*
Json2Asg::parse_texp_2(const char* s, TypeExpr*& v)
{
RULE_1:
  if (auto p = parse_texp_1(s, v))
    return p;

RULE_2:
  if (auto p = match(s, "*")) {
    TypeExpr* texp;
    p = parse_texp_2(skip_spaces(p), texp);
    if (!p)
      goto EMPTY;
    v = &mMgr.make<PointerType>();
    v->sub = texp;
    return p;
  }

EMPTY:
  return nullptr;
}

const char*
Json2Asg::parse_args(const char* s, std::vector<Decl*>& v)
{
  Type ty;
  auto p = parse_type(s, ty);
  if (!p)
    return s;
  s = p;
  if (ty.texp)
    ty.texp = turn_texp(ty.texp); // 将类型表达式内外翻转

  std::vector<Decl*> params;
  auto& decl = mMgr.make<Decl>();
  decl.type = ty;
  params.push_back(&decl);

  while (true) {
    p = match(skip_spaces(s), ",");
    if (!p)
      break;
    Type ty2;
    p = parse_type(skip_spaces(p), ty2);
    if (!p)
      return nullptr;
    s = p;
    if (ty2.texp)
      ty2.texp = turn_texp(ty2.texp); // 将类型表达式内外翻转

    auto& decl = mMgr.make<Decl>();
    decl.type = ty2;
    v.push_back(&decl);
  }

  v = std::move(params);
  return s;
}

} // namespace asg
