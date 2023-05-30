package com.jingdong.common.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jingdong.common.ui.PhotoView;

/* loaded from: classes12.dex */
public class UnNetTouchImageView extends FrameLayout {
    private View customView;
    private PhotoView imageView;
    private SimpleTouchImageView simpleDraweeView;

    public UnNetTouchImageView(Context context) {
        super(context);
        initView();
    }

    private void addImageView() {
        if (this.imageView == null) {
            this.imageView = new PhotoView(getContext());
        }
        removeAllViews();
        addView(this.imageView);
    }

    private void addSimpleDraweeView() {
        if (this.simpleDraweeView == null) {
            this.simpleDraweeView = new SimpleTouchImageView(getContext());
        }
        removeAllViews();
        addView(this.simpleDraweeView);
    }

    private void initView() {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            addImageView();
        } else {
            addSimpleDraweeView();
        }
    }

    private void updateLayoutParams() {
        View view;
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            view = this.imageView;
        } else {
            view = this.simpleDraweeView;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
        if (layoutParams2 == null) {
            view.setLayoutParams(new FrameLayout.LayoutParams(layoutParams.width, layoutParams.height));
            return;
        }
        int i2 = layoutParams.width;
        if (i2 == layoutParams2.width && layoutParams.height == layoutParams2.height) {
            return;
        }
        layoutParams2.width = i2;
        layoutParams2.height = layoutParams.height;
        view.setLayoutParams(layoutParams2);
    }

    public View getCustomView() {
        return this.customView;
    }

    public View getOriginView() {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            return this.imageView;
        }
        return this.simpleDraweeView;
    }

    public boolean isZoomed() {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            return this.imageView.isZoomed();
        }
        return this.simpleDraweeView.isZoomed();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateLayoutParams();
    }

    public void setCustomView(View view) {
        this.customView = view;
        removeAllViews();
        addView(view);
    }

    public void setImage(String str) {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            UnImageLoader.getUnImageLoader().displayImage(getContext(), this.imageView, str);
        } else {
            UnImageLoader.getUnImageLoader().displayImage(getContext(), this.simpleDraweeView, str);
        }
    }

    public void setImageBitmap(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setImageBitmap(bitmap);
        } else {
            this.simpleDraweeView.setImageBitmap(bitmap);
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        updateLayoutParams();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setScaleType(scaleType);
        } else if (UnImageLoader.getUnImageLoader().imageType == 3) {
            this.simpleDraweeView.setScaleType(scaleType);
        }
    }

    public UnNetTouchImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public void setImage(int i2) {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setImageResource(i2);
        } else {
            this.simpleDraweeView.setImageResource(i2);
        }
    }

    public UnNetTouchImageView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView();
    }

    @RequiresApi(api = 21)
    public UnNetTouchImageView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initView();
    }
}
