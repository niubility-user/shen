package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class d3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18529c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18530e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18532g;

    /* renamed from: i  reason: collision with root package name */
    private boolean f18534i;

    /* renamed from: k  reason: collision with root package name */
    private boolean f18536k;
    private String b = "";
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private String f18531f = "";

    /* renamed from: h  reason: collision with root package name */
    private String f18533h = "";

    /* renamed from: j  reason: collision with root package name */
    private String f18535j = "";

    /* renamed from: l  reason: collision with root package name */
    private String f18537l = "";

    /* renamed from: m  reason: collision with root package name */
    private int f18538m = -1;

    public String A() {
        return this.f18537l;
    }

    public boolean B() {
        return this.f18536k;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18538m < 0) {
            i();
        }
        return this.f18538m;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        j(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (m()) {
            y0Var.x(1, l());
        }
        if (p()) {
            y0Var.x(2, o());
        }
        if (s()) {
            y0Var.x(3, r());
        }
        if (v()) {
            y0Var.x(4, u());
        }
        if (y()) {
            y0Var.x(5, x());
        }
        if (B()) {
            y0Var.x(6, A());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int g2 = m() ? 0 + y0.g(1, l()) : 0;
        if (p()) {
            g2 += y0.g(2, o());
        }
        if (s()) {
            g2 += y0.g(3, r());
        }
        if (v()) {
            g2 += y0.g(4, u());
        }
        if (y()) {
            g2 += y0.g(5, x());
        }
        if (B()) {
            g2 += y0.g(6, A());
        }
        this.f18538m = g2;
        return g2;
    }

    public d3 j(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                k(c0Var.h());
            } else if (b == 18) {
                n(c0Var.h());
            } else if (b == 26) {
                q(c0Var.h());
            } else if (b == 34) {
                t(c0Var.h());
            } else if (b == 42) {
                w(c0Var.h());
            } else if (b == 50) {
                z(c0Var.h());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public d3 k(String str) {
        this.a = true;
        this.b = str;
        return this;
    }

    public String l() {
        return this.b;
    }

    public boolean m() {
        return this.a;
    }

    public d3 n(String str) {
        this.f18529c = true;
        this.d = str;
        return this;
    }

    public String o() {
        return this.d;
    }

    public boolean p() {
        return this.f18529c;
    }

    public d3 q(String str) {
        this.f18530e = true;
        this.f18531f = str;
        return this;
    }

    public String r() {
        return this.f18531f;
    }

    public boolean s() {
        return this.f18530e;
    }

    public d3 t(String str) {
        this.f18532g = true;
        this.f18533h = str;
        return this;
    }

    public String u() {
        return this.f18533h;
    }

    public boolean v() {
        return this.f18532g;
    }

    public d3 w(String str) {
        this.f18534i = true;
        this.f18535j = str;
        return this;
    }

    public String x() {
        return this.f18535j;
    }

    public boolean y() {
        return this.f18534i;
    }

    public d3 z(String str) {
        this.f18536k = true;
        this.f18537l = str;
        return this;
    }
}
