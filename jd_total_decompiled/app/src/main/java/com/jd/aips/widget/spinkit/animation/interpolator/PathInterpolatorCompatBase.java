package com.jd.aips.widget.spinkit.animation.interpolator;

import android.graphics.Path;
import android.view.animation.Interpolator;

/* loaded from: classes12.dex */
class PathInterpolatorCompatBase {
    private PathInterpolatorCompatBase() {
    }

    public static Interpolator create(Path path) {
        return new PathInterpolatorDonut(path);
    }

    public static Interpolator create(float f2, float f3) {
        return new PathInterpolatorDonut(f2, f3);
    }

    public static Interpolator create(float f2, float f3, float f4, float f5) {
        return new PathInterpolatorDonut(f2, f3, f4, f5);
    }
}
