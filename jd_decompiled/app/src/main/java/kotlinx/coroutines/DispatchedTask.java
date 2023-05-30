package kotlinx.coroutines;

import com.jingdong.common.XView2.common.XView2Constants;
import jpbury.t;
import kotlin.ExceptionsKt__ExceptionsKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TaskContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b \u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00060\u0002j\u0002`\u0003B\u000f\u0012\u0006\u0010!\u001a\u00020 \u00a2\u0006\u0004\b#\u0010$J\u0011\u0010\u0007\u001a\u0004\u0018\u00010\u0004H \u00a2\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000e\u001a\u00020\u000b2\b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\tH\u0010\u00a2\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u0011\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0010\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0000\u00a2\u0006\u0004\b\u0012\u0010\u0013J\r\u0010\u0015\u001a\u00020\u000b\u00a2\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u001b\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\t2\b\u0010\u0018\u001a\u0004\u0018\u00010\tH\u0000\u00a2\u0006\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001f\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c8 @ X\u00a0\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010!\u001a\u00020 8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0006\n\u0004\b!\u0010\"\u00a8\u0006%"}, d2 = {"Lkotlinx/coroutines/DispatchedTask;", "T", "Lkotlinx/coroutines/scheduling/Task;", "Lkotlinx/coroutines/SchedulerTask;", "", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", XView2Constants.STATE, "", "cause", "", "cancelResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelResult", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "getExceptionalResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "getExceptionalResult", "run", "()V", t.f20145j, "finallyException", "handleFatalException$kotlinx_coroutines_core", "(Ljava/lang/Throwable;Ljava/lang/Throwable;)V", "handleFatalException", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "", "resumeMode", "I", "<init>", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class DispatchedTask<T> extends Task {
    @JvmField
    public int resumeMode;

    public DispatchedTask(int i2) {
        this.resumeMode = i2;
    }

    public void cancelResult$kotlinx_coroutines_core(@Nullable Object r1, @NotNull Throwable cause) {
    }

    @NotNull
    public abstract Continuation<T> getDelegate$kotlinx_coroutines_core();

    @Nullable
    public final Throwable getExceptionalResult$kotlinx_coroutines_core(@Nullable Object r3) {
        if (!(r3 instanceof CompletedExceptionally)) {
            r3 = null;
        }
        CompletedExceptionally completedExceptionally = (CompletedExceptionally) r3;
        if (completedExceptionally != null) {
            return completedExceptionally.cause;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object r1) {
        return r1;
    }

    public final void handleFatalException$kotlinx_coroutines_core(@Nullable Throwable r3, @Nullable Throwable finallyException) {
        if (r3 == null && finallyException == null) {
            return;
        }
        if (r3 != null && finallyException != null) {
            ExceptionsKt__ExceptionsKt.addSuppressed(r3, finallyException);
        }
        if (r3 == null) {
            r3 = finallyException;
        }
        String str = "Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers";
        if (r3 == null) {
            Intrinsics.throwNpe();
        }
        CoroutineExceptionHandlerKt.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError(str, r3));
    }

    @Override // java.lang.Runnable
    public final void run() {
        Object m200constructorimpl;
        Object m200constructorimpl2;
        TaskContext taskContext = this.taskContext;
        try {
            Continuation<T> delegate$kotlinx_coroutines_core = getDelegate$kotlinx_coroutines_core();
            if (delegate$kotlinx_coroutines_core != null) {
                DispatchedContinuation[ r1 = (DispatchedContinuation[) delegate$kotlinx_coroutines_core;
                Continuation<T> continuation = r1.;
                CoroutineContext context = continuation.getContext();
                Object takeState$kotlinx_coroutines_core = takeState$kotlinx_coroutines_core();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(context, r1.countOrElement);
                Throwable exceptionalResult$kotlinx_coroutines_core = getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
                Job job = DispatchedTaskKt.isCancellableMode(this.resumeMode) ? (Job) context.get(Job.INSTANCE) : null;
                if (exceptionalResult$kotlinx_coroutines_core == null && job != null && !job.isActive()) {
                    Throwable cancellationException = job.getCancellationException();
                    cancelResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core, cancellationException);
                    Result.Companion companion = Result.INSTANCE;
                    if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                        cancellationException = StackTraceRecoveryKt.recoverFromStackFrame(cancellationException, (CoroutineStackFrame) continuation);
                    }
                    continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(cancellationException)));
                } else if (exceptionalResult$kotlinx_coroutines_core != null) {
                    Result.Companion companion2 = Result.INSTANCE;
                    continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(exceptionalResult$kotlinx_coroutines_core)));
                } else {
                    T successfulResult$kotlinx_coroutines_core = getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
                    Result.Companion companion3 = Result.INSTANCE;
                    continuation.resumeWith(Result.m200constructorimpl(successfulResult$kotlinx_coroutines_core));
                }
                Unit unit = Unit.INSTANCE;
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                try {
                    Result.Companion companion4 = Result.INSTANCE;
                    taskContext.afterTask();
                    m200constructorimpl2 = Result.m200constructorimpl(unit);
                } catch (Throwable th) {
                    Result.Companion companion5 = Result.INSTANCE;
                    m200constructorimpl2 = Result.m200constructorimpl(ResultKt.createFailure(th));
                }
                handleFatalException$kotlinx_coroutines_core(null, Result.m203exceptionOrNullimpl(m200constructorimpl2));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.DispatchedContinuation<T>");
        } catch (Throwable th2) {
            try {
                Result.Companion companion6 = Result.INSTANCE;
                taskContext.afterTask();
                m200constructorimpl = Result.m200constructorimpl(Unit.INSTANCE);
            } catch (Throwable th3) {
                Result.Companion companion7 = Result.INSTANCE;
                m200constructorimpl = Result.m200constructorimpl(ResultKt.createFailure(th3));
            }
            handleFatalException$kotlinx_coroutines_core(th2, Result.m203exceptionOrNullimpl(m200constructorimpl));
        }
    }

    @Nullable
    public abstract Object takeState$kotlinx_coroutines_core();
}
