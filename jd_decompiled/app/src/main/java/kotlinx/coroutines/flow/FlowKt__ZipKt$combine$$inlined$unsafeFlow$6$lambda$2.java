package kotlinx.coroutines.flow;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\t\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0000\u0018\u0001\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u008a@\u00a2\u0006\u0004\b\u0006\u0010\u0007\u00a8\u0006\b"}, d2 = {"T", "R", "LLkotlinx/coroutines/flow/FlowCollector;;", "L;", AdvanceSetting.NETWORK_TYPE, "", "invoke", "kotlin/Array", "kotlinx/coroutines/flow/FlowKt__ZipKt$combine$6$2", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2", f = "Zip.kt", i = {0, 0}, l = {269}, m = "invokeSuspend", n = {"$receiver", AdvanceSetting.NETWORK_TYPE}, s = {"L$0", "L$1"})
/* loaded from: classes11.dex */
public final class FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2<R, T> extends SuspendLambda implements Function3<FlowCollector<? super R>, T[], Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    private FlowCollector p$;
    private Object[] p$0;
    final /* synthetic */ FlowKt__ZipKt$combine$$inlined$unsafeFlow$6 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2(Continuation continuation, FlowKt__ZipKt$combine$$inlined$unsafeFlow$6 flowKt__ZipKt$combine$$inlined$unsafeFlow$6) {
        super(3, continuation);
        this.this$0 = flowKt__ZipKt$combine$$inlined$unsafeFlow$6;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull FlowCollector<? super R> flowCollector, @NotNull T[] tArr, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2 flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2(continuation, this.this$0);
        flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2.p$ = flowCollector;
        flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2.p$0 = tArr;
        return flowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Continuation<? super Unit> continuation) {
        return ((FlowKt__ZipKt$combine$$inlined$unsafeFlow$6$lambda$2) create((FlowCollector) obj, (Object[]) obj2, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.p$;
            Object[] objArr = this.p$0;
            Object invoke = this.this$0.$transform$inlined.invoke(objArr, this);
            this.L$0 = flowCollector;
            this.L$1 = objArr;
            this.label = 1;
            if (flowCollector.emit(invoke, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            Object[] objArr2 = (Object[]) this.L$1;
            FlowCollector flowCollector2 = (FlowCollector) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Nullable
    public final Object invokeSuspend$$forInline(@NotNull Object obj) {
        FlowCollector flowCollector = this.p$;
        Object invoke = this.this$0.$transform$inlined.invoke(this.p$0, this);
        InlineMarker.mark(0);
        flowCollector.emit(invoke, this);
        InlineMarker.mark(2);
        InlineMarker.mark(1);
        return Unit.INSTANCE;
    }
}
