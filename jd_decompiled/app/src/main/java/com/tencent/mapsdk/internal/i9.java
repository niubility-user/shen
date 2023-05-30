package com.tencent.mapsdk.internal;

import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class i9 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return f2 * (2.0f - f2);
    }
}
