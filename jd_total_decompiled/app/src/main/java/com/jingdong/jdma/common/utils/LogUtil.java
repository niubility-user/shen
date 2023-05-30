package com.jingdong.jdma.common.utils;

/* loaded from: classes12.dex */
public final class LogUtil {
    private static boolean isDebug;

    public static void d(String str, String str2) {
        if (isDebug) {
            getTag(str);
        }
    }

    public static void debug(boolean z) {
        isDebug = z;
    }

    public static void e(String str, String str2) {
        if (isDebug) {
            getTag(str);
        }
    }

    private static String getTag(String str) {
        return "JDMASDK".concat(":").concat(str);
    }

    public static void i(String str, String str2) {
        if (isDebug) {
            getTag(str);
        }
    }

    public static boolean isDebug() {
        return isDebug;
    }

    public static void v(String str, String str2) {
        if (isDebug) {
            getTag(str);
        }
    }

    public static void w(String str, String str2) {
        if (isDebug) {
            getTag(str);
        }
    }

    public static void d(String str) {
        if (isDebug) {
            getTag("");
        }
    }

    public static void e(String str) {
        if (isDebug) {
            getTag("");
        }
    }

    public static void i(String str) {
        if (isDebug) {
            getTag("");
        }
    }

    public static void v(String str) {
        if (isDebug) {
            getTag("");
        }
    }

    public static void w(String str) {
        if (isDebug) {
            getTag("");
        }
    }
}
