package com.jingdong.app.mall.home.floor.view.widget;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.jingdong.app.mall.home.base.HomeDraweeView;

/* loaded from: classes4.dex */
public class FitBottomImage extends HomeDraweeView {

    /* renamed from: g  reason: collision with root package name */
    public static final a f10066g = new a();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class a extends ScalingUtils.AbstractScaleType {
        a() {
        }

        @Override // com.facebook.drawee.drawable.ScalingUtils.AbstractScaleType
        public void getTransformImpl(Matrix matrix, Rect rect, int i2, int i3, float f2, float f3, float f4, float f5) {
            float width = rect.width() / i2;
            matrix.setScale(width, width);
            matrix.postTranslate(0.0f, (rect.bottom - (i3 * width)) + 0.5f);
        }
    }

    @Override // android.widget.ImageView
    protected boolean setFrame(int i2, int i3, int i4, int i5) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return super.setFrame(i2, i3, i4, i5);
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth > 0 && intrinsicHeight > 0) {
            Matrix imageMatrix = getImageMatrix();
            f10066g.getTransformImpl(imageMatrix, new Rect(getPaddingLeft(), 0, getWidth() - getPaddingRight(), getHeight()), intrinsicWidth, intrinsicHeight, 0.0f, 0.0f, 1.0f, 1.0f);
            setImageMatrix(imageMatrix);
            return super.setFrame(i2, i3, i4, i5);
        }
        return super.setFrame(i2, i3, i4, i5);
    }

    @Override // com.facebook.drawee.view.DraweeView
    public void setHierarchy(GenericDraweeHierarchy genericDraweeHierarchy) {
        genericDraweeHierarchy.setActualImageScaleType(f10066g);
        super.setHierarchy((FitBottomImage) genericDraweeHierarchy);
    }
}
