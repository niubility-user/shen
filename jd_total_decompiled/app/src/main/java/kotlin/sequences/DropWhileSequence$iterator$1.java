package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Add missing generic type declarations: [T] */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0002\u00a2\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00028\u0000H\u0096\u0002\u00a2\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\b\u001a\u00020\u0007H\u0096\u0002\u00a2\u0006\u0004\b\b\u0010\tR\"\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\u0004\u0018\u00018\u00008\u0006@\u0006X\u0086\u000e\u00a2\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\u0015R\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00018\u0006@\u0006\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019\u00a8\u0006\u001a"}, d2 = {"kotlin/sequences/DropWhileSequence$iterator$1", "", "", "drop", "()V", "next", "()Ljava/lang/Object;", "", "hasNext", "()Z", "", "dropState", "I", "getDropState", "()I", "setDropState", "(I)V", "nextItem", "Ljava/lang/Object;", "getNextItem", "setNextItem", "(Ljava/lang/Object;)V", "iterator", "Ljava/util/Iterator;", "getIterator", "()Ljava/util/Iterator;", "kotlin-stdlib"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes11.dex */
public final class DropWhileSequence$iterator$1<T> implements Iterator<T>, KMappedMarker {
    private int dropState;
    @NotNull
    private final Iterator<T> iterator;
    @Nullable
    private T nextItem;
    final /* synthetic */ DropWhileSequence this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DropWhileSequence$iterator$1(DropWhileSequence dropWhileSequence) {
        Sequence sequence;
        this.this$0 = dropWhileSequence;
        sequence = dropWhileSequence.sequence;
        this.iterator = sequence.iterator();
        this.dropState = -1;
    }

    private final void drop() {
        Function1 function1;
        while (this.iterator.hasNext()) {
            T next = this.iterator.next();
            function1 = this.this$0.predicate;
            if (!((Boolean) function1.invoke(next)).booleanValue()) {
                this.nextItem = next;
                this.dropState = 1;
                return;
            }
        }
        this.dropState = 0;
    }

    public final int getDropState() {
        return this.dropState;
    }

    @NotNull
    public final Iterator<T> getIterator() {
        return this.iterator;
    }

    @Nullable
    public final T getNextItem() {
        return this.nextItem;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.dropState == -1) {
            drop();
        }
        return this.dropState == 1 || this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public T next() {
        if (this.dropState == -1) {
            drop();
        }
        if (this.dropState == 1) {
            T t = this.nextItem;
            this.nextItem = null;
            this.dropState = 0;
            return t;
        }
        return this.iterator.next();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setDropState(int i2) {
        this.dropState = i2;
    }

    public final void setNextItem(@Nullable T t) {
        this.nextItem = t;
    }
}
