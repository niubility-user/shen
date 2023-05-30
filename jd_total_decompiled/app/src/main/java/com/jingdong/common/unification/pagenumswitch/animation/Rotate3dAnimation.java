package com.jingdong.common.unification.pagenumswitch.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes6.dex */
public class Rotate3dAnimation extends Animation {
    public static final Byte ROTATE_X_AXIS = (byte) 0;
    public static final Byte ROTATE_Y_AXIS = (byte) 1;
    public static final Byte ROTATE_Z_AXIS = (byte) 2;
    private Camera mCamera;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final float mFromDegrees;
    private final boolean mReverse;
    private Byte mRotateAxis;
    private final float mToDegrees;

    public Rotate3dAnimation(float f2, float f3, float f4, float f5, float f6, Byte b, boolean z) {
        this.mFromDegrees = f2;
        this.mToDegrees = f3;
        this.mCenterX = f4;
        this.mCenterY = f5;
        this.mDepthZ = f6;
        this.mRotateAxis = b;
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
        if (ROTATE_X_AXIS.equals(this.mRotateAxis)) {
            camera.rotateX(f4);
        } else if (ROTATE_Y_AXIS.equals(this.mRotateAxis)) {
            camera.rotateY(f4);
        } else {
            camera.rotateZ(f4);
        }
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
