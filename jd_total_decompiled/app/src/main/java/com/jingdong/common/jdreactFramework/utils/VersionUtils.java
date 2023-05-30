package com.jingdong.common.jdreactFramework.utils;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;

/* loaded from: classes5.dex */
public class VersionUtils {
    public static int compareVersion(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
                return 0;
            }
            if (TextUtils.isEmpty(str)) {
                return -1;
            }
            if (TextUtils.isEmpty(str2)) {
                return 1;
            }
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            if (split != null && split2 != null) {
                int min = Math.min(split.length, split2.length);
                int i2 = 0;
                for (int i3 = 0; i3 < min; i3++) {
                    String str3 = split[i3];
                    String str4 = split2[i3];
                    if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4)) {
                        break;
                    }
                    i2 = str3.length() - str4.length();
                    if (i2 == 0) {
                        i2 = str3.compareTo(str4);
                    }
                    if (i2 != 0) {
                        break;
                    }
                }
                if (i2 > 0) {
                    return 1;
                }
                if (i2 < 0) {
                    return -1;
                }
                if (split.length == split2.length) {
                    return 0;
                }
                return split.length > split2.length ? 1 : -1;
            }
            return 0;
        } catch (Exception unused) {
            return compareVersion2(str, str2);
        }
    }

    public static int compareVersion2(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        float floatVersionSafe = getFloatVersionSafe(str);
        float floatVersionSafe2 = getFloatVersionSafe(str2);
        if (floatVersionSafe > floatVersionSafe2) {
            return 1;
        }
        return floatVersionSafe < floatVersionSafe2 ? -1 : 0;
    }

    private static float getFloatVersionSafe(String str) {
        try {
            try {
                return Float.parseFloat(str);
            } catch (Exception unused) {
                return 0.1f;
            }
        } catch (Exception unused2) {
            String[] split = str.split("\\.");
            StringBuilder sb = new StringBuilder();
            if (split.length > 2) {
                for (int i2 = 0; i2 < 2; i2++) {
                    sb.append(split[i2]);
                    if (i2 == 0) {
                        sb.append(OrderISVUtil.MONEY_DECIMAL_CHAR);
                    }
                }
                return Float.parseFloat(sb.toString());
            }
            return 0.1f;
        }
    }
}
