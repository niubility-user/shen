package com.jingdong.app.mall.home.shakeandshow;

import android.hardware.SensorEvent;
import android.os.SystemClock;
import com.jingdong.app.mall.home.o.a.k;
import com.jingdong.app.mall.home.o.a.l;
import com.jingdong.jdsdk.constant.JshopConst;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes4.dex */
public class b {
    private static final int[] d = {10, 5, 3};

    /* renamed from: e  reason: collision with root package name */
    private static final int[] f10818e = {3, 5, 8};

    /* renamed from: f  reason: collision with root package name */
    private static final AtomicBoolean f10819f = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private static long f10820g;
    private final a a = new a(this, JshopConst.JSHOP_PROMOTIO_X);
    private final a b = new a(this, JshopConst.JSHOP_PROMOTIO_Y);

    /* renamed from: c  reason: collision with root package name */
    private final a f10821c = new a(this, "z", 1);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public class a {
        final LinkedList<C0322b> a;
        c b;

        /* renamed from: c  reason: collision with root package name */
        c f10822c;
        c d;

        /* renamed from: e  reason: collision with root package name */
        String f10823e;

        public a(b bVar, String str) {
            this(bVar, str, 0);
        }

        void a(float f2) {
            C0322b first = this.a.size() <= 0 ? null : this.a.getFirst();
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (first != null && elapsedRealtime - first.b > 2000) {
                this.a.remove(first);
                first.b(f2);
                this.a.addLast(first);
            } else {
                this.a.addLast(new C0322b(f2));
            }
            com.jingdong.app.mall.home.o.a.f.r0(this, this.f10823e + " ActionSize = " + this.a.size());
        }

        void b() {
            Iterator<C0322b> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().a();
            }
        }

        boolean c() {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - b.f10820g < 2000) {
                return false;
            }
            Iterator<C0322b> it = this.a.iterator();
            C0322b c0322b = null;
            this.b.e();
            this.f10822c.e();
            this.d.e();
            while (it.hasNext()) {
                C0322b next = it.next();
                if (elapsedRealtime - b.f10820g >= 2000) {
                    float abs = c0322b == null ? 0.0f : Math.abs(c0322b.a - next.a);
                    this.b.a(abs);
                    this.f10822c.a(abs);
                    this.d.a(abs);
                    c0322b = next;
                }
            }
            if (k.v() && (this.b.c() | this.f10822c.c() | this.d.c())) {
                com.jingdong.app.mall.home.o.a.f.r0("ActionV", this.f10823e + "-----------------");
            }
            return this.b.b() || this.f10822c.b() || this.d.b();
        }

        public void d() {
            this.b.d();
            this.f10822c.d();
            this.d.d();
        }

        public a(b bVar, String str, int i2) {
            this.a = new LinkedList<>();
            this.f10823e = str;
            this.b = new c(0, i2);
            this.f10822c = new c(1, i2);
            this.d = new c(2, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.jingdong.app.mall.home.shakeandshow.b$b  reason: collision with other inner class name */
    /* loaded from: classes4.dex */
    public static class C0322b {
        float a;
        long b = SystemClock.elapsedRealtime();

        public C0322b(float f2) {
            this.a = f2;
        }

        void a() {
            this.a = 0.0f;
            this.b = 0L;
        }

        void b(float f2) {
            this.a = f2;
            this.b = SystemClock.elapsedRealtime();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class c {
        private final int a;
        private float b;

        /* renamed from: c  reason: collision with root package name */
        private int f10824c;
        private int d;

        /* renamed from: e  reason: collision with root package name */
        private int f10825e;

        public c(int i2, int i3) {
            this.a = i2;
            this.f10825e = i3;
            this.b = b.d[i2];
            this.f10824c = b.f10818e[i2] + i3;
        }

        void a(float f2) {
            if (f2 > this.b) {
                this.d++;
            }
        }

        boolean b() {
            return this.d > this.f10824c;
        }

        boolean c() {
            if (this.d > 1) {
                String str = "v(" + this.b + ") = " + this.d;
                if (b()) {
                    str = str + " >> match <<";
                }
                com.jingdong.app.mall.home.o.a.f.r0("ActionV", str);
                return true;
            }
            return false;
        }

        public void d() {
            this.f10825e = Math.min(this.f10825e + 1, 3);
        }

        void e() {
            this.d = 0;
            this.b = b.d[this.a];
            this.f10824c = b.f10818e[this.a] + this.f10825e;
        }
    }

    private boolean e(SensorEvent sensorEvent) {
        if (sensorEvent == null) {
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f10820g < 10000) {
            return false;
        }
        g();
        this.a.a(sensorEvent.values[0]);
        this.b.a(sensorEvent.values[1]);
        this.f10821c.a(sensorEvent.values[2]);
        boolean z = this.a.c() || this.b.c() || this.f10821c.c();
        if (z) {
            f10820g = elapsedRealtime;
            this.a.b();
            this.b.b();
            this.f10821c.b();
        }
        return z;
    }

    private void g() {
        if (f10819f.getAndSet(true)) {
            return;
        }
        int k2 = l.k();
        if (k2 > 1000) {
            int[] iArr = d;
            iArr[0] = Math.max(10, k2 / 100);
            iArr[1] = Math.max(5, (k2 / 10) % 10);
            iArr[2] = Math.max(3, k2 % 10);
        }
        int m2 = l.m();
        if (m2 > 1000) {
            int[] iArr2 = f10818e;
            iArr2[0] = Math.max(3, m2 / 1000);
            iArr2[1] = Math.max(5, (m2 / 100) % 10);
            iArr2[2] = Math.max(8, m2 % 100);
        }
    }

    public boolean d(SensorEvent sensorEvent) {
        try {
            if (k.w()) {
                return e(sensorEvent);
            }
            int n2 = l.n();
            if (sensorEvent != null && n2 > 0) {
                return e(sensorEvent);
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void f() {
        try {
            this.a.d();
            this.b.d();
            this.f10821c.d();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
