package kotlin.coroutines.experimental.migration;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import jpbury.t;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0015\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u00a2\u0006\u0004\b\u0015\u0010\u0016J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\nR\u001c\u0010\f\u001a\u00020\u000b8\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0017"}, d2 = {"Lkotlin/coroutines/experimental/migration/ExperimentalContinuationMigration;", "T", "Lkotlin/coroutines/experimental/Continuation;", "value", "", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;)V", "", t.f20145j, "resumeWithException", "(Ljava/lang/Throwable;)V", "Lkotlin/coroutines/experimental/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "Lkotlin/coroutines/Continuation;", "continuation", "Lkotlin/coroutines/Continuation;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "<init>", "(Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class ExperimentalContinuationMigration<T> implements Continuation<T> {
    @NotNull
    private final CoroutineContext context;
    @NotNull
    private final kotlin.coroutines.Continuation<T> continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public ExperimentalContinuationMigration(@NotNull kotlin.coroutines.Continuation<? super T> continuation) {
        this.continuation = continuation;
        this.context = CoroutinesMigrationKt.toExperimentalCoroutineContext(continuation.getContext());
    }

    @Override // kotlin.coroutines.experimental.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    @NotNull
    public final kotlin.coroutines.Continuation<T> getContinuation() {
        return this.continuation;
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resume(T t) {
        kotlin.coroutines.Continuation<T> continuation = this.continuation;
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(t));
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resumeWithException(@NotNull Throwable r3) {
        kotlin.coroutines.Continuation<T> continuation = this.continuation;
        Result.Companion companion = Result.INSTANCE;
        continuation.resumeWith(Result.m200constructorimpl(ResultKt.createFailure(r3)));
    }
}
