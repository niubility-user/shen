package com.facebook.react.views.image;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.drawee.drawable.ScalingUtils;

/* loaded from: classes12.dex */
public class ScaleTypeStartInside extends ScalingUtils.AbstractScaleType {
    public static final ScalingUtils.ScaleType INSTANCE = new ScaleTypeStartInside();

    @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3, float f4, float f5) {
        float min = Math.min(Math.min(f4, f5), 1.0f);
        matrix.setScale(min, min);
        matrix.postTranslate((int) (rect.left + 0.5f), (int) (rect.top + 0.5f));
    }

    public String toString() {
        return "start_inside";
    }
}
