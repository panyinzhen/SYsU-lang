# 1 "/root/SYsU-lang/tester/function_test2020/13_and.sysu.c"
# 1 "<built-in>" 1
# 1 "<built-in>" 3
# 361 "<built-in>" 3
# 1 "<command line>" 1
# 1 "<built-in>" 2
# 1 "/root/SYsU-lang/tester/function_test2020/13_and.sysu.c" 2
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
# 2 "/root/SYsU-lang/tester/function_test2020/13_and.sysu.c" 2
int a;
int b;
int main(){
 a = _sysy_getint();
 b = _sysy_getint();
 if ( a && b ) {
  return 1;
 }
 else {
  return 0;
 }
}
