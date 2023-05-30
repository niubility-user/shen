package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.a8;
import com.xiaomi.push.e5;
import com.xiaomi.push.e6;
import com.xiaomi.push.f6;
import com.xiaomi.push.g6;
import com.xiaomi.push.i6;
import com.xiaomi.push.service.i0;
import java.util.Collection;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class h2 {
    private x2 a = new x2();

    public static String c(i0.b bVar) {
        StringBuilder sb;
        String str;
        if ("9".equals(bVar.f19100h)) {
            sb = new StringBuilder();
            sb.append(bVar.a);
            str = ".permission.MIMC_RECEIVE";
        } else {
            sb = new StringBuilder();
            sb.append(bVar.a);
            str = ".permission.MIPUSH_RECEIVE";
        }
        sb.append(str);
        return sb.toString();
    }

    private static void e(Context context, Intent intent, i0.b bVar) {
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(intent, c(bVar));
        }
    }

    i0.b a(e5 e5Var) {
        Collection<i0.b> f2 = i0.c().f(Integer.toString(e5Var.a()));
        if (f2.isEmpty()) {
            return null;
        }
        Iterator<i0.b> it = f2.iterator();
        if (f2.size() == 1) {
            return it.next();
        }
        String F = e5Var.F();
        while (it.hasNext()) {
            i0.b next = it.next();
            if (TextUtils.equals(F, next.b)) {
                return next;
            }
        }
        return null;
    }

    i0.b b(g6 g6Var) {
        Collection<i0.b> f2 = i0.c().f(g6Var.m());
        if (f2.isEmpty()) {
            return null;
        }
        Iterator<i0.b> it = f2.iterator();
        if (f2.size() == 1) {
            return it.next();
        }
        String q = g6Var.q();
        String o = g6Var.o();
        while (it.hasNext()) {
            i0.b next = it.next();
            if (TextUtils.equals(q, next.b) || TextUtils.equals(o, next.b)) {
                return next;
            }
        }
        return null;
    }

    @SuppressLint({"WrongConstant"})
    public void d(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.service_started");
        if (a8.r()) {
            intent.addFlags(16777216);
        }
        g.j.a.a.a.c.o("[Bcst] send ***.push.service_started broadcast to inform push service has started.");
        context.sendBroadcast(intent);
    }

    @SuppressLint({"DefaultLocale"})
    public void f(Context context, i0.b bVar, int i2) {
        if ("5".equalsIgnoreCase(bVar.f19100h)) {
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_closed");
        intent.setPackage(bVar.a);
        intent.putExtra(m0.s, bVar.f19100h);
        intent.putExtra("ext_reason", i2);
        intent.putExtra(m0.p, bVar.b);
        intent.putExtra(m0.F, bVar.f19102j);
        if (bVar.r == null || !"9".equals(bVar.f19100h)) {
            g.j.a.a.a.c.o(String.format("[Bcst] notify channel closed. %s,%s,%d", bVar.f19100h, bVar.a, Integer.valueOf(i2)));
            e(context, intent, bVar);
            return;
        }
        try {
            bVar.r.send(Message.obtain(null, 17, intent));
        } catch (RemoteException unused) {
            bVar.r = null;
            StringBuilder sb = new StringBuilder();
            sb.append("peer may died: ");
            String str = bVar.b;
            sb.append(str.substring(str.lastIndexOf(64)));
            g.j.a.a.a.c.o(sb.toString());
        }
    }

    public void g(Context context, i0.b bVar, String str, String str2) {
        if (bVar == null) {
            g.j.a.a.a.c.D("error while notify kick by server!");
        } else if ("5".equalsIgnoreCase(bVar.f19100h)) {
            g.j.a.a.a.c.D("mipush kicked by server");
        } else {
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.kicked");
            intent.setPackage(bVar.a);
            intent.putExtra("ext_kick_type", str);
            intent.putExtra("ext_kick_reason", str2);
            intent.putExtra("ext_chid", bVar.f19100h);
            intent.putExtra(m0.p, bVar.b);
            intent.putExtra(m0.F, bVar.f19102j);
            g.j.a.a.a.c.o(String.format("[Bcst] notify packet(blob) arrival. %s,%s,%s", bVar.f19100h, bVar.a, str2));
            e(context, intent, bVar);
        }
    }

    @SuppressLint({"DefaultLocale"})
    public void h(Context context, i0.b bVar, boolean z, int i2, String str) {
        if ("5".equalsIgnoreCase(bVar.f19100h)) {
            this.a.f(context, bVar, z, i2, str);
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.xiaomi.push.channel_opened");
        intent.setPackage(bVar.a);
        intent.putExtra("ext_succeeded", z);
        if (!z) {
            intent.putExtra("ext_reason", i2);
        }
        if (!TextUtils.isEmpty(str)) {
            intent.putExtra("ext_reason_msg", str);
        }
        intent.putExtra("ext_chid", bVar.f19100h);
        intent.putExtra(m0.p, bVar.b);
        intent.putExtra(m0.F, bVar.f19102j);
        g.j.a.a.a.c.o(String.format("[Bcst] notify channel open result. %s,%s,%b,%d", bVar.f19100h, bVar.a, Boolean.valueOf(z), Integer.valueOf(i2)));
        e(context, intent, bVar);
    }

    public void i(XMPushService xMPushService, String str, e5 e5Var) {
        i0.b a = a(e5Var);
        if (a == null) {
            g.j.a.a.a.c.D("error while notify channel closed! channel " + str + " not registered");
        } else if ("5".equalsIgnoreCase(str)) {
            this.a.g(xMPushService, e5Var, a);
        } else {
            String str2 = a.a;
            Intent intent = new Intent();
            intent.setAction("com.xiaomi.push.new_msg");
            intent.setPackage(str2);
            intent.putExtra("ext_rcv_timestamp", SystemClock.elapsedRealtime());
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_raw_packet", e5Var.q(a.f19101i));
            intent.putExtra(m0.F, a.f19102j);
            intent.putExtra(m0.x, a.f19101i);
            if (b2.a(e5Var)) {
                intent.putExtra("ext_downward_pkt_id", e5Var.D());
            }
            if (a.r != null) {
                try {
                    a.r.send(Message.obtain(null, 17, intent));
                    g.j.a.a.a.c.o("message was sent by messenger for chid=" + str);
                    return;
                } catch (RemoteException unused) {
                    a.r = null;
                    StringBuilder sb = new StringBuilder();
                    sb.append("peer may died: ");
                    String str3 = a.b;
                    sb.append(str3.substring(str3.lastIndexOf(64)));
                    g.j.a.a.a.c.o(sb.toString());
                }
            }
            if ("com.xiaomi.xmsf".equals(str2)) {
                return;
            }
            g.j.a.a.a.c.o(String.format("[Bcst] notify packet(blob) arrival. %s,%s,%s", a.f19100h, a.a, e5Var.D()));
            if (b2.a(e5Var)) {
                t0.a().c(e5Var.D(), SystemClock.elapsedRealtime());
            }
            e(xMPushService, intent, a);
        }
    }

    public void j(XMPushService xMPushService, String str, g6 g6Var) {
        String str2;
        String str3;
        i0.b b = b(g6Var);
        if (b != null) {
            if ("5".equalsIgnoreCase(str)) {
                this.a.h(xMPushService, g6Var, b);
                return;
            }
            String str4 = b.a;
            if (g6Var instanceof f6) {
                str3 = "com.xiaomi.push.new_msg";
            } else if (g6Var instanceof e6) {
                str3 = "com.xiaomi.push.new_iq";
            } else if (g6Var instanceof i6) {
                str3 = "com.xiaomi.push.new_pres";
            } else {
                str2 = "unknown packet type, drop it";
            }
            Intent intent = new Intent();
            intent.setAction(str3);
            intent.setPackage(str4);
            intent.putExtra("ext_chid", str);
            intent.putExtra("ext_packet", g6Var.a());
            intent.putExtra(m0.F, b.f19102j);
            intent.putExtra(m0.x, b.f19101i);
            g.j.a.a.a.c.o(String.format("[Bcst] notify packet arrival. %s,%s,%s", b.f19100h, b.a, g6Var.l()));
            if ("3".equalsIgnoreCase(str)) {
                intent.putExtra(m0.t, g6Var.f18658j);
                intent.putExtra(m0.u, System.currentTimeMillis());
            }
            e(xMPushService, intent, b);
            return;
        }
        str2 = "error while notify channel closed! channel " + str + " not registered";
        g.j.a.a.a.c.D(str2);
    }
}
