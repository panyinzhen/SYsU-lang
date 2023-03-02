find_package(LLVM REQUIRED)

llvm_map_components_to_libnames(LLVM_LIBS core support transformutils)



add_executable(proving-exam1 exam1.cpp)

target_link_libraries(proving-exam1 ${LLVM_LIBS})

target_include_directories(proving-exam1 PRIVATE ${LLVM_INCLUDE_DIRS})
