package com.jingdong.app.mall.home.floor.view.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.jingdong.app.mall.home.state.dark.DarkWhiteBgImageView;

/* loaded from: classes4.dex */
public class FitTopImage extends DarkWhiteBgImageView {

    /* renamed from: j  reason: collision with root package name */
    public static final a f10067j = new a();

    /* renamed from: i  reason: collision with root package name */
    private boolean f10068i;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class a extends ScalingUtils.AbstractScaleType {
        a() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3, float f4, float f5) {
            float width = rect.width() / i2;
            matrix.setScale(width, width);
            matrix.postTranslate(0.0f, 0.0f);
        }
    }

    public FitTopImage(Context context) {
        super(context);
        setScaleType(ImageView.ScaleType.MATRIX);
    }

    public void changeScaleType(ImageView.ScaleType scaleType) {
        this.f10068i = true;
        setScaleType(scaleType);
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i2, int i3, int i4, int i5) {
        if (this.f10068i) {
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
        f10067j.getTransformImpl(imageMatrix, new Rect(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight()), intrinsicWidth, 0, 0.0f, 0.0f, 1.0f, 1.0f);
        setImageMatrix(imageMatrix);
        return super.setFrame(i2, i3, i4, i5);
    }

    @Override // com.facebook.drawee.view.DraweeView
    public void setHierarchy(GenericDraweeHierarchy genericDraweeHierarchy) {
        if (!this.f10068i) {
            genericDraweeHierarchy.setActualImageScaleType(f10067j);
        }
        super.setHierarchy((FitTopImage) genericDraweeHierarchy);
    }
}
