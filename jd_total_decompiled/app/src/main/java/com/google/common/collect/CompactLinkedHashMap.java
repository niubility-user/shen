package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import java.util.Arrays;

@GwtIncompatible
/* loaded from: classes12.dex */
class CompactLinkedHashMap<K, V> extends CompactHashMap<K, V> {
    private static final int ENDPOINT = -2;
    private final boolean accessOrder;
    private transient int firstEntry;
    private transient int lastEntry;
    @VisibleForTesting
    transient long[] links;

    CompactLinkedHashMap() {
        this(3);
    }

    public static <K, V> CompactLinkedHashMap<K, V> create() {
        return new CompactLinkedHashMap<>();
    }

    public static <K, V> CompactLinkedHashMap<K, V> createWithExpectedSize(int i2) {
        return new CompactLinkedHashMap<>(i2);
    }

    private int getPredecessor(int i2) {
        return (int) (this.links[i2] >>> 32);
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

    @Override // com.google.common.collect.CompactHashMap
    void accessEntry(int i2) {
        if (this.accessOrder) {
            setSucceeds(getPredecessor(i2), getSuccessor(i2));
            setSucceeds(this.lastEntry, i2);
            setSucceeds(i2, -2);
            this.modCount++;
        }
    }

    @Override // com.google.common.collect.CompactHashMap
    int adjustAfterRemove(int i2, int i3) {
        return i2 >= size() ? i3 : i2;
    }

    @Override // com.google.common.collect.CompactHashMap, java.util.AbstractMap, java.util.Map
    public void clear() {
        super.clear();
        this.firstEntry = -2;
        this.lastEntry = -2;
    }

    @Override // com.google.common.collect.CompactHashMap
    int firstEntryIndex() {
        return this.firstEntry;
    }

    @Override // com.google.common.collect.CompactHashMap
    int getSuccessor(int i2) {
        return (int) this.links[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void init(int i2, float f2) {
        super.init(i2, f2);
        this.firstEntry = -2;
        this.lastEntry = -2;
        long[] jArr = new long[i2];
        this.links = jArr;
        Arrays.fill(jArr, -1L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
    public void insertEntry(int i2, K k2, V v, int i3) {
        super.insertEntry(i2, k2, v, i3);
        setSucceeds(this.lastEntry, i2);
        setSucceeds(i2, -2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.CompactHashMap
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
    @Override // com.google.common.collect.CompactHashMap
    public void resizeEntries(int i2) {
        super.resizeEntries(i2);
        this.links = Arrays.copyOf(this.links, i2);
    }

    CompactLinkedHashMap(int i2) {
        this(i2, 1.0f, false);
    }

    CompactLinkedHashMap(int i2, float f2, boolean z) {
        super(i2, f2);
        this.accessOrder = z;
    }
}
