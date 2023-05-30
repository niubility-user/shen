package com.xiaomi.push.service;

import android.text.TextUtils;
import com.coremedia.iso.boxes.AuthorBox;
import com.jd.libs.jdmbridge.base.proxy.share.IShareAdapter;
import com.xiaomi.push.c3;
import com.xiaomi.push.c5;
import com.xiaomi.push.d6;
import com.xiaomi.push.e3;
import com.xiaomi.push.e5;
import com.xiaomi.push.e6;
import com.xiaomi.push.f6;
import com.xiaomi.push.g6;
import com.xiaomi.push.h3;
import com.xiaomi.push.i3;
import com.xiaomi.push.j3;
import com.xiaomi.push.k3;
import com.xiaomi.push.l3;
import com.xiaomi.push.p5;
import com.xiaomi.push.r4;
import com.xiaomi.push.service.i0;
import com.xiaomi.push.u6;
import java.util.Date;

/* loaded from: classes11.dex */
public class g0 {
    private XMPushService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g0(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    private void b(d6 d6Var) {
        String k2 = d6Var.k();
        if (TextUtils.isEmpty(k2)) {
            return;
        }
        String[] split = k2.split(";");
        com.xiaomi.push.b1 b = com.xiaomi.push.f1.c().b(p5.b(), false);
        if (b == null || split.length <= 0) {
            return;
        }
        b.o(split);
        this.a.a(20, (Exception) null);
        this.a.a(true);
    }

    private void e(g6 g6Var) {
        i0.b b;
        String o = g6Var.o();
        String m2 = g6Var.m();
        if (TextUtils.isEmpty(o) || TextUtils.isEmpty(m2) || (b = i0.c().b(m2, o)) == null) {
            return;
        }
        u6.j(this.a, b.a, u6.b(g6Var.f()), true, true, System.currentTimeMillis());
    }

    private void f(e5 e5Var) {
        i0.b b;
        String F = e5Var.F();
        String num = Integer.toString(e5Var.a());
        if (TextUtils.isEmpty(F) || TextUtils.isEmpty(num) || (b = i0.c().b(num, F)) == null) {
            return;
        }
        u6.j(this.a, b.a, e5Var.x(), true, true, System.currentTimeMillis());
    }

    public void a(e5 e5Var) {
        if (5 != e5Var.a()) {
            f(e5Var);
        }
        try {
            d(e5Var);
        } catch (Exception e2) {
            g.j.a.a.a.c.q("handle Blob chid = " + e5Var.a() + " cmd = " + e5Var.e() + " packetid = " + e5Var.D() + " failure ", e2);
        }
    }

    public void c(g6 g6Var) {
        if (!"5".equals(g6Var.m())) {
            e(g6Var);
        }
        String m2 = g6Var.m();
        if (TextUtils.isEmpty(m2)) {
            m2 = "1";
            g6Var.p("1");
        }
        if (m2.equals("0")) {
            g.j.a.a.a.c.o("Received wrong packet with chid = 0 : " + g6Var.f());
        }
        if (g6Var instanceof e6) {
            d6 b = g6Var.b("kick");
            if (b != null) {
                String o = g6Var.o();
                String f2 = b.f("type");
                String f3 = b.f("reason");
                g.j.a.a.a.c.o("kicked by server, chid=" + m2 + " res=" + i0.b.e(o) + " type=" + f2 + " reason=" + f3);
                if (!"wait".equals(f2)) {
                    this.a.a(m2, o, 3, f3, f2);
                    i0.c().n(m2, o);
                    return;
                }
                i0.b b2 = i0.c().b(m2, o);
                if (b2 != null) {
                    this.a.a(b2);
                    b2.k(i0.c.unbind, 3, 0, f3, f2);
                    return;
                }
                return;
            }
        } else if (g6Var instanceof f6) {
            f6 f6Var = (f6) g6Var;
            if ("redir".equals(f6Var.B())) {
                d6 b3 = f6Var.b("hosts");
                if (b3 != null) {
                    b(b3);
                    return;
                }
                return;
            }
        }
        this.a.m156b().j(this.a, m2, g6Var);
    }

    public void d(e5 e5Var) {
        StringBuilder sb;
        String n2;
        String str;
        i0.c cVar;
        int i2;
        int i3;
        String e2 = e5Var.e();
        if (e5Var.a() != 0) {
            String num = Integer.toString(e5Var.a());
            if (!"SECMSG".equals(e5Var.e())) {
                if (!"BIND".equals(e2)) {
                    if ("KICK".equals(e2)) {
                        h3 l2 = h3.l(e5Var.p());
                        String F = e5Var.F();
                        String m2 = l2.m();
                        String p = l2.p();
                        g.j.a.a.a.c.o("kicked by server, chid=" + num + " res= " + i0.b.e(F) + " type=" + m2 + " reason=" + p);
                        if (!"wait".equals(m2)) {
                            this.a.a(num, F, 3, p, m2);
                            i0.c().n(num, F);
                            return;
                        }
                        i0.b b = i0.c().b(num, F);
                        if (b != null) {
                            this.a.a(b);
                            b.k(i0.c.unbind, 3, 0, p, m2);
                            return;
                        }
                        return;
                    }
                    return;
                }
                e3 m3 = e3.m(e5Var.p());
                String F2 = e5Var.F();
                i0.b b2 = i0.c().b(num, F2);
                if (b2 == null) {
                    return;
                }
                if (m3.o()) {
                    g.j.a.a.a.c.o("SMACK: channel bind succeeded, chid=" + e5Var.a());
                    b2.k(i0.c.binded, 1, 0, null, null);
                    return;
                }
                String n3 = m3.n();
                if (AuthorBox.TYPE.equals(n3)) {
                    if ("invalid-sig".equals(m3.q())) {
                        g.j.a.a.a.c.o("SMACK: bind error invalid-sig token = " + b2.f19096c + " sec = " + b2.f19101i);
                        c5.d(0, r4.BIND_INVALID_SIG.a(), 1, null, 0);
                    }
                    cVar = i0.c.unbind;
                    i2 = 1;
                    i3 = 5;
                } else if (!"cancel".equals(n3)) {
                    if ("wait".equals(n3)) {
                        this.a.a(b2);
                        b2.k(i0.c.unbind, 1, 7, m3.q(), n3);
                    }
                    str = "SMACK: channel bind failed, chid=" + num + " reason=" + m3.q();
                    g.j.a.a.a.c.o(str);
                } else {
                    cVar = i0.c.unbind;
                    i2 = 1;
                    i3 = 7;
                }
                b2.k(cVar, i2, i3, m3.q(), n3);
                i0.c().n(num, F2);
                str = "SMACK: channel bind failed, chid=" + num + " reason=" + m3.q();
                g.j.a.a.a.c.o(str);
            } else if (!e5Var.o()) {
                this.a.m156b().i(this.a, num, e5Var);
                return;
            } else {
                sb = new StringBuilder();
                sb.append("Recv SECMSG errCode = ");
                sb.append(e5Var.r());
                sb.append(" errStr = ");
                n2 = e5Var.z();
            }
        } else if ("PING".equals(e2)) {
            byte[] p2 = e5Var.p();
            if (p2 != null && p2.length > 0) {
                k3 o = k3.o(p2);
                if (o.q()) {
                    z0.f().j(o.k());
                }
            }
            if (!"com.xiaomi.xmsf".equals(this.a.getPackageName())) {
                this.a.m153a();
            }
            if ("1".equals(e5Var.D())) {
                g.j.a.a.a.c.o("received a server ping");
            } else {
                c5.j();
            }
            this.a.m157b();
            return;
        } else if ("SYNC".equals(e2)) {
            if ("CONF".equals(e5Var.t())) {
                z0.f().j(c3.m(e5Var.p()));
                return;
            } else if (TextUtils.equals("U", e5Var.t())) {
                l3 p3 = l3.p(e5Var.p());
                com.xiaomi.push.i2.b(this.a).h(p3.q(), p3.v(), new Date(p3.j()), new Date(p3.s()), p3.x() * 1024, p3.A());
                e5 e5Var2 = new e5();
                e5Var2.h(0);
                e5Var2.l(e5Var.e(), "UCA");
                e5Var2.k(e5Var.D());
                XMPushService xMPushService = this.a;
                xMPushService.a(new x0(xMPushService, e5Var2));
                return;
            } else if (!TextUtils.equals(IShareAdapter.SHARE_ACTION_PANE, e5Var.t())) {
                return;
            } else {
                j3 m4 = j3.m(e5Var.p());
                e5 e5Var3 = new e5();
                e5Var3.h(0);
                e5Var3.l(e5Var.e(), "PCA");
                e5Var3.k(e5Var.D());
                j3 j3Var = new j3();
                if (m4.n()) {
                    j3Var.k(m4.j());
                }
                e5Var3.n(j3Var.h(), null);
                XMPushService xMPushService2 = this.a;
                xMPushService2.a(new x0(xMPushService2, e5Var3));
                sb = new StringBuilder();
                sb.append("ACK msgP: id = ");
                n2 = e5Var.D();
            }
        } else if (!"NOTIFY".equals(e5Var.e())) {
            return;
        } else {
            i3 m5 = i3.m(e5Var.p());
            sb = new StringBuilder();
            sb.append("notify by server err = ");
            sb.append(m5.q());
            sb.append(" desc = ");
            n2 = m5.n();
        }
        sb.append(n2);
        str = sb.toString();
        g.j.a.a.a.c.o(str);
    }
}
