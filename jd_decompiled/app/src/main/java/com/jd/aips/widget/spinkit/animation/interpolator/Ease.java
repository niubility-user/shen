package com.jd.aips.widget.spinkit.animation.interpolator;

import android.view.animation.Interpolator;

/* loaded from: classes12.dex */
public class Ease {
    public static Interpolator inOut() {
        return PathInterpolatorCompat.create(0.42f, 0.0f, 0.58f, 1.0f);
    }
}
