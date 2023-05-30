package com.jingdong.common.messagecenter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes5.dex */
public class BadgeUtil {
    private static String getLauncherClassName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setPackage(context.getPackageName());
        intent.addCategory("android.intent.category.LAUNCHER");
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (resolveActivity == null) {
            resolveActivity = packageManager.resolveActivity(intent, 0);
        }
        return resolveActivity.activityInfo.name;
    }

    public static void resetBadgeCount(Context context) {
        if (BaseInfo.getDeviceManufacture().toLowerCase().contains("samsung")) {
            sendToSamsumg(context, 0);
        }
    }

    private static void sendToSamsumg(Context context, int i2) {
        String launcherClassName = getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", i2);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }
}
