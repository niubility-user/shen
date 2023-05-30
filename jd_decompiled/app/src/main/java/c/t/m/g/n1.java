package c.t.m.g;

import java.util.Arrays;

/* loaded from: classes.dex */
public class n1 {
    public int a;
    public double[] b;

    /* renamed from: c  reason: collision with root package name */
    public double f560c;
    public int d;

    /* renamed from: e  reason: collision with root package name */
    public int f561e;

    public n1(int i2) {
        if (i2 <= 0) {
            throw new IllegalArgumentException("cacheSize max > 0.");
        }
        this.a = i2;
        this.b = new double[i2];
        b();
    }

    public final int a(int i2) {
        int i3 = this.f561e;
        int i4 = this.a;
        return i3 < i4 ? i2 : ((this.d + i2) + i4) % i4;
    }

    public void b() {
        this.d = 0;
        this.f561e = 0;
        this.f560c = 0.0d;
        Arrays.fill(this.b, 0.0d);
    }

    public void c(double d) {
        double d2 = this.f560c;
        double[] dArr = this.b;
        int i2 = this.d;
        double d3 = d2 - dArr[i2];
        this.f560c = d3;
        this.f560c = d3 + d;
        dArr[i2] = d;
        int i3 = i2 + 1;
        this.d = i3;
        if (i3 == this.a) {
            this.d = 0;
        }
        int i4 = this.f561e;
        if (i4 < Integer.MAX_VALUE) {
            this.f561e = i4 + 1;
        }
    }

    public double d(int i2) {
        if (i2 < 0 || i2 >= e()) {
            throw new ArrayIndexOutOfBoundsException("cache max size is " + this.a + ",current size is " + e() + ",index is " + i2);
        }
        return this.b[a(i2)];
    }

    public int e() {
        int i2 = this.f561e;
        int i3 = this.a;
        return i2 < i3 ? i2 : i3;
    }
}
