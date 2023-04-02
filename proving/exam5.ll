; ModuleID = 'exam5.c'
source_filename = "exam5.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

; Function Attrs: mustprogress nofree nosync nounwind readnone uwtable willreturn
define dso_local i32 @foo(i32 noundef %0, i32 noundef %1) local_unnamed_addr #0 {
  %3 = alloca [4 x i32], align 16
  %4 = alloca [4 x i32], align 16
  %5 = bitcast [4 x i32]* %3 to i8*
  call void @llvm.lifetime.start.p0i8(i64 16, i8* nonnull %5) #3
  %6 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 0
  store i32 %0, i32* %6, align 16, !tbaa !5
  %7 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 1
  %8 = mul nsw i32 %0, %0
  store i32 %8, i32* %7, align 4, !tbaa !5
  %9 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 2
  store i32 0, i32* %9, align 8, !tbaa !5
  %10 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 3
  %11 = sdiv i32 %0, 3
  store i32 %11, i32* %10, align 4, !tbaa !5
  %12 = bitcast [4 x i32]* %4 to i8*
  call void @llvm.lifetime.start.p0i8(i64 16, i8* nonnull %12) #3
  call void @llvm.memset.p0i8.i64(i8* noundef nonnull align 16 dereferenceable(16) %12, i8 0, i64 16, i1 false)
  %13 = getelementptr inbounds [4 x i32], [4 x i32]* %4, i64 0, i64 2
  store i32 %1, i32* %13, align 8, !tbaa !5
  %14 = sext i32 %1 to i64
  %15 = getelementptr inbounds [4 x i32], [4 x i32]* %3, i64 0, i64 %14
  %16 = load i32, i32* %15, align 4, !tbaa !5
  %17 = getelementptr inbounds [4 x i32], [4 x i32]* %4, i64 0, i64 %14
  %18 = load i32, i32* %17, align 4, !tbaa !5
  %19 = add nsw i32 %18, %16
  call void @llvm.lifetime.end.p0i8(i64 16, i8* nonnull %12) #3
  call void @llvm.lifetime.end.p0i8(i64 16, i8* nonnull %5) #3
  ret i32 %19
}

; Function Attrs: argmemonly mustprogress nofree nosync nounwind willreturn
declare void @llvm.lifetime.start.p0i8(i64 immarg, i8* nocapture) #1

; Function Attrs: argmemonly mustprogress nofree nounwind willreturn writeonly
declare void @llvm.memset.p0i8.i64(i8* nocapture writeonly, i8, i64, i1 immarg) #2

; Function Attrs: argmemonly mustprogress nofree nosync nounwind willreturn
declare void @llvm.lifetime.end.p0i8(i64 immarg, i8* nocapture) #1

attributes #0 = { mustprogress nofree nosync nounwind readnone uwtable willreturn "frame-pointer"="none" "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }
attributes #1 = { argmemonly mustprogress nofree nosync nounwind willreturn }
attributes #2 = { argmemonly mustprogress nofree nounwind willreturn writeonly }
attributes #3 = { nounwind }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 1}
!4 = !{!"Ubuntu clang version 14.0.0-1ubuntu1"}
!5 = !{!6, !6, i64 0}
!6 = !{!"int", !7, i64 0}
!7 = !{!"omnipotent char", !8, i64 0}
!8 = !{!"Simple C/C++ TBAA"}
