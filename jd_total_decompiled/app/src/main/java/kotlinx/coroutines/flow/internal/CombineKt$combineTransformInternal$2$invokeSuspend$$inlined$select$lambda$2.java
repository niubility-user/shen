package kotlinx.coroutines.flow.internal;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.channels.ReceiveChannel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0006\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004\u00a8\u0006\u0005"}, d2 = {"", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx/coroutines/flow/internal/CombineKt$combineTransformInternal$2$$special$$inlined$onReceive$1", "<anonymous>"}, k = 3, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ ReceiveChannel $firstChannel$inlined;
    final /* synthetic */ Ref.BooleanRef $firstIsClosed$inlined;
    final /* synthetic */ Ref.ObjectRef $firstValue$inlined;
    final /* synthetic */ Function2 $onReceive;
    final /* synthetic */ ReceiveChannel $secondChannel$inlined;
    final /* synthetic */ Ref.BooleanRef $secondIsClosed$inlined;
    final /* synthetic */ Ref.ObjectRef $secondValue$inlined;
    Object L$0;
    int label;
    private Object p$0;
    final /* synthetic */ CombineKt$combineTransformInternal$2 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2(Function2 function2, Continuation continuation, CombineKt$combineTransformInternal$2 combineKt$combineTransformInternal$2, Ref.BooleanRef booleanRef, ReceiveChannel receiveChannel, Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, Ref.BooleanRef booleanRef2, ReceiveChannel receiveChannel2) {
        super(2, continuation);
        this.$onReceive = function2;
        this.this$0 = combineKt$combineTransformInternal$2;
        this.$firstIsClosed$inlined = booleanRef;
        this.$firstChannel$inlined = receiveChannel;
        this.$firstValue$inlined = objectRef;
        this.$secondValue$inlined = objectRef2;
        this.$secondIsClosed$inlined = booleanRef2;
        this.$secondChannel$inlined = receiveChannel2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2 combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2 = new CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2(this.$onReceive, continuation, this.this$0, this.$firstIsClosed$inlined, this.$firstChannel$inlined, this.$firstValue$inlined, this.$secondValue$inlined, this.$secondIsClosed$inlined, this.$secondChannel$inlined);
        combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2.p$0 = obj;
        return combineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((CombineKt$combineTransformInternal$2$invokeSuspend$$inlined$select$lambda$2) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        Object coroutine_suspended;
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.p$0;
            if (obj2 == null) {
                this.$firstIsClosed$inlined.element = true;
            } else {
                Function2 function2 = this.$onReceive;
                this.L$0 = obj2;
                this.label = 1;
                if (function2.invoke(obj2, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i2 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
