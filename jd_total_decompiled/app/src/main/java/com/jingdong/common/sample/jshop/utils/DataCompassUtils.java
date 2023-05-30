package com.jingdong.common.sample.jshop.utils;

import android.text.TextUtils;
import com.jd.dynamic.DYConstants;

/* loaded from: classes6.dex */
public class DataCompassUtils {
    public static final String MODULE_TYPE_COUPON = "coupon";
    public static final String MODULE_TYPE_FLOOR = "floor";
    public static final String MODULE_TYPE_HEAD = "head";
    public static final String MODULE_TYPE_MENU = "menu";
    public static final String MODULE_TYPE_SUBMENU = "submenu";

    public static String getModuleId(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            str3 = DYConstants.DY_NULL_STR;
        }
        return String.format("[%s-%s-%s]", str, str2, str3);
    }

    public static String getTunnelId(String str, String str2, String str3, String str4) {
        if (TextUtils.isEmpty(str3)) {
            str3 = DYConstants.DY_NULL_STR;
        }
        return String.format("[%s-%s-%s-%s]", str, str2, str3, str4);
    }

    public static String getTunnelId(String str, String str2, String str3, int i2) {
        if (TextUtils.isEmpty(str3)) {
            str3 = DYConstants.DY_NULL_STR;
        }
        return String.format("[%s-%s-%s-%d]", str, str2, str3, Integer.valueOf(i2));
    }
}
