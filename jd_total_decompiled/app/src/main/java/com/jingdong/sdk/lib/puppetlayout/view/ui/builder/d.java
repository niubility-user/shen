package com.jingdong.sdk.lib.puppetlayout.view.ui.builder;

import java.lang.ref.WeakReference;

/* loaded from: classes8.dex */
public class d {
    private e a;
    private int b = 2;

    /* renamed from: c  reason: collision with root package name */
    private boolean f15238c = true;
    private WeakReference<b> d = null;

    /* renamed from: e  reason: collision with root package name */
    public int f15239e;

    /* loaded from: classes8.dex */
    class a extends e {

        /* renamed from: f  reason: collision with root package name */
        final /* synthetic */ long f15240f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(long j2, long j3, int i2, long j4) {
            super(j2, j3, i2);
            this.f15240f = j4;
        }

        @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.e
        public void d(int i2) {
            if (d.this.d != null && d.this.d.get() != null) {
                ((b) d.this.d.get()).b(this, this.f15240f, i2);
            }
            d.this.b();
        }

        @Override // com.jingdong.sdk.lib.puppetlayout.view.ui.builder.e
        public boolean e(long j2, int i2) {
            long[] g2;
            d dVar = d.this;
            if (dVar.f15239e == 1) {
                g2 = d.f(j2);
            } else {
                g2 = dVar.g(j2);
            }
            long[] jArr = g2;
            if (d.this.d != null) {
                if (d.this.d.get() != null) {
                    ((b) d.this.d.get()).a(this, j2, jArr, i2);
                    if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                        com.jingdong.sdk.lib.puppetlayout.g.b.a("CountdownHelper", "setCountdown listenerRef get " + d.this.d.get());
                    }
                    return true;
                }
                d.this.b();
                if (com.jingdong.sdk.lib.puppetlayout.g.b.a) {
                    com.jingdong.sdk.lib.puppetlayout.g.b.a("CountdownHelper", "setCountdown listenerRef null");
                }
            }
            return false;
        }
    }

    /* loaded from: classes8.dex */
    public interface b {
        void a(e eVar, long j2, long[] jArr, int i2);

        boolean b(e eVar, long j2, int i2);
    }

    public d(int i2) {
        this.f15239e = 0;
        this.f15239e = i2;
    }

    public static long[] f(long j2) {
        long j3 = j2 / 1000;
        long j4 = j3 / 60;
        long j5 = (j4 / 60) / 24;
        if (j5 < 0) {
            j5 = 0;
        }
        long j6 = j5 * 60;
        long j7 = (((j2 - (((j6 * 60) * 1000) * 24)) / 1000) / 60) / 60;
        if (j7 < 0) {
            j7 = 0;
        }
        Long.signum(j6);
        long j8 = j6 * 24;
        long j9 = j7 * 60;
        long j10 = (j4 - j8) - j9;
        if (j10 < 0) {
            j10 = 0;
        }
        long j11 = ((j3 - (j8 * 60)) - (j9 * 60)) - (60 * j10);
        return new long[]{j5, j7, j10, j11 >= 0 ? j11 : 0L};
    }

    public void b() {
        e eVar = this.a;
        if (eVar != null) {
            this.f15238c = true;
            eVar.c(2);
            this.a.c(1);
            this.a.c(3);
            this.a = null;
        }
    }

    public long c(long j2, long j3) {
        if (j2 > 0) {
            this.b = 1;
            return j2;
        } else if (j3 > 0 && j2 < 0) {
            this.b = 2;
            return j3;
        } else if (j3 >= 0 || j2 >= 0) {
            return 0L;
        } else {
            this.b = 3;
            return 1L;
        }
    }

    public boolean d() {
        return this.f15238c;
    }

    public void e(long j2, long j3, b bVar) {
        long c2 = c(j2, j3);
        com.jingdong.sdk.lib.puppetlayout.g.b.a("CountdownHelper", " -->>setCountdown countdownTime=" + c2);
        e eVar = this.a;
        if (eVar == null) {
            this.d = new WeakReference<>(bVar);
            a aVar = new a(c2, 1000L, this.b, j3);
            aVar.g();
            this.a = aVar;
        } else {
            eVar.f(c2, 1000L, this.b);
        }
        this.f15238c = false;
    }

    public long[] g(long j2) {
        long j3 = j2 / 1000;
        long j4 = (j3 / 60) / 60;
        long j5 = j4 * 60 * 60;
        long j6 = ((j2 - (j5 * 1000)) / 1000) / 60;
        long j7 = (j3 - j5) - (60 * j6);
        if (j4 < 0) {
            j4 = 0;
        }
        if (j6 < 0) {
            j6 = 0;
        }
        if (j7 < 0) {
            j7 = 0;
        }
        return new long[]{j4, j6, j7};
    }
}
