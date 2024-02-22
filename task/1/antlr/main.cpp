#include "SYsU_lang.h" // 确保这里的头文件名与您生成的词法分析器匹配
#include <fstream>
#include <iostream>

void
print_token(const antlr4::Token* token,
            const antlr4::CommonTokenStream& tokens,
            std::ofstream& outFile,
            const antlr4::Lexer& lexer)
{
  auto& vocabulary = lexer.getVocabulary();

  auto tokenTypeName =
    std::string(vocabulary.getSymbolicName(token->getType()));

  if (tokenTypeName.empty())
    tokenTypeName = "<UNKNOWN>"; // 处理可能的空字符串情况

  auto locInfo = " Loc=<" + std::to_string(token->getLine()) + ":" +
                 std::to_string(token->getCharPositionInLine() + 1) + ">";

  bool startOfLine;
  if (token->getText() == "<EOF>") {
    startOfLine = false;
  } else {
    startOfLine =
      (token->getCharPositionInLine() == 0) ||
      (token->getCharPositionInLine() > 0 &&
       tokens.get(token->getTokenIndex() - 1)->getLine() != token->getLine());
  }

  bool leadingSpace = false;
  if (token->getTokenIndex() > 0) {
    auto prevToken = tokens.get(token->getTokenIndex() - 1);

    auto currentTokenLine = token->getLine();
    auto currentTokenStartColumn = token->getCharPositionInLine();

    auto prevTokenLine = prevToken->getLine();

    if (currentTokenLine != prevTokenLine && currentTokenStartColumn > 0) {
      leadingSpace = true;
    } else {
      auto prevTokenStartColumn = prevToken->getCharPositionInLine();
      auto prevTokenLength = prevToken->getText().length();
      auto prevTokenStopColumn = prevTokenStartColumn + prevTokenLength - 1;

      if (currentTokenStartColumn - prevTokenStopColumn > 1) {
        leadingSpace = true;
      }
    }
  }

  outFile << tokenTypeName << " '" << token->getText() << "'";
  if (startOfLine)
    outFile << "\t [StartOfLine]";
  if (leadingSpace)
    outFile << " [LeadingSpace]";
  outFile << locInfo << std::endl;
}

int
main(int argc, char* argv[])
{
  if (argc != 3) {
    std::cout << "Usage: " << argv[0] << " <input> <output>\n";
    return -1;
  }

  std::ifstream inFile(argv[1]);
  if (!inFile) {
    std::cout << "Error: unable to open input file: " << argv[1] << '\n';
    return -2;
  }

  std::ofstream outFile(argv[2]);
  if (!outFile) {
    std::cout << "Error: unable to open output file: " << argv[2] << '\n';
    return -3;
  }

  std::cout << "程序 '" << argv[0] << std::endl;
  std::cout << "输入 '" << argv[1] << std::endl;
  std::cout << "输出 '" << argv[2] << std::endl;

  antlr4::ANTLRInputStream input(inFile);
  SYsU_lang lexer(&input);

  antlr4::CommonTokenStream tokens(&lexer);
  tokens.fill();

  for (auto&& token : tokens.getTokens())
    print_token(token, tokens, outFile, lexer);
}
