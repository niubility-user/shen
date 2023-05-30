package com.jd.lib.flexcube.layout.layout;

import android.graphics.Rect;
import android.text.TextUtils;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaNode;
import com.facebook.yoga.YogaWrap;
import com.jd.dynamic.DYConstants;
import com.jd.lib.flexcube.iwidget.entity.BaseConfig;
import com.jd.lib.flexcube.iwidget.entity.material.PaddingInfo;
import com.jd.lib.flexcube.widgets.entity.FlexBoxStyleConfig;
import com.jd.lib.flexcube.widgets.entity.ViewStylePadding;
import com.jd.lib.flexcube.widgets.entity.flexbox.FlexBoxConfig;

/* loaded from: classes14.dex */
public class a {
    public static void a(YogaNode yogaNode) {
        if (yogaNode != null) {
            yogaNode.setMargin(YogaEdge.ALL, 0.0f);
            yogaNode.setMargin(YogaEdge.LEFT, 0.0f);
            yogaNode.setMargin(YogaEdge.TOP, 0.0f);
            yogaNode.setMargin(YogaEdge.RIGHT, 0.0f);
            yogaNode.setMargin(YogaEdge.BOTTOM, 0.0f);
        }
    }

    public static void b(YogaNode yogaNode) {
        if (yogaNode != null) {
            yogaNode.setPadding(YogaEdge.ALL, 0.0f);
            yogaNode.setPadding(YogaEdge.LEFT, 0.0f);
            yogaNode.setPadding(YogaEdge.TOP, 0.0f);
            yogaNode.setPadding(YogaEdge.RIGHT, 0.0f);
            yogaNode.setPadding(YogaEdge.BOTTOM, 0.0f);
        }
    }

    public static void c(YogaNode yogaNode) {
        if (yogaNode != null) {
            yogaNode.setWidth(0.0f);
            yogaNode.setHeight(0.0f);
        }
    }

    public static YogaFlexDirection d(String str) {
        if (TextUtils.isEmpty(str)) {
            return YogaFlexDirection.ROW;
        }
        if ("row".equals(str)) {
            return YogaFlexDirection.ROW;
        }
        if ("column".equals(str)) {
            return YogaFlexDirection.COLUMN;
        }
        if ("columnReverse".equals(str)) {
            return YogaFlexDirection.COLUMN_REVERSE;
        }
        if ("rowReverse".equals(str)) {
            return YogaFlexDirection.ROW_REVERSE;
        }
        return YogaFlexDirection.ROW;
    }

    public static YogaJustify e(String str) {
        if (TextUtils.isEmpty(str)) {
            return YogaJustify.FLEX_START;
        }
        if ("flexStart".equals(str)) {
            return YogaJustify.FLEX_START;
        }
        if ("flexEnd".equals(str)) {
            return YogaJustify.FLEX_END;
        }
        if (DYConstants.DY_CENTER.equals(str)) {
            return YogaJustify.CENTER;
        }
        if ("spaceBetween".equals(str)) {
            return YogaJustify.SPACE_BETWEEN;
        }
        if ("spaceAround".equals(str)) {
            return YogaJustify.SPACE_AROUND;
        }
        if ("spaceEvenly".equals(str)) {
            return YogaJustify.SPACE_EVENLY;
        }
        return YogaJustify.FLEX_START;
    }

    public static YogaWrap f(String str) {
        if (TextUtils.isEmpty(str)) {
            return YogaWrap.NO_WRAP;
        }
        if ("nowrap".equals(str)) {
            return YogaWrap.NO_WRAP;
        }
        if ("wrap".equals(str)) {
            return YogaWrap.WRAP;
        }
        if ("wrapReverse".equals(str)) {
            return YogaWrap.WRAP_REVERSE;
        }
        return YogaWrap.NO_WRAP;
    }

    public static int g(YogaNode yogaNode, int i2, boolean z) {
        if (z) {
            if (i2 < -2) {
                i2 = 0;
            }
            return l(yogaNode, i2);
        }
        if (i2 < -2) {
            i2 = 0;
        }
        return k(yogaNode, i2);
    }

    public static YogaAlign h(String str) {
        if (TextUtils.isEmpty(str)) {
            return YogaAlign.FLEX_START;
        }
        if ("flexStart".equals(str)) {
            return YogaAlign.FLEX_START;
        }
        if ("flexEnd".equals(str)) {
            return YogaAlign.FLEX_END;
        }
        if (DYConstants.DY_CENTER.equals(str)) {
            return YogaAlign.CENTER;
        }
        if (DYConstants.DY_STRETCH.equals(str)) {
            return YogaAlign.STRETCH;
        }
        if (DYConstants.DY_BASE_LINE.equals(str)) {
            return YogaAlign.BASELINE;
        }
        if ("spaceBetween".equals(str)) {
            return YogaAlign.SPACE_BETWEEN;
        }
        if ("spaceAround".equals(str)) {
            return YogaAlign.SPACE_AROUND;
        }
        if ("auto".equals(str)) {
            return YogaAlign.AUTO;
        }
        return YogaAlign.FLEX_START;
    }

    public static boolean i(YogaNode yogaNode, FlexBoxConfig flexBoxConfig, float f2) {
        FlexBoxStyleConfig flexBoxStyleConfig;
        if (yogaNode != null && flexBoxConfig != null && (flexBoxStyleConfig = flexBoxConfig.linearLayoutStyle) != null) {
            try {
                yogaNode.setFlexDirection(d(flexBoxStyleConfig.flexDirection));
                yogaNode.setJustifyContent(e(flexBoxStyleConfig.justifyContent));
                yogaNode.setWrap(f(flexBoxStyleConfig.flexWrap));
                yogaNode.setAlignItems(h(flexBoxStyleConfig.alignItems));
                yogaNode.setAlignContent(h(flexBoxStyleConfig.alignContent));
                p(yogaNode, flexBoxConfig.linearLayoutStyle.viewStyle, f2);
                o(yogaNode, flexBoxConfig.marginInfo, f2);
                return true;
            } catch (Exception e2) {
                if (com.jd.lib.flexcube.a.a) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean j(YogaNode yogaNode, BaseConfig baseConfig, float f2) {
        if (baseConfig != null && yogaNode != null) {
            try {
                o(yogaNode, baseConfig.marginInfo, f2);
                return true;
            } catch (Exception e2) {
                if (com.jd.lib.flexcube.a.a) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    public static int k(YogaNode yogaNode, int i2) {
        if (i2 == -1) {
            yogaNode.setHeightPercent(100.0f);
            return -1;
        } else if (i2 == -2) {
            yogaNode.setHeightAuto();
            return -2;
        } else {
            yogaNode.setHeight(i2);
            return i2;
        }
    }

    public static int l(YogaNode yogaNode, int i2) {
        if (i2 == -1) {
            yogaNode.setWidthPercent(100.0f);
            return -1;
        } else if (i2 == -2) {
            yogaNode.setWidthAuto();
            return -2;
        } else {
            yogaNode.setWidth(i2);
            return i2;
        }
    }

    public static int m(YogaNode yogaNode, int i2, boolean z, float f2) {
        float f3 = i2;
        int round = Math.round(f2 * f3);
        if (z) {
            yogaNode.setHeightAuto();
            if (round > 0) {
                yogaNode.setMaxHeight(f3);
                return -2;
            }
            return -2;
        }
        yogaNode.setHeight(round);
        return round;
    }

    public static int n(YogaNode yogaNode, int i2, boolean z, float f2) {
        int round = Math.round(i2 * f2);
        if (z) {
            yogaNode.setWidthAuto();
            if (round > 0) {
                yogaNode.setMaxWidth(round);
                return -2;
            }
            return -2;
        }
        yogaNode.setWidth(round);
        return round;
    }

    private static void o(YogaNode yogaNode, PaddingInfo paddingInfo, float f2) {
        Rect padding;
        if (paddingInfo == null) {
            padding = new Rect();
        } else {
            padding = paddingInfo.getPadding(f2);
        }
        yogaNode.setMargin(YogaEdge.LEFT, padding.left);
        yogaNode.setMargin(YogaEdge.TOP, padding.top);
        yogaNode.setMargin(YogaEdge.RIGHT, padding.right);
        yogaNode.setMargin(YogaEdge.BOTTOM, padding.bottom);
    }

    private static void p(YogaNode yogaNode, ViewStylePadding viewStylePadding, float f2) {
        if (viewStylePadding == null) {
            yogaNode.setPadding(YogaEdge.LEFT, 0.0f);
            yogaNode.setPadding(YogaEdge.TOP, 0.0f);
            yogaNode.setPadding(YogaEdge.RIGHT, 0.0f);
            yogaNode.setPadding(YogaEdge.BOTTOM, 0.0f);
            return;
        }
        yogaNode.setPadding(YogaEdge.LEFT, Math.round(viewStylePadding.leftPadding * f2));
        yogaNode.setPadding(YogaEdge.TOP, Math.round(viewStylePadding.topPadding * f2));
        yogaNode.setPadding(YogaEdge.RIGHT, Math.round(viewStylePadding.rightPadding * f2));
        yogaNode.setPadding(YogaEdge.BOTTOM, Math.round(viewStylePadding.bottomPadding * f2));
    }
}
