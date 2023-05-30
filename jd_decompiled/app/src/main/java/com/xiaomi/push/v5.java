package com.xiaomi.push;

import android.content.Context;
import android.os.SystemClock;
import com.xiaomi.push.service.XMPushService;
import java.io.IOException;
import java.net.Socket;

/* loaded from: classes11.dex */
public abstract class v5 extends o5 {
    protected Exception q;
    protected Socket r;
    private String s;
    protected XMPushService t;
    protected volatile long u;
    private int v;
    private long w;

    public v5(XMPushService xMPushService, p5 p5Var) {
        super(xMPushService, p5Var);
        this.q = null;
        this.u = 0L;
        this.w = 0L;
        this.t = xMPushService;
    }

    private void K(p5 p5Var) {
        M(p5Var.j(), p5Var.a());
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0339  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void M(java.lang.String r32, int r33) {
        /*
            Method dump skipped, instructions count: 836
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.v5.M(java.lang.String, int):void");
    }

    @Override // com.xiaomi.push.o5
    public void A(boolean z) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long currentTimeMillis = System.currentTimeMillis();
        O(z);
        com.xiaomi.push.service.n2.c(this.t).q();
        if (z) {
            return;
        }
        this.t.a(new w5(this, 13, elapsedRealtime, currentTimeMillis), 10000L);
    }

    public Context F() {
        return this.t;
    }

    b1 G(String str) {
        b1 b = f1.c().b(str, false);
        if (!b.u()) {
            s6.c(new z5(this, str));
        }
        return b;
    }

    public Socket H() {
        return new Socket();
    }

    protected synchronized void I() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public synchronized void J(int i2, Exception exc) {
        if (s() == 2) {
            return;
        }
        h(2, i2, exc);
        this.f18900i = "";
        try {
            this.r.close();
        } catch (Throwable unused) {
        }
    }

    protected void L(Exception exc) {
        if (SystemClock.elapsedRealtime() - this.u < 300000) {
            if (!j0.p(this.t)) {
                return;
            }
            int i2 = this.v + 1;
            this.v = i2;
            if (i2 < 2) {
                return;
            }
            String d = d();
            g.j.a.a.a.c.o("max short conn time reached, sink down current host:" + d);
            N(d, 0L, exc);
        }
        this.v = 0;
    }

    protected void N(String str, long j2, Exception exc) {
        b1 b = f1.c().b(p5.b(), false);
        if (b != null) {
            b.t(str, j2, 0L, exc);
            f1.c().w();
        }
    }

    protected abstract void O(boolean z);

    public String P() {
        return this.f18900i;
    }

    public void Q(int i2, Exception exc) {
        this.t.a(new x5(this, 2, i2, exc));
    }

    public synchronized void R() {
        try {
            if (!D() && !B()) {
                h(0, 0, null);
                K(this.f18903l);
                return;
            }
            g.j.a.a.a.c.o("WARNING: current xmpp has connected");
        } catch (IOException e2) {
            throw new a6(e2);
        }
    }

    public void S() {
        SystemClock.elapsedRealtime();
    }

    public void T() {
        SystemClock.elapsedRealtime();
    }

    @Override // com.xiaomi.push.o5
    public String d() {
        return this.s;
    }

    @Override // com.xiaomi.push.o5
    public void v(int i2, Exception exc) {
        J(i2, exc);
        if ((exc != null || i2 == 18) && this.u != 0) {
            L(exc);
        }
    }
}
