package com.tencent.mapsdk.internal;

/* loaded from: classes9.dex */
public class e0 {
    private l0 a;

    public e0(l0 l0Var) {
        this.a = null;
        this.a = l0Var;
    }

    public float a(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.c(i2);
        }
        return 0.0f;
    }

    public void a() {
        if (this.a != null) {
            this.a = null;
        }
    }

    public void a(float f2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setLogoScale(f2);
        }
    }

    public void a(int i2, float f2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.a(i2, f2);
        }
    }

    public void a(int i2, int i3) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setCompassExtraPadding(i2, i3);
        }
    }

    public void a(int i2, int i3, int i4, int i5, int i6) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.a(i2, i3, i4, i5, i6);
        }
    }

    public void a(int i2, int[] iArr) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.a(i2, iArr);
        }
    }

    public void a(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setAllGesturesEnabled(z);
        }
    }

    @Deprecated
    public void b(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setCompassExtraPadding(i2);
        }
    }

    public void b(int i2, int i3, int i4, int i5, int i6) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.b(i2, i3, i4, i5, i6);
        }
    }

    public void b(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setCompassEnabled(z);
        }
    }

    public boolean b() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isCompassEnabled();
        }
        return false;
    }

    public void c(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.f(i2);
        }
    }

    public void c(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.b(z);
        }
    }

    public boolean c() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isIndoorLevelPickerEnabled();
        }
        return false;
    }

    public void d(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.b(i2);
        }
    }

    public void d(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setGestureRotateByMapCenter(z);
        }
    }

    public boolean d() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isMyLocationButtonEnabled();
        }
        return false;
    }

    public void e(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.e(i2);
        }
    }

    public void e(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setGestureScaleByMapCenter(z);
        }
    }

    public boolean e() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isRotateGesturesEnabled();
        }
        return false;
    }

    public void f(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setLogoSize(i2);
        }
    }

    public void f(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setIndoorLevelPickerEnabled(z);
        }
    }

    public boolean f() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.b();
        }
        return false;
    }

    public void g(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.d(i2);
        }
    }

    public void g(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.a(z);
        }
    }

    public boolean g() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isScrollGesturesEnabled();
        }
        return false;
    }

    public void h(int i2) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setZoomPosition(i2);
        }
    }

    public void h(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setMyLocationButtonEnabled(z);
        }
    }

    public boolean h() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isTiltGesturesEnabled();
        }
        return false;
    }

    public void i(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setRotateGesturesEnabled(z);
        }
    }

    public boolean i() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isZoomControlsEnabled();
        }
        return false;
    }

    public void j(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setScaleViewEnabled(z);
        }
    }

    public boolean j() {
        l0 l0Var = this.a;
        if (l0Var != null) {
            return l0Var.isZoomGesturesEnabled();
        }
        return false;
    }

    public void k(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setScaleViewFadeEnable(z);
        }
    }

    public void l(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setScrollGesturesEnabled(z);
        }
    }

    public void m(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setTiltGesturesEnabled(z);
        }
    }

    public void n(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setZoomControlsEnabled(z);
        }
    }

    public void o(boolean z) {
        l0 l0Var = this.a;
        if (l0Var != null) {
            l0Var.setZoomGesturesEnabled(z);
        }
    }
}
