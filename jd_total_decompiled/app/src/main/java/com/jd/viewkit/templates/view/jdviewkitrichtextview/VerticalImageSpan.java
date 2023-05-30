package com.jd.viewkit.templates.view.jdviewkitrichtextview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.style.DynamicDrawableSpan;
import androidx.annotation.DrawableRes;

/* loaded from: classes18.dex */
public class VerticalImageSpan extends DynamicDrawableSpan {
    private int height;
    private Context mContext;
    private Drawable mDrawable;
    private int mResourceId;
    private int width;

    public VerticalImageSpan(Context context, Bitmap bitmap) {
        this(context, bitmap, 0, 0);
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i2, int i3, float f2, int i4, int i5, int i6, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        int i7 = paint.getFontMetricsInt().descent;
        canvas.translate(f2, ((i5 + i7) - ((i7 - r3.ascent) / 2)) - ((drawable.getBounds().bottom - drawable.getBounds().top) / 2));
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() {
        Drawable drawable = this.mDrawable;
        if (drawable == null) {
            drawable = null;
            try {
                drawable = this.mContext.getResources().getDrawable(this.mResourceId);
                if (this.width <= 0) {
                    this.width = drawable.getIntrinsicWidth();
                }
                if (this.height <= 0) {
                    this.height = drawable.getIntrinsicHeight();
                }
                drawable.setBounds(0, 0, this.width, this.height);
            } catch (Exception unused) {
                String str = "Unable to find resource: " + this.mResourceId;
            }
        }
        return drawable;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i2, int i3, Paint.FontMetricsInt fontMetricsInt) {
        Rect bounds = getDrawable().getBounds();
        if (fontMetricsInt != null) {
            Paint.FontMetricsInt fontMetricsInt2 = paint.getFontMetricsInt();
            int i4 = fontMetricsInt2.descent;
            int i5 = fontMetricsInt2.ascent;
            int i6 = i5 + ((i4 - i5) / 2);
            int i7 = (bounds.bottom - bounds.top) / 2;
            int i8 = i6 - i7;
            fontMetricsInt.ascent = i8;
            fontMetricsInt.top = i8;
            int i9 = i6 + i7;
            fontMetricsInt.bottom = i9;
            fontMetricsInt.descent = i9;
        }
        return bounds.right;
    }

    public VerticalImageSpan(Context context, Bitmap bitmap, int i2, int i3) {
        super(0);
        BitmapDrawable bitmapDrawable;
        this.mContext = context;
        if (context != null) {
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmap);
        } else {
            bitmapDrawable = new BitmapDrawable(bitmap);
        }
        this.mDrawable = bitmapDrawable;
        i2 = i2 <= 0 ? bitmapDrawable.getIntrinsicWidth() : i2;
        i3 = i3 <= 0 ? this.mDrawable.getIntrinsicHeight() : i3;
        this.mDrawable.setBounds(0, 0, i2 <= 0 ? 0 : i2, i3 <= 0 ? 0 : i3);
    }

    public VerticalImageSpan(Drawable drawable) {
        this(drawable, 0, 0);
    }

    public VerticalImageSpan(Drawable drawable, int i2, int i3) {
        super(0);
        this.mDrawable = drawable;
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        drawable.setBounds(0, 0, i2, i3);
    }

    public VerticalImageSpan(Context context, @DrawableRes int i2) {
        super(0);
        this.mContext = context;
        this.mResourceId = i2;
    }

    public VerticalImageSpan(Context context, @DrawableRes int i2, int i3, int i4) {
        super(0);
        this.mContext = context;
        this.mResourceId = i2;
        this.width = i3;
        this.height = i4;
    }
}
