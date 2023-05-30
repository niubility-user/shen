package com.jingdong.jdpush_new.connect;

import android.content.Context;
import com.jingdong.jdpush_new.j.o;
import com.jingdong.sdk.baseinfo.BaseInfo;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes12.dex */
public class c extends Thread {
    private static final int[] p = {0, 3, 10, 60, 180, 300, R2.attr.backgroundColor, 600};

    /* renamed from: g */
    private Context f12791g;

    /* renamed from: h */
    private final byte[] f12792h;

    /* renamed from: i */
    public int f12793i;

    /* renamed from: j */
    private f f12794j;

    /* renamed from: k */
    private boolean f12795k;

    /* renamed from: l */
    private boolean f12796l;

    /* renamed from: m */
    private long f12797m;

    /* renamed from: n */
    private ScheduledFuture<?> f12798n;
    private int o;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public class a implements Runnable {
        a() {
            c.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            com.jingdong.jdpush_new.j.g.c("\u957f\u65f6\u95f4\u5728\u540e\u53f0\uff0c\u65ad\u5f00\u8fde\u63a5");
            if (c.this.f12794j != null) {
                c.this.f12794j.f();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static class b {
        private static c a = new c(null);
    }

    /* synthetic */ c(a aVar) {
        this();
    }

    private int b() {
        int i2;
        int i3 = this.f12793i;
        int[] iArr = p;
        if (i3 < iArr.length) {
            i2 = iArr[i3];
        } else {
            i2 = iArr[iArr.length - 1];
        }
        return i2 * 1000;
    }

    public static c c() {
        return b.a;
    }

    private boolean e() {
        return this.f12796l && System.currentTimeMillis() - this.f12797m > ((long) this.o) * 1000;
    }

    public f d() {
        return this.f12794j;
    }

    public boolean f() {
        return this.f12795k;
    }

    public void g() {
        synchronized (this.f12792h) {
            this.f12792h.notify();
        }
    }

    public void h() {
        if ("none".equals(BaseInfo.getNetworkType())) {
            return;
        }
        g();
    }

    public void i(long j2) {
        this.f12796l = true;
        this.f12797m = j2;
        this.f12798n = o.b().c(new a(), this.o, TimeUnit.SECONDS);
    }

    public void j(long j2) {
        this.f12796l = false;
        ScheduledFuture<?> scheduledFuture = this.f12798n;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
            this.f12798n = null;
        }
        com.jingdong.jdpush_new.j.g.a("\u5207\u6362\u5230\u524d\u53f0\uff0c\u901a\u77e5\u8fde\u63a5");
        g();
    }

    public synchronized void k(Context context) {
        if (this.f12795k) {
            return;
        }
        this.f12791g = context;
        this.f12795k = true;
        super.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            try {
                com.jingdong.jdpush_new.mta.b.b().l(103000);
                com.jingdong.jdpush_new.e.a.a(this.f12791g);
                com.jingdong.jdpush_new.mta.b.b().l(103010);
                while (!isInterrupted()) {
                    synchronized (this.f12792h) {
                        while (true) {
                            if (!e() && !"none".equals(BaseInfo.getNetworkType())) {
                                break;
                            }
                            com.jingdong.jdpush_new.mta.b.b().l(103052);
                            this.f12792h.wait(600000L);
                        }
                    }
                    f fVar = new f(this.f12791g);
                    this.f12794j = fVar;
                    try {
                        fVar.h();
                    } catch (Exception e2) {
                        this.f12793i++;
                        com.jingdong.jdpush_new.j.g.f("create long conn failed ", e2);
                    }
                    this.f12794j = null;
                    if (this.f12793i > 0) {
                        synchronized (this.f12792h) {
                            this.f12792h.wait(b());
                        }
                    }
                }
            } catch (Throwable th) {
                com.jingdong.jdpush_new.j.g.g(th);
                com.jingdong.jdpush_new.mta.b.b().i(0, th);
            }
        } catch (InterruptedException unused) {
            com.jingdong.jdpush_new.j.g.c("long conn manager thread interrupted");
        }
    }

    private c() {
        this.f12792h = new byte[0];
        this.f12793i = 0;
        this.f12795k = false;
        this.f12796l = false;
        this.o = d.c().d();
    }
}
