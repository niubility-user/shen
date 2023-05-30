package com.huawei.hms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import g.e.a.d;
import g.e.a.f;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes12.dex */
public class AGCUtils {
    /* JADX WARN: Removed duplicated region for block: B:14:0x0069 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static String a(Context context, String str) {
        String str2;
        InputStream inputStream = null;
        try {
            try {
                try {
                    f fVar = new f();
                    inputStream = context.getResources().getAssets().open("agconnect-services.json");
                    fVar.b(inputStream);
                    str2 = fVar.a(context).getString(str);
                } catch (NullPointerException e2) {
                    HMSLog.e("AGCUtils", "Get " + str + " with AGConnectServicesConfig failed: " + e2);
                    str2 = "";
                    IOUtils.closeQuietly(inputStream);
                    if (TextUtils.isEmpty(str2)) {
                    }
                }
            } catch (IOException e3) {
                HMSLog.e("AGCUtils", "Get " + str + " failed: " + e3);
                str2 = "";
                IOUtils.closeQuietly(inputStream);
                if (TextUtils.isEmpty(str2)) {
                }
            }
            IOUtils.closeQuietly(inputStream);
            if (TextUtils.isEmpty(str2)) {
                HMSLog.e("AGCUtils", "The " + str + " is null.");
                return "";
            }
            return str2;
        } catch (Throwable th) {
            IOUtils.closeQuietly(inputStream);
            throw th;
        }
    }

    private static String b(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.client.cpid")) != null) {
                String valueOf = String.valueOf(obj);
                return valueOf.startsWith("cpid=") ? valueOf.substring(5) : valueOf;
            }
            HMSLog.i("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (AndroidException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.");
            return "";
        } catch (RuntimeException e2) {
            HMSLog.e("AGCUtils", "In getMetaDataCpId, Failed to read meta data for the CpId.", e2);
            return "";
        }
    }

    private static boolean c(Context context) {
        return context.getPackageName().equals(HMSPackageManager.getInstance(context).getHMSPackageNameForMultiService());
    }

    public static String getAppId(Context context) {
        String str;
        if (c(context)) {
            str = a(context, "client/app_id");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        } else {
            str = null;
        }
        try {
            d c2 = d.c();
            if (c2.b() != context) {
                c2 = d.a(new f().a(context));
            }
            str = c2.d().getString("client/app_id");
        } catch (NullPointerException unused) {
            HMSLog.e("AGCUtils", "Get appId with AGConnectServicesConfig failed");
        }
        if (TextUtils.isEmpty(str)) {
            String a = a(context);
            return !TextUtils.isEmpty(a) ? a : a(context, "client/app_id");
        }
        return str;
    }

    public static String getCpId(Context context) {
        if (c(context)) {
            return a(context, "client/cp_id");
        }
        String str = null;
        try {
            d c2 = d.c();
            if (c2.b() != context) {
                c2 = d.a(new f().a(context));
            }
            str = c2.d().getString("client/cp_id");
        } catch (NullPointerException unused) {
            HMSLog.e("AGCUtils", "Get cpid with AGConnectServicesConfig failed");
        }
        if (TextUtils.isEmpty(str)) {
            String b = b(context);
            return !TextUtils.isEmpty(b) ? b : a(context, "client/cp_id");
        }
        return str;
    }

    private static String a(Context context) {
        Bundle bundle;
        Object obj;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to get 'PackageManager' instance.");
            return "";
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(context.getPackageName(), 128).applicationInfo;
            if (applicationInfo != null && (bundle = applicationInfo.metaData) != null && (obj = bundle.get("com.huawei.hms.client.appid")) != null) {
                String valueOf = String.valueOf(obj);
                return valueOf.startsWith("appid=") ? valueOf.substring(6) : valueOf;
            }
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (AndroidException unused) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.");
            return "";
        } catch (RuntimeException e2) {
            HMSLog.e("AGCUtils", "In getMetaDataAppId, Failed to read meta data for the AppID.", e2);
            return "";
        }
    }
}
