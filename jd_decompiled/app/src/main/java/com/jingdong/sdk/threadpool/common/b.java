package com.jingdong.sdk.threadpool.common;

import java.util.concurrent.FutureTask;

/* loaded from: classes10.dex */
public class b<V> extends FutureTask<V> implements Comparable<V> {

    /* renamed from: g  reason: collision with root package name */
    private f<V> f15546g;

    public b(f<V> fVar) {
        super(fVar, null);
        this.f15546g = fVar;
    }

    public f<V> b() {
        return this.f15546g;
    }

    @Override // java.lang.Comparable
    public int compareTo(V v) {
        return this.f15546g.compareTo(((b) v).b());
    }
}
