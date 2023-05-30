package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.Serialization;
import com.google.common.primitives.Ints;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = true)
/* loaded from: classes12.dex */
public final class TreeMultiset<E> extends AbstractSortedMultiset<E> implements Serializable {
    @GwtIncompatible
    private static final long serialVersionUID = 1;
    private final transient AvlNode<E> header;
    private final transient GeneralRange<E> range;
    private final transient Reference<AvlNode<E>> rootReference;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.google.common.collect.TreeMultiset$4  reason: invalid class name */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$google$common$collect$BoundType;

        static {
            int[] iArr = new int[BoundType.values().length];
            $SwitchMap$com$google$common$collect$BoundType = iArr;
            try {
                iArr[BoundType.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$common$collect$BoundType[BoundType.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum Aggregate {
        SIZE { // from class: com.google.common.collect.TreeMultiset.Aggregate.1
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            int nodeAggregate(AvlNode<?> avlNode) {
                return ((AvlNode) avlNode).elemCount;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            long treeAggregate(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).totalCount;
            }
        },
        DISTINCT { // from class: com.google.common.collect.TreeMultiset.Aggregate.2
            @Override // com.google.common.collect.TreeMultiset.Aggregate
            int nodeAggregate(AvlNode<?> avlNode) {
                return 1;
            }

            @Override // com.google.common.collect.TreeMultiset.Aggregate
            long treeAggregate(@NullableDecl AvlNode<?> avlNode) {
                if (avlNode == null) {
                    return 0L;
                }
                return ((AvlNode) avlNode).distinctElements;
            }
        };

        abstract int nodeAggregate(AvlNode<?> avlNode);

        abstract long treeAggregate(@NullableDecl AvlNode<?> avlNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class Reference<T> {
        @NullableDecl
        private T value;

        private Reference() {
        }

        public void checkAndSet(@NullableDecl T t, T t2) {
            if (this.value == t) {
                this.value = t2;
                return;
            }
            throw new ConcurrentModificationException();
        }

        @NullableDecl
        public T get() {
            return this.value;
        }
    }

    TreeMultiset(Reference<AvlNode<E>> reference, GeneralRange<E> generalRange, AvlNode<E> avlNode) {
        super(generalRange.comparator());
        this.rootReference = reference;
        this.range = generalRange;
        this.header = avlNode;
    }

    private long aggregateAboveRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long treeAggregate;
        long aggregateAboveRange;
        if (avlNode == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.getUpperEndpoint(), ((AvlNode) avlNode).elem);
        if (compare > 0) {
            return aggregateAboveRange(aggregate, ((AvlNode) avlNode).right);
        }
        if (compare == 0) {
            int i2 = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.getUpperBoundType().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return aggregate.treeAggregate(((AvlNode) avlNode).right);
                }
                throw new AssertionError();
            }
            treeAggregate = aggregate.nodeAggregate(avlNode);
            aggregateAboveRange = aggregate.treeAggregate(((AvlNode) avlNode).right);
        } else {
            treeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).right) + aggregate.nodeAggregate(avlNode);
            aggregateAboveRange = aggregateAboveRange(aggregate, ((AvlNode) avlNode).left);
        }
        return treeAggregate + aggregateAboveRange;
    }

    private long aggregateBelowRange(Aggregate aggregate, @NullableDecl AvlNode<E> avlNode) {
        long treeAggregate;
        long aggregateBelowRange;
        if (avlNode == null) {
            return 0L;
        }
        int compare = comparator().compare(this.range.getLowerEndpoint(), ((AvlNode) avlNode).elem);
        if (compare < 0) {
            return aggregateBelowRange(aggregate, ((AvlNode) avlNode).left);
        }
        if (compare == 0) {
            int i2 = AnonymousClass4.$SwitchMap$com$google$common$collect$BoundType[this.range.getLowerBoundType().ordinal()];
            if (i2 != 1) {
                if (i2 == 2) {
                    return aggregate.treeAggregate(((AvlNode) avlNode).left);
                }
                throw new AssertionError();
            }
            treeAggregate = aggregate.nodeAggregate(avlNode);
            aggregateBelowRange = aggregate.treeAggregate(((AvlNode) avlNode).left);
        } else {
            treeAggregate = aggregate.treeAggregate(((AvlNode) avlNode).left) + aggregate.nodeAggregate(avlNode);
            aggregateBelowRange = aggregateBelowRange(aggregate, ((AvlNode) avlNode).right);
        }
        return treeAggregate + aggregateBelowRange;
    }

    private long aggregateForEntries(Aggregate aggregate) {
        AvlNode<E> avlNode = this.rootReference.get();
        long treeAggregate = aggregate.treeAggregate(avlNode);
        if (this.range.hasLowerBound()) {
            treeAggregate -= aggregateBelowRange(aggregate, avlNode);
        }
        return this.range.hasUpperBound() ? treeAggregate - aggregateAboveRange(aggregate, avlNode) : treeAggregate;
    }

    public static <E extends Comparable> TreeMultiset<E> create() {
        return new TreeMultiset<>(Ordering.natural());
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> firstNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (!this.range.hasLowerBound()) {
            avlNode = ((AvlNode) this.header).succ;
        } else {
            E lowerEndpoint = this.range.getLowerEndpoint();
            avlNode = this.rootReference.get().ceiling(comparator(), lowerEndpoint);
            if (avlNode == null) {
                return null;
            }
            if (this.range.getLowerBoundType() == BoundType.OPEN && comparator().compare(lowerEndpoint, avlNode.getElement()) == 0) {
                avlNode = ((AvlNode) avlNode).succ;
            }
        }
        if (avlNode == this.header || !this.range.contains(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NullableDecl
    public AvlNode<E> lastNode() {
        AvlNode<E> avlNode;
        if (this.rootReference.get() == null) {
            return null;
        }
        if (!this.range.hasUpperBound()) {
            avlNode = ((AvlNode) this.header).pred;
        } else {
            E upperEndpoint = this.range.getUpperEndpoint();
            avlNode = this.rootReference.get().floor(comparator(), upperEndpoint);
            if (avlNode == null) {
                return null;
            }
            if (this.range.getUpperBoundType() == BoundType.OPEN && comparator().compare(upperEndpoint, avlNode.getElement()) == 0) {
                avlNode = ((AvlNode) avlNode).pred;
            }
        }
        if (avlNode == this.header || !this.range.contains(avlNode.getElement())) {
            return null;
        }
        return avlNode;
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator comparator = (Comparator) objectInputStream.readObject();
        Serialization.getFieldSetter(AbstractSortedMultiset.class, "comparator").set((Serialization.FieldSetter) this, (Object) comparator);
        Serialization.getFieldSetter(TreeMultiset.class, "range").set((Serialization.FieldSetter) this, (Object) GeneralRange.all(comparator));
        Serialization.getFieldSetter(TreeMultiset.class, "rootReference").set((Serialization.FieldSetter) this, (Object) new Reference());
        AvlNode avlNode = new AvlNode(null, 1);
        Serialization.getFieldSetter(TreeMultiset.class, "header").set((Serialization.FieldSetter) this, (Object) avlNode);
        successor(avlNode, avlNode);
        Serialization.populateMultiset(this, objectInputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2) {
        ((AvlNode) avlNode).succ = avlNode2;
        ((AvlNode) avlNode2).pred = avlNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Multiset.Entry<E> wrapEntry(final AvlNode<E> avlNode) {
        return new Multisets.AbstractEntry<E>() { // from class: com.google.common.collect.TreeMultiset.1
            @Override // com.google.common.collect.Multiset.Entry
            public int getCount() {
                int count = avlNode.getCount();
                return count == 0 ? TreeMultiset.this.count(getElement()) : count;
            }

            @Override // com.google.common.collect.Multiset.Entry
            public E getElement() {
                return (E) avlNode.getElement();
            }
        };
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(elementSet().comparator());
        Serialization.writeMultiset(this, objectOutputStream);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean add(@NullableDecl Object obj) {
        return super.add(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean addAll(Collection collection) {
        return super.addAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public /* bridge */ /* synthetic */ Comparator comparator() {
        return super.comparator();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean contains(@NullableDecl Object obj) {
        return super.contains(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        try {
            AvlNode<E> avlNode = this.rootReference.get();
            if (this.range.contains(obj) && avlNode != null) {
                return avlNode.count(comparator(), obj);
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    @Override // com.google.common.collect.AbstractSortedMultiset
    Iterator<Multiset.Entry<E>> descendingEntryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.3
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry = null;

            {
                this.current = TreeMultiset.this.lastNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (TreeMultiset.this.range.tooLow(this.current.getElement())) {
                    this.current = null;
                    return false;
                }
                return true;
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.current);
                    this.prevEntry = wrapEntry;
                    if (((AvlNode) this.current).pred != TreeMultiset.this.header) {
                        this.current = ((AvlNode) this.current).pred;
                    } else {
                        this.current = null;
                    }
                    return wrapEntry;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset descendingMultiset() {
        return super.descendingMultiset();
    }

    @Override // com.google.common.collect.AbstractMultiset
    int distinctElements() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.DISTINCT));
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ NavigableSet elementSet() {
        return super.elementSet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    public Iterator<Multiset.Entry<E>> entryIterator() {
        return new Iterator<Multiset.Entry<E>>() { // from class: com.google.common.collect.TreeMultiset.2
            AvlNode<E> current;
            Multiset.Entry<E> prevEntry;

            {
                this.current = TreeMultiset.this.firstNode();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.current == null) {
                    return false;
                }
                if (TreeMultiset.this.range.tooHigh(this.current.getElement())) {
                    this.current = null;
                    return false;
                }
                return true;
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.prevEntry != null);
                TreeMultiset.this.setCount(this.prevEntry.getElement(), 0);
                this.prevEntry = null;
            }

            @Override // java.util.Iterator
            public Multiset.Entry<E> next() {
                if (hasNext()) {
                    Multiset.Entry<E> wrapEntry = TreeMultiset.this.wrapEntry(this.current);
                    this.prevEntry = wrapEntry;
                    if (((AvlNode) this.current).succ != TreeMultiset.this.header) {
                        this.current = ((AvlNode) this.current).succ;
                    } else {
                        this.current = null;
                    }
                    return wrapEntry;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Set entrySet() {
        return super.entrySet();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry firstEntry() {
        return super.firstEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.Collection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> headMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.upTo(comparator(), e2, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection
    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry lastEntry() {
        return super.lastEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollFirstEntry() {
        return super.pollFirstEntry();
    }

    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ Multiset.Entry pollLastEntry() {
        return super.pollLastEntry();
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean remove(@NullableDecl Object obj) {
        return super.remove(obj);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e2, int i2) {
        CollectPreconditions.checkNonnegative(i2, "count");
        if (!this.range.contains(e2)) {
            Preconditions.checkArgument(i2 == 0);
            return 0;
        }
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            if (i2 > 0) {
                add(e2, i2);
            }
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e2, i2, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return Ints.saturatedCast(aggregateForEntries(Aggregate.SIZE));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractSortedMultiset, com.google.common.collect.SortedMultiset
    public /* bridge */ /* synthetic */ SortedMultiset subMultiset(@NullableDecl Object obj, BoundType boundType, @NullableDecl Object obj2, BoundType boundType2) {
        return super.subMultiset(obj, boundType, obj2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    public SortedMultiset<E> tailMultiset(@NullableDecl E e2, BoundType boundType) {
        return new TreeMultiset(this.rootReference, this.range.intersect(GeneralRange.downTo(comparator(), e2, boundType)), this.header);
    }

    @Override // com.google.common.collect.AbstractMultiset, java.util.AbstractCollection, com.google.common.collect.Multiset
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public static <E> TreeMultiset<E> create(@NullableDecl Comparator<? super E> comparator) {
        return comparator == null ? new TreeMultiset<>(Ordering.natural()) : new TreeMultiset<>(comparator);
    }

    static int distinctElements(@NullableDecl AvlNode<?> avlNode) {
        if (avlNode == null) {
            return 0;
        }
        return ((AvlNode) avlNode).distinctElements;
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(@NullableDecl E e2, int i2) {
        CollectPreconditions.checkNonnegative(i2, "occurrences");
        if (i2 == 0) {
            return count(e2);
        }
        Preconditions.checkArgument(this.range.contains(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode == null) {
            comparator().compare(e2, e2);
            AvlNode<E> avlNode2 = new AvlNode<>(e2, i2);
            AvlNode<E> avlNode3 = this.header;
            successor(avlNode3, avlNode2, avlNode3);
            this.rootReference.checkAndSet(avlNode, avlNode2);
            return 0;
        }
        int[] iArr = new int[1];
        this.rootReference.checkAndSet(avlNode, avlNode.add(comparator(), e2, i2, iArr));
        return iArr[0];
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i2) {
        CollectPreconditions.checkNonnegative(i2, "occurrences");
        if (i2 == 0) {
            return count(obj);
        }
        AvlNode<E> avlNode = this.rootReference.get();
        int[] iArr = new int[1];
        try {
            if (this.range.contains(obj) && avlNode != null) {
                this.rootReference.checkAndSet(avlNode, avlNode.remove(comparator(), obj, i2, iArr));
                return iArr[0];
            }
        } catch (ClassCastException | NullPointerException unused) {
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> void successor(AvlNode<T> avlNode, AvlNode<T> avlNode2, AvlNode<T> avlNode3) {
        successor(avlNode, avlNode2);
        successor(avlNode2, avlNode3);
    }

    public static <E extends Comparable> TreeMultiset<E> create(Iterable<? extends E> iterable) {
        TreeMultiset<E> create = create();
        Iterables.addAll(create, iterable);
        return create;
    }

    TreeMultiset(Comparator<? super E> comparator) {
        super(comparator);
        this.range = GeneralRange.all(comparator);
        AvlNode<E> avlNode = new AvlNode<>(null, 1);
        this.header = avlNode;
        successor(avlNode, avlNode);
        this.rootReference = new Reference<>();
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E e2, int i2, int i3) {
        CollectPreconditions.checkNonnegative(i3, "newCount");
        CollectPreconditions.checkNonnegative(i2, "oldCount");
        Preconditions.checkArgument(this.range.contains(e2));
        AvlNode<E> avlNode = this.rootReference.get();
        if (avlNode != null) {
            int[] iArr = new int[1];
            this.rootReference.checkAndSet(avlNode, avlNode.setCount(comparator(), e2, i2, i3, iArr));
            return iArr[0] == i2;
        } else if (i2 == 0) {
            if (i3 > 0) {
                add(e2, i3);
            }
            return true;
        } else {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static final class AvlNode<E> extends Multisets.AbstractEntry<E> {
        private int distinctElements;
        @NullableDecl
        private final E elem;
        private int elemCount;
        private int height;
        private AvlNode<E> left;
        private AvlNode<E> pred;
        private AvlNode<E> right;
        private AvlNode<E> succ;
        private long totalCount;

        AvlNode(@NullableDecl E e2, int i2) {
            Preconditions.checkArgument(i2 > 0);
            this.elem = e2;
            this.elemCount = i2;
            this.totalCount = i2;
            this.distinctElements = 1;
            this.height = 1;
            this.left = null;
            this.right = null;
        }

        private AvlNode<E> addLeftChild(E e2, int i2) {
            AvlNode<E> avlNode = new AvlNode<>(e2, i2);
            this.left = avlNode;
            TreeMultiset.successor(this.pred, avlNode, this);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i2;
            return this;
        }

        private AvlNode<E> addRightChild(E e2, int i2) {
            AvlNode<E> avlNode = new AvlNode<>(e2, i2);
            this.right = avlNode;
            TreeMultiset.successor(this, avlNode, this.succ);
            this.height = Math.max(2, this.height);
            this.distinctElements++;
            this.totalCount += i2;
            return this;
        }

        private int balanceFactor() {
            return height(this.left) - height(this.right);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> ceiling(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.ceiling(comparator, e2), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.ceiling(comparator, e2);
            }
        }

        private AvlNode<E> deleteMe() {
            int i2 = this.elemCount;
            this.elemCount = 0;
            TreeMultiset.successor(this.pred, this.succ);
            AvlNode<E> avlNode = this.left;
            if (avlNode == null) {
                return this.right;
            }
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return avlNode;
            }
            if (avlNode.height >= avlNode2.height) {
                AvlNode<E> avlNode3 = this.pred;
                avlNode3.left = avlNode.removeMax(avlNode3);
                avlNode3.right = this.right;
                avlNode3.distinctElements = this.distinctElements - 1;
                avlNode3.totalCount = this.totalCount - i2;
                return avlNode3.rebalance();
            }
            AvlNode<E> avlNode4 = this.succ;
            avlNode4.right = avlNode2.removeMin(avlNode4);
            avlNode4.left = this.left;
            avlNode4.distinctElements = this.distinctElements - 1;
            avlNode4.totalCount = this.totalCount - i2;
            return avlNode4.rebalance();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        @NullableDecl
        public AvlNode<E> floor(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare > 0) {
                AvlNode<E> avlNode = this.right;
                return avlNode == null ? this : (AvlNode) MoreObjects.firstNonNull(avlNode.floor(comparator, e2), this);
            } else if (compare == 0) {
                return this;
            } else {
                AvlNode<E> avlNode2 = this.left;
                if (avlNode2 == null) {
                    return null;
                }
                return avlNode2.floor(comparator, e2);
            }
        }

        private static int height(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0;
            }
            return ((AvlNode) avlNode).height;
        }

        private AvlNode<E> rebalance() {
            int balanceFactor = balanceFactor();
            if (balanceFactor == -2) {
                if (this.right.balanceFactor() > 0) {
                    this.right = this.right.rotateRight();
                }
                return rotateLeft();
            } else if (balanceFactor != 2) {
                recomputeHeight();
                return this;
            } else {
                if (this.left.balanceFactor() < 0) {
                    this.left = this.left.rotateLeft();
                }
                return rotateRight();
            }
        }

        private void recompute() {
            recomputeMultiset();
            recomputeHeight();
        }

        private void recomputeHeight() {
            this.height = Math.max(height(this.left), height(this.right)) + 1;
        }

        private void recomputeMultiset() {
            this.distinctElements = TreeMultiset.distinctElements(this.left) + 1 + TreeMultiset.distinctElements(this.right);
            this.totalCount = this.elemCount + totalCount(this.left) + totalCount(this.right);
        }

        private AvlNode<E> removeMax(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.right;
            if (avlNode2 == null) {
                return this.left;
            }
            this.right = avlNode2.removeMax(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> removeMin(AvlNode<E> avlNode) {
            AvlNode<E> avlNode2 = this.left;
            if (avlNode2 == null) {
                return this.right;
            }
            this.left = avlNode2.removeMin(avlNode);
            this.distinctElements--;
            this.totalCount -= avlNode.elemCount;
            return rebalance();
        }

        private AvlNode<E> rotateLeft() {
            Preconditions.checkState(this.right != null);
            AvlNode<E> avlNode = this.right;
            this.right = avlNode.left;
            avlNode.left = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private AvlNode<E> rotateRight() {
            Preconditions.checkState(this.left != null);
            AvlNode<E> avlNode = this.left;
            this.left = avlNode.right;
            avlNode.right = this;
            avlNode.totalCount = this.totalCount;
            avlNode.distinctElements = this.distinctElements;
            recompute();
            avlNode.recomputeHeight();
            return avlNode;
        }

        private static long totalCount(@NullableDecl AvlNode<?> avlNode) {
            if (avlNode == null) {
                return 0L;
            }
            return ((AvlNode) avlNode).totalCount;
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> add(Comparator<? super E> comparator, @NullableDecl E e2, int i2, int[] iArr) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return addLeftChild(e2, i2);
                }
                int i3 = avlNode.height;
                AvlNode<E> add = avlNode.add(comparator, e2, i2, iArr);
                this.left = add;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2;
                return add.height == i3 ? this : rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return addRightChild(e2, i2);
                }
                int i4 = avlNode2.height;
                AvlNode<E> add2 = avlNode2.add(comparator, e2, i2, iArr);
                this.right = add2;
                if (iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2;
                return add2.height == i4 ? this : rebalance();
            } else {
                int i5 = this.elemCount;
                iArr[0] = i5;
                long j2 = i2;
                Preconditions.checkArgument(((long) i5) + j2 <= 2147483647L);
                this.elemCount += i2;
                this.totalCount += j2;
                return this;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int count(Comparator<? super E> comparator, E e2) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    return 0;
                }
                return avlNode.count(comparator, e2);
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    return 0;
                }
                return avlNode2.count(comparator, e2);
            } else {
                return this.elemCount;
            }
        }

        @Override // com.google.common.collect.Multiset.Entry
        public int getCount() {
            return this.elemCount;
        }

        @Override // com.google.common.collect.Multiset.Entry
        public E getElement() {
            return this.elem;
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> remove(Comparator<? super E> comparator, @NullableDecl E e2, int i2, int[] iArr) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.left = avlNode.remove(comparator, e2, i2, iArr);
                if (iArr[0] > 0) {
                    if (i2 >= iArr[0]) {
                        this.distinctElements--;
                        this.totalCount -= iArr[0];
                    } else {
                        this.totalCount -= i2;
                    }
                }
                return iArr[0] == 0 ? this : rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return this;
                }
                this.right = avlNode2.remove(comparator, e2, i2, iArr);
                if (iArr[0] > 0) {
                    if (i2 >= iArr[0]) {
                        this.distinctElements--;
                        this.totalCount -= iArr[0];
                    } else {
                        this.totalCount -= i2;
                    }
                }
                return rebalance();
            } else {
                int i3 = this.elemCount;
                iArr[0] = i3;
                if (i2 >= i3) {
                    return deleteMe();
                }
                this.elemCount = i3 - i2;
                this.totalCount -= i2;
                return this;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> setCount(Comparator<? super E> comparator, @NullableDecl E e2, int i2, int[] iArr) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return i2 > 0 ? addLeftChild(e2, i2) : this;
                }
                this.left = avlNode.setCount(comparator, e2, i2, iArr);
                if (i2 == 0 && iArr[0] != 0) {
                    this.distinctElements--;
                } else if (i2 > 0 && iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2 - iArr[0];
                return rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return i2 > 0 ? addRightChild(e2, i2) : this;
                }
                this.right = avlNode2.setCount(comparator, e2, i2, iArr);
                if (i2 == 0 && iArr[0] != 0) {
                    this.distinctElements--;
                } else if (i2 > 0 && iArr[0] == 0) {
                    this.distinctElements++;
                }
                this.totalCount += i2 - iArr[0];
                return rebalance();
            } else {
                iArr[0] = this.elemCount;
                if (i2 == 0) {
                    return deleteMe();
                }
                this.totalCount += i2 - r3;
                this.elemCount = i2;
                return this;
            }
        }

        @Override // com.google.common.collect.Multisets.AbstractEntry, com.google.common.collect.Multiset.Entry
        public String toString() {
            return Multisets.immutableEntry(getElement(), getCount()).toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        AvlNode<E> setCount(Comparator<? super E> comparator, @NullableDecl E e2, int i2, int i3, int[] iArr) {
            int compare = comparator.compare(e2, (E) this.elem);
            if (compare < 0) {
                AvlNode<E> avlNode = this.left;
                if (avlNode == null) {
                    iArr[0] = 0;
                    return (i2 != 0 || i3 <= 0) ? this : addLeftChild(e2, i3);
                }
                this.left = avlNode.setCount(comparator, e2, i2, i3, iArr);
                if (iArr[0] == i2) {
                    if (i3 == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i3 > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += i3 - iArr[0];
                }
                return rebalance();
            } else if (compare > 0) {
                AvlNode<E> avlNode2 = this.right;
                if (avlNode2 == null) {
                    iArr[0] = 0;
                    return (i2 != 0 || i3 <= 0) ? this : addRightChild(e2, i3);
                }
                this.right = avlNode2.setCount(comparator, e2, i2, i3, iArr);
                if (iArr[0] == i2) {
                    if (i3 == 0 && iArr[0] != 0) {
                        this.distinctElements--;
                    } else if (i3 > 0 && iArr[0] == 0) {
                        this.distinctElements++;
                    }
                    this.totalCount += i3 - iArr[0];
                }
                return rebalance();
            } else {
                int i4 = this.elemCount;
                iArr[0] = i4;
                if (i2 == i4) {
                    if (i3 == 0) {
                        return deleteMe();
                    }
                    this.totalCount += i3 - i4;
                    this.elemCount = i3;
                }
                return this;
            }
        }
    }
}
