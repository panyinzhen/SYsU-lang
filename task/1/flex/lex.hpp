#pragma once

#include <string>
#include <string_view>

namespace lex {

enum Id
{
  YYEMPTY = -2,
  YYEOF = 0,     /* "end of file"  */
  YYerror = 256, /* error  */
  YYUNDEF = 257, /* "invalid token"  */
  IDENTIFIER,
  CONSTANT,
  STRING_LITERAL,
  SIZEOF,
  PTR_OP,
  INC_OP,
  DEC_OP,
  LEFT_OP,
  RIGHT_OP,
  LE_OP,
  GE_OP,
  EQ_OP,
  NE_OP,
  AND_OP,
  OR_OP,
  MUL_ASSIGN,
  DIV_ASSIGN,
  MOD_ASSIGN,
  ADD_ASSIGN,
  SUB_ASSIGN,
  LEFT_ASSIGN,
  RIGHT_ASSIGN,
  AND_ASSIGN,
  XOR_ASSIGN,
  OR_ASSIGN,
  TYPE_NAME,
  TYPEDEF,
  EXTERN,
  STATIC,
  AUTO,
  REGISTER,
  INLINE,
  RESTRICT,
  CHAR,
  SHORT,
  INT,
  LONG,
  SIGNED,
  UNSIGNED,
  FLOAT,
  DOUBLE,
  CONST,
  VOLATILE,
  VOID,
  BOOL,
  COMPLEX,
  IMAGINARY,
  STRUCT,
  UNION,
  ENUM,
  ELLIPSIS,
  CASE,
  DEFAULT,
  IF,
  ELSE,
  SWITCH,
  WHILE,
  DO,
  FOR,
  GOTO,
  CONTINUE,
  BREAK,
  RETURN,
};

const char*
id2str(Id id);

struct G
{
  Id mId{ YYEOF };              // 词号
  std::string_view mText;       // 对应文本
  std::string mFile;            // 文件路径
  int mLine{ 0 }, mColumn{ 0 }; // 行号、列号
  bool mStartOfLine{ true };    // 是否是行首
  bool mLeadingSpace{ false };  // 是否有前导空格
};

extern G g;

int
come(int tokenId, const char* yytext, int yyleng, int yylineno);

void
spaces(const char* yytext, int yyleng);

} // namespace lex
