package com.tencent.mapsdk.internal;

import com.jd.dynamic.DYConstants;

/* loaded from: classes9.dex */
public class b6 {
    private float a;
    private float b;

    /* renamed from: c  reason: collision with root package name */
    private float f16291c;

    public b6(float f2, float f3, float f4) {
        this.a = f2;
        this.b = f3;
        this.f16291c = f4;
        f();
    }

    public static b6 a(b6 b6Var, b6 b6Var2) {
        return new b6(b6Var.a + b6Var2.a, b6Var.b + b6Var2.b, b6Var.f16291c + b6Var2.f16291c);
    }

    public static b6 b(b6 b6Var) {
        return new b6(-b6Var.a, -b6Var.b, -b6Var.f16291c);
    }

    public static b6 c(b6 b6Var) {
        float f2 = b6Var.a;
        float f3 = b6Var.b;
        double d = f2;
        double b = b6Var.b();
        Double.isNaN(d);
        float f4 = (float) (d / b);
        double d2 = -f3;
        double b2 = b6Var.b();
        Double.isNaN(d2);
        float f5 = (float) (d2 / b2);
        b6 b6Var2 = new b6(f4, f5, 0.0f);
        return b6Var2.a(b6Var) != 90.0d ? new b6(-f4, -f5, 0.0f) : b6Var2;
    }

    private void f() {
        double b = b();
        if (b == 0.0d) {
            return;
        }
        double d = this.a;
        Double.isNaN(d);
        this.a = (float) (d / b);
        double d2 = this.b;
        Double.isNaN(d2);
        this.b = (float) (d2 / b);
        double d3 = this.f16291c;
        Double.isNaN(d3);
        this.f16291c = (float) (d3 / b);
    }

    public double a(b6 b6Var) {
        double c2 = (c() * b6Var.c()) + (d() * b6Var.d()) + (e() * b6Var.e());
        Double.isNaN(c2);
        return (Math.acos(c2 / (b() * b6Var.b())) * 180.0d) / 3.141592653589793d;
    }

    public float[] a() {
        return new float[]{this.a, this.b, this.f16291c};
    }

    public double b() {
        float f2 = this.a;
        float f3 = this.b;
        float f4 = (f2 * f2) + (f3 * f3);
        float f5 = this.f16291c;
        return Math.sqrt(f4 + (f5 * f5));
    }

    public float c() {
        return this.a;
    }

    public float d() {
        return this.b;
    }

    public float e() {
        return this.f16291c;
    }

    public String toString() {
        return this.a + DYConstants.DY_REGEX_COMMA + this.b + DYConstants.DY_REGEX_COMMA + this.f16291c;
    }
}
