package com.jingdong.common.ntask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
final class NTaskProgress extends View {
    private Paint mBgPaint;
    private RectF mCircleRectF;
    private int mCurrentAngle;
    private Paint mProgressPaint;
    private int mStrokeWidth;

    public NTaskProgress(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.mStrokeWidth = DPIUtil.getWidthByDesignValue750(getContext(), 4);
        this.mCircleRectF = new RectF();
        Paint paint = new Paint();
        this.mBgPaint = paint;
        paint.setAntiAlias(true);
        this.mBgPaint.setStyle(Paint.Style.STROKE);
        this.mBgPaint.setStrokeWidth(this.mStrokeWidth);
        this.mBgPaint.setColor(Color.parseColor("#26000000"));
        Paint paint2 = new Paint();
        this.mProgressPaint = paint2;
        paint2.setAntiAlias(true);
        this.mProgressPaint.setStyle(Paint.Style.STROKE);
        this.mProgressPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mProgressPaint.setStrokeWidth(this.mStrokeWidth);
        this.mProgressPaint.setColor(Color.parseColor("#FFD000"));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = this.mCircleRectF;
        int i2 = this.mStrokeWidth;
        rectF.set(i2, i2, getWidth() - this.mStrokeWidth, getHeight() - this.mStrokeWidth);
        canvas.drawArc(this.mCircleRectF, -90.0f, 360.0f, false, this.mBgPaint);
        canvas.drawArc(this.mCircleRectF, -90.0f, this.mCurrentAngle, false, this.mProgressPaint);
    }

    public void setProgress(int i2) {
        this.mCurrentAngle = (int) ((i2 / 100.0f) * 360.0f);
        invalidate();
    }
}
