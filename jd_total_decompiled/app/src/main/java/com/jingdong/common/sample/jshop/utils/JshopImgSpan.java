package com.jingdong.common.sample.jshop.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: classes6.dex */
public class JshopImgSpan extends ImageSpan {
    public JshopImgSpan(Context context, int i2) {
        super(context, i2);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f2, ((((fontMetricsInt.descent - fontMetricsInt.ascent) - drawable.getBounds().bottom) / 2) + fontMetricsInt.ascent) - fontMetricsInt.top);
        drawable.draw(canvas);
        canvas.restore();
    }
}
