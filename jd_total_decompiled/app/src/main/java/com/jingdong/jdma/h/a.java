package com.jingdong.jdma.h;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;

/* loaded from: classes12.dex */
public class a {
    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !TextUtils.isDigitsOnly(str) || !TextUtils.isDigitsOnly(str2)) {
            return false;
        }
        try {
            return Double.valueOf(str).doubleValue() >= Double.valueOf(str2).doubleValue();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && TextUtils.isDigitsOnly(str) && TextUtils.isDigitsOnly(str2)) {
            try {
                return Double.valueOf(str).doubleValue() > Double.valueOf(str2).doubleValue();
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }
        return false;
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str.equals(str2);
    }

    public static int d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "100";
        }
        if (TextUtils.isDigitsOnly(str2) && Integer.valueOf(str2).intValue() != 0) {
            return com.jingdong.jdma.common.utils.b.a(str, Integer.valueOf(str2).intValue());
        }
        return -1;
    }

    public static boolean e(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Boolean.valueOf(str).booleanValue() || Boolean.valueOf(str2).booleanValue();
    }

    public static boolean f(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            for (String str3 : str2.contains(DYConstants.DY_REGEX_COMMA) ? str2.split(DYConstants.DY_REGEX_COMMA) : new String[]{str2}) {
                if (!TextUtils.isEmpty(str3) && str.equals(str3.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int g(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !TextUtils.isDigitsOnly(str) || !TextUtils.isDigitsOnly(str2) || Integer.valueOf(str2).intValue() == 0) {
            return -1;
        }
        try {
            return (int) (Long.valueOf(str).longValue() % Integer.valueOf(str2).intValue());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public static boolean h(String str, String str2) {
        return !c(str, str2);
    }

    public static boolean i(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !TextUtils.isDigitsOnly(str) || !TextUtils.isDigitsOnly(str2)) {
            return false;
        }
        try {
            return Double.valueOf(str).doubleValue() <= Double.valueOf(str2).doubleValue();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean j(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !TextUtils.isDigitsOnly(str) || !TextUtils.isDigitsOnly(str2)) {
            return false;
        }
        try {
            return Double.valueOf(str).doubleValue() < Double.valueOf(str2).doubleValue();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static boolean k(String str, String str2) {
        return !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && Boolean.valueOf(str).booleanValue() && Boolean.valueOf(str2).booleanValue();
    }

    public static String a(String str, String str2, String str3) {
        int i2;
        int i3;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || !TextUtils.isDigitsOnly(str2) || !TextUtils.isDigitsOnly(str3)) {
            return "";
        }
        int i4 = 0;
        try {
            i2 = Integer.valueOf(str2).intValue();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        try {
            i4 = Integer.valueOf(str3).intValue();
        } catch (NumberFormatException e3) {
            e3.printStackTrace();
        }
        if (i2 < 0 || i4 <= 0 || i2 >= str.length() || i4 > str.length() || (i3 = i4 + i2) > str.length()) {
            return "";
        }
        try {
            return str.substring(i2, i3);
        } catch (StringIndexOutOfBoundsException e4) {
            e4.printStackTrace();
            return "";
        }
    }
}
