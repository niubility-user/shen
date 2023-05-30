package com.jdjr.risk.util.a;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes18.dex */
public class e {
    public static boolean a(Context context, String str) {
        PackageManager packageManager;
        return (context == null || (packageManager = context.getPackageManager()) == null || packageManager.checkPermission(str, context.getPackageName()) != 0) ? false : true;
    }
}
