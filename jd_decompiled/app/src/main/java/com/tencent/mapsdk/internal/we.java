package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import android.util.Pair;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.tools.IndexCallback;
import com.tencent.tencentmap.mapsdk.maps.model.ArcOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class we extends ye<g0> implements g0 {
    private final qc B;
    public r0 C;
    private LatLng D;
    private LatLng E;
    private LatLng F;
    private float G;
    private LatLng H;
    private double I;
    private double J;
    private int K;
    private float L;
    private boolean M;

    /* loaded from: classes9.dex */
    public class a implements IndexCallback<Pair<Double, Double>> {
        public final /* synthetic */ LatLng[] a;

        public a(LatLng[] latLngArr) {
            this.a = latLngArr;
        }

        @Override // com.tencent.map.tools.IndexCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(int i2, Pair<Double, Double> pair) {
            this.a[i2] = we.this.B.getProjection().a(new w5(((Double) pair.first).doubleValue(), ((Double) pair.second).doubleValue()));
        }
    }

    public we(ArcOptions arcOptions, a1 a1Var) {
        super(a1Var);
        this.K = -16776961;
        this.L = 10.0f;
        this.B = a1Var.A();
        a(arcOptions);
    }

    private void K() {
        double a2;
        boolean z;
        LatLng latLng = this.D;
        LatLng latLng2 = this.F;
        LatLng latLng3 = this.E;
        float f2 = this.G;
        w5 a3 = this.B.getProjection().a(latLng);
        w5 a4 = this.B.getProjection().a(latLng3);
        w5 w5Var = new w5(0.0d, 0.0d);
        if (f2 == 0.0f) {
            w5 a5 = this.B.getProjection().a(latLng2);
            a2 = wa.a(a3, a5, a4, w5Var);
            z = wa.a(a3.x(), a3.y(), a4.x(), a4.y(), a5.x(), a5.y()) > 0.0d;
        } else {
            boolean z2 = f2 < 180.0f;
            if (f2 > 180.0f) {
                f2 = 360.0f - f2;
            }
            a2 = wa.a(a3, a4, f2 * 2.0f, z2, w5Var);
            z = z2;
        }
        this.H = this.B.getProjection().a(w5Var);
        this.I = wa.b(a3, a4, w5Var);
        this.J = wa.a(a3, w5Var);
        LatLng[] latLngArr = new LatLng[R2.attr.additionBottom];
        wa.a(w5Var, a2, a3, a4, z, new a(latLngArr));
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.D);
        for (int i2 = 0; i2 < 360; i2++) {
            LatLng latLng4 = latLngArr[i2];
            if (latLng4 != null) {
                arrayList.add(latLng4);
            }
        }
        arrayList.add(this.E);
        r0 r0Var = this.C;
        if (r0Var != null) {
            r0Var.remove();
        }
        Polyline a6 = this.B.a(new PolylineOptions().addAll(arrayList).color(this.K).width(this.L).borderWidth(getStrokeWidth()).borderColor(getStrokeColor()).zIndex(getZIndex()).level(getLevel()).arrow(this.M).lineCap(true));
        if (a6 instanceof y0) {
            this.C = ((y0) a6).x();
        }
        this.B.w0();
    }

    private void f(boolean z) {
        this.M = z;
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        if (this.B == null || this.C == null) {
            return;
        }
        if (!isVisible()) {
            this.C.remove();
        } else if (C()) {
            K();
            r0 r0Var = this.C;
            if (r0Var instanceof p4) {
                p4 p4Var = (p4) r0Var;
                if (a() == -1) {
                    a(p4Var.a());
                } else {
                    p4Var.n();
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        super.H();
        r0 r0Var = this.C;
        if (r0Var != null) {
            r0Var.remove();
            this.C = null;
        }
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: J  reason: merged with bridge method [inline-methods] */
    public g0 x() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        LatLng latLng = this.H;
        if (latLng != null) {
            w5 a2 = s4Var.a(latLng);
            w5 w5Var = new w5(a2.b() - this.J, a2.c() - this.J);
            w5 w5Var2 = new w5(a2.b() + this.J, a2.c() + this.J);
            LatLng a3 = s4Var.a(w5Var);
            LatLng a4 = s4Var.a(w5Var2);
            Rect rect = new Rect();
            rect.left = (int) (a3.longitude * 1000000.0d);
            rect.top = (int) (a3.latitude * 1000000.0d);
            rect.right = (int) (a4.longitude * 1000000.0d);
            rect.bottom = (int) (a4.latitude * 1000000.0d);
            return rect;
        }
        return super.getBound(s4Var);
    }

    public void a(float f2) {
        if (this.G != f2) {
            this.G = f2;
            B();
        }
    }

    @Override // com.tencent.mapsdk.internal.g0
    public void a(ArcOptions arcOptions) {
        if (this.B == null || arcOptions == null) {
            return;
        }
        LatLng startLatLng = arcOptions.getStartLatLng();
        LatLng passLatLng = arcOptions.getPassLatLng();
        LatLng endLatLng = arcOptions.getEndLatLng();
        float angle = arcOptions.getAngle() >= 0.0f ? arcOptions.getAngle() % 360.0f : (arcOptions.getAngle() % 360.0f) + 360.0f;
        if (startLatLng == null || endLatLng == null || startLatLng.equals(endLatLng)) {
            return;
        }
        if (passLatLng == null && (angle == 0.0f || angle == 180.0f)) {
            return;
        }
        if (passLatLng == null || !(passLatLng.equals(startLatLng) || passLatLng.equals(endLatLng))) {
            d(startLatLng);
            b(endLatLng);
            c(passLatLng);
            a(angle);
            setColor(arcOptions.getColor());
            setWidth(arcOptions.getWidth());
            setStrokeWidth(arcOptions.getStrokeWidth());
            setStrokeColor(arcOptions.getStrokeColor());
            f(arcOptions.isShowArrow());
            if (C()) {
                K();
            }
        }
    }

    public void b(LatLng latLng) {
        if (this.E != latLng) {
            this.E = latLng;
            B();
        }
    }

    public void c(LatLng latLng) {
        if (this.F != latLng) {
            this.F = latLng;
            B();
        }
    }

    public void d(LatLng latLng) {
        if (this.D != latLng) {
            this.D = latLng;
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
    public LatLng getCenter() {
        return this.H;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Colorable
    public int getColor() {
        return this.K;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
    public double getLength() {
        return this.I;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Arc
    public double getRadius() {
        return this.J;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Widthable
    public float getWidth() {
        return this.L;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Colorable
    public void setColor(int i2) {
        if (this.K != i2) {
            this.K = i2;
            B();
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Widthable
    public void setWidth(float f2) {
        if (this.L != f2) {
            this.L = f2;
            B();
        }
    }
}
