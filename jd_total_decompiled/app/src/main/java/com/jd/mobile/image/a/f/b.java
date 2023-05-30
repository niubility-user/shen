package com.jd.mobile.image.a.f;

import android.graphics.Matrix;
import android.graphics.Rect;
import com.facebook.drawee.drawable.ScalingUtils;

/* loaded from: classes17.dex */
public class b extends ScalingUtils.AbstractScaleType {
    @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
    public void getTransformImpl(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3, float f4, float f5) {
        matrix.setScale(f5, f5);
        matrix.postTranslate((int) (rect.left + 0.5f), (int) (rect.top + 0.5f));
    }
}
