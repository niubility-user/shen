package c.t.m.g;

import com.tencent.map.geolocation.TencentLocation;

/* loaded from: classes.dex */
public class r1 {
    public double a;
    public double b;

    /* renamed from: c  reason: collision with root package name */
    public double f649c;
    public double d;

    /* renamed from: e  reason: collision with root package name */
    public double f650e;

    /* renamed from: f  reason: collision with root package name */
    public double f651f;

    /* renamed from: g  reason: collision with root package name */
    public long f652g;

    /* renamed from: h  reason: collision with root package name */
    public double f653h;

    /* renamed from: i  reason: collision with root package name */
    public String f654i;

    public r1(double[] dArr) {
        try {
            this.f652g = (long) dArr[0];
            this.a = dArr[1];
            this.b = dArr[2];
            this.f651f = dArr[3];
            this.f653h = dArr[4];
            this.f650e = dArr[5];
            this.d = dArr[6];
            double d = dArr[7];
            this.f654i = d == 1.0d ? "gps" : d == 0.0d ? TencentLocation.FUSED_PROVIDER : "unknown";
            this.f649c = dArr[9];
        } catch (Exception unused) {
        }
    }

    public double a() {
        return this.d;
    }

    public double b() {
        return this.f649c;
    }

    public double c() {
        return this.f651f;
    }

    public double d() {
        return this.a;
    }

    public double e() {
        return this.b;
    }

    public double f() {
        return this.f653h;
    }

    public String g() {
        return this.f654i;
    }

    public double h() {
        return this.f650e;
    }

    public long i() {
        return this.f652g;
    }
}
