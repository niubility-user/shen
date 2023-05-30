package com.jd.libs.hybrid.base.util;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes16.dex */
public class VersionUtils {
    public static int compareVersion(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return TextUtils.isEmpty(str2) ? 0 : -1;
        } else if (TextUtils.isEmpty(str2)) {
            return 1;
        } else {
            String[] split = str.split("\\.");
            String[] split2 = str2.split("\\.");
            if (split.length != split2.length) {
                return split2.length - split.length;
            }
            for (int i2 = 0; i2 < split.length; i2++) {
                int parseInt = Integer.parseInt(split[i2]) - Integer.parseInt(split2[i2]);
                if (parseInt != 0) {
                    return parseInt;
                }
            }
            return 0;
        }
    }

    public static boolean isAppVersionBetween(Context context, String str, String str2) {
        try {
            String str3 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            if (TextUtils.isEmpty(str) || (str.matches("\\d+\\.\\d+\\.\\d+") && compareVersion(str3, str) >= 0)) {
                if (TextUtils.isEmpty(str2)) {
                    return true;
                }
                if (str2.matches("\\d+\\.\\d+\\.\\d+")) {
                    return compareVersion(str3, str2) <= 0;
                }
                return false;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
