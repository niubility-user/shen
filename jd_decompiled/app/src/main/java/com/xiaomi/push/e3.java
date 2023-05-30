package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class e3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18557c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18558e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18560g;
    private boolean b = false;
    private String d = "";

    /* renamed from: f  reason: collision with root package name */
    private String f18559f = "";

    /* renamed from: h  reason: collision with root package name */
    private String f18561h = "";

    /* renamed from: i  reason: collision with root package name */
    private int f18562i = -1;

    public static e3 m(byte[] bArr) {
        e3 e3Var = new e3();
        e3Var.c(bArr);
        return e3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18562i < 0) {
            i();
        }
        return this.f18562i;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        j(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (r()) {
            y0Var.y(1, o());
        }
        if (u()) {
            y0Var.x(2, n());
        }
        if (v()) {
            y0Var.x(3, q());
        }
        if (w()) {
            y0Var.x(4, t());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int h2 = r() ? 0 + y0.h(1, o()) : 0;
        if (u()) {
            h2 += y0.g(2, n());
        }
        if (v()) {
            h2 += y0.g(3, q());
        }
        if (w()) {
            h2 += y0.g(4, t());
        }
        this.f18562i = h2;
        return h2;
    }

    public e3 j(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 8) {
                l(c0Var.l());
            } else if (b == 18) {
                k(c0Var.h());
            } else if (b == 26) {
                p(c0Var.h());
            } else if (b == 34) {
                s(c0Var.h());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public e3 k(String str) {
        this.f18557c = true;
        this.d = str;
        return this;
    }

    public e3 l(boolean z) {
        this.a = true;
        this.b = z;
        return this;
    }

    public String n() {
        return this.d;
    }

    public boolean o() {
        return this.b;
    }

    public e3 p(String str) {
        this.f18558e = true;
        this.f18559f = str;
        return this;
    }

    public String q() {
        return this.f18559f;
    }

    public boolean r() {
        return this.a;
    }

    public e3 s(String str) {
        this.f18560g = true;
        this.f18561h = str;
        return this;
    }

    public String t() {
        return this.f18561h;
    }

    public boolean u() {
        return this.f18557c;
    }

    public boolean v() {
        return this.f18558e;
    }

    public boolean w() {
        return this.f18560g;
    }
}
