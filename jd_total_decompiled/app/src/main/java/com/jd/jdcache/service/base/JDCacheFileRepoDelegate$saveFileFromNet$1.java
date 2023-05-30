package com.jd.jdcache.service.base;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0096@\u00a2\u0006\u0004\b\t\u0010\n"}, d2 = {"", "url", "relativeFilePath", "Lcom/jd/jdcache/service/base/FileSaveOption;", "option", "Lkotlin/coroutines/Continuation;", "Lcom/jd/jdcache/service/base/FileState;", "continuation", "", "saveFileFromNet", "(Ljava/lang/String;Ljava/lang/String;Lcom/jd/jdcache/service/base/FileSaveOption;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.base.JDCacheFileRepoDelegate", f = "JDCacheFileRepoDelegate.kt", i = {0, 0, 0, 0}, l = {50}, m = "saveFileFromNet$suspendImpl", n = {"this", "url", "relativeFilePath", "option"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes13.dex */
public final class JDCacheFileRepoDelegate$saveFileFromNet$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ JDCacheFileRepoDelegate this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JDCacheFileRepoDelegate$saveFileFromNet$1(JDCacheFileRepoDelegate jDCacheFileRepoDelegate, Continuation continuation) {
        super(continuation);
        this.this$0 = jDCacheFileRepoDelegate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return JDCacheFileRepoDelegate.saveFileFromNet$suspendImpl(this.this$0, null, null, null, this);
    }
}
