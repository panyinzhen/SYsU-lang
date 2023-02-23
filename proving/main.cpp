#include <llvm/IR/IRBuilder.h>
#include <llvm/IR/Verifier.h>
#include <llvm/Support/raw_ostream.h>
#include <llvm/Transforms/Utils/ModuleUtils.h>

llvm::LLVMContext gCtx;
llvm::Module gMod("-", gCtx);

int
main()
{
  {
    auto i32Ty = llvm::Type::getInt32Ty(gCtx);
    auto gvar =
      llvm::dyn_cast<llvm::GlobalVariable>(gMod.getOrInsertGlobal("g", i32Ty));
    auto init = llvm::ConstantInt::get(i32Ty, 30);
    gvar->setInitializer(init);
  }

  {
    auto ctorType =
      llvm::FunctionType::get(llvm::Type::getVoidTy(gCtx), {}, false);

    auto ctorFunc =
      llvm::Function::Create(llvm::cast<llvm::FunctionType>(ctorType),
                             llvm::GlobalValue::LinkageTypes::ExternalLinkage,
                             "my_constructor",
                             &gMod);

    llvm::appendToGlobalCtors(gMod, ctorFunc, 0, nullptr);
  }

  if (llvm::verifyModule(gMod, &llvm::outs()))
    return -1;
  gMod.print(llvm::outs(), nullptr);
}
