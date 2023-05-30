package com.facebook.react.fabric.mounting;

import android.view.View;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaMeasureMode;

/* loaded from: classes12.dex */
public class LayoutMetricsConversions {
    public static final int REACT_CONSTRAINT_UNDEFINED = Integer.MIN_VALUE;

    public static float getMaxSize(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            return -2.14748365E9f;
        }
        return size;
    }

    public static float getMinSize(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        return 0.0f;
    }

    public static YogaMeasureMode getYogaMeasureMode(float f2, float f3) {
        if (f2 == f3) {
            return YogaMeasureMode.EXACTLY;
        }
        if (f3 == -2.14748365E9f) {
            return YogaMeasureMode.UNDEFINED;
        }
        return YogaMeasureMode.AT_MOST;
    }

    public static float getYogaSize(float f2, float f3) {
        if (f2 == f3) {
            return PixelUtil.toPixelFromDIP(f3);
        }
        if (f3 == -2.14748365E9f) {
            return 0.0f;
        }
        return PixelUtil.toPixelFromDIP(f3);
    }
}
