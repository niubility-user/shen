package com.jingdong.sdk.utils;

import android.app.Application;
import android.content.pm.PackageInfo;

/* loaded from: classes10.dex */
public class PackageInfoUtil {
    private static PackageInfo getPackageInfo(Application application) {
        try {
            return application.getPackageManager().getPackageInfo(application.getPackageName(), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public static int getVersionCode(Application application) {
        PackageInfo packageInfo = getPackageInfo(application);
        if (packageInfo == null) {
            return 0;
        }
        return packageInfo.versionCode;
    }

    public static String getVersionName(Application application) {
        PackageInfo packageInfo = getPackageInfo(application);
        return packageInfo == null ? "" : packageInfo.versionName;
    }
}
