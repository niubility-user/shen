package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.DisplayMetricsHolder;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public abstract class ARTVirtualNode extends ReactShadowNodeImpl {
    protected static final float MIN_OPACITY_FOR_DRAW = 0.01f;
    private static final float[] sMatrixData = new float[9];
    private static final float[] sRawMatrix = new float[9];
    protected float mOpacity = 1.0f;
    @Nullable
    private Matrix mMatrix = new Matrix();
    protected final float mScale = DisplayMetricsHolder.getWindowDisplayMetrics().density;

    public abstract void draw(Canvas canvas, Paint paint, float f2);

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void restoreCanvas(Canvas canvas) {
        canvas.restore();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void saveAndSetupCanvas(Canvas canvas) {
        canvas.save();
        Matrix matrix = this.mMatrix;
        if (matrix != null) {
            canvas.concat(matrix);
        }
    }

    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(float f2) {
        this.mOpacity = f2;
        markUpdated();
    }

    @ReactProp(name = "transform")
    public void setTransform(@Nullable ReadableArray readableArray) {
        if (readableArray != null) {
            int floatArray = PropHelper.toFloatArray(readableArray, sMatrixData);
            if (floatArray == 6) {
                setupMatrix();
            } else if (floatArray != -1) {
                throw new JSApplicationIllegalArgumentException("Transform matrices must be of size 6");
            }
        } else {
            this.mMatrix = null;
        }
        markUpdated();
    }

    protected void setupMatrix() {
        float[] fArr = sRawMatrix;
        float[] fArr2 = sMatrixData;
        fArr[0] = fArr2[0];
        fArr[1] = fArr2[2];
        float f2 = fArr2[4];
        float f3 = this.mScale;
        fArr[2] = f2 * f3;
        fArr[3] = fArr2[1];
        fArr[4] = fArr2[3];
        fArr[5] = fArr2[5] * f3;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
        if (this.mMatrix == null) {
            this.mMatrix = new Matrix();
        }
        this.mMatrix.setValues(fArr);
    }
}
