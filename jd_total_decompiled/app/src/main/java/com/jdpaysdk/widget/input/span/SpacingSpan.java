package com.jdpaysdk.widget.input.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jingdong.common.utils.LangUtils;

/* loaded from: classes18.dex */
public class SpacingSpan extends ReplacementSpan implements IFormatSpan {
    private TextPaint textPaint;

    private SpacingSpan() {
    }

    public static SpacingSpan create() {
        return new SpacingSpan();
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, @NonNull Paint paint) {
        canvas.drawText(charSequence, i2, i3, f2, i5, this.textPaint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i2, int i3, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        TextPaint textPaint = new TextPaint(paint);
        this.textPaint = textPaint;
        float measureText = textPaint.measureText(LangUtils.SINGLE_SPACE);
        if (charSequence instanceof Spanned) {
            for (CharacterStyle characterStyle : (CharacterStyle[]) ((Spanned) charSequence).getSpans(i2, i3, CharacterStyle.class)) {
                if (!(characterStyle instanceof MetricAffectingSpan)) {
                    characterStyle.updateDrawState(this.textPaint);
                }
            }
        }
        return (int) (measureText + this.textPaint.measureText(charSequence, i2, i3));
    }
}
