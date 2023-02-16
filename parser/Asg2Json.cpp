#include "Asg2Json.hpp"

#define self (*this)

namespace asg {

json::Object
Asg2Json::operator()(TranslationUnit& tu)
{
  json::Object ret;

  ret["kind"] = "TranslationUnitDecl";

  json::Array inner;
  for (auto&& i : tu)
    inner.push_back(self(i));
  ret["inner"] = std::move(inner);

  return ret;
}

//==============================================================================
// 表达式
//==============================================================================

json::Object
Asg2Json::operator()(Expr* obj)
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

  assert(false);
}

json::Object
Asg2Json::operator()(IntegerLiteral* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "IntegerLiteral";
  ret["value"] = std::to_string(obj->val);

  return ret;
}

json::Object
Asg2Json::operator()(StringLiteral* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "StringLiteral";
  ret["value"] = obj->val;

  return ret;
}

json::Object
Asg2Json::operator()(DeclRefExpr* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "DeclRefExpr";

  return ret;
}

json::Object
Asg2Json::operator()(UnaryExpr* obj)
{
  assert(obj->sub);

  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "UnaryOperator";

  switch (obj->op) {
    case UnaryExpr::kPos:
      ret["opcode"] = "+";
      break;

    case UnaryExpr::kNeg:
      ret["opcode"] = "-";
      break;

    case UnaryExpr::kNot:
      ret["opcode"] = "!";
      break;

    default:
      assert(false);
  }

  json::Array inner;
  inner.push_back(self(obj->sub));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(BinaryExpr* obj)
{
  assert(obj->lft && obj->rht);

  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "BinaryExpr";

  switch (obj->op) {
    case BinaryExpr::kMul:
      ret["opcode"] = "*";
      break;

    case BinaryExpr::kDiv:
      ret["opcode"] = "/";
      break;

    case BinaryExpr::kMod:
      ret["opcode"] = "%";
      break;

    case BinaryExpr::kAdd:
      ret["opcode"] = "+";
      break;

    case BinaryExpr::kSub:
      ret["opcode"] = "-";
      break;

    case BinaryExpr::kGt:
      ret["opcode"] = ">";
      break;

    case BinaryExpr::kLt:
      ret["opcode"] = "<";
      break;

    case BinaryExpr::kGe:
      ret["opcode"] = ">=";
      break;

    case BinaryExpr::kLe:
      ret["opcode"] = "<=";
      break;

    case BinaryExpr::kEq:
      ret["opcode"] = "==";
      break;

    case BinaryExpr::kNe:
      ret["opcode"] = "!=";
      break;

    case BinaryExpr::kAnd:
      ret["opcode"] = "&&";
      break;

    case BinaryExpr::kOr:
      ret["opcode"] = "||";
      break;

    case BinaryExpr::kAssign:
      ret["opcode"] = "=";
      break;

    case BinaryExpr::kComma:
      ret["opcode"] = ",";
      break;

    case BinaryExpr::kIndex:
      ret["opcode"] = "[]";
      break;

    default:
      assert(false);
  }

  json::Array inner;
  inner.push_back(self(obj->lft));
  inner.push_back(self(obj->rht));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(CallExpr* obj)
{
  assert(obj->head);

  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "CallExpr";

  json::Array inner;
  inner.push_back(self(obj->head));
  for (auto&& i : obj->args)
    inner.push_back(self(i));
  ret["inner"] = std::move(inner);

  return ret;
}

//==============================================================================
// 语句
//==============================================================================

json::Object
Asg2Json::operator()(Stmt* obj)
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

  if (typeid(*obj) == typeid(Stmt)) {
    json::Object ret;
    ret["kind"] = "NullStmt";
    return ret;
  }

  assert(false);
}

json::Object
Asg2Json::operator()(DeclStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "DeclStmt";

  json::Array inner;
  for (auto&& i : obj->decls)
    inner.push_back(self(i));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(ExprStmt* obj)
{
  assert(obj->expr);
  return self(obj->expr);
}

json::Object
Asg2Json::operator()(CompoundStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "CompoundStmt";

  json::Array inner;
  for (auto&& i : obj->subs)
    inner.push_back(self(i));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(IfStmt* obj)
{
  assert(obj->cond && obj->then);

  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "IfStmt";

  json::Array inner;
  inner.push_back(self(obj->cond));
  inner.push_back(self(obj->then));
  if (obj->else_)
    inner.push_back(self(obj->else_));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(WhileStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "WhileStmt";

  json::Array inner;
  inner.push_back(self(obj->cond));
  inner.push_back(self(obj->body));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(DoStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "DoStmt";

  json::Array inner;
  inner.push_back(self(obj->body));
  inner.push_back(self(obj->cond));
  ret["inner"] = std::move(inner);

  return ret;
}

json::Object
Asg2Json::operator()(BreakStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "BreakStmt";

  return ret;
}

json::Object
Asg2Json::operator()(ContinueStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "ContinueStmt";

  return ret;
}

json::Object
Asg2Json::operator()(ReturnStmt* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "ReturnStmt";

  json::Array inner;
  inner.push_back(self(obj->expr));
  ret["inner"] = std::move(inner);

  return ret;
}

//==============================================================================
// 声明
//==============================================================================

json::Object
Asg2Json::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  assert(false);
}

json::Object
Asg2Json::operator()(VarDecl* obj)
{
  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "VarDecl";

  ret["name"] = obj->name;

  return ret;
}

json::Object
Asg2Json::operator()(FunctionDecl* obj)
{
  assert(obj->body);

  json::Object ret;
  WalkedGuard guard(self, obj);

  ret["kind"] = "FunctionDecl";

  ret["name"] = obj->name;

  json::Array inner;
  for (auto&& i : obj->params)
    inner.push_back(self(i));
  inner.push_back(self(obj->body));
  ret["inner"] = std::move(inner);

  return ret;
}

}
