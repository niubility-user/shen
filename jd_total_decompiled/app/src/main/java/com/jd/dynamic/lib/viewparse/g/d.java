package com.jd.dynamic.lib.viewparse.g;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ViewProps;
import com.jd.dynamic.DYConstants;
import com.jd.dynamic.R;
import com.jd.dynamic.base.DynamicSdk;
import com.jd.dynamic.base.interfaces.IExceptionHandler;
import com.jd.dynamic.yoga.YogaAlign;
import com.jd.dynamic.yoga.YogaDisplay;
import com.jd.dynamic.yoga.YogaFlexDirection;
import com.jd.dynamic.yoga.YogaJustify;
import com.jd.dynamic.yoga.YogaOverflow;
import com.jd.dynamic.yoga.YogaPositionType;
import com.jd.dynamic.yoga.YogaWrap;
import com.jd.dynamic.yoga.android.YogaLayout;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class d implements b<YogaLayout.LayoutParams> {
    private b<YogaLayout.LayoutParams> a = a.c();

    /* loaded from: classes13.dex */
    public static class a implements b<YogaLayout.LayoutParams> {
        static b<YogaLayout.LayoutParams> a = new a();

        private a() {
        }

        public static b<YogaLayout.LayoutParams> c() {
            return a;
        }

        private YogaLayout.LayoutParams d(HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams, Context context) {
            d.g(hashMap, layoutParams, context);
            d.h(hashMap, layoutParams, context);
            d.j(hashMap, layoutParams, context);
            d.i(hashMap, layoutParams, context);
            return layoutParams;
        }

        @Override // com.jd.dynamic.lib.viewparse.g.b
        public /* bridge */ /* synthetic */ ViewGroup.LayoutParams a(Context context, HashMap hashMap, YogaLayout.LayoutParams layoutParams) {
            YogaLayout.LayoutParams layoutParams2 = layoutParams;
            b(context, hashMap, layoutParams2);
            return layoutParams2;
        }

        public ViewGroup.LayoutParams b(Context context, HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams) {
            d(hashMap, layoutParams, context);
            return layoutParams;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void g(HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams, Context context) {
        String str;
        int i2;
        YogaWrap yogaWrap;
        char c2;
        char c3;
        if (hashMap.containsKey(ViewProps.ALIGN_SELF)) {
            String str2 = hashMap.get(ViewProps.ALIGN_SELF);
            YogaAlign yogaAlign = YogaAlign.AUTO;
            if (!"auto".equals(str2)) {
                if (DYConstants.DY_FLEX_START.equals(str2)) {
                    yogaAlign = YogaAlign.FLEX_START;
                } else if (DYConstants.DY_FLEX_END.equals(str2)) {
                    yogaAlign = YogaAlign.FLEX_END;
                } else if (DYConstants.DY_CENTER.equals(str2)) {
                    yogaAlign = YogaAlign.CENTER;
                } else if (DYConstants.DY_BASE_LINE.equals(str2)) {
                    yogaAlign = YogaAlign.BASELINE;
                } else if (DYConstants.DY_STRETCH.equals(str2)) {
                    yogaAlign = YogaAlign.STRETCH;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_alignSelf, yogaAlign.intValue());
        }
        if (hashMap.containsKey(ViewProps.FLEX_DIRECTION)) {
            String str3 = hashMap.get(ViewProps.FLEX_DIRECTION);
            YogaFlexDirection yogaFlexDirection = YogaFlexDirection.ROW;
            if (str3 != null) {
                str3.hashCode();
                switch (str3.hashCode()) {
                    case -1781065991:
                        if (str3.equals(DYConstants.DY_COLUMN_REVERSE)) {
                            c3 = 0;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    case -1354837162:
                        if (str3.equals("column")) {
                            c3 = 1;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    case -207799939:
                        if (str3.equals(DYConstants.DY_ROW_REVERSE)) {
                            c3 = 2;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    case 113114:
                        if (str3.equals("row")) {
                            c3 = 3;
                            break;
                        }
                        c3 = '\uffff';
                        break;
                    default:
                        c3 = '\uffff';
                        break;
                }
                switch (c3) {
                    case 0:
                        yogaFlexDirection = YogaFlexDirection.COLUMN_REVERSE;
                        break;
                    case 1:
                        yogaFlexDirection = YogaFlexDirection.COLUMN;
                        break;
                    case 2:
                        yogaFlexDirection = YogaFlexDirection.ROW_REVERSE;
                        break;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_flexDirection, yogaFlexDirection.intValue());
        }
        if (hashMap.containsKey(ViewProps.FLEX_WRAP)) {
            String str4 = hashMap.get(ViewProps.FLEX_WRAP);
            YogaWrap yogaWrap2 = YogaWrap.NO_WRAP;
            if (!"nowrap".equals(str4)) {
                if ("wrap".equals(str4)) {
                    yogaWrap2 = YogaWrap.WRAP;
                } else if ("wrap_reverse".equals(str4)) {
                    yogaWrap2 = YogaWrap.WRAP_REVERSE;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_wrap, yogaWrap2.intValue());
        }
        if (hashMap.containsKey(ViewProps.JUSTIFY_CONTENT)) {
            String str5 = hashMap.get(ViewProps.JUSTIFY_CONTENT);
            YogaJustify yogaJustify = YogaJustify.FLEX_START;
            if (!DYConstants.DY_FLEX_START.equals(str5)) {
                if (DYConstants.DY_FLEX_END.equals(str5)) {
                    yogaJustify = YogaJustify.FLEX_END;
                } else if (DYConstants.DY_CENTER.equals(str5)) {
                    yogaJustify = YogaJustify.CENTER;
                } else if ("space_between".equals(str5)) {
                    yogaJustify = YogaJustify.SPACE_BETWEEN;
                } else if ("space_around".equals(str5)) {
                    yogaJustify = YogaJustify.SPACE_AROUND;
                } else if ("space_evenly".equals(str5)) {
                    yogaJustify = YogaJustify.SPACE_EVENLY;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_justifyContent, yogaJustify.intValue());
        }
        if (hashMap.containsKey(ViewProps.ALIGN_ITEMS)) {
            String str6 = hashMap.get(ViewProps.ALIGN_ITEMS);
            YogaAlign yogaAlign2 = YogaAlign.FLEX_START;
            if (!DYConstants.DY_FLEX_START.equals(str6)) {
                if (DYConstants.DY_FLEX_END.equals(str6)) {
                    yogaAlign2 = YogaAlign.FLEX_END;
                } else if (DYConstants.DY_CENTER.equals(str6)) {
                    yogaAlign2 = YogaAlign.CENTER;
                } else if (DYConstants.DY_BASE_LINE.equals(str6)) {
                    yogaAlign2 = YogaAlign.BASELINE;
                } else if (DYConstants.DY_STRETCH.equals(str6)) {
                    yogaAlign2 = YogaAlign.STRETCH;
                } else if ("auto".equals(str6)) {
                    yogaAlign2 = YogaAlign.AUTO;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_alignItems, yogaAlign2.intValue());
        }
        if (hashMap.containsKey(ViewProps.ALIGN_CONTENT)) {
            String str7 = hashMap.get(ViewProps.ALIGN_CONTENT);
            YogaAlign yogaAlign3 = YogaAlign.FLEX_START;
            if (!DYConstants.DY_FLEX_START.equals(str7)) {
                if (DYConstants.DY_FLEX_END.equals(str7)) {
                    yogaAlign3 = YogaAlign.FLEX_END;
                } else if (DYConstants.DY_CENTER.equals(str7)) {
                    yogaAlign3 = YogaAlign.CENTER;
                } else if ("space_between".equals(str7)) {
                    yogaAlign3 = YogaAlign.SPACE_BETWEEN;
                } else if ("space_around".equals(str7)) {
                    yogaAlign3 = YogaAlign.SPACE_AROUND;
                } else if (DYConstants.DY_STRETCH.equals(str7)) {
                    yogaAlign3 = YogaAlign.STRETCH;
                } else if ("auto".equals(str7)) {
                    yogaAlign3 = YogaAlign.AUTO;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_alignContent, yogaAlign3.intValue());
        }
        if (hashMap.containsKey("position")) {
            String str8 = hashMap.get("position");
            YogaPositionType yogaPositionType = YogaPositionType.ABSOLUTE;
            if (str8 != null) {
                str8.hashCode();
                if (str8.equals("relative")) {
                    yogaPositionType = YogaPositionType.RELATIVE;
                } else {
                    str8.equals("absolute");
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionType, yogaPositionType.intValue());
        }
        if (hashMap.containsKey(ViewProps.DISPLAY)) {
            String str9 = hashMap.get(ViewProps.DISPLAY);
            YogaDisplay yogaDisplay = YogaDisplay.FLEX;
            if (str9 != null) {
                str9.hashCode();
                if (!str9.equals(ViewProps.FLEX) && str9.equals("none")) {
                    yogaDisplay = YogaDisplay.NONE;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_display, yogaDisplay.intValue());
        }
        if (hashMap.containsKey(ViewProps.OVERFLOW)) {
            String str10 = hashMap.get(ViewProps.OVERFLOW);
            YogaOverflow yogaOverflow = YogaOverflow.SCROLL;
            if (str10 != null) {
                str10.hashCode();
                switch (str10.hashCode()) {
                    case -1217487446:
                        if (str10.equals(ViewProps.HIDDEN)) {
                            c2 = 0;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case -907680051:
                        if (str10.equals("scroll")) {
                            c2 = 1;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    case 466743410:
                        if (str10.equals(ViewProps.VISIBLE)) {
                            c2 = 2;
                            break;
                        }
                        c2 = '\uffff';
                        break;
                    default:
                        c2 = '\uffff';
                        break;
                }
                switch (c2) {
                    case 0:
                        yogaOverflow = YogaOverflow.HIDDEN;
                        break;
                    case 2:
                        yogaOverflow = YogaOverflow.VISIBLE;
                        break;
                }
            }
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_overflow, yogaOverflow.intValue());
        }
        if (!hashMap.containsKey("wrap") || (str = hashMap.get("wrap")) == null) {
            return;
        }
        str.hashCode();
        if (str.equals("nowrap")) {
            i2 = R.styleable.yoga_yg_wrap;
            yogaWrap = YogaWrap.NO_WRAP;
        } else if (!str.equals("wrap")) {
            return;
        } else {
            i2 = R.styleable.yoga_yg_wrap;
            yogaWrap = YogaWrap.WRAP;
        }
        layoutParams.putNumericAttribute(i2, yogaWrap.intValue());
    }

    public static void h(HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams, Context context) {
        if (hashMap.containsKey(ViewProps.FLEX_GROW) && !TextUtils.isEmpty(hashMap.get(ViewProps.FLEX_GROW))) {
            String str = hashMap.get(ViewProps.FLEX_GROW);
            try {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_flexGrow, Float.parseFloat(str + ""));
            } catch (Exception e2) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "YogaLayoutParse parseNumberAttrs flexGrow error", null, null, e2);
            }
        }
        if (hashMap.containsKey(ViewProps.FLEX_SHRINK) && !TextUtils.isEmpty(hashMap.get(ViewProps.FLEX_SHRINK))) {
            String str2 = hashMap.get(ViewProps.FLEX_SHRINK);
            try {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_flexShrink, Float.parseFloat(str2 + ""));
            } catch (Exception e3) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "YogaLayoutParse parseNumberAttrs flexShrink error", null, null, e3);
            }
        }
        if (hashMap.containsKey(ViewProps.FLEX) && !TextUtils.isEmpty(hashMap.get(ViewProps.FLEX))) {
            try {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_flex, Float.parseFloat(hashMap.get(ViewProps.FLEX)));
            } catch (Exception e4) {
                DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "YogaLayoutParse parseNumberAttrs flex error", null, null, e4);
            }
        }
        if (hashMap.containsKey("borderLeft")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderLeft, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderLeft"), context));
        }
        if (hashMap.containsKey("borderTop")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderTop, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderTop"), context));
        }
        if (hashMap.containsKey("borderRight")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderRight, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderRight"), context));
        }
        if (hashMap.containsKey("borderBottom")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderBottom, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderBottom"), context));
        }
        if (hashMap.containsKey("borderStart")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderStart, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderStart"), context));
        }
        if (hashMap.containsKey("borderEnd")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderEnd, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderEnd"), context));
        }
        if (hashMap.containsKey("borderHorizontal")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderHorizontal, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderHorizontal"), context));
        }
        if (hashMap.containsKey("borderVertical")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderVertical, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderVertical"), context));
        }
        if (hashMap.containsKey("borderAll")) {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_borderAll, com.jd.dynamic.lib.viewparse.d.a(hashMap.get("borderAll"), context));
        }
        if (!hashMap.containsKey(ViewProps.ASPECT_RATIO) || TextUtils.isEmpty(hashMap.get(ViewProps.ASPECT_RATIO))) {
            return;
        }
        try {
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_aspectRatio, Float.parseFloat(hashMap.get(ViewProps.ASPECT_RATIO)));
        } catch (Exception e5) {
            DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "YogaLayoutParse parseNumberAttrs aspectRatio error", null, null, e5);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:411:0x01ce, code lost:
        if (r0.endsWith("%") != false) goto L419;
     */
    /* JADX WARN: Code restructure failed: missing block: B:418:0x01ef, code lost:
        if (r0.endsWith("%") != false) goto L419;
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x01f1, code lost:
        r6.putStringAttribute(com.jd.dynamic.R.styleable.yoga_yg_marginAll, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x01f7, code lost:
        r6.putNumericAttribute(com.jd.dynamic.R.styleable.yoga_yg_marginAll, com.jd.dynamic.lib.viewparse.d.a(r0, r7));
        r0 = (int) com.jd.dynamic.lib.viewparse.d.a(r0, r7);
        r6.setMargins(r0, r0, r0, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:490:0x039e, code lost:
        if (r5.endsWith("%") != false) goto L498;
     */
    /* JADX WARN: Code restructure failed: missing block: B:497:0x03bf, code lost:
        if (r5.endsWith("%") != false) goto L498;
     */
    /* JADX WARN: Code restructure failed: missing block: B:498:0x03c1, code lost:
        r6.putStringAttribute(com.jd.dynamic.R.styleable.yoga_yg_paddingAll, r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:499:0x03c7, code lost:
        r6.putNumericAttribute(com.jd.dynamic.R.styleable.yoga_yg_paddingAll, com.jd.dynamic.lib.viewparse.d.a(r5, r7));
     */
    /* JADX WARN: Code restructure failed: missing block: B:500:0x03d0, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:503:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void i(HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams, Context context) {
        String str;
        String str2;
        if (hashMap.containsKey("marginLeft") && !TextUtils.isEmpty(hashMap.get("marginLeft"))) {
            String str3 = hashMap.get("marginLeft");
            if (str3.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginLeft, str3);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginLeft, com.jd.dynamic.lib.viewparse.d.a(str3, context));
                ((ViewGroup.MarginLayoutParams) layoutParams).leftMargin = (int) com.jd.dynamic.lib.viewparse.d.a(str3, context);
            }
        }
        if (hashMap.containsKey("marginRight") && !TextUtils.isEmpty(hashMap.get("marginRight"))) {
            String str4 = hashMap.get("marginRight");
            if (str4.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginRight, str4);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginRight, com.jd.dynamic.lib.viewparse.d.a(str4, context));
                ((ViewGroup.MarginLayoutParams) layoutParams).rightMargin = (int) com.jd.dynamic.lib.viewparse.d.a(str4, context);
            }
        }
        if (hashMap.containsKey("marginTop") && !TextUtils.isEmpty(hashMap.get("marginTop"))) {
            String str5 = hashMap.get("marginTop");
            if (str5.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginTop, str5);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginTop, com.jd.dynamic.lib.viewparse.d.a(str5, context));
                ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = (int) com.jd.dynamic.lib.viewparse.d.a(str5, context);
            }
        }
        if (hashMap.containsKey("marginBottom") && !TextUtils.isEmpty(hashMap.get("marginBottom"))) {
            String str6 = hashMap.get("marginBottom");
            if (str6.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginBottom, str6);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginBottom, com.jd.dynamic.lib.viewparse.d.a(str6, context));
                ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin = (int) com.jd.dynamic.lib.viewparse.d.a(str6, context);
            }
        }
        if (hashMap.containsKey("marginStart") && !TextUtils.isEmpty(hashMap.get("marginStart"))) {
            String str7 = hashMap.get("marginStart");
            if (str7.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginStart, str7);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginLeft, com.jd.dynamic.lib.viewparse.d.a(str7, context));
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.setMarginStart((int) com.jd.dynamic.lib.viewparse.d.a(str7, context));
                }
            }
        }
        if (hashMap.containsKey("marginEnd") && !TextUtils.isEmpty(hashMap.get("marginEnd"))) {
            String str8 = hashMap.get("marginEnd");
            if (str8.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginEnd, str8);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginEnd, com.jd.dynamic.lib.viewparse.d.a(str8, context));
                if (Build.VERSION.SDK_INT >= 17) {
                    layoutParams.setMarginEnd((int) com.jd.dynamic.lib.viewparse.d.a(str8, context));
                }
            }
        }
        if (hashMap.containsKey("marginHorizontal") && !TextUtils.isEmpty(hashMap.get("marginHorizontal"))) {
            String str9 = hashMap.get("marginHorizontal");
            if (str9.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginHorizontal, str9);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginHorizontal, com.jd.dynamic.lib.viewparse.d.a(str9, context));
            }
        }
        if (hashMap.containsKey("marginVertical") && !TextUtils.isEmpty(hashMap.get("marginVertical"))) {
            String str10 = hashMap.get("marginVertical");
            if (str10.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_marginVertical, str10);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_marginVertical, com.jd.dynamic.lib.viewparse.d.a(str10, context));
            }
        }
        if (hashMap.containsKey(DYConstants.DY_MARGIN_ALL) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_MARGIN_ALL))) {
            str = hashMap.get(DYConstants.DY_MARGIN_ALL);
        } else if (hashMap.containsKey("margin") && !TextUtils.isEmpty(hashMap.get("margin"))) {
            str = hashMap.get("margin");
        }
        if (hashMap.containsKey("paddingLeft") && !TextUtils.isEmpty(hashMap.get("paddingLeft"))) {
            String str11 = hashMap.get("paddingLeft");
            if (str11.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingLeft, str11);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingLeft, com.jd.dynamic.lib.viewparse.d.a(str11, context));
            }
        }
        if (hashMap.containsKey("paddingRight") && !TextUtils.isEmpty(hashMap.get("paddingRight"))) {
            String str12 = hashMap.get("paddingRight");
            if (str12.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingRight, str12);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingRight, com.jd.dynamic.lib.viewparse.d.a(str12, context));
            }
        }
        if (hashMap.containsKey("paddingBottom") && !TextUtils.isEmpty(hashMap.get("paddingBottom"))) {
            String str13 = hashMap.get("paddingBottom");
            if (str13.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingBottom, str13);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingBottom, com.jd.dynamic.lib.viewparse.d.a(str13, context));
            }
        }
        if (hashMap.containsKey("paddingTop") && !TextUtils.isEmpty(hashMap.get("paddingTop"))) {
            String str14 = hashMap.get("paddingTop");
            if (str14.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingTop, str14);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingTop, com.jd.dynamic.lib.viewparse.d.a(str14, context));
            }
        }
        if (hashMap.containsKey("paddingStart") && !TextUtils.isEmpty(hashMap.get("paddingStart"))) {
            String str15 = hashMap.get("paddingStart");
            if (str15.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingStart, str15);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingStart, com.jd.dynamic.lib.viewparse.d.a(str15, context));
            }
        }
        if (hashMap.containsKey("paddingEnd") && !TextUtils.isEmpty(hashMap.get("paddingEnd"))) {
            String str16 = hashMap.get("paddingEnd");
            if (str16.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingEnd, str16);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingEnd, com.jd.dynamic.lib.viewparse.d.a(str16, context));
            }
        }
        if (hashMap.containsKey("paddingHorizontal") && !TextUtils.isEmpty(hashMap.get("paddingHorizontal"))) {
            String str17 = hashMap.get("paddingHorizontal");
            if (str17.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingHorizontal, str17);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingHorizontal, com.jd.dynamic.lib.viewparse.d.a(str17, context));
            }
        }
        if (hashMap.containsKey("paddingVertical") && !TextUtils.isEmpty(hashMap.get("paddingVertical"))) {
            String str18 = hashMap.get("paddingVertical");
            if (str18.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_paddingVertical, str18);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_paddingVertical, com.jd.dynamic.lib.viewparse.d.a(str18, context));
            }
        }
        if (hashMap.containsKey(DYConstants.DY_PADDING_ALL) && !TextUtils.isEmpty(hashMap.get(DYConstants.DY_PADDING_ALL))) {
            str2 = hashMap.get(DYConstants.DY_PADDING_ALL);
        } else if (!hashMap.containsKey("padding") || TextUtils.isEmpty(hashMap.get("padding"))) {
        } else {
            str2 = hashMap.get("padding");
        }
    }

    public static void j(HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams, Context context) {
        if (hashMap.containsKey(ViewProps.FLEX_BASIS) && !TextUtils.isEmpty(hashMap.get(ViewProps.FLEX_BASIS))) {
            String str = hashMap.get(ViewProps.FLEX_BASIS);
            if (str.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_flexBasis, str);
            } else {
                try {
                    layoutParams.putNumericAttribute(R.styleable.yoga_yg_flexBasis, Float.parseFloat(str + ""));
                } catch (Exception e2) {
                    DynamicSdk.handException(IExceptionHandler.DynamicExceptionData.TYPE_BIND, "YogaLayoutParse parseNumberAttrs flexBasis error", null, null, e2);
                }
            }
        }
        if (hashMap.containsKey("positionLeft") && !TextUtils.isEmpty(hashMap.get("positionLeft"))) {
            String str2 = hashMap.get("positionLeft");
            if (str2.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionLeft, str2);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionLeft, com.jd.dynamic.lib.viewparse.d.a(str2, context));
            }
        }
        if (hashMap.containsKey("top") && !TextUtils.isEmpty(hashMap.get("top"))) {
            String str3 = hashMap.get("top");
            if (str3.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionTop, str3);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionTop, com.jd.dynamic.lib.viewparse.d.a(str3, context));
            }
        }
        if (hashMap.containsKey("bottom") && !TextUtils.isEmpty(hashMap.get("bottom"))) {
            String str4 = hashMap.get("bottom");
            if (str4.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionBottom, str4);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionBottom, com.jd.dynamic.lib.viewparse.d.a(str4, context));
            }
        }
        if (hashMap.containsKey("right") && !TextUtils.isEmpty(hashMap.get("right"))) {
            String str5 = hashMap.get("right");
            if (str5.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionRight, str5);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionRight, com.jd.dynamic.lib.viewparse.d.a(str5, context));
            }
        }
        if (hashMap.containsKey("left") && !TextUtils.isEmpty(hashMap.get("left"))) {
            String str6 = hashMap.get("left");
            if (str6.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionLeft, str6);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionLeft, com.jd.dynamic.lib.viewparse.d.a(str6, context));
            }
        }
        if (hashMap.containsKey("start") && !TextUtils.isEmpty(hashMap.get("start"))) {
            String str7 = hashMap.get("start");
            if (str7.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionStart, str7);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionStart, com.jd.dynamic.lib.viewparse.d.a(str7, context));
            }
        }
        if (hashMap.containsKey("end") && !TextUtils.isEmpty(hashMap.get("end"))) {
            String str8 = hashMap.get("end");
            if (str8.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionEnd, str8);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionEnd, com.jd.dynamic.lib.viewparse.d.a(str8, context));
            }
        }
        if (hashMap.containsKey("positionHorizontal") && !TextUtils.isEmpty(hashMap.get("positionHorizontal"))) {
            String str9 = hashMap.get("positionHorizontal");
            if (str9.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionHorizontal, str9);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionHorizontal, com.jd.dynamic.lib.viewparse.d.a(str9, context));
            }
        }
        if (hashMap.containsKey("positionVertical") && !TextUtils.isEmpty(hashMap.get("positionVertical"))) {
            String str10 = hashMap.get("positionVertical");
            if (str10.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionVertical, str10);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionVertical, com.jd.dynamic.lib.viewparse.d.a(str10, context));
            }
        }
        if (hashMap.containsKey("positionAll") && !TextUtils.isEmpty(hashMap.get("positionAll"))) {
            String str11 = hashMap.get("positionAll");
            if (str11.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_positionAll, str11);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_positionAll, com.jd.dynamic.lib.viewparse.d.a(str11, context));
            }
        }
        if (hashMap.containsKey("maxHeight") && !TextUtils.isEmpty(hashMap.get("maxHeight"))) {
            String str12 = hashMap.get("maxHeight");
            if (str12.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_maxHeight, str12);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_maxHeight, com.jd.dynamic.lib.viewparse.d.a(str12, context));
            }
        }
        if (hashMap.containsKey("maxWidth") && !TextUtils.isEmpty(hashMap.get("maxWidth"))) {
            String str13 = hashMap.get("maxWidth");
            if (str13.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_maxWidth, str13);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_maxWidth, com.jd.dynamic.lib.viewparse.d.a(str13, context));
            }
        }
        if (hashMap.containsKey(ViewProps.MIN_HEIGHT) && !TextUtils.isEmpty(hashMap.get(ViewProps.MIN_HEIGHT))) {
            String str14 = hashMap.get(ViewProps.MIN_HEIGHT);
            if (str14.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_minHeight, str14);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_minHeight, com.jd.dynamic.lib.viewparse.d.a(str14, context));
            }
        }
        if (hashMap.containsKey(ViewProps.MIN_WIDTH) && !TextUtils.isEmpty(hashMap.get(ViewProps.MIN_WIDTH))) {
            String str15 = hashMap.get(ViewProps.MIN_WIDTH);
            if (str15.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_minWidth, str15);
            } else {
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_minWidth, com.jd.dynamic.lib.viewparse.d.a(str15, context));
            }
        }
        if (hashMap.containsKey("width") && !TextUtils.isEmpty(hashMap.get("width"))) {
            String str16 = hashMap.get("width");
            if (str16.endsWith("%")) {
                layoutParams.putStringAttribute(R.styleable.yoga_yg_width, str16);
            } else if (str16.equals(DYConstants.DY_MATCH_PARENT)) {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = -1;
            } else if (str16.equals(DYConstants.DY_WRAP_CONTENT)) {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = -2;
            } else {
                ((ViewGroup.MarginLayoutParams) layoutParams).width = (int) com.jd.dynamic.lib.viewparse.d.a(str16, context);
                layoutParams.putNumericAttribute(R.styleable.yoga_yg_width, com.jd.dynamic.lib.viewparse.d.a(str16, context));
            }
        }
        if (!hashMap.containsKey("height") || TextUtils.isEmpty(hashMap.get("height"))) {
            return;
        }
        String str17 = hashMap.get("height");
        if (str17.endsWith("%")) {
            layoutParams.putStringAttribute(R.styleable.yoga_yg_height, str17);
        } else if (str17.equals(DYConstants.DY_MATCH_PARENT)) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = -1;
        } else if (str17.equals(DYConstants.DY_WRAP_CONTENT)) {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = -2;
        } else {
            ((ViewGroup.MarginLayoutParams) layoutParams).height = (int) com.jd.dynamic.lib.viewparse.d.a(str17, context);
            layoutParams.putNumericAttribute(R.styleable.yoga_yg_height, com.jd.dynamic.lib.viewparse.d.a(str17, context));
        }
    }

    @Override // com.jd.dynamic.lib.viewparse.g.b
    /* renamed from: b */
    public ViewGroup.LayoutParams a(Context context, HashMap<String, String> hashMap, YogaLayout.LayoutParams layoutParams) {
        return this.a.a(context, hashMap, layoutParams);
    }
}
