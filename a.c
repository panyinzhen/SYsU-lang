# 1 "/root/SYsU-lang/tester/functional/057_if_complex_expr.sysu.c"
# 1 "<built-in>" 1
# 1 "<built-in>" 3
# 361 "<built-in>" 3
# 1 "<command line>" 1
# 1 "<built-in>" 2
# 1 "/root/SYsU-lang/tester/functional/057_if_complex_expr.sysu.c" 2
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
# 2 "/root/SYsU-lang/tester/functional/057_if_complex_expr.sysu.c" 2

int main () {
    int a;
    int b;
    int c;
    int d;
    int result;
    a = 5;
    b = 5;
    c = 1;
    d = -2;
    result = 2;
    if ((d * 1 / 2) < 0 || (a - b) != 0 && (c + 3) % 2 != 0) {
        _sysy_putint(result);
    }
    if ((d % 2 + 67) < 0 || (a - b) != 0 && (c + 2) % 2 != 0) {
        result = 4;
        _sysy_putint(result);
    }
    return 0;
}
