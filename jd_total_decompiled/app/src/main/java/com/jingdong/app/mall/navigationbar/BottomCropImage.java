package com.jingdong.app.mall.navigationbar;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes4.dex */
public class BottomCropImage extends ImageView {
    public BottomCropImage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void a() {
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i2, int i3, int i4, int i5) {
        if (getDrawable() == null) {
            return super.setFrame(i2, i3, i4, i5);
        }
        Matrix imageMatrix = getImageMatrix();
        int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int intrinsicWidth = getDrawable().getIntrinsicWidth();
        int intrinsicHeight = getDrawable().getIntrinsicHeight();
        if (OKLog.D) {
            OKLog.d("BottomCropImage", "viewWidth=" + measuredWidth + "--viewHeight=" + measuredHeight + "--drawableWidth=" + intrinsicWidth + "--drawableHeight=" + intrinsicHeight);
        }
        float f2 = intrinsicWidth * measuredHeight > intrinsicHeight * measuredWidth ? measuredHeight / intrinsicHeight : measuredWidth / intrinsicWidth;
        if (OKLog.D) {
            OKLog.d("BottomCropImage", "scale=" + f2 + "--viewWidth/scale=" + (measuredWidth / f2) + "--viewHeight/scale=" + (measuredHeight / f2));
        }
        float f3 = measuredWidth;
        float f4 = measuredHeight;
        imageMatrix.setRectToRect(new RectF(0.0f, 0.0f, f3 / f2, f4 / f2), new RectF(0.0f, 0.0f, f3, f4), Matrix.ScaleToFit.FILL);
        setImageMatrix(imageMatrix);
        return super.setFrame(i2, i3, i4, i5);
    }

    public BottomCropImage(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        a();
    }
}
