package com.tencent.mapsdk.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

/* loaded from: classes9.dex */
public class h9 implements Interpolator {
    public h9() {
    }

    public h9(Context context, AttributeSet attributeSet) {
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return f2 * f2;
    }
}
