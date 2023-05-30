package com.jingdong.wireless.iconfont;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.TypedValue;
import com.jingdong.wireless.iconfont.widget.IconImpl;

/* loaded from: classes12.dex */
public class IconDrawable extends Drawable {
    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;
    private int alpha;
    private Context context;
    private Icon icon;
    private TextPaint paint;
    private int size;

    public IconDrawable(Context context, int i2) {
        this(context, new IconImpl("", context.getString(i2)), JDIconFontUtil.COMMON_PATH);
    }

    private int convertDpToPx(Context context, float f2) {
        return (int) TypedValue.applyDimension(1, f2, context.getResources().getDisplayMetrics());
    }

    private void init(Context context, IconImpl iconImpl, Typeface typeface) {
        this.context = context;
        this.icon = iconImpl;
        TextPaint textPaint = new TextPaint();
        this.paint = textPaint;
        textPaint.setTypeface(typeface);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextAlign(Paint.Align.CENTER);
        this.paint.setUnderlineText(false);
        this.paint.setColor(-16777216);
        this.paint.setAntiAlias(true);
    }

    private boolean isEnabled(int[] iArr) {
        for (int i2 : iArr) {
            if (i2 == 16842910) {
                return true;
            }
        }
        return false;
    }

    public IconDrawable actionBarSize() {
        return sizeDp(24);
    }

    public IconDrawable alpha(int i2) {
        setAlpha(i2);
        invalidateSelf();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void clearColorFilter() {
        this.paint.setColorFilter(null);
    }

    public IconDrawable color(int i2) {
        this.paint.setColor(i2);
        invalidateSelf();
        return this;
    }

    public IconDrawable colorRes(int i2) {
        this.paint.setColor(this.context.getResources().getColor(i2));
        invalidateSelf();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.paint.getTypeface() == null) {
            this.paint.setAlpha(0);
        }
        Rect bounds = getBounds();
        int height = bounds.height();
        TextPaint textPaint = this.paint;
        if (height > 2) {
            height--;
        }
        textPaint.setTextSize(height);
        Paint.FontMetrics fontMetrics = this.paint.getFontMetrics();
        Icon icon = this.icon;
        canvas.drawText(JDIconFontUtil.decode(icon == null ? "" : icon.character()), bounds.exactCenterX(), -fontMetrics.ascent, this.paint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.size;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.alpha;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        this.alpha = i2;
        this.paint.setAlpha(i2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public IconDrawable setIcon(Icon icon) {
        this.icon = icon;
        invalidateSelf();
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        int alpha = this.paint.getAlpha();
        int i2 = isEnabled(iArr) ? this.alpha : this.alpha / 2;
        this.paint.setAlpha(i2);
        return alpha != i2;
    }

    public void setStyle(Paint.Style style) {
        this.paint.setStyle(style);
    }

    public void setTypeface(Typeface typeface) {
        this.paint.setTypeface(typeface);
        invalidateSelf();
    }

    public IconDrawable sizeDp(int i2) {
        return sizePx(convertDpToPx(this.context, i2));
    }

    public IconDrawable sizePx(int i2) {
        this.size = i2;
        setBounds(0, 0, i2, i2);
        invalidateSelf();
        return this;
    }

    public IconDrawable sizeRes(int i2) {
        return sizePx(this.context.getResources().getDimensionPixelSize(i2));
    }

    public IconDrawable typeface(Typeface typeface) {
        setTypeface(typeface);
        invalidateSelf();
        return this;
    }

    public IconDrawable(Context context, IconImpl iconImpl, String str) {
        this.size = -1;
        this.alpha = 255;
        init(context, iconImpl, JDIconFontUtil.getTypefaceWithMta(context, str, iconImpl == null ? "" : iconImpl.character()));
    }

    public IconDrawable(Context context, IconImpl iconImpl, Typeface typeface) {
        this.size = -1;
        this.alpha = 255;
        init(context, iconImpl, typeface);
    }
}
