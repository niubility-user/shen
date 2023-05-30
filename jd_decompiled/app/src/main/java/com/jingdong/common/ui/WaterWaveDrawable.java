package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.un.basewidget.R;
import com.jingdong.common.DpiUtil;

/* loaded from: classes6.dex */
public class WaterWaveDrawable extends Drawable {
    private boolean borderVis;
    private int[] defaultColorRes;
    private Context mContext;
    private Paint mPaint;
    private Path mPath;
    private int mPercent;
    private float radius;

    public WaterWaveDrawable(Context context) {
        int i2 = R.color.c_F6F6F6;
        this.defaultColorRes = new int[]{i2, R.color.c_F0F0F0, i2};
        this.borderVis = true;
        this.mContext = context;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPath = new Path();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(@NonNull Canvas canvas) {
        Rect bounds = getBounds();
        float f2 = bounds.right - bounds.left;
        float f3 = bounds.bottom - bounds.top;
        int dip2px = DpiUtil.dip2px(this.mContext, 1.0f);
        if (this.radius <= 0.0f) {
            this.radius = f3 / 2.0f;
        }
        int saveLayer = canvas.saveLayer(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight(), null, 31);
        this.mPaint.setColor(this.mContext.getResources().getColor(this.defaultColorRes[0]));
        this.mPaint.setStyle(Paint.Style.FILL);
        float f4 = dip2px;
        float f5 = f2 - f4;
        float f6 = f3 - f4;
        RectF rectF = new RectF(f4, f4, f5, f6);
        float f7 = this.radius;
        canvas.drawRoundRect(rectF, f7, f7, this.mPaint);
        this.mPath.reset();
        this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));
        this.mPaint.setColor(this.mContext.getResources().getColor(this.defaultColorRes[1]));
        this.mPaint.setStyle(Paint.Style.FILL);
        float f8 = f3 - ((f3 / 100.0f) * this.mPercent);
        this.mPath.moveTo(0.0f, f8);
        float dip2px2 = DpiUtil.dip2px(this.mContext, 6.0f);
        float f9 = f2 / 4.0f;
        this.mPath.quadTo(f9, f8 + dip2px2, f2 / 2.0f, f8);
        this.mPath.quadTo(f9 * 3.0f, f8 - dip2px2, f2, f8);
        this.mPath.lineTo(f2, f3);
        this.mPath.lineTo(0.0f, f3);
        this.mPath.close();
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPaint.setXfermode(null);
        canvas.restoreToCount(saveLayer);
        if (this.borderVis) {
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setColor(this.mContext.getResources().getColor(this.defaultColorRes[2]));
            this.mPaint.setStrokeWidth(DpiUtil.dip2px(this.mContext, 1.0f));
            RectF rectF2 = new RectF(f4, f4, f5, f6);
            float f10 = this.radius;
            canvas.drawRoundRect(rectF2, f10, f10, this.mPaint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setBorderVisi(boolean z) {
        this.borderVis = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    public void setDrawableColorRes(int[] iArr) {
        if (iArr == null || iArr.length != this.defaultColorRes.length) {
            return;
        }
        this.defaultColorRes = iArr;
    }

    public void setPercent(int i2) {
        if (i2 > 95) {
            i2 = 95;
        } else if (i2 < 5) {
            i2 = 5;
        }
        this.mPercent = i2;
    }

    public void setRoundRectRadius(float f2) {
        this.radius = f2;
    }
}
