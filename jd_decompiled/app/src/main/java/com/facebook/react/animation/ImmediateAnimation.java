package com.facebook.react.animation;

/* loaded from: classes.dex */
public class ImmediateAnimation extends Animation {
    public ImmediateAnimation(int i2, AnimationPropertyUpdater animationPropertyUpdater) {
        super(i2, animationPropertyUpdater);
    }

    @Override // com.facebook.react.animation.Animation
    public void run() {
        onUpdate(1.0f);
        finish();
    }
}
