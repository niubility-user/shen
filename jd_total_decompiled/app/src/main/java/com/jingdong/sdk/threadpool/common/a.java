package com.jingdong.sdk.threadpool.common;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes10.dex */
public class a<E> extends PriorityBlockingQueue<E> {
    private int capacity;

    public a(int i2) {
        super(i2, null);
        this.capacity = i2;
    }

    public int getCapacity() {
        return this.capacity;
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
    public boolean offer(E e2) {
        if (size() == this.capacity) {
            return false;
        }
        return super.offer(e2);
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.concurrent.BlockingQueue
    public int remainingCapacity() {
        return this.capacity - size();
    }

    public a(int i2, Comparator<? super E> comparator) {
        super(i2, comparator);
        this.capacity = i2;
    }

    @Override // java.util.concurrent.PriorityBlockingQueue, java.util.concurrent.BlockingQueue
    public boolean offer(E e2, long j2, TimeUnit timeUnit) {
        if (size() == this.capacity) {
            return false;
        }
        return super.offer(e2, j2, timeUnit);
    }

    public a(Collection<? extends E> collection) {
        super(collection);
        this.capacity = collection.size();
    }
}
