package com.jingdong.app.mall.bundle.dolphinlib.common.span;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.style.ReplacementSpan;

/* loaded from: classes19.dex */
public class RadiusBackgroundColorSpan extends ReplacementSpan {
    private int mColor;
    private int[] mColors;
    private int mRadius;
    private int mSize;
    private int mTextColor;

    public RadiusBackgroundColorSpan(int[] iArr, int i2, int i3) {
        this.mColors = iArr;
        this.mTextColor = i2;
        this.mRadius = i3;
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        float f3 = i5;
        float ascent = paint.ascent() + f3;
        float f4 = this.mSize + f2;
        float descent = paint.descent() + f3;
        if (this.mColors != null) {
            paint.setShader(new LinearGradient(0.0f, 0.0f, this.mSize, paint.descent() - paint.ascent(), this.mColors, new float[]{0.0f, 0.7f, 1.0f}, Shader.TileMode.CLAMP));
        } else {
            paint.setColor(this.mColor);
        }
        paint.setAntiAlias(true);
        RectF rectF = new RectF(f2, ascent, f4, descent);
        int i7 = this.mRadius;
        canvas.drawRoundRect(rectF, i7, i7, paint);
        paint.setShader(null);
        paint.setColor(this.mTextColor);
        canvas.drawText(charSequence, i2, i3, f2 + this.mRadius, f3, paint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        int measureText = (int) (paint.measureText(charSequence, i2, i3) + (this.mRadius * 2));
        this.mSize = measureText;
        return measureText;
    }

    public RadiusBackgroundColorSpan(int i2, int i3, int i4) {
        this.mColor = i2;
        this.mTextColor = i3;
        this.mRadius = i4;
    }
}
