#include "asg.hpp"
#include <llvm/IR/IRBuilder.h>
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>

namespace asg {

class EmitIR
{
public:
  llvm::Module _mod;

public:
  EmitIR(llvm::LLVMContext& ctx, llvm::StringRef mid = "-");

public:
  llvm::Module& operator()(const TranslationUnit& tu);

private:
  struct LoopAny
  {
    llvm::BasicBlock *continue_, *break_;
  };

private:
  llvm::LLVMContext& _ctx;

  llvm::Type* _intTy;
  llvm::FunctionType* _ctorTy;

  llvm::Function* _curFunc;
  std::unique_ptr<llvm::IRBuilder<>> _curIrb;

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

  void trans_init(llvm::Value* val, Expr* obj);

  llvm::Value* trans_bool(llvm::Value* cond);

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
};

}
