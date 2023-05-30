package com.jingdong.common.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.TypedValue;
import com.jd.lib.un.utils.config.UnDeviceInfo;

/* loaded from: classes6.dex */
public class TextDrawable extends Drawable {
    private static final int MONOSPACE = 3;
    private static final int SANS = 1;
    private static final int SERIF = 2;
    private Resources mResources;
    private ColorStateList mTextColors;
    private StaticLayout mTextLayout;
    private TextPaint mTextPaint;
    private Path mTextPath;
    private static final int[] THEME_ATTRIBUTES = {16842804};
    private static final int[] APPEARANCE_ATTRIBUTES = {16842901, 16842902, 16842903, 16842904};
    private Layout.Alignment mTextAlignment = Layout.Alignment.ALIGN_NORMAL;
    private CharSequence mText = "";
    private Rect mTextBounds = new Rect();

    public TextDrawable(Context context) {
        this.mResources = context.getResources();
        TextPaint textPaint = new TextPaint(1);
        this.mTextPaint = textPaint;
        textPaint.density = UnDeviceInfo.getDensity();
        this.mTextPaint.setDither(true);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(THEME_ATTRIBUTES);
        int i2 = -1;
        int resourceId = obtainStyledAttributes.getResourceId(0, -1);
        obtainStyledAttributes.recycle();
        Typeface typeface = null;
        TypedArray obtainStyledAttributes2 = resourceId != -1 ? context.obtainStyledAttributes(resourceId, APPEARANCE_ATTRIBUTES) : null;
        ColorStateList colorStateList = null;
        int i3 = -1;
        int i4 = 15;
        if (obtainStyledAttributes2 != null) {
            for (int i5 = 0; i5 < obtainStyledAttributes2.getIndexCount(); i5++) {
                int index = obtainStyledAttributes2.getIndex(i5);
                if (index == 0) {
                    i4 = obtainStyledAttributes.getDimensionPixelSize(index, i4);
                } else if (index == 1) {
                    i2 = obtainStyledAttributes.getInt(index, i2);
                } else if (index == 2) {
                    i3 = obtainStyledAttributes.getInt(index, i3);
                } else if (index == 3) {
                    colorStateList = obtainStyledAttributes.getColorStateList(index);
                }
            }
            obtainStyledAttributes2.recycle();
        }
        setTextColor(colorStateList == null ? ColorStateList.valueOf(-16777216) : colorStateList);
        setRawTextSize(i4);
        if (i2 == 1) {
            typeface = Typeface.SANS_SERIF;
        } else if (i2 == 2) {
            typeface = Typeface.SERIF;
        } else if (i2 == 3) {
            typeface = Typeface.MONOSPACE;
        }
        setTypeface(typeface, i3);
    }

    private void measureContent() {
        if (this.mTextPath != null) {
            this.mTextLayout = null;
            this.mTextBounds.setEmpty();
        } else {
            StaticLayout staticLayout = new StaticLayout(this.mText, this.mTextPaint, (int) Math.ceil(Layout.getDesiredWidth(this.mText, this.mTextPaint)), this.mTextAlignment, 1.0f, 0.0f, false);
            this.mTextLayout = staticLayout;
            this.mTextBounds.set(0, 0, staticLayout.getWidth(), this.mTextLayout.getHeight());
        }
        invalidateSelf();
    }

    private void setRawTextSize(float f2) {
        if (f2 != this.mTextPaint.getTextSize()) {
            this.mTextPaint.setTextSize(f2);
            measureContent();
        }
    }

    private boolean updateTextColors(int[] iArr) {
        int colorForState = this.mTextColors.getColorForState(iArr, -1);
        if (this.mTextPaint.getColor() != colorForState) {
            this.mTextPaint.setColor(colorForState);
            return true;
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        int save = canvas.save();
        canvas.translate(bounds.left, bounds.top);
        if (this.mTextPath == null) {
            this.mTextLayout.draw(canvas);
        } else {
            canvas.drawTextOnPath(this.mText.toString(), this.mTextPath, 0.0f, 0.0f, this.mTextPaint);
        }
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.mTextBounds.isEmpty()) {
            return -1;
        }
        Rect rect = this.mTextBounds;
        return rect.bottom - rect.top;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.mTextBounds.isEmpty()) {
            return -1;
        }
        Rect rect = this.mTextBounds;
        return rect.right - rect.left;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.mTextPaint.getAlpha();
    }

    public CharSequence getText() {
        return this.mText;
    }

    public Layout.Alignment getTextAlign() {
        return this.mTextAlignment;
    }

    public float getTextScaleX() {
        return this.mTextPaint.getTextScaleX();
    }

    public float getTextSize() {
        return this.mTextPaint.getTextSize();
    }

    public Typeface getTypeface() {
        return this.mTextPaint.getTypeface();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mTextColors.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.mTextBounds.set(rect);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        return updateTextColors(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (this.mTextPaint.getAlpha() != i2) {
            this.mTextPaint.setAlpha(i2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mTextPaint.getColorFilter() != colorFilter) {
            this.mTextPaint.setColorFilter(colorFilter);
        }
    }

    public void setText(CharSequence charSequence) {
        if (charSequence == null) {
            charSequence = "";
        }
        this.mText = charSequence;
        measureContent();
    }

    public void setTextAlign(Layout.Alignment alignment) {
        if (this.mTextAlignment != alignment) {
            this.mTextAlignment = alignment;
            measureContent();
        }
    }

    public void setTextColor(int i2) {
        setTextColor(ColorStateList.valueOf(i2));
    }

    public void setTextPath(Path path) {
        if (this.mTextPath != path) {
            this.mTextPath = path;
            measureContent();
        }
    }

    public void setTextScaleX(float f2) {
        if (f2 != this.mTextPaint.getTextScaleX()) {
            this.mTextPaint.setTextScaleX(f2);
            measureContent();
        }
    }

    public void setTextSize(float f2) {
        setTextSize(2, f2);
    }

    public void setTypeface(Typeface typeface) {
        if (this.mTextPaint.getTypeface() != typeface) {
            this.mTextPaint.setTypeface(typeface);
            measureContent();
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.mTextColors = colorStateList;
        updateTextColors(getState());
    }

    public void setTextSize(int i2, float f2) {
        setRawTextSize(TypedValue.applyDimension(i2, f2, this.mResources.getDisplayMetrics()));
    }

    public void setTypeface(Typeface typeface, int i2) {
        Typeface create;
        if (i2 > 0) {
            if (typeface == null) {
                create = Typeface.defaultFromStyle(i2);
            } else {
                create = Typeface.create(typeface, i2);
            }
            setTypeface(create);
            int style = ((create != null ? create.getStyle() : 0) ^ (-1)) & i2;
            this.mTextPaint.setFakeBoldText((style & 1) != 0);
            this.mTextPaint.setTextSkewX((style & 2) != 0 ? -0.25f : 0.0f);
            return;
        }
        this.mTextPaint.setFakeBoldText(false);
        this.mTextPaint.setTextSkewX(0.0f);
        setTypeface(typeface);
    }
}
