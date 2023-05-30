package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class b3 extends o2 {
    private boolean a;

    /* renamed from: c */
    private boolean f18472c;

    /* renamed from: e */
    private boolean f18473e;

    /* renamed from: g */
    private boolean f18475g;

    /* renamed from: i */
    private boolean f18477i;

    /* renamed from: k */
    private boolean f18479k;

    /* renamed from: m */
    private boolean f18481m;
    private boolean o;
    private boolean q;
    private boolean s;
    private boolean u;
    private boolean w;
    private boolean y;
    private int b = 0;
    private long d = 0;

    /* renamed from: f */
    private String f18474f = "";

    /* renamed from: h */
    private String f18476h = "";

    /* renamed from: j */
    private String f18478j = "";

    /* renamed from: l */
    private String f18480l = "";

    /* renamed from: n */
    private String f18482n = "";
    private int p = 1;
    private int r = 0;
    private int t = 0;
    private String v = "";
    private long x = 0;
    private long z = 0;
    private int A = -1;

    public b3 A(long j2) {
        this.y = true;
        this.z = j2;
        return this;
    }

    public b3 B(String str) {
        this.f18477i = true;
        this.f18478j = str;
        return this;
    }

    public String C() {
        return this.f18478j;
    }

    public boolean D() {
        return this.f18473e;
    }

    public int E() {
        return this.p;
    }

    public b3 F(int i2) {
        this.s = true;
        this.t = i2;
        return this;
    }

    public b3 G(String str) {
        this.f18479k = true;
        this.f18480l = str;
        return this;
    }

    public String H() {
        return this.f18480l;
    }

    public boolean I() {
        return this.f18475g;
    }

    public int J() {
        return this.r;
    }

    public b3 K(String str) {
        this.f18481m = true;
        this.f18482n = str;
        return this;
    }

    public String L() {
        return this.f18482n;
    }

    public boolean M() {
        return this.f18477i;
    }

    public int N() {
        return this.t;
    }

    public b3 O(String str) {
        this.u = true;
        this.v = str;
        return this;
    }

    public String P() {
        return this.v;
    }

    public boolean Q() {
        return this.f18479k;
    }

    public boolean R() {
        return this.f18481m;
    }

    public boolean S() {
        return this.o;
    }

    public boolean T() {
        return this.q;
    }

    public boolean U() {
        return this.s;
    }

    public boolean V() {
        return this.u;
    }

    public boolean W() {
        return this.w;
    }

    public boolean X() {
        return this.y;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.A < 0) {
            i();
        }
        return this.A;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        n(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (q()) {
            y0Var.t(1, x());
        }
        if (w()) {
            y0Var.N(2, j());
        }
        if (D()) {
            y0Var.x(3, p());
        }
        if (I()) {
            y0Var.x(4, v());
        }
        if (M()) {
            y0Var.x(5, C());
        }
        if (Q()) {
            y0Var.x(6, H());
        }
        if (R()) {
            y0Var.x(7, L());
        }
        if (S()) {
            y0Var.t(8, E());
        }
        if (T()) {
            y0Var.t(9, J());
        }
        if (U()) {
            y0Var.t(10, N());
        }
        if (V()) {
            y0Var.x(11, P());
        }
        if (W()) {
            y0Var.N(12, r());
        }
        if (X()) {
            y0Var.N(13, y());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int c2 = q() ? 0 + y0.c(1, x()) : 0;
        if (w()) {
            c2 += y0.I(2, j());
        }
        if (D()) {
            c2 += y0.g(3, p());
        }
        if (I()) {
            c2 += y0.g(4, v());
        }
        if (M()) {
            c2 += y0.g(5, C());
        }
        if (Q()) {
            c2 += y0.g(6, H());
        }
        if (R()) {
            c2 += y0.g(7, L());
        }
        if (S()) {
            c2 += y0.c(8, E());
        }
        if (T()) {
            c2 += y0.c(9, J());
        }
        if (U()) {
            c2 += y0.c(10, N());
        }
        if (V()) {
            c2 += y0.g(11, P());
        }
        if (W()) {
            c2 += y0.I(12, r());
        }
        if (X()) {
            c2 += y0.I(13, y());
        }
        this.A = c2;
        return c2;
    }

    public long j() {
        return this.d;
    }

    public b3 k() {
        this.f18479k = false;
        this.f18480l = "";
        return this;
    }

    public b3 l(int i2) {
        this.a = true;
        this.b = i2;
        return this;
    }

    public b3 m(long j2) {
        this.f18472c = true;
        this.d = j2;
        return this;
    }

    public b3 n(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            switch (b) {
                case 0:
                    return this;
                case 8:
                    l(c0Var.p());
                    break;
                case 16:
                    m(c0Var.q());
                    break;
                case 26:
                    o(c0Var.h());
                    break;
                case 34:
                    u(c0Var.h());
                    break;
                case 42:
                    B(c0Var.h());
                    break;
                case 50:
                    G(c0Var.h());
                    break;
                case 58:
                    K(c0Var.h());
                    break;
                case 64:
                    s(c0Var.p());
                    break;
                case 72:
                    z(c0Var.p());
                    break;
                case 80:
                    F(c0Var.p());
                    break;
                case 90:
                    O(c0Var.h());
                    break;
                case 96:
                    t(c0Var.q());
                    break;
                case 104:
                    A(c0Var.q());
                    break;
                default:
                    if (g(c0Var, b)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public b3 o(String str) {
        this.f18473e = true;
        this.f18474f = str;
        return this;
    }

    public String p() {
        return this.f18474f;
    }

    public boolean q() {
        return this.a;
    }

    public long r() {
        return this.x;
    }

    public b3 s(int i2) {
        this.o = true;
        this.p = i2;
        return this;
    }

    public b3 t(long j2) {
        this.w = true;
        this.x = j2;
        return this;
    }

    public b3 u(String str) {
        this.f18475g = true;
        this.f18476h = str;
        return this;
    }

    public String v() {
        return this.f18476h;
    }

    public boolean w() {
        return this.f18472c;
    }

    public int x() {
        return this.b;
    }

    public long y() {
        return this.z;
    }

    public b3 z(int i2) {
        this.q = true;
        this.r = i2;
        return this;
    }
}
