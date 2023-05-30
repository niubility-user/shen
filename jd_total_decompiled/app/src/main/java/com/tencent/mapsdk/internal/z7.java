package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.tencent.map.lib.models.GeoPoint;

/* loaded from: classes9.dex */
public abstract class z7 {
    public long a = 1500;
    private boolean b = false;

    /* renamed from: c  reason: collision with root package name */
    public long f17553c = 0;
    private boolean d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f17554e = false;

    /* renamed from: f  reason: collision with root package name */
    private Interpolator f17555f = new LinearInterpolator();

    /* renamed from: g  reason: collision with root package name */
    public a f17556g = null;

    /* renamed from: h  reason: collision with root package name */
    public b f17557h = null;

    /* loaded from: classes9.dex */
    public interface a {
        void a();

        void onAnimationStart();
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(float f2);

        void a(float f2, float f3, float f4, float f5);

        void a(int i2, int i3);

        void setAlpha(float f2);

        void setScale(float f2, float f3);
    }

    private long b() {
        return SystemClock.uptimeMillis();
    }

    public void a() {
        a aVar;
        if (!this.b) {
            if (!this.d || this.f17554e || (aVar = this.f17556g) == null) {
                return;
            }
            aVar.a();
            return;
        }
        float b2 = ((float) (b() - this.f17553c)) / ((float) this.a);
        if (b2 <= 1.0f) {
            a(b2, this.f17555f);
            return;
        }
        this.b = false;
        a(1.0f, this.f17555f);
        a aVar2 = this.f17556g;
        if (aVar2 != null) {
            aVar2.a();
        }
        this.f17554e = true;
    }

    public abstract void a(float f2, Interpolator interpolator);

    public void a(long j2) {
        this.a = j2;
    }

    public void a(Interpolator interpolator) {
        this.f17555f = interpolator;
    }

    public void a(a aVar) {
        this.f17556g = aVar;
    }

    public void a(b bVar) {
        this.f17557h = bVar;
    }

    public boolean a(GeoPoint geoPoint, GeoPoint geoPoint2) {
        if (this.a <= 0) {
            return false;
        }
        this.d = true;
        this.f17553c = b();
        this.b = true;
        a aVar = this.f17556g;
        if (aVar != null) {
            aVar.onAnimationStart();
        }
        return true;
    }

    public long c() {
        return this.a;
    }

    public Interpolator d() {
        return this.f17555f;
    }

    public boolean e() {
        return this.f17554e;
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.d;
    }

    public void h() {
        this.b = false;
    }
}
