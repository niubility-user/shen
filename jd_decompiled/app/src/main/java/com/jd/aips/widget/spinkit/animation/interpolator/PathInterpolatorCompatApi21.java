package com.jd.aips.widget.spinkit.animation.interpolator;

import android.annotation.TargetApi;
import android.graphics.Path;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* loaded from: classes12.dex */
class PathInterpolatorCompatApi21 {
    private PathInterpolatorCompatApi21() {
    }

    @TargetApi(21)
    public static Interpolator create(Path path) {
        return new PathInterpolator(path);
    }

    @TargetApi(21)
    public static Interpolator create(float f2, float f3) {
        return new PathInterpolator(f2, f3);
    }

    @TargetApi(21)
    public static Interpolator create(float f2, float f3, float f4, float f5) {
        return new PathInterpolator(f2, f3, f4, f5);
    }
}
