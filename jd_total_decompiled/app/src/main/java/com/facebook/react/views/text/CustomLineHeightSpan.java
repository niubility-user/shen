package com.facebook.react.views.text;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* loaded from: classes12.dex */
public class CustomLineHeightSpan implements LineHeightSpan, ReactSpan {
    private final int mHeight;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CustomLineHeightSpan(float f2) {
        this.mHeight = (int) Math.ceil(f2);
    }

    @Override // android.text.style.LineHeightSpan
    public void chooseHeight(CharSequence charSequence, int i2, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt) {
        int i6 = fontMetricsInt.descent;
        int i7 = this.mHeight;
        if (i6 > i7) {
            int min = Math.min(i7, i6);
            fontMetricsInt.descent = min;
            fontMetricsInt.bottom = min;
            fontMetricsInt.ascent = 0;
            fontMetricsInt.top = 0;
            return;
        }
        int i8 = fontMetricsInt.ascent;
        if ((-i8) + i6 > i7) {
            fontMetricsInt.bottom = i6;
            int i9 = (-i7) + i6;
            fontMetricsInt.ascent = i9;
            fontMetricsInt.top = i9;
            return;
        }
        int i10 = fontMetricsInt.bottom;
        if ((-i8) + i10 > i7) {
            fontMetricsInt.top = i8;
            fontMetricsInt.bottom = i8 + i7;
            return;
        }
        int i11 = fontMetricsInt.top;
        if ((-i11) + i10 > i7) {
            fontMetricsInt.top = i10 - i7;
            return;
        }
        double d = i11;
        double d2 = (i7 - ((-i11) + i10)) / 2.0f;
        double ceil = Math.ceil(d2);
        Double.isNaN(d);
        fontMetricsInt.top = (int) (d - ceil);
        double d3 = fontMetricsInt.bottom;
        double floor = Math.floor(d2);
        Double.isNaN(d3);
        int i12 = (int) (d3 + floor);
        fontMetricsInt.bottom = i12;
        fontMetricsInt.ascent = fontMetricsInt.top;
        fontMetricsInt.descent = i12;
    }
}
