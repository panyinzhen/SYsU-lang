#pragma once

#include "CParser.h"
#include <llvm/ADT/StringMap.h>
#include <memory>
#include <string>
#include <unordered_map>
#include <vector>

namespace asg {

class Obj
{
public:
  class Mgr : public std::vector<std::unique_ptr<Obj>>
  {
  public:
    template<typename T, typename... Args>
    T& make(Args... args)
    {
      auto ptr = std::make_unique<T>(args...);
      auto& obj = *ptr;
      emplace_back(std::move(ptr));
      return obj;
    }
  };

  template<typename... Ts>
  class Ptr;

public:
  virtual ~Obj() = default;

public:
  template<typename T>
  T* dcast()
  {
    return dynamic_cast<T>(this);
  }

  template<typename T>
  T* rcast()
  {
    return reinterpret_cast<T>(this);
  }
};

template<bool...>
struct bool_pack;

template<bool... v>
using all_true = std::is_same<bool_pack<true, v...>, bool_pack<v..., true>>;

template<typename T, typename... Ts>
using is_one_of = std::disjunction<std::is_same<T, Ts>...>;

template<typename... Ts>
class Obj::Ptr
{
  static_assert(all_true<std::is_convertible_v<Ts*, Obj*>...>::value);

public:
  Obj* _{ nullptr };

public:
  Ptr() {}

  Ptr(std::nullptr_t) {}

  template<typename T, typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
  Ptr(T* p)
    : _(p)
  {
  }

public:
  template<typename T, typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
  T* dcast()
  {
    return dynamic_cast<T>(this);
  }

  template<typename T, typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
  T* rcast()
  {
    return reinterpret_cast<T>(this);
  }
};

//==============================================================================
// 类型
//==============================================================================

struct TypeExpr;
struct Expr;

struct Type
{
  struct Specs
  {
    enum
    {
      kINVALID,
      kVoid,
      kChar,
      kInt,
      kLong,
      kLongLong,
    };

    unsigned isConst : 1, base : 3;

    Specs()
      : isConst(false)
      , base(kINVALID)
    {
    }
  } specs;
  TypeExpr* texp;
};

struct TypeExpr : public Obj
{
  TypeExpr* sub;
};

struct PointerType : public TypeExpr
{};

struct ArrayType : public TypeExpr
{
  Expr* len;
};

struct FunctionType : public TypeExpr
{
  std::vector<Type> params;
};

//==============================================================================
// 表达式
//==============================================================================

struct Decl;

struct Expr : public Obj
{
  Type type;
};

struct IntegerLiteral : public Expr
{
  int val;
};

struct StringLiteral : public Expr
{
  std::string val;
};

struct DeclRefExpr : public Expr
{
  Decl* decl;
};

struct UnaryExpr : public Expr
{
  enum Op
  {
    kPos,
    kNeg,
    kNot
  };

  Op op;
  Expr* sub;
};

struct BinaryExpr : public Expr
{
  enum Op
  {
    kMul,
    kDiv,
    kMod,
    kAdd,
    kSub,
    kGt,
    kLt,
    kGe,
    kLe,
    kEq,
    kNe,
    kAnd,
    kOr,
    kAssign,
    kComma,
    kIndex,
  };

  Op op;
  Expr *lft, *rht;
};

struct CallExpr : public Expr
{
  Expr* head;
  std::vector<Expr*> args;
};

//==============================================================================
// 语句
//==============================================================================

struct Stmt : public Obj
{};

struct DeclStmt : public Stmt
{
  std::vector<Decl*> decls;
};

struct ExprStmt : public Stmt
{
  Expr* expr;
};

struct CompoundStmt : public Stmt
{
  std::vector<Stmt*> subs;
};

struct IfStmt : public Stmt
{
  Expr* cond;
  Stmt *then, *else_;
};

struct WhileStmt : public Stmt
{
  Expr* cond;
  Stmt* body;
};

struct DoStmt : public Stmt
{
  Stmt* body;
  Expr* cond;
};

struct BreakStmt : public Stmt
{
  Stmt* loop;
};

struct ContinueStmt : public Stmt
{
  Stmt* loop;
};

struct ReturnStmt : public Stmt
{
  Expr* expr;
};

//==============================================================================
// 声明
//==============================================================================

struct Decl : public Obj
{
  Type type;
  std::string name;
};

struct InitList : public Obj
{
  struct Elem
  {
    Expr* des;
    Obj::Ptr<Expr, InitList> val;
  };
  std::vector<Elem> list;
};

struct VarDecl : public Decl
{
  Obj::Ptr<Expr, InitList> init;
};

struct FunctionDecl : public Decl
{
  std::vector<VarDecl*> params;
  CompoundStmt* body;
};

using TranslationUnit = std::vector<Decl*>;

//==============================================================================
// Ast2Asg
//==============================================================================

using ast = antlr_c::CParser;

class Ast2Asg
{
public:
  Obj::Mgr _mgr;

public:
  TranslationUnit operator()(ast::TranslationUnitContext* ctx);

private:
  struct LocalDecls : public std::unordered_map<std::string, Decl*>
  {
    Ast2Asg& _self;
    LocalDecls* _prev;

    LocalDecls(Ast2Asg& self)
      : _self(self)
      , _prev(self._localDecls)
    {
      _self._localDecls = this;
    }

    ~LocalDecls() { _self._localDecls = _prev; }

    Decl* resolve(const std::string& name);
  };

  LocalDecls* _localDecls{ nullptr };

  struct CurrentLoop
  {
    Ast2Asg& _self;
    Stmt* _prev;

    CurrentLoop(Ast2Asg& self, Stmt* loop)
      : _self(self)
      , _prev(self._currentLoop)
    {
      _self._currentLoop = loop;
    }

    ~CurrentLoop() { _self._currentLoop = _prev; }
  };

  Stmt* _currentLoop{ nullptr };

private:
  //============================================================================
  // 类型
  //============================================================================

  Type::Specs operator()(ast::DeclarationSpecifiersContext* ctx);

  Type::Specs operator()(ast::DeclarationSpecifiers2Context* ctx);

  std::pair<TypeExpr*, std::string> operator()(ast::DeclaratorContext* ctx);

  std::pair<TypeExpr*, std::string> operator()(
    ast::DirectDeclaratorContext* ctx);

  TypeExpr* operator()(ast::AbstractDeclaratorContext* ctx);

  TypeExpr* operator()(ast::DirectAbstractDeclaratorContext* ctx);

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

  VarDecl* operator()(ast::InitDeclaratorContext* ctx, Type::Specs specs);

  VarDecl* operator()(ast::ParameterDeclarationContext* ctx);

  Obj::Ptr<Expr, InitList> operator()(ast::InitializerContext* ctx);

  InitList* operator()(ast::InitializerListContext* ctx);

private:
  template<typename T, typename... Args>
  T& make(Args... args)
  {
    return _mgr.make<T>(args...);
  }
};

//==============================================================================
// Asg2Json
//==============================================================================

}
