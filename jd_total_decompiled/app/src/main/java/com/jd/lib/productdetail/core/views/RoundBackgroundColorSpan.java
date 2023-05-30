package com.jd.lib.productdetail.core.views;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class RoundBackgroundColorSpan extends ReplacementSpan {
    private int bgColor;
    private int textColor;

    public RoundBackgroundColorSpan(int i2, int i3) {
        this.bgColor = i2;
        this.textColor = i3;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        int color = paint.getColor();
        paint.setColor(this.bgColor);
        canvas.drawRoundRect(new RectF(f2, i4 + PDUtils.dip2px(1.0f), ((int) paint.measureText(charSequence, i2, i3)) + PDUtils.dip2px(4.0f) + f2, i6), PDUtils.dip2px(2.0f), PDUtils.dip2px(2.0f), paint);
        paint.setColor(this.textColor);
        canvas.drawText(charSequence, i2, i3, f2 + PDUtils.dip2px(2.0f), i5, paint);
        paint.setColor(color);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        return ((int) paint.measureText(charSequence, i2, i3)) + PDUtils.dip2px(4.0f);
    }
}
