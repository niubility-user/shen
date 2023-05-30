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
    */
    public void b(int i2) {
        K key;
        V value;
        while (true) {
            synchronized (this) {
                if (this.b >= 0 && (!this.a.isEmpty() || this.b == 0)) {
                    if (this.b <= i2 || this.a.isEmpty()) {
                        break;
                    }
                    Map.Entry<K, V> next = this.a.entrySet().iterator().next();
                    key = next.getKey();
                    value = next.getValue();
                    this.a.remove(key);
                    this.b -= b(key, value);
                    this.f17320f++;
                } else {
                    break;
                }
            }
            a(true, key, value, null);
        }
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
