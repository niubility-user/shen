package com.jd.jdcache.service.impl.net;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0001\u001a\u00020\u00002\u001e\u0010\u0005\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0004\u0018\u00010\u00022\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\u0012\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\u0094@\u00a2\u0006\u0004\b\u000f\u0010\u0010"}, d2 = {"", "responseCode", "", "", "", "responseHeaders", "", "contentLength", "Ljava/io/InputStream;", "inputStream", "Lkotlin/coroutines/Continuation;", "Lcom/jd/jdcache/service/base/NetState;", "Ljava/io/File;", "continuation", "", "parseData", "(ILjava/util/Map;JLjava/io/InputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.impl.net.FileRequest", f = "FileRequest.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {30, 40}, m = "parseData", n = {"this", "responseCode", "responseHeaders", "contentLength", "inputStream", "bufferedOutputStream", "this", "responseCode", "responseHeaders", "contentLength", "inputStream", "bufferedOutputStream", "fos", "buffer", "currentSize", "hasRead"}, s = {"L$0", "I$0", "L$1", "J$0", "L$2", "L$3", "L$0", "I$0", "L$1", "J$0", "L$2", "L$3", "L$4", "L$5", "J$1", "L$6"})
/* loaded from: classes13.dex */
public final class FileRequest$parseData$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FileRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FileRequest$parseData$1(FileRequest fileRequest, Continuation continuation) {
        super(continuation);
        this.this$0 = fileRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.parseData(0, null, 0L, null, this);
    }
}
