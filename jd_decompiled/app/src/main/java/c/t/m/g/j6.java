package c.t.m.g;

import com.jingdong.sdk.platform.business.personal.R2;
import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class j6 extends l5 {
    public static double[][] d = (double[][]) Array.newInstance(double.class, 3, R2.anim.manto_push_right_in);

    /* renamed from: e  reason: collision with root package name */
    public static double[] f503e = new double[3];
    public int a;
    public double[][] b;

    /* renamed from: c  reason: collision with root package name */
    public double[] f504c;

    public j6() {
        int i2 = k4.a;
        this.a = i2;
        this.b = (double[][]) Array.newInstance(double.class, i2, i2);
        this.f504c = new double[i2];
    }

    @Override // c.t.m.g.l5
    public void a() {
    }

    @Override // c.t.m.g.l5
    public double[] b(double[] dArr) {
        int i2;
        int i3 = 0;
        while (i3 < this.a - 1) {
            int i4 = i3 + 1;
            int i5 = i4;
            while (true) {
                int i6 = this.a;
                if (i5 < i6) {
                    int i7 = ((i6 * i3) + i5) - (((i3 + 2) * i4) / 2);
                    double d2 = h1.d(dArr, d[i7]) + f503e[i7];
                    if (d2 > 100.0d) {
                        d2 = 100.0d;
                    }
                    if (d2 < -100.0d) {
                        d2 = -100.0d;
                    }
                    double d3 = -d2;
                    this.b[i3][i5] = 1.0d / (Math.exp(d3 / 1.0d) + 1.0d);
                    this.b[i5][i3] = 1.0d / (Math.exp((-d3) / 1.0d) + 1.0d);
                    i5++;
                }
            }
            i3 = i4;
        }
        double d4 = 0.0d;
        for (int i8 = 0; i8 < this.a; i8++) {
            this.f504c[i8] = 0.0d;
            int i9 = 0;
            while (true) {
                i2 = this.a;
                if (i9 < i2) {
                    if (i8 != i9) {
                        double[] dArr2 = this.f504c;
                        dArr2[i8] = dArr2[i8] + (1.0d / this.b[i8][i9]);
                    }
                    i9++;
                }
            }
            double[] dArr3 = this.f504c;
            double d5 = dArr3[i8];
            double d6 = i2;
            Double.isNaN(d6);
            dArr3[i8] = 1.0d / (d5 - (d6 - 2.0d));
            d4 += dArr3[i8];
        }
        for (int i10 = 0; i10 < this.a; i10++) {
            double[] dArr4 = this.f504c;
            dArr4[i10] = dArr4[i10] / d4;
        }
        return this.f504c;
    }

    @Override // c.t.m.g.l5
    public double[] c(double[][] dArr) {
        return a.i(dArr);
    }

    @Override // c.t.m.g.l5
    public String d() {
        return "SVM";
    }

    @Override // c.t.m.g.l5
    public void e() {
    }

    @Override // c.t.m.g.l5
    public void f() {
    }
}
