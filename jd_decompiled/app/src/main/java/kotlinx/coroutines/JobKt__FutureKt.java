package kotlinx.coroutines;

import java.util.concurrent.Future;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u0003*\u00020\u00002\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0007\u00a2\u0006\u0004\b\u0004\u0010\u0005\u001a!\u0010\b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00062\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0001\u00a2\u0006\u0004\b\b\u0010\t\u00a8\u0006\n"}, d2 = {"Lkotlinx/coroutines/Job;", "Ljava/util/concurrent/Future;", "future", "Lkotlinx/coroutines/DisposableHandle;", "cancelFutureOnCompletion", "(Lkotlinx/coroutines/Job;Ljava/util/concurrent/Future;)Lkotlinx/coroutines/DisposableHandle;", "Lkotlinx/coroutines/CancellableContinuation;", "", "cancelFutureOnCancellation", "(Lkotlinx/coroutines/CancellableContinuation;Ljava/util/concurrent/Future;)V", "kotlinx-coroutines-core"}, k = 5, mv = {1, 4, 0}, xs = "kotlinx/coroutines/JobKt")
/* loaded from: classes11.dex */
public final /* synthetic */ class JobKt__FutureKt {
    public static final void cancelFutureOnCancellation(@NotNull CancellableContinuation<?> cancellableContinuation, @NotNull Future<?> future) {
        cancellableContinuation.invokeOnCancellation(new CancelFutureOnCancel[(future));
    }

    @InternalCoroutinesApi
    @NotNull
    public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job job, @NotNull Future<?> future) {
        return job.invokeOnCompletion(new CancelFutureOnCompletion[(job, future));
    }
}
