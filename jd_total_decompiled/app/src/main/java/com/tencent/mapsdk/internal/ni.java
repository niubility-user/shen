package com.tencent.mapsdk.internal;

import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;

/* loaded from: classes9.dex */
public class ni extends z8 {
    public static final int A0 = 10000;
    public static final long z0 = 500;
    private final Runnable B;
    private final Runnable C;
    private c D;
    public long E;
    private boolean F;
    public long G;
    private Interpolator H;
    private TencentMap.CancelableCallback I;
    private boolean J;
    private boolean K;
    private boolean L;
    private int M;
    private int N;
    private int O;
    private int P;
    private int Q;
    private int R;
    private boolean S;
    private boolean T;
    private boolean U;
    private boolean V;
    private float W;
    private float X;
    private double Y;
    private double Z;
    private double a0;
    private double b0;
    private double c0;
    private boolean d0;
    private int e0;
    private int f0;
    private boolean g0;
    public int h0;
    public int i0;
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0;
    private boolean p0;
    public float q0;
    public float r0;
    public float s0;
    public float t0;
    private boolean u0;
    public float v0;
    public float w0;
    public float x0;
    public float y0;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ni.this.I == null) {
                return;
            }
            ni.this.I.onFinish();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (ni.this.I == null) {
                return;
            }
            ni.this.I.onCancel();
        }
    }

    /* loaded from: classes9.dex */
    public interface c {
        float a();

        void a(Runnable runnable);

        GeoPoint b();

        GeoPoint c();

        float d();

        float e();

        int f();

        boolean g();

        float h();
    }

    public ni(int i2) {
        super(i2, null);
        this.B = new a();
        this.C = new b();
        this.D = null;
        this.E = 500L;
        this.F = false;
        this.G = 0L;
        this.H = new LinearInterpolator();
        this.I = null;
        this.J = false;
        this.K = false;
        this.L = false;
        this.M = 0;
        this.N = 0;
        this.O = 0;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.S = false;
        this.T = false;
        this.U = false;
        this.V = false;
        this.W = 0.0f;
        this.X = 0.0f;
        this.Y = 0.0d;
        this.Z = 0.0d;
        this.a0 = 0.0d;
        this.b0 = 0.0d;
        this.c0 = 1.0d;
        this.d0 = false;
        this.e0 = 0;
        this.f0 = 0;
        this.g0 = false;
        this.h0 = 0;
        this.i0 = 0;
        this.j0 = 0;
        this.k0 = 0;
        this.l0 = 0;
        this.m0 = 0;
        this.n0 = 0;
        this.o0 = 0;
        this.p0 = false;
        this.q0 = 0.0f;
        this.r0 = 0.0f;
        this.s0 = 0.0f;
        this.t0 = 0.0f;
        this.u0 = false;
        this.v0 = 0.0f;
        this.w0 = 0.0f;
        this.x0 = 0.0f;
        this.y0 = 0.0f;
    }

    private float a(float f2) {
        return ((f2 % 360.0f) + 360.0f) % 360.0f;
    }

    private float b(float f2) {
        float f3 = ((f2 % 360.0f) + 360.0f) % 360.0f;
        if (f3 > 45.0f) {
            return 45.0f;
        }
        return f3;
    }

    public void a(double d) {
        this.Y = d;
        this.U = true;
    }

    public void a(int i2, int i3) {
        this.h0 = i2;
        this.i0 = i3;
        this.g0 = true;
    }

    public void a(long j2) {
        this.E = j2;
    }

    public void a(c cVar) {
        this.D = cVar;
    }

    public void a(TencentMap.CancelableCallback cancelableCallback) {
        this.I = cancelableCallback;
    }

    public void a(boolean z) {
        this.J = z;
    }

    @Override // com.tencent.mapsdk.internal.z8
    public void b() {
        c cVar;
        super.b();
        this.K = true;
        if (this.I == null || (cVar = this.D) == null) {
            return;
        }
        cVar.a(this.C);
    }

    public void b(int i2, int i3) {
        this.e0 = i2;
        this.f0 = i3;
        this.d0 = true;
    }

    public void c(float f2) {
        this.q0 = a(f2);
        this.p0 = true;
    }

    public void c(int i2, int i3) {
        if (i2 == 0 && i3 == 0) {
            this.L = false;
        }
        this.M = i2;
        this.N = i3;
        this.L = true;
    }

    public void d(float f2) {
        this.v0 = b(f2);
        this.u0 = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:70:0x0124, code lost:
        if (r0 > 1.0f) goto L71;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d2  */
    @Override // com.tencent.mapsdk.internal.z8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean d() {
        float f2;
        c cVar;
        double a2;
        c cVar2;
        float f3;
        c cVar3;
        GeoPoint c2;
        if (!this.F) {
            this.F = true;
            if (this.L) {
                this.P = 0;
                this.R = 0;
            }
            if (this.S || this.T || this.U) {
                this.Z = 1.0d / Math.pow(2.0d, 20.0f - this.D.a());
            }
            if (this.S) {
                int i2 = this.D.g() ? 22 : 20;
                c cVar4 = this.D;
                if (cVar4 != null) {
                    i2 = Math.min(cVar4.f(), i2);
                }
                float f4 = i2;
                if (this.W >= f4) {
                    this.W = f4;
                }
                if (this.D != null) {
                    if (Math.abs(this.W - r0.a()) < 0.001d) {
                        this.V = true;
                    }
                }
                a2 = 20 - this.W;
            } else {
                if (this.T) {
                    if (Math.abs(this.X) < 0.001d) {
                        this.V = true;
                    }
                    a2 = 20.0f - (this.D.a() + this.X);
                }
                if (this.g0 && (cVar3 = this.D) != null) {
                    c2 = !this.J ? cVar3.c() : cVar3.b();
                    if (c2 != null) {
                        this.j0 = c2.getLatitudeE6();
                        int longitudeE6 = c2.getLongitudeE6();
                        this.k0 = longitudeE6;
                        this.l0 = this.h0 - this.j0;
                        this.m0 = this.i0 - longitudeE6;
                    }
                }
                if (this.p0) {
                    c cVar5 = this.D;
                    if (cVar5 != null) {
                        this.r0 = cVar5.d();
                    }
                    float f5 = this.q0 - this.r0;
                    this.s0 = f5;
                    if (f5 > 180.0f) {
                        f3 = f5 - 360.0f;
                    } else if (f5 < -180.0f) {
                        f3 = f5 + 360.0f;
                    }
                    this.s0 = f3;
                }
                if (this.u0 && (cVar2 = this.D) != null) {
                    float h2 = cVar2.h();
                    this.w0 = h2;
                    this.x0 = this.v0 - h2;
                }
                this.G = e();
            }
            this.Y = 1.0d / Math.pow(2.0d, a2);
            if (this.g0) {
                if (!this.J) {
                }
                if (c2 != null) {
                }
            }
            if (this.p0) {
            }
            if (this.u0) {
                float h22 = cVar2.h();
                this.w0 = h22;
                this.x0 = this.v0 - h22;
            }
            this.G = e();
        }
        if (!this.K) {
            f2 = ((float) (e() - this.G)) / ((float) this.E);
        }
        f2 = 1.0f;
        float interpolation = this.H.getInterpolation(f2);
        this.K = true;
        if (this.L) {
            int i3 = this.M;
            int i4 = (int) (i3 * interpolation);
            this.O = i4;
            int i5 = (int) (this.N * interpolation);
            this.Q = i5;
            this.P = i4;
            this.R = i5;
            this.O = i4 - this.P;
            this.Q = i5 - this.R;
            if (Math.abs(i3) > 0 || Math.abs(this.N) > 0) {
                this.K = false;
            }
        }
        if (this.S || this.T || this.U) {
            double d = this.Z;
            double d2 = interpolation;
            Double.isNaN(d2);
            this.a0 = d + ((this.Y - d) * d2);
            if (!this.V) {
                this.K = false;
            }
        }
        if (this.g0) {
            int i6 = this.j0;
            int i7 = this.l0;
            this.n0 = i6 + ((int) (i7 * interpolation));
            this.o0 = this.k0 + ((int) (this.m0 * interpolation));
            if (Math.abs(i7) > 1 || Math.abs(this.m0) > 1) {
                this.K = false;
            }
        }
        if (this.p0) {
            float f6 = this.r0;
            float f7 = this.s0;
            this.t0 = f6 + (f7 * interpolation);
            if (Math.abs(f7) > 1.0f) {
                this.K = false;
            }
        }
        if (this.u0) {
            float f8 = this.w0;
            float f9 = this.x0;
            this.y0 = f8 + (interpolation * f9);
            if (Math.abs(f9) > 1.0f) {
                this.K = false;
            }
        }
        if (f2 >= 1.0f) {
            if (this.I != null && (cVar = this.D) != null) {
                cVar.a(this.B);
            }
            return true;
        }
        return false;
    }

    public long e() {
        return SystemClock.uptimeMillis();
    }

    public void e(float f2) {
        if (f2 == 0.0f) {
            return;
        }
        this.X = f2;
        this.T = true;
    }

    public int f() {
        return this.e0;
    }

    public void f(float f2) {
        if (f2 <= 0.0f) {
            return;
        }
        this.W = f2;
        this.S = true;
    }

    public int g() {
        return this.f0;
    }

    public int h() {
        return this.n0;
    }

    public int i() {
        return this.o0;
    }

    public float j() {
        return this.t0;
    }

    public float k() {
        return (float) this.a0;
    }

    public int l() {
        return this.O;
    }

    public int m() {
        return this.Q;
    }

    public float n() {
        return this.y0;
    }

    public boolean o() {
        return this.g0;
    }

    public boolean p() {
        return this.d0;
    }

    public boolean q() {
        return this.J;
    }

    public boolean r() {
        return this.p0;
    }

    public boolean s() {
        return this.L;
    }

    public boolean t() {
        return this.u0;
    }

    public boolean u() {
        return this.T || this.S || this.U;
    }
}
