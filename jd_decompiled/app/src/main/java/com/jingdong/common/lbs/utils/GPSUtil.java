package com.jingdong.common.lbs.utils;

import java.math.BigDecimal;

/* loaded from: classes.dex */
public class GPSUtil {
    private static final double a = 6378245.0d;
    private static final double ee = 0.006693421622965943d;
    private static final double pi = 3.141592653589793d;

    private static double[] calDev(double d, double d2) {
        if (isOverSeas(d, d2)) {
            return new double[]{0.0d, 0.0d};
        }
        double d3 = d2 - 105.0d;
        double d4 = d - 35.0d;
        double calLat = calLat(d3, d4);
        double calLon = calLon(d3, d4);
        double d5 = (d / 180.0d) * 3.141592653589793d;
        double sin = Math.sin(d5);
        double d6 = 1.0d - ((ee * sin) * sin);
        double sqrt = Math.sqrt(d6);
        return new double[]{(calLat * 180.0d) / ((6335552.717000426d / (d6 * sqrt)) * 3.141592653589793d), (calLon * 180.0d) / (((a / sqrt) * Math.cos(d5)) * 3.141592653589793d)};
    }

    private static double calLat(double d, double d2) {
        double d3 = d * 2.0d;
        double d4 = d2 * 3.141592653589793d;
        return (-100.0d) + d3 + (d2 * 3.0d) + (d2 * 0.2d * d2) + (0.1d * d * d2) + (Math.sqrt(Math.abs(d)) * 0.2d) + ((((Math.sin((6.0d * d) * 3.141592653589793d) * 20.0d) + (Math.sin(d3 * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d4) * 20.0d) + (Math.sin((d2 / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * 3.141592653589793d) * 160.0d) + (Math.sin(d4 / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    private static double calLon(double d, double d2) {
        double d3 = d * 0.1d;
        return d + 300.0d + (d2 * 2.0d) + (d3 * d) + (d3 * d2) + (Math.sqrt(Math.abs(d)) * 0.1d) + ((((Math.sin((6.0d * d) * 3.141592653589793d) * 20.0d) + (Math.sin((d * 2.0d) * 3.141592653589793d) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(d * 3.141592653589793d) * 20.0d) + (Math.sin((d / 3.0d) * 3.141592653589793d) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d / 12.0d) * 3.141592653589793d) * 150.0d) + (Math.sin((d / 30.0d) * 3.141592653589793d) * 300.0d)) * 2.0d) / 3.0d);
    }

    public static int calculateDistance(double d, double d2, double d3, double d4) {
        double d5 = (d * 3.141592653589793d) / 180.0d;
        double d6 = (d3 * 3.141592653589793d) / 180.0d;
        return (int) Math.round(Math.asin(Math.sqrt(Math.pow(Math.sin((d5 - d6) / 2.0d), 2.0d) + (Math.cos(d5) * Math.cos(d6) * Math.pow(Math.sin((((d2 - d4) * 3.141592653589793d) / 180.0d) / 2.0d), 2.0d)))) * 2.0d * 6378.137d * 1000.0d);
    }

    public static double formatDouble(double d) {
        if (d != Double.NaN) {
            try {
                return new BigDecimal(d).setScale(6, 4).doubleValue();
            } catch (Exception e2) {
                e2.printStackTrace();
                return d;
            }
        }
        return d;
    }

    public static boolean isOverSeas(double d, double d2) {
        return d2 < 72.004d || d2 > 137.8347d || d < 0.8293d || d > 55.8271d;
    }

    public static double[] toGCJ02Point(double d, double d2) {
        double[] calDev = calDev(d, d2);
        return new double[]{d + calDev[0], d2 + calDev[1]};
    }

    public static double[] toWGS84Point(double d, double d2) {
        double[] calDev = calDev(d, d2);
        double[] calDev2 = calDev(d - calDev[0], d2 - calDev[1]);
        return new double[]{d - calDev2[0], d2 - calDev2[1]};
    }
}
