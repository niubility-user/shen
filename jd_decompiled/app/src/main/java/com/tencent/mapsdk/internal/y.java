package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.location.Location;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.s4;
import com.tencent.mapsdk.internal.v;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class y extends z5 implements s4 {

    /* renamed from: e */
    public static final int f17468e = 268435456;

    /* renamed from: f */
    private static final int f17469f = 19;

    /* renamed from: g */
    private static final int f17470g = 14;
    public v b;

    /* renamed from: c */
    private qi f17471c;
    public qc d;

    /* loaded from: classes9.dex */
    public class a implements qc.b {
        public final /* synthetic */ List a;
        public final /* synthetic */ List b;

        /* renamed from: c */
        public final /* synthetic */ int f17472c;
        public final /* synthetic */ int d;

        /* renamed from: e */
        public final /* synthetic */ Rect f17473e;

        /* renamed from: f */
        public final /* synthetic */ int f17474f;

        /* renamed from: g */
        public final /* synthetic */ z f17475g;

        /* renamed from: h */
        public final /* synthetic */ s4.a f17476h;

        public a(List list, List list2, int i2, int i3, Rect rect, int i4, z zVar, s4.a aVar) {
            y.this = r1;
            this.a = list;
            this.b = list2;
            this.f17472c = i2;
            this.d = i3;
            this.f17473e = rect;
            this.f17474f = i4;
            this.f17475g = zVar;
            this.f17476h = aVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            v.c v;
            GeoPoint a = y.this.b.a();
            float p = y.this.b.p();
            float i2 = y.this.b.i();
            GeoPoint geoPoint = null;
            float f2 = 4.0f;
            while (true) {
                if (f2 < i2) {
                    break;
                }
                y.this.b(f2);
                Rect a2 = y.this.a(this.a, this.b);
                GeoPoint geoPoint2 = new GeoPoint(a2.centerY(), a2.centerX());
                y.this.c(geoPoint2);
                GeoPoint geoPoint3 = new GeoPoint(a2.top, a2.left);
                GeoPoint geoPoint4 = new GeoPoint(a2.bottom, a2.right);
                o5 a3 = y.this.a(geoPoint3);
                o5 a4 = y.this.a(geoPoint4);
                Rect rect = new Rect();
                rect.left = (int) Math.min(a3.a, a4.a);
                rect.right = (int) Math.max(a3.a, a4.a);
                rect.top = (int) Math.min(a3.b, a4.b);
                rect.bottom = (int) Math.max(a3.b, a4.b);
                if (this.f17472c < rect.width() || this.d < rect.height()) {
                    f2 /= 1.01f;
                    geoPoint = geoPoint2;
                } else {
                    geoPoint = !y.this.c() ? y.this.a(geoPoint2, this.f17473e) : (!y.this.d() || (v = y.this.b.v()) == null) ? geoPoint2 : y.this.a(geoPoint2, v);
                }
            }
            float max = Math.max(i2, f2);
            int i3 = this.f17474f;
            if (i3 != 60) {
                this.f17475g.b(i3);
            }
            y.this.c(a);
            y.this.b(p);
            s4.a aVar = this.f17476h;
            if (aVar != null) {
                try {
                    aVar.a(max, geoPoint, -1.0d);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public y(e1 e1Var) {
        super(4.007501668E7d);
        this.f17471c = e1Var.f();
        this.b = e1Var.b();
        if (e1Var instanceof qc) {
            this.d = (qc) e1Var;
        }
    }

    public Rect a(List<? extends Boundable> list, List<GeoPoint> list2) {
        int i2;
        int i3;
        int i4;
        Rect bound;
        int i5 = 0;
        if (list != null) {
            i2 = 0;
            i3 = 0;
            i4 = 0;
            for (Boundable boundable : list) {
                if (boundable != null && (bound = boundable.getBound(this)) != null) {
                    if (i5 == 0) {
                        i5 = bound.left;
                    }
                    if (i2 == 0) {
                        i2 = bound.right;
                    }
                    if (i3 == 0) {
                        i3 = bound.top;
                    }
                    if (i4 == 0) {
                        i4 = bound.bottom;
                    }
                    int i6 = bound.left;
                    if (i6 < i5) {
                        i5 = i6;
                    }
                    int i7 = bound.right;
                    if (i7 > i2) {
                        i2 = i7;
                    }
                    int i8 = bound.top;
                    if (i8 > i3) {
                        i3 = i8;
                    }
                    int i9 = bound.bottom;
                    if (i9 < i4) {
                        i4 = i9;
                    }
                }
            }
        } else {
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        if (list2 != null) {
            for (GeoPoint geoPoint : list2) {
                if (geoPoint != null) {
                    if (i5 == 0) {
                        i5 = geoPoint.getLongitudeE6();
                    }
                    if (i2 == 0) {
                        i2 = geoPoint.getLongitudeE6();
                    }
                    if (i3 == 0) {
                        i3 = geoPoint.getLatitudeE6();
                    }
                    if (i4 == 0) {
                        i4 = geoPoint.getLatitudeE6();
                    }
                    if (geoPoint.getLongitudeE6() < i5) {
                        i5 = geoPoint.getLongitudeE6();
                    }
                    if (geoPoint.getLongitudeE6() > i2) {
                        i2 = geoPoint.getLongitudeE6();
                    }
                    if (geoPoint.getLatitudeE6() > i3) {
                        i3 = geoPoint.getLatitudeE6();
                    }
                    if (geoPoint.getLatitudeE6() < i4) {
                        i4 = geoPoint.getLatitudeE6();
                    }
                }
            }
        }
        return new Rect(i5, i3, i2, i4);
    }

    public GeoPoint a(GeoPoint geoPoint, Rect rect) {
        o5 a2;
        if (geoPoint == null || (a2 = a(geoPoint)) == null || rect == null) {
            return null;
        }
        double d = a2.a;
        double d2 = rect.left - rect.right;
        Double.isNaN(d2);
        double d3 = d - (d2 * 0.5d);
        double d4 = a2.b;
        double d5 = rect.top - rect.bottom;
        Double.isNaN(d5);
        return a(new o5(d3, d4 - (d5 * 0.5d)));
    }

    public GeoPoint a(GeoPoint geoPoint, v.c cVar) {
        o5 a2;
        if (geoPoint == null || (a2 = a(geoPoint)) == null || cVar == null) {
            return null;
        }
        double d = a2.a;
        double d2 = a2.b;
        Rect rect = new Rect(this.d.e());
        int width = rect.width();
        int height = rect.height();
        float b = cVar.b() + 0.5f;
        double a3 = cVar.a() + 0.5f;
        if (a3 < 0.25d) {
            Double.isNaN(a3);
            double d3 = width;
            Double.isNaN(d3);
            d += (0.25d - a3) * d3;
        } else if (a3 > 0.75d) {
            Double.isNaN(a3);
            double d4 = width;
            Double.isNaN(d4);
            d -= (a3 - 0.75d) * d4;
        }
        double d5 = b;
        if (d5 < 0.25d) {
            Double.isNaN(d5);
            double d6 = height;
            Double.isNaN(d6);
            d2 += (0.25d - d5) * d6;
        } else if (d5 > 0.75d) {
            Double.isNaN(d5);
            double d7 = height;
            Double.isNaN(d7);
            d2 -= (d5 - 0.75d) * d7;
        }
        return a(new o5(d, d2));
    }

    public static o5 a(v vVar, GeoPoint geoPoint) {
        if (vVar == null || geoPoint == null) {
            return null;
        }
        double l2 = vVar.l();
        double k2 = vVar.k();
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double min = Math.min(Math.max(Math.sin((latitudeE6 / 1000000.0d) * 0.017453292519943295d), -0.9999d), 0.9999d);
        double w = vVar.w() >> 1;
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        Double.isNaN(w);
        Double.isNaN(w);
        return new o5(((longitudeE6 / 1000000.0d) * l2) + w, w + (Math.log((min + 1.0d) / (1.0d - min)) * k2 * 0.5d));
    }

    private LatLng a(Context context, o5 o5Var) {
        double d = f7.d(context) * 2.68435456E8f;
        Double.isNaN(d);
        double d2 = d / 2.0d;
        Double.isNaN(d);
        double d3 = d / 360.0d;
        Double.isNaN(d);
        return new LatLng((Math.asin(1.0d - (2.0d / (Math.pow(2.718281828459045d, ((o5Var.b - d2) / 0.5d) / (d / 6.283185307179586d)) + 1.0d))) * 180.0d) / 3.141592653589793d, (o5Var.a - d2) / d3);
    }

    public static w5 b(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        return new w5(((longitudeE6 / 1000000.0d) * 2.003750834E7d) / 180.0d, ((Math.log(Math.tan((((latitudeE6 / 1000000.0d) + 90.0d) * 3.141592653589793d) / 360.0d)) / 0.017453292519943295d) * 2.003750834E7d) / 180.0d);
    }

    public static LatLng b(w5 w5Var) {
        if (w5Var == null) {
            return null;
        }
        return new LatLng(((Math.atan(Math.exp((((w5Var.y() / 2.003750834E7d) * 180.0d) * 3.141592653589793d) / 180.0d)) * 2.0d) - 1.5707963267948966d) * 57.29577951308232d, (w5Var.x() / 2.003750834E7d) * 180.0d);
    }

    private o5 c(o5 o5Var) {
        if (o5Var == null) {
            return null;
        }
        o5 b = this.b.b();
        double width = this.b.r().width() / 2;
        Double.isNaN(width);
        double height = this.b.r().height() / 2;
        Double.isNaN(height);
        return new o5(width + (o5Var.a - b.a), height - (o5Var.b - b.b));
    }

    public boolean c() {
        v.c v = this.b.v();
        if (v == null) {
            return false;
        }
        return (v.a() == 0.0f && v.b() == 0.0f) ? false : true;
    }

    public boolean d() {
        v.c v = this.b.v();
        if (v == null) {
            return false;
        }
        return ((double) Math.abs(v.a())) > 0.25d || ((double) Math.abs(v.b())) > 0.25d;
    }

    @Override // com.tencent.mapsdk.internal.s4
    public double a(Point point2, Point point3) {
        GeoPoint a2 = a(new o5(point2.x, point2.y));
        GeoPoint a3 = a(new o5(point3.x, point3.y));
        double latitudeE6 = a2.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double longitudeE6 = a2.getLongitudeE6();
        Double.isNaN(longitudeE6);
        double latitudeE62 = a3.getLatitudeE6();
        Double.isNaN(latitudeE62);
        double longitudeE62 = a3.getLongitudeE6();
        Double.isNaN(longitudeE62);
        Location.distanceBetween(latitudeE6 / 1000000.0d, longitudeE6 / 1000000.0d, latitudeE62 / 1000000.0d, longitudeE62 / 1000000.0d, new float[1]);
        return r2[0] / ((int) Math.sqrt(Math.pow(point2.x - point3.x, 2.0d) + Math.pow(point2.y - point3.y, 2.0d)));
    }

    /* JADX WARN: Removed duplicated region for block: B:113:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01b9  */
    @Override // com.tencent.mapsdk.internal.s4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public float a(com.tencent.tencentmap.mapsdk.maps.model.LatLng r30, com.tencent.tencentmap.mapsdk.maps.model.LatLng r31, int r32, int r33, int r34, int r35, com.tencent.tencentmap.mapsdk.maps.model.LatLng r36) {
        /*
            Method dump skipped, instructions count: 513
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.y.a(com.tencent.tencentmap.mapsdk.maps.model.LatLng, com.tencent.tencentmap.mapsdk.maps.model.LatLng, int, int, int, int, com.tencent.tencentmap.mapsdk.maps.model.LatLng):float");
    }

    @Override // com.tencent.mapsdk.internal.s4
    public GeoPoint a(o5 o5Var) {
        Rect f0;
        if (o5Var == null) {
            return null;
        }
        byte[] y = this.b.y();
        Rect r = this.b.r();
        int height = r.height();
        float A = this.f17471c.A();
        if (String.valueOf(A).equals("960.0")) {
            A = height;
        }
        double d = o5Var.b;
        if (d >= r.top) {
            double d2 = (r1 + height) - A;
            if (d < d2) {
                d = d2;
            }
        }
        float f2 = (float) o5Var.a;
        float f3 = (float) d;
        qc qcVar = this.d;
        if (qcVar != null && (f0 = qcVar.f0()) != null) {
            f2 -= f0.left;
            f3 -= f0.top;
        }
        return this.f17471c.a(y, f2, f3);
    }

    @Override // com.tencent.mapsdk.internal.s4
    public o5 a(Context context, LatLng latLng) {
        double d = f7.d(context) * 2.68435456E8f;
        Double.isNaN(d);
        double d2 = d / 2.0d;
        Double.isNaN(d);
        double d3 = d / 360.0d;
        Double.isNaN(d);
        double min = Math.min(Math.max(Math.sin(latLng.latitude * 0.017453292519943295d), -0.9999d), 0.9999d);
        return new o5((latLng.longitude * d3) + d2, d2 + (Math.log((min + 1.0d) / (1.0d - min)) * (d / 6.283185307179586d) * 0.5d));
    }

    @Override // com.tencent.mapsdk.internal.s4
    public o5 a(GeoPoint geoPoint) {
        Rect f0;
        if (geoPoint == null) {
            return null;
        }
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double d = latitudeE6 / 1000000.0d;
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        PointF a2 = this.f17471c.a(this.b.y(), d, longitudeE6 / 1000000.0d);
        float f2 = a2.x;
        float f3 = a2.y;
        qc qcVar = this.d;
        if (qcVar != null && (f0 = qcVar.f0()) != null) {
            f2 += f0.left;
            f3 += f0.top;
        }
        return new o5(f2, f3);
    }

    @Override // com.tencent.mapsdk.internal.s4
    public w5 a(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return b(GeoPoint.from(latLng));
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng a(PointF pointF) {
        if (pointF == null) {
            return null;
        }
        float m2 = this.d.b().m();
        Rect r = this.b.r();
        float width = r.width();
        float height = r.height();
        GeoPoint a2 = this.b.a();
        if (a2 == null) {
            return new LatLng(0.0d, 0.0d);
        }
        o5 c2 = c(ea.d(a2));
        v.c v = this.b.v();
        PointF pointF2 = v == null ? new PointF(width / 2.0f, height / 2.0f) : new PointF((v.a() + 0.5f) * width, (v.b() + 0.5f) * height);
        Matrix matrix = new Matrix();
        matrix.setRotate(-m2);
        if (v == null) {
            matrix.preTranslate(width * (-0.5f), (-0.5f) * height);
            matrix.postTranslate(width * 0.5f, height * 0.5f);
        } else {
            matrix.preTranslate(((-0.5f) - v.a()) * width, ((-0.5f) - v.b()) * height);
            matrix.postTranslate((v.a() + 0.5f) * width, (v.b() + 0.5f) * height);
        }
        float[] fArr = new float[2];
        matrix.mapPoints(fArr, new float[]{pointF.x, pointF.y});
        double q = (1 << this.b.q()) * 256;
        Double.isNaN(q);
        double d = 2.003750834E7d / q;
        double w = b7.w();
        Double.isNaN(w);
        double d2 = d * w;
        double d3 = c2.a;
        double d4 = fArr[0] - pointF2.x;
        Double.isNaN(d4);
        double d5 = c2.b;
        double d6 = fArr[1] - pointF2.y;
        Double.isNaN(d6);
        return b(new o5(d3 + (d4 * d2), d5 + (d6 * d2)));
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng a(w5 w5Var) {
        return b(w5Var);
    }

    public void a(float f2) {
        this.b.f(f2);
    }

    public void a(int i2) {
        this.b.f(i2);
    }

    public void a(v vVar) {
        this.b = vVar;
    }

    @Override // com.tencent.mapsdk.internal.s4
    public void a(List<? extends Boundable> list, List<GeoPoint> list2, Rect rect, s4.a aVar) {
        if ((list == null || list.isEmpty()) && (list2 == null || list2.isEmpty())) {
            return;
        }
        Rect rect2 = new Rect(this.d.e());
        if (rect != null) {
            rect2.left += rect.left;
            rect2.right -= rect.right;
            rect2.top += rect.top;
            rect2.bottom -= rect.bottom;
        }
        int width = rect2.width();
        int height = rect2.height();
        z g2 = this.d.h().g();
        int f2 = g2.f();
        this.d.a(new a(list, list2, width, height, rect, f2, g2, aVar));
        if (f2 != 60) {
            g2.j();
            g2.d();
        }
    }

    @Override // com.tencent.mapsdk.internal.s4
    public LatLng[] a() {
        Rect e2 = this.d.e();
        float width = e2.width();
        float height = e2.height();
        return new LatLng[]{this.d.getProjection().a(new PointF(0.0f, 0.0f)), this.d.getProjection().a(new PointF(width, 0.0f)), this.d.getProjection().a(new PointF(width, height)), this.d.getProjection().a(new PointF(0.0f, height))};
    }

    @Override // com.tencent.mapsdk.internal.s4
    public PointF b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        double sin = Math.sin(Math.toRadians(latLng.latitude));
        return new PointF((float) (((latLng.longitude / 360.0d) + 0.5d) * 2.68435456E8d), (float) ((((Math.log((sin + 1.0d) / (1.0d - sin)) * 0.5d) / (-6.283185307179586d)) + 0.5d) * 2.68435456E8d));
    }

    public v b() {
        return this.b;
    }

    public void b(float f2) {
        this.b.h(f2);
    }

    public void c(GeoPoint geoPoint) {
        this.b.b(geoPoint);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public LatLng fromScreenLocation(Point point2) {
        return a(new o5(point2.x, point2.y)).toLatLng();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public VisibleRegion getVisibleRegion() {
        xi xiVar = (xi) this.d.j();
        float A = this.d.f().A();
        int height = this.b.r().height();
        Point point2 = new Point(0, xiVar.a1);
        Point point3 = new Point(xiVar.Z0, xiVar.a1);
        double d = height - A;
        o5 o5Var = new o5(0.0d, d);
        o5 o5Var2 = new o5(xiVar.Z0, d);
        LatLng fromScreenLocation = fromScreenLocation(point2);
        LatLng fromScreenLocation2 = fromScreenLocation(point3);
        LatLng latLng = a(o5Var).toLatLng();
        LatLng latLng2 = a(o5Var2).toLatLng();
        return new VisibleRegion(fromScreenLocation, fromScreenLocation2, latLng, latLng2, LatLngBounds.builder().include(fromScreenLocation).include(fromScreenLocation2).include(latLng).include(latLng2).build());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glModelMatrix(PointF pointF, float f2) {
        if (pointF == null) {
            return null;
        }
        float[] fArr = new float[16];
        android.opengl.Matrix.setIdentityM(fArr, 0);
        float[] G = this.f17471c.G();
        float[] H = this.f17471c.H();
        GeoPoint n2 = this.f17471c.n();
        android.opengl.Matrix.multiplyMM(fArr, 0, G, 0, H, 0);
        PointF b = b(ea.d(n2));
        android.opengl.Matrix.translateM(fArr, 0, pointF.x - b.x, b.y - pointF.y, 0.0f);
        android.opengl.Matrix.scaleM(fArr, 0, f2, f2, f2);
        return fArr;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float glPixelRatio() {
        return this.f17471c.I();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glProjectionMatrix() {
        return this.f17471c.G();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public PointF glVertexForCoordinate(LatLng latLng) {
        return b(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public float[] glViewMatrix() {
        return this.f17471c.H();
    }

    @Override // com.tencent.mapsdk.internal.s4, com.tencent.tencentmap.mapsdk.maps.Projection
    public double metersPerPixel(double d) {
        double w = b7.w();
        Double.isNaN(w);
        return (4.0076E7d / ((Math.pow(2.0d, this.b.x()) * 256.0d) * w)) * Math.cos((d * 3.141592653589793d) / 180.0d);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.Projection
    public Point toScreenLocation(LatLng latLng) {
        Rect f0;
        PointF a2 = this.f17471c.a(this.b.y(), latLng.latitude, latLng.longitude);
        float f2 = a2.x;
        float f3 = a2.y;
        qc qcVar = this.d;
        if (qcVar != null && (f0 = qcVar.f0()) != null) {
            f2 += f0.left;
            f3 += f0.top;
        }
        o5 o5Var = new o5(f2, f3);
        Point point2 = new Point();
        point2.x = (int) Math.round(o5Var.a);
        point2.y = (int) Math.round(o5Var.b);
        return point2;
    }
}
