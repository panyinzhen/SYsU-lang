#pragma once

#include "SYsU_langParser.h"
#include "asg.hpp"

namespace asg {

using ast = SYsU_langParser;

class Ast2Asg
{
public:
  Obj::Mgr& mMgr;

  Ast2Asg(Obj::Mgr& mgr)
    : mMgr(mgr)
  {
  }

  TranslationUnit operator()(ast::TranslationUnitContext* ctx);

private:
  using SpecQual = std::pair<Type::Spec, Type::Qual>;

  struct Symtbl;
  Symtbl* mSymtbl{ nullptr };

  struct CurrentLoop;
  Stmt* mCurrentLoop{ nullptr };

  FunctionDecl* mCurrentFunc{ nullptr };

  template<typename T, typename... Args>
  T& make(Args... args)
  {
    return mMgr.make<T>(args...);
  }

  //============================================================================
  // 类型
  //============================================================================

  SpecQual operator()(ast::DeclarationSpecifiersContext* ctx);

  SpecQual operator()(ast::DeclarationSpecifiers2Context* ctx);

  std::pair<TypeExpr*, std::string> operator()(ast::DeclaratorContext* ctx,
                                               TypeExpr* sub);

  std::pair<TypeExpr*, std::string> operator()(
    ast::DirectDeclaratorContext* ctx,
    TypeExpr* sub);

  TypeExpr* operator()(ast::AbstractDeclaratorContext* ctx, TypeExpr* sub);

  TypeExpr* operator()(ast::DirectAbstractDeclaratorContext* ctx,
                       TypeExpr* sub);

  //============================================================================
  // 表达式
  //============================================================================

  Expr* operator()(ast::ExpressionContext* ctx);

  Expr* operator()(ast::AssignmentExpressionContext* ctx);

  Expr* operator()(ast::LogicalOrExpressionContext* ctx);

  Expr* operator()(ast::LogicalAndExpressionContext* ctx);

  Expr* operator()(ast::EqualityExpressionContext* ctx);

  Expr* operator()(ast::RelationalExpressionContext* ctx);

  Expr* operator()(ast::AdditiveExpressionContext* ctx);

  Expr* operator()(ast::MultiplicativeExpressionContext* ctx);

  Expr* operator()(ast::UnaryExpressionContext* ctx);

  Expr* operator()(ast::PostfixExpressionContext* ctx);

  Expr* operator()(ast::PrimaryExpressionContext* ctx);

  Expr* operator()(ast::InitializerContext* ctx);

  //============================================================================
  // 语句
  //============================================================================

  Stmt* operator()(ast::StatementContext* ctx);

  CompoundStmt* operator()(ast::CompoundStatementContext* ctx);

  Stmt* operator()(ast::ExpressionStatementContext* ctx);

  Stmt* operator()(ast::SelectionStatementContext* ctx);

  Stmt* operator()(ast::IterationStatementContext* ctx);

  Stmt* operator()(ast::JumpStatementContext* ctx);

  //============================================================================
  // 声明
  //============================================================================

  std::vector<Decl*> operator()(ast::DeclarationContext* ctx);

  FunctionDecl* operator()(ast::FunctionDefinitionContext* ctx);

  Decl* operator()(ast::InitDeclaratorContext* ctx, SpecQual sq);

  VarDecl* operator()(ast::ParameterDeclarationContext* ctx);
};

} // namespace asg
