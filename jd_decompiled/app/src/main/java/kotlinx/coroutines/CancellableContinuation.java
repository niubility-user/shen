package kotlinx.coroutines;

import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import jpbury.t;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u0002J%\u0010\u0006\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00028\u00002\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004H'\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\bH'\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0004H'\u00a2\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH'\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u001b\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\bH&\u00a2\u0006\u0004\b\u0014\u0010\u0015J8\u0010\u001b\u001a\u00020\r2'\u0010\u001a\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\b\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\r0\u0016j\u0002`\u0019H&\u00a2\u0006\u0004\b\u001b\u0010\u001cJ\u001b\u0010\u001e\u001a\u00020\r*\u00020\u001d2\u0006\u0010\u0003\u001a\u00028\u0000H'\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u001b\u0010 \u001a\u00020\r*\u00020\u001d2\u0006\u0010\t\u001a\u00020\bH'\u00a2\u0006\u0004\b \u0010!J:\u0010#\u001a\u00020\r2\u0006\u0010\u0003\u001a\u00028\u00002!\u0010\"\u001a\u001d\u0012\u0013\u0012\u00110\b\u00a2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\r0\u0016H'\u00a2\u0006\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u00138&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00138&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b'\u0010&R\u0016\u0010(\u001a\u00020\u00138&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b(\u0010&\u00a8\u0006)"}, d2 = {"Lkotlinx/coroutines/CancellableContinuation;", "T", "Lkotlin/coroutines/Continuation;", "value", "", "idempotent", "tryResume", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "", t.f20145j, "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "token", "", "completeResume", "(Ljava/lang/Object;)V", "initCancellability", "()V", "cause", "", "cancel", "(Ljava/lang/Throwable;)Z", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Throwable;)V", "onCancellation", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "isActive", "()Z", "isCompleted", "isCancelled", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public interface CancellableContinuation<T> extends Continuation<T> {

    @Metadata(bv = {1, 0, 3}, d1 = {}, d2 = {}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ boolean cancel$default(CancellableContinuation cancellableContinuation, Throwable th, int i2, Object obj) {
            if (obj == null) {
                if ((i2 & 1) != 0) {
                    th = null;
                }
                return cancellableContinuation.cancel(th);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: cancel");
        }

        public static /* synthetic */ Object tryResume$default(CancellableContinuation cancellableContinuation, Object obj, Object obj2, int i2, Object obj3) {
            if (obj3 == null) {
                if ((i2 & 2) != 0) {
                    obj2 = null;
                }
                return cancellableContinuation.tryResume(obj, obj2);
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: tryResume");
        }
    }

    boolean cancel(@Nullable Throwable cause);

    @InternalCoroutinesApi
    void completeResume(@NotNull Object token);

    @InternalCoroutinesApi
    void initCancellability();

    void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> handler);

    boolean isActive();

    boolean isCancelled();

    boolean isCompleted();

    @ExperimentalCoroutinesApi
    void resume(T t, @NotNull Function1<? super Throwable, Unit> function1);

    @ExperimentalCoroutinesApi
    void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t);

    @ExperimentalCoroutinesApi
    void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th);

    @InternalCoroutinesApi
    @Nullable
    Object tryResume(T value, @Nullable Object idempotent);

    @InternalCoroutinesApi
    @Nullable
    Object tryResumeWithException(@NotNull Throwable r1);
}
