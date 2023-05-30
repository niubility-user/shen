package kotlinx.coroutines;

import com.jingdong.common.XView2.common.XView2Constants;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a$\u0010\u0003\u001a\u0004\u0018\u00010\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0001H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004\u001a0\u0010\u0003\u001a\u0004\u0018\u00010\u0002\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0007\u001a6\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0001\"\u0004\b\u0000\u0010\u00002\b\u0010\b\u001a\u0004\u0018\u00010\u00022\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0000\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"T", "Lkotlin/Result;", "", "toState", "(Ljava/lang/Object;)Ljava/lang/Object;", "Lkotlinx/coroutines/CancellableContinuation;", "caller", "(Ljava/lang/Object;Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Object;", XView2Constants.STATE, "Lkotlin/coroutines/Continuation;", "uCont", "recoverResult", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class CompletedExceptionallyKt {
    @NotNull
    public static final <T> Object recoverResult(@Nullable Object obj, @NotNull Continuation<? super T> continuation) {
        if (obj instanceof CompletedExceptionally) {
            Result.Companion companion = Result.INSTANCE;
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.getRECOVER_STACK_TRACES() && (continuation instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(th, (CoroutineStackFrame) continuation);
            }
            return Result.m200constructorimpl(ResultKt.createFailure(th));
        }
        Result.Companion companion2 = Result.INSTANCE;
        return Result.m200constructorimpl(obj);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj) {
        Throwable m203exceptionOrNullimpl = Result.m203exceptionOrNullimpl(obj);
        return m203exceptionOrNullimpl == null ? obj : new CompletedExceptionally(m203exceptionOrNullimpl, false, 2, null);
    }

    @Nullable
    public static final <T> Object toState(@NotNull Object obj, @NotNull CancellableContinuation<?> cancellableContinuation) {
        Throwable m203exceptionOrNullimpl = Result.m203exceptionOrNullimpl(obj);
        if (m203exceptionOrNullimpl == null) {
            return obj;
        }
        if (DebugKt.getRECOVER_STACK_TRACES() && (cancellableContinuation instanceof CoroutineStackFrame)) {
            m203exceptionOrNullimpl = StackTraceRecoveryKt.recoverFromStackFrame(m203exceptionOrNullimpl, (CoroutineStackFrame) cancellableContinuation);
        }
        return new CompletedExceptionally(m203exceptionOrNullimpl, false, 2, null);
    }
}
