package kotlinx.coroutines;

import com.jingdong.amon.router.annotation.AnnoConst;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.internal.ThreadContextKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u00028\u00000\u00022\u00060\u0003j\u0002`\u00042\b\u0012\u0004\u0012\u00028\u00000\u0005B\u001d\u0012\u0006\u00107\u001a\u000206\u0012\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u00a2\u0006\u0004\bC\u0010DJ\u0017\u0010\b\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0016\u00a2\u0006\u0004\b\b\u0010\tJ\u0019\u0010\r\u001a\u00020\f2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\n\u00a2\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\n\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0011\u00a2\u0006\u0004\b\u0014\u0010\u0015J\u0015\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0013\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u0011\u0010\u001c\u001a\u0004\u0018\u00010\u0019H\u0010\u00a2\u0006\u0004\b\u001a\u0010\u001bJ \u0010 \u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0016\u00f8\u0001\u0000\u00a2\u0006\u0004\b \u0010!J!\u0010\"\u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b\"\u0010!J\u0010\u0010#\u001a\u00020\fH\u0086\b\u00a2\u0006\u0004\b#\u0010$J!\u0010%\u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0086\b\u00f8\u0001\u0000\u00a2\u0006\u0004\b%\u0010!J\u001f\u0010+\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00028\u0000H\u0000\u00a2\u0006\u0004\b)\u0010*J\u000f\u0010-\u001a\u00020,H\u0016\u00a2\u0006\u0004\b-\u0010.R\u001c\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b\u0012\u0010/R\u001e\u00100\u001a\u0004\u0018\u00010\u00198\u0000@\u0000X\u0081\u000e\u00a2\u0006\f\n\u0004\b0\u00101\u0012\u0004\b2\u00103R\u0016\u0010'\u001a\u00020&8\u0016@\u0016X\u0096\u0005\u00a2\u0006\u0006\u001a\u0004\b4\u00105R\u0016\u00107\u001a\u0002068\u0006@\u0007X\u0087\u0004\u00a2\u0006\u0006\n\u0004\b7\u00108R$\u00109\u001a\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00048\u0016@\u0016X\u0096\u0004\u00a2\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u001c\u0010?\u001a\b\u0012\u0004\u0012\u00028\u00000\u00058P@\u0010X\u0090\u0004\u00a2\u0006\u0006\u001a\u0004\b=\u0010>R\u0019\u0010A\u001a\b\u0012\u0002\b\u0003\u0018\u00010\n8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b@\u0010\u0010R\u0016\u0010B\u001a\u00020\u00198\u0000@\u0001X\u0081\u0004\u00a2\u0006\u0006\n\u0004\bB\u00101\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lkotlinx/coroutines/DispatchedContinuation;", "T", "Lkotlinx/coroutines/DispatchedTask;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Lkotlinx/coroutines/internal/CoroutineStackFrame;", "Lkotlin/coroutines/Continuation;", "Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/internal/StackTraceElement;", "getStackTraceElement", "()Ljava/lang/StackTraceElement;", "Lkotlinx/coroutines/CancellableContinuationImpl;", "requester", "", "isReusable", "(Lkotlinx/coroutines/CancellableContinuationImpl;)Z", "claimReusableCancellableContinuation", "()Lkotlinx/coroutines/CancellableContinuationImpl;", "Lkotlinx/coroutines/CancellableContinuation;", "continuation", "", "checkPostponedCancellation", "(Lkotlinx/coroutines/CancellableContinuation;)Ljava/lang/Throwable;", "cause", "postponeCancellation", "(Ljava/lang/Throwable;)Z", "", "takeState$kotlinx_coroutines_core", "()Ljava/lang/Object;", "takeState", "Lkotlin/Result;", "result", "", "resumeWith", "(Ljava/lang/Object;)V", "resumeCancellableWith", "resumeCancelled", "()Z", "resumeUndispatchedWith", "Lkotlin/coroutines/CoroutineContext;", AnnoConst.Constructor_Context, "value", "dispatchYield$kotlinx_coroutines_core", "(Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "dispatchYield", "", "toString", "()Ljava/lang/String;", "Lkotlin/coroutines/Continuation;", "_state", "Ljava/lang/Object;", "_state$annotations", "()V", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "callerFrame", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getDelegate$kotlinx_coroutines_core", "()Lkotlin/coroutines/Continuation;", "delegate", "getReusableCancellableContinuation", "reusableCancellableContinuation", "countOrElement", "<init>", "(Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/coroutines/Continuation;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* renamed from: kotlinx.coroutines.DispatchedContinuation  reason: from toString */
/* loaded from: classes11.dex */
public final class DispatchedContinuation[<T> extends DispatchedTask<T> implements CoroutineStackFrame, Continuation<T> {
    private static final AtomicReferenceFieldUpdater _reusableCancellableContinuation$FU = AtomicReferenceFieldUpdater.newUpdater(DispatchedContinuation[.class, Object.class, "_reusableCancellableContinuation");
    private volatile Object _reusableCancellableContinuation;
    @JvmField
    @Nullable
    public Object _state;
    @Nullable
    private final CoroutineStackFrame callerFrame;
    @JvmField
    @NotNull

    /* renamed from: continuation  reason: from kotlin metadata and from toString */
    public final Continuation<T> ;
    @JvmField
    @NotNull
    public final Object countOrElement;
    @JvmField
    @NotNull

    /* renamed from: dispatcher  reason: from kotlin metadata and from toString */
    public final CoroutineDispatcher DispatchedContinuation[;

    /* JADX WARN: Multi-variable type inference failed */
    public DispatchedContinuation[(@NotNull CoroutineDispatcher coroutineDispatcher, @NotNull Continuation<? super T> continuation) {
        super(0);
        Symbol symbol;
        this.DispatchedContinuation[ = coroutineDispatcher;
        this. = continuation;
        symbol = DispatchedContinuationKt.UNDEFINED;
        this._state = symbol;
        this.callerFrame = (CoroutineStackFrame) (continuation instanceof CoroutineStackFrame ? continuation : null);
        this.countOrElement = ThreadContextKt.threadContextElements(get$context());
        this._reusableCancellableContinuation = null;
    }

    public static /* synthetic */ void _state$annotations() {
    }

    @Nullable
    public final Throwable checkPostponedCancellation(@NotNull CancellableContinuation<?> continuation) {
        Symbol symbol;
        do {
            Object obj = this._reusableCancellableContinuation;
            symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (obj != symbol) {
                if (obj == null) {
                    return null;
                }
                if (obj instanceof Throwable) {
                    if (_reusableCancellableContinuation$FU.compareAndSet(this, obj, null)) {
                        return (Throwable) obj;
                    }
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!_reusableCancellableContinuation$FU.compareAndSet(this, symbol, continuation));
        return null;
    }

    @Nullable
    public final CancellableContinuationImpl<T> claimReusableCancellableContinuation() {
        Object obj;
        do {
            obj = this._reusableCancellableContinuation;
            if (obj == null) {
                this._reusableCancellableContinuation = DispatchedContinuationKt.REUSABLE_CLAIMED;
                return null;
            } else if (!(obj instanceof CancellableContinuationImpl)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        } while (!_reusableCancellableContinuation$FU.compareAndSet(this, obj, DispatchedContinuationKt.REUSABLE_CLAIMED));
        return (CancellableContinuationImpl) obj;
    }

    public final void dispatchYield$kotlinx_coroutines_core(@NotNull CoroutineContext context, T value) {
        this._state = value;
        this.resumeMode = 1;
        this.DispatchedContinuation[.dispatchYield(context, this);
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public CoroutineStackFrame getCallerFrame() {
        return this.callerFrame;
    }

    @Override // kotlin.coroutines.Continuation
    @NotNull
    /* renamed from: getContext */
    public CoroutineContext get$context() {
        return this..get$context();
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @NotNull
    public Continuation<T> getDelegate$kotlinx_coroutines_core() {
        return this;
    }

    @Nullable
    public final CancellableContinuationImpl<?> getReusableCancellableContinuation() {
        Object obj = this._reusableCancellableContinuation;
        if (!(obj instanceof CancellableContinuationImpl)) {
            obj = null;
        }
        return (CancellableContinuationImpl) obj;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    @Nullable
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    public final boolean isReusable(@NotNull CancellableContinuationImpl<?> requester) {
        Object obj = this._reusableCancellableContinuation;
        if (obj != null) {
            return !(obj instanceof CancellableContinuationImpl) || obj == requester;
        }
        return false;
    }

    public final boolean postponeCancellation(@NotNull Throwable cause) {
        while (true) {
            Object obj = this._reusableCancellableContinuation;
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (Intrinsics.areEqual(obj, symbol)) {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, symbol, cause)) {
                    return true;
                }
            } else if (obj instanceof Throwable) {
                return true;
            } else {
                if (_reusableCancellableContinuation$FU.compareAndSet(this, obj, null)) {
                    return false;
                }
            }
        }
    }

    public final void resumeCancellableWith(@NotNull Object result) {
        boolean z;
        Object state = CompletedExceptionallyKt.toState(result);
        if (this.DispatchedContinuation[.isDispatchNeeded(get$context())) {
            this._state = state;
            this.resumeMode = 1;
            this.DispatchedContinuation[.mo1254dispatch(get$context(), this);
            return;
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 1;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            Job job = (Job) get$context().get(Job.INSTANCE);
            if (job == null || job.isActive()) {
                z = false;
            } else {
                CancellationException cancellationException = job.getCancellationException();
                Result.Companion companion = Result.INSTANCE;
                resumeWith(Result.m200constructorimpl(ResultKt.createFailure(cancellationException)));
                z = true;
            }
            if (!z) {
                CoroutineContext coroutineContext = get$context();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, this.countOrElement);
                this..resumeWith(result);
                Unit unit = Unit.INSTANCE;
                InlineMarker.finallyStart(1);
                ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
                InlineMarker.finallyEnd(1);
            }
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
            InlineMarker.finallyStart(1);
        } catch (Throwable th) {
            try {
                handleFatalException$kotlinx_coroutines_core(th, null);
                InlineMarker.finallyStart(1);
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
        eventLoop$kotlinx_coroutines_core.decrementUseCount(true);
        InlineMarker.finallyEnd(1);
    }

    public final boolean resumeCancelled() {
        Job job = (Job) get$context().get(Job.INSTANCE);
        if (job == null || job.isActive()) {
            return false;
        }
        CancellationException cancellationException = job.getCancellationException();
        Result.Companion companion = Result.INSTANCE;
        resumeWith(Result.m200constructorimpl(ResultKt.createFailure(cancellationException)));
        return true;
    }

    public final void resumeUndispatchedWith(@NotNull Object result) {
        CoroutineContext coroutineContext = get$context();
        Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext, this.countOrElement);
        try {
            this..resumeWith(result);
            Unit unit = Unit.INSTANCE;
        } finally {
            InlineMarker.finallyStart(1);
            ThreadContextKt.restoreThreadContext(coroutineContext, updateThreadContext);
            InlineMarker.finallyEnd(1);
        }
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(@NotNull Object result) {
        CoroutineContext coroutineContext = this..get$context();
        Object state = CompletedExceptionallyKt.toState(result);
        if (this.DispatchedContinuation[.isDispatchNeeded(coroutineContext)) {
            this._state = state;
            this.resumeMode = 0;
            this.DispatchedContinuation[.mo1254dispatch(coroutineContext, this);
            return;
        }
        EventLoop eventLoop$kotlinx_coroutines_core = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
        if (eventLoop$kotlinx_coroutines_core.isUnconfinedLoopActive()) {
            this._state = state;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core.incrementUseCount(true);
        try {
            CoroutineContext coroutineContext2 = get$context();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext2, this.countOrElement);
            this..resumeWith(result);
            Unit unit = Unit.INSTANCE;
            ThreadContextKt.restoreThreadContext(coroutineContext2, updateThreadContext);
            do {
            } while (eventLoop$kotlinx_coroutines_core.processUnconfinedEvent());
        } finally {
            try {
            } finally {
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    @Nullable
    public Object takeState$kotlinx_coroutines_core() {
        Symbol symbol;
        Symbol symbol2;
        Object obj = this._state;
        if (DebugKt.getASSERTIONS_ENABLED()) {
            symbol2 = DispatchedContinuationKt.UNDEFINED;
            if (!(obj != symbol2)) {
                throw new AssertionError();
            }
        }
        symbol = DispatchedContinuationKt.UNDEFINED;
        this._state = symbol;
        return obj;
    }

    @NotNull
    public String toString() {
        return "DispatchedContinuation[" + this.DispatchedContinuation[ + ", " + DebugStringsKt.toDebugString(this.) + ']';
    }
}
