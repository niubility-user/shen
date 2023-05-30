package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.jd.dynamic.DYConstants;
import com.jingdong.common.utils.LangUtils;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.MapParamConstants;
import com.tencent.tencentmap.mapsdk.maps.model.GeometryConstants;

/* loaded from: classes9.dex */
public class v implements Cloneable {
    private int d;

    /* renamed from: e  reason: collision with root package name */
    private double f17355e;

    /* renamed from: f  reason: collision with root package name */
    private double f17356f;

    /* renamed from: n  reason: collision with root package name */
    private Rect f17364n;
    private qc o;
    private qi p;
    private c r;

    /* renamed from: g  reason: collision with root package name */
    private double f17357g = 0.0d;

    /* renamed from: h  reason: collision with root package name */
    private double f17358h = 1.0d;

    /* renamed from: i  reason: collision with root package name */
    private double f17359i = 0.0d;

    /* renamed from: j  reason: collision with root package name */
    private double f17360j = 1.0d;

    /* renamed from: k  reason: collision with root package name */
    private double f17361k = 0.0d;

    /* renamed from: l  reason: collision with root package name */
    private double f17362l = 1.0d;
    private boolean s = false;
    private int a = 0;
    private b b = new b();

    /* renamed from: c  reason: collision with root package name */
    private Rect f17354c = new Rect();

    /* renamed from: m  reason: collision with root package name */
    private GeoPoint f17363m = new GeoPoint();
    private o5 q = new o5();

    /* loaded from: classes9.dex */
    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            y5.values();
            int[] iArr = new int[3];
            a = iArr;
            try {
                y5 y5Var = y5.SCALE_LEVEL_CHANGED;
                iArr[2] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class b implements Cloneable {

        /* renamed from: g  reason: collision with root package name */
        public static final int f17365g = 20;

        /* renamed from: h  reason: collision with root package name */
        public static final int f17366h = 20;

        /* renamed from: i  reason: collision with root package name */
        public static final int f17367i = 20;

        /* renamed from: j  reason: collision with root package name */
        public static final int f17368j = 19;

        /* renamed from: k  reason: collision with root package name */
        public static final int f17369k = 22;

        /* renamed from: l  reason: collision with root package name */
        public static final int f17370l = 16;

        /* renamed from: m  reason: collision with root package name */
        public static final int f17371m = 3;

        /* renamed from: n  reason: collision with root package name */
        public static final float f17372n = 1.6f;
        public static final float o = 0.8f;
        public static final float p = 4.0f;
        public static final float q = 3.0517578E-5f;
        public static final int r = 20;
        public static final int s = 1;
        public static final float t = 1.9073486E-6f;

        /* renamed from: e  reason: collision with root package name */
        private float f17374e;

        /* renamed from: f  reason: collision with root package name */
        private int f17375f;
        private float b = 4.0f;
        private float a = 3.0517578E-5f;
        private int d = 20;

        /* renamed from: c  reason: collision with root package name */
        private int f17373c = 3;

        public float a() {
            return this.f17374e / a(this.f17375f);
        }

        public float a(int i2) {
            return (1 << (i2 - 1)) * 1.9073486E-6f;
        }

        public void a(float f2) {
            b bVar = new b();
            this.f17373c = bVar.g();
            this.a = f2 / bVar.a();
        }

        public void a(int i2, float f2) {
            this.f17374e = f2;
            this.f17375f = i2;
        }

        public int b() {
            return this.d;
        }

        public void b(float f2) {
            this.f17374e = f2;
        }

        public void b(int i2) {
            this.d = i2;
        }

        public void b(b bVar) {
            this.a = bVar.a;
            this.b = bVar.b;
            this.f17373c = bVar.f17373c;
            this.d = bVar.d;
            this.f17374e = bVar.f17374e;
            this.f17375f = bVar.f17375f;
        }

        public int c() {
            return 20;
        }

        public void c(int i2) {
            this.f17373c = i2;
        }

        public Object clone() {
            return super.clone();
        }

        public float d() {
            return this.a;
        }

        public int e() {
            return this.f17373c;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                return this.f17374e == bVar.f17374e && this.f17375f == bVar.f17375f;
            }
            return false;
        }

        public float f() {
            return this.f17374e;
        }

        public int g() {
            return this.f17375f;
        }

        public int hashCode() {
            return toString().hashCode();
        }

        public String toString() {
            return "scale:" + this.f17374e + ", scaleLevel:" + this.f17375f;
        }
    }

    /* loaded from: classes9.dex */
    public static class c {
        private float a;
        private float b;

        public c(float f2, float f3) {
            this.a = 0.0f;
            this.b = 0.0f;
            this.a = f2;
            this.b = f3;
        }

        public float a() {
            return this.a;
        }

        public void a(float f2, float f3) {
            this.a = f2;
            this.b = f3;
        }

        public float b() {
            return this.b;
        }
    }

    public v(qc qcVar) {
        this.o = qcVar;
        this.p = qcVar.f();
    }

    private void a(float f2) {
        MapParamConstants.MAX_SKEW_ANGLE = this.s ? c(f2) : 40.0f;
    }

    private void b(float f2) {
        float d = d(f2);
        if (d >= x()) {
            return;
        }
        float c2 = c(d);
        if (s() <= c2) {
            return;
        }
        i(c2);
    }

    private float c(float f2) {
        float f3;
        float f4 = 40.0f;
        if (f2 < 16.0f) {
            return 40.0f;
        }
        if (f2 >= 16.0f && f2 < 17.0f) {
            f3 = (f2 - 16.0f) * 10.0f;
        } else if (f2 >= 17.0f && f2 < 18.0f) {
            f3 = (f2 - 17.0f) * 10.0f;
            f4 = 50.0f;
        } else if (f2 < 18.0f || f2 >= 19.0f) {
            return 75.0f;
        } else {
            f3 = (f2 - 18.0f) * 15.0f;
            f4 = 60.0f;
        }
        return f3 + f4;
    }

    private float d(float f2) {
        return ((float) (Math.log(f2) / Math.log(2.0d))) + 20.0f;
    }

    private void g(int i2) {
        double d = (1 << i2) * 256;
        this.d = (int) d;
        Double.isNaN(d);
        this.f17355e = d / 360.0d;
        Double.isNaN(d);
        this.f17356f = d / 6.283185307179586d;
    }

    public float a(int i2) {
        return this.b.a(i2);
    }

    public GeoPoint a() {
        return this.f17363m;
    }

    public void a(double d, double d2) {
        this.q.e(d, d2);
    }

    public void a(Rect rect) {
        this.f17354c.set(rect);
    }

    public void a(Rect rect, int i2, int i3, int i4) {
        this.f17364n = rect;
        this.f17354c = GeometryConstants.BOUNDARY_WORLD;
        f(i4);
        c(0);
        a(i2, i3, false);
    }

    public void a(v vVar) {
        this.a = vVar.a;
        this.b.b(vVar.b);
        this.f17354c.set(vVar.f17354c);
        this.d = vVar.d;
        this.f17355e = vVar.f17355e;
        this.f17356f = vVar.f17356f;
        this.f17357g = vVar.f17357g;
        this.f17358h = vVar.f17358h;
        this.f17359i = vVar.f17359i;
        this.f17360j = vVar.f17360j;
        this.f17361k = vVar.f17361k;
        this.f17362l = vVar.f17362l;
        this.f17363m.setGeoPoint(vVar.f17363m);
        o5 o5Var = this.q;
        o5 o5Var2 = vVar.q;
        o5Var.e(o5Var2.a, o5Var2.b);
        this.f17364n = vVar.f17364n;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public boolean a(float f2, float f3, boolean z) {
        c cVar = this.r;
        if (cVar == null) {
            this.r = new c(f2, f3);
        } else {
            cVar.a(f2, f3);
        }
        this.o.a(f2, f3, z);
        return true;
    }

    public boolean a(int i2, int i3) {
        return a(i2, i3, false);
    }

    public boolean a(int i2, int i3, boolean z) {
        int i4;
        int i5;
        boolean z2 = true;
        int q = (1 << (20 - q())) < 0 ? 0 : 20 - q();
        if (131072 > q) {
            i4 = ((this.f17364n.width() * 131072) - (this.f17364n.width() * q)) / 2;
            i5 = ((this.f17364n.height() * 131072) - (this.f17364n.height() * q)) / 2;
        } else {
            i4 = 0;
            i5 = 0;
        }
        Rect rect = this.f17354c;
        int i6 = rect.left - i4;
        int i7 = rect.right + i4;
        int i8 = rect.top - i5;
        int i9 = rect.bottom + i5;
        if (i2 < i8) {
            i2 = i8;
        }
        if (i2 <= i9) {
            i9 = i2;
        }
        if (i3 < i6) {
            i3 = i6;
        }
        if (i3 <= i7) {
            i7 = i3;
        }
        if (i9 == this.f17363m.getLatitudeE6() && i7 == this.f17363m.getLongitudeE6()) {
            z2 = false;
        }
        this.f17363m.setLatitudeE6(i9);
        this.f17363m.setLongitudeE6(i7);
        o5 a2 = y.a(this, this.f17363m);
        a(a2.a, a2.b);
        this.p.a(this.f17363m, z);
        return z2;
    }

    public boolean a(GeoPoint geoPoint) {
        return a(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6(), true);
    }

    public o5 b() {
        return this.q;
    }

    public boolean b(int i2) {
        return i2 == 8 || i2 == 13 || i2 == 10;
    }

    public boolean b(int i2, int i3) {
        int i4;
        int q = 1 << (20 - q());
        int i5 = 0;
        if (131072 > q) {
            i5 = ((this.f17364n.width() * 131072) - (this.f17364n.width() * q)) / 2;
            i4 = ((this.f17364n.height() * 131072) - (this.f17364n.height() * q)) / 2;
        } else {
            i4 = 0;
        }
        Rect rect = this.f17354c;
        int i6 = rect.left - i5;
        int i7 = rect.right + i5;
        int i8 = rect.top - i4;
        int i9 = rect.bottom + i4;
        if (i2 < i8) {
            i2 = i8;
        }
        if (i2 <= i9) {
            i9 = i2;
        }
        if (i3 < i6) {
            i3 = i6;
        }
        if (i3 <= i7) {
            i7 = i3;
        }
        this.p.d(new GeoPoint(i9, i7));
        return true;
    }

    public boolean b(GeoPoint geoPoint) {
        return b(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
    }

    public float c() {
        return this.b.a();
    }

    public boolean c(int i2) {
        int B;
        qi qiVar = this.p;
        if (qiVar == null || (B = qiVar.B()) == i2) {
            return false;
        }
        if (B == 11) {
            this.o.r(false);
        }
        if (i2 == 11) {
            this.o.r(true);
        }
        this.a = i2;
        this.p.b(i2, false);
        this.p.h(b(i2));
        ma.a(la.f16819f, "setMapStyle : styleId[" + i2 + "]");
        if (this.o.o0()) {
            this.o.A0();
        }
        return true;
    }

    public Object clone() {
        v vVar = (v) super.clone();
        vVar.f17354c = new Rect(this.f17354c);
        vVar.b = (b) this.b.clone();
        vVar.f17363m = new GeoPoint(this.f17363m);
        o5 o5Var = this.q;
        vVar.q = new o5(o5Var.a, o5Var.b);
        return vVar;
    }

    public double d() {
        return this.f17362l;
    }

    public void d(int i2) {
        this.b.b(i2);
    }

    public double e() {
        return this.f17361k;
    }

    public void e(float f2) {
        this.b.a(f2);
    }

    public void e(int i2) {
        this.b.c(i2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof v) {
            v vVar = (v) obj;
            return vVar.f17363m.equals(this.f17363m) && vVar.b.equals(this.b) && vVar.a == this.a;
        }
        return false;
    }

    public float f(float f2) {
        if (this.p.C() == f2) {
            return f2;
        }
        float f3 = f2 % 360.0f;
        double radians = Math.toRadians(f2);
        this.f17357g = Math.sin(radians);
        this.f17358h = Math.cos(radians);
        qi qiVar = this.p;
        if (qiVar != null) {
            qiVar.b(f3);
        }
        return f3;
    }

    public int f() {
        qi qiVar = this.p;
        return qiVar == null ? this.a : qiVar.B();
    }

    public boolean f(int i2) {
        return g(this.b.a(i2)) == y5.SCALE_LEVEL_CHANGED;
    }

    public int g() {
        return this.b.b();
    }

    public y5 g(float f2) {
        float f3;
        int i2;
        y5 y5Var = y5.NO_CHANGED;
        float f4 = this.b.f();
        int g2 = this.b.g();
        qi qiVar = this.p;
        if (qiVar != null) {
            qiVar.a(f2, false);
            f3 = this.p.D();
            i2 = this.p.E();
        } else {
            f3 = f4;
            i2 = g2;
        }
        this.b.a(i2, f3);
        if (i2 != g2) {
            y5Var = y5.SCALE_LEVEL_CHANGED;
        } else if (f3 != f4) {
            y5Var = y5.SCALE_CHANGED;
        }
        if (y5Var.ordinal() == 2) {
            g(this.b.g());
        }
        o5 a2 = y.a(this, a());
        this.q.e(a2.a, a2.b);
        return y5Var;
    }

    public int h() {
        return this.b.c();
    }

    public y5 h(float f2) {
        qi qiVar = this.p;
        if (qiVar != null) {
            qiVar.a(f2);
        }
        this.b.b(f2);
        return y5.SCALE_LEVEL_CHANGED;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public float i() {
        return this.b.d();
    }

    public float i(float f2) {
        if (this.p.F() == f2) {
            return f2;
        }
        a(x());
        float max = Math.max(0.0f, Math.min(MapParamConstants.MAX_SKEW_ANGLE, f2));
        double radians = Math.toRadians(f2);
        this.f17359i = Math.sin(radians);
        this.f17360j = Math.cos(radians);
        double d = 1.5707963267948966d - radians;
        this.f17362l = Math.cos(d);
        this.f17361k = Math.sin(d);
        qi qiVar = this.p;
        if (qiVar != null) {
            qiVar.c(max);
        }
        return max;
    }

    public int j() {
        return this.b.e();
    }

    public double k() {
        return this.f17356f;
    }

    public double l() {
        return this.f17355e;
    }

    public float m() {
        return this.p.C();
    }

    public double n() {
        return this.f17358h;
    }

    public double o() {
        return this.f17357g;
    }

    public float p() {
        return this.b.f();
    }

    public int q() {
        return this.b.g();
    }

    public Rect r() {
        return this.f17364n;
    }

    public float s() {
        return this.p.F();
    }

    public double t() {
        return this.f17360j;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        GeoPoint a2 = a();
        sb.append("mapParam: ");
        sb.append("center:" + a2.toString() + LangUtils.SINGLE_SPACE);
        sb.append("mode:" + this.a + LangUtils.SINGLE_SPACE);
        sb.append("mapScale:" + this.b.toString() + LangUtils.SINGLE_SPACE);
        StringBuilder sb2 = new StringBuilder();
        sb2.append("screenRect:");
        Rect rect = this.f17364n;
        sb2.append(rect != null ? rect.toString() : DYConstants.DY_NULL_STR);
        sb2.append(LangUtils.SINGLE_SPACE);
        sb.append(sb2.toString());
        return sb.toString();
    }

    public double u() {
        return this.f17359i;
    }

    public c v() {
        return this.r;
    }

    public int w() {
        return this.d;
    }

    public float x() {
        return d(this.b.f17374e);
    }

    public byte[] y() {
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void z() {
        d0 h2;
        y5 y5Var;
        if (this.o == null) {
            return;
        }
        this.f17363m = this.p.n();
        int E = this.p.E();
        float D = this.p.D();
        if (E == q()) {
            if (D != p()) {
                h2 = this.o.h();
                y5Var = y5.SCALE_CHANGED;
            }
            if (this.b != null) {
                b(D);
                this.b.a(E, D);
            }
            this.a = this.p.B();
        }
        h2 = this.o.h();
        y5Var = y5.SCALE_LEVEL_CHANGED;
        h2.a(y5Var);
        if (this.b != null) {
        }
        this.a = this.p.B();
    }
}
