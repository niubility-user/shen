package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.cashiernative.CashierPayChannelCode;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.manto.sdk.api.IMantoServerRequester;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes11.dex */
public class a8 {
    private static volatile int a = 0;
    private static int b = -1;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, m9> f18460c;

    /* JADX WARN: Removed duplicated region for block: B:13:0x0024  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static int a() {
        /*
            int r0 = com.xiaomi.push.a8.a
            if (r0 != 0) goto L47
            r0 = 0
            java.lang.String r1 = "ro.miui.ui.version.code"
            java.lang.String r1 = g(r1)     // Catch: java.lang.Throwable -> L29
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29
            r2 = 1
            if (r1 == 0) goto L21
            java.lang.String r1 = "ro.miui.ui.version.name"
            java.lang.String r1 = g(r1)     // Catch: java.lang.Throwable -> L29
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.Throwable -> L29
            if (r1 != 0) goto L1f
            goto L21
        L1f:
            r1 = 0
            goto L22
        L21:
            r1 = 1
        L22:
            if (r1 == 0) goto L25
            goto L26
        L25:
            r2 = 2
        L26:
            com.xiaomi.push.a8.a = r2     // Catch: java.lang.Throwable -> L29
            goto L31
        L29:
            r1 = move-exception
            java.lang.String r2 = "get isMIUI failed"
            g.j.a.a.a.c.q(r2, r1)
            com.xiaomi.push.a8.a = r0
        L31:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "isMIUI's value is: "
            r0.append(r1)
            int r1 = com.xiaomi.push.a8.a
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            g.j.a.a.a.c.y(r0)
        L47:
            int r0 = com.xiaomi.push.a8.a
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.a8.a():int");
    }

    public static int b(Context context) {
        String g2 = g("ro.miui.ui.version.code");
        if (TextUtils.isEmpty(g2) || !TextUtils.isDigitsOnly(g2)) {
            return 0;
        }
        return Integer.parseInt(g2);
    }

    public static m9 c(String str) {
        m9 m2 = m(str);
        return m2 == null ? m9.Global : m2;
    }

    public static String d() {
        int a2 = r9.a();
        return (!i() || a2 <= 0) ? "" : a2 < 2 ? "alpha" : a2 < 3 ? "development" : "stable";
    }

    public static String e(Intent intent) {
        if (intent == null) {
            return null;
        }
        return intent.toString() + LangUtils.SINGLE_SPACE + f(intent.getExtras());
    }

    public static String f(Bundle bundle) {
        String f2;
        StringBuilder sb = new StringBuilder("Bundle[");
        if (bundle == null) {
            sb.append(DYConstants.DY_NULL_STR);
        } else {
            boolean z = true;
            for (String str : bundle.keySet()) {
                if (!z) {
                    sb.append(", ");
                }
                sb.append(str);
                sb.append('=');
                Object obj = bundle.get(str);
                if (obj instanceof int[]) {
                    f2 = Arrays.toString((int[]) obj);
                } else if (obj instanceof byte[]) {
                    f2 = Arrays.toString((byte[]) obj);
                } else if (obj instanceof boolean[]) {
                    f2 = Arrays.toString((boolean[]) obj);
                } else if (obj instanceof short[]) {
                    f2 = Arrays.toString((short[]) obj);
                } else if (obj instanceof long[]) {
                    f2 = Arrays.toString((long[]) obj);
                } else if (obj instanceof float[]) {
                    f2 = Arrays.toString((float[]) obj);
                } else if (obj instanceof double[]) {
                    f2 = Arrays.toString((double[]) obj);
                } else if (obj instanceof String[]) {
                    f2 = Arrays.toString((String[]) obj);
                } else if (obj instanceof CharSequence[]) {
                    f2 = Arrays.toString((CharSequence[]) obj);
                } else if (obj instanceof Parcelable[]) {
                    f2 = Arrays.toString((Parcelable[]) obj);
                } else if (obj instanceof Bundle) {
                    f2 = f((Bundle) obj);
                } else {
                    sb.append(obj);
                    z = false;
                }
                sb.append(f2);
                z = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static String g(String str) {
        try {
            try {
                return (String) k0.g("android.os.SystemProperties", IMantoServerRequester.GET, str, "");
            } catch (Exception e2) {
                g.j.a.a.a.c.D("fail to get property. " + e2);
                return null;
            }
        } catch (Throwable unused) {
            return null;
        }
    }

    private static void h() {
        if (f18460c != null) {
            return;
        }
        HashMap hashMap = new HashMap();
        f18460c = hashMap;
        hashMap.put("CN", m9.China);
        Map<String, m9> map = f18460c;
        m9 m9Var = m9.Europe;
        map.put("FI", m9Var);
        f18460c.put("SE", m9Var);
        f18460c.put("NO", m9Var);
        f18460c.put("FO", m9Var);
        f18460c.put("EE", m9Var);
        f18460c.put("LV", m9Var);
        f18460c.put("LT", m9Var);
        f18460c.put("BY", m9Var);
        f18460c.put("MD", m9Var);
        f18460c.put("UA", m9Var);
        f18460c.put("PL", m9Var);
        f18460c.put("CZ", m9Var);
        f18460c.put("SK", m9Var);
        f18460c.put("HU", m9Var);
        f18460c.put("DE", m9Var);
        f18460c.put("AT", m9Var);
        f18460c.put("CH", m9Var);
        f18460c.put("LI", m9Var);
        f18460c.put(CashierPayChannelCode.JD_PAY_GB, m9Var);
        f18460c.put("IE", m9Var);
        f18460c.put("NL", m9Var);
        f18460c.put("BE", m9Var);
        f18460c.put("LU", m9Var);
        f18460c.put("FR", m9Var);
        f18460c.put("RO", m9Var);
        f18460c.put("BG", m9Var);
        f18460c.put("RS", m9Var);
        f18460c.put("MK", m9Var);
        f18460c.put("AL", m9Var);
        f18460c.put("GR", m9Var);
        f18460c.put("SI", m9Var);
        f18460c.put("HR", m9Var);
        f18460c.put("IT", m9Var);
        f18460c.put("SM", m9Var);
        f18460c.put("MT", m9Var);
        f18460c.put("ES", m9Var);
        f18460c.put("PT", m9Var);
        f18460c.put("AD", m9Var);
        f18460c.put("CY", m9Var);
        f18460c.put("DK", m9Var);
        f18460c.put("IS", m9Var);
        f18460c.put("UK", m9Var);
        f18460c.put("EL", m9Var);
        f18460c.put("RU", m9.Russia);
        f18460c.put("IN", m9.India);
    }

    public static boolean i() {
        return a() == 1;
    }

    public static boolean j(Context context) {
        return context != null && k(context.getPackageName());
    }

    public static boolean k(String str) {
        return "com.xiaomi.xmsf".equals(str);
    }

    public static int l(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode;
        } catch (Exception unused) {
            return 0;
        }
    }

    private static m9 m(String str) {
        h();
        return f18460c.get(str.toUpperCase());
    }

    public static String n() {
        String a2 = q9.a("ro.miui.region", "");
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("persist.sys.oppo.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("ro.oppo.regionmark", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("ro.vendor.oplus.regionmark", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("ro.hw.country", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("ro.csc.countryiso_code", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = o(q9.a("ro.product.country.region", ""));
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("gsm.vivo.countrycode", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("persist.sys.oem.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("ro.product.locale.region", "");
        }
        if (TextUtils.isEmpty(a2)) {
            a2 = q9.a("persist.sys.country", "");
        }
        if (!TextUtils.isEmpty(a2)) {
            g.j.a.a.a.c.o("get region from system, region = " + a2);
        }
        if (TextUtils.isEmpty(a2)) {
            String country = Locale.getDefault().getCountry();
            g.j.a.a.a.c.o("locale.default.country = " + country);
            return country;
        }
        return a2;
    }

    private static String o(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String[] split = str.split("-");
        return split.length > 0 ? split[0] : str;
    }

    public static boolean p() {
        return a() == 2;
    }

    public static String q() {
        return g("ro.miui.ui.version.name");
    }

    public static boolean r() {
        if (b < 0) {
            b = !v() ? 1 : 0;
        }
        return b > 0;
    }

    public static String s() {
        return g("ro.build.characteristics");
    }

    public static boolean t() {
        return !m9.China.name().equalsIgnoreCase(c(n()).name());
    }

    public static String u() {
        return g("ro.product.manufacturer");
    }

    public static boolean v() {
        String str = "";
        try {
            str = q9.a("ro.miui.ui.version.code", "");
        } catch (Exception unused) {
        }
        return !TextUtils.isEmpty(str);
    }
}
