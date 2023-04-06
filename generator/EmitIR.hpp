#include "asg.hpp"
#include <llvm/IR/IRBuilder.h>
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
  struct LoopAny
  {
    llvm::BasicBlock *continue_, *break_;
  };

private:
  llvm::Function* _curFunc;   // 传参给编译语句
  llvm::IRBuilder<>* _curIrb; // 传参给编译表达式

private:
  //============================================================================
  // 类型
  //============================================================================

  llvm::Type* operator()(const Type& type);

  //============================================================================
  // 表达式
  //============================================================================

  llvm::Value* operator()(Expr* obj);

  llvm::Constant* operator()(IntegerLiteral* obj);

  llvm::Constant* operator()(StringLiteral* obj);

  llvm::Value* operator()(DeclRefExpr* obj);

  llvm::Value* operator()(UnaryExpr* obj);

  llvm::Value* operator()(BinaryExpr* obj);

  llvm::Value* operator()(CallExpr* obj);

  llvm::Value* operator()(ImplicitCastExpr* obj);

  llvm::Constant* operator()(ImplicitInitExpr* obj);

  void trans_init(llvm::Value* val, Expr* obj);

  llvm::Constant* trans_static_init(Expr* obj);

  //============================================================================
  // 语句
  //============================================================================

  /**
   * @param obj 待翻译语句
   * @param enter 空的入口块，翻译函数可以直接使用这个块
   * @return 所有语句的翻译函数应该创建一个新的空出口块返回
   */
  llvm::BasicBlock* operator()(Stmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(DeclStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(ExprStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(CompoundStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(IfStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(WhileStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(DoStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(BreakStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(ContinueStmt* obj, llvm::BasicBlock* enter);

  llvm::BasicBlock* operator()(ReturnStmt* obj, llvm::BasicBlock* enter);

  //============================================================================
  // 声明
  //============================================================================

  void operator()(Decl* obj);

  void operator()(VarDecl* obj);

  void operator()(FunctionDecl* obj);

private:
  llvm::Value* boolize(llvm::Value* cond);
};

}
