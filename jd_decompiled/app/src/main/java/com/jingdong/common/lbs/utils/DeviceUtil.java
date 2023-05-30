package com.jingdong.common.lbs.utils;

import android.content.pm.PackageInfo;
import android.os.Build;
import android.text.TextUtils;
import com.jingdong.common.lbs.proxy.LBSListener;

/* loaded from: classes.dex */
public class DeviceUtil {
    public static final String SDK_VERSION = "1.0.7";
    private static String appkey = "";
    private static String deviceName = "";
    private static boolean hostDebug = false;
    public static String[] location = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
    private static LBSListener mListener = null;
    private static String packageName = "";
    private static String versionCode = "";
    private static String versionName = "";

    public static String getAppKey() {
        return notNull(appkey);
    }

    public static String getAppPackageName() {
        try {
            if (TextUtils.isEmpty(packageName)) {
                packageName = notNull(com.jingdong.common.lbs.proxy.a.a.getPackageName());
            }
            return packageName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getAppVersionCode() {
        try {
            if (TextUtils.isEmpty(versionCode)) {
                PackageInfo packageInfo = com.jingdong.common.lbs.proxy.a.a.getPackageManager().getPackageInfo(getAppPackageName(), 0);
                if (packageInfo == null) {
                    return "";
                }
                StringBuilder sb = new StringBuilder();
                sb.append(packageInfo.versionCode);
                versionCode = sb.toString();
            }
            return versionCode;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getAppVersionName() {
        try {
            if (TextUtils.isEmpty(versionName)) {
                PackageInfo packageInfo = com.jingdong.common.lbs.proxy.a.a.getPackageManager().getPackageInfo(getAppPackageName(), 0);
                if (packageInfo == null) {
                    return "";
                }
                versionName = packageInfo.versionName;
            }
            return versionName;
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getDeviceName() {
        return notNull(deviceName);
    }

    public static String getPin() {
        try {
            LBSListener lBSListener = mListener;
            return lBSListener == null ? "" : notNull(lBSListener.getPin());
        } catch (Exception unused) {
            return "";
        }
    }

    public static String getSDKVersion() {
        return SDK_VERSION;
    }

    public static String getUUID() {
        try {
            LBSListener lBSListener = mListener;
            return lBSListener == null ? "" : notNull(lBSListener.getUUID());
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean hasLocationPermission() {
        return Build.VERSION.SDK_INT >= 26 ? hasPermission(location) : hasPermission("android.permission.ACCESS_COARSE_LOCATION");
    }

    public static boolean hasPermission(String str) {
        return com.jingdong.common.lbs.proxy.a.a.getPackageManager().checkPermission(str, com.jingdong.common.lbs.proxy.a.a.getPackageName()) == 0;
    }

    public static boolean hasPermission(String[] strArr) {
        try {
            for (String str : strArr) {
                if (-1 == com.jingdong.common.lbs.proxy.a.a.getPackageManager().checkPermission(str, com.jingdong.common.lbs.proxy.a.a.getPackageName())) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean hasPrivacy() {
        try {
            LBSListener lBSListener = mListener;
            if (lBSListener == null) {
                return false;
            }
            return lBSListener.hasPrivacy();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isAppForeground() {
        try {
            LBSListener lBSListener = mListener;
            if (lBSListener == null) {
                return false;
            }
            return lBSListener.isAppForeground();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isHostDebug() {
        return hostDebug;
    }

    public static boolean isSceneAllowed(String str) {
        try {
            LBSListener lBSListener = mListener;
            if (lBSListener == null) {
                return false;
            }
            return lBSListener.isSceneAllowed(str);
        } catch (Exception unused) {
            return false;
        }
    }

    public static String notNull(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static void setAppKey(String str) {
        appkey = str;
    }

    public static void setDeviceName(String str) {
        deviceName = str;
    }

    public static void setHostDebug(boolean z) {
        hostDebug = z;
    }

    public static void setLBSListener(LBSListener lBSListener) {
        mListener = lBSListener;
    }
}
