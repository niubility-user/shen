package com.jd.lib.babel.task.common;

import android.content.Context;

/* loaded from: classes14.dex */
public class DPIUtil {
    public static double defaultWidth = 1125.0d;

    public static int getRealPx(Context context, int i2) {
        if (i2 <= 0) {
            return 0;
        }
        int width = getWidth(context);
        if (width <= 0) {
            return i2;
        }
        double d = i2;
        double d2 = width;
        double d3 = defaultWidth;
        Double.isNaN(d2);
        Double.isNaN(d);
        return (int) Math.round(d * (d2 / d3));
    }

    public static int getWidth(Context context) {
        return com.jd.lib.babel.servicekit.util.DPIUtil.getWidth(context);
    }
}
