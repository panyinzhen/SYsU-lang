#include "Asg2Json.hpp"
#include "Ast2Asg.hpp"
#include "CLexer.h"
#include "EmitIR.hpp"
#include "InferType.hpp"
#include "asg.hpp"
#include <fstream>
#include <iostream>
#include <llvm/IR/Verifier.h>

using namespace antlr_c;

int
main(int argc, char* argv[])
{
  std::ifstream fin;
  if (argc > 1) {
    fin.open(argv[1]);
    std::cin.rdbuf(fin.rdbuf());
  }

  antlr4::ANTLRInputStream input(std::cin);
  CLexer lexer(&input);

  antlr4::CommonTokenStream tokens(&lexer);
  // tokens.fill();
  // for (auto token : tokens.getTokens())
  //   std::cout << token->toString() << std::endl;

  CParser parser(&tokens);
  auto ast = parser.compilationUnit();
  // std::cout << ast->toStringTree(true) << std::endl;

  asg::Ast2Asg ast2asg;
  auto asg = ast2asg(ast->translationUnit());

  asg::InferType inferType;
  inferType(asg);

  asg::Asg2Json asg2json;
  llvm::json::Value json = asg2json(asg);
  llvm::outs() << json << '\n';

  asg::EmitIR generator;
  auto& mod = generator(asg);
  mod.print(llvm::outs(), nullptr, false, true);

  llvm::outs() << '\n';
  if (llvm::verifyModule(mod, &llvm::outs()))
    return -1;
}
