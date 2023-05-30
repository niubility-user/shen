package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public class ObjectCountHashMap<K> extends AbstractObjectCountMap<K> {
    static final float DEFAULT_LOAD_FACTOR = 1.0f;
    static final int DEFAULT_SIZE = 3;
    private static final long HASH_MASK = -4294967296L;
    private static final int MAXIMUM_CAPACITY = 1073741824;
    private static final long NEXT_MASK = 4294967295L;
    static final int UNSET = -1;
    @VisibleForTesting
    transient long[] entries;
    private transient float loadFactor;
    private transient int[] table;
    private transient int threshold;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class HashEntrySetView extends AbstractObjectCountMap<K>.EntrySetView {
        HashEntrySetView() {
            super();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Multiset.Entry<K>> iterator() {
            return new AbstractObjectCountMap<K>.Itr<Multiset.Entry<K>>() { // from class: com.google.common.collect.ObjectCountHashMap.HashEntrySetView.1
                {
                    ObjectCountHashMap objectCountHashMap = ObjectCountHashMap.this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.google.common.collect.AbstractObjectCountMap.Itr
                public Multiset.Entry<K> getOutput(int i2) {
                    return new AbstractObjectCountMap.MapEntry(i2);
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap() {
        init(3, 1.0f);
    }

    public static <K> ObjectCountHashMap<K> create() {
        return new ObjectCountHashMap<>();
    }

    public static <K> ObjectCountHashMap<K> createWithExpectedSize(int i2) {
        return new ObjectCountHashMap<>(i2);
    }

    private static int getHash(long j2) {
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

    @Override // com.google.common.collect.AbstractObjectCountMap
    public void clear() {
        this.modCount++;
        Arrays.fill(this.keys, 0, this.size, (Object) null);
        Arrays.fill(this.values, 0, this.size, 0);
        Arrays.fill(this.table, -1);
        Arrays.fill(this.entries, -1L);
        this.size = 0;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public boolean containsKey(@NullableDecl Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    Set<Multiset.Entry<K>> createEntrySet() {
        return new HashEntrySetView();
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    public int get(@NullableDecl Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        return this.values[indexOf];
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    int indexOf(@NullableDecl Object obj) {
        int smearedHash = Hashing.smearedHash(obj);
        int i2 = this.table[hashTableMask() & smearedHash];
        while (i2 != -1) {
            long j2 = this.entries[i2];
            if (getHash(j2) == smearedHash && Objects.equal(obj, this.keys[i2])) {
                return i2;
            }
            i2 = getNext(j2);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void init(int i2, float f2) {
        Preconditions.checkArgument(i2 >= 0, "Initial capacity must be non-negative");
        Preconditions.checkArgument(f2 > 0.0f, "Illegal load factor");
        int closedTableSize = Hashing.closedTableSize(i2, f2);
        this.table = newTable(closedTableSize);
        this.loadFactor = f2;
        this.keys = new Object[i2];
        this.values = new int[i2];
        this.entries = newEntries(i2);
        this.threshold = Math.max(1, (int) (closedTableSize * f2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertEntry(int i2, @NullableDecl K k2, int i3, int i4) {
        this.entries[i2] = (i4 << 32) | NEXT_MASK;
        this.keys[i2] = k2;
        this.values[i2] = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void moveLastEntry(int i2) {
        int size = size() - 1;
        if (i2 < size) {
            Object[] objArr = this.keys;
            objArr[i2] = objArr[size];
            int[] iArr = this.values;
            iArr[i2] = iArr[size];
            objArr[size] = null;
            iArr[size] = 0;
            long[] jArr = this.entries;
            long j2 = jArr[size];
            jArr[i2] = j2;
            jArr[size] = -1;
            int hash = getHash(j2) & hashTableMask();
            int[] iArr2 = this.table;
            int i3 = iArr2[hash];
            if (i3 == size) {
                iArr2[hash] = i2;
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
            this.keys[i2] = null;
            this.values[i2] = 0;
            this.entries[i2] = -1;
        }
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int put(@NullableDecl K k2, int i2) {
        CollectPreconditions.checkPositive(i2, "count");
        long[] jArr = this.entries;
        Object[] objArr = this.keys;
        int[] iArr = this.values;
        int smearedHash = Hashing.smearedHash(k2);
        int hashTableMask = hashTableMask() & smearedHash;
        int i3 = this.size;
        int[] iArr2 = this.table;
        int i4 = iArr2[hashTableMask];
        if (i4 == -1) {
            iArr2[hashTableMask] = i3;
        } else {
            while (true) {
                long j2 = jArr[i4];
                if (getHash(j2) == smearedHash && Objects.equal(k2, objArr[i4])) {
                    int i5 = iArr[i4];
                    iArr[i4] = i2;
                    return i5;
                }
                int next = getNext(j2);
                if (next == -1) {
                    jArr[i4] = swapNext(j2, i3);
                    break;
                }
                i4 = next;
            }
        }
        if (i3 != Integer.MAX_VALUE) {
            int i6 = i3 + 1;
            resizeMeMaybe(i6);
            insertEntry(i3, k2, i2, smearedHash);
            this.size = i6;
            if (i3 >= this.threshold) {
                resizeTable(this.table.length * 2);
            }
            this.modCount++;
            return 0;
        }
        throw new IllegalStateException("Cannot contain more than Integer.MAX_VALUE elements!");
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj) {
        return remove(obj, Hashing.smearedHash(obj));
    }

    @Override // com.google.common.collect.AbstractObjectCountMap
    @CanIgnoreReturnValue
    int removeEntry(int i2) {
        return remove(this.keys[i2], getHash(this.entries[i2]));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void resizeEntries(int i2) {
        this.keys = Arrays.copyOf(this.keys, i2);
        this.values = Arrays.copyOf(this.values, i2);
        long[] jArr = this.entries;
        int length = jArr.length;
        long[] copyOf = Arrays.copyOf(jArr, i2);
        if (i2 > length) {
            Arrays.fill(copyOf, length, i2, -1L);
        }
        this.entries = copyOf;
    }

    private int remove(@NullableDecl Object obj, int i2) {
        int hashTableMask = hashTableMask() & i2;
        int i3 = this.table[hashTableMask];
        if (i3 == -1) {
            return 0;
        }
        int i4 = -1;
        while (true) {
            if (getHash(this.entries[i3]) == i2 && Objects.equal(obj, this.keys[i3])) {
                int i5 = this.values[i3];
                if (i4 == -1) {
                    this.table[hashTableMask] = getNext(this.entries[i3]);
                } else {
                    long[] jArr = this.entries;
                    jArr[i4] = swapNext(jArr[i4], getNext(jArr[i3]));
                }
                moveLastEntry(i3);
                this.size--;
                this.modCount++;
                return i5;
            }
            int next = getNext(this.entries[i3]);
            if (next == -1) {
                return 0;
            }
            i4 = i3;
            i3 = next;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(AbstractObjectCountMap<K> abstractObjectCountMap) {
        init(abstractObjectCountMap.size(), 1.0f);
        int firstIndex = abstractObjectCountMap.firstIndex();
        while (firstIndex != -1) {
            put(abstractObjectCountMap.getKey(firstIndex), abstractObjectCountMap.getValue(firstIndex));
            firstIndex = abstractObjectCountMap.nextIndex(firstIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2) {
        this(i2, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ObjectCountHashMap(int i2, float f2) {
        init(i2, f2);
    }
}
