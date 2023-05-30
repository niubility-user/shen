package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class g9 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double d = f2;
        Double.isNaN(d);
        double d2 = d / 0.5d;
        return (float) ((d2 < 1.0d ? Math.pow(d2, 3.0d) : Math.pow(d2 - 2.0d, 3.0d) + 2.0d) * 0.5d);
    }
}
