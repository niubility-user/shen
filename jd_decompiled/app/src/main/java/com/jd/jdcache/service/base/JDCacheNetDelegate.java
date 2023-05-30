package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Keep
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u0083\u0001\u0010\u000e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\nH&\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u0081\u0001\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0085\u0001\u0010\u0013\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\nH&\u00a2\u0006\u0004\b\u0013\u0010\u000fJ\u0083\u0001\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00022\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0014\u0010\u0011Ji\u0010\u0017\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\r\u0018\u00010\f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002H&\u00a2\u0006\u0004\b\u0017\u0010\u0018Jg\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\r2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\u0018\b\u0002\u0010\u0006\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001d"}, d2 = {"Lcom/jd/jdcache/service/base/JDCacheNetDelegate;", "Lcom/jd/jdcache/service/base/AbstractDelegate;", "", "url", "method", "", "header", "userAgent", "cookie", "body", "", "followRedirect", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jd/jdcache/service/base/NetState;", "requestFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)Lkotlinx/coroutines/flow/Flow;", "request", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Ljava/io/InputStream;", "connectFlow", "connect", "savePath", "Ljava/io/File;", "downloadFlow", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;)Lkotlinx/coroutines/flow/Flow;", IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<init>", "()V", "JDCache_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes13.dex */
public abstract class JDCacheNetDelegate extends AbstractDelegate {
    public static /* synthetic */ Object connect$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            return jDCacheNetDelegate.connect(str, (i2 & 2) != 0 ? "GET" : str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : map2, (i2 & 64) != 0 ? true : z, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: connect");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076 A[Catch: Exception -> 0x007e, TRY_LEAVE, TryCatch #0 {Exception -> 0x007e, blocks: (B:12:0x0044, B:22:0x0072, B:24:0x0076, B:17:0x0053, B:19:0x0059), top: B:31:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object connect$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate r5, java.lang.String r6, java.lang.String r7, java.util.Map r8, java.lang.String r9, java.lang.String r10, java.util.Map r11, boolean r12, kotlin.coroutines.Continuation r13) {
        /*
            boolean r0 = r13 instanceof com.jd.jdcache.service.base.JDCacheNetDelegate$connect$1
            if (r0 == 0) goto L13
            r0 = r13
            com.jd.jdcache.service.base.JDCacheNetDelegate$connect$1 r0 = (com.jd.jdcache.service.base.JDCacheNetDelegate$connect$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.service.base.JDCacheNetDelegate$connect$1 r0 = new com.jd.jdcache.service.base.JDCacheNetDelegate$connect$1
            r0.<init>(r5, r13)
        L18:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L50
            if (r2 != r3) goto L48
            boolean r5 = r0.Z$0
            java.lang.Object r5 = r0.L$6
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r0.L$5
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$4
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$3
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.jd.jdcache.service.base.JDCacheNetDelegate r5 = (com.jd.jdcache.service.base.JDCacheNetDelegate) r5
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Exception -> L7e
            goto L72
        L48:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L50:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.flow.Flow r13 = r5.connectFlow(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L7e
            if (r13 == 0) goto L8e
            r0.L$0 = r5     // Catch: java.lang.Exception -> L7e
            r0.L$1 = r6     // Catch: java.lang.Exception -> L7e
            r0.L$2 = r7     // Catch: java.lang.Exception -> L7e
            r0.L$3 = r8     // Catch: java.lang.Exception -> L7e
            r0.L$4 = r9     // Catch: java.lang.Exception -> L7e
            r0.L$5 = r10     // Catch: java.lang.Exception -> L7e
            r0.L$6 = r11     // Catch: java.lang.Exception -> L7e
            r0.Z$0 = r12     // Catch: java.lang.Exception -> L7e
            r0.label = r3     // Catch: java.lang.Exception -> L7e
            java.lang.Object r13 = kotlinx.coroutines.flow.FlowKt.toList$default(r13, r4, r0, r3, r4)     // Catch: java.lang.Exception -> L7e
            if (r13 != r1) goto L72
            return r1
        L72:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Exception -> L7e
            if (r13 == 0) goto L8e
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r13)     // Catch: java.lang.Exception -> L7e
            com.jd.jdcache.service.base.NetState r6 = (com.jd.jdcache.service.base.NetState) r6     // Catch: java.lang.Exception -> L7e
            r4 = r6
            goto L8e
        L7e:
            r6 = move-exception
            com.jd.jdcache.util.JDCacheLog r7 = com.jd.jdcache.util.JDCacheLog.INSTANCE
            boolean r8 = r7.getCanLog()
            if (r8 == 0) goto L8e
            java.lang.String r5 = r5.getName()
            r7.e(r5, r6)
        L8e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.base.JDCacheNetDelegate.connect$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate, java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.util.Map, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Flow connectFlow$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, int i2, Object obj) {
        if (obj == null) {
            return jDCacheNetDelegate.connectFlow(str, (i2 & 2) != 0 ? "GET" : str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) == 0 ? map2 : null, (i2 & 64) != 0 ? true : z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: connectFlow");
    }

    public static /* synthetic */ Object download$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, String str3, Map map, String str4, String str5, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            return jDCacheNetDelegate.download(str, str2, (i2 & 4) != 0 ? "GET" : str3, (i2 & 8) != 0 ? null : map, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: download");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0072 A[Catch: Exception -> 0x007a, TRY_LEAVE, TryCatch #0 {Exception -> 0x007a, blocks: (B:12:0x0042, B:22:0x006e, B:24:0x0072, B:17:0x0051, B:19:0x0057), top: B:31:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object download$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.util.Map r9, java.lang.String r10, java.lang.String r11, kotlin.coroutines.Continuation r12) {
        /*
            boolean r0 = r12 instanceof com.jd.jdcache.service.base.JDCacheNetDelegate$download$1
            if (r0 == 0) goto L13
            r0 = r12
            com.jd.jdcache.service.base.JDCacheNetDelegate$download$1 r0 = (com.jd.jdcache.service.base.JDCacheNetDelegate$download$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.service.base.JDCacheNetDelegate$download$1 r0 = new com.jd.jdcache.service.base.JDCacheNetDelegate$download$1
            r0.<init>(r5, r12)
        L18:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L4e
            if (r2 != r3) goto L46
            java.lang.Object r5 = r0.L$6
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$5
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$4
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r0.L$3
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.jd.jdcache.service.base.JDCacheNetDelegate r5 = (com.jd.jdcache.service.base.JDCacheNetDelegate) r5
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Exception -> L7a
            goto L6e
        L46:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L4e:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.flow.Flow r12 = r5.downloadFlow(r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> L7a
            if (r12 == 0) goto L8a
            r0.L$0 = r5     // Catch: java.lang.Exception -> L7a
            r0.L$1 = r6     // Catch: java.lang.Exception -> L7a
            r0.L$2 = r7     // Catch: java.lang.Exception -> L7a
            r0.L$3 = r8     // Catch: java.lang.Exception -> L7a
            r0.L$4 = r9     // Catch: java.lang.Exception -> L7a
            r0.L$5 = r10     // Catch: java.lang.Exception -> L7a
            r0.L$6 = r11     // Catch: java.lang.Exception -> L7a
            r0.label = r3     // Catch: java.lang.Exception -> L7a
            java.lang.Object r12 = kotlinx.coroutines.flow.FlowKt.toList$default(r12, r4, r0, r3, r4)     // Catch: java.lang.Exception -> L7a
            if (r12 != r1) goto L6e
            return r1
        L6e:
            java.util.List r12 = (java.util.List) r12     // Catch: java.lang.Exception -> L7a
            if (r12 == 0) goto L8a
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r12)     // Catch: java.lang.Exception -> L7a
            com.jd.jdcache.service.base.NetState r6 = (com.jd.jdcache.service.base.NetState) r6     // Catch: java.lang.Exception -> L7a
            r4 = r6
            goto L8a
        L7a:
            r6 = move-exception
            com.jd.jdcache.util.JDCacheLog r7 = com.jd.jdcache.util.JDCacheLog.INSTANCE
            boolean r8 = r7.getCanLog()
            if (r8 == 0) goto L8a
            java.lang.String r5 = r5.getName()
            r7.e(r5, r6)
        L8a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.base.JDCacheNetDelegate.download$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate, java.lang.String, java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Flow downloadFlow$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, String str3, Map map, String str4, String str5, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 4) != 0) {
                str3 = "GET";
            }
            return jDCacheNetDelegate.downloadFlow(str, str2, str3, (i2 & 8) != 0 ? null : map, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : str5);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: downloadFlow");
    }

    public static /* synthetic */ Object request$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, Continuation continuation, int i2, Object obj) {
        if (obj == null) {
            return jDCacheNetDelegate.request(str, (i2 & 2) != 0 ? "GET" : str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) != 0 ? null : map2, (i2 & 64) != 0 ? true : z, continuation);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: request");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0076 A[Catch: Exception -> 0x007e, TRY_LEAVE, TryCatch #0 {Exception -> 0x007e, blocks: (B:12:0x0044, B:22:0x0072, B:24:0x0076, B:17:0x0053, B:19:0x0059), top: B:31:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object request$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate r5, java.lang.String r6, java.lang.String r7, java.util.Map r8, java.lang.String r9, java.lang.String r10, java.util.Map r11, boolean r12, kotlin.coroutines.Continuation r13) {
        /*
            boolean r0 = r13 instanceof com.jd.jdcache.service.base.JDCacheNetDelegate$request$1
            if (r0 == 0) goto L13
            r0 = r13
            com.jd.jdcache.service.base.JDCacheNetDelegate$request$1 r0 = (com.jd.jdcache.service.base.JDCacheNetDelegate$request$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.jd.jdcache.service.base.JDCacheNetDelegate$request$1 r0 = new com.jd.jdcache.service.base.JDCacheNetDelegate$request$1
            r0.<init>(r5, r13)
        L18:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L50
            if (r2 != r3) goto L48
            boolean r5 = r0.Z$0
            java.lang.Object r5 = r0.L$6
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r0.L$5
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$4
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$3
            java.util.Map r5 = (java.util.Map) r5
            java.lang.Object r5 = r0.L$2
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r5 = r0.L$0
            com.jd.jdcache.service.base.JDCacheNetDelegate r5 = (com.jd.jdcache.service.base.JDCacheNetDelegate) r5
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Exception -> L7e
            goto L72
        L48:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L50:
            kotlin.ResultKt.throwOnFailure(r13)
            kotlinx.coroutines.flow.Flow r13 = r5.requestFlow(r6, r7, r8, r9, r10, r11, r12)     // Catch: java.lang.Exception -> L7e
            if (r13 == 0) goto L8e
            r0.L$0 = r5     // Catch: java.lang.Exception -> L7e
            r0.L$1 = r6     // Catch: java.lang.Exception -> L7e
            r0.L$2 = r7     // Catch: java.lang.Exception -> L7e
            r0.L$3 = r8     // Catch: java.lang.Exception -> L7e
            r0.L$4 = r9     // Catch: java.lang.Exception -> L7e
            r0.L$5 = r10     // Catch: java.lang.Exception -> L7e
            r0.L$6 = r11     // Catch: java.lang.Exception -> L7e
            r0.Z$0 = r12     // Catch: java.lang.Exception -> L7e
            r0.label = r3     // Catch: java.lang.Exception -> L7e
            java.lang.Object r13 = kotlinx.coroutines.flow.FlowKt.toList$default(r13, r4, r0, r3, r4)     // Catch: java.lang.Exception -> L7e
            if (r13 != r1) goto L72
            return r1
        L72:
            java.util.List r13 = (java.util.List) r13     // Catch: java.lang.Exception -> L7e
            if (r13 == 0) goto L8e
            java.lang.Object r6 = kotlin.collections.CollectionsKt.last(r13)     // Catch: java.lang.Exception -> L7e
            com.jd.jdcache.service.base.NetState r6 = (com.jd.jdcache.service.base.NetState) r6     // Catch: java.lang.Exception -> L7e
            r4 = r6
            goto L8e
        L7e:
            r6 = move-exception
            com.jd.jdcache.util.JDCacheLog r7 = com.jd.jdcache.util.JDCacheLog.INSTANCE
            boolean r8 = r7.getCanLog()
            if (r8 == 0) goto L8e
            java.lang.String r5 = r5.getName()
            r7.e(r5, r6)
        L8e:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jd.jdcache.service.base.JDCacheNetDelegate.request$suspendImpl(com.jd.jdcache.service.base.JDCacheNetDelegate, java.lang.String, java.lang.String, java.util.Map, java.lang.String, java.lang.String, java.util.Map, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Flow requestFlow$default(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, int i2, Object obj) {
        if (obj == null) {
            return jDCacheNetDelegate.requestFlow(str, (i2 & 2) != 0 ? "GET" : str2, (i2 & 4) != 0 ? null : map, (i2 & 8) != 0 ? null : str3, (i2 & 16) != 0 ? null : str4, (i2 & 32) == 0 ? map2 : null, (i2 & 64) != 0 ? true : z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: requestFlow");
    }

    @Nullable
    public Object connect(@NotNull String str, @NotNull String str2, @Nullable Map<String, String> map, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map2, boolean z, @NotNull Continuation<? super NetState<InputStream>> continuation) {
        return connect$suspendImpl(this, str, str2, map, str3, str4, map2, z, continuation);
    }

    @Nullable
    public abstract Flow<NetState<InputStream>> connectFlow(@NotNull String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect);

    @Nullable
    public Object download(@NotNull String str, @NotNull String str2, @NotNull String str3, @Nullable Map<String, String> map, @Nullable String str4, @Nullable String str5, @NotNull Continuation<? super NetState<File>> continuation) {
        return download$suspendImpl(this, str, str2, str3, map, str4, str5, continuation);
    }

    @Nullable
    public abstract Flow<NetState<File>> downloadFlow(@NotNull String url, @NotNull String savePath, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie);

    @Nullable
    public Object request(@NotNull String str, @NotNull String str2, @Nullable Map<String, String> map, @Nullable String str3, @Nullable String str4, @Nullable Map<String, String> map2, boolean z, @NotNull Continuation<? super NetState<String>> continuation) {
        return request$suspendImpl(this, str, str2, map, str3, str4, map2, z, continuation);
    }

    @Nullable
    public abstract Flow<NetState<String>> requestFlow(@NotNull String url, @NotNull String method, @Nullable Map<String, String> header, @Nullable String userAgent, @Nullable String cookie, @Nullable Map<String, String> body, boolean followRedirect);
}
