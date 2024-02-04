#include "par.hpp"
#include "lex.hpp"

namespace par {

asg::Obj::Mgr gMgr;
std::unique_ptr<asg::TranslationUnit> gTranslationUnit;

} // namespace par

void
yyerror(char const* s)
{
  fflush(stdout);
  printf("\n%*s\n%*s\n", lex::g.mLine, "^", lex::g.mColumn, s);
}
