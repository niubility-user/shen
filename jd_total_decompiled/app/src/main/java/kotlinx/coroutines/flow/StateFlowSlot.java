package kotlinx.coroutines.flow;

import com.coremedia.iso.boxes.FreeBox;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\f\u0010\u0007J\r\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\r\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005\u00a2\u0006\u0004\b\b\u0010\u0007J\r\u0010\t\u001a\u00020\u0002\u00a2\u0006\u0004\b\t\u0010\u0004J\u0013\u0010\n\u001a\u00020\u0005H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowSlot;", "", "", "allocate", "()Z", "", FreeBox.TYPE, "()V", "makePending", "takePending", "awaitPending", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "<init>", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class StateFlowSlot {
    static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowSlot.class, Object.class, "_state");
    volatile Object _state = null;

    public final boolean allocate() {
        Symbol symbol;
        if (this._state != null) {
            return false;
        }
        symbol = StateFlowKt.NONE;
        this._state = symbol;
        return true;
    }

    @Nullable
    public final Object awaitPending(@NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Symbol symbol;
        Object coroutine_suspended;
        Symbol symbol2;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        cancellableContinuationImpl.initCancellability();
        if (!DebugKt.getASSERTIONS_ENABLED() || Boxing.boxBoolean(!(this._state instanceof CancellableContinuationImpl)).booleanValue()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
            symbol = StateFlowKt.NONE;
            if (!atomicReferenceFieldUpdater.compareAndSet(this, symbol, cancellableContinuationImpl)) {
                if (DebugKt.getASSERTIONS_ENABLED()) {
                    Object obj = this._state;
                    symbol2 = StateFlowKt.PENDING;
                    if (!Boxing.boxBoolean(obj == symbol2).booleanValue()) {
                        throw new AssertionError();
                    }
                }
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                cancellableContinuationImpl.resumeWith(Result.m200constructorimpl(unit));
            }
            Object result = cancellableContinuationImpl.getResult();
            coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (result == coroutine_suspended) {
                DebugProbes.probeCoroutineSuspended(continuation);
            }
            return result;
        }
        throw new AssertionError();
    }

    public final void free() {
        this._state = null;
    }

    public final void makePending() {
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        Symbol symbol4;
        while (true) {
            Object obj = this._state;
            if (obj == null) {
                return;
            }
            symbol = StateFlowKt.PENDING;
            if (obj == symbol) {
                return;
            }
            symbol2 = StateFlowKt.NONE;
            if (obj == symbol2) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
                symbol3 = StateFlowKt.PENDING;
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, symbol3)) {
                    return;
                }
            } else {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _state$FU;
                symbol4 = StateFlowKt.NONE;
                if (atomicReferenceFieldUpdater2.compareAndSet(this, obj, symbol4)) {
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion = Result.INSTANCE;
                    ((CancellableContinuationImpl) obj).resumeWith(Result.m200constructorimpl(unit));
                    return;
                }
            }
        }
    }

    public final boolean takePending() {
        Symbol symbol;
        Symbol symbol2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _state$FU;
        symbol = StateFlowKt.NONE;
        Object andSet = atomicReferenceFieldUpdater.getAndSet(this, symbol);
        if (andSet == null) {
            Intrinsics.throwNpe();
        }
        if (!DebugKt.getASSERTIONS_ENABLED() || (!(andSet instanceof CancellableContinuationImpl)) == true) {
            symbol2 = StateFlowKt.PENDING;
            return andSet == symbol2;
        }
        throw new AssertionError();
    }
}
