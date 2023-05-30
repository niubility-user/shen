package com.jingdong.common.utils;

/* loaded from: classes6.dex */
public class BaiduMapUtils {
    private static final double x_pi = 52.35987755982988d;

    public static double[] bdDecrypt(double d, double d2) {
        double d3 = d2 - 0.0065d;
        double d4 = d - 0.006d;
        double sqrt = Math.sqrt((d3 * d3) + (d4 * d4)) - (Math.sin(d4 * x_pi) * 2.0E-5d);
        double atan2 = Math.atan2(d4, d3) - (Math.cos(d3 * x_pi) * 3.0E-6d);
        return new double[]{Math.sin(atan2) * sqrt, sqrt * Math.cos(atan2)};
    }

    public static double[] bdEncrypt(double d, double d2) {
        double sqrt = Math.sqrt((d2 * d2) + (d * d)) + (Math.sin(d * x_pi) * 2.0E-5d);
        double atan2 = Math.atan2(d, d2) + (Math.cos(d2 * x_pi) * 3.0E-6d);
        return new double[]{(Math.sin(atan2) * sqrt) + 0.006d, (sqrt * Math.cos(atan2)) + 0.0065d};
    }
}
