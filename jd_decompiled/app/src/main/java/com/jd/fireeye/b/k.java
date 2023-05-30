package com.jd.fireeye.b;

import android.content.Context;

/* loaded from: classes13.dex */
public class k {
    public static final boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return !q.b() || context.getPackageManager().checkPermission("android.permission.BLUETOOTH", context.getPackageName()) == 0;
    }
}
