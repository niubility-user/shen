package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class f3 extends o2 {
    private int A;
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18612c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18613e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18615g;

    /* renamed from: i  reason: collision with root package name */
    private boolean f18617i;

    /* renamed from: k  reason: collision with root package name */
    private boolean f18619k;

    /* renamed from: m  reason: collision with root package name */
    private boolean f18621m;
    private boolean o;
    private boolean q;
    private boolean s;
    private boolean u;
    private a v;
    private boolean w;
    private a x;
    private boolean y;
    private int z;
    private int b = 0;
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private String f18614f = "";

    /* renamed from: h  reason: collision with root package name */
    private String f18616h = "";

    /* renamed from: j  reason: collision with root package name */
    private int f18618j = 0;

    /* renamed from: l  reason: collision with root package name */
    private String f18620l = "";

    /* renamed from: n  reason: collision with root package name */
    private String f18622n = "";
    private String p = "";
    private c3 r = null;
    private int t = 0;

    public f3() {
        a aVar = a.f18439c;
        this.v = aVar;
        this.x = aVar;
        this.z = 0;
        this.A = -1;
    }

    public f3 A(String str) {
        this.f18615g = true;
        this.f18616h = str;
        return this;
    }

    public String B() {
        return this.f18616h;
    }

    public boolean C() {
        return this.f18613e;
    }

    public int D() {
        return this.f18618j;
    }

    public f3 E(int i2) {
        this.y = true;
        this.z = i2;
        return this;
    }

    public f3 F(String str) {
        this.f18619k = true;
        this.f18620l = str;
        return this;
    }

    public String G() {
        return this.f18620l;
    }

    public boolean H() {
        return this.f18615g;
    }

    public int I() {
        return this.t;
    }

    public f3 J(String str) {
        this.f18621m = true;
        this.f18622n = str;
        return this;
    }

    public String K() {
        return this.f18622n;
    }

    public boolean L() {
        return this.f18617i;
    }

    public int M() {
        return this.z;
    }

    public f3 N(String str) {
        this.o = true;
        this.p = str;
        return this;
    }

    public String O() {
        return this.p;
    }

    public boolean P() {
        return this.f18619k;
    }

    public boolean Q() {
        return this.f18621m;
    }

    public boolean R() {
        return this.o;
    }

    public boolean S() {
        return this.q;
    }

    public boolean T() {
        return this.s;
    }

    public boolean U() {
        return this.u;
    }

    public boolean V() {
        return this.w;
    }

    public boolean W() {
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
        if (r()) {
            y0Var.M(1, y());
        }
        if (x()) {
            y0Var.x(2, q());
        }
        if (C()) {
            y0Var.x(3, w());
        }
        if (H()) {
            y0Var.x(4, B());
        }
        if (L()) {
            y0Var.t(5, D());
        }
        if (P()) {
            y0Var.x(6, G());
        }
        if (Q()) {
            y0Var.x(7, K());
        }
        if (R()) {
            y0Var.x(8, O());
        }
        if (S()) {
            y0Var.w(9, k());
        }
        if (T()) {
            y0Var.t(10, I());
        }
        if (U()) {
            y0Var.v(11, j());
        }
        if (V()) {
            y0Var.v(12, s());
        }
        if (W()) {
            y0Var.t(13, M());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int H = r() ? 0 + y0.H(1, y()) : 0;
        if (x()) {
            H += y0.g(2, q());
        }
        if (C()) {
            H += y0.g(3, w());
        }
        if (H()) {
            H += y0.g(4, B());
        }
        if (L()) {
            H += y0.c(5, D());
        }
        if (P()) {
            H += y0.g(6, G());
        }
        if (Q()) {
            H += y0.g(7, K());
        }
        if (R()) {
            H += y0.g(8, O());
        }
        if (S()) {
            H += y0.f(9, k());
        }
        if (T()) {
            H += y0.c(10, I());
        }
        if (U()) {
            H += y0.e(11, j());
        }
        if (V()) {
            H += y0.e(12, s());
        }
        if (W()) {
            H += y0.c(13, M());
        }
        this.A = H;
        return H;
    }

    public a j() {
        return this.v;
    }

    public c3 k() {
        return this.r;
    }

    public f3 l(int i2) {
        this.a = true;
        this.b = i2;
        return this;
    }

    public f3 m(a aVar) {
        this.u = true;
        this.v = aVar;
        return this;
    }

    public f3 n(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            switch (b) {
                case 0:
                    return this;
                case 8:
                    l(c0Var.u());
                    break;
                case 18:
                    p(c0Var.h());
                    break;
                case 26:
                    v(c0Var.h());
                    break;
                case 34:
                    A(c0Var.h());
                    break;
                case 40:
                    t(c0Var.p());
                    break;
                case 50:
                    F(c0Var.h());
                    break;
                case 58:
                    J(c0Var.h());
                    break;
                case 66:
                    N(c0Var.h());
                    break;
                case 74:
                    c3 c3Var = new c3();
                    c0Var.k(c3Var);
                    o(c3Var);
                    break;
                case 80:
                    z(c0Var.p());
                    break;
                case 90:
                    m(c0Var.e());
                    break;
                case 98:
                    u(c0Var.e());
                    break;
                case 104:
                    E(c0Var.p());
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

    public f3 o(c3 c3Var) {
        c3Var.getClass();
        this.q = true;
        this.r = c3Var;
        return this;
    }

    public f3 p(String str) {
        this.f18612c = true;
        this.d = str;
        return this;
    }

    public String q() {
        return this.d;
    }

    public boolean r() {
        return this.a;
    }

    public a s() {
        return this.x;
    }

    public f3 t(int i2) {
        this.f18617i = true;
        this.f18618j = i2;
        return this;
    }

    public f3 u(a aVar) {
        this.w = true;
        this.x = aVar;
        return this;
    }

    public f3 v(String str) {
        this.f18613e = true;
        this.f18614f = str;
        return this;
    }

    public String w() {
        return this.f18614f;
    }

    public boolean x() {
        return this.f18612c;
    }

    public int y() {
        return this.b;
    }

    public f3 z(int i2) {
        this.s = true;
        this.t = i2;
        return this;
    }
}
