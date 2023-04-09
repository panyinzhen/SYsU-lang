#include "asg.hpp"

namespace asg {

/**
 * @brief 在抽象语法图上自动推导并补全类型
 */
class InferType
{
public:
  InferType(Obj::Mgr& mgr)
    : _mgr(mgr)
  {
  }

public:
  void operator()(TranslationUnit& tu);

  //============================================================================
  // 表达式
  //============================================================================

  Expr* operator()(Expr* obj);

  Expr* operator()(IntegerLiteral* obj);

  Expr* operator()(StringLiteral* obj);

  Expr* operator()(DeclRefExpr* obj);

  Expr* operator()(UnaryExpr* obj);

  Expr* operator()(BinaryExpr* obj);

  Expr* operator()(CallExpr* obj);

  //============================================================================
  // 语句
  //============================================================================

  void operator()(Stmt* obj);

  void operator()(DeclStmt* obj);

  void operator()(ExprStmt* obj);

  void operator()(CompoundStmt* obj);

  void operator()(IfStmt* obj);

  void operator()(WhileStmt* obj);

  void operator()(DoStmt* obj);

  void operator()(ReturnStmt* obj);

  //============================================================================
  // 声明
  //============================================================================

  void operator()(Decl* obj);

  void operator()(VarDecl* obj);

  void operator()(FunctionDecl* obj);

private:
  Obj::Mgr& _mgr;
  std::unordered_set<Obj*> _walked;

private:
  struct WalkedGuard
  {
    InferType& _;
    Obj* _obj;

    WalkedGuard(InferType& _, Obj* obj)
      : _(_)
      , _obj(obj)
    {
      if (_._walked.find(obj) != _._walked.end())
        ASG_ABORT();
      _._walked.insert(obj);
    }

    ~WalkedGuard() { _._walked.erase(_obj); }
  };

private:
  template<typename T, typename... Args>
  T& make(Args... args)
  {
    return _mgr.make<T>(args...);
  }

private:
  Expr* ensure_rvalue(Expr* exp);

  // 整数提升：https://zh.cppreference.com/w/c/language/conversion#%E6%95%B4%E6%95%B0%E6%8F%90%E5%8D%87
  Expr* promote_integer(Expr* exp, int to = Type::Specs::kInt);

  // 转换 rht 到 lft
  Expr* assigment_cast(const Type& lft, Expr* rht);

  // 推断初始化表达式的类型，这会去除重复和冗余并重组表达式的结构，将其规范化
  Expr* infer_init(Expr* init, const Type& to);

  // 推断列表初始化的类型，返回构造的初始化表达式和用到了第几个初始化元素
  std::pair<Expr*, std::size_t> infer_initlist(const std::vector<Expr*>& list,
                                               std::size_t begin,
                                               const Type& to);
};

}
