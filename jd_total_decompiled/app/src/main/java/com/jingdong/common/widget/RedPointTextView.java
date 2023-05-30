package com.jingdong.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.core.internal.view.SupportMenu;

/* loaded from: classes12.dex */
public class RedPointTextView extends TextView {
    private boolean isRedPointShow;
    private int mPointColor;
    private float mRadius;
    private Paint mRedPaint;

    public RedPointTextView(Context context) {
        super(context);
        this.isRedPointShow = false;
        this.mRadius = 0.0f;
        this.mPointColor = SupportMenu.CATEGORY_MASK;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.isRedPointShow || this.mRadius <= 0.0f) {
            return;
        }
        if (this.mRedPaint == null) {
            this.mRedPaint = new Paint();
        }
        this.mRedPaint.setColor(this.mPointColor);
        this.mRedPaint.setAntiAlias(true);
        this.mRedPaint.setDither(true);
        this.mRedPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        float f2 = this.mRadius;
        canvas.drawCircle(getWidth() - f2, 2.0f * f2, f2, this.mRedPaint);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int measuredHeight = getMeasuredHeight();
        float f2 = this.mRadius;
        setMeasuredDimension((int) (getMeasuredWidth() + (4.0f * f2)), (int) (measuredHeight + (f2 * 2.0f)));
    }

    public void setPointColor(@ColorInt int i2) {
        this.mPointColor = i2;
    }

    public void setRadius(float f2) {
        if (f2 > 0.0f) {
            this.mRadius = f2;
        }
    }

    public void setRedPointShow(boolean z) {
        this.isRedPointShow = z;
    }

    public RedPointTextView(Context context, AttributeSet attributeSet, boolean z) {
        super(context, attributeSet);
        this.isRedPointShow = false;
        this.mRadius = 0.0f;
        this.mPointColor = SupportMenu.CATEGORY_MASK;
        this.isRedPointShow = z;
    }
}
