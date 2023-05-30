package com.jd.stat.common;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.provider.Settings;

/* loaded from: classes18.dex */
public class m {
    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().hasSystemFeature("android.hardware.nfc");
    }

    public static boolean b(Context context) {
        FingerprintManager fingerprintManager;
        if (com.jd.stat.security.d.a().t() && l.a(context, "android.permission.USE_FINGERPRINT") && com.jd.stat.common.b.g.b() && (fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint")) != null) {
            return fingerprintManager.isHardwareDetected();
        }
        return false;
    }

    public static boolean c(Context context) {
        FingerprintManager fingerprintManager;
        if (com.jd.stat.security.d.a().t() && l.a(context, "android.permission.USE_FINGERPRINT") && com.jd.stat.common.b.g.b() && (fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint")) != null) {
            return fingerprintManager.hasEnrolledFingerprints();
        }
        return false;
    }

    public static int d(Context context) {
        return -1;
    }

    public static boolean e(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) > 0;
    }

    public static String a() {
        return NativeInfo.getProp("ro.product.locale");
    }

    public static String b() {
        return NativeInfo.getProp("persist.sys.locale");
    }
}
