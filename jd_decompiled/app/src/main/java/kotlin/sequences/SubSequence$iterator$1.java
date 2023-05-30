package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0006\u001a\u00020\u0005H\u0096\u0002\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0015"}, d2 = {"kotlin/sequences/SubSequence$iterator$1", "", "", "drop", "()V", "", "hasNext", "()Z", "next", "()Ljava/lang/Object;", "", "position", "I", "getPosition", "()I", "setPosition", "(I)V", "iterator", "Ljava/util/Iterator;", "getIterator", "()Ljava/util/Iterator;", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class SubSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    private final Iterator<T> iterator;
    private int position;
    final /* synthetic */ SubSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubSequence$iterator$1(SubSequence subSequence) {
        Sequence sequence;
        this.this$0 = subSequence;
        sequence = subSequence.sequence;
        this.iterator = sequence.iterator();
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0008 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void drop() {
        /*
            r2 = this;
        L0:
            int r0 = r2.position
            kotlin.sequences.SubSequence r1 = r2.this$0
            int r1 = kotlin.sequences.SubSequence.access$getStartIndex$p(r1)
            if (r0 >= r1) goto L1e
            java.util.Iterator<T> r0 = r2.iterator
            boolean r0 = r0.hasNext()
            if (r0 == 0) goto L1e
            java.util.Iterator<T> r0 = r2.iterator
            r0.next()
            int r0 = r2.position
            int r0 = r0 + 1
            r2.position = r0
            goto L0
        L1e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.sequences.SubSequence$iterator$1.drop():void");
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    public final int getPosition() {
        return this.position;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        int i2;
        drop();
        int i3 = this.position;
        i2 = this.this$0.endIndex;
        return i3 < i2 && this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        int i2;
        drop();
        int i3 = this.position;
        i2 = this.this$0.endIndex;
        if (i3 < i2) {
            this.position++;
            return this.iterator.next();
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setPosition(int i2) {
        this.position = i2;
    }
}
