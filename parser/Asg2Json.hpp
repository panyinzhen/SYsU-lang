#include "asg.hpp"
#include <llvm/Support/JSON.h>
#include <unordered_set>

namespace asg {

namespace json = llvm::json;

class Asg2Json
{
public:
  json::Object operator()(const TranslationUnit& tu);

private:
  std::unordered_set<Obj*> _walked;
};

}
