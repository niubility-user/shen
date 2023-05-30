package com.jd.lib.productdetail.core.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.text.TextUtils;
import com.jingdong.corelib.utils.Log;
import jpbury.t;

/* loaded from: classes15.dex */
public class DateDrawableForTen extends Drawable {
    private static final int HEIGHT_320 = 320;
    private static final String PREFIX = ":";
    private static final int WIDTH_240 = 240;
    private boolean isChangeSkin;
    private float mTextSize;
    private Typeface mTypeFace;
    private TextPaint paint;
    private CharSequence hh = "00";
    private CharSequence mm = "00";
    private CharSequence ss = "00";
    private int textColor = -16777216;
    private int prefixColor = -16777216;
    private int backgroundColor = -1;
    private int backgroundWidth = 0;
    private int backgroundHeight = 0;
    private int dividerWh = 2;
    private int conner = PDUtils.dip2px(4.0f);

    public DateDrawableForTen(boolean z) {
        this.isChangeSkin = false;
        this.isChangeSkin = z;
        TextPaint textPaint = new TextPaint(1);
        this.paint = textPaint;
        textPaint.setAntiAlias(true);
        this.paint.setTextSize(14.0f);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(this.dividerWh);
    }

    private float measureX(Paint paint, CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return 0.0f;
        }
        return (this.backgroundWidth - paint.measureText(charSequence.toString())) / 2.0f;
    }

    private float measureY(Paint paint) {
        return 0.0f;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        try {
            Typeface typeface = this.mTypeFace;
            if (typeface == null) {
                typeface = Typeface.DEFAULT;
            }
            this.paint.setTypeface(typeface);
            Rect bounds = getBounds();
            int dip2px = PDUtils.dip2px(2.0f);
            float f2 = (bounds.right - ((this.backgroundWidth * 3) + r5)) / 2.0f;
            Rect rect = new Rect();
            this.paint.getTextBounds("00", 0, 2, rect);
            float height = (bounds.height() / 2) + (rect.height() / 2);
            float f3 = dip2px * 2;
            float f4 = dip2px * 4;
            float measureX = measureX(this.paint, this.hh) + f2;
            float measureX2 = this.backgroundWidth + f2 + f3 + measureX(this.paint, this.mm);
            float measureX3 = (this.backgroundWidth * 2) + f2 + f4 + measureX(this.paint, this.ss);
            if (PDUtils.getWidth() <= 240 && PDUtils.getHeight() <= 320) {
                measureX -= 1.0f;
                measureX2 -= 1.0f;
                measureX3 -= 1.0f;
            }
            int i2 = this.backgroundWidth;
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.textColor);
            CharSequence charSequence = this.hh;
            canvas.drawText(charSequence, 0, charSequence.length(), measureX, height + measureY(this.paint), this.paint);
            this.paint.setColor(this.prefixColor);
            canvas.drawText(PREFIX, 0, 1, f2 + i2, height + measureY(this.paint), (Paint) this.paint);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.textColor);
            CharSequence charSequence2 = this.mm;
            canvas.drawText(charSequence2, 0, charSequence2.length(), measureX2, height + measureY(this.paint), this.paint);
            this.paint.setColor(this.prefixColor);
            canvas.drawText(PREFIX, 0, 1, f3 + f2 + (i2 * 2), height + measureY(this.paint), (Paint) this.paint);
            this.paint.setStyle(Paint.Style.FILL);
            this.paint.setColor(this.textColor);
            CharSequence charSequence3 = this.ss;
            canvas.drawText(charSequence3, 0, charSequence3.length(), measureX3, height + measureY(this.paint), this.paint);
        } catch (Exception e2) {
            if (Log.D) {
                Log.d(t.f20145j, e2.getMessage());
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public int getTextColor() {
        return this.textColor;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setBackgroundColor(int i2) {
        this.backgroundColor = i2;
    }

    public void setBackgroundHeight(int i2) {
        this.backgroundHeight = i2;
    }

    public void setBackgroundWidth(int i2) {
        this.backgroundWidth = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setConner(int i2) {
        this.conner = i2;
    }

    public void setHour(CharSequence charSequence) {
        this.hh = charSequence;
    }

    public void setMinute(CharSequence charSequence) {
        this.mm = charSequence;
    }

    public void setPointColor(int i2) {
        this.prefixColor = i2;
    }

    public void setSecond(CharSequence charSequence) {
        this.ss = charSequence;
    }

    public void setTextColor(int i2) {
        this.textColor = i2;
    }

    public void setTextSize(float f2) {
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setTextSize(f2);
        }
        this.mTextSize = f2;
    }

    public void setTypeFace(Typeface typeface) {
        this.mTypeFace = typeface;
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setTypeface(typeface);
        }
    }
}
