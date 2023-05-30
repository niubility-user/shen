package com.xiaomi.push;

/* loaded from: classes11.dex */
public final class j3 extends o2 {
    private boolean a;
    private a b = a.f18439c;

    /* renamed from: c  reason: collision with root package name */
    private int f18773c = -1;

    public static j3 m(byte[] bArr) {
        j3 j3Var = new j3();
        j3Var.c(bArr);
        return j3Var;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18773c < 0) {
            i();
        }
        return this.f18773c;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        l(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (n()) {
            y0Var.v(1, j());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int e2 = n() ? 0 + y0.e(1, j()) : 0;
        this.f18773c = e2;
        return e2;
    }

    public a j() {
        return this.b;
    }

    public j3 k(a aVar) {
        this.a = true;
        this.b = aVar;
        return this;
    }

    public j3 l(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 10) {
                k(c0Var.e());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public boolean n() {
        return this.a;
    }
}
