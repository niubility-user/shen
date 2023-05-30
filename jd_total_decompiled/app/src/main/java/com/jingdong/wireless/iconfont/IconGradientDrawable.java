package com.jingdong.wireless.iconfont;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.util.TypedValue;
import com.jingdong.wireless.iconfont.widget.IconImpl;

/* loaded from: classes12.dex */
public class IconGradientDrawable extends GradientDrawable {
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    private int alpha;
    private Context context;
    private Icon icon;
    private TextPaint textPaint;
    private Rect textRect;

    public IconGradientDrawable(Context context, int i2) {
        this(context, new IconImpl("", context.getString(i2)), JDIconFontUtil.COMMON_PATH);
    }

    private void init(Context context, IconImpl iconImpl, Typeface typeface) {
        this.context = context;
        this.icon = iconImpl;
        TextPaint textPaint = new TextPaint();
        this.textPaint = textPaint;
        textPaint.setTypeface(typeface);
        this.textPaint.setStyle(Paint.Style.FILL);
        this.textPaint.setTextAlign(Paint.Align.CENTER);
        this.textPaint.setUnderlineText(false);
        this.textPaint.setColor(-16777216);
        this.textPaint.setAntiAlias(true);
    }

    private boolean isEnabled(int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                return true;
            }
        }
        return false;
    }

    public IconGradientDrawable actionBarSize() {
        return sizeDp(24);
    }

    public IconGradientDrawable alpha(int i2) {
        setAlpha(i2);
        invalidateSelf();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void clearColorFilter() {
        this.textPaint.setColorFilter(null);
    }

    public IconGradientDrawable color(int i2) {
        this.textPaint.setColor(i2);
        invalidateSelf();
        return this;
    }

    public IconGradientDrawable colorRes(int i2) {
        this.textPaint.setColor(this.context.getResources().getColor(i2));
        invalidateSelf();
        return this;
    }

    public int convertDpToPx(Context context, float f2) {
        return (int) TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (this.textPaint.getTypeface() == null) {
            this.textPaint.setAlpha(0);
        }
        if (this.textRect == null) {
            this.textRect = copyBounds();
        }
        Icon icon = this.icon;
        String decode = JDIconFontUtil.decode(icon == null ? "" : icon.character());
        Paint.FontMetrics fontMetrics = this.textPaint.getFontMetrics();
        float f2 = fontMetrics.bottom;
        canvas.drawText(decode, this.textRect.exactCenterX(), this.textRect.centerY() + (((f2 - fontMetrics.top) / 2.0f) - f2), this.textPaint);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.textPaint.setColorFilter(colorFilter);
    }

    public IconGradientDrawable setIcon(Icon icon) {
        this.icon = icon;
        invalidateSelf();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        int alpha = this.textPaint.getAlpha();
        int i2 = isEnabled(iArr) ? this.alpha : this.alpha / 2;
        this.textPaint.setAlpha(i2);
        return alpha != i2;
    }

    public void setStyle(Paint.Style style) {
        this.textPaint.setStyle(style);
    }

    public void setTextAlpha(int i2) {
        this.alpha = i2;
        this.textPaint.setAlpha(i2);
    }

    public void setTypeface(Typeface typeface) {
        this.textPaint.setTypeface(typeface);
        invalidateSelf();
    }

    public IconGradientDrawable sizeDp(int i2) {
        return sizePx(convertDpToPx(this.context, i2));
    }

    public IconGradientDrawable sizePx(int i2) {
        this.textPaint.setTextSize(i2);
        invalidateSelf();
        return this;
    }

    public IconGradientDrawable sizeRes(int i2) {
        return sizePx(this.context.getResources().getDimensionPixelSize(i2));
    }

    public IconGradientDrawable typeface(Typeface typeface) {
        setTypeface(typeface);
        invalidateSelf();
        return this;
    }

    public IconGradientDrawable(Context context, IconImpl iconImpl, String str) {
        this.alpha = 255;
        init(context, iconImpl, JDIconFontUtil.getTypefaceWithMta(context, str, iconImpl == null ? "" : iconImpl.character()));
    }

    public IconGradientDrawable(Context context, IconImpl iconImpl, Typeface typeface) {
        this.alpha = 255;
        init(context, iconImpl, typeface);
    }
}
