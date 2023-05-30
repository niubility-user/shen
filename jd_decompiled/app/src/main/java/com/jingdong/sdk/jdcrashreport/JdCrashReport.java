package com.jingdong.sdk.jdcrashreport;

import android.app.Activity;
import com.jingdong.sdk.jdcrashreport.crash.jni.NativeMonitor;
import java.util.Map;

/* loaded from: classes.dex */
public class JdCrashReport {
    public static void appendUrl(String str) {
        if (d.w()) {
            d.C(str);
        }
    }

    public static String getAppCrashState() {
        return !d.w() ? "" : d.x();
    }

    public static void init(JDCrashReportConfig jDCrashReportConfig) {
        init(jDCrashReportConfig, false, false);
    }

    public static void postCaughtException(Throwable th) {
        if (d.w()) {
            postCaughtException(th, null);
        }
    }

    public static void postFlutterException(Throwable th, String str, String str2, Map<String, String> map) {
        if (d.w()) {
            d.u(th, str, str2, map);
        }
    }

    public static void postRNException(String str, String str2, String str3, Throwable th) {
        if (d.w()) {
            d.r(str, str2, str3, th);
        }
    }

    public static void setCrashHandleCallback(a aVar) {
        if (d.w()) {
            d.m(aVar);
        }
    }

    public static void skipWhenRecover(Class<? extends Activity> cls) {
        if (d.w()) {
            d.p(cls);
        }
    }

    public static void testNativeCrash() {
        if (d.w()) {
            NativeMonitor.a(false);
        }
    }

    public static void updateDeviceUniqueId(String str) {
        if (d.w()) {
            d.B(str);
        }
    }

    public static void updateUserId(String str) {
        if (d.w()) {
            d.q(str);
        }
    }

    public static void updateUts(String str) {
        if (d.w()) {
            d.y(str);
        }
    }

    @Deprecated
    public static void userReportAnr(JDCrashReportListener jDCrashReportListener) {
        if (d.w()) {
            d.o(jDCrashReportListener);
        }
    }

    public static void init(JDCrashReportConfig jDCrashReportConfig, boolean z) {
        init(jDCrashReportConfig, z, false);
    }

    public static void init(JDCrashReportConfig jDCrashReportConfig, boolean z, boolean z2) {
        if (jDCrashReportConfig != null && jDCrashReportConfig.a() != null) {
            jDCrashReportConfig.a(z);
            jDCrashReportConfig.b(z2);
            d.n(jDCrashReportConfig);
            return;
        }
        throw new NullPointerException("JDCrashReportConfig or Context is null, please check init code!");
    }

    public static void postCaughtException(Throwable th, String str) {
        if (d.w()) {
            d.t(th, str);
        }
    }

    public static void postRNException(String str, String str2, String str3, String str4, String str5) {
        if (d.w()) {
            postRNException(str, str2, str3, new Throwable(str4 + ", stack:\n" + str5));
        }
    }
}
