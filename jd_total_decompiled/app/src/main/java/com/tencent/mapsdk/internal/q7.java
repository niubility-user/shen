package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public abstract class q7 {

    /* renamed from: f  reason: collision with root package name */
    public static final long f17029f = -1;

    /* renamed from: g  reason: collision with root package name */
    public static final int f17030g = 0;

    /* renamed from: h  reason: collision with root package name */
    public static final int f17031h = 1;

    /* renamed from: i  reason: collision with root package name */
    public static final int f17032i = 2;
    public long a;
    private boolean d;

    /* renamed from: e  reason: collision with root package name */
    private long f17034e;

    /* renamed from: c  reason: collision with root package name */
    private long f17033c = -1;
    public int b = 0;

    public q7(long j2) {
        this.a = j2;
    }

    public long a() {
        return this.a;
    }

    public void a(long j2) {
        this.f17034e = j2;
    }

    public void a(GL10 gl10) {
        if (this.b != 1) {
            return;
        }
        if (this.f17033c == -1) {
            this.f17033c = SystemClock.elapsedRealtime();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - this.f17033c;
        long j2 = this.f17034e;
        if (j2 - elapsedRealtime > 0) {
            return;
        }
        long j3 = elapsedRealtime - j2;
        if (j3 >= this.a) {
            if (this.d) {
                d();
            }
            this.b = 2;
        }
        a(gl10, j3);
    }

    public abstract void a(GL10 gl10, long j2);

    public void a(boolean z) {
        this.d = z;
    }

    public boolean b() {
        return this.b == 1;
    }

    public boolean c() {
        return this.b == 2;
    }

    public void d() {
        this.b = 1;
        this.f17033c = -1L;
    }

    public void e() {
        this.b = 1;
        this.f17033c = -1L;
    }

    public void f() {
        this.b = 2;
    }
}
