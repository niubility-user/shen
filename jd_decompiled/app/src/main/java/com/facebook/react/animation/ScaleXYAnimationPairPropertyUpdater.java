package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class ScaleXYAnimationPairPropertyUpdater extends AbstractFloatPairPropertyUpdater {
    public ScaleXYAnimationPairPropertyUpdater(float f2, float f3) {
        super(f2, f3);
    }

    @Override // com.facebook.react.animation.AbstractFloatPairPropertyUpdater
    protected void getProperty(View view, float[] fArr) {
        fArr[0] = view.getScaleX();
        fArr[1] = view.getScaleY();
    }

    @Override // com.facebook.react.animation.AbstractFloatPairPropertyUpdater
    protected void setProperty(View view, float[] fArr) {
        view.setScaleX(fArr[0]);
        view.setScaleY(fArr[1]);
    }

    public ScaleXYAnimationPairPropertyUpdater(float f2, float f3, float f4, float f5) {
        super(f2, f3, f4, f5);
    }
}
