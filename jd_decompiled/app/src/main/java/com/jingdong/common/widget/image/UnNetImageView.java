package com.jingdong.common.widget.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class UnNetImageView extends FrameLayout {
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private View customView;
    private ImageView imageView;
    public ImageView.ScaleType mScaleType;
    private SimpleDraweeView simpleDraweeView;

    public UnNetImageView(Context context) {
        super(context);
        initView(null);
    }

    private void addImageView() {
        if (this.imageView == null) {
            this.imageView = new ImageView(getContext());
        }
        removeAllViews();
        addView(this.imageView);
    }

    private void addSimpleDraweeView() {
        if (this.simpleDraweeView == null) {
            this.simpleDraweeView = new SimpleDraweeView(getContext());
        }
        removeAllViews();
        addView(this.simpleDraweeView);
    }

    private void initView(AttributeSet attributeSet) {
        int i2;
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            addImageView();
        } else {
            addSimpleDraweeView();
        }
        if (attributeSet != null && (i2 = getContext().obtainStyledAttributes(attributeSet, R.styleable.UnNetImageView, 0, 0).getInt(R.styleable.UnNetImageView_nImageViewScaleType, -1)) >= 0) {
            setScaleType(sScaleTypeArray[i2]);
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

    public void setImageDrawable(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setImageDrawable(drawable);
        } else {
            this.simpleDraweeView.setImageDrawable(drawable);
        }
    }

    @Override // android.view.View
    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        super.setLayoutParams(layoutParams);
        updateLayoutParams();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setScaleType(scaleType);
        } else if (UnImageLoader.getUnImageLoader().imageType == 3) {
            this.simpleDraweeView.setScaleType(scaleType);
        }
    }

    public UnNetImageView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(attributeSet);
    }

    public void setImage(int i2) {
        if (UnImageLoader.getUnImageLoader().isImageView()) {
            this.imageView.setImageResource(i2);
        } else {
            this.simpleDraweeView.setImageResource(i2);
        }
    }

    public UnNetImageView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initView(attributeSet);
    }

    @RequiresApi(api = 21)
    public UnNetImageView(Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        initView(attributeSet);
    }
}
