package com.jingdong.jdsdk.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.text.TextUtils;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.core.content.ContextCompat;

/* loaded from: classes14.dex */
public class TextDrawable extends GradientDrawable {
    private String mContent;
    private Context mContext;
    private TextPaint mTextPaint;
    private int mTextSize;
    private int mTextStroke;
    private Rect rectF;

    public TextDrawable(Context context, String str) {
        this.mContext = context;
        this.mContent = str;
        init();
    }

    private void init() {
        TextPaint textPaint = new TextPaint();
        this.mTextPaint = textPaint;
        textPaint.setStyle(Paint.Style.FILL);
        this.mTextPaint.setTextAlign(Paint.Align.CENTER);
        this.mTextPaint.setAntiAlias(true);
    }

    public int dip2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (TextUtils.isEmpty(this.mContent)) {
            return;
        }
        if (this.rectF == null) {
            this.rectF = copyBounds();
        }
        int height = this.rectF.height();
        if (this.mTextSize == 0) {
            TextPaint textPaint = this.mTextPaint;
            if (height > 2) {
                height--;
            }
            textPaint.setTextSize(height);
        }
        Paint.FontMetrics fontMetrics = this.mTextPaint.getFontMetrics();
        float f2 = fontMetrics.bottom;
        canvas.drawText(this.mContent, this.rectF.exactCenterX(), this.rectF.centerY() + (((f2 - fontMetrics.top) / 2.0f) - f2), this.mTextPaint);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getBounds().height();
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return getBounds().width();
    }

    public TextDrawable setBgColor(@ColorInt int i2) {
        setColor(i2);
        invalidateSelf();
        return this;
    }

    public TextDrawable setBgColorRes(@ColorRes int i2) {
        setColor(ContextCompat.getColor(this.mContext, i2));
        invalidateSelf();
        return this;
    }

    public TextDrawable setBgSizeDp(int i2, int i3) {
        setBounds(0, 0, dip2px(this.mContext, i2), dip2px(this.mContext, i3));
        invalidateSelf();
        return this;
    }

    public TextDrawable setDrawableAlpha(int i2) {
        setAlpha(i2);
        invalidateSelf();
        return this;
    }

    public TextDrawable setRadiusDp(int i2) {
        float dip2px = dip2px(this.mContext, i2);
        setCornerRadii(new float[]{dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px, dip2px});
        invalidateSelf();
        return this;
    }

    public TextDrawable setTextColor(@ColorInt int i2) {
        this.mTextPaint.setColor(i2);
        invalidateSelf();
        return this;
    }

    public TextDrawable setTextColorRes(@ColorRes int i2) {
        this.mTextPaint.setColor(ContextCompat.getColor(this.mContext, i2));
        invalidateSelf();
        return this;
    }

    public TextDrawable setTextSizeSp(int i2) {
        int sp2px = sp2px(this.mContext, i2);
        this.mTextSize = sp2px;
        this.mTextPaint.setTextSize(sp2px);
        invalidateSelf();
        return this;
    }

    public TextDrawable setTextTypeface(Typeface typeface) {
        this.mTextPaint.setTypeface(typeface);
        invalidateSelf();
        return this;
    }

    public int sp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public TextDrawable setRadiusDp(int i2, int i3, int i4, int i5) {
        int dip2px = dip2px(this.mContext, i2);
        int dip2px2 = dip2px(this.mContext, i3);
        int dip2px3 = dip2px(this.mContext, i4);
        float f2 = dip2px;
        float f3 = dip2px2;
        float dip2px4 = dip2px(this.mContext, i5);
        float f4 = dip2px3;
        setCornerRadii(new float[]{f2, f2, f3, f3, dip2px4, dip2px4, f4, f4});
        invalidateSelf();
        return this;
    }

    @Deprecated
    public TextDrawable(Context context, String str, int i2) {
        this.mContext = context;
        this.mContent = str;
        this.mTextStroke = dip2px(context, i2);
        init();
    }
}
