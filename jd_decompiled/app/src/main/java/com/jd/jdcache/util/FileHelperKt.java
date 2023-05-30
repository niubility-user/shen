package com.jd.jdcache.util;

import kotlin.Metadata;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u001b\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0002\u0010\u0003\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u0004\u0018\u00010\u0001H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006"}, d2 = {"Ljava/io/File;", "", "getString", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateFileName", "(Ljava/lang/String;)Ljava/lang/String;", "JDCache_release"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public final class FileHelperKt {
    @NotNull
    public static final String generateFileName(@Nullable String str) {
        String fileNameFromUrl = str != null ? UrlHelper.INSTANCE.getFileNameFromUrl(str) : null;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append(Random.INSTANCE.nextInt(900) + 100);
        String sb2 = sb.toString();
        if (fileNameFromUrl == null || fileNameFromUrl.length() == 0) {
            return sb2;
        }
        return fileNameFromUrl + '_' + sb2;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x003b  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object getString(@org.jetbrains.annotations.Nullable java.io.File r8, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super java.lang.String> r9) {
        /*
            boolean r0 = r9 instanceof com.jd.jdcache.util.FileHelperKt$getString$1
            if (r0 == 0) goto L13
            r0 = r9
            com.jd.jdcache.util.FileHelperKt$getString$1 r0 = (com.jd.jdcache.util.FileHelperKt$getString$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.util.FileHelperKt$getString$1 r0 = new com.jd.jdcache.util.FileHelperKt$getString$1
            r0.<init>(r9)
        L18:
            r5 = r0
            java.lang.Object r9 = r5.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L3b
            if (r1 != r2) goto L33
            java.lang.Object r8 = r5.L$1
            java.io.File r8 = (java.io.File) r8
            java.lang.Object r8 = r5.L$0
            java.io.File r8 = (java.io.File) r8
            kotlin.ResultKt.throwOnFailure(r9)
            goto L66
        L33:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L3b:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r8 == 0) goto L69
            boolean r9 = r8.exists()
            if (r9 == 0) goto L69
            boolean r9 = r8.isFile()
            if (r9 != 0) goto L4d
            goto L69
        L4d:
            com.jd.jdcache.util.CoroutineHelper r1 = com.jd.jdcache.util.CoroutineHelper.INSTANCE
            r9 = 0
            com.jd.jdcache.util.FileHelperKt$getString$2$1 r4 = new com.jd.jdcache.util.FileHelperKt$getString$2$1
            r4.<init>(r8, r3)
            r6 = 1
            r7 = 0
            r5.L$0 = r8
            r5.L$1 = r8
            r5.label = r2
            r2 = r8
            r3 = r9
            java.lang.Object r9 = com.jd.jdcache.util.CoroutineHelper.runOnIo$default(r1, r2, r3, r4, r5, r6, r7)
            if (r9 != r0) goto L66
            return r0
        L66:
            java.lang.String r9 = (java.lang.String) r9
            r3 = r9
        L69:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.util.FileHelperKt.getString(java.io.File, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
