#pragma once

#include <any>
#include <cstdio>
#include <memory>
#include <string>
#include <vector>

/// 错误断言，打印文件和行号，方便定位问题。
#define ASSERT(expr)                                                           \
  ((expr) || (fprintf(stderr, "asserted at %s:%d\n", __FILE__, __LINE__),      \
              abort(),                                                         \
              false))

/// 错误中断，打印文件和行号，方便定位问题。
#define ABORT()                                                                \
  (fprintf(stderr, "aborted at %s:%d\n", __FILE__, __LINE__), abort())

namespace asg {

template<bool...>
struct bool_pack;

template<bool... v>
using all_true = std::is_same<bool_pack<true, v...>, bool_pack<v..., true>>;

template<typename T, typename... Ts>
using is_one_of = std::disjunction<std::is_same<T, Ts>...>;

struct Obj
{
  std::any any; /// 留给遍历器存放任意数据

  virtual ~Obj() = default;

  template<typename T>
  T* dcst()
  {
    return dynamic_cast<T*>(this);
  }

  template<typename T>
  T* scst()
  {
    return static_cast<T*>(this);
  }

  template<typename T>
  T& rcst()
  {
    return *reinterpret_cast<T*>(this);
  }

  struct Mgr : public std::vector<std::unique_ptr<Obj>>
  {
    template<typename T, typename... Args>
    T& make(Args... args)
    {
      auto ptr = std::make_unique<T>(args...);
      auto& obj = *ptr;
      emplace_back(std::move(ptr));
      return obj;
    }
  };

  /// 检查循环引用，防止无限递归。
  struct Walked
  {
    Obj* mObj;

    Walked(Obj* obj)
      : mObj(obj)
    {
      ASSERT(!mObj->any.has_value());
      mObj->any = nullptr;
    }

    ~Walked() { mObj->any.reset(); }
  };

  /// 有限泛型的指针模板类
  template<typename... Ts>
  struct Ptr
  {
    static_assert(all_true<std::is_convertible_v<Ts*, Obj*>...>::value);

    Obj* mObj{ nullptr };

    Ptr() {}

    Ptr(std::nullptr_t) {}

    template<typename T,
             typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
    Ptr(T* p)
      : mObj(p)
    {
    }

    operator bool() { return mObj != nullptr; }

    template<typename T,
             typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
    T* dcst()
    {
      return dynamic_cast<T*>(mObj);
    }

    template<typename T,
             typename = std::enable_if_t<is_one_of<T, Ts...>::value>>
    T& rcst()
    {
      return *reinterpret_cast<T>(mObj);
    }
  };
};

//==============================================================================
// 类型
//==============================================================================

struct TypeExpr;
struct Expr;
struct Decl;

struct Type
{
  /// 说明（Specifier）
  enum class Spec : std::uint8_t
  {
    kINVALID,
    kVoid,
    kChar,
    kInt,
    kLong,
    kLongLong,
  };

  /// 限定（Qualifier）
  enum class Qual : std::uint8_t
  {
    kNone,
    kConst,
    // kVolatile,
  };

  Spec spec{ Spec::kINVALID };
  Qual qual{ Qual::kNone };

  TypeExpr* texp{ nullptr };
};

struct TypeExpr : public Obj
{
  TypeExpr* sub{ nullptr };
};

struct PointerType : public TypeExpr
{
  Type::Qual qual{ Type::Qual::kNone };
};

struct ArrayType : public TypeExpr
{
  std::uint32_t len{ 0 }; /// 数组长度，kUnLen 表示未知
  static constexpr std::uint32_t kUnLen = UINT32_MAX;
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
  enum class Cate : std::uint8_t
  {
    kINVALID,
    kRValue,
    kLValue,
  };

  Type type;
  Cate cate{ Cate::kINVALID };
};

struct IntegerLiteral : public Expr
{
  std::uint64_t val{ 0 };
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
    kINVALID,
    kPos,
    kNeg,
    kNot
  };

  Op op{ kINVALID };
  Expr* sub{ nullptr };
};

struct BinaryExpr : public Expr
{
  enum Op
  {
    kINVALID,
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

  Op op{ kINVALID };
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

struct NullStmt : public Stmt
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
  Expr* init{ nullptr };
};

struct FunctionDecl : public Decl
{
  std::vector<Decl*> params;
  CompoundStmt* body{ nullptr };
};

using TranslationUnit = std::vector<Decl*>;

} // namespace asg
