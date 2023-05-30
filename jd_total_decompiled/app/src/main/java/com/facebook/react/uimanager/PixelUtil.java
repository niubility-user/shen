package com.facebook.react.uimanager;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/* loaded from: classes12.dex */
public class PixelUtil {
    public static float getDisplayMetricDensity() {
        return DisplayMetricsHolder.getScreenDisplayMetrics().density;
    }

    public static float toDIPFromPixel(float f2) {
        return f2 / DisplayMetricsHolder.getWindowDisplayMetrics().density;
    }

    public static float toPixelFromDIP(float f2) {
        return TypedValue.applyDimension(1, f2, DisplayMetricsHolder.getWindowDisplayMetrics());
    }

    public static float toPixelFromSP(float f2) {
        return toPixelFromSP(f2, Float.NaN);
    }

    public static float toSPFromPixel(float f2) {
        return f2 / DisplayMetricsHolder.getScreenDisplayMetrics().scaledDensity;
    }

    public static float toPixelFromSP(float f2, float f3) {
        DisplayMetrics windowDisplayMetrics = DisplayMetricsHolder.getWindowDisplayMetrics();
        float f4 = windowDisplayMetrics.scaledDensity;
        float f5 = windowDisplayMetrics.density;
        float f6 = f4 / f5;
        if (f3 >= 1.0f && f3 < f6) {
            f4 = f5 * f3;
        }
        return f2 * f4;
    }

    public static float toPixelFromDIP(double d) {
        return toPixelFromDIP((float) d);
    }

    public static float toPixelFromSP(double d) {
        return toPixelFromSP((float) d);
    }
}
