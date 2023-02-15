#include "CLexer.h"
#include "asg.hpp"
#include <iostream>

using namespace antlr_c;

int
main()
{
  antlr4::ANTLRInputStream input(std::cin);
  CLexer lexer(&input);

  antlr4::CommonTokenStream tokens(&lexer);
  // tokens.fill();
  // for (auto token : tokens.getTokens()) {
  //   std::cout << token->toString() << std::endl;
  // }

  CParser parser(&tokens);
  std::cout << parser.compilationUnit()->toStringTree(true) << std::endl;

  // Value val = to_json(*parser.compilationUnit());
  // llvm::outs() << val << '\n';
}
