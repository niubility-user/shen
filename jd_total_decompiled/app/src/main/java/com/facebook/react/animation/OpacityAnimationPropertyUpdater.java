package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class OpacityAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public OpacityAnimationPropertyUpdater(float f2) {
        super(f2);
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected float getProperty(View view) {
        return view.getAlpha();
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected void setProperty(View view, float f2) {
        view.setAlpha(f2);
    }

    public OpacityAnimationPropertyUpdater(float f2, float f3) {
        super(f2, f3);
    }
}
