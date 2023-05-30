package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    static final Map.Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Map.Entry[0];
    @LazyInit
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    @LazyInit
    private transient ImmutableSet<K> keySet;
    @LazyInit
    private transient ImmutableSetMultimap<K, V> multimapView;
    @LazyInit
    private transient ImmutableCollection<V> values;

    /* loaded from: classes12.dex */
    public static class Builder<K, V> {
        Object[] alternatingKeysAndValues;
        boolean entriesUsed;
        int size;
        Comparator<? super V> valueComparator;

        public Builder() {
            this(4);
        }

        private void ensureCapacity(int i2) {
            int i3 = i2 * 2;
            Object[] objArr = this.alternatingKeysAndValues;
            if (i3 > objArr.length) {
                this.alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i3));
                this.entriesUsed = false;
            }
        }

        public ImmutableMap<K, V> build() {
            sortEntries();
            this.entriesUsed = true;
            return RegularImmutableMap.create(this.size, this.alternatingKeysAndValues);
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> orderEntriesByValue(Comparator<? super V> comparator) {
            Preconditions.checkState(this.valueComparator == null, "valueComparator was already set");
            this.valueComparator = (Comparator) Preconditions.checkNotNull(comparator, "valueComparator");
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(K k2, V v) {
            ensureCapacity(this.size + 1);
            CollectPreconditions.checkEntryNotNull(k2, v);
            Object[] objArr = this.alternatingKeysAndValues;
            int i2 = this.size;
            objArr[i2 * 2] = k2;
            objArr[(i2 * 2) + 1] = v;
            this.size = i2 + 1;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
            return putAll(map.entrySet());
        }

        public void sortEntries() {
            int i2;
            if (this.valueComparator != null) {
                if (this.entriesUsed) {
                    this.alternatingKeysAndValues = Arrays.copyOf(this.alternatingKeysAndValues, this.size * 2);
                }
                Map.Entry[] entryArr = new Map.Entry[this.size];
                int i3 = 0;
                while (true) {
                    i2 = this.size;
                    if (i3 >= i2) {
                        break;
                    }
                    Object[] objArr = this.alternatingKeysAndValues;
                    int i4 = i3 * 2;
                    entryArr[i3] = new AbstractMap.SimpleImmutableEntry(objArr[i4], objArr[i4 + 1]);
                    i3++;
                }
                Arrays.sort(entryArr, 0, i2, Ordering.from(this.valueComparator).onResultOf(Maps.valueFunction()));
                for (int i5 = 0; i5 < this.size; i5++) {
                    int i6 = i5 * 2;
                    this.alternatingKeysAndValues[i6] = entryArr[i5].getKey();
                    this.alternatingKeysAndValues[i6 + 1] = entryArr[i5].getValue();
                }
            }
        }

        public Builder(int i2) {
            this.alternatingKeysAndValues = new Object[i2 * 2];
            this.size = 0;
            this.entriesUsed = false;
        }

        @CanIgnoreReturnValue
        @Beta
        public Builder<K, V> putAll(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
            if (iterable instanceof Collection) {
                ensureCapacity(this.size + ((Collection) iterable).size());
            }
            Iterator<? extends Map.Entry<? extends K, ? extends V>> it = iterable.iterator();
            while (it.hasNext()) {
                put(it.next());
            }
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<K, V> put(Map.Entry<? extends K, ? extends V> entry) {
            return put(entry.getKey(), entry.getValue());
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class IteratorBasedImmutableMap<K, V> extends ImmutableMap<K, V> {
        @Override // com.google.common.collect.ImmutableMap
        ImmutableSet<Map.Entry<K, V>> createEntrySet() {
            return new ImmutableMapEntrySet<K, V>() { // from class: com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap.1EntrySetImpl
                {
                    IteratorBasedImmutableMap.this = this;
                }

                @Override // com.google.common.collect.ImmutableMapEntrySet
                ImmutableMap<K, V> map() {
                    return IteratorBasedImmutableMap.this;
                }

                @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
                public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
                    return IteratorBasedImmutableMap.this.entryIterator();
                }
            };
        }

        @Override // com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return new ImmutableMapKeySet(this);
        }

        @Override // com.google.common.collect.ImmutableMap
        ImmutableCollection<V> createValues() {
            return new ImmutableMapValues(this);
        }

        abstract UnmodifiableIterator<Map.Entry<K, V>> entryIterator();

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set entrySet() {
            return super.entrySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Set keySet() {
            return super.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public /* bridge */ /* synthetic */ Collection values() {
            return super.values();
        }
    }

    /* loaded from: classes12.dex */
    public final class MapViewOfValuesAsSingletonSets extends IteratorBasedImmutableMap<K, ImmutableSet<V>> {
        private MapViewOfValuesAsSingletonSets() {
            ImmutableMap.this = r1;
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return ImmutableMap.this.containsKey(obj);
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap, com.google.common.collect.ImmutableMap
        public ImmutableSet<K> createKeySet() {
            return ImmutableMap.this.keySet();
        }

        @Override // com.google.common.collect.ImmutableMap.IteratorBasedImmutableMap
        UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>> entryIterator() {
            ImmutableMap.this.entrySet().iterator();
            return new UnmodifiableIterator<Map.Entry<K, ImmutableSet<V>>>
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: RETURN 
                  (wrap: com.google.common.collect.UnmodifiableIterator<java.util.Map$Entry<K, com.google.common.collect.ImmutableSet<V>>> : 0x000c: CONSTRUCTOR 
                  (r2v0 'this' com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets A[IMMUTABLE_TYPE, THIS])
                  (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                 A[MD:(com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets, java.util.Iterator):void (m), WRAPPED] (LINE:2) call: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.1.<init>(com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets, java.util.Iterator):void type: CONSTRUCTOR)
                 (LINE:2) in method: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.entryIterator():com.google.common.collect.UnmodifiableIterator<java.util.Map$Entry<K, com.google.common.collect.ImmutableSet<V>>>, file: classes12.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.dex.regions.Region.generate(Region.java:35)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
                	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
                	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                Caused by: java.lang.NullPointerException
                	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
                	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
                	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
                	... 15 more
                */
            /*
                this = this;
                com.google.common.collect.ImmutableMap r0 = com.google.common.collect.ImmutableMap.this
                com.google.common.collect.ImmutableSet r0 = r0.entrySet()
                com.google.common.collect.UnmodifiableIterator r0 = r0.iterator()
                com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets$1 r1 = new com.google.common.collect.ImmutableMap$MapViewOfValuesAsSingletonSets$1
                r1.<init>()
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableMap.MapViewOfValuesAsSingletonSets.entryIterator():com.google.common.collect.UnmodifiableIterator");
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public int hashCode() {
            return ImmutableMap.this.hashCode();
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isHashCodeFast() {
            return ImmutableMap.this.isHashCodeFast();
        }

        @Override // com.google.common.collect.ImmutableMap
        public boolean isPartialView() {
            return ImmutableMap.this.isPartialView();
        }

        @Override // java.util.Map
        public int size() {
            return ImmutableMap.this.size();
        }

        @Override // com.google.common.collect.ImmutableMap, java.util.Map
        public ImmutableSet<V> get(@NullableDecl Object obj) {
            Object obj2 = ImmutableMap.this.get(obj);
            if (obj2 == null) {
                return null;
            }
            return ImmutableSet.of(obj2);
        }
    }

    /* loaded from: classes12.dex */
    public static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object[] keys;
        private final Object[] values;

        public SerializedForm(ImmutableMap<?, ?> immutableMap) {
            this.keys = new Object[immutableMap.size()];
            this.values = new Object[immutableMap.size()];
            UnmodifiableIterator<Map.Entry<?, ?>> it = immutableMap.entrySet().iterator();
            int i2 = 0;
            while (it.hasNext()) {
                Map.Entry<?, ?> next = it.next();
                this.keys[i2] = next.getKey();
                this.values[i2] = next.getValue();
                i2++;
            }
        }

        public Object createMap(Builder<Object, Object> builder) {
            int i2 = 0;
            while (true) {
                Object[] objArr = this.keys;
                if (i2 < objArr.length) {
                    builder.put(objArr[i2], this.values[i2]);
                    i2++;
                } else {
                    return builder.build();
                }
            }
        }

        Object readResolve() {
            return createMap(new Builder<>(this.keys.length));
        }
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    @Beta
    public static <K, V> Builder<K, V> builderWithExpectedSize(int i2) {
        CollectPreconditions.checkNonnegative(i2, "expectedSize");
        return new Builder<>(i2);
    }

    public static void checkNoConflict(boolean z, String str, Map.Entry<?, ?> entry, Map.Entry<?, ?> entry2) {
        if (z) {
            return;
        }
        throw new IllegalArgumentException("Multiple entries with same " + str + ": " + entry + " and " + entry2);
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof SortedMap)) {
            ImmutableMap<K, V> immutableMap = (ImmutableMap) map;
            if (!immutableMap.isPartialView()) {
                return immutableMap;
            }
        }
        return copyOf(map.entrySet());
    }

    public static <K, V> Map.Entry<K, V> entryOf(K k2, V v) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        return new AbstractMap.SimpleImmutableEntry(k2, v);
    }

    public static <K, V> ImmutableMap<K, V> of() {
        return (ImmutableMap<K, V>) RegularImmutableMap.EMPTY;
    }

    public ImmutableSetMultimap<K, V> asMultimap() {
        if (isEmpty()) {
            return ImmutableSetMultimap.of();
        }
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap == null) {
            ImmutableSetMultimap<K, V> immutableSetMultimap2 = new ImmutableSetMultimap<>(new MapViewOfValuesAsSingletonSets(), size(), null);
            this.multimapView = immutableSetMultimap2;
            return immutableSetMultimap2;
        }
        return immutableSetMultimap;
    }

    @Override // java.util.Map
    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public boolean containsKey(@NullableDecl Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public boolean containsValue(@NullableDecl Object obj) {
        return values().contains(obj);
    }

    abstract ImmutableSet<Map.Entry<K, V>> createEntrySet();

    abstract ImmutableSet<K> createKeySet();

    abstract ImmutableCollection<V> createValues();

    @Override // java.util.Map
    public boolean equals(@NullableDecl Object obj) {
        return Maps.equalsImpl(this, obj);
    }

    @Override // java.util.Map
    public abstract V get(@NullableDecl Object obj);

    @Override // java.util.Map
    public final V getOrDefault(@NullableDecl Object obj, @NullableDecl V v) {
        V v2 = get(obj);
        return v2 != null ? v2 : v;
    }

    @Override // java.util.Map
    public int hashCode() {
        return Sets.hashCodeImpl(entrySet());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isHashCodeFast() {
        return false;
    }

    public abstract boolean isPartialView();

    public UnmodifiableIterator<K> keyIterator() {
        entrySet().iterator();
        return new UnmodifiableIterator<K>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000d: RETURN 
              (wrap: com.google.common.collect.UnmodifiableIterator<K> : 0x000a: CONSTRUCTOR 
              (r2v0 'this' com.google.common.collect.ImmutableMap<K, V> A[IMMUTABLE_TYPE, THIS])
              (r0 I:com.google.common.collect.UnmodifiableIterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.google.common.collect.ImmutableMap, com.google.common.collect.UnmodifiableIterator):void (m), WRAPPED] (LINE:2) call: com.google.common.collect.ImmutableMap.1.<init>(com.google.common.collect.ImmutableMap, com.google.common.collect.UnmodifiableIterator):void type: CONSTRUCTOR)
             (LINE:2) in method: com.google.common.collect.ImmutableMap.keyIterator():com.google.common.collect.UnmodifiableIterator<K>, file: classes12.dex
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.dex.regions.Region.generate(Region.java:35)
            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:297)
            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:276)
            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:382)
            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:277)
            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1541)
            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
            Caused by: java.lang.NullPointerException
            	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:798)
            	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:718)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:417)
            	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:144)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:120)
            	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
            	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:367)
            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:302)
            	... 15 more
            */
        /*
            this = this;
            com.google.common.collect.ImmutableSet r0 = r2.entrySet()
            com.google.common.collect.UnmodifiableIterator r0 = r0.iterator()
            com.google.common.collect.ImmutableMap$1 r1 = new com.google.common.collect.ImmutableMap$1
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.ImmutableMap.keyIterator():com.google.common.collect.UnmodifiableIterator");
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V put(K k2, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    @CanIgnoreReturnValue
    @Deprecated
    public final V remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    Object writeReplace() {
        return new SerializedForm(this);
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        return RegularImmutableMap.create(1, new Object[]{k2, v});
    }

    @Override // java.util.Map
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet == null) {
            ImmutableSet<Map.Entry<K, V>> createEntrySet = createEntrySet();
            this.entrySet = createEntrySet;
            return createEntrySet;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet == null) {
            ImmutableSet<K> createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }
        return immutableSet;
    }

    @Override // java.util.Map
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection == null) {
            ImmutableCollection<V> createValues = createValues();
            this.values = createValues;
            return createValues;
        }
        return immutableCollection;
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        CollectPreconditions.checkEntryNotNull(k3, v2);
        return RegularImmutableMap.create(2, new Object[]{k2, v, k3, v2});
    }

    @Beta
    public static <K, V> ImmutableMap<K, V> copyOf(Iterable<? extends Map.Entry<? extends K, ? extends V>> iterable) {
        Builder builder = new Builder(iterable instanceof Collection ? ((Collection) iterable).size() : 4);
        builder.putAll(iterable);
        return builder.build();
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        CollectPreconditions.checkEntryNotNull(k3, v2);
        CollectPreconditions.checkEntryNotNull(k4, v3);
        return RegularImmutableMap.create(3, new Object[]{k2, v, k3, v2, k4, v3});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        CollectPreconditions.checkEntryNotNull(k3, v2);
        CollectPreconditions.checkEntryNotNull(k4, v3);
        CollectPreconditions.checkEntryNotNull(k5, v4);
        return RegularImmutableMap.create(4, new Object[]{k2, v, k3, v2, k4, v3, k5, v4});
    }

    public static <K, V> ImmutableMap<K, V> of(K k2, V v, K k3, V v2, K k4, V v3, K k5, V v4, K k6, V v5) {
        CollectPreconditions.checkEntryNotNull(k2, v);
        CollectPreconditions.checkEntryNotNull(k3, v2);
        CollectPreconditions.checkEntryNotNull(k4, v3);
        CollectPreconditions.checkEntryNotNull(k5, v4);
        CollectPreconditions.checkEntryNotNull(k6, v5);
        return RegularImmutableMap.create(5, new Object[]{k2, v, k3, v2, k4, v3, k5, v4, k6, v5});
    }
}
