package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtIncompatible
/* loaded from: classes12.dex */
public class CompactHashSet<E> extends AbstractSet<E> implements Serializable {
    private static final float DEFAULT_LOAD_FACTOR = 1.0f;
    private static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    transient Object[] elements;
    private transient long[] entries;
    transient float loadFactor;
    transient int modCount;
    private transient int size;
    private transient int[] table;
    private transient int threshold;

    public CompactHashSet() {
        init(3, 1.0f);
    }

    public static <E> CompactHashSet<E> create() {
        return new CompactHashSet<>();
    }

    public static <E> CompactHashSet<E> createWithExpectedSize(int i2) {
        return new CompactHashSet<>(i2);
    }

    public static int getHash(long j2) {
        return (int) (j2 >>> 32);
    }

    private static int getNext(long j2) {
        return (int) j2;
    }

    private int hashTableMask() {
        return this.table.length - 1;
    }

    private static long[] newEntries(int i2) {
        long[] jArr = new long[i2];
        Arrays.fill(jArr, -1L);
        return jArr;
    }

    private static int[] newTable(int i2) {
        int[] iArr = new int[i2];
        Arrays.fill(iArr, -1);
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init(3, 1.0f);
        int readInt = objectInputStream.readInt();
        while (true) {
            readInt--;
            if (readInt < 0) {
                return;
            }
            add(objectInputStream.readObject());
        }
    }

    private void resizeMeMaybe(int i2) {
        int length = this.entries.length;
        if (i2 > length) {
            int max = Math.max(1, length >>> 1) + length;
            if (max < 0) {
                max = Integer.MAX_VALUE;
            }
            if (max != length) {
                resizeEntries(max);
            }
        }
    }

    private void resizeTable(int i2) {
        if (this.table.length >= 1073741824) {
            this.threshold = Integer.MAX_VALUE;
            return;
        }
        int i3 = ((int) (i2 * this.loadFactor)) + 1;
        int[] newTable = newTable(i2);
        long[] jArr = this.entries;
        int length = newTable.length - 1;
        for (int i4 = 0; i4 < this.size; i4++) {
            int hash = getHash(jArr[i4]);
            int i5 = hash & length;
            int i6 = newTable[i5];
            newTable[i5] = i4;
            jArr[i4] = (hash << 32) | (NEXT_MASK & i6);
        }
        this.threshold = i3;
        this.table = newTable;
    }

    private static long swapNext(long j2, int i2) {
        return (j2 & HASH_MASK) | (i2 & NEXT_MASK);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this.size);
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            objectOutputStream.writeObject(it.next());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean add(@NullableDecl E e2) {
        long[] jArr = this.entries;
        Object[] objArr = this.elements;
        int smearedHash = Hashing.smearedHash(e2);
        int hashTableMask = hashTableMask() & smearedHash;
        int i2 = this.size;
        int[] iArr = this.table;
        int i3 = iArr[hashTableMask];
        if (i3 == -1) {
            iArr[hashTableMask] = i2;
        } else {
            while (true) {
                long j2 = jArr[i3];
                if (getHash(j2) == smearedHash && Objects.equal(e2, objArr[i3])) {
                    return false;
                }
                int next = getNext(j2);
                if (next == -1) {
                    jArr[i3] = swapNext(j2, i2);
                    break;
                }
                i3 = next;
            }
        }
        if (i2 != Integer.MAX_VALUE) {
            int i4 = i2 + 1;
            resizeMeMaybe(i4);
            insertEntry(i2, e2, smearedHash);
            this.size = i4;
            if (i2 >= this.threshold) {
                resizeTable(this.table.length * 2);
            }
            this.modCount++;
            return true;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    int adjustAfterRemove(int i2, int i3) {
        return i2 - 1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.modCount++;
        Arrays.fill(this.elements, 0, this.size, (Object) null);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int i2 = this.table[hashTableMask() & smearedHash];
        while (i2 != -1) {
            long j2 = this.entries[i2];
            if (getHash(j2) == smearedHash && Objects.equal(obj, this.elements[i2])) {
                return true;
            }
            i2 = getNext(j2);
        }
        return false;
    }

    int firstEntryIndex() {
        return isEmpty() ? -1 : 0;
    }

    int getSuccessor(int i2) {
        int i3 = i2 + 1;
        if (i3 < this.size) {
            return i3;
        }
        return -1;
    }

    public void init(int i2, float f2) {
        Preconditions.checkArgument(i2 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f2 > 0.0f, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(i2, f2);
        this.table = newTable(closedTableSize);
        this.loadFactor = f2;
        this.elements = new Object[i2];
        this.entries = newEntries(i2);
        this.threshold = Math.max(1, (int) (closedTableSize * f2));
    }

    public void insertEntry(int i2, E e2, int i3) {
        this.entries[i2] = (i3 << 32) | NEXT_MASK;
        this.elements[i2] = e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: com.google.common.collect.CompactHashSet.1
            int expectedModCount;
            int index;
            int indexToRemove = -1;

            {
                CompactHashSet.this = this;
                this.expectedModCount = this.modCount;
                this.index = this.firstEntryIndex();
            }

            private void checkForConcurrentModification() {
                if (CompactHashSet.this.modCount != this.expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index >= 0;
            }

            @Override // java.util.Iterator
            public E next() {
                checkForConcurrentModification();
                if (hasNext()) {
                    int i2 = this.index;
                    this.indexToRemove = i2;
                    CompactHashSet compactHashSet = CompactHashSet.this;
                    E e2 = (E) compactHashSet.elements[i2];
                    this.index = compactHashSet.getSuccessor(i2);
                    return e2;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                checkForConcurrentModification();
                CollectPreconditions.checkRemove(this.indexToRemove >= 0);
                this.expectedModCount++;
                CompactHashSet compactHashSet = CompactHashSet.this;
                compactHashSet.remove(compactHashSet.elements[this.indexToRemove], CompactHashSet.getHash(compactHashSet.entries[this.indexToRemove]));
                this.index = CompactHashSet.this.adjustAfterRemove(this.index, this.indexToRemove);
                this.indexToRemove = -1;
            }
        };
    }

    public void moveEntry(int i2) {
        int size = size() - 1;
        if (i2 < size) {
            Object[] objArr = this.elements;
            objArr[i2] = objArr[size];
            objArr[size] = null;
            long[] jArr = this.entries;
            long j2 = jArr[size];
            jArr[i2] = j2;
            jArr[size] = -1;
            int hash = getHash(j2) & hashTableMask();
            int[] iArr = this.table;
            int i3 = iArr[hash];
            if (i3 == size) {
                iArr[hash] = i2;
                return;
            }
            while (true) {
                long j3 = this.entries[i3];
                int next = getNext(j3);
                if (next == size) {
                    this.entries[i3] = swapNext(j3, i2);
                    return;
                }
                i3 = next;
            }
        } else {
            this.elements[i2] = null;
            this.entries[i2] = -1;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    public void resizeEntries(int i2) {
        this.elements = Arrays.copyOf(this.elements, i2);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i2);
        if (i2 > length) {
            Arrays.fill(copyOf, length, i2, -1L);
        }
        this.entries = copyOf;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return Arrays.copyOf(this.elements, this.size);
    }

    public void trimToSize() {
        int i2 = this.size;
        if (i2 < this.entries.length) {
            resizeEntries(i2);
        }
        int max = Math.max(1, Integer.highestOneBit((int) (i2 / this.loadFactor)));
        if (max < 1073741824) {
            double d = i2;
            double d2 = max;
            Double.isNaN(d);
            Double.isNaN(d2);
            if (d / d2 > this.loadFactor) {
                max <<= 1;
            }
        }
        if (max < this.table.length) {
            resizeTable(max);
        }
    }

    public static <E> CompactHashSet<E> create(Collection<? extends E> collection) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(collection.size());
        createWithExpectedSize.addAll(collection);
        return createWithExpectedSize;
    }

    @CanIgnoreReturnValue
    public boolean remove(Object obj, int i2) {
        int hashTableMask = hashTableMask() & i2;
        int i3 = this.table[hashTableMask];
        if (i3 == -1) {
            return false;
        }
        int i4 = -1;
        while (true) {
            if (getHash(this.entries[i3]) == i2 && Objects.equal(obj, this.elements[i3])) {
                if (i4 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i3]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i4] = swapNext(jArr[i4], getNext(jArr[i3]));
                }
                moveEntry(i3);
                this.size--;
                this.modCount++;
                return true;
            }
            int next = getNext(this.entries[i3]);
            if (next == -1) {
                return false;
            }
            i4 = i3;
            i3 = next;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this.elements, 0, this.size, tArr);
    }

    public CompactHashSet(int i2) {
        init(i2, 1.0f);
    }

    public static <E> CompactHashSet<E> create(E... eArr) {
        CompactHashSet<E> createWithExpectedSize = createWithExpectedSize(eArr.length);
        Collections.addAll(createWithExpectedSize, eArr);
        return createWithExpectedSize;
    }
}
