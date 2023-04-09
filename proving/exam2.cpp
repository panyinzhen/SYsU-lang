/**
 * 演示了：
 * 1. 使用 @llvm.global_ctor 实现非常量全局变量初始化表达式的翻译
 */

#include <llvm/IR/IRBuilder.h>
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>
#include <llvm/IR/Verifier.h>
#include <llvm/Support/raw_ostream.h>

using namespace llvm;

LLVMContext gCtx;
Module gMod("-", gCtx);

// void
const auto kVoidTy = Type::getVoidTy(gCtx);

// int
const auto kIntTy = Type::getInt32Ty(gCtx);

// main
const auto kMainTy = FunctionType::get(kIntTy, {}, false);
const auto kMainFun =
  Function::Create(kMainTy, Function::ExternalLinkage, "main", gMod);

// ctor
const auto kCtorTy = FunctionType::get(kVoidTy, {}, false);

// global_ctors
const auto kGlobalCtorTy =
  StructType::get(gCtx, { kIntTy, kCtorTy, Type::getInt8PtrTy(gCtx) });

// a
const auto kAVarC = new GlobalVariable(gMod,
                                       kIntTy,
                                       true,
                                       GlobalVariable::ExternalLinkage,
                                       ConstantInt::get(kIntTy, 1));

// b
// const auto k

int
main()
{

  gMod.print(llvm::outs(), nullptr);
  llvm::outs() << '\n';
  if (llvm::verifyModule(gMod, &llvm::outs()))
    return -1;
}
