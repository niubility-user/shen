package com.jingdong.app.mall.mylib.CouponUnit;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

/* loaded from: classes4.dex */
public class CommonTypefaceSpan extends MetricAffectingSpan {
    private final Typeface newType;

    public CommonTypefaceSpan(Typeface typeface) {
        this.newType = typeface;
    }

    private static void applyCustomTypeFace(Paint paint, Typeface typeface) {
        if (typeface == null) {
            typeface = Typeface.DEFAULT_BOLD;
        }
        Typeface typeface2 = paint.getTypeface();
        int style = typeface2 == null ? 0 : typeface2.getStyle();
        if (typeface != null) {
            int style2 = style & (typeface.getStyle() ^ (-1));
            if ((style2 & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style2 & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
        }
        paint.setTypeface(typeface);
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        applyCustomTypeFace(textPaint, this.newType);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        applyCustomTypeFace(textPaint, this.newType);
    }
}
