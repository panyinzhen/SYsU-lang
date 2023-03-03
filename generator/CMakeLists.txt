set(CMAKE_CXX_STANDARD 17)

find_package(LLVM REQUIRED)
llvm_map_components_to_libnames(LLVM_LIBS core support transformutils)

set(antlr4-runtime_DIR "/root/@/antlr4-cpp-runtime-4.11.1/install/lib/cmake/antlr4-runtime")
find_package(antlr4-runtime REQUIRED)

set(antlr4-generator_DIR "/root/@/antlr4-cpp-runtime-4.11.1/install/lib/cmake/antlr4-generator")
set(ANTLR4_JAR_LOCATION "/root/.m2/repository/org/antlr/antlr4/4.11.1/antlr4-4.11.1-complete.jar")
find_package(antlr4-generator REQUIRED)



antlr4_generate(antlr_c2 ${CMAKE_CURRENT_SOURCE_DIR}/C.g4
  BOTH TRUE TRUE "antlr_c")



add_executable(sysu-generator
  main.cpp ${ANTLR4_SRC_FILES_antlr_c2}
  
  asg.hpp
  Ast2Asg.hpp Ast2Asg.cpp
  Asg2Json.hpp Asg2Json.cpp
  InferType.hpp InferType.cpp
  EmitIR.hpp EmitIR.cpp
)

target_include_directories(sysu-generator PRIVATE .
  ${ANTLR4_INCLUDE_DIR_antlr_c2} ${ANTLR4_INCLUDE_DIR} ${LLVM_INCLUDE_DIRS})

target_link_libraries(sysu-generator antlr4_static ${LLVM_LIBS})

install(TARGETS sysu-generator)
