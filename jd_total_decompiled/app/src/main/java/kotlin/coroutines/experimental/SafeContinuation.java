package kotlin.coroutines.experimental;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import jpbury.t;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\b\u0001\u0018\u0000 \u001a*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0002\u001a\u001bB!\b\u0000\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\u0004\b\u0017\u0010\u0018B\u0017\b\u0011\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u00a2\u0006\u0004\b\u0017\u0010\u0019J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016\u00a2\u0006\u0004\b\t\u0010\nJ\u0011\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0001\u00a2\u0006\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000b8\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00108V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00028\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001c"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation;", "T", "Lkotlin/coroutines/experimental/Continuation;", "value", "", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;)V", "", t.f20145j, "resumeWithException", "(Ljava/lang/Throwable;)V", "", "getResult", "()Ljava/lang/Object;", "result", "Ljava/lang/Object;", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", AnnoConst.Constructor_Context, "delegate", "Lkotlin/coroutines/experimental/Continuation;", "initialResult", "<init>", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "(Lkotlin/coroutines/experimental/Continuation;)V", "Companion", "Fail", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
@PublishedApi
/* loaded from: classes11.dex */
public final class SafeContinuation<T> implements Continuation<T> {
    private final Continuation<T> delegate;
    private volatile Object result;

    /* renamed from: Companion  reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Object UNDECIDED = new Object();
    private static final Object RESUMED = new Object();
    private static final AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> RESULT = AtomicReferenceFieldUpdater.newUpdater(SafeContinuation.class, Object.class, "result");

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\f\u0010\bR`\u0010\u0005\u001aF\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001 \u0004*\"\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0004*\b\u0012\u0002\b\u0003\u0018\u00010\u00030\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00020\u00028\u0002@\u0003X\u0083\u0004\u00a2\u0006\f\n\u0004\b\u0005\u0010\u0006\u0012\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u000b\u0010\n\u00a8\u0006\r"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Companion;", "", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/experimental/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "RESULT$annotations", "()V", "RESUMED", "Ljava/lang/Object;", "UNDECIDED", "<init>", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @JvmStatic
        private static /* synthetic */ void RESULT$annotations() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0003\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0007\u0010\bR\u0019\u0010\u0003\u001a\u00020\u00028\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\t"}, d2 = {"Lkotlin/coroutines/experimental/SafeContinuation$Fail;", "", "", t.f20145j, "Ljava/lang/Throwable;", "getException", "()Ljava/lang/Throwable;", "<init>", "(Ljava/lang/Throwable;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes11.dex */
    private static final class Fail {
        @NotNull
        private final Throwable exception;

        public Fail(@NotNull Throwable th) {
            this.exception = th;
        }

        @NotNull
        public final Throwable getException() {
            return this.exception;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SafeContinuation(@NotNull Continuation<? super T> continuation, @Nullable Object obj) {
        this.delegate = continuation;
        this.result = obj;
    }

    @Override // kotlin.coroutines.experimental.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.delegate.getContext();
    }

    @PublishedApi
    @Nullable
    public final Object getResult() {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        Object coroutine_suspended3;
        Object obj = this.result;
        Object obj2 = UNDECIDED;
        if (obj == obj2) {
            AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> atomicReferenceFieldUpdater = RESULT;
            coroutine_suspended2 = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
            if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, coroutine_suspended2)) {
                coroutine_suspended3 = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                return coroutine_suspended3;
            }
            obj = this.result;
        }
        if (obj == RESUMED) {
            coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        } else if (obj instanceof Fail) {
            throw ((Fail) obj).getException();
        } else {
            return obj;
        }
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resume(T value) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        while (true) {
            Object obj = this.result;
            Object obj2 = UNDECIDED;
            if (obj != obj2) {
                coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                if (obj != coroutine_suspended) {
                    throw new IllegalStateException("Already resumed");
                }
                AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> atomicReferenceFieldUpdater = RESULT;
                coroutine_suspended2 = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                if (atomicReferenceFieldUpdater.compareAndSet(this, coroutine_suspended2, RESUMED)) {
                    this.delegate.resume(value);
                    return;
                }
            } else if (RESULT.compareAndSet(this, obj2, value)) {
                return;
            }
        }
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resumeWithException(@NotNull Throwable exception) {
        Object coroutine_suspended;
        Object coroutine_suspended2;
        while (true) {
            Object obj = this.result;
            Object obj2 = UNDECIDED;
            if (obj != obj2) {
                coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                if (obj != coroutine_suspended) {
                    throw new IllegalStateException("Already resumed");
                }
                AtomicReferenceFieldUpdater<SafeContinuation<?>, Object> atomicReferenceFieldUpdater = RESULT;
                coroutine_suspended2 = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
                if (atomicReferenceFieldUpdater.compareAndSet(this, coroutine_suspended2, RESUMED)) {
                    this.delegate.resumeWithException(exception);
                    return;
                }
            } else if (RESULT.compareAndSet(this, obj2, new Fail(exception))) {
                return;
            }
        }
    }

    @PublishedApi
    public SafeContinuation(@NotNull Continuation<? super T> continuation) {
        this(continuation, UNDECIDED);
    }
}
