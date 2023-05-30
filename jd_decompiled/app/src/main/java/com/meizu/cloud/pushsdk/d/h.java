package com.meizu.cloud.pushsdk.d;

/* loaded from: classes14.dex */
public class h<T> {
    private T a;
    private T b;

    /* JADX INFO: Access modifiers changed from: protected */
    public h(T t) {
        if (t == null) {
            throw new RuntimeException("proxy must be has a default implementation");
        }
        this.b = t;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public T b() {
        T t = this.a;
        return t != null ? t : this.b;
    }
}
