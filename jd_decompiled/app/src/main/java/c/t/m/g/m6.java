package c.t.m.g;

/* loaded from: classes.dex */
public final class m6 {
    public static double[] a;
    public static double[] b;

    public static void a(double[] dArr, double[] dArr2) {
        int length = dArr.length;
        if (length != dArr2.length) {
            throw new IllegalArgumentException("Mismatched lengths");
        }
        int numberOfLeadingZeros = 31 - Integer.numberOfLeadingZeros(length);
        if ((1 << numberOfLeadingZeros) != length) {
            throw new IllegalArgumentException("Length is not a power of 2");
        }
        double[] dArr3 = a;
        if (dArr3 == null || dArr3.length != length / 2) {
            int i2 = length / 2;
            b = new double[i2];
            a = new double[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                double[] dArr4 = b;
                double d = i3;
                Double.isNaN(d);
                double d2 = length;
                Double.isNaN(d2);
                double d3 = (d * 6.283185307179586d) / d2;
                dArr4[i3] = Math.cos(d3);
                a[i3] = Math.sin(d3);
            }
        }
        for (int i4 = 0; i4 < length; i4++) {
            int reverse = Integer.reverse(i4) >>> (32 - numberOfLeadingZeros);
            if (reverse > i4) {
                double d4 = dArr[i4];
                dArr[i4] = dArr[reverse];
                dArr[reverse] = d4;
                double d5 = dArr2[i4];
                dArr2[i4] = dArr2[reverse];
                dArr2[reverse] = d5;
            }
        }
        for (int i5 = 2; i5 <= length; i5 *= 2) {
            int i6 = i5 / 2;
            int i7 = length / i5;
            for (int i8 = 0; i8 < length; i8 += i5) {
                int i9 = i8;
                int i10 = 0;
                while (i9 < i8 + i6) {
                    int i11 = i9 + i6;
                    double d6 = dArr[i11];
                    double[] dArr5 = b;
                    double d7 = d6 * dArr5[i10];
                    double d8 = dArr2[i11];
                    double[] dArr6 = a;
                    double d9 = d7 + (d8 * dArr6[i10]);
                    double d10 = ((-dArr[i11]) * dArr6[i10]) + (dArr2[i11] * dArr5[i10]);
                    dArr[i11] = dArr[i9] - d9;
                    dArr2[i11] = dArr2[i9] - d10;
                    dArr[i9] = dArr[i9] + d9;
                    dArr2[i9] = dArr2[i9] + d10;
                    i9++;
                    i10 += i7;
                }
            }
            if (i5 == length) {
                return;
            }
        }
    }
}
