package com.jingdong.app.mall.bundle.productdetailcard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* loaded from: classes3.dex */
public class a extends ImageSpan {

    /* renamed from: g  reason: collision with root package name */
    private int f8253g;

    public a(Drawable drawable) {
        super(drawable);
    }

    public static BitmapDrawable a(Context context, Bitmap bitmap) {
        BitmapDrawable bitmapDrawable;
        if (context != null) {
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        } else {
            bitmapDrawable = new BitmapDrawable(bitmap);
        }
        int intrinsicWidth = bitmapDrawable.getIntrinsicWidth();
        int intrinsicHeight = bitmapDrawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 0;
        }
        if (intrinsicHeight <= 0) {
            intrinsicHeight = 0;
        }
        bitmapDrawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        return bitmapDrawable;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f2, (((i6 - i4) - drawable.getBounds().bottom) / 2) + i4 + this.f8253g);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i4 = fontMetricsInt2.bottom - fontMetricsInt2.top;
            int i5 = (bounds.bottom - bounds.top) / 2;
            int i6 = i4 / 4;
            int i7 = i5 - i6;
            int i8 = -(i5 + i6);
            fontMetricsInt.ascent = i8;
            fontMetricsInt.top = i8;
            fontMetricsInt.bottom = i7;
            fontMetricsInt.descent = i7;
        }
        return bounds.right;
    }
}
