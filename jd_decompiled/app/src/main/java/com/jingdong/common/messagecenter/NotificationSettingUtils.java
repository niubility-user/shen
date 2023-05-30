package com.jingdong.common.messagecenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import com.jingdong.jdsdk.JdSdk;
import com.jingdong.jdsdk.res.StringUtil;

/* loaded from: classes5.dex */
public class NotificationSettingUtils {
    public static void jumpToAppInfoSetting(Context context) {
        try {
            if ((RomUtil.getDevice() == 1) & (Build.VERSION.SDK_INT >= 26)) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.setClassName("com.android.settings", "com.android.settings.Settings$NotificationFilterActivity");
                intent.addFlags(32768);
                intent.addFlags(268435456);
                intent.putExtra("appName", StringUtil.app_name);
                intent.putExtra("packageName", JdSdk.getInstance().getApplication().getPackageName());
                context.startActivity(intent);
            } else {
                Intent intent2 = new Intent();
                intent2.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent2.addCategory("android.intent.category.DEFAULT");
                intent2.setData(Uri.parse("package:" + context.getPackageName()));
                context.startActivity(intent2);
            }
        } catch (Exception unused) {
        }
    }

    public static boolean jumpToNotificationSetting(Context context) {
        try {
            String packageName = context.getPackageName();
            Intent intent = new Intent();
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 26) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);
            } else if (i2 >= 21) {
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
                intent.putExtra("app_package", packageName);
                intent.putExtra("app_uid", context.getApplicationInfo().uid);
            } else {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", packageName, null));
            }
            if (!(context instanceof Activity)) {
                intent.addFlags(268435456);
            }
            context.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
