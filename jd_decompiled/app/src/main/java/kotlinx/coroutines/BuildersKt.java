package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"kotlinx/coroutines/BuildersKt__BuildersKt", "kotlinx/coroutines/BuildersKt__Builders_commonKt"}, d2 = {}, k = 4, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class BuildersKt {
    @NotNull
    public static final <T> Deferred<T> async(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, @NotNull CoroutineStart coroutineStart, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.async(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    @ExperimentalCoroutinesApi
    @Nullable
    public static final <T> Object invoke(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return BuildersKt__Builders_commonKt.invoke(coroutineDispatcher, function2, continuation);
    }

    @NotNull
    public static final Job launch(@NotNull CoroutineScope coroutineScope, @NotNull CoroutineContext coroutineContext, @NotNull CoroutineStart coroutineStart, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return BuildersKt__Builders_commonKt.launch(coroutineScope, coroutineContext, coroutineStart, function2);
    }

    public static /* synthetic */ Job launch$default(CoroutineScope coroutineScope, CoroutineContext coroutineContext, CoroutineStart coroutineStart, Function2 function2, int i2, Object obj) {
        return BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext, coroutineStart, function2, i2, obj);
    }

    public static final <T> T runBlocking(@NotNull CoroutineContext coroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2) throws InterruptedException {
        return (T) BuildersKt__BuildersKt.runBlocking(coroutineContext, function2);
    }

    public static /* synthetic */ Object runBlocking$default(CoroutineContext coroutineContext, Function2 function2, int i2, Object obj) throws InterruptedException {
        return BuildersKt__BuildersKt.runBlocking$default(coroutineContext, function2, i2, obj);
    }

    @Nullable
    public static final <T> Object withContext(@NotNull CoroutineContext coroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> function2, @NotNull Continuation<? super T> continuation) {
        return BuildersKt__Builders_commonKt.withContext(coroutineContext, function2, continuation);
    }
}
