package com.jdpay.membercode.e;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: classes18.dex */
public abstract class a {
    protected final ScheduledExecutorService a = Executors.newSingleThreadScheduledExecutor();
    protected ScheduledFuture b;

    /* renamed from: c  reason: collision with root package name */
    protected int f7530c;

    /* renamed from: com.jdpay.membercode.e.a$a  reason: collision with other inner class name */
    /* loaded from: classes18.dex */
    class RunnableC0230a implements Runnable {
        RunnableC0230a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c(true);
        }
    }

    public void a() {
        ScheduledFuture scheduledFuture = this.b;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public void b(int i2, int i3) {
        this.f7530c = i3;
        a();
        this.b = this.a.scheduleAtFixedRate(new RunnableC0230a(), i2, i3, TimeUnit.SECONDS);
    }

    public abstract void c(boolean z);

    public boolean d() {
        ScheduledFuture scheduledFuture = this.b;
        return (scheduledFuture == null || scheduledFuture.isCancelled()) ? false : true;
    }
}
