package com.jd.lib.babel.servicekit.imagekit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import com.jd.lib.babel.servicekit.R;
import com.jd.lib.babel.servicekit.imagekit.AspectRatioMeasure;

/* loaded from: classes13.dex */
public class ImageWraper extends FrameLayout {
    private static final ImageView.ScaleType[] sScaleTypeArray = {ImageView.ScaleType.MATRIX, ImageView.ScaleType.FIT_XY, ImageView.ScaleType.FIT_START, ImageView.ScaleType.FIT_CENTER, ImageView.ScaleType.FIT_END, ImageView.ScaleType.CENTER, ImageView.ScaleType.CENTER_CROP, ImageView.ScaleType.CENTER_INSIDE};
    private float mAspectRatio;
    private int mCornersRadii;
    private ImageView mImageView;
    private final AspectRatioMeasure.Spec mMeasureSpec;
    private boolean mRoundAsCircle;

    public ImageWraper(Context context) {
        this(context, null);
    }

    private void initView(Context context, AttributeSet attributeSet) {
        ImageView newImageView = ImageLoad.newImageView(context, attributeSet);
        this.mImageView = newImageView;
        addView(newImageView, new ViewGroup.LayoutParams(-1, -1));
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ImageWraper);
            this.mAspectRatio = obtainStyledAttributes.getFloat(R.styleable.ImageWraper_viewAspectRatio, 0.0f);
            this.mRoundAsCircle = obtainStyledAttributes.getBoolean(R.styleable.ImageWraper_roundAsCircle, false);
            this.mCornersRadii = obtainStyledAttributes.getDimensionPixelSize(R.styleable.ImageWraper_roundedCornerRadius, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.ImageWraper_src);
            if (drawable != null) {
                setImageDrawable(drawable);
            }
            int i2 = obtainStyledAttributes.getInt(R.styleable.ImageWraper_scaleType, -1);
            if (i2 >= 0) {
                setScaleType(sScaleTypeArray[i2]);
            }
            setMaxWidth(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ImageWraper_maxWidth, Integer.MAX_VALUE));
            setMaxHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.ImageWraper_maxHeight, Integer.MAX_VALUE));
            obtainStyledAttributes.recycle();
        }
    }

    public float getAspectRatio() {
        return this.mAspectRatio;
    }

    public int getCornersRadii() {
        return this.mCornersRadii;
    }

    public ImageView getImageView() {
        return this.mImageView;
    }

    public boolean isRoundAsCircle() {
        return this.mRoundAsCircle;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        AspectRatioMeasure.Spec spec = this.mMeasureSpec;
        spec.width = i2;
        spec.height = i3;
        AspectRatioMeasure.updateMeasureSpec(spec, this.mAspectRatio, getLayoutParams(), getPaddingLeft() + getPaddingRight(), getPaddingTop() + getPaddingBottom());
        AspectRatioMeasure.Spec spec2 = this.mMeasureSpec;
        super.onMeasure(spec2.width, spec2.height);
    }

    public void setAspectRatio(float f2) {
        if (this.mAspectRatio != f2) {
            this.mAspectRatio = f2;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setBackgroundColor(int i2) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setBackgroundColor(i2);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setBackgroundDrawable(drawable);
        }
    }

    public void setCornersRadii(int i2) {
        this.mCornersRadii = i2;
    }

    public void setImageDrawable(Drawable drawable) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setImageDrawable(drawable);
        }
    }

    public void setImageResource(@DrawableRes int i2) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setImageResource(i2);
        }
    }

    public void setMaxHeight(int i2) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setMaxHeight(i2);
        }
    }

    public void setMaxWidth(int i2) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setMaxWidth(i2);
        }
    }

    public void setRoundAsCircle(boolean z) {
        this.mRoundAsCircle = z;
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setScaleType(scaleType);
        }
    }

    public ImageWraper(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ImageWraper(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMeasureSpec = new AspectRatioMeasure.Spec();
        this.mAspectRatio = 0.0f;
        this.mRoundAsCircle = false;
        this.mCornersRadii = 0;
        initView(context, attributeSet);
    }
}
