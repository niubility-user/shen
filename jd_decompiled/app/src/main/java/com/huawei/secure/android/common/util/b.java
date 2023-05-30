package com.huawei.secure.android.common.util;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes12.dex */
public class b {
    private static final Pattern a = Pattern.compile("[0-9]*[a-z|A-Z]*[\u4e00-\u9fa5]*");

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

    private static String b(String str, String str2) {
        StringBuilder sb = new StringBuilder(512);
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(a(str2));
        }
        return sb.toString();
    }

    private static String c(String str, boolean z) {
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

    public static void d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        c(str2, false);
    }

    public static void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return;
        }
        b(str2, str3);
    }

    public static void f(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        c(str2, false);
    }
}
