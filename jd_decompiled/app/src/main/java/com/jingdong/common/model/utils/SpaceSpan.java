package com.jingdong.common.model.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;
import androidx.annotation.ColorInt;

/* loaded from: classes5.dex */
public class SpaceSpan extends ReplacementSpan {
    private int mColor;

    public SpaceSpan(@ColorInt int i2) {
        this.mColor = -16777216;
        this.mColor = i2;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        StringBuilder sb = new StringBuilder(charSequence.subSequence(i2, i3));
        sb.insert(0, ' ');
        paint.setColor(this.mColor);
        canvas.drawText(sb.toString(), f2, i5, paint);
    }

    @ColorInt
    public int getForegroundColor() {
        return this.mColor;
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        StringBuilder sb = new StringBuilder(charSequence.subSequence(i2, i3));
        sb.insert(0, ' ');
        return (int) paint.measureText(sb.toString());
    }
}
