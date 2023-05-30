package com.jd.lib.un.basewidget.widget.banner.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jd.lib.un.basewidget.widget.banner.indicator.BaseIndicator;

/* loaded from: classes16.dex */
public class ClassicIndicator extends BaseIndicator {
    private int mLastSelected;
    private Paint normalPaint;
    private int normalPointColor;
    private int pointRadius;
    private int pointSpace;
    private Paint selectedPaint;
    private int selectedPointColor;
    private float selectedPointLeft;

    public ClassicIndicator(Context context) {
        this(context, null);
    }

    private void configPointLayout() {
        clearIndicatorPoint();
        for (int i2 = 0; i2 < getCount(); i2++) {
            BaseIndicator.IndicatorPoint indicatorPoint = new BaseIndicator.IndicatorPoint();
            indicatorPoint.left = getPaddingLeft() + ((this.pointSpace + (this.pointRadius * 2)) * i2);
            int paddingTop = getPaddingTop();
            indicatorPoint.top = paddingTop;
            int i3 = this.pointRadius;
            indicatorPoint.bottom = paddingTop + (i3 * 2);
            indicatorPoint.right = indicatorPoint.left + (i3 * 2);
            indicatorPoint.index = i2;
            indicatorPoint.rect = new RectF(indicatorPoint.left, indicatorPoint.top, indicatorPoint.right, indicatorPoint.bottom);
            addIndicatorPoint(indicatorPoint);
        }
    }

    private void drawNormalPoint(Canvas canvas) {
        for (BaseIndicator.IndicatorPoint indicatorPoint : this.indicatorPoints) {
            int i2 = indicatorPoint.left;
            int i3 = this.pointRadius;
            int i4 = indicatorPoint.top;
            canvas.drawCircle(i2 + i3, i4 + ((indicatorPoint.bottom - i4) / 2), i3, this.normalPaint);
        }
    }

    private void drawSelectedPoint(Canvas canvas) {
        canvas.drawCircle(this.selectedPointLeft + this.pointRadius, getPaddingTop() + (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) / 2), this.pointRadius, this.selectedPaint);
    }

    private void initConfig(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ClassicIndicator, 0, 0);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.ClassicIndicator_classic_radius) {
                this.pointRadius = obtainStyledAttributes.getDimensionPixelOffset(index, this.pointRadius);
            } else if (index == R.styleable.ClassicIndicator_classic_color_normal) {
                this.normalPointColor = obtainStyledAttributes.getColor(index, this.normalPointColor);
            } else if (index == R.styleable.ClassicIndicator_classic_color_selected) {
                this.selectedPointColor = obtainStyledAttributes.getColor(index, this.selectedPointColor);
            } else if (index == R.styleable.ClassicIndicator_classic_space) {
                this.pointSpace = obtainStyledAttributes.getDimensionPixelOffset(index, this.pointSpace);
            } else if (index == R.styleable.ClassicIndicator_classic_loop) {
                this.isSupportLoop = obtainStyledAttributes.getBoolean(index, this.isSupportLoop);
            }
        }
        obtainStyledAttributes.recycle();
        setPadding(10, 5, 5, 10);
    }

    private void initDrawConfig() {
        Paint paint = new Paint(1);
        this.normalPaint = paint;
        paint.setStrokeJoin(Paint.Join.ROUND);
        this.normalPaint.setStrokeCap(Paint.Cap.ROUND);
        this.normalPaint.setStyle(Paint.Style.FILL);
        this.normalPaint.setColor(this.normalPointColor);
        Paint paint2 = new Paint(1);
        this.selectedPaint = paint2;
        paint2.setStrokeJoin(Paint.Join.ROUND);
        this.selectedPaint.setStrokeCap(Paint.Cap.ROUND);
        this.selectedPaint.setStyle(Paint.Style.FILL);
        this.selectedPaint.setColor(this.selectedPointColor);
    }

    private void setCurrentItem(int i2) {
        setCurrentItem(i2, 0.0f);
    }

    @Override // com.jd.lib.un.basewidget.widget.banner.indicator.BaseIndicator
    protected void initSelected(int i2) {
        setCurrentItem(i2);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawNormalPoint(canvas);
        drawSelectedPoint(canvas);
    }

    @Override // android.view.View
    protected void onMeasure(int i2, int i3) {
        super.onMeasure(i2, i3);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i3);
        int size2 = View.MeasureSpec.getSize(i3);
        if (mode != 1073741824) {
            size = getPaddingLeft() + getPaddingRight() + (getCount() * this.pointRadius * 2) + ((getCount() - 1) * this.pointSpace);
        }
        if (mode2 != 1073741824) {
            size2 = getPaddingTop() + getPaddingBottom() + (this.pointRadius * 2);
        }
        setMeasuredDimension(size, size2);
        configPointLayout();
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i2, float f2, int i3) {
        setCurrentItem(i2, f2);
    }

    @Override // com.jd.lib.un.basewidget.widget.banner.indicator.BaseIndicator, androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i2) {
        super.onPageSelected(i2);
        this.mLastSelected = i2;
    }

    public ClassicIndicator(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    private void setCurrentItem(int i2, float f2) {
        int paddingLeft = getPaddingLeft() + ((this.pointSpace + (this.pointRadius * 2)) * i2);
        if (this.isSupportLoop && i2 == getCount() - 1) {
            if (i2 == getCount() - 1) {
                if (this.mLastSelected == 0) {
                    int paddingLeft2 = getPaddingLeft();
                    int paddingLeft3 = getPaddingLeft() + getPaddingRight();
                    int i3 = this.pointRadius;
                    float f3 = paddingLeft2 + ((-(paddingLeft3 + (i3 * 2 * 2))) * (1.0f - f2));
                    this.selectedPointLeft = f3;
                    if (f3 < (-i3) * 2) {
                        this.selectedPointLeft = getMeasuredWidth() + this.selectedPointLeft + (this.pointRadius * 2);
                    }
                } else {
                    float paddingLeft4 = paddingLeft + ((getPaddingLeft() + getPaddingRight() + (this.pointRadius * 2 * 2)) * f2);
                    this.selectedPointLeft = paddingLeft4;
                    if (paddingLeft4 > getMeasuredWidth()) {
                        this.selectedPointLeft = (this.selectedPointLeft - getMeasuredWidth()) - (this.pointRadius * 2);
                    }
                }
            }
        } else {
            this.selectedPointLeft = paddingLeft + ((this.pointSpace + (this.pointRadius * 2)) * f2);
        }
        invalidate();
    }

    public ClassicIndicator(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.pointRadius = dp2px(5);
        this.normalPointColor = -7829368;
        this.selectedPointColor = -1;
        this.pointSpace = dp2px(10);
        this.mLastSelected = -1;
        initConfig(attributeSet);
        initDrawConfig();
    }
}
