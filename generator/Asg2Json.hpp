#include "asg.hpp"
#include <llvm/Support/JSON.h>
#include <unordered_set>

namespace asg {

namespace json = llvm::json;

class Asg2Json
{
public:
  json::Object operator()(TranslationUnit& tu);

private:
  std::unordered_set<Obj*> _walked;

private:
  struct WalkedGuard
  {
    Asg2Json& _;
    Obj* _obj;

    WalkedGuard(Asg2Json& _, Obj* obj)
      : _(_)
      , _obj(obj)
    {
      if (_._walked.find(obj) != _._walked.end())
        ASG_ABORT();
      _._walked.insert(obj);
    }

    ~WalkedGuard() { _._walked.erase(_obj); }
  };

  //============================================================================
  // 类型
  //============================================================================

  std::string operator()(TypeExpr* texp);

  std::string operator()(const Type& type);

  //============================================================================
  // 表达式
  //============================================================================

  json::Object operator()(Expr* obj);

  json::Object operator()(IntegerLiteral* obj);

  json::Object operator()(StringLiteral* obj);

  json::Object operator()(DeclRefExpr* obj);

  json::Object operator()(UnaryExpr* obj);

  json::Object operator()(BinaryExpr* obj);

  json::Object operator()(CallExpr* obj);

  json::Object operator()(InitListExpr* obj);

  json::Object operator()(ImplicitInitExpr* obj);

  json::Object operator()(ImplicitCastExpr* obj);

  //============================================================================
  // 语句
  //============================================================================

  json::Object operator()(Stmt* obj);

  json::Object operator()(NullStmt* obj);

  json::Object operator()(DeclStmt* obj);

  json::Object operator()(ExprStmt* obj);

  json::Object operator()(CompoundStmt* obj);

  json::Object operator()(IfStmt* obj);

  json::Object operator()(WhileStmt* obj);

  json::Object operator()(DoStmt* obj);

  json::Object operator()(BreakStmt* obj);

  json::Object operator()(ContinueStmt* obj);

  json::Object operator()(ReturnStmt* obj);

  //============================================================================
  // 声明
  //============================================================================

  json::Object operator()(Decl* obj);

  json::Object operator()(VarDecl* obj);

  json::Object operator()(FunctionDecl* obj);
};

}
