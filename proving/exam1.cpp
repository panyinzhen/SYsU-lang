/**
 * 演示了：
 * 1. 定义全局变量以及初始化
 * 2. 声明和定义函数
 * 3. 使用Φ结点实现循环
 * 4. 算数运算和指针寻址
 * 5. 函数调用和返回
 */

#include <llvm/IR/IRBuilder.h>
#include <llvm/IR/LLVMContext.h>
#include <llvm/IR/Module.h>
#include <llvm/IR/Verifier.h>
#include <llvm/Support/raw_ostream.h>

using namespace llvm;

LLVMContext gCtx;
Module gMod("-", gCtx);

// int
const auto kIntTy = Type::getInt32Ty(gCtx);

// char
const auto kCharTy = Type::getInt8Ty(gCtx);

// char *
const auto kCharPTy = kCharTy->getPointerTo();

// char **
const auto kCharPPTy = kCharPTy->getPointerTo();

// printf
const auto kPrintfTy = FunctionType::get(kIntTy, { kCharPTy }, true);
const auto kPrintfFun =
  Function::Create(kPrintfTy, Function::ExternalLinkage, "printf", gMod);

// main
const auto kMainTy = FunctionType::get(kIntTy, { kIntTy, kCharPPTy }, false);
const auto kMainFun =
  Function::Create(kMainTy, Function::ExternalLinkage, "main", gMod);

// "%s\n"
const auto kFmtstrVarC = ConstantDataArray::getString(gCtx, "%s\n");
const auto kFmtstrVar =
  new llvm::GlobalVariable(gMod,
                           kFmtstrVarC->getType(),
                           false,
                           llvm::GlobalVariable::ExternalLinkage,
                           kFmtstrVarC);

int
main()
{
  auto entryBB = BasicBlock::Create(gCtx, "entry", kMainFun);
  llvm::IRBuilder<> entryIRB(entryBB);

  auto condBB = BasicBlock::Create(gCtx, "cond", kMainFun);
  llvm::IRBuilder<> condIRB(condBB);

  auto loopBB = BasicBlock::Create(gCtx, "loop", kMainFun);
  llvm::IRBuilder<> loopIRB(loopBB);

  auto retBB = BasicBlock::Create(gCtx, "ret", kMainFun);
  llvm::IRBuilder<> retIRB(retBB);

  auto argIter = kMainFun->arg_begin();
  auto argcVar = &*argIter, argvVar = &*++argIter;
  argcVar->setName("argc"), argvVar->setName("argv");

  // entry
  entryIRB.CreateBr(condBB);

  // cond
  auto iVar = condIRB.CreatePHI(kIntTy, 2, "i");
  condIRB.CreateCondBr(condIRB.CreateICmpSLT(iVar, argcVar), loopBB, retBB);
  iVar->addIncoming(ConstantInt::get(kIntTy, 0), entryBB);

  // loop
  auto fmtstrVar = loopIRB.CreateBitCast(kFmtstrVar, kCharPTy);
  loopIRB.CreateCall(
    kPrintfFun,
    {
      loopIRB.CreateGEP(
        fmtstrVar->getType(), fmtstrVar, ConstantInt::get(kIntTy, 0)),
      loopIRB.CreateLoad(kCharPTy, loopIRB.CreateGEP(kCharPTy, argvVar, iVar)),
    });
  iVar->addIncoming(loopIRB.CreateAdd(iVar, ConstantInt::get(kIntTy, 1)),
                    loopBB);
  loopIRB.CreateBr(condBB);

  // ret
  retIRB.CreateRet(ConstantInt::get(kIntTy, 0));

  gMod.print(llvm::outs(), nullptr);
  llvm::outs() << '\n';
  if (llvm::verifyModule(gMod, &llvm::outs()))
    return -1;
}
