int
foo(int* a, int* b, int c)
{
  if (c)
    return *a = *b + 10;
  else if (c > *b)
    return *b = 10;
  
  return 0;
}
