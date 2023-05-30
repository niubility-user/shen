package com.xiaomi.push;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes11.dex */
public final class a3 extends o2 {
    private boolean a;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18449c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f18450e;

    /* renamed from: g  reason: collision with root package name */
    private boolean f18452g;
    private int b = 0;
    private boolean d = false;

    /* renamed from: f  reason: collision with root package name */
    private int f18451f = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f18453h = false;

    /* renamed from: i  reason: collision with root package name */
    private List<String> f18454i = Collections.emptyList();

    /* renamed from: j  reason: collision with root package name */
    private int f18455j = -1;

    public static a3 n(byte[] bArr) {
        a3 a3Var = new a3();
        a3Var.c(bArr);
        return a3Var;
    }

    public static a3 r(c0 c0Var) {
        a3 a3Var = new a3();
        a3Var.k(c0Var);
        return a3Var;
    }

    public boolean A() {
        return this.f18452g;
    }

    @Override // com.xiaomi.push.o2
    public int a() {
        if (this.f18455j < 0) {
            i();
        }
        return this.f18455j;
    }

    @Override // com.xiaomi.push.o2
    public /* bridge */ /* synthetic */ o2 b(c0 c0Var) {
        k(c0Var);
        return this;
    }

    @Override // com.xiaomi.push.o2
    public void e(y0 y0Var) {
        if (p()) {
            y0Var.M(1, u());
        }
        if (v()) {
            y0Var.y(2, t());
        }
        if (x()) {
            y0Var.t(3, w());
        }
        if (A()) {
            y0Var.y(4, z());
        }
        Iterator<String> it = o().iterator();
        while (it.hasNext()) {
            y0Var.x(5, it.next());
        }
    }

    @Override // com.xiaomi.push.o2
    public int i() {
        int i2 = 0;
        int H = p() ? y0.H(1, u()) + 0 : 0;
        if (v()) {
            H += y0.h(2, t());
        }
        if (x()) {
            H += y0.c(3, w());
        }
        if (A()) {
            H += y0.h(4, z());
        }
        Iterator<String> it = o().iterator();
        while (it.hasNext()) {
            i2 += y0.l(it.next());
        }
        int size = H + i2 + (o().size() * 1);
        this.f18455j = size;
        return size;
    }

    public a3 j(int i2) {
        this.a = true;
        this.b = i2;
        return this;
    }

    public a3 k(c0 c0Var) {
        while (true) {
            int b = c0Var.b();
            if (b == 0) {
                return this;
            }
            if (b == 8) {
                j(c0Var.u());
            } else if (b == 16) {
                m(c0Var.l());
            } else if (b == 24) {
                q(c0Var.p());
            } else if (b == 32) {
                s(c0Var.l());
            } else if (b == 42) {
                l(c0Var.h());
            } else if (!g(c0Var, b)) {
                return this;
            }
        }
    }

    public a3 l(String str) {
        str.getClass();
        if (this.f18454i.isEmpty()) {
            this.f18454i = new ArrayList();
        }
        this.f18454i.add(str);
        return this;
    }

    public a3 m(boolean z) {
        this.f18449c = true;
        this.d = z;
        return this;
    }

    public List<String> o() {
        return this.f18454i;
    }

    public boolean p() {
        return this.a;
    }

    public a3 q(int i2) {
        this.f18450e = true;
        this.f18451f = i2;
        return this;
    }

    public a3 s(boolean z) {
        this.f18452g = true;
        this.f18453h = z;
        return this;
    }

    public boolean t() {
        return this.d;
    }

    public int u() {
        return this.b;
    }

    public boolean v() {
        return this.f18449c;
    }

    public int w() {
        return this.f18451f;
    }

    public boolean x() {
        return this.f18450e;
    }

    public int y() {
        return this.f18454i.size();
    }

    public boolean z() {
        return this.f18453h;
    }
}
