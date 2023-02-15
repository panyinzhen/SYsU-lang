#include <iostream>

int
main()
{
  for (int c; (c = std::cin.get()) != EOF; std::cout.put(c))
    ;
}
