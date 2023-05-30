package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u001d\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001a\u0010\u0007\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0096\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f8\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\r\u0010\u000e\u00a8\u0006\u0012"}, d2 = {"Lkotlinx/coroutines/ResumeAwaitOnCompletion;", "T", "Lkotlinx/coroutines/JobNode;", "Lkotlinx/coroutines/JobSupport;", "", "cause", "", "invoke", "(Ljava/lang/Throwable;)V", "", "toString", "()Ljava/lang/String;", "Lkotlinx/coroutines/CancellableContinuationImpl;", "continuation", "Lkotlinx/coroutines/CancellableContinuationImpl;", "job", "<init>", "(Lkotlinx/coroutines/JobSupport;Lkotlinx/coroutines/CancellableContinuationImpl;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.ResumeAwaitOnCompletion  reason: from toString */
/* loaded from: classes11.dex */
public final class ResumeAwaitOnCompletion[<T> extends JobNode<JobSupport> {

    /* renamed from: continuation  reason: from kotlin metadata and from toString */
    private final CancellableContinuationImpl<T> ResumeAwaitOnCompletion[;

    /* JADX WARN: Multi-variable type inference failed */
    public ResumeAwaitOnCompletion[(@NotNull JobSupport jobSupport, @NotNull CancellableContinuationImpl<? super T> cancellableContinuationImpl) {
        super(jobSupport);
        this.ResumeAwaitOnCompletion[ = cancellableContinuationImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
        invoke2(th);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    @NotNull
    public String toString() {
        return "ResumeAwaitOnCompletion[" + this.ResumeAwaitOnCompletion[ + ']';
    }

    @Override // kotlinx.coroutines.CompletionHandlerBase
    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public void invoke2(@Nullable Throwable cause) {
        Object state$kotlinx_coroutines_core = ((JobSupport) this.job).getState$kotlinx_coroutines_core();
        if (DebugKt.getASSERTIONS_ENABLED() && (!(state$kotlinx_coroutines_core instanceof Incomplete)) != true) {
            throw new AssertionError();
        }
        if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            CancellableContinuationImpl<T> cancellableContinuationImpl = this.ResumeAwaitOnCompletion[;
            Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
            Result.Companion companion = Result.INSTANCE;
            cancellableContinuationImpl.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(th)));
            return;
        }
        CancellableContinuationImpl<T> cancellableContinuationImpl2 = this.ResumeAwaitOnCompletion[;
        Object unboxState = JobSupportKt.unboxState(state$kotlinx_coroutines_core);
        Result.Companion companion2 = Result.INSTANCE;
        cancellableContinuationImpl2.resumeWith(Result.m200constructorimpl(unboxState));
    }
}
