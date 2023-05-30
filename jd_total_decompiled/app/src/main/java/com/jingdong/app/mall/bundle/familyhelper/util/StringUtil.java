package com.jingdong.app.mall.bundle.familyhelper.util;

/* loaded from: classes18.dex */
public class StringUtil {
    private static final String IMAGE_URL_HOST = "https://m.360buyimg.com/njmobilecms/";

    public static boolean isEmpty(String str) {
        return str == null || str == "";
    }

    public static String verifyString(String str, String str2) {
        return isEmpty(str) ? str2 : str;
    }

    public static String verifyUri(String str, String str2) {
        if (isEmpty(str)) {
            return str2;
        }
        if (str.startsWith("http")) {
            return str;
        }
        if (str.startsWith("jfs")) {
            return IMAGE_URL_HOST + str;
        }
        return str2;
    }
}
