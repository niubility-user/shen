package com.facebook.react.views.text;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.PixelUtil;

/* loaded from: classes12.dex */
public class TextAttributes {
    public static final float DEFAULT_MAX_FONT_SIZE_MULTIPLIER = 0.0f;
    private boolean mAllowFontScaling = true;
    private float mFontSize = Float.NaN;
    private float mLineHeight = Float.NaN;
    private float mLetterSpacing = Float.NaN;
    private float mMaxFontSizeMultiplier = Float.NaN;
    private float mHeightOfTallestInlineImage = Float.NaN;
    private TextTransform mTextTransform = TextTransform.UNSET;
    private boolean mUseLineSpacingFromFallbacks = true;

    public TextAttributes applyChild(TextAttributes textAttributes) {
        TextAttributes textAttributes2 = new TextAttributes();
        textAttributes2.mAllowFontScaling = this.mAllowFontScaling;
        textAttributes2.mFontSize = !Float.isNaN(textAttributes.mFontSize) ? textAttributes.mFontSize : this.mFontSize;
        textAttributes2.mLineHeight = !Float.isNaN(textAttributes.mLineHeight) ? textAttributes.mLineHeight : this.mLineHeight;
        textAttributes2.mLetterSpacing = !Float.isNaN(textAttributes.mLetterSpacing) ? textAttributes.mLetterSpacing : this.mLetterSpacing;
        textAttributes2.mMaxFontSizeMultiplier = !Float.isNaN(textAttributes.mMaxFontSizeMultiplier) ? textAttributes.mMaxFontSizeMultiplier : this.mMaxFontSizeMultiplier;
        textAttributes2.mHeightOfTallestInlineImage = !Float.isNaN(textAttributes.mHeightOfTallestInlineImage) ? textAttributes.mHeightOfTallestInlineImage : this.mHeightOfTallestInlineImage;
        TextTransform textTransform = textAttributes.mTextTransform;
        if (textTransform == TextTransform.UNSET) {
            textTransform = this.mTextTransform;
        }
        textAttributes2.mTextTransform = textTransform;
        return textAttributes2;
    }

    public boolean getAllowFontScaling() {
        return this.mAllowFontScaling;
    }

    public int getEffectiveFontSize() {
        double ceil;
        float f2 = !Float.isNaN(this.mFontSize) ? this.mFontSize : 14.0f;
        if (this.mAllowFontScaling) {
            ceil = Math.ceil(PixelUtil.toPixelFromSP(f2, getEffectiveMaxFontSizeMultiplier()));
        } else {
            ceil = Math.ceil(PixelUtil.toPixelFromDIP(f2));
        }
        return (int) ceil;
    }

    public float getEffectiveLetterSpacing() {
        float pixelFromDIP;
        if (Float.isNaN(this.mLetterSpacing)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLetterSpacing, getEffectiveMaxFontSizeMultiplier());
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLetterSpacing);
        }
        return pixelFromDIP / getEffectiveFontSize();
    }

    public float getEffectiveLineHeight() {
        float pixelFromDIP;
        if (Float.isNaN(this.mLineHeight)) {
            return Float.NaN;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLineHeight, getEffectiveMaxFontSizeMultiplier());
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLineHeight);
        }
        return !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > pixelFromDIP ? 1 : (this.mHeightOfTallestInlineImage == pixelFromDIP ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : pixelFromDIP;
    }

    public float getEffectiveMaxFontSizeMultiplier() {
        if (Float.isNaN(this.mMaxFontSizeMultiplier)) {
            return 0.0f;
        }
        return this.mMaxFontSizeMultiplier;
    }

    public float getFontSize() {
        return this.mFontSize;
    }

    public float getHeightOfTallestInlineImage() {
        return this.mHeightOfTallestInlineImage;
    }

    public float getLetterSpacing() {
        return this.mLetterSpacing;
    }

    public float getLineHeight() {
        return this.mLineHeight;
    }

    public float getMaxFontSizeMultiplier() {
        return this.mMaxFontSizeMultiplier;
    }

    public TextTransform getTextTransform() {
        return this.mTextTransform;
    }

    public boolean isUseLineSpacingFromFallbacks() {
        return this.mUseLineSpacingFromFallbacks;
    }

    public void setAllowFontScaling(boolean z) {
        this.mAllowFontScaling = z;
    }

    public void setFontSize(float f2) {
        this.mFontSize = f2;
    }

    public void setHeightOfTallestInlineImage(float f2) {
        this.mHeightOfTallestInlineImage = f2;
    }

    public void setLetterSpacing(float f2) {
        this.mLetterSpacing = f2;
    }

    public void setLineHeight(float f2) {
        this.mLineHeight = f2;
    }

    public void setMaxFontSizeMultiplier(float f2) {
        if (f2 != 0.0f && f2 < 1.0f) {
            throw new JSApplicationIllegalArgumentException("maxFontSizeMultiplier must be NaN, 0, or >= 1");
        }
        this.mMaxFontSizeMultiplier = f2;
    }

    public void setTextTransform(TextTransform textTransform) {
        this.mTextTransform = textTransform;
    }

    public void setUseLineSpacingFromFallbacks(boolean z) {
        this.mUseLineSpacingFromFallbacks = z;
    }

    public String toString() {
        return "TextAttributes {\n  getAllowFontScaling(): " + getAllowFontScaling() + "\n  getFontSize(): " + getFontSize() + "\n  getEffectiveFontSize(): " + getEffectiveFontSize() + "\n  getHeightOfTallestInlineImage(): " + getHeightOfTallestInlineImage() + "\n  getLetterSpacing(): " + getLetterSpacing() + "\n  getEffectiveLetterSpacing(): " + getEffectiveLetterSpacing() + "\n  getLineHeight(): " + getLineHeight() + "\n  getEffectiveLineHeight(): " + getEffectiveLineHeight() + "\n  getTextTransform(): " + getTextTransform() + "\n  getMaxFontSizeMultiplier(): " + getMaxFontSizeMultiplier() + "\n  getEffectiveMaxFontSizeMultiplier(): " + getEffectiveMaxFontSizeMultiplier() + "\n}";
    }
}
