package com.jdjr.risk.device.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.jingdong.sdk.baseinfo.BaseInfo;

/* loaded from: classes18.dex */
public class g {
    public static String a() {
        try {
            return BaseInfo.getAndroidVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String a(Context context) {
        PackageManager packageManager;
        try {
            String hardwareSerialNo = (Build.VERSION.SDK_INT <= 25 || ((packageManager = context.getPackageManager()) != null && packageManager.checkPermission("android.permission.READ_PHONE_STATE", context.getPackageName()) == 0)) ? BaseInfo.getHardwareSerialNo() : "";
            return hardwareSerialNo == null ? "" : hardwareSerialNo;
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String b() {
        try {
            return BaseInfo.getDeviceModel();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String c() {
        try {
            return BaseInfo.getHostName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String d() {
        try {
            return BaseInfo.getTimeZoneID();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String e() {
        try {
            return BaseInfo.getAndroidSDKVersion() + "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String f() {
        try {
            return BaseInfo.getBootloaderVersion();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String g() {
        try {
            return BaseInfo.getDeviceProductName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String h() {
        try {
            return BaseInfo.getDeviceBrand();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String i() {
        try {
            return BaseInfo.getCountry();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String j() {
        try {
            return BaseInfo.getElapsedRealtime() + "";
        } catch (Exception unused) {
            return "";
        }
    }

    public static String k() {
        try {
            return BaseInfo.getDeviceName();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String l() {
        try {
            return BaseInfo.getLanguage();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String m() {
        try {
            return BaseInfo.getDeviceManufacture();
        } catch (Exception unused) {
            return "";
        }
    }

    public static String n() {
        String deviceProductName = BaseInfo.getDeviceProductName();
        return deviceProductName == null ? "" : deviceProductName;
    }

    public static String o() {
        try {
            return BaseInfo.getHardwareName();
        } catch (Exception unused) {
            return "";
        }
    }
}
