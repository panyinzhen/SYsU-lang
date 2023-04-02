; ModuleID = 'exam4.c'
source_filename = "exam4.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @foo(i32* noundef %0, i32* noundef %1, i32 noundef %2) #0 {
  %4 = alloca i32, align 4
  %5 = alloca i32*, align 8
  %6 = alloca i32*, align 8
  %7 = alloca i32, align 4
  store i32* %0, i32** %5, align 8
  store i32* %1, i32** %6, align 8
  store i32 %2, i32* %7, align 4
  %8 = load i32, i32* %7, align 4
  %9 = icmp ne i32 %8, 0
  br i1 %9, label %10, label %15

10:                                               ; preds = %3
  %11 = load i32*, i32** %6, align 8
  %12 = load i32, i32* %11, align 4
  %13 = add nsw i32 %12, 10
  %14 = load i32*, i32** %5, align 8
  store i32 %13, i32* %14, align 4
  store i32 %13, i32* %4, align 4
  br label %24

15:                                               ; preds = %3
  %16 = load i32, i32* %7, align 4
  %17 = load i32*, i32** %6, align 8
  %18 = load i32, i32* %17, align 4
  %19 = icmp sgt i32 %16, %18
  br i1 %19, label %20, label %22

20:                                               ; preds = %15
  %21 = load i32*, i32** %6, align 8
  store i32 10, i32* %21, align 4
  store i32 10, i32* %4, align 4
  br label %24

22:                                               ; preds = %15
  br label %23

23:                                               ; preds = %22
  store i32 0, i32* %4, align 4
  br label %24

24:                                               ; preds = %23, %20, %10
  %25 = load i32, i32* %4, align 4
  ret i32 %25
}

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3, !4}
!llvm.ident = !{!5}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{i32 7, !"frame-pointer", i32 2}
!5 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
