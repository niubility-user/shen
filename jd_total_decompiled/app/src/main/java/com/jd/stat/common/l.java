package com.jd.stat.common;

import android.content.Context;

/* loaded from: classes18.dex */
public class l {
    public static boolean a(Context context) {
        return false;
    }

    public static boolean a(Context context, String str) {
        if (context == null) {
            return false;
        }
        if (com.jd.stat.common.b.g.b()) {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        }
        return true;
    }

    public static final boolean b(Context context) {
        if (context == null) {
            return false;
        }
        if (com.jd.stat.common.b.g.b()) {
            return context.getPackageManager().checkPermission("android.permission.BLUETOOTH", context.getPackageName()) == 0;
        }
        return true;
    }
}
