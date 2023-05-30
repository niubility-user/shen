package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.R;
import com.jingdong.sdk.platform.utils.PlatformTools;

/* loaded from: classes10.dex */
public class CircleIndicator extends View {
    private int circleInterval;
    private int count;
    private int currentPage;
    private float mDensity;
    private final Paint mPaintFill;
    private final Paint mPaintStroke;
    private final TextPaint mPaintText;
    private int mTextHeight;
    private int radius;
    private int upLimit;

    public CircleIndicator(Context context) {
        super(context);
        this.radius = 4;
        this.mPaintStroke = new Paint(1);
        this.mPaintFill = new Paint(1);
        this.mPaintText = new TextPaint(1);
        this.count = 1;
        this.currentPage = 0;
        this.circleInterval = this.radius;
        this.upLimit = 6;
        initColors(-1, -1);
    }

    private void initColors(int i2, int i3) {
        this.mPaintStroke.setStyle(Paint.Style.FILL);
        this.mPaintStroke.setColor(i3);
        this.mPaintFill.setStyle(Paint.Style.FILL);
        this.mPaintFill.setColor(i2);
        this.mPaintText.setColor(PlatformTools.parseColor("#F96A0E"));
        this.mPaintText.setTextSize(this.mDensity * 18.0f);
        Paint.FontMetrics fontMetrics = this.mPaintText.getFontMetrics();
        this.mTextHeight = ((int) Math.ceil(fontMetrics.descent - fontMetrics.top)) + 2;
    }

    private int measureHeight(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (this.count > this.upLimit) {
            return this.mTextHeight + getPaddingTop() + getPaddingBottom();
        }
        if (mode == 1073741824) {
            return size;
        }
        int paddingTop = (this.radius * 2) + getPaddingTop() + getPaddingBottom();
        return mode == Integer.MIN_VALUE ? Math.min(paddingTop, size) : paddingTop;
    }

    private int measureWidth(int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode == 1073741824) {
            return size;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int i3 = this.count;
        int i4 = paddingLeft + (i3 * 2 * this.radius) + ((i3 - 1) * this.circleInterval);
        return mode == Integer.MIN_VALUE ? Math.min(i4, size) : i4;
    }

    public void initData(int i2, int i3) {
        this.count = i2;
        invalidate();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.count <= this.upLimit) {
            for (int i2 = 0; i2 < this.count; i2++) {
                int paddingLeft = getPaddingLeft();
                int i3 = this.radius;
                int paddingTop = getPaddingTop();
                canvas.drawCircle(paddingLeft + i3 + (((i3 * 2) + this.circleInterval) * i2), paddingTop + r3, this.radius, this.mPaintStroke);
            }
            float paddingLeft2 = getPaddingLeft() + this.radius + (((this.radius * 2) + this.circleInterval) * this.currentPage);
            int paddingTop2 = getPaddingTop();
            canvas.drawCircle(paddingLeft2, paddingTop2 + r2, this.radius, this.mPaintFill);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.currentPage);
        sb.append("/");
        sb.append(this.count - 1);
        canvas.drawText((this.currentPage + 1) + "/" + this.count, (getMeasuredWidth() - Layout.getDesiredWidth(sb.toString(), this.mPaintText)) / 2.0f, getPaddingTop() + getMeasuredHeight(), this.mPaintText);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        setMeasuredDimension(measureWidth(i2), measureHeight(i3));
    }

    public void setCurrentPage(int i2) {
        this.currentPage = i2;
        invalidate();
    }

    public void setFillColor(int i2) {
        this.mPaintFill.setColor(i2);
        invalidate();
    }

    public void setStrokeColor(int i2) {
        this.mPaintStroke.setColor(i2);
        invalidate();
    }

    public void setUpLimit(int i2) {
        this.upLimit = i2;
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radius = 4;
        this.mPaintStroke = new Paint(1);
        this.mPaintFill = new Paint(1);
        this.mPaintText = new TextPaint(1);
        this.count = 1;
        this.currentPage = 0;
        this.circleInterval = this.radius;
        this.upLimit = 6;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircleIndicator);
        try {
            try {
                int color = obtainStyledAttributes.getColor(R.styleable.CircleIndicator_circleSolidColor, -1);
                int color2 = obtainStyledAttributes.getColor(R.styleable.CircleIndicator_circleStrokeColor, -1);
                int dimension = (int) obtainStyledAttributes.getDimension(R.styleable.CircleIndicator_circleRadius, this.radius);
                this.radius = dimension;
                this.circleInterval = (int) obtainStyledAttributes.getDimension(R.styleable.CircleIndicator_circleInterval, dimension);
                this.mDensity = BaseInfo.getDensity();
                initColors(color, color2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
