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

    /* JADX WARN: Removed duplicated region for block: B:179:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x01b3  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x01b9  */
    @Override // com.tencent.mapsdk.internal.s4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public float a(LatLng latLng, LatLng latLng2, int i2, int i3, int i4, int i5, LatLng latLng3) {
        int i6;
        int i7;
        LatLng latLng4;
        Context context;
        float f2;
        double d;
        double d2;
        double d3;
        double a2;
        double b;
        Context context2 = this.d.getContext();
        Rect k2 = this.d.h().k();
        boolean C = this.d.h().C();
        int i8 = k2.left;
        int i9 = k2.right;
        int i10 = k2.top;
        int i11 = k2.bottom;
        int width = this.d.e().width();
        int height = this.d.e().height();
        float g2 = this.d.b().g();
        float j2 = this.d.b().j();
        v.c v = this.d.b().v();
        if (width == 0 || height == 0 || latLng == null || latLng2 == null) {
            return -2.0f;
        }
        if (i2 + i3 + i8 + i9 > width || i4 + i5 + i10 + i11 > height) {
            return -1.0f;
        }
        int h2 = this.d.b().h();
        o5 a3 = a(context2, latLng);
        o5 a4 = a(context2, latLng2);
        double d4 = a4.a - a3.a;
        if (d4 < 0.0d) {
            d4 = Math.abs(d4);
        }
        double d5 = a4.b - a3.b;
        if (d5 < 0.0d) {
            d5 = Math.abs(d5);
        }
        int i12 = (((width - i2) - i3) - i8) - i9;
        int i13 = (((height - i4) - i5) - i10) - i11;
        int i14 = i12 <= 0 ? 1 : i12;
        if (i13 <= 0) {
            i7 = height;
            i6 = 1;
        } else {
            i6 = i13;
            i7 = height;
        }
        double d6 = i14;
        Double.isNaN(d6);
        double d7 = d4 / d6;
        double d8 = i6;
        Double.isNaN(d8);
        double max = Math.max(d7, d5 / d8);
        double d9 = h2;
        Double.isNaN(d9);
        float log = (float) (d9 - (Math.log(max) / Math.log(2.0d)));
        if (g2 < log) {
            max = Math.pow(2.0d, h2 - g2);
        } else {
            g2 = log;
        }
        if (j2 > g2) {
            max = Math.pow(2.0d, h2 - j2);
            latLng4 = latLng3;
            g2 = j2;
        } else {
            latLng4 = latLng3;
        }
        if (latLng4 != null) {
            LatLng latLng5 = new LatLng((latLng.latitude + latLng2.latitude) / 2.0d, (latLng.longitude + latLng2.longitude) / 2.0d);
            double d10 = i3 - i2;
            Double.isNaN(d10);
            double d11 = i4 - i5;
            Double.isNaN(d11);
            o5 a5 = a(context2, latLng5);
            double d12 = a5.a + ((d10 * max) / 2.0d);
            a5.a = d12;
            double d13 = a5.b + ((d11 * max) / 2.0d);
            a5.b = d13;
            if (C) {
                int i15 = i8 - i9;
                int i16 = i10 - i11;
                double d14 = (i15 / (width * 2.0f)) + 0.5f;
                if (d14 < 0.25d) {
                    double d15 = width;
                    Double.isNaN(d15);
                    d = d15 * (-0.5d);
                } else {
                    if (d14 > 0.75d) {
                        double d16 = width;
                        Double.isNaN(d16);
                        d = d16 * 0.5d;
                    }
                    int i17 = i7;
                    f2 = g2;
                    d2 = (i16 / (i17 * 2.0f)) + 0.5f;
                    if (d2 >= 0.25d) {
                        double d17 = i17;
                        Double.isNaN(d17);
                        d3 = d17 * (-0.5d);
                    } else {
                        if (d2 > 0.75d) {
                            double d18 = i17;
                            Double.isNaN(d18);
                            d3 = d18 * 0.5d;
                        }
                        double d19 = i15;
                        Double.isNaN(d19);
                        double d20 = (d19 * max) / 2.0d;
                        double d21 = i16;
                        Double.isNaN(d21);
                        double d22 = (d21 * max) / 2.0d;
                        if (v == null) {
                            context = context2;
                            b = 0.0d;
                            a2 = 0.0d;
                        } else {
                            context = context2;
                            a2 = v.a();
                            b = v.b();
                        }
                        double d23 = d12 - d20;
                        double d24 = width;
                        Double.isNaN(d24);
                        d12 = d23 + (a2 * d24 * max);
                        double d25 = d13 - d22;
                        double d26 = i17;
                        Double.isNaN(d26);
                        d13 = d25 - ((b * d26) * max);
                    }
                    i16 = (int) d3;
                    double d192 = i15;
                    Double.isNaN(d192);
                    double d202 = (d192 * max) / 2.0d;
                    double d212 = i16;
                    Double.isNaN(d212);
                    double d222 = (d212 * max) / 2.0d;
                    if (v == null) {
                    }
                    double d232 = d12 - d202;
                    double d242 = width;
                    Double.isNaN(d242);
                    d12 = d232 + (a2 * d242 * max);
                    double d252 = d13 - d222;
                    double d262 = i17;
                    Double.isNaN(d262);
                    d13 = d252 - ((b * d262) * max);
                }
                i15 = (int) d;
                int i172 = i7;
                f2 = g2;
                d2 = (i16 / (i172 * 2.0f)) + 0.5f;
                if (d2 >= 0.25d) {
                }
                i16 = (int) d3;
                double d1922 = i15;
                Double.isNaN(d1922);
                double d2022 = (d1922 * max) / 2.0d;
                double d2122 = i16;
                Double.isNaN(d2122);
                double d2222 = (d2122 * max) / 2.0d;
                if (v == null) {
                }
                double d2322 = d12 - d2022;
                double d2422 = width;
                Double.isNaN(d2422);
                d12 = d2322 + (a2 * d2422 * max);
                double d2522 = d13 - d2222;
                double d2622 = i172;
                Double.isNaN(d2622);
                d13 = d2522 - ((b * d2622) * max);
            } else {
                context = context2;
                f2 = g2;
            }
            LatLng a6 = a(context, new o5(d12, d13));
            latLng3.latitude = a6.latitude;
            latLng3.longitude = a6.longitude;
            return f2;
        }
        return g2;
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
