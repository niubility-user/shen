package com.cmic.sso.sdk.a;

import android.text.TextUtils;
import com.cmic.sso.sdk.e.k;

/* loaded from: classes.dex */
class d {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        return System.currentTimeMillis() >= k.a("sso_config_xf", "client_valid", 0L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean b(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_IPV6_LIST", !z ? "0" : "1"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean c(boolean z) {
        String str = !z ? "0" : "1";
        return "1".equals(k.a("sso_config_xf", "CLOSE_M008_APPID_LIST", str)) || "1".equals(k.a("sso_config_xf", "CLOSE_M008_SDKVERSION_LIST", str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean d(boolean z) {
        return k.a("sso_config_xf", "CLOSE_FRIEND_WAPKS", z ? "CU" : "").contains("CU");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean e(boolean z) {
        return k.a("sso_config_xf", "CLOSE_FRIEND_WAPKS", z ? "CT" : "").contains("CT");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_LOGS_VERSION", z ? "1" : "0"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(boolean z) {
        return "1".equals(k.a("sso_config_xf", "CLOSE_IPV4_LIST", !z ? "0" : "1"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(String str) {
        String a = k.a("sso_config_xf", "https_get_phone_scrip_host", (String) null);
        return TextUtils.isEmpty(a) ? str : a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(String str) {
        String a = k.a("sso_config_xf", "config_host", (String) null);
        return TextUtils.isEmpty(a) ? str : a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String c(String str) {
        String a = k.a("sso_config_xf", "logHost", "");
        return TextUtils.isEmpty(a) ? str : a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int b(int i2) {
        return k.a("sso_config_xf", "pauseTime", i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int a(int i2) {
        return k.a("sso_config_xf", "maxFailedLogTimes", i2);
    }
}
