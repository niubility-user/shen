package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0007\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"T", "R", "LLkotlinx/coroutines/CoroutineScope;;", "", "invoke", "Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object", "kotlinx/coroutines/flow/internal/ChannelFlowTransformLatest$flowCollect$3$1$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $value;
    Object L$0;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1(Object obj, Continuation continuation, ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1 channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1) {
        super(2, continuation);
        this.$value = obj;
        this.this$0 = channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1 channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1 = new ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1(this.$value, continuation, this.this$0);
        channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1.p$ = (CoroutineScope) obj;
        return channelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChannelFlowTransformLatest$flowCollect$3$invokeSuspend$$inlined$collect$1$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        Function3 function3;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.p$;
            function3 = this.this$0.this$0.this$0.transform;
            FlowCollector flowCollector = this.this$0.this$0.$collector;
            Object obj2 = this.$value;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (function3.invoke(flowCollector, obj2, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
