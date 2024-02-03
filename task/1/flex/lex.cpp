#include "lex.hpp"
#include <iostream>

void
print_token();

namespace lex {

static const char* kTokenNames[] = {
  "IDENTIFIER",   "CONSTANT",   "STRING_LITERAL",
  "SIZEOF",       "PTR_OP",     "INC_OP",
  "DEC_OP",       "LEFT_OP",    "RIGHT_OP",
  "LE_OP",        "GE_OP",      "EQ_OP",
  "NE_OP",        "AND_OP",     "OR_OP",
  "MUL_ASSIGN",   "DIV_ASSIGN", "MOD_ASSIGN",
  "ADD_ASSIGN",   "SUB_ASSIGN", "LEFT_ASSIGN",
  "RIGHT_ASSIGN", "AND_ASSIGN", "XOR_ASSIGN",
  "OR_ASSIGN",    "TYPE_NAME",  "TYPEDEF",
  "EXTERN",       "STATIC",     "AUTO",
  "REGISTER",     "INLINE",     "RESTRICT",
  "CHAR",         "SHORT",      "INT",
  "LONG",         "SIGNED",     "UNSIGNED",
  "FLOAT",        "DOUBLE",     "CONST",
  "VOLATILE",     "VOID",       "BOOL",
  "COMPLEX",      "IMAGINARY",  "STRUCT",
  "UNION",        "ENUM",       "ELLIPSIS",
  "CASE",         "DEFAULT",    "IF",
  "ELSE",         "SWITCH",     "WHILE",
  "DO",           "FOR",        "GOTO",
  "CONTINUE",     "BREAK",      "RETURN",
};

const char*
id2str(Id id)
{
  static char sCharBuf[2] = { 0, 0 };
  if (id < Id::IDENTIFIER) {
    sCharBuf[0] = char(id);
    return sCharBuf;
  }
  return kTokenNames[int(id) - int(Id::IDENTIFIER)];
}

G g;

int
come(int tokenId, const char* yytext, int yyleng, int yylineno)
{
  g.mId = Id(tokenId);
  g.mText = { yytext, std::size_t(yyleng) };
  g.mLine = yylineno;

  print_token();
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
