package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public abstract class AbstractObjectCountMap<K> {
    static final int UNSET = -1;
    private transient Set<Multiset.Entry<K>> entrySetView;
    private transient Set<K> keySetView;
    transient Object[] keys;
    transient int modCount;
    transient int size;
    transient int[] values;

    /* loaded from: classes12.dex */
    abstract class EntrySetView extends Sets.ImprovedAbstractSet<Multiset.Entry<K>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                int indexOf = AbstractObjectCountMap.this.indexOf(entry.getElement());
                return indexOf != -1 && AbstractObjectCountMap.this.values[indexOf] == entry.getCount();
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Multiset.Entry) {
                Multiset.Entry entry = (Multiset.Entry) obj;
                int indexOf = AbstractObjectCountMap.this.indexOf(entry.getElement());
                if (indexOf == -1 || AbstractObjectCountMap.this.values[indexOf] != entry.getCount()) {
                    return false;
                }
                AbstractObjectCountMap.this.removeEntry(indexOf);
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractObjectCountMap.this.size;
        }
    }

    /* loaded from: classes12.dex */
    abstract class Itr<T> implements Iterator<T> {
        int expectedModCount;
        boolean nextCalled = false;
        int index = 0;

        /* JADX INFO: Access modifiers changed from: package-private */
        public Itr() {
            this.expectedModCount = AbstractObjectCountMap.this.modCount;
        }

        void checkForConcurrentModification() {
            if (AbstractObjectCountMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        abstract T getOutput(int i2);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < AbstractObjectCountMap.this.size;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                this.nextCalled = true;
                int i2 = this.index;
                this.index = i2 + 1;
                return getOutput(i2);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.nextCalled);
            this.expectedModCount++;
            int i2 = this.index - 1;
            this.index = i2;
            AbstractObjectCountMap.this.removeEntry(i2);
            this.nextCalled = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class KeySetView extends Sets.ImprovedAbstractSet<K> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new AbstractObjectCountMap<K>.Itr<K>() { // from class: com.google.common.collect.AbstractObjectCountMap.KeySetView.1
                {
                    AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
                }

                @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                K getOutput(int i2) {
                    return (K) AbstractObjectCountMap.this.keys[i2];
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractObjectCountMap.this.size;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
            return ObjectArrays.copyAsObjectArray(abstractObjectCountMap.keys, 0, abstractObjectCountMap.size);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            AbstractObjectCountMap abstractObjectCountMap = AbstractObjectCountMap.this;
            return (T[]) ObjectArrays.toArrayImpl(abstractObjectCountMap.keys, 0, abstractObjectCountMap.size, tArr);
        }
    }

    /* loaded from: classes12.dex */
    class MapEntry extends Multisets.AbstractEntry<K> {
        @NullableDecl
        final K key;
        int lastKnownIndex;

        /* JADX INFO: Access modifiers changed from: package-private */
        public MapEntry(int i2) {
            this.key = (K) AbstractObjectCountMap.this.keys[i2];
            this.lastKnownIndex = i2;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            updateLastKnownIndex();
            int i2 = this.lastKnownIndex;
            if (i2 == -1) {
                return 0;
            }
            return AbstractObjectCountMap.this.values[i2];
        }

        @Override // com.google.common.collect.Multiset.Entry
        public K getElement() {
            return this.key;
        }

        @CanIgnoreReturnValue
        public int setCount(int i2) {
            updateLastKnownIndex();
            int i3 = this.lastKnownIndex;
            if (i3 == -1) {
                AbstractObjectCountMap.this.put(this.key, i2);
                return 0;
            }
            int[] iArr = AbstractObjectCountMap.this.values;
            int i4 = iArr[i3];
            iArr[i3] = i2;
            return i4;
        }

        void updateLastKnownIndex() {
            int i2 = this.lastKnownIndex;
            if (i2 == -1 || i2 >= AbstractObjectCountMap.this.size() || !Objects.equal(this.key, AbstractObjectCountMap.this.keys[this.lastKnownIndex])) {
                this.lastKnownIndex = AbstractObjectCountMap.this.indexOf(this.key);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void clear();

    abstract boolean containsKey(@NullableDecl Object obj);

    abstract Set<Multiset.Entry<K>> createEntrySet();

    Set<K> createKeySet() {
        return new KeySetView();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Multiset.Entry<K>> entrySet() {
        Set<Multiset.Entry<K>> set = this.entrySetView;
        if (set == null) {
            Set<Multiset.Entry<K>> createEntrySet = createEntrySet();
            this.entrySetView = createEntrySet;
            return createEntrySet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int firstIndex() {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int get(@NullableDecl Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public Multiset.Entry<K> getEntry(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return new MapEntry(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public K getKey(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return (K) this.keys[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getValue(int i2) {
        Preconditions.checkElementIndex(i2, this.size);
        return this.values[i2];
    }

    abstract int indexOf(@NullableDecl Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<K> keySet() {
        Set<K> set = this.keySetView;
        if (set == null) {
            Set<K> createKeySet = createKeySet();
            this.keySetView = createKeySet;
            return createKeySet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int nextIndex(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public abstract int put(@NullableDecl K k2, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    @CanIgnoreReturnValue
    public abstract int remove(@NullableDecl Object obj);

    @CanIgnoreReturnValue
    abstract int removeEntry(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }
}
