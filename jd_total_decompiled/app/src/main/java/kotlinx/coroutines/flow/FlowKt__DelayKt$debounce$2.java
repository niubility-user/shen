package kotlinx.coroutines.flow;

import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u008a@\u00a2\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlinx/coroutines/flow/FlowCollector;", "downstream", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__DelayKt$debounce$2", f = "Delay.kt", i = {0, 0, 0, 0}, l = {R2.anim.slide_in_from_bottom_medium_time}, m = "invokeSuspend", n = {"$this$scopedFlow", "downstream", "values", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* loaded from: classes11.dex */
public final class FlowKt__DelayKt$debounce$2<T> extends SuspendLambda implements Function3<CoroutineScope, FlowCollector<? super T>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $this_debounce;
    final /* synthetic */ long $timeoutMillis;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    private CoroutineScope p$;
    private FlowCollector p$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$debounce$2(Flow flow, long j2, Continuation continuation) {
        super(3, continuation);
        this.$this_debounce = flow;
        this.$timeoutMillis = j2;
    }

    @NotNull
    public final Continuation<Unit> create(@NotNull CoroutineScope coroutineScope, @NotNull FlowCollector<? super T> flowCollector, @NotNull Continuation<? super Unit> continuation) {
        FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$2 = new FlowKt__DelayKt$debounce$2(this.$this_debounce, this.$timeoutMillis, continuation);
        flowKt__DelayKt$debounce$2.p$ = coroutineScope;
        flowKt__DelayKt$debounce$2.p$0 = flowCollector;
        return flowKt__DelayKt$debounce$2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(CoroutineScope coroutineScope, Object obj, Continuation<? super Unit> continuation) {
        return ((FlowKt__DelayKt$debounce$2) create(coroutineScope, (FlowCollector) obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(4:12|13|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x009e, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x00a8, code lost:
        r3.handleBuilderException(r0);
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ba A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00bf  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00bb -> B:30:0x00bd). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        FlowKt__DelayKt$debounce$2<T> flowKt__DelayKt$debounce$2;
        CoroutineScope coroutineScope;
        ReceiveChannel receiveChannel;
        Ref.ObjectRef objectRef;
        FlowCollector flowCollector;
        Object obj2;
        SelectInstance selectInstance;
        FlowKt__DelayKt$debounce$2<T> flowKt__DelayKt$debounce$22;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.p$;
            FlowCollector flowCollector2 = this.p$0;
            ReceiveChannel produce$default = ProduceKt.produce$default(coroutineScope2, null, -1, new FlowKt__DelayKt$debounce$2$values$1(this, null), 1, null);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = null;
            flowKt__DelayKt$debounce$2 = this;
            coroutineScope = coroutineScope2;
            receiveChannel = produce$default;
            objectRef = objectRef2;
            flowCollector = flowCollector2;
            obj2 = coroutine_suspended;
            if (objectRef.element != NullSurrogateKt.DONE) {
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            FlowKt__DelayKt$debounce$2 flowKt__DelayKt$debounce$23 = (FlowKt__DelayKt$debounce$2) this.L$4;
            ResultKt.throwOnFailure(obj);
            flowKt__DelayKt$debounce$2 = this;
            objectRef = (Ref.ObjectRef) this.L$3;
            receiveChannel = (ReceiveChannel) this.L$2;
            flowCollector = (FlowCollector) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            obj2 = coroutine_suspended;
            i3 = 1;
            if (objectRef.element != NullSurrogateKt.DONE) {
                flowKt__DelayKt$debounce$2.L$0 = coroutineScope;
                flowKt__DelayKt$debounce$2.L$1 = flowCollector;
                flowKt__DelayKt$debounce$2.L$2 = receiveChannel;
                flowKt__DelayKt$debounce$2.L$3 = objectRef;
                flowKt__DelayKt$debounce$2.L$4 = flowKt__DelayKt$debounce$2;
                flowKt__DelayKt$debounce$2.label = i3;
                SelectInstance selectInstance2 = new SelectInstance(flowKt__DelayKt$debounce$2);
                try {
                } catch (Throwable th) {
                    th = th;
                    selectInstance = selectInstance2;
                    flowKt__DelayKt$debounce$22 = flowKt__DelayKt$debounce$2;
                }
                selectInstance2.invoke(receiveChannel.getOnReceiveOrNull(), new FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$1(null, flowKt__DelayKt$debounce$2, receiveChannel, objectRef, flowCollector));
                T t = objectRef.element;
                if (t != null) {
                    selectInstance = selectInstance2;
                    flowKt__DelayKt$debounce$22 = flowKt__DelayKt$debounce$2;
                    selectInstance.onTimeout(flowKt__DelayKt$debounce$2.$timeoutMillis, new FlowKt__DelayKt$debounce$2$invokeSuspend$$inlined$select$lambda$2(t, null, selectInstance2, flowKt__DelayKt$debounce$2, receiveChannel, objectRef, flowCollector));
                } else {
                    selectInstance = selectInstance2;
                    flowKt__DelayKt$debounce$22 = flowKt__DelayKt$debounce$2;
                }
                Object result = selectInstance.getResult();
                coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result == coroutine_suspended2) {
                    DebugProbes.probeCoroutineSuspended(flowKt__DelayKt$debounce$22);
                }
                if (result != obj2) {
                    return obj2;
                }
                flowKt__DelayKt$debounce$2 = flowKt__DelayKt$debounce$22;
                i3 = 1;
                if (objectRef.element != NullSurrogateKt.DONE) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
