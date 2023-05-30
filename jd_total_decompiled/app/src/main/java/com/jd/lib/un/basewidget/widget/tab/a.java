package com.jd.lib.un.basewidget.widget.tab;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/* loaded from: classes16.dex */
class a {
    static final Interpolator a;

    static {
        new LinearInterpolator();
        a = new FastOutSlowInInterpolator();
        new FastOutLinearInInterpolator();
        new LinearOutSlowInInterpolator();
        new DecelerateInterpolator();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2, int i3, float f2) {
        return i2 + Math.round(f2 * (i3 - i2));
    }
}
