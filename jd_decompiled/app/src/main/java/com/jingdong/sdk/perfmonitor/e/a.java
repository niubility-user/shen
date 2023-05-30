package com.jingdong.sdk.perfmonitor.e;

import android.app.Application;
import android.content.pm.PackageInfo;

/* loaded from: classes12.dex */
public class a {
    private static int a;

    private static PackageInfo a(Application application) {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int b(Application application) {
        if (application != null) {
            if (a == 0) {
                PackageInfo a2 = a(application);
                a = a2 == null ? 0 : a2.versionCode;
            }
            return a;
        }
        throw new IllegalArgumentException("application must not be null");
    }
}
