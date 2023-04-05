# 编译安装
# `${CMAKE_C_COMPILER}` 仅用于编译 `.sysu.c`
# 非 SYsU 语言的代码都将直接/间接使用 `${CMAKE_CXX_COMPILER}` 编译（后缀为 `.cc`）
# rm -rf $HOME/sysu
# cmake -G Ninja \
#   -DCMAKE_BUILD_TYPE=Debug \
#   -DCMAKE_C_COMPILER=clang \
#   -DCMAKE_CXX_COMPILER=clang++ \
#   -DCMAKE_INSTALL_PREFIX=$HOME/sysu \
#   -DCMAKE_PREFIX_PATH="$(llvm-config --cmakedir)" \
#   -DCPACK_SOURCE_IGNORE_FILES=".git/;tester/third_party/" \
#   -B $HOME/sysu/build
# cmake --build $HOME/sysu/build

cmake --build $HOME/sysu/build -t install
( export PATH=$HOME/sysu/bin:$PATH \
  CPATH=$HOME/sysu/include:$CPATH \
  LIBRARY_PATH=$HOME/sysu/lib:$LIBRARY_PATH \
  LD_LIBRARY_PATH=$HOME/sysu/lib:$LD_LIBRARY_PATH &&
  # sysu-compiler --unittest=benchmark_generator_and_optimizer_1 "**/*.sysu.c" )
  clang -E -O0 /root/SYsU-lang/tester/function_test2020/13_and.sysu.c )
