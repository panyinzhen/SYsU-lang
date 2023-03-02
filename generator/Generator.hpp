#include "asg.hpp"
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>

namespace asg {

class Generator
{
public:
  llvm::LLVMContext _ctx;
  llvm::Module _mod;

public:
  Generator()
    : _ctx()
    , _mod("-", _ctx)
  {
  }

public:
  llvm::Module& operator()(const TranslationUnit& tu);

private:
  llvm::Function* _curFunc;

private:
  //============================================================================
  // 类型
  //============================================================================

  llvm::Type* operator()(const Type& type);

  //============================================================================
  // 表达式
  //============================================================================

  llvm::Value* operator()(Expr* obj);

  llvm::Value* operator()(IntegerLiteral* obj);

  llvm::Value* operator()(StringLiteral* obj);

  llvm::Value* operator()(DeclRefExpr* obj);

  llvm::Value* operator()(UnaryExpr* obj);

  llvm::Value* operator()(BinaryExpr* obj);

  llvm::Value* operator()(CallExpr* obj);

  void operator()(InitListExpr* obj);

  void operator()(ImplicitInitExpr* obj);

  void operator()(ImplicitCastExpr* obj);

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

  void operator()(BreakStmt* obj);

  void operator()(ContinueStmt* obj);

  void operator()(ReturnStmt* obj);

  //============================================================================
  // 声明
  //============================================================================

  void operator()(Decl* obj);

  void operator()(VarDecl* obj);

  void operator()(FunctionDecl* obj);

private:
  llvm::Constant* trans_init(Expr* obj);
};

}
