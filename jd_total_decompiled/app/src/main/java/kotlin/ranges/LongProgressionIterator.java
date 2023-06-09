package kotlin.ranges;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.LongIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\r\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u000e\u001a\u00020\u0005\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u0003\u001a\u00020\u0002H\u0096\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u000f\u0010\u0006\u001a\u00020\u0005H\u0016\u00a2\u0006\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0003\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\u0003\u0010\bR\u0016\u0010\t\u001a\u00020\u00058\u0002@\u0002X\u0082\u0004\u00a2\u0006\u0006\n\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\u00020\u00058\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000b\u0010\n\u001a\u0004\b\f\u0010\u0007R\u0016\u0010\r\u001a\u00020\u00058\u0002@\u0002X\u0082\u000e\u00a2\u0006\u0006\n\u0004\b\r\u0010\n\u00a8\u0006\u0012"}, d2 = {"Lkotlin/ranges/LongProgressionIterator;", "Lkotlin/collections/LongIterator;", "", "hasNext", "()Z", "", "nextLong", "()J", "Z", "finalElement", "J", "step", "getStep", "next", "first", "last", "<init>", "(JJJ)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes.dex */
public final class LongProgressionIterator extends LongIterator {
    private final long finalElement;
    private boolean hasNext;
    private long next;
    private final long step;

    public LongProgressionIterator(long j2, long j3, long j4) {
        this.step = j4;
        this.finalElement = j3;
        boolean z = true;
        int i2 = (j4 > 0L ? 1 : (j4 == 0L ? 0 : -1));
        int i3 = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
        if (i2 <= 0 ? i3 < 0 : i3 > 0) {
            z = false;
        }
        this.hasNext = z;
        this.next = z ? j2 : j3;
    }

    public final long getStep() {
        return this.step;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.hasNext;
    }

    @Override // kotlin.collections.LongIterator
    public long nextLong() {
        long j2 = this.next;
        if (j2 == this.finalElement) {
            if (this.hasNext) {
                this.hasNext = false;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            this.next = this.step + j2;
        }
        return j2;
    }
}
