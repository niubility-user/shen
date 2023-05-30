package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class a6 {
    private c6 a;
    private c6 b;

    /* renamed from: c  reason: collision with root package name */
    private c6 f16231c;
    private d6 d;

    public a6() {
        this.d = new d6();
    }

    public a6(c6 c6Var, c6 c6Var2, c6 c6Var3) {
        this();
        a(c6Var, c6Var2, c6Var3);
    }

    public a6(float[] fArr) {
        this();
        a(new c6(fArr[0], fArr[1], fArr[2]), new c6(fArr[3], fArr[4], fArr[5]), new c6(fArr[6], fArr[7], fArr[8]));
    }

    private float a() {
        float[] a = this.d.a();
        float a2 = a(a[0], a[1], a[2], a[3], a[4], a[5]);
        float a3 = a(a[3], a[4], a[5], a[6], a[7], a[8]);
        float a4 = ((a2 + a3) + a(a[0], a[1], a[2], a[6], a[7], a[8])) / 2.0f;
        return (float) Math.sqrt((a4 - a2) * a4 * (a4 - a3) * (a4 - r0));
    }

    private float a(float f2, float f3, float f4, float f5, float f6, float f7) {
        float f8 = f2 - f5;
        float f9 = f3 - f6;
        float f10 = f4 - f7;
        return (float) Math.sqrt((f8 * f8) + (f9 * f9) + (f10 * f10));
    }

    private void a(c6 c6Var, c6 c6Var2, c6 c6Var3) {
        this.a = c6Var;
        this.b = c6Var2;
        this.f16231c = c6Var3;
        this.d.a(c6Var);
        this.d.a(c6Var2);
        this.d.a(c6Var3);
    }

    public boolean a(float[] fArr) {
        c6 c6Var = new c6(fArr[0], fArr[1], fArr[2]);
        return ((double) Math.abs(a() - ((new a6(c6Var, this.a, this.b).a() + new a6(c6Var, this.a, this.f16231c).a()) + new a6(c6Var, this.b, this.f16231c).a()))) < 0.001d;
    }

    public float[] b() {
        return this.d.a();
    }
}
