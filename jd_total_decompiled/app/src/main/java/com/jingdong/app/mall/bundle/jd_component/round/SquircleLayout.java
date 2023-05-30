package com.jingdong.app.mall.bundle.jd_component.round;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.jingdong.app.mall.bundle.jd_component.R;
import com.jingdong.app.mall.bundle.jd_component.round.ICornerView;

/* loaded from: classes2.dex */
public class SquircleLayout extends ViewGroup {
    private float bottomLeftRadius;
    private float bottomRightRadius;
    private boolean cornerEnable;
    private Paint imagePaint;
    private int mChildH;
    private int mChildW;
    private float mRatio;
    private int mRoundMode;
    private ICornerView mRoundView;
    private Paint mShadowPaint;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private boolean shadowEnable;
    private float shadowRadius;
    private float shadowWidth;
    private float topLeftRadius;
    private float topRightRadius;

    /* loaded from: classes2.dex */
    static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i3) {
            super(i2, i3);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public SquircleLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    private static int dpToPx(float f2) {
        return (int) ((f2 * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    private void drawShadow(Canvas canvas, Path path) {
        canvas.drawPath(path, this.mShadowPaint);
    }

    private void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SquircleLayout);
            this.shadowEnable = obtainStyledAttributes.getBoolean(R.styleable.SquircleLayout_shadowEnable, false);
            this.shadowWidth = obtainStyledAttributes.getDimension(R.styleable.SquircleLayout_shadowWidth, dpToPx(4.0f));
            this.shadowDx = obtainStyledAttributes.getDimension(R.styleable.SquircleLayout_shadowDx, 0.0f);
            this.shadowDy = obtainStyledAttributes.getDimension(R.styleable.SquircleLayout_shadowDy, dpToPx(1.0f));
            this.shadowColor = obtainStyledAttributes.getColor(R.styleable.SquircleLayout_android_shadowColor, Color.parseColor("#0C8D99A7"));
            this.shadowRadius = obtainStyledAttributes.getDimension(R.styleable.SquircleLayout_shadowRadius, dpToPx(3.0f));
            int i2 = R.styleable.SquircleLayout_radius;
            float dimension = obtainStyledAttributes.getDimension(i2, 0.0f);
            this.topLeftRadius = obtainStyledAttributes.getDimension(i2, dimension);
            this.topRightRadius = obtainStyledAttributes.getDimension(i2, dimension);
            this.bottomLeftRadius = obtainStyledAttributes.getDimension(i2, dimension);
            this.bottomRightRadius = obtainStyledAttributes.getDimension(i2, dimension);
            this.mRoundMode = obtainStyledAttributes.getInt(R.styleable.SquircleLayout_roundMode, 0);
            this.mRatio = obtainStyledAttributes.getFloat(R.styleable.SquircleLayout_ratio, 0.6f);
            obtainStyledAttributes.recycle();
        }
        this.cornerEnable = this.topLeftRadius > 0.0f || this.topRightRadius > 0.0f || this.bottomRightRadius > 0.0f || this.bottomLeftRadius > 0.0f;
        createRoundView();
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mShadowPaint.setAntiAlias(true);
        this.mShadowPaint.setColor(this.shadowColor);
        this.mShadowPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OVER));
        this.mShadowPaint.setShadowLayer(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor);
        Paint paint2 = new Paint();
        this.imagePaint = paint2;
        paint2.setXfermode(null);
    }

    private void radiusChanged() {
        this.mRoundView.setRadius(this.topLeftRadius, this.topRightRadius, this.bottomLeftRadius, this.bottomRightRadius);
        invalidate();
    }

    void createRoundView() {
        this.mRoundView = ICornerView.Builder.getInstance(this.mRoundMode).setWidth(this.mChildW).setHeight(this.mChildH).setRatio(this.mRatio).setRadius(this.topLeftRadius, this.topRightRadius, this.bottomLeftRadius, this.bottomRightRadius).build();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight()), this.imagePaint, 31);
        super.dispatchDraw(canvas);
        if (RoundUtils.isEnable()) {
            if (this.cornerEnable) {
                this.mRoundView.drawCorner(canvas);
            }
            if (this.shadowEnable) {
                drawShadow(canvas, this.mRoundView.getShadowPath(0.0f, 0.0f));
            }
            canvas.restore();
        }
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public float getBottomLeftRadius() {
        return this.bottomLeftRadius;
    }

    public float getBottomRightRadius() {
        return this.bottomRightRadius;
    }

    public float getRatio() {
        return this.mRatio;
    }

    public int getRoundMode() {
        return this.mRoundMode;
    }

    public int getShadowColor() {
        return this.shadowColor;
    }

    public float getShadowDx() {
        return this.shadowDx;
    }

    public float getShadowDy() {
        return this.shadowDy;
    }

    public float getShadowRadius() {
        return this.shadowRadius;
    }

    public float getShadowWidth() {
        return this.shadowWidth;
    }

    public float getTopLeftRadius() {
        return this.topLeftRadius;
    }

    public float getTopRightRadius() {
        return this.topRightRadius;
    }

    public boolean isShadowEnable() {
        return this.shadowEnable;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int measuredWidth2 = childAt.getMeasuredWidth();
            int measuredHeight2 = childAt.getMeasuredHeight();
            int i6 = (measuredWidth - measuredWidth2) / 2;
            int i7 = (measuredHeight - measuredHeight2) / 2;
            int i8 = (measuredWidth + measuredWidth2) / 2;
            int i9 = (measuredHeight + measuredHeight2) / 2;
            childAt.layout(i6, i7, i8, i9);
            int i10 = i8 - i6;
            this.mChildW = i10;
            int i11 = i9 - i7;
            this.mChildH = i11;
            this.mRoundView.onSizeChanged(i10, i11);
            this.mRoundView.setPadding(i6, i7);
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        super.onMeasure(i2, i3);
        if (getChildCount() == 1) {
            float f2 = (this.shadowEnable && RoundUtils.isEnable()) ? this.shadowWidth : 0.0f;
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int i7 = 0;
            View childAt = getChildAt(0);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            int max = (int) Math.max(f2, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
            int max2 = (int) Math.max(f2, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
            int max3 = (int) Math.max(f2, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
            int max4 = (int) Math.max(f2, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
            if (mode == 0) {
                i4 = View.MeasureSpec.getSize(i2);
            } else {
                i4 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                if (i4 == -1) {
                    i4 = (measuredWidth - max2) - max3;
                } else if (-2 == i4) {
                    i4 = (measuredWidth - max2) - max3;
                    i7 = Integer.MIN_VALUE;
                }
                i7 = 1073741824;
            }
            if (mode2 == 0) {
                i5 = View.MeasureSpec.getSize(i3);
                i6 = 0;
            } else {
                i5 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                if (i5 == -1) {
                    i5 = (measuredHeight - max) - max4;
                } else if (-2 == i5) {
                    i5 = (measuredHeight - max) - max4;
                    i6 = Integer.MIN_VALUE;
                }
                i6 = 1073741824;
            }
            measureChild(childAt, View.MeasureSpec.makeMeasureSpec(i4, i7), View.MeasureSpec.makeMeasureSpec(i5, i6));
            int mode3 = View.MeasureSpec.getMode(i2);
            int mode4 = View.MeasureSpec.getMode(i3);
            int measuredHeight2 = childAt.getMeasuredHeight();
            int measuredWidth2 = childAt.getMeasuredWidth();
            int i8 = mode4 == Integer.MIN_VALUE ? max4 + measuredHeight2 + max : measuredHeight;
            int i9 = mode3 == Integer.MIN_VALUE ? max3 + measuredWidth2 + max2 : measuredWidth;
            float f3 = f2 * 2.0f;
            float f4 = measuredWidth2 + f3;
            if (i9 < f4) {
                i9 = (int) f4;
            }
            float f5 = measuredHeight2 + f3;
            if (i8 < f5) {
                i8 = (int) f5;
            }
            if (i8 == measuredHeight && i9 == measuredWidth) {
                return;
            }
            setMeasuredDimension(i9, i8);
            return;
        }
        throw new IllegalStateException("SquircleLayout \u6709\u4e14\u53ea\u80fd\u6709\u4e00\u4e2a\u5b50View");
    }

    public SquircleLayout setBottomLeftRadius(float f2) {
        if (this.bottomLeftRadius != f2) {
            this.bottomLeftRadius = f2;
            radiusChanged();
        }
        return this;
    }

    public SquircleLayout setBottomRightRadius(float f2) {
        if (this.bottomRightRadius != f2) {
            this.bottomRightRadius = f2;
            radiusChanged();
        }
        return this;
    }

    public SquircleLayout setRadius(float f2) {
        this.topLeftRadius = f2;
        this.topRightRadius = f2;
        this.bottomLeftRadius = f2;
        this.bottomRightRadius = f2;
        radiusChanged();
        return this;
    }

    public SquircleLayout setRatio(float f2) {
        if (this.mRatio != f2) {
            this.mRatio = f2;
            this.mRoundView.setRatio(f2);
            invalidate();
        }
        return this;
    }

    public SquircleLayout setRoundMode(int i2) {
        if (this.mRoundMode != i2) {
            this.mRoundMode = i2;
            createRoundView();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowColor(int i2) {
        if (this.shadowColor != i2) {
            this.shadowColor = i2;
            initPaint();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowDx(float f2) {
        if (this.shadowDx != f2) {
            this.shadowDx = f2;
            initPaint();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowDy(float f2) {
        if (this.shadowDy != f2) {
            this.shadowDy = f2;
            initPaint();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowEnable(boolean z) {
        if (this.shadowEnable != z) {
            this.shadowEnable = z;
            requestLayout();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowRadius(float f2) {
        if (f2 != this.shadowRadius) {
            this.shadowRadius = f2;
            initPaint();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setShadowWidth(float f2) {
        if (this.shadowWidth != f2) {
            this.shadowWidth = f2;
            requestLayout();
            invalidate();
        }
        return this;
    }

    public SquircleLayout setTopLeftRadius(float f2) {
        if (this.topLeftRadius != f2) {
            this.topLeftRadius = f2;
            radiusChanged();
        }
        return this;
    }

    public SquircleLayout setTopRightRadius(float f2) {
        if (this.topRightRadius != f2) {
            this.topRightRadius = f2;
            radiusChanged();
        }
        return this;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public SquircleLayout(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public SquircleLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        init(context, attributeSet);
    }

    @RequiresApi(api = 21)
    public SquircleLayout(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
        init(context, attributeSet);
    }
}
