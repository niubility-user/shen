package com.googlecode.mp4parser.util;

/* loaded from: classes12.dex */
public class Math {
    public static long gcd(long j2, long j3) {
        while (true) {
            long j4 = j2;
            j2 = j3;
            if (j2 <= 0) {
                return j4;
            }
            j3 = j4 % j2;
        }
    }

    public static long lcm(long j2, long j3) {
        return j2 * (j3 / gcd(j2, j3));
    }

    public static int gcd(int i2, int i3) {
        while (true) {
            int i4 = i3;
            int i5 = i2;
            i2 = i4;
            if (i2 <= 0) {
                return i5;
            }
            i3 = i5 % i2;
        }
    }

    public static long lcm(long[] jArr) {
        long j2 = jArr[0];
        for (int i2 = 1; i2 < jArr.length; i2++) {
            j2 = lcm(j2, jArr[i2]);
        }
        return j2;
    }

    public static int lcm(int i2, int i3) {
        return i2 * (i3 / gcd(i2, i3));
    }
}
