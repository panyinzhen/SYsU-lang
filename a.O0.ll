; ModuleID = 'a.c'
source_filename = "a.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @main() #0 {
  %1 = alloca i32, align 4
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  %4 = alloca i32, align 4
  %5 = alloca i32, align 4
  %6 = alloca i32, align 4
  %7 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  store i32 5, i32* %2, align 4
  br label %8

8:                                                ; preds = %47, %0
  %9 = load i32, i32* %2, align 4
  %10 = icmp slt i32 %9, 18
  br i1 %10, label %11, label %50

11:                                               ; preds = %8
  %12 = load i32, i32* %2, align 4
  call void @putint(i32 noundef %12)
  store i32 0, i32* %3, align 4
  br label %13

13:                                               ; preds = %44, %11
  %14 = load i32, i32* %3, align 4
  %15 = icmp slt i32 %14, 18
  br i1 %15, label %16, label %47

16:                                               ; preds = %13
  store i32 0, i32* %4, align 4
  br label %17

17:                                               ; preds = %41, %16
  %18 = load i32, i32* %4, align 4
  %19 = icmp slt i32 %18, 18
  br i1 %19, label %20, label %44

20:                                               ; preds = %17
  store i32 0, i32* %5, align 4
  br label %21

21:                                               ; preds = %38, %20
  %22 = load i32, i32* %5, align 4
  %23 = icmp slt i32 %22, 18
  br i1 %23, label %24, label %41

24:                                               ; preds = %21
  store i32 0, i32* %6, align 4
  br label %25

25:                                               ; preds = %35, %24
  %26 = load i32, i32* %6, align 4
  %27 = icmp slt i32 %26, 18
  br i1 %27, label %28, label %38

28:                                               ; preds = %25
  store i32 0, i32* %7, align 4
  br label %29

29:                                               ; preds = %32, %28
  %30 = load i32, i32* %7, align 4
  %31 = icmp slt i32 %30, 7
  br i1 %31, label %32, label %35

32:                                               ; preds = %29
  %33 = load i32, i32* %7, align 4
  %34 = add nsw i32 %33, 1
  store i32 %34, i32* %7, align 4
  br label %29, !llvm.loop !6

35:                                               ; preds = %29
  %36 = load i32, i32* %6, align 4
  %37 = add nsw i32 %36, 1
  store i32 %37, i32* %6, align 4
  br label %25, !llvm.loop !8

38:                                               ; preds = %25
  %39 = load i32, i32* %5, align 4
  %40 = add nsw i32 %39, 1
  store i32 %40, i32* %5, align 4
  br label %21, !llvm.loop !9

41:                                               ; preds = %21
  %42 = load i32, i32* %4, align 4
  %43 = add nsw i32 %42, 1
  store i32 %43, i32* %4, align 4
  br label %17, !llvm.loop !10

44:                                               ; preds = %17
  %45 = load i32, i32* %3, align 4
  %46 = add nsw i32 %45, 1
  store i32 %46, i32* %3, align 4
  br label %13, !llvm.loop !11

47:                                               ; preds = %13
  %48 = load i32, i32* %2, align 4
  %49 = add nsw i32 %48, 1
  store i32 %49, i32* %2, align 4
  br label %8, !llvm.loop !12

50:                                               ; preds = %8
  call void @putint(i32 noundef 233333)
  ret i32 0
}

declare void @putint(i32 noundef) #1

attributes #0 = { noinline nounwind optnone uwtable "frame-pointer"="all" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3, !4}
!llvm.ident = !{!5}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{i32 7, !"frame-pointer", i32 2}
!5 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
!6 = distinct !{!6, !7}
!7 = !{!"llvm.loop.mustprogress"}
!8 = distinct !{!8, !7}
!9 = distinct !{!9, !7}
!10 = distinct !{!10, !7}
!11 = distinct !{!11, !7}
!12 = distinct !{!12, !7}
