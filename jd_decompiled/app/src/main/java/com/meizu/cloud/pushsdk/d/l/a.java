package com.meizu.cloud.pushsdk.d.l;

import java.util.HashMap;

/* loaded from: classes14.dex */
public class a {
    private static final HashMap<String, Class<?>> d = new HashMap<>();
    private Class<?> a;
    private String b;

    /* renamed from: c  reason: collision with root package name */
    private Object f15727c;

    private a(Object obj) {
        this.f15727c = obj;
    }

    private a(String str) {
        this.b = str;
    }

    public static a a(Object obj) {
        return new a(obj);
    }

    public static a b(String str) {
        return new a(str);
    }

    public b c(Class<?>... clsArr) {
        return new b(this, clsArr);
    }

    public c d(String str, Class<?>... clsArr) {
        return new c(this, str, clsArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Class<?> e() throws ClassNotFoundException {
        Class<?> cls = this.a;
        if (cls != null) {
            return cls;
        }
        Object obj = this.f15727c;
        if (obj != null) {
            return obj.getClass();
        }
        HashMap<String, Class<?>> hashMap = d;
        Class<?> cls2 = hashMap.get(this.b);
        if (cls2 == null) {
            cls2 = Class.forName(this.b);
            hashMap.put(this.b, cls2);
        }
        return cls2;
    }
}
