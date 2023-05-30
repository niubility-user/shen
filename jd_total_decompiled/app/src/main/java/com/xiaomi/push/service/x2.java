package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.cart.clean.CartCleanConstants;
import com.jingdong.jdsdk.mta.ExposureRvUtils;
import com.xiaomi.push.a6;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d4;
import com.xiaomi.push.d6;
import com.xiaomi.push.e5;
import com.xiaomi.push.e8;
import com.xiaomi.push.f6;
import com.xiaomi.push.g6;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.p7;
import com.xiaomi.push.s7;
import com.xiaomi.push.s8;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.service.n;
import com.xiaomi.push.u6;
import com.xiaomi.push.y4;
import com.xiaomi.push.y7;
import com.xiaomi.push.z6;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes11.dex */
public class x2 {
    public static Intent a(byte[] bArr, long j2) {
        y7 d = d(bArr);
        if (d == null) {
            return null;
        }
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mrt", Long.toString(j2));
        intent.setPackage(d.b);
        return intent;
    }

    public static y7 b(Context context, y7 y7Var) {
        return c(context, y7Var, null);
    }

    public static y7 c(Context context, y7 y7Var, Map<String, String> map) {
        s7 s7Var = new s7();
        s7Var.b(y7Var.m186a());
        p7 m185a = y7Var.m185a();
        if (m185a != null) {
            s7Var.a(m185a.m120a());
            s7Var.a(m185a.m118a());
            if (!TextUtils.isEmpty(m185a.m125b())) {
                s7Var.c(m185a.m125b());
            }
        }
        s7Var.a(m8.c(context, y7Var));
        y7 d = k.d(y7Var.b(), y7Var.m186a(), s7Var, c7.AckMessage);
        p7 m185a2 = y7Var.m185a();
        if (m185a2 != null) {
            m185a2 = m185a2.m119a();
            v0.a(m185a2);
        }
        m185a2.a("mat", Long.toString(System.currentTimeMillis()));
        m185a2.a("cs", String.valueOf(m8.a(context, y7Var)));
        if (map != null) {
            try {
                if (map.size() > 0) {
                    for (String str : map.keySet()) {
                        m185a2.a(str, map.get(str));
                    }
                }
            } catch (Throwable unused) {
            }
        }
        d.a(m185a2);
        return d;
    }

    public static y7 d(byte[] bArr) {
        y7 y7Var = new y7();
        try {
            m8.e(y7Var, bArr);
            return y7Var;
        } catch (Throwable th) {
            g.j.a.a.a.c.s(th);
            return null;
        }
    }

    public static void e(Context context, y7 y7Var, byte[] bArr) {
        try {
            n.c r = n.r(context, y7Var, bArr);
            if (r.b > 0 && !TextUtils.isEmpty(r.a)) {
                u6.j(context, r.a, r.b, true, false, System.currentTimeMillis());
            }
            if (!a8.j(context) || !j.e(context, y7Var, r.f19142c)) {
                u(context, y7Var, bArr);
                return;
            }
            j.b(context, y7Var);
            g.j.a.a.a.c.o("consume this broadcast by tts");
        } catch (Exception e2) {
            g.j.a.a.a.c.o("notify push msg error " + e2);
            e2.printStackTrace();
        }
    }

    private static void i(XMPushService xMPushService, y7 y7Var) {
        xMPushService.a(new c(4, xMPushService, y7Var));
    }

    private static void j(XMPushService xMPushService, y7 y7Var, c8 c8Var) {
        xMPushService.a(new i(4, c8Var, y7Var, xMPushService));
    }

    private static void k(XMPushService xMPushService, y7 y7Var, String str) {
        xMPushService.a(new g(4, xMPushService, y7Var, str));
    }

    private static void l(XMPushService xMPushService, y7 y7Var, String str, String str2) {
        xMPushService.a(new h(4, xMPushService, y7Var, str, str2));
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x03c7  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x042b  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x03a9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void m(XMPushService xMPushService, String str, byte[] bArr, Intent intent) {
        boolean z;
        c8 c8Var;
        String str2;
        int i2;
        d4 a;
        String b;
        String m120a;
        int i3;
        String str3;
        String str4;
        d4 a2;
        String b2;
        String Q;
        String m120a2;
        String str5;
        String str6;
        y7 d = d(bArr);
        p7 m185a = d.m185a();
        n8 n8Var = null;
        String str7 = null;
        if (bArr != null) {
            com.xiaomi.push.w1.f(d.b(), xMPushService.getApplicationContext(), null, d.a(), bArr.length);
        }
        if (y(d) && q(xMPushService, str)) {
            if (n.b0(d)) {
                d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), m185a.m120a(), "5");
            }
            x(xMPushService, d);
        } else if (s(d) && !q(xMPushService, str) && !w(d)) {
            if (n.b0(d)) {
                d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), m185a.m120a(), "6");
            }
            z(xMPushService, d);
        } else if ((!n.J(d) || !y4.o(xMPushService, d.b)) && !p(xMPushService, intent)) {
            if (!y4.o(xMPushService, d.b)) {
                if (n.b0(d)) {
                    d4.a(xMPushService.getApplicationContext()).i(d.b(), n.Q(d), m185a.m120a(), "2");
                }
                i(xMPushService, d);
                return;
            }
            g.j.a.a.a.c.o("receive a mipush message, we can see the app, but we can't see the receiver.");
            if (n.b0(d)) {
                d4.a(xMPushService.getApplicationContext()).i(d.b(), n.Q(d), m185a.m120a(), "3");
            }
        } else {
            boolean z2 = false;
            if (c7.Registration == d.a()) {
                String b3 = d.b();
                SharedPreferences.Editor edit = xMPushService.getSharedPreferences("pref_registered_pkg_names", 0).edit();
                edit.putString(b3, d.f265a);
                edit.commit();
                e8 a3 = m2.a(d);
                if (a3.a() != 0 || TextUtils.isEmpty(a3.b())) {
                    g.j.a.a.a.c.D("read regSecret failed");
                } else {
                    m2.c(xMPushService, b3, a3.b());
                }
                u2.a(xMPushService).i(b3);
                u2.a(xMPushService).j(b3);
                d4.a(xMPushService.getApplicationContext()).g(b3, "E100003", m185a.m120a(), 6003, null);
                if (!TextUtils.isEmpty(m185a.m120a())) {
                    intent.putExtra("messageId", m185a.m120a());
                    intent.putExtra("eventMessageType", 6000);
                }
            }
            if (n.Z(d)) {
                d4.a(xMPushService.getApplicationContext()).f(d.b(), n.Q(d), m185a.m120a(), 1001, System.currentTimeMillis(), null);
                if (!TextUtils.isEmpty(m185a.m120a())) {
                    intent.putExtra("messageId", m185a.m120a());
                    intent.putExtra("eventMessageType", 1000);
                }
            }
            if (n.V(d)) {
                d4.a(xMPushService.getApplicationContext()).f(d.b(), n.Q(d), m185a.m120a(), 2001, System.currentTimeMillis(), null);
                if (!TextUtils.isEmpty(m185a.m120a())) {
                    intent.putExtra("messageId", m185a.m120a());
                    intent.putExtra("eventMessageType", 2000);
                }
            }
            if (n.J(d)) {
                d4.a(xMPushService.getApplicationContext()).f(d.b(), n.Q(d), m185a.m120a(), 3001, System.currentTimeMillis(), null);
                if (!TextUtils.isEmpty(m185a.m120a())) {
                    intent.putExtra("messageId", m185a.m120a());
                    intent.putExtra("eventMessageType", 3000);
                }
            }
            if (m185a != null && !TextUtils.isEmpty(m185a.m128c()) && !TextUtils.isEmpty(m185a.d()) && m185a.b != 1 && !n.H(xMPushService, d.b, n.L(m185a.m121a()))) {
                if (m185a != null) {
                    Map<String, String> map = m185a.f195a;
                    str7 = map != null ? map.get("jobkey") : null;
                    if (TextUtils.isEmpty(str7)) {
                        str7 = m185a.m120a();
                    }
                    z2 = p.a(xMPushService, d.b, str7);
                }
                if (z2) {
                    d4.a(xMPushService.getApplicationContext()).j(d.b(), n.Q(d), m185a.m120a(), "1:" + str7);
                    str6 = "drop a duplicate message, key=" + str7;
                } else if (a8.j(xMPushService) && j.f(d)) {
                    str6 = "receive pull down message";
                } else {
                    e(xMPushService, d, bArr);
                    v(xMPushService, d);
                }
                g.j.a.a.a.c.o(str6);
                v(xMPushService, d);
            } else if ("com.xiaomi.xmsf".contains(d.b) && !d.m193b() && m185a != null && m185a.m121a() != null && m185a.m121a().containsKey(CartCleanConstants.CART_CLEAN_DIALOG_AB)) {
                v(xMPushService, d);
                g.j.a.a.a.c.B("receive abtest message. ack it." + m185a.m120a());
            } else if (t(xMPushService, str, d, m185a)) {
                if (m185a != null && !TextUtils.isEmpty(m185a.m120a())) {
                    if (n.V(d)) {
                        a = d4.a(xMPushService.getApplicationContext());
                        b = d.b();
                        str4 = n.Q(d);
                        m120a = m185a.m120a();
                        i3 = 2002;
                        str3 = null;
                    } else {
                        if (n.J(d)) {
                            a2 = d4.a(xMPushService.getApplicationContext());
                            b2 = d.b();
                            Q = n.Q(d);
                            m120a2 = m185a.m120a();
                            str5 = "7";
                        } else if (n.Z(d)) {
                            a2 = d4.a(xMPushService.getApplicationContext());
                            b2 = d.b();
                            Q = n.Q(d);
                            m120a2 = m185a.m120a();
                            str5 = "8";
                        } else if (n.a0(d)) {
                            a = d4.a(xMPushService.getApplicationContext());
                            b = d.b();
                            m120a = m185a.m120a();
                            i3 = 6004;
                            str3 = null;
                            str4 = "E100003";
                        }
                        a2.h(b2, Q, m120a2, str5);
                    }
                    a.g(b, str4, m120a, i3, str3);
                }
                if (c7.Notification == d.a) {
                    try {
                        n8Var = j1.a(xMPushService, d);
                    } catch (s8 e2) {
                        g.j.a.a.a.c.D("receive a message which action string is not valid. " + e2);
                    }
                    if (n8Var == null) {
                        g.j.a.a.a.c.D("receiving an un-recognized notification message. " + d.a);
                        z = false;
                        if (z && (n8Var instanceof c8)) {
                            c8Var = (c8) n8Var;
                            if (m7.CancelPushMessage.f179a.equals(c8Var.d) && c8Var.m35a() != null) {
                                str2 = c8Var.m35a().get(m0.M);
                                i2 = -2;
                                if (!TextUtils.isEmpty(str2)) {
                                    try {
                                        i2 = Integer.parseInt(str2);
                                    } catch (NumberFormatException e3) {
                                        g.j.a.a.a.c.o("parse notifyId from STRING to INT failed: " + e3);
                                    }
                                }
                                if (i2 < -1) {
                                    g.j.a.a.a.c.o("try to retract a message by notifyId=" + i2);
                                    n.y(xMPushService, d.b, i2);
                                } else {
                                    g.j.a.a.a.c.o("try to retract a message by title&description.");
                                    n.B(xMPushService, d.b, c8Var.m35a().get(m0.K), c8Var.m35a().get(m0.L));
                                }
                                if (m185a != null && m185a.m121a() != null && a8.j(xMPushService) && "pulldown".equals(z.h(m185a.m121a()))) {
                                    j.c(d);
                                }
                                j(xMPushService, d, c8Var);
                                if (z2) {
                                    g.j.a.a.a.c.o("broadcast passthrough message.");
                                    xMPushService.sendBroadcast(intent, k.g(d.b));
                                }
                            }
                        }
                    } else {
                        z = true;
                        if (z) {
                            c8Var = (c8) n8Var;
                            if (m7.CancelPushMessage.f179a.equals(c8Var.d)) {
                                str2 = c8Var.m35a().get(m0.M);
                                i2 = -2;
                                if (!TextUtils.isEmpty(str2)) {
                                }
                                if (i2 < -1) {
                                }
                                if (m185a != null) {
                                    j.c(d);
                                }
                                j(xMPushService, d, c8Var);
                                if (z2) {
                                }
                            }
                        }
                    }
                }
                z2 = true;
                if (z2) {
                }
            } else {
                d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), m185a.m120a(), "9");
            }
            if (d.a() != c7.UnRegistration || "com.xiaomi.xmsf".equals(xMPushService.getPackageName())) {
                return;
            }
            xMPushService.stopSelf();
        }
    }

    private static void n(XMPushService xMPushService, byte[] bArr, long j2) {
        o(xMPushService, bArr, j2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static void o(XMPushService xMPushService, byte[] bArr, long j2, Map<String, String> map) {
        byte[] bArr2;
        String t;
        c7 c7Var;
        String str;
        Map<String, String> m121a;
        Map<String, String> m121a2;
        byte[] f2;
        y7 d = d(bArr);
        if (d == null) {
            return;
        }
        if (TextUtils.isEmpty(d.b)) {
            g.j.a.a.a.c.o("receive a mipush message without package name");
            return;
        }
        p7 m185a = d.m185a();
        if (m185a != null && map != null && !map.isEmpty() && (m121a2 = m185a.m121a()) != null && !m121a2.isEmpty()) {
            boolean z = false;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (m121a2.containsKey(entry.getKey())) {
                    m121a2.put(entry.getKey(), entry.getValue());
                    z = true;
                }
            }
            if (z && (f2 = m8.f(d)) != null && f2.length > 0) {
                bArr2 = f2;
                Long valueOf = Long.valueOf(System.currentTimeMillis());
                Intent a = a(bArr2, valueOf.longValue());
                t = n.t(d);
                u6.j(xMPushService, t, j2, true, true, System.currentTimeMillis());
                if (m185a != null && m185a.m120a() != null) {
                    g.j.a.a.a.c.E(String.format("receive a message. appid=%1$s, msgid= %2$s, action=%3$s", d.m186a(), f0.b(m185a.m120a()), d.a()));
                }
                if (m185a != null) {
                    m185a.a("mrt", Long.toString(valueOf.longValue()));
                }
                c7Var = c7.SendMessage;
                String str2 = "";
                if (c7Var != d.a() && u2.a(xMPushService).c(d.b) && !n.J(d)) {
                    if (m185a != null) {
                        str2 = m185a.m120a();
                        if (n.b0(d)) {
                            d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), str2, "1");
                        }
                    }
                    g.j.a.a.a.c.o("Drop a message for unregistered, msgid=" + str2);
                    k(xMPushService, d, d.b);
                    return;
                } else if (c7Var != d.a() && u2.a(xMPushService).g(d.b) && !n.J(d)) {
                    if (m185a != null) {
                        str2 = m185a.m120a();
                        if (n.b0(d)) {
                            d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), str2, "2");
                        }
                    }
                    g.j.a.a.a.c.o("Drop a message for push closed, msgid=" + str2);
                    k(xMPushService, d, d.b);
                    return;
                } else if (c7Var != d.a() && !TextUtils.equals(xMPushService.getPackageName(), "com.xiaomi.xmsf") && !TextUtils.equals(xMPushService.getPackageName(), d.b)) {
                    g.j.a.a.a.c.o("Receive a message with wrong package name, expect " + xMPushService.getPackageName() + ", received " + d.b);
                    l(xMPushService, d, "unmatched_package", "package should be " + xMPushService.getPackageName() + ", but got " + d.b);
                    if (m185a == null || !n.b0(d)) {
                        return;
                    }
                    d4.a(xMPushService.getApplicationContext()).h(d.b(), n.Q(d), m185a.m120a(), "3");
                    return;
                } else {
                    if (c7Var == d.a() || z6.c() != 999) {
                        str = t;
                    } else {
                        str = t;
                        if (z6.k(xMPushService, str)) {
                            g.j.a.a.a.c.o("Receive the uninstalled dual app message");
                            try {
                                k.i(xMPushService, k.c(str, d.m186a()));
                                g.j.a.a.a.c.o("uninstall " + str + " msg sent");
                            } catch (a6 e2) {
                                g.j.a.a.a.c.D("Fail to send Message: " + e2.getMessage());
                                xMPushService.a(10, e2);
                            }
                            n.x(xMPushService, str);
                            return;
                        }
                    }
                    if (m185a == null && (m121a = m185a.m121a()) != null && m121a.containsKey(ExposureRvUtils.HIDE) && DYConstants.DY_TRUE.equalsIgnoreCase(m121a.get(ExposureRvUtils.HIDE))) {
                        v(xMPushService, d);
                        return;
                    }
                    m(xMPushService, str, bArr2, a);
                }
            }
        }
        bArr2 = bArr;
        Long valueOf2 = Long.valueOf(System.currentTimeMillis());
        Intent a2 = a(bArr2, valueOf2.longValue());
        t = n.t(d);
        u6.j(xMPushService, t, j2, true, true, System.currentTimeMillis());
        if (m185a != null) {
            g.j.a.a.a.c.E(String.format("receive a message. appid=%1$s, msgid= %2$s, action=%3$s", d.m186a(), f0.b(m185a.m120a()), d.a()));
        }
        if (m185a != null) {
        }
        c7Var = c7.SendMessage;
        String str22 = "";
        if (c7Var != d.a()) {
        }
        if (c7Var != d.a()) {
        }
        if (c7Var != d.a()) {
        }
        if (c7Var == d.a()) {
        }
        str = t;
        if (m185a == null) {
        }
        m(xMPushService, str, bArr2, a2);
    }

    private static boolean p(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                if (!queryBroadcastReceivers.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    private static boolean q(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.mipush.miui.CLICK_MESSAGE");
        intent.setPackage(str);
        Intent intent2 = new Intent("com.xiaomi.mipush.miui.RECEIVE_MESSAGE");
        intent2.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        try {
            List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent2, 32);
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 32);
            if (queryBroadcastReceivers.isEmpty()) {
                if (queryIntentServices.isEmpty()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e2) {
            g.j.a.a.a.c.s(e2);
            return false;
        }
    }

    public static boolean r(Context context, String str, byte[] bArr) {
        if (y4.k(context, str)) {
            Intent intent = new Intent("com.xiaomi.mipush.MESSAGE_ARRIVED");
            intent.putExtra("mipush_payload", bArr);
            intent.setPackage(str);
            try {
                if (context.getPackageManager().queryBroadcastReceivers(intent, 0).isEmpty()) {
                    return false;
                }
                g.j.a.a.a.c.o("broadcast message arrived.");
                context.sendBroadcast(intent, k.g(str));
                return true;
            } catch (Exception e2) {
                g.j.a.a.a.c.o("meet error when broadcast message arrived. " + e2);
                return false;
            }
        }
        return false;
    }

    private static boolean s(y7 y7Var) {
        return "com.xiaomi.xmsf".equals(y7Var.b) && y7Var.m185a() != null && y7Var.m185a().m121a() != null && y7Var.m185a().m121a().containsKey("miui_package_name");
    }

    private static boolean t(XMPushService xMPushService, String str, y7 y7Var, p7 p7Var) {
        boolean z = true;
        if (p7Var != null && p7Var.m121a() != null && p7Var.m121a().containsKey("__check_alive") && p7Var.m121a().containsKey("__awake")) {
            c8 c8Var = new c8();
            c8Var.b(y7Var.m186a());
            c8Var.d(str);
            c8Var.c(m7.AwakeSystemApp.f179a);
            c8Var.a(p7Var.m120a());
            c8Var.f113a = new HashMap();
            boolean k2 = y4.k(xMPushService.getApplicationContext(), str);
            c8Var.f113a.put("app_running", Boolean.toString(k2));
            if (!k2) {
                boolean parseBoolean = Boolean.parseBoolean(p7Var.m121a().get("__awake"));
                c8Var.f113a.put("awaked", Boolean.toString(parseBoolean));
                if (!parseBoolean) {
                    z = false;
                }
            }
            try {
                k.i(xMPushService, k.d(y7Var.b(), y7Var.m186a(), c8Var, c7.Notification));
            } catch (a6 e2) {
                g.j.a.a.a.c.s(e2);
            }
        }
        return z;
    }

    private static void u(Context context, y7 y7Var, byte[] bArr) {
        if (n.J(y7Var)) {
            return;
        }
        String t = n.t(y7Var);
        if (TextUtils.isEmpty(t) || r(context, t, bArr)) {
            return;
        }
        d4.a(context).i(t, n.Q(y7Var), y7Var.m185a().m120a(), "1");
    }

    private static void v(XMPushService xMPushService, y7 y7Var) {
        xMPushService.a(new d(4, xMPushService, y7Var));
    }

    private static boolean w(y7 y7Var) {
        Map<String, String> m121a = y7Var.m185a().m121a();
        return m121a != null && m121a.containsKey("notify_effect");
    }

    private static void x(XMPushService xMPushService, y7 y7Var) {
        xMPushService.a(new e(4, xMPushService, y7Var));
    }

    private static boolean y(y7 y7Var) {
        if (y7Var.m185a() == null || y7Var.m185a().m121a() == null) {
            return false;
        }
        return "1".equals(y7Var.m185a().m121a().get("obslete_ads_message"));
    }

    private static void z(XMPushService xMPushService, y7 y7Var) {
        xMPushService.a(new f(4, xMPushService, y7Var));
    }

    public void f(Context context, i0.b bVar, boolean z, int i2, String str) {
        s2 b;
        if (z || (b = t2.b(context)) == null || !"token-expired".equals(str)) {
            return;
        }
        t2.c(context, b.f19189f, b.d, b.f19188e);
    }

    public void g(XMPushService xMPushService, e5 e5Var, i0.b bVar) {
        try {
            byte[] q = e5Var.q(bVar.f19101i);
            HashMap hashMap = null;
            if (b2.b(e5Var)) {
                hashMap = new HashMap();
                hashMap.put("t_im", String.valueOf(e5Var.s()));
                hashMap.put("t_rt", String.valueOf(e5Var.b()));
            }
            o(xMPushService, q, e5Var.x(), hashMap);
        } catch (IllegalArgumentException e2) {
            g.j.a.a.a.c.s(e2);
        }
    }

    public void h(XMPushService xMPushService, g6 g6Var, i0.b bVar) {
        if (!(g6Var instanceof f6)) {
            g.j.a.a.a.c.o("not a mipush message");
            return;
        }
        f6 f6Var = (f6) g6Var;
        d6 b = f6Var.b("s");
        if (b != null) {
            try {
                n(xMPushService, r0.h(r0.g(bVar.f19101i, f6Var.l()), b.k()), u6.b(g6Var.f()));
            } catch (IllegalArgumentException e2) {
                g.j.a.a.a.c.s(e2);
            }
        }
    }
}
