package com.jingdong.common.messagepop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.jingdong.app.mall.mylib.R;

/* loaded from: classes5.dex */
public class JDShadowLayout extends FrameLayout {
    private final int DEFLAUT_SHADOW_COLOR;
    private float mCornerRadius;
    private float mDx;
    private float mDy;
    private boolean mForceInvalidateShadow;
    private boolean mInvalidateShadowOnSizeChanged;
    private int mShadowColor;
    private float mShadowRadius;

    public JDShadowLayout(Context context) {
        super(context);
        this.mInvalidateShadowOnSizeChanged = true;
        this.mForceInvalidateShadow = false;
        this.DEFLAUT_SHADOW_COLOR = 436207616;
        initView(context, null);
    }

    private Bitmap createShadowBitmap(int i2, int i3, float f2, float f3, float f4, float f5, int i4, int i5) {
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ALPHA_8);
        Canvas canvas = new Canvas(createBitmap);
        RectF rectF = new RectF(f3, f3, i2 - f3, i3 - f3);
        if (f5 > 0.0f) {
            rectF.top += f5;
            rectF.bottom -= f5;
        } else if (f5 < 0.0f) {
            rectF.top += Math.abs(f5);
            rectF.bottom -= Math.abs(f5);
        }
        if (f4 > 0.0f) {
            rectF.left += f4;
            rectF.right -= f4;
        } else if (f4 < 0.0f) {
            rectF.left += Math.abs(f4);
            rectF.right -= Math.abs(f4);
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(i5);
        paint.setStyle(Paint.Style.FILL);
        if (!isInEditMode()) {
            paint.setShadowLayer(f3, f4, f5, i4);
        }
        canvas.drawRoundRect(rectF, f2, f2, paint);
        return createBitmap;
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] iArr) {
        return context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray typedArray = getTypedArray(context, attributeSet, R.styleable.ShadowLayout);
        if (typedArray == null) {
            return;
        }
        try {
            this.mCornerRadius = typedArray.getDimension(R.styleable.ShadowLayout_sl_cornerRadius, 0.0f);
            this.mShadowRadius = typedArray.getDimension(R.styleable.ShadowLayout_sl_shadowRadius, 0.0f);
            this.mDx = typedArray.getDimension(R.styleable.ShadowLayout_sl_dx, 0.0f);
            this.mDy = typedArray.getDimension(R.styleable.ShadowLayout_sl_dy, 0.0f);
            this.mShadowColor = typedArray.getColor(R.styleable.ShadowLayout_sl_shadowColor, 436207616);
        } finally {
            typedArray.recycle();
        }
    }

    private void initView(Context context, AttributeSet attributeSet) {
        initAttributes(context, attributeSet);
        int abs = (int) (this.mShadowRadius + Math.abs(this.mDx));
        setPadding(abs, 0, abs, (int) (this.mShadowRadius + Math.abs(this.mDy)));
    }

    private void setBackgroundCompat(int i2, int i3) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), createShadowBitmap(i2, i3, this.mCornerRadius, this.mShadowRadius, this.mDx, this.mDy, this.mShadowColor, 0));
        if (Build.VERSION.SDK_INT <= 16) {
            setBackgroundDrawable(bitmapDrawable);
        } else {
            setBackground(bitmapDrawable);
        }
    }

    @Override // android.view.View
    protected int getSuggestedMinimumHeight() {
        return 0;
    }

    @Override // android.view.View
    protected int getSuggestedMinimumWidth() {
        return 0;
    }

    public void invalidateShadow() {
        this.mForceInvalidateShadow = true;
        requestLayout();
        invalidate();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        if (this.mForceInvalidateShadow) {
            this.mForceInvalidateShadow = false;
            setBackgroundCompat(i4 - i2, i5 - i3);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        if (getBackground() == null || this.mInvalidateShadowOnSizeChanged || this.mForceInvalidateShadow) {
            this.mForceInvalidateShadow = false;
            setBackgroundCompat(i2, i3);
        }
    }

    public void setInvalidateShadowOnSizeChanged(boolean z) {
        this.mInvalidateShadowOnSizeChanged = z;
    }

    public void setmShadowColor(int i2) {
        this.mShadowColor = i2;
    }

    public JDShadowLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInvalidateShadowOnSizeChanged = true;
        this.mForceInvalidateShadow = false;
        this.DEFLAUT_SHADOW_COLOR = 436207616;
        initView(context, attributeSet);
    }

    public JDShadowLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mInvalidateShadowOnSizeChanged = true;
        this.mForceInvalidateShadow = false;
        this.DEFLAUT_SHADOW_COLOR = 436207616;
        initView(context, attributeSet);
    }
}
