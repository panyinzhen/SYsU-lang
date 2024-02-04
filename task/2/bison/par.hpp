#pragma once

#include "asg.hpp"
#include <memory>

namespace par {

extern asg::Obj::Mgr gMgr;
extern std::unique_ptr<asg::TranslationUnit> gTranslationUnit;

} // namespace par
