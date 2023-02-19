# 1 "tester/function_test2021/029_if_test2.sysu.c"
# 1 "<built-in>" 1
# 1 "<built-in>" 3
# 361 "<built-in>" 3
# 1 "<command line>" 1
# 1 "<built-in>" 2
# 1 "tester/function_test2021/029_if_test2.sysu.c" 2
# 1 "/root/sysu/include/sysy/sylib.h" 1
# 10 "/root/sysu/include/sysy/sylib.h"
void _sysy_starttime(int lineno);
void _sysy_stoptime(int lineno);


int _sysy_getch();
void _sysy_putch(int a);

int _sysy_getint();
void _sysy_putint(int a);

int _sysy_getarray(int a[]);
void _sysy_putarray(int n, int a[]);
# 2 "tester/function_test2021/029_if_test2.sysu.c" 2

int ifElseIf() {
  int a;
  a = 5;
  int b;
  b = 10;
  if(a == 6 || b == 0xb) {
    return a;
  }
  else {
    if (b == 10 && a == 1)
      a = 25;
    else if (b == 10 && a == -5)
      a = a + 15;
    else
      a = -+a;
  }

  return a;
}

int main(){
  _sysy_putint(ifElseIf());
  return 0;
}
