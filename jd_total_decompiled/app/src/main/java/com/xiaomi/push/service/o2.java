package com.xiaomi.push.service;

import android.os.SystemClock;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: classes11.dex */
public class o2 {

    /* renamed from: c  reason: collision with root package name */
    private static long f19160c;
    private static long d;
    private final c a;
    private final a b;

    /* loaded from: classes11.dex */
    private static final class a {
        private final c a;

        a(c cVar) {
            this.a = cVar;
        }

        protected void finalize() {
            try {
                synchronized (this.a) {
                    this.a.f19166k = true;
                    this.a.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    /* loaded from: classes11.dex */
    public static abstract class b implements Runnable {

        /* renamed from: g  reason: collision with root package name */
        protected int f19161g;

        public b(int i2) {
            this.f19161g = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static final class c extends Thread {

        /* renamed from: j  reason: collision with root package name */
        private boolean f19165j;

        /* renamed from: k  reason: collision with root package name */
        private boolean f19166k;

        /* renamed from: g  reason: collision with root package name */
        private volatile long f19162g = 0;

        /* renamed from: h  reason: collision with root package name */
        private volatile boolean f19163h = false;

        /* renamed from: i  reason: collision with root package name */
        private long f19164i = 50;

        /* renamed from: l  reason: collision with root package name */
        private a f19167l = new a();

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes11.dex */
        public static final class a {
            private int a;
            private d[] b;

            /* renamed from: c  reason: collision with root package name */
            private int f19168c;
            private int d;

            private a() {
                this.a = 256;
                this.b = new d[256];
                this.f19168c = 0;
                this.d = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int b(d dVar) {
                int i2 = 0;
                while (true) {
                    d[] dVarArr = this.b;
                    if (i2 >= dVarArr.length) {
                        return -1;
                    }
                    if (dVarArr[i2] == dVar) {
                        return i2;
                    }
                    i2++;
                }
            }

            private void l() {
                int i2 = this.f19168c - 1;
                int i3 = (i2 - 1) / 2;
                while (true) {
                    d[] dVarArr = this.b;
                    if (dVarArr[i2].f19169c >= dVarArr[i3].f19169c) {
                        return;
                    }
                    d dVar = dVarArr[i2];
                    dVarArr[i2] = dVarArr[i3];
                    dVarArr[i3] = dVar;
                    int i4 = i3;
                    i3 = (i3 - 1) / 2;
                    i2 = i4;
                }
            }

            private void m(int i2) {
                int i3 = (i2 * 2) + 1;
                while (true) {
                    int i4 = this.f19168c;
                    if (i3 >= i4 || i4 <= 0) {
                        return;
                    }
                    int i5 = i3 + 1;
                    if (i5 < i4) {
                        d[] dVarArr = this.b;
                        if (dVarArr[i5].f19169c < dVarArr[i3].f19169c) {
                            i3 = i5;
                        }
                    }
                    d[] dVarArr2 = this.b;
                    if (dVarArr2[i2].f19169c < dVarArr2[i3].f19169c) {
                        return;
                    }
                    d dVar = dVarArr2[i2];
                    dVarArr2[i2] = dVarArr2[i3];
                    dVarArr2[i3] = dVar;
                    int i6 = i3;
                    i3 = (i3 * 2) + 1;
                    i2 = i6;
                }
            }

            public d c() {
                return this.b[0];
            }

            public void d() {
                this.b = new d[this.a];
                this.f19168c = 0;
            }

            public void e(int i2) {
                for (int i3 = 0; i3 < this.f19168c; i3++) {
                    d[] dVarArr = this.b;
                    if (dVarArr[i3].f19170e == i2) {
                        dVarArr[i3].b();
                    }
                }
                j();
            }

            public void f(int i2, b bVar) {
                for (int i3 = 0; i3 < this.f19168c; i3++) {
                    d[] dVarArr = this.b;
                    if (dVarArr[i3].d == bVar) {
                        dVarArr[i3].b();
                    }
                }
                j();
            }

            public void g(d dVar) {
                d[] dVarArr = this.b;
                int length = dVarArr.length;
                int i2 = this.f19168c;
                if (length == i2) {
                    d[] dVarArr2 = new d[i2 * 2];
                    System.arraycopy(dVarArr, 0, dVarArr2, 0, i2);
                    this.b = dVarArr2;
                }
                d[] dVarArr3 = this.b;
                int i3 = this.f19168c;
                this.f19168c = i3 + 1;
                dVarArr3[i3] = dVar;
                l();
            }

            public boolean h() {
                return this.f19168c == 0;
            }

            public boolean i(int i2) {
                for (int i3 = 0; i3 < this.f19168c; i3++) {
                    if (this.b[i3].f19170e == i2) {
                        return true;
                    }
                }
                return false;
            }

            public void j() {
                int i2 = 0;
                while (i2 < this.f19168c) {
                    if (this.b[i2].b) {
                        this.d++;
                        k(i2);
                        i2--;
                    }
                    i2++;
                }
            }

            public void k(int i2) {
                int i3;
                if (i2 < 0 || i2 >= (i3 = this.f19168c)) {
                    return;
                }
                d[] dVarArr = this.b;
                int i4 = i3 - 1;
                this.f19168c = i4;
                dVarArr[i2] = dVarArr[i4];
                dVarArr[i4] = null;
                m(i2);
            }
        }

        c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(d dVar) {
            this.f19167l.g(dVar);
            notify();
        }

        public synchronized void a() {
            this.f19165j = true;
            this.f19167l.d();
            notify();
        }

        public boolean d() {
            return this.f19163h && SystemClock.uptimeMillis() - this.f19162g > 600000;
        }

        /* JADX WARN: Code restructure failed: missing block: B:50:0x008e, code lost:
            r10.f19162g = android.os.SystemClock.uptimeMillis();
            r10.f19163h = true;
            r2.d.run();
            r10.f19163h = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x009f, code lost:
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a0, code lost:
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00a1, code lost:
            r10.f19165j = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00a4, code lost:
            throw r1;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void run() {
            while (true) {
                synchronized (this) {
                    if (this.f19165j) {
                        return;
                    }
                    if (!this.f19167l.h()) {
                        long a2 = o2.a();
                        d c2 = this.f19167l.c();
                        synchronized (c2.a) {
                            if (c2.b) {
                                this.f19167l.k(0);
                            } else {
                                long j2 = c2.f19169c - a2;
                                if (j2 > 0) {
                                    long j3 = this.f19164i;
                                    if (j2 > j3) {
                                        j2 = j3;
                                    }
                                    long j4 = j3 + 50;
                                    this.f19164i = j4;
                                    if (j4 > 500) {
                                        this.f19164i = 500L;
                                    }
                                    wait(j2);
                                } else {
                                    this.f19164i = 50L;
                                    synchronized (c2.a) {
                                        int b = this.f19167l.c().f19169c != c2.f19169c ? this.f19167l.b(c2) : 0;
                                        if (c2.b) {
                                            a aVar = this.f19167l;
                                            aVar.k(aVar.b(c2));
                                        } else {
                                            c2.a(c2.f19169c);
                                            this.f19167l.k(b);
                                            c2.f19169c = 0L;
                                        }
                                    }
                                }
                            }
                        }
                    } else if (this.f19166k) {
                        return;
                    } else {
                        try {
                            wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class d {
        final Object a = new Object();
        boolean b;

        /* renamed from: c  reason: collision with root package name */
        long f19169c;
        b d;

        /* renamed from: e  reason: collision with root package name */
        int f19170e;

        d() {
        }

        void a(long j2) {
            synchronized (this.a) {
            }
        }

        public boolean b() {
            boolean z;
            synchronized (this.a) {
                z = !this.b && this.f19169c > 0;
                this.b = true;
            }
            return z;
        }
    }

    static {
        long elapsedRealtime = SystemClock.elapsedRealtime() > 0 ? SystemClock.elapsedRealtime() : 0L;
        f19160c = elapsedRealtime;
        d = elapsedRealtime;
    }

    public o2(String str) {
        this(str, false);
    }

    public o2(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        c cVar = new c(str, z);
        this.a = cVar;
        this.b = new a(cVar);
    }

    static synchronized long a() {
        long j2;
        synchronized (o2.class) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long j3 = d;
            if (elapsedRealtime > j3) {
                f19160c += elapsedRealtime - j3;
            }
            d = elapsedRealtime;
            j2 = f19160c;
        }
        return j2;
    }

    private void j(b bVar, long j2) {
        synchronized (this.a) {
            if (this.a.f19165j) {
                throw new IllegalStateException("Timer was canceled");
            }
            long a2 = j2 + a();
            if (a2 < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + a2);
            }
            d dVar = new d();
            dVar.f19170e = bVar.f19161g;
            dVar.d = bVar;
            dVar.f19169c = a2;
            this.a.c(dVar);
        }
    }

    public void b() {
        g.j.a.a.a.c.o("quit. finalizer:" + this.b);
        this.a.a();
    }

    public void c(int i2) {
        synchronized (this.a) {
            this.a.f19167l.e(i2);
        }
    }

    public void d(int i2, b bVar) {
        synchronized (this.a) {
            this.a.f19167l.f(i2, bVar);
        }
    }

    public void e(b bVar) {
        if (g.j.a.a.a.c.a() >= 1 || Thread.currentThread() == this.a) {
            bVar.run();
        } else {
            g.j.a.a.a.c.D("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public void f(b bVar, long j2) {
        if (j2 >= 0) {
            j(bVar, j2);
            return;
        }
        throw new IllegalArgumentException("delay < 0: " + j2);
    }

    public boolean g() {
        return this.a.d();
    }

    public boolean h(int i2) {
        boolean i3;
        synchronized (this.a) {
            i3 = this.a.f19167l.i(i2);
        }
        return i3;
    }

    public void i() {
        synchronized (this.a) {
            this.a.f19167l.d();
        }
    }
}
