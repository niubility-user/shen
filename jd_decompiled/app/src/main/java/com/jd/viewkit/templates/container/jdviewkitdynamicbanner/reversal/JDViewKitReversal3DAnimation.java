package com.jd.viewkit.templates.container.jdviewkitdynamicbanner.reversal;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* loaded from: classes18.dex */
public class JDViewKitReversal3DAnimation extends Animation {
    float alpha;
    int centerX;
    int centerY;
    int columnIndex;
    int columnNumber;
    float deg;
    float depthZ;
    int direction;
    Camera mCamera = new Camera();
    int type;

    public JDViewKitReversal3DAnimation(float f2, float f3, float f4, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.depthZ = f2;
        this.deg = f3;
        this.alpha = f4;
        this.centerX = i2;
        this.centerY = i3;
        this.columnIndex = i4;
        this.columnNumber = i5;
        this.type = i6;
        this.direction = i7;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f2, Transformation transformation) {
        super.applyTransformation(f2, transformation);
        Matrix matrix = transformation.getMatrix();
        this.mCamera.save();
        this.mCamera.setLocation(1.0f, 0.0f, -100.0f);
        this.mCamera.rotateY(this.deg);
        this.mCamera.getMatrix(matrix);
        this.mCamera.restore();
        matrix.preTranslate(-this.centerX, -this.centerY);
        matrix.postTranslate(this.centerX, this.centerY);
    }
}
