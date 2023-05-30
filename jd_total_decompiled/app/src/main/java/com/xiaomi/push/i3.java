package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class i3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18737c;
    private int b = 0;
    private String d = "";

    /* renamed from: e  reason: collision with root package name */
    private int f18738e = -1;

    public static i3 m(byte[] bArr) {
        i3 i3Var = new i3();
        i3Var.c(bArr);
        return i3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18738e < 0) {
            i();
        }
        return this.f18738e;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        k(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (o()) {
            y0Var.t(1, q());
        }
        if (p()) {
            y0Var.x(2, n());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int c2 = o() ? 0 + y0.c(1, q()) : 0;
        if (p()) {
            c2 += y0.g(2, n());
        }
        this.f18738e = c2;
        return c2;
    }

    public i3 j(int i2) {
        this.a = true;
        this.b = i2;
        return this;
    }

    public i3 k(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 8) {
                j(c0Var.p());
            } else if (b == 18) {
                l(c0Var.h());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public i3 l(String str) {
        this.f18737c = true;
        this.d = str;
        return this;
    }

    public String n() {
        return this.d;
    }

    public boolean o() {
        return this.a;
    }

    public boolean p() {
        return this.f18737c;
    }

    public int q() {
        return this.b;
    }
}
