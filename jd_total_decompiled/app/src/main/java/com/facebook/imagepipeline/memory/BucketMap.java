package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.LinkedList;
import javax.annotation.Nullable;

@ThreadSafe
/* loaded from: classes.dex */
public class BucketMap<T> {
    @VisibleForTesting
    @Nullable
    LinkedEntry<T> mHead;
    protected final SparseArray<LinkedEntry<T>> mMap = new SparseArray<>();
    @VisibleForTesting
    @Nullable
    LinkedEntry<T> mTail;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes.dex */
    public static class LinkedEntry<I> {
        int key;
        @Nullable
        LinkedEntry<I> next;
        @Nullable
        LinkedEntry<I> prev;
        LinkedList<I> value;

        private LinkedEntry(@Nullable LinkedEntry<I> linkedEntry, int i2, LinkedList<I> linkedList, @Nullable LinkedEntry<I> linkedEntry2) {
            this.prev = linkedEntry;
            this.key = i2;
            this.value = linkedList;
            this.next = linkedEntry2;
        }

        public String toString() {
            return "LinkedEntry(key: " + this.key + ")";
        }
    }

    private void maybePrune(LinkedEntry<T> linkedEntry) {
        if (linkedEntry == null || !linkedEntry.value.isEmpty()) {
            return;
        }
        prune(linkedEntry);
        this.mMap.remove(linkedEntry.key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void moveToFront(LinkedEntry<T> linkedEntry) {
        if (this.mHead == linkedEntry) {
            return;
        }
        prune(linkedEntry);
        LinkedEntry linkedEntry2 = (LinkedEntry<T>) this.mHead;
        if (linkedEntry2 == null) {
            this.mHead = linkedEntry;
            this.mTail = linkedEntry;
            return;
        }
        linkedEntry.next = linkedEntry2;
        linkedEntry2.prev = linkedEntry;
        this.mHead = linkedEntry;
    }

    private synchronized void prune(LinkedEntry<T> linkedEntry) {
        LinkedEntry linkedEntry2 = (LinkedEntry<T>) linkedEntry.prev;
        LinkedEntry linkedEntry3 = (LinkedEntry<T>) linkedEntry.next;
        if (linkedEntry2 != null) {
            linkedEntry2.next = linkedEntry3;
        }
        if (linkedEntry3 != null) {
            linkedEntry3.prev = linkedEntry2;
        }
        linkedEntry.prev = null;
        linkedEntry.next = null;
        if (linkedEntry == this.mHead) {
            this.mHead = linkedEntry3;
        }
        if (linkedEntry == this.mTail) {
            this.mTail = linkedEntry2;
        }
    }

    @Nullable
    public synchronized T acquire(int i2) {
        LinkedEntry<T> linkedEntry = this.mMap.get(i2);
        if (linkedEntry == null) {
            return null;
        }
        T pollFirst = linkedEntry.value.pollFirst();
        moveToFront(linkedEntry);
        return pollFirst;
    }

    public synchronized void release(int i2, T t) {
        LinkedEntry<T> linkedEntry = this.mMap.get(i2);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(null, i2, new LinkedList(), null);
            this.mMap.put(i2, linkedEntry);
        }
        linkedEntry.value.addLast(t);
        moveToFront(linkedEntry);
    }

    @Nullable
    public synchronized T removeFromEnd() {
        LinkedEntry<T> linkedEntry = this.mTail;
        if (linkedEntry == null) {
            return null;
        }
        T pollLast = linkedEntry.value.pollLast();
        maybePrune(linkedEntry);
        return pollLast;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized int valueCount() {
        int i2;
        i2 = 0;
        for (LinkedEntry linkedEntry = this.mHead; linkedEntry != null; linkedEntry = linkedEntry.next) {
            LinkedList<I> linkedList = linkedEntry.value;
            if (linkedList != 0) {
                i2 += linkedList.size();
            }
        }
        return i2;
    }
}
