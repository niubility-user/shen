package com.tencent.mapsdk.internal;

import com.huawei.hms.framework.common.ContainerUtils;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes9.dex */
public class ua<K, V> {
    private final LinkedHashMap<K, V> a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f17318c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f17319e;

    /* renamed from: f  reason: collision with root package name */
    private int f17320f;

    /* renamed from: g  reason: collision with root package name */
    private int f17321g;

    /* renamed from: h  reason: collision with root package name */
    private int f17322h;

    public ua(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f17318c = i2;
        this.a = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int b(K k2, V v) {
        int c2 = c(k2, v);
        if (c2 >= 0) {
            return c2;
        }
        throw new IllegalStateException("Negative size: " + k2 + ContainerUtils.KEY_VALUE_DELIMITER + v);
    }

    public final synchronized int a() {
        return this.f17319e;
    }

    public V a(K k2) {
        return null;
    }

    public final V a(K k2, V v) {
        V put;
        if (k2 == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.d++;
            this.b += b(k2, v);
            put = this.a.put(k2, v);
            if (put != null) {
                this.b -= b(k2, put);
            }
        }
        if (put != null) {
            a(false, k2, put, v);
        }
        b(this.f17318c);
        return put;
    }

    public void a(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        synchronized (this) {
            this.f17318c = i2;
        }
        b(i2);
    }

    public void a(boolean z, K k2, V v, V v2) {
    }

    public final V b(K k2) {
        V put;
        if (k2 != null) {
            synchronized (this) {
                V v = this.a.get(k2);
                if (v != null) {
                    this.f17321g++;
                    return v;
                }
                this.f17322h++;
                V a = a((ua<K, V>) k2);
                if (a == null) {
                    return null;
                }
                synchronized (this) {
                    this.f17319e++;
                    put = this.a.put(k2, a);
                    if (put != null) {
                        this.a.put(k2, put);
                    } else {
                        this.b += b(k2, a);
                    }
                }
                if (put != null) {
                    a(false, k2, a, put);
                    return put;
                }
                b(this.f17318c);
                return a;
            }
        }
        throw new NullPointerException("key == null");
    }

    public final void b() {
        b(-1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0070, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(int r5) {
        /*
            r4 = this;
        L0:
            monitor-enter(r4)
            int r0 = r4.b     // Catch: java.lang.Throwable -> L71
            if (r0 < 0) goto L52
            java.util.LinkedHashMap<K, V> r0 = r4.a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L11
            int r0 = r4.b     // Catch: java.lang.Throwable -> L71
            if (r0 != 0) goto L52
        L11:
            int r0 = r4.b     // Catch: java.lang.Throwable -> L71
            if (r0 <= r5) goto L50
            java.util.LinkedHashMap<K, V> r0 = r4.a     // Catch: java.lang.Throwable -> L71
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L1e
            goto L50
        L1e:
            java.util.LinkedHashMap<K, V> r0 = r4.a     // Catch: java.lang.Throwable -> L71
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L71
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L71
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L71
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L71
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L71
            java.util.LinkedHashMap<K, V> r2 = r4.a     // Catch: java.lang.Throwable -> L71
            r2.remove(r1)     // Catch: java.lang.Throwable -> L71
            int r2 = r4.b     // Catch: java.lang.Throwable -> L71
            int r3 = r4.b(r1, r0)     // Catch: java.lang.Throwable -> L71
            int r2 = r2 - r3
            r4.b = r2     // Catch: java.lang.Throwable -> L71
            int r2 = r4.f17320f     // Catch: java.lang.Throwable -> L71
            r3 = 1
            int r2 = r2 + r3
            r4.f17320f = r2     // Catch: java.lang.Throwable -> L71
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            r2 = 0
            r4.a(r3, r1, r0, r2)
            goto L0
        L50:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            return
        L52:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L71
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71
            r0.<init>()     // Catch: java.lang.Throwable -> L71
            java.lang.Class r1 = r4.getClass()     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L71
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L71
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L71
            r5.<init>(r0)     // Catch: java.lang.Throwable -> L71
            throw r5     // Catch: java.lang.Throwable -> L71
        L71:
            r5 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L71
            goto L75
        L74:
            throw r5
        L75:
            goto L74
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.ua.b(int):void");
    }

    public final synchronized int c() {
        return this.f17320f;
    }

    public int c(K k2, V v) {
        return 1;
    }

    public final V c(K k2) {
        V remove;
        if (k2 != null) {
            synchronized (this) {
                remove = this.a.remove(k2);
                if (remove != null) {
                    this.b -= b(k2, remove);
                }
            }
            if (remove != null) {
                a(false, k2, remove, null);
            }
            return remove;
        }
        throw new NullPointerException("key == null");
    }

    public final synchronized int d() {
        return this.f17321g;
    }

    public final synchronized int e() {
        return this.f17318c;
    }

    public final synchronized int f() {
        return this.f17322h;
    }

    public final synchronized int g() {
        return this.d;
    }

    public final synchronized int h() {
        return this.b;
    }

    public final synchronized Map<K, V> i() {
        return new LinkedHashMap(this.a);
    }

    public final synchronized String toString() {
        int i2;
        int i3;
        i2 = this.f17321g;
        i3 = this.f17322h + i2;
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.f17318c), Integer.valueOf(this.f17321g), Integer.valueOf(this.f17322h), Integer.valueOf(i3 != 0 ? (i2 * 100) / i3 : 0));
    }
}
