package com.jingdong.common.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes5.dex */
public class Rotate3d extends Animation {
    public static final int ROTATE_X = 0;
    public static final int ROTATE_XY = 3;
    public static final int ROTATE_XYZ = 6;
    public static final int ROTATE_XZ = 4;
    public static final int ROTATE_Y = 1;
    public static final int ROTATE_YZ = 5;
    public static final int ROTATE_Z = 2;
    private Camera mCamera;
    private float mCenterX;
    private float mCenterY;
    private float mFromDegree;
    private float mSaveFromDegree;
    private float mSaveToDegree;
    private float mToDegree;
    private int type = 1;

    public Rotate3d(float f2, float f3, float f4, float f5) {
        this.mFromDegree = f2;
        this.mToDegree = f3;
        this.mCenterX = f4;
        this.mCenterY = f5;
        this.mSaveFromDegree = f2;
        this.mSaveToDegree = f3;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        float f3 = this.mFromDegree;
        float f4 = f3 + ((this.mToDegree - f3) * f2);
        float f5 = this.mCenterX;
        float f6 = this.mCenterY;
        Matrix matrix = transformation.getMatrix();
        if (f4 <= -76.0f) {
            this.mCamera.save();
            rotate(-90.0f);
            this.mCamera.getMatrix(matrix);
            this.mCamera.restore();
        } else if (f4 >= 76.0f) {
            this.mCamera.save();
            rotate(90.0f);
            this.mCamera.getMatrix(matrix);
            this.mCamera.restore();
        } else {
            this.mCamera.save();
            this.mCamera.translate(0.0f, 0.0f, f5);
            rotate(f4);
            this.mCamera.translate(0.0f, 0.0f, -f5);
            this.mCamera.getMatrix(matrix);
            this.mCamera.restore();
        }
        matrix.preTranslate(-f5, -f6);
        matrix.postTranslate(f5, f6);
    }

    public int getType() {
        return this.type;
    }

    @Override // android.view.animation.Animation
    public void initialize(int i2, int i3, int i4, int i5) {
        super.initialize(i2, i3, i4, i5);
        this.mCamera = new Camera();
    }

    public void reverseTransformation(boolean z) {
        if (z) {
            this.mFromDegree = -this.mSaveFromDegree;
            this.mToDegree = -this.mSaveToDegree;
            return;
        }
        this.mFromDegree = this.mSaveFromDegree;
        this.mToDegree = this.mSaveToDegree;
    }

    public void rotate(float f2) {
        switch (this.type) {
            case 0:
                this.mCamera.rotateX(f2);
                return;
            case 1:
                this.mCamera.rotateY(f2);
                return;
            case 2:
                this.mCamera.rotateZ(f2);
                return;
            case 3:
                this.mCamera.rotateX(f2);
                this.mCamera.rotateY(f2);
                return;
            case 4:
                this.mCamera.rotateX(f2);
                this.mCamera.rotateZ(f2);
                return;
            case 5:
                this.mCamera.rotateY(f2);
                this.mCamera.rotateZ(f2);
                return;
            case 6:
                this.mCamera.rotateX(f2);
                this.mCamera.rotateY(f2);
                this.mCamera.rotateZ(f2);
                return;
            default:
                return;
        }
    }

    public void setType(int i2) {
        this.type = i2;
    }
}
