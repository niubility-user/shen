package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.PointF;
import android.os.SystemClock;
import android.view.ViewConfiguration;

/* loaded from: classes9.dex */
public class uf implements v4 {

    /* renamed from: m  reason: collision with root package name */
    private static final int f17331m = 8;

    /* renamed from: n  reason: collision with root package name */
    private static final float f17332n = 10.0f;

    /* renamed from: c  reason: collision with root package name */
    private float f17333c;
    private float d;

    /* renamed from: e  reason: collision with root package name */
    private e1 f17334e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f17335f;

    /* renamed from: g  reason: collision with root package name */
    private float f17336g;

    /* renamed from: h  reason: collision with root package name */
    private float f17337h;

    /* renamed from: i  reason: collision with root package name */
    private xf f17338i;

    /* renamed from: k  reason: collision with root package name */
    private final float f17340k;

    /* renamed from: l  reason: collision with root package name */
    private a1 f17341l;
    private final long a = 250;
    private final long b = 1200;

    /* renamed from: j  reason: collision with root package name */
    private boolean f17339j = false;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ d0 a;

        public a(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.G();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ d0 a;

        public b(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.G();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public final /* synthetic */ d0 a;

        public c(d0 d0Var) {
            this.a = d0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.G();
        }
    }

    /* loaded from: classes9.dex */
    public class d extends z8 {
        public final /* synthetic */ long B;
        public final /* synthetic */ long C;
        public final /* synthetic */ PointF D;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public d(int i2, double[] dArr, long j2, long j3, PointF pointF) {
            super(i2, dArr);
            this.B = j2;
            this.C = j3;
            this.D = pointF;
        }

        @Override // com.tencent.mapsdk.internal.z8
        public void b() {
            uf.this.f17335f = false;
        }

        @Override // com.tencent.mapsdk.internal.z8
        public boolean d() {
            long elapsedRealtime = SystemClock.elapsedRealtime() - this.B;
            long j2 = this.C;
            if (elapsedRealtime > j2) {
                uf.this.f17335f = false;
                return true;
            }
            float f2 = this.D.x;
            if (f2 != 0.0f) {
                this.b[0] = w8.x(elapsedRealtime, f2, -f2, j2);
            }
            float f3 = this.D.y;
            if (f3 != 0.0f) {
                this.b[1] = w8.x(elapsedRealtime, f3, -f3, this.C);
            }
            return false;
        }
    }

    public uf(e1 e1Var) {
        this.f17333c = ViewConfiguration.getMinimumFlingVelocity();
        this.d = ViewConfiguration.getMaximumFlingVelocity();
        this.f17334e = e1Var;
        a1 a1Var = (a1) e1Var.j();
        this.f17341l = a1Var;
        if (a1Var != null) {
            a1Var.a(this);
            Context context = this.f17341l.getContext();
            if (context != null) {
                ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
                this.f17333c = viewConfiguration.getScaledMinimumFlingVelocity();
                this.d = viewConfiguration.getScaledMaximumFlingVelocity();
            }
        }
        this.f17338i = new xf();
        this.f17340k = e1Var.d() * 2.5f;
    }

    private void e(float f2, float f3) {
        this.f17339j = true;
        if (this.f17335f) {
            return;
        }
        float f4 = f2 / 64.0f;
        float f5 = f3 / 64.0f;
        if (Math.abs(f4) >= this.f17340k || Math.abs(f5) >= this.f17340k) {
            float max = Math.max(Math.abs(f2), Math.abs(f3));
            float f6 = this.f17333c;
            long j2 = (((max - f6) / (this.d - f6)) * 950.0f) + 250;
            PointF pointF = new PointF(f4, f5);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.f17335f = true;
            this.f17334e.h().d(new d(3, new double[]{0.0d, 0.0d}, elapsedRealtime, j2, pointF));
        }
    }

    private boolean f(float f2, float f3) {
        return this.f17334e.g().a(f2, f3);
    }

    public void a(xf xfVar) {
        this.f17338i = xfVar;
        boolean a2 = xfVar.a();
        a1 a1Var = this.f17341l;
        if (a1Var == null) {
            return;
        }
        if (a2) {
            a1Var.b(this);
        } else {
            a1Var.a(this);
        }
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a() {
        qa.a(new Object[0]);
        if (this.f17338i.d()) {
            d0 h2 = this.f17334e.h();
            h2.b(new b(h2));
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(float f2) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(PointF pointF, PointF pointF2, double d2, double d3) {
        if (this.f17338i.k()) {
            d0 h2 = this.f17334e.h();
            h2.a(d3 / d2, pointF.x, pointF.y, pointF2.x, pointF2.y, new c(h2));
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean a(PointF pointF, PointF pointF2, float f2) {
        if (this.f17338i.g()) {
            this.f17334e.h().a(f2, pointF.x, pointF.y, pointF2.x, pointF2.y);
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b() {
        if (this.f17335f) {
            this.f17334e.h().e();
            this.f17335f = false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b(float f2) {
        if (this.f17338i.j()) {
            this.f17334e.h().o((f2 / 8.0f) * 2.0f);
            return true;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean b(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public void c() {
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean c(float f2, float f3) {
        if (this.f17338i.b()) {
            this.f17336g = this.f17334e.h().q();
            this.f17337h = f3;
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean d() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean d(float f2, float f3) {
        qa.a(Float.valueOf(f2), Float.valueOf(f3));
        if (this.f17338i.b()) {
            d0 h2 = this.f17334e.h();
            double pow = Math.pow(2.0d, ((this.f17337h - f3) * f17332n) / this.f17334e.e().height());
            double d2 = this.f17336g;
            Double.isNaN(d2);
            h2.n(pow * d2);
        }
        return true;
    }

    public boolean e() {
        boolean z = this.f17339j;
        this.f17339j = false;
        return z;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onDoubleTap(float f2, float f3) {
        if (this.f17338i.b()) {
            d0 h2 = this.f17334e.h();
            h2.a(f2, f3, new a(h2));
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onDown(float f2, float f3) {
        this.f17334e.h().e();
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onFling(float f2, float f3) {
        if (this.f17338i.h() && this.f17338i.e()) {
            e(f2, f3);
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onLongPress(float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onScroll(float f2, float f3) {
        if (this.f17338i.h()) {
            this.f17334e.h().a(f2, f3);
            return false;
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onSingleTap(float f2, float f3) {
        if (this.f17338i.i()) {
            return f(f2, f3);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v4
    public boolean onUp(float f2, float f3) {
        return false;
    }
}
