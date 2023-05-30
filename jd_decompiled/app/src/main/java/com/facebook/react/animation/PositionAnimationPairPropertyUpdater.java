package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class PositionAnimationPairPropertyUpdater extends AbstractFloatPairPropertyUpdater {
    public PositionAnimationPairPropertyUpdater(float f2, float f3) {
        super(f2, f3);
    }

    @Override // com.facebook.react.animation.AbstractFloatPairPropertyUpdater
    protected void getProperty(View view, float[] fArr) {
        fArr[0] = view.getX() + (view.getWidth() * 0.5f);
        fArr[1] = view.getY() + (view.getHeight() * 0.5f);
    }

    @Override // com.facebook.react.animation.AbstractFloatPairPropertyUpdater
    protected void setProperty(View view, float[] fArr) {
        view.setX(fArr[0] - (view.getWidth() * 0.5f));
        view.setY(fArr[1] - (view.getHeight() * 0.5f));
    }

    public PositionAnimationPairPropertyUpdater(float f2, float f3, float f4, float f5) {
        super(f2, f3, f4, f5);
    }
}
