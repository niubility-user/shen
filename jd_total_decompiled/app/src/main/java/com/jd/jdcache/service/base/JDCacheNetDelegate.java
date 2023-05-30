package com.jd.jdcache.service.base;

import androidx.annotation.Keep;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.jdcache.util.JDCacheLog;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__CollectionKt;
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
    */
    public static /* synthetic */ Object connect$suspendImpl(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, Continuation continuation) {
        JDCacheNetDelegate$connect$1 jDCacheNetDelegate$connect$1;
        Object coroutine_suspended;
        int i2;
        List list;
        try {
            if (continuation instanceof JDCacheNetDelegate$connect$1) {
                jDCacheNetDelegate$connect$1 = (JDCacheNetDelegate$connect$1) continuation;
                int i3 = jDCacheNetDelegate$connect$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    jDCacheNetDelegate$connect$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = jDCacheNetDelegate$connect$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = jDCacheNetDelegate$connect$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow<NetState<InputStream>> connectFlow = jDCacheNetDelegate.connectFlow(str, str2, map, str3, str4, map2, z);
                        if (connectFlow == null) {
                            return null;
                        }
                        jDCacheNetDelegate$connect$1.L$0 = jDCacheNetDelegate;
                        jDCacheNetDelegate$connect$1.L$1 = str;
                        jDCacheNetDelegate$connect$1.L$2 = str2;
                        jDCacheNetDelegate$connect$1.L$3 = map;
                        jDCacheNetDelegate$connect$1.L$4 = str3;
                        jDCacheNetDelegate$connect$1.L$5 = str4;
                        jDCacheNetDelegate$connect$1.L$6 = map2;
                        jDCacheNetDelegate$connect$1.Z$0 = z;
                        jDCacheNetDelegate$connect$1.label = 1;
                        obj = FlowKt__CollectionKt.toList$default(connectFlow, null, jDCacheNetDelegate$connect$1, 1, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        boolean z2 = jDCacheNetDelegate$connect$1.Z$0;
                        Map map3 = (Map) jDCacheNetDelegate$connect$1.L$6;
                        String str5 = (String) jDCacheNetDelegate$connect$1.L$5;
                        String str6 = (String) jDCacheNetDelegate$connect$1.L$4;
                        Map map4 = (Map) jDCacheNetDelegate$connect$1.L$3;
                        String str7 = (String) jDCacheNetDelegate$connect$1.L$2;
                        String str8 = (String) jDCacheNetDelegate$connect$1.L$1;
                        jDCacheNetDelegate = (JDCacheNetDelegate) jDCacheNetDelegate$connect$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    list = (List) obj;
                    if (list == null) {
                        return (NetState) CollectionsKt.last(list);
                    }
                    return null;
                }
            }
            if (i2 != 0) {
            }
            list = (List) obj;
            if (list == null) {
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(jDCacheNetDelegate.getName(), e2);
                return null;
            }
            return null;
        }
        jDCacheNetDelegate$connect$1 = new JDCacheNetDelegate$connect$1(jDCacheNetDelegate, continuation);
        Object obj2 = jDCacheNetDelegate$connect$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jDCacheNetDelegate$connect$1.label;
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
    */
    public static /* synthetic */ Object download$suspendImpl(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, String str3, Map map, String str4, String str5, Continuation continuation) {
        JDCacheNetDelegate$download$1 jDCacheNetDelegate$download$1;
        Object coroutine_suspended;
        int i2;
        List list;
        try {
            if (continuation instanceof JDCacheNetDelegate$download$1) {
                jDCacheNetDelegate$download$1 = (JDCacheNetDelegate$download$1) continuation;
                int i3 = jDCacheNetDelegate$download$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    jDCacheNetDelegate$download$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = jDCacheNetDelegate$download$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = jDCacheNetDelegate$download$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow<NetState<File>> downloadFlow = jDCacheNetDelegate.downloadFlow(str, str2, str3, map, str4, str5);
                        if (downloadFlow == null) {
                            return null;
                        }
                        jDCacheNetDelegate$download$1.L$0 = jDCacheNetDelegate;
                        jDCacheNetDelegate$download$1.L$1 = str;
                        jDCacheNetDelegate$download$1.L$2 = str2;
                        jDCacheNetDelegate$download$1.L$3 = str3;
                        jDCacheNetDelegate$download$1.L$4 = map;
                        jDCacheNetDelegate$download$1.L$5 = str4;
                        jDCacheNetDelegate$download$1.L$6 = str5;
                        jDCacheNetDelegate$download$1.label = 1;
                        obj = FlowKt__CollectionKt.toList$default(downloadFlow, null, jDCacheNetDelegate$download$1, 1, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        String str6 = (String) jDCacheNetDelegate$download$1.L$6;
                        String str7 = (String) jDCacheNetDelegate$download$1.L$5;
                        Map map2 = (Map) jDCacheNetDelegate$download$1.L$4;
                        String str8 = (String) jDCacheNetDelegate$download$1.L$3;
                        String str9 = (String) jDCacheNetDelegate$download$1.L$2;
                        String str10 = (String) jDCacheNetDelegate$download$1.L$1;
                        jDCacheNetDelegate = (JDCacheNetDelegate) jDCacheNetDelegate$download$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    list = (List) obj;
                    if (list == null) {
                        return (NetState) CollectionsKt.last(list);
                    }
                    return null;
                }
            }
            if (i2 != 0) {
            }
            list = (List) obj;
            if (list == null) {
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(jDCacheNetDelegate.getName(), e2);
                return null;
            }
            return null;
        }
        jDCacheNetDelegate$download$1 = new JDCacheNetDelegate$download$1(jDCacheNetDelegate, continuation);
        Object obj2 = jDCacheNetDelegate$download$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jDCacheNetDelegate$download$1.label;
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
    */
    public static /* synthetic */ Object request$suspendImpl(JDCacheNetDelegate jDCacheNetDelegate, String str, String str2, Map map, String str3, String str4, Map map2, boolean z, Continuation continuation) {
        JDCacheNetDelegate$request$1 jDCacheNetDelegate$request$1;
        Object coroutine_suspended;
        int i2;
        List list;
        try {
            if (continuation instanceof JDCacheNetDelegate$request$1) {
                jDCacheNetDelegate$request$1 = (JDCacheNetDelegate$request$1) continuation;
                int i3 = jDCacheNetDelegate$request$1.label;
                if ((i3 & Integer.MIN_VALUE) != 0) {
                    jDCacheNetDelegate$request$1.label = i3 - Integer.MIN_VALUE;
                    Object obj = jDCacheNetDelegate$request$1.result;
                    coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    i2 = jDCacheNetDelegate$request$1.label;
                    if (i2 != 0) {
                        ResultKt.throwOnFailure(obj);
                        Flow<NetState<String>> requestFlow = jDCacheNetDelegate.requestFlow(str, str2, map, str3, str4, map2, z);
                        if (requestFlow == null) {
                            return null;
                        }
                        jDCacheNetDelegate$request$1.L$0 = jDCacheNetDelegate;
                        jDCacheNetDelegate$request$1.L$1 = str;
                        jDCacheNetDelegate$request$1.L$2 = str2;
                        jDCacheNetDelegate$request$1.L$3 = map;
                        jDCacheNetDelegate$request$1.L$4 = str3;
                        jDCacheNetDelegate$request$1.L$5 = str4;
                        jDCacheNetDelegate$request$1.L$6 = map2;
                        jDCacheNetDelegate$request$1.Z$0 = z;
                        jDCacheNetDelegate$request$1.label = 1;
                        obj = FlowKt__CollectionKt.toList$default(requestFlow, null, jDCacheNetDelegate$request$1, 1, null);
                        if (obj == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (i2 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        boolean z2 = jDCacheNetDelegate$request$1.Z$0;
                        Map map3 = (Map) jDCacheNetDelegate$request$1.L$6;
                        String str5 = (String) jDCacheNetDelegate$request$1.L$5;
                        String str6 = (String) jDCacheNetDelegate$request$1.L$4;
                        Map map4 = (Map) jDCacheNetDelegate$request$1.L$3;
                        String str7 = (String) jDCacheNetDelegate$request$1.L$2;
                        String str8 = (String) jDCacheNetDelegate$request$1.L$1;
                        jDCacheNetDelegate = (JDCacheNetDelegate) jDCacheNetDelegate$request$1.L$0;
                        ResultKt.throwOnFailure(obj);
                    }
                    list = (List) obj;
                    if (list == null) {
                        return (NetState) CollectionsKt.last(list);
                    }
                    return null;
                }
            }
            if (i2 != 0) {
            }
            list = (List) obj;
            if (list == null) {
            }
        } catch (Exception e2) {
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(jDCacheNetDelegate.getName(), e2);
                return null;
            }
            return null;
        }
        jDCacheNetDelegate$request$1 = new JDCacheNetDelegate$request$1(jDCacheNetDelegate, continuation);
        Object obj2 = jDCacheNetDelegate$request$1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = jDCacheNetDelegate$request$1.label;
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
