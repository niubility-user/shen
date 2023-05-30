package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.uc;

/* loaded from: classes9.dex */
public abstract class sc<T extends uc> {

    /* renamed from: g */
    public int f17242g;

    /* renamed from: h */
    public long f17243h;

    /* renamed from: i */
    public tc<T> f17244i;

    /* renamed from: j */
    public T f17245j;

    public sc(tc<T> tcVar, T t) {
        this.f17244i = tcVar;
        this.f17245j = t;
    }

    public void a(long j2) {
        this.f17243h = j2;
    }

    public void a(T t) {
        tc<T> tcVar = this.f17244i;
        if (tcVar == null || t == null) {
            return;
        }
        this.f17245j = t;
        tcVar.c(this);
    }

    public T f() {
        return this.f17245j;
    }

    public String getId() {
        return this.f17242g + "";
    }

    public int l() {
        return this.f17242g;
    }

    public void remove() {
        tc<T> tcVar = this.f17244i;
        if (tcVar == null) {
            return;
        }
        tcVar.b(this);
    }

    public long x() {
        return this.f17243h;
    }
}
