package com.facebook.react.views.text;

import android.os.Build;
import androidx.room.FtsOptions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga.YogaDirection;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class TextAttributeProps {
    private static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_IMAGE_PLACEHOLDER = "I";
    private static final String PROP_SHADOW_COLOR = "textShadowColor";
    private static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    private static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    private static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    private static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    private static final String PROP_TEXT_TRANSFORM = "textTransform";
    public static final int UNSET = -1;
    protected int mBackgroundColor;
    protected int mColor;
    protected boolean mContainsImages;
    @Nullable
    protected String mFontFamily;
    protected int mFontStyle;
    protected int mFontWeight;
    protected float mHeightOfTallestInlineImage;
    protected boolean mIncludeFontPadding;
    protected boolean mIsLineThroughTextDecorationSet;
    protected boolean mIsUnderlineTextDecorationSet;
    protected int mJustificationMode;
    private final ReactStylesDiffMap mProps;
    protected int mTextBreakStrategy;
    protected int mTextShadowColor;
    protected float mTextShadowOffsetDx;
    protected float mTextShadowOffsetDy;
    protected float mTextShadowRadius;
    protected TextTransform mTextTransform;
    protected boolean mUseLineSpacingFromFallbacks;
    protected float mLineHeight = Float.NaN;
    protected float mLetterSpacing = Float.NaN;
    protected boolean mIsColorSet = false;
    protected boolean mAllowFontScaling = true;
    protected boolean mIsBackgroundColorSet = false;
    protected int mNumberOfLines = -1;
    protected int mFontSize = -1;
    protected float mFontSizeInput = -1.0f;
    protected float mLineHeightInput = -1.0f;
    protected float mLetterSpacingInput = Float.NaN;
    protected int mTextAlign = 0;

    public TextAttributeProps(ReactStylesDiffMap reactStylesDiffMap) {
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        this.mJustificationMode = 0;
        this.mTextTransform = TextTransform.UNSET;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 1.0f;
        this.mTextShadowColor = 1426063360;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mIncludeFontPadding = true;
        this.mUseLineSpacingFromFallbacks = true;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mContainsImages = false;
        this.mHeightOfTallestInlineImage = Float.NaN;
        this.mProps = reactStylesDiffMap;
        setNumberOfLines(getIntProp(ViewProps.NUMBER_OF_LINES, -1));
        setLineHeight(getFloatProp(ViewProps.LINE_HEIGHT, -1.0f));
        setLetterSpacing(getFloatProp(ViewProps.LETTER_SPACING, Float.NaN));
        setAllowFontScaling(getBooleanProp(ViewProps.ALLOW_FONT_SCALING, true));
        setTextAlign(getStringProp(ViewProps.TEXT_ALIGN));
        setFontSize(getFloatProp(ViewProps.FONT_SIZE, -1.0f));
        setColor(reactStylesDiffMap.hasKey("color") ? Integer.valueOf(reactStylesDiffMap.getInt("color", 0)) : null);
        setColor(reactStylesDiffMap.hasKey("foregroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("foregroundColor", 0)) : null);
        setBackgroundColor(reactStylesDiffMap.hasKey(ViewProps.BACKGROUND_COLOR) ? Integer.valueOf(reactStylesDiffMap.getInt(ViewProps.BACKGROUND_COLOR, 0)) : null);
        setFontFamily(getStringProp(ViewProps.FONT_FAMILY));
        setFontWeight(getStringProp(ViewProps.FONT_WEIGHT));
        setFontStyle(getStringProp(ViewProps.FONT_STYLE));
        setIncludeFontPadding(getBooleanProp("includeFontPadding", true));
        setUseLineSpacingFromFallbacks(getBooleanProp(ViewProps.LINESPACING_FROM_FALLBACK, true));
        setTextDecorationLine(getStringProp(ViewProps.TEXT_DECORATION_LINE));
        setTextBreakStrategy(getStringProp(ViewProps.TEXT_BREAK_STRATEGY));
        setTextShadowOffset(reactStylesDiffMap.hasKey("textShadowOffset") ? reactStylesDiffMap.getMap("textShadowOffset") : null);
        setTextShadowRadius(getIntProp("textShadowRadius", 1));
        setTextShadowColor(getIntProp("textShadowColor", 1426063360));
        setTextTransform(getStringProp("textTransform"));
    }

    private boolean getBooleanProp(String str, boolean z) {
        return this.mProps.hasKey(str) ? this.mProps.getBoolean(str, z) : z;
    }

    private float getFloatProp(String str, float f2) {
        return this.mProps.hasKey(str) ? this.mProps.getFloat(str, f2) : f2;
    }

    private int getIntProp(String str, int i2) {
        return this.mProps.hasKey(str) ? this.mProps.getInt(str, i2) : i2;
    }

    private YogaDirection getLayoutDirection() {
        return YogaDirection.LTR;
    }

    private float getPaddingProp(String str) {
        if (this.mProps.hasKey("padding")) {
            return PixelUtil.toPixelFromDIP(getFloatProp("padding", 0.0f));
        }
        return PixelUtil.toPixelFromDIP(getFloatProp(str, 0.0f));
    }

    private String getStringProp(String str) {
        if (this.mProps.hasKey(str)) {
            return this.mProps.getString(str);
        }
        return null;
    }

    private static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    public float getBottomPadding() {
        return getPaddingProp("paddingBottom");
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    public float getEndPadding() {
        return getPaddingProp("paddingEnd");
    }

    public float getLeftPadding() {
        return getPaddingProp("paddingLeft");
    }

    public float getRightPadding() {
        return getPaddingProp("paddingRight");
    }

    public float getStartPadding() {
        return getPaddingProp("paddingStart");
    }

    public int getTextAlign() {
        int i2 = this.mTextAlign;
        if (getLayoutDirection() == YogaDirection.RTL) {
            if (i2 == 5) {
                return 3;
            }
            if (i2 == 3) {
                return 5;
            }
            return i2;
        }
        return i2;
    }

    public float getTopPadding() {
        return getPaddingProp("paddingTop");
    }

    public void setAllowFontScaling(boolean z) {
        if (z != this.mAllowFontScaling) {
            this.mAllowFontScaling = z;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            setLetterSpacing(this.mLetterSpacingInput);
        }
    }

    public void setBackgroundColor(Integer num) {
        boolean z = num != null;
        this.mIsBackgroundColorSet = z;
        if (z) {
            this.mBackgroundColor = num.intValue();
        }
    }

    public void setColor(@Nullable Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
    }

    public void setFontFamily(@Nullable String str) {
        this.mFontFamily = str;
    }

    public void setFontSize(float f2) {
        double ceil;
        this.mFontSizeInput = f2;
        if (f2 != -1.0f) {
            if (this.mAllowFontScaling) {
                ceil = Math.ceil(PixelUtil.toPixelFromSP(f2));
            } else {
                ceil = Math.ceil(PixelUtil.toPixelFromDIP(f2));
            }
            f2 = (float) ceil;
        }
        this.mFontSize = (int) f2;
    }

    public void setFontStyle(@Nullable String str) {
        int i2;
        if ("italic".equals(str)) {
            i2 = 2;
        } else {
            i2 = "normal".equals(str) ? 0 : -1;
        }
        if (i2 != this.mFontStyle) {
            this.mFontStyle = i2;
        }
    }

    public void setFontWeight(@Nullable String str) {
        int i2 = -1;
        int parseNumericFontWeight = str != null ? parseNumericFontWeight(str) : -1;
        if (parseNumericFontWeight >= 500 || "bold".equals(str)) {
            i2 = 1;
        } else if ("normal".equals(str) || (parseNumericFontWeight != -1 && parseNumericFontWeight < 500)) {
            i2 = 0;
        }
        if (i2 != this.mFontWeight) {
            this.mFontWeight = i2;
        }
    }

    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    public void setLetterSpacing(float f2) {
        float pixelFromDIP;
        this.mLetterSpacingInput = f2;
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(f2);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(f2);
        }
        this.mLetterSpacing = pixelFromDIP;
    }

    public void setLineHeight(float f2) {
        float pixelFromDIP;
        this.mLineHeightInput = f2;
        if (f2 == -1.0f) {
            this.mLineHeight = Float.NaN;
            return;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(f2);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(f2);
        }
        this.mLineHeight = pixelFromDIP;
    }

    public void setNumberOfLines(int i2) {
        if (i2 == 0) {
            i2 = -1;
        }
        this.mNumberOfLines = i2;
    }

    public void setTextAlign(@Nullable String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 1;
            }
            this.mTextAlign = 3;
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mJustificationMode = 0;
        }
        if (str != null && !"auto".equals(str)) {
            if ("left".equals(str)) {
                this.mTextAlign = 3;
                return;
            } else if ("right".equals(str)) {
                this.mTextAlign = 5;
                return;
            } else if (DYConstants.DY_CENTER.equals(str)) {
                this.mTextAlign = 1;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
            }
        }
        this.mTextAlign = 0;
    }

    public void setTextBreakStrategy(@Nullable String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (str != null && !"highQuality".equals(str)) {
            if (FtsOptions.TOKENIZER_SIMPLE.equals(str)) {
                this.mTextBreakStrategy = 0;
                return;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
        }
        this.mTextBreakStrategy = 1;
    }

    public void setTextDecorationLine(@Nullable String str) {
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split(LangUtils.SINGLE_SPACE)) {
                if ("underline".equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("line-through".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
    }

    public void setTextShadowColor(int i2) {
        if (i2 != this.mTextShadowColor) {
            this.mTextShadowColor = i2;
        }
    }

    public void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (!readableMap.hasKey("height") || readableMap.isNull("height")) {
                return;
            }
            this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
        }
    }

    public void setTextShadowRadius(float f2) {
        if (f2 != this.mTextShadowRadius) {
            this.mTextShadowRadius = f2;
        }
    }

    public void setTextTransform(@Nullable String str) {
        if (str != null && !"none".equals(str)) {
            if ("uppercase".equals(str)) {
                this.mTextTransform = TextTransform.UPPERCASE;
                return;
            } else if ("lowercase".equals(str)) {
                this.mTextTransform = TextTransform.LOWERCASE;
                return;
            } else if ("capitalize".equals(str)) {
                this.mTextTransform = TextTransform.CAPITALIZE;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
            }
        }
        this.mTextTransform = TextTransform.NONE;
    }

    public void setUseLineSpacingFromFallbacks(boolean z) {
        this.mUseLineSpacingFromFallbacks = z;
    }
}
