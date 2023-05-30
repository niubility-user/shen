package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.RectF;
import androidx.annotation.NonNull;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.ue;

/* loaded from: classes9.dex */
public class te extends ue implements ue.a {
    public static final int c0 = 1;
    public static final int d0 = 16;
    public static final int e0 = 256;
    public static final int f0 = 4096;
    public static final int g0 = 65536;
    public double A;
    public double B;
    private RectF C;
    private int D;
    private int E;
    private int F;
    private int G;
    private int H;
    private float I;
    private float J;
    private boolean K;
    private float L;
    private float M;
    private float N;
    private float O;
    private float P;
    private int Q;
    private float R;
    private float S;
    private boolean T;
    private boolean U;
    private boolean V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private int a0;
    private int b0;
    public int w;
    private String x;
    private String y;
    public Bitmap[] z;

    public te(@NonNull ig igVar) {
        this(igVar.g(), igVar.k(), igVar.c(), igVar.d(), igVar.i(), igVar.j(), igVar.f());
    }

    public te(String str, GeoPoint geoPoint, float f2, float f3, int i2, int i3, Bitmap... bitmapArr) {
        this.H = 0;
        this.I = 0.5f;
        this.J = 0.5f;
        this.K = false;
        this.L = 0.0f;
        this.M = 0.0f;
        this.N = 0.0f;
        this.O = 0.0f;
        this.P = 1.0f;
        this.Q = 0;
        this.R = 1.0f;
        this.S = 1.0f;
        this.T = false;
        this.U = true;
        this.V = false;
        this.W = true;
        this.X = false;
        this.Y = false;
        this.Z = true;
        a(this);
        this.I = f2;
        this.J = f3;
        this.D = i2;
        this.E = i3;
        if (geoPoint != null) {
            double longitudeE6 = geoPoint.getLongitudeE6();
            Double.isNaN(longitudeE6);
            this.A = longitudeE6 / 1000000.0d;
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            this.B = latitudeE6 / 1000000.0d;
        }
        a(str, bitmapArr);
    }

    public te(String str, GeoPoint geoPoint, float f2, float f3, Bitmap... bitmapArr) {
        this(str, geoPoint, f2, f3, 0, 0, bitmapArr);
    }

    public te(String str, GeoPoint geoPoint, Bitmap... bitmapArr) {
        this(str, geoPoint, 0.5f, 0.5f, 0, 0, bitmapArr);
    }

    private Bitmap c(int i2) {
        Bitmap[] bitmapArr = this.z;
        if (bitmapArr == null) {
            return null;
        }
        return (i2 < 0 || i2 >= bitmapArr.length) ? bitmapArr[0] : bitmapArr[i2];
    }

    public boolean A() {
        return this.T;
    }

    public boolean B() {
        return this.U;
    }

    public boolean C() {
        return this.V;
    }

    @Override // com.tencent.mapsdk.internal.ue.a
    public synchronized Bitmap a(int i2) {
        return c(c());
    }

    public void a(float f2) {
        this.P = f2;
        d(true);
    }

    public void a(float f2, float f3) {
        this.I = f2;
        this.J = f3;
        a(this.F, this.G);
        d(true);
    }

    public void a(int i2, int i3) {
        if (this.F == i2 && this.G == i3) {
            return;
        }
        this.F = i2;
        this.G = i3;
        float f2 = this.D / i2;
        float f3 = this.E / i3;
        this.C = new RectF(f2, -f3, 0.0f, -0.0f);
        float f4 = this.I - f2;
        this.I = f4;
        float f5 = this.J - f3;
        this.J = f5;
        int i4 = this.F;
        float f6 = (-i4) * f4;
        this.L = f6;
        this.M = i4 + f6;
        float f7 = this.G;
        float f8 = f5 * f7;
        this.N = f8;
        this.O = f8 - f7;
    }

    public void a(GeoPoint geoPoint) {
        if (geoPoint != null) {
            double d = this.T ? 1.0d : 1000000.0d;
            double longitudeE6 = geoPoint.getLongitudeE6();
            Double.isNaN(longitudeE6);
            this.A = longitudeE6 / d;
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            this.B = latitudeE6 / d;
            d(true);
        }
    }

    public void a(ig igVar) {
        a(igVar.a());
        a(igVar.c(), igVar.d());
        e(igVar.l());
        f(igVar.r());
        e(igVar.q());
        b(igVar.o());
        a(igVar.n());
        c(igVar.p());
        g(igVar.s());
        d(igVar.h());
        f(igVar.m());
    }

    public final synchronized void a(String str, Bitmap... bitmapArr) {
        if (bitmapArr == null) {
            return;
        }
        d(true);
        h(true);
        this.x = str;
        this.z = bitmapArr;
        int i2 = this.H;
        if (i2 < 0 || i2 >= bitmapArr.length) {
            this.H = 0;
        }
        int i3 = this.H;
        if (bitmapArr[i3] != null) {
            a(bitmapArr[i3].getWidth(), bitmapArr[this.H].getHeight());
        }
    }

    public void a(boolean z) {
        this.Y = z;
        ma.a(la.f16819f, "setAvoidMarker = " + z);
        d(true);
    }

    @Override // com.tencent.mapsdk.internal.ue.a
    public boolean a() {
        return true;
    }

    @Override // com.tencent.mapsdk.internal.ue.a
    public String b() {
        return this.x;
    }

    public void b(float f2, float f3) {
        this.R = f2;
        this.S = f3;
        d(true);
    }

    @Override // com.tencent.mapsdk.internal.ue
    public synchronized void b(int i2) {
        this.H = i2;
        d(true);
        h(true);
        Bitmap c2 = c(i2);
        if (c2 != null) {
            int width = c2.getWidth();
            int height = c2.getHeight();
            if (this.F != width || this.G != height) {
                a(width, height);
            }
        }
        super.b(i2);
    }

    public void b(int i2, int i3) {
        this.D = i2;
        this.E = i3;
        d(true);
    }

    public void b(boolean z) {
        this.X = z;
        ma.a(la.f16819f, "setAvoidPoi = " + z);
        d(true);
    }

    public void c(boolean z) {
        this.Z = z;
    }

    public float d() {
        return this.P;
    }

    public void d(int i2) {
        this.a0 = i2;
    }

    public void d(boolean z) {
        this.K = z;
    }

    public float e() {
        return this.I;
    }

    public void e(int i2) {
        this.Q = i2;
        d(true);
    }

    public void e(boolean z) {
        this.W = z;
    }

    public boolean equals(Object obj) {
        return (obj instanceof te) && this.w == ((te) obj).w;
    }

    public float f() {
        return this.J;
    }

    public void f(int i2) {
        this.b0 = i2;
    }

    public void f(boolean z) {
        this.T = z;
    }

    public float g() {
        return this.O;
    }

    public void g(boolean z) {
        this.U = z;
        d(true);
    }

    public RectF h() {
        return new RectF(this.C);
    }

    public void h(boolean z) {
        this.V = z;
        if (z) {
            return;
        }
        this.y = this.x;
    }

    public int hashCode() {
        return String.valueOf(this.w).hashCode() + R2.attr.calendarTextColor;
    }

    public int i() {
        return this.w;
    }

    public String j() {
        return this.x;
    }

    public String k() {
        return this.y;
    }

    public float l() {
        return this.L;
    }

    public int m() {
        return this.a0;
    }

    public double n() {
        return this.A;
    }

    public double o() {
        return this.B;
    }

    public float p() {
        return this.M;
    }

    public float q() {
        return this.Z ? 360 - this.Q : this.Q;
    }

    public float r() {
        return this.R;
    }

    public float s() {
        return this.S;
    }

    public float t() {
        return this.N;
    }

    public int u() {
        return this.b0;
    }

    public boolean v() {
        return this.Y;
    }

    public boolean w() {
        return this.X;
    }

    public boolean x() {
        return this.Z;
    }

    public boolean y() {
        return this.K;
    }

    public boolean z() {
        return this.W;
    }
}
