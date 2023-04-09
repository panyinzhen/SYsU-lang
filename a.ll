; ModuleID = '-'
source_filename = "-"

declare i32 @getint()

declare void @putint(i32 %0)

declare void @putch(i32 %0)

define i32 @main() {
entry:
  %i = alloca i32, align 4
  store i32 0, i32* %i, align 4
  br label %while_cond

while_cond:                                       ; preds = %while_loop, %entry
  %0 = load i32, i32* %i, align 4
  %1 = icmp slt i32 %0, 100000
  %2 = zext i1 %1 to i32
  %3 = icmp ne i32 %2, 0
  br i1 %3, label %while_loop, label %while_exit

while_loop:                                       ; preds = %while_cond
  %j = alloca i32, align 4
  store i32 10, i32* %j, align 4
  br label %while_cond

while_exit:                                       ; preds = %while_cond
  ret i32 0

return_exit:                                      ; No predecessors!
  unreachable
}

