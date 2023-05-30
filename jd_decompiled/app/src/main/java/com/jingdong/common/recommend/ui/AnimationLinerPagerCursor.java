package com.jingdong.common.recommend.ui;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import androidx.annotation.Nullable;

/* loaded from: classes6.dex */
public class AnimationLinerPagerCursor extends LinerPagerCursor {
    private int cursorColor;
    private float indicatorOffset;
    private int indicatorOffsetMax;
    private boolean isRightToLeft;
    private float lastPositionOffset;
    private int scrolledPosition;
    private int selectColor;
    private Paint strokePaint;
    private Paint transitionPaint;

    public AnimationLinerPagerCursor(Context context) {
        super(context);
        this.isRightToLeft = true;
    }

    private boolean isBoundary(int i2) {
        return this.scrolledPosition == this.mCount - 1 && i2 == 0;
    }

    @Override // com.jingdong.common.recommend.ui.LinerPagerCursor
    public void drawPoint(Canvas canvas) {
        float f2;
        Paint paint;
        float f3;
        Paint paint2;
        this.dRectF.top = this.strokePaint.getStrokeWidth() / 2.0f;
        RectF rectF = this.dRectF;
        rectF.bottom = this.indicatorH - rectF.top;
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        int i2 = this.scrolledPosition + 1;
        int i3 = 0;
        while (i3 < this.mCount) {
            RectF rectF2 = this.dRectF;
            rectF2.left = rectF2.right + (i3 == 0 ? 0 : this.indicatorPadding) + (this.strokePaint.getStrokeWidth() / 2.0f);
            if (this.isRightToLeft) {
                RectF rectF3 = this.dRectF;
                float strokeWidth = rectF3.left - this.strokePaint.getStrokeWidth();
                if (i3 == this.scrolledPosition) {
                    f3 = this.selectedWidth - this.indicatorOffset;
                } else {
                    f3 = (i3 == i2 || isBoundary(i3)) ? this.indicatorW + this.indicatorOffset : this.indicatorW;
                }
                rectF3.right = strokeWidth + f3;
                RectF rectF4 = this.dRectF;
                float strokeWidth2 = (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f;
                float strokeWidth3 = (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f;
                if (i3 == this.scrolledPosition) {
                    paint2 = this.selectPaint;
                } else {
                    paint2 = (i3 == i2 || isBoundary(i3)) ? this.transitionPaint : this.cursorPaint;
                }
                canvas.drawRoundRect(rectF4, strokeWidth2, strokeWidth3, paint2);
            } else {
                RectF rectF5 = this.dRectF;
                float strokeWidth4 = rectF5.left - this.strokePaint.getStrokeWidth();
                if (i3 != i2 && !isBoundary(i3)) {
                    f2 = i3 == this.scrolledPosition ? this.indicatorW + this.indicatorOffset : this.indicatorW;
                } else {
                    f2 = this.selectedWidth - this.indicatorOffset;
                }
                rectF5.right = strokeWidth4 + f2;
                RectF rectF6 = this.dRectF;
                float strokeWidth5 = (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f;
                float strokeWidth6 = (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f;
                if (i3 != i2 && !isBoundary(i3)) {
                    paint = i3 == this.scrolledPosition ? this.transitionPaint : this.cursorPaint;
                } else {
                    paint = this.selectPaint;
                }
                canvas.drawRoundRect(rectF6, strokeWidth5, strokeWidth6, paint);
            }
            canvas.drawRoundRect(this.dRectF, (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f, (this.indicatorH - this.strokePaint.getStrokeWidth()) / 2.0f, this.strokePaint);
            i3++;
        }
    }

    @Override // com.jingdong.common.recommend.ui.LinerPagerCursor
    public void onPageScrolled(int i2, float f2, int i3) {
        int intValue;
        int intValue2;
        this.scrolledPosition = i2;
        float f3 = this.lastPositionOffset;
        if (f3 > f2) {
            this.isRightToLeft = false;
        } else if (f3 < f2) {
            this.isRightToLeft = true;
        }
        this.lastPositionOffset = f2;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        if (this.isRightToLeft) {
            this.indicatorOffset = this.indicatorOffsetMax * f2;
            intValue = ((Integer) argbEvaluator.evaluate(f2, Integer.valueOf(this.cursorColor), Integer.valueOf(this.selectColor))).intValue();
            intValue2 = ((Integer) argbEvaluator.evaluate(f2, Integer.valueOf(this.selectColor), Integer.valueOf(this.cursorColor))).intValue();
        } else {
            float f4 = 1.0f - f2;
            this.indicatorOffset = this.indicatorOffsetMax * f4;
            intValue = ((Integer) argbEvaluator.evaluate(f4, Integer.valueOf(this.cursorColor), Integer.valueOf(this.selectColor))).intValue();
            intValue2 = ((Integer) argbEvaluator.evaluate(f4, Integer.valueOf(this.selectColor), Integer.valueOf(this.cursorColor))).intValue();
        }
        this.transitionPaint.setColor(intValue);
        this.selectPaint.setColor(intValue2);
        postInvalidate();
    }

    @Override // com.jingdong.common.recommend.ui.LinerPagerCursor
    public void onPageSelected(int i2) {
        this.scrolledPosition = i2;
        this.indicatorOffset = 0.0f;
        super.onPageSelected(i2);
    }

    @Override // com.jingdong.common.recommend.ui.LinerPagerCursor
    public void setIndicatorColor(int i2, int i3, int i4) {
        if (this.cursorPaint == null) {
            Paint paint = new Paint();
            this.cursorPaint = paint;
            paint.setAntiAlias(true);
            Paint paint2 = new Paint();
            this.selectPaint = paint2;
            paint2.setAntiAlias(true);
            Paint paint3 = new Paint();
            this.transitionPaint = paint3;
            paint3.setAntiAlias(true);
            Paint paint4 = new Paint();
            this.strokePaint = paint4;
            paint4.setAntiAlias(true);
            this.strokePaint.setStyle(Paint.Style.STROKE);
        }
        this.strokePaint.setStrokeWidth(0.5f);
        this.cursorColor = i2;
        this.selectColor = i4;
        this.cursorPaint.setColor(i2);
        this.selectPaint.setColor(this.selectColor);
        this.transitionPaint.setColor(this.cursorColor);
        this.strokePaint.setColor(i3);
    }

    @Override // com.jingdong.common.recommend.ui.LinerPagerCursor
    public void setIndicatorSize(int i2, int i3, int i4, int i5) {
        this.indicatorOffsetMax = i3 - i2;
        super.setIndicatorSize(i2, i3, i4, i5);
    }

    public AnimationLinerPagerCursor(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isRightToLeft = true;
    }

    public AnimationLinerPagerCursor(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.isRightToLeft = true;
    }
}
