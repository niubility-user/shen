package com.alibaba.fastjson.util;

import java.lang.reflect.Type;

/* loaded from: classes.dex */
public class IdentityHashMap<V> {
    private final Entry<V>[] buckets;
    private final int indexMask;

    /* loaded from: classes.dex */
    protected static final class Entry<V> {
        public final int hashCode;
        public final Type key;
        public final Entry<V> next;
        public V value;

        public Entry(Type type, V v, int i2, Entry<V> entry) {
            this.key = type;
            this.value = v;
            this.next = entry;
            this.hashCode = i2;
        }
    }

    public IdentityHashMap(int i2) {
        this.indexMask = i2 - 1;
        this.buckets = new Entry[i2];
    }

    public final V get(Type type) {
        for (Entry<V> entry = this.buckets[System.identityHashCode(type) & this.indexMask]; entry != null; entry = entry.next) {
            if (type == entry.key) {
                return entry.value;
            }
        }
        return null;
    }

    public boolean put(Type type, V v) {
        int identityHashCode = System.identityHashCode(type);
        int i2 = this.indexMask & identityHashCode;
        for (Entry<V> entry = this.buckets[i2]; entry != null; entry = entry.next) {
            if (type == entry.key) {
                entry.value = v;
                return true;
            }
        }
        this.buckets[i2] = new Entry<>(type, v, identityHashCode, this.buckets[i2]);
        return false;
    }
}
