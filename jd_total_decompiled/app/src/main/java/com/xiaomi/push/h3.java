package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class h3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18688c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18689e;
    private String b = "";
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private String f18690f = "";

    /* renamed from: g  reason: collision with root package name */
    private int f18691g = -1;

    public static h3 l(byte[] bArr) {
        h3 h3Var = new h3();
        h3Var.c(bArr);
        return h3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18691g < 0) {
            i();
        }
        return this.f18691g;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        j(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (n()) {
            y0Var.x(1, m());
        }
        if (q()) {
            y0Var.x(2, p());
        }
        if (t()) {
            y0Var.x(3, s());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int g2 = n() ? 0 + y0.g(1, m()) : 0;
        if (q()) {
            g2 += y0.g(2, p());
        }
        if (t()) {
            g2 += y0.g(3, s());
        }
        this.f18691g = g2;
        return g2;
    }

    public h3 j(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                k(c0Var.h());
            } else if (b == 18) {
                o(c0Var.h());
            } else if (b == 26) {
                r(c0Var.h());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public h3 k(String str) {
        this.a = true;
        this.b = str;
        return this;
    }

    public String m() {
        return this.b;
    }

    public boolean n() {
        return this.a;
    }

    public h3 o(String str) {
        this.f18688c = true;
        this.d = str;
        return this;
    }

    public String p() {
        return this.d;
    }

    public boolean q() {
        return this.f18688c;
    }

    public h3 r(String str) {
        this.f18689e = true;
        this.f18690f = str;
        return this;
    }

    public String s() {
        return this.f18690f;
    }

    public boolean t() {
        return this.f18689e;
    }
}
