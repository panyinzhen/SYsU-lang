# 选择实验一的完成方式："flex"或"antlr"
set(TASK1_WITH "antlr")

if(TASK1_WITH STREQUAL "flex")
  message(AUTHOR_WARNING "使用 Flex 完成实验一")
  add_subdirectory(flex)

elseif(TASK1_WITH STREQUAL "antlr")
  message(AUTHOR_WARNING "使用 ANTLR 完成实验一")
  add_subdirectory(antlr)

else()
  message(FATAL_ERROR "Unknown task1 completion way: ${TASK1_WITH}")

endif()
