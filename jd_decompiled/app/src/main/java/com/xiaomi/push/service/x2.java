package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.push.a6;
import com.xiaomi.push.a8;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.d4;
import com.xiaomi.push.d6;
import com.xiaomi.push.e5;
import com.xiaomi.push.f6;
import com.xiaomi.push.g6;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.p7;
import com.xiaomi.push.s7;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.service.n;
import com.xiaomi.push.u6;
import com.xiaomi.push.y4;
import com.xiaomi.push.y7;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m(com.xiaomi.push.service.XMPushService r19, java.lang.String r20, byte[] r21, android.content.Intent r22) {
        /*
            Method dump skipped, instructions count: 1209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.x2.m(com.xiaomi.push.service.XMPushService, java.lang.String, byte[], android.content.Intent):void");
    }

    private static void n(XMPushService xMPushService, byte[] bArr, long j2) {
        o(xMPushService, bArr, j2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00c1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void o(com.xiaomi.push.service.XMPushService r19, byte[] r20, long r21, java.util.Map<java.lang.String, java.lang.String> r23) {
        /*
            Method dump skipped, instructions count: 648
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.x2.o(com.xiaomi.push.service.XMPushService, byte[], long, java.util.Map):void");
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
