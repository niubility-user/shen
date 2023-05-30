package com.xiaomi.push;

import android.os.SystemClock;
import com.jingdong.common.utils.JDReminderNewUtils;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.x4;
import java.util.Hashtable;

/* loaded from: classes11.dex */
public class c5 {
    private static final int a = r4.PING_RTT.a();
    private static long b = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class a {
        static Hashtable<Integer, Long> a = new Hashtable<>();
    }

    public static void a() {
        if (b == 0 || SystemClock.elapsedRealtime() - b > JDReminderNewUtils.REMINDER_DURATION_TIME_MAX) {
            b = SystemClock.elapsedRealtime();
            c(0, a);
        }
    }

    public static void b(int i2) {
        s4 a2 = a5.f().a();
        a2.a(r4.CHANNEL_STATS_COUNTER.a());
        a2.c(i2);
        a5.f().i(a2);
    }

    public static synchronized void c(int i2, int i3) {
        synchronized (c5.class) {
            if (i3 < 16777215) {
                a.a.put(Integer.valueOf((i2 << 24) | i3), Long.valueOf(System.currentTimeMillis()));
            } else {
                g.j.a.a.a.c.D("stats key should less than 16777215");
            }
        }
    }

    public static void d(int i2, int i3, int i4, String str, int i5) {
        s4 a2 = a5.f().a();
        a2.a((byte) i2);
        a2.a(i3);
        a2.b(i4);
        a2.b(str);
        a2.c(i5);
        a5.f().i(a2);
    }

    public static synchronized void e(int i2, int i3, String str, int i4) {
        synchronized (c5.class) {
            long currentTimeMillis = System.currentTimeMillis();
            int i5 = (i2 << 24) | i3;
            if (a.a.containsKey(Integer.valueOf(i5))) {
                s4 a2 = a5.f().a();
                a2.a(i3);
                a2.b((int) (currentTimeMillis - a.a.get(Integer.valueOf(i5)).longValue()));
                a2.b(str);
                if (i4 > -1) {
                    a2.c(i4);
                }
                a5.f().i(a2);
                a.a.remove(Integer.valueOf(i3));
            } else {
                g.j.a.a.a.c.D("stats key not found");
            }
        }
    }

    public static void f(XMPushService xMPushService, i0.b bVar) {
        new u4(xMPushService, bVar).b();
    }

    public static void g(String str, int i2, Exception exc) {
        s4 a2 = a5.f().a();
        if (a5.e() != null && a5.e().f19350g != null) {
            a2.c(j0.q(a5.e().f19350g) ? 1 : 0);
        }
        if (i2 > 0) {
            a2.a(r4.GSLB_REQUEST_SUCCESS.a());
            a2.b(str);
            a2.b(i2);
            a5.f().i(a2);
            return;
        }
        try {
            x4.a a3 = x4.a(exc);
            a2.a(a3.a.a());
            a2.c(a3.b);
            a2.b(str);
            a5.f().i(a2);
        } catch (NullPointerException unused) {
        }
    }

    public static void h(String str, Exception exc) {
        try {
            x4.a c2 = x4.c(exc);
            s4 a2 = a5.f().a();
            a2.a(c2.a.a());
            a2.c(c2.b);
            a2.b(str);
            if (a5.e() != null && a5.e().f19350g != null) {
                a2.c(j0.q(a5.e().f19350g) ? 1 : 0);
            }
            a5.f().i(a2);
        } catch (NullPointerException unused) {
        }
    }

    public static byte[] i() {
        t4 c2 = a5.f().c();
        if (c2 != null) {
            return m8.f(c2);
        }
        return null;
    }

    public static void j() {
        e(0, a, null, -1);
    }

    public static void k(String str, Exception exc) {
        try {
            x4.a e2 = x4.e(exc);
            s4 a2 = a5.f().a();
            a2.a(e2.a.a());
            a2.c(e2.b);
            a2.b(str);
            if (a5.e() != null && a5.e().f19350g != null) {
                a2.c(j0.q(a5.e().f19350g) ? 1 : 0);
            }
            a5.f().i(a2);
        } catch (NullPointerException unused) {
        }
    }
}
