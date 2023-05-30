package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public class RotationAnimationPropertyUpdater extends AbstractSingleFloatProperyUpdater {
    public RotationAnimationPropertyUpdater(float f2) {
        super(f2);
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected float getProperty(View view) {
        return view.getRotation();
    }

    @Override // com.facebook.react.animation.AbstractSingleFloatProperyUpdater
    protected void setProperty(View view, float f2) {
        view.setRotation((float) Math.toDegrees(f2));
    }
}
