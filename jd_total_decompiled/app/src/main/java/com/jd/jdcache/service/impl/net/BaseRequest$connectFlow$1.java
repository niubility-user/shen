package com.jd.jdcache.service.impl.net;

import com.jd.jdcache.service.base.NetState;
import java.net.URL;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/jd/jdcache/service/base/NetState;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.impl.net.BaseRequest$connectFlow$1", f = "BaseRequest.kt", i = {0, 0, 1, 1}, l = {92, 92}, m = "invokeSuspend", n = {"$this$flow", "url", "$this$flow", "url"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes13.dex */
public final class BaseRequest$connectFlow$1<T> extends SuspendLambda implements Function2<FlowCollector<? super NetState<T>>, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private FlowCollector p$;
    final /* synthetic */ BaseRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRequest$connectFlow$1(BaseRequest baseRequest, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        BaseRequest$connectFlow$1 baseRequest$connectFlow$1 = new BaseRequest$connectFlow$1(this.this$0, continuation);
        baseRequest$connectFlow$1.p$ = (FlowCollector) obj;
        return baseRequest$connectFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((BaseRequest$connectFlow$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FlowCollector flowCollector;
        FlowCollector flowCollector2;
        URL url;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            flowCollector = this.p$;
            this.this$0.flowCollector = flowCollector;
            URL url2 = new URL(this.this$0.getRequestUrl());
            BaseRequest baseRequest = this.this$0;
            this.L$0 = flowCollector;
            this.L$1 = url2;
            this.L$2 = flowCollector;
            this.label = 1;
            Object connect = baseRequest.connect(url2, this);
            if (connect == coroutine_suspended) {
                return coroutine_suspended;
            }
            flowCollector2 = flowCollector;
            url = url2;
            obj = connect;
        } else if (i2 != 1) {
            if (i2 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            URL url3 = (URL) this.L$1;
            FlowCollector flowCollector3 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            flowCollector = (FlowCollector) this.L$2;
            url = (URL) this.L$1;
            flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        this.L$0 = flowCollector2;
        this.L$1 = url;
        this.label = 2;
        if (flowCollector.emit(obj, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
