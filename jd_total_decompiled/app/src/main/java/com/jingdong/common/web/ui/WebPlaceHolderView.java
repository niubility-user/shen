package com.jingdong.common.web.ui;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

/* loaded from: classes12.dex */
public class WebPlaceHolderView extends SimpleDraweeView {
    public static final FitTop FIT_TOP = new FitTop();
    private boolean isChangedScaleType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class FitTop extends ScalingUtils.AbstractScaleType {
        FitTop() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3, float f4, float f5) {
            float width = rect.width() / i2;
            matrix.setScale(width, width);
            matrix.postTranslate(0.0f, 0.0f);
        }
    }

    public WebPlaceHolderView(Context context) {
        super(context);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public void changeScaleType(ImageView.ScaleType scaleType) {
        this.isChangedScaleType = true;
        setScaleType(scaleType);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i2, int i3, int i4, int i5) {
        if (this.isChangedScaleType) {
            return super.setFrame(i2, i3, i4, i5);
        }
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return super.setFrame(i2, i3, i4, i5);
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            return super.setFrame(i2, i3, i4, i5);
        }
        Matrix imageMatrix = getImageMatrix();
        FIT_TOP.getTransformImpl(imageMatrix, new Rect(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight()), intrinsicWidth, 0, 0.0f, 0.0f, 1.0f, 1.0f);
        setImageMatrix(imageMatrix);
        return super.setFrame(i2, i3, i4, i5);
    }

    @Override // com.facebook.drawee.view.DraweeView
    public void setHierarchy(GenericDraweeHierarchy genericDraweeHierarchy) {
        if (!this.isChangedScaleType) {
            genericDraweeHierarchy.setActualImageScaleType(FIT_TOP);
        }
        super.setHierarchy((WebPlaceHolderView) genericDraweeHierarchy);
    }
}
