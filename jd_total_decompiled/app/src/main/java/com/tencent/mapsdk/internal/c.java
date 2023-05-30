package com.tencent.mapsdk.internal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public class c {
    public HashMap<String, HashMap<String, byte[]>> a = new HashMap<>();
    public HashMap<String, Object> b = new HashMap<>();

    /* renamed from: c */
    private HashMap<String, Object> f16355c = new HashMap<>();
    public String d = "GBK";

    /* renamed from: e */
    public m f16356e = new m();

    private void a(ArrayList<String> arrayList, Object obj) {
        Object obj2;
        if (obj.getClass().isArray()) {
            if (!obj.getClass().getComponentType().toString().equals("byte")) {
                throw new IllegalArgumentException("only byte[] is supported");
            }
            if (Array.getLength(obj) <= 0) {
                arrayList.add("Array");
                arrayList.add("?");
            }
            arrayList.add("java.util.List");
            obj2 = Array.get(obj, 0);
            a(arrayList, obj2);
        } else if (obj instanceof Array) {
            throw new IllegalArgumentException("can not support Array, please use List");
        } else {
            if (obj instanceof List) {
                arrayList.add("java.util.List");
                List list = (List) obj;
                if (list.size() > 0) {
                    obj2 = list.get(0);
                    a(arrayList, obj2);
                }
            } else if (!(obj instanceof Map)) {
                arrayList.add(a((c) obj));
                return;
            } else {
                arrayList.add("java.util.Map");
                Map map = (Map) obj;
                if (map.size() > 0) {
                    Object obj3 = map.get(map.keySet().iterator().next());
                    arrayList.add(a((c) obj));
                    a(arrayList, obj3);
                    return;
                }
                arrayList.add("?");
            }
            arrayList.add("?");
        }
    }

    private Object b(String str, boolean z, ClassLoader classLoader) {
        String a = o.a(str);
        if (a != null && !"".equals(a)) {
            str = a;
        }
        if (this.b.containsKey(str)) {
            return this.b.get(str);
        }
        Object b = a.b(str, z, classLoader);
        this.b.put(str, b);
        return b;
    }

    private void b(String str, Object obj) {
        this.f16355c.put(str, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T a(String str, Object obj, boolean z, ClassLoader classLoader) {
        String str2;
        byte[] bArr;
        if (this.a.containsKey(str)) {
            if (this.f16355c.containsKey(str)) {
                return (T) this.f16355c.get(str);
            }
            byte[] bArr2 = new byte[0];
            Iterator<Map.Entry<String, byte[]>> it = this.a.get(str).entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry<String, byte[]> next = it.next();
                str2 = next.getKey();
                bArr = next.getValue();
            } else {
                str2 = "";
                bArr = bArr2;
            }
            try {
                Object b = b(str2, z, classLoader);
                this.f16356e.b(bArr);
                this.f16356e.a(this.d);
                T t = (T) this.f16356e.a((m) b, 0, true);
                b(str, t);
                return t;
            } catch (Exception e2) {
                e2.printStackTrace();
                b(str, obj);
                return obj;
            }
        }
        return obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T a(String str, boolean z, ClassLoader classLoader) {
        String str2 = null;
        if (this.a.containsKey(str)) {
            if (this.f16355c.containsKey(str)) {
                return (T) this.f16355c.get(str);
            }
            byte[] bArr = new byte[0];
            Iterator<Map.Entry<String, byte[]>> it = this.a.get(str).entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry<String, byte[]> next = it.next();
                str2 = next.getKey();
                bArr = next.getValue();
            }
            try {
                Object b = b(str2, z, classLoader);
                this.f16356e.b(bArr);
                this.f16356e.a(this.d);
                T t = (T) this.f16356e.a((m) b, 0, true);
                b(str, t);
                return t;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new b(e2);
            }
        }
        return null;
    }

    public <T> String a(T t) {
        String className = t instanceof p ? ((p) t).className() : "";
        return "".equals(className) ? t.getClass().getName() : className;
    }

    public void a() {
        this.f16355c.clear();
    }

    public <T> void a(String str, T t) {
        if (str == null) {
            throw new IllegalArgumentException("put key can not is null");
        }
        if (t == null) {
            throw new IllegalArgumentException("put value can not is null");
        }
        if (t instanceof Set) {
            throw new IllegalArgumentException("can not support Set");
        }
        n nVar = new n();
        nVar.a(this.d);
        nVar.a(t, 0);
        byte[] b = q.b(nVar.a());
        HashMap<String, byte[]> hashMap = new HashMap<>(1);
        ArrayList<String> arrayList = new ArrayList<>(1);
        a(arrayList, t);
        hashMap.put(a.a(arrayList), b);
        this.f16355c.remove(str);
        this.a.put(str, hashMap);
    }

    public void a(byte[] bArr) {
        this.f16356e.b(bArr);
        this.f16356e.a(this.d);
        HashMap hashMap = new HashMap(1);
        HashMap hashMap2 = new HashMap(1);
        hashMap2.put("", new byte[0]);
        hashMap.put("", hashMap2);
        this.a = this.f16356e.a((Map) hashMap, 0, false);
    }

    public boolean a(String str) {
        return this.a.containsKey(str);
    }

    public void b(String str) {
        this.d = str;
    }

    public byte[] b() {
        n nVar = new n(0);
        nVar.a(this.d);
        nVar.a((Map) this.a, 0);
        return q.b(nVar.a());
    }

    public <T> T c(String str, boolean z, ClassLoader classLoader) {
        String str2 = null;
        if (this.a.containsKey(str)) {
            if (this.f16355c.containsKey(str)) {
                return (T) this.f16355c.get(str);
            }
            byte[] bArr = new byte[0];
            Iterator<Map.Entry<String, byte[]>> it = this.a.get(str).entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry<String, byte[]> next = it.next();
                str2 = next.getKey();
                bArr = next.getValue();
            }
            try {
                Object b = b(str2, z, classLoader);
                this.f16356e.b(bArr);
                this.f16356e.a(this.d);
                T t = (T) this.f16356e.a((p) b, 0, true);
                b(str, t);
                return t;
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new b(e2);
            }
        }
        return null;
    }

    public String c() {
        return this.d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T d(String str, boolean z, ClassLoader classLoader) {
        String str2;
        byte[] bArr;
        if (this.a.containsKey(str)) {
            byte[] bArr2 = new byte[0];
            Iterator<Map.Entry<String, byte[]>> it = this.a.remove(str).entrySet().iterator();
            if (it.hasNext()) {
                Map.Entry<String, byte[]> next = it.next();
                str2 = next.getKey();
                bArr = next.getValue();
            } else {
                str2 = "";
                bArr = bArr2;
            }
            try {
                Object b = a.b(str2, z, classLoader);
                this.f16356e.b(bArr);
                this.f16356e.a(this.d);
                return (T) this.f16356e.a((m) b, 0, true);
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new b(e2);
            }
        }
        return null;
    }

    public Set<String> d() {
        return Collections.unmodifiableSet(this.a.keySet());
    }

    public boolean e() {
        return this.a.isEmpty();
    }

    public int f() {
        return this.a.size();
    }
}
