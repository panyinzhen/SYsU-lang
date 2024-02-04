#include "lex.hpp"
#include <iostream>

namespace lex {

G g;

int
come(int tokenId, const char* yytext, int yyleng, int yylineno)
{
  g.mId = tokenId;
  g.mText = { yytext, std::size_t(yyleng) };
  g.mLine = yylineno;

  g.mStartOfLine = false;
  g.mLeadingSpace = false;

  return tokenId;
}

void
spaces(const char* yytext, int yyleng)
{
  g.mLeadingSpace = true;
  for (int i = 0; i < yyleng; ++i) {
    if (yytext[i] == '\n') {
      g.mColumn = 0;
      g.mStartOfLine = true;
      g.mLeadingSpace = false;
    } else {
      ++g.mColumn;
      g.mStartOfLine = false;
      g.mLeadingSpace = true;
    }
  }
}

} // namespace lex
