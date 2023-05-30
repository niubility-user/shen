package kotlinx.coroutines.flow.internal;

import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0007\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010\u0002*\u00020\u0003H\u008a@\u00a2\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"T1", "T2", "R", "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineTransformInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {R2.anim.miaosha_dropdown_in}, m = "invokeSuspend", n = {"$this$coroutineScope", "firstChannel", "secondChannel", "firstValue", "secondValue", "firstIsClosed", "secondIsClosed"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6"})
/* loaded from: classes11.dex */
public final class CombineKt$combineTransformInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Flow $first;
    final /* synthetic */ Flow $second;
    final /* synthetic */ FlowCollector $this_combineTransformInternal;
    final /* synthetic */ Function4 $transform;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineTransformInternal$2(FlowCollector flowCollector, Flow flow, Flow flow2, Function4 function4, Continuation continuation) {
        super(2, continuation);
        this.$this_combineTransformInternal = flowCollector;
        this.$first = flow;
        this.$second = flow2;
        this.$transform = function4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$combineTransformInternal$2 combineKt$combineTransformInternal$2 = new CombineKt$combineTransformInternal$2(this.$this_combineTransformInternal, this.$first, this.$second, this.$transform, continuation);
        combineKt$combineTransformInternal$2.p$ = (CoroutineScope) obj;
        return combineKt$combineTransformInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineTransformInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:13|14|15|16|17|18|(1:20)(3:35|36|37)|21|(1:23)(2:30|31)|24|(1:26)|(1:28)(16:29|5|6|(1:8)|13|14|15|16|17|18|(0)(0)|21|(0)(0)|24|(0)|(0)(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0118, code lost:
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0126, code lost:
        r16 = r13;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00bf A[Catch: all -> 0x0118, TRY_LEAVE, TryCatch #1 {all -> 0x0118, blocks: (B:19:0x00b7, B:22:0x00bf), top: B:47:0x00b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x013a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x013b -> B:44:0x0147). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ReceiveChannel asFairChannel;
        ReceiveChannel asFairChannel2;
        CoroutineScope coroutineScope;
        ReceiveChannel receiveChannel;
        Ref.ObjectRef objectRef;
        Ref.BooleanRef booleanRef;
        Ref.ObjectRef objectRef2;
        Ref.BooleanRef booleanRef2;
        Object obj2;
        CombineKt$combineTransformInternal$2 combineKt$combineTransformInternal$2;
        ReceiveChannel receiveChannel2;
        SelectInstance selectInstance;
        CombineKt$combineTransformInternal$2 combineKt$combineTransformInternal$22;
        CoroutineScope coroutineScope2;
        ReceiveChannel receiveChannel3;
        ReceiveChannel receiveChannel4;
        Ref.ObjectRef objectRef3;
        Ref.ObjectRef objectRef4;
        Object result;
        Object coroutine_suspended2;
        boolean z;
        boolean z2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        int i3 = 1;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.p$;
            asFairChannel = CombineKt.asFairChannel(coroutineScope3, this.$first);
            asFairChannel2 = CombineKt.asFairChannel(coroutineScope3, this.$second);
            Ref.ObjectRef objectRef5 = new Ref.ObjectRef();
            objectRef5.element = null;
            Ref.ObjectRef objectRef6 = new Ref.ObjectRef();
            objectRef6.element = null;
            Ref.BooleanRef booleanRef3 = new Ref.BooleanRef();
            booleanRef3.element = false;
            Ref.BooleanRef booleanRef4 = new Ref.BooleanRef();
            booleanRef4.element = false;
            coroutineScope = coroutineScope3;
            receiveChannel = asFairChannel2;
            objectRef = objectRef5;
            booleanRef = booleanRef3;
            objectRef2 = objectRef6;
            booleanRef2 = booleanRef4;
            obj2 = coroutine_suspended;
            combineKt$combineTransformInternal$2 = this;
            receiveChannel2 = asFairChannel;
            if (!booleanRef.element) {
            }
            combineKt$combineTransformInternal$2.L$0 = coroutineScope;
            combineKt$combineTransformInternal$2.L$1 = receiveChannel2;
            combineKt$combineTransformInternal$2.L$2 = receiveChannel;
            combineKt$combineTransformInternal$2.L$3 = objectRef;
            combineKt$combineTransformInternal$2.L$4 = objectRef2;
            combineKt$combineTransformInternal$2.L$5 = booleanRef;
            combineKt$combineTransformInternal$2.L$6 = booleanRef2;
            combineKt$combineTransformInternal$2.L$7 = combineKt$combineTransformInternal$2;
            combineKt$combineTransformInternal$2.label = i3;
            SelectInstance selectInstance2 = new SelectInstance(combineKt$combineTransformInternal$2);
            z = booleanRef.element;
            selectInstance = selectInstance2;
            combineKt$combineTransformInternal$22 = combineKt$combineTransformInternal$2;
            coroutineScope2 = coroutineScope;
            receiveChannel3 = receiveChannel2;
            receiveChannel4 = receiveChannel;
            objectRef3 = objectRef;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1(null, combineKt$combineTransformInternal$2, booleanRef, receiveChannel2, objectRef, objectRef2, booleanRef2, receiveChannel4);
            if (z) {
            }
            z2 = booleanRef2.element;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3(null, combineKt$combineTransformInternal$22, booleanRef, receiveChannel3, objectRef3, objectRef4, booleanRef2, receiveChannel4);
            if (!z2) {
            }
            result = selectInstance.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended2) {
            }
            if (result == obj2) {
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CombineKt$combineTransformInternal$2 combineKt$combineTransformInternal$23 = (CombineKt$combineTransformInternal$2) this.L$7;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            booleanRef2 = (Ref.BooleanRef) this.L$6;
            booleanRef = (Ref.BooleanRef) this.L$5;
            objectRef2 = (Ref.ObjectRef) this.L$4;
            objectRef = (Ref.ObjectRef) this.L$3;
            receiveChannel = (ReceiveChannel) this.L$2;
            receiveChannel2 = (ReceiveChannel) this.L$1;
            obj2 = coroutine_suspended;
            combineKt$combineTransformInternal$2 = this;
            i3 = 1;
            if (!booleanRef.element && booleanRef2.element) {
                return Unit.INSTANCE;
            }
            combineKt$combineTransformInternal$2.L$0 = coroutineScope;
            combineKt$combineTransformInternal$2.L$1 = receiveChannel2;
            combineKt$combineTransformInternal$2.L$2 = receiveChannel;
            combineKt$combineTransformInternal$2.L$3 = objectRef;
            combineKt$combineTransformInternal$2.L$4 = objectRef2;
            combineKt$combineTransformInternal$2.L$5 = booleanRef;
            combineKt$combineTransformInternal$2.L$6 = booleanRef2;
            combineKt$combineTransformInternal$2.L$7 = combineKt$combineTransformInternal$2;
            combineKt$combineTransformInternal$2.label = i3;
            SelectInstance selectInstance22 = new SelectInstance(combineKt$combineTransformInternal$2);
            try {
            } catch (Throwable th) {
                th = th;
                selectInstance = selectInstance22;
                combineKt$combineTransformInternal$22 = combineKt$combineTransformInternal$2;
                coroutineScope2 = coroutineScope;
                receiveChannel3 = receiveChannel2;
                receiveChannel4 = receiveChannel;
                objectRef3 = objectRef;
            }
            z = booleanRef.element;
            selectInstance = selectInstance22;
            combineKt$combineTransformInternal$22 = combineKt$combineTransformInternal$2;
            coroutineScope2 = coroutineScope;
            receiveChannel3 = receiveChannel2;
            receiveChannel4 = receiveChannel;
            objectRef3 = objectRef;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$12 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1(null, combineKt$combineTransformInternal$2, booleanRef, receiveChannel2, objectRef, objectRef2, booleanRef2, receiveChannel4);
            if (z) {
                objectRef4 = objectRef2;
            } else {
                objectRef4 = objectRef2;
                selectInstance.invoke(receiveChannel3.getOnReceiveOrNull(), new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2(combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$12, null, combineKt$combineTransformInternal$22, booleanRef, receiveChannel3, objectRef3, objectRef2, booleanRef2, receiveChannel4));
            }
            z2 = booleanRef2.element;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$32 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3(null, combineKt$combineTransformInternal$22, booleanRef, receiveChannel3, objectRef3, objectRef4, booleanRef2, receiveChannel4);
            if (!z2) {
                try {
                } catch (Throwable th2) {
                    th = th2;
                    selectInstance.handleBuilderException(th);
                    result = selectInstance.getResult();
                    coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (result == coroutine_suspended2) {
                    }
                    if (result == obj2) {
                    }
                }
                selectInstance.invoke(receiveChannel4.getOnReceiveOrNull(), new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$4(combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$32, null, combineKt$combineTransformInternal$22, booleanRef, receiveChannel3, objectRef3, objectRef4, booleanRef2, receiveChannel4));
            }
            result = selectInstance.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended2) {
                DebugProbes.probeCoroutineSuspended(combineKt$combineTransformInternal$22);
            }
            if (result == obj2) {
                return obj2;
            }
            objectRef2 = objectRef4;
            combineKt$combineTransformInternal$2 = combineKt$combineTransformInternal$22;
            coroutineScope = coroutineScope2;
            receiveChannel2 = receiveChannel3;
            receiveChannel = receiveChannel4;
            objectRef = objectRef3;
            i3 = 1;
            if (!booleanRef.element) {
            }
            combineKt$combineTransformInternal$2.L$0 = coroutineScope;
            combineKt$combineTransformInternal$2.L$1 = receiveChannel2;
            combineKt$combineTransformInternal$2.L$2 = receiveChannel;
            combineKt$combineTransformInternal$2.L$3 = objectRef;
            combineKt$combineTransformInternal$2.L$4 = objectRef2;
            combineKt$combineTransformInternal$2.L$5 = booleanRef;
            combineKt$combineTransformInternal$2.L$6 = booleanRef2;
            combineKt$combineTransformInternal$2.L$7 = combineKt$combineTransformInternal$2;
            combineKt$combineTransformInternal$2.label = i3;
            SelectInstance selectInstance222 = new SelectInstance(combineKt$combineTransformInternal$2);
            z = booleanRef.element;
            selectInstance = selectInstance222;
            combineKt$combineTransformInternal$22 = combineKt$combineTransformInternal$2;
            coroutineScope2 = coroutineScope;
            receiveChannel3 = receiveChannel2;
            receiveChannel4 = receiveChannel;
            objectRef3 = objectRef;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$122 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$1(null, combineKt$combineTransformInternal$2, booleanRef, receiveChannel2, objectRef, objectRef2, booleanRef2, receiveChannel4);
            if (z) {
            }
            z2 = booleanRef2.element;
            CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$322 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$3(null, combineKt$combineTransformInternal$22, booleanRef, receiveChannel3, objectRef3, objectRef4, booleanRef2, receiveChannel4);
            if (!z2) {
            }
            result = selectInstance.getResult();
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended2) {
            }
            if (result == obj2) {
            }
        }
    }
}
