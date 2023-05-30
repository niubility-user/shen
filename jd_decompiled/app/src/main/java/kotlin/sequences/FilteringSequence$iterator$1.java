package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002\u00a2\u0006\u0004\b\b\u0010\tR$\u0010\n\u001a\u0004\u0018\u00018\u00008\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\u0006\"\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"kotlin/sequences/FilteringSequence$iterator$1", "", "", "calcNext", "()V", "next", "()Ljava/lang/Object;", "", "hasNext", "()Z", "nextItem", "Ljava/lang/Object;", "getNextItem", "setNextItem", "(Ljava/lang/Object;)V", "iterator", "Ljava/util/Iterator;", "getIterator", "()Ljava/util/Iterator;", "", "nextState", "I", "getNextState", "()I", "setNextState", "(I)V", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class FilteringSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    @NotNull
    private final Iterator<T> iterator;
    @Nullable
    private T nextItem;
    private int nextState;
    final /* synthetic */ FilteringSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteringSequence$iterator$1(FilteringSequence filteringSequence) {
        Sequence sequence;
        this.this$0 = filteringSequence;
        sequence = filteringSequence.sequence;
        this.iterator = sequence.iterator();
        this.nextState = -1;
    }

    private final void calcNext() {
        Function1 function1;
        boolean z;
        while (this.iterator.hasNext()) {
            T next = this.iterator.next();
            function1 = this.this$0.predicate;
            boolean booleanValue = ((Boolean) function1.invoke(next)).booleanValue();
            z = this.this$0.sendWhen;
            if (booleanValue == z) {
                this.nextItem = next;
                this.nextState = 1;
                return;
            }
        }
        this.nextState = 0;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    @Nullable
    public final T getNextItem() {
        return this.nextItem;
    }

    public final int getNextState() {
        return this.nextState;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.nextState == -1) {
            calcNext();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.nextState == -1) {
            calcNext();
        }
        if (this.nextState != 0) {
            T t = this.nextItem;
            this.nextItem = null;
            this.nextState = -1;
            return t;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setNextItem(@Nullable T t) {
        this.nextItem = t;
    }

    public final void setNextState(int i2) {
        this.nextState = i2;
    }
}
