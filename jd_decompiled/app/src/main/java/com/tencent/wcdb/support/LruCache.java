package com.tencent.wcdb.support;

import android.annotation.SuppressLint;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final LinkedHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int i2) {
        if (i2 > 0) {
            this.maxSize = i2;
            this.map = new LinkedHashMap<>(0, 0.75f, true);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    private int safeSizeOf(K k2, V v) {
        int sizeOf = sizeOf(k2, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException("Negative size: " + k2 + ContainerUtils.KEY_VALUE_DELIMITER + v);
    }

    protected V create(K k2) {
        return null;
    }

    public final synchronized int createCount() {
        return this.createCount;
    }

    protected void entryRemoved(boolean z, K k2, V v, V v2) {
    }

    public final void evictAll() {
        trimToSize(-1);
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

    public final synchronized int maxSize() {
        return this.maxSize;
    }

    public final synchronized int missCount() {
        return this.missCount;
    }

    public final V put(K k2, V v) {
        V put;
        if (k2 != null && v != null) {
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
        throw new NullPointerException("key == null || value == null");
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

    public void resize(int i2) {
        if (i2 > 0) {
            synchronized (this) {
                this.maxSize = i2;
            }
            trimToSize(i2);
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    public final synchronized int size() {
        return this.size;
    }

    protected int sizeOf(K k2, V v) {
        return 1;
    }

    public final synchronized Map<K, V> snapshot() {
        return new LinkedHashMap(this.map);
    }

    @SuppressLint({"DefaultLocale"})
    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.hitCount;
        i3 = this.missCount + i2;
        return String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.maxSize), Integer.valueOf(this.hitCount), Integer.valueOf(this.missCount), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0086, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L0:
            monitor-enter(r5)
            int r0 = r5.size     // Catch: java.lang.Throwable -> L87
            if (r0 < 0) goto L68
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: java.lang.Throwable -> L87
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L87
            if (r0 == 0) goto L11
            int r0 = r5.size     // Catch: java.lang.Throwable -> L87
            if (r0 != 0) goto L68
        L11:
            int r0 = r5.size     // Catch: java.lang.Throwable -> L87
            if (r0 <= r6) goto L66
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: java.lang.Throwable -> L87
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L87
            if (r0 == 0) goto L1e
            goto L66
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: java.lang.Throwable -> L87
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L87
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L87
            boolean r0 = r0.hasNext()     // Catch: java.lang.Throwable -> L87
            r1 = 0
            if (r0 == 0) goto L40
            java.util.LinkedHashMap<K, V> r0 = r5.map     // Catch: java.lang.Throwable -> L87
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L87
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L87
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L87
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L87
            goto L41
        L40:
            r0 = r1
        L41:
            if (r0 != 0) goto L45
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L87
            goto L67
        L45:
            java.lang.Object r2 = r0.getKey()     // Catch: java.lang.Throwable -> L87
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L87
            java.util.LinkedHashMap<K, V> r3 = r5.map     // Catch: java.lang.Throwable -> L87
            r3.remove(r2)     // Catch: java.lang.Throwable -> L87
            int r3 = r5.size     // Catch: java.lang.Throwable -> L87
            int r4 = r5.safeSizeOf(r2, r0)     // Catch: java.lang.Throwable -> L87
            int r3 = r3 - r4
            r5.size = r3     // Catch: java.lang.Throwable -> L87
            int r3 = r5.evictionCount     // Catch: java.lang.Throwable -> L87
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch: java.lang.Throwable -> L87
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L87
            r5.entryRemoved(r4, r2, r0, r1)
            goto L0
        L66:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L87
        L67:
            return
        L68:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L87
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r0.<init>()     // Catch: java.lang.Throwable -> L87
            java.lang.Class r1 = r5.getClass()     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L87
            r0.append(r1)     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L87
            r6.<init>(r0)     // Catch: java.lang.Throwable -> L87
            throw r6     // Catch: java.lang.Throwable -> L87
        L87:
            r6 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> L87
            goto L8b
        L8a:
            throw r6
        L8b:
            goto L8a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.wcdb.support.LruCache.trimToSize(int):void");
    }
}
