package com.xiaomi.push;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* loaded from: classes11.dex */
public class l {
    private a a;
    private Handler b;

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f18812c;
    private final boolean d;

    /* renamed from: e  reason: collision with root package name */
    private int f18813e;

    /* renamed from: f  reason: collision with root package name */
    private volatile b f18814f;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class a extends Thread {

        /* renamed from: g  reason: collision with root package name */
        private final LinkedBlockingQueue<b> f18815g;

        public a() {
            super("PackageProcessor");
            this.f18815g = new LinkedBlockingQueue<>();
        }

        private void a(int i2, b bVar) {
            try {
                l.this.b.sendMessage(l.this.b.obtainMessage(i2, bVar));
            } catch (Exception e2) {
                g.j.a.a.a.c.s(e2);
            }
        }

        public void b(b bVar) {
            try {
                this.f18815g.add(bVar);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long j2 = l.this.f18813e > 0 ? l.this.f18813e : Long.MAX_VALUE;
            while (!l.this.f18812c) {
                try {
                    b poll = this.f18815g.poll(j2, TimeUnit.SECONDS);
                    l.this.f18814f = poll;
                    if (poll != null) {
                        a(0, poll);
                        poll.b();
                        a(1, poll);
                    } else if (l.this.f18813e > 0) {
                        l.this.d();
                    }
                } catch (InterruptedException e2) {
                    g.j.a.a.a.c.s(e2);
                }
            }
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class b {
        public void a() {
        }

        public abstract void b();

        public void c() {
        }
    }

    public l(boolean z) {
        this(z, 0);
    }

    public l(boolean z, int i2) {
        this.b = null;
        this.f18812c = false;
        this.f18813e = 0;
        this.b = new m(this, Looper.getMainLooper());
        this.d = z;
        this.f18813e = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void d() {
        this.a = null;
        this.f18812c = true;
    }

    public synchronized void e(b bVar) {
        if (this.a == null) {
            a aVar = new a();
            this.a = aVar;
            aVar.setDaemon(this.d);
            this.f18812c = false;
            this.a.start();
        }
        this.a.b(bVar);
    }

    public void f(b bVar, long j2) {
        this.b.postDelayed(new n(this, bVar), j2);
    }
}
