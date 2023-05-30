package com.jingdong.discovertask.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.common.R;

/* loaded from: classes12.dex */
public class ShadowContainer extends ViewGroup {
    private final float cornerRadius;
    private final float deltaBottomLength;
    private final float deltaLeftLength;
    private final float deltaRightLength;
    private final float deltaTopLength;
    private boolean drawShadow;
    private final Paint mShadowPaint;

    /* loaded from: classes12.dex */
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

    public ShadowContainer(Context context) {
        this(context, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        if (this.drawShadow) {
            if (getLayerType() != 1) {
                setLayerType(1, null);
            }
            View childAt = getChildAt(0);
            int left = childAt.getLeft();
            int top = childAt.getTop();
            int right = childAt.getRight();
            int bottom = childAt.getBottom();
            if (Build.VERSION.SDK_INT >= 21) {
                float f2 = this.cornerRadius;
                canvas.drawRoundRect(left, top, right, bottom, f2, f2, this.mShadowPaint);
            } else {
                Path path = new Path();
                float f3 = left;
                float f4 = top;
                path.moveTo(this.cornerRadius + f3, f4);
                float f5 = this.cornerRadius;
                path.arcTo(new RectF(f3, f4, (f5 * 2.0f) + f3, (f5 * 2.0f) + f4), -90.0f, -90.0f, false);
                float f6 = bottom;
                path.lineTo(f3, f6 - this.cornerRadius);
                float f7 = this.cornerRadius;
                path.arcTo(new RectF(f3, f6 - (f7 * 2.0f), (f7 * 2.0f) + f3, f6), 180.0f, -90.0f, false);
                float f8 = right;
                path.lineTo(f8 - this.cornerRadius, f6);
                float f9 = this.cornerRadius;
                path.arcTo(new RectF(f8 - (f9 * 2.0f), f6 - (f9 * 2.0f), f8, f6), 90.0f, -90.0f, false);
                path.lineTo(f8, this.cornerRadius + f4);
                float f10 = this.cornerRadius;
                path.arcTo(new RectF(f8 - (f10 * 2.0f), f4, f8, (f10 * 2.0f) + f4), 0.0f, -90.0f, false);
                path.close();
                canvas.drawPath(path, this.mShadowPaint);
                super.dispatchDraw(canvas);
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View childAt = getChildAt(0);
        getMeasuredWidth();
        getMeasuredHeight();
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        float f2 = this.deltaLeftLength;
        float f3 = this.deltaTopLength;
        childAt.layout((int) f2, (int) f3, (int) (measuredWidth + f2), (int) (measuredHeight + f3));
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        int i4;
        int i5;
        super.onMeasure(i2, i3);
        if (getChildCount() == 1) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            int mode = View.MeasureSpec.getMode(i2);
            int mode2 = View.MeasureSpec.getMode(i3);
            int i6 = 0;
            View childAt = getChildAt(0);
            if (childAt.getLayoutParams() instanceof LayoutParams) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int max = (int) Math.max(this.deltaBottomLength, ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin);
                int max2 = (int) Math.max(this.deltaLeftLength, ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin);
                int max3 = (int) Math.max(this.deltaRightLength, ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin);
                int max4 = (int) Math.max(this.deltaTopLength, ((ViewGroup.MarginLayoutParams) layoutParams).topMargin);
                int i7 = 1073741824;
                if (mode == 0) {
                    i4 = View.MeasureSpec.getSize(i2);
                } else {
                    i4 = ((ViewGroup.MarginLayoutParams) layoutParams).width;
                    if (i4 == -1) {
                        i4 = (measuredWidth - max2) - max3;
                    } else if (-2 == i4) {
                        i4 = (measuredWidth - max2) - max3;
                        i6 = Integer.MIN_VALUE;
                    }
                    i6 = 1073741824;
                }
                if (mode2 == 0) {
                    i5 = View.MeasureSpec.getSize(i3);
                    i7 = 0;
                } else {
                    i5 = ((ViewGroup.MarginLayoutParams) layoutParams).height;
                    if (i5 == -1) {
                        i5 = (measuredHeight - max) - max4;
                    } else if (-2 == i5) {
                        i5 = (measuredHeight - max) - max4;
                        i7 = Integer.MIN_VALUE;
                    }
                }
                measureChild(childAt, View.MeasureSpec.makeMeasureSpec(i4, i6), View.MeasureSpec.makeMeasureSpec(i5, i7));
                int mode3 = View.MeasureSpec.getMode(i2);
                int mode4 = View.MeasureSpec.getMode(i3);
                int measuredHeight2 = childAt.getMeasuredHeight();
                int measuredWidth2 = childAt.getMeasuredWidth();
                int i8 = mode4 == Integer.MIN_VALUE ? max4 + measuredHeight2 + max : measuredHeight;
                int i9 = mode3 == Integer.MIN_VALUE ? max3 + measuredWidth2 + max2 : measuredWidth;
                float f2 = measuredWidth2;
                float f3 = this.deltaLeftLength;
                float f4 = this.deltaRightLength;
                if (i9 < f2 + f3 + f4) {
                    i9 = (int) (f2 + f3 + f4);
                }
                float f5 = measuredHeight2;
                float f6 = this.deltaTopLength;
                float f7 = this.deltaBottomLength;
                if (i8 < f5 + f6 + f7) {
                    i8 = (int) (f5 + f6 + f7);
                }
                if (i8 == measuredHeight && i9 == measuredWidth) {
                    return;
                }
                setMeasuredDimension(i9, i8);
                return;
            }
            return;
        }
        throw new IllegalStateException("\u5b50View\u53ea\u80fd\u6709\u4e00\u4e2a");
    }

    public void setDrawShadow(boolean z) {
        if (this.drawShadow == z) {
            return;
        }
        this.drawShadow = z;
        postInvalidate();
    }

    public ShadowContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public ShadowContainer(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShadowContainer);
        int color = obtainStyledAttributes.getColor(R.styleable.ShadowContainer_containerShadowColor, SupportMenu.CATEGORY_MASK);
        float dimension = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerShadowRadius, 0.0f);
        this.deltaLeftLength = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerLeftDeltaLength, 0.0f);
        this.deltaRightLength = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerRightDeltaLength, 0.0f);
        this.deltaTopLength = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerTopDeltaLength, 0.0f);
        this.deltaBottomLength = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerBottomDeltaLength, 0.0f);
        this.cornerRadius = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_containerCornerRadius, 0.0f);
        float dimension2 = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_deltaX, 0.0f);
        float dimension3 = obtainStyledAttributes.getDimension(R.styleable.ShadowContainer_deltaY, 0.0f);
        this.drawShadow = obtainStyledAttributes.getBoolean(R.styleable.ShadowContainer_enable, true);
        obtainStyledAttributes.recycle();
        Paint paint = new Paint();
        this.mShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setShadowLayer(dimension, dimension2, dimension3, color);
    }
}
