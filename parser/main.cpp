#include "CLexer.h"
#include "asg.hpp"
#include <fstream>
#include <iostream>

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
  // for (auto token : tokens.getTokens()) {
  //   std::cout << token->toString() << std::endl;
  // }

  CParser parser(&tokens);
  auto cu = parser.compilationUnit();
  std::cout << cu->toStringTree(true) << std::endl;

  asg::Ast2Asg ast2asg;
  auto tu = ast2asg(cu->translationUnit());

  // Value val = to_json(*parser.compilationUnit());
  // llvm::outs() << val << '\n';

  std::cout << tu.size() << std::endl;
}
