#include "EmitIR.hpp"
#include "Json2Asg.hpp"
#include "asg.hpp"
#include <fstream>
#include <iostream>
#include <llvm/IR/Verifier.h>
#include <llvm/Support/MemoryBuffer.h>

int
main(int argc, char* argv[])
{
  if (argc != 3) {
    std::cout << "Usage: " << argv[0] << " <input> <output>\n";
    return -1;
  }

  auto InFileOrErr = llvm::MemoryBuffer::getFile(argv[1]);
  if (auto Err = InFileOrErr.getError()) {
    std::cout << "Error: unable to open input file: " << argv[1] << '\n';
    return -2;
  }
  auto InFile = std::move(InFileOrErr.get());

  std::error_code ec;
  llvm::StringRef outPath(argv[2]);
  llvm::raw_fd_ostream outFile(outPath, ec);
  if (ec) {
    std::cout << "Error: unable to open output file: " << argv[2] << '\n';
    return -3;
  }

  auto json = llvm::json::parse(InFile->getBuffer());
  if (!json) {
    std::cout << "Error: unable to parse input file: " << argv[1] << '\n';
    return 1;
  }

  asg::Obj::Mgr mgr;
  asg::Json2Asg json2asg(mgr);
  auto asg = json2asg(json.get());

  llvm::LLVMContext ctx;
  asg::EmitIR emitIR(ctx);
  auto& mod = emitIR(asg);

  mod.print(outFile, nullptr, false, true);
  if (llvm::verifyModule(mod, &llvm::outs()))
    return 3;
}
