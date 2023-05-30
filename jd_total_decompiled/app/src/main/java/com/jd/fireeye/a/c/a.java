package com.jd.fireeye.a.c;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

/* loaded from: classes13.dex */
public class a {
    public static String a(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return resolveActivity == null ? "" : f.b(resolveActivity.activityInfo.packageName);
    }
}
