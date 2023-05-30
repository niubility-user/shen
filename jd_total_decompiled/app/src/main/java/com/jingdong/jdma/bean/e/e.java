package com.jingdong.jdma.bean.e;

import com.jingdong.jdma.common.utils.n;

/* loaded from: classes12.dex */
public class e {
    private int a;
    private int b;

    /* renamed from: c  reason: collision with root package name */
    private int f12660c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private int f12661e;

    public e() {
        f();
    }

    private void f() {
        int i2 = n.a().c() ? 25 : 10;
        this.d = i2;
        this.f12661e = i2;
        this.f12660c = i2;
        this.b = i2;
        this.a = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int c() {
        return this.f12660c;
    }

    public int d() {
        return this.f12661e;
    }

    public int e() {
        return this.d;
    }

    public void g() {
        f();
    }

    public void a(int i2) {
        if (i2 > 0) {
            this.a = i2;
        }
    }

    public void b(int i2) {
        if (i2 > 0) {
            this.b = i2;
        }
    }

    public void c(int i2) {
        if (i2 > 0) {
            this.f12660c = i2;
        }
    }

    public void d(int i2) {
        if (i2 > 0) {
            this.f12661e = i2;
        }
    }

    public void e(int i2) {
        if (i2 > 0) {
            this.d = i2;
        }
    }
}
