; ModuleID = 'a.ll'
source_filename = "-"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

declare void @putint(i32) local_unnamed_addr

define i32 @main() local_unnamed_addr {
entry:
  tail call void @putint(i32 5)
  tail call void @putint(i32 6)
  tail call void @putint(i32 7)
  tail call void @putint(i32 8)
  tail call void @putint(i32 9)
  tail call void @putint(i32 10)
  tail call void @putint(i32 11)
  tail call void @putint(i32 12)
  tail call void @putint(i32 13)
  tail call void @putint(i32 14)
  tail call void @putint(i32 15)
  tail call void @putint(i32 16)
  tail call void @putint(i32 17)
  tail call void @putint(i32 233333)
  ret i32 0
}
