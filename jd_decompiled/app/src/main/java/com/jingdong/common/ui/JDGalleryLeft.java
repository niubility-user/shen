package com.jingdong.common.ui;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes6.dex */
public class JDGalleryLeft extends JDGallery {
    private OnGallerySizeChangeListener mOnGallerySizeChangeListener;

    /* loaded from: classes6.dex */
    public interface OnGallerySizeChangeListener {
        void onGallerySizeChange(int i2, int i3, int i4, int i5);
    }

    public JDGalleryLeft(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        OnGallerySizeChangeListener onGallerySizeChangeListener = this.mOnGallerySizeChangeListener;
        if (onGallerySizeChangeListener != null) {
            onGallerySizeChangeListener.onGallerySizeChange(i2, i3, i4, i5);
        }
        super.onSizeChanged(i2, i3, i4, i5);
    }

    public void setOnGallerySizeChangeListener(OnGallerySizeChangeListener onGallerySizeChangeListener) {
        this.mOnGallerySizeChangeListener = onGallerySizeChangeListener;
    }

    public JDGalleryLeft(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
    }

    public JDGalleryLeft(Context context) {
        super(context);
    }
}
