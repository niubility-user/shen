package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J!\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0007\u00b8\u0006\u0000"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", "", "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 implements Flow<Long> {
    final /* synthetic */ long[] $this_asFlow$inlined;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0007\u001a\u0004\u0018\u00010\u0006\"\u0004\b\u0000\u0010\u00002\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0096@\u00a2\u0006\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"T", "LLkotlinx/coroutines/flow/FlowCollector;;", "collector", "Lkotlin/coroutines/Continuation;", "L;", "continuation", "", "collect", "(LLkotlinx/coroutines/flow/FlowCollector;;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "otlinx/coroutines/flow/internal/SafeCollector_commonKt.unsafeFlow.1.collect."}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8", f = "Builders.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {115}, m = "collect", n = {"this", "collector", "continuation", "$receiver", "$this$forEach$iv", "element$iv", "value"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "J$1"})
    /* renamed from: kotlinx.coroutines.flow.FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        long J$0;
        long J$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
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
            return FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8.this.collect(null, this);
        }
    }

    public FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8(long[] jArr) {
        this.$this_asFlow$inlined = jArr;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0027  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00ab  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x00a0 -> B:20:0x00a5). Please submit an issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object collect(@NotNull FlowCollector<? super Long> flowCollector, @NotNull Continuation continuation) {
        AnonymousClass1 anonymousClass1;
        Object coroutine_suspended;
        int i2;
        FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8;
        long[] jArr;
        long[] jArr2;
        Object obj;
        int length;
        int i3;
        AnonymousClass1 anonymousClass12;
        Object obj2;
        FlowCollector<? super Long> flowCollector2;
        FlowCollector<? super Long> flowCollector3;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i4 = anonymousClass1.label;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i4 - Integer.MIN_VALUE;
                Object obj3 = anonymousClass1.result;
                coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                i2 = anonymousClass1.label;
                int i5 = 1;
                if (i2 != 0) {
                    ResultKt.throwOnFailure(obj3);
                    long[] jArr3 = this.$this_asFlow$inlined;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 = this;
                    jArr = jArr3;
                    jArr2 = jArr;
                    obj = coroutine_suspended;
                    length = jArr3.length;
                    i3 = 0;
                    FlowCollector<? super Long> flowCollector4 = flowCollector;
                    anonymousClass12 = anonymousClass1;
                    obj2 = anonymousClass12;
                    flowCollector2 = flowCollector4;
                    flowCollector3 = flowCollector4;
                    if (i3 < length) {
                    }
                } else if (i2 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                } else {
                    long j2 = anonymousClass1.J$1;
                    long j3 = anonymousClass1.J$0;
                    int i6 = anonymousClass1.I$1;
                    int i7 = anonymousClass1.I$0;
                    Object obj4 = (Continuation) anonymousClass1.L$2;
                    flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8 = (FlowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8) anonymousClass1.L$0;
                    ResultKt.throwOnFailure(obj3);
                    FlowCollector<? super Long> flowCollector5 = (FlowCollector) anonymousClass1.L$3;
                    jArr = (long[]) anonymousClass1.L$5;
                    long[] jArr4 = (long[]) anonymousClass1.L$4;
                    length = i7;
                    obj = coroutine_suspended;
                    anonymousClass12 = anonymousClass1;
                    flowCollector2 = (FlowCollector) anonymousClass1.L$1;
                    long[] jArr5 = jArr4;
                    i3 = i6 + i5;
                    obj2 = obj4;
                    jArr2 = jArr5;
                    flowCollector3 = flowCollector5;
                    if (i3 < length) {
                        long j4 = jArr[i3];
                        Object obj5 = obj;
                        long longValue = Boxing.boxLong(j4).longValue();
                        Long boxLong = Boxing.boxLong(longValue);
                        anonymousClass12.L$0 = flowKt__BuildersKt$asFlow$$inlined$unsafeFlow$8;
                        anonymousClass12.L$1 = flowCollector2;
                        anonymousClass12.L$2 = obj2;
                        anonymousClass12.L$3 = flowCollector3;
                        anonymousClass12.L$4 = jArr2;
                        anonymousClass12.L$5 = jArr;
                        anonymousClass12.I$0 = length;
                        anonymousClass12.I$1 = i3;
                        anonymousClass12.J$0 = j4;
                        anonymousClass12.J$1 = longValue;
                        i5 = 1;
                        anonymousClass12.label = 1;
                        if (flowCollector3.emit(boxLong, anonymousClass12) == obj5) {
                            return obj5;
                        }
                        obj = obj5;
                        long[] jArr6 = jArr2;
                        obj4 = obj2;
                        i6 = i3;
                        jArr4 = jArr6;
                        flowCollector5 = flowCollector3;
                        long[] jArr52 = jArr4;
                        i3 = i6 + i5;
                        obj2 = obj4;
                        jArr2 = jArr52;
                        flowCollector3 = flowCollector5;
                        if (i3 < length) {
                            return Unit.INSTANCE;
                        }
                    }
                }
            }
        }
        anonymousClass1 = new AnonymousClass1(continuation);
        Object obj32 = anonymousClass1.result;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        i2 = anonymousClass1.label;
        int i52 = 1;
        if (i2 != 0) {
        }
    }
}
