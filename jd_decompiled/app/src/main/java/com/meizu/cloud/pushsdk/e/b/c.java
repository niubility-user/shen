package com.meizu.cloud.pushsdk.e.b;

import com.meizu.cloud.pushsdk.e.d.k;

/* loaded from: classes14.dex */
public class c<T> {
    private final T a;
    private final com.meizu.cloud.pushsdk.e.c.a b;

    public c(com.meizu.cloud.pushsdk.e.c.a aVar) {
        this.a = null;
        this.b = aVar;
    }

    public c(T t) {
        this.a = t;
        this.b = null;
    }

    public static <T> c<T> a(com.meizu.cloud.pushsdk.e.c.a aVar) {
        return new c<>(aVar);
    }

    public static <T> c<T> b(T t) {
        return new c<>(t);
    }

    public com.meizu.cloud.pushsdk.e.c.a c() {
        return this.b;
    }

    public void d(k kVar) {
    }

    public T e() {
        return this.a;
    }

    public boolean f() {
        return this.b == null;
    }
}
