package com.jd.lib.productdetail.core.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

/* loaded from: classes15.dex */
public class DateDrawableTenth extends Drawable {
    private static final String PREFIX = ":";
    private int conner;
    private boolean isChangeSkin;
    private int mBoardWidth;
    private float mDiffHeight;
    private int mPreferPadding;
    private int mTotalWidth;
    private Typeface mTypeFace;
    private TextPaint paint;
    private String hh = "00";
    private String mm = "00";
    private String ss = "00";
    private int textColor = -16777216;
    private int prefixColor = -16777216;
    private int boardColor = -1;
    private int backgroundFillColor = -1;
    private int backgroundWidth = 0;
    private int backgroundHeight = 0;

    /* loaded from: classes15.dex */
    public static final class Builder {
        private int conner;
        private int mBoardWidth;
        private int preferPadding;
        private int textSize;
        private int textColor = -16777216;
        private int boardColor = -1;
        private int backgroundFillColor = -1;
        private int backgroundWidth = 0;
        private int backgroundHeight = 0;
        private boolean isChangeSkin = false;

        private Builder() {
        }

        public static Builder aDateDrawableTenth() {
            return new Builder();
        }

        public Builder backgroundFillColor(int i2) {
            this.backgroundFillColor = i2;
            return this;
        }

        public Builder backgroundHeight(int i2) {
            this.backgroundHeight = i2;
            return this;
        }

        public Builder backgroundWidth(int i2) {
            this.backgroundWidth = i2;
            return this;
        }

        public Builder boardColor(int i2) {
            this.boardColor = i2;
            return this;
        }

        public Builder boardWidth(int i2) {
            this.mBoardWidth = i2;
            return this;
        }

        public DateDrawableTenth build() {
            DateDrawableTenth dateDrawableTenth = new DateDrawableTenth(false);
            dateDrawableTenth.setTextColor(this.textColor);
            dateDrawableTenth.setBoardColor(this.boardColor);
            dateDrawableTenth.setBackgroundFillColor(this.backgroundFillColor);
            dateDrawableTenth.setBackgroundWidth(this.backgroundWidth);
            dateDrawableTenth.setBackgroundHeight(this.backgroundHeight);
            dateDrawableTenth.setConner(this.conner);
            dateDrawableTenth.setTextSize(this.textSize);
            dateDrawableTenth.setBoardWidth(this.mBoardWidth);
            dateDrawableTenth.isChangeSkin = this.isChangeSkin;
            dateDrawableTenth.setmPreferPadding(this.preferPadding);
            return dateDrawableTenth;
        }

        public Builder conner(int i2) {
            this.conner = i2;
            return this;
        }

        public Builder isChangeSkin(boolean z) {
            this.isChangeSkin = z;
            return this;
        }

        public Builder preferPadding(int i2) {
            this.preferPadding = i2;
            return this;
        }

        public Builder textColor(int i2) {
            this.textColor = i2;
            return this;
        }

        public Builder textSize(int i2) {
            this.textSize = i2;
            return this;
        }
    }

    public DateDrawableTenth(boolean z) {
        this.isChangeSkin = false;
        this.isChangeSkin = z;
        TextPaint textPaint = new TextPaint(1);
        this.paint = textPaint;
        textPaint.setAntiAlias(true);
        this.paint.setTextSize(14.0f);
        this.paint.setTypeface(Typeface.DEFAULT);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setStrokeWidth(this.mBoardWidth);
        calcDiffHeight();
        calcTotalWidth();
    }

    private void calcDiffHeight() {
        this.mDiffHeight = getWordWidth("00").height() - getWordWidth(PREFIX).height();
    }

    private void calcTotalWidth() {
        this.mTotalWidth = (int) ((this.backgroundWidth * 3) + (this.paint.measureText(PREFIX) * 2.0f) + (this.mPreferPadding * 4));
    }

    private int drawPrefxi(Canvas canvas, int i2, float f2) {
        int i3 = i2 + this.mPreferPadding;
        this.paint.setColor(this.prefixColor);
        canvas.drawText(PREFIX, i3, f2 - (this.mDiffHeight / 2.0f), this.paint);
        return (int) (i3 + this.mPreferPadding + this.paint.measureText(PREFIX));
    }

    private int drawSub(Canvas canvas, int i2, String str, float f2, int i3) {
        RectF bgRect = getBgRect(i2);
        this.paint.setStrokeWidth(this.mBoardWidth);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.backgroundFillColor);
        int i4 = this.mBoardWidth;
        bgRect.inset((i4 * 1.0f) / 2.0f, (i4 * 1.0f) / 2.0f);
        int i5 = this.conner;
        canvas.drawRoundRect(bgRect, i5, i5, this.paint);
        this.paint.setColor(this.boardColor);
        this.paint.setStyle(this.isChangeSkin ? Paint.Style.FILL_AND_STROKE : Paint.Style.STROKE);
        int i6 = this.conner;
        canvas.drawRoundRect(bgRect, i6, i6, this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setColor(this.textColor);
        canvas.drawText(str, i2 + ((((this.backgroundWidth - i3) - this.mBoardWidth) * 1.0f) / 2.0f), f2, this.paint);
        return i2 + this.backgroundWidth;
    }

    private RectF getBgRect(int i2) {
        RectF rectF = new RectF(0.0f, 0.0f, this.backgroundWidth, this.backgroundHeight);
        rectF.offset(i2, 0.0f);
        return rectF;
    }

    private Rect getWordWidth(String str) {
        Rect rect = new Rect();
        this.paint.getTextBounds(str, 0, str.length(), rect);
        return rect;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBoardWidth(int i2) {
        this.mBoardWidth = i2;
        this.paint.setStrokeWidth(i2);
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
            Rect rect = new Rect();
            this.paint.getTextBounds("00", 0, 2, rect);
            float height = ((bounds.height() + rect.height()) * 1.0f) / 2.0f;
            int width = rect.width();
            drawSub(canvas, drawPrefxi(canvas, drawSub(canvas, drawPrefxi(canvas, drawSub(canvas, 0, this.hh, height, width), height), this.mm, height, width), height), this.ss, height, width);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.backgroundHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mTotalWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -1;
    }

    public int getTextColor() {
        return this.textColor;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
    }

    public void setBackgroundFillColor(int i2) {
        this.backgroundFillColor = i2;
    }

    public void setBackgroundHeight(int i2) {
        this.backgroundHeight = i2;
    }

    public void setBackgroundWidth(int i2) {
        this.backgroundWidth = i2;
    }

    public void setBoardColor(int i2) {
        this.boardColor = i2;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public void setConner(int i2) {
        this.conner = i2;
    }

    public void setHour(String str) {
        this.hh = str;
    }

    public void setMinute(String str) {
        this.mm = str;
    }

    public void setPointColor(int i2) {
        this.prefixColor = i2;
    }

    public void setSecond(String str) {
        this.ss = str;
    }

    public void setTextColor(int i2) {
        this.textColor = i2;
    }

    public void setTextSize(float f2) {
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setTextSize(f2);
            calcDiffHeight();
        }
    }

    public void setTypeFace(Typeface typeface) {
        this.mTypeFace = typeface;
        TextPaint textPaint = this.paint;
        if (textPaint != null) {
            textPaint.setTypeface(typeface);
        }
    }

    public void setmPreferPadding(int i2) {
        this.mPreferPadding = i2;
        calcTotalWidth();
    }
}
