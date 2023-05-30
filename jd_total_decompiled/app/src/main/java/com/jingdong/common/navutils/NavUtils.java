package com.jingdong.common.navutils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.jingdong.common.deeplinkhelper.DeepLinkSwitch;
import com.jingdong.common.navutils.NavCenterParser;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class NavUtils {
    private static final String BUNDLE_NAME_KEY = "bundleName";
    private static final String MAIN_APP = "main";
    private static final String TAG = "NavUtils";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String findBundleNameByComponentName(Context context, ComponentName componentName) {
        try {
            ActivityInfo activityInfo = context.getPackageManager().getActivityInfo(componentName, 128);
            if (activityInfo == null) {
                return null;
            }
            Bundle bundle = activityInfo.metaData;
            if (bundle != null && bundle.containsKey(BUNDLE_NAME_KEY)) {
                return activityInfo.metaData.getString(BUNDLE_NAME_KEY);
            }
            return "main";
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String findBundleNameByNavActivityInfo(NavCenterParser.NavActivityInfo navActivityInfo) {
        return TextUtils.isEmpty(navActivityInfo.bundleName) ? "main" : navActivityInfo.bundleName;
    }

    public static boolean isBundleSwitchOpen(String str) {
        if ("main".equals(str)) {
            return true;
        }
        return DeepLinkSwitch.getInstance().isSwitchOpen(str);
    }

    public static boolean isDegradeToH5(String str) {
        if ("main".equals(str)) {
            return false;
        }
        return DegradeConfig.getInstance().isDegradeToH5(str);
    }

    public static boolean isDegradeToOrigin(String str) {
        if ("main".equals(str)) {
            return false;
        }
        return DegradeConfig.getInstance().isDegradeToOrigin(str);
    }

    public static boolean isPageDegradeToH5(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (Uri.parse(str).getBooleanQueryParameter("isDegrade2H5", false)) {
                return true;
            }
            Intent intent = new Intent();
            intent.setData(Uri.parse(str));
            intent.setPackage(context.getPackageName());
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null) {
                return false;
            }
            ActivityInfo activityInfo = resolveActivity.activityInfo;
            String findBundleNameByComponentName = findBundleNameByComponentName(context, new ComponentName(activityInfo.packageName, activityInfo.name));
            return !TextUtils.isEmpty(findBundleNameByComponentName) && isDegradeToH5(findBundleNameByComponentName);
        } catch (Exception e2) {
            OKLog.e(TAG, e2);
            return false;
        }
    }

    public static boolean isUsePlugin(String str) {
        if ("main".equals(str)) {
            return true;
        }
        return DegradeConfig.getInstance().isNotDegrade(str) && isBundleSwitchOpen(str);
    }

    public static boolean shouldNavToNative(Context context, String str) {
        if (!DegradeConfig.getInstance().shouldNavWeb2Native() || TextUtils.isEmpty(str) || Uri.parse(str).getBooleanQueryParameter("isDegrade2H5", false)) {
            return false;
        }
        Intent intent = new Intent();
        intent.setData(Uri.parse(str));
        intent.setPackage(context.getPackageName());
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || context.getClass().getName().contains(resolveActivity.activityInfo.name)) {
            return false;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        String findBundleNameByComponentName = findBundleNameByComponentName(context, new ComponentName(activityInfo.packageName, activityInfo.name));
        if (TextUtils.isEmpty(findBundleNameByComponentName)) {
            return false;
        }
        return isUsePlugin(findBundleNameByComponentName) || isDegradeToOrigin(findBundleNameByComponentName);
    }
}
