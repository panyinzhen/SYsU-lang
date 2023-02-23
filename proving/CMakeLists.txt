find_package(LLVM REQUIRED)

llvm_map_components_to_libnames(LLVM_LIBS core support transformutils)



add_executable(proving main.cpp)

target_link_libraries(proving ${LLVM_LIBS})

target_include_directories(proving PRIVATE ${LLVM_INCLUDE_DIRS})
