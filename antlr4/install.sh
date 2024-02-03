wget https://www.antlr.org/download/antlr-4.13.1-complete.jar
wget https://www.antlr.org/download/antlr4-cpp-runtime-4.13.1-source.zip
unzip antlr4-cpp-runtime-4.13.1-source.zip
mkdir build install
cmake . -B build -G Ninja \
  -DCMAKE_INSTALL_PREFIX=$(realpath install) \
  -DANTLR4_INSTALL=ON
cmake --build build --target install
