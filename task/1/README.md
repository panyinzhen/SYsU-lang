# 实验一：词法分析

本实验要求完成一个词法分析器命令行程序 `task1`，它的用法如下：

```
task1 <input> <output>
```

其中 `<input>` 是 `clang -E <src>` 的输出文件路径，`<output>` 是输出文件路径。程序读取 `<input>` 的内容，对其进行词法分析，然后将词法单元输出到 `<output>`。

`<output>` 中的内容应与 `clang -cc1 -dump-tokens` 的输出相当，但不必完全一致，只要结果正确即可。

## 输出格式

以如下代码为例：

```c
int main(){
    return 3;
}
```

`clang -cc1 -dump-tokens` 的输出如下：

```
int 'int'        [StartOfLine]  Loc=<test/cases/000_main.sysu.c:1:1>
identifier 'main'        [LeadingSpace] Loc=<test/cases/000_main.sysu.c:1:5>
l_paren '('             Loc=<test/cases/000_main.sysu.c:1:9>
r_paren ')'             Loc=<test/cases/000_main.sysu.c:1:10>
l_brace '{'             Loc=<test/cases/000_main.sysu.c:1:11>
return 'return'  [StartOfLine] [LeadingSpace]   Loc=<test/cases/000_main.sysu.c:2:5>
numeric_constant '3'     [LeadingSpace] Loc=<test/cases/000_main.sysu.c:2:12>
semi ';'                Loc=<test/cases/000_main.sysu.c:2:13>
r_brace '}'      [StartOfLine]  Loc=<test/cases/000_main.sysu.c:3:1>
eof ''          Loc=<test/cases/000_main.sysu.c:3:2>
```

观察总结可知，每一行的输出包含：

1. （一个）有效 token。
2. （一个）由单引号包裹的匹配串。
3. （若干）识别上一个有效 token 到这个有效 token 间遇到的无效 token。
4. （一个）有效 token 的位置。

## 实现方式

本次提供了两种方式来完成该实验：[基于 Flex](./flex)（默认）和[基于 ANTLR](./antlr)。

你可以通过更改[实验设置](../../config.cmake)中的 `TASK1_WITH` 来切换所使用的开发模板。

## 运行测试

实验框架为每个测试用例都创建了一个 CTest 项，以 `test/` 为前缀。每个 CTest 项会调用你的程序，并将输出保存到 `build/test/task1/<case_path>/` 目录中的 `output` 文件。

**注意测试运行成功仅代表你的程序能够正常运行结束，不代表你的程序输出了正确的结果**。通过构建 `task1-answer` 目标来在相同目录下生成 `clang -cc1 -dump-tokens` 的参考答案，将它和你的 `output` 文件进行比较来判断你的程序是否正确。

## 评分规则

本实验的评分分为两部分：基础部分和挑战部分。

- 对于基础部分的实验，由低到高分别给出三档实验要求，并要求通过对应的自动评测。详见自动评测细则一节。
- 对于挑战部分的实验，你可以完成挑战方向一节的要求，也可以自行探索；如果可能，请同时编写对应的自动评测脚本。助教将按照你实现的难度给出评分。

如有疑问，参照 `clang -cc1 -dump-tokens 2>&1`。你需要提交一份实验报告，简要记录你的实验过程、遇到的难点以及解决的方法，并在报告中附上自动评测的结果。

### 自动评测细则

本次实验的评测项目为 `lexer-[0-3]`。`lexer-0` 仅用于证明模板（代码与评测脚本）可以正确工作，不计入成绩；其他三个评测项依次检查：

1. `sysu-lexer` 是否提取出正确的 token（60 分）。
2. `sysu-lexer` 是否提取出正确的 token location（30 分）。
3. `sysu-lexer` 是否识别其他无关字符（10 分）。

评测脚本忽略空白符，可以查看[评测脚本](../compiler/sysu-compiler)以了解检查算法，但不得修改评测逻辑而投机取巧。你也可以像这样调用评测脚本，单独执行其中某一个评测项。

```bash
( export PATH=$HOME/sysu/bin:$PATH \
  CPATH=$HOME/sysu/include:$CPATH \
  LIBRARY_PATH=$HOME/sysu/lib:$LIBRARY_PATH \
  LD_LIBRARY_PATH=$HOME/sysu/lib:$LD_LIBRARY_PATH &&
  sysu-compiler --unittest=lexer-1 "**/*.sysu.c" )
```

### 挑战方向

本节给出一些挑战方向供参考。

1. 扩展更多 C 语言的 token。
2. 不借助 flex，并完全使用 SYsU 完成本实验，然后用它作为输入测试功能是否正确，以实现自举。
   - 提示：如果你不知道如何下手的话，仔细回忆老师上课所讲的内容，尤其是**正则文法与有限自动机（FA）的等价性**！
3. 借助 libclang 实现相同的功能。
4. 改进这个实验模板（欢迎 PR！）。
5. Do what you want to do。

## 你可能会感兴趣的

- [Lexical Analysis With Flex](http://westes.github.io/flex/manual/)
- [FindFLEX — CMake 3.20.6 Documentation](https://cmake.org/cmake/help/v3.20/module/FindFLEX.html)
- [Preprocessor Output](https://gcc.gnu.org/onlinedocs/gcc-13.2.0/cpp/Preprocessor-Output.html)
- [这篇博客](https://wu-kan.cn/2020/05/14/%E4%BD%BF%E7%94%A8%E8%AF%8D%E6%B3%95%E5%88%86%E6%9E%90%E5%99%A8-Flex-%E6%8F%90%E5%8F%96%E7%A8%8B%E5%BA%8F%E4%B8%AD%E7%9A%84%E6%95%B4%E6%95%B0%E5%92%8C%E6%B5%AE%E7%82%B9%E6%95%B0/)提到了一种处理注释的方案，如果你不想使用 [flex 自带的注释处理方法](http://westes.github.io/flex/manual/Comments-in-the-Input.html)（当然，实际上注释在预处理阶段已经去除了…）。
- [这篇博客](https://wu-kan.cn/2020/07/03/%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F%E5%85%B3%E7%B3%BB%E5%88%A4%E5%AE%9A/)通过构造有限自动机，判断两个正则表达式的关系。
