package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class z implements b1, be {
    public static final int q = 60;
    private static final int r = 200;

    /* renamed from: k  reason: collision with root package name */
    private a f17527k;

    /* renamed from: m  reason: collision with root package name */
    private c9 f17529m;

    /* renamed from: n  reason: collision with root package name */
    private long f17530n;
    private boolean o;
    private k5 p;

    /* renamed from: g  reason: collision with root package name */
    private ArrayList<z8> f17523g = new ArrayList<>();

    /* renamed from: h  reason: collision with root package name */
    private final byte[] f17524h = new byte[0];

    /* renamed from: i  reason: collision with root package name */
    private ArrayList<z8> f17525i = new ArrayList<>();

    /* renamed from: j  reason: collision with root package name */
    private ArrayList<z8> f17526j = new ArrayList<>();

    /* renamed from: l  reason: collision with root package name */
    private int f17528l = 60;

    /* loaded from: classes9.dex */
    public class a extends Thread {
        private boolean a;
        private boolean b;

        public a() {
            super("tms-act");
        }

        public synchronized void a() {
            this.b = false;
        }

        public synchronized void b() {
            this.b = true;
        }

        @Override // java.lang.Thread
        public synchronized void destroy() {
            this.a = false;
            interrupt();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (this.a) {
                if (!this.b) {
                    if (z.this.f17529m != null) {
                        z.this.f17529m.a(z8.z);
                    }
                    if (z.this.o && SystemClock.elapsedRealtime() - z.this.f17530n > 50) {
                        z.this.o = false;
                        ma.a(la.f16819f, "notify onStable");
                        if (z.this.p != null) {
                            z.this.p.b();
                        }
                    }
                }
                try {
                    synchronized (this) {
                        wait(z.this.e());
                    }
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }

        @Override // java.lang.Thread
        public synchronized void start() {
            this.a = true;
            super.start();
        }
    }

    public z(c9 c9Var) {
        this.f17529m = c9Var;
        y8.f(e());
    }

    @Override // com.tencent.mapsdk.internal.be
    public void a() {
    }

    public void a(double d, double d2) {
        synchronized (this.f17524h) {
            while (!this.f17523g.isEmpty()) {
                ArrayList<z8> arrayList = this.f17523g;
                if (arrayList.get(arrayList.size() - 1).a != 3) {
                    break;
                }
                ArrayList<z8> arrayList2 = this.f17523g;
                double[] dArr = arrayList2.remove(arrayList2.size() - 1).b;
                d += dArr[0];
                d2 += dArr[1];
            }
            a(new z8(3, new double[]{d, d2}));
        }
    }

    public void a(int i2) {
        synchronized (this.f17524h) {
            for (int size = this.f17523g.size() - 1; size >= 0; size--) {
                z8 z8Var = this.f17523g.get(size);
                if (z8Var.a == i2) {
                    this.f17523g.remove(size);
                    z8Var.b();
                }
            }
        }
    }

    public void a(k5 k5Var) {
        this.p = k5Var;
    }

    @Override // com.tencent.mapsdk.internal.b1
    public void a(v vVar) {
        this.o = true;
        this.f17530n = SystemClock.elapsedRealtime();
    }

    public void a(z8 z8Var) {
        synchronized (this.f17524h) {
            if (this.f17523g.size() > 200) {
                this.f17523g.clear();
            }
            this.f17523g.add(z8Var);
        }
        a();
    }

    public void b() {
        ArrayList<z8> arrayList;
        synchronized (this.f17524h) {
            this.f17526j.clear();
            this.f17525i.clear();
            boolean z = false;
            Iterator<z8> it = this.f17523g.iterator();
            while (it.hasNext()) {
                z8 next = it.next();
                if (next.f17566e) {
                    z = true;
                    arrayList = this.f17525i;
                } else {
                    arrayList = this.f17526j;
                }
                arrayList.add(next);
            }
            this.f17523g.clear();
            if (z) {
                ArrayList<z8> arrayList2 = this.f17523g;
                this.f17523g = this.f17525i;
                this.f17525i = arrayList2;
            }
            if (this.f17526j.size() > 0) {
                Iterator<z8> it2 = this.f17526j.iterator();
                while (it2.hasNext()) {
                    it2.next().b();
                }
            }
        }
    }

    public void b(int i2) {
        if (i2 <= 0) {
            return;
        }
        this.f17528l = i2;
    }

    public void c() {
        a aVar = this.f17527k;
        if (aVar != null) {
            aVar.destroy();
        }
    }

    public void d() {
        a aVar = this.f17527k;
    }

    public long e() {
        long j2 = 1000 / this.f17528l;
        if (j2 == 0) {
            return 1L;
        }
        return j2;
    }

    public int f() {
        return this.f17528l;
    }

    public boolean g() {
        boolean z;
        synchronized (this.f17524h) {
            z = !this.f17523g.isEmpty();
        }
        return z;
    }

    public void h() {
        a aVar = this.f17527k;
        if (aVar != null) {
            aVar.b();
        }
        b();
    }

    public boolean i() {
        boolean isEmpty;
        synchronized (this.f17524h) {
            if (this.f17523g.isEmpty()) {
                return false;
            }
            z8 z8Var = this.f17523g.get(0);
            if (z8Var != null && z8Var.a(this.f17529m)) {
                z8Var.c();
                synchronized (this.f17524h) {
                    this.f17523g.remove(z8Var);
                }
            }
            synchronized (this.f17524h) {
                isEmpty = this.f17523g.isEmpty();
            }
            return !isEmpty;
        }
    }

    public void j() {
        this.f17528l = 60;
    }

    public void k() {
        a aVar = this.f17527k;
        if (aVar != null) {
            aVar.a();
        }
    }

    public void l() {
        a aVar = this.f17527k;
        if (aVar != null) {
            aVar.destroy();
        }
        a aVar2 = new a();
        this.f17527k = aVar2;
        aVar2.start();
    }
}
