package com.jd.jdcache.service.impl.net;

import com.jd.jdcache.service.base.NetState;
import com.jd.jdcache.util.JDCacheLog;
import com.jingdong.app.mall.e;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\b\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u0000*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u008a@\u00a2\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/jd/jdcache/service/base/NetState;", "", e.a, "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.jd.jdcache.service.impl.net.BaseRequest$connectFlow$3", f = "BaseRequest.kt", i = {0, 0}, l = {97}, m = "invokeSuspend", n = {"$this$catch", e.a}, s = {"L$0", "L$1"})
/* loaded from: classes13.dex */
public final class BaseRequest$connectFlow$3<T> extends SuspendLambda implements Function3<FlowCollector<? super NetState<T>>, Throwable, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    private FlowCollector p$;
    private Throwable p$0;
    final /* synthetic */ BaseRequest this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRequest$connectFlow$3(BaseRequest baseRequest, Continuation continuation) {
        super(3, continuation);
        this.this$0 = baseRequest;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull FlowCollector<? super NetState<T>> flowCollector, @NotNull Throwable th, @NotNull Continuation<? super Unit> continuation) {
        BaseRequest$connectFlow$3 baseRequest$connectFlow$3 = new BaseRequest$connectFlow$3(this.this$0, continuation);
        baseRequest$connectFlow$3.p$ = flowCollector;
        baseRequest$connectFlow$3.p$0 = th;
        return baseRequest$connectFlow$3;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Throwable th, Continuation<? super Unit> continuation) {
        return ((BaseRequest$connectFlow$3) create((FlowCollector) obj, th, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.p$;
            Throwable th = this.p$0;
            JDCacheLog jDCacheLog = JDCacheLog.INSTANCE;
            if (jDCacheLog.getCanLog()) {
                jDCacheLog.e(this.this$0.getTAG(), th);
            }
            NetState.Error error = new NetState.Error(-1, th);
            this.L$0 = flowCollector;
            this.L$1 = th;
            this.label = 1;
            if (flowCollector.emit(error, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Throwable th2 = (Throwable) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
