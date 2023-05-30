package com.jd.lib.productdetail.core.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.jd.lib.productdetail.core.utils.PDUtils;

/* loaded from: classes15.dex */
public class VerticalCenterImageSpan extends ImageSpan {
    private int paddingV;

    public VerticalCenterImageSpan(Drawable drawable) {
        super(drawable);
        this.paddingV = PDUtils.dip2px(2.0f);
    }

    public static BitmapDrawable getBitmapDrawble(Context context, Bitmap bitmap) {
        BitmapDrawable bitmapDrawable;
        if (context != null) {
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        } else {
            bitmapDrawable = new BitmapDrawable(bitmap);
        }
        bitmapDrawable.setBounds(0, 0, Math.max(bitmapDrawable.getIntrinsicWidth(), 0), Math.max(bitmapDrawable.getIntrinsicHeight(), 0));
        return bitmapDrawable;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(@NonNull Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, @NonNull Paint paint) {
        Drawable drawable = getDrawable();
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        float f3 = ((paint.getFontMetrics().bottom - paint.getFontMetricsInt().top) - this.paddingV) / drawable.getBounds().bottom;
        if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        canvas.save();
        canvas.translate(f2, (int) (((((fontMetricsInt.descent + i5) + i5) + fontMetricsInt.ascent) / 2) - ((drawable.getBounds().bottom * f3) / 2.0f)));
        canvas.scale(f3, f3);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(@NonNull Paint paint, CharSequence charSequence, int i2, int i3, @Nullable Paint.FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        float f2 = ((paint.getFontMetrics().bottom - paint.getFontMetricsInt().top) - this.paddingV) / bounds.bottom;
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        return (int) (bounds.right * f2);
    }
}
