%{
#include "lex.hpp"

using namespace lex;

#define ADDCOL() g.mColumn += yyleng;
#define COME(id) return come(id, yytext, yyleng, yylineno)
%}

%option 8bit warn noyywrap yylineno

D     [0-9]
L     [a-zA-Z_]
H     [a-fA-F0-9]
E     ([Ee][+-]?{D}+)
P     ([Pp][+-]?{D}+)
FS    (f|F|l|L)
IS    ((u|U)|(u|U)?(l|L|ll|LL)|(l|L|ll|LL)(u|U))

%%

"auto"        { ADDCOL(); COME(AUTO); }
"_Bool"       { ADDCOL(); COME(BOOL); }
"break"       { ADDCOL(); COME(BREAK); }
"case"        { ADDCOL(); COME(CASE); }
"char"        { ADDCOL(); COME(CHAR); }
"_Complex"    { ADDCOL(); COME(COMPLEX); }
"const"       { ADDCOL(); COME(CONST); }
"continue"    { ADDCOL(); COME(CONTINUE); }
"default"     { ADDCOL(); COME(DEFAULT); }
"do"          { ADDCOL(); COME(DO); }
"double"      { ADDCOL(); COME(DOUBLE); }
"else"        { ADDCOL(); COME(ELSE); }
"enum"        { ADDCOL(); COME(ENUM); }
"extern"      { ADDCOL(); COME(EXTERN); }
"float"       { ADDCOL(); COME(FLOAT); }
"for"         { ADDCOL(); COME(FOR); }
"goto"        { ADDCOL(); COME(GOTO); }
"if"          { ADDCOL(); COME(IF); }
"_Imaginary"  { ADDCOL(); COME(IMAGINARY); }
"inline"      { ADDCOL(); COME(INLINE); }
"int"         { ADDCOL(); COME(INT); }
"long"        { ADDCOL(); COME(LONG); }
"register"    { ADDCOL(); COME(REGISTER); }
"restrict"    { ADDCOL(); COME(RESTRICT); }
"return"      { ADDCOL(); COME(RETURN); }
"short"       { ADDCOL(); COME(SHORT); }
"signed"      { ADDCOL(); COME(SIGNED); }
"sizeof"      { ADDCOL(); COME(SIZEOF); }
"static"      { ADDCOL(); COME(STATIC); }
"struct"      { ADDCOL(); COME(STRUCT); }
"switch"      { ADDCOL(); COME(SWITCH); }
"typedef"     { ADDCOL(); COME(TYPEDEF); }
"union"       { ADDCOL(); COME(UNION); }
"unsigned"    { ADDCOL(); COME(UNSIGNED); }
"void"        { ADDCOL(); COME(VOID); }
"volatile"    { ADDCOL(); COME(VOLATILE); }
"while"       { ADDCOL(); COME(WHILE); }

{L}({L}|{D})*     { ADDCOL(); COME(IDENTIFIER); }

0[xX]{H}+{IS}?        { ADDCOL(); COME(CONSTANT); }
0[0-7]*{IS}?          { ADDCOL(); COME(CONSTANT); }
[1-9]{D}*{IS}?        { ADDCOL(); COME(CONSTANT); }
L?'(\\.|[^\\'\n])+'   { ADDCOL(); COME(CONSTANT); }

{D}+{E}{FS}?                { ADDCOL(); COME(CONSTANT); }
{D}*"."{D}+{E}?{FS}?        { ADDCOL(); COME(CONSTANT); }
{D}+"."{D}*{E}?{FS}?        { ADDCOL(); COME(CONSTANT); }
0[xX]{H}+{P}{FS}?           { ADDCOL(); COME(CONSTANT); }
0[xX]{H}*"."{H}+{P}?{FS}?   { ADDCOL(); COME(CONSTANT); }
0[xX]{H}+"."{H}*{P}?{FS}?   { ADDCOL(); COME(CONSTANT); }

L?\"(\\.|[^\\"\n])*\" { ADDCOL(); COME(STRING_LITERAL); }

"..."       { ADDCOL(); COME(ELLIPSIS); }
">>="       { ADDCOL(); COME(RIGHT_ASSIGN); }
"<<="       { ADDCOL(); COME(LEFT_ASSIGN); }
"+="        { ADDCOL(); COME(ADD_ASSIGN); }
"-="        { ADDCOL(); COME(SUB_ASSIGN); }
"*="        { ADDCOL(); COME(MUL_ASSIGN); }
"/="        { ADDCOL(); COME(DIV_ASSIGN); }
"%="        { ADDCOL(); COME(MOD_ASSIGN); }
"&="        { ADDCOL(); COME(AND_ASSIGN); }
"^="        { ADDCOL(); COME(XOR_ASSIGN); }
"|="        { ADDCOL(); COME(OR_ASSIGN); }
">>"        { ADDCOL(); COME(RIGHT_OP); }
"<<"        { ADDCOL(); COME(LEFT_OP); }
"++"        { ADDCOL(); COME(INC_OP); }
"--"        { ADDCOL(); COME(DEC_OP); }
"->"        { ADDCOL(); COME(PTR_OP); }
"&&"        { ADDCOL(); COME(AND_OP); }
"||"        { ADDCOL(); COME(OR_OP); }
"<="        { ADDCOL(); COME(LE_OP); }
">="        { ADDCOL(); COME(GE_OP); }
"=="        { ADDCOL(); COME(EQ_OP); }
"!="        { ADDCOL(); COME(NE_OP); }
";"         { ADDCOL(); COME(';'); }
("{"|"<%")  { ADDCOL(); COME('{'); }
("}"|"%>")  { ADDCOL(); COME('}'); }
","         { ADDCOL(); COME(','); }
":"         { ADDCOL(); COME(':'); }
"="         { ADDCOL(); COME('='); }
"("         { ADDCOL(); COME('('); }
")"         { ADDCOL(); COME(')'); }
("["|"<:")  { ADDCOL(); COME('['); }
("]"|":>")  { ADDCOL(); COME(']'); }
"."         { ADDCOL(); COME('.'); }
"&"         { ADDCOL(); COME('&'); }
"!"         { ADDCOL(); COME('!'); }
"~"         { ADDCOL(); COME('~'); }
"-"         { ADDCOL(); COME('-'); }
"+"         { ADDCOL(); COME('+'); }
"*"         { ADDCOL(); COME('*'); }
"/"         { ADDCOL(); COME('/'); }
"%"         { ADDCOL(); COME('%'); }
"<"         { ADDCOL(); COME('<'); }
">"         { ADDCOL(); COME('>'); }
"^"         { ADDCOL(); COME('^'); }
"|"         { ADDCOL(); COME('|'); }
"?"         { ADDCOL(); COME('?'); }

[ \t\v\n\f]   { spaces(yytext, yyleng); }

.   { ADDCOL(); COME(YYUNDEF); }

%%

/* about symbols avaliable (yytext, yyleng etc.) in the context of Flex:
 * https://ftp.gnu.org/old-gnu/Manuals/flex-2.5.4/html_node/flex_14.html
 * https://ftp.gnu.org/old-gnu/Manuals/flex-2.5.4/html_node/flex_15.html
*/
