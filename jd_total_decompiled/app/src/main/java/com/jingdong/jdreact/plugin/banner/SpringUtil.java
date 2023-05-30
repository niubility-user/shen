package com.jingdong.jdreact.plugin.banner;

/* loaded from: classes13.dex */
public class SpringUtil {
    public static double clamp(double d, double d2, double d3) {
        return Math.min(Math.max(d, d2), d3);
    }

    public static double mapValueFromRangeToRange(double d, double d2, double d3, double d4, double d5) {
        return d4 + (((d - d2) / (d3 - d2)) * (d5 - d4));
    }

    public static int clamp(int i2, int i3, int i4) {
        return Math.min(Math.max(i2, i3), i4);
    }
}
