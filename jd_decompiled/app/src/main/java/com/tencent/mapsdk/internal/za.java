package com.tencent.mapsdk.internal;

import java.util.HashMap;

/* loaded from: classes9.dex */
public final class za<T, K> {
    private static final int d = 1024;
    private HashMap<T, K> a;
    private int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f17576c = 1024;

    public synchronized K a(T t) {
        HashMap<T, K> hashMap = this.a;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(t);
    }

    public void a(int i2) {
        this.f17576c = i2;
    }

    public synchronized void a(T t, K k2) {
        if (this.a == null) {
            this.a = new HashMap<>();
        }
        this.a.put(t, k2);
    }

    public synchronized void b(T t) {
        HashMap<T, K> hashMap = this.a;
        if (hashMap == null) {
            return;
        }
        hashMap.remove(t);
    }
}
