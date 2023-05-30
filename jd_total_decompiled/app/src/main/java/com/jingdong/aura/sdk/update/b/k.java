package com.jingdong.aura.sdk.update.b;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/* loaded from: classes4.dex */
public final class k {
    private static String a = null;
    private static int b = 0;

    /* renamed from: c  reason: collision with root package name */
    private static String f12246c = "";

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            PackageInfo c2 = c(context);
            a = c2 == null ? "" : c2.versionName;
        }
        return a;
    }

    public static int b(Context context) {
        if (b == 0) {
            PackageInfo c2 = c(context);
            b = c2 == null ? 0 : c2.versionCode;
        }
        return b;
    }

    private static PackageInfo c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }
}
