package com.facebook.react.animation;

import android.view.View;

/* loaded from: classes.dex */
public abstract class AbstractFloatPairPropertyUpdater implements AnimationPropertyUpdater {
    private boolean mFromSource;
    private final float[] mFromValues;
    private final float[] mToValues;
    private final float[] mUpdateValues;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFloatPairPropertyUpdater(float f2, float f3) {
        this.mFromValues = new float[2];
        this.mToValues = r1;
        this.mUpdateValues = new float[2];
        float[] fArr = {f2, f3};
        this.mFromSource = true;
    }

    protected abstract void getProperty(View view, float[] fArr);

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public void onFinish(View view) {
        setProperty(view, this.mToValues);
    }

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public void onUpdate(View view, float f2) {
        float[] fArr = this.mUpdateValues;
        float[] fArr2 = this.mFromValues;
        float f3 = fArr2[0];
        float[] fArr3 = this.mToValues;
        fArr[0] = f3 + ((fArr3[0] - fArr2[0]) * f2);
        fArr[1] = fArr2[1] + ((fArr3[1] - fArr2[1]) * f2);
        setProperty(view, fArr);
    }

    @Override // com.facebook.react.animation.AnimationPropertyUpdater
    public void prepare(View view) {
        if (this.mFromSource) {
            getProperty(view, this.mFromValues);
        }
    }

    protected abstract void setProperty(View view, float[] fArr);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFloatPairPropertyUpdater(float f2, float f3, float f4, float f5) {
        this(f4, f5);
        float[] fArr = this.mFromValues;
        fArr[0] = f2;
        fArr[1] = f3;
        this.mFromSource = false;
    }
}
