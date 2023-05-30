package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public class ObjectCountLinkedHashMap<K> extends ObjectCountHashMap<K> {
    private static final int ENDPOINT = -2;
    private transient int firstEntry;
    private transient int lastEntry;
    @VisibleForTesting
    transient long[] links;

    /* loaded from: classes12.dex */
    private abstract class LinkedItr<T> implements Iterator<T> {
        private int expectedModCount;
        private int nextEntry;
        private int toRemove;

        private LinkedItr() {
            this.nextEntry = ObjectCountLinkedHashMap.this.firstEntry;
            this.toRemove = -1;
            this.expectedModCount = ObjectCountLinkedHashMap.this.modCount;
        }

        private void checkForConcurrentModification() {
            if (ObjectCountLinkedHashMap.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        abstract T getOutput(int i2);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.nextEntry != -2;
        }

        @Override // java.util.Iterator
        public T next() {
            checkForConcurrentModification();
            if (hasNext()) {
                T output = getOutput(this.nextEntry);
                int i2 = this.nextEntry;
                this.toRemove = i2;
                this.nextEntry = ObjectCountLinkedHashMap.this.getSuccessor(i2);
                return output;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            checkForConcurrentModification();
            CollectPreconditions.checkRemove(this.toRemove != -1);
            ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
            objectCountLinkedHashMap.remove(objectCountLinkedHashMap.keys[this.toRemove]);
            if (this.nextEntry >= ObjectCountLinkedHashMap.this.size()) {
                this.nextEntry = this.toRemove;
            }
            this.expectedModCount = ObjectCountLinkedHashMap.this.modCount;
            this.toRemove = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap() {
        this(3);
    }

    public static <K> ObjectCountLinkedHashMap<K> create() {
        return new ObjectCountLinkedHashMap<>();
    }

    public static <K> ObjectCountLinkedHashMap<K> createWithExpectedSize(int i2) {
        return new ObjectCountLinkedHashMap<>(i2);
    }

    private int getPredecessor(int i2) {
        return (int) (this.links[i2] >>> 32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSuccessor(int i2) {
        return (int) this.links[i2];
    }

    private void setPredecessor(int i2, int i3) {
        long[] jArr = this.links;
        jArr[i2] = (jArr[i2] & 4294967295L) | (i3 << 32);
    }

    private void setSucceeds(int i2, int i3) {
        if (i2 == -2) {
            this.firstEntry = i3;
        } else {
            setSuccessor(i2, i3);
        }
        if (i3 == -2) {
            this.lastEntry = i2;
        } else {
            setPredecessor(i3, i2);
        }
    }

    private void setSuccessor(int i2, int i3) {
        long[] jArr = this.links;
        jArr[i2] = (jArr[i2] & (-4294967296L)) | (i3 & 4294967295L);
    }

    @Override // com.google.common.collect.ObjectCountHashMap, com.google.common.collect.AbstractObjectCountMap
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.ObjectCountHashMap, com.google.common.collect.AbstractObjectCountMap
    Set<Multiset.Entry<K>> createEntrySet() {
        return new AbstractObjectCountMap<K>.EntrySetView() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.2
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<K>> iterator() {
                return new ObjectCountLinkedHashMap<K>.LinkedItr<Multiset.Entry<K>>() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.2.1
                    {
                        ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    @Override // com.google.common.collect.ObjectCountLinkedHashMap.LinkedItr
                    public Multiset.Entry<K> getOutput(int i2) {
                        return new AbstractObjectCountMap.MapEntry(i2);
                    }
                };
            }
        };
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    Set<K> createKeySet() {
        return new AbstractObjectCountMap<K>.KeySetView() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.1
            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<K> iterator() {
                return new ObjectCountLinkedHashMap<K>.LinkedItr<K>() { // from class: com.google.common.collect.ObjectCountLinkedHashMap.1.1
                    {
                        ObjectCountLinkedHashMap objectCountLinkedHashMap = ObjectCountLinkedHashMap.this;
                    }

                    @Override // com.google.common.collect.ObjectCountLinkedHashMap.LinkedItr
                    K getOutput(int i2) {
                        return (K) ObjectCountLinkedHashMap.this.keys[i2];
                    }
                };
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public Object[] toArray() {
                return ObjectArrays.toArrayImpl(this);
            }

            @Override // com.google.common.collect.AbstractObjectCountMap.KeySetView, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public <T> T[] toArray(T[] tArr) {
                return (T[]) ObjectArrays.toArrayImpl(this, tArr);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractObjectCountMap
    public int firstIndex() {
        int i2 = this.firstEntry;
        if (i2 == -2) {
            return -1;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void init(int i2, float f2) {
        super.init(i2, f2);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[i2];
        this.links = jArr;
        Arrays.fill(jArr, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void insertEntry(int i2, K k2, int i3, int i4) {
        super.insertEntry(i2, k2, i3, i4);
        setSucceeds(this.lastEntry, i2);
        setSucceeds(i2, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void moveLastEntry(int i2) {
        int size = size() - 1;
        setSucceeds(getPredecessor(i2), getSuccessor(i2));
        if (i2 < size) {
            setSucceeds(getPredecessor(size), i2);
            setSucceeds(i2, getSuccessor(size));
        }
        super.moveLastEntry(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractObjectCountMap
    public int nextIndex(int i2) {
        int successor = getSuccessor(i2);
        if (successor == -2) {
            return -1;
        }
        return successor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ObjectCountHashMap
    public void resizeEntries(int i2) {
        super.resizeEntries(i2);
        this.links = Arrays.copyOf(this.links, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap(int i2) {
        this(i2, 1.0f);
    }

    ObjectCountLinkedHashMap(int i2, float f2) {
        super(i2, f2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountLinkedHashMap(AbstractObjectCountMap<K> abstractObjectCountMap) {
        init(abstractObjectCountMap.size(), 1.0f);
        int firstIndex = abstractObjectCountMap.firstIndex();
        while (firstIndex != -1) {
            put(abstractObjectCountMap.getKey(firstIndex), abstractObjectCountMap.getValue(firstIndex));
            firstIndex = abstractObjectCountMap.nextIndex(firstIndex);
        }
    }
}
