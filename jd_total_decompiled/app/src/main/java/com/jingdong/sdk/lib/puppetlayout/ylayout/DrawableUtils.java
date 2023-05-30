package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.View;

/* loaded from: classes8.dex */
public class DrawableUtils {
    public static Drawable createDashLine(int i2, int i3, float f2, float f3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(2);
        gradientDrawable.setStroke(i2, i3, f2, f3);
        return gradientDrawable;
    }

    private static Drawable createGradient(int i2, boolean z, int i3, int i4, boolean z2, float[] fArr, boolean z3) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (z) {
            gradientDrawable.setColor(i2);
        }
        if (i3 != -1 && z2) {
            gradientDrawable.setStroke(i3, i4);
        }
        if (z3) {
            gradientDrawable.setShape(1);
        } else if (fArr != null) {
            gradientDrawable.setCornerRadii(fArr);
        }
        return gradientDrawable;
    }

    public static Drawable createNormalBg(int i2, boolean z, int i3, int i4, boolean z2, float[] fArr, boolean z3) {
        return createGradient(i2, z, i3, i4, z2, fArr, z3);
    }

    public static Drawable createStateBg(int i2, boolean z, int i3, int i4, boolean z2, float[] fArr, int i5, boolean z3, int i6, int i7, boolean z4, float[] fArr2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, createGradient(i5, z3, i6, i7, z4, fArr2, false));
        stateListDrawable.addState(new int[0], createGradient(i2, z, i3, i4, z2, fArr, false));
        return stateListDrawable;
    }

    public static void setCompactBg(View view, Drawable drawable) {
        if (view == null || drawable == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}
