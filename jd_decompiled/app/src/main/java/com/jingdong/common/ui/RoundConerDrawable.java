package com.jingdong.common.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RoundConerDrawable extends Drawable {
    public static final int RADIUS_ALL = 0;
    public static final int RADIUS_BOTTOM = 2;
    public static final int RADIUS_TOP = 1;
    private int color;
    private Paint mPaint;
    private float radius;
    private RectF rcF;
    private RectF rectF;
    private int type;

    public RoundConerDrawable(int i2) {
        this(i2, DPIUtil.dip2px(3.0f), 0);
    }

    private float getRadius() {
        float f2 = this.radius;
        if (f2 < 0.0f) {
            return 0.0f;
        }
        return f2;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float radius = getRadius();
        RectF rectF = this.rectF;
        if (rectF != null) {
            canvas.drawRect(rectF, this.mPaint);
        }
        canvas.drawRoundRect(this.rcF, radius, radius, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.mPaint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i2, int i3, int i4, int i5) {
        super.setBounds(i2, i3, i4, i5);
        float f2 = i2;
        float f3 = i3;
        float f4 = i4;
        float f5 = i5;
        this.rcF = new RectF(f2, f3, f4, f5);
        int i6 = this.type;
        if (i6 == 1) {
            this.rectF = new RectF(f2, f3 + getRadius(), f4, f5);
        } else if (i6 != 2) {
            this.rectF = null;
        } else {
            this.rectF = new RectF(f2, f3, f4, f5 - getRadius());
        }
    }

    public void setColor(int i2) {
        this.color = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public RoundConerDrawable(int i2, float f2, int i3) {
        this.color = i2;
        this.radius = f2;
        this.type = i3;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(this.color);
        this.mPaint.setAntiAlias(true);
    }
}
