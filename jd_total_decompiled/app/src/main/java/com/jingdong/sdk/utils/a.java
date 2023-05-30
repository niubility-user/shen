package com.jingdong.sdk.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/* loaded from: classes.dex */
public class a {
    public static Bundle a(Context context) {
        ApplicationInfo applicationInfo;
        if (context == null) {
            return null;
        }
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            return applicationInfo.metaData;
        }
        return null;
    }
}
