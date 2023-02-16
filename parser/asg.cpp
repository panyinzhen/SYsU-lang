#include "asg.hpp"

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

  abort();
}

Expr*
TypeInfer::operator()(IntegerLiteral* obj)
{
  obj->type.cate = Type::kPrvalue;
  obj->type.specs.isConst = 1;
  obj->type.specs.base = Type::Specs::kInt;
  obj->type.texp = nullptr;
  return obj;
}

Expr*
TypeInfer::operator()(StringLiteral* obj)
{
  obj->type.cate = Type::kPrvalue;
  obj->type.specs.isConst = 1;
  obj->type.specs.base = Type::Specs::kChar;
  obj->type.texp = nullptr;
  return obj;
}

Expr*
TypeInfer::operator()(DeclRefExpr* obj)
{
}

Expr*
TypeInfer::operator()(UnaryExpr* obj)
{
}

Expr*
TypeInfer::operator()(BinaryExpr* obj)
{
}

Expr*
TypeInfer::operator()(CallExpr* obj)
{
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

}
