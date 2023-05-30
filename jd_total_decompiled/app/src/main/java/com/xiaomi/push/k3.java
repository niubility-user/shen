package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class k3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18793c;
    private a b = a.f18439c;
    private c3 d = null;

    /* renamed from: e  reason: collision with root package name */
    private int f18794e = -1;

    public static k3 o(byte[] bArr) {
        k3 k3Var = new k3();
        k3Var.c(bArr);
        return k3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18794e < 0) {
            i();
        }
        return this.f18794e;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        m(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (p()) {
            y0Var.v(1, j());
        }
        if (q()) {
            y0Var.w(2, k());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int e2 = p() ? 0 + y0.e(1, j()) : 0;
        if (q()) {
            e2 += y0.f(2, k());
        }
        this.f18794e = e2;
        return e2;
    }

    public a j() {
        return this.b;
    }

    public c3 k() {
        return this.d;
    }

    public k3 l(a aVar) {
        this.a = true;
        this.b = aVar;
        return this;
    }

    public k3 m(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                l(c0Var.e());
            } else if (b == 18) {
                c3 c3Var = new c3();
                c0Var.k(c3Var);
                n(c3Var);
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public k3 n(c3 c3Var) {
        c3Var.getClass();
        this.f18793c = true;
        this.d = c3Var;
        return this;
    }

    public boolean p() {
        return this.a;
    }

    public boolean q() {
        return this.f18793c;
    }
}
