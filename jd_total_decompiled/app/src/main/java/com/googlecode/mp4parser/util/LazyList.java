package com.googlecode.mp4parser.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/* loaded from: classes12.dex */
public class LazyList<E> extends AbstractList<E> {
    private static final Logger LOG = Logger.getLogger(LazyList.class);
    Iterator<E> elementSource;
    List<E> underlying;

    public LazyList(List<E> list, Iterator<E> it) {
        this.underlying = list;
        this.elementSource = it;
    }

    private void blowup() {
        LOG.logDebug("blowup running");
        while (this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i2) {
        if (this.underlying.size() > i2) {
            return this.underlying.get(i2);
        }
        if (this.elementSource.hasNext()) {
            this.underlying.add(this.elementSource.next());
            return get(i2);
        }
        throw new NoSuchElementException();
    }

    public List<E> getUnderlying() {
        return this.underlying;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: com.googlecode.mp4parser.util.LazyList.1
            int pos = 0;

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.pos < LazyList.this.underlying.size() || LazyList.this.elementSource.hasNext();
            }

            @Override // java.util.Iterator
            public E next() {
                if (this.pos < LazyList.this.underlying.size()) {
                    List<E> list = LazyList.this.underlying;
                    int i2 = this.pos;
                    this.pos = i2 + 1;
                    return list.get(i2);
                }
                LazyList lazyList = LazyList.this;
                lazyList.underlying.add(lazyList.elementSource.next());
                return (E) next();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        LOG.logDebug("potentially expensive size() call");
        blowup();
        return this.underlying.size();
    }
}
