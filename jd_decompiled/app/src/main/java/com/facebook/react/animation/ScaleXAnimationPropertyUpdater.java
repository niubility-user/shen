package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class ScaleXAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleXAnimationPropertyUpdater(float f2) {
        super(f2);
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected float getProperty(View view) {
        return view.getScaleX();
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected void setProperty(View view, float f2) {
        view.setScaleX(f2);
    }

    public ScaleXAnimationPropertyUpdater(float f2, float f3) {
        super(f2, f3);
    }
}
