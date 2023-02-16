#pragma once

#include <memory>
#include <string>
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
    return dynamic_cast<T*>(this);
  }

  template<typename T>
  T& rcast()
  {
    return *reinterpret_cast<T*>(this);
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

  operator bool() { return _ != nullptr; }

public:
  template<typename T, typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
  T* dcast()
  {
    return dynamic_cast<T*>(_);
  }

  template<typename T, typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
  T& rcast()
  {
    return *reinterpret_cast<T>(_);
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
  std::vector<Obj::Ptr<Expr, InitList>> list;
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
// 工具类
//==============================================================================

class TypeInfer
{
public:
  Obj::Mgr _mgr;

private:
  template<typename T, typename... Args>
  T& make(Args... args)
  {
    return _mgr.make<T>(args...);
  }
};

}
