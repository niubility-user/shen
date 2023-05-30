package com.jingdong.app.mall.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class q extends Thread {

    /* renamed from: l  reason: collision with root package name */
    private static volatile q f11842l;

    /* renamed from: g  reason: collision with root package name */
    private Handler f11843g;

    /* renamed from: h  reason: collision with root package name */
    private final Object f11844h = new Object();

    /* renamed from: i  reason: collision with root package name */
    private final Object f11845i = new Object();

    /* renamed from: j  reason: collision with root package name */
    private int f11846j = 5;

    /* renamed from: k  reason: collision with root package name */
    private final AtomicBoolean f11847k = new AtomicBoolean(false);

    /* loaded from: classes.dex */
    class a implements MessageQueue.IdleHandler {
        a() {
        }

        @Override // android.os.MessageQueue.IdleHandler
        public boolean queueIdle() {
            synchronized (q.this.f11845i) {
                q.this.f11845i.notify();
            }
            return true;
        }
    }

    private q() {
    }

    public static synchronized q c() {
        q qVar;
        synchronized (q.class) {
            if (f11842l == null) {
                f11842l = new q();
            }
            qVar = f11842l;
        }
        return qVar;
    }

    public void b(int i2) {
        this.f11846j = c().getPriority();
        c().setPriority(i2);
    }

    public void d(Runnable runnable) {
        synchronized (this.f11844h) {
            if (this.f11843g == null) {
                try {
                    this.f11844h.wait();
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
            this.f11843g.post(runnable);
        }
    }

    public void e() {
        c().setPriority(this.f11846j);
    }

    public void f() {
        synchronized (this.f11845i) {
            try {
                this.f11845i.wait();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        if (this.f11847k.getAndSet(true)) {
            return;
        }
        Looper.prepare();
        synchronized (this.f11844h) {
            this.f11843g = new Handler();
            this.f11844h.notify();
        }
        Looper.myQueue().addIdleHandler(new a());
        Looper.loop();
    }
}
