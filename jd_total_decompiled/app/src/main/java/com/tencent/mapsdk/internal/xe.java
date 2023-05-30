package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.map.lib.models.CircleInfo;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/* loaded from: classes9.dex */
public class xe extends ye<h0> implements ee, h0 {
    private static final int H = 128;
    private GeoPoint B;
    private double C;
    private float D;
    private CircleOptions E;
    private CircleInfo F;
    private qc G;

    public xe(a1 a1Var) {
        super(a1Var);
        this.B = new GeoPoint(39909230, 116397428);
        this.C = 0.0d;
        this.D = 1000.0f;
        this.F = new CircleInfo();
        this.G = a1Var.A();
    }

    private double a(double d, double d2) {
        return d / Math.cos((d2 * 3.141592653589793d) / 180.0d);
    }

    private LatLng a(w5 w5Var) {
        Double.isNaN((float) ((w5Var.c() * 180.0d) / 2.003750834E7d));
        return new LatLng((float) (((Math.atan(Math.exp((r4 * 3.141592653589793d) / 180.0d)) * 2.0d) - 1.5707963267948966d) * 57.29577951308232d), (float) ((w5Var.b() * 180.0d) / 2.003750834E7d));
    }

    private w5 b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new w5((latLng.longitude * 2.003750834E7d) / 180.0d, ((Math.log(Math.tan(((latLng.latitude + 90.0d) * 3.141592653589793d) / 360.0d)) / 0.017453292519943295d) * 2.003750834E7d) / 180.0d);
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        if (this.G == null) {
            return;
        }
        n();
        J();
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        qc qcVar;
        if (a() == -1 || (qcVar = this.G) == null) {
            return;
        }
        qcVar.f().c(a());
        a(-1);
    }

    public void J() {
        if (a() == -1) {
            a(this.G.f().a(this.F));
        } else if (C()) {
            this.G.f().a(a(), this.F);
            this.G.w0();
        }
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: K  reason: merged with bridge method [inline-methods] */
    public h0 x() {
        return this;
    }

    public double L() {
        return this.D;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        double d = this.C;
        double latitudeE6 = this.B.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double a = a(d, latitudeE6 / 1000000.0d);
        double latitudeE62 = this.B.getLatitudeE6();
        Double.isNaN(latitudeE62);
        double longitudeE6 = this.B.getLongitudeE6();
        Double.isNaN(longitudeE6);
        w5 b = b(new LatLng(latitudeE62 / 1000000.0d, longitudeE6 / 1000000.0d));
        w5 w5Var = new w5(b.b() - a, b.c() + a);
        w5 w5Var2 = new w5(b.b() + a, b.c() - a);
        LatLng a2 = a(w5Var);
        LatLng a3 = a(w5Var2);
        Rect rect = new Rect();
        rect.left = (int) (a2.longitude * 1000000.0d);
        rect.top = (int) (a2.latitude * 1000000.0d);
        rect.right = (int) (a3.longitude * 1000000.0d);
        rect.bottom = (int) (a3.latitude * 1000000.0d);
        return rect;
    }

    public void a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return;
        }
        GeoPoint geoPoint2 = this.B;
        if (geoPoint2 == null) {
            this.B = new GeoPoint(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
        } else {
            geoPoint2.setLatitudeE6(geoPoint.getLatitudeE6());
            this.B.setLongitudeE6(geoPoint.getLongitudeE6());
        }
        B();
    }

    public void a(CircleOptions circleOptions) {
        if (circleOptions == null) {
            return;
        }
        this.E = circleOptions;
        LatLng center = circleOptions.getCenter();
        if (center != null) {
            a(GeoPoint.from(center));
        }
        setRadius(circleOptions.getRadius());
        setStrokeWidth(circleOptions.getStrokeWidth());
        setStrokeColor(circleOptions.getStrokeColor());
        setFillColor(circleOptions.getFillColor());
        setZIndex(circleOptions.getZIndex());
        setVisible(circleOptions.isVisible());
        setLevel(circleOptions.getLevel());
        this.E = circleOptions;
        B();
    }

    @Override // com.tencent.mapsdk.internal.ee
    public boolean a(ye yeVar, float f2, float f3) {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        Rect bound = getBound(s4Var);
        int i2 = bound.left;
        int i3 = bound.right;
        int i4 = bound.top;
        int i5 = bound.bottom;
        GeoPoint geoPoint = new GeoPoint(i4, i2);
        GeoPoint geoPoint2 = new GeoPoint(i5, i2);
        GeoPoint geoPoint3 = new GeoPoint(i5, i3);
        GeoPoint geoPoint4 = new GeoPoint(i4, i3);
        o5 a = s4Var.a(geoPoint);
        o5 a2 = s4Var.a(geoPoint2);
        o5 a3 = s4Var.a(geoPoint3);
        o5 a4 = s4Var.a(geoPoint4);
        return new Rect((int) Math.min(Math.min(a.a, a2.a), Math.min(a3.a, a4.a)), (int) Math.min(Math.min(a.b, a2.b), Math.min(a3.b, a4.b)), (int) Math.max(Math.max(a.a, a2.a), Math.max(a3.a, a4.a)), (int) Math.max(Math.max(a.b, a2.b), Math.max(a3.b, a4.b)));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public boolean contains(LatLng latLng) {
        return da.a(getCenter(), latLng) < getRadius();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public LatLng getCenter() {
        GeoPoint geoPoint = this.B;
        if (geoPoint != null) {
            return geoPoint.toLatLng();
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public double getRadius() {
        return this.C;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public void n() {
        GeoPoint geoPoint;
        if ((a() > 0 && !C()) || this.G == null || (geoPoint = this.B) == null) {
            return;
        }
        double d = this.C;
        if (d <= 0.0d) {
            return;
        }
        CircleInfo circleInfo = this.F;
        circleInfo.zIndex = (int) this.f17513m;
        circleInfo.borderColor = this.f17512l;
        circleInfo.borderWidth = this.f17510j;
        circleInfo.fillColor = this.f17511k;
        circleInfo.radius = (float) d;
        circleInfo.centerX = geoPoint.getLongitudeE6();
        this.F.centerY = this.B.getLatitudeE6();
        CircleInfo circleInfo2 = this.F;
        circleInfo2.isVisible = this.f17514n;
        circleInfo2.level = this.p;
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        qc qcVar;
        if (this.B == null || (qcVar = this.G) == null) {
            return false;
        }
        GeoPoint a = qcVar.getProjection().a(new o5(f2, f3));
        return Math.hypot((double) (a.getLatitudeE6() - this.B.getLatitudeE6()), (double) (a.getLongitudeE6() - this.B.getLongitudeE6())) <= ((double) this.D);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public void setCenter(LatLng latLng) {
        a(GeoPoint.from(latLng));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public void setOptions(CircleOptions circleOptions) {
        a(circleOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Circle
    public void setRadius(double d) {
        if (d < 0.0d) {
            return;
        }
        if (d == 0.0d) {
            d = 1.0E-10d;
        }
        CircleOptions circleOptions = this.E;
        if (circleOptions == null || circleOptions.getCenter() == null) {
            return;
        }
        this.C = d;
        this.D = (float) b7.a(d, this.E.getCenter().latitude);
        B();
    }
}
