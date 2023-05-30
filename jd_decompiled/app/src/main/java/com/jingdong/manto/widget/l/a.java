package com.jingdong.manto.widget.l;

import android.graphics.Paint;
import android.text.style.LineHeightSpan;

/* loaded from: classes16.dex */
public final class a implements LineHeightSpan {
    public int a;

    public a(float f2) {
        this.a = Math.round(f2);
    }

    public final boolean a(float f2) {
        return this.a != Math.round(f2);
    }

    @Override // android.text.style.LineHeightSpan
    public final void chooseHeight(CharSequence charSequence, int i2, int i3, int i4, int i5, Paint.FontMetricsInt fontMetricsInt) {
        int i6;
        int i7 = fontMetricsInt.ascent;
        int i8 = -i7;
        int i9 = this.a;
        if (i8 > i9) {
            int i10 = -i9;
            fontMetricsInt.ascent = i10;
            fontMetricsInt.top = i10;
            i6 = 0;
            fontMetricsInt.descent = 0;
        } else {
            if (fontMetricsInt.descent + i8 > i9) {
                int i11 = fontMetricsInt.bottom;
                fontMetricsInt.descent = i11;
                int i12 = i11 - i9;
                fontMetricsInt.ascent = i12;
                fontMetricsInt.top = i12;
                return;
            }
            int i13 = fontMetricsInt.bottom;
            if (i8 + i13 <= i9) {
                if ((-fontMetricsInt.top) + i13 > i9) {
                    fontMetricsInt.top = i13 - i9;
                    return;
                }
                int round = Math.round((i9 - r4) / 2.0f);
                fontMetricsInt.top -= round;
                fontMetricsInt.ascent -= round;
                fontMetricsInt.bottom += round;
                fontMetricsInt.descent = round + fontMetricsInt.descent;
                return;
            }
            fontMetricsInt.top = i7;
            i6 = i7 + i9;
        }
        fontMetricsInt.bottom = i6;
    }
}
