package kotlinx.coroutines.flow.internal;

import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000H\u008a@\u00a2\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", AdvanceSetting.NETWORK_TYPE, "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "<anonymous>"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "kotlinx.coroutines.flow.internal.CombineKt$onReceive$1", f = "Combine.kt", i = {0}, l = {89}, m = "invokeSuspend", n = {AdvanceSetting.NETWORK_TYPE}, s = {"L$0"})
/* loaded from: classes11.dex */
public final class CombineKt$onReceive$1 extends SuspendLambda implements Function2<Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $onClosed;
    final /* synthetic */ Function2 $onReceive;
    Object L$0;
    int label;
    private Object p$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CombineKt$onReceive$1(Function0 function0, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$onClosed = function0;
        this.$onReceive = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        CombineKt$onReceive$1 combineKt$onReceive$1 = new CombineKt$onReceive$1(this.$onClosed, this.$onReceive, continuation);
        combineKt$onReceive$1.p$0 = obj;
        return combineKt$onReceive$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((CombineKt$onReceive$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
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
                this.$onClosed.invoke();
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

    @Nullable
    public final Object invokeSuspend$$forInline(@NotNull Object obj) {
        Object obj2 = this.p$0;
        if (obj2 == null) {
            this.$onClosed.invoke();
        } else {
            Function2 function2 = this.$onReceive;
            InlineMarker.mark(0);
            function2.invoke(obj2, this);
            InlineMarker.mark(2);
            InlineMarker.mark(1);
        }
        return Unit.INSTANCE;
    }
}
