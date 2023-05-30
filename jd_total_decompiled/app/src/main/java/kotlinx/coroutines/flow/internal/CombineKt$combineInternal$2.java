package kotlinx.coroutines.flow.internal;

import com.jd.cashier.app.jdlibcutter.protocol.share.ShareKey;
import com.jingdong.common.apkcenter.ApkDownloadTable;
import com.jingdong.sdk.platform.business.personal.R2;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0006\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000\"\u0004\b\u0001\u0010\u0001*\u00020\u0002H\u008a@\u00a2\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"R", "T", "Lkotlinx/coroutines/CoroutineScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$combineInternal$2", f = "Combine.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {R2.anim.miaosha_dropdown_in}, m = "invokeSuspend", n = {"$this$coroutineScope", ApkDownloadTable.FIELD_SIZE, ShareKey.shareChannel, "latestValues", "isClosed", "nonClosed", "remainingNulls"}, s = {"L$0", "I$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes11.dex */
public final class CombineKt$combineInternal$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $arrayFactory;
    final /* synthetic */ Flow[] $flows;
    final /* synthetic */ FlowCollector $this_combineInternal;
    final /* synthetic */ Function3 $transform;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    private CoroutineScope p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineInternal$2(FlowCollector flowCollector, Flow[] flowArr, Function0 function0, Function3 function3, Continuation continuation) {
        super(2, continuation);
        this.$this_combineInternal = flowCollector;
        this.$flows = flowArr;
        this.$arrayFactory = function0;
        this.$transform = function3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$combineInternal$2 combineKt$combineInternal$2 = new CombineKt$combineInternal$2(this.$this_combineInternal, this.$flows, this.$arrayFactory, this.$transform, continuation);
        combineKt$combineInternal$2.p$ = (CoroutineScope) obj;
        return combineKt$combineInternal$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineInternal$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0153 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0168  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x0154 -> B:43:0x0164). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        ReceiveChannel[] receiveChannelArr;
        Object[] objArr;
        Boolean[] boolArr;
        Ref.IntRef intRef;
        Ref.IntRef intRef2;
        CombineKt$combineInternal$2 combineKt$combineInternal$2;
        CoroutineScope coroutineScope;
        int i2;
        Object obj2;
        ReceiveChannel asFairChannel;
        SelectInstance selectInstance;
        CombineKt$combineInternal$2 combineKt$combineInternal$22;
        CoroutineScope coroutineScope2;
        int i3;
        ReceiveChannel[] receiveChannelArr2;
        Object[] objArr2;
        Boolean[] boolArr2;
        Ref.IntRef intRef3;
        Ref.IntRef intRef4;
        Object result;
        Object coroutine_suspended2;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i4 = this.label;
        int i5 = 1;
        if (i4 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.p$;
            int length = this.$flows.length;
            ReceiveChannel[] receiveChannelArr3 = new ReceiveChannel[length];
            for (int i6 = 0; i6 < length; i6++) {
                asFairChannel = CombineKt.asFairChannel(coroutineScope3, this.$flows[Boxing.boxInt(i6).intValue()]);
                receiveChannelArr3[i6] = asFairChannel;
            }
            Object[] objArr3 = new Object[length];
            Boolean[] boolArr3 = new Boolean[length];
            for (int i7 = 0; i7 < length; i7++) {
                Boxing.boxInt(i7).intValue();
                boolArr3[i7] = Boxing.boxBoolean(false);
            }
            Ref.IntRef intRef5 = new Ref.IntRef();
            intRef5.element = length;
            Ref.IntRef intRef6 = new Ref.IntRef();
            intRef6.element = length;
            receiveChannelArr = receiveChannelArr3;
            objArr = objArr3;
            boolArr = boolArr3;
            intRef = intRef5;
            intRef2 = intRef6;
            combineKt$combineInternal$2 = this;
            coroutineScope = coroutineScope3;
            i2 = length;
            obj2 = coroutine_suspended;
            if (intRef.element != 0) {
            }
        } else if (i4 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            CombineKt$combineInternal$2 combineKt$combineInternal$23 = (CombineKt$combineInternal$2) this.L$6;
            int i8 = this.I$0;
            CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            intRef2 = (Ref.IntRef) this.L$5;
            intRef = (Ref.IntRef) this.L$4;
            boolArr = (Boolean[]) this.L$3;
            objArr = (Object[]) this.L$2;
            receiveChannelArr = (ReceiveChannel[]) this.L$1;
            obj2 = coroutine_suspended;
            combineKt$combineInternal$2 = this;
            i2 = i8;
            coroutineScope = coroutineScope4;
            i5 = 1;
            if (intRef.element != 0) {
                combineKt$combineInternal$2.L$0 = coroutineScope;
                combineKt$combineInternal$2.I$0 = i2;
                combineKt$combineInternal$2.L$1 = receiveChannelArr;
                combineKt$combineInternal$2.L$2 = objArr;
                combineKt$combineInternal$2.L$3 = boolArr;
                combineKt$combineInternal$2.L$4 = intRef;
                combineKt$combineInternal$2.L$5 = intRef2;
                combineKt$combineInternal$2.L$6 = combineKt$combineInternal$2;
                combineKt$combineInternal$2.label = i5;
                SelectInstance selectInstance2 = new SelectInstance(combineKt$combineInternal$2);
                int i9 = 0;
                while (i9 < i2) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                        selectInstance = selectInstance2;
                        combineKt$combineInternal$22 = combineKt$combineInternal$2;
                        coroutineScope2 = coroutineScope;
                        i3 = i2;
                        receiveChannelArr2 = receiveChannelArr;
                        objArr2 = objArr;
                        boolArr2 = boolArr;
                        intRef3 = intRef;
                    }
                    boolean booleanValue = boolArr[i9].booleanValue();
                    ReceiveChannel receiveChannel = receiveChannelArr[i9];
                    selectInstance = selectInstance2;
                    combineKt$combineInternal$22 = combineKt$combineInternal$2;
                    coroutineScope2 = coroutineScope;
                    i3 = i2;
                    receiveChannelArr2 = receiveChannelArr;
                    objArr2 = objArr;
                    boolArr2 = boolArr;
                    intRef3 = intRef;
                    try {
                    } catch (Throwable th2) {
                        th = th2;
                        intRef4 = intRef2;
                        selectInstance.handleBuilderException(th);
                        result = selectInstance.getResult();
                        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (result == coroutine_suspended2) {
                        }
                        if (result == obj2) {
                        }
                    }
                    CombineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$1 combineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$1 = new CombineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$1(i9, null, combineKt$combineInternal$2, i2, boolArr, receiveChannelArr, objArr, intRef2, intRef);
                    if (booleanValue) {
                        intRef4 = intRef2;
                    } else {
                        SelectClause1 onReceiveOrNull = receiveChannel.getOnReceiveOrNull();
                        intRef4 = intRef2;
                        try {
                        } catch (Throwable th3) {
                            th = th3;
                            selectInstance.handleBuilderException(th);
                            result = selectInstance.getResult();
                            coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            if (result == coroutine_suspended2) {
                            }
                            if (result == obj2) {
                            }
                        }
                        selectInstance.invoke(onReceiveOrNull, new CombineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$2(combineKt$combineInternal$2$invokeSuspend$$inlined$select$lambda$1, null, i9, combineKt$combineInternal$22, i3, boolArr2, receiveChannelArr2, objArr2, intRef2, intRef3));
                    }
                    i9++;
                    selectInstance2 = selectInstance;
                    intRef2 = intRef4;
                    combineKt$combineInternal$2 = combineKt$combineInternal$22;
                    coroutineScope = coroutineScope2;
                    i2 = i3;
                    receiveChannelArr = receiveChannelArr2;
                    objArr = objArr2;
                    boolArr = boolArr2;
                    intRef = intRef3;
                }
                selectInstance = selectInstance2;
                combineKt$combineInternal$22 = combineKt$combineInternal$2;
                coroutineScope2 = coroutineScope;
                i3 = i2;
                receiveChannelArr2 = receiveChannelArr;
                objArr2 = objArr;
                boolArr2 = boolArr;
                intRef3 = intRef;
                intRef4 = intRef2;
                result = selectInstance.getResult();
                coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (result == coroutine_suspended2) {
                    DebugProbes.probeCoroutineSuspended(combineKt$combineInternal$22);
                }
                if (result == obj2) {
                    return obj2;
                }
                intRef2 = intRef4;
                combineKt$combineInternal$2 = combineKt$combineInternal$22;
                coroutineScope = coroutineScope2;
                i2 = i3;
                receiveChannelArr = receiveChannelArr2;
                objArr = objArr2;
                boolArr = boolArr2;
                intRef = intRef3;
                i5 = 1;
                if (intRef.element != 0) {
                    return Unit.INSTANCE;
                }
            }
        }
    }
}
