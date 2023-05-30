package com.tencent.mapsdk.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class e9 implements Interpolator {
    public e9() {
    }

    public e9(Context context, AttributeSet attributeSet) {
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        float f3 = f2 - 1.0f;
        return (float) Math.sqrt(1.0f - (f3 * f3));
    }
}
