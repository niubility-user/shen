package com.xiaomi.push;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/* loaded from: classes11.dex */
public class p1 {
    private static v1 a = null;
    private static int b = -1;

    /* renamed from: c */
    private static String f18929c;

    private static int a(Context context) {
        String str;
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                j(null);
                return -1;
            } else if (activeNetworkInfo.getType() != 0) {
                if (activeNetworkInfo.getType() != 1 && activeNetworkInfo.getType() != 6) {
                    j(null);
                    return -1;
                }
                j("WIFI-ID-UNKNOWN");
                return 1;
            } else {
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (TextUtils.isEmpty(subtypeName) || "UNKNOWN".equalsIgnoreCase(subtypeName)) {
                    str = null;
                } else {
                    str = "M-" + subtypeName;
                }
                j(str);
                return 0;
            }
        } catch (Exception e2) {
            g.j.a.a.a.c.D("DisconnectStatsHelper getNetType occurred error: " + e2.getMessage());
            j(null);
            return -1;
        }
    }

    private static synchronized String b() {
        String str;
        synchronized (p1.class) {
            str = f18929c;
        }
        return str;
    }

    public static void c(Context context) {
        String str;
        if (h(context)) {
            long currentTimeMillis = System.currentTimeMillis();
            b = a(context);
            s1.i(context, currentTimeMillis);
            str = "onReconnection connectedNetworkType = " + b;
        } else {
            str = "onReconnection shouldSampling = false";
        }
        g(str);
    }

    public static void d(Context context, o5 o5Var) {
        if (h(context)) {
            if (a == null) {
                a = new v1(context);
            }
            o5Var.i(a);
            g("startStats");
        }
    }

    public static void e(Context context, String str) {
        if (!h(context)) {
            g("onWifiChanged shouldSampling = false");
            return;
        }
        g("onWifiChanged wifiDigest = " + str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        j("W-" + str);
    }

    public static void f(Context context, String str, int i2) {
        String str2;
        if (h(context)) {
            s1.k(context, str, j0.q(context), System.currentTimeMillis(), i2, com.xiaomi.push.service.n2.c(context).l(), a(context), b(), b);
            str2 = "onDisconnection";
        } else {
            str2 = "onDisconnection shouldSampling = false";
        }
        g(str2);
    }

    public static void g(String str) {
        l1.b("Push-DiscntStats", str);
    }

    private static boolean h(Context context) {
        return l1.c(context);
    }

    public static void i(Context context, o5 o5Var) {
        v1 v1Var = a;
        if (v1Var != null) {
            o5Var.x(v1Var);
            a = null;
            g("stopStats");
        }
    }

    private static synchronized void j(String str) {
        synchronized (p1.class) {
            if ("WIFI-ID-UNKNOWN".equals(str)) {
                String str2 = f18929c;
                if (str2 == null || !str2.startsWith("W-")) {
                    f18929c = null;
                }
            } else {
                f18929c = str;
            }
            g("updateNetId new networkId = " + str + ", finally netId = " + f18929c);
        }
    }
}
