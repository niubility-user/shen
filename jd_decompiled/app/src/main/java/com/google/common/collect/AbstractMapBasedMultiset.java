package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultiset;
import com.google.common.collect.AbstractObjectCountMap;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.InvalidObjectException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
abstract class AbstractMapBasedMultiset<E> extends AbstractMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = -2250766705698539974L;
    transient AbstractObjectCountMap<E> backingMap;
    private transient long size = super.size();

    /* loaded from: classes12.dex */
    private class MapBasedMultisetIterator implements Iterator<E> {
        Multiset.Entry<E> currentEntry;
        final Iterator<Multiset.Entry<E>> entryIterator;
        int occurrencesLeft = 0;
        boolean canRemove = false;

        MapBasedMultisetIterator() {
            this.entryIterator = AbstractMapBasedMultiset.this.backingMap.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.occurrencesLeft > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (this.occurrencesLeft == 0) {
                Multiset.Entry<E> next = this.entryIterator.next();
                this.currentEntry = next;
                this.occurrencesLeft = next.getCount();
            }
            this.occurrencesLeft--;
            this.canRemove = true;
            return this.currentEntry.getElement();
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            int count = this.currentEntry.getCount();
            if (count > 0) {
                if (count == 1) {
                    this.entryIterator.remove();
                } else {
                    ((AbstractObjectCountMap.MapEntry) this.currentEntry).setCount(count - 1);
                }
                AbstractMapBasedMultiset.access$010(AbstractMapBasedMultiset.this);
                this.canRemove = false;
                return;
            }
            throw new ConcurrentModificationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapBasedMultiset(AbstractObjectCountMap<E> abstractObjectCountMap) {
        this.backingMap = (AbstractObjectCountMap) Preconditions.checkNotNull(abstractObjectCountMap);
    }

    static /* synthetic */ long access$010(AbstractMapBasedMultiset abstractMapBasedMultiset) {
        long j2 = abstractMapBasedMultiset.size;
        abstractMapBasedMultiset.size = j2 - 1;
        return j2;
    }

    @GwtIncompatible
    private void readObjectNoData() throws ObjectStreamException {
        throw new InvalidObjectException("Stream data required");
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@NullableDecl E e2, int i2) {
        if (i2 == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(i2 > 0, "occurrences cannot be negative: %s", i2);
        int i3 = this.backingMap.get(e2);
        long j2 = i2;
        long j3 = i3 + j2;
        Preconditions.checkArgument(j3 <= 2147483647L, "too many occurrences: %s", j3);
        this.backingMap.put(e2, (int) j3);
        this.size += j2;
        return i3;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.backingMap.clear();
        this.size = 0L;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        return this.backingMap.get(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset
    Set<E> createElementSet() {
        return this.backingMap.keySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    public Set<Multiset.Entry<E>> createEntrySet() {
        return new AbstractMultiset.EntrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset
    int distinctElements() {
        return this.backingMap.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        this.backingMap.entrySet().iterator();
        return new Iterator<Multiset.Entry<E>>
        /*  JADX ERROR: Method code generation error
            jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x000f: RETURN 
              (wrap: java.util.Iterator<com.google.common.collect.Multiset$Entry<E>> : 0x000c: CONSTRUCTOR 
              (r2v0 'this' com.google.common.collect.AbstractMapBasedMultiset<E> A[IMMUTABLE_TYPE, THIS])
              (r0 I:java.util.Iterator A[DONT_GENERATE, DONT_INLINE, REMOVE])
             A[MD:(com.google.common.collect.AbstractMapBasedMultiset, java.util.Iterator):void (m), WRAPPED] (LINE:2) call: com.google.common.collect.AbstractMapBasedMultiset.1.<init>(com.google.common.collect.AbstractMapBasedMultiset, java.util.Iterator):void type: CONSTRUCTOR)
             (LINE:2) in method: com.google.common.collect.AbstractMapBasedMultiset.entryIterator():java.util.Iterator<com.google.common.collect.Multiset$Entry<E>>, file: classes12.dex
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
            */
        /*
            this = this;
            com.google.common.collect.AbstractObjectCountMap<E> r0 = r2.backingMap
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
            com.google.common.collect.AbstractMapBasedMultiset$1 r1 = new com.google.common.collect.AbstractMapBasedMultiset$1
            r1.<init>()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.AbstractMapBasedMultiset.entryIterator():java.util.Iterator");
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public Iterator<E> iterator() {
        return new MapBasedMultisetIterator();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i2) {
        if (i2 == 0) {
            return count(obj);
        }
        Preconditions.checkArgument(i2 > 0, "occurrences cannot be negative: %s", i2);
        int i3 = this.backingMap.get(obj);
        if (i3 > i2) {
            this.backingMap.put(obj, i3 - i2);
        } else {
            this.backingMap.remove(obj);
            i2 = i3;
        }
        this.size -= i2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setBackingMap(AbstractObjectCountMap<E> abstractObjectCountMap) {
        this.backingMap = abstractObjectCountMap;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e2, int i2) {
        CollectPreconditions.checkNonnegative(i2, "count");
        AbstractObjectCountMap<E> abstractObjectCountMap = this.backingMap;
        int remove = i2 == 0 ? abstractObjectCountMap.remove(e2) : abstractObjectCountMap.put(e2, i2);
        this.size += i2 - remove;
        return remove;
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(this.size);
    }
}
