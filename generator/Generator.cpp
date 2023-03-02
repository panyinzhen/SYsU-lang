#include "Generator.hpp"
#include <llvm/IR/IRBuilder.h>
#include <llvm/Transforms/Utils/ModuleUtils.h>

#define self (*this)

namespace asg {

llvm::Module&
Generator::operator()(const asg::TranslationUnit& tu)
{
  for (auto&& i : tu)
    self(i);
  return _mod;
}

//============================================================================
// 表达式
//============================================================================

//============================================================================
// 语句
//============================================================================

//============================================================================
// 声明
//============================================================================

void
Generator::operator()(Decl* obj)
{
  if (auto p = obj->dcast<VarDecl>())
    return self(p);

  if (auto p = obj->dcast<FunctionDecl>())
    return self(p);

  ASG_ABORT();
}

void
Generator::operator()(VarDecl* obj)
{
  auto ty = self(obj->type);
  auto init = trans_init(obj->init);
  auto gvar = new llvm::GlobalVariable(
    _mod, ty, false, llvm::GlobalVariable::ExternalLinkage, init, obj->name);
}

void
Generator::operator()(FunctionDecl* obj)
{
  auto fty = llvm::dyn_cast<llvm::FunctionType>(self(obj->type));
  _curFunc = llvm::Function::Create(
    fty, llvm::GlobalVariable::ExternalLinkage, obj->name, _mod);
  auto argIter = _curFunc->arg_begin();
  for (auto&& param : obj->params)
    argIter->setName(obj->name), ++argIter;
}

}
