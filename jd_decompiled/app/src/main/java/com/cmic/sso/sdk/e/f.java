package com.cmic.sso.sdk.e;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.huawei.hms.framework.common.ContainerUtils;

/* loaded from: classes.dex */
public class f {
    public static String a(Context context) {
        String str = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            String str2 = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(d(context), 0));
            if (str2 != null) {
                return str2;
            }
            try {
                PackageInfo c2 = c(context);
                if (c2 == null) {
                    return null;
                }
                return context.getResources().getString(c2.applicationInfo.labelRes);
            } catch (Exception e2) {
                e = e2;
                str = str2;
                e.printStackTrace();
                return str;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static String b(Context context) {
        try {
            PackageInfo c2 = c(context);
            if (c2 != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(d(context));
                sb.append(ContainerUtils.FIELD_DELIMITER);
                sb.append(c2.versionName);
                return sb.toString();
            }
            return "";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static PackageInfo c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static String d(Context context) {
        PackageInfo c2 = c(context);
        return c2 == null ? "" : c2.packageName;
    }
}
