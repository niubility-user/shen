package com.xiaomi.push;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.o5;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.i0;
import java.util.Iterator;

/* loaded from: classes11.dex */
public class l5 extends v5 {
    private byte[] A;
    private Thread x;
    private g5 y;
    private h5 z;

    public l5(XMPushService xMPushService, p5 p5Var) {
        super(xMPushService, p5Var);
    }

    private e5 U(boolean z) {
        k5 k5Var = new k5();
        if (z) {
            k5Var.k("1");
        }
        byte[] i2 = c5.i();
        if (i2 != null) {
            k3 k3Var = new k3();
            k3Var.l(a.b(i2));
            k5Var.n(k3Var.h(), null);
        }
        return k5Var;
    }

    private void Z() {
        try {
            this.y = new g5(this.r.getInputStream(), this);
            this.z = new h5(this.r.getOutputStream(), this);
            m5 m5Var = new m5(this, "Blob Reader (" + this.f18902k + ")");
            this.x = m5Var;
            m5Var.start();
        } catch (Exception e2) {
            throw new a6("Error to init reader and writer", e2);
        }
    }

    @Override // com.xiaomi.push.v5
    protected synchronized void I() {
        Z();
        this.z.b();
    }

    @Override // com.xiaomi.push.v5
    public synchronized void J(int i2, Exception exc) {
        g5 g5Var = this.y;
        if (g5Var != null) {
            g5Var.e();
            this.y = null;
        }
        h5 h5Var = this.z;
        if (h5Var != null) {
            try {
                h5Var.c();
            } catch (Exception e2) {
                g.j.a.a.a.c.D("SlimConnection shutdown cause exception: " + e2);
            }
            this.z = null;
        }
        this.A = null;
        super.J(i2, exc);
    }

    @Override // com.xiaomi.push.v5
    protected void O(boolean z) {
        if (this.z == null) {
            throw new a6("The BlobWriter is null.");
        }
        e5 U = U(z);
        g.j.a.a.a.c.o("[Slim] SND ping id=" + U.D());
        w(U);
        S();
    }

    public void W(e5 e5Var) {
        if (e5Var == null) {
            return;
        }
        if (com.xiaomi.push.service.b2.a(e5Var)) {
            e5 e5Var2 = new e5();
            e5Var2.h(e5Var.a());
            e5Var2.l("SYNC", "ACK_RTT");
            e5Var2.k(e5Var.D());
            e5Var2.u(e5Var.s());
            e5Var2.i(e5Var.y());
            XMPushService xMPushService = this.f18904m;
            xMPushService.a(new com.xiaomi.push.service.x0(xMPushService, e5Var2));
        }
        if (e5Var.o()) {
            g.j.a.a.a.c.o("[Slim] RCV blob chid=" + e5Var.a() + "; id=" + e5Var.D() + "; errCode=" + e5Var.r() + "; err=" + e5Var.z());
        }
        if (e5Var.a() == 0) {
            if ("PING".equals(e5Var.e())) {
                g.j.a.a.a.c.o("[Slim] RCV ping id=" + e5Var.D());
                T();
            } else if ("CLOSE".equals(e5Var.e())) {
                Q(13, null);
            }
        }
        Iterator<o5.a> it = this.f18897f.values().iterator();
        while (it.hasNext()) {
            it.next().a(e5Var);
        }
    }

    public synchronized byte[] X() {
        if (this.A == null && !TextUtils.isEmpty(this.f18900i)) {
            String g2 = com.xiaomi.push.service.z0.g();
            StringBuilder sb = new StringBuilder();
            String str = this.f18900i;
            sb.append(str.substring(str.length() / 2));
            sb.append(g2.substring(g2.length() / 2));
            this.A = com.xiaomi.push.service.r0.i(this.f18900i.getBytes(), sb.toString().getBytes());
        }
        return this.A;
    }

    public void Y(g6 g6Var) {
        if (g6Var == null) {
            return;
        }
        Iterator<o5.a> it = this.f18897f.values().iterator();
        while (it.hasNext()) {
            it.next().b(g6Var);
        }
    }

    @Override // com.xiaomi.push.o5
    @Deprecated
    public void l(g6 g6Var) {
        w(e5.c(g6Var, null));
    }

    @Override // com.xiaomi.push.o5
    public synchronized void m(i0.b bVar) {
        d5.a(bVar, P(), this);
    }

    @Override // com.xiaomi.push.o5
    public synchronized void o(String str, String str2) {
        d5.b(str, str2, this);
    }

    @Override // com.xiaomi.push.o5
    public void p(e5[] e5VarArr) {
        for (e5 e5Var : e5VarArr) {
            w(e5Var);
        }
    }

    @Override // com.xiaomi.push.o5
    public boolean q() {
        return true;
    }

    @Override // com.xiaomi.push.o5
    public void w(e5 e5Var) {
        h5 h5Var = this.z;
        if (h5Var == null) {
            throw new a6("the writer is null.");
        }
        try {
            int a = h5Var.a(e5Var);
            SystemClock.elapsedRealtime();
            String E = e5Var.E();
            if (!TextUtils.isEmpty(E)) {
                u6.j(this.f18904m, E, a, false, true, System.currentTimeMillis());
            }
            Iterator<o5.a> it = this.f18898g.values().iterator();
            while (it.hasNext()) {
                it.next().a(e5Var);
            }
        } catch (Exception e2) {
            throw new a6(e2);
        }
    }
}
