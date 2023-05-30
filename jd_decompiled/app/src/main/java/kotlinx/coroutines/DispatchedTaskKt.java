package kotlinx.coroutines;

import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import jpbury.t;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a'\u0010\u0005\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\u0005\u0010\u0006\u001a5\u0010\n\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u00072\u0006\u0010\t\u001a\u00020\u0002H\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u001a\u0017\u0010\f\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u0001H\u0002\u00a2\u0006\u0004\b\f\u0010\r\u001a.\u0010\u0012\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u000f\u001a\u00020\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0010H\u0080\b\u00a2\u0006\u0004\b\u0012\u0010\u0013\u001a \u0010\u0016\u001a\u00020\u0004*\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0015\u001a\u00020\u0014H\u0080\b\u00a2\u0006\u0004\b\u0016\u0010\u0017\"\u001c\u0010\u0018\u001a\u00020\u00028\u0000@\u0001X\u0081T\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u0012\u0004\b\u001a\u0010\u001b\"\u001c\u0010\u001c\u001a\u00020\u00028\u0000@\u0001X\u0081T\u00a2\u0006\f\n\u0004\b\u001c\u0010\u0019\u0012\u0004\b\u001d\u0010\u001b\"\u001c\u0010\u001e\u001a\u00020\u00028\u0000@\u0001X\u0081T\u00a2\u0006\f\n\u0004\b\u001e\u0010\u0019\u0012\u0004\b\u001f\u0010\u001b\"\u001a\u0010!\u001a\u00020 *\u00020\u00028@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001a\u0010#\u001a\u00020 *\u00020\u00028@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\b#\u0010\"\u00a8\u0006$"}, d2 = {"T", "Lkotlinx/coroutines/DispatchedTask;", "", "mode", "", "dispatch", "(Lkotlinx/coroutines/DispatchedTask;I)V", "Lkotlin/coroutines/Continuation;", "delegate", "useMode", JsApiLivePlayer.CM_RESUME, "(Lkotlinx/coroutines/DispatchedTask;Lkotlin/coroutines/Continuation;I)V", "resumeUnconfined", "(Lkotlinx/coroutines/DispatchedTask;)V", "Lkotlinx/coroutines/EventLoop;", "eventLoop", "Lkotlin/Function0;", JDReactConstant.BLOCK, "runUnconfinedEventLoop", "(Lkotlinx/coroutines/DispatchedTask;Lkotlinx/coroutines/EventLoop;Lkotlin/jvm/functions/Function0;)V", "", t.f20145j, "resumeWithStackTrace", "(Lkotlin/coroutines/Continuation;Ljava/lang/Throwable;)V", "MODE_CANCELLABLE", "I", "MODE_CANCELLABLE$annotations", "()V", "MODE_UNDISPATCHED", "MODE_UNDISPATCHED$annotations", "MODE_ATOMIC_DEFAULT", "MODE_ATOMIC_DEFAULT$annotations", "", "isDispatchedMode", "(I)Z", "isCancellableMode", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DispatchedTaskKt {
    public static final int MODE_ATOMIC_DEFAULT = 0;
    public static final int MODE_CANCELLABLE = 1;
    public static final int MODE_UNDISPATCHED = 2;

    @PublishedApi
    public static /* synthetic */ void MODE_ATOMIC_DEFAULT$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_CANCELLABLE$annotations() {
    }

    @PublishedApi
    public static /* synthetic */ void MODE_UNDISPATCHED$annotations() {
    }

    public static final <T> void dispatch(@NotNull DispatchedTask<? super T> dispatchedTask, int i2) {
        Continuation<? super T> delegate$kotlinx_coroutines_core = dispatchedTask.getDelegate$kotlinx_coroutines_core();
        if (isDispatchedMode(i2) && (delegate$kotlinx_coroutines_core instanceof DispatchedContinuation[) && isCancellableMode(i2) == isCancellableMode(dispatchedTask.resumeMode)) {
            CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation[) delegate$kotlinx_coroutines_core).DispatchedContinuation[;
            CoroutineContext coroutineContext = delegate$kotlinx_coroutines_core.get$context();
            if (coroutineDispatcher.isDispatchNeeded(coroutineContext)) {
                coroutineDispatcher.mo1254dispatch(coroutineContext, dispatchedTask);
                return;
            } else {
                resumeUnconfined(dispatchedTask);
                return;
            }
        }
        resume(dispatchedTask, delegate$kotlinx_coroutines_core, i2);
    }

    public static final boolean isCancellableMode(int i2) {
        return i2 == 1;
    }

    public static final boolean isDispatchedMode(int i2) {
        return i2 == 0 || i2 == 1;
    }

    public static final <T> void resume(@NotNull DispatchedTask<? super T> dispatchedTask, @NotNull Continuation<? super T> continuation, int i2) {
        Object successfulResult$kotlinx_coroutines_core;
        Object takeState$kotlinx_coroutines_core = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exceptionalResult$kotlinx_coroutines_core = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        if (exceptionalResult$kotlinx_coroutines_core == null) {
            exceptionalResult$kotlinx_coroutines_core = null;
        } else if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
            exceptionalResult$kotlinx_coroutines_core = StackTraceRecoveryKt.recoverFromStackFrame(exceptionalResult$kotlinx_coroutines_core, (CoroutineStackFrame) continuation);
        }
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            Result.Companion companion = Result.INSTANCE;
            successfulResult$kotlinx_coroutines_core = ResultKt.createFailure(exceptionalResult$kotlinx_coroutines_core);
        } else {
            Result.Companion companion2 = Result.INSTANCE;
            successfulResult$kotlinx_coroutines_core = dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        }
        Object m200constructorimpl = Result.m200constructorimpl(successfulResult$kotlinx_coroutines_core);
        if (i2 == 0) {
            continuation.resumeWith(m200constructorimpl);
        } else if (i2 == 1) {
            DispatchedContinuationKt.resumeCancellableWith(continuation, m200constructorimpl);
        } else if (i2 != 2) {
            throw new IllegalStateException(("Invalid mode " + i2).toString());
        } else if (continuation != null) {
            DispatchedContinuation[ r4 = (DispatchedContinuation[) continuation;
            CoroutineContext coroutineContext = r4.get$context();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, r4.countOrElement);
            try {
                r4..resumeWith(m200constructorimpl);
                Unit unit = Unit.INSTANCE;
            } finally {
                ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            }
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.DispatchedContinuation<T>");
        }
    }

    private static final void resumeUnconfined(@NotNull DispatchedTask<?> dispatchedTask) {
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(dispatchedTask);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            resume(dispatchedTask, dispatchedTask.getDelegate$kotlinx_coroutines_core(), 2);
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }

    public static final void resumeWithStackTrace(@NotNull Continuation<?> continuation, @NotNull Throwable th) {
        Result.Companion companion = Result.INSTANCE;
        if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
            th = StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
        }
        continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
    }

    public static final void runUnconfinedEventLoop(@NotNull DispatchedTask<?> dispatchedTask, @NotNull EventLoop eventLoop, @NotNull Function0<Unit> function0) {
        eventLoop.incrementUseCount(true);
        try {
            function0.invoke();
            do {
            } while (eventLoop.processUnconfinedEvent());
            InlineMarker.finallyStart(1);
        } catch (Throwable th) {
            try {
                dispatchedTask.handleFatalException$kotlinx_coroutines_core(th, null);
                InlineMarker.finallyStart(1);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                eventLoop.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
        eventLoop.decrementUseCount(true);
        InlineMarker.finallyEnd(1);
    }
}
