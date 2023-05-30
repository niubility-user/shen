package com.jingdong.union.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/* loaded from: classes12.dex */
public class d {
    private static String a;
    private static int b;

    private static PackageInfo a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int b(Context context) {
        if (b == 0) {
            PackageInfo a2 = a(context);
            b = a2 == null ? 0 : a2.versionCode;
        }
        return b;
    }

    public static String c(Context context) {
        if (TextUtils.isEmpty(a)) {
            PackageInfo a2 = a(context);
            a = a2 == null ? "" : a2.versionName;
        }
        return a;
    }
}
