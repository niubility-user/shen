package com.jingdong.jdreact.plugin.utils;

/* loaded from: classes14.dex */
public class LogUtil {
    private static boolean IS_PRINTABLE;

    public static void d(String str, Object obj) {
        if (IS_PRINTABLE && obj != null) {
            obj.toString();
        }
    }

    public static void e(String str, Object obj) {
        if (IS_PRINTABLE && obj != null) {
            obj.toString();
        }
    }

    public static void i(String str, Object obj) {
        if (IS_PRINTABLE && obj != null) {
            obj.toString();
        }
    }

    public static void init(boolean z) {
        IS_PRINTABLE = z;
    }

    public static boolean isOpen() {
        return IS_PRINTABLE;
    }

    public static void v(String str, Object obj) {
        if (IS_PRINTABLE && obj != null) {
            obj.toString();
        }
    }

    public static void w(String str, Object obj) {
        if (IS_PRINTABLE && obj != null) {
            obj.toString();
        }
    }
}
