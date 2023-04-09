#include <stdio.h>

int
getint()
{
  int n;
  scanf("%d", &n);
  return n;
}

void
putint(int n)
{
  printf("%d\n", n);
}

void
putch(int a)
{
  printf("%c\n", a);
}
