package com.jingdong.common.unification.customtheme;

import android.text.TextUtils;
import com.jd.lib.un.business.widget.a;
import com.jingdong.jdsdk.constant.CartConstant;

/* loaded from: classes6.dex */
public class UnCustomThemeUtils {
    public static String createImageId(String str, String str2, String str3, boolean z) {
        if (z) {
            return createImageIdByDark(str, str2, str3);
        }
        return createImageId(str, str2, str3);
    }

    public static String createImageIdAuto(String str, String str2, String str3) {
        if (a.g().p()) {
            return createImageIdByDark(str, str2, str3);
        }
        return createImageId(str, str2, str3);
    }

    public static String createImageIdByDark(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return "";
        }
        return "dark_" + str + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3;
    }

    public static String createImageId(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return "";
        }
        return str + CartConstant.KEY_YB_INFO_LINK + str2 + CartConstant.KEY_YB_INFO_LINK + str3;
    }
}
