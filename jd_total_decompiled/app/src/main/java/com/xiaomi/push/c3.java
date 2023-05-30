package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class c3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18496c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18497e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18499g;
    private boolean b = false;
    private int d = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f18498f = 0;

    /* renamed from: h  reason: collision with root package name */
    private int f18500h = 0;

    /* renamed from: i  reason: collision with root package name */
    private int f18501i = -1;

    public static c3 m(byte[] bArr) {
        c3 c3Var = new c3();
        c3Var.c(bArr);
        return c3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18501i < 0) {
            i();
        }
        return this.f18501i;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        k(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (p()) {
            y0Var.y(1, n());
        }
        if (s()) {
            y0Var.t(3, q());
        }
        if (u()) {
            y0Var.t(4, t());
        }
        if (w()) {
            y0Var.t(5, v());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int h2 = p() ? 0 + y0.h(1, n()) : 0;
        if (s()) {
            h2 += y0.c(3, q());
        }
        if (u()) {
            h2 += y0.c(4, t());
        }
        if (w()) {
            h2 += y0.c(5, v());
        }
        this.f18501i = h2;
        return h2;
    }

    public c3 j(int i2) {
        this.f18496c = true;
        this.d = i2;
        return this;
    }

    public c3 k(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 8) {
                l(c0Var.l());
            } else if (b == 24) {
                j(c0Var.p());
            } else if (b == 32) {
                o(c0Var.p());
            } else if (b == 40) {
                r(c0Var.p());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public c3 l(boolean z) {
        this.a = true;
        this.b = z;
        return this;
    }

    public boolean n() {
        return this.b;
    }

    public c3 o(int i2) {
        this.f18497e = true;
        this.f18498f = i2;
        return this;
    }

    public boolean p() {
        return this.a;
    }

    public int q() {
        return this.d;
    }

    public c3 r(int i2) {
        this.f18499g = true;
        this.f18500h = i2;
        return this;
    }

    public boolean s() {
        return this.f18496c;
    }

    public int t() {
        return this.f18498f;
    }

    public boolean u() {
        return this.f18497e;
    }

    public int v() {
        return this.f18500h;
    }

    public boolean w() {
        return this.f18499g;
    }
}
