package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class n5 {
    public final double a;
    public final double b;

    /* renamed from: c  reason: collision with root package name */
    public final double f16884c;
    public final double d;

    /* renamed from: e  reason: collision with root package name */
    public final double f16885e;

    /* renamed from: f  reason: collision with root package name */
    public final double f16886f;

    public n5(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d3;
        this.f16884c = d2;
        this.d = d4;
        this.f16885e = (d + d2) / 2.0d;
        this.f16886f = (d3 + d4) / 2.0d;
    }

    public boolean a(double d, double d2) {
        return this.a <= d && d <= this.f16884c && this.b <= d2 && d2 <= this.d;
    }

    public boolean a(double d, double d2, double d3, double d4) {
        return d < this.f16884c && this.a < d2 && d3 < this.d && this.b < d4;
    }

    public boolean a(n5 n5Var) {
        return n5Var.a >= this.a && n5Var.f16884c <= this.f16884c && n5Var.b >= this.b && n5Var.d <= this.d;
    }

    public boolean a(o5 o5Var) {
        return a(o5Var.a, o5Var.b);
    }

    public boolean b(n5 n5Var) {
        return a(n5Var.a, n5Var.f16884c, n5Var.b, n5Var.d);
    }
}
