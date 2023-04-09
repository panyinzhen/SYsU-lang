const int a = 1;
const int b[3] = { a, 2, 3 };
int c = a; // 按照C标准应该是错误的！（按C++是对的）
// int d[] = { b[0], b[1], b[2] }; // 错误

int e = a || b[2];

int
main()
{
  return b[0];
}
