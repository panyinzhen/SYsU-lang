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
  llvm::IRBuilder<> irb0(entryBB);
  llvm::IRBuilder<> irb1(entryBB);
  llvm::IRBuilder<> irb2(entryBB);

  irb0.CreateAlloca(kIntTy, nullptr, "0");
  irb1.CreateAlloca(kIntTy, nullptr, "1");
  auto p = irb2.CreateAlloca(kIntTy, nullptr, "2");

  llvm::IRBuilder<> irb3(entryBB);
  llvm::IRBuilder<> irb4(entryBB);
  llvm::IRBuilder<> irb5(entryBB);

  irb5.SetInsertPoint(p);
  irb3.CreateAlloca(kIntTy, nullptr, "3");
  irb4.CreateAlloca(kIntTy, nullptr, "4");
  irb5.CreateAlloca(kIntTy, nullptr, "5");

  irb0.CreateAlloca(kIntTy, nullptr, "6");
  irb1.CreateAlloca(kIntTy, nullptr, "7");
  irb2.CreateAlloca(kIntTy, nullptr, "8");

  entryBB->print(llvm::outs());
}
