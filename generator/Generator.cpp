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
  auto gvar = _mod.getOrInsertGlobal(obj->name, self(obj->type));
  
}

}
