package c.t.m.g;

import c.t.m.g.y;

/* loaded from: classes.dex */
public class s1 {
    public static int[] a = {0, 8, 10, 33, 65535, 50594049, 268435455, Integer.MAX_VALUE};

    public static boolean a(int i2) {
        return i2 == y.a.CDMA.ordinal();
    }

    public static boolean b(int i2, int i3, int i4, int i5, long j2) {
        if (i3 < 0 || i4 < 0 || i5 < 0 || j2 <= 0 || i3 == 535 || i4 == 535 || i5 == 65535 || j2 == 65535) {
            return false;
        }
        if (a(i2)) {
            return true;
        }
        return (i5 == 25840 || c(j2, a)) ? false : true;
    }

    public static boolean c(long j2, int[] iArr) {
        for (int i2 : iArr) {
            if (j2 == i2) {
                return true;
            }
        }
        return false;
    }
}
