package com.jingdong.common.Interpolator;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

/* loaded from: classes5.dex */
public class AccelerateDecelerateInterpolator implements Interpolator {
    public AccelerateDecelerateInterpolator() {
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double d = f2 + 1.0f;
        Double.isNaN(d);
        return ((float) (Math.cos(d * 3.141592653589793d) / 2.0d)) + 0.5f;
    }

    public AccelerateDecelerateInterpolator(Context context, AttributeSet attributeSet) {
    }
}
