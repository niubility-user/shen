package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public final class ImmutableSortedMap<K, V> extends ImmutableSortedMapFauxverideShim<K, V> implements NavigableMap<K, V> {
    private static final long serialVersionUID = 0;
    private transient ImmutableSortedMap<K, V> descendingMap;
    private final transient RegularImmutableSortedSet<K> keySet;
    private final transient ImmutableList<V> valueList;
    private static final Comparator<Comparable> NATURAL_ORDER = Ordering.natural();
    private static final ImmutableSortedMap<Comparable, Object> NATURAL_EMPTY_MAP = new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(Ordering.natural()), ImmutableList.of());

    /* loaded from: classes12.dex */
    public static class Builder<K, V> extends ImmutableMap.Builder<K, V> {
        private final Comparator<? super K> comparator;
        private transient Object[] keys;
        private transient Object[] values;

        public Builder(Comparator<? super K> comparator) {
            this(comparator, 4);
        }

        private void ensureCapacity(int i2) {
            Object[] objArr = this.keys;
            if (i2 > objArr.length) {
                int expandedCapacity = ImmutableCollection.Builder.expandedCapacity(objArr.length, i2);
                this.keys = Arrays.copyOf(this.keys, expandedCapacity);
                this.values = Arrays.copyOf(this.values, expandedCapacity);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public /* bridge */ /* synthetic */ ImmutableMap.Builder put(Object obj, Object obj2) {
            return put((Builder<K, V>) obj, obj2);
        }

        private Builder(Comparator<? super K> comparator, int i2) {
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
            this.keys = new Object[i2];
            this.values = new Object[i2];
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        public ImmutableSortedMap<K, V> build() {
            int i2 = this.size;
            if (i2 != 0) {
                if (i2 == 1) {
                    return ImmutableSortedMap.of(this.comparator, this.keys[0], this.values[0]);
                }
                Object[] copyOf = Arrays.copyOf(this.keys, i2);
                Arrays.sort(copyOf, this.comparator);
                Object[] objArr = new Object[this.size];
                for (int i3 = 0; i3 < this.size; i3++) {
                    if (i3 > 0) {
                        int i4 = i3 - 1;
                        if (this.comparator.compare(copyOf[i4], copyOf[i3]) == 0) {
                            throw new IllegalArgumentException("keys required to be distinct but compared as equal: " + copyOf[i4] + " and " + copyOf[i3]);
                        }
                    }
                    objArr[Arrays.binarySearch(copyOf, this.keys[i3], this.comparator)] = this.values[i3];
                }
                return new ImmutableSortedMap<>(new RegularImmutableSortedSet(ImmutableList.asImmutableList(copyOf), this.comparator), ImmutableList.asImmutableList(objArr));
            }
            return ImmutableSortedMap.emptyMap(this.comparator);
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        @Deprecated
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            throw new UnsupportedOperationException("Not available on ImmutableSortedMap.Builder");
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(K k2, V v) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(k2, v);
            Object[] objArr = this.keys;
            int i2 = this.size;
            objArr[i2] = k2;
            this.values[i2] = v;
            this.size = i2 + 1;
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            super.putAll((Map) map);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            super.putAll((Iterable) iterable);
            return this;
        }

        @Override // com.google.common.collect.ImmutableMap.Builder
        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            super.put((Map.Entry) entry);
            return this;
        }
    }

    /* loaded from: classes12.dex */
    private static class SerializedForm extends ImmutableMap.SerializedForm {
        private static final long serialVersionUID = 0;
        private final Comparator<Object> comparator;

        SerializedForm(ImmutableSortedMap<?, ?> immutableSortedMap) {
            super(immutableSortedMap);
            this.comparator = immutableSortedMap.comparator();
        }

        @Override // com.google.common.collect.ImmutableMap.SerializedForm
        Object readResolve() {
            return createMap(new Builder(this.comparator));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList) {
        this(regularImmutableSortedSet, immutableList, null);
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        return copyOfInternal(map, (Ordering) NATURAL_ORDER);
    }

    private static <K, V> ImmutableSortedMap<K, V> copyOfInternal(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        boolean z = false;
        if (map instanceof SortedMap) {
            Comparator<? super K> comparator2 = ((SortedMap) map).comparator();
            if (comparator2 == null) {
                if (comparator == NATURAL_ORDER) {
                    z = true;
                }
            } else {
                z = comparator.equals(comparator2);
            }
        }
        if (z && (map instanceof ImmutableSortedMap)) {
            ImmutableSortedMap<K, V> immutableSortedMap = (ImmutableSortedMap) map;
            if (!immutableSortedMap.isPartialView()) {
                return immutableSortedMap;
            }
        }
        return fromEntries(comparator, z, map.entrySet());
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOfSorted(SortedMap<K, ? extends V> sortedMap) {
        Comparator<? super K> comparator = sortedMap.comparator();
        if (comparator == null) {
            comparator = NATURAL_ORDER;
        }
        if (sortedMap instanceof ImmutableSortedMap) {
            ImmutableSortedMap<K, V> immutableSortedMap = (ImmutableSortedMap) sortedMap;
            if (!immutableSortedMap.isPartialView()) {
                return immutableSortedMap;
            }
        }
        return fromEntries(comparator, true, sortedMap.entrySet());
    }

    static <K, V> ImmutableSortedMap<K, V> emptyMap(Comparator<? super K> comparator) {
        if (Ordering.natural().equals(comparator)) {
            return of();
        }
        return new ImmutableSortedMap<>(ImmutableSortedSet.emptySet(comparator), ImmutableList.of());
    }

    private static <K, V> ImmutableSortedMap<K, V> fromEntries(Comparator<? super K> comparator, boolean z, Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Map.Entry[] entryArr = (Map.Entry[]) Iterables.toArray(iterable, ImmutableMap.EMPTY_ENTRY_ARRAY);
        return fromEntries(comparator, z, entryArr, entryArr.length);
    }

    private ImmutableSortedMap<K, V> getSubMap(int i2, int i3) {
        if (i2 == 0 && i3 == size()) {
            return this;
        }
        if (i2 == i3) {
            return emptyMap(comparator());
        }
        return new ImmutableSortedMap<>(this.keySet.getSubSet(i2, i3), this.valueList.subList(i2, i3));
    }

    public static <K extends Comparable<?>, V> Builder<K, V> naturalOrder() {
        return new Builder<>(Ordering.natural());
    }

    public static <K, V> ImmutableSortedMap<K, V> of() {
        return (ImmutableSortedMap<K, V>) NATURAL_EMPTY_MAP;
    }

    private static <K extends Comparable<? super K>, V> ImmutableSortedMap<K, V> ofEntries(Map.Entry<K, V>... entryArr) {
        return fromEntries(Ordering.natural(), false, entryArr, entryArr.length);
    }

    public static <K, V> Builder<K, V> orderedBy(Comparator<K> comparator) {
        return new Builder<>(comparator);
    }

    public static <K extends Comparable<?>, V> Builder<K, V> reverseOrder() {
        return new Builder<>(Ordering.natural().reverse());
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K k2) {
        return tailMap((ImmutableSortedMap<K, V>) k2, true).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K k2) {
        return (K) Maps.keyOrNull(ceilingEntry(k2));
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return keySet().comparator();
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return isEmpty() ? ImmutableSet.of() : new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableSortedMap.1EntrySet
            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.google.common.collect.ImmutableSet
            public ImmutableList<Map.Entry<K, V>> createAsList() {
                return new ImmutableList<Map.Entry<K, V>>() { // from class: com.google.common.collect.ImmutableSortedMap.1EntrySet.1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    @Override // com.google.common.collect.ImmutableCollection
                    public boolean isPartialView() {
                        return true;
                    }

                    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
                    public int size() {
                        return ImmutableSortedMap.this.size();
                    }

                    @Override // java.util.List
                    public Map.Entry<K, V> get(int i2) {
                        return new AbstractMap.SimpleImmutableEntry(ImmutableSortedMap.this.keySet.asList().get(i2), ImmutableSortedMap.this.valueList.get(i2));
                    }
                };
            }

            @Override // com.google.common.collect.ImmutableMapEntrySet
            ImmutableMap<K, V> map() {
                return ImmutableSortedMap.this;
            }

            @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
            public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                return asList().iterator();
            }
        };
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableSet<K> createKeySet() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.ImmutableMap
    ImmutableCollection<V> createValues() {
        throw new AssertionError("should never be called");
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(0);
    }

    @Override // java.util.SortedMap
    public K firstKey() {
        return keySet().first();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K k2) {
        return headMap((ImmutableSortedMap<K, V>) k2, true).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K floorKey(K k2) {
        return (K) Maps.keyOrNull(floorEntry(k2));
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public V get(@NullableDecl Object obj) {
        int indexOf = this.keySet.indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return this.valueList.get(indexOf);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap headMap(Object obj, boolean z) {
        return headMap((ImmutableSortedMap<K, V>) obj, z);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K k2) {
        return tailMap((ImmutableSortedMap<K, V>) k2, false).firstEntry();
    }

    @Override // java.util.NavigableMap
    public K higherKey(K k2) {
        return (K) Maps.keyOrNull(higherEntry(k2));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableMap
    public boolean isPartialView() {
        return this.keySet.isPartialView() || this.valueList.isPartialView();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        if (isEmpty()) {
            return null;
        }
        return entrySet().asList().get(size() - 1);
    }

    @Override // java.util.SortedMap
    public K lastKey() {
        return keySet().last();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K k2) {
        return headMap((ImmutableSortedMap<K, V>) k2, false).lastEntry();
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K k2) {
        return (K) Maps.keyOrNull(lowerEntry(k2));
    }

    @Override // java.util.NavigableMap
    @CanIgnoreReturnValue
    @Deprecated
    public final Map.Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableMap
    @CanIgnoreReturnValue
    @Deprecated
    public final Map.Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public int size() {
        return this.valueList.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap subMap(Object obj, boolean z, Object obj2, boolean z2) {
        return subMap((boolean) obj, z, (boolean) obj2, z2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public /* bridge */ /* synthetic */ NavigableMap tailMap(Object obj, boolean z) {
        return tailMap((ImmutableSortedMap<K, V>) obj, z);
    }

    @Override // com.google.common.collect.ImmutableMap
    Object writeReplace() {
        return new SerializedForm(this);
    }

    ImmutableSortedMap(RegularImmutableSortedSet<K> regularImmutableSortedSet, ImmutableList<V> immutableList, ImmutableSortedMap<K, V> immutableSortedMap) {
        this.keySet = regularImmutableSortedSet;
        this.valueList = immutableList;
        this.descendingMap = immutableSortedMap;
    }

    /* JADX WARN: Incorrect types in method signature: <K::Ljava/lang/Comparable<-TK;>;V:Ljava/lang/Object;>(TK;TV;)Lcom/google/common/collect/ImmutableSortedMap<TK;TV;>; */
    public static ImmutableSortedMap of(Comparable comparable, Object obj) {
        return of(Ordering.natural(), comparable, obj);
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> descendingKeySet() {
        return this.keySet.descendingSet();
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> descendingMap() {
        ImmutableSortedMap<K, V> immutableSortedMap = this.descendingMap;
        if (immutableSortedMap == null) {
            if (isEmpty()) {
                return emptyMap(Ordering.from(comparator()).reverse());
            }
            return new ImmutableSortedMap<>((RegularImmutableSortedSet) this.keySet.descendingSet(), this.valueList.reverse(), this);
        }
        return immutableSortedMap;
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        return super.entrySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, java.util.SortedMap
    public /* bridge */ /* synthetic */ SortedMap headMap(Object obj) {
        return headMap((ImmutableSortedMap<K, V>) obj);
    }

    @Override // java.util.NavigableMap
    public ImmutableSortedSet<K> navigableKeySet() {
        return this.keySet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap, java.util.SortedMap
    public /* bridge */ /* synthetic */ SortedMap tailMap(Object obj) {
        return tailMap((ImmutableSortedMap<K, V>) obj);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableCollection<V> values() {
        return this.valueList;
    }

    public static <K, V> ImmutableSortedMap<K, V> copyOf(Map<? extends K, ? extends V> map, Comparator<? super K> comparator) {
        return copyOfInternal(map, (Comparator) Preconditions.checkNotNull(comparator));
    }

    private static <K, V> ImmutableSortedMap<K, V> fromEntries(final Comparator<? super K> comparator, boolean z, Map.Entry<K, V>[] entryArr, int i2) {
        if (i2 != 0) {
            if (i2 != 1) {
                Object[] objArr = new Object[i2];
                Object[] objArr2 = new Object[i2];
                if (z) {
                    for (int i3 = 0; i3 < i2; i3++) {
                        K key = entryArr[i3].getKey();
                        V value = entryArr[i3].getValue();
                        CollectPreconditions.checkEntryNotNull(key, value);
                        objArr[i3] = key;
                        objArr2[i3] = value;
                    }
                } else {
                    Arrays.sort(entryArr, 0, i2, new Comparator<Map.Entry<K, V>>() { // from class: com.google.common.collect.ImmutableSortedMap.1
                        @Override // java.util.Comparator
                        public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                            return compare((Map.Entry) ((Map.Entry) obj), (Map.Entry) ((Map.Entry) obj2));
                        }

                        public int compare(Map.Entry<K, V> entry, Map.Entry<K, V> entry2) {
                            return comparator.compare(entry.getKey(), entry2.getKey());
                        }
                    });
                    Object key2 = entryArr[0].getKey();
                    objArr[0] = key2;
                    objArr2[0] = entryArr[0].getValue();
                    CollectPreconditions.checkEntryNotNull(objArr[0], objArr2[0]);
                    int i4 = 1;
                    while (i4 < i2) {
                        Object key3 = entryArr[i4].getKey();
                        V value2 = entryArr[i4].getValue();
                        CollectPreconditions.checkEntryNotNull(key3, value2);
                        objArr[i4] = key3;
                        objArr2[i4] = value2;
                        ImmutableMap.checkNoConflict(comparator.compare(key2, key3) != 0, "key", entryArr[i4 - 1], entryArr[i4]);
                        i4++;
                        key2 = key3;
                    }
                }
                return new ImmutableSortedMap<>(new RegularImmutableSortedSet(ImmutableList.asImmutableList(objArr), comparator), ImmutableList.asImmutableList(objArr2));
            }
            return of(comparator, entryArr[0].getKey(), entryArr[0].getValue());
        }
        return emptyMap(comparator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <K, V> ImmutableSortedMap<K, V> of(Comparator<? super K> comparator, K k2, V v) {
        return new ImmutableSortedMap<>(new RegularImmutableSortedSet(ImmutableList.of(k2), (Comparator) Preconditions.checkNotNull(comparator)), ImmutableList.of(v));
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> headMap(K k2) {
        return headMap((ImmutableSortedMap<K, V>) k2, false);
    }

    @Override // com.google.common.collect.ImmutableMap, java.util.Map
    public ImmutableSortedSet<K> keySet() {
        return this.keySet;
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> subMap(K k2, K k3) {
        return subMap((boolean) k2, true, (boolean) k3, false);
    }

    @Override // java.util.NavigableMap, java.util.SortedMap
    public ImmutableSortedMap<K, V> tailMap(K k2) {
        return tailMap((ImmutableSortedMap<K, V>) k2, true);
    }

    @Beta
    public static <K, V> ImmutableSortedMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        return copyOf(iterable, (Ordering) NATURAL_ORDER);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> headMap(K k2, boolean z) {
        return getSubMap(0, this.keySet.headIndex(Preconditions.checkNotNull(k2), z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> subMap(K k2, boolean z, K k3, boolean z2) {
        Preconditions.checkNotNull(k2);
        Preconditions.checkNotNull(k3);
        Preconditions.checkArgument(comparator().compare(k2, k3) <= 0, "expected fromKey <= toKey but %s > %s", k2, k3);
        return headMap((ImmutableSortedMap<K, V>) k3, z2).tailMap((ImmutableSortedMap<K, V>) k2, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.NavigableMap
    public ImmutableSortedMap<K, V> tailMap(K k2, boolean z) {
        return getSubMap(this.keySet.tailIndex(Preconditions.checkNotNull(k2), z), size());
    }

    @Beta
    public static <K, V> ImmutableSortedMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable, Comparator<? super K> comparator) {
        return fromEntries((Comparator) Preconditions.checkNotNull(comparator), false, iterable);
    }

    /* JADX WARN: Incorrect types in method signature: <K::Ljava/lang/Comparable<-TK;>;V:Ljava/lang/Object;>(TK;TV;TK;TV;)Lcom/google/common/collect/ImmutableSortedMap<TK;TV;>; */
    public static ImmutableSortedMap of(Comparable comparable, Object obj, Comparable comparable2, Object obj2) {
        return ofEntries(ImmutableMap.entryOf(comparable, obj), ImmutableMap.entryOf(comparable2, obj2));
    }

    /* JADX WARN: Incorrect types in method signature: <K::Ljava/lang/Comparable<-TK;>;V:Ljava/lang/Object;>(TK;TV;TK;TV;TK;TV;)Lcom/google/common/collect/ImmutableSortedMap<TK;TV;>; */
    public static ImmutableSortedMap of(Comparable comparable, Object obj, Comparable comparable2, Object obj2, Comparable comparable3, Object obj3) {
        return ofEntries(ImmutableMap.entryOf(comparable, obj), ImmutableMap.entryOf(comparable2, obj2), ImmutableMap.entryOf(comparable3, obj3));
    }

    /* JADX WARN: Incorrect types in method signature: <K::Ljava/lang/Comparable<-TK;>;V:Ljava/lang/Object;>(TK;TV;TK;TV;TK;TV;TK;TV;)Lcom/google/common/collect/ImmutableSortedMap<TK;TV;>; */
    public static ImmutableSortedMap of(Comparable comparable, Object obj, Comparable comparable2, Object obj2, Comparable comparable3, Object obj3, Comparable comparable4, Object obj4) {
        return ofEntries(ImmutableMap.entryOf(comparable, obj), ImmutableMap.entryOf(comparable2, obj2), ImmutableMap.entryOf(comparable3, obj3), ImmutableMap.entryOf(comparable4, obj4));
    }

    /* JADX WARN: Incorrect types in method signature: <K::Ljava/lang/Comparable<-TK;>;V:Ljava/lang/Object;>(TK;TV;TK;TV;TK;TV;TK;TV;TK;TV;)Lcom/google/common/collect/ImmutableSortedMap<TK;TV;>; */
    public static ImmutableSortedMap of(Comparable comparable, Object obj, Comparable comparable2, Object obj2, Comparable comparable3, Object obj3, Comparable comparable4, Object obj4, Comparable comparable5, Object obj5) {
        return ofEntries(ImmutableMap.entryOf(comparable, obj), ImmutableMap.entryOf(comparable2, obj2), ImmutableMap.entryOf(comparable3, obj3), ImmutableMap.entryOf(comparable4, obj4), ImmutableMap.entryOf(comparable5, obj5));
    }
}
