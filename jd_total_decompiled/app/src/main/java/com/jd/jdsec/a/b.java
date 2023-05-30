package com.jd.jdsec.a;

import android.content.Intent;
import android.content.pm.ResolveInfo;

/* loaded from: classes13.dex */
public class b {
    public static String a() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = com.jd.jdsec.c.g.a.getPackageManager().resolveActivity(intent, 0);
        return resolveActivity == null ? "" : com.jd.jdsec.a.l.e.h(resolveActivity.activityInfo.packageName);
    }
}
