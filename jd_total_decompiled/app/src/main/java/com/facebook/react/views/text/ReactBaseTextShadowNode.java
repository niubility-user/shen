package com.facebook.react.views.text;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import androidx.room.FtsOptions;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactShadowNodeImpl;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.yoga.YogaDirection;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@TargetApi(23)
/* loaded from: classes12.dex */
public abstract class ReactBaseTextShadowNode extends LayoutShadowNode {
    public static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_IMAGE_PLACEHOLDER = "I";
    public static final String PROP_SHADOW_COLOR = "textShadowColor";
    public static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    public static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    public static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    public static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    public static final String PROP_TEXT_TRANSFORM = "textTransform";
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
    protected TextAttributes mTextAttributes;
    protected int mTextBreakStrategy;
    protected int mTextShadowColor;
    protected float mTextShadowOffsetDx;
    protected float mTextShadowOffsetDy;
    protected float mTextShadowRadius;
    protected TextTransform mTextTransform;
    protected boolean mIsColorSet = false;
    protected boolean mIsBackgroundColorSet = false;
    protected int mNumberOfLines = -1;
    protected int mTextAlign = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class SetSpanOperation {
        protected int end;
        protected int start;
        protected ReactSpan what;

        SetSpanOperation(int i2, int i3, ReactSpan reactSpan) {
            this.start = i2;
            this.end = i3;
            this.what = reactSpan;
        }

        public void execute(SpannableStringBuilder spannableStringBuilder, int i2) {
            int i3 = this.start;
            spannableStringBuilder.setSpan(this.what, i3, this.end, ((i2 << 16) & 16711680) | ((i3 == 0 ? 18 : 34) & (-16711681)));
        }
    }

    public ReactBaseTextShadowNode() {
        this.mTextBreakStrategy = Build.VERSION.SDK_INT < 23 ? 0 : 1;
        this.mJustificationMode = 0;
        this.mTextTransform = TextTransform.UNSET;
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        this.mTextShadowRadius = 0.0f;
        this.mTextShadowColor = DEFAULT_TEXT_SHADOW_COLOR;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        this.mIncludeFontPadding = true;
        this.mFontStyle = -1;
        this.mFontWeight = -1;
        this.mFontFamily = null;
        this.mContainsImages = false;
        this.mHeightOfTallestInlineImage = Float.NaN;
        this.mTextAttributes = new TextAttributes();
    }

    private static boolean buildSpannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, SpannableStringBuilder spannableStringBuilder, List<SetSpanOperation> list, TextAttributes textAttributes, int i2) {
        TextAttributes textAttributes2;
        if (textAttributes != null) {
            textAttributes2 = textAttributes.applyChild(reactBaseTextShadowNode.mTextAttributes);
        } else {
            textAttributes2 = reactBaseTextShadowNode.mTextAttributes;
        }
        int childCount = reactBaseTextShadowNode.getChildCount();
        boolean z = false;
        for (int i3 = 0; i3 < childCount; i3++) {
            ReactShadowNodeImpl childAt = reactBaseTextShadowNode.getChildAt(i3);
            if (childAt instanceof ReactRawTextShadowNode) {
                ReactRawTextShadowNode reactRawTextShadowNode = (ReactRawTextShadowNode) childAt;
                if (reactRawTextShadowNode.getText() != null) {
                    spannableStringBuilder.append((CharSequence) TextTransform.apply(reactRawTextShadowNode.getText(), textAttributes2.getTextTransform()));
                }
            } else if (childAt instanceof ReactBaseTextShadowNode) {
                z = buildSpannedFromShadowNode((ReactBaseTextShadowNode) childAt, spannableStringBuilder, list, textAttributes2, spannableStringBuilder.length());
            } else if (childAt instanceof ReactTextInlineImageShadowNode) {
                spannableStringBuilder.append(INLINE_IMAGE_PLACEHOLDER);
                list.add(new SetSpanOperation(spannableStringBuilder.length() - 1, spannableStringBuilder.length(), ((ReactTextInlineImageShadowNode) childAt).buildInlineImageSpan()));
            } else {
                throw new IllegalViewOperationException("Unexpected view type nested under text node: " + childAt.getClass());
            }
            childAt.markUpdateSeen();
        }
        int length = spannableStringBuilder.length();
        if (length >= i2) {
            if (reactBaseTextShadowNode.mIsColorSet) {
                list.add(new SetSpanOperation(i2, length, new ReactForegroundColorSpan(reactBaseTextShadowNode.mColor)));
            }
            if (reactBaseTextShadowNode.mIsBackgroundColorSet) {
                list.add(new SetSpanOperation(i2, length, new ReactBackgroundColorSpan(reactBaseTextShadowNode.mBackgroundColor)));
            }
            if (Build.VERSION.SDK_INT >= 21) {
                float effectiveLetterSpacing = textAttributes2.getEffectiveLetterSpacing();
                if (!Float.isNaN(effectiveLetterSpacing) && (textAttributes == null || textAttributes.getEffectiveLetterSpacing() != effectiveLetterSpacing)) {
                    list.add(new SetSpanOperation(i2, length, new CustomLetterSpacingSpan(effectiveLetterSpacing)));
                }
            }
            int effectiveFontSize = textAttributes2.getEffectiveFontSize();
            if (textAttributes == null || textAttributes.getEffectiveFontSize() != effectiveFontSize) {
                list.add(new SetSpanOperation(i2, length, new ReactAbsoluteSizeSpan(effectiveFontSize)));
            }
            if (reactBaseTextShadowNode.mFontStyle != -1 || reactBaseTextShadowNode.mFontWeight != -1 || reactBaseTextShadowNode.mFontFamily != null) {
                list.add(new SetSpanOperation(i2, length, new CustomStyleSpan(reactBaseTextShadowNode.mFontStyle, reactBaseTextShadowNode.mFontWeight, reactBaseTextShadowNode.mFontFamily, reactBaseTextShadowNode.getThemedContext().getAssets())));
            }
            if (reactBaseTextShadowNode.mIsUnderlineTextDecorationSet) {
                list.add(new SetSpanOperation(i2, length, new ReactUnderlineSpan()));
            }
            if (reactBaseTextShadowNode.mIsLineThroughTextDecorationSet) {
                list.add(new SetSpanOperation(i2, length, new ReactStrikethroughSpan()));
            }
            if ((reactBaseTextShadowNode.mTextShadowOffsetDx != 0.0f || reactBaseTextShadowNode.mTextShadowOffsetDy != 0.0f || reactBaseTextShadowNode.mTextShadowRadius != 0.0f) && Color.alpha(reactBaseTextShadowNode.mTextShadowColor) != 0) {
                list.add(new SetSpanOperation(i2, length, new ShadowStyleSpan(reactBaseTextShadowNode.mTextShadowOffsetDx, reactBaseTextShadowNode.mTextShadowOffsetDy, reactBaseTextShadowNode.mTextShadowRadius, reactBaseTextShadowNode.mTextShadowColor)));
            }
            float effectiveLineHeight = textAttributes2.getEffectiveLineHeight();
            if (!Float.isNaN(effectiveLineHeight) && (textAttributes == null || textAttributes.getEffectiveLineHeight() != effectiveLineHeight)) {
                list.add(new SetSpanOperation(i2, length, new CustomLineHeightSpan(effectiveLineHeight)));
                z = true;
            }
            list.add(new SetSpanOperation(i2, length, new ReactTagSpan(reactBaseTextShadowNode.getReactTag())));
        }
        return z;
    }

    private int getTextAlign() {
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

    private static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Spannable spannedFromShadowNode(ReactBaseTextShadowNode reactBaseTextShadowNode, String str) {
        double ceil;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        ArrayList<SetSpanOperation> arrayList = new ArrayList();
        if (str != null) {
            spannableStringBuilder.append((CharSequence) TextTransform.apply(str, reactBaseTextShadowNode.mTextAttributes.getTextTransform()));
        }
        int i2 = 0;
        if (Float.isNaN(reactBaseTextShadowNode.mTextAttributes.getLineHeight()) && reactBaseTextShadowNode.mTextAttributes.isUseLineSpacingFromFallbacks()) {
            buildSpannedFromShadowNode(reactBaseTextShadowNode, spannableStringBuilder, arrayList, null, 0);
        } else if (!buildSpannedFromShadowNode(reactBaseTextShadowNode, spannableStringBuilder, arrayList, null, 0) && Build.VERSION.SDK_INT >= 28) {
            if (Float.isNaN(reactBaseTextShadowNode.mTextAttributes.getFontSize())) {
                if (reactBaseTextShadowNode.mTextAttributes.getAllowFontScaling()) {
                    ceil = Math.ceil(PixelUtil.toPixelFromSP(14.0f));
                } else {
                    ceil = Math.ceil(PixelUtil.toPixelFromDIP(14.0f));
                }
                arrayList.add(new SetSpanOperation(0, spannableStringBuilder.length(), new CustomLineHeightSpan(((int) ceil) * 1.15f)));
            } else {
                arrayList.add(new SetSpanOperation(0, spannableStringBuilder.length(), new CustomLineHeightSpan(PixelUtil.toPixelFromSP(reactBaseTextShadowNode.mTextAttributes.getFontSize() * 1.15f))));
            }
        }
        reactBaseTextShadowNode.mContainsImages = false;
        float f2 = Float.NaN;
        for (SetSpanOperation setSpanOperation : arrayList) {
            ReactSpan reactSpan = setSpanOperation.what;
            if (reactSpan instanceof TextInlineImageSpan) {
                int height = ((TextInlineImageSpan) reactSpan).getHeight();
                reactBaseTextShadowNode.mContainsImages = true;
                if (Float.isNaN(f2) || height > f2) {
                    f2 = height;
                }
            }
            setSpanOperation.execute(spannableStringBuilder, i2);
            i2++;
        }
        reactBaseTextShadowNode.mTextAttributes.setHeightOfTallestInlineImage(f2);
        return spannableStringBuilder;
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.ALLOW_FONT_SCALING)
    public void setAllowFontScaling(boolean z) {
        if (z != this.mTextAttributes.getAllowFontScaling()) {
            this.mTextAttributes.setAllowFontScaling(z);
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.BACKGROUND_COLOR)
    public void setBackgroundColor(Integer num) {
        if (isVirtualAnchor()) {
            return;
        }
        boolean z = num != null;
        this.mIsBackgroundColorSet = z;
        if (z) {
            this.mBackgroundColor = num.intValue();
        }
        markUpdated();
    }

    @ReactProp(name = "color")
    public void setColor(@Nullable Integer num) {
        boolean z = num != null;
        this.mIsColorSet = z;
        if (z) {
            this.mColor = num.intValue();
        }
        markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_FAMILY)
    public void setFontFamily(@Nullable String str) {
        this.mFontFamily = str;
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.FONT_SIZE)
    public void setFontSize(float f2) {
        this.mTextAttributes.setFontSize(f2);
        markUpdated();
    }

    @ReactProp(name = ViewProps.FONT_STYLE)
    public void setFontStyle(@Nullable String str) {
        int i2;
        if ("italic".equals(str)) {
            i2 = 2;
        } else {
            i2 = "normal".equals(str) ? 0 : -1;
        }
        if (i2 != this.mFontStyle) {
            this.mFontStyle = i2;
            markUpdated();
        }
    }

    @ReactProp(name = ViewProps.FONT_WEIGHT)
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
            markUpdated();
        }
    }

    @ReactProp(defaultBoolean = true, name = "includeFontPadding")
    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.LETTER_SPACING)
    public void setLetterSpacing(float f2) {
        this.mTextAttributes.setLetterSpacing(f2);
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.LINE_HEIGHT)
    public void setLineHeight(float f2) {
        this.mTextAttributes.setLineHeight(f2);
        markUpdated();
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.MAX_FONT_SIZE_MULTIPLIER)
    public void setMaxFontSizeMultiplier(float f2) {
        if (f2 != this.mTextAttributes.getMaxFontSizeMultiplier()) {
            this.mTextAttributes.setMaxFontSizeMultiplier(f2);
            markUpdated();
        }
    }

    @ReactProp(defaultInt = -1, name = ViewProps.NUMBER_OF_LINES)
    public void setNumberOfLines(int i2) {
        if (i2 == 0) {
            i2 = -1;
        }
        this.mNumberOfLines = i2;
        markUpdated();
    }

    @ReactProp(name = ViewProps.TEXT_ALIGN)
    public void setTextAlign(@Nullable String str) {
        if ("justify".equals(str)) {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 1;
            }
            this.mTextAlign = 3;
        } else {
            if (Build.VERSION.SDK_INT >= 26) {
                this.mJustificationMode = 0;
            }
            if (str != null && !"auto".equals(str)) {
                if ("left".equals(str)) {
                    this.mTextAlign = 3;
                } else if ("right".equals(str)) {
                    this.mTextAlign = 5;
                } else if (DYConstants.DY_CENTER.equals(str)) {
                    this.mTextAlign = 1;
                } else {
                    throw new JSApplicationIllegalArgumentException("Invalid textAlign: " + str);
                }
            } else {
                this.mTextAlign = 0;
            }
        }
        markUpdated();
    }

    @ReactProp(name = ViewProps.TEXT_BREAK_STRATEGY)
    public void setTextBreakStrategy(@Nullable String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return;
        }
        if (str != null && !"highQuality".equals(str)) {
            if (FtsOptions.TOKENIZER_SIMPLE.equals(str)) {
                this.mTextBreakStrategy = 0;
            } else if ("balanced".equals(str)) {
                this.mTextBreakStrategy = 2;
            } else {
                throw new JSApplicationIllegalArgumentException("Invalid textBreakStrategy: " + str);
            }
        } else {
            this.mTextBreakStrategy = 1;
        }
        markUpdated();
    }

    @ReactProp(name = ViewProps.TEXT_DECORATION_LINE)
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
        markUpdated();
    }

    @ReactProp(customType = "Color", defaultInt = DEFAULT_TEXT_SHADOW_COLOR, name = PROP_SHADOW_COLOR)
    public void setTextShadowColor(int i2) {
        if (i2 != this.mTextShadowColor) {
            this.mTextShadowColor = i2;
            markUpdated();
        }
    }

    @ReactProp(name = PROP_SHADOW_OFFSET)
    public void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (readableMap.hasKey("height") && !readableMap.isNull("height")) {
                this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
            }
        }
        markUpdated();
    }

    @ReactProp(defaultInt = 1, name = PROP_SHADOW_RADIUS)
    public void setTextShadowRadius(float f2) {
        if (f2 != this.mTextShadowRadius) {
            this.mTextShadowRadius = f2;
            markUpdated();
        }
    }

    @ReactProp(name = PROP_TEXT_TRANSFORM)
    public void setTextTransform(@Nullable String str) {
        if (str == null) {
            this.mTextAttributes.setTextTransform(TextTransform.UNSET);
        } else if ("none".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.NONE);
        } else if ("uppercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.UPPERCASE);
        } else if ("lowercase".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.LOWERCASE);
        } else if ("capitalize".equals(str)) {
            this.mTextAttributes.setTextTransform(TextTransform.CAPITALIZE);
        } else {
            throw new JSApplicationIllegalArgumentException("Invalid textTransform: " + str);
        }
        markUpdated();
    }

    @ReactProp(defaultBoolean = false, name = ViewProps.LINESPACING_FROM_FALLBACK)
    public void setUseLineSpacingFromFallbacks(boolean z) {
        if (z != this.mTextAttributes.isUseLineSpacingFromFallbacks()) {
            this.mTextAttributes.setUseLineSpacingFromFallbacks(z);
            markUpdated();
        }
    }
}
