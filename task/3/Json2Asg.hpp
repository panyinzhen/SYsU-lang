#pragma once

#include "asg.hpp"
#include <llvm/Support/JSON.h>
#include <unordered_map>

namespace asg {

class Json2Asg
{
public:
  Obj::Mgr& mMgr;

  Json2Asg(Obj::Mgr& mgr)
    : mMgr(mgr)
  {
  }

  TranslationUnit operator()(const llvm::json::Value& jval);

private:
  std::unordered_map<std::size_t, asg::Obj*> mIdMap;

  std::unordered_map<std::string, Type> mTyMap;

  template<typename T, typename... Args>
  T* make(std::size_t id, Args... args)
  {
    auto& obj = mMgr.make<T>(args...);
    mIdMap.emplace(id, &obj);
    return &obj;
  }

  FunctionDecl* cur_func; // 存放 ReturnStmt 对应的 FunctionDecl

  Type gety(const llvm::json::Object& jobj);
  Expr::Cate getvc(const llvm::json::Object& jobj);

  Decl* decl(const llvm::json::Object& jobj);
  VarDecl* var_decl(const llvm::json::Object& jobj);
  FunctionDecl* function_decl(const llvm::json::Object& jobj);

  //============================================================================
  // 表达式
  //============================================================================
  Expr* expr(const llvm::json::Object& jobj);
  BinaryExpr* binary_expr(const llvm::json::Object& jobj);
  ImplicitCastExpr* implicit_cast_expr(const llvm::json::Object& jobj);
  DeclRefExpr* declref_expr(const llvm::json::Object& jobj);
  IntegerLiteral* integer_literal(const llvm::json::Object& jobj);
  ExprStmt* expr_stmt(const llvm::json::Object& jobj);

  //============================================================================
  // 语句
  //============================================================================

  Stmt* stmt(const llvm::json::Object& jobj);
  CompoundStmt* compound_stmt(const llvm::json::Object& jobj);
  DeclStmt* decl_stmt(const llvm::json::Object& jobj);
  ReturnStmt* return_stmt(const llvm::json::Object& jobj);

private:
  /**
   * @brief 尝试解析以 \p s 为起始的字符串，将语义值存入 \p v 。
   * 成功时返回剩余字符串指针，失败时返回 nullptr。
   */
  const char* parse_type(const char* s, Type& v);

  /// 解析类型表达式，注意 \p v 在构建后是由外到内的顺序，需要再调用 turn_texp
  /// 将内外翻转。
  const char* parse_texp(const char* s, TypeExpr*& v);
  const char* parse_texp_0(const char* s, TypeExpr*& v);
  const char* parse_texp_1(const char* s, TypeExpr*& v);
  const char* parse_texp_2(const char* s, TypeExpr*& v);

  const char* parse_args(const char* s, std::vector<Decl*>& v);
};

} // namespace asg
