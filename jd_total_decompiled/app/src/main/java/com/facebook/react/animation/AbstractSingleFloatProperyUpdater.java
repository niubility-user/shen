package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public abstract class AbstractSingleFloatProperyUpdater implements AnimationPropertyUpdater {
    private boolean mFromSource;
    private float mFromValue;
    private float mToValue;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSingleFloatProperyUpdater(float f2) {
        this.mToValue = f2;
        this.mFromSource = true;
    }

    protected abstract float getProperty(View view);

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public void onFinish(View view) {
        setProperty(view, this.mToValue);
    }

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public final void onUpdate(View view, float f2) {
        float f3 = this.mFromValue;
        setProperty(view, f3 + ((this.mToValue - f3) * f2));
    }

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public final void prepare(View view) {
        if (this.mFromSource) {
            this.mFromValue = getProperty(view);
        }
    }

    protected abstract void setProperty(View view, float f2);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSingleFloatProperyUpdater(float f2, float f3) {
        this(f3);
        this.mFromValue = f2;
        this.mFromSource = false;
    }
}
