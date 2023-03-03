#include "asg.hpp"
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>

namespace asg {

class EmitIR
{
public:
  llvm::LLVMContext _ctx;
  llvm::Module _mod;

public:
  EmitIR()
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

  llvm::Value* operator()(InitListExpr* obj);

  llvm::Constant* operator()(ImplicitInitExpr* obj);

  llvm::Value* operator()(ImplicitCastExpr* obj);

  //============================================================================
  // 语句
  //============================================================================

  /**
   * @param obj 待翻译语句
   * @param enter 空的入口块，翻译函数可以直接使用这个块
   * @return 所有语句的翻译函数应该创建一个新的空出口块返回
   */
  llvm::BasicBlock* operator()(Stmt* obj, llvm::BasicBlock* enter);

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
  llvm::Constant* trans_static_init(Expr* obj);
};

}
