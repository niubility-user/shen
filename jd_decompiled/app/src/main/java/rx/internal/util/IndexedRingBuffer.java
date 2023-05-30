package rx.internal.util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;
import rx.Subscription;
import rx.functions.Func1;

/* loaded from: classes11.dex */
public final class IndexedRingBuffer<E> implements Subscription {
    private static final ObjectPool<IndexedRingBuffer<?>> POOL = new ObjectPool<IndexedRingBuffer<?>>() { // from class: rx.internal.util.IndexedRingBuffer.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // rx.internal.util.ObjectPool
        public IndexedRingBuffer<?> createObject() {
            return new IndexedRingBuffer<>();
        }
    };
    static final int SIZE;
    static int _size;
    private final ElementSection<E> elements = new ElementSection<>();
    private final IndexSection removed = new IndexSection();
    final AtomicInteger index = new AtomicInteger();
    final AtomicInteger removedIndex = new AtomicInteger();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class ElementSection<E> {
        final AtomicReferenceArray<E> array = new AtomicReferenceArray<>(IndexedRingBuffer.SIZE);
        final AtomicReference<ElementSection<E>> next = new AtomicReference<>();

        ElementSection() {
        }

        ElementSection<E> getNext() {
            if (this.next.get() != null) {
                return this.next.get();
            }
            ElementSection<E> elementSection = new ElementSection<>();
            return this.next.compareAndSet(null, elementSection) ? elementSection : this.next.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class IndexSection {
        private final AtomicIntegerArray unsafeArray = new AtomicIntegerArray(IndexedRingBuffer.SIZE);
        private final AtomicReference<IndexSection> _next = new AtomicReference<>();

        IndexSection() {
        }

        public int getAndSet(int i2, int i3) {
            return this.unsafeArray.getAndSet(i2, i3);
        }

        IndexSection getNext() {
            if (this._next.get() != null) {
                return this._next.get();
            }
            IndexSection indexSection = new IndexSection();
            return this._next.compareAndSet(null, indexSection) ? indexSection : this._next.get();
        }

        public void set(int i2, int i3) {
            this.unsafeArray.set(i2, i3);
        }
    }

    static {
        _size = 256;
        if (PlatformDependent.isAndroid()) {
            _size = 8;
        }
        String property = System.getProperty("rx.indexed-ring-buffer.size");
        if (property != null) {
            try {
                _size = Integer.parseInt(property);
            } catch (Exception e2) {
                System.err.println("Failed to set 'rx.indexed-ring-buffer.size' with value " + property + " => " + e2.getMessage());
            }
        }
        SIZE = _size;
    }

    IndexedRingBuffer() {
    }

    private ElementSection<E> getElementSection(int i2) {
        int i3 = SIZE;
        if (i2 < i3) {
            return this.elements;
        }
        int i4 = i2 / i3;
        ElementSection<E> elementSection = this.elements;
        for (int i5 = 0; i5 < i4; i5++) {
            elementSection = elementSection.getNext();
        }
        return elementSection;
    }

    private synchronized int getIndexForAdd() {
        int andIncrement;
        int indexFromPreviouslyRemoved = getIndexFromPreviouslyRemoved();
        if (indexFromPreviouslyRemoved >= 0) {
            int i2 = SIZE;
            if (indexFromPreviouslyRemoved < i2) {
                andIncrement = this.removed.getAndSet(indexFromPreviouslyRemoved, -1);
            } else {
                andIncrement = getIndexSection(indexFromPreviouslyRemoved).getAndSet(indexFromPreviouslyRemoved % i2, -1);
            }
            if (andIncrement == this.index.get()) {
                this.index.getAndIncrement();
            }
        } else {
            andIncrement = this.index.getAndIncrement();
        }
        return andIncrement;
    }

    private synchronized int getIndexFromPreviouslyRemoved() {
        int i2;
        int i3;
        do {
            i2 = this.removedIndex.get();
            if (i2 <= 0) {
                return -1;
            }
            i3 = i2 - 1;
        } while (!this.removedIndex.compareAndSet(i2, i3));
        return i3;
    }

    private IndexSection getIndexSection(int i2) {
        int i3 = SIZE;
        if (i2 < i3) {
            return this.removed;
        }
        int i4 = i2 / i3;
        IndexSection indexSection = this.removed;
        for (int i5 = 0; i5 < i4; i5++) {
            indexSection = indexSection.getNext();
        }
        return indexSection;
    }

    public static <T> IndexedRingBuffer<T> getInstance() {
        return (IndexedRingBuffer<T>) POOL.borrowObject();
    }

    private synchronized void pushRemovedIndex(int i2) {
        int andIncrement = this.removedIndex.getAndIncrement();
        int i3 = SIZE;
        if (andIncrement < i3) {
            this.removed.set(andIncrement, i2);
        } else {
            getIndexSection(andIncrement).set(andIncrement % i3, i2);
        }
    }

    public int add(E e2) {
        int indexForAdd = getIndexForAdd();
        int i2 = SIZE;
        if (indexForAdd < i2) {
            this.elements.array.set(indexForAdd, e2);
            return indexForAdd;
        }
        getElementSection(indexForAdd).array.set(indexForAdd % i2, e2);
        return indexForAdd;
    }

    public int forEach(Func1<? super E, Boolean> func1) {
        return forEach(func1, 0);
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return false;
    }

    public void releaseToPool() {
        int i2 = this.index.get();
        int i3 = 0;
        loop0: for (ElementSection<E> elementSection = this.elements; elementSection != null; elementSection = elementSection.next.get()) {
            int i4 = 0;
            while (i4 < SIZE) {
                if (i3 >= i2) {
                    break loop0;
                }
                elementSection.array.set(i4, null);
                i4++;
                i3++;
            }
        }
        this.index.set(0);
        this.removedIndex.set(0);
        POOL.returnObject(this);
    }

    public E remove(int i2) {
        E andSet;
        int i3 = SIZE;
        if (i2 < i3) {
            andSet = this.elements.array.getAndSet(i2, null);
        } else {
            andSet = getElementSection(i2).array.getAndSet(i2 % i3, null);
        }
        pushRemovedIndex(i2);
        return andSet;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        releaseToPool();
    }

    public int forEach(Func1<? super E, Boolean> func1, int i2) {
        int forEach = forEach(func1, i2, this.index.get());
        if (i2 > 0 && forEach == this.index.get()) {
            return forEach(func1, 0, i2);
        }
        if (forEach == this.index.get()) {
            return 0;
        }
        return forEach;
    }

    private int forEach(Func1<? super E, Boolean> func1, int i2, int i3) {
        ElementSection<E> elementSection;
        int i4;
        int i5 = this.index.get();
        ElementSection<E> elementSection2 = this.elements;
        int i6 = SIZE;
        if (i2 >= i6) {
            ElementSection<E> elementSection3 = getElementSection(i2);
            i4 = i2;
            i2 %= i6;
            elementSection = elementSection3;
        } else {
            elementSection = elementSection2;
            i4 = i2;
        }
        loop0: while (elementSection != null) {
            while (i2 < SIZE) {
                if (i4 >= i5 || i4 >= i3) {
                    break loop0;
                }
                Object obj = (E) elementSection.array.get(i2);
                if (obj != null && !func1.call(obj).booleanValue()) {
                    return i4;
                }
                i2++;
                i4++;
            }
            elementSection = elementSection.next.get();
            i2 = 0;
        }
        return i4;
    }
}
