package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

@TargetApi(21)
/* loaded from: classes12.dex */
public class CustomLetterSpacingSpan extends MetricAffectingSpan implements ReactSpan {
    private final float mLetterSpacing;

    public CustomLetterSpacingSpan(float f2) {
        this.mLetterSpacing = f2;
    }

    private void apply(TextPaint textPaint) {
        if (Float.isNaN(this.mLetterSpacing)) {
            return;
        }
        textPaint.setLetterSpacing(this.mLetterSpacing);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        apply(textPaint);
    }
}
