package com.jingdong.app.mall.bundle.styleinfoview.utils;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes3.dex */
public class SuperTextSpan extends MetricAffectingSpan {
    double ratio;

    public SuperTextSpan(double d) {
        this.ratio = 0.5d;
        this.ratio = d;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        if (textPaint != null) {
            int i2 = textPaint.baselineShift;
            double ascent = textPaint.ascent();
            double d = this.ratio;
            Double.isNaN(ascent);
            textPaint.baselineShift = i2 + ((int) (ascent * d));
        }
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        if (textPaint != null) {
            int i2 = textPaint.baselineShift;
            double ascent = textPaint.ascent();
            double d = this.ratio;
            Double.isNaN(ascent);
            textPaint.baselineShift = i2 + ((int) (ascent * d));
        }
    }
}
