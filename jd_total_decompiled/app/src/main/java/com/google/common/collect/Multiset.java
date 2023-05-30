package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
/* loaded from: classes12.dex */
public interface Multiset<E> extends Collection<E> {

    /* loaded from: classes12.dex */
    public interface Entry<E> {
        boolean equals(Object obj);

        int getCount();

        E getElement();

        int hashCode();

        String toString();
    }

    @CanIgnoreReturnValue
    int add(@NullableDecl E e2, int i2);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    boolean add(E e2);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    boolean contains(@NullableDecl Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@NullableDecl @CompatibleWith("E") Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    @Override // com.google.common.collect.Multiset
    boolean equals(@NullableDecl Object obj);

    @Override // com.google.common.collect.Multiset
    int hashCode();

    @Override // java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    Iterator<E> iterator();

    @CanIgnoreReturnValue
    int remove(@NullableDecl @CompatibleWith("E") Object obj, int i2);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    boolean remove(@NullableDecl Object obj);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    boolean removeAll(Collection<?> collection);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    boolean retainAll(Collection<?> collection);

    @CanIgnoreReturnValue
    int setCount(E e2, int i2);

    @CanIgnoreReturnValue
    boolean setCount(E e2, int i2, int i3);

    @Override // java.util.Collection, com.google.common.collect.Multiset
    int size();

    String toString();
}
