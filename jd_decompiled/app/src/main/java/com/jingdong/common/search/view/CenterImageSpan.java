package com.jingdong.common.search.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import androidx.annotation.NonNull;

/* loaded from: classes6.dex */
public class CenterImageSpan extends ImageSpan {
    public CenterImageSpan(@NonNull Context context, @NonNull Bitmap bitmap) {
        super(context, bitmap);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        int i7 = paint.getFontMetricsInt().ascent + i5;
        canvas.translate(f2, i7 + ((((i5 + r3.descent) - i7) - (drawable.getBounds().bottom - drawable.getBounds().top)) / 2));
        drawable.draw(canvas);
        canvas.restore();
    }

    public CenterImageSpan(@NonNull Drawable drawable) {
        super(drawable);
    }
}
