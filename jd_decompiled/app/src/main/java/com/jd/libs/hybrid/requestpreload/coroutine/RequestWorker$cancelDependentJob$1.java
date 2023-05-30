package com.jd.libs.hybrid.requestpreload.coroutine;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0001\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0082@\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;", "requestItem", "Lkotlin/coroutines/Continuation;", "", "continuation", "", "cancelDependentJob", "(Lcom/jd/libs/hybrid/requestpreload/entity/RequestItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.libs.hybrid.requestpreload.coroutine.RequestWorker", f = "RequestWorker.kt", i = {0, 0, 0, 0, 0, 0}, l = {205}, m = "cancelDependentJob", n = {"this", "requestItem", "$this$forEach$iv", "element$iv", AdvanceSetting.NETWORK_TYPE, "job"}, s = {"L$0", "L$1", "L$2", "L$4", "L$5", "L$6"})
/* loaded from: classes16.dex */
public final class RequestWorker$cancelDependentJob$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RequestWorker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RequestWorker$cancelDependentJob$1(RequestWorker requestWorker, Continuation continuation) {
        super(continuation);
        this.this$0 = requestWorker;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.a(null, this);
    }
}
