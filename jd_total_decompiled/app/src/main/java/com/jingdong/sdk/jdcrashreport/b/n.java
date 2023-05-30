package com.jingdong.sdk.jdcrashreport.b;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.sdk.baseinfo.BaseInfo;
import java.util.UUID;

/* loaded from: classes7.dex */
public class n {
    private static String a = null;
    private static String b = null;

    /* renamed from: c */
    private static String f14875c = null;
    private static String d = null;

    /* renamed from: e */
    private static int f14876e = -1;

    /* renamed from: f */
    private static String f14877f;

    /* renamed from: g */
    private static String f14878g;

    /* renamed from: h */
    private static String f14879h;

    /* renamed from: i */
    private static String f14880i;

    /* renamed from: j */
    private static String f14881j;

    /* renamed from: k */
    private static String f14882k;

    public static synchronized String a() {
        String f2;
        synchronized (n.class) {
            f2 = i.f("random_uuid", "");
            if (TextUtils.isEmpty(f2)) {
                r.b("JDCrashReport-DeviceUtils", "uuid: gen random uuid");
                f2 = UUID.randomUUID().toString();
                i.d("random_uuid", f2);
            }
        }
        return f2;
    }

    public static String b(Context context) {
        if (TextUtils.isEmpty(f14882k)) {
            if (context != null) {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 1024);
                    if (!TextUtils.isEmpty(applicationInfo.nativeLibraryDir)) {
                        String[] split = applicationInfo.nativeLibraryDir.split("/");
                        if (split.length > 0) {
                            if (split[split.length - 1].contains("64")) {
                                f14882k = "64";
                            } else {
                                f14882k = "32";
                            }
                            return f14882k;
                        }
                    }
                    String str = "getAppArch nativeLibraryDir: " + applicationInfo.nativeLibraryDir;
                } catch (PackageManager.NameNotFoundException e2) {
                    String str2 = "getAppArch failed: " + e2.getMessage();
                }
            }
            return "32";
        }
        return f14882k;
    }

    public static String c(boolean z) {
        if (TextUtils.isEmpty(a)) {
            try {
                String deviceBrand = BaseInfo.getDeviceBrand();
                a = deviceBrand;
                if (TextUtils.isEmpty(deviceBrand) && z) {
                    a = BaseInfo.getDeviceBrand();
                }
            } catch (Throwable th) {
                r.g("JDCrashReport-DeviceUtils", th);
                a = "";
            }
        }
        return TextUtils.isEmpty(a) ? "" : a;
    }

    public static String d() {
        if (TextUtils.isEmpty(f14875c)) {
            try {
                f14875c = BaseInfo.getAndroidVersion();
            } catch (Throwable th) {
                r.g("JDCrashReport-DeviceUtils", th);
                f14875c = "";
            }
        }
        return TextUtils.isEmpty(f14875c) ? "" : f14875c;
    }

    public static String e(boolean z) {
        if (TextUtils.isEmpty(b)) {
            try {
                String deviceModel = BaseInfo.getDeviceModel();
                b = deviceModel;
                if (TextUtils.isEmpty(deviceModel) && z) {
                    b = BaseInfo.getDeviceModel();
                }
            } catch (Throwable th) {
                r.g("JDCrashReport-DeviceUtils", th);
                b = "";
            }
        }
        return TextUtils.isEmpty(b) ? "" : b;
    }

    public static String f() {
        if (TextUtils.isEmpty(d)) {
            try {
                d = BaseInfo.getDisplayMetrics();
            } catch (Throwable th) {
                r.g("JDCrashReport-DeviceUtils", th);
                d = "";
            }
        }
        return TextUtils.isEmpty(d) ? "" : d;
    }

    public static int g() {
        if (f14876e < 0) {
            try {
                f14876e = BaseInfo.getAndroidSDKVersion();
            } catch (Throwable th) {
                r.g("JDCrashReport-DeviceUtils", th);
                f14876e = -1;
            }
        }
        return f14876e;
    }

    public static String h() {
        if (TextUtils.isEmpty(f14877f)) {
            f14877f = TextUtils.join(DYConstants.DY_REGEX_COMMA, BaseInfo.getDeviceSuppportedABIs());
        }
        return TextUtils.isEmpty(f14877f) ? "" : f14877f;
    }

    public static String i() {
        if (TextUtils.isEmpty(f14879h)) {
            f14879h = BaseInfo.getOSFingerprint();
        }
        return TextUtils.isEmpty(f14879h) ? "" : f14879h;
    }

    public static String j() {
        if (TextUtils.isEmpty(f14880i)) {
            f14880i = BaseInfo.getDeviceManufacture();
        }
        return TextUtils.isEmpty(f14880i) ? "" : f14880i;
    }

    public static String k() {
        String str;
        Throwable th;
        try {
            str = BaseInfo.getNetworkType().toUpperCase();
        } catch (Throwable th2) {
            str = "unknown";
            th = th2;
        }
        try {
        } catch (Throwable th3) {
            th = th3;
            r.g("JDCrashReport-DeviceUtils", th);
            return str;
        }
        if (TextUtils.isEmpty(str) || "mobile".equalsIgnoreCase(str)) {
            return "unknown";
        }
        if ("none".equalsIgnoreCase(str)) {
            return "unknown";
        }
        return str;
    }

    public static String l() {
        if (TextUtils.isEmpty(f14878g)) {
            if (s.c()) {
                return "";
            }
            f14878g = BaseInfo.getRomName();
        }
        return TextUtils.isEmpty(f14878g) ? "" : f14878g;
    }

    public static String m() {
        if (TextUtils.isEmpty(f14881j)) {
            f14881j = BaseInfo.getOSName();
        }
        return TextUtils.isEmpty(f14881j) ? "" : f14881j;
    }
}
