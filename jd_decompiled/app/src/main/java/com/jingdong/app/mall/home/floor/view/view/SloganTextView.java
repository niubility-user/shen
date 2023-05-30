package com.jingdong.app.mall.home.floor.view.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import com.jingdong.app.mall.home.floor.common.d;
import com.jingdong.app.mall.home.floor.view.view.GradientTextView;

/* loaded from: classes4.dex */
public class SloganTextView extends GradientTextView {
    private boolean mArrowVisibility;
    private Paint mBgPaint;
    private RectF mBgRectF;
    private Paint mDarkBgPaint;
    private Paint mLinePaint;
    private int mPaintWidth;

    public SloganTextView(Context context) {
        super(context);
        this.mArrowVisibility = true;
        this.mBgRectF = new RectF();
        this.mBgPaint = new Paint();
        this.mDarkBgPaint = new Paint(1);
        this.mLinePaint = new Paint(1);
        initAttribute();
    }

    private void initAttribute() {
        this.mPaintWidth = d.d(1) + 1;
        this.mBgPaint.setStyle(Paint.Style.STROKE);
        this.mBgPaint.setStrokeWidth(this.mPaintWidth);
        this.mBgPaint.setAntiAlias(true);
        this.mDarkBgPaint.setAntiAlias(true);
        this.mDarkBgPaint.setAlpha(20);
    }

    private void useTextPaintShader() {
        LinearGradient linearGradient;
        GradientTextView.GradientParam gradientParam = this.mTextGradientParam;
        if (gradientParam != null && gradientParam.gradientType != null && (linearGradient = this.mTextGradient) != null) {
            this.mBgPaint.setShader(linearGradient);
            this.mDarkBgPaint.setShader(this.mTextGradient);
            this.mLinePaint.setShader(this.mTextGradient);
            return;
        }
        this.mBgPaint.setShader(null);
        this.mDarkBgPaint.setShader(null);
        this.mLinePaint.setShader(null);
        this.mBgPaint.setColor(getPaint().getColor());
        this.mDarkBgPaint.setColor(getPaint().getColor() & 553648127);
        this.mLinePaint.setColor(getPaint().getColor());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.app.mall.home.floor.view.view.GradientTextView
    public void drawTextBg(Canvas canvas) {
        super.drawTextBg(canvas);
        useTextPaintShader();
        RectF rectF = this.mBgRectF;
        int i2 = this.mPaintWidth;
        rectF.left = i2;
        rectF.top = i2;
        rectF.right = getWidth() - this.mPaintWidth;
        this.mBgRectF.bottom = getHeight() - this.mPaintWidth;
        canvas.drawRoundRect(this.mBgRectF, getHeight() >> 1, getHeight() >> 1, this.mBgPaint);
        if (this.mArrowVisibility) {
            float d = this.mBgRectF.right - d.d(13);
            RectF rectF2 = this.mBgRectF;
            canvas.drawLine(d, (rectF2.bottom + this.mPaintWidth) / 2.0f, rectF2.right - d.d(19), ((this.mBgRectF.bottom + this.mPaintWidth) / 2.0f) - d.d(7), this.mBgPaint);
            float d2 = this.mBgRectF.right - d.d(13);
            RectF rectF3 = this.mBgRectF;
            canvas.drawLine(d2, (rectF3.bottom + this.mPaintWidth) / 2.0f, rectF3.right - d.d(19), ((this.mBgRectF.bottom + this.mPaintWidth) / 2.0f) + d.d(7), this.mBgPaint);
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i2, int i3, int i4, int i5) {
        super.onSizeChanged(i2, i3, i4, i5);
        if (i4 == i2 && i5 == i3) {
            return;
        }
        initAttribute();
    }

    public void setSloganArrowVisibility(boolean z) {
        this.mArrowVisibility = z;
        if (z) {
            setPadding(d.d(17), d.d(4), d.d(32), d.d(4));
        } else {
            setPadding(d.d(17), d.d(4), d.d(17), d.d(4));
        }
        postInvalidate();
    }
}
