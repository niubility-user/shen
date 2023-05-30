package c.t.m.g;

import com.jingdong.sdk.platform.business.personal.R2;
import java.util.Arrays;

/* loaded from: classes.dex */
public class a {
    public static final a o = new a();
    public double[] a = new double[R2.anim.manto_push_right_in];
    public double[] b = new double[8];

    /* renamed from: c  reason: collision with root package name */
    public double[] f275c = new double[7];
    public double[] d = new double[36];

    /* renamed from: e  reason: collision with root package name */
    public double[] f276e = new double[128];

    /* renamed from: f  reason: collision with root package name */
    public double[] f277f = new double[128];

    /* renamed from: g  reason: collision with root package name */
    public double[] f278g = new double[128];

    /* renamed from: h  reason: collision with root package name */
    public double[] f279h = null;

    /* renamed from: i  reason: collision with root package name */
    public double[] f280i = null;

    /* renamed from: j  reason: collision with root package name */
    public double[] f281j = null;

    /* renamed from: k  reason: collision with root package name */
    public double[] f282k = null;

    /* renamed from: l  reason: collision with root package name */
    public double[] f283l = null;

    /* renamed from: m  reason: collision with root package name */
    public double[] f284m = null;

    /* renamed from: n  reason: collision with root package name */
    public double[] f285n = null;

    /* renamed from: c.t.m.g.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public enum EnumC0006a {
        TIME,
        FFT
    }

    public static double[] i(double[][] dArr) {
        return o.g(dArr);
    }

    public final void a(double[] dArr) {
        int length = dArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            double d = i2;
            Double.isNaN(d);
            double d2 = (double) (length - 1);
            Double.isNaN(d2);
            dArr[i2] = 0.5d - (Math.cos((d * 6.283185307179586d) / d2) * 0.5d);
        }
    }

    public final void b(double[] dArr, int i2, double d) {
        int length = (dArr.length - i2) / 2;
        for (int length2 = dArr.length - 1; length2 >= 0; length2--) {
            if (length2 >= i2 + length || length2 < length) {
                dArr[length2] = d;
            } else {
                dArr[length2] = dArr[length2 - length];
            }
        }
    }

    public final void c(double[] dArr, int i2, EnumC0006a enumC0006a, double[] dArr2, double d) {
        if (enumC0006a == EnumC0006a.TIME) {
            h(this.b, dArr2);
            double[] dArr3 = this.b;
            System.arraycopy(dArr3, 0, dArr, i2, dArr3.length);
        } else if (enumC0006a == EnumC0006a.FFT) {
            e(this.f275c, dArr2, d);
            double[] dArr4 = this.f275c;
            System.arraycopy(dArr4, 0, dArr, i2, dArr4.length);
        }
    }

    public final void d(double[] dArr, double[] dArr2) {
        int length = dArr2.length;
        Arrays.fill(dArr, 0.0d);
        System.arraycopy(dArr2, 0, this.f277f, 0, dArr2.length);
        Arrays.sort(this.f277f);
        if (length % 2 == 0) {
            double[] dArr3 = this.f277f;
            int i2 = length / 2;
            dArr[0] = (dArr3[i2 - 1] + dArr3[i2]) / 2.0d;
        } else {
            double[] dArr4 = this.f277f;
            double d = length + 0;
            Double.isNaN(d);
            dArr[0] = dArr4[(int) Math.floor(d / 2.0d)];
        }
        double[] dArr5 = this.f277f;
        int i3 = length - 1;
        dArr[1] = (dArr5[0] + dArr5[i3]) / 2.0d;
        double h2 = h1.h(dArr2);
        double d2 = k4.f522c;
        Double.isNaN(d2);
        double d3 = 1.0d / d2;
        Arrays.fill(this.f278g, 0.0d);
        double d4 = 0.0d;
        int i4 = 0;
        for (int i5 = 0; i5 < dArr2.length; i5++) {
            if (dArr2[i5] > h2) {
                this.f278g[i4] = dArr2[i5];
                d4 += dArr2[i5];
                i4++;
            }
        }
        if (i4 != 0) {
            double d5 = i4;
            Double.isNaN(d5);
            d4 /= d5;
        }
        b(this.f278g, i4, d4);
        double[] dArr6 = this.f278g;
        int length2 = (dArr6.length - i4) / 2;
        f(this.b, dArr6, length2, length2 + i4);
        dArr[2] = i4;
        double[] dArr7 = this.b;
        System.arraycopy(dArr7, 0, dArr, 3, dArr7.length);
        e(this.f275c, this.f278g, d3);
        double[] dArr8 = this.f275c;
        System.arraycopy(dArr8, 0, dArr, 11, dArr8.length);
        Arrays.fill(this.f278g, 0.0d);
        double d6 = 0.0d;
        int i6 = 0;
        for (int i7 = 0; i7 < dArr2.length; i7++) {
            if (dArr2[i7] < h2) {
                this.f278g[i6] = dArr2[i7];
                d6 += dArr2[i7];
                i6++;
            }
        }
        if (i6 != 0) {
            double d7 = i6;
            Double.isNaN(d7);
            d6 /= d7;
        }
        b(this.f278g, i6, d6);
        double[] dArr9 = this.f278g;
        int length3 = (dArr9.length - i6) / 2;
        f(this.b, dArr9, length3, length3 + i6);
        dArr[18] = i6;
        double[] dArr10 = this.b;
        System.arraycopy(dArr10, 0, dArr, 19, dArr10.length);
        e(this.f275c, this.f278g, d3);
        double[] dArr11 = this.f275c;
        System.arraycopy(dArr11, 0, dArr, 27, dArr11.length);
        double[] dArr12 = this.f277f;
        dArr[34] = dArr12[i3] - h2;
        dArr[35] = h2 - dArr12[0];
    }

    public void e(double[] dArr, double[] dArr2, double d) {
        double[] dArr3;
        double[] dArr4;
        int length = dArr2.length;
        int i2 = (length / 2) + 1;
        double[] dArr5 = this.f279h;
        double d2 = 2.0d;
        if (dArr5 == null || dArr5.length != length) {
            this.f279h = new double[length];
            this.f280i = new double[length];
            double[] dArr6 = new double[length];
            this.f281j = dArr6;
            this.f282k = new double[i2];
            this.f283l = new double[i2];
            this.f284m = new double[i2];
            this.f285n = new double[i2];
            a(dArr6);
            int i3 = 0;
            while (true) {
                double[] dArr7 = this.f283l;
                if (i3 >= dArr7.length) {
                    break;
                }
                double d3 = i3;
                Double.isNaN(d3);
                double d4 = (double) (i2 - 1);
                Double.isNaN(d4);
                dArr7[i3] = ((d3 * (1.0d / d)) / 2.0d) / d4;
                i3++;
            }
        }
        Arrays.fill(dArr, 0.0d);
        System.arraycopy(dArr2, 0, this.f279h, 0, length);
        Arrays.fill(this.f280i, 0.0d);
        double h2 = h1.h(this.f279h);
        double[] dArr8 = this.f279h;
        h1.f(dArr8, dArr8, h2);
        int i4 = 0;
        while (true) {
            dArr3 = this.f279h;
            if (i4 >= dArr3.length) {
                break;
            }
            dArr3[i4] = dArr3[i4] * this.f281j[i4];
            i4++;
        }
        m6.a(dArr3, this.f280i);
        double d5 = -1.7976931348623157E308d;
        Arrays.fill(this.f284m, 0.0d);
        double d6 = 0.0d;
        double d7 = 0.0d;
        int i5 = 0;
        while (true) {
            dArr4 = this.f282k;
            if (i5 >= i2) {
                break;
            }
            double[] dArr9 = this.f279h;
            double[] dArr10 = this.f280i;
            double abs = Math.abs(Math.sqrt((dArr9[i5] * dArr9[i5]) + (dArr10[i5] * dArr10[i5]))) * d2;
            double d8 = i2;
            Double.isNaN(d8);
            dArr4[i5] = abs / d8;
            double[] dArr11 = this.f282k;
            if (d5 <= dArr11[i5]) {
                d5 = dArr11[i5];
            }
            d6 += dArr11[i5];
            double d9 = dArr11[i5] * dArr11[i5];
            double d10 = d9 * d9;
            d7 += d10;
            this.f284m[i5] = d10;
            i5++;
            d2 = 2.0d;
        }
        double length2 = dArr4.length;
        Double.isNaN(length2);
        double d11 = d6 / length2;
        double d12 = 0.0d;
        double d13 = 0.0d;
        int i6 = 0;
        while (i6 < i2) {
            int i7 = i2;
            double[] dArr12 = this.f284m;
            dArr12[i6] = d7 == 0.0d ? 0.0d : dArr12[i6] / d7;
            d12 += dArr12[i6] * this.f283l[i6];
            double d14 = d6 == 0.0d ? 0.0d : this.f282k[i6] / d6;
            if (d14 == 0.0d) {
                d14 = 1.0E-7d;
            }
            d13 -= d14 * Math.log(d14);
            i6++;
            i2 = i7;
        }
        h1.g(this.f285n, this.f282k, this.f283l);
        double h3 = h1.h(this.f285n);
        double a = h1.a(this.f285n, h3);
        double d15 = a == 0.0d ? 0.0d : h3 / a;
        double a2 = h1.a(this.f282k, d11);
        dArr[0] = d12;
        dArr[1] = a2 != 0.0d ? d11 / a2 : 0.0d;
        dArr[2] = d11;
        dArr[3] = d5;
        dArr[4] = h3;
        dArr[5] = d15;
        dArr[6] = d13;
    }

    public void f(double[] dArr, double[] dArr2, int i2, int i3) {
        double d = 0.0d;
        Arrays.fill(dArr, 0.0d);
        int i4 = i3 - i2;
        double d2 = 0.0d;
        for (int i5 = i2; i5 < i3; i5++) {
            d2 += dArr2[i5];
        }
        double d3 = i4;
        Double.isNaN(d3);
        double d4 = d2 / d3;
        double d5 = -1.7976931348623157E308d;
        double d6 = Double.MAX_VALUE;
        int i6 = Integer.MIN_VALUE;
        int i7 = i2;
        double d7 = 0.0d;
        double d8 = 0.0d;
        int i8 = Integer.MAX_VALUE;
        int i9 = 0;
        while (i7 < i3) {
            d7 += (dArr2[i7] - d4) * (dArr2[i7] - d4);
            if (d5 < dArr2[i7]) {
                d5 = dArr2[i7];
            }
            if (d6 > dArr2[i7]) {
                d6 = dArr2[i7];
            }
            double signum = Math.signum(dArr2[i7] - d4);
            if (i7 <= i2 || signum == d || signum == d8) {
                i9 = i9;
            } else {
                if (i8 == Integer.MAX_VALUE) {
                    i8 = i7;
                }
                i9++;
                i6 = i7;
            }
            i7++;
            d8 = signum;
            d = 0.0d;
        }
        int i10 = i9;
        Double.isNaN(d3);
        double sqrt = Math.sqrt(d7 / d3);
        double d9 = 0.0d;
        double d10 = sqrt != 0.0d ? d4 / sqrt : 0.0d;
        if (i10 > 1) {
            double d11 = i6 - i8;
            double d12 = i10;
            Double.isNaN(d12);
            Double.isNaN(d11);
            d9 = d11 / (d12 - 1.0d);
        }
        dArr[0] = d4;
        dArr[1] = sqrt;
        dArr[2] = d10;
        dArr[3] = d5;
        dArr[4] = d6;
        dArr[5] = d5 - d6;
        dArr[6] = i10;
        dArr[7] = d9;
    }

    public final synchronized double[] g(double[][] dArr) {
        Arrays.fill(this.a, 0.0d);
        double[] dArr2 = dArr[3];
        double[] dArr3 = dArr[4];
        double[] dArr4 = dArr[5];
        for (int i2 = 0; i2 < dArr4.length; i2++) {
            this.f276e[i2] = dArr4[i2] - dArr4[0];
        }
        double d = k4.f522c;
        Double.isNaN(d);
        double d2 = 1.0d / d;
        double[] dArr5 = this.a;
        EnumC0006a enumC0006a = EnumC0006a.TIME;
        c(dArr5, 0, enumC0006a, dArr2, d2);
        double[] dArr6 = this.a;
        EnumC0006a enumC0006a2 = EnumC0006a.FFT;
        c(dArr6, 8, enumC0006a2, dArr2, d2);
        c(this.a, 15, enumC0006a, dArr3, d2);
        c(this.a, 23, enumC0006a2, dArr3, d2);
        c(this.a, 30, enumC0006a, this.f276e, d2);
        c(this.a, 38, enumC0006a2, this.f276e, d2);
        d(this.d, dArr2);
        double[] dArr7 = this.d;
        System.arraycopy(dArr7, 0, this.a, 45, dArr7.length);
        c(this.a, 81, enumC0006a, dArr[6], d2);
        c(this.a, 89, enumC0006a2, dArr[6], d2);
        c(this.a, 96, enumC0006a, dArr[7], d2);
        c(this.a, 104, enumC0006a2, dArr[7], d2);
        c(this.a, 111, enumC0006a, dArr[8], d2);
        c(this.a, 119, enumC0006a2, dArr[8], d2);
        c(this.a, 126, enumC0006a, dArr[9], d2);
        c(this.a, 134, enumC0006a2, dArr[9], d2);
        return this.a;
    }

    public final void h(double[] dArr, double[] dArr2) {
        f(dArr, dArr2, 0, dArr2.length);
    }
}
