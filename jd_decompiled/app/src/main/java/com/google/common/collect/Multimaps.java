package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Sets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class Multimaps {

    /* loaded from: classes12.dex */
    public static final class AsMap<K, V> extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        @Weak
        private final Multimap<K, V> multimap;

        /* loaded from: classes12.dex */
        public class EntrySet extends Maps.EntrySet<K, Collection<V>> {
            EntrySet() {
                AsMap.this = r1;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return Maps.asMapEntryIterator(AsMap.this.multimap.keySet(), new Function<K, Collection<V>>() { // from class: com.google.common.collect.Multimaps.AsMap.EntrySet.1
                    {
                        EntrySet.this = this;
                    }

                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((AnonymousClass1) obj);
                    }

                    @Override // com.google.common.base.Function
                    public Collection<V> apply(K k2) {
                        return AsMap.this.multimap.get(k2);
                    }
                });
            }

            @Override // com.google.common.collect.Maps.EntrySet
            Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (contains(obj)) {
                    AsMap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                    return true;
                }
                return false;
            }
        }

        public AsMap(Multimap<K, V> multimap) {
            this.multimap = (Multimap) Preconditions.checkNotNull(multimap);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            this.multimap.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return this.multimap.containsKey(obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        protected Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new EntrySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean isEmpty() {
            return this.multimap.isEmpty();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        public Set<K> keySet() {
            return this.multimap.keySet();
        }

        void removeValuesForKey(Object obj) {
            this.multimap.keySet().remove(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.multimap.keySet().size();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object obj) {
            if (containsKey(obj)) {
                return this.multimap.get(obj);
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object obj) {
            if (containsKey(obj)) {
                return this.multimap.removeAll(obj);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CustomListMultimap<K, V> extends AbstractListMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends List<V>> factory;

        CustomListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (Supplier) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractListMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public List<V> createCollection() {
            return this.factory.get();
        }
    }

    /* loaded from: classes12.dex */
    private static class CustomMultimap<K, V> extends AbstractMapBasedMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Collection<V>> factory;

        CustomMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (Supplier) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap
        protected Collection<V> createCollection() {
            return this.factory.get();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CustomSetMultimap<K, V> extends AbstractSetMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends Set<V>> factory;

        CustomSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.factory = (Supplier) objectInputStream.readObject();
            setMap((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public Set<V> createCollection() {
            return this.factory.get();
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class Entries<K, V> extends AbstractCollection<Map.Entry<K, V>> {
        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            multimap().clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return multimap().containsEntry(entry.getKey(), entry.getValue());
            }
            return false;
        }

        abstract Multimap<K, V> multimap();

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                return multimap().remove(entry.getKey(), entry.getValue());
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return multimap().size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class Keys<K, V> extends AbstractMultiset<K> {
        @Weak
        final Multimap<K, V> multimap;

        /* loaded from: classes12.dex */
        class KeysEntrySet extends Multisets.EntrySet<K> {
            KeysEntrySet() {
                Keys.this = r1;
            }

            @Override // com.google.common.collect.Multisets.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(@NullableDecl Object obj) {
                if (obj instanceof Multiset.Entry) {
                    Multiset.Entry entry = (Multiset.Entry) obj;
                    Collection<V> collection = Keys.this.multimap.asMap().get(entry.getElement());
                    return collection != null && collection.size() == entry.getCount();
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean isEmpty() {
                return Keys.this.multimap.isEmpty();
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<K>> iterator() {
                return Keys.this.entryIterator();
            }

            @Override // com.google.common.collect.Multisets.EntrySet
            Multiset<K> multiset() {
                return Keys.this;
            }

            @Override // com.google.common.collect.Multisets.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(@NullableDecl Object obj) {
                if (obj instanceof Multiset.Entry) {
                    Multiset.Entry entry = (Multiset.Entry) obj;
                    Collection<V> collection = Keys.this.multimap.asMap().get(entry.getElement());
                    if (collection == null || collection.size() != entry.getCount()) {
                        return false;
                    }
                    collection.clear();
                    return true;
                }
                return false;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return Keys.this.distinctElements();
            }
        }

        public Keys(Multimap<K, V> multimap) {
            this.multimap = multimap;
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.multimap.clear();
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
        public boolean contains(@NullableDecl Object obj) {
            return this.multimap.containsKey(obj);
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int count(@NullableDecl Object obj) {
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            return collection.size();
        }

        @Override // com.google.common.collect.AbstractMultiset
        Set<Multiset.Entry<K>> createEntrySet() {
            return new KeysEntrySet();
        }

        @Override // com.google.common.collect.AbstractMultiset
        int distinctElements() {
            return this.multimap.asMap().size();
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public Set<K> elementSet() {
            return this.multimap.keySet();
        }

        @Override // com.google.common.collect.AbstractMultiset
        public Iterator<Multiset.Entry<K>> entryIterator() {
            return new TransformedIterator<Map.Entry<K, Collection<V>>, Multiset.Entry<K>>(this.multimap.asMap().entrySet().iterator()) { // from class: com.google.common.collect.Multimaps.Keys.1
                {
                    Keys.this = this;
                }

                @Override // com.google.common.collect.TransformedIterator
                public /* bridge */ /* synthetic */ Object transform(Object obj) {
                    return transform((Map.Entry) ((Map.Entry) obj));
                }

                Multiset.Entry<K> transform(final Map.Entry<K, Collection<V>> entry) {
                    return new Multisets.AbstractEntry<K>() { // from class: com.google.common.collect.Multimaps.Keys.1.1
                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // com.google.common.collect.Multiset.Entry
                        public int getCount() {
                            return ((Collection) entry.getValue()).size();
                        }

                        @Override // com.google.common.collect.Multiset.Entry
                        public K getElement() {
                            return (K) entry.getKey();
                        }
                    };
                }
            };
        }

        @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
        public Iterator<K> iterator() {
            return Maps.keyIterator(this.multimap.entries().iterator());
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(@NullableDecl Object obj, int i2) {
            CollectPreconditions.checkNonnegative(i2, "occurrences");
            if (i2 == 0) {
                return count(obj);
            }
            Collection collection = (Collection) Maps.safeGet(this.multimap.asMap(), obj);
            if (collection == null) {
                return 0;
            }
            int size = collection.size();
            if (i2 >= size) {
                collection.clear();
            } else {
                Iterator it = collection.iterator();
                for (int i3 = 0; i3 < i2; i3++) {
                    it.next();
                    it.remove();
                }
            }
            return size;
        }
    }

    /* loaded from: classes12.dex */
    public static class MapMultimap<K, V> extends AbstractMultimap<K, V> implements SetMultimap<K, V>, Serializable {
        private static final long serialVersionUID = 7845222491160860175L;
        final Map<K, V> map;

        MapMultimap(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            this.map.clear();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean containsEntry(Object obj, Object obj2) {
            return this.map.entrySet().contains(Maps.immutableEntry(obj, obj2));
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object obj) {
            return this.map.containsKey(obj);
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean containsValue(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // com.google.common.collect.AbstractMultimap
        Map<K, Collection<V>> createAsMap() {
            return new AsMap(this);
        }

        @Override // com.google.common.collect.AbstractMultimap
        Collection<Map.Entry<K, V>> createEntries() {
            throw new AssertionError("unreachable");
        }

        @Override // com.google.common.collect.AbstractMultimap
        Set<K> createKeySet() {
            return this.map.keySet();
        }

        @Override // com.google.common.collect.AbstractMultimap
        Multiset<K> createKeys() {
            return new Keys(this);
        }

        @Override // com.google.common.collect.AbstractMultimap
        Collection<V> createValues() {
            return this.map.values();
        }

        @Override // com.google.common.collect.AbstractMultimap
        Iterator<Map.Entry<K, V>> entryIterator() {
            return this.map.entrySet().iterator();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((MapMultimap<K, V>) obj);
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public int hashCode() {
            return this.map.hashCode();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean put(K k2, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean remove(Object obj, Object obj2) {
            return this.map.entrySet().remove(Maps.immutableEntry(obj, obj2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((MapMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            return this.map.size();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            return this.map.entrySet();
        }

        @Override // com.google.common.collect.Multimap
        public Set<V> get(final K k2) {
            return new Sets.ImprovedAbstractSet<V>() { // from class: com.google.common.collect.Multimaps.MapMultimap.1
                {
                    MapMultimap.this = this;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<V> iterator() {
                    return new Iterator<V>() { // from class: com.google.common.collect.Multimaps.MapMultimap.1.1

                        /* renamed from: i */
                        int f1068i;

                        {
                            AnonymousClass1.this = this;
                        }

                        @Override // java.util.Iterator
                        public boolean hasNext() {
                            if (this.f1068i == 0) {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                if (MapMultimap.this.map.containsKey(k2)) {
                                    return true;
                                }
                            }
                            return false;
                        }

                        @Override // java.util.Iterator
                        public V next() {
                            if (hasNext()) {
                                this.f1068i++;
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                return MapMultimap.this.map.get(k2);
                            }
                            throw new NoSuchElementException();
                        }

                        @Override // java.util.Iterator
                        public void remove() {
                            CollectPreconditions.checkRemove(this.f1068i == 1);
                            this.f1068i = -1;
                            AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                            MapMultimap.this.map.remove(k2);
                        }
                    };
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return MapMultimap.this.map.containsKey(k2) ? 1 : 0;
                }
            };
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimap
        public Set<V> removeAll(Object obj) {
            HashSet hashSet = new HashSet(2);
            if (this.map.containsKey(obj)) {
                hashSet.add(this.map.remove(obj));
                return hashSet;
            }
            return hashSet;
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public Set<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes12.dex */
    public static final class TransformedEntriesListMultimap<K, V1, V2> extends TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
        TransformedEntriesListMultimap(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            super(listMultimap, entryTransformer);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((TransformedEntriesListMultimap<K, V1, V2>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((TransformedEntriesListMultimap<K, V1, V2>) obj, iterable);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap
        /* bridge */ /* synthetic */ Collection transform(Object obj, Collection collection) {
            return transform((TransformedEntriesListMultimap<K, V1, V2>) obj, collection);
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.Multimap
        public List<V2> get(K k2) {
            return transform((TransformedEntriesListMultimap<K, V1, V2>) k2, (Collection) this.fromMultimap.get(k2));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.Multimap
        public List<V2> removeAll(Object obj) {
            return transform((TransformedEntriesListMultimap<K, V1, V2>) obj, (Collection) this.fromMultimap.removeAll(obj));
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public List<V2> replaceValues(K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.TransformedEntriesMultimap
        List<V2> transform(K k2, Collection<V1> collection) {
            return Lists.transform((List) collection, Maps.asValueToValueFunction(this.transformer, k2));
        }
    }

    /* loaded from: classes12.dex */
    public static class TransformedEntriesMultimap<K, V1, V2> extends AbstractMultimap<K, V2> {
        final Multimap<K, V1> fromMultimap;
        final Maps.EntryTransformer<? super K, ? super V1, V2> transformer;

        TransformedEntriesMultimap(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
            this.fromMultimap = (Multimap) Preconditions.checkNotNull(multimap);
            this.transformer = (Maps.EntryTransformer) Preconditions.checkNotNull(entryTransformer);
        }

        @Override // com.google.common.collect.Multimap
        public void clear() {
            this.fromMultimap.clear();
        }

        @Override // com.google.common.collect.Multimap
        public boolean containsKey(Object obj) {
            return this.fromMultimap.containsKey(obj);
        }

        @Override // com.google.common.collect.AbstractMultimap
        Map<K, Collection<V2>> createAsMap() {
            return Maps.transformEntries(this.fromMultimap.asMap(), new Maps.EntryTransformer<K, Collection<V1>, Collection<V2>>() { // from class: com.google.common.collect.Multimaps.TransformedEntriesMultimap.1
                {
                    TransformedEntriesMultimap.this = this;
                }

                @Override // com.google.common.collect.Maps.EntryTransformer
                public /* bridge */ /* synthetic */ Object transformEntry(Object obj, Object obj2) {
                    return transformEntry((AnonymousClass1) obj, (Collection) ((Collection) obj2));
                }

                public Collection<V2> transformEntry(K k2, Collection<V1> collection) {
                    return TransformedEntriesMultimap.this.transform(k2, collection);
                }
            });
        }

        @Override // com.google.common.collect.AbstractMultimap
        Collection<Map.Entry<K, V2>> createEntries() {
            return new AbstractMultimap.Entries();
        }

        @Override // com.google.common.collect.AbstractMultimap
        Set<K> createKeySet() {
            return this.fromMultimap.keySet();
        }

        @Override // com.google.common.collect.AbstractMultimap
        Multiset<K> createKeys() {
            return this.fromMultimap.keys();
        }

        @Override // com.google.common.collect.AbstractMultimap
        Collection<V2> createValues() {
            return Collections2.transform(this.fromMultimap.entries(), Maps.asEntryToValueFunction(this.transformer));
        }

        @Override // com.google.common.collect.AbstractMultimap
        Iterator<Map.Entry<K, V2>> entryIterator() {
            return Iterators.transform(this.fromMultimap.entries().iterator(), Maps.asEntryToEntryFunction(this.transformer));
        }

        @Override // com.google.common.collect.Multimap
        public Collection<V2> get(K k2) {
            return transform(k2, this.fromMultimap.get(k2));
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean isEmpty() {
            return this.fromMultimap.isEmpty();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean put(K k2, V2 v2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean remove(Object obj, Object obj2) {
            return get(obj).remove(obj2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimap
        public Collection<V2> removeAll(Object obj) {
            return transform(obj, this.fromMultimap.removeAll(obj));
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public Collection<V2> replaceValues(K k2, Iterable<? extends V2> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimap
        public int size() {
            return this.fromMultimap.size();
        }

        Collection<V2> transform(K k2, Collection<V1> collection) {
            Function asValueToValueFunction = Maps.asValueToValueFunction(this.transformer, k2);
            if (collection instanceof List) {
                return Lists.transform((List) collection, asValueToValueFunction);
            }
            return Collections2.transform(collection, asValueToValueFunction);
        }

        @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V2> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes12.dex */
    private static class UnmodifiableListMultimap<K, V> extends UnmodifiableMultimap<K, V> implements ListMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
            super(listMultimap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((UnmodifiableListMultimap<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((UnmodifiableListMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public List<V> get(K k2) {
            return Collections.unmodifiableList(delegate().get((ListMultimap<K, V>) k2));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public List<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public List<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public ListMultimap<K, V> delegate() {
            return (ListMultimap) super.delegate();
        }
    }

    /* loaded from: classes12.dex */
    public static class UnmodifiableMultimap<K, V> extends ForwardingMultimap<K, V> implements Serializable {
        private static final long serialVersionUID = 0;
        final Multimap<K, V> delegate;
        transient Collection<Map.Entry<K, V>> entries;
        transient Set<K> keySet;
        transient Multiset<K> keys;
        transient Map<K, Collection<V>> map;
        transient Collection<V> values;

        UnmodifiableMultimap(Multimap<K, V> multimap) {
            this.delegate = (Multimap) Preconditions.checkNotNull(multimap);
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
        public Map<K, Collection<V>> asMap() {
            Map<K, Collection<V>> map = this.map;
            if (map == null) {
                Map<K, Collection<V>> unmodifiableMap = Collections.unmodifiableMap(Maps.transformValues(this.delegate.asMap(), new Function<Collection<V>, Collection<V>>() { // from class: com.google.common.collect.Multimaps.UnmodifiableMultimap.1
                    {
                        UnmodifiableMultimap.this = this;
                    }

                    @Override // com.google.common.base.Function
                    public /* bridge */ /* synthetic */ Object apply(Object obj) {
                        return apply((Collection) ((Collection) obj));
                    }

                    public Collection<V> apply(Collection<V> collection) {
                        return Multimaps.unmodifiableValueCollection(collection);
                    }
                }));
                this.map = unmodifiableMap;
                return unmodifiableMap;
            }
            return map;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<Map.Entry<K, V>> entries() {
            Collection<Map.Entry<K, V>> collection = this.entries;
            if (collection == null) {
                Collection<Map.Entry<K, V>> unmodifiableEntries = Multimaps.unmodifiableEntries(this.delegate.entries());
                this.entries = unmodifiableEntries;
                return unmodifiableEntries;
            }
            return collection;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> get(K k2) {
            return Multimaps.unmodifiableValueCollection(this.delegate.get(k2));
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<K> keySet() {
            Set<K> set = this.keySet;
            if (set == null) {
                Set<K> unmodifiableSet = Collections.unmodifiableSet(this.delegate.keySet());
                this.keySet = unmodifiableSet;
                return unmodifiableSet;
            }
            return set;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Multiset<K> keys() {
            Multiset<K> multiset = this.keys;
            if (multiset == null) {
                Multiset<K> unmodifiableMultiset = Multisets.unmodifiableMultiset(this.delegate.keys());
                this.keys = unmodifiableMultiset;
                return unmodifiableMultiset;
            }
            return multiset;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean put(K k2, V v) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean remove(Object obj, Object obj2) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Collection<V> values() {
            Collection<V> collection = this.values;
            if (collection == null) {
                Collection<V> unmodifiableCollection = Collections.unmodifiableCollection(this.delegate.values());
                this.values = unmodifiableCollection;
                return unmodifiableCollection;
            }
            return collection;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public Multimap<K, V> delegate() {
            return this.delegate;
        }

        @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes12.dex */
    public static class UnmodifiableSetMultimap<K, V> extends UnmodifiableMultimap<K, V> implements SetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
            super(setMultimap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((UnmodifiableSetMultimap<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((UnmodifiableSetMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<Map.Entry<K, V>> entries() {
            return Maps.unmodifiableEntrySet(delegate().entries());
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<V> get(K k2) {
            return Collections.unmodifiableSet(delegate().get((SetMultimap<K, V>) k2));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public Set<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public SetMultimap<K, V> delegate() {
            return (SetMultimap) super.delegate();
        }
    }

    /* loaded from: classes12.dex */
    private static class UnmodifiableSortedSetMultimap<K, V> extends UnmodifiableSetMultimap<K, V> implements SortedSetMultimap<K, V> {
        private static final long serialVersionUID = 0;

        UnmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
            super(sortedSetMultimap);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection get(Object obj) {
            return get((UnmodifiableSortedSetMultimap<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Collection replaceValues(Object obj, Iterable iterable) {
            return replaceValues((UnmodifiableSortedSetMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            return delegate().valueComparator();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Set get(Object obj) {
            return get((UnmodifiableSortedSetMultimap<K, V>) obj);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public /* bridge */ /* synthetic */ Set replaceValues(Object obj, Iterable iterable) {
            return replaceValues((UnmodifiableSortedSetMultimap<K, V>) obj, iterable);
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public SortedSet<V> get(K k2) {
            return Collections.unmodifiableSortedSet(delegate().get((SortedSetMultimap<K, V>) k2));
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public SortedSet<V> removeAll(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap
        public SortedSet<V> replaceValues(K k2, Iterable<? extends V> iterable) {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.common.collect.Multimaps.UnmodifiableSetMultimap, com.google.common.collect.Multimaps.UnmodifiableMultimap, com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
        public SortedSetMultimap<K, V> delegate() {
            return (SortedSetMultimap) super.delegate();
        }
    }

    private Multimaps() {
    }

    @Beta
    public static <K, V> Map<K, List<V>> asMap(ListMultimap<K, V> listMultimap) {
        return (Map<K, Collection<V>>) listMultimap.asMap();
    }

    public static boolean equalsImpl(Multimap<?, ?> multimap, @NullableDecl Object obj) {
        if (obj == multimap) {
            return true;
        }
        if (obj instanceof Multimap) {
            return multimap.asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }

    public static <K, V> Multimap<K, V> filterEntries(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (multimap instanceof SetMultimap) {
            return filterEntries((SetMultimap) multimap, (Predicate) predicate);
        }
        if (multimap instanceof FilteredMultimap) {
            return filterFiltered((FilteredMultimap) multimap, predicate);
        }
        return new FilteredEntryMultimap((Multimap) Preconditions.checkNotNull(multimap), predicate);
    }

    private static <K, V> Multimap<K, V> filterFiltered(FilteredMultimap<K, V> filteredMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntryMultimap(filteredMultimap.unfiltered(), Predicates.and(filteredMultimap.entryPredicate(), predicate));
    }

    public static <K, V> Multimap<K, V> filterKeys(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        if (multimap instanceof SetMultimap) {
            return filterKeys((SetMultimap) multimap, (Predicate) predicate);
        }
        if (multimap instanceof ListMultimap) {
            return filterKeys((ListMultimap) multimap, (Predicate) predicate);
        }
        if (multimap instanceof FilteredKeyMultimap) {
            FilteredKeyMultimap filteredKeyMultimap = (FilteredKeyMultimap) multimap;
            return new FilteredKeyMultimap(filteredKeyMultimap.unfiltered, Predicates.and(filteredKeyMultimap.keyPredicate, predicate));
        } else if (multimap instanceof FilteredMultimap) {
            return filterFiltered((FilteredMultimap) multimap, Maps.keyPredicateOnEntries(predicate));
        } else {
            return new FilteredKeyMultimap(multimap, predicate);
        }
    }

    public static <K, V> Multimap<K, V> filterValues(Multimap<K, V> multimap, Predicate<? super V> predicate) {
        return filterEntries(multimap, Maps.valuePredicateOnEntries(predicate));
    }

    public static <K, V> SetMultimap<K, V> forMap(Map<K, V> map) {
        return new MapMultimap(map);
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterable<V> iterable, Function<? super V, K> function) {
        return index(iterable.iterator(), function);
    }

    @CanIgnoreReturnValue
    public static <K, V, M extends Multimap<K, V>> M invertFrom(Multimap<? extends V, ? extends K> multimap, M m2) {
        Preconditions.checkNotNull(m2);
        for (Map.Entry<? extends V, ? extends K> entry : multimap.entries()) {
            m2.put(entry.getValue(), entry.getKey());
        }
        return m2;
    }

    public static <K, V> ListMultimap<K, V> newListMultimap(Map<K, Collection<V>> map, Supplier<? extends List<V>> supplier) {
        return new CustomListMultimap(map, supplier);
    }

    public static <K, V> Multimap<K, V> newMultimap(Map<K, Collection<V>> map, Supplier<? extends Collection<V>> supplier) {
        return new CustomMultimap(map, supplier);
    }

    public static <K, V> SetMultimap<K, V> newSetMultimap(Map<K, Collection<V>> map, Supplier<? extends Set<V>> supplier) {
        return new CustomSetMultimap(map, supplier);
    }

    public static <K, V> SortedSetMultimap<K, V> newSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
        return new CustomSortedSetMultimap(map, supplier);
    }

    public static <K, V> ListMultimap<K, V> synchronizedListMultimap(ListMultimap<K, V> listMultimap) {
        return Synchronized.listMultimap(listMultimap, null);
    }

    public static <K, V> Multimap<K, V> synchronizedMultimap(Multimap<K, V> multimap) {
        return Synchronized.multimap(multimap, null);
    }

    public static <K, V> SetMultimap<K, V> synchronizedSetMultimap(SetMultimap<K, V> setMultimap) {
        return Synchronized.setMultimap(setMultimap, null);
    }

    public static <K, V> SortedSetMultimap<K, V> synchronizedSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return Synchronized.sortedSetMultimap(sortedSetMultimap, null);
    }

    public static <K, V1, V2> Multimap<K, V2> transformEntries(Multimap<K, V1> multimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesMultimap(multimap, entryTransformer);
    }

    public static <K, V1, V2> Multimap<K, V2> transformValues(Multimap<K, V1> multimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries(multimap, Maps.asEntryTransformer(function));
    }

    public static <K, V> Collection<Map.Entry<K, V>> unmodifiableEntries(Collection<Map.Entry<K, V>> collection) {
        if (collection instanceof Set) {
            return Maps.unmodifiableEntrySet((Set) collection);
        }
        return new Maps.UnmodifiableEntries(Collections.unmodifiableCollection(collection));
    }

    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ListMultimap<K, V> listMultimap) {
        return ((listMultimap instanceof UnmodifiableListMultimap) || (listMultimap instanceof ImmutableListMultimap)) ? listMultimap : new UnmodifiableListMultimap(listMultimap);
    }

    public static <K, V> Multimap<K, V> unmodifiableMultimap(Multimap<K, V> multimap) {
        return ((multimap instanceof UnmodifiableMultimap) || (multimap instanceof ImmutableMultimap)) ? multimap : new UnmodifiableMultimap(multimap);
    }

    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(SetMultimap<K, V> setMultimap) {
        return ((setMultimap instanceof UnmodifiableSetMultimap) || (setMultimap instanceof ImmutableSetMultimap)) ? setMultimap : new UnmodifiableSetMultimap(setMultimap);
    }

    public static <K, V> SortedSetMultimap<K, V> unmodifiableSortedSetMultimap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return sortedSetMultimap instanceof UnmodifiableSortedSetMultimap ? sortedSetMultimap : new UnmodifiableSortedSetMultimap(sortedSetMultimap);
    }

    public static <V> Collection<V> unmodifiableValueCollection(Collection<V> collection) {
        if (collection instanceof SortedSet) {
            return Collections.unmodifiableSortedSet((SortedSet) collection);
        }
        if (collection instanceof Set) {
            return Collections.unmodifiableSet((Set) collection);
        }
        if (collection instanceof List) {
            return Collections.unmodifiableList((List) collection);
        }
        return Collections.unmodifiableCollection(collection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class CustomSortedSetMultimap<K, V> extends AbstractSortedSetMultimap<K, V> {
        @GwtIncompatible
        private static final long serialVersionUID = 0;
        transient Supplier<? extends SortedSet<V>> factory;
        transient Comparator<? super V> valueComparator;

        CustomSortedSetMultimap(Map<K, Collection<V>> map, Supplier<? extends SortedSet<V>> supplier) {
            super(map);
            this.factory = (Supplier) Preconditions.checkNotNull(supplier);
            this.valueComparator = supplier.get().comparator();
        }

        @GwtIncompatible
        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            Supplier<? extends SortedSet<V>> supplier = (Supplier) objectInputStream.readObject();
            this.factory = supplier;
            this.valueComparator = supplier.get().comparator();
            setMap((Map) objectInputStream.readObject());
        }

        @GwtIncompatible
        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.factory);
            objectOutputStream.writeObject(backingMap());
        }

        @Override // com.google.common.collect.SortedSetMultimap
        public Comparator<? super V> valueComparator() {
            return this.valueComparator;
        }

        @Override // com.google.common.collect.AbstractSortedSetMultimap, com.google.common.collect.AbstractSetMultimap, com.google.common.collect.AbstractMapBasedMultimap
        public SortedSet<V> createCollection() {
            return this.factory.get();
        }
    }

    @Beta
    public static <K, V> Map<K, Set<V>> asMap(SetMultimap<K, V> setMultimap) {
        return (Map<K, Collection<V>>) setMultimap.asMap();
    }

    public static <K, V> SetMultimap<K, V> filterValues(SetMultimap<K, V> setMultimap, Predicate<? super V> predicate) {
        return filterEntries((SetMultimap) setMultimap, Maps.valuePredicateOnEntries(predicate));
    }

    public static <K, V> ImmutableListMultimap<K, V> index(Iterator<V> it, Function<? super V, K> function) {
        Preconditions.checkNotNull(function);
        ImmutableListMultimap.Builder builder = ImmutableListMultimap.builder();
        while (it.hasNext()) {
            V next = it.next();
            Preconditions.checkNotNull(next, it);
            builder.put((ImmutableListMultimap.Builder) function.apply(next), (K) next);
        }
        return builder.build();
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformEntries(ListMultimap<K, V1> listMultimap, Maps.EntryTransformer<? super K, ? super V1, V2> entryTransformer) {
        return new TransformedEntriesListMultimap(listMultimap, entryTransformer);
    }

    @Beta
    public static <K, V> Map<K, SortedSet<V>> asMap(SortedSetMultimap<K, V> sortedSetMultimap) {
        return (Map<K, Collection<V>>) sortedSetMultimap.asMap();
    }

    private static <K, V> SetMultimap<K, V> filterFiltered(FilteredSetMultimap<K, V> filteredSetMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        return new FilteredEntrySetMultimap(filteredSetMultimap.unfiltered(), Predicates.and(filteredSetMultimap.entryPredicate(), predicate));
    }

    @Deprecated
    public static <K, V> ListMultimap<K, V> unmodifiableListMultimap(ImmutableListMultimap<K, V> immutableListMultimap) {
        return (ListMultimap) Preconditions.checkNotNull(immutableListMultimap);
    }

    @Deprecated
    public static <K, V> Multimap<K, V> unmodifiableMultimap(ImmutableMultimap<K, V> immutableMultimap) {
        return (Multimap) Preconditions.checkNotNull(immutableMultimap);
    }

    @Deprecated
    public static <K, V> SetMultimap<K, V> unmodifiableSetMultimap(ImmutableSetMultimap<K, V> immutableSetMultimap) {
        return (SetMultimap) Preconditions.checkNotNull(immutableSetMultimap);
    }

    @Beta
    public static <K, V> Map<K, Collection<V>> asMap(Multimap<K, V> multimap) {
        return multimap.asMap();
    }

    public static <K, V1, V2> ListMultimap<K, V2> transformValues(ListMultimap<K, V1> listMultimap, Function<? super V1, V2> function) {
        Preconditions.checkNotNull(function);
        return transformEntries((ListMultimap) listMultimap, Maps.asEntryTransformer(function));
    }

    public static <K, V> SetMultimap<K, V> filterEntries(SetMultimap<K, V> setMultimap, Predicate<? super Map.Entry<K, V>> predicate) {
        Preconditions.checkNotNull(predicate);
        if (setMultimap instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) setMultimap, (Predicate) predicate);
        }
        return new FilteredEntrySetMultimap((SetMultimap) Preconditions.checkNotNull(setMultimap), predicate);
    }

    public static <K, V> SetMultimap<K, V> filterKeys(SetMultimap<K, V> setMultimap, Predicate<? super K> predicate) {
        if (setMultimap instanceof FilteredKeySetMultimap) {
            FilteredKeySetMultimap filteredKeySetMultimap = (FilteredKeySetMultimap) setMultimap;
            return new FilteredKeySetMultimap(filteredKeySetMultimap.unfiltered(), Predicates.and(filteredKeySetMultimap.keyPredicate, predicate));
        } else if (setMultimap instanceof FilteredSetMultimap) {
            return filterFiltered((FilteredSetMultimap) setMultimap, Maps.keyPredicateOnEntries(predicate));
        } else {
            return new FilteredKeySetMultimap(setMultimap, predicate);
        }
    }

    public static <K, V> ListMultimap<K, V> filterKeys(ListMultimap<K, V> listMultimap, Predicate<? super K> predicate) {
        if (listMultimap instanceof FilteredKeyListMultimap) {
            FilteredKeyListMultimap filteredKeyListMultimap = (FilteredKeyListMultimap) listMultimap;
            return new FilteredKeyListMultimap(filteredKeyListMultimap.unfiltered(), Predicates.and(filteredKeyListMultimap.keyPredicate, predicate));
        }
        return new FilteredKeyListMultimap(listMultimap, predicate);
    }
}