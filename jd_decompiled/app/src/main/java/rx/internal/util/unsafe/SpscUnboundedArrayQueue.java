package rx.internal.util.unsafe;

import java.util.Iterator;
import sun.misc.Unsafe;

/* loaded from: classes11.dex */
public class SpscUnboundedArrayQueue<E> extends SpscUnboundedArrayQueueConsumerField<E> implements QueueProgressIndicators {
    private static final long C_INDEX_OFFSET;
    private static final long P_INDEX_OFFSET;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    static final int MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096).intValue();
    private static final Object HAS_NEXT = new Object();

    static {
        Unsafe unsafe = UnsafeAccess.UNSAFE;
        int arrayIndexScale = unsafe.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = 2;
        } else if (8 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        REF_ARRAY_BASE = unsafe.arrayBaseOffset(Object[].class);
        try {
            P_INDEX_OFFSET = unsafe.objectFieldOffset(SpscUnboundedArrayQueueProducerFields.class.getDeclaredField("producerIndex"));
            try {
                C_INDEX_OFFSET = unsafe.objectFieldOffset(SpscUnboundedArrayQueueConsumerField.class.getDeclaredField("consumerIndex"));
            } catch (NoSuchFieldException e2) {
                throw new RuntimeException(e2);
            }
        } catch (NoSuchFieldException e3) {
            throw new RuntimeException(e3);
        }
    }

    public SpscUnboundedArrayQueue(int i2) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        long j2 = (long) (roundToPowerOfTwo - 1);
        E[] eArr = (E[]) new Object[roundToPowerOfTwo + 1];
        this.producerBuffer = eArr;
        this.producerMask = j2;
        adjustLookAheadStep(roundToPowerOfTwo);
        this.consumerBuffer = eArr;
        this.consumerMask = j2;
        this.producerLookAhead = j2 - 1;
        soProducerIndex(0L);
    }

    private void adjustLookAheadStep(int i2) {
        this.producerLookAheadStep = Math.min(i2 / 4, MAX_LOOK_AHEAD_STEP);
    }

    private static long calcDirectOffset(long j2) {
        return REF_ARRAY_BASE + (j2 << REF_ELEMENT_SHIFT);
    }

    private static long calcWrappedOffset(long j2, long j3) {
        return calcDirectOffset(j2 & j3);
    }

    private long lvConsumerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, C_INDEX_OFFSET);
    }

    private static <E> Object lvElement(E[] eArr, long j2) {
        return UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j2);
    }

    private E[] lvNext(E[] eArr) {
        return (E[]) ((Object[]) lvElement(eArr, calcDirectOffset((long) (eArr.length - 1))));
    }

    private long lvProducerIndex() {
        return UnsafeAccess.UNSAFE.getLongVolatile(this, P_INDEX_OFFSET);
    }

    private E newBufferPeek(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        return (E) lvElement(eArr, calcWrappedOffset(j2, j3));
    }

    private E newBufferPoll(E[] eArr, long j2, long j3) {
        this.consumerBuffer = eArr;
        long calcWrappedOffset = calcWrappedOffset(j2, j3);
        E e2 = (E) lvElement(eArr, calcWrappedOffset);
        if (e2 == null) {
            return null;
        }
        soElement(eArr, calcWrappedOffset, null);
        soConsumerIndex(j2 + 1);
        return e2;
    }

    private void resize(E[] eArr, long j2, long j3, E e2, long j4) {
        E[] eArr2 = (E[]) new Object[eArr.length];
        this.producerBuffer = eArr2;
        this.producerLookAhead = (j4 + j2) - 1;
        soElement(eArr2, j3, e2);
        soNext(eArr, eArr2);
        soElement(eArr, j3, HAS_NEXT);
        soProducerIndex(j2 + 1);
    }

    private void soConsumerIndex(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, C_INDEX_OFFSET, j2);
    }

    private static void soElement(Object[] objArr, long j2, Object obj) {
        UnsafeAccess.UNSAFE.putOrderedObject(objArr, j2, obj);
    }

    private void soNext(E[] eArr, E[] eArr2) {
        soElement(eArr, calcDirectOffset((long) (eArr.length - 1)), eArr2);
    }

    private void soProducerIndex(long j2) {
        UnsafeAccess.UNSAFE.putOrderedLong(this, P_INDEX_OFFSET, j2);
    }

    private boolean writeToQueue(E[] eArr, E e2, long j2, long j3) {
        soElement(eArr, j3, e2);
        soProducerIndex(j2 + 1);
        return true;
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentConsumerIndex() {
        return lvConsumerIndex();
    }

    @Override // rx.internal.util.unsafe.QueueProgressIndicators
    public long currentProducerIndex() {
        return lvProducerIndex();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Queue
    public final boolean offer(E e2) {
        if (e2 != null) {
            E[] eArr = this.producerBuffer;
            long j2 = this.producerIndex;
            long j3 = this.producerMask;
            long calcWrappedOffset = calcWrappedOffset(j2, j3);
            if (j2 < this.producerLookAhead) {
                return writeToQueue(eArr, e2, j2, calcWrappedOffset);
            }
            long j4 = this.producerLookAheadStep + j2;
            if (lvElement(eArr, calcWrappedOffset(j4, j3)) == null) {
                this.producerLookAhead = j4 - 1;
                return writeToQueue(eArr, e2, j2, calcWrappedOffset);
            } else if (lvElement(eArr, calcWrappedOffset(1 + j2, j3)) != null) {
                return writeToQueue(eArr, e2, j2, calcWrappedOffset);
            } else {
                resize(eArr, j2, calcWrappedOffset, e2, j3);
                return true;
            }
        }
        throw new NullPointerException("Null is not a valid element");
    }

    @Override // java.util.Queue
    public final E peek() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        E e2 = (E) lvElement(eArr, calcWrappedOffset(j2, j3));
        return e2 == HAS_NEXT ? newBufferPeek(lvNext(eArr), j2, j3) : e2;
    }

    @Override // java.util.Queue
    public final E poll() {
        E[] eArr = this.consumerBuffer;
        long j2 = this.consumerIndex;
        long j3 = this.consumerMask;
        long calcWrappedOffset = calcWrappedOffset(j2, j3);
        E e2 = (E) lvElement(eArr, calcWrappedOffset);
        boolean z = e2 == HAS_NEXT;
        if (e2 == null || z) {
            if (z) {
                return newBufferPoll(lvNext(eArr), j2, j3);
            }
            return null;
        }
        soElement(eArr, calcWrappedOffset, null);
        soConsumerIndex(j2 + 1);
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        long lvConsumerIndex = lvConsumerIndex();
        while (true) {
            long lvProducerIndex = lvProducerIndex();
            long lvConsumerIndex2 = lvConsumerIndex();
            if (lvConsumerIndex == lvConsumerIndex2) {
                return (int) (lvProducerIndex - lvConsumerIndex2);
            }
            lvConsumerIndex = lvConsumerIndex2;
        }
    }
}
