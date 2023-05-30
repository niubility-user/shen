package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class ScaleYAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public ScaleYAnimationPropertyUpdater(float f2) {
        super(f2);
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected float getProperty(View view) {
        return view.getScaleY();
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected void setProperty(View view, float f2) {
        view.setScaleY(f2);
    }

    public ScaleYAnimationPropertyUpdater(float f2, float f3) {
        super(f2, f3);
    }
}
