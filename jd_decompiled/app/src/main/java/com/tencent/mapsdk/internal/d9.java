package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class d9 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double d = f2;
        Double.isNaN(d);
        Double.isNaN(d);
        double d2 = d * d;
        double d3 = d2 * d2;
        return (float) (d3 * d3);
    }
}
