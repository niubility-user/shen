package com.jd.jdcache.service.base;

import com.jd.dynamic.base.interfaces.IExceptionHandler;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00002\u0018\b\u0002\u0010\u0005\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0000\u0012\u0004\u0012\u00020\u0000\u0018\u00010\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00002\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00002\u0014\u0010\u000b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t0\bH\u0096@\u00a2\u0006\u0004\b\r\u0010\u000e"}, d2 = {"", "url", "savePath", "method", "", "header", "userAgent", "cookie", "Lkotlin/coroutines/Continuation;", "Lcom/jd/jdcache/service/base/NetState;", "Ljava/io/File;", "continuation", "", IExceptionHandler.DynamicExceptionData.TYPE_DOWNLOAD, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.base.JDCacheNetDelegate", f = "JDCacheNetDelegate.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {106}, m = "download$suspendImpl", n = {"this", "url", "savePath", "method", "header", "userAgent", "cookie"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* loaded from: classes13.dex */
public final class JDCacheNetDelegate$download$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ JDCacheNetDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JDCacheNetDelegate$download$1(JDCacheNetDelegate jDCacheNetDelegate, Continuation continuation) {
        super(continuation);
        this.this$0 = jDCacheNetDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return JDCacheNetDelegate.download$suspendImpl(this.this$0, null, null, null, null, null, null, this);
    }
}
