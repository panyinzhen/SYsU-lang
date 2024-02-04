# 你的学号
set(STUDENT_ID "0123456789")
# 你的姓名
set(STUDENT_NAME "某某某")

# 实验一的完成方式："flex"或"antlr"
set(TASK1_WITH "antlr")

# 实验二的完成方式："bison"或"antlr"
set(TASK2_WITH "antlr")
# 是否在实验二复活，ON或OFF
set(TASK2_REVIVE OFF)

# 是否在实验三复活，ON或OFF
set(TASK3_REVIVE ON)

# 是否在实验四复活，ON或OFF
set(TASK4_REVIVE ON)

# ANTLR4
set(antlr4-runtime_DIR
    "${CMAKE_SOURCE_DIR}/antlr/install/lib/cmake/antlr4-runtime")
set(antlr4-generator_DIR
    "${CMAKE_SOURCE_DIR}/antlr/install/lib/cmake/antlr4-generator")
set(ANTLR4_JAR_LOCATION "${CMAKE_SOURCE_DIR}/antlr/antlr-4.13.1-complete.jar")

# llvm clang
set(LLVM_DIR "${CMAKE_SOURCE_DIR}/llvm/install/lib/cmake/llvm")
set(CLANG_EXECUTABLE "${CMAKE_SOURCE_DIR}/llvm/install/bin/clang")
