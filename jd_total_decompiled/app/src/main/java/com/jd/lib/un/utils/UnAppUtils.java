package com.jd.lib.un.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import jd.wjlogin_sdk.util.f;

/* loaded from: classes16.dex */
public class UnAppUtils {
    public static boolean isInstalled(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        return packageInfo != null;
    }

    public static boolean jdIsInstalled(Context context) {
        return isInstalled(context, f.f19954c);
    }

    public static void startAppWithUrl(Activity activity, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        intent.setFlags(268435456);
        activity.startActivity(intent);
    }

    public static void startJDWithUrl(Activity activity, String str) {
        if (activity == null || TextUtils.isEmpty(str) || !str.startsWith("openapp.jdmobile")) {
            return;
        }
        startAppWithUrl(activity, str);
    }
}
