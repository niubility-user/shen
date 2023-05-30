package com.novoda.imageloader.core.cache.util;

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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void trimToSize(long r7) {
        /*
            r6 = this;
        L0:
            monitor-enter(r6)
            long r0 = r6.size     // Catch: java.lang.Throwable -> L9c
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 < 0) goto L7d
            java.util.LinkedHashMap<K, V> r0 = r6.map     // Catch: java.lang.Throwable -> L9c
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L9c
            if (r0 == 0) goto L17
            long r0 = r6.size     // Catch: java.lang.Throwable -> L9c
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L7d
        L17:
            java.lang.Class<com.novoda.imageloader.core.cache.util.LruCache> r0 = com.novoda.imageloader.core.cache.util.LruCache.class
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L9c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9c
            r1.<init>()     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = "trimToSize() size="
            r1.append(r2)     // Catch: java.lang.Throwable -> L9c
            long r2 = r6.size     // Catch: java.lang.Throwable -> L9c
            r1.append(r2)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = " maxSize="
            r1.append(r2)     // Catch: java.lang.Throwable -> L9c
            r1.append(r7)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r2 = " -->> "
            r1.append(r2)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L9c
            com.facebook.common.logging.FLog.d(r0, r1)     // Catch: java.lang.Throwable -> L9c
            long r0 = r6.size     // Catch: java.lang.Throwable -> L9c
            int r2 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r2 > 0) goto L47
            goto L59
        L47:
            java.util.LinkedHashMap<K, V> r0 = r6.map     // Catch: java.lang.Throwable -> L9c
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L9c
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L9c
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L9c
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L9c
            if (r0 != 0) goto L5b
        L59:
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L9c
            return
        L5b:
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L9c
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L9c
            java.util.LinkedHashMap<K, V> r2 = r6.map     // Catch: java.lang.Throwable -> L9c
            r2.remove(r1)     // Catch: java.lang.Throwable -> L9c
            long r2 = r6.size     // Catch: java.lang.Throwable -> L9c
            long r4 = r6.safeSizeOf(r1, r0)     // Catch: java.lang.Throwable -> L9c
            long r2 = r2 - r4
            r6.size = r2     // Catch: java.lang.Throwable -> L9c
            int r2 = r6.evictionCount     // Catch: java.lang.Throwable -> L9c
            r3 = 1
            int r2 = r2 + r3
            r6.evictionCount = r2     // Catch: java.lang.Throwable -> L9c
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L9c
            r2 = 0
            r6.entryRemoved(r3, r1, r0, r2)
            goto L0
        L7d:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L9c
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9c
            r8.<init>()     // Catch: java.lang.Throwable -> L9c
            java.lang.Class r0 = r6.getClass()     // Catch: java.lang.Throwable -> L9c
            java.lang.String r0 = r0.getName()     // Catch: java.lang.Throwable -> L9c
            r8.append(r0)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r0 = ".sizeOf() is reporting inconsistent results!"
            r8.append(r0)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L9c
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L9c
            throw r7     // Catch: java.lang.Throwable -> L9c
        L9c:
            r7 = move-exception
            monitor-exit(r6)     // Catch: java.lang.Throwable -> L9c
            goto La0
        L9f:
            throw r7
        La0:
            goto L9f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.novoda.imageloader.core.cache.util.LruCache.trimToSize(long):void");
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
