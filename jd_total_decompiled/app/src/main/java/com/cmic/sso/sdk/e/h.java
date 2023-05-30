package com.cmic.sso.sdk.e;

import android.content.Context;
import android.text.TextUtils;
import com.cmic.sso.sdk.e.k;
import com.cmic.sso.sdk.e.n;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;

/* loaded from: classes.dex */
public class h {
    private static String a = null;
    private static String b = null;

    /* renamed from: c  reason: collision with root package name */
    private static long f1036c = 0;
    private static int d = -1;

    private static boolean c() {
        if (TextUtils.isEmpty(a)) {
            return !TextUtils.isEmpty(k.b("phonescripcache", "")) && a(k.a("phonescripstarttime", 0L));
        }
        c.b("PhoneScripUtils", b + LangUtils.SINGLE_SPACE + f1036c);
        return a(f1036c);
    }

    public static void a(boolean z, boolean z2) {
        k.a a2 = k.a();
        a2.a("phonescripstarttime");
        a2.a("phonescripcache");
        a2.a("pre_sim_key");
        a2.a("phonescripversion");
        if (z2) {
            a2.a();
        } else {
            a2.b();
        }
        if (z) {
            a = null;
            b = null;
            f1036c = 0L;
            d = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String str, long j2, String str2) {
        String a2 = b.a(context, str);
        if (TextUtils.isEmpty(a2)) {
            return;
        }
        k.a a3 = k.a();
        a3.a("phonescripcache", a2);
        a3.a("phonescripstarttime", j2);
        a3.a("phonescripversion", 2);
        a3.a("pre_sim_key", str2);
        a3.b();
    }

    public static void a(final Context context, final String str, long j2, final String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || j2 <= 0) {
            return;
        }
        c.b("PhoneScripUtils", "save phone scrip simKey = " + str2);
        a = str;
        long j3 = j2 * 1000;
        f1036c = System.currentTimeMillis() + j3;
        c.b("sLifeTime", f1036c + "");
        b = str2;
        d = 2;
        if (!"operator".equals(str3)) {
            n.a(new n.a() { // from class: com.cmic.sso.sdk.e.h.1
                @Override // com.cmic.sso.sdk.e.n.a
                protected void a() {
                    c.b("PhoneScripUtils", "start save scrip to sp in sub thread");
                    h.b(context, str, h.f1036c, str2);
                }
            });
        } else if (j3 > 3600000) {
            f1036c = System.currentTimeMillis() + 3600000;
        } else {
            f1036c = System.currentTimeMillis() + j3;
        }
    }

    public static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            String b2 = k.b("phonescripcache", "");
            if (TextUtils.isEmpty(b2)) {
                c.a("PhoneScripUtils", DYConstants.DY_NULL_STR);
                return null;
            }
            f1036c = k.a("phonescripstarttime", 0L);
            b = k.b("pre_sim_key", "");
            d = k.a("phonescripversion", -1);
            String b3 = b.b(context, b2);
            a = b3;
            return b3;
        }
        return a;
    }

    private static boolean a(long j2) {
        long currentTimeMillis = System.currentTimeMillis();
        c.b("PhoneScripUtils", j2 + "");
        c.b("PhoneScripUtils", currentTimeMillis + "");
        return j2 - currentTimeMillis > 10000;
    }

    public static long a() {
        long a2;
        long j2;
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(a)) {
            c.b("PhoneScripUtils", b + LangUtils.SINGLE_SPACE + f1036c);
            a2 = f1036c;
        } else {
            String b2 = k.b("phonescripcache", "");
            a2 = k.a("phonescripstarttime", 0L);
            if (TextUtils.isEmpty(b2)) {
                j2 = 0;
                return Math.max(j2 / 1000, 0L);
            }
        }
        j2 = (a2 - currentTimeMillis) - 10000;
        return Math.max(j2 / 1000, 0L);
    }

    private static int a(String str) {
        String b2;
        if (!TextUtils.isEmpty(b)) {
            b2 = b;
        } else {
            b2 = k.b("pre_sim_key", "");
            b = b2;
        }
        if (TextUtils.isEmpty(b2)) {
            return 0;
        }
        return b2.equals(str) ? 1 : 2;
    }

    public static boolean a(com.cmic.sso.sdk.a aVar) {
        int a2 = a(aVar.b("scripKey"));
        aVar.a("imsiState", a2 + "");
        c.b("PhoneScripUtils", "simState = " + a2);
        if (a2 == 0) {
            return false;
        }
        if (d == -1) {
            d = k.a("phonescripversion", -1);
        }
        if (d != 2) {
            a(true, false);
            b.a();
            c.b("PhoneScripUtils", "phoneScriptVersion change");
            return false;
        } else if (a2 == 2) {
            a(true, false);
            return false;
        } else {
            return c();
        }
    }
}
