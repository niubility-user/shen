package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true, serializable = true)
/* loaded from: classes12.dex */
public final class RegularImmutableSortedSet<E> extends ImmutableSortedSet<E> {
    static final RegularImmutableSortedSet<Comparable> NATURAL_EMPTY_SET = new RegularImmutableSortedSet<>(ImmutableList.of(), Ordering.natural());
    @VisibleForTesting
    final transient ImmutableList<E> elements;

    public RegularImmutableSortedSet(ImmutableList<E> immutableList, Comparator<? super E> comparator) {
        super(comparator);
        this.elements = immutableList;
    }

    private int unsafeBinarySearch(Object obj) throws ClassCastException {
        return Collections.binarySearch(this.elements, obj, unsafeComparator());
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public ImmutableList<E> asList() {
        return this.elements;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E ceiling(E e2) {
        int tailIndex = tailIndex(e2, true);
        if (tailIndex == size()) {
            return null;
        }
        return this.elements.get(tailIndex);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(@NullableDecl Object obj) {
        if (obj != null) {
            try {
                return unsafeBinarySearch(obj) >= 0;
            } catch (ClassCastException unused) {
                return false;
            }
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Multiset) {
            collection = ((Multiset) collection).elementSet();
        }
        if (SortedIterables.hasSameComparator(comparator(), collection) && collection.size() > 1) {
            UnmodifiableIterator<E> it = iterator();
            Iterator<?> it2 = collection.iterator();
            if (!it.hasNext()) {
                return false;
            }
            Object next = it2.next();
            E next2 = it.next();
            while (true) {
                try {
                    int unsafeCompare = unsafeCompare(next2, next);
                    if (unsafeCompare < 0) {
                        if (!it.hasNext()) {
                            return false;
                        }
                        next2 = it.next();
                    } else if (unsafeCompare == 0) {
                        if (!it2.hasNext()) {
                            return true;
                        }
                        next = it2.next();
                    } else if (unsafeCompare > 0) {
                        break;
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
        } else {
            return super.containsAll(collection);
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i2) {
        return this.elements.copyIntoArray(objArr, i2);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet<E> createDescendingSet() {
        Comparator reverseOrder = Collections.reverseOrder(this.comparator);
        if (isEmpty()) {
            return ImmutableSortedSet.emptySet(reverseOrder);
        }
        return new RegularImmutableSortedSet(this.elements.reverse(), reverseOrder);
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            if (size() != set.size()) {
                return false;
            }
            if (isEmpty()) {
                return true;
            }
            if (SortedIterables.hasSameComparator(this.comparator, set)) {
                Iterator<E> it = set.iterator();
                try {
                    UnmodifiableIterator<E> it2 = iterator();
                    while (it2.hasNext()) {
                        E next = it2.next();
                        E next2 = it.next();
                        if (next2 == null || unsafeCompare(next, next2) != 0) {
                            return false;
                        }
                    }
                    return true;
                } catch (ClassCastException | NoSuchElementException unused) {
                    return false;
                }
            }
            return containsAll(set);
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E first() {
        if (!isEmpty()) {
            return this.elements.get(0);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E floor(E e2) {
        int headIndex = headIndex(e2, true) - 1;
        if (headIndex == -1) {
            return null;
        }
        return this.elements.get(headIndex);
    }

    public RegularImmutableSortedSet<E> getSubSet(int i2, int i3) {
        if (i2 == 0 && i3 == size()) {
            return this;
        }
        if (i2 < i3) {
            return new RegularImmutableSortedSet<>(this.elements.subList(i2, i3), this.comparator);
        }
        return ImmutableSortedSet.emptySet(this.comparator);
    }

    public int headIndex(E e2, boolean z) {
        int binarySearch = Collections.binarySearch(this.elements, Preconditions.checkNotNull(e2), comparator());
        return binarySearch >= 0 ? z ? binarySearch + 1 : binarySearch : binarySearch ^ (-1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableSortedSet
    public ImmutableSortedSet<E> headSetImpl(E e2, boolean z) {
        return getSubSet(0, headIndex(e2, z));
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E higher(E e2) {
        int tailIndex = tailIndex(e2, false);
        if (tailIndex == size()) {
            return null;
        }
        return this.elements.get(tailIndex);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    public int indexOf(@NullableDecl Object obj) {
        if (obj == null) {
            return -1;
        }
        try {
            int binarySearch = Collections.binarySearch(this.elements, obj, unsafeComparator());
            if (binarySearch >= 0) {
                return binarySearch;
            }
            return -1;
        } catch (ClassCastException unused) {
            return -1;
        }
    }

    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.elements.isPartialView();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.SortedSet
    public E last() {
        if (!isEmpty()) {
            return this.elements.get(size() - 1);
        }
        throw new NoSuchElementException();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    public E lower(E e2) {
        int headIndex = headIndex(e2, false) - 1;
        if (headIndex == -1) {
            return null;
        }
        return this.elements.get(headIndex);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.elements.size();
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet<E> subSetImpl(E e2, boolean z, E e3, boolean z2) {
        return tailSetImpl(e2, z).headSetImpl(e3, z2);
    }

    public int tailIndex(E e2, boolean z) {
        int binarySearch = Collections.binarySearch(this.elements, Preconditions.checkNotNull(e2), comparator());
        return binarySearch >= 0 ? z ? binarySearch : binarySearch + 1 : binarySearch ^ (-1);
    }

    @Override // com.google.common.collect.ImmutableSortedSet
    ImmutableSortedSet<E> tailSetImpl(E e2, boolean z) {
        return getSubSet(tailIndex(e2, z), size());
    }

    Comparator<Object> unsafeComparator() {
        return this.comparator;
    }

    @Override // com.google.common.collect.ImmutableSortedSet, java.util.NavigableSet
    @GwtIncompatible
    public UnmodifiableIterator<E> descendingIterator() {
        return this.elements.reverse().iterator();
    }

    @Override // com.google.common.collect.ImmutableSortedSet, com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set, java.util.NavigableSet, com.google.common.collect.SortedIterable
    public UnmodifiableIterator<E> iterator() {
        return this.elements.iterator();
    }
}
