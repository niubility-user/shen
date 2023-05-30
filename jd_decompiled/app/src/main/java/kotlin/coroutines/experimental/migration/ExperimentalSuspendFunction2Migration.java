package kotlin.coroutines.experimental.migration;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\"\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004B/\u0012&\u0010\r\u001a\"\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004\u00a2\u0006\u0004\b\u0011\u0010\u0012J0\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00020\u0005H\u0096\u0002\u00a2\u0006\u0004\b\n\u0010\u000bR9\u0010\r\u001a\"\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00048\u0006@\u0006\u00a2\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0013"}, d2 = {"Lkotlin/coroutines/experimental/migration/ExperimentalSuspendFunction2Migration;", "T1", "T2", "R", "Lkotlin/Function3;", "Lkotlin/coroutines/experimental/Continuation;", "", "t1", "t2", "continuation", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "Lkotlin/coroutines/Continuation;", "function", "Lkotlin/jvm/functions/Function3;", "getFunction", "()Lkotlin/jvm/functions/Function3;", "<init>", "(Lkotlin/jvm/functions/Function3;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
final class ExperimentalSuspendFunction2Migration<T1, T2, R> implements Function3<T1, T2, Continuation<? super R>, Object> {
    @NotNull
    private final Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, Object> function;

    /* JADX WARN: Multi-variable type inference failed */
    public ExperimentalSuspendFunction2Migration(@NotNull Function3<? super T1, ? super T2, ? super kotlin.coroutines.Continuation<? super R>, ? extends Object> function3) {
        this.function = function3;
    }

    @NotNull
    public final Function3<T1, T2, kotlin.coroutines.Continuation<? super R>, Object> getFunction() {
        return this.function;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke((ExperimentalSuspendFunction2Migration<T1, T2, R>) obj, obj2, (Continuation) ((Continuation) obj3));
    }

    @Nullable
    public Object invoke(T1 t1, T2 t2, @NotNull Continuation<? super R> continuation) {
        return this.function.invoke(t1, t2, CoroutinesMigrationKt.toContinuation(continuation));
    }
}
