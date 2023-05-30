package com.jingdong.sdk.lib.puppetlayout.ylayout;

import android.text.TextUtils;
import com.facebook.react.uimanager.ViewProps;
import com.jingdong.sdk.lib.puppetlayout.g.a;
import com.jingdong.sdk.lib.puppetlayout.g.b;

/* loaded from: classes8.dex */
public class LayoutUtils {
    private static final String FORMAT_DIMENSION = "";
    private static final String FORMAT_ENUM = "";
    private static final String FORMAT_FLOAT = "";
    private static final String FORMAT_STRING = "";
    public String format = "";
    public String name;

    public static boolean canUnitDp(String str) {
        return str.equals(ViewProps.BORDER_LEFT_WIDTH) || str.equals(ViewProps.BORDER_TOP_WIDTH) || str.equals(ViewProps.BORDER_RIGHT_WIDTH) || str.equals(ViewProps.BORDER_BOTTOM_WIDTH) || str.equals(ViewProps.BORDER_START_WIDTH) || str.equals(ViewProps.BORDER_END_WIDTH) || str.equals("borderHorizontal") || str.equals("borderVertical") || str.equals("border") || str.equals("height") || str.equals("marginLeft") || str.equals("marginTop") || str.equals("marginRight") || str.equals("marginBottom") || str.equals("marginStart") || str.equals(ViewProps.BORDER_END_WIDTH) || str.equals("borderHorizontal") || str.equals("marginVertical") || str.equals("margin") || str.equals("maxHeight") || str.equals("maxWidth") || str.equals(ViewProps.MIN_HEIGHT) || str.equals(ViewProps.MIN_WIDTH) || str.equals("paddingLeft") || str.equals("paddingTop") || str.equals("paddingRight") || str.equals("paddingBottom") || str.equals("paddingStart") || str.equals("paddingEnd") || str.equals("paddingHorizontal") || str.equals("paddingVertical") || str.equals("padding") || str.equals("left") || str.equals("top") || str.equals("right") || str.equals("bottom") || str.equals("start") || str.equals("end") || str.equals("positionHorizontal") || str.equals("positionVertical") || str.equals("positionAll") || str.equals("width") || str.equals(ViewProps.FLEX_BASIS);
    }

    public static float getDpValue(String str) {
        return getDpValue(str, 0.0f);
    }

    public static float getValue(String str, String str2) {
        int ordinal;
        int i2 = 0;
        if (ViewProps.ALIGN_CONTENT.equals(str)) {
            yg_alignContent[] values = yg_alignContent.values();
            int length = values.length;
            while (i2 < length) {
                yg_alignContent yg_aligncontent = values[i2];
                if (yg_aligncontent.tName.equals(str2)) {
                    ordinal = yg_aligncontent.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.ALIGN_ITEMS.equals(str)) {
            yg_alignItems[] values2 = yg_alignItems.values();
            int length2 = values2.length;
            while (i2 < length2) {
                yg_alignItems yg_alignitems = values2[i2];
                if (yg_alignitems.tName.equals(str2)) {
                    ordinal = yg_alignitems.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.ALIGN_SELF.equals(str)) {
            yg_alignSelf[] values3 = yg_alignSelf.values();
            int length3 = values3.length;
            while (i2 < length3) {
                yg_alignSelf yg_alignself = values3[i2];
                if (yg_alignself.tName.equals(str2)) {
                    ordinal = yg_alignself.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if ("direction".equals(str)) {
            yg_direction[] values4 = yg_direction.values();
            int length4 = values4.length;
            while (i2 < length4) {
                yg_direction yg_directionVar = values4[i2];
                if (yg_directionVar.tName.equals(str2)) {
                    ordinal = yg_directionVar.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.DISPLAY.equals(str)) {
            yg_display[] values5 = yg_display.values();
            int length5 = values5.length;
            while (i2 < length5) {
                yg_display yg_displayVar = values5[i2];
                if (yg_displayVar.tName.equals(str2)) {
                    ordinal = yg_displayVar.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.FLEX_DIRECTION.equals(str)) {
            yg_flexDirection[] values6 = yg_flexDirection.values();
            int length6 = values6.length;
            while (i2 < length6) {
                yg_flexDirection yg_flexdirection = values6[i2];
                if (yg_flexdirection.tName.equals(str2)) {
                    ordinal = yg_flexdirection.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.JUSTIFY_CONTENT.equals(str)) {
            yg_justifyContent[] values7 = yg_justifyContent.values();
            int length7 = values7.length;
            while (i2 < length7) {
                yg_justifyContent yg_justifycontent = values7[i2];
                if (yg_justifycontent.tName.equals(str2)) {
                    ordinal = yg_justifycontent.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.OVERFLOW.equals(str)) {
            yg_overflow[] values8 = yg_overflow.values();
            int length8 = values8.length;
            while (i2 < length8) {
                yg_overflow yg_overflowVar = values8[i2];
                if (yg_overflowVar.tName.equals(str2)) {
                    ordinal = yg_overflowVar.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if (ViewProps.FLEX_WRAP.equals(str)) {
            yg_wrap[] values9 = yg_wrap.values();
            int length9 = values9.length;
            while (i2 < length9) {
                yg_wrap yg_wrapVar = values9[i2];
                if (yg_wrapVar.tName.equals(str2)) {
                    ordinal = yg_wrapVar.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else if ("position".equals(str)) {
            yg_positionType[] values10 = yg_positionType.values();
            int length10 = values10.length;
            while (i2 < length10) {
                yg_positionType yg_positiontype = values10[i2];
                if (yg_positiontype.tName.equals(str2)) {
                    ordinal = yg_positiontype.ordinal();
                } else {
                    i2++;
                }
            }
            return -1.0f;
        } else {
            return -1.0f;
        }
        return ordinal;
    }

    /* loaded from: classes8.dex */
    enum yg_alignContent {
        auto,
        flex_start("flex-start"),
        center,
        flex_end("flex-end"),
        stretch,
        baseline;
        
        public final String tName;

        yg_alignContent(String str) {
            this.tName = str;
        }

        yg_alignContent() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_alignItems {
        auto,
        flex_start("flex-start"),
        center,
        flex_end("flex-end"),
        stretch,
        baseline;
        
        public final String tName;

        yg_alignItems(String str) {
            this.tName = str;
        }

        yg_alignItems() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_alignSelf {
        auto,
        flex_start("flex-start"),
        center,
        flex_end("flex-end"),
        stretch,
        baseline;
        
        public final String tName;

        yg_alignSelf(String str) {
            this.tName = str;
        }

        yg_alignSelf() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_direction {
        inherit,
        ltr,
        rtl;
        
        public final String tName;

        yg_direction(String str) {
            this.tName = str;
        }

        yg_direction() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_display {
        flex,
        none;
        
        public final String tName;

        yg_display(String str) {
            this.tName = str;
        }

        yg_display() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_flexDirection {
        col,
        column_reverse("col-reverse"),
        row,
        row_reverse("row-reverse");
        
        public final String tName;

        yg_flexDirection(String str) {
            this.tName = str;
        }

        yg_flexDirection() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_justifyContent {
        flex_start("flex-start"),
        center,
        flex_end("flex-end"),
        space_between("space-between"),
        space_around("space-around");
        
        public final String tName;

        yg_justifyContent(String str) {
            this.tName = str;
        }

        yg_justifyContent() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_overflow {
        visible,
        hidden,
        scroll;
        
        private final String tName;

        yg_overflow(String str) {
            this.tName = str;
        }

        yg_overflow() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_positionType {
        relative,
        absolute;
        
        private final String tName;

        yg_positionType(String str) {
            this.tName = str;
        }

        yg_positionType() {
            this.tName = name();
        }
    }

    /* loaded from: classes8.dex */
    enum yg_wrap {
        no_wrap("no-wrap"),
        wrap,
        wrap_reverse("wrap-reverse");
        
        private final String tName;

        yg_wrap(String str) {
            this.tName = str;
        }

        yg_wrap() {
            this.tName = name();
        }
    }

    public static float getDpValue(String str, float f2) {
        float floatValue;
        try {
            if (TextUtils.isEmpty(str)) {
                return f2;
            }
            if (str.endsWith("dp")) {
                if (str.length() <= 2) {
                    return f2;
                }
                floatValue = a.a(Float.valueOf(str.substring(0, str.length() - 2)).floatValue());
            } else if (str.endsWith("px")) {
                if (str.length() <= 2) {
                    return f2;
                }
                floatValue = Float.valueOf(str.substring(0, str.length() - 2)).floatValue();
            } else {
                return a.a(Float.valueOf(str).floatValue());
            }
            return floatValue;
        } catch (Exception e2) {
            if (b.a) {
                e2.printStackTrace();
                return f2;
            }
            return f2;
        }
    }
}
