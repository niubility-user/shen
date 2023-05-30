package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0001\u001a\u00020\u0000H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0001\u0010\u0002\u001a\u0013\u0010\u0004\u001a\u00020\u0000*\u00020\u0003H\u0000\u00a2\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0006"}, d2 = {"", "yield", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/CoroutineContext;", "checkCompletion", "(Lkotlin/coroutines/CoroutineContext;)V", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class YieldKt {
    public static final void checkCompletion(@NotNull CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.INSTANCE);
        if (job != null && !job.isActive()) {
            throw job.getCancellationException();
        }
    }

    @Nullable
    public static final Object yield(@NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object obj;
        Object coroutine_suspended;
        Object coroutine_suspended2;
        CoroutineContext coroutineContext = continuation.get$context();
        checkCompletion(coroutineContext);
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        if (!(intercepted instanceof DispatchedContinuation[)) {
            intercepted = null;
        }
        DispatchedContinuation[ r1 = (DispatchedContinuation[) intercepted;
        if (r1 != null) {
            if (r1.DispatchedContinuation[.isDispatchNeeded(coroutineContext)) {
                r1.dispatchYield$kotlinx_coroutines_core(coroutineContext, Unit.INSTANCE);
            } else {
                YieldContext yieldContext = new YieldContext();
                CoroutineContext plus = coroutineContext.plus(yieldContext);
                obj = Unit.INSTANCE;
                r1.dispatchYield$kotlinx_coroutines_core(plus, obj);
                if (yieldContext.dispatcherWasUnconfined) {
                    if (DispatchedContinuationKt.yieldUndispatched(r1)) {
                        obj = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    }
                }
            }
            obj = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        } else {
            obj = Unit.INSTANCE;
        }
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (obj == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        coroutine_suspended2 = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return obj == coroutine_suspended2 ? obj : Unit.INSTANCE;
    }
}
