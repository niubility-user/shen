package com.jd.viewkit.tool;

import com.jd.dynamic.DYConstants;

/* loaded from: classes18.dex */
public class NumberTool {
    public static double defaultWidth = 1125.0d;

    public static boolean isInt(String str) {
        if (StringTool.isEmpty(str)) {
            return false;
        }
        try {
            String trim = str.trim();
            if (StringTool.isInteger(trim)) {
                Integer.valueOf(trim).intValue();
            }
            if (StringTool.isDouble(trim)) {
                Double.valueOf(trim).intValue();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static int px2size(int i2, int i3) {
        double d = i2;
        double d2 = defaultWidth;
        double d3 = i3;
        Double.isNaN(d3);
        Double.isNaN(d);
        return (int) Math.round(d * (d2 / d3));
    }

    public static int size2px(int i2, int i3) {
        double d = i2;
        double d2 = i3;
        double d3 = defaultWidth;
        Double.isNaN(d2);
        Double.isNaN(d);
        return (int) Math.round(d * (d2 / d3));
    }

    public static boolean valueOfBoolean(String str) {
        if (StringTool.isEmpty(str)) {
            return false;
        }
        if (str.equals(DYConstants.DY_TRUE)) {
            return true;
        }
        if (str.equals(DYConstants.DY_FALSE)) {
        }
        return false;
    }

    public static float valueOfFloat(String str) {
        if (StringTool.isEmpty(str)) {
            return 0.0f;
        }
        try {
            String trim = str.trim();
            return StringTool.isDouble(trim) ? Double.valueOf(trim).floatValue() : StringTool.isInteger(trim) ? Integer.valueOf(trim).intValue() : 0.0f;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0f;
        }
    }

    public static int valueOfInt(String str) {
        if (StringTool.isEmpty(str)) {
            return 0;
        }
        try {
            String trim = str.trim();
            return StringTool.isDouble(trim) ? Double.valueOf(trim).intValue() : StringTool.isInteger(trim) ? Integer.valueOf(trim).intValue() : 0;
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0;
        }
    }

    public static Long valueOfLong(String str) {
        long j2 = 0;
        if (StringTool.isEmpty(str)) {
            return 0L;
        }
        try {
            j2 = Long.valueOf(str.trim()).intValue();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return Long.valueOf(j2);
    }
}
