package kotlin.coroutines.experimental.jvm.internal;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import com.unionpay.tsmservice.mi.data.Constant;
import jpbury.t;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\b&\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B!\u0012\u0006\u0010!\u001a\u00020\u0019\u0012\u0010\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0003\u00a2\u0006\u0004\b\"\u0010#J\u0019\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\bH\u0016\u00a2\u0006\u0004\b\n\u0010\u000bJ%\u0010\r\u001a\u0004\u0018\u00010\u00022\b\u0010\f\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\bH$\u00a2\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0011J+\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016\u00a2\u0006\u0004\b\u0010\u0010\u0012R\u001b\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00038F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0004@\u0004X\u0085\u000e\u00a2\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001e\u001a\u00020\u00168V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR \u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u001f\u0010 R \u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00038\u0004@\u0004X\u0085\u000e\u00a2\u0006\u0006\n\u0004\b\u000f\u0010 \u00a8\u0006$"}, d2 = {"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/jvm/internal/Lambda;", "", "Lkotlin/coroutines/experimental/Continuation;", "value", "", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;)V", "", t.f20145j, "resumeWithException", "(Ljava/lang/Throwable;)V", "data", "doResume", "(Ljava/lang/Object;Ljava/lang/Throwable;)Ljava/lang/Object;", "completion", "create", "(Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "getFacade", "()Lkotlin/coroutines/experimental/Continuation;", "facade", "Lkotlin/coroutines/experimental/CoroutineContext;", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "", Constant.KEY_PROMOTION_LABEL, "I", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", AnnoConst.Constructor_Context, "_facade", "Lkotlin/coroutines/experimental/Continuation;", "arity", "<init>", "(ILkotlin/coroutines/experimental/Continuation;)V", "kotlin-stdlib-coroutines"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class CoroutineImpl extends Lambda<Object> implements Continuation<Object> {
    private final CoroutineContext _context;
    private Continuation<Object> _facade;
    @JvmField
    @Nullable
    protected Continuation<Object> completion;
    @JvmField
    protected int label;

    public CoroutineImpl(int i2, @Nullable Continuation<Object> continuation) {
        super(i2);
        this.completion = continuation;
        this.label = continuation != null ? 0 : -1;
        this._context = continuation != null ? continuation.getContext() : null;
    }

    @NotNull
    public Continuation<Unit> create(@NotNull Continuation<?> completion) {
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    @Nullable
    protected abstract Object doResume(@Nullable Object data, @Nullable Throwable exception);

    @Override // kotlin.coroutines.experimental.Continuation
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        if (coroutineContext == null) {
            Intrinsics.throwNpe();
        }
        return coroutineContext;
    }

    @NotNull
    public final Continuation<Object> getFacade() {
        if (this._facade == null) {
            CoroutineContext coroutineContext = this._context;
            if (coroutineContext == null) {
                Intrinsics.throwNpe();
            }
            this._facade = CoroutineIntrinsics.interceptContinuationIfNeeded(coroutineContext, this);
        }
        Continuation<Object> continuation = this._facade;
        if (continuation == null) {
            Intrinsics.throwNpe();
        }
        return continuation;
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resume(@Nullable Object value) {
        Object coroutine_suspended;
        Continuation<Object> continuation = this.completion;
        if (continuation == null) {
            Intrinsics.throwNpe();
        }
        try {
            Object doResume = doResume(value, null);
            coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
            if (doResume != coroutine_suspended) {
                if (continuation == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                continuation.resume(doResume);
            }
        } catch (Throwable th) {
            continuation.resumeWithException(th);
        }
    }

    @Override // kotlin.coroutines.experimental.Continuation
    public void resumeWithException(@NotNull Throwable exception) {
        Object coroutine_suspended;
        Continuation<Object> continuation = this.completion;
        if (continuation == null) {
            Intrinsics.throwNpe();
        }
        try {
            Object doResume = doResume(null, exception);
            coroutine_suspended = IntrinsicsKt__IntrinsicsJvmKt.getCOROUTINE_SUSPENDED();
            if (doResume != coroutine_suspended) {
                if (continuation == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                continuation.resume(doResume);
            }
        } catch (Throwable th) {
            continuation.resumeWithException(th);
        }
    }

    @NotNull
    public Continuation<Unit> create(@Nullable Object value, @NotNull Continuation<?> completion) {
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
