package com.facebook.react.views.text;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

/* loaded from: classes12.dex */
public class ShadowStyleSpan extends CharacterStyle implements ReactSpan {
    private final int mColor;
    private final float mDx;
    private final float mDy;
    private final float mRadius;

    public ShadowStyleSpan(float f2, float f3, float f4, int i2) {
        this.mDx = f2;
        this.mDy = f3;
        this.mRadius = f4;
        this.mColor = i2;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        textPaint.setShadowLayer(this.mRadius, this.mDx, this.mDy, this.mColor);
    }
}
