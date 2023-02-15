# find_package(LLVM REQUIRED)
# find_package(BISON REQUIRED)
# bison_target(parser parser.y main.cc DEFINES_FILE parser.hh)
# add_executable(sysu-parser ${BISON_parser_OUTPUT_SOURCE})
# llvm_map_components_to_libnames(LLVM_LIBS support)
# target_link_libraries(sysu-parser ${LLVM_LIBS})
# target_include_directories(sysu-parser PRIVATE ${LLVM_INCLUDE_DIRS})

set(CMAKE_CXX_STANDARD 17)

set(antlr4-generator_DIR "/root/@/antlr4-cpp-runtime-4.11.1/install/lib/cmake/antlr4-generator")
set(ANTLR4_JAR_LOCATION "/root/.m2/repository/org/antlr/antlr4/4.11.1/antlr4-4.11.1-complete.jar")
find_package(antlr4-generator REQUIRED)
antlr4_generate(antlr_c ${CMAKE_CURRENT_SOURCE_DIR}/C.g4
  BOTH TRUE TRUE "antlr_c")

set(antlr4-runtime_DIR "/root/@/antlr4-cpp-runtime-4.11.1/install/lib/cmake/antlr4-runtime")
find_package(antlr4-runtime REQUIRED)

find_package(LLVM REQUIRED)
llvm_map_components_to_libnames(LLVM_LIBS support)

add_executable(sysu-parser
  main.cpp asg.hpp asg.cpp
  ${ANTLR4_SRC_FILES_antlr_c}
)

target_include_directories(sysu-parser PRIVATE
  . ${ANTLR4_INCLUDE_DIR_antlr_c} ${ANTLR4_INCLUDE_DIR})

target_link_libraries(sysu-parser antlr4_static ${LLVM_LIBS})

install(TARGETS sysu-parser)
