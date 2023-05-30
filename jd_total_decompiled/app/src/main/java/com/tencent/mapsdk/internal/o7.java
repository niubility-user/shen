package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.tencent.map.lib.models.GeoPoint;

/* loaded from: classes9.dex */
public class o7 {
    private boolean b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f16911c;

    /* renamed from: e  reason: collision with root package name */
    private boolean f16912e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f16913f;
    public long a = 1500;
    public long d = 0;

    /* renamed from: g  reason: collision with root package name */
    private Interpolator f16914g = new LinearInterpolator();

    /* renamed from: h  reason: collision with root package name */
    public a f16915h = null;

    /* renamed from: i  reason: collision with root package name */
    public b f16916i = null;

    /* loaded from: classes9.dex */
    public static abstract class a {
        public void a() {
        }

        public void a(float f2) {
        }

        public void b() {
        }
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(float f2);

        void a(float f2, float f3, float f4, float f5);

        void a(GeoPoint geoPoint);

        void setAlpha(float f2);

        void setScale(float f2, float f3);
    }

    private float a(float f2) {
        Interpolator interpolator = this.f16914g;
        return interpolator != null ? interpolator.getInterpolation(f2) : f2;
    }

    private long b() {
        return SystemClock.uptimeMillis();
    }

    public void a() {
        if (!this.b && this.f16912e && this.d == 0) {
            this.d = b();
            a aVar = this.f16915h;
            if (aVar != null) {
                aVar.b();
            }
            this.b = true;
        }
        long b2 = b();
        float f2 = ((float) (b2 - this.d)) / ((float) this.a);
        if (f2 > 1.0f) {
            if (this.f16911c) {
                this.d = b2;
            } else {
                this.b = false;
            }
            f2 = 1.0f;
        }
        b(a(f2));
        if (this.b) {
            return;
        }
        this.f16913f = true;
        a aVar2 = this.f16915h;
        if (aVar2 != null) {
            aVar2.a();
        }
    }

    public void a(long j2) {
        this.a = j2;
    }

    public void a(Interpolator interpolator) {
        this.f16914g = interpolator;
    }

    public void a(a aVar) {
        this.f16915h = aVar;
    }

    public void a(b bVar) {
        this.f16916i = bVar;
    }

    public boolean a(boolean z) {
        if (this.a <= 0 || this.f16912e) {
            return false;
        }
        this.f16912e = true;
        this.f16911c = z;
        return true;
    }

    public void b(float f2) {
        a aVar = this.f16915h;
        if (aVar != null) {
            aVar.a(f2);
        }
    }

    public Interpolator c() {
        return this.f16914g;
    }

    public boolean d() {
        return this.f16913f;
    }

    public boolean e() {
        return this.f16911c;
    }

    public boolean f() {
        return this.b;
    }

    public boolean g() {
        return this.f16912e;
    }

    public boolean h() {
        return a(false);
    }

    public void i() {
        this.b = false;
    }
}
