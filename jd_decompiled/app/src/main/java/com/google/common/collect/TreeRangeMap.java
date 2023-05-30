package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
@GwtIncompatible
/* loaded from: classes12.dex */
public final class TreeRangeMap<K extends Comparable, V> implements RangeMap<K, V> {
    private static final RangeMap EMPTY_SUB_RANGE_MAP = new RangeMap() { // from class: com.google.common.collect.TreeRangeMap.1
        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asDescendingMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range, Object> asMapOfRanges() {
            return Collections.emptyMap();
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Object get(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range, Object> getEntry(Comparable comparable) {
            return null;
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap rangeMap) {
            if (!rangeMap.asMapOfRanges().isEmpty()) {
                throw new IllegalArgumentException("Cannot putAll(nonEmptyRangeMap) into an empty subRangeMap");
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range range, Object obj) {
            Preconditions.checkNotNull(range);
            throw new IllegalArgumentException("Cannot insert range " + range + " into an empty subRangeMap");
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range range) {
            Preconditions.checkNotNull(range);
        }

        @Override // com.google.common.collect.RangeMap
        public Range span() {
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap subRangeMap(Range range) {
            Preconditions.checkNotNull(range);
            return this;
        }
    };
    private final NavigableMap<Cut<K>, RangeMapEntry<K, V>> entriesByLowerBound = Maps.newTreeMap();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class AsMapOfRanges extends Maps.IteratorBasedAbstractMap<Range<K>, V> {
        final Iterable<Map.Entry<Range<K>, V>> entryIterable;

        AsMapOfRanges(Iterable<RangeMapEntry<K, V>> iterable) {
            this.entryIterable = iterable;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap
        public Iterator<Map.Entry<Range<K>, V>> entryIterator() {
            return this.entryIterable.iterator();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public V get(@NullableDecl Object obj) {
            if (obj instanceof Range) {
                Range range = (Range) obj;
                RangeMapEntry rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                if (rangeMapEntry == null || !rangeMapEntry.getKey().equals(range)) {
                    return null;
                }
                return (V) rangeMapEntry.getValue();
            }
            return null;
        }

        @Override // com.google.common.collect.Maps.IteratorBasedAbstractMap, java.util.AbstractMap, java.util.Map
        public int size() {
            return TreeRangeMap.this.entriesByLowerBound.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
        private final Range<K> range;
        private final V value;

        RangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
            this(Range.create(cut, cut2), v);
        }

        public boolean contains(K k2) {
            return this.range.contains(k2);
        }

        Cut<K> getLowerBound() {
            return this.range.lowerBound;
        }

        Cut<K> getUpperBound() {
            return this.range.upperBound;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public V getValue() {
            return this.value;
        }

        RangeMapEntry(Range<K> range, V v) {
            this.range = range;
            this.value = v;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public Range<K> getKey() {
            return this.range;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SubRangeMap implements RangeMap<K, V> {
        private final Range<K> subRange;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes12.dex */
        public class SubRangeMapAsMap extends AbstractMap<Range<K>, V> {
            SubRangeMapAsMap() {
            }

            /* JADX INFO: Access modifiers changed from: private */
            public boolean removeEntryIf(Predicate<? super Map.Entry<Range<K>, V>> predicate) {
                ArrayList newArrayList = Lists.newArrayList();
                for (Map.Entry<Range<K>, V> entry : entrySet()) {
                    if (predicate.apply(entry)) {
                        newArrayList.add(entry.getKey());
                    }
                }
                Iterator it = newArrayList.iterator();
                while (it.hasNext()) {
                    TreeRangeMap.this.remove((Range) it.next());
                }
                return !newArrayList.isEmpty();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public void clear() {
                SubRangeMap.this.clear();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public boolean containsKey(Object obj) {
                return get(obj) != null;
            }

            Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                if (!SubRangeMap.this.subRange.isEmpty()) {
                    TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) MoreObjects.firstNonNull(TreeRangeMap.this.entriesByLowerBound.floorKey(SubRangeMap.this.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator();
                    return new AbstractIterator<Map.Entry<Range<K>, V>>
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x004d: RETURN 
                          (wrap: java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K extends java.lang.Comparable>, V>> : CONSTRUCTOR 
                          (r3v0 'this' com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap A[IMMUTABLE_TYPE, THIS])
                          (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                         A[MD:(com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap, java.util.Iterator):void (m), WRAPPED] (LINE:8) call: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.3.<init>(com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap, java.util.Iterator):void type: CONSTRUCTOR)
                         (LINE:8) in method: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.entryIterator():java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K extends java.lang.Comparable>, V>>, file: classes12.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:80)
                        	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:123)
                        	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                        */
                    /*
                        this = this;
                        com.google.common.collect.TreeRangeMap$SubRangeMap r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                        com.google.common.collect.Range r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.access$300(r0)
                        boolean r0 = r0.isEmpty()
                        if (r0 == 0) goto L11
                        com.google.common.collect.UnmodifiableIterator r0 = com.google.common.collect.Iterators.emptyIterator()
                        return r0
                    L11:
                        com.google.common.collect.TreeRangeMap$SubRangeMap r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                        com.google.common.collect.TreeRangeMap r0 = com.google.common.collect.TreeRangeMap.this
                        java.util.NavigableMap r0 = com.google.common.collect.TreeRangeMap.access$000(r0)
                        com.google.common.collect.TreeRangeMap$SubRangeMap r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                        com.google.common.collect.Range r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.access$300(r1)
                        com.google.common.collect.Cut<C extends java.lang.Comparable> r1 = r1.lowerBound
                        java.lang.Object r0 = r0.floorKey(r1)
                        com.google.common.collect.TreeRangeMap$SubRangeMap r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                        com.google.common.collect.Range r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.access$300(r1)
                        com.google.common.collect.Cut<C extends java.lang.Comparable> r1 = r1.lowerBound
                        java.lang.Object r0 = com.google.common.base.MoreObjects.firstNonNull(r0, r1)
                        com.google.common.collect.Cut r0 = (com.google.common.collect.Cut) r0
                        com.google.common.collect.TreeRangeMap$SubRangeMap r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                        com.google.common.collect.TreeRangeMap r1 = com.google.common.collect.TreeRangeMap.this
                        java.util.NavigableMap r1 = com.google.common.collect.TreeRangeMap.access$000(r1)
                        r2 = 1
                        java.util.NavigableMap r0 = r1.tailMap(r0, r2)
                        java.util.Collection r0 = r0.values()
                        java.util.Iterator r0 = r0.iterator()
                        com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap$3 r1 = new com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap$3
                        r1.<init>()
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.entryIterator():java.util.Iterator");
                }

                @Override // java.util.AbstractMap, java.util.Map
                public Set<Map.Entry<Range<K>, V>> entrySet() {
                    return new Maps.EntrySet<Range<K>, V>() { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.2
                        @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                        public boolean isEmpty() {
                            return !iterator().hasNext();
                        }

                        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                        public Iterator<Map.Entry<Range<K>, V>> iterator() {
                            return SubRangeMapAsMap.this.entryIterator();
                        }

                        @Override // com.google.common.collect.Maps.EntrySet
                        Map<Range<K>, V> map() {
                            return SubRangeMapAsMap.this;
                        }

                        @Override // com.google.common.collect.Maps.EntrySet, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                        public boolean retainAll(Collection<?> collection) {
                            return SubRangeMapAsMap.this.removeEntryIf(Predicates.not(Predicates.in(collection)));
                        }

                        @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                        public int size() {
                            return Iterators.size(iterator());
                        }
                    };
                }

                @Override // java.util.AbstractMap, java.util.Map
                public V get(Object obj) {
                    RangeMapEntry rangeMapEntry;
                    try {
                        if (obj instanceof Range) {
                            Range range = (Range) obj;
                            if (SubRangeMap.this.subRange.encloses(range) && !range.isEmpty()) {
                                if (range.lowerBound.compareTo((Cut) SubRangeMap.this.subRange.lowerBound) == 0) {
                                    Map.Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.lowerBound);
                                    rangeMapEntry = floorEntry != null ? (RangeMapEntry) floorEntry.getValue() : null;
                                } else {
                                    rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                                }
                                if (rangeMapEntry != null && rangeMapEntry.getKey().isConnected(SubRangeMap.this.subRange) && rangeMapEntry.getKey().intersection(SubRangeMap.this.subRange).equals(range)) {
                                    return (V) rangeMapEntry.getValue();
                                }
                            }
                        }
                    } catch (ClassCastException unused) {
                    }
                    return null;
                }

                @Override // java.util.AbstractMap, java.util.Map
                public Set<Range<K>> keySet() {
                    return new Maps.KeySet<Range<K>, V>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.1
                        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                        public boolean remove(@NullableDecl Object obj) {
                            return SubRangeMapAsMap.this.remove(obj) != null;
                        }

                        @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                        public boolean retainAll(Collection<?> collection) {
                            return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.keyFunction()));
                        }
                    };
                }

                @Override // java.util.AbstractMap, java.util.Map
                public V remove(Object obj) {
                    V v = (V) get(obj);
                    if (v != null) {
                        TreeRangeMap.this.remove((Range) obj);
                        return v;
                    }
                    return null;
                }

                @Override // java.util.AbstractMap, java.util.Map
                public Collection<V> values() {
                    return new Maps.Values<Range<K>, V>(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.4
                        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                        public boolean removeAll(Collection<?> collection) {
                            return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.in(collection), Maps.valueFunction()));
                        }

                        @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                        public boolean retainAll(Collection<?> collection) {
                            return SubRangeMapAsMap.this.removeEntryIf(Predicates.compose(Predicates.not(Predicates.in(collection)), Maps.valueFunction()));
                        }
                    };
                }
            }

            SubRangeMap(Range<K> range) {
                this.subRange = range;
            }

            @Override // com.google.common.collect.RangeMap
            public Map<Range<K>, V> asDescendingMapOfRanges() {
                return new TreeRangeMap<K, V>.SubRangeMap.SubRangeMapAsMap() { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.1
                    @Override // com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap
                    Iterator<Map.Entry<Range<K>, V>> entryIterator() {
                        if (SubRangeMap.this.subRange.isEmpty()) {
                            return Iterators.emptyIterator();
                        }
                        TreeRangeMap.this.entriesByLowerBound.headMap(SubRangeMap.this.subRange.upperBound, false).descendingMap().values().iterator();
                        return new AbstractIterator<Map.Entry<Range<K>, V>>
                        /*  JADX ERROR: Method code generation error
                            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0037: RETURN 
                              (wrap: java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K extends java.lang.Comparable>, V>> : CONSTRUCTOR 
                              (r3v0 'this' com.google.common.collect.TreeRangeMap$SubRangeMap$1 A[IMMUTABLE_TYPE, THIS])
                              (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
                             A[MD:(com.google.common.collect.TreeRangeMap$SubRangeMap$1, java.util.Iterator):void (m), WRAPPED] (LINE:9) call: com.google.common.collect.TreeRangeMap.SubRangeMap.1.1.<init>(com.google.common.collect.TreeRangeMap$SubRangeMap$1, java.util.Iterator):void type: CONSTRUCTOR)
                             (LINE:9) in method: com.google.common.collect.TreeRangeMap.SubRangeMap.1.entryIterator():java.util.Iterator<java.util.Map$Entry<com.google.common.collect.Range<K extends java.lang.Comparable>, V>>, file: classes12.dex
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:309)
                            	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:272)
                            	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:91)
                            	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
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
                            */
                        /*
                            this = this;
                            com.google.common.collect.TreeRangeMap$SubRangeMap r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                            com.google.common.collect.Range r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.access$300(r0)
                            boolean r0 = r0.isEmpty()
                            if (r0 == 0) goto L11
                            com.google.common.collect.UnmodifiableIterator r0 = com.google.common.collect.Iterators.emptyIterator()
                            return r0
                        L11:
                            com.google.common.collect.TreeRangeMap$SubRangeMap r0 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                            com.google.common.collect.TreeRangeMap r0 = com.google.common.collect.TreeRangeMap.this
                            java.util.NavigableMap r0 = com.google.common.collect.TreeRangeMap.access$000(r0)
                            com.google.common.collect.TreeRangeMap$SubRangeMap r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.this
                            com.google.common.collect.Range r1 = com.google.common.collect.TreeRangeMap.SubRangeMap.access$300(r1)
                            com.google.common.collect.Cut<C extends java.lang.Comparable> r1 = r1.upperBound
                            r2 = 0
                            java.util.NavigableMap r0 = r0.headMap(r1, r2)
                            java.util.NavigableMap r0 = r0.descendingMap()
                            java.util.Collection r0 = r0.values()
                            java.util.Iterator r0 = r0.iterator()
                            com.google.common.collect.TreeRangeMap$SubRangeMap$1$1 r1 = new com.google.common.collect.TreeRangeMap$SubRangeMap$1$1
                            r1.<init>()
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.TreeRangeMap.SubRangeMap.AnonymousClass1.entryIterator():java.util.Iterator");
                    }
                };
            }

            @Override // com.google.common.collect.RangeMap
            public Map<Range<K>, V> asMapOfRanges() {
                return new SubRangeMapAsMap();
            }

            @Override // com.google.common.collect.RangeMap
            public void clear() {
                TreeRangeMap.this.remove(this.subRange);
            }

            @Override // com.google.common.collect.RangeMap
            public boolean equals(@NullableDecl Object obj) {
                if (obj instanceof RangeMap) {
                    return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
                }
                return false;
            }

            @Override // com.google.common.collect.RangeMap
            @NullableDecl
            public V get(K k2) {
                if (this.subRange.contains(k2)) {
                    return (V) TreeRangeMap.this.get(k2);
                }
                return null;
            }

            @Override // com.google.common.collect.RangeMap
            @NullableDecl
            public Map.Entry<Range<K>, V> getEntry(K k2) {
                Map.Entry<Range<K>, V> entry;
                if (!this.subRange.contains(k2) || (entry = TreeRangeMap.this.getEntry(k2)) == null) {
                    return null;
                }
                return Maps.immutableEntry(entry.getKey().intersection(this.subRange), entry.getValue());
            }

            @Override // com.google.common.collect.RangeMap
            public int hashCode() {
                return asMapOfRanges().hashCode();
            }

            @Override // com.google.common.collect.RangeMap
            public void put(Range<K> range, V v) {
                Preconditions.checkArgument(this.subRange.encloses(range), "Cannot put range %s into a subRangeMap(%s)", range, this.subRange);
                TreeRangeMap.this.put(range, v);
            }

            @Override // com.google.common.collect.RangeMap
            public void putAll(RangeMap<K, V> rangeMap) {
                if (rangeMap.asMapOfRanges().isEmpty()) {
                    return;
                }
                Range<K> span = rangeMap.span();
                Preconditions.checkArgument(this.subRange.encloses(span), "Cannot putAll rangeMap with span %s into a subRangeMap(%s)", span, this.subRange);
                TreeRangeMap.this.putAll(rangeMap);
            }

            @Override // com.google.common.collect.RangeMap
            public void putCoalescing(Range<K> range, V v) {
                if (!TreeRangeMap.this.entriesByLowerBound.isEmpty() && !range.isEmpty() && this.subRange.encloses(range)) {
                    put(TreeRangeMap.this.coalescedRange(range, Preconditions.checkNotNull(v)).intersection(this.subRange), v);
                } else {
                    put(range, v);
                }
            }

            @Override // com.google.common.collect.RangeMap
            public void remove(Range<K> range) {
                if (range.isConnected(this.subRange)) {
                    TreeRangeMap.this.remove(range.intersection(this.subRange));
                }
            }

            @Override // com.google.common.collect.RangeMap
            public Range<K> span() {
                Cut<K> cut;
                Cut<K> upperBound;
                Map.Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(this.subRange.lowerBound);
                if (floorEntry == null || ((RangeMapEntry) floorEntry.getValue()).getUpperBound().compareTo(this.subRange.lowerBound) <= 0) {
                    cut = (Cut) TreeRangeMap.this.entriesByLowerBound.ceilingKey(this.subRange.lowerBound);
                    if (cut == null || cut.compareTo(this.subRange.upperBound) >= 0) {
                        throw new NoSuchElementException();
                    }
                } else {
                    cut = this.subRange.lowerBound;
                }
                Map.Entry lowerEntry = TreeRangeMap.this.entriesByLowerBound.lowerEntry(this.subRange.upperBound);
                if (lowerEntry != null) {
                    if (((RangeMapEntry) lowerEntry.getValue()).getUpperBound().compareTo(this.subRange.upperBound) >= 0) {
                        upperBound = this.subRange.upperBound;
                    } else {
                        upperBound = ((RangeMapEntry) lowerEntry.getValue()).getUpperBound();
                    }
                    return Range.create(cut, upperBound);
                }
                throw new NoSuchElementException();
            }

            @Override // com.google.common.collect.RangeMap
            public RangeMap<K, V> subRangeMap(Range<K> range) {
                if (!range.isConnected(this.subRange)) {
                    return TreeRangeMap.this.emptySubRangeMap();
                }
                return TreeRangeMap.this.subRangeMap(range.intersection(this.subRange));
            }

            @Override // com.google.common.collect.RangeMap
            public String toString() {
                return asMapOfRanges().toString();
            }
        }

        private TreeRangeMap() {
        }

        private static <K extends Comparable, V> Range<K> coalesce(Range<K> range, V v, @NullableDecl Map.Entry<Cut<K>, RangeMapEntry<K, V>> entry) {
            return (entry != null && entry.getValue().getKey().isConnected(range) && entry.getValue().getValue().equals(v)) ? range.span(entry.getValue().getKey()) : range;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Range<K> coalescedRange(Range<K> range, V v) {
            return coalesce(coalesce(range, v, this.entriesByLowerBound.lowerEntry(range.lowerBound)), v, this.entriesByLowerBound.floorEntry(range.upperBound));
        }

        public static <K extends Comparable, V> TreeRangeMap<K, V> create() {
            return new TreeRangeMap<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public RangeMap<K, V> emptySubRangeMap() {
            return EMPTY_SUB_RANGE_MAP;
        }

        private void putRangeMapEntry(Cut<K> cut, Cut<K> cut2, V v) {
            this.entriesByLowerBound.put(cut, new RangeMapEntry(cut, cut2, v));
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asDescendingMapOfRanges() {
            return new AsMapOfRanges(this.entriesByLowerBound.descendingMap().values());
        }

        @Override // com.google.common.collect.RangeMap
        public Map<Range<K>, V> asMapOfRanges() {
            return new AsMapOfRanges(this.entriesByLowerBound.values());
        }

        @Override // com.google.common.collect.RangeMap
        public void clear() {
            this.entriesByLowerBound.clear();
        }

        @Override // com.google.common.collect.RangeMap
        public boolean equals(@NullableDecl Object obj) {
            if (obj instanceof RangeMap) {
                return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
            }
            return false;
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public V get(K k2) {
            Map.Entry<Range<K>, V> entry = getEntry(k2);
            if (entry == null) {
                return null;
            }
            return entry.getValue();
        }

        @Override // com.google.common.collect.RangeMap
        @NullableDecl
        public Map.Entry<Range<K>, V> getEntry(K k2) {
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> floorEntry = this.entriesByLowerBound.floorEntry(Cut.belowValue(k2));
            if (floorEntry == null || !floorEntry.getValue().contains(k2)) {
                return null;
            }
            return floorEntry.getValue();
        }

        @Override // com.google.common.collect.RangeMap
        public int hashCode() {
            return asMapOfRanges().hashCode();
        }

        @Override // com.google.common.collect.RangeMap
        public void put(Range<K> range, V v) {
            if (range.isEmpty()) {
                return;
            }
            Preconditions.checkNotNull(v);
            remove(range);
            this.entriesByLowerBound.put(range.lowerBound, new RangeMapEntry(range, v));
        }

        @Override // com.google.common.collect.RangeMap
        public void putAll(RangeMap<K, V> rangeMap) {
            for (Map.Entry<Range<K>, V> entry : rangeMap.asMapOfRanges().entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.RangeMap
        public void putCoalescing(Range<K> range, V v) {
            if (this.entriesByLowerBound.isEmpty()) {
                put(range, v);
            } else {
                put(coalescedRange(range, Preconditions.checkNotNull(v)), v);
            }
        }

        @Override // com.google.common.collect.RangeMap
        public void remove(Range<K> range) {
            if (range.isEmpty()) {
                return;
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry = this.entriesByLowerBound.lowerEntry(range.lowerBound);
            if (lowerEntry != null) {
                RangeMapEntry<K, V> value = lowerEntry.getValue();
                if (value.getUpperBound().compareTo(range.lowerBound) > 0) {
                    if (value.getUpperBound().compareTo(range.upperBound) > 0) {
                        putRangeMapEntry(range.upperBound, value.getUpperBound(), lowerEntry.getValue().getValue());
                    }
                    putRangeMapEntry(value.getLowerBound(), range.lowerBound, lowerEntry.getValue().getValue());
                }
            }
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lowerEntry2 = this.entriesByLowerBound.lowerEntry(range.upperBound);
            if (lowerEntry2 != null) {
                RangeMapEntry<K, V> value2 = lowerEntry2.getValue();
                if (value2.getUpperBound().compareTo(range.upperBound) > 0) {
                    putRangeMapEntry(range.upperBound, value2.getUpperBound(), lowerEntry2.getValue().getValue());
                }
            }
            this.entriesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
        }

        @Override // com.google.common.collect.RangeMap
        public Range<K> span() {
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> firstEntry = this.entriesByLowerBound.firstEntry();
            Map.Entry<Cut<K>, RangeMapEntry<K, V>> lastEntry = this.entriesByLowerBound.lastEntry();
            if (firstEntry != null) {
                return Range.create(firstEntry.getValue().getKey().lowerBound, lastEntry.getValue().getKey().upperBound);
            }
            throw new NoSuchElementException();
        }

        @Override // com.google.common.collect.RangeMap
        public RangeMap<K, V> subRangeMap(Range<K> range) {
            return range.equals(Range.all()) ? this : new SubRangeMap(range);
        }

        @Override // com.google.common.collect.RangeMap
        public String toString() {
            return this.entriesByLowerBound.values().toString();
        }
    }
