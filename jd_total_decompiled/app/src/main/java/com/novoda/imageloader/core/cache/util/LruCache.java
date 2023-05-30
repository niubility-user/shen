package com.novoda.imageloader.core.cache.util;

import com.facebook.common.logging.FLog;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes14.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private long maxSize;
    private int missCount;
    private int putCount;
    private long size;

    public LruCache(long j2) {
        if (j2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = j2;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    private long safeSizeOf(K k2, V v) {
        long sizeOf = sizeOf(k2, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException("Negative size: " + k2 + ContainerUtils.KEY_VALUE_DELIMITER + v);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x009b, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void trimToSize(long j2) {
        Map.Entry<K, V> next;
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.size >= 0 && (!this.map.isEmpty() || this.size == 0)) {
                    FLog.d(LruCache.class.getName(), "trimToSize() size=" + this.size + " maxSize=" + j2 + " -->> ");
                    if (this.size <= j2 || (next = this.map.entrySet().iterator().next()) == null) {
                        break;
                    }
                    key = next.getKey();
                    value = next.getValue();
                    this.map.remove(key);
                    this.size -= safeSizeOf(key, value);
                    this.evictionCount++;
                } else {
                    break;
                }
            }
            entryRemoved(true, key, value, null);
        }
    }

    protected V create(K k2) {
        return null;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    protected void entryRemoved(boolean z, K k2, V v, V v2) {
    }

    public final void evict(long j2) {
        trimToSize(j2);
    }

    public final void evictAll() {
        trimToSize(0L);
    }

    public final synchronized int evictionCount() {
        return this.evictionCount;
    }

    public final V get(K k2) {
        V put;
        if (k2 != null) {
            synchronized (this) {
                V v = this.map.get(k2);
                if (v != null) {
                    this.hitCount++;
                    return v;
                }
                this.missCount++;
                V create = create(k2);
                if (create == null) {
                    return null;
                }
                synchronized (this) {
                    this.createCount++;
                    put = this.map.put(k2, create);
                    if (put != null) {
                        this.map.put(k2, put);
                    } else {
                        this.size += safeSizeOf(k2, create);
                    }
                }
                if (put != null) {
                    entryRemoved(false, k2, create, put);
                    return put;
                }
                trimToSize(this.maxSize);
                return create;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final synchronized int hitCount() {
        return this.hitCount;
    }

    public final synchronized long maxSize() {
        return this.maxSize;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final V put(K k2, V v) {
        V put;
        if (k2 == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += safeSizeOf(k2, v);
            put = this.map.put(k2, v);
            if (put != null) {
                this.size -= safeSizeOf(k2, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k2, put, v);
        }
        trimToSize(this.maxSize);
        return put;
    }

    public final synchronized int putCount() {
        return this.putCount;
    }

    public final V remove(K k2) {
        V remove;
        if (k2 != null) {
            synchronized (this) {
                remove = this.map.remove(k2);
                if (remove != null) {
                    this.size -= safeSizeOf(k2, remove);
                }
            }
            if (remove != null) {
                entryRemoved(false, k2, remove, null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    public final synchronized long size() {
        return this.size;
    }

    protected long sizeOf(K k2, V v) {
        return 1L;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.hitCount;
        i3 = this.missCount + i2;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Long.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }
}
