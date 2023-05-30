package com.jd.viewkit.templates.view.helper;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

/* loaded from: classes18.dex */
public class JDViewKitRoundHelper {
    private int mStrokeColor;
    private int mStrokeWidth;
    private float[] rdii = new float[8];
    private Path mClipPath = new Path();
    private Paint mPaint = new Paint(1);
    private RectF mRectF = new RectF();

    public void darwStroke(Canvas canvas) {
        if (this.mStrokeWidth > 0) {
            getClipPathPath();
            this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            this.mPaint.setColor(-1);
            this.mPaint.setStrokeWidth(this.mStrokeWidth * 2);
            this.mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(this.mClipPath, this.mPaint);
            this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            this.mPaint.setColor(this.mStrokeColor);
            this.mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(this.mClipPath, this.mPaint);
        }
    }

    public Path getClipPathPath() {
        this.mClipPath.reset();
        this.mClipPath.addRoundRect(this.mRectF, this.rdii, Path.Direction.CW);
        return this.mClipPath;
    }

    public void setRect(RectF rectF) {
        this.mRectF = rectF;
    }

    public void setRound(float f2, float f3, float f4, float f5) {
        float[] fArr = this.rdii;
        fArr[0] = f2;
        fArr[1] = f2;
        fArr[2] = f3;
        fArr[3] = f3;
        fArr[4] = f4;
        fArr[5] = f4;
        fArr[6] = f5;
        fArr[7] = f5;
    }

    public void setStrokeColor(int i2) {
        this.mStrokeColor = i2;
    }

    public void setStrokeWidth(int i2) {
        this.mStrokeWidth = i2;
    }
}
