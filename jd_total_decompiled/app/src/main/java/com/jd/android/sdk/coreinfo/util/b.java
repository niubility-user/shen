package com.jd.android.sdk.coreinfo.util;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: classes.dex */
public final class b {
    public static boolean a(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager != null && packageManager.checkPermission(str, context.getPackageName()) == 0;
    }
}
