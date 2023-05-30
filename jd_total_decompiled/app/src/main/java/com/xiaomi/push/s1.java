package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.jd.dynamic.DYConstants;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes11.dex */
public class s1 {
    private static int a;

    private static int a(Context context) {
        if (a <= 0) {
            a = a8.l(context);
        }
        return a;
    }

    private static int b(boolean z) {
        return z ? 1 : 0;
    }

    private static SharedPreferences c(Context context) {
        return context.getSharedPreferences("sp_disconnect_stats", 0);
    }

    private static String d(String str, int i2) {
        return f(str, String.valueOf(i2));
    }

    private static String e(String str, long j2) {
        return f(str, String.valueOf(j2));
    }

    private static String f(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            str2 = DYConstants.DY_NULL_STR;
        }
        if (str == null || str.length() <= 0) {
            return str2;
        }
        return str + ";" + str2;
    }

    private static List<q1> g(Context context) {
        String str;
        SharedPreferences c2 = c(context);
        String[] l2 = l(c2.getString("host", null));
        if (l2 == null || l2.length <= 0) {
            str = "DisconnectStatsSP Cached hosts data is empty,drop.";
        } else {
            String[] l3 = l(c2.getString("network_state", null));
            String[] l4 = l(c2.getString("reason", null));
            String[] l5 = l(c2.getString("ping_interval", null));
            String[] l6 = l(c2.getString("network_type", null));
            String[] l7 = l(c2.getString("wifi_digest", null));
            String[] l8 = l(c2.getString("connected_network_type", null));
            String[] l9 = l(c2.getString("disconnect_time", null));
            String[] l10 = l(c2.getString("connected_time", null));
            String[] l11 = l(c2.getString("xmsf_vc", null));
            String[] l12 = l(c2.getString("android_vc", null));
            if (l3 != null && l4 != null && l5 != null && l6 != null && l7 != null && l8 != null && l9 != null && l10 != null && l11 != null && l12 != null && l2.length == l3.length && l2.length == l4.length && l2.length == l5.length && l2.length == l6.length && l2.length == l7.length && l2.length == l8.length && l2.length == l9.length && l2.length == l10.length && l2.length == l11.length && l2.length == l12.length) {
                ArrayList arrayList = new ArrayList(l2.length);
                int i2 = 0;
                while (i2 < l2.length) {
                    q1 q1Var = new q1();
                    q1Var.d(1);
                    q1Var.f(l2[i2]);
                    q1Var.j(s9.a(l3[i2], -1));
                    q1Var.o(s9.a(l4[i2], -1));
                    String[] strArr = l3;
                    String[] strArr2 = l2;
                    ArrayList arrayList2 = arrayList;
                    q1Var.e(s9.b(l5[i2], -1L));
                    q1Var.s(s9.a(l6[i2], -1));
                    q1Var.l(l7[i2]);
                    q1Var.v(s9.a(l8[i2], -1));
                    long b = s9.b(l9[i2], -1L);
                    long b2 = s9.b(l10[i2], -1L);
                    q1Var.k(b2 - b);
                    q1Var.p(b);
                    q1Var.t(b2);
                    q1Var.x(s9.a(l11[i2], -1));
                    q1Var.z(s9.a(l12[i2], -1));
                    arrayList2.add(q1Var);
                    i2++;
                    l3 = strArr;
                    arrayList = arrayList2;
                    l5 = l5;
                    l4 = l4;
                    l2 = strArr2;
                    l6 = l6;
                }
                return arrayList;
            }
            str = "DisconnectStatsSP Cached data incorrect,drop.";
        }
        g.j.a.a.a.c.o(str);
        return null;
    }

    private static void h(Context context) {
        p1.g("upload");
        new r1().a(context, g(context));
        m(context);
    }

    public static void i(Context context, long j2) {
        i.b(context).g(new u1(context, j2));
    }

    private static void j(Context context, String str, int i2, long j2, int i3, long j3, int i4, String str2, int i5) {
        p1.g(String.format(Locale.US, "recordDisconnectInfo host=%s, netState=%d, currentTimeMillis=%d, reason=%d, pingInterval=%d, netType=%d, wifiDigest=%s, connectedNetType=%d", str, Integer.valueOf(i2), Long.valueOf(j2), Integer.valueOf(i3), Long.valueOf(j3), Integer.valueOf(i4), str2, Integer.valueOf(i5)));
        SharedPreferences c2 = c(context);
        String string = c2.getString("host", null);
        String string2 = c2.getString("network_state", null);
        String string3 = c2.getString("reason", null);
        String string4 = c2.getString("ping_interval", null);
        String string5 = c2.getString("network_type", null);
        String string6 = c2.getString("wifi_digest", null);
        String string7 = c2.getString("connected_network_type", null);
        String string8 = c2.getString("disconnect_time", null);
        String string9 = c2.getString("xmsf_vc", null);
        String string10 = c2.getString("android_vc", null);
        String f2 = f(string, str);
        String d = d(string2, i2);
        String d2 = d(string3, i3);
        String e2 = e(string4, j3);
        String d3 = d(string5, i4);
        String f3 = f(string6, str2);
        String d4 = d(string7, i5);
        String e3 = e(string8, j2);
        c2.edit().putString("host", f2).putString("network_state", d).putString("reason", d2).putString("ping_interval", e2).putString("network_type", d3).putString("wifi_digest", f3).putString("connected_network_type", d4).putString("disconnect_time", e3).putString("xmsf_vc", d(string9, a(context))).putString("android_vc", d(string10, Build.VERSION.SDK_INT)).apply();
    }

    public static void k(Context context, String str, boolean z, long j2, int i2, long j3, int i3, String str2, int i4) {
        i.b(context).g(new t1(context, str, z, j2, i2, j3, i3, str2, i4));
    }

    private static String[] l(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return str.split(";");
    }

    private static void m(Context context) {
        p1.g("resetAfterUpload");
        c(context).edit().putString("host", null).putString("network_state", null).putString("reason", null).putString("ping_interval", null).putString("network_type", null).putString("wifi_digest", null).putString("connected_network_type", null).putString("disconnect_time", null).putString("connected_time", null).putLong("last_discnt_time", 0L).putInt("discnt_count", 0).putInt("cnt_count", 0).putString("xmsf_vc", null).putString("android_vc", null).apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008b, code lost:
        if (r3 >= 10) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static synchronized void p(Context context, long j2) {
        synchronized (s1.class) {
            SharedPreferences c2 = c(context);
            long j3 = c2.getLong("start_time_for_day", 0L);
            if (j3 == 0) {
                c2.edit().putLong("start_time_for_day", j2).putLong("last_discnt_time", 0L).putInt("discnt_count_in_day", 0).putInt("discnt_count", 0).putInt("cnt_count", 0).apply();
                return;
            }
            int i2 = c2.getInt("discnt_count", 0);
            int i3 = c2.getInt("cnt_count", 0);
            if (i2 > i3) {
                c2.edit().putInt("cnt_count", i3 + 1).putString("connected_time", e(c2.getString("connected_time", null), j2)).apply();
            }
            if (j2 - j3 >= 86400000) {
                c2.edit().putLong("start_time_for_day", j2).putInt("discnt_count_in_day", 0).apply();
            }
            h(context);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void q(Context context, String str, boolean z, long j2, int i2, long j3, int i3, String str2, int i4) {
        synchronized (s1.class) {
            SharedPreferences c2 = c(context);
            long j4 = c2.getLong("start_time_for_day", 0L);
            if (j4 == 0) {
                p1.g("recordDisconnection not initialized");
            } else if (j2 - c2.getLong("last_discnt_time", 0L) < 60000) {
                p1.g("recordDisconnection anti-shake");
            } else {
                if (j2 - j4 < 86400000) {
                    int i5 = c2.getInt("discnt_count_in_day", 0);
                    if (i5 > 100) {
                        p1.g("recordDisconnection count > 100 in 24H cycle,abandon.");
                        return;
                    } else {
                        c2.edit().putInt("discnt_count_in_day", i5 + 1).apply();
                    }
                } else {
                    p1.g("recordDisconnection with the current time exceeds 24H cycle, go on.");
                }
                int i6 = c2.getInt("discnt_count", 0);
                if (i6 == c2.getInt("cnt_count", 0)) {
                    b(z);
                    j(context, str, z ? 1 : 0, j2, i2, j3, i3, str2, i4);
                    c2.edit().putLong("last_discnt_time", j2).putInt("discnt_count", i6 + 1).apply();
                }
                p1.g("recordDisconnection complete");
            }
        }
    }
}
