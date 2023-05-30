package c.t.m.g;

import java.util.Locale;

/* loaded from: classes.dex */
public class f2 {
    public static int a(boolean z) {
        return z ? 1 : 0;
    }

    public static final String b(double d, int i2) {
        return String.format(Locale.ENGLISH, "%." + i2 + "f", Double.valueOf(d));
    }

    public static final boolean c(double d, double d2) {
        return d(d, d2, 1.0E-8d);
    }

    public static final boolean d(double d, double d2, double d3) {
        return (Double.isNaN(d) || Double.isNaN(d2) || Math.abs(d - d2) >= d3) ? false : true;
    }

    public static final boolean e(float f2, float f3) {
        return f(f2, f3, 1.0E-8f);
    }

    public static final boolean f(float f2, float f3, float f4) {
        return (Float.isNaN(f2) || Float.isNaN(f3) || Math.abs(f2 - f3) >= f4) ? false : true;
    }
}
