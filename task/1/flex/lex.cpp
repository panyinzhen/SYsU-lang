#include "lex.hpp"
#include <iostream>

void
print_token();

namespace lex {

static const char* kTokenNames[] = {
  "identifier",   "numeric_constant",   "string_literal",
  "sizeof",       "ptr_op",     "inc_op",
  "dec_op",       "left_op",    "right_op",
  "le_op",        "ge_op",      "eq_op",
  "ne_op",        "and_op",     "or_op",
  "mul_assign",   "div_assign", "mod_assign",
  "add_assign",   "sub_assign", "left_assign",
  "right_assign", "and_assign", "xor_assign",
  "or_assign",    "type_name",  "typedef",
  "extern",       "static",     "auto",
  "register",     "inline",     "restrict",
  "char",         "short",      "int",
  "long",         "signed",     "unsigned",
  "float",        "double",     "const",
  "volatile",     "void",       "bool",
  "complex",      "imaginary",  "struct",
  "union",        "enum",       "ellipsis",
  "case",         "default",    "if",
  "else",         "switch",     "while",
  "do",           "for",        "goto",
  "continue",     "break",      "return",
  "l_brace",      "r_brace",     "l_paren",
  "r_paren",      "semi",        "equal",
  "plus",         "comma",       "l_square",
  "r_square",     "minus"
};

const char*
id2str(Id id)
{
  static char sCharBuf[2] = { 0, 0 };
  if (id == Id::YYEOF) {
    return "eof";
  }
  else if (id < Id::IDENTIFIER) {
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
      g.mColumn = 1;
      g.mStartOfLine = true;
      g.mLeadingSpace = false;
    } else {
      ++g.mColumn;
      g.mLeadingSpace = true;
    }
  }
}

int
read_path(const char* yytext)
{
  static char sHeaderPath[200];
  int yyrow;
  sscanf(yytext, "# %d \"%s\"", &yyrow, sHeaderPath);
  --yyrow;
  sHeaderPath[strlen(sHeaderPath) - 1] = 0;
  g.mFile = sHeaderPath;
  return yyrow;
}

} // namespace lex
