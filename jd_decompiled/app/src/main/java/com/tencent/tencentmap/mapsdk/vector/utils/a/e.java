package com.tencent.tencentmap.mapsdk.vector.utils.a;

/* loaded from: classes9.dex */
public class e {
    public final double a;
    public final double b;

    /* renamed from: c  reason: collision with root package name */
    public final double f18005c;
    public final double d;

    /* renamed from: e  reason: collision with root package name */
    public final double f18006e;

    /* renamed from: f  reason: collision with root package name */
    public final double f18007f;

    public e(double d, double d2, double d3, double d4) {
        this.a = d;
        this.b = d3;
        this.f18005c = d2;
        this.d = d4;
        this.f18006e = (d + d2) / 2.0d;
        this.f18007f = (d3 + d4) / 2.0d;
    }

    public boolean a(double d, double d2) {
        return this.a <= d && d <= this.f18005c && this.b <= d2 && d2 <= this.d;
    }

    public boolean b(e eVar) {
        return a(eVar.a, eVar.f18005c, eVar.b, eVar.d);
    }

    public boolean a(f fVar) {
        return a(fVar.a, fVar.b);
    }

    public boolean a(double d, double d2, double d3, double d4) {
        return d < this.f18005c && this.a < d2 && d3 < this.d && this.b < d4;
    }

    public boolean a(e eVar) {
        return eVar.a >= this.a && eVar.f18005c <= this.f18005c && eVar.b >= this.b && eVar.d <= this.d;
    }
}
