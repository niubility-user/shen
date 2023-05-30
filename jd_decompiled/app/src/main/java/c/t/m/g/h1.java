package c.t.m.g;

/* loaded from: classes.dex */
public class h1 {
    public static double a(double[] dArr, double d) {
        return Math.sqrt(i(dArr, d));
    }

    public static double b(double[] dArr, double d, int i2, int i3) {
        double d2 = 0.0d;
        for (int i4 = i2; i4 < i3; i4++) {
            d2 += (dArr[i4] - d) * (dArr[i4] - d);
        }
        double d3 = i3 - i2;
        Double.isNaN(d3);
        return d2 / d3;
    }

    public static double c(double[] dArr, int i2, int i3) {
        double j2 = j(dArr, i2, i3);
        double d = i3 - i2;
        Double.isNaN(d);
        return j2 / d;
    }

    public static double d(double[] dArr, double[] dArr2) {
        double d = 0.0d;
        for (int i2 = 0; i2 < dArr.length; i2++) {
            d += dArr[i2] * dArr2[i2];
        }
        return d;
    }

    public static int e(double[] dArr) {
        int i2 = 0;
        for (int i3 = 1; i3 < dArr.length; i3++) {
            if (dArr[i3] > dArr[i2]) {
                i2 = i3;
            }
        }
        return i2;
    }

    public static void f(double[] dArr, double[] dArr2, double d) {
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            dArr[i2] = dArr2[i2] - d;
        }
    }

    public static void g(double[] dArr, double[] dArr2, double[] dArr3) {
        for (int i2 = 0; i2 < dArr2.length; i2++) {
            dArr[i2] = dArr2[i2] * dArr3[i2];
        }
    }

    public static double h(double[] dArr) {
        return c(dArr, 0, dArr.length);
    }

    public static double i(double[] dArr, double d) {
        return b(dArr, d, 0, dArr.length);
    }

    public static double j(double[] dArr, int i2, int i3) {
        double d = 0.0d;
        while (i2 < i3) {
            d += dArr[i2];
            i2++;
        }
        return d;
    }

    public static double k(double... dArr) {
        double d = 0.0d;
        for (double d2 : dArr) {
            d += d2 * d2;
        }
        return Math.sqrt(d);
    }

    public static double l(double[] dArr) {
        return j(dArr, 0, dArr.length);
    }
}
