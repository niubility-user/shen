package com.jingdong.common.jdreactFramework.modules.uphone_sdk.FloatWindow.permission.rom;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.util.Log;
import com.huawei.hms.push.AttributionReporter;

/* loaded from: classes5.dex */
public class SmartisanUtils {
    private static final String TAG = "SmartisanUtils";

    public static void applyPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            Intent intent = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS_NEW");
            intent.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
            intent.putExtra("index", 17);
            startSafely(context, intent);
            return;
        }
        Intent intent2 = new Intent("com.smartisanos.security.action.SWITCHED_PERMISSIONS");
        intent2.setClassName("com.smartisanos.security", "com.smartisanos.security.SwitchedPermissions");
        intent2.putExtra(AttributionReporter.SYSTEM_PERMISSION, "android.permission.SYSTEM_ALERT_WINDOW");
        startSafely(context, intent2);
    }

    public static boolean checkFloatWindowPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            return checkOp(context, 24);
        }
        return true;
    }

    @TargetApi(19)
    private static boolean checkOp(Context context, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            try {
                Class cls = Integer.TYPE;
                return ((Integer) AppOpsManager.class.getDeclaredMethod("checkOp", cls, cls, String.class).invoke(appOpsManager, Integer.valueOf(i2), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0;
            } catch (Exception e2) {
                Log.getStackTraceString(e2);
            }
        }
        return false;
    }

    public static boolean startSafely(Context context, Intent intent) {
        if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        }
        String str = "Intent is not available! " + intent;
        return false;
    }
}
