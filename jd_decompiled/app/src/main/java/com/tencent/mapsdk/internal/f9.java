package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class f9 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double d = f2;
        Double.isNaN(d);
        double d2 = d - 1.0d;
        double d3 = d2 * d2;
        double d4 = d3 * d3;
        return (float) (1.0d - (d4 * d4));
    }
}
