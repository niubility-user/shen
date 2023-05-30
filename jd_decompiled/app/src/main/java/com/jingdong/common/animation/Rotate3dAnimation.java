package com.jingdong.common.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes5.dex */
public class Rotate3dAnimation extends Animation {
    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final float mFromDegrees;
    private final boolean mReverse;
    private final float mToDegrees;

    public Rotate3dAnimation(float f2, float f3, float f4, float f5, float f6, boolean z) {
        this.mFromDegrees = f2;
        this.mToDegrees = f3;
        this.mCenterX = f4;
        this.mCenterY = f5;
        this.mDepthZ = f6;
        this.mReverse = z;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        float f3 = this.mFromDegrees;
        float f4 = f3 + ((this.mToDegrees - f3) * f2);
        float f5 = this.mCenterX;
        float f6 = this.mCenterY;
        Camera camera = this.mCamera;
        Matrix matrix = transformation.getMatrix();
        camera.save();
        if (this.mReverse) {
            camera.translate(0.0f, 0.0f, this.mDepthZ * f2);
        } else {
            camera.translate(0.0f, 0.0f, this.mDepthZ * (1.0f - f2));
        }
        camera.rotateY(f4);
        camera.getMatrix(matrix);
        camera.restore();
        matrix.preTranslate(-f5, -f6);
        matrix.postTranslate(f5, f6);
    }

    @Override // android.view.animation.Animation
    public void initialize(int i2, int i3, int i4, int i5) {
        super.initialize(i2, i3, i4, i5);
        this.mCamera = new Camera();
    }
}
