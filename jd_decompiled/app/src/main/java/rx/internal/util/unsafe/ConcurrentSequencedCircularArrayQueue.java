package rx.internal.util.unsafe;

/* loaded from: classes11.dex */
public abstract class ConcurrentSequencedCircularArrayQueue<E> extends ConcurrentCircularArrayQueue<E> {
    private static final long ARRAY_BASE;
    private static final int ELEMENT_SHIFT;
    protected final long[] sequenceBuffer;

    static {
        if (8 == UnsafeAccess.UNSAFE.arrayIndexScale(long[].class)) {
            ELEMENT_SHIFT = ConcurrentCircularArrayQueue.SPARSE_SHIFT + 3;
            ARRAY_BASE = r1.arrayBaseOffset(long[].class) + (32 << (r3 - r2));
            return;
        }
        throw new IllegalStateException("Unexpected long[] element size");
    }

    public ConcurrentSequencedCircularArrayQueue(int i2) {
        super(i2);
        int i3 = (int) (this.mask + 1);
        this.sequenceBuffer = new long[(i3 << ConcurrentCircularArrayQueue.SPARSE_SHIFT) + 64];
        for (long j2 = 0; j2 < i3; j2++) {
            soSequence(this.sequenceBuffer, calcSequenceOffset(j2), j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long calcSequenceOffset(long j2) {
        return ARRAY_BASE + ((j2 & this.mask) << ELEMENT_SHIFT);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long lvSequence(long[] jArr, long j2) {
        return UnsafeAccess.UNSAFE.getLongVolatile(jArr, j2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void soSequence(long[] jArr, long j2, long j3) {
        UnsafeAccess.UNSAFE.putOrderedLong(jArr, j2, j3);
    }
}
