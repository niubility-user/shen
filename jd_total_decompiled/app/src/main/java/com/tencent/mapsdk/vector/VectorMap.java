package com.tencent.mapsdk.vector;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Keep;
import com.tencent.map.lib.MapLanguage;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.map.lib.models.AnnocationText;
import com.tencent.map.lib.models.AnnocationTextResult;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.IndoorCellInfo;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.map.sdk.comps.vis.VisualLayer;
import com.tencent.map.sdk.comps.vis.VisualLayerOptions;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.mapsdk.internal.a0;
import com.tencent.mapsdk.internal.a5;
import com.tencent.mapsdk.internal.b1;
import com.tencent.mapsdk.internal.c0;
import com.tencent.mapsdk.internal.c5;
import com.tencent.mapsdk.internal.ce;
import com.tencent.mapsdk.internal.cg;
import com.tencent.mapsdk.internal.d0;
import com.tencent.mapsdk.internal.d5;
import com.tencent.mapsdk.internal.dj;
import com.tencent.mapsdk.internal.e0;
import com.tencent.mapsdk.internal.e5;
import com.tencent.mapsdk.internal.ej;
import com.tencent.mapsdk.internal.f0;
import com.tencent.mapsdk.internal.f5;
import com.tencent.mapsdk.internal.fa;
import com.tencent.mapsdk.internal.g1;
import com.tencent.mapsdk.internal.g5;
import com.tencent.mapsdk.internal.h1;
import com.tencent.mapsdk.internal.i5;
import com.tencent.mapsdk.internal.i8;
import com.tencent.mapsdk.internal.j5;
import com.tencent.mapsdk.internal.j8;
import com.tencent.mapsdk.internal.jf;
import com.tencent.mapsdk.internal.k1;
import com.tencent.mapsdk.internal.k4;
import com.tencent.mapsdk.internal.k5;
import com.tencent.mapsdk.internal.ke;
import com.tencent.mapsdk.internal.l1;
import com.tencent.mapsdk.internal.l5;
import com.tencent.mapsdk.internal.la;
import com.tencent.mapsdk.internal.lc;
import com.tencent.mapsdk.internal.lf;
import com.tencent.mapsdk.internal.m1;
import com.tencent.mapsdk.internal.ma;
import com.tencent.mapsdk.internal.me;
import com.tencent.mapsdk.internal.oe;
import com.tencent.mapsdk.internal.pc;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.internal.s4;
import com.tencent.mapsdk.internal.se;
import com.tencent.mapsdk.internal.sh;
import com.tencent.mapsdk.internal.t4;
import com.tencent.mapsdk.internal.u4;
import com.tencent.mapsdk.internal.v;
import com.tencent.mapsdk.internal.v4;
import com.tencent.mapsdk.internal.w;
import com.tencent.mapsdk.internal.w0;
import com.tencent.mapsdk.internal.w4;
import com.tencent.mapsdk.internal.x;
import com.tencent.mapsdk.internal.x4;
import com.tencent.mapsdk.internal.xf;
import com.tencent.mapsdk.internal.y3;
import com.tencent.mapsdk.internal.y4;
import com.tencent.mapsdk.internal.z4;
import com.tencent.mapsdk.internal.z8;
import com.tencent.mapsdk.internal.zg;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.CustomRender;
import com.tencent.tencentmap.mapsdk.maps.LocationSource;
import com.tencent.tencentmap.mapsdk.maps.MapParamConstants;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapContext;
import com.tencent.tencentmap.mapsdk.maps.UiSettings;
import com.tencent.tencentmap.mapsdk.maps.internal.TencentMapPro;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayer;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Arc;
import com.tencent.tencentmap.mapsdk.maps.model.ArcOptions;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayer;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.GeometryConstants;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.IOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapFontSize;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MyLocationStyle;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.maps.model.RestrictBoundsFitMode;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class VectorMap extends m1 {
    public static final int A = 5;
    public static final int B = 11;
    public static final int C = 15;
    public static final int D = 18;
    public static final float E = MapParamConstants.MAX_SKEW_ANGLE;
    public static final int F = 0;
    public static final int G = 1;
    public static final int H = 2;
    private static final int I = 1;
    private static final int J = 2;
    private static final int K = 3;
    public static final int L = 0;
    public static final int M = 1;
    public static final int N = 2;
    public static final int O = 3;
    public static final int v = 0;
    public static final int w = 1;
    public static final int x = 2;
    public static final int y = 3;
    public static final int z = 4;
    private qc b;
    private h1 d;

    /* renamed from: i */
    private a0 f17593i;

    /* renamed from: l */
    private jf f17596l;
    private UiSettings o;
    private boolean p;
    private TencentMapPro q;
    private ej r;
    private float s;
    private boolean a = false;

    /* renamed from: c */
    private boolean f17588c = true;

    /* renamed from: e */
    private lf f17589e = null;

    /* renamed from: f */
    private g1 f17590f = null;

    /* renamed from: g */
    private f0 f17591g = null;

    /* renamed from: h */
    private Projection f17592h = null;

    /* renamed from: j */
    private c0 f17594j = null;

    /* renamed from: k */
    private e0 f17595k = null;

    /* renamed from: m */
    private boolean f17597m = false;

    /* renamed from: n */
    private boolean f17598n = false;
    private GeoPoint t = new GeoPoint();
    private int u = 0;

    /* loaded from: classes9.dex */
    public class a implements l1.d {
        public a() {
            VectorMap.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.l1.d
        public void a() {
            VectorMap.this.setMyLocationEnabled(true);
            Location myLocation = VectorMap.this.getMyLocation();
            if (myLocation != null) {
                VectorMap.this.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(myLocation.getLatitude(), myLocation.getLongitude())));
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
            VectorMap.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            VectorMap.this.b.a(false, true, false, false);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            Language.values();
            int[] iArr = new int[2];
            a = iArr;
            try {
                Language language = Language.en;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                Language language2 = Language.zh;
                iArr2[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public VectorMap(qc qcVar) {
        this.b = qcVar;
    }

    private boolean a(float f2, float f3) {
        jf jfVar;
        TappedElement e2 = e(f2, f3);
        if (e2 == null || (jfVar = this.f17596l) == null) {
            return false;
        }
        return jfVar.a(e2);
    }

    private boolean b(float f2, float f3) {
        return this.b.g().c(f2, f3);
    }

    private boolean c(float f2, float f3) {
        g1 g1Var = this.f17590f;
        if (g1Var != null) {
            return g1Var.a(f2, f3);
        }
        return false;
    }

    public static boolean c(int i2, int i3) {
        return GeometryConstants.BOUNDARY_WORLD.contains(i3, i2);
    }

    private void d0() {
        if (this.f17590f == null) {
            this.f17590f = new g1(this.b, this.f17593i);
        }
        if (this.f17594j == null) {
            this.f17594j = new c0(this.f17590f);
        }
    }

    public static boolean e(GeoPoint geoPoint) {
        return k4.f16759e.contains(geoPoint.getLongitudeE6(), geoPoint.getLatitudeE6());
    }

    public static boolean f(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return false;
        }
        return c(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
    }

    private int p(int i2) {
        if (i2 < 3) {
            i2 = 3;
        }
        if (i2 > 22) {
            return 22;
        }
        return i2;
    }

    private void r0() {
        e0 e0Var = this.f17595k;
        if (e0Var != null) {
            e0Var.a();
            this.f17595k = null;
        }
        c0 c0Var = this.f17594j;
        if (c0Var != null) {
            c0Var.c();
            this.f17594j = null;
        }
        a0 a0Var = this.f17593i;
        if (a0Var != null) {
            a0Var.a();
            this.f17593i = null;
        }
        if (this.f17592h != null) {
            this.f17592h = null;
        }
        f0 f0Var = this.f17591g;
        if (f0Var != null) {
            f0Var.a();
            this.f17591g = null;
        }
    }

    private void s0() {
        g1 g1Var = this.f17590f;
        if (g1Var != null) {
            g1Var.e();
            this.f17590f = null;
        }
        h1 h1Var = this.d;
        if (h1Var != null) {
            h1Var.i();
            this.d = null;
        }
    }

    public float A() {
        return this.b.h().j();
    }

    public void A0() {
        this.b.h().P();
    }

    public Rect B() {
        return this.b.Q();
    }

    public void B0() {
        this.b.h().Q();
    }

    public String[] C() {
        return this.b.R();
    }

    public void C0() {
        this.b.h().R();
    }

    public g1 D() {
        return this.f17590f;
    }

    public void D0() {
        this.b.D0();
    }

    public int E() {
        return this.u;
    }

    public void E0() {
        this.b.E0();
    }

    public pc F() {
        return this.b.U();
    }

    public qc G() {
        return this.b;
    }

    public String H() {
        qc qcVar = this.b;
        if (qcVar == null) {
            return null;
        }
        return qcVar.W();
    }

    public String I() {
        qc qcVar = this.b;
        if (qcVar == null) {
            return null;
        }
        return qcVar.X();
    }

    public String J() {
        qc qcVar = this.b;
        if (qcVar != null) {
            return qcVar.p();
        }
        return null;
    }

    public Language K() {
        return this.b.T();
    }

    public h1 L() {
        return this.d;
    }

    public v M() {
        return this.b.b();
    }

    public ArrayList<MapPoi> N() {
        qc qcVar = this.b;
        if (qcVar == null) {
            return null;
        }
        return qcVar.Y();
    }

    public Rect O() {
        return this.b.h().n();
    }

    public int P() {
        return this.b.b().g();
    }

    public int Q() {
        return this.b.b().h();
    }

    public int R() {
        return this.b.b().j();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    /* renamed from: S */
    public s4 getProjection() {
        qc qcVar;
        return (this.f17597m || (qcVar = this.b) == null) ? new x() : qcVar.getProjection();
    }

    public float T() {
        return this.b.h().p();
    }

    public float U() {
        return this.b.h().q();
    }

    public int V() {
        return this.b.h().r();
    }

    public GeoPoint W() {
        return this.b.h().t();
    }

    public Rect X() {
        return this.b.e();
    }

    public GeoPoint Y() {
        return this.b.h().u();
    }

    public float Z() {
        return this.b.h().v();
    }

    public double a(Rect rect, Rect rect2) {
        return this.b.h().b(rect, rect2);
    }

    public float a(double d, GeoPoint geoPoint) {
        if (geoPoint == null) {
            return 0.0f;
        }
        return this.b.a(d, geoPoint);
    }

    public int a(int i2, int i3, int i4, int i5, int i6, float f2) {
        return this.b.a(i2, i3, i4, i5, i6, f2);
    }

    public int a(int i2, int i3, int i4, int i5, boolean z2) {
        return this.b.h().a(i2, i3, i4, i5, z2);
    }

    @Deprecated
    public int a(TileOverlayCallback tileOverlayCallback, boolean z2) {
        return this.b.a(tileOverlayCallback, z2);
    }

    public int a(PolygonInfo polygonInfo) {
        return this.b.a(polygonInfo);
    }

    public int a(String str) {
        qc qcVar = this.b;
        if (qcVar == null) {
            return -1;
        }
        return qcVar.a(str);
    }

    public AnnocationTextResult a(AnnocationText annocationText) {
        return this.b.a(annocationText);
    }

    public j8 a(int i2, Object obj, Object obj2) {
        return this.b.h().a(i2, obj, obj2);
    }

    public String a(GeoPoint geoPoint) {
        return this.b.a(geoPoint);
    }

    public List<Integer> a(Rect rect, int i2) {
        return this.b.a(rect, i2);
    }

    public void a(double d) {
        this.b.h().l(d);
    }

    public void a(double d, double d2) {
        this.b.h().b(d, d2);
    }

    public void a(double d, double d2, double d3, double d4, double d5, Runnable runnable) {
        this.b.h().a(d, d2, d3, d4, d5, runnable);
    }

    public void a(float f2) {
        this.s = f2;
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(f2);
            if (this.u != 2 || b0()) {
                return;
            }
            a(this.s);
        }
    }

    public void a(float f2, float f3, float f4, float f5) {
        this.b.S().a(f2, f3, f4, f5);
    }

    public void a(float f2, float f3, int i2, boolean z2) {
        this.b.h().b(true);
        this.b.h().a(f2, f3, z2);
    }

    public void a(float f2, float f3, boolean z2) {
        this.b.h().a(f2, f3, 0, z2);
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void a(int i2) {
        super.a(i2);
        if (this.f17597m || this.b == null) {
            return;
        }
        ma.a(la.f16819f, "setIndoorConfigType:" + i2);
        this.b.i(i2);
    }

    public void a(int i2, float f2) {
        this.b.h().a(i2, f2, true);
    }

    public void a(int i2, int i3) {
        this.b.a(i2, i3);
    }

    @Deprecated
    public void a(int i2, int i3, int i4) {
        this.b.b(i2, i3, i4);
    }

    public void a(int i2, int i3, int i4, int i5) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(i2, i3, i4, i5);
        }
    }

    public void a(int i2, GeoPoint geoPoint, Runnable runnable, i8 i8Var) {
        this.b.h().b(geoPoint, i2, runnable);
    }

    public void a(Rect rect) {
        this.b.h().a(rect);
    }

    public void a(Rect rect, int i2, int i3, j5 j5Var) {
        qc qcVar = this.b;
        if (rect == null) {
            rect = w();
        }
        qcVar.a(rect, i2, i3, j5Var);
    }

    public void a(Rect rect, Rect rect2, Runnable runnable, i8 i8Var) {
        this.b.h().a(rect, rect2, runnable, i8Var);
    }

    public void a(GeoPoint geoPoint, float f2, float f3, boolean z2) {
        this.s = f2;
        this.t.setLatitudeE6(geoPoint.getLatitudeE6());
        this.t.setLongitudeE6(geoPoint.getLongitudeE6());
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(this.t, this.s, f3, z2);
            int i2 = this.u;
            if (i2 == 1 || i2 == 2) {
                a(this.t, (Runnable) null);
            }
            if (this.u != 2 || b0()) {
                return;
            }
            a(this.s);
        }
    }

    public void a(GeoPoint geoPoint, float f2, Runnable runnable) {
        this.b.h().a(geoPoint, f2, runnable);
    }

    public void a(GeoPoint geoPoint, int i2, Runnable runnable) {
        this.b.h().a(geoPoint, i2, runnable);
    }

    public void a(GeoPoint geoPoint, Rect rect) {
        this.b.h().a(geoPoint, rect);
    }

    public void a(GeoPoint geoPoint, RectF rectF, boolean z2) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(geoPoint, rectF, z2);
        }
    }

    public void a(GeoPoint geoPoint, i8 i8Var) {
        this.b.h().a(geoPoint, i8Var);
    }

    public void a(GeoPoint geoPoint, Runnable runnable) {
        b(geoPoint, V(), runnable);
    }

    public void a(GeoPoint geoPoint, Runnable runnable, i8 i8Var) {
        this.b.h().a(geoPoint, runnable, i8Var);
    }

    public void a(a5 a5Var) {
        this.b.h().a(a5Var);
    }

    public void a(b1 b1Var) {
        this.b.h().a(b1Var);
    }

    public void a(c5 c5Var) {
        this.b.a(c5Var);
    }

    public void a(ce ceVar) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(ceVar);
        }
    }

    public void a(d0.h hVar) {
        this.b.h().a(hVar);
    }

    public void a(d5 d5Var) {
        this.b.h().a(d5Var);
    }

    public void a(e5 e5Var) {
        this.b.h().a(e5Var);
    }

    public void a(f5 f5Var) {
        this.b.h().a(f5Var);
    }

    public void a(g5 g5Var) {
        this.b.h().a(g5Var);
    }

    public void a(i5 i5Var) {
        this.b.h().a(i5Var);
    }

    public void a(k5 k5Var) {
        this.b.h().a(k5Var);
    }

    public void a(ke keVar) {
        this.b.a(keVar);
    }

    public void a(l5 l5Var) {
        this.b.h().a(l5Var);
    }

    public void a(me meVar) {
        this.b.a(meVar);
    }

    public void a(oe oeVar) {
        qc qcVar = this.b;
        if (qcVar == null) {
            return;
        }
        qcVar.a(oeVar);
    }

    public void a(se seVar) {
        this.b.h().a(seVar);
    }

    public void a(t4 t4Var) {
        this.b.g().a(t4Var);
        this.b.K();
    }

    public void a(t4 t4Var, int i2, int i3, j5 j5Var) {
        this.b.a(t4Var, i2, i3, j5Var);
    }

    public synchronized void a(t4 t4Var, t4 t4Var2) {
        this.b.g().a(t4Var, t4Var2);
        this.b.K();
    }

    public void a(u4 u4Var) {
        this.b.g().a(u4Var);
    }

    public void a(v4 v4Var) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.a(v4Var);
        }
    }

    public void a(w4 w4Var) {
        this.b.g().a(w4Var);
    }

    public void a(x4 x4Var) {
        this.b.g().a(x4Var);
    }

    public void a(xf xfVar) {
        this.b.h().a(xfVar);
    }

    public void a(y4 y4Var) {
        this.b.h().a(y4Var);
    }

    public void a(z4 z4Var) {
        this.b.g().a(z4Var);
    }

    public void a(z8 z8Var) {
        this.b.h().b(z8Var);
    }

    public void a(zg zgVar) {
        this.b.a(zgVar);
    }

    public void a(Language language) {
        this.b.a(language);
    }

    public void a(LatLngBounds latLngBounds, int i2) {
        qc qcVar = this.b;
        if (qcVar == null) {
            return;
        }
        qcVar.a(latLngBounds, i2);
    }

    public void a(Runnable runnable) {
        this.b.h().a(runnable);
    }

    public void a(List<MapRouteSection> list, List<GeoPoint> list2) {
        this.b.a(list, list2);
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void a(boolean z2) {
        h1 h1Var;
        if (this.f17597m || (h1Var = this.d) == null) {
            return;
        }
        h1Var.d(z2);
    }

    public void a(String[] strArr) {
        this.b.a(strArr);
    }

    public boolean a(String str, byte[] bArr) {
        w a0 = this.b.a0();
        if (a0 == null) {
            return false;
        }
        return a0.a(str, bArr);
    }

    public int a0() {
        return this.b.h().l().w();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public AoiLayer addAoiLayer(String str, AoiLayerOptions aoiLayerOptions, AoiLayer.OnAoiLayerLoadListener onAoiLayerLoadListener) {
        jf jfVar;
        if (this.f17597m || (jfVar = this.f17596l) == null) {
            return null;
        }
        AoiLayer a2 = jfVar.a(str, aoiLayerOptions, onAoiLayerLoadListener);
        v0();
        return a2;
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Arc addArc(ArcOptions arcOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null || arcOptions == null) {
            return null;
        }
        return qcVar.Z().a(arcOptions);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Circle addCircle(CircleOptions circleOptions) {
        qc qcVar;
        if (this.f17597m || circleOptions == null || (qcVar = this.b) == null) {
            return null;
        }
        return qcVar.Z().a(circleOptions);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public CustomLayer addCustomLayer(CustomLayerOptions customLayerOptions) {
        a0 a0Var;
        super.addCustomLayer(customLayerOptions);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.a(customLayerOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null || groundOverlayOptions == null) {
            return null;
        }
        GroundOverlay a2 = qcVar.a(groundOverlayOptions);
        v0();
        return a2;
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Marker addMarker(MarkerOptions markerOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null || markerOptions == null) {
            return null;
        }
        return qcVar.Z().a(markerOptions);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void addOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        a0 a0Var;
        super.addOnMapLoadedCallback(onMapLoadedCallback);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onMapLoadedCallback);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Polygon addPolygon(PolygonOptions polygonOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null || polygonOptions == null) {
            return null;
        }
        return qcVar.Z().a(polygonOptions);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Polyline addPolyline(PolylineOptions polylineOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null || polylineOptions == null) {
            return null;
        }
        return qcVar.Z().a(polylineOptions);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void addTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        a0 a0Var;
        super.addTencentMapGestureListener(tencentMapGestureListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        a0 a0Var;
        super.addTileOverlay(tileOverlayOptions);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.a(tileOverlayOptions);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public VectorHeatOverlay addVectorHeatOverlay(VectorHeatOverlayOptions vectorHeatOverlayOptions) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null) {
            return null;
        }
        VectorHeatOverlay a2 = qcVar.a(vectorHeatOverlayOptions);
        v0();
        return a2;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public <L extends VectorOverlay> L addVectorOverlay(VectorOverlayProvider vectorOverlayProvider) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null) {
            return null;
        }
        L l2 = (L) qcVar.a(vectorOverlayProvider);
        v0();
        return l2;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public VisualLayer addVisualLayer(VisualLayerOptions visualLayerOptions) {
        y3 y3Var = (y3) getMapComponent(y3.class);
        if (y3Var != null) {
            return y3Var.a(visualLayerOptions);
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void animateCamera(CameraUpdate cameraUpdate) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null || a0Var.m()) {
            return;
        }
        this.f17593i.a(cameraUpdate, 500L, (TencentMap.CancelableCallback) null);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void animateCamera(CameraUpdate cameraUpdate, long j2, TencentMap.CancelableCallback cancelableCallback) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null || a0Var.m()) {
            return;
        }
        this.f17593i.a(cameraUpdate, j2, cancelableCallback);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void animateCamera(CameraUpdate cameraUpdate, TencentMap.CancelableCallback cancelableCallback) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null || a0Var.m()) {
            return;
        }
        this.f17593i.a(cameraUpdate, 500L, cancelableCallback);
    }

    public cg b(GeoPoint geoPoint) {
        return this.b.S().b(geoPoint);
    }

    public void b(double d) {
        this.b.h().n(d);
    }

    public void b(float f2) {
        this.b.h().a(f2);
    }

    public void b(float f2, float f3, boolean z2) {
        this.b.h().a(f2, f3, z2);
    }

    public void b(int i2) {
        this.b.h().a(i2);
    }

    public void b(int i2, float f2) {
        this.b.a(i2, f2);
    }

    public void b(int i2, int i3) {
        this.b.b(i2, i3);
    }

    public void b(Rect rect) {
        this.b.h().b(rect);
    }

    public void b(Rect rect, Rect rect2) {
        this.b.h().c(rect, rect2);
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void b(Bundle bundle) {
        super.b(bundle);
        this.r = (ej) this.b.j();
        sh.b().a(this.b.getContext());
        k1.a().a(this.b.V());
        h1 h1Var = new h1(this.b, this.r.getMapRenderView(), this.b.r());
        this.d = h1Var;
        this.f17591g = new f0(h1Var);
        this.f17593i = new a0(this.d);
        this.q = new TencentMapPro(this.d, this.f17591g);
        this.f17596l = new jf(this.b);
        this.f17595k = new e0(this.d.n());
        this.d.a(new a());
        this.d.a(this.b.g(), this.b.r());
        lf lfVar = new lf(this.d);
        this.f17589e = lfVar;
        lfVar.a(this.b.g(), this.b.r());
        this.f17589e.b();
        addOnMapLoadedCallback(this.b.r().getOnMapLoadedCallback());
        this.p = true;
    }

    public void b(GeoPoint geoPoint, int i2, Runnable runnable) {
        this.b.h().c(geoPoint, i2, runnable);
    }

    public void b(PolygonInfo polygonInfo) {
        this.b.b(polygonInfo);
    }

    public void b(a5 a5Var) {
        this.b.h().b(a5Var);
    }

    public void b(b1 b1Var) {
        this.b.h().b(b1Var);
    }

    public void b(c5 c5Var) {
        this.b.b(c5Var);
    }

    public void b(d5 d5Var) {
        this.b.h().b(d5Var);
    }

    public void b(e5 e5Var) {
        this.b.h().b(e5Var);
    }

    public void b(f5 f5Var) {
        this.b.h().b(f5Var);
    }

    public void b(g5 g5Var) {
        this.b.h().b(g5Var);
    }

    public void b(i5 i5Var) {
        this.b.h().b(i5Var);
    }

    public void b(k5 k5Var) {
        this.b.h().b(k5Var);
    }

    public void b(l5 l5Var) {
        this.b.h().b(l5Var);
    }

    public void b(me meVar) {
        this.b.b(meVar);
    }

    public void b(se seVar) {
        this.b.h().b(seVar);
    }

    public void b(t4 t4Var) {
        this.b.g().b(t4Var);
        this.b.K();
    }

    public synchronized void b(t4 t4Var, t4 t4Var2) {
        this.b.g().b(t4Var, t4Var2);
        this.b.K();
    }

    public void b(v4 v4Var) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.b(v4Var);
        }
    }

    public void b(w4 w4Var) {
        this.b.g().b(w4Var);
    }

    public void b(y4 y4Var) {
        this.b.h().b(y4Var);
    }

    public void b(z4 z4Var) {
        this.b.g().a((z4) null);
    }

    public void b(zg zgVar) {
        this.b.b(zgVar);
    }

    public void b(Runnable runnable) {
        this.b.h().b(runnable);
    }

    public void b(boolean z2) {
        this.f17588c = z2;
        this.b.e(z2);
    }

    public boolean b(String str) {
        return this.b.b(str);
    }

    public boolean b(String str, byte[] bArr) {
        w a0 = this.b.a0();
        if (a0 == null) {
            return false;
        }
        return a0.b(str, bArr);
    }

    public boolean b0() {
        return this.b.h().w();
    }

    public String c(GeoPoint geoPoint) {
        return this.b.b(geoPoint);
    }

    public void c(double d) {
        this.b.h().e(d);
    }

    public void c(float f2) {
        this.b.h().b(f2);
    }

    public void c(int i2) {
        this.b.c(i2);
    }

    public void c(t4 t4Var) {
        this.b.g().c(t4Var);
        this.b.K();
    }

    public void c(String str) {
        this.b.d(str);
    }

    public void c(boolean z2) {
        this.a = z2;
    }

    public boolean c0() {
        return this.b.g0();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public CameraPosition calculateZoomToSpanLevel(List<IOverlay> list, List<LatLng> list2, int i2, int i3, int i4, int i5) {
        ArrayList arrayList = null;
        if (this.f17597m || this.f17593i == null) {
            return null;
        }
        int abs = Math.abs(i2);
        int abs2 = Math.abs(i3);
        int abs3 = Math.abs(i4);
        int abs4 = Math.abs(i5);
        if (list != null) {
            arrayList = new ArrayList();
            for (IOverlay iOverlay : list) {
                if (iOverlay instanceof t4) {
                    arrayList.add((t4) iOverlay);
                }
            }
        }
        return this.f17593i.a(arrayList, list2, abs, abs2, abs3, abs4);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clear() {
        super.clear();
        clearAllOverlays();
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clearAllOverlays() {
        ej ejVar;
        super.clearAllOverlays();
        if (this.f17597m || (ejVar = this.r) == null) {
            return;
        }
        ejVar.E();
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void clearCache() {
        super.clearCache();
        fa.e(lc.b(getMapContext().getContext()).b());
    }

    public void clearRouteNameSegments() {
        this.b.I();
    }

    public float d(int i2) {
        return this.b.h().l().a(i2);
    }

    public void d(float f2) {
        this.b.h().c(f2);
    }

    public void d(int i2, int i3) {
        this.b.h().a(i2, i3);
    }

    public void d(t4 t4Var) {
        this.b.g().d(t4Var);
        this.b.K();
    }

    public void d(String str) {
        this.b.S().i(str);
    }

    public void d(boolean z2) {
        this.b.h(z2);
    }

    public boolean d(float f2, float f3) {
        return b(f2, f3) || c(f2, f3) || a(f2, f3);
    }

    public boolean d(GeoPoint geoPoint) {
        return this.b.h().b(geoPoint);
    }

    public TappedElement e(float f2, float f3) {
        return this.b.a(f2, f3);
    }

    @Deprecated
    public void e(int i2) {
        this.b.e(i2);
    }

    public void e(int i2, int i3) {
        this.b.h().b(i2, i3);
    }

    public void e(String str) {
        this.b.f(str);
    }

    public void e(boolean z2) {
        this.b.S().j(z2);
    }

    public boolean e0() {
        return this.b.h().B();
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void enableAutoMaxSkew(boolean z2) {
        qc qcVar;
        super.enableAutoMaxSkew(z2);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.b().a(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void enableMultipleInfowindow(boolean z2) {
        a0 a0Var;
        super.enableMultipleInfowindow(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(z2);
    }

    public void f(float f2, float f3) {
        this.b.h().a(f2, f3, 0, true);
    }

    public void f(int i2) {
        this.b.f(i2);
    }

    public void f(int i2, int i3) {
        this.b.c(i2, i3);
    }

    public void f(String str) {
        this.b.g(str);
    }

    public void f(boolean z2) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.q(z2);
        }
    }

    public boolean f0() {
        return this.a;
    }

    public void g(float f2, float f3) {
        this.b.h().a(f2, f3, true);
    }

    @Deprecated
    public void g(int i2) {
        this.b.g(i2);
    }

    public void g(int i2, int i3) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.e(i2, i3);
        }
    }

    public void g(GeoPoint geoPoint) {
        if (f(geoPoint)) {
            this.b.h().a(geoPoint);
        }
    }

    public void g(boolean z2) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.s(z2);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1
    public boolean g() {
        return (!this.p || this.f17598n || this.f17597m) ? false : true;
    }

    public boolean g0() {
        return this.b.j0();
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public String getActivedIndoorBuilding(LatLng latLng) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.b(latLng);
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public String[] getActivedIndoorFloorNames() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.e();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public List<LatLng> getBounderPoints(Marker marker) {
        qc qcVar;
        w0 w0Var;
        if (marker == null || (qcVar = this.b) == null || (w0Var = (w0) qcVar.Z().a(marker.getId(), w0.class)) == null) {
            return null;
        }
        return w0Var.x().v();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public CameraPosition getCameraPosition() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.b();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public String getCityName(LatLng latLng) {
        a0 a0Var;
        return (this.f17597m || (a0Var = this.f17593i) == null) ? "" : a0Var.a(latLng);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public String getDebugError() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return null;
        }
        return a0Var.c();
    }

    @Override // com.tencent.map.sdk.comps.indoor.IIndoor
    public int getIndoorFloorId() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return -1;
        }
        return a0Var.d();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public MapLanguage getLanguage() {
        return this.b.T().ordinal() != 1 ? MapLanguage.LAN_CHINESE : MapLanguage.LAN_ENGLISH;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public TencentMapContext getMapContext() {
        return this.b;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public int getMapHeight() {
        return this.b.j().getMapRenderView().getHeight();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public Rect getMapPadding() {
        return this.b.h().k();
    }

    @Keep
    public TencentMapPro getMapPro() {
        return this.q;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public int getMapStyle() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return 0;
        }
        return a0Var.g();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public int getMapType() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return 1000;
        }
        return a0Var.h();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public int getMapWidth() {
        return this.b.j().getMapRenderView().getWidth();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public float getMaxZoomLevel() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return 0.0f;
        }
        return a0Var.i();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public float getMinZoomLevel() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return 0.0f;
        }
        return a0Var.j();
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public Location getMyLocation() {
        if (this.f17597m) {
            return null;
        }
        d0();
        return this.f17594j.d();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public UiSettings getUiSettings() {
        if (this.f17597m) {
            return null;
        }
        if (this.o == null) {
            this.o = new dj(this.f17595k);
        }
        return this.o;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public String getVersion() {
        a0 a0Var;
        return (this.f17597m || (a0Var = this.f17593i) == null) ? "" : a0Var.k();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return 0.0f;
        }
        if (latLng == null || latLng2 == null) {
            return -1.0f;
        }
        return a0Var.a(latLng, latLng2);
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void h() {
        super.h();
        if (this.f17597m) {
            return;
        }
        a0 a0Var = this.f17593i;
        if (a0Var != null) {
            a0Var.o();
        }
        lf lfVar = this.f17589e;
        if (lfVar != null) {
            lfVar.a();
        }
        jf jfVar = this.f17596l;
        if (jfVar != null) {
            jfVar.c();
        }
        sh.b().c();
        r0();
        s0();
        this.f17597m = true;
    }

    public void h(float f2, float f3) {
        qc qcVar = this.b;
        if (qcVar != null) {
            qcVar.h().b(f2, f3);
        }
    }

    public void h(int i2) {
        this.b.h().g().b(i2);
    }

    @Deprecated
    public void h(int i2, int i3) {
        this.b.g(i2, i3);
    }

    public void h(GeoPoint geoPoint) {
        this.b.h().b(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
    }

    public void h(boolean z2) {
        this.b.j(z2);
    }

    public boolean h0() {
        return this.f17588c;
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void i() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.p();
        this.f17598n = true;
    }

    public void i(int i2) {
        this.b.k(i2);
    }

    public void i(int i2, int i3) {
        this.b.i(i2, i3);
    }

    public void i(boolean z2) {
        this.b.l(z2);
    }

    public boolean i0() {
        return this.b.d0().c();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public boolean isBlockRouteEnabled() {
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public boolean isDestroyed() {
        return this.f17597m;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public boolean isHandDrawMapEnable() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return false;
        }
        return a0Var.l();
    }

    @Override // com.tencent.map.sdk.comps.mylocation.IMyLocation
    public boolean isMyLocationEnabled() {
        if (this.f17597m) {
            return false;
        }
        d0();
        return this.f17594j.e();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public boolean isSateLiteEnable() {
        return getMapType() == 1011;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public boolean isTrafficEnabled() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return false;
        }
        return a0Var.n();
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void j() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.q();
    }

    public void j(int i2) {
        qc qcVar = this.b;
        if (qcVar == null) {
            return;
        }
        if (i2 != 0) {
            if (i2 == 1) {
                qcVar.a(false, false, false, false);
                int i3 = (getMapStyle() != 11 ? 18 : 17) - 1;
                if (this.u == 2) {
                    b(i3);
                } else {
                    int V = V();
                    if (V > i3) {
                        i3 = V;
                    }
                    b(this.t, i3, (Runnable) null);
                }
            } else if (i2 == 2) {
                int i4 = getMapStyle() != 11 ? 18 : 17;
                int V2 = V();
                if (V2 > i4) {
                    i4 = V2;
                }
                if (this.t.getLatitudeE6() != 0) {
                    this.b.h().a(this.t.getLatitudeE6(), this.t.getLongitudeE6(), 2);
                }
                a(i4, this.s);
                new Handler().postDelayed(new b(), 1000L);
            } else if (i2 == 3) {
                qcVar.a(false, false, false, true);
            }
        } else {
            qcVar.a(false, false, false, false);
        }
        this.u = i2;
    }

    public void j(boolean z2) {
        this.b.n(z2);
    }

    public boolean j0() {
        return this.b.o0();
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void k() {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.r();
        this.f17598n = false;
    }

    public void k(int i2) {
        this.b.n(i2);
    }

    public void k(boolean z2) {
        if (z2) {
            this.b.B0();
        } else {
            this.b.h0();
        }
    }

    public void k0() {
        this.b.p0();
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void l() {
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        jf jfVar = this.f17596l;
        if (jfVar != null) {
            this.r.b(jfVar);
        }
        this.f17593i.s();
    }

    public void l(int i2) {
        this.b.o(i2);
    }

    public void l(boolean z2) {
        this.b.d0().a(z2);
    }

    public void l0() {
        this.b.h().G();
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void loadKMLFile(String str) {
        a0 a0Var;
        super.loadKMLFile(str);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.b(str);
    }

    @Override // com.tencent.mapsdk.internal.m1
    public void m() {
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        jf jfVar = this.f17596l;
        if (jfVar != null) {
            this.r.a(jfVar);
        }
        this.f17593i.t();
    }

    public void m(int i2) {
        this.b.h().e(i2);
    }

    public void m(boolean z2) {
        this.b.u(z2);
    }

    public void m0() {
        this.b.h().f16378g.h();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void moveCamera(CameraUpdate cameraUpdate) {
        a0 a0Var;
        if (this.f17597m || (a0Var = this.f17593i) == null || a0Var.m()) {
            return;
        }
        this.f17593i.a(cameraUpdate);
    }

    public void n(int i2) {
        this.b.p(i2);
    }

    public boolean n() {
        return true;
    }

    public void n0() {
        this.b.v0();
    }

    public void o() {
        this.b.h().e();
    }

    public void o(int i2) {
        this.b.h().e(i2);
    }

    public void o0() {
        this.b.h().K();
    }

    public void p() {
        this.b.H();
    }

    public void p0() {
        this.b.h().L();
    }

    public v q() {
        return this.b.h().f();
    }

    public void q0() {
        this.b.h().M();
    }

    public String r() {
        return this.b.toString();
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void removeOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        a0 a0Var;
        super.removeOnMapLoadedCallback(onMapLoadedCallback);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.b(onMapLoadedCallback);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void removeTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        a0 a0Var;
        super.removeTencentMapGestureListener(tencentMapGestureListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.b(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void resetIndoorCellInfo() {
        super.resetIndoorCellInfo();
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        this.b.x0();
    }

    public GeoPoint s() {
        return this.b.h().h();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBaseMapEnabled(boolean z2) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.c(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuilding3dEffectEnable(boolean z2) {
        qc qcVar;
        super.setBuilding3dEffectEnable(z2);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.g(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuildingBlackList(List<LatLngBounds> list) {
        super.setBuildingBlackList(list);
        this.b.a(list);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setBuildingEnable(boolean z2) {
        super.setBuildingEnable(z2);
        setBuilding3dEffectEnable(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCameraCenterProportion(float f2, float f3) {
        super.setCameraCenterProportion(f2, f3);
        setCameraCenterProportion(f2, f3, true);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCameraCenterProportion(float f2, float f3, boolean z2) {
        a0 a0Var;
        super.setCameraCenterProportion(f2, f3, z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(f2, f3, z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setCustomRender(CustomRender customRender) {
        qc qcVar;
        super.setCustomRender(customRender);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.a(customRender);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setDrawPillarWith2DStyle(boolean z2) {
        qc qcVar;
        super.setDrawPillarWith2DStyle(z2);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.g(!z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setForeignLanguage(Language language) {
        a0 a0Var;
        super.setForeignLanguage(language);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(language);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setHandDrawMapEnable(boolean z2) {
        a0 a0Var;
        super.setHandDrawMapEnable(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.b(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorCellInfo(List<IndoorCellInfo> list) {
        super.setIndoorCellInfo(list);
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        this.b.b(list);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorEnabled(boolean z2) {
        a0 a0Var;
        super.setIndoorEnabled(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.c(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorFloor(int i2) {
        a0 a0Var;
        super.setIndoorFloor(i2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.b(i2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorFloor(String str, String str2) {
        a0 a0Var;
        super.setIndoorFloor(str, str2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(str, str2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setIndoorMaskColor(int i2) {
        super.setIndoorMaskColor(i2);
        this.b.S().j(i2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setInfoWindowAdapter(TencentMap.InfoWindowAdapter infoWindowAdapter) {
        ej ejVar;
        super.setInfoWindowAdapter(infoWindowAdapter);
        if (this.f17597m || (ejVar = this.r) == null) {
            return;
        }
        ejVar.a(infoWindowAdapter);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setLocationCompassHidden(boolean z2) {
        super.setLocationCompassHidden(z2);
        this.b.m(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setLocationNavigationGravityLineHidden(boolean z2) {
        super.setLocationNavigationGravityLineHidden(z2);
        this.b.o(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setLocationSource(LocationSource locationSource) {
        super.setLocationSource(locationSource);
        if (this.f17597m) {
            return;
        }
        d0();
        this.f17594j.a(locationSource);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapCenterAndScale(float f2, float f3, float f4) {
        h1 h1Var;
        super.setMapCenterAndScale(f2, f3, f4);
        if (this.f17597m || (h1Var = this.d) == null) {
            return;
        }
        h1Var.a(f2, f3, f4);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapFontSize(MapFontSize mapFontSize) {
        if (this.f17597m || this.f17593i == null || mapFontSize == null) {
            return;
        }
        this.b.l(mapFontSize.getValue());
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapFrameRate(float f2) {
        super.setMapFrameRate(f2);
        ej ejVar = this.r;
        if (ejVar != null) {
            ejVar.a(f2);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapStyle(int i2) {
        a0 a0Var;
        super.setMapStyle(i2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.c(i2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMapType(int i2) {
        a0 a0Var;
        super.setMapType(i2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.d(i2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMaxZoomLevel(int i2) {
        super.setMaxZoomLevel(i2);
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        int p = p(i2);
        this.f17593i.e(p);
        float f2 = p;
        if (this.f17593i.b().zoom > f2) {
            animateCamera(CameraUpdateFactory.zoomTo(f2));
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setMinZoomLevel(int i2) {
        super.setMinZoomLevel(i2);
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        int p = p(i2);
        this.f17593i.f(p);
        float f2 = p;
        if (this.f17593i.b().zoom < f2) {
            animateCamera(CameraUpdateFactory.zoomTo(f2));
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationClickListener(TencentMap.OnMyLocationClickListener onMyLocationClickListener) {
        super.setMyLocationClickListener(onMyLocationClickListener);
        if (this.f17597m) {
            return;
        }
        if (this.f17590f == null) {
            d0();
        }
        this.f17590f.a(onMyLocationClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationEnabled(boolean z2) {
        super.setMyLocationEnabled(z2);
        if (this.f17597m) {
            return;
        }
        d0();
        if (!z2) {
            this.f17594j.a();
        } else if (isMyLocationEnabled()) {
        } else {
            this.f17594j.b();
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        super.setMyLocationStyle(myLocationStyle);
        if (this.f17594j == null) {
            d0();
        }
        this.f17594j.a(myLocationStyle);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnCameraChangeListener(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        a0 a0Var;
        super.setOnCameraChangeListener(onCameraChangeListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onCameraChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnCompassClickedListener(TencentMap.OnCompassClickedListener onCompassClickedListener) {
        a0 a0Var;
        super.setOnCompassClickedListener(onCompassClickedListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onCompassClickedListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.indoor.IIndoor
    public void setOnIndoorStateChangeListener(TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        a0 a0Var;
        super.setOnIndoorStateChangeListener(onIndoorStateChangeListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onIndoorStateChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnInfoWindowClickListener(TencentMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        a0 a0Var;
        super.setOnInfoWindowClickListener(onInfoWindowClickListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onInfoWindowClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapClickListener(TencentMap.OnMapClickListener onMapClickListener) {
        a0 a0Var;
        super.setOnMapClickListener(onMapClickListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onMapClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapLongClickListener(TencentMap.OnMapLongClickListener onMapLongClickListener) {
        a0 a0Var;
        super.setOnMapLongClickListener(onMapLongClickListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onMapLongClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMapPoiClickListener(TencentMap.OnMapPoiClickListener onMapPoiClickListener) {
        super.setOnMapPoiClickListener(onMapPoiClickListener);
        if (this.f17597m || this.f17593i == null) {
            return;
        }
        jf jfVar = this.f17596l;
        if (jfVar != null) {
            jfVar.a(onMapPoiClickListener);
        }
        this.f17593i.a(onMapPoiClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMarkerClickListener(TencentMap.OnMarkerClickListener onMarkerClickListener) {
        a0 a0Var;
        super.setOnMarkerClickListener(onMarkerClickListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onMarkerClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnMarkerDragListener(TencentMap.OnMarkerDragListener onMarkerDragListener) {
        qc qcVar;
        super.setOnMarkerDragListener(onMarkerDragListener);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.g().a(onMarkerDragListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.map.sdk.comps.mylocation.IMyLocation
    public void setOnMyLocationChangeListener(TencentMap.OnMyLocationChangeListener onMyLocationChangeListener) {
        super.setOnMyLocationChangeListener(onMyLocationChangeListener);
        if (this.f17597m) {
            return;
        }
        if (this.f17590f == null) {
            d0();
        }
        this.f17590f.a(onMyLocationChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnPolygonClickListener(TencentMap.OnPolygonClickListener onPolygonClickListener) {
        ej ejVar;
        super.setOnPolygonClickListener(onPolygonClickListener);
        if (this.f17597m || (ejVar = this.r) == null) {
            return;
        }
        ejVar.a(onPolygonClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnPolylineClickListener(TencentMap.OnPolylineClickListener onPolylineClickListener) {
        ej ejVar;
        super.setOnPolylineClickListener(onPolylineClickListener);
        if (this.f17597m || (ejVar = this.r) == null) {
            return;
        }
        ejVar.a(onPolylineClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnScaleViewChangedListener(TencentMap.OnScaleViewChangedListener onScaleViewChangedListener) {
        a0 a0Var;
        super.setOnScaleViewChangedListener(onScaleViewChangedListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onScaleViewChangedListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnTapMapViewInfoWindowHidden(boolean z2) {
        a0 a0Var;
        super.setOnTapMapViewInfoWindowHidden(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.f(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnTrafficEventClickListener(TencentMap.OnTrafficEventClickListener onTrafficEventClickListener) {
        a0 a0Var;
        super.setOnTrafficEventClickListener(onTrafficEventClickListener);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(onTrafficEventClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOnVectorOverlayClickListener(TencentMap.OnVectorOverlayClickListener onVectorOverlayClickListener) {
        ej ejVar;
        super.setOnVectorOverlayClickListener(onVectorOverlayClickListener);
        if (this.f17597m || (ejVar = this.r) == null) {
            return;
        }
        ejVar.a(onVectorOverlayClickListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOverSeaEnable(boolean z2) {
        super.setOverSeaEnable(z2);
        ej ejVar = this.r;
        if (ejVar != null) {
            ejVar.k(z2);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setOverSeaTileProvider(OverSeaTileProvider overSeaTileProvider) {
        qc qcVar;
        super.setOverSeaTileProvider(overSeaTileProvider);
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.a(overSeaTileProvider);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPadding(int i2, int i3, int i4, int i5) {
        super.setPadding(i2, i3, i4, i5);
        h1 h1Var = this.d;
        if (h1Var != null) {
            h1Var.a(i2, i3, i4, i5, false);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPadding(int i2, int i3, int i4, int i5, boolean z2) {
        super.setPadding(i2, i3, i4, i5, z2);
        h1 h1Var = this.d;
        if (h1Var != null) {
            h1Var.a(i2, i3, i4, i5, z2);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPointToCenter(int i2, int i3) {
        s4 projection;
        super.setPointToCenter(i2, i3);
        if (this.f17597m || this.f17593i == null || (projection = getProjection()) == null) {
            return;
        }
        this.f17593i.a(CameraUpdateFactory.newLatLng(projection.fromScreenLocation(new Point(i2, i3))));
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setPoisEnabled(boolean z2) {
        a0 a0Var;
        super.setPoisEnabled(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.g(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setRestrictBounds(LatLngBounds latLngBounds, RestrictBoundsFitMode restrictBoundsFitMode) {
        a0 a0Var;
        super.setRestrictBounds(latLngBounds, restrictBoundsFitMode);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.a(latLngBounds, restrictBoundsFitMode == null ? 0 : restrictBoundsFitMode.ordinal());
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setSatelliteEnabled(boolean z2) {
        super.setSatelliteEnabled(z2);
        setMapType(z2 ? 1011 : 1000);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        super.setTencentMapGestureListener(tencentMapGestureListener);
        addTencentMapGestureListener(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTrafficEnabled(boolean z2) {
        a0 a0Var;
        super.setTrafficEnabled(z2);
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.h(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void setTrafficMode(int i2, int i3) {
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void showBuilding(boolean z2) {
        super.showBuilding(z2);
        this.b.d(z2);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback) {
        super.snapshot(snapshotReadyCallback);
        snapshot(snapshotReadyCallback, Bitmap.Config.ARGB_8888);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback, Bitmap.Config config) {
        super.snapshot(snapshotReadyCallback, config);
        snapshot(snapshotReadyCallback, config, 0);
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void snapshot(TencentMap.SnapshotReadyCallback snapshotReadyCallback, Bitmap.Config config, int i2) {
        super.snapshot(snapshotReadyCallback, config, i2);
        a0 a0Var = this.f17593i;
        if (a0Var != null) {
            a0Var.a(snapshotReadyCallback, config, i2);
        }
    }

    @Override // com.tencent.mapsdk.internal.m1, com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void stopAnimation() {
        a0 a0Var;
        super.stopAnimation();
        if (this.f17597m || (a0Var = this.f17593i) == null) {
            return;
        }
        a0Var.u();
    }

    public String[] t() {
        return this.b.N();
    }

    public void t0() {
        w a0 = this.b.a0();
        if (a0 == null) {
            return;
        }
        a0.c();
    }

    public String u() {
        return this.b.O();
    }

    public void u0() {
        this.b.h().a();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap
    public void updateVectorOverlay(VectorOverlay vectorOverlay, VectorOverlayProvider vectorOverlayProvider) {
        qc qcVar;
        if (this.f17597m || (qcVar = this.b) == null) {
            return;
        }
        qcVar.a(vectorOverlay, vectorOverlayProvider);
        v0();
    }

    public int v() {
        return this.b.h().r();
    }

    public void v0() {
        this.b.w0();
    }

    public Rect w() {
        return this.b.h().i();
    }

    public void w0() {
        this.b.h().g().j();
    }

    public String x() {
        return this.b.P();
    }

    public void x0() {
        this.b.h().N();
    }

    public String y() {
        qc qcVar = this.b;
        if (qcVar != null) {
            return qcVar.n();
        }
        return null;
    }

    public void y0() {
        this.b.h().f16378g.k();
    }

    public long z() {
        return this.b.h().f16378g.e();
    }

    public void z0() {
        this.b.A0();
    }
}
