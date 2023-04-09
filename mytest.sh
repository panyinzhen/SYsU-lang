for i in tester/functional/*.sysu.c;
do
  ( export PATH=$HOME/sysu/bin:$PATH \
    CPATH=$HOME/sysu/include:$CPATH \
    LIBRARY_PATH=$HOME/sysu/lib:$LIBRARY_PATH \
    LD_LIBRARY_PATH=$HOME/sysu/lib:$LD_LIBRARY_PATH &&
    clang -E -O0 $i | ~/sysu/build/generator/sysu-generator 1> /dev/null )
  a=$?
  if [[ $a -ne 0 ]]; then
    echo $i $a
  fi
done
