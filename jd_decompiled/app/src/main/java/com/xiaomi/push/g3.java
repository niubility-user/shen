package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class g3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18637c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18638e;
    private String b = "";
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private c3 f18639f = null;

    /* renamed from: g  reason: collision with root package name */
    private int f18640g = -1;

    public static g3 n(byte[] bArr) {
        g3 g3Var = new g3();
        g3Var.c(bArr);
        return g3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18640g < 0) {
            i();
        }
        return this.f18640g;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        k(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (p()) {
            y0Var.x(1, o());
        }
        if (s()) {
            y0Var.x(2, r());
        }
        if (t()) {
            y0Var.w(3, j());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int g2 = p() ? 0 + y0.g(1, o()) : 0;
        if (s()) {
            g2 += y0.g(2, r());
        }
        if (t()) {
            g2 += y0.f(3, j());
        }
        this.f18640g = g2;
        return g2;
    }

    public c3 j() {
        return this.f18639f;
    }

    public g3 k(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                m(c0Var.h());
            } else if (b == 18) {
                q(c0Var.h());
            } else if (b == 26) {
                c3 c3Var = new c3();
                c0Var.k(c3Var);
                l(c3Var);
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public g3 l(c3 c3Var) {
        c3Var.getClass();
        this.f18638e = true;
        this.f18639f = c3Var;
        return this;
    }

    public g3 m(String str) {
        this.a = true;
        this.b = str;
        return this;
    }

    public String o() {
        return this.b;
    }

    public boolean p() {
        return this.a;
    }

    public g3 q(String str) {
        this.f18637c = true;
        this.d = str;
        return this;
    }

    public String r() {
        return this.d;
    }

    public boolean s() {
        return this.f18637c;
    }

    public boolean t() {
        return this.f18638e;
    }
}
