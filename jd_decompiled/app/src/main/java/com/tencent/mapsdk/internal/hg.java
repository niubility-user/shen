package com.tencent.mapsdk.internal;

import android.graphics.Bitmap;
import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable;

/* loaded from: classes9.dex */
public class hg extends ye {
    private pc B;
    private e1 C;
    public ig D;
    public te E;
    public Selectable.OnSelectedListener F;
    public o5 G;
    private o7 H;
    private o7 I;
    private final p0 J;

    public hg(p0 p0Var, a1 a1Var, ig igVar) {
        super(a1Var);
        this.G = new o5();
        this.H = null;
        this.J = p0Var;
        this.B = a1Var.w();
        this.C = a1Var.getMapContext();
        this.D = igVar;
        this.E = new te(igVar);
        this.f17514n = true;
        a(igVar);
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        if (this.B == null) {
            return;
        }
        if (!isVisible()) {
            te teVar = this.E;
            if (teVar != null) {
                teVar.w = -1;
                return;
            }
            return;
        }
        o7 o7Var = this.H;
        if (o7Var != null) {
            if (o7Var.d()) {
                this.H = null;
            } else {
                this.H.a();
            }
        }
        o7 o7Var2 = this.I;
        if (o7Var2 != null && !o7Var2.d()) {
            this.I.a();
        }
        te teVar2 = this.E;
        if (teVar2 != null) {
            this.B.a(teVar2);
            int i2 = this.E.w;
            if (i2 != 0) {
                a(i2);
            }
        }
    }

    public void J() {
        te teVar = this.E;
        if (teVar != null) {
            teVar.d(true);
        }
    }

    public float K() {
        return this.D.c();
    }

    public float L() {
        return this.D.d();
    }

    public float M() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.g();
        }
        return 0.0f;
    }

    public int N() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.w;
        }
        return -1;
    }

    public te O() {
        return this.E;
    }

    public ig P() {
        return this.D;
    }

    public float Q() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.l();
        }
        return 0.0f;
    }

    public GeoPoint R() {
        return this.D.k();
    }

    public float S() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.p();
        }
        return 0.0f;
    }

    public int T() {
        return this.D.l();
    }

    public float U() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.r();
        }
        return 1.0f;
    }

    public float V() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.s();
        }
        return 1.0f;
    }

    public float W() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.t();
        }
        return 0.0f;
    }

    public boolean X() {
        ig igVar = this.D;
        if (igVar != null) {
            return igVar.p();
        }
        return true;
    }

    public boolean Y() {
        te teVar = this.E;
        if (teVar != null) {
            return teVar.A();
        }
        return false;
    }

    public boolean Z() {
        ig igVar = this.D;
        if (igVar != null) {
            return igVar.s();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        ig igVar;
        if (this.E == null || (igVar = this.D) == null || igVar.k() == null) {
            return null;
        }
        if (Y()) {
            GeoPoint R = R();
            o5 o5Var = new o5(0.0d, 0.0d);
            if (R != null) {
                o5Var.a = R.getLongitudeE6();
                o5Var.b = R.getLatitudeE6();
            }
            this.G = o5Var;
        } else {
            this.G = s4Var.a(R());
        }
        o5 o5Var2 = new o5();
        o5 o5Var3 = new o5();
        te teVar = this.E;
        Bitmap a = teVar.a(teVar.c());
        if (a == null) {
            return null;
        }
        int width = a.getWidth();
        int height = a.getHeight();
        o5 o5Var4 = this.G;
        o5Var2.a = o5Var4.a;
        double d = o5Var4.a;
        double d2 = width;
        Double.isNaN(d2);
        o5Var3.a = d + d2;
        o5Var2.b = o5Var4.b;
        double d3 = o5Var4.b;
        double d4 = height;
        Double.isNaN(d4);
        o5Var3.b = d3 + d4;
        int c2 = (int) (this.D.c() * width);
        int d5 = (int) (this.D.d() * height);
        double d6 = o5Var2.a;
        double d7 = c2;
        Double.isNaN(d7);
        o5Var2.a = d6 - d7;
        double d8 = o5Var3.a;
        Double.isNaN(d7);
        o5Var3.a = d8 - d7;
        double d9 = o5Var2.b;
        double d10 = d5;
        Double.isNaN(d10);
        o5Var2.b = d9 - d10;
        double d11 = o5Var3.b;
        Double.isNaN(d10);
        o5Var3.b = d11 - d10;
        int i2 = this.D.i();
        int j2 = this.D.j();
        double d12 = o5Var2.a;
        double d13 = i2;
        Double.isNaN(d13);
        o5Var2.a = d12 + d13;
        double d14 = o5Var3.a;
        Double.isNaN(d13);
        o5Var3.a = d14 + d13;
        double d15 = o5Var2.b;
        double d16 = j2;
        Double.isNaN(d16);
        o5Var2.b = d15 + d16;
        double d17 = o5Var3.b;
        Double.isNaN(d16);
        o5Var3.b = d17 + d16;
        GeoPoint a2 = s4Var.a(o5Var2);
        GeoPoint a3 = s4Var.a(o5Var3);
        return new Rect(a2.getLongitudeE6(), a2.getLatitudeE6(), a3.getLongitudeE6(), a3.getLatitudeE6());
    }

    public void a(GeoPoint geoPoint) {
        this.D = this.D.a(geoPoint);
        te teVar = this.E;
        if (teVar != null) {
            teVar.a(geoPoint);
            this.E.d(true);
        }
    }

    public void a(ig igVar) {
        if (igVar == null) {
            return;
        }
        this.D = igVar;
        te teVar = this.E;
        if (teVar == null) {
            this.E = new te(igVar);
        } else {
            teVar.a(igVar);
        }
    }

    public void a(o7 o7Var) {
        this.I = o7Var;
    }

    public void a(String str, Bitmap... bitmapArr) {
        b(str, bitmapArr);
    }

    public void a0() {
        te teVar = this.E;
        if (teVar != null) {
            teVar.w = 0;
        }
        o7 o7Var = this.I;
        if (o7Var != null) {
            o7Var.i();
        }
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        ig igVar;
        int i2;
        if (this.E == null || (igVar = this.D) == null || igVar.k() == null) {
            return null;
        }
        if (Y()) {
            GeoPoint R = R();
            o5 o5Var = new o5(0.0d, 0.0d);
            if (R != null) {
                o5Var.a = R.getLongitudeE6();
                o5Var.b = R.getLatitudeE6();
            }
            this.G = o5Var;
        } else {
            this.G = s4Var.a(R());
        }
        o5 o5Var2 = new o5();
        o5 o5Var3 = new o5();
        te teVar = this.E;
        Bitmap a = teVar.a(teVar.c());
        int i3 = 0;
        if (a != null) {
            i3 = a.getWidth();
            i2 = a.getHeight();
        } else {
            i2 = 0;
        }
        o5 o5Var4 = this.G;
        o5Var2.a = o5Var4.a;
        double d = o5Var4.a;
        double d2 = i3;
        Double.isNaN(d2);
        o5Var3.a = d + d2;
        o5Var2.b = o5Var4.b;
        double d3 = o5Var4.b;
        double d4 = i2;
        Double.isNaN(d4);
        o5Var3.b = d3 + d4;
        int c2 = (int) (this.D.c() * i3);
        int d5 = (int) (this.D.d() * i2);
        double d6 = o5Var2.a;
        double d7 = c2;
        Double.isNaN(d7);
        o5Var2.a = d6 - d7;
        double d8 = o5Var3.a;
        Double.isNaN(d7);
        o5Var3.a = d8 - d7;
        double d9 = o5Var2.b;
        double d10 = d5;
        Double.isNaN(d10);
        o5Var2.b = d9 - d10;
        double d11 = o5Var3.b;
        Double.isNaN(d10);
        o5Var3.b = d11 - d10;
        int i4 = this.D.i();
        int j2 = this.D.j();
        double d12 = o5Var2.a;
        double d13 = i4;
        Double.isNaN(d13);
        o5Var2.a = d12 + d13;
        double d14 = o5Var3.a;
        Double.isNaN(d13);
        o5Var3.a = d14 + d13;
        double d15 = o5Var2.b;
        double d16 = j2;
        Double.isNaN(d16);
        o5Var2.b = d15 + d16;
        double d17 = o5Var3.b;
        Double.isNaN(d16);
        o5Var3.b = d17 + d16;
        return new Rect((int) o5Var2.a, (int) o5Var2.b, (int) o5Var3.a, (int) o5Var3.b);
    }

    public void b(int i2) {
        this.D = this.D.c(i2);
        te teVar = this.E;
        if (teVar != null) {
            teVar.e(i2);
            this.E.d(true);
        }
    }

    public void b(int i2, int i3) {
        this.D.a(i2, i3);
        te teVar = this.E;
        if (teVar != null) {
            teVar.b(i2, i3);
            this.E.d(true);
        }
    }

    public void b(o7 o7Var) {
        if (o7Var == null) {
            return;
        }
        this.H = o7Var;
        o7Var.h();
    }

    public void b(String str, Bitmap... bitmapArr) {
        this.D = this.D.a(str, bitmapArr);
        te teVar = this.E;
        if (teVar != null) {
            teVar.a(str, bitmapArr);
            this.E.d(true);
        }
    }

    public boolean b0() {
        ig igVar = this.D;
        if (igVar != null) {
            igVar.t();
            return false;
        }
        return false;
    }

    public void f(boolean z) {
        ig igVar = this.D;
        if (igVar != null) {
            igVar.a(z);
        }
        te teVar = this.E;
        if (teVar != null) {
            teVar.a(z);
            this.E.d(true);
        }
    }

    public void g(boolean z) {
        ig igVar = this.D;
        if (igVar != null) {
            igVar.b(z);
        }
        te teVar = this.E;
        if (teVar != null) {
            teVar.b(z);
            this.E.d(true);
        }
    }

    public float getAlpha() {
        return this.D.a();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getZIndex() {
        return this.D.m();
    }

    public void h(boolean z) {
        te teVar = this.E;
        if (teVar != null) {
            teVar.f(z);
            this.E.d(true);
        }
    }

    public void i(boolean z) {
        this.D.g(z);
    }

    public boolean isFastLoad() {
        te teVar = this.E;
        if (teVar == null) {
            return false;
        }
        return teVar.z();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public boolean isSelected() {
        return this.E.c() == 1;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        e1 e1Var;
        Selectable.OnSelectedListener onSelectedListener;
        if (!isVisible() || this.D.k() == null || (e1Var = this.C) == null) {
            return false;
        }
        TappedElement a = e1Var.f().a(f2, f3);
        boolean z = a != null && a.itemId == ((long) N());
        if (z) {
            this.E.b(1);
        } else {
            this.E.b(0);
        }
        if (z && (onSelectedListener = this.F) != null) {
            onSelectedListener.onSelected(this);
        }
        return z;
    }

    public void setAlpha(float f2) {
        this.D = this.D.a(f2);
        te teVar = this.E;
        if (teVar != null) {
            teVar.a(f2);
            this.E.d(true);
        }
    }

    public void setAnchor(float f2, float f3) {
        this.D = this.D.a(f2, f3);
        te teVar = this.E;
        if (teVar != null) {
            teVar.a(f2, f3);
            this.E.d(true);
        }
    }

    public void setFastLoad(boolean z) {
        te teVar = this.E;
        if (teVar == null) {
            return;
        }
        teVar.e(z);
        this.E.d(true);
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setLevel(int i2) {
        this.D = this.D.b(i2);
        te teVar = this.E;
        if (teVar != null) {
            teVar.d(i2);
            this.E.d(true);
        }
    }

    public void setScale(float f2, float f3) {
        te teVar = this.E;
        if (teVar != null) {
            teVar.b(f2, f3);
            this.E.d(true);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelected(boolean z) {
        te teVar;
        int i2;
        if (z) {
            teVar = this.E;
            i2 = 1;
        } else {
            teVar = this.E;
            i2 = 0;
        }
        teVar.b(i2);
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelectedListener(Selectable.OnSelectedListener onSelectedListener) {
        this.F = onSelectedListener;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(int i2) {
        this.D = this.D.d(i2);
        te teVar = this.E;
        if (teVar != null) {
            teVar.f(i2);
            this.E.d(true);
        }
    }

    @Override // com.tencent.mapsdk.internal.v0
    public p0 x() {
        return this.J;
    }
}
