package kotlinx.coroutines.internal;

import com.jingdong.jdsdk.auraSetting.AuraConstants;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0017\b \u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00002\u00020\u0002B\u0011\u0012\b\u0010\u0018\u001a\u0004\u0018\u00018\u0000\u00a2\u0006\u0004\b!\u0010\"J \u0010\u0006\u001a\u0004\u0018\u00018\u00002\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0086\b\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000\u00a2\u0006\u0004\b\n\u0010\u000bJ\r\u0010\r\u001a\u00020\f\u00a2\u0006\u0004\b\r\u0010\u000eJ\r\u0010\u000f\u001a\u00020\t\u00a2\u0006\u0004\b\u000f\u0010\u0010J\r\u0010\u0011\u001a\u00020\f\u00a2\u0006\u0004\b\u0011\u0010\u000eR\u0013\u0010\u0012\u001a\u00020\t8F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00028B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0015\u0010\u0018\u001a\u0004\u0018\u00018\u00008F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0015\u0010\u001a\u001a\u0004\u0018\u00018\u00008F@\u0006\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0016\u0010\u001c\u001a\u00028\u00008B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0018\u0010\u001e\u001a\u0004\u0018\u00018\u00008B@\u0002X\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0016\u0010 \u001a\u00020\t8&@&X\u00a6\u0004\u00a2\u0006\u0006\u001a\u0004\b\u001f\u0010\u0010\u00a8\u0006#"}, d2 = {"Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", AuraConstants.MESSAGE_COUPON_TYPE_NEW, "", "Lkotlin/Function0;", "", "onClosedAction", "nextOrIfClosed", "(Lkotlin/jvm/functions/Function0;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "value", "", "trySetNext", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Z", "", "cleanPrev", "()V", "markAsClosed", "()Z", "remove", "isTail", "getNextOrClosed", "()Ljava/lang/Object;", "nextOrClosed", "getPrev", "()Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "prev", "getNext", "next", "getRightmostAliveNode", "rightmostAliveNode", "getLeftmostAliveNode", "leftmostAliveNode", "getRemoved", "removed", "<init>", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public abstract class ConcurrentLinkedListNode<N extends ConcurrentLinkedListNode<N>> {
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_next");
    private static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentLinkedListNode.class, Object.class, "_prev");
    private volatile Object _next = null;
    private volatile Object _prev;

    public ConcurrentLinkedListNode(@Nullable N n2) {
        this._prev = n2;
    }

    private final N getLeftmostAliveNode() {
        N prev = getPrev();
        while (prev != null && prev.getRemoved()) {
            prev = (N) prev._prev;
        }
        return prev;
    }

    /* renamed from: getNextOrClosed  reason: from getter */
    public final Object get_next() {
        return this._next;
    }

    private final N getRightmostAliveNode() {
        if (!DebugKt.getASSERTIONS_ENABLED() || (!isTail()) == true) {
            N next = getNext();
            if (next == null) {
                Intrinsics.throwNpe();
            }
            while (next.getRemoved()) {
                next = (N) next.getNext();
                if (next == null) {
                    Intrinsics.throwNpe();
                }
            }
            return next;
        }
        throw new AssertionError();
    }

    public final void cleanPrev() {
        _prev$FU.lazySet(this, null);
    }

    @Nullable
    public final N getNext() {
        Object obj = get_next();
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (N) obj;
    }

    @Nullable
    public final N getPrev() {
        return (N) this._prev;
    }

    public abstract boolean getRemoved();

    public final boolean isTail() {
        return getNext() == null;
    }

    public final boolean markAsClosed() {
        return _next$FU.compareAndSet(this, null, ConcurrentLinkedListKt.CLOSED);
    }

    @Nullable
    public final N nextOrIfClosed(@NotNull Function0 onClosedAction) {
        Object obj = get_next();
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            return (N) obj;
        }
        onClosedAction.invoke();
        throw null;
    }

    public final void remove() {
        if (DebugKt.getASSERTIONS_ENABLED() && !getRemoved()) {
            throw new AssertionError();
        }
        if (DebugKt.getASSERTIONS_ENABLED() && (!isTail()) != true) {
            throw new AssertionError();
        }
        while (true) {
            N leftmostAliveNode = getLeftmostAliveNode();
            N rightmostAliveNode = getRightmostAliveNode();
            rightmostAliveNode._prev = leftmostAliveNode;
            if (leftmostAliveNode != null) {
                leftmostAliveNode._next = rightmostAliveNode;
            }
            if (!rightmostAliveNode.getRemoved() && (leftmostAliveNode == null || !leftmostAliveNode.getRemoved())) {
                return;
            }
        }
    }

    public final boolean trySetNext(@NotNull N value) {
        return _next$FU.compareAndSet(this, null, value);
    }
}
