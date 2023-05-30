package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class l3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18818c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18819e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18821g;

    /* renamed from: i  reason: collision with root package name */
    private boolean f18823i;

    /* renamed from: k  reason: collision with root package name */
    private boolean f18825k;
    private String b = "";
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private long f18820f = 0;

    /* renamed from: h  reason: collision with root package name */
    private long f18822h = 0;

    /* renamed from: j  reason: collision with root package name */
    private boolean f18824j = false;

    /* renamed from: l  reason: collision with root package name */
    private int f18826l = 0;

    /* renamed from: m  reason: collision with root package name */
    private int f18827m = -1;

    public static l3 p(byte[] bArr) {
        l3 l3Var = new l3();
        l3Var.c(bArr);
        return l3Var;
    }

    public boolean A() {
        return this.f18824j;
    }

    public boolean B() {
        return this.f18823i;
    }

    public boolean C() {
        return this.f18825k;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18827m < 0) {
            i();
        }
        return this.f18827m;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        m(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (r()) {
            y0Var.x(1, q());
        }
        if (w()) {
            y0Var.x(2, v());
        }
        if (y()) {
            y0Var.u(3, j());
        }
        if (z()) {
            y0Var.u(4, s());
        }
        if (B()) {
            y0Var.y(5, A());
        }
        if (C()) {
            y0Var.t(6, x());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int g2 = r() ? 0 + y0.g(1, q()) : 0;
        if (w()) {
            g2 += y0.g(2, v());
        }
        if (y()) {
            g2 += y0.d(3, j());
        }
        if (z()) {
            g2 += y0.d(4, s());
        }
        if (B()) {
            g2 += y0.h(5, A());
        }
        if (C()) {
            g2 += y0.c(6, x());
        }
        this.f18827m = g2;
        return g2;
    }

    public long j() {
        return this.f18820f;
    }

    public l3 k(int i2) {
        this.f18825k = true;
        this.f18826l = i2;
        return this;
    }

    public l3 l(long j2) {
        this.f18819e = true;
        this.f18820f = j2;
        return this;
    }

    public l3 m(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                n(c0Var.h());
            } else if (b == 18) {
                u(c0Var.h());
            } else if (b == 24) {
                l(c0Var.d());
            } else if (b == 32) {
                t(c0Var.d());
            } else if (b == 40) {
                o(c0Var.l());
            } else if (b == 48) {
                k(c0Var.p());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public l3 n(String str) {
        this.a = true;
        this.b = str;
        return this;
    }

    public l3 o(boolean z) {
        this.f18823i = true;
        this.f18824j = z;
        return this;
    }

    public String q() {
        return this.b;
    }

    public boolean r() {
        return this.a;
    }

    public long s() {
        return this.f18822h;
    }

    public l3 t(long j2) {
        this.f18821g = true;
        this.f18822h = j2;
        return this;
    }

    public l3 u(String str) {
        this.f18818c = true;
        this.d = str;
        return this;
    }

    public String v() {
        return this.d;
    }

    public boolean w() {
        return this.f18818c;
    }

    public int x() {
        return this.f18826l;
    }

    public boolean y() {
        return this.f18819e;
    }

    public boolean z() {
        return this.f18821g;
    }
}
