"""实验零评测脚本

参数：
  <bindir> 输出目录
  <task0_exe> task0 的可执行文件路径
"""

import json
import sys
import subprocess

if __name__ == "__main__":
    if len(sys.argv) != 3:
        print(__doc__)
        sys.exit(1)

    bindir = sys.argv[1]
    task0_exe = sys.argv[2]

    # with open(f"{bindir}/???.json", "w", encoding="utf-8") as f:
    #     f.write("")

    # TODO 此处应实现的逻辑：运行 task0_exe，检查输出是不是 "Hello, SYsU-lang!\n"，
    # TODO 是就满分，不是就零分。
