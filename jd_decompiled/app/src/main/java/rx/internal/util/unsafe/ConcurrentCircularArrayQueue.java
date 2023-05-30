package rx.internal.util.unsafe;

import java.util.Iterator;

/* loaded from: classes11.dex */
public abstract class ConcurrentCircularArrayQueue<E> extends ConcurrentCircularArrayQueueL0Pad<E> {
    protected static final int BUFFER_PAD = 32;
    private static final long REF_ARRAY_BASE;
    private static final int REF_ELEMENT_SHIFT;
    protected static final int SPARSE_SHIFT;
    protected final E[] buffer;
    protected final long mask;

    static {
        int intValue = Integer.getInteger("sparse.shift", 0).intValue();
        SPARSE_SHIFT = intValue;
        int arrayIndexScale = UnsafeAccess.UNSAFE.arrayIndexScale(Object[].class);
        if (4 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = intValue + 2;
        } else if (8 == arrayIndexScale) {
            REF_ELEMENT_SHIFT = intValue + 3;
        } else {
            throw new IllegalStateException("Unknown pointer size");
        }
        REF_ARRAY_BASE = r2.arrayBaseOffset(Object[].class) + (32 << (REF_ELEMENT_SHIFT - intValue));
    }

    public ConcurrentCircularArrayQueue(int i2) {
        int roundToPowerOfTwo = Pow2.roundToPowerOfTwo(i2);
        this.mask = (long) (roundToPowerOfTwo - 1);
        this.buffer = (E[]) new Object[(roundToPowerOfTwo << SPARSE_SHIFT) + 64];
    }

    public final long calcElementOffset(long j2) {
        return calcElementOffset(j2, this.mask);
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    public final E lpElement(long j2) {
        return lpElement(this.buffer, j2);
    }

    public final E lvElement(long j2) {
        return lvElement(this.buffer, j2);
    }

    protected final void soElement(long j2, E e2) {
        soElement(this.buffer, j2, e2);
    }

    public final void spElement(long j2, E e2) {
        spElement(this.buffer, j2, e2);
    }

    protected final long calcElementOffset(long j2, long j3) {
        return REF_ARRAY_BASE + ((j2 & j3) << REF_ELEMENT_SHIFT);
    }

    public final E lpElement(E[] eArr, long j2) {
        return (E) UnsafeAccess.UNSAFE.getObject(eArr, j2);
    }

    public final E lvElement(E[] eArr, long j2) {
        return (E) UnsafeAccess.UNSAFE.getObjectVolatile(eArr, j2);
    }

    public final void soElement(E[] eArr, long j2, E e2) {
        UnsafeAccess.UNSAFE.putOrderedObject(eArr, j2, e2);
    }

    public final void spElement(E[] eArr, long j2, E e2) {
        UnsafeAccess.UNSAFE.putObject(eArr, j2, e2);
    }
}
