package com.jd.aips.widget.spinkit.animation.interpolator;

import android.graphics.Path;
import android.os.Build;
import android.view.animation.Interpolator;

/* loaded from: classes12.dex */
public class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(path);
        }
        return PathInterpolatorCompatBase.create(path);
    }

    public static Interpolator create(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(f2, f3);
        }
        return PathInterpolatorCompatBase.create(f2, f3);
    }

    public static Interpolator create(float f2, float f3, float f4, float f5) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(f2, f3, f4, f5);
        }
        return PathInterpolatorCompatBase.create(f2, f3, f4, f5);
    }
}
