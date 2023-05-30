package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Build;
import com.tencent.connect.common.Constants;
import com.tencent.smtt.utils.TbsLog;
import java.io.File;

/* loaded from: classes9.dex */
public class TbsShareManager {
    private static Context a;
    private static boolean b;
    public static boolean mHasQueried;

    @Deprecated
    public static int findCoreForThirdPartyApp(Context context) {
        return -1;
    }

    @Deprecated
    public static File getBackupCoreFile(Context context, String str) {
        return null;
    }

    @Deprecated
    public static int getBackupCoreVersion(Context context, String str) {
        return 0;
    }

    @Deprecated
    public static File getBackupDecoupleCoreFile(Context context, String str) {
        return null;
    }

    @Deprecated
    public static int getBackupDecoupleCoreVersion(Context context, String str) {
        return 0;
    }

    @Deprecated
    public static boolean getCoreDisabled() {
        return false;
    }

    @Deprecated
    public static String[] getCoreProviderAppList() {
        return new String[0];
    }

    @Deprecated
    public static int getCoreShareDecoupleCoreVersion(Context context, String str) {
        return 0;
    }

    @Deprecated
    public static String getHostCorePathAppDefined() {
        return "";
    }

    public static Context getPackageContext(Context context, String str, boolean z) {
        if (context == null || !context.getPackageName().equals(str)) {
            if (context == null) {
                TbsLog.e("TbsShareManager", "getPackageContext appContext is null!!");
                return null;
            }
            if (z) {
                try {
                    if (!context.getPackageName().equals(str) && (TbsPVConfig.getInstance(context).isEnableNoCoreGray() || Build.VERSION.SDK_INT >= 29)) {
                        return null;
                    }
                } catch (Throwable unused) {
                    return null;
                }
            }
            return context.createPackageContext(str, 2);
        }
        return context;
    }

    @Deprecated
    public static File getSDCoreFile(Context context, String str, int i2) {
        return null;
    }

    @Deprecated
    public static int getSharedTbsCoreVersion(Context context, String str) {
        return 0;
    }

    @Deprecated
    public static File getStableCoreFile(Context context, String str) {
        return null;
    }

    @Deprecated
    public static int getStableCoreVersion(Context context, String str) {
        return 0;
    }

    @Deprecated
    public static int getTbsStableCoreVersion(Context context, int i2) {
        return 0;
    }

    public static boolean isThirdPartyApp(Context context) {
        Context context2;
        try {
            context2 = a;
        } catch (Throwable th) {
            TbsLog.i(th);
        }
        if (context2 == null || !context2.equals(context.getApplicationContext())) {
            Context applicationContext = context.getApplicationContext();
            a = applicationContext;
            String packageName = applicationContext.getPackageName();
            String[] strArr = {TbsConfig.APP_DEMO, "com.tencent.mm", "com.tencent.mobileqq", "com.qzone", Constants.PACKAGE_QQ_SPEED};
            for (int i2 = 0; i2 < 5; i2++) {
                if (packageName.equals(strArr[i2])) {
                    b = false;
                    return false;
                }
            }
            b = true;
            return true;
        }
        return b;
    }

    @Deprecated
    public static void setHostCorePathAppDefined(String str) {
    }
}
