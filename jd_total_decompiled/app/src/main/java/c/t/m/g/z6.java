package c.t.m.g;

import java.util.Arrays;

/* loaded from: classes.dex */
public class z6 {
    public double[] a = {0.0d, 0.0d, 0.0d};
    public double[] b = {0.0d, 0.0d, 0.0d};

    public final double a(double[][] dArr, int i2, int i3) {
        int i4;
        Arrays.fill(this.a, 0.0d);
        Arrays.fill(this.b, 0.0d);
        int i5 = (i2 + i3) / 2;
        int i6 = i5 - i2;
        int i7 = i3 - i2;
        while (true) {
            i4 = 0;
            if (i2 >= i3) {
                break;
            }
            while (i4 < 3) {
                if (i2 < i5) {
                    double[] dArr2 = this.a;
                    dArr2[i4] = dArr2[i4] + dArr[i4 + 0][i2];
                }
                double[] dArr3 = this.b;
                dArr3[i4] = dArr3[i4] + dArr[i4 + 0][i2];
                i4++;
            }
            i2++;
        }
        while (true) {
            double[] dArr4 = this.a;
            if (i4 >= 3) {
                return Math.acos(h1.d(dArr4, this.b) / (h1.k(this.a) * h1.k(this.b)));
            }
            double d = dArr4[i4];
            double d2 = i6;
            Double.isNaN(d2);
            dArr4[i4] = d / d2;
            double[] dArr5 = this.b;
            double d3 = dArr5[i4];
            double d4 = i7;
            Double.isNaN(d4);
            dArr5[i4] = d3 / d4;
            i4++;
        }
    }

    public boolean b(double[][] dArr) {
        if (dArr == null || t2.f(dArr[3])) {
            return false;
        }
        int length = dArr[3].length;
        int i2 = length / 2;
        return a(dArr, 0, i2) > 0.6d || a(dArr, i2, length) > 0.6d || a(dArr, length / 4, (length * 3) / 4) > 0.6d;
    }
}
