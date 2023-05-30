package com.jingdong.common.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.common.UnLog;

/* loaded from: classes6.dex */
public class CountdownDrawable extends Drawable {
    private static final String HH = "\u65f6";
    private static final String MM = "\u5206";
    private static final String PREFIX = "\u8fd8\u5269";
    private static final String SS = "\u79d2";
    private static final String TAG = "CountdownDrawable";
    public static final int TYPE_DIY = 1;
    public static final int TYPE_NORMOL = 0;
    private CharSequence hh;
    private CharSequence mm;
    private TextPaint paint;
    private CharSequence ss;
    private CharSequence text;
    private int text_color;
    private float text_size;
    private int type;

    public CountdownDrawable(Context context) {
        this.text_color = -16777216;
        TextPaint textPaint = new TextPaint(1);
        this.paint = textPaint;
        textPaint.setAntiAlias(true);
        this.paint.setTextSize(18.0f);
        this.paint.setTypeface(Typeface.DEFAULT_BOLD);
        this.paint.setStyle(Paint.Style.FILL);
        setType(0);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        try {
            Rect bounds = getBounds();
            Rect rect = new Rect();
            getPadding(rect);
            float f2 = bounds.left + rect.left;
            float f3 = bounds.top + rect.top + this.text_size + 1.0f;
            int i2 = this.type;
            if (i2 == 0) {
                this.paint.setColor(-16777216);
                canvas.drawText(PREFIX, 0, 2, f2, f3, (Paint) this.paint);
                this.paint.setColor(SupportMenu.CATEGORY_MASK);
                CharSequence charSequence = this.hh;
                canvas.drawText(charSequence, 0, charSequence.length(), f2 + this.paint.measureText(PREFIX), f3, this.paint);
                this.paint.setColor(-16777216);
                canvas.drawText(HH, 0, 1, f2 + this.paint.measureText(PREFIX + ((Object) this.hh)), f3, (Paint) this.paint);
                this.paint.setColor(SupportMenu.CATEGORY_MASK);
                CharSequence charSequence2 = this.mm;
                canvas.drawText(charSequence2, 0, charSequence2.length(), f2 + this.paint.measureText(PREFIX + ((Object) this.hh) + HH), f3, this.paint);
                this.paint.setColor(-16777216);
                canvas.drawText(MM, 0, 1, f2 + this.paint.measureText(PREFIX + ((Object) this.hh) + HH + ((Object) this.mm)), f3, (Paint) this.paint);
                this.paint.setColor(SupportMenu.CATEGORY_MASK);
                CharSequence charSequence3 = this.ss;
                canvas.drawText(charSequence3, 0, charSequence3.length(), f2 + this.paint.measureText(PREFIX + ((Object) this.hh) + HH + ((Object) this.mm) + MM), f3, this.paint);
                this.paint.setColor(-16777216);
                canvas.drawText(SS, 0, 1, f2 + this.paint.measureText(PREFIX + ((Object) this.hh) + HH + ((Object) this.mm) + MM + ((Object) this.ss)), f3, (Paint) this.paint);
            } else if (i2 == 1) {
                try {
                    this.paint.setColor(this.text_color);
                    CharSequence charSequence4 = this.text;
                    canvas.drawText(charSequence4, 0, charSequence4.length(), f2, f3, this.paint);
                } catch (Exception e2) {
                    if (UnLog.E) {
                        UnLog.e(TAG, e2.getMessage());
                    }
                }
            }
        } catch (Exception e3) {
            if (UnLog.E) {
                UnLog.e(TAG, e3.getMessage());
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public int getTextColor() {
        return this.text_color;
    }

    public float getTextSize() {
        return this.text_size;
    }

    public int getType() {
        return this.type;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setHour(CharSequence charSequence) {
        this.hh = charSequence;
    }

    public void setMinute(CharSequence charSequence) {
        this.mm = charSequence;
    }

    public void setSecond(CharSequence charSequence) {
        this.ss = charSequence;
    }

    public void setText(CharSequence charSequence) {
        this.text = charSequence;
    }

    public void setTextColor(int i2) {
        this.text_color = i2;
    }

    public void setTextSize(float f2) {
        this.text_size = f2;
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setTextSize(f2);
        }
    }

    public void setType(int i2) {
        this.type = i2;
    }

    public CountdownDrawable(Context context, CharSequence charSequence) {
        this(context);
        this.text = charSequence;
    }
}
