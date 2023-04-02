int
foo(int a, int b)
{
  int n[] = { a, a * a, a - a, a / 3 };
  int m[4] = {};
  return n[b] + m[b];
}
