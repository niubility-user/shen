package g.d.a.j;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/* loaded from: classes12.dex */
public class h {
    public static String a(int[] iArr) {
        StringBuilder sb = new StringBuilder();
        for (int i2 : iArr) {
            sb.append((char) i2);
        }
        return sb.toString();
    }

    public static int b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e2) {
            d.a("getVersionCode--Exception:" + e2.getMessage());
            return 0;
        }
    }

    public static int c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (Exception e2) {
            d.a("getVersionCode--Exception:" + e2.getMessage());
            return 0;
        }
    }

    public static String d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e2) {
            d.a("getVersionName--Exception:" + e2.getMessage());
            return "0";
        }
    }

    public static String e(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (Exception e2) {
            d.a("getVersionName--Exception:" + e2.getMessage());
            return null;
        }
    }

    public static boolean f(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (PackageManager.NameNotFoundException e2) {
            d.b("isExistPackage NameNotFoundException:" + e2.getMessage());
            return false;
        }
    }

    public static boolean g(Context context, String str, String str2) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e2) {
            d.b("isSupportPush NameNotFoundException:" + e2.getMessage());
            applicationInfo = null;
        }
        return applicationInfo != null && applicationInfo.metaData.getBoolean(str2, false);
    }

    public static boolean h(Context context) {
        return g.d.a.b.s().M(context);
    }

    public static int i(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e2) {
                d.b("parseInt--NumberFormatException" + e2.getMessage());
            }
        }
        return -1;
    }
}
