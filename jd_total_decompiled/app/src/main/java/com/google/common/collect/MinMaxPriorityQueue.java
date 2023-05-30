package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.math.IntMath;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.Weak;
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

@Beta
@GwtCompatible
/* loaded from: classes12.dex */
public final class MinMaxPriorityQueue<E> extends AbstractQueue<E> {
    private static final int DEFAULT_CAPACITY = 11;
    private static final int EVEN_POWERS_OF_TWO = 1431655765;
    private static final int ODD_POWERS_OF_TWO = -1431655766;
    private final MinMaxPriorityQueue<E>.Heap maxHeap;
    @VisibleForTesting
    final int maximumSize;
    private final MinMaxPriorityQueue<E>.Heap minHeap;
    private int modCount;
    private Object[] queue;
    private int size;

    @Beta
    /* loaded from: classes12.dex */
    public static final class Builder<B> {
        private static final int UNSET_EXPECTED_SIZE = -1;
        private final Comparator<B> comparator;
        private int expectedSize;
        private int maximumSize;

        /* JADX INFO: Access modifiers changed from: private */
        public <T extends B> Ordering<T> ordering() {
            return Ordering.from(this.comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create() {
            return create(Collections.emptySet());
        }

        @CanIgnoreReturnValue
        public Builder<B> expectedSize(int i2) {
            Preconditions.checkArgument(i2 >= 0);
            this.expectedSize = i2;
            return this;
        }

        @CanIgnoreReturnValue
        public Builder<B> maximumSize(int i2) {
            Preconditions.checkArgument(i2 > 0);
            this.maximumSize = i2;
            return this;
        }

        private Builder(Comparator<B> comparator) {
            this.expectedSize = -1;
            this.maximumSize = Integer.MAX_VALUE;
            this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
        }

        public <T extends B> MinMaxPriorityQueue<T> create(Iterable<? extends T> iterable) {
            MinMaxPriorityQueue<T> minMaxPriorityQueue = new MinMaxPriorityQueue<>(this, MinMaxPriorityQueue.initialQueueSize(this.expectedSize, this.maximumSize, iterable));
            Iterator<? extends T> it = iterable.iterator();
            while (it.hasNext()) {
                minMaxPriorityQueue.offer(it.next());
            }
            return minMaxPriorityQueue;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class Heap {
        final Ordering<E> ordering;
        @Weak
        MinMaxPriorityQueue<E>.Heap otherHeap;

        Heap(Ordering<E> ordering) {
            this.ordering = ordering;
        }

        private int getGrandparentIndex(int i2) {
            return getParentIndex(getParentIndex(i2));
        }

        private int getLeftChildIndex(int i2) {
            return (i2 * 2) + 1;
        }

        private int getParentIndex(int i2) {
            return (i2 - 1) / 2;
        }

        private int getRightChildIndex(int i2) {
            return (i2 * 2) + 2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean verifyIndex(int i2) {
            if (getLeftChildIndex(i2) >= MinMaxPriorityQueue.this.size || compareElements(i2, getLeftChildIndex(i2)) <= 0) {
                if (getRightChildIndex(i2) >= MinMaxPriorityQueue.this.size || compareElements(i2, getRightChildIndex(i2)) <= 0) {
                    if (i2 <= 0 || compareElements(i2, getParentIndex(i2)) <= 0) {
                        return i2 <= 2 || compareElements(getGrandparentIndex(i2), i2) <= 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        void bubbleUp(int i2, E e2) {
            Heap heap;
            int crossOverUp = crossOverUp(i2, e2);
            if (crossOverUp == i2) {
                crossOverUp = i2;
                heap = this;
            } else {
                heap = this.otherHeap;
            }
            heap.bubbleUpAlternatingLevels(crossOverUp, e2);
        }

        @CanIgnoreReturnValue
        int bubbleUpAlternatingLevels(int i2, E e2) {
            while (i2 > 2) {
                int grandparentIndex = getGrandparentIndex(i2);
                Object elementData = MinMaxPriorityQueue.this.elementData(grandparentIndex);
                if (((Ordering<E>) this.ordering).compare(elementData, e2) <= 0) {
                    break;
                }
                MinMaxPriorityQueue.this.queue[i2] = elementData;
                i2 = grandparentIndex;
            }
            MinMaxPriorityQueue.this.queue[i2] = e2;
            return i2;
        }

        int compareElements(int i2, int i3) {
            return ((Ordering<E>) this.ordering).compare(MinMaxPriorityQueue.this.elementData(i2), MinMaxPriorityQueue.this.elementData(i3));
        }

        int crossOver(int i2, E e2) {
            int findMinChild = findMinChild(i2);
            if (findMinChild > 0 && ((Ordering<E>) this.ordering).compare(MinMaxPriorityQueue.this.elementData(findMinChild), e2) < 0) {
                MinMaxPriorityQueue.this.queue[i2] = MinMaxPriorityQueue.this.elementData(findMinChild);
                MinMaxPriorityQueue.this.queue[findMinChild] = e2;
                return findMinChild;
            }
            return crossOverUp(i2, e2);
        }

        int crossOverUp(int i2, E e2) {
            int rightChildIndex;
            if (i2 == 0) {
                MinMaxPriorityQueue.this.queue[0] = e2;
                return 0;
            }
            int parentIndex = getParentIndex(i2);
            Object elementData = MinMaxPriorityQueue.this.elementData(parentIndex);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object elementData2 = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (((Ordering<E>) this.ordering).compare(elementData2, elementData) < 0) {
                    parentIndex = rightChildIndex;
                    elementData = elementData2;
                }
            }
            if (((Ordering<E>) this.ordering).compare(elementData, e2) < 0) {
                MinMaxPriorityQueue.this.queue[i2] = elementData;
                MinMaxPriorityQueue.this.queue[parentIndex] = e2;
                return parentIndex;
            }
            MinMaxPriorityQueue.this.queue[i2] = e2;
            return i2;
        }

        int fillHoleAt(int i2) {
            while (true) {
                int findMinGrandChild = findMinGrandChild(i2);
                if (findMinGrandChild <= 0) {
                    return i2;
                }
                MinMaxPriorityQueue.this.queue[i2] = MinMaxPriorityQueue.this.elementData(findMinGrandChild);
                i2 = findMinGrandChild;
            }
        }

        int findMin(int i2, int i3) {
            if (i2 >= MinMaxPriorityQueue.this.size) {
                return -1;
            }
            Preconditions.checkState(i2 > 0);
            int min = Math.min(i2, MinMaxPriorityQueue.this.size - i3) + i3;
            for (int i4 = i2 + 1; i4 < min; i4++) {
                if (compareElements(i4, i2) < 0) {
                    i2 = i4;
                }
            }
            return i2;
        }

        int findMinChild(int i2) {
            return findMin(getLeftChildIndex(i2), 2);
        }

        int findMinGrandChild(int i2) {
            int leftChildIndex = getLeftChildIndex(i2);
            if (leftChildIndex < 0) {
                return -1;
            }
            return findMin(getLeftChildIndex(leftChildIndex), 4);
        }

        int swapWithConceptuallyLastElement(E e2) {
            int rightChildIndex;
            int parentIndex = getParentIndex(MinMaxPriorityQueue.this.size);
            if (parentIndex != 0 && (rightChildIndex = getRightChildIndex(getParentIndex(parentIndex))) != parentIndex && getLeftChildIndex(rightChildIndex) >= MinMaxPriorityQueue.this.size) {
                Object elementData = MinMaxPriorityQueue.this.elementData(rightChildIndex);
                if (((Ordering<E>) this.ordering).compare(elementData, e2) < 0) {
                    MinMaxPriorityQueue.this.queue[rightChildIndex] = e2;
                    MinMaxPriorityQueue.this.queue[MinMaxPriorityQueue.this.size] = elementData;
                    return rightChildIndex;
                }
            }
            return MinMaxPriorityQueue.this.size;
        }

        MoveDesc<E> tryCrossOverAndBubbleUp(int i2, int i3, E e2) {
            Object elementData;
            int crossOver = crossOver(i3, e2);
            if (crossOver == i3) {
                return null;
            }
            if (crossOver < i2) {
                elementData = MinMaxPriorityQueue.this.elementData(i2);
            } else {
                elementData = MinMaxPriorityQueue.this.elementData(getParentIndex(i2));
            }
            if (this.otherHeap.bubbleUpAlternatingLevels(crossOver, e2) < i2) {
                return new MoveDesc<>(e2, elementData);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class MoveDesc<E> {
        final E replaced;
        final E toTrickle;

        MoveDesc(E e2, E e3) {
            this.toTrickle = e2;
            this.replaced = e3;
        }
    }

    /* loaded from: classes12.dex */
    private class QueueIterator implements Iterator<E> {
        private boolean canRemove;
        private int cursor;
        private int expectedModCount;
        private Queue<E> forgetMeNot;
        private E lastFromForgetMeNot;
        private int nextCursor;
        private List<E> skipMe;

        private QueueIterator() {
            this.cursor = -1;
            this.nextCursor = -1;
            this.expectedModCount = MinMaxPriorityQueue.this.modCount;
        }

        private void checkModCount() {
            if (MinMaxPriorityQueue.this.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        private boolean foundAndRemovedExactReference(Iterable<E> iterable, E e2) {
            Iterator<E> it = iterable.iterator();
            while (it.hasNext()) {
                if (it.next() == e2) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void nextNotInSkipMe(int i2) {
            if (this.nextCursor < i2) {
                if (this.skipMe != null) {
                    while (i2 < MinMaxPriorityQueue.this.size() && foundAndRemovedExactReference(this.skipMe, MinMaxPriorityQueue.this.elementData(i2))) {
                        i2++;
                    }
                }
                this.nextCursor = i2;
            }
        }

        private boolean removeExact(Object obj) {
            for (int i2 = 0; i2 < MinMaxPriorityQueue.this.size; i2++) {
                if (MinMaxPriorityQueue.this.queue[i2] == obj) {
                    MinMaxPriorityQueue.this.removeAt(i2);
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor >= MinMaxPriorityQueue.this.size()) {
                Queue<E> queue = this.forgetMeNot;
                return (queue == null || queue.isEmpty()) ? false : true;
            }
            return true;
        }

        @Override // java.util.Iterator
        public E next() {
            checkModCount();
            nextNotInSkipMe(this.cursor + 1);
            if (this.nextCursor < MinMaxPriorityQueue.this.size()) {
                int i2 = this.nextCursor;
                this.cursor = i2;
                this.canRemove = true;
                return (E) MinMaxPriorityQueue.this.elementData(i2);
            }
            if (this.forgetMeNot != null) {
                this.cursor = MinMaxPriorityQueue.this.size();
                E poll = this.forgetMeNot.poll();
                this.lastFromForgetMeNot = poll;
                if (poll != null) {
                    this.canRemove = true;
                    return poll;
                }
            }
            throw new NoSuchElementException("iterator moved past last element in queue.");
        }

        @Override // java.util.Iterator
        public void remove() {
            CollectPreconditions.checkRemove(this.canRemove);
            checkModCount();
            this.canRemove = false;
            this.expectedModCount++;
            if (this.cursor < MinMaxPriorityQueue.this.size()) {
                MoveDesc<E> removeAt = MinMaxPriorityQueue.this.removeAt(this.cursor);
                if (removeAt != null) {
                    if (this.forgetMeNot == null) {
                        this.forgetMeNot = new ArrayDeque();
                        this.skipMe = new ArrayList(3);
                    }
                    if (!foundAndRemovedExactReference(this.skipMe, removeAt.toTrickle)) {
                        this.forgetMeNot.add(removeAt.toTrickle);
                    }
                    if (!foundAndRemovedExactReference(this.forgetMeNot, removeAt.replaced)) {
                        this.skipMe.add(removeAt.replaced);
                    }
                }
                this.cursor--;
                this.nextCursor--;
                return;
            }
            Preconditions.checkState(removeExact(this.lastFromForgetMeNot));
            this.lastFromForgetMeNot = null;
        }
    }

    private int calculateNewCapacity() {
        int length = this.queue.length;
        return capAtMaximumSize(length < 64 ? (length + 1) * 2 : IntMath.checkedMultiply(length / 2, 3), this.maximumSize);
    }

    private static int capAtMaximumSize(int i2, int i3) {
        return Math.min(i2 - 1, i3) + 1;
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create() {
        return new Builder(Ordering.natural()).create();
    }

    public static Builder<Comparable> expectedSize(int i2) {
        return new Builder(Ordering.natural()).expectedSize(i2);
    }

    private MoveDesc<E> fillHole(int i2, E e2) {
        MinMaxPriorityQueue<E>.Heap heapForIndex = heapForIndex(i2);
        int fillHoleAt = heapForIndex.fillHoleAt(i2);
        int bubbleUpAlternatingLevels = heapForIndex.bubbleUpAlternatingLevels(fillHoleAt, e2);
        if (bubbleUpAlternatingLevels == fillHoleAt) {
            return heapForIndex.tryCrossOverAndBubbleUp(i2, fillHoleAt, e2);
        }
        if (bubbleUpAlternatingLevels < i2) {
            return new MoveDesc<>(e2, elementData(i2));
        }
        return null;
    }

    private int getMaxElementIndex() {
        int i2 = this.size;
        if (i2 != 1) {
            return (i2 == 2 || this.maxHeap.compareElements(1, 2) <= 0) ? 1 : 2;
        }
        return 0;
    }

    private void growIfNeeded() {
        if (this.size > this.queue.length) {
            Object[] objArr = new Object[calculateNewCapacity()];
            Object[] objArr2 = this.queue;
            System.arraycopy(objArr2, 0, objArr, 0, objArr2.length);
            this.queue = objArr;
        }
    }

    private MinMaxPriorityQueue<E>.Heap heapForIndex(int i2) {
        return isEvenLevel(i2) ? this.minHeap : this.maxHeap;
    }

    @VisibleForTesting
    static int initialQueueSize(int i2, int i3, Iterable<?> iterable) {
        if (i2 == -1) {
            i2 = 11;
        }
        if (iterable instanceof Collection) {
            i2 = Math.max(i2, ((Collection) iterable).size());
        }
        return capAtMaximumSize(i2, i3);
    }

    @VisibleForTesting
    static boolean isEvenLevel(int i2) {
        int i3 = ((i2 + 1) ^ (-1)) ^ (-1);
        Preconditions.checkState(i3 > 0, "negative index");
        return (EVEN_POWERS_OF_TWO & i3) > (i3 & ODD_POWERS_OF_TWO);
    }

    public static Builder<Comparable> maximumSize(int i2) {
        return new Builder(Ordering.natural()).maximumSize(i2);
    }

    public static <B> Builder<B> orderedBy(Comparator<B> comparator) {
        return new Builder<>(comparator);
    }

    private E removeAndGet(int i2) {
        E elementData = elementData(i2);
        removeAt(i2);
        return elementData;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection, java.util.Queue
    @CanIgnoreReturnValue
    public boolean add(E e2) {
        offer(e2);
        return true;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        Iterator<? extends E> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            offer(it.next());
            z = true;
        }
        return z;
    }

    @VisibleForTesting
    int capacity() {
        return this.queue.length;
    }

    @Override // java.util.AbstractQueue, java.util.AbstractCollection, java.util.Collection
    public void clear() {
        for (int i2 = 0; i2 < this.size; i2++) {
            this.queue[i2] = null;
        }
        this.size = 0;
    }

    public Comparator<? super E> comparator() {
        return this.minHeap.ordering;
    }

    E elementData(int i2) {
        return (E) this.queue[i2];
    }

    @VisibleForTesting
    boolean isIntact() {
        for (int i2 = 1; i2 < this.size; i2++) {
            if (!heapForIndex(i2).verifyIndex(i2)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public boolean offer(E e2) {
        Preconditions.checkNotNull(e2);
        this.modCount++;
        int i2 = this.size;
        this.size = i2 + 1;
        growIfNeeded();
        heapForIndex(i2).bubbleUp(i2, e2);
        return this.size <= this.maximumSize || pollLast() != e2;
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return elementData(0);
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return elementData(getMaxElementIndex());
    }

    @Override // java.util.Queue
    @CanIgnoreReturnValue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(0);
    }

    @CanIgnoreReturnValue
    public E pollFirst() {
        return poll();
    }

    @CanIgnoreReturnValue
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        return removeAndGet(getMaxElementIndex());
    }

    @VisibleForTesting
    @CanIgnoreReturnValue
    MoveDesc<E> removeAt(int i2) {
        Preconditions.checkPositionIndex(i2, this.size);
        this.modCount++;
        int i3 = this.size - 1;
        this.size = i3;
        if (i3 == i2) {
            this.queue[i3] = null;
            return null;
        }
        E elementData = elementData(i3);
        int swapWithConceptuallyLastElement = heapForIndex(this.size).swapWithConceptuallyLastElement(elementData);
        if (swapWithConceptuallyLastElement == i2) {
            this.queue[this.size] = null;
            return null;
        }
        E elementData2 = elementData(this.size);
        this.queue[this.size] = null;
        MoveDesc<E> fillHole = fillHole(i2, elementData2);
        if (swapWithConceptuallyLastElement < i2) {
            if (fillHole == null) {
                return new MoveDesc<>(elementData, elementData2);
            }
            return new MoveDesc<>(elementData, fillHole.replaced);
        }
        return fillHole;
    }

    @CanIgnoreReturnValue
    public E removeFirst() {
        return remove();
    }

    @CanIgnoreReturnValue
    public E removeLast() {
        if (!isEmpty()) {
            return removeAndGet(getMaxElementIndex());
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public Object[] toArray() {
        int i2 = this.size;
        Object[] objArr = new Object[i2];
        System.arraycopy(this.queue, 0, objArr, 0, i2);
        return objArr;
    }

    private MinMaxPriorityQueue(Builder<? super E> builder, int i2) {
        Ordering ordering = builder.ordering();
        MinMaxPriorityQueue<E>.Heap heap = new Heap(ordering);
        this.minHeap = heap;
        MinMaxPriorityQueue<E>.Heap heap2 = new Heap(ordering.reverse());
        this.maxHeap = heap2;
        heap.otherHeap = heap2;
        heap2.otherHeap = heap;
        this.maximumSize = ((Builder) builder).maximumSize;
        this.queue = new Object[i2];
    }

    public static <E extends Comparable<E>> MinMaxPriorityQueue<E> create(Iterable<? extends E> iterable) {
        return new Builder(Ordering.natural()).create(iterable);
    }
}
