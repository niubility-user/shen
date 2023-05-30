package c.t.m.g;

import java.util.Locale;

/* loaded from: classes.dex */
public class r6 {
    public boolean a = true;
    public long b;

    /* renamed from: c  reason: collision with root package name */
    public double f657c;
    public v1 d;

    /* renamed from: e  reason: collision with root package name */
    public n1 f658e;

    /* renamed from: f  reason: collision with root package name */
    public n1 f659f;

    public r6(int i2) {
        this.d = new v1(i2);
        this.f658e = new n1(i2);
        this.f659f = new n1(i2);
        d();
    }

    public void a(long j2, double d) {
        this.b = j2;
        this.f657c = d;
    }

    public void b(long j2, int i2, double d) {
        this.d.b(i2);
        this.f658e.c(d);
        this.f659f.c(j2 - this.b < 2500 ? this.f657c : -1.0d);
        int c2 = this.d.c();
        for (int i3 = 0; i3 < this.d.e(); i3++) {
            int f2 = this.d.f(i3);
            double d2 = this.f658e.d(i3);
            double d3 = this.f659f.d(i3);
            if (d3 > 4.2d && d2 > 0.9d && (f2 == 1 || f2 == 2)) {
                c2--;
            }
            if (d3 >= 0.0d && d3 < 0.1d && d2 > 0.9d && f2 == 2) {
                c2--;
            }
        }
        boolean z = c2 > this.d.c() / 3;
        String.format(Locale.ENGLISH, "checkArAvailable,%d,%d,%.4f,%d,%.2f,%d", Long.valueOf(j2), Integer.valueOf(i2), Double.valueOf(d), Long.valueOf(this.b), Double.valueOf(this.f657c), Integer.valueOf(c2));
        if (!z) {
            z0.d("AR", "available,false," + (this.d.c() - c2));
        }
        if (this.a != z) {
            this.a = z;
        }
    }

    public boolean c() {
        return this.a;
    }

    public void d() {
        this.a = true;
        this.b = 0L;
        this.f657c = -1.0d;
        this.d.a();
        this.f658e.b();
        this.f659f.b();
    }
}
