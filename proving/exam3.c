static int a = 0;

int
foo(int b)
{
  a += b;
  return a;
}

int
bar(int b)
{
  a -= b;
  return a;
}
