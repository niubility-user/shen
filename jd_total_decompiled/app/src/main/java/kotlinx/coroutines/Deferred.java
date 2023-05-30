package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.selects.SelectClause1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0013\u0010\u0003\u001a\u00028\u0000H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0005\u001a\u00028\u0000H'\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0011\u0010\b\u001a\u0004\u0018\u00010\u0007H'\u00a2\u0006\u0004\b\b\u0010\tR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\n8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lkotlinx/coroutines/Deferred;", "T", "Lkotlinx/coroutines/Job;", "await", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompleted", "()Ljava/lang/Object;", "", "getCompletionExceptionOrNull", "()Ljava/lang/Throwable;", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnAwait", "()Lkotlinx/coroutines/selects/SelectClause1;", "onAwait", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface Deferred<T> extends Job {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes.dex */
    public static final class DefaultImpls {
        public static <T, R> R fold(Deferred<? extends T> deferred, R r, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
            return (R) Job.DefaultImpls.fold(deferred, r, function2);
        }

        @Nullable
        public static <T, E extends CoroutineContext.Element> E get(Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<E> key) {
            return (E) Job.DefaultImpls.get(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext minusKey(Deferred<? extends T> deferred, @NotNull CoroutineContext.Key<?> key) {
            return Job.DefaultImpls.minusKey(deferred, key);
        }

        @NotNull
        public static <T> CoroutineContext plus(Deferred<? extends T> deferred, @NotNull CoroutineContext coroutineContext) {
            return Job.DefaultImpls.plus(deferred, coroutineContext);
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
        @NotNull
        public static <T> Job plus(Deferred<? extends T> deferred, @NotNull Job job) {
            return Job.DefaultImpls.plus((Job) deferred, job);
        }
    }

    @Nullable
    Object await(@NotNull Continuation<? super T> continuation);

    @ExperimentalCoroutinesApi
    T getCompleted();

    @ExperimentalCoroutinesApi
    @Nullable
    Throwable getCompletionExceptionOrNull();

    @NotNull
    SelectClause1<T> getOnAwait();
}
