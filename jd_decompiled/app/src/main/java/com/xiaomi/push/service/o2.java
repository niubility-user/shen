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
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                r10 = this;
            L0:
                monitor-enter(r10)
                boolean r0 = r10.f19165j     // Catch: java.lang.Throwable -> Lae
                if (r0 == 0) goto L7
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lae
                return
            L7:
                com.xiaomi.push.service.o2$c$a r0 = r10.f19167l     // Catch: java.lang.Throwable -> Lae
                boolean r0 = r0.h()     // Catch: java.lang.Throwable -> Lae
                if (r0 == 0) goto L1a
                boolean r0 = r10.f19166k     // Catch: java.lang.Throwable -> Lae
                if (r0 == 0) goto L15
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lae
                return
            L15:
                r10.wait()     // Catch: java.lang.InterruptedException -> L18 java.lang.Throwable -> Lae
            L18:
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lae
                goto L0
            L1a:
                long r0 = com.xiaomi.push.service.o2.a()     // Catch: java.lang.Throwable -> Lae
                com.xiaomi.push.service.o2$c$a r2 = r10.f19167l     // Catch: java.lang.Throwable -> Lae
                com.xiaomi.push.service.o2$d r2 = r2.c()     // Catch: java.lang.Throwable -> Lae
                java.lang.Object r3 = r2.a     // Catch: java.lang.Throwable -> Lae
                monitor-enter(r3)     // Catch: java.lang.Throwable -> Lae
                boolean r4 = r2.b     // Catch: java.lang.Throwable -> Lab
                r5 = 0
                if (r4 == 0) goto L33
                com.xiaomi.push.service.o2$c$a r0 = r10.f19167l     // Catch: java.lang.Throwable -> Lab
                r0.k(r5)     // Catch: java.lang.Throwable -> Lab
                monitor-exit(r3)     // Catch: java.lang.Throwable -> Lab
                goto L18
            L33:
                long r6 = r2.f19169c     // Catch: java.lang.Throwable -> Lab
                long r6 = r6 - r0
                monitor-exit(r3)     // Catch: java.lang.Throwable -> Lab
                r0 = 50
                r3 = 0
                int r8 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r8 <= 0) goto L55
                long r2 = r10.f19164i     // Catch: java.lang.Throwable -> Lae
                int r4 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                if (r4 <= 0) goto L46
                r6 = r2
            L46:
                long r2 = r2 + r0
                r10.f19164i = r2     // Catch: java.lang.Throwable -> Lae
                r0 = 500(0x1f4, double:2.47E-321)
                int r4 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r4 <= 0) goto L51
                r10.f19164i = r0     // Catch: java.lang.Throwable -> Lae
            L51:
                r10.wait(r6)     // Catch: java.lang.InterruptedException -> L18 java.lang.Throwable -> Lae
                goto L18
            L55:
                r10.f19164i = r0     // Catch: java.lang.Throwable -> Lae
                java.lang.Object r0 = r2.a     // Catch: java.lang.Throwable -> Lae
                monitor-enter(r0)     // Catch: java.lang.Throwable -> Lae
                com.xiaomi.push.service.o2$c$a r1 = r10.f19167l     // Catch: java.lang.Throwable -> La8
                com.xiaomi.push.service.o2$d r1 = r1.c()     // Catch: java.lang.Throwable -> La8
                long r6 = r1.f19169c     // Catch: java.lang.Throwable -> La8
                long r8 = r2.f19169c     // Catch: java.lang.Throwable -> La8
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 == 0) goto L6f
                com.xiaomi.push.service.o2$c$a r1 = r10.f19167l     // Catch: java.lang.Throwable -> La8
                int r1 = com.xiaomi.push.service.o2.c.a.a(r1, r2)     // Catch: java.lang.Throwable -> La8
                goto L70
            L6f:
                r1 = 0
            L70:
                boolean r6 = r2.b     // Catch: java.lang.Throwable -> La8
                if (r6 == 0) goto L7f
                com.xiaomi.push.service.o2$c$a r1 = r10.f19167l     // Catch: java.lang.Throwable -> La8
                int r2 = com.xiaomi.push.service.o2.c.a.a(r1, r2)     // Catch: java.lang.Throwable -> La8
                r1.k(r2)     // Catch: java.lang.Throwable -> La8
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La8
                goto L18
            L7f:
                long r6 = r2.f19169c     // Catch: java.lang.Throwable -> La8
                r2.a(r6)     // Catch: java.lang.Throwable -> La8
                com.xiaomi.push.service.o2$c$a r6 = r10.f19167l     // Catch: java.lang.Throwable -> La8
                r6.k(r1)     // Catch: java.lang.Throwable -> La8
                r2.f19169c = r3     // Catch: java.lang.Throwable -> La8
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La8
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lae
                r0 = 1
                long r3 = android.os.SystemClock.uptimeMillis()     // Catch: java.lang.Throwable -> L9f
                r10.f19162g = r3     // Catch: java.lang.Throwable -> L9f
                r10.f19163h = r0     // Catch: java.lang.Throwable -> L9f
                com.xiaomi.push.service.o2$b r1 = r2.d     // Catch: java.lang.Throwable -> L9f
                r1.run()     // Catch: java.lang.Throwable -> L9f
                r10.f19163h = r5     // Catch: java.lang.Throwable -> L9f
                goto L0
            L9f:
                r1 = move-exception
                monitor-enter(r10)
                r10.f19165j = r0     // Catch: java.lang.Throwable -> La5
                monitor-exit(r10)     // Catch: java.lang.Throwable -> La5
                throw r1
            La5:
                r0 = move-exception
                monitor-exit(r10)     // Catch: java.lang.Throwable -> La5
                throw r0
            La8:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> La8
                throw r1     // Catch: java.lang.Throwable -> Lae
            Lab:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> Lab
                throw r0     // Catch: java.lang.Throwable -> Lae
            Lae:
                r0 = move-exception
                monitor-exit(r10)     // Catch: java.lang.Throwable -> Lae
                goto Lb2
            Lb1:
                throw r0
            Lb2:
                goto Lb1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.o2.c.run():void");
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
