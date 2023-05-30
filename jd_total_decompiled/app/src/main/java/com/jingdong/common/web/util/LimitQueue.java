package com.jingdong.common.web.util;

import java.util.LinkedList;

/* loaded from: classes12.dex */
public class LimitQueue<E> {
    private int limit;
    private LinkedList<E> queue = new LinkedList<>();

    public LimitQueue(int i2) {
        this.limit = 5;
        this.limit = i2;
    }

    public E get(int i2) {
        return this.queue.get(i2);
    }

    public E getFirst() {
        return this.queue.getFirst();
    }

    public E getLast() {
        return this.queue.getLast();
    }

    public int getLimit() {
        return this.limit;
    }

    public void offer(E e2) {
        if (this.queue.size() >= this.limit) {
            this.queue.poll();
        }
        this.queue.offer(e2);
    }

    public int size() {
        return this.queue.size();
    }
}
