package com.jd.lib.babel.servicekit.imagekit;

import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes13.dex */
public class AspectRatioMeasure {

    /* loaded from: classes13.dex */
    public static class Spec {
        public int height;
        public int width;
    }

    private static boolean shouldAdjust(int i2) {
        return i2 == 0 || i2 == -2;
    }

    public static void updateMeasureSpec(Spec spec, float f2, ViewGroup.LayoutParams layoutParams, int i2, int i3) {
        if (f2 <= 0.0f || layoutParams == null) {
            return;
        }
        if (shouldAdjust(layoutParams.height)) {
            spec.height = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) (((View.MeasureSpec.getSize(spec.width) - i2) / f2) + i3), spec.height), 1073741824);
        } else if (shouldAdjust(layoutParams.width)) {
            spec.width = View.MeasureSpec.makeMeasureSpec(View.resolveSize((int) (((View.MeasureSpec.getSize(spec.height) - i3) * f2) + i2), spec.width), 1073741824);
        }
    }
}
