#pragma once

#include <memory>
#include <string>
#include <unordered_set>
#include <vector>

namespace asg {

#define ASG_ABORT()                                                            \
  (fprintf(stderr, "aborted at %s:%d\n", __FILE__, __LINE__), abort())

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
struct Decl;

struct Type
{
  enum Category
  {
    kINVALID,
    kRValue,
    kLValue,
  } cate{ kINVALID };

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

  TypeExpr* texp{ nullptr };
};

struct TypeExpr : public Obj
{
  TypeExpr* sub{ nullptr };
};

struct ArrayType : public TypeExpr
{
  int len{ 0 }; /// 如果 len 为 -1 则表示不确定长度
};

struct FunctionType : public TypeExpr
{
  std::vector<Decl*> params;
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
  std::uint64_t val;
};

struct StringLiteral : public Expr
{
  std::string val;
};

struct DeclRefExpr : public Expr
{
  Decl* decl{ nullptr };
};

struct ParenExpr : public Expr
{
  Expr* sub{ nullptr };
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
  Expr* sub{ nullptr };
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
  Expr *lft{ nullptr }, *rht{ nullptr };
};

struct CallExpr : public Expr
{
  Expr* head{ nullptr };
  std::vector<Expr*> args;
};

struct InitListExpr : public Expr
{
  std::vector<Expr*> list;
};

struct ImplicitInitExpr : public Expr
{};

struct ImplicitCastExpr : public Expr
{
  enum
  {
    kINVALID,
    kLValueToRValue,
    kIntegralCast,
    kArrayToPointerDecay,
    kFunctionToPointerDecay,
    kNoOp,
  } kind{ kINVALID };
  Expr* sub{ nullptr };
};

//==============================================================================
// 语句
//==============================================================================

struct FunctionDecl;

struct Stmt : public Obj
{};

struct DeclStmt : public Stmt
{
  std::vector<Decl*> decls;
};

struct ExprStmt : public Stmt
{
  Expr* expr{ nullptr };
};

struct CompoundStmt : public Stmt
{
  std::vector<Stmt*> subs;
};

struct IfStmt : public Stmt
{
  Expr* cond{ nullptr };
  Stmt *then{ nullptr }, *else_{ nullptr };
};

struct WhileStmt : public Stmt
{
  Expr* cond{ nullptr };
  Stmt* body{ nullptr };
};

struct DoStmt : public Stmt
{
  Stmt* body{ nullptr };
  Expr* cond{ nullptr };
};

struct BreakStmt : public Stmt
{
  Stmt* loop{ nullptr };
};

struct ContinueStmt : public Stmt
{
  Stmt* loop{ nullptr };
};

struct ReturnStmt : public Stmt
{
  FunctionDecl* func{ nullptr };
  Expr* expr{ nullptr };
};

//==============================================================================
// 声明
//==============================================================================

struct Decl : public Obj
{
  Type type;
  std::string name;
};

struct VarDecl : public Decl
{
  Expr* init;
};

struct FunctionDecl : public Decl
{
  std::vector<Decl*> params;
  CompoundStmt* body{ nullptr };
};

using TranslationUnit = std::vector<Decl*>;

}
