package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

@GwtIncompatible
/* loaded from: classes12.dex */
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    @Beta
    /* loaded from: classes12.dex */
    protected class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet() {
            super(ForwardingNavigableSet.this);
        }
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e2) {
        return delegate().ceiling(e2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract NavigableSet<E> delegate();

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return delegate().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return delegate().descendingSet();
    }

    @Override // java.util.NavigableSet
    public E floor(E e2) {
        return delegate().floor(e2);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e2, boolean z) {
        return delegate().headSet(e2, z);
    }

    @Override // java.util.NavigableSet
    public E higher(E e2) {
        return delegate().higher(e2);
    }

    @Override // java.util.NavigableSet
    public E lower(E e2) {
        return delegate().lower(e2);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return delegate().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return delegate().pollLast();
    }

    protected E standardCeiling(E e2) {
        return (E) Iterators.getNext(tailSet(e2, true).iterator(), null);
    }

    protected E standardFirst() {
        return iterator().next();
    }

    protected E standardFloor(E e2) {
        return (E) Iterators.getNext(headSet(e2, true).descendingIterator(), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SortedSet<E> standardHeadSet(E e2) {
        return headSet(e2, false);
    }

    protected E standardHigher(E e2) {
        return (E) Iterators.getNext(tailSet(e2, false).iterator(), null);
    }

    protected E standardLast() {
        return descendingIterator().next();
    }

    protected E standardLower(E e2) {
        return (E) Iterators.getNext(headSet(e2, false).descendingIterator(), null);
    }

    protected E standardPollFirst() {
        return (E) Iterators.pollNext(iterator());
    }

    protected E standardPollLast() {
        return (E) Iterators.pollNext(descendingIterator());
    }

    @Beta
    protected NavigableSet<E> standardSubSet(E e2, boolean z, E e3, boolean z2) {
        return tailSet(e2, z).headSet(e3, z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SortedSet<E> standardTailSet(E e2) {
        return tailSet(e2, true);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E e2, boolean z, E e3, boolean z2) {
        return delegate().subSet(e2, z, e3, z2);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e2, boolean z) {
        return delegate().tailSet(e2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet
    public SortedSet<E> standardSubSet(E e2, E e3) {
        return subSet(e2, true, e3, false);
    }
}
