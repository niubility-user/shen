package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.jd.dynamic.DYConstants;
import com.xiaomi.push.a6;
import com.xiaomi.push.c7;
import com.xiaomi.push.c8;
import com.xiaomi.push.e5;
import com.xiaomi.push.g6;
import com.xiaomi.push.m7;
import com.xiaomi.push.m8;
import com.xiaomi.push.n8;
import com.xiaomi.push.o5;
import com.xiaomi.push.p7;
import com.xiaomi.push.r7;
import com.xiaomi.push.s8;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.y7;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes11.dex */
public final class k {
    static e5 a(XMPushService xMPushService, byte[] bArr) {
        y7 y7Var = new y7();
        try {
            m8.e(y7Var, bArr);
            return b(t2.b(xMPushService), xMPushService, y7Var);
        } catch (s8 e2) {
            g.j.a.a.a.c.s(e2);
            return null;
        }
    }

    static e5 b(s2 s2Var, Context context, y7 y7Var) {
        try {
            e5 e5Var = new e5();
            e5Var.h(5);
            e5Var.B(s2Var.a);
            e5Var.v(f(y7Var));
            e5Var.l("SECMSG", "message");
            String str = s2Var.a;
            y7Var.f264a.f203a = str.substring(0, str.indexOf(DYConstants.DY_REGEX_AT));
            y7Var.f264a.f18995c = str.substring(str.indexOf("/") + 1);
            e5Var.n(m8.f(y7Var), s2Var.f19187c);
            e5Var.m((short) 1);
            g.j.a.a.a.c.o("try send mi push message. packagename:" + y7Var.b + " action:" + y7Var.a);
            return e5Var;
        } catch (NullPointerException e2) {
            g.j.a.a.a.c.s(e2);
            return null;
        }
    }

    public static y7 c(String str, String str2) {
        c8 c8Var = new c8();
        c8Var.b(str2);
        c8Var.c("package uninstalled");
        c8Var.a(g6.k());
        c8Var.a(false);
        return d(str, str2, c8Var, c7.Notification);
    }

    public static <T extends n8<T, ?>> y7 d(String str, String str2, T t, c7 c7Var) {
        return e(str, str2, t, c7Var, true);
    }

    private static <T extends n8<T, ?>> y7 e(String str, String str2, T t, c7 c7Var, boolean z) {
        byte[] f2 = m8.f(t);
        y7 y7Var = new y7();
        r7 r7Var = new r7();
        r7Var.a = 5L;
        r7Var.f203a = "fakeid";
        y7Var.a(r7Var);
        y7Var.a(ByteBuffer.wrap(f2));
        y7Var.a(c7Var);
        y7Var.b(z);
        y7Var.b(str);
        y7Var.a(false);
        y7Var.a(str2);
        return y7Var;
    }

    private static String f(y7 y7Var) {
        Map<String, String> map;
        p7 p7Var = y7Var.f263a;
        if (p7Var != null && (map = p7Var.f198b) != null) {
            String str = map.get("ext_traffic_source_pkg");
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return y7Var.b;
    }

    public static String g(String str) {
        return str + ".permission.MIPUSH_RECEIVE";
    }

    public static void h(XMPushService xMPushService) {
        s2 b = t2.b(xMPushService.getApplicationContext());
        if (b != null) {
            i0.b a = t2.b(xMPushService.getApplicationContext()).a(xMPushService);
            g.j.a.a.a.c.o("prepare account. " + a.a);
            j(xMPushService, a);
            i0.c().l(a);
            k(xMPushService, b, 172800);
        }
    }

    public static void i(XMPushService xMPushService, y7 y7Var) {
        com.xiaomi.push.w1.e(y7Var.b(), xMPushService.getApplicationContext(), y7Var, -1);
        o5 m151a = xMPushService.m151a();
        if (m151a == null) {
            throw new a6("try send msg while connection is null.");
        }
        if (!m151a.q()) {
            throw new a6("Don't support XMPP connection.");
        }
        e5 b = b(t2.b(xMPushService), xMPushService, y7Var);
        if (b != null) {
            m151a.w(b);
        }
    }

    public static void j(XMPushService xMPushService, i0.b bVar) {
        bVar.h(null);
        bVar.i(new m(xMPushService));
    }

    private static void k(XMPushService xMPushService, s2 s2Var, int i2) {
        b1.c(xMPushService).f(new l("MSAID", i2, xMPushService, s2Var));
    }

    public static void l(XMPushService xMPushService, String str, byte[] bArr) {
        com.xiaomi.push.w1.g(str, xMPushService.getApplicationContext(), bArr);
        o5 m151a = xMPushService.m151a();
        if (m151a == null) {
            throw new a6("try send msg while connection is null.");
        }
        if (!m151a.q()) {
            throw new a6("Don't support XMPP connection.");
        }
        e5 a = a(xMPushService, bArr);
        if (a != null) {
            m151a.w(a);
        } else {
            w2.b(xMPushService, str, bArr, 70000003, "not a valid message");
        }
    }

    public static y7 m(String str, String str2) {
        c8 c8Var = new c8();
        c8Var.b(str2);
        c8Var.c(m7.AppDataCleared.f179a);
        c8Var.a(f0.a());
        c8Var.a(false);
        return d(str, str2, c8Var, c7.Notification);
    }

    public static <T extends n8<T, ?>> y7 n(String str, String str2, T t, c7 c7Var) {
        return e(str, str2, t, c7Var, false);
    }
}
