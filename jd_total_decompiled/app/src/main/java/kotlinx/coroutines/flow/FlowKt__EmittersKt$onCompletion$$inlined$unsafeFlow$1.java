package kotlinx.coroutines.flow;

import com.jingdong.app.mall.e;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.internal.SafeCollector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J!\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007\u00b8\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> implements Flow<T> {
    final /* synthetic */ Function3 $action$inlined;
    final /* synthetic */ Flow $this_onCompletion$inlined;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0096@\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"T", "LLkotlinx/coroutines/flow/FlowCollector;;", "collector", "Lkotlin/coroutines/Continuation;", "L;", "continuation", "", "collect", "(LLkotlinx/coroutines/flow/FlowCollector;;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "otlinx/coroutines/flow/internal/SafeCollector_commonKt.unsafeFlow.1.collect."}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1", f = "Emitters.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 2, 2}, l = {114, 121, 125}, m = "collect", n = {"this", "collector", "continuation", "$receiver", "this", "collector", "continuation", "$receiver", e.a, "this", "collector", "continuation", "$receiver"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.this.collect(null, this);
        }
    }

    public FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(Flow flow, Function3 function3) {
        this.$this_onCompletion$inlined = flow;
        this.$action$inlined = function3;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00af A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector flowCollector, @NotNull Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1<T> flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        FlowCollector flowCollector2;
        Continuation continuation2;
        Throwable th;
        FlowCollector flowCollector3;
        ThrowingCollector throwingCollector;
        Function3 function3;
        SafeCollector safeCollector;
        Function3 function32;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i3 = anonymousClass1.label;
            if ((i3 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i3 - Integer.MIN_VALUE;
                Object obj = anonymousClass1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.label;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj);
                    try {
                        Flow flow = this.$this_onCompletion$inlined;
                        anonymousClass1.L$0 = this;
                        anonymousClass1.L$1 = flowCollector;
                        anonymousClass1.L$2 = anonymousClass1;
                        anonymousClass1.L$3 = flowCollector;
                        anonymousClass1.label = 1;
                        if (flow.collect(flowCollector, anonymousClass1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                        flowCollector2 = flowCollector;
                        continuation2 = anonymousClass1;
                    } catch (Throwable th2) {
                        flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = this;
                        flowCollector2 = flowCollector;
                        continuation2 = anonymousClass1;
                        th = th2;
                        flowCollector3 = flowCollector2;
                        throwingCollector = new ThrowingCollector(th);
                        function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                        anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                        anonymousClass1.L$1 = flowCollector2;
                        anonymousClass1.L$2 = continuation2;
                        anonymousClass1.L$3 = flowCollector3;
                        anonymousClass1.L$4 = th;
                        anonymousClass1.label = 2;
                        if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) != coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        throw th;
                    }
                } else if (i2 != 1) {
                    if (i2 == 2) {
                        Throwable th3 = (Throwable) anonymousClass1.L$4;
                        FlowCollector flowCollector4 = (FlowCollector) anonymousClass1.L$3;
                        Continuation continuation3 = (Continuation) anonymousClass1.L$2;
                        FlowCollector flowCollector5 = (FlowCollector) anonymousClass1.L$1;
                        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$12 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) anonymousClass1.L$0;
                        ResultKt.throwOnFailure(obj);
                        throw th3;
                    } else if (i2 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    } else {
                        FlowCollector flowCollector6 = (FlowCollector) anonymousClass1.L$3;
                        Continuation continuation4 = (Continuation) anonymousClass1.L$2;
                        FlowCollector flowCollector7 = (FlowCollector) anonymousClass1.L$1;
                        FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$13 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) anonymousClass1.L$0;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                } else {
                    flowCollector = (FlowCollector) anonymousClass1.L$3;
                    continuation2 = (Continuation) anonymousClass1.L$2;
                    flowCollector2 = (FlowCollector) anonymousClass1.L$1;
                    flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = (FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1) anonymousClass1.L$0;
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Throwable th4) {
                        flowCollector3 = flowCollector;
                        th = th4;
                        throwingCollector = new ThrowingCollector(th);
                        function3 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                        anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                        anonymousClass1.L$1 = flowCollector2;
                        anonymousClass1.L$2 = continuation2;
                        anonymousClass1.L$3 = flowCollector3;
                        anonymousClass1.L$4 = th;
                        anonymousClass1.label = 2;
                        if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(throwingCollector, function3, th, anonymousClass1) != coroutine_suspended) {
                        }
                    }
                }
                safeCollector = new SafeCollector(flowCollector, anonymousClass1.get$context());
                function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
                anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
                anonymousClass1.L$1 = flowCollector2;
                anonymousClass1.L$2 = continuation2;
                anonymousClass1.L$3 = flowCollector;
                anonymousClass1.label = 3;
                if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(safeCollector, function32, null, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj2 = anonymousClass1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.label;
        if (i2 != 0) {
        }
        safeCollector = new SafeCollector(flowCollector, anonymousClass1.get$context());
        function32 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1.$action$inlined;
        anonymousClass1.L$0 = flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
        anonymousClass1.L$1 = flowCollector2;
        anonymousClass1.L$2 = continuation2;
        anonymousClass1.L$3 = flowCollector;
        anonymousClass1.label = 3;
        if (FlowKt__EmittersKt.invokeSafely$FlowKt__EmittersKt(safeCollector, function32, null, anonymousClass1) == coroutine_suspended) {
        }
        return Unit.INSTANCE;
    }
}
