package com.facebook.react.uimanager;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaWrap;
import com.jd.dynamic.DYConstants;
import javax.annotation.Nullable;

/* loaded from: classes12.dex */
public class LayoutShadowNode extends ReactShadowNodeImpl {
    private final MutableYogaValue mTempYogaValue = new MutableYogaValue((AnonymousClass1) null);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.facebook.react.uimanager.LayoutShadowNode$1 */
    /* loaded from: classes12.dex */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit;

        static {
            int[] iArr = new int[YogaUnit.values().length];
            $SwitchMap$com$facebook$yoga$YogaUnit = iArr;
            try {
                iArr[YogaUnit.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.UNDEFINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class MutableYogaValue {
        YogaUnit unit;
        float value;

        /* synthetic */ MutableYogaValue(AnonymousClass1 anonymousClass1) {
            this();
        }

        void setFromDynamic(Dynamic dynamic) {
            if (dynamic.isNull()) {
                this.unit = YogaUnit.UNDEFINED;
                this.value = Float.NaN;
            } else if (dynamic.getType() == ReadableType.String) {
                String asString = dynamic.asString();
                if (asString.equals("auto")) {
                    this.unit = YogaUnit.AUTO;
                    this.value = Float.NaN;
                } else if (asString.endsWith("%")) {
                    this.unit = YogaUnit.PERCENT;
                    this.value = Float.parseFloat(asString.substring(0, asString.length() - 1));
                } else {
                    throw new IllegalArgumentException("Unknown value: " + asString);
                }
            } else {
                this.unit = YogaUnit.POINT;
                this.value = PixelUtil.toPixelFromDIP(dynamic.asDouble());
            }
        }

        private MutableYogaValue() {
        }

        private MutableYogaValue(MutableYogaValue mutableYogaValue) {
            this.value = mutableYogaValue.value;
            this.unit = mutableYogaValue.unit;
        }
    }

    private int maybeTransformLeftRightToStartEnd(int i2) {
        if (I18nUtil.getInstance().doLeftAndRightSwapInRTL(getThemedContext())) {
            if (i2 != 0) {
                if (i2 != 2) {
                    return i2;
                }
                return 5;
            }
            return 4;
        }
        return i2;
    }

    @ReactProp(name = ViewProps.ALIGN_CONTENT)
    public void setAlignContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignContent(YogaAlign.FLEX_START);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals(DYConstants.DY_STRETCH)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals(DYConstants.DY_BASE_LINE)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals(DYConstants.DY_CENTER)) {
                    c2 = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c2 = 4;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setAlignContent(YogaAlign.STRETCH);
                return;
            case 1:
                setAlignContent(YogaAlign.BASELINE);
                return;
            case 2:
                setAlignContent(YogaAlign.CENTER);
                return;
            case 3:
                setAlignContent(YogaAlign.FLEX_START);
                return;
            case 4:
                setAlignContent(YogaAlign.AUTO);
                return;
            case 5:
                setAlignContent(YogaAlign.SPACE_BETWEEN);
                return;
            case 6:
                setAlignContent(YogaAlign.FLEX_END);
                return;
            case 7:
                setAlignContent(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignContent: " + str);
        }
    }

    @ReactProp(name = ViewProps.ALIGN_ITEMS)
    public void setAlignItems(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignItems(YogaAlign.STRETCH);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals(DYConstants.DY_STRETCH)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals(DYConstants.DY_BASE_LINE)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals(DYConstants.DY_CENTER)) {
                    c2 = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c2 = 4;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setAlignItems(YogaAlign.STRETCH);
                return;
            case 1:
                setAlignItems(YogaAlign.BASELINE);
                return;
            case 2:
                setAlignItems(YogaAlign.CENTER);
                return;
            case 3:
                setAlignItems(YogaAlign.FLEX_START);
                return;
            case 4:
                setAlignItems(YogaAlign.AUTO);
                return;
            case 5:
                setAlignItems(YogaAlign.SPACE_BETWEEN);
                return;
            case 6:
                setAlignItems(YogaAlign.FLEX_END);
                return;
            case 7:
                setAlignItems(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignItems: " + str);
        }
    }

    @ReactProp(name = ViewProps.ALIGN_SELF)
    public void setAlignSelf(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignSelf(YogaAlign.AUTO);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals(DYConstants.DY_STRETCH)) {
                    c2 = 0;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals(DYConstants.DY_BASE_LINE)) {
                    c2 = 1;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals(DYConstants.DY_CENTER)) {
                    c2 = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c2 = 3;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c2 = 4;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c2 = 5;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c2 = 6;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c2 = 7;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setAlignSelf(YogaAlign.STRETCH);
                return;
            case 1:
                setAlignSelf(YogaAlign.BASELINE);
                return;
            case 2:
                setAlignSelf(YogaAlign.CENTER);
                return;
            case 3:
                setAlignSelf(YogaAlign.FLEX_START);
                return;
            case 4:
                setAlignSelf(YogaAlign.AUTO);
                return;
            case 5:
                setAlignSelf(YogaAlign.SPACE_BETWEEN);
                return;
            case 6:
                setAlignSelf(YogaAlign.FLEX_END);
                return;
            case 7:
                setAlignSelf(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for alignSelf: " + str);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.ASPECT_RATIO)
    public void setAspectRatio(float f2) {
        setStyleAspectRatio(f2);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", ViewProps.BORDER_START_WIDTH, ViewProps.BORDER_END_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH})
    public void setBorderWidths(int i2, float f2) {
        if (isVirtual()) {
            return;
        }
        setBorder(maybeTransformLeftRightToStartEnd(ViewProps.BORDER_SPACING_TYPES[i2]), PixelUtil.toPixelFromDIP(f2));
    }

    @ReactProp(name = ViewProps.DISPLAY)
    public void setDisplay(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setDisplay(YogaDisplay.FLEX);
            return;
        }
        str.hashCode();
        if (str.equals(ViewProps.FLEX)) {
            setDisplay(YogaDisplay.FLEX);
        } else if (!str.equals("none")) {
            throw new JSApplicationIllegalArgumentException("invalid value for display: " + str);
        } else {
            setDisplay(YogaDisplay.NONE);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX)
    public void setFlex(float f2) {
        if (isVirtual()) {
            return;
        }
        super.setFlex(f2);
    }

    @ReactProp(name = ViewProps.FLEX_BASIS)
    public void setFlexBasis(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setFlexBasis(this.mTempYogaValue.value);
        } else if (i2 == 3) {
            setFlexBasisAuto();
        } else if (i2 == 4) {
            setFlexBasisPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.FLEX_DIRECTION)
    public void setFlexDirection(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexDirection(YogaFlexDirection.COLUMN);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1448970769:
                if (str.equals("row-reverse")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1354837162:
                if (str.equals("column")) {
                    c2 = 1;
                    break;
                }
                break;
            case 113114:
                if (str.equals("row")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1272730475:
                if (str.equals("column-reverse")) {
                    c2 = 3;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setFlexDirection(YogaFlexDirection.ROW_REVERSE);
                return;
            case 1:
                setFlexDirection(YogaFlexDirection.COLUMN);
                return;
            case 2:
                setFlexDirection(YogaFlexDirection.ROW);
                return;
            case 3:
                setFlexDirection(YogaFlexDirection.COLUMN_REVERSE);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for flexDirection: " + str);
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX_GROW)
    public void setFlexGrow(float f2) {
        if (isVirtual()) {
            return;
        }
        super.setFlexGrow(f2);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX_SHRINK)
    public void setFlexShrink(float f2) {
        if (isVirtual()) {
            return;
        }
        super.setFlexShrink(f2);
    }

    @ReactProp(name = ViewProps.FLEX_WRAP)
    public void setFlexWrap(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexWrap(YogaWrap.NO_WRAP);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1039592053:
                if (str.equals("nowrap")) {
                    c2 = 0;
                    break;
                }
                break;
            case -749527969:
                if (str.equals("wrap-reverse")) {
                    c2 = 1;
                    break;
                }
                break;
            case 3657802:
                if (str.equals("wrap")) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setFlexWrap(YogaWrap.NO_WRAP);
                return;
            case 1:
                setFlexWrap(YogaWrap.WRAP_REVERSE);
                return;
            case 2:
                setFlexWrap(YogaWrap.WRAP);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for flexWrap: " + str);
        }
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleHeight(this.mTempYogaValue.value);
        } else if (i2 == 3) {
            setStyleHeightAuto();
        } else if (i2 == 4) {
            setStyleHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.JUSTIFY_CONTENT)
    public void setJustifyContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setJustifyContent(YogaJustify.FLEX_START);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals(DYConstants.DY_CENTER)) {
                    c2 = 0;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c2 = 1;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c2 = 2;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c2 = 4;
                    break;
                }
                break;
            case 2055030478:
                if (str.equals("space-evenly")) {
                    c2 = 5;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setJustifyContent(YogaJustify.CENTER);
                return;
            case 1:
                setJustifyContent(YogaJustify.FLEX_START);
                return;
            case 2:
                setJustifyContent(YogaJustify.SPACE_BETWEEN);
                return;
            case 3:
                setJustifyContent(YogaJustify.FLEX_END);
                return;
            case 4:
                setJustifyContent(YogaJustify.SPACE_AROUND);
                return;
            case 5:
                setJustifyContent(YogaJustify.SPACE_EVENLY);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for justifyContent: " + str);
        }
    }

    @ReactPropGroup(names = {"margin", "marginVertical", "marginHorizontal", "marginStart", "marginEnd", "marginTop", "marginBottom", "marginLeft", "marginRight"})
    public void setMargins(int i2, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i2]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i3 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i3 == 1 || i3 == 2) {
            setMargin(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i3 == 3) {
            setMarginAuto(maybeTransformLeftRightToStartEnd);
        } else if (i3 == 4) {
            setMarginPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleMaxHeight(this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setStyleMaxHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleMaxWidth(this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setStyleMaxWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.MIN_HEIGHT)
    public void setMinHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleMinHeight(this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setStyleMinHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.MIN_WIDTH)
    public void setMinWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleMinWidth(this.mTempYogaValue.value);
        } else if (i2 == 4) {
            setStyleMinWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.OVERFLOW)
    public void setOverflow(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setOverflow(YogaOverflow.VISIBLE);
            return;
        }
        str.hashCode();
        char c2 = '\uffff';
        switch (str.hashCode()) {
            case -1217487446:
                if (str.equals(ViewProps.HIDDEN)) {
                    c2 = 0;
                    break;
                }
                break;
            case -907680051:
                if (str.equals("scroll")) {
                    c2 = 1;
                    break;
                }
                break;
            case 466743410:
                if (str.equals(ViewProps.VISIBLE)) {
                    c2 = 2;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                setOverflow(YogaOverflow.HIDDEN);
                return;
            case 1:
                setOverflow(YogaOverflow.SCROLL);
                return;
            case 2:
                setOverflow(YogaOverflow.VISIBLE);
                return;
            default:
                throw new JSApplicationIllegalArgumentException("invalid value for overflow: " + str);
        }
    }

    @ReactPropGroup(names = {"padding", "paddingVertical", "paddingHorizontal", "paddingStart", "paddingEnd", "paddingTop", "paddingBottom", "paddingLeft", "paddingRight"})
    public void setPaddings(int i2, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i2]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i3 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i3 == 1 || i3 == 2) {
            setPadding(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i3 == 4) {
            setPaddingPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "position")
    public void setPosition(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setPositionType(YogaPositionType.RELATIVE);
            return;
        }
        str.hashCode();
        if (str.equals("relative")) {
            setPositionType(YogaPositionType.RELATIVE);
        } else if (!str.equals("absolute")) {
            throw new JSApplicationIllegalArgumentException("invalid value for position: " + str);
        } else {
            setPositionType(YogaPositionType.ABSOLUTE);
        }
    }

    @ReactPropGroup(names = {"start", "end", "left", "right", "top", "bottom"})
    public void setPositionValues(int i2, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(new int[]{4, 5, 0, 2, 1, 3}[i2]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i3 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i3 == 1 || i3 == 2) {
            setPosition(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (i3 == 4) {
            setPositionPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(name = ViewProps.ON_LAYOUT)
    public void setShouldNotifyOnLayout(boolean z) {
        super.setShouldNotifyOnLayout(z);
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int i2 = AnonymousClass1.$SwitchMap$com$facebook$yoga$YogaUnit[this.mTempYogaValue.unit.ordinal()];
        if (i2 == 1 || i2 == 2) {
            setStyleWidth(this.mTempYogaValue.value);
        } else if (i2 == 3) {
            setStyleWidthAuto();
        } else if (i2 == 4) {
            setStyleWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }
}
