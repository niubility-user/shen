package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: classes8.dex */
public abstract class e {
    private long a;
    private long b;

    /* renamed from: c  reason: collision with root package name */
    private long f15242c;
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private Handler f15243e = new a();

    /* loaded from: classes8.dex */
    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i2 = message.what;
            synchronized (e.this) {
                long elapsedRealtime = e.this.f15242c - SystemClock.elapsedRealtime();
                if (elapsedRealtime > 0) {
                    if (elapsedRealtime < e.this.b) {
                        if (e.this.e(elapsedRealtime, i2)) {
                            sendMessageDelayed(obtainMessage(i2), elapsedRealtime);
                        }
                    } else {
                        long elapsedRealtime2 = SystemClock.elapsedRealtime();
                        if (e.this.e(elapsedRealtime, i2)) {
                            long elapsedRealtime3 = (elapsedRealtime2 + e.this.b) - SystemClock.elapsedRealtime();
                            while (elapsedRealtime3 < 0) {
                                elapsedRealtime3 += e.this.b;
                            }
                            sendMessageDelayed(obtainMessage(i2), elapsedRealtime3);
                        }
                    }
                } else {
                    e.this.d(i2);
                }
            }
        }
    }

    public e(long j2, long j3, int i2) {
        this.a = j2;
        this.b = j3;
        this.d = i2;
    }

    public final void c(int i2) {
        this.f15243e.removeMessages(i2);
    }

    public abstract void d(int i2);

    public abstract boolean e(long j2, int i2);

    public final synchronized void f(long j2, long j3, int i2) {
        if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
            com.jingdong.sdk.lib.puppetlayout.g.b.a("MyCountdownTimer", "reset(); mMillisInFuture=" + this.a + "\tmCountdownInterval=" + this.b);
        }
        this.a = j2;
        this.b = j3;
        this.d = i2;
        g();
    }

    public final synchronized e g() {
        if (this.a <= 0) {
            d(this.d);
            return this;
        }
        this.f15242c = SystemClock.elapsedRealtime() + this.a;
        Handler handler = this.f15243e;
        handler.sendMessage(handler.obtainMessage(this.d));
        return this;
    }
}
