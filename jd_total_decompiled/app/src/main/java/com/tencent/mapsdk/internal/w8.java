package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class w8 {
    public static double a(long j2, float f2, float f3, long j3) {
        float f4 = (float) (j2 / j3);
        return (f3 * f4 * f4 * ((float) ((r0 * 21) - 20))) + f2;
    }

    public static double b(long j2, float f2, float f3, long j3) {
        double d = f3;
        long j4 = j2 / j3;
        Double.isNaN(d);
        double d2 = f2;
        Double.isNaN(d2);
        return (d * (1.0d - Math.sqrt(1 - (j4 * j4)))) + d2;
    }

    public static double c(long j2, float f2, float f3, long j3) {
        double d = f3;
        double pow = Math.pow(j2 / j3, 3.0d);
        Double.isNaN(d);
        double d2 = d * pow;
        double d3 = f2;
        Double.isNaN(d3);
        return d2 + d3;
    }

    public static double d(long j2, float f2, float f3, long j3) {
        double d = f3;
        double pow = Math.pow(2.0d, ((j2 / j3) - 1) * 10);
        Double.isNaN(d);
        double d2 = d * pow;
        double d3 = f2;
        Double.isNaN(d3);
        return d2 + d3;
    }

    public static double e(long j2, float f2, float f3, long j3) {
        float f4;
        long j4 = j2 / (j3 / 2);
        float f5 = f3 / 2.0f;
        if (j4 < 1) {
            double d = 2;
            Double.isNaN(d);
            f4 = (float) (j4 * j4 * (((r11 + 1) * j4) - ((int) (d * 1.525d))));
        } else {
            long j5 = j4 - 2;
            double d2 = 2;
            Double.isNaN(d2);
            f4 = (float) ((j5 * j5 * (((r11 + 1) * j5) + ((int) (d2 * 1.525d)))) + 2);
        }
        return (f5 * f4) + f2;
    }

    public static double f(long j2, float f2, float f3, long j3) {
        long j4 = j2 / (j3 / 2);
        float f4 = f3 / 2.0f;
        if (j4 < 1) {
            double d = f4;
            Double.isNaN(d);
            double d2 = f2;
            Double.isNaN(d2);
            return (d * (1.0d - Math.sqrt(1 - (j4 * j4)))) + d2;
        }
        double d3 = f4;
        long j5 = j4 - 2;
        Double.isNaN(d3);
        double sqrt = d3 * (Math.sqrt(1 - (j5 * j5)) + 1.0d);
        double d4 = f2;
        Double.isNaN(d4);
        return sqrt + d4;
    }

    public static double g(long j2, float f2, float f3, long j3) {
        long j4 = j2 / (j3 / 2);
        float f4 = f3 / 2.0f;
        if (j4 >= 1) {
            double d = f4;
            Double.isNaN(d);
            double pow = d * (Math.pow(j4 - 2, 3.0d) + 2.0d);
            double d2 = f2;
            Double.isNaN(d2);
            return pow + d2;
        }
        double d3 = f4;
        double pow2 = Math.pow(j4, 3.0d);
        Double.isNaN(d3);
        double d4 = d3 * pow2;
        double d5 = f2;
        Double.isNaN(d5);
        return d4 + d5;
    }

    public static double h(long j2, float f2, float f3, long j3) {
        float f4;
        float f5;
        long j4 = ((float) j2) / (((float) j3) / 2.0f);
        if (j4 < 1) {
            f5 = (float) j4;
            f4 = (f3 / 2.0f) * f5;
        } else {
            f4 = (-f3) / 2.0f;
            long j5 = j4 - 1;
            f5 = (float) ((j5 * (j5 - 2)) - 1);
        }
        return (f4 * f5) + f2;
    }

    public static double i(long j2, float f2, float f3, long j3) {
        float f4;
        float f5;
        long j4 = j2 / (j3 / 2);
        if (j4 < 1) {
            f5 = (float) j4;
            f4 = (f3 / 2.0f) * f5 * f5 * f5;
        } else {
            f4 = (-f3) / 2.0f;
            long j5 = j4 - 2;
            f5 = (float) ((((j5 * j5) * j5) * j5) - 2);
        }
        return (f4 * f5) + f2;
    }

    public static double j(long j2, float f2, float f3, long j3) {
        float f4;
        long j4 = j2 / (j3 / 2);
        float f5 = f3 / 2.0f;
        if (j4 < 1) {
            f4 = (float) j4;
            f5 = f5 * f4 * f4 * f4 * f4;
        } else {
            long j5 = j4 - 2;
            f4 = (float) ((j5 * j5 * j5 * j5 * j5) + 2);
        }
        return (f5 * f4) + f2;
    }

    public static double k(long j2, float f2, float f3, long j3) {
        double d = f3 / 2.0f;
        double d2 = j2;
        Double.isNaN(d2);
        double d3 = j3;
        Double.isNaN(d3);
        Double.isNaN(d);
        double d4 = f2;
        Double.isNaN(d4);
        return (d * (1.0d - Math.cos((d2 * 3.141592653589793d) / d3))) + d4;
    }

    public static double l(long j2, float f2, float f3, long j3) {
        float f4 = ((float) j2) / ((float) j3);
        return (f3 * f4 * f4) + f2;
    }

    public static double m(long j2, float f2, float f3, long j3) {
        float f4 = ((float) j2) / ((float) j3);
        return (f3 * f4 * f4 * f4 * f4) + f2;
    }

    public static double n(long j2, float f2, float f3, long j3) {
        float f4 = (float) (j2 / j3);
        return (f3 * f4 * f4 * f4 * f4 * f4) + f2;
    }

    public static double o(long j2, float f2, float f3, long j3) {
        double d = f3;
        double d2 = j2 / j3;
        Double.isNaN(d2);
        Double.isNaN(d);
        double d3 = f2;
        Double.isNaN(d3);
        return (d * (1.0d - Math.cos(d2 * 1.5707963267948966d))) + d3;
    }

    public static double p(long j2, float f2, float f3, long j3) {
        long j4 = (j2 / j3) - 1;
        return (f3 * ((float) ((j4 * j4 * ((j4 * 21) + 20)) + 1))) + f2;
    }

    public static double q(long j2, float f2, float f3, long j3) {
        double d = f3;
        long j4 = j2 / (j3 - 1);
        double sqrt = Math.sqrt(1 - (j4 * j4));
        Double.isNaN(d);
        double d2 = d * sqrt;
        double d3 = f2;
        Double.isNaN(d3);
        return d2 + d3;
    }

    public static double r(long j2, float f2, float f3, long j3) {
        double d = f3;
        Double.isNaN(d);
        double pow = d * (Math.pow((((float) j2) / ((float) j3)) - 1.0f, 3.0d) + 1.0d);
        double d2 = f2;
        Double.isNaN(d2);
        return pow + d2;
    }

    public static double s(long j2, float f2, float f3, long j3) {
        double d = f3;
        Double.isNaN(d);
        double d2 = d * ((-Math.pow(2.0d, (j2 * (-10)) / j3)) + 1.0d);
        double d3 = f2;
        Double.isNaN(d3);
        return d2 + d3;
    }

    public static double t(long j2, float f2, float f3, long j3) {
        float f4 = ((float) j2) / ((float) j3);
        return ((-f3) * f4 * (f4 - 2.0f)) + f2;
    }

    public static double u(long j2, float f2, float f3, long j3) {
        float f4 = (float) (j2 / (j3 - 1));
        return ((-f3) * ((((f4 * f4) * f4) * f4) - 1.0f)) + f2;
    }

    public static double v(long j2, float f2, float f3, long j3) {
        long j4 = (j2 / j3) - 1;
        return (f3 * ((float) ((j4 * j4 * j4 * j4 * j4) + 1))) + f2;
    }

    public static double w(long j2, float f2, float f3, long j3) {
        double d = f3;
        double d2 = j2 / j3;
        Double.isNaN(d2);
        double sin = Math.sin(d2 * 1.5707963267948966d);
        Double.isNaN(d);
        double d3 = d * sin;
        double d4 = f2;
        Double.isNaN(d4);
        return d3 + d4;
    }

    public static double x(long j2, float f2, float f3, long j3) {
        return ((f3 * ((float) j2)) / ((float) j3)) + f2;
    }
}
