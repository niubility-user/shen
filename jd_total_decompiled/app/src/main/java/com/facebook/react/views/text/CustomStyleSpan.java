package com.facebook.react.views.text;

import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class CustomStyleSpan extends MetricAffectingSpan implements ReactSpan {
    private final AssetManager mAssetManager;
    @Nullable
    private final String mFontFamily;
    private final int mStyle;
    private final int mWeight;

    public CustomStyleSpan(int i2, int i3, @Nullable String str, AssetManager assetManager) {
        this.mStyle = i2;
        this.mWeight = i3;
        this.mFontFamily = str;
        this.mAssetManager = assetManager;
    }

    private static void apply(Paint paint, int i2, int i3, @Nullable String str, AssetManager assetManager) {
        Typeface typeface = paint.getTypeface();
        int i4 = 0;
        int style = typeface == null ? 0 : typeface.getStyle();
        if (i3 == 1 || ((style & 1) != 0 && i3 == -1)) {
            i4 = 1;
        }
        if (i2 == 2 || ((2 & style) != 0 && i2 == -1)) {
            i4 |= 2;
        }
        if (str != null) {
            typeface = ReactFontManager.getInstance().getTypeface(str, i4, assetManager);
        } else if (typeface != null) {
            typeface = Typeface.create(typeface, i4);
        }
        if (typeface != null) {
            paint.setTypeface(typeface);
        } else {
            paint.setTypeface(Typeface.defaultFromStyle(i4));
        }
        paint.setSubpixelText(true);
    }

    @Nullable
    public String getFontFamily() {
        return this.mFontFamily;
    }

    public int getStyle() {
        int i2 = this.mStyle;
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    public int getWeight() {
        int i2 = this.mWeight;
        if (i2 == -1) {
            return 0;
        }
        return i2;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint textPaint) {
        apply(textPaint, this.mStyle, this.mWeight, this.mFontFamily, this.mAssetManager);
    }
}
