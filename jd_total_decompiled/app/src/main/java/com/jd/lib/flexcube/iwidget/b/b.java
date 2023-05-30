package com.jd.lib.flexcube.iwidget.b;

import java.util.regex.Pattern;

/* loaded from: classes14.dex */
public class b {
    private static Pattern a = Pattern.compile("^[\\d]*$");
    private static Pattern b = Pattern.compile("^[-\\+]?[.\\d]*$");

    public static boolean a(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return b.matcher(str).matches();
    }

    public static boolean b(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return a.matcher(str).matches();
    }

    public static int c(float f2) {
        return Math.round(f2);
    }

    public static int d(int i2, float f2) {
        return Math.round(i2 * f2);
    }

    public static float e(String str, int i2) {
        if (c.c(str)) {
            return i2;
        }
        float f2 = i2;
        try {
            String trim = str.trim();
            if (b(trim)) {
                f2 = Integer.valueOf(trim).intValue();
            }
            return a(trim) ? Double.valueOf(trim).floatValue() : f2;
        } catch (Exception e2) {
            e2.printStackTrace();
            return f2;
        }
    }

    public static int f(String str, int i2) {
        if (c.c(str)) {
            return i2;
        }
        try {
            return new Integer(str).intValue();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return i2;
        }
    }
}
