package com.tencent.mapsdk.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class e extends c {

    /* renamed from: f */
    public HashMap<String, byte[]> f16434f = null;

    /* renamed from: g */
    private HashMap<String, Object> f16435g = new HashMap<>();

    /* renamed from: h */
    public m f16436h = new m();

    private Object a(byte[] bArr, Object obj) {
        this.f16436h.b(bArr);
        this.f16436h.a(this.d);
        return this.f16436h.a((m) obj, 0, true);
    }

    private void b(String str, Object obj) {
        this.f16435g.put(str, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T a(String str, T t, Object obj) {
        return !this.f16434f.containsKey(str) ? obj : (T) d(str, t);
    }

    @Override // com.tencent.mapsdk.internal.c
    public <T> T a(String str, Object obj, boolean z, ClassLoader classLoader) {
        if (this.f16434f == null) {
            return (T) super.a(str, obj, z, classLoader);
        }
        throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy, Object defaultValue)");
    }

    @Override // com.tencent.mapsdk.internal.c
    public <T> T a(String str, boolean z, ClassLoader classLoader) {
        if (this.f16434f == null) {
            return (T) super.a(str, z, classLoader);
        }
        throw new RuntimeException("data is encoded by new version, please use getByClass(String name, T proxy)");
    }

    @Override // com.tencent.mapsdk.internal.c
    public /* bridge */ /* synthetic */ String a(Object obj) {
        return super.a((e) obj);
    }

    @Override // com.tencent.mapsdk.internal.c
    public void a() {
        this.f16435g.clear();
    }

    @Override // com.tencent.mapsdk.internal.c
    public <T> void a(String str, T t) {
        if (this.f16434f == null) {
            super.a(str, (String) t);
        } else if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        } else {
            if (t == null) {
                throw new IllegalArgumentException("put value can not is null");
            }
            if (t instanceof Set) {
                throw new IllegalArgumentException("can not support Set");
            }
            n nVar = new n();
            nVar.a(this.d);
            nVar.a(t, 0);
            this.f16434f.put(str, q.b(nVar.a()));
        }
    }

    @Override // com.tencent.mapsdk.internal.c
    public void a(byte[] bArr) {
        try {
            super.a(bArr);
        } catch (Exception unused) {
            this.f16436h.b(bArr);
            this.f16436h.a(this.d);
            HashMap hashMap = new HashMap(1);
            hashMap.put("", new byte[0]);
            this.f16434f = this.f16436h.a((Map) hashMap, 0, false);
        }
    }

    @Override // com.tencent.mapsdk.internal.c
    public boolean a(String str) {
        HashMap<String, byte[]> hashMap = this.f16434f;
        return hashMap != null ? hashMap.containsKey(str) : this.a.containsKey(str);
    }

    public <T> T b(String str, T t, T t2) {
        HashMap<String, byte[]> hashMap = this.f16434f;
        if (hashMap != null) {
            if (!hashMap.containsKey(str)) {
                return t2;
            }
            if (!this.f16435g.containsKey(str)) {
                try {
                    T t3 = (T) a(this.f16434f.get(str), t);
                    if (t3 != null) {
                        b(str, t3);
                    }
                    return t3;
                } catch (Exception e2) {
                    throw new b(e2);
                }
            }
        } else if (!this.a.containsKey(str)) {
            return t2;
        } else {
            if (!this.f16435g.containsKey(str)) {
                byte[] bArr = new byte[0];
                Iterator<Map.Entry<String, byte[]>> it = this.a.get(str).entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry<String, byte[]> next = it.next();
                    next.getKey();
                    bArr = next.getValue();
                }
                try {
                    this.f16436h.b(bArr);
                    this.f16436h.a(this.d);
                    T t4 = (T) this.f16436h.a((m) t, 0, true);
                    b(str, t4);
                    return t4;
                } catch (Exception e3) {
                    throw new b(e3);
                }
            }
        }
        return (T) this.f16435g.get(str);
    }

    @Override // com.tencent.mapsdk.internal.c
    public /* bridge */ /* synthetic */ void b(String str) {
        super.b(str);
    }

    public void b(byte[] bArr) {
        super.a(bArr);
    }

    @Override // com.tencent.mapsdk.internal.c
    public byte[] b() {
        if (this.f16434f != null) {
            n nVar = new n(0);
            nVar.a(this.d);
            nVar.a((Map) this.f16434f, 0);
            return q.b(nVar.a());
        }
        return super.b();
    }

    public <T> T c(String str) {
        return (T) a(str, true, (ClassLoader) null);
    }

    public <T> T c(String str, Object obj) {
        return (T) a(str, obj, true, null);
    }

    @Override // com.tencent.mapsdk.internal.c
    public <T> T c(String str, boolean z, ClassLoader classLoader) {
        if (this.f16434f == null) {
            return (T) super.c(str, z, classLoader);
        }
        throw new RuntimeException("data is encoded by new version, please use getJceStruct(String name,T proxy)");
    }

    @Override // com.tencent.mapsdk.internal.c
    public /* bridge */ /* synthetic */ String c() {
        return super.c();
    }

    public void c(byte[] bArr) {
        this.f16436h.b(bArr);
        this.f16436h.a(this.d);
        HashMap hashMap = new HashMap(1);
        hashMap.put("", new byte[0]);
        this.f16434f = this.f16436h.a((Map) hashMap, 0, false);
    }

    public <T> T d(String str) {
        return (T) c(str, true, null);
    }

    public <T> T d(String str, T t) {
        HashMap<String, byte[]> hashMap = this.f16434f;
        if (hashMap != null) {
            if (!hashMap.containsKey(str)) {
                return null;
            }
            if (!this.f16435g.containsKey(str)) {
                try {
                    T t2 = (T) a(this.f16434f.get(str), t);
                    if (t2 != null) {
                        b(str, t2);
                    }
                    return t2;
                } catch (Exception e2) {
                    throw new b(e2);
                }
            }
        } else if (!this.a.containsKey(str)) {
            return null;
        } else {
            if (!this.f16435g.containsKey(str)) {
                byte[] bArr = new byte[0];
                Iterator<Map.Entry<String, byte[]>> it = this.a.get(str).entrySet().iterator();
                if (it.hasNext()) {
                    Map.Entry<String, byte[]> next = it.next();
                    next.getKey();
                    bArr = next.getValue();
                }
                try {
                    this.f16436h.b(bArr);
                    this.f16436h.a(this.d);
                    T t3 = (T) this.f16436h.a((m) t, 0, true);
                    b(str, t3);
                    return t3;
                } catch (Exception e3) {
                    throw new b(e3);
                }
            }
        }
        return (T) this.f16435g.get(str);
    }

    @Override // com.tencent.mapsdk.internal.c
    public <T> T d(String str, boolean z, ClassLoader classLoader) {
        HashMap<String, byte[]> hashMap = this.f16434f;
        if (hashMap != null) {
            if (hashMap.containsKey(str)) {
                this.f16434f.remove(str);
                return null;
            }
            return null;
        }
        return (T) super.d(str, z, classLoader);
    }

    @Override // com.tencent.mapsdk.internal.c
    public Set<String> d() {
        HashMap hashMap = this.f16434f;
        if (hashMap == null) {
            hashMap = this.a;
        }
        return Collections.unmodifiableSet(hashMap.keySet());
    }

    public <T> T e(String str) {
        return (T) d(str, true, null);
    }

    public <T> T e(String str, T t) {
        if (this.f16434f.containsKey(str)) {
            if (this.f16435g.containsKey(str)) {
                return (T) this.f16435g.get(str);
            }
            try {
                T t2 = (T) a(this.f16434f.get(str), t);
                if (t2 != null) {
                    b(str, t2);
                }
                return t2;
            } catch (Exception e2) {
                throw new b(e2);
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.c
    public boolean e() {
        HashMap<String, byte[]> hashMap = this.f16434f;
        return hashMap != null ? hashMap.isEmpty() : this.a.isEmpty();
    }

    @Override // com.tencent.mapsdk.internal.c
    public int f() {
        HashMap<String, byte[]> hashMap = this.f16434f;
        return hashMap != null ? hashMap.size() : this.a.size();
    }

    public <T> T f(String str, T t) {
        if (this.f16434f.containsKey(str)) {
            if (t != null) {
                return (T) a(this.f16434f.remove(str), t);
            }
            this.f16434f.remove(str);
            return null;
        }
        return null;
    }

    public void g() {
        this.f16434f = new HashMap<>();
    }
}
