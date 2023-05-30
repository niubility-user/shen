package com.huawei.hms.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.util.AndroidException;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/* loaded from: classes12.dex */
public class SystemUtils {
    public static final String UNKNOWN = "unknown";

    private static String a() {
        return getSystemProperties("ro.product.locale", "");
    }

    private static String b() {
        return getSystemProperties("ro.product.locale.region", "");
    }

    public static String getAndoridVersion() {
        return getSystemProperties("ro.build.version.release", "unknown");
    }

    public static String getLocalCountry() {
        Locale locale = Locale.getDefault();
        return locale != null ? locale.getCountry() : "";
    }

    public static String getManufacturer() {
        return getSystemProperties("ro.product.manufacturer", "unknown");
    }

    public static long getMegabyte(double d) {
        double d2 = Build.VERSION.SDK_INT > 25 ? 1000.0d : 1024.0d;
        return (long) (d * d2 * d2);
    }

    public static String getNetType(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        return (context == null || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) ? "" : activeNetworkInfo.getTypeName();
    }

    public static String getPhoneModel() {
        return getSystemProperties("ro.product.model", "unknown");
    }

    public static String getSystemProperties(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getDeclaredMethod(IMantoServerRequester.GET, String.class, String.class).invoke(cls, str, str2);
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            HMSLog.e("SystemUtils", "An exception occurred while reading: getSystemProperties:" + str);
            return str2;
        }
    }

    public static boolean isChinaROM() {
        String b = b();
        if (!TextUtils.isEmpty(b)) {
            return AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(b);
        }
        String a = a();
        if (!TextUtils.isEmpty(a)) {
            return a.toLowerCase(Locale.US).contains(AdvanceSetting.CLEAR_NOTIFICATION);
        }
        String localCountry = getLocalCountry();
        if (TextUtils.isEmpty(localCountry)) {
            return false;
        }
        return AdvanceSetting.CLEAR_NOTIFICATION.equalsIgnoreCase(localCountry);
    }

    public static boolean isEMUI() {
        StringBuilder sb = new StringBuilder();
        sb.append("is Emui :");
        int i2 = HwBuildEx.VERSION.EMUI_SDK_INT;
        sb.append(i2);
        HMSLog.i("SystemUtils", sb.toString());
        return i2 > 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean isSystemApp(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (AndroidException e2) {
            HMSLog.e("SystemUtils", "isSystemApp Exception: " + e2);
            packageInfo = null;
            if (packageInfo == null) {
            }
        } catch (RuntimeException e3) {
            HMSLog.e("SystemUtils", "isSystemApp RuntimeException:" + e3);
            packageInfo = null;
            if (packageInfo == null) {
            }
        }
        return packageInfo == null && (packageInfo.applicationInfo.flags & 1) > 0;
    }

    public static boolean isTVDevice() {
        return getSystemProperties("ro.build.characteristics", "default").equalsIgnoreCase("tv");
    }
}
