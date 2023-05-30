package kotlinx.coroutines;

import com.jingdong.amon.router.annotation.AnnoConst;
import com.jingdong.common.XView2.common.XView2Constants;
import com.jingdong.common.jdreactFramework.JDReactConstant;
import com.jingdong.manto.jsapi.refact.live.JsApiLivePlayer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import jpbury.t;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\b\u0011\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u00032\u00060\u0004j\u0002`\u0005B\u001d\u0012\f\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000r\u0012\u0006\u0010(\u001a\u00020#\u00a2\u0006\u0004\by\u0010zJ\u000f\u0010\u0007\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\n\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\f\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\f\u0010\bJ\u0017\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\rH\u0002\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u0013\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0011H\u0082\b\u00a2\u0006\u0004\b\u0013\u0010\u0014J\u000f\u0010\u0015\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0015\u0010\bJ\u000f\u0010\u0016\u001a\u00020\u0006H\u0002\u00a2\u0006\u0004\b\u0016\u0010\bJB\u0010\u001e\u001a\u00020\t2'\u0010\u001b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0017j\u0002`\u001a2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0002\u00a2\u0006\u0004\b\u001e\u0010\u001fJ8\u0010!\u001a\u00020 2'\u0010\u001b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0017j\u0002`\u001aH\u0002\u00a2\u0006\u0004\b!\u0010\"J\u0017\u0010%\u001a\u00020\t2\u0006\u0010$\u001a\u00020#H\u0002\u00a2\u0006\u0004\b%\u0010&J#\u0010*\u001a\u0004\u0018\u00010)2\b\u0010'\u001a\u0004\u0018\u00010\u001c2\u0006\u0010(\u001a\u00020#H\u0002\u00a2\u0006\u0004\b*\u0010+J\u0019\u0010,\u001a\u00020\t2\b\u0010'\u001a\u0004\u0018\u00010\u001cH\u0002\u00a2\u0006\u0004\b,\u0010-J\u000f\u0010.\u001a\u00020\tH\u0002\u00a2\u0006\u0004\b.\u0010\u000bJ\u000f\u0010/\u001a\u00020\tH\u0016\u00a2\u0006\u0004\b/\u0010\u000bJ\u000f\u00100\u001a\u00020\u0006H\u0001\u00a2\u0006\u0004\b0\u0010\bJ\u0017\u00103\u001a\n\u0018\u000101j\u0004\u0018\u0001`2H\u0016\u00a2\u0006\u0004\b3\u00104J\u0011\u00107\u001a\u0004\u0018\u00010\u001cH\u0010\u00a2\u0006\u0004\b5\u00106J!\u0010:\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u000e\u001a\u00020\rH\u0010\u00a2\u0006\u0004\b8\u00109J\u0019\u0010;\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016\u00a2\u0006\u0004\b;\u0010\u0010J\u0017\u0010>\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\rH\u0000\u00a2\u0006\u0004\b<\u0010=J\u0017\u0010A\u001a\u00020\r2\u0006\u0010@\u001a\u00020?H\u0016\u00a2\u0006\u0004\bA\u0010BJ\u0011\u0010C\u001a\u0004\u0018\u00010\u001cH\u0001\u00a2\u0006\u0004\bC\u00106J \u0010F\u001a\u00020\t2\f\u0010E\u001a\b\u0012\u0004\u0012\u00028\u00000DH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\bF\u0010-J:\u0010I\u001a\u00020\t2\u0006\u0010G\u001a\u00028\u00002!\u0010H\u001a\u001d\u0012\u0013\u0012\u00110\r\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0017H\u0016\u00a2\u0006\u0004\bI\u0010JJ8\u0010K\u001a\u00020\t2'\u0010\u001b\u001a#\u0012\u0015\u0012\u0013\u0018\u00010\r\u00a2\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\t0\u0017j\u0002`\u001aH\u0016\u00a2\u0006\u0004\bK\u0010LJ\u000f\u0010N\u001a\u00020\tH\u0000\u00a2\u0006\u0004\bM\u0010\u000bJ#\u0010\u0016\u001a\u0004\u0018\u00010\u001c2\u0006\u0010G\u001a\u00028\u00002\b\u0010O\u001a\u0004\u0018\u00010\u001cH\u0016\u00a2\u0006\u0004\b\u0016\u0010PJ\u0019\u0010R\u001a\u0004\u0018\u00010\u001c2\u0006\u0010Q\u001a\u00020\rH\u0016\u00a2\u0006\u0004\bR\u0010SJ\u0017\u0010U\u001a\u00020\t2\u0006\u0010T\u001a\u00020\u001cH\u0016\u00a2\u0006\u0004\bU\u0010-J\u001b\u0010W\u001a\u00020\t*\u00020V2\u0006\u0010G\u001a\u00028\u0000H\u0016\u00a2\u0006\u0004\bW\u0010XJ\u001b\u0010Y\u001a\u00020\t*\u00020V2\u0006\u0010Q\u001a\u00020\rH\u0016\u00a2\u0006\u0004\bY\u0010ZJ\u001f\u0010]\u001a\u00028\u0001\"\u0004\b\u0001\u0010\u00012\b\u0010\u001d\u001a\u0004\u0018\u00010\u001cH\u0010\u00a2\u0006\u0004\b[\u0010\\J\u000f\u0010_\u001a\u00020^H\u0016\u00a2\u0006\u0004\b_\u0010`J\u000f\u0010a\u001a\u00020^H\u0014\u00a2\u0006\u0004\ba\u0010`R\u001c\u0010c\u001a\u00020b8\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\bc\u0010d\u001a\u0004\be\u0010fR\u0016\u0010g\u001a\u00020\u00068V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bg\u0010\bR(\u0010m\u001a\u0004\u0018\u00010h2\b\u0010G\u001a\u0004\u0018\u00010h8B@BX\u0082\u000e\u00a2\u0006\f\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u0016\u0010n\u001a\u00020\u00068V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bn\u0010\bR\u001e\u0010q\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u00058V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bo\u0010pR\"\u0010s\u001a\b\u0012\u0004\u0012\u00028\u00000r8\u0000@\u0000X\u0080\u0004\u00a2\u0006\f\n\u0004\bs\u0010t\u001a\u0004\bu\u0010vR\u0016\u0010w\u001a\u00020\u00068V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\bw\u0010\bR\u0018\u0010\u001d\u001a\u0004\u0018\u00010\u001c8@@\u0000X\u0080\u0004\u00a2\u0006\u0006\u001a\u0004\bx\u00106\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006{"}, d2 = {"Lkotlinx/coroutines/CancellableContinuationImpl;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlinx/coroutines/CancellableContinuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "", "isReusable", "()Z", "", "setupCancellation", "()V", "checkCompleted", "", "cause", "cancelLater", "(Ljava/lang/Throwable;)Z", "Lkotlin/Function0;", JDReactConstant.BLOCK, "invokeHandlerSafely", "(Lkotlin/jvm/functions/Function0;)V", "trySuspend", "tryResume", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lkotlinx/coroutines/CompletionHandler;", "handler", "", XView2Constants.STATE, "multipleHandlersError", "(Lkotlin/jvm/functions/Function1;Ljava/lang/Object;)V", "Lkotlinx/coroutines/CancelHandler;", "makeHandler", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/CancelHandler;", "", "mode", "dispatchResume", "(I)V", "proposedUpdate", "resumeMode", "Lkotlinx/coroutines/CancelledContinuation;", "resumeImpl", "(Ljava/lang/Object;I)Lkotlinx/coroutines/CancelledContinuation;", "alreadyResumedError", "(Ljava/lang/Object;)V", "detachChildIfNonResuable", "initCancellability", "resetState", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "cancelResult$kotlinx_coroutines_core", "(Ljava/lang/Object;Ljava/lang/Throwable;)V", "cancelResult", "cancel", "parentCancelled$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "parentCancelled", "Lkotlinx/coroutines/Job;", "parent", "getContinuationCancellationCause", "(Lkotlinx/coroutines/Job;)Ljava/lang/Throwable;", "getResult", "Lkotlin/Result;", "result", "resumeWith", "value", "onCancellation", JsApiLivePlayer.CM_RESUME, "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "invokeOnCancellation", "(Lkotlin/jvm/functions/Function1;)V", "detachChild$kotlinx_coroutines_core", "detachChild", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", t.f20145j, "tryResumeWithException", "(Ljava/lang/Throwable;)Ljava/lang/Object;", "token", "completeResume", "Lkotlinx/coroutines/CoroutineDispatcher;", "resumeUndispatched", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Object;)V", "resumeUndispatchedWithException", "(Lkotlinx/coroutines/CoroutineDispatcher;Ljava/lang/Throwable;)V", "getSuccessfulResult$kotlinx_coroutines_core", "(Ljava/lang/Object;)Ljava/lang/Object;", "getSuccessfulResult", "", "toString", "()Ljava/lang/String;", "nameString", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "isActive", "Lkotlinx/coroutines/DisposableHandle;", "getParentHandle", "()Lkotlinx/coroutines/DisposableHandle;", "setParentHandle", "(Lkotlinx/coroutines/DisposableHandle;)V", "parentHandle", "isCancelled", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "callerFrame", "Lkotlin/coroutines/Continuation;", "delegate", "Lkotlin/coroutines/Continuation;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "isCompleted", "getState$kotlinx_coroutines_core", "<init>", "(Lkotlin/coroutines/Continuation;I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
@PublishedApi
/* loaded from: classes11.dex */
public class CancellableContinuationImpl<T> extends DispatchedTask<T> implements CancellableContinuation<T>, CoroutineStackFrame {
    private static final AtomicIntegerFieldUpdater _decision$FU = AtomicIntegerFieldUpdater.newUpdater(CancellableContinuationImpl.class, "_decision");
    private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(CancellableContinuationImpl.class, Object.class, "_state");
    private volatile int _decision;
    private volatile Object _parentHandle;
    private volatile Object _state;
    @NotNull
    private final CoroutineContext context;
    @NotNull
    private final Continuation<T> delegate;

    /* JADX WARN: Multi-variable type inference failed */
    public CancellableContinuationImpl(@NotNull Continuation<? super T> continuation, int i2) {
        super(i2);
        this.delegate = continuation;
        this.context = continuation.getContext();
        this._decision = 0;
        this._state = Active.INSTANCE;
        this._parentHandle = null;
    }

    private final void alreadyResumedError(Object proposedUpdate) {
        throw new IllegalStateException(("Already resumed, but proposed with update " + proposedUpdate).toString());
    }

    private final boolean cancelLater(Throwable cause) {
        if (this.resumeMode != 0) {
            return false;
        }
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation[)) {
            continuation = null;
        }
        DispatchedContinuation[ r0 = (DispatchedContinuation[) continuation;
        if (r0 != null) {
            return r0.postponeCancellation(cause);
        }
        return false;
    }

    private final boolean checkCompleted() {
        Throwable checkPostponedCancellation;
        boolean isCompleted = isCompleted();
        if (this.resumeMode != 0) {
            return isCompleted;
        }
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation[)) {
            continuation = null;
        }
        DispatchedContinuation[ r1 = (DispatchedContinuation[) continuation;
        if (r1 == null || (checkPostponedCancellation = r1.checkPostponedCancellation(this)) == null) {
            return isCompleted;
        }
        if (!isCompleted) {
            cancel(checkPostponedCancellation);
        }
        return true;
    }

    private final void detachChildIfNonResuable() {
        if (isReusable()) {
            return;
        }
        detachChild$kotlinx_coroutines_core();
    }

    private final void dispatchResume(int mode) {
        if (tryResume()) {
            return;
        }
        DispatchedTaskKt.dispatch(this, mode);
    }

    private final DisposableHandle getParentHandle() {
        return (DisposableHandle) this._parentHandle;
    }

    private final void invokeHandlerSafely(Function0<Unit> r5) {
        try {
            r5.invoke();
        } catch (Throwable th) {
            CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th));
        }
    }

    private final boolean isReusable() {
        Continuation<T> continuation = this.delegate;
        return (continuation instanceof DispatchedContinuation[) && ((DispatchedContinuation[) continuation).isReusable(this);
    }

    private final CancelHandler makeHandler(Function1<? super Throwable, Unit> handler) {
        return handler instanceof CancelHandler ? (CancelHandler) handler : new InvokeOnCancel[(handler);
    }

    private final void multipleHandlersError(Function1<? super Throwable, Unit> handler, Object r4) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + handler + ", already has " + r4).toString());
    }

    private final CancelledContinuation resumeImpl(Object proposedUpdate, int resumeMode) {
        while (true) {
            Object obj = this._state;
            if (obj instanceof NotCompleted) {
                if (_state$FU.compareAndSet(this, obj, proposedUpdate)) {
                    detachChildIfNonResuable();
                    dispatchResume(resumeMode);
                    return null;
                }
            } else {
                if (obj instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj;
                    if (cancelledContinuation.makeResumed()) {
                        return cancelledContinuation;
                    }
                }
                alreadyResumedError(proposedUpdate);
            }
        }
    }

    private final void setParentHandle(DisposableHandle disposableHandle) {
        this._parentHandle = disposableHandle;
    }

    private final void setupCancellation() {
        Job job;
        if (checkCompleted() || getParentHandle() != null || (job = (Job) this.delegate.getContext().get(Job.INSTANCE)) == null) {
            return;
        }
        job.start();
        DisposableHandle invokeOnCompletion$default = Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new ChildContinuation[(job, this), 2, null);
        setParentHandle(invokeOnCompletion$default);
        if (!isCompleted() || isReusable()) {
            return;
        }
        invokeOnCompletion$default.dispose();
        setParentHandle(NonDisposableHandle.INSTANCE);
    }

    private final boolean tryResume() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 1) {
                    return false;
                }
                throw new IllegalStateException("Already resumed".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 2));
        return true;
    }

    private final boolean trySuspend() {
        do {
            int i2 = this._decision;
            if (i2 != 0) {
                if (i2 == 2) {
                    return false;
                }
                throw new IllegalStateException("Already suspended".toString());
            }
        } while (!_decision$FU.compareAndSet(this, 0, 1));
        return true;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean cancel(@Nullable Throwable cause) {
        Object obj;
        boolean z;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            z = obj instanceof CancelHandler;
        } while (!_state$FU.compareAndSet(this, obj, new CancelledContinuation(this, cause, z)));
        if (z) {
            try {
                ((CancelHandler) obj).invoke(cause);
            } catch (Throwable th) {
                CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th));
            }
        }
        detachChildIfNonResuable();
        dispatchResume(0);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public void cancelResult$kotlinx_coroutines_core(@Nullable Object r4, @NotNull Throwable cause) {
        if (r4 instanceof CompletedWithCancellation[) {
            try {
                ((CompletedWithCancellation[) r4).onCancellation.invoke(cause);
            } catch (Throwable th) {
                CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th));
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void completeResume(@NotNull Object token) {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(token == CancellableContinuationImplKt.RESUME_TOKEN)) {
                throw new AssertionError();
            }
        }
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle != null) {
            parentHandle.dispose();
        }
        setParentHandle(NonDisposableHandle.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof CoroutineStackFrame)) {
            continuation = null;
        }
        return (CoroutineStackFrame) continuation;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.context;
    }

    @NotNull
    public Throwable getContinuationCancellationCause(@NotNull Job parent) {
        return parent.getCancellationException();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public final Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @PublishedApi
    @Nullable
    public final Object getResult() {
        Job job;
        Object coroutine_suspended;
        setupCancellation();
        if (trySuspend()) {
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            return coroutine_suspended;
        }
        Object obj = get_state();
        if (obj instanceof CompletedExceptionally) {
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                throw StackTraceRecoveryKt.recoverFromStackFrame(th, this);
            }
            throw th;
        } else if (this.resumeMode == 1 && (job = (Job) getContext().get(Job.INSTANCE)) != null && !job.isActive()) {
            CancellationException cancellationException = job.getCancellationException();
            cancelResult$kotlinx_coroutines_core(obj, cancellationException);
            if (DebugKt.getRECOVER_STACK_TRACES()) {
                throw StackTraceRecoveryKt.recoverFromStackFrame(cancellationException, this);
            }
            throw cancellationException;
        } else {
            return getSuccessfulResult$kotlinx_coroutines_core(obj);
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Nullable
    /* renamed from: getState$kotlinx_coroutines_core  reason: from getter */
    public final Object get_state() {
        return this._state;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.DispatchedTask
    public <T> T getSuccessfulResult$kotlinx_coroutines_core(@Nullable Object r2) {
        return r2 instanceof CompletedIdempotentResult[ ? (T) ((CompletedIdempotentResult[) r2).CompletedIdempotentResult[ : r2 instanceof CompletedWithCancellation[ ? (T) ((CompletedWithCancellation[) r2).CompletedWithCancellation[ : r2;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void initCancellability() {
        setupCancellation();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void invokeOnCancellation(@NotNull Function1<? super Throwable, Unit> handler) {
        CancelHandler cancelHandler = null;
        while (true) {
            Object obj = this._state;
            if (obj instanceof Active) {
                if (cancelHandler == null) {
                    cancelHandler = makeHandler(handler);
                }
                if (_state$FU.compareAndSet(this, obj, cancelHandler)) {
                    return;
                }
            } else if (!(obj instanceof CancelHandler)) {
                if (obj instanceof CancelledContinuation) {
                    if (!((CancelledContinuation) obj).makeHandled()) {
                        multipleHandlersError(handler, obj);
                    }
                    try {
                        if (!(obj instanceof CompletedExceptionally)) {
                            obj = null;
                        }
                        CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
                        handler.invoke(completedExceptionally != null ? completedExceptionally.cause : null);
                        return;
                    } catch (Throwable th) {
                        CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th));
                        return;
                    }
                }
                return;
            } else {
                multipleHandlersError(handler, obj);
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isActive() {
        return get_state() instanceof NotCompleted;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCancelled() {
        return get_state() instanceof CancelledContinuation;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public boolean isCompleted() {
        return !(get_state() instanceof NotCompleted);
    }

    @NotNull
    protected String nameString() {
        return "CancellableContinuation";
    }

    public final void parentCancelled$kotlinx_coroutines_core(@NotNull Throwable cause) {
        if (cancelLater(cause)) {
            return;
        }
        cancel(cause);
        detachChildIfNonResuable();
    }

    @JvmName(name = "resetState")
    public final boolean resetState() {
        if (DebugKt.getASSERTIONS_ENABLED()) {
            if (!(getParentHandle() != NonDisposableHandle.INSTANCE)) {
                throw new AssertionError();
            }
        }
        Object obj = this._state;
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(obj instanceof NotCompleted)) == true) {
            if (obj instanceof CompletedIdempotentResult[) {
                detachChild$kotlinx_coroutines_core();
                return false;
            }
            this._decision = 0;
            this._state = Active.INSTANCE;
            return true;
        }
        throw new AssertionError();
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resume(T t, @NotNull Function1<? super Throwable, Unit> function1) {
        CancelledContinuation resumeImpl = resumeImpl(new CompletedWithCancellation[(t, function1), this.resumeMode);
        if (resumeImpl != null) {
            try {
                function1.invoke(resumeImpl.cause);
            } catch (Throwable th) {
                CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), new CompletionHandlerException("Exception in cancellation handler for " + this, th));
            }
        }
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatched(@NotNull CoroutineDispatcher coroutineDispatcher, T t) {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation[)) {
            continuation = null;
        }
        DispatchedContinuation[ r0 = (DispatchedContinuation[) continuation;
        resumeImpl(t, (r0 != null ? r0.DispatchedContinuation[ : null) == coroutineDispatcher ? 2 : this.resumeMode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [kotlinx.coroutines.CoroutineDispatcher] */
    @Override // kotlinx.coroutines.CancellableContinuation
    public void resumeUndispatchedWithException(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Throwable th) {
        Continuation<T> continuation = this.delegate;
        if (!(continuation instanceof DispatchedContinuation[)) {
            continuation = null;
        }
        DispatchedContinuation[ r0 = (DispatchedContinuation[) continuation;
        resumeImpl(new CompletedExceptionally(th, false, 2, r2), (r0 != null ? r0.DispatchedContinuation[ : null) != coroutineDispatcher ? this.resumeMode : 2);
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object result) {
        resumeImpl(CompletedExceptionallyKt.toState(result, this), this.resumeMode);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        return get_state();
    }

    @NotNull
    public String toString() {
        return nameString() + '(' + DebugStringsKt.toDebugString(this.delegate) + "){" + get_state() + "}@" + DebugStringsKt.getHexAddress(this);
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResumeWithException(@NotNull Throwable r6) {
        Object obj;
        DefaultConstructorMarker defaultConstructorMarker;
        do {
            obj = this._state;
            defaultConstructorMarker = null;
            if (!(obj instanceof NotCompleted)) {
                return null;
            }
        } while (!_state$FU.compareAndSet(this, obj, new CompletedExceptionally(r6, false, 2, defaultConstructorMarker)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }

    @Override // kotlinx.coroutines.CancellableContinuation
    @Nullable
    public Object tryResume(T value, @Nullable Object idempotent) {
        Object obj;
        do {
            obj = this._state;
            if (!(obj instanceof NotCompleted)) {
                if (obj instanceof CompletedIdempotentResult[) {
                    CompletedIdempotentResult[ r0 = (CompletedIdempotentResult[) obj;
                    if (r0.idempotentResume == idempotent) {
                        if (DebugKt.getASSERTIONS_ENABLED()) {
                            if (!(r0.CompletedIdempotentResult[ == value)) {
                                throw new AssertionError();
                            }
                        }
                        return CancellableContinuationImplKt.RESUME_TOKEN;
                    }
                    return null;
                }
                return null;
            }
        } while (!_state$FU.compareAndSet(this, obj, idempotent == null ? value : new CompletedIdempotentResult[(idempotent, value)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
