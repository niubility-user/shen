package com.xiaomi.push;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.xiaomi.push.service.XMPushService;

/* loaded from: classes11.dex */
public class z4 implements r5 {

    /* renamed from: g */
    XMPushService f19350g;

    /* renamed from: h */
    private int f19351h;

    /* renamed from: i */
    private Exception f19352i;
    private long o;
    private long p;

    /* renamed from: k */
    private long f19354k = 0;

    /* renamed from: l */
    private long f19355l = 0;

    /* renamed from: m */
    private long f19356m = 0;

    /* renamed from: n */
    private long f19357n = 0;

    /* renamed from: j */
    private String f19353j = "";

    public z4(XMPushService xMPushService) {
        this.o = 0L;
        this.p = 0L;
        this.f19350g = xMPushService;
        c();
        int myUid = Process.myUid();
        try {
            this.p = TrafficStats.getUidRxBytes(myUid);
            this.o = TrafficStats.getUidTxBytes(myUid);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Failed to obtain traffic data during initialization: " + e2);
            this.p = -1L;
            this.o = -1L;
        }
    }

    private void c() {
        this.f19355l = 0L;
        this.f19357n = 0L;
        this.f19354k = 0L;
        this.f19356m = 0L;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j0.p(this.f19350g)) {
            this.f19354k = elapsedRealtime;
        }
        if (this.f19350g.m159c()) {
            this.f19356m = elapsedRealtime;
        }
    }

    private synchronized void d() {
        g.j.a.a.a.c.B("stat connpt = " + this.f19353j + " netDuration = " + this.f19355l + " ChannelDuration = " + this.f19357n + " channelConnectedTime = " + this.f19356m);
        s4 s4Var = new s4();
        s4Var.a = (byte) 0;
        s4Var.a(r4.CHANNEL_ONLINE_RATE.a());
        s4Var.a(this.f19353j);
        s4Var.d((int) (System.currentTimeMillis() / 1000));
        s4Var.b((int) (this.f19355l / 1000));
        s4Var.c((int) (this.f19357n / 1000));
        a5.f().i(s4Var);
        c();
    }

    public Exception a() {
        return this.f19352i;
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var) {
        this.f19351h = 0;
        this.f19352i = null;
        this.f19353j = j0.g(this.f19350g);
        c5.c(0, r4.CONN_SUCCESS.a());
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, int i2, Exception exc) {
        long j2;
        if (this.f19351h == 0 && this.f19352i == null) {
            this.f19351h = i2;
            this.f19352i = exc;
            c5.k(o5Var.d(), exc);
        }
        if (i2 == 22 && this.f19356m != 0) {
            long b = o5Var.b() - this.f19356m;
            if (b < 0) {
                b = 0;
            }
            this.f19357n += b + (u5.f() / 2);
            this.f19356m = 0L;
        }
        b();
        int myUid = Process.myUid();
        long j3 = -1;
        try {
            j3 = TrafficStats.getUidRxBytes(myUid);
            j2 = TrafficStats.getUidTxBytes(myUid);
        } catch (Exception e2) {
            g.j.a.a.a.c.o("Failed to obtain traffic data: " + e2);
            j2 = -1L;
        }
        g.j.a.a.a.c.B("Stats rx=" + (j3 - this.p) + ", tx=" + (j2 - this.o));
        this.p = j3;
        this.o = j2;
    }

    @Override // com.xiaomi.push.r5
    public void a(o5 o5Var, Exception exc) {
        c5.d(0, r4.CHANNEL_CON_FAIL.a(), 1, o5Var.d(), j0.q(this.f19350g) ? 1 : 0);
        b();
    }

    public synchronized void b() {
        XMPushService xMPushService = this.f19350g;
        if (xMPushService == null) {
            return;
        }
        String g2 = j0.g(xMPushService);
        boolean q = j0.q(this.f19350g);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.f19354k;
        if (j2 > 0) {
            this.f19355l += elapsedRealtime - j2;
            this.f19354k = 0L;
        }
        long j3 = this.f19356m;
        if (j3 != 0) {
            this.f19357n += elapsedRealtime - j3;
            this.f19356m = 0L;
        }
        if (q) {
            if ((!TextUtils.equals(this.f19353j, g2) && this.f19355l > Final.HALF_MINUTE) || this.f19355l > 5400000) {
                d();
            }
            this.f19353j = g2;
            if (this.f19354k == 0) {
                this.f19354k = elapsedRealtime;
            }
            if (this.f19350g.m159c()) {
                this.f19356m = elapsedRealtime;
            }
        }
    }

    @Override // com.xiaomi.push.r5
    public void b(o5 o5Var) {
        b();
        this.f19356m = SystemClock.elapsedRealtime();
        c5.e(0, r4.CONN_SUCCESS.a(), o5Var.d(), o5Var.a());
    }
}
