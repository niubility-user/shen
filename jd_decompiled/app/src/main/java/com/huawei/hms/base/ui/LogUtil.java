package com.huawei.hms.base.ui;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class LogUtil {
    private static final Pattern a = Pattern.compile("[0-9]*[a-z|A-Z]*[\u4e00-\u9fa5]*");

    private static String a(String str, boolean z) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            if (z) {
                sb.append(a(str));
            } else {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static void e(String str, String str2, boolean z) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a(str2, z);
    }

    public static void e(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        a(str2, false);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int length = str.length();
        int i2 = 1;
        if (1 == length) {
            return String.valueOf('*');
        }
        StringBuilder sb = new StringBuilder(length);
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            if (a.matcher(String.valueOf(charAt)).matches()) {
                if (i2 % 2 == 0) {
                    charAt = '*';
                }
                i2++;
            }
            sb.append(charAt);
        }
        return sb.toString();
    }
}
