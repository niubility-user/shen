package com.jdpaysdk.widget.input.span;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.ReplacementSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* loaded from: classes18.dex */
public class SlashSpan extends ReplacementSpan implements IFormatSpan {
    private TextPaint textPaint;

    private SlashSpan() {
    }

    public static SlashSpan create() {
        return new SlashSpan();
    }

    @Override // android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, @NonNull Paint paint) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence, i2, i3);
        spannableStringBuilder.append((CharSequence) "/");
        canvas.drawText(spannableStringBuilder, 0, spannableStringBuilder.length(), f2, i5, this.textPaint);
    }

    @Override // android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i2, int i3, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        this.textPaint = new TextPaint(paint);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(charSequence, i2, i3);
        spannableStringBuilder.append((CharSequence) "/");
        return (int) this.textPaint.measureText(spannableStringBuilder, 0, spannableStringBuilder.length());
    }
}
