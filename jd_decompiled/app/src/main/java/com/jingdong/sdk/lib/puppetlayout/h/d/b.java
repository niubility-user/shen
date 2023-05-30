package com.jingdong.sdk.lib.puppetlayout.h.d;

import android.util.SparseArray;
import android.view.View;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga2.YogaAlign;
import com.facebook.yoga2.YogaDirection;
import com.facebook.yoga2.YogaDisplay;
import com.facebook.yoga2.YogaEdge;
import com.facebook.yoga2.YogaFlexDirection;
import com.facebook.yoga2.YogaJustify;
import com.facebook.yoga2.YogaNode;
import com.facebook.yoga2.YogaOverflow;
import com.facebook.yoga2.YogaPositionType;
import com.facebook.yoga2.YogaWrap;
import com.jingdong.sdk.lib.puppetlayout.R;
import com.jingdong.sdk.lib.puppetlayout.ylayout.PuppetManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes8.dex */
public class b {
    private static Map<String, Integer> a = new HashMap();
    private static Map<String, Integer> b = new HashMap();

    static {
        a.put(ViewProps.ALIGN_CONTENT, Integer.valueOf(R.styleable.ylayout_yg2_alignContent));
        a.put(ViewProps.ALIGN_ITEMS, Integer.valueOf(R.styleable.ylayout_yg2_alignItems));
        a.put(ViewProps.ALIGN_SELF, Integer.valueOf(R.styleable.ylayout_yg2_alignSelf));
        a.put(ViewProps.ASPECT_RATIO, Integer.valueOf(R.styleable.ylayout_yg2_aspectRatio));
        a.put(ViewProps.BORDER_LEFT_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderLeft));
        a.put(ViewProps.BORDER_TOP_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderTop));
        a.put(ViewProps.BORDER_RIGHT_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderRight));
        a.put(ViewProps.BORDER_BOTTOM_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderBottom));
        a.put(ViewProps.BORDER_START_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderStart));
        a.put(ViewProps.BORDER_END_WIDTH, Integer.valueOf(R.styleable.ylayout_yg2_borderEnd));
        a.put("borderHorizontal", Integer.valueOf(R.styleable.ylayout_yg2_borderHorizontal));
        a.put("borderVertical", Integer.valueOf(R.styleable.ylayout_yg2_borderVertical));
        a.put("border", Integer.valueOf(R.styleable.ylayout_yg2_borderAll));
        a.put("direction", Integer.valueOf(R.styleable.ylayout_yg2_direction));
        a.put(ViewProps.DISPLAY, Integer.valueOf(R.styleable.ylayout_yg2_display));
        a.put(ViewProps.FLEX, Integer.valueOf(R.styleable.ylayout_yg2_flex));
        Map<String, Integer> map = a;
        int i2 = R.styleable.ylayout_yg2_flexBasis;
        map.put(ViewProps.FLEX_BASIS, Integer.valueOf(i2));
        a.put(ViewProps.FLEX_DIRECTION, Integer.valueOf(R.styleable.ylayout_yg2_flexDirection));
        a.put(ViewProps.FLEX_GROW, Integer.valueOf(R.styleable.ylayout_yg2_flexGrow));
        a.put(ViewProps.FLEX_SHRINK, Integer.valueOf(R.styleable.ylayout_yg2_flexShrink));
        Map<String, Integer> map2 = a;
        int i3 = R.styleable.ylayout_yg2_height;
        map2.put("height", Integer.valueOf(i3));
        Map<String, Integer> map3 = a;
        int i4 = R.styleable.ylayout_yg2_marginLeft;
        map3.put("marginLeft", Integer.valueOf(i4));
        a.put(ViewProps.JUSTIFY_CONTENT, Integer.valueOf(R.styleable.ylayout_yg2_justifyContent));
        Map<String, Integer> map4 = a;
        int i5 = R.styleable.ylayout_yg2_marginTop;
        map4.put("marginTop", Integer.valueOf(i5));
        Map<String, Integer> map5 = a;
        int i6 = R.styleable.ylayout_yg2_marginRight;
        map5.put("marginRight", Integer.valueOf(i6));
        Map<String, Integer> map6 = a;
        int i7 = R.styleable.ylayout_yg2_marginBottom;
        map6.put("marginBottom", Integer.valueOf(i7));
        Map<String, Integer> map7 = a;
        int i8 = R.styleable.ylayout_yg2_marginStart;
        map7.put("marginStart", Integer.valueOf(i8));
        Map<String, Integer> map8 = a;
        int i9 = R.styleable.ylayout_yg2_marginEnd;
        map8.put("marginEnd", Integer.valueOf(i9));
        Map<String, Integer> map9 = a;
        int i10 = R.styleable.ylayout_yg2_marginHorizontal;
        map9.put("marginHorizontal", Integer.valueOf(i10));
        Map<String, Integer> map10 = a;
        int i11 = R.styleable.ylayout_yg2_marginVertical;
        map10.put("marginVertical", Integer.valueOf(i11));
        Map<String, Integer> map11 = a;
        int i12 = R.styleable.ylayout_yg2_marginAll;
        map11.put("margin", Integer.valueOf(i12));
        Map<String, Integer> map12 = a;
        int i13 = R.styleable.ylayout_yg2_maxHeight;
        map12.put("maxHeight", Integer.valueOf(i13));
        Map<String, Integer> map13 = a;
        int i14 = R.styleable.ylayout_yg2_maxWidth;
        map13.put("maxWidth", Integer.valueOf(i14));
        Map<String, Integer> map14 = a;
        int i15 = R.styleable.ylayout_yg2_minHeight;
        map14.put(ViewProps.MIN_HEIGHT, Integer.valueOf(i15));
        Map<String, Integer> map15 = a;
        int i16 = R.styleable.ylayout_yg2_minWidth;
        map15.put(ViewProps.MIN_WIDTH, Integer.valueOf(i16));
        a.put(ViewProps.OVERFLOW, Integer.valueOf(R.styleable.ylayout_yg2_overflow));
        Map<String, Integer> map16 = a;
        int i17 = R.styleable.ylayout_yg2_paddingLeft;
        map16.put("paddingLeft", Integer.valueOf(i17));
        Map<String, Integer> map17 = a;
        int i18 = R.styleable.ylayout_yg2_paddingTop;
        map17.put("paddingTop", Integer.valueOf(i18));
        Map<String, Integer> map18 = a;
        int i19 = R.styleable.ylayout_yg2_paddingRight;
        map18.put("paddingRight", Integer.valueOf(i19));
        Map<String, Integer> map19 = a;
        int i20 = R.styleable.ylayout_yg2_paddingBottom;
        map19.put("paddingBottom", Integer.valueOf(i20));
        Map<String, Integer> map20 = a;
        int i21 = R.styleable.ylayout_yg2_paddingStart;
        map20.put("paddingStart", Integer.valueOf(i21));
        Map<String, Integer> map21 = a;
        int i22 = R.styleable.ylayout_yg2_paddingEnd;
        map21.put("paddingEnd", Integer.valueOf(i22));
        Map<String, Integer> map22 = a;
        int i23 = R.styleable.ylayout_yg2_paddingHorizontal;
        map22.put("paddingHorizontal", Integer.valueOf(i23));
        Map<String, Integer> map23 = a;
        int i24 = R.styleable.ylayout_yg2_paddingVertical;
        map23.put("paddingVertical", Integer.valueOf(i24));
        Map<String, Integer> map24 = a;
        int i25 = R.styleable.ylayout_yg2_paddingAll;
        map24.put("padding", Integer.valueOf(i25));
        Map<String, Integer> map25 = a;
        int i26 = R.styleable.ylayout_yg2_positionLeft;
        map25.put("left", Integer.valueOf(i26));
        Map<String, Integer> map26 = a;
        int i27 = R.styleable.ylayout_yg2_positionTop;
        map26.put("top", Integer.valueOf(i27));
        Map<String, Integer> map27 = a;
        int i28 = R.styleable.ylayout_yg2_positionRight;
        map27.put("right", Integer.valueOf(i28));
        Map<String, Integer> map28 = a;
        int i29 = R.styleable.ylayout_yg2_positionBottom;
        map28.put("bottom", Integer.valueOf(i29));
        Map<String, Integer> map29 = a;
        int i30 = R.styleable.ylayout_yg2_positionStart;
        map29.put("start", Integer.valueOf(i30));
        Map<String, Integer> map30 = a;
        int i31 = R.styleable.ylayout_yg2_positionEnd;
        map30.put("end", Integer.valueOf(i31));
        Map<String, Integer> map31 = a;
        int i32 = R.styleable.ylayout_yg2_positionHorizontal;
        map31.put("positionHorizontal", Integer.valueOf(i32));
        Map<String, Integer> map32 = a;
        int i33 = R.styleable.ylayout_yg2_positionVertical;
        map32.put("positionVertical", Integer.valueOf(i33));
        Map<String, Integer> map33 = a;
        int i34 = R.styleable.ylayout_yg2_positionAll;
        map33.put("positionAll", Integer.valueOf(i34));
        a.put("position", Integer.valueOf(R.styleable.ylayout_yg2_positionType));
        Map<String, Integer> map34 = a;
        int i35 = R.styleable.ylayout_yg2_width;
        map34.put("width", Integer.valueOf(i35));
        a.put(ViewProps.FLEX_WRAP, Integer.valueOf(R.styleable.ylayout_yg2_wrap));
        b.put(ViewProps.FLEX_BASIS, Integer.valueOf(i2));
        b.put("height", Integer.valueOf(i3));
        b.put("marginLeft", Integer.valueOf(i4));
        b.put("marginTop", Integer.valueOf(i5));
        b.put("marginRight", Integer.valueOf(i6));
        b.put("marginBottom", Integer.valueOf(i7));
        b.put("marginStart", Integer.valueOf(i8));
        b.put("marginEnd", Integer.valueOf(i9));
        b.put("marginHorizontal", Integer.valueOf(i10));
        b.put("marginVertical", Integer.valueOf(i11));
        b.put("margin", Integer.valueOf(i12));
        b.put("maxHeight", Integer.valueOf(i13));
        b.put("maxWidth", Integer.valueOf(i14));
        b.put(ViewProps.MIN_HEIGHT, Integer.valueOf(i15));
        b.put(ViewProps.MIN_WIDTH, Integer.valueOf(i16));
        b.put("paddingLeft", Integer.valueOf(i17));
        b.put("paddingTop", Integer.valueOf(i18));
        b.put("paddingRight", Integer.valueOf(i19));
        b.put("paddingBottom", Integer.valueOf(i20));
        b.put("paddingStart", Integer.valueOf(i21));
        b.put("paddingEnd", Integer.valueOf(i22));
        b.put("paddingHorizontal", Integer.valueOf(i23));
        b.put("paddingVertical", Integer.valueOf(i24));
        b.put("padding", Integer.valueOf(i25));
        b.put("left", Integer.valueOf(i26));
        b.put("top", Integer.valueOf(i27));
        b.put("right", Integer.valueOf(i28));
        b.put("bottom", Integer.valueOf(i29));
        b.put("start", Integer.valueOf(i30));
        b.put("end", Integer.valueOf(i31));
        b.put("positionHorizontal", Integer.valueOf(i32));
        b.put("positionVertical", Integer.valueOf(i33));
        b.put("positionAll", Integer.valueOf(i34));
        b.put("width", Integer.valueOf(i35));
    }

    public static void a(SparseArray<Float> sparseArray, SparseArray<String> sparseArray2, YogaNode yogaNode, View view, boolean z) {
        if (sparseArray != null && sparseArray.size() > 0) {
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                int keyAt = sparseArray.keyAt(i2);
                float floatValue = sparseArray.valueAt(i2).floatValue();
                if (keyAt == R.styleable.ylayout_yg2_alignContent) {
                    yogaNode.setAlignContent(YogaAlign.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_alignItems) {
                    yogaNode.setAlignItems(YogaAlign.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_alignSelf) {
                    yogaNode.setAlignSelf(YogaAlign.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_aspectRatio) {
                    yogaNode.setAspectRatio(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderLeft) {
                    yogaNode.setBorder(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderTop) {
                    yogaNode.setBorder(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderRight) {
                    yogaNode.setBorder(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderBottom) {
                    yogaNode.setBorder(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderStart) {
                    yogaNode.setBorder(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderEnd) {
                    yogaNode.setBorder(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderHorizontal) {
                    yogaNode.setBorder(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderVertical) {
                    yogaNode.setBorder(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_borderAll) {
                    yogaNode.setBorder(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_direction) {
                    yogaNode.setDirection(YogaDirection.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_display) {
                    yogaNode.setDisplay(YogaDisplay.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_flex) {
                    yogaNode.setFlex(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_flexBasis) {
                    yogaNode.setFlexBasis(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_flexDirection) {
                    yogaNode.setFlexDirection(YogaFlexDirection.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_flexGrow) {
                    yogaNode.setFlexGrow(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_flexShrink) {
                    yogaNode.setFlexShrink(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_height) {
                    yogaNode.setHeight(floatValue);
                    if (z) {
                        PuppetManager.getInstance().setRootHeight(view, floatValue);
                    }
                } else if (keyAt == R.styleable.ylayout_yg2_marginLeft) {
                    yogaNode.setMargin(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_justifyContent) {
                    yogaNode.setJustifyContent(YogaJustify.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_marginTop) {
                    yogaNode.setMargin(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginRight) {
                    yogaNode.setMargin(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginBottom) {
                    yogaNode.setMargin(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginStart) {
                    yogaNode.setMargin(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginEnd) {
                    yogaNode.setMargin(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginHorizontal) {
                    yogaNode.setMargin(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginVertical) {
                    yogaNode.setMargin(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_marginAll) {
                    yogaNode.setMargin(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_maxHeight) {
                    yogaNode.setMaxHeight(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_maxWidth) {
                    yogaNode.setMaxWidth(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_minHeight) {
                    yogaNode.setMinHeight(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_minWidth) {
                    yogaNode.setMinWidth(floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_overflow) {
                    yogaNode.setOverflow(YogaOverflow.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_paddingLeft) {
                    yogaNode.setPadding(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingTop) {
                    yogaNode.setPadding(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingRight) {
                    yogaNode.setPadding(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingBottom) {
                    yogaNode.setPadding(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingStart) {
                    yogaNode.setPadding(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingEnd) {
                    yogaNode.setPadding(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingHorizontal) {
                    yogaNode.setPadding(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingVertical) {
                    yogaNode.setPadding(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_paddingAll) {
                    yogaNode.setPadding(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionLeft) {
                    yogaNode.setPosition(YogaEdge.LEFT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionTop) {
                    yogaNode.setPosition(YogaEdge.TOP, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionRight) {
                    yogaNode.setPosition(YogaEdge.RIGHT, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionBottom) {
                    yogaNode.setPosition(YogaEdge.BOTTOM, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionStart) {
                    yogaNode.setPosition(YogaEdge.START, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionEnd) {
                    yogaNode.setPosition(YogaEdge.END, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionHorizontal) {
                    yogaNode.setPosition(YogaEdge.HORIZONTAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionVertical) {
                    yogaNode.setPosition(YogaEdge.VERTICAL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionAll) {
                    yogaNode.setPosition(YogaEdge.ALL, floatValue);
                } else if (keyAt == R.styleable.ylayout_yg2_positionType) {
                    yogaNode.setPositionType(YogaPositionType.fromInt(Math.round(floatValue)));
                } else if (keyAt == R.styleable.ylayout_yg2_width) {
                    yogaNode.setWidth(floatValue);
                    if (z) {
                        PuppetManager.getInstance().setRootWidth(view, floatValue);
                    }
                } else if (keyAt == R.styleable.ylayout_yg2_wrap) {
                    yogaNode.setWrap(YogaWrap.fromInt(Math.round(floatValue)));
                }
            }
        }
        if (sparseArray2 == null || sparseArray2.size() <= 0) {
            return;
        }
        for (int i3 = 0; i3 < sparseArray2.size(); i3++) {
            int keyAt2 = sparseArray2.keyAt(i3);
            String valueAt = sparseArray2.valueAt(i3);
            if (valueAt.equals("auto")) {
                if (keyAt2 == R.styleable.ylayout_yg2_marginLeft) {
                    yogaNode.setMarginAuto(YogaEdge.LEFT);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginTop) {
                    yogaNode.setMarginAuto(YogaEdge.TOP);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginRight) {
                    yogaNode.setMarginAuto(YogaEdge.RIGHT);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginBottom) {
                    yogaNode.setMarginAuto(YogaEdge.BOTTOM);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginStart) {
                    yogaNode.setMarginAuto(YogaEdge.START);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginEnd) {
                    yogaNode.setMarginAuto(YogaEdge.END);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginHorizontal) {
                    yogaNode.setMarginAuto(YogaEdge.HORIZONTAL);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginVertical) {
                    yogaNode.setMarginAuto(YogaEdge.VERTICAL);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginAll) {
                    yogaNode.setMarginAuto(YogaEdge.ALL);
                }
            }
            if (valueAt.endsWith("%")) {
                float parseFloat = Float.parseFloat(valueAt.substring(0, valueAt.length() - 1));
                if (keyAt2 == R.styleable.ylayout_yg2_flexBasis) {
                    yogaNode.setFlexBasisPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_height) {
                    yogaNode.setHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginLeft) {
                    yogaNode.setMarginPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginTop) {
                    yogaNode.setMarginPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginRight) {
                    yogaNode.setMarginPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginBottom) {
                    yogaNode.setMarginPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginStart) {
                    yogaNode.setMarginPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginEnd) {
                    yogaNode.setMarginPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginHorizontal) {
                    yogaNode.setMarginPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginVertical) {
                    yogaNode.setMarginPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_marginAll) {
                    yogaNode.setMarginPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_maxHeight) {
                    yogaNode.setMaxHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_maxWidth) {
                    yogaNode.setMaxWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_minHeight) {
                    yogaNode.setMinHeightPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_minWidth) {
                    yogaNode.setMinWidthPercent(parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingLeft) {
                    yogaNode.setPaddingPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingTop) {
                    yogaNode.setPaddingPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingRight) {
                    yogaNode.setPaddingPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingBottom) {
                    yogaNode.setPaddingPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingStart) {
                    yogaNode.setPaddingPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingEnd) {
                    yogaNode.setPaddingPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingHorizontal) {
                    yogaNode.setPaddingPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingVertical) {
                    yogaNode.setPaddingPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_paddingAll) {
                    yogaNode.setPaddingPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionLeft) {
                    yogaNode.setPositionPercent(YogaEdge.LEFT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionTop) {
                    yogaNode.setPositionPercent(YogaEdge.TOP, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionRight) {
                    yogaNode.setPositionPercent(YogaEdge.RIGHT, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionBottom) {
                    yogaNode.setPositionPercent(YogaEdge.BOTTOM, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionStart) {
                    yogaNode.setPositionPercent(YogaEdge.START, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionEnd) {
                    yogaNode.setPositionPercent(YogaEdge.END, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionHorizontal) {
                    yogaNode.setPositionPercent(YogaEdge.HORIZONTAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionVertical) {
                    yogaNode.setPositionPercent(YogaEdge.VERTICAL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_positionAll) {
                    yogaNode.setPositionPercent(YogaEdge.ALL, parseFloat);
                } else if (keyAt2 == R.styleable.ylayout_yg2_width) {
                    yogaNode.setWidthPercent(parseFloat);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void b(java.lang.String r4, java.lang.String r5, android.util.SparseArray<java.lang.Float> r6, android.util.SparseArray<java.lang.String> r7) {
        /*
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.jingdong.sdk.lib.puppetlayout.h.d.b.b
            boolean r0 = r0.containsKey(r4)
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L4a
            java.lang.String r0 = "%"
            boolean r3 = r5.endsWith(r0)
            if (r3 != 0) goto L1a
            java.lang.String r3 = "auto"
            boolean r3 = r5.equals(r3)
            if (r3 == 0) goto L4a
        L1a:
            boolean r0 = r5.endsWith(r0)     // Catch: java.lang.NumberFormatException -> L2e
            if (r0 == 0) goto L2c
            int r0 = r5.length()     // Catch: java.lang.NumberFormatException -> L2e
            int r0 = r0 - r1
            java.lang.String r0 = r5.substring(r2, r0)     // Catch: java.lang.NumberFormatException -> L2e
            java.lang.Float.parseFloat(r0)     // Catch: java.lang.NumberFormatException -> L2e
        L2c:
            r0 = 1
            goto L37
        L2e:
            r0 = move-exception
            boolean r3 = com.jingdong.sdk.lib.puppetlayout.g.b.a
            if (r3 == 0) goto L36
            r0.printStackTrace()
        L36:
            r0 = 0
        L37:
            if (r0 == 0) goto L4a
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.jingdong.sdk.lib.puppetlayout.h.d.b.b
            java.lang.Object r0 = r0.get(r4)
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            r7.put(r0, r5)
            r7 = 1
            goto L4b
        L4a:
            r7 = 0
        L4b:
            if (r7 != 0) goto La1
            java.util.Map<java.lang.String, java.lang.Integer> r7 = com.jingdong.sdk.lib.puppetlayout.h.d.b.a
            boolean r7 = r7.containsKey(r4)
            if (r7 == 0) goto La1
            r7 = 0
            float r0 = com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils.getValue(r4, r5)     // Catch: java.lang.Exception -> L82
            r3 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r3 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r3 == 0) goto L61
            goto L8c
        L61:
            boolean r3 = com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils.canUnitDp(r4)     // Catch: java.lang.Exception -> L7f
            if (r3 == 0) goto L75
            java.lang.String r3 = "0"
            boolean r3 = r3.equals(r5)     // Catch: java.lang.Exception -> L7f
            if (r3 == 0) goto L70
            goto L7d
        L70:
            float r7 = com.jingdong.sdk.lib.puppetlayout.ylayout.LayoutUtils.getDpValue(r5)     // Catch: java.lang.Exception -> L7f
            goto L7d
        L75:
            java.lang.Float r5 = java.lang.Float.valueOf(r5)     // Catch: java.lang.Exception -> L7f
            float r7 = r5.floatValue()     // Catch: java.lang.Exception -> L7f
        L7d:
            r0 = r7
            goto L8c
        L7f:
            r5 = move-exception
            r7 = r0
            goto L83
        L82:
            r5 = move-exception
        L83:
            boolean r0 = com.jingdong.sdk.lib.puppetlayout.g.b.a
            if (r0 == 0) goto L8a
            r5.printStackTrace()
        L8a:
            r0 = r7
            r1 = 0
        L8c:
            if (r1 == 0) goto La1
            java.util.Map<java.lang.String, java.lang.Integer> r5 = com.jingdong.sdk.lib.puppetlayout.h.d.b.a
            java.lang.Object r4 = r5.get(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            java.lang.Float r5 = java.lang.Float.valueOf(r0)
            r6.put(r4, r5)
        La1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.sdk.lib.puppetlayout.h.d.b.b(java.lang.String, java.lang.String, android.util.SparseArray, android.util.SparseArray):void");
    }
}
