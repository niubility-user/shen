package kotlinx.coroutines.sync;

import com.jingdong.app.mall.bundle.dolphinlib.common.util.EtModelMaker;
import com.jingdong.common.sample.jshop.utils.DataCompassUtils;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbes;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListKt;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.SegmentOrClosed;
import kotlinx.coroutines.internal.Symbol;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0014\u001a\u00020\u0010\u0012\u0006\u0010\u0016\u001a\u00020\u0010\u00a2\u0006\u0004\b\u0017\u0010\u0018J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\b\u001a\u00020\u0005H\u0002\u00a2\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\n\u0010\tJ\u0013\u0010\u000b\u001a\u00020\u0003H\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u000b\u0010\fJ\u0013\u0010\r\u001a\u00020\u0003H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0003H\u0016\u00a2\u0006\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0013\u001a\u00020\u00108V@\u0016X\u0096\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0014\u001a\u00020\u00108\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\u0014\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lkotlinx/coroutines/sync/SemaphoreImpl;", "Lkotlinx/coroutines/sync/Semaphore;", "Lkotlinx/coroutines/CancellableContinuation;", "", EtModelMaker.KEY_CONT, "", "addAcquireToQueue", "(Lkotlinx/coroutines/CancellableContinuation;)Z", "tryResumeNextFromQueue", "()Z", "tryAcquire", "acquire", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "acquireSlowPath", "release", "()V", "", "getAvailablePermits", "()I", "availablePermits", "permits", "I", "acquiredPermits", "<init>", "(II)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class SemaphoreImpl implements Semaphore {
    volatile int _availablePermits;
    private volatile long deqIdx = 0;
    private volatile long enqIdx = 0;
    private volatile Object head;
    private final int permits;
    private volatile Object tail;
    private static final AtomicReferenceFieldUpdater head$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, DataCompassUtils.MODULE_TYPE_HEAD);
    private static final AtomicLongFieldUpdater deqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "deqIdx");
    private static final AtomicReferenceFieldUpdater tail$FU = AtomicReferenceFieldUpdater.newUpdater(SemaphoreImpl.class, Object.class, "tail");
    private static final AtomicLongFieldUpdater enqIdx$FU = AtomicLongFieldUpdater.newUpdater(SemaphoreImpl.class, "enqIdx");
    static final AtomicIntegerFieldUpdater _availablePermits$FU = AtomicIntegerFieldUpdater.newUpdater(SemaphoreImpl.class, "_availablePermits");

    public SemaphoreImpl(int i2, int i3) {
        this.permits = i2;
        if (!(i2 > 0)) {
            throw new IllegalArgumentException(("Semaphore should have at least 1 permit, but had " + i2).toString());
        }
        if (i3 >= 0 && i2 >= i3) {
            SemaphoreSegment[id= r2 = new SemaphoreSegment[id=(0L, null, 2);
            this.head = r2;
            this.tail = r2;
            this._availablePermits = i2 - i3;
            return;
        }
        throw new IllegalArgumentException(("The number of acquired permits should be in 0.." + i2).toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
        r8 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean addAcquireToQueue(CancellableContinuation<? super Unit> cont) {
        int i2;
        Object m1257constructorimpl;
        int i3;
        Symbol symbol;
        Symbol symbol2;
        Symbol symbol3;
        boolean z;
        Segment segment = (SemaphoreSegment[id=) this.tail;
        long andIncrement = enqIdx$FU.getAndIncrement(this);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        long j2 = andIncrement / i2;
        do {
            Segment segment2 = segment;
            while (true) {
                if (segment2.getId() < j2 || segment2.getRemoved()) {
                    Object obj = segment2.get_next();
                    if (obj == ConcurrentLinkedListKt.CLOSED) {
                        m1257constructorimpl = SegmentOrClosed.m1257constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    Segment segment3 = (Segment) ((ConcurrentLinkedListNode) obj);
                    if (segment3 == null) {
                        segment3 = SemaphoreKt.createSegment(segment2.getId() + 1, (SemaphoreSegment[id=) segment2);
                        if (segment2.trySetNext(segment3)) {
                            if (segment2.getRemoved()) {
                                segment2.remove();
                            }
                        }
                    }
                    segment2 = segment3;
                } else {
                    m1257constructorimpl = SegmentOrClosed.m1257constructorimpl(segment2);
                    break;
                }
            }
            if (!SegmentOrClosed.m1262isClosedimpl(m1257constructorimpl)) {
                Segment m1260getSegmentimpl = SegmentOrClosed.m1260getSegmentimpl(m1257constructorimpl);
                while (true) {
                    Segment segment4 = (Segment) this.tail;
                    if (segment4.getId() >= m1260getSegmentimpl.getId()) {
                        break;
                    } else if (!m1260getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                        z = false;
                        break;
                    } else if (tail$FU.compareAndSet(this, segment4, m1260getSegmentimpl)) {
                        if (segment4.decPointers$kotlinx_coroutines_core()) {
                            segment4.remove();
                        }
                    } else if (m1260getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                        m1260getSegmentimpl.remove();
                    }
                }
            } else {
                break;
            }
        } while (!z);
        SemaphoreSegment[id= r2 = (SemaphoreSegment[id=) SegmentOrClosed.m1260getSegmentimpl(m1257constructorimpl);
        i3 = SemaphoreKt.SEGMENT_SIZE;
        int i4 = (int) (andIncrement % i3);
        if (!r2.acquirers.compareAndSet(i4, null, cont)) {
            symbol = SemaphoreKt.PERMIT;
            symbol2 = SemaphoreKt.TAKEN;
            if (r2.acquirers.compareAndSet(i4, symbol, symbol2)) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                cont.resumeWith(Result.m200constructorimpl(unit));
                return true;
            }
            if (DebugKt.getASSERTIONS_ENABLED()) {
                Object obj2 = r2.acquirers.get(i4);
                symbol3 = SemaphoreKt.BROKEN;
                if (!(obj2 == symbol3)) {
                    throw new AssertionError();
                }
            }
            return false;
        }
        cont.invokeOnCancellation(new CancelSemaphoreAcquisitionHandler[(r2, i4));
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
        r6 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean tryResumeNextFromQueue() {
        int i2;
        Object m1257constructorimpl;
        int i3;
        int i4;
        Symbol symbol;
        Symbol symbol2;
        boolean tryResume;
        int i5;
        Symbol symbol3;
        Symbol symbol4;
        Symbol symbol5;
        boolean z;
        Segment segment = (SemaphoreSegment[id=) this.head;
        long andIncrement = deqIdx$FU.getAndIncrement(this);
        i2 = SemaphoreKt.SEGMENT_SIZE;
        long j2 = andIncrement / i2;
        do {
            Segment segment2 = segment;
            while (true) {
                if (segment2.getId() < j2 || segment2.getRemoved()) {
                    Object obj = segment2.get_next();
                    if (obj == ConcurrentLinkedListKt.CLOSED) {
                        m1257constructorimpl = SegmentOrClosed.m1257constructorimpl(ConcurrentLinkedListKt.CLOSED);
                        break;
                    }
                    Segment segment3 = (Segment) ((ConcurrentLinkedListNode) obj);
                    if (segment3 == null) {
                        segment3 = SemaphoreKt.createSegment(segment2.getId() + 1, (SemaphoreSegment[id=) segment2);
                        if (segment2.trySetNext(segment3)) {
                            if (segment2.getRemoved()) {
                                segment2.remove();
                            }
                        }
                    }
                    segment2 = segment3;
                } else {
                    m1257constructorimpl = SegmentOrClosed.m1257constructorimpl(segment2);
                    break;
                }
            }
            if (SegmentOrClosed.m1262isClosedimpl(m1257constructorimpl)) {
                break;
            }
            Segment m1260getSegmentimpl = SegmentOrClosed.m1260getSegmentimpl(m1257constructorimpl);
            while (true) {
                Segment segment4 = (Segment) this.head;
                if (segment4.getId() >= m1260getSegmentimpl.getId()) {
                    break;
                } else if (!m1260getSegmentimpl.tryIncPointers$kotlinx_coroutines_core()) {
                    z = false;
                    break;
                } else if (head$FU.compareAndSet(this, segment4, m1260getSegmentimpl)) {
                    if (segment4.decPointers$kotlinx_coroutines_core()) {
                        segment4.remove();
                    }
                } else if (m1260getSegmentimpl.decPointers$kotlinx_coroutines_core()) {
                    m1260getSegmentimpl.remove();
                }
            }
        } while (!z);
        SemaphoreSegment[id= r0 = (SemaphoreSegment[id=) SegmentOrClosed.m1260getSegmentimpl(m1257constructorimpl);
        r0.cleanPrev();
        if (r0.getId() > j2) {
            return false;
        }
        i4 = SemaphoreKt.SEGMENT_SIZE;
        int i6 = (int) (andIncrement % i4);
        symbol = SemaphoreKt.PERMIT;
        Object andSet = r0.acquirers.getAndSet(i6, symbol);
        if (andSet == null) {
            i5 = SemaphoreKt.MAX_SPIN_CYCLES;
            for (i3 = 0; i3 < i5; i3++) {
                Object obj2 = r0.acquirers.get(i6);
                symbol5 = SemaphoreKt.TAKEN;
                if (obj2 == symbol5) {
                    return true;
                }
            }
            symbol3 = SemaphoreKt.PERMIT;
            symbol4 = SemaphoreKt.BROKEN;
            return !r0.acquirers.compareAndSet(i6, symbol3, symbol4);
        }
        symbol2 = SemaphoreKt.CANCELLED;
        if (andSet == symbol2) {
            return false;
        }
        tryResume = SemaphoreKt.tryResume((CancellableContinuation) andSet);
        return tryResume;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    @Nullable
    public Object acquire(@NotNull Continuation<? super Unit> continuation) {
        Object coroutine_suspended;
        if (_availablePermits$FU.getAndDecrement(this) > 0) {
            return Unit.INSTANCE;
        }
        Object acquireSlowPath = acquireSlowPath(continuation);
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        return acquireSlowPath == coroutine_suspended ? acquireSlowPath : Unit.INSTANCE;
    }

    @Nullable
    final /* synthetic */ Object acquireSlowPath(@NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        CancellableContinuationImpl orCreateCancellableContinuation = CancellableContinuationKt.getOrCreateCancellableContinuation(intercepted);
        while (true) {
            if (addAcquireToQueue(orCreateCancellableContinuation)) {
                break;
            } else if (_availablePermits$FU.getAndDecrement(this) > 0) {
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                orCreateCancellableContinuation.resumeWith(Result.m200constructorimpl(unit));
                break;
            }
        }
        Object result = orCreateCancellableContinuation.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbes.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public int getAvailablePermits() {
        return Math.max(this._availablePermits, 0);
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public void release() {
        while (true) {
            int i2 = this._availablePermits;
            if (i2 < this.permits) {
                if (_availablePermits$FU.compareAndSet(this, i2, i2 + 1) && (i2 >= 0 || tryResumeNextFromQueue())) {
                    return;
                }
            } else {
                throw new IllegalStateException(("The number of released permits cannot be greater than " + this.permits).toString());
            }
        }
    }

    @Override // kotlinx.coroutines.sync.Semaphore
    public boolean tryAcquire() {
        int i2;
        do {
            i2 = this._availablePermits;
            if (i2 <= 0) {
                return false;
            }
        } while (!_availablePermits$FU.compareAndSet(this, i2, i2 - 1));
        return true;
    }
}
