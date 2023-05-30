package com.jingdong.jdreact.plugin.gradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;

/* loaded from: classes13.dex */
public class LinearGradientView extends View {
    private float[] mBorderRadii;
    private int[] mColors;
    private float[] mEndPos;
    private float[] mLocations;
    private final Paint mPaint;
    private Path mPathForBorderRadius;
    private LinearGradient mShader;
    private int[] mSize;
    private float[] mStartPos;
    private RectF mTempRectForBorderRadius;

    public LinearGradientView(Context context) {
        super(context);
        this.mPaint = new Paint(1);
        this.mStartPos = new float[]{0.0f, 0.0f};
        this.mEndPos = new float[]{0.0f, 1.0f};
        this.mSize = new int[]{0, 0};
        this.mBorderRadii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
    }

    private void drawGradient() {
        int[] iArr = this.mColors;
        if (iArr != null) {
            float[] fArr = this.mLocations;
            if (fArr == null || iArr.length == fArr.length) {
                float[] fArr2 = this.mStartPos;
                float f2 = fArr2[0];
                int[] iArr2 = this.mSize;
                float f3 = fArr2[1] * iArr2[1];
                float[] fArr3 = this.mEndPos;
                LinearGradient linearGradient = new LinearGradient(f2 * iArr2[0], f3, fArr3[0] * iArr2[0], fArr3[1] * iArr2[1], this.mColors, this.mLocations, Shader.TileMode.CLAMP);
                this.mShader = linearGradient;
                this.mPaint.setShader(linearGradient);
                invalidate();
            }
        }
    }

    private void updatePath() {
        if (this.mPathForBorderRadius == null) {
            this.mPathForBorderRadius = new Path();
            this.mTempRectForBorderRadius = new RectF();
        }
        this.mPathForBorderRadius.reset();
        RectF rectF = this.mTempRectForBorderRadius;
        int[] iArr = this.mSize;
        rectF.set(0.0f, 0.0f, iArr[0], iArr[1]);
        this.mPathForBorderRadius.addRoundRect(this.mTempRectForBorderRadius, this.mBorderRadii, Path.Direction.CW);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = this.mPathForBorderRadius;
        if (path == null) {
            canvas.drawPaint(this.mPaint);
        } else {
            canvas.drawPath(path, this.mPaint);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        this.mSize = new int[]{i2, i3};
        updatePath();
        drawGradient();
    }

    public void setBorderRadii(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            fArr[i2] = PixelUtil.toPixelFromDIP((float) readableArray.getDouble(i2));
        }
        this.mBorderRadii = fArr;
        updatePath();
        drawGradient();
    }

    public void setColors(ReadableArray readableArray) {
        int size = readableArray.size();
        int[] iArr = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr[i2] = readableArray.getInt(i2);
        }
        this.mColors = iArr;
        drawGradient();
    }

    public void setEndPosition(ReadableArray readableArray) {
        this.mEndPos = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        drawGradient();
    }

    public void setLocations(ReadableArray readableArray) {
        int size = readableArray.size();
        float[] fArr = new float[size];
        for (int i2 = 0; i2 < size; i2++) {
            fArr[i2] = (float) readableArray.getDouble(i2);
        }
        this.mLocations = fArr;
        drawGradient();
    }

    public void setStartPosition(ReadableArray readableArray) {
        this.mStartPos = new float[]{(float) readableArray.getDouble(0), (float) readableArray.getDouble(1)};
        drawGradient();
    }
}
