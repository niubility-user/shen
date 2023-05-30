package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.TextUtils;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.map.lib.models.AggregationOverlayInfo;
import com.tencent.map.lib.models.AnnocationText;
import com.tencent.map.lib.models.AnnocationTextResult;
import com.tencent.map.lib.models.ArcLineOverlayInfo;
import com.tencent.map.lib.models.CircleInfo;
import com.tencent.map.lib.models.CityTrafficInfo;
import com.tencent.map.lib.models.DataSource;
import com.tencent.map.lib.models.GLModelInfo;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.GroundOverlayInfo;
import com.tencent.map.lib.models.HeatmapInfo;
import com.tencent.map.lib.models.IndoorCellInfo;
import com.tencent.map.lib.models.IntersectionOverlayInfo;
import com.tencent.map.lib.models.MapTileID;
import com.tencent.map.lib.models.MarkerInfo;
import com.tencent.map.lib.models.MaskLayer;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.map.lib.models.ScatterPlotInfo;
import com.tencent.map.lib.models.TrailOverlayInfo;
import com.tencent.map.tools.CallbackRunnable;
import com.tencent.mapsdk.engine.jni.JNI;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.mapsdk.internal.fg;
import com.tencent.mapsdk.internal.qc;
import com.tencent.mapsdk.shell.events.EngineWriteDataModel;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficStyle;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatAggregationUnit;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class qi implements de, fe, ge, ke, me, oe, pe, com.tencent.mapsdk.internal.r1, com.tencent.mapsdk.internal.t1 {
    private static final int A = -11635864;
    private static final int B = -16777063;
    private static final int C = -16777063;
    public static final int D = 0;
    public static final int E = -1;
    public static final int F = -9;
    private static boolean G = false;
    private static final int t = 256;
    private static final int u = -14803236;
    private static final int v = -13752731;
    private static final int w = -15611905;
    private static final int x = -14650226;
    private static final int y = -11088785;
    private static final int z = -9906011;

    /* renamed from: g */
    private JNI f17075g;

    /* renamed from: h */
    private volatile long f17076h;

    /* renamed from: i */
    private be f17077i;

    /* renamed from: j */
    public jb f17078j;

    /* renamed from: k */
    private final ib f17079k;

    /* renamed from: l */
    private ke f17080l;

    /* renamed from: m */
    private qc f17081m;

    /* renamed from: n */
    private long f17082n;
    private long o;
    private float p;
    private String q;
    private final Object r;
    private final ReentrantLock s;

    /* loaded from: classes9.dex */
    public class a implements CallbackRunnable<Boolean> {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17083c;

        public a(boolean z, float f2, float f3) {
            qi.this = r1;
            this.a = z;
            this.b = f2;
            this.f17083c = f3;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            if (qi.this.f17076h == 0) {
                return Boolean.FALSE;
            }
            if (this.a) {
                qi.this.f17075g.nativeSetScreenCenterOffset(qi.this.f17076h, this.b, this.f17083c, true);
            } else {
                com.tencent.mapsdk.internal.s4 projection = qi.this.f17081m.getProjection();
                com.tencent.mapsdk.internal.o5 a = projection.a(qi.this.n());
                qi.this.f17075g.nativeSetScreenCenterOffset(qi.this.f17076h, this.b, this.f17083c, false);
                com.tencent.mapsdk.internal.o5 a2 = projection.a(qi.this.n());
                double d = a2.a;
                double d2 = a2.b;
                qi.this.f17075g.nativeSetCenter(qi.this.f17076h, projection.a(new com.tencent.mapsdk.internal.o5(d + (d - a.a), d2 + (d2 - a.b))), false);
            }
            return Boolean.TRUE;
        }
    }

    /* loaded from: classes9.dex */
    public class a0 implements CallbackRunnable<List<Integer>> {
        public final /* synthetic */ Rect a;
        public final /* synthetic */ int b;

        public a0(Rect rect, int i2) {
            qi.this = r1;
            this.a = rect;
            this.b = i2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public List<Integer> run() {
            ArrayList arrayList = null;
            if (0 == qi.this.f17076h) {
                return null;
            }
            int[] iArr = new int[100];
            int nativeQueryCityCodeList = qi.this.f17075g.nativeQueryCityCodeList(qi.this.f17076h, this.a, this.b, iArr, 100);
            if (nativeQueryCityCodeList > 0) {
                arrayList = new ArrayList(nativeQueryCityCodeList);
                for (int i2 = 0; i2 < nativeQueryCityCodeList; i2++) {
                    arrayList.add(Integer.valueOf(iArr[i2]));
                }
            }
            return arrayList;
        }
    }

    /* loaded from: classes9.dex */
    public class a1 implements CallbackRunnable<Long> {
        public final /* synthetic */ MarkerInfo a;

        public a1(MarkerInfo markerInfo) {
            qi.this = r1;
            this.a = markerInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddMarker2(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class a2 implements CallbackRunnable<Long> {
        public final /* synthetic */ GLModelInfo a;

        public a2(GLModelInfo gLModelInfo) {
            qi.this = r1;
            this.a = gLModelInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddGLModel(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class a3 implements qc.b {
        public final /* synthetic */ int a;

        public a3(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeRemoveMaskLayer(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class a4 implements CallbackRunnable<TappedElement> {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        public a4(float f2, float f3) {
            qi.this = r1;
            this.a = f2;
            this.b = f3;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public TappedElement run() {
            byte[] nativeOnTap;
            if (qi.this.f17076h == 0 || (nativeOnTap = qi.this.f17075g.nativeOnTap(qi.this.f17076h, this.a, this.b)) == null) {
                return null;
            }
            try {
                return TappedElement.fromBytes(nativeOnTap);
            } catch (Exception unused) {
                return null;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class a5 implements CallbackRunnable<cg> {
        public final /* synthetic */ GeoPoint a;

        public a5(GeoPoint geoPoint) {
            qi.this = r1;
            this.a = geoPoint;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public cg run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            return new cg(qi.this.f17075g.nativeGetActiveIndoorBuildingGUID(qi.this.f17076h), qi.this.f17075g.nativeGetCurIndoorName(qi.this.f17076h, this.a), qi.this.f17075g.nativeGetIndoorFloorNames(qi.this.f17076h), qi.this.f17075g.nativeGetIndoorCurrentFloorId(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class a6 implements qc.b {
        public final /* synthetic */ double a;

        public a6(double d) {
            qi.this = r1;
            this.a = d;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h || qi.this.f17081m == null) {
                return;
            }
            qi.this.f17075g.nativeSetScale(qi.this.f17076h, this.a, false);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17086c;
        public final /* synthetic */ int d;

        public b(int i2, int i3, int i4, int i5) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17086c = i4;
            this.d = i5;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetViewport(qi.this.f17076h, this.a, this.b, this.f17086c, this.d);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b0 implements qc.b {
        public final /* synthetic */ String a;

        public b0(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeMapLoadKMLFile(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class b1 implements CallbackRunnable<Integer> {
        public final /* synthetic */ long a;

        public b1(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeGetEngineId(this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class b2 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ GLModelInfo b;

        public b2(long j2, GLModelInfo gLModelInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = gLModelInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateGLModel(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class b3 implements CallbackRunnable<GeoPoint> {
        public final /* synthetic */ byte[] a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17089c;

        public b3(byte[] bArr, float f2, float f3) {
            qi.this = r1;
            this.a = bArr;
            this.b = f2;
            this.f17089c = f3;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public GeoPoint run() {
            if (qi.this.f17076h == 0) {
                return new GeoPoint();
            }
            double[] dArr = new double[2];
            qi.this.f17075g.nativeFromScreenLocation(qi.this.f17076h, this.a, this.b, this.f17089c, dArr);
            return new GeoPoint((int) (dArr[1] * 1000000.0d), (int) (dArr[0] * 1000000.0d));
        }
    }

    /* loaded from: classes9.dex */
    public class b4 implements CallbackRunnable<Boolean> {
        public b4() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h != 0 ? Boolean.valueOf(qi.this.f17075g.nativeNeedRedraw(qi.this.f17076h)) : Boolean.FALSE;
        }
    }

    /* loaded from: classes9.dex */
    public class b5 implements CallbackRunnable<Rect> {
        public b5() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Rect run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            return qi.this.f17075g.nativeGetIndoorBound(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class b6 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ boolean b;

        public b6(int i2, boolean z) {
            qi.this = r1;
            this.a = i2;
            this.b = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetScaleLevel(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c implements qc.b {
        public c() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            JNI jni;
            long j2;
            double d;
            if (0 == qi.this.f17076h) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (qi.this.f17082n == 0) {
                jni = qi.this.f17075g;
                j2 = qi.this.f17076h;
                d = 0.0d;
            } else {
                jni = qi.this.f17075g;
                j2 = qi.this.f17076h;
                d = elapsedRealtime - qi.this.f17082n;
            }
            jni.nativeUpdateFrame(j2, d);
            qi.this.f17082n = elapsedRealtime;
        }
    }

    /* loaded from: classes9.dex */
    public class c0 implements qc.b {
        public final /* synthetic */ int a;

        public c0(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetLanguage(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class c1 implements CallbackRunnable<CityTrafficInfo> {
        public final /* synthetic */ String a;

        public c1(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public CityTrafficInfo run() {
            if (qi.this.f17076h == 0) {
                return null;
            }
            CityTrafficInfo cityTrafficInfo = new CityTrafficInfo();
            qi.this.f17075g.nativeGetTrafficCityInfo(qi.this.f17076h, this.a, cityTrafficInfo);
            return cityTrafficInfo;
        }
    }

    /* loaded from: classes9.dex */
    public class c2 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ float f17091c;
        public final /* synthetic */ boolean d;

        public c2(long j2, int i2, float f2, boolean z) {
            qi.this = r1;
            this.a = j2;
            this.b = i2;
            this.f17091c = f2;
            this.d = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeStartGLModelSkeletonAnimation(qi.this.f17076h, this.a, this.b, this.f17091c, this.d);
        }
    }

    /* loaded from: classes9.dex */
    public class c3 implements CallbackRunnable<PointF> {
        public final /* synthetic */ byte[] a;
        public final /* synthetic */ double b;

        /* renamed from: c */
        public final /* synthetic */ double f17093c;

        public c3(byte[] bArr, double d, double d2) {
            qi.this = r1;
            this.a = bArr;
            this.b = d;
            this.f17093c = d2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public PointF run() {
            if (qi.this.f17076h == 0) {
                return new PointF();
            }
            float[] fArr = new float[2];
            qi.this.f17075g.nativeToScreenLocation(qi.this.f17076h, this.a, this.b, this.f17093c, fArr);
            return new PointF(fArr[0], fArr[1]);
        }
    }

    /* loaded from: classes9.dex */
    public class c4 implements qc.b {
        public c4() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeLockEngine(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class c5 implements CallbackRunnable<String> {
        public c5() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            if (qi.this.f17076h == 0) {
                return null;
            }
            return qi.this.f17075g.nativeGetActiveIndoorBuildingGUID(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class c6 implements qc.b {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        public c6(float f2, float f3) {
            qi.this = r1;
            this.a = f2;
            this.b = f3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeZoomIn(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d implements CallbackRunnable<Boolean> {
        public d() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return 0 == qi.this.f17076h ? Boolean.FALSE : Boolean.valueOf(qi.this.f17075g.nativeNeedDispaly(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class d0 implements CallbackRunnable<Integer> {
        public d0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeGetLanguage(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class d1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ MarkerInfo b;

        public d1(long j2, MarkerInfo markerInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = markerInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateMarker(this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class d2 implements qc.b {
        public final /* synthetic */ long a;

        public d2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeStopGLModelSkeletonAnimation(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class d3 implements CallbackRunnable<Boolean> {
        public final /* synthetic */ String a;

        public d3(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h == 0 ? Boolean.FALSE : Boolean.valueOf(qi.this.f17075g.nativeHasStreetRoad(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class d4 implements qc.b {
        public final /* synthetic */ boolean a;

        public d4(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 != qi.this.f17076h) {
                qi.this.f17075g.nativeSetBuilding3DEffect(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d5 implements CallbackRunnable<String> {
        public final /* synthetic */ GeoPoint a;

        public d5(GeoPoint geoPoint) {
            qi.this = r1;
            this.a = geoPoint;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            return qi.this.f17075g.nativeGetCurIndoorName(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class d6 implements qc.b {
        public d6() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeZoomOut(qi.this.f17076h);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e implements qc.b {
        public final /* synthetic */ boolean a;

        public e(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetNeedDisplay(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class e0 implements qc.b {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ String f17096c;

        public e0(String str, String str2, String str3) {
            qi.this = r1;
            this.a = str;
            this.b = str2;
            this.f17096c = str3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeResetEnginePath(qi.this.f17076h, this.a, this.b, this.f17096c);
        }
    }

    /* loaded from: classes9.dex */
    public class e1 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public e1(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetMarkerMainSubRelation(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class e2 implements CallbackRunnable<Integer> {
        public final /* synthetic */ long a;

        public e2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeGetGLModelSkeletonAnimationCount(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class e3 implements qc.b {
        public e3() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeShowStreetRoad(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class e4 implements qc.b {
        public final /* synthetic */ List a;

        public e4(List list) {
            qi.this = r1;
            this.a = list;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 != qi.this.f17076h) {
                if (this.a == null) {
                    qi.this.f17075g.nativeSetBuildingBlackList(qi.this.f17076h, null);
                } else {
                    qi.this.f17075g.nativeSetBuildingBlackList(qi.this.f17076h, (LatLngBounds[]) this.a.toArray(new LatLngBounds[0]));
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e5 implements qc.b {
        public final /* synthetic */ int a;

        public e5(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetIndoorMaskColor(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e6 implements qc.b {
        public final /* synthetic */ RectF a;
        public final /* synthetic */ GeoPoint b;

        /* renamed from: c */
        public final /* synthetic */ int f17098c;
        public final /* synthetic */ int d;

        /* renamed from: e */
        public final /* synthetic */ boolean f17099e;

        public e6(RectF rectF, GeoPoint geoPoint, int i2, int i3, boolean z) {
            qi.this = r1;
            this.a = rectF;
            this.b = geoPoint;
            this.f17098c = i2;
            this.d = i3;
            this.f17099e = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                JNI jni = qi.this.f17075g;
                long j2 = qi.this.f17076h;
                RectF rectF = this.a;
                jni.nativeSetFlagOfZoomToSpanForLocation(j2, rectF.top, rectF.left, rectF.bottom, rectF.right);
                qi.this.f17075g.nativeZoomToSpanForNavigation(qi.this.f17076h, this.b, this.f17098c, this.d, this.f17099e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f implements qc.b {
        public final /* synthetic */ int a;

        public f(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetMaxScaleLevel(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class f0 implements qc.b {
        public final /* synthetic */ boolean a;

        public f0(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSwitchEngineForeGround(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f1 implements qc.b {
        public final /* synthetic */ int a;

        public f1(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetMapFontSize(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class f2 implements CallbackRunnable<float[]> {
        public final /* synthetic */ long a;

        public f2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public float[] run() {
            return qi.this.f17076h == 0 ? new float[0] : qi.this.f17075g.nativeGetGLModelSkeletonAnimationDuration(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class f3 implements qc.b {
        public f3() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeHideStreetRoad(qi.this.f17076h);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f4 implements qc.b {
        public final /* synthetic */ GeoPoint a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17101c;
        public final /* synthetic */ boolean d;

        public f4(GeoPoint geoPoint, float f2, float f3, boolean z) {
            qi.this = r1;
            this.a = geoPoint;
            this.b = f2;
            this.f17101c = f3;
            this.d = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                JNI jni = qi.this.f17075g;
                long j2 = qi.this.f17076h;
                double latitudeE6 = this.a.getLatitudeE6();
                Double.isNaN(latitudeE6);
                double longitudeE6 = this.a.getLongitudeE6();
                Double.isNaN(longitudeE6);
                jni.nativeSetLocationInfo(j2, latitudeE6 / 1000000.0d, longitudeE6 / 1000000.0d, this.b, this.f17101c, this.d);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f5 implements qc.b {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17103c;
        public final /* synthetic */ float d;

        public f5(float f2, float f3, float f4, float f5) {
            qi.this = r1;
            this.a = f2;
            this.b = f3;
            this.f17103c = f4;
            this.d = f5;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetIndoorActiveScreenArea(qi.this.f17076h, this.a, this.b, this.f17103c, this.d);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f6 implements CallbackRunnable<Integer> {
        public final /* synthetic */ byte[] a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17105c;
        public final /* synthetic */ boolean d;

        public f6(byte[] bArr, int i2, boolean z, boolean z2) {
            qi.this = r1;
            this.a = bArr;
            this.b = i2;
            this.f17105c = z;
            this.d = z2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            synchronized (qi.this.r) {
                if (qi.this.f17076h == 0) {
                    return -1;
                }
                return Integer.valueOf(qi.this.f17075g.nativeRefreshTrafficData(qi.this.f17076h, this.a, this.b, this.f17105c, this.d));
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g implements qc.b {
        public final /* synthetic */ int a;

        public g(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetMinScaleLevel(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class g0 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17107c;
        public final /* synthetic */ int d;

        /* renamed from: e */
        public final /* synthetic */ int f17108e;

        public g0(int i2, int i3, int i4, int i5, int i6) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17107c = i4;
            this.d = i5;
            this.f17108e = i6;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeCheckTrafficBlockCacheForReplay(qi.this.f17076h, this.a, this.b, this.f17107c, this.d, this.f17108e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g1 implements qc.b {
        public final /* synthetic */ long a;

        public g1(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeRemoveEngineOverlay(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class g2 implements CallbackRunnable<String[]> {
        public final /* synthetic */ long a;

        public g2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String[] run() {
            return qi.this.f17076h == 0 ? new String[0] : qi.this.f17075g.nativeGetGLModelSkeletonAnimationName(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class g3 implements CallbackRunnable<Boolean> {
        public g3() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return Boolean.valueOf(qi.this.f17075g.nativeDrawFrame(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class g4 implements qc.b {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ boolean b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17110c;
        public final /* synthetic */ boolean d;

        public g4(boolean z, boolean z2, boolean z3, boolean z4) {
            qi.this = r1;
            this.a = z;
            this.b = z2;
            this.f17110c = z3;
            this.d = z4;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationFollow(qi.this.f17076h, this.a, this.b, this.f17110c, this.d);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g5 implements qc.b {
        public final /* synthetic */ boolean a;

        public g5(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetIndoorBuildingPickEnabled(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class h implements qc.b {
        public final /* synthetic */ float a;

        public h(float f2) {
            qi.this = r1;
            this.a = f2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetRotate(qi.this.f17076h, this.a, false);
        }
    }

    /* loaded from: classes9.dex */
    public class h0 implements qc.b {
        public final /* synthetic */ fg a;

        public h0(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R;
            if (qi.this.f17076h == 0 || (R = this.a.R()) == -1 || this.a.Y() < 0.0f) {
                return;
            }
            int O = this.a.O();
            if (O == 3 || O == 0) {
                qi.this.f17075g.nativeSetLineArrowSpacing(qi.this.f17076h, R, this.a.Y());
            } else {
                qi.this.f17075g.nativeSetLineFootPrintSpacing(qi.this.f17076h, R, this.a.Y());
            }
        }
    }

    /* loaded from: classes9.dex */
    public class h1 implements CallbackRunnable<Long> {
        public final /* synthetic */ AggregationOverlayInfo a;

        public h1(AggregationOverlayInfo aggregationOverlayInfo) {
            qi.this = r1;
            this.a = aggregationOverlayInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddAggregatioinOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class h2 implements CallbackRunnable<String[]> {
        public final /* synthetic */ long a;

        public h2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String[] run() {
            return qi.this.f17076h == 0 ? new String[0] : qi.this.f17075g.nativeGetVariantNames(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class h3 implements qc.b {
        public final /* synthetic */ boolean a;

        public h3(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetSatelliteEnabled(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class h4 implements qc.b {
        public final /* synthetic */ float a;

        public h4(float f2) {
            qi.this = r1;
            this.a = f2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationHeading(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class h5 implements qc.b {
        public final /* synthetic */ String[] a;

        public h5(String[] strArr) {
            qi.this = r1;
            this.a = strArr;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetShowIndoorBuildingWhiteList(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class i implements qc.b {
        public final /* synthetic */ float a;

        public i(float f2) {
            qi.this = r1;
            this.a = f2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetSkew(qi.this.f17076h, this.a, false);
        }
    }

    /* loaded from: classes9.dex */
    public class i0 implements qc.b {
        public final /* synthetic */ String a;

        public i0(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetServerHost(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class i1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ AggregationOverlayInfo b;

        public i1(long j2, AggregationOverlayInfo aggregationOverlayInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = aggregationOverlayInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateAggregatioinOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class i2 implements CallbackRunnable<Integer> {
        public final /* synthetic */ long a;

        public i2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeGetCurrentMaterialVariant(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class i3 implements qc.b {
        public i3() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeShowTraffic(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class i4 implements qc.b {
        public i4() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeHideCompass(qi.this.f17076h);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class i5 implements CallbackRunnable<Integer> {
        public final /* synthetic */ String a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17113c;

        public i5(String str, float f2, float f3) {
            qi.this = r1;
            this.a = str;
            this.b = f2;
            this.f17113c = f3;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(0 == qi.this.f17076h ? 0 : qi.this.f17075g.nativeSetLocationMarkerImage(qi.this.f17076h, this.a, this.b, this.f17113c));
        }
    }

    /* loaded from: classes9.dex */
    public class j implements CallbackRunnable<Integer> {
        public final /* synthetic */ TileOverlayCallback a;
        public final /* synthetic */ boolean b;

        public j(TileOverlayCallback tileOverlayCallback, boolean z) {
            qi.this = r1;
            this.a = tileOverlayCallback;
            this.b = z;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? -1 : qi.this.f17075g.nativeAddTileOverlay(qi.this.f17076h, this.a, this.b));
        }
    }

    /* loaded from: classes9.dex */
    public class j0 implements qc.b {
        public final /* synthetic */ String a;

        public j0(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeMapSetSatelliteServerFullUrl(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class j1 implements CallbackRunnable<Long> {
        public final /* synthetic */ HeatmapInfo a;

        public j1(HeatmapInfo heatmapInfo) {
            qi.this = r1;
            this.a = heatmapInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddHeatmapOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class j2 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ CircleInfo b;

        public j2(int i2, CircleInfo circleInfo) {
            qi.this = r1;
            this.a = i2;
            this.b = circleInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            qi.this.f17075g.nativeUpdateCircle(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class j3 implements qc.b {
        public j3() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeHideTraffic(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class j4 implements CallbackRunnable<Integer> {
        public final /* synthetic */ te a;

        public j4(te teVar) {
            qi.this = r1;
            this.a = teVar;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(0 == qi.this.f17076h ? 0 : qi.this.f17075g.nativeAddMarker(qi.this.f17076h, this.a.j(), this.a.n(), this.a.o(), this.a.e(), this.a.f(), this.a.r(), this.a.s(), this.a.d(), this.a.q(), this.a.B(), this.a.A(), this.a.z(), this.a.w(), this.a.v(), this.a.u(), this.a.m()));
        }
    }

    /* loaded from: classes9.dex */
    public class j5 implements CallbackRunnable<Integer> {
        public final /* synthetic */ String a;

        public j5(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeIsCityHasTraffic(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class k implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public k(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetTrafficMode(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class k0 implements qc.b {
        public final /* synthetic */ boolean a;

        public k0(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeEnablePOI(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class k1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ HeatmapInfo b;

        public k1(long j2, HeatmapInfo heatmapInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = heatmapInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateHeatmapOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class k2 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ int b;

        public k2(long j2, int i2) {
            qi.this = r1;
            this.a = j2;
            this.b = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeSetMaterialVariant(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class k3 implements CallbackRunnable<String> {
        public final /* synthetic */ GeoPoint a;

        public k3(GeoPoint geoPoint) {
            qi.this = r1;
            this.a = geoPoint;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            byte[] nativeGetCityName;
            if (qi.this.f17076h != 0 && (nativeGetCityName = qi.this.f17075g.nativeGetCityName(qi.this.f17076h, this.a)) != null) {
                try {
                    return new String(nativeGetCityName, "GBK").trim();
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                }
            }
            return "";
        }
    }

    /* loaded from: classes9.dex */
    public class k4 implements qc.b {
        public final /* synthetic */ te a;

        public k4(te teVar) {
            qi.this = r1;
            this.a = teVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeUpdateMarkerInfo(qi.this.f17076h, this.a.i(), this.a.j(), this.a.n(), this.a.o(), this.a.e(), this.a.f(), this.a.r(), this.a.s(), this.a.d(), this.a.q(), this.a.B(), this.a.A(), this.a.z(), this.a.w(), this.a.v(), this.a.u(), this.a.m());
            }
        }
    }

    /* loaded from: classes9.dex */
    public class k5 implements qc.b {
        public final /* synthetic */ int a;

        public k5(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationCircleColor(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class l implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public l(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetTileOverlayPriority(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class l0 implements qc.b {
        public final /* synthetic */ boolean a;

        public l0(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeEnableBaseMap(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class l1 implements qc.b {
        public final /* synthetic */ long a;

        public l1(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeRemoveGLVisualizationOverlay(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class l2 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17120c;
        public final /* synthetic */ float d;

        public l2(long j2, float f2, float f3, float f4) {
            qi.this = r1;
            this.a = j2;
            this.b = f2;
            this.f17120c = f3;
            this.d = f4;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeSetMonoColor(qi.this.f17076h, this.a, this.b, this.f17120c, this.d);
        }
    }

    /* loaded from: classes9.dex */
    public class l3 implements qc.b {
        public final /* synthetic */ String a;

        public l3(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateMapResource(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class l4 implements qc.b {
        public final /* synthetic */ int[] a;
        public final /* synthetic */ int b;

        public l4(int[] iArr, int i2) {
            qi.this = r1;
            this.a = iArr;
            this.b = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeDeleteIcons(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class l5 implements qc.b {
        public final /* synthetic */ boolean a;

        public l5(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationCircleHidden(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class m implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17123c;

        public m(int i2, int i3, int i4) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17123c = i4;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetTileOverlayDataLevelRange(qi.this.f17076h, this.a, this.b, this.f17123c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class m0 implements qc.b {
        public final /* synthetic */ boolean a;

        public m0(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeEnableBuilding(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class m1 implements CallbackRunnable<VectorHeatAggregationUnit> {
        public final /* synthetic */ long a;
        public final /* synthetic */ LatLng b;

        public m1(long j2, LatLng latLng) {
            qi.this = r1;
            this.a = j2;
            this.b = latLng;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public VectorHeatAggregationUnit run() {
            if (qi.this.f17076h == 0) {
                return null;
            }
            return qi.this.f17075g.getAggregationUnit(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class m2 implements qc.b {
        public final /* synthetic */ long a;

        public m2(long j2) {
            qi.this = r1;
            this.a = j2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeResetMonoColor(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class m3 implements CallbackRunnable<Integer> {
        public final /* synthetic */ fg a;

        public m3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            fg fgVar = this.a;
            if (fgVar == null) {
                return -1;
            }
            return Integer.valueOf(fgVar.R() != -1 ? this.a.R() : qi.this.b(this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class m4 implements qc.b {
        public final /* synthetic */ int[] a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17125c;

        public m4(int[] iArr, int i2, boolean z) {
            qi.this = r1;
            this.a = iArr;
            this.b = i2;
            this.f17125c = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeSetIconsHidden(qi.this.f17076h, this.a, this.b, this.f17125c);
        }
    }

    /* loaded from: classes9.dex */
    public class m5 implements qc.b {
        public final /* synthetic */ boolean a;

        public m5(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationMarkerHidden(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class n implements qc.b {
        public final /* synthetic */ int a;

        public n(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeRemoveTileOverlay(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class n0 implements CallbackRunnable<String> {
        public n0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            return qi.this.f17076h == 0 ? "" : qi.this.f17075g.nativeGetMapEngineVersion(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class n1 implements CallbackRunnable<Integer> {
        public final /* synthetic */ PolygonInfo a;

        public n1(PolygonInfo polygonInfo) {
            qi.this = r1;
            this.a = polygonInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeAddPolygon(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class n2 implements qc.b {
        public final /* synthetic */ TrafficStyle a;

        public n2(TrafficStyle trafficStyle) {
            qi.this = r1;
            this.a = trafficStyle;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.setTrafficStyle(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class n3 implements qc.b {
        public final /* synthetic */ fg a;

        public n3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            fg fgVar = this.a;
            if (fgVar == null || fgVar.R() == -1) {
                return;
            }
            qi.this.b(this.a);
            qi.this.k(this.a);
            qi.this.j(this.a);
            qi.this.f(this.a);
            if (this.a.f0()) {
                qi.this.c(this.a);
            } else {
                qi.this.d(this.a);
            }
            qi.this.g(this.a);
            if (!e7.b(this.a.N())) {
                qi.this.e(this.a);
            }
            qi.this.i(this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class n4 implements qc.b {
        public n4() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUnlockEngine(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class n5 implements qc.b {
        public final /* synthetic */ String a;

        public n5(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationCompassMarkerImage(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class o implements qc.b {
        public final /* synthetic */ int a;

        public o(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeReloadTileOverlay(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class o0 implements CallbackRunnable<String> {
        public o0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            return qi.this.f17076h == 0 ? "" : qi.this.f17075g.nativeGetDataEngineVersion(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class o1 implements CallbackRunnable<Long> {
        public final /* synthetic */ ArcLineOverlayInfo a;

        public o1(ArcLineOverlayInfo arcLineOverlayInfo) {
            qi.this = r1;
            this.a = arcLineOverlayInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddArcLineOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class o2 implements qc.b {
        public final /* synthetic */ List a;

        public o2(List list) {
            qi.this = r1;
            this.a = list;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetIndoorCellInfo(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class o3 implements CallbackRunnable<Integer> {
        public final /* synthetic */ fg a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17126c;
        public final /* synthetic */ boolean d;

        /* renamed from: e */
        public final /* synthetic */ int[] f17127e;

        /* renamed from: f */
        public final /* synthetic */ int[] f17128f;

        public o3(fg fgVar, int i2, boolean z, boolean z2, int[] iArr, int[] iArr2) {
            qi.this = r1;
            this.a = fgVar;
            this.b = i2;
            this.f17126c = z;
            this.d = z2;
            this.f17127e = iArr;
            this.f17128f = iArr2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17075g.nativeCreateOrUpdateLine(qi.this.f17076h, this.a.R(), this.a.W(), this.a.X(), (GeoPoint[]) this.a.S().toArray(new GeoPoint[0]), this.a.Z(), this.a.getWidth(), this.b, this.f17126c, this.d, this.a.g0(), this.a.isGradientEnable(), this.a.getZIndex(), this.a.d0(), this.a.L(), this.f17127e, this.a.M(), this.f17128f, this.a.getAlpha(), this.a.getLevel(), this.a.isVisible()));
        }
    }

    /* loaded from: classes9.dex */
    public class o4 implements qc.b {
        public final /* synthetic */ boolean a;

        public o4(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetCompassVisible(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class o5 implements qc.b {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ String f17130c;
        public final /* synthetic */ String d;

        /* renamed from: e */
        public final /* synthetic */ String f17131e;

        public o5(String str, String str2, String str3, String str4, String str5) {
            qi.this = r1;
            this.a = str;
            this.b = str2;
            this.f17130c = str3;
            this.d = str4;
            this.f17131e = str5;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationCompassGroupImages(qi.this.f17076h, this.a, this.b, this.f17130c, this.d, this.f17131e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class p implements CallbackRunnable<Boolean> {
        public p() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h == 0 ? Boolean.FALSE : Boolean.valueOf(qi.this.f17075g.nativeIsTileOverlayEnabled(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class p0 implements qc.b {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        public p0(String str, String str2) {
            qi.this = r1;
            this.a = str;
            this.b = str2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetBuildingToSpecificFloor(qi.this.f17076h, this.a, this.b);
            if (qi.this.f17080l != null) {
                qi.this.f17080l.d();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class p1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ ArcLineOverlayInfo b;

        public p1(long j2, ArcLineOverlayInfo arcLineOverlayInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = arcLineOverlayInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateArcLineOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class p2 implements qc.b {
        public p2() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeResetIndoorCellInfo(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class p3 implements qc.b {
        public final /* synthetic */ fg a;

        public p3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R;
            if (qi.this.f17076h == 0 || (R = this.a.R()) == -1) {
                return;
            }
            qi.this.f17075g.nativeSetLineSelected(qi.this.f17076h, R, this.a.isSelected());
        }
    }

    /* loaded from: classes9.dex */
    public class p4 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public p4(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetCompassPosition(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class p5 implements qc.b {
        public final /* synthetic */ boolean a;

        public p5(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationCompassMarkerHidden(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class q implements qc.b {
        public final /* synthetic */ boolean a;

        public q(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetTileOverlayEnabled(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class q0 implements CallbackRunnable<Integer> {
        public final /* synthetic */ String a;

        public q0(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? -1 : qi.this.f17075g.getIndoorOutlineZoom(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class q1 implements CallbackRunnable<Long> {
        public final /* synthetic */ ScatterPlotInfo a;

        public q1(ScatterPlotInfo scatterPlotInfo) {
            qi.this = r1;
            this.a = scatterPlotInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddScatterOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class q2 implements CallbackRunnable<Object> {
        public final /* synthetic */ qc.b a;

        public q2(qc.b bVar) {
            qi.this = r1;
            this.a = bVar;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        public Object run() {
            this.a.a(null);
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class q3 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ boolean b;

        public q3(int i2, boolean z) {
            qi.this = r1;
            this.a = i2;
            this.b = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (this.a == -1) {
                return;
            }
            qi.this.f17075g.nativeDeleteLine(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class q4 implements qc.b {
        public final /* synthetic */ String a;

        public q4(String str) {
            qi.this = r1;
            this.a = str;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetCompassImage(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class q5 implements qc.b {
        public final /* synthetic */ boolean a;

        public q5(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationRedLineHidden(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class r implements qc.b {
        public final /* synthetic */ com.tencent.mapsdk.internal.v a;

        public r(com.tencent.mapsdk.internal.v vVar) {
            qi.this = r1;
            this.a = vVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            com.tencent.mapsdk.internal.v vVar = this.a;
            if (vVar != null) {
                vVar.z();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class r0 implements CallbackRunnable<ah[]> {
        public r0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public ah[] run() {
            synchronized (qi.this.r) {
                if (qi.this.f17076h == 0) {
                    return null;
                }
                int[] nativeFetchLackedTrafficBlocks = qi.this.f17075g.nativeFetchLackedTrafficBlocks(qi.this.f17076h);
                if (nativeFetchLackedTrafficBlocks == null || nativeFetchLackedTrafficBlocks.length == 0) {
                    return null;
                }
                ah[] ahVarArr = new ah[nativeFetchLackedTrafficBlocks.length / 9];
                for (int i2 = 0; i2 < nativeFetchLackedTrafficBlocks.length / 9; i2++) {
                    ahVarArr[i2] = new ah();
                    int i3 = i2 * 9;
                    ahVarArr[i2].a = nativeFetchLackedTrafficBlocks[i3];
                    ahVarArr[i2].b = nativeFetchLackedTrafficBlocks[i3 + 1];
                    ahVarArr[i2].d = nativeFetchLackedTrafficBlocks[i3 + 2];
                    ahVarArr[i2].f16249c = nativeFetchLackedTrafficBlocks[i3 + 3];
                    ahVarArr[i2].f16251f = nativeFetchLackedTrafficBlocks[i3 + 4];
                    ahVarArr[i2].f16250e = nativeFetchLackedTrafficBlocks[i3 + 5];
                    ahVarArr[i2].f16252g = nativeFetchLackedTrafficBlocks[i3 + 6];
                    ahVarArr[i2].f16253h = nativeFetchLackedTrafficBlocks[i3 + 7];
                    ahVarArr[i2].f16254i = nativeFetchLackedTrafficBlocks[i3 + 8];
                }
                return ahVarArr;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class r1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ ScatterPlotInfo b;

        public r1(long j2, ScatterPlotInfo scatterPlotInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = scatterPlotInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateScatterPlotOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class r2 implements CallbackRunnable<Object> {
        public final /* synthetic */ qc.b a;

        public r2(qc.b bVar) {
            qi.this = r1;
            this.a = bVar;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        public Object run() {
            this.a.a(null);
            return null;
        }
    }

    /* loaded from: classes9.dex */
    public class r3 implements CallbackRunnable<Integer> {
        public r3() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? -1 : qi.this.f17075g.nativeClearCache(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class r4 implements CallbackRunnable<Double> {
        public final /* synthetic */ Rect a;
        public final /* synthetic */ Rect b;

        public r4(Rect rect, Rect rect2) {
            qi.this = r1;
            this.a = rect;
            this.b = rect2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Double run() {
            return Double.valueOf(0 == qi.this.f17076h ? 1.0d : qi.this.f17075g.nativeGetTargetScale(qi.this.f17076h, this.a, this.b));
        }
    }

    /* loaded from: classes9.dex */
    public class r5 implements qc.b {
        public final /* synthetic */ float a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ LatLng f17139c;

        public r5(float f2, int i2, LatLng latLng) {
            qi.this = r1;
            this.a = f2;
            this.b = i2;
            this.f17139c = latLng;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetLocationRedLineInfo(qi.this.f17076h, this.a, this.b, this.f17139c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class s implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17140c;
        public final /* synthetic */ int d;

        public s(int i2, int i3, int i4, int i5) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17140c = i4;
            this.d = i5;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetTrafficColor(qi.this.f17076h, this.a, this.b, this.f17140c, this.d);
        }
    }

    /* loaded from: classes9.dex */
    public class s0 implements CallbackRunnable<String> {
        public s0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            if (qi.this.f17076h == 0) {
                return null;
            }
            return qi.this.f17075g.getMapEngineRenderStatus(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class s1 implements CallbackRunnable<Long> {
        public final /* synthetic */ TrailOverlayInfo a;

        public s1(TrailOverlayInfo trailOverlayInfo) {
            qi.this = r1;
            this.a = trailOverlayInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddTrailOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class s2 implements qc.b {
        public final /* synthetic */ CallbackRunnable a;

        public s2(CallbackRunnable callbackRunnable) {
            qi.this = r1;
            this.a = callbackRunnable;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            this.a.run();
        }
    }

    /* loaded from: classes9.dex */
    public class s3 implements CallbackRunnable<Boolean> {
        public final /* synthetic */ int a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f17142c;

        public s3(int i2, float f2, float f3) {
            qi.this = r1;
            this.a = i2;
            this.b = f2;
            this.f17142c = f3;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return Boolean.valueOf(qi.this.f17075g.nativeOnTapLine(this.a, this.b, this.f17142c));
        }
    }

    /* loaded from: classes9.dex */
    public class s4 implements qc.b {
        public final /* synthetic */ Rect a;
        public final /* synthetic */ Rect b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17143c;

        public s4(Rect rect, Rect rect2, boolean z) {
            qi.this = r1;
            this.a = rect;
            this.b = rect2;
            this.f17143c = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeZoomToSpan(qi.this.f17076h, this.a, this.b, this.f17143c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class s5 implements qc.b {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17144c;

        public s5(float f2, float f3, boolean z) {
            qi.this = r1;
            this.a = f2;
            this.b = f3;
            this.f17144c = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeMoveBy(qi.this.f17076h, this.a, this.b, this.f17144c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class t implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public t(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeBringElementAbove(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class t0 implements CallbackRunnable<ArrayList<MapPoi>> {
        public t0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public ArrayList<MapPoi> run() {
            if (qi.this.f17076h == 0) {
                return null;
            }
            return qi.this.f17075g.nativeGetPoisInScreen(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class t1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ TrailOverlayInfo b;

        public t1(long j2, TrailOverlayInfo trailOverlayInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = trailOverlayInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateTrailOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class t2 implements qc.b {
        public final /* synthetic */ int a;

        public t2(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            qi.this.f17075g.nativeDeleteCircle(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class t3 implements qc.b {
        public final /* synthetic */ fg a;

        public t3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            fg.a a0;
            int R = this.a.R();
            if (R == -1 || (a0 = this.a.a0()) == null) {
                return;
            }
            qi.this.f17075g.nativeSetTurnArrow(qi.this.f17076h, R, this.a.S(), a0.a, a0.b);
        }
    }

    /* loaded from: classes9.dex */
    public class t4 implements qc.b {
        public final /* synthetic */ boolean a;

        public t4(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeIndoorBuildingEnabled(qi.this.f17076h, this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class t5 implements qc.b {
        public final /* synthetic */ GeoPoint a;
        public final /* synthetic */ boolean b;

        public t5(GeoPoint geoPoint, boolean z) {
            qi.this = r1;
            this.a = geoPoint;
            this.b = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetCenter(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class u implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public u(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeBringElementBelow(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class u0 implements CallbackRunnable<Boolean> {
        public final /* synthetic */ int a;

        public u0(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h == 0 ? Boolean.FALSE : Boolean.valueOf(qi.this.f17075g.checkMapLoadFinishedTask(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class u1 implements CallbackRunnable<Boolean> {
        public u1() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h == 0 ? Boolean.FALSE : Boolean.valueOf(qi.this.f17075g.nativeGetAndResetDirty(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class u2 implements qc.b {
        public final /* synthetic */ PolygonInfo a;

        public u2(PolygonInfo polygonInfo) {
            qi.this = r1;
            this.a = polygonInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            JNI jni = qi.this.f17075g;
            long j2 = qi.this.f17076h;
            PolygonInfo polygonInfo = this.a;
            jni.nativeUpdatePolygon(j2, polygonInfo.polygonId, polygonInfo.borderLineId, polygonInfo);
        }
    }

    /* loaded from: classes9.dex */
    public class u3 implements qc.b {
        public final /* synthetic */ fg a;

        public u3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R = this.a.R();
            if (R == -1) {
                return;
            }
            int[] b0 = this.a.b0();
            qi.this.f17075g.nativeSetTurnArrowStyle(qi.this.f17076h, R, b0[0], b0[1]);
        }
    }

    /* loaded from: classes9.dex */
    public class u4 implements qc.b {
        public final /* synthetic */ boolean a;

        public u4(boolean z) {
            qi.this = r1;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetIndoorBuildingStyle(qi.this.f17076h, this.a ? 1 : 0);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class u5 implements qc.b {
        public final /* synthetic */ int a;

        public u5(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            JNI jni;
            long j2;
            int i2;
            int i3;
            int i4;
            if (qi.this.f17076h == 0) {
                return;
            }
            int i5 = this.a;
            if (i5 == 1) {
                jni = qi.this.f17075g;
                j2 = qi.this.f17076h;
                i2 = qi.u;
                i3 = qi.w;
                i4 = qi.z;
            } else if (i5 == 2) {
                jni = qi.this.f17075g;
                j2 = qi.this.f17076h;
                i2 = qi.v;
                i3 = qi.x;
                i4 = qi.A;
            } else {
                jni = qi.this.f17075g;
                j2 = qi.this.f17076h;
                i2 = qi.u;
                i3 = qi.w;
                i4 = qi.y;
            }
            jni.nativeSetTrafficColor(j2, i2, i3, i4, -16777063);
        }
    }

    /* loaded from: classes9.dex */
    public class v implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17149c;
        public final /* synthetic */ int d;

        /* renamed from: e */
        public final /* synthetic */ int f17150e;

        public v(int i2, int i3, int i4, int i5, int i6) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17149c = i4;
            this.d = i5;
            this.f17150e = i6;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeCheckTrafficBlockCache(qi.this.f17076h, this.a, this.b, this.f17149c, this.d, this.f17150e);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class v0 implements qc.b {
        public final /* synthetic */ double[] a;
        public final /* synthetic */ double[] b;

        /* renamed from: c */
        public final /* synthetic */ int f17152c;

        public v0(double[] dArr, double[] dArr2, int i2) {
            qi.this = r1;
            this.a = dArr;
            this.b = dArr2;
            this.f17152c = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.setRestrictBounds(qi.this.f17076h, this.a, this.b, this.f17152c);
        }
    }

    /* loaded from: classes9.dex */
    public class v1 implements CallbackRunnable<Long> {
        public final /* synthetic */ GroundOverlayInfo a;

        public v1(GroundOverlayInfo groundOverlayInfo) {
            qi.this = r1;
            this.a = groundOverlayInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            GroundOverlayInfo groundOverlayInfo;
            if (qi.this.f17076h == 0 || (groundOverlayInfo = this.a) == null || !groundOverlayInfo.checkValid()) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddGroundOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class v2 extends ob {
        public v2() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.ob, com.tencent.mapsdk.internal.ib
        public void a(String str, byte[] bArr) {
            synchronized (qi.this.r) {
                if (qi.this.f17076h != 0 && !TextUtils.isEmpty(str)) {
                    EngineWriteDataModel nativeWriteMapDataBlock = qi.this.f17075g.nativeWriteMapDataBlock(qi.this.f17076h, str, bArr);
                    if (nativeWriteMapDataBlock != null && nativeWriteMapDataBlock.resultCode != 0) {
                        com.tencent.mapsdk.internal.u.f().a(new mi(nativeWriteMapDataBlock));
                    }
                    qa.a(la.b, "data-length", bArr != a7.a() ? bArr.length : 0);
                    if (qa.g(la.b, "req-count") == qa.i(la.b, "data-count") + qa.g(la.b, "cancel-count")) {
                        qa.j(la.b);
                    }
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class v3 implements qc.b {
        public final /* synthetic */ fg a;

        public v3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R = this.a.R();
            if (R == -1) {
                return;
            }
            qi.this.f17075g.nativeSetLineDrawArrow(qi.this.f17076h, R, this.a.c0());
        }
    }

    /* loaded from: classes9.dex */
    public class v4 implements qc.b {
        public final /* synthetic */ int a;

        public v4(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetIndoorConfigType(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class v5 implements qc.b {
        public final /* synthetic */ GeoPoint a;

        public v5(GeoPoint geoPoint) {
            qi.this = r1;
            this.a = geoPoint;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h || qi.this.f17081m == null) {
                return;
            }
            qi.this.f17075g.nativeSetCenter(qi.this.f17076h, this.a, false);
        }
    }

    /* loaded from: classes9.dex */
    public class w implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ float b;

        public w(int i2, float f2) {
            qi.this = r1;
            this.a = i2;
            this.b = f2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeSetPriority(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class w0 implements CallbackRunnable<Integer> {
        public final /* synthetic */ GeoPoint[] a;
        public final /* synthetic */ PolylineOptions.Text b;

        public w0(GeoPoint[] geoPointArr, PolylineOptions.Text text) {
            qi.this = r1;
            this.a = geoPointArr;
            this.b = text;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? -1 : qi.this.f17075g.addLineText(qi.this.f17076h, this.a, this.b));
        }
    }

    /* loaded from: classes9.dex */
    public class w1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ GroundOverlayInfo b;

        public w1(long j2, GroundOverlayInfo groundOverlayInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = groundOverlayInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateGroundOverlay(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class w2 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17156c;

        public w2(int i2, int i3, boolean z) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17156c = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a < 0 || qi.this.f17081m == null) {
                return;
            }
            qi.this.f17075g.nativeSetPolygonHidden(qi.this.f17076h, this.a, this.b, this.f17156c);
        }
    }

    /* loaded from: classes9.dex */
    public class w3 implements qc.b {
        public final /* synthetic */ fg a;

        public w3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R = this.a.R();
            if (R == -1) {
                return;
            }
            qi.this.f17075g.nativeSetLineDirectionArrowTextureName(qi.this.f17076h, R, this.a.N());
        }
    }

    /* loaded from: classes9.dex */
    public class w4 implements qc.b {
        public final /* synthetic */ int a;

        public w4(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h || qi.this.f17081m == null) {
                return;
            }
            qi.this.f17075g.nativeSetIndoorFloor(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class w5 implements qc.b {
        public final /* synthetic */ GeoPoint a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ boolean f17157c;

        public w5(GeoPoint geoPoint, int i2, boolean z) {
            qi.this = r1;
            this.a = geoPoint;
            this.b = i2;
            this.f17157c = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetCenterMapPointAndScaleLevel(qi.this.f17076h, this.a, this.b, this.f17157c);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class x implements qc.b {
        public final /* synthetic */ List a;
        public final /* synthetic */ List b;

        public x(List list, List list2) {
            qi.this = r1;
            this.a = list;
            this.b = list2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            List list;
            List list2;
            if (0 == qi.this.f17076h || (list = this.a) == null || list.isEmpty() || (list2 = this.b) == null || list2.isEmpty()) {
                return;
            }
            int size = this.a.size();
            byte[][] bArr = new byte[size];
            for (int i2 = 0; i2 < size; i2++) {
                MapRouteSection mapRouteSection = (MapRouteSection) this.a.get(i2);
                if (mapRouteSection != null) {
                    bArr[i2] = mapRouteSection.toBytes();
                }
            }
            int size2 = this.b.size();
            qi.this.f17075g.nativeAddRouteNameSegments(qi.this.f17076h, bArr, size, (GeoPoint[]) this.b.toArray(new GeoPoint[size2]), size2);
        }
    }

    /* loaded from: classes9.dex */
    public class x0 implements qc.b {
        public final /* synthetic */ int a;

        public x0(int i2) {
            qi.this = r1;
            this.a = i2;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.removeLineText(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class x1 implements CallbackRunnable<Long> {
        public final /* synthetic */ IntersectionOverlayInfo a;

        public x1(IntersectionOverlayInfo intersectionOverlayInfo) {
            qi.this = r1;
            this.a = intersectionOverlayInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Long run() {
            if (qi.this.f17076h == 0) {
                return 0L;
            }
            return Long.valueOf(qi.this.f17075g.nativeAddIntersectionOverlay(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class x2 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public x2(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a < 0 || qi.this.f17081m == null) {
                return;
            }
            qi.this.f17075g.nativeDeletePolygon(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class x3 implements qc.b {
        public final /* synthetic */ fg a;

        public x3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            int R = this.a.R();
            if (R == -1) {
                return;
            }
            qi.this.f17075g.nativeSetDrawCap(qi.this.f17076h, R, this.a.e0());
        }
    }

    /* loaded from: classes9.dex */
    public class x4 implements CallbackRunnable<Integer> {
        public x4() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(0 == qi.this.f17076h ? -1 : qi.this.f17075g.nativeGetIndoorCurrentFloorId(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class x5 implements CallbackRunnable<GeoPoint> {
        public x5() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public GeoPoint run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            GeoPoint geoPoint = new GeoPoint();
            qi.this.f17075g.nativeGetCenterMapPoint(qi.this.f17076h, geoPoint);
            return geoPoint;
        }
    }

    /* loaded from: classes9.dex */
    public class y implements qc.b {
        public y() {
            qi.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (0 == qi.this.f17076h) {
                return;
            }
            qi.this.f17075g.nativeClearRouteNameSegments(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class y0 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ PolylineOptions.Text b;

        public y0(int i2, PolylineOptions.Text text) {
            qi.this = r1;
            this.a = i2;
            this.b = text;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.setLineTextStyle(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class y1 implements CallbackRunnable<Integer> {
        public final /* synthetic */ CircleInfo a;

        public y1(CircleInfo circleInfo) {
            qi.this = r1;
            this.a = circleInfo;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf((qi.this.f17076h == 0 || this.a == null) ? -1 : qi.this.f17075g.nativeAddCircle(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class y2 implements CallbackRunnable<Integer> {
        public final /* synthetic */ MaskLayer a;

        public y2(MaskLayer maskLayer) {
            qi.this = r1;
            this.a = maskLayer;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Integer run() {
            return Integer.valueOf(qi.this.f17076h == 0 ? 0 : qi.this.f17075g.nativeAddMaskLayer(qi.this.f17076h, this.a));
        }
    }

    /* loaded from: classes9.dex */
    public class y3 implements qc.b {
        public final /* synthetic */ fg a;

        public y3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            GeoPoint P;
            int R = this.a.R();
            if (R == -1 || (P = this.a.P()) == null) {
                return;
            }
            qi.this.f17075g.nativeLineInsertPoint(qi.this.f17076h, R, P, this.a.Q());
        }
    }

    /* loaded from: classes9.dex */
    public class y4 implements CallbackRunnable<Boolean> {
        public y4() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public Boolean run() {
            return qi.this.f17076h == 0 ? Boolean.TRUE : Boolean.valueOf(qi.this.f17075g.nativeIsMapDrawFinished(qi.this.f17076h));
        }
    }

    /* loaded from: classes9.dex */
    public class y5 implements qc.b {
        public final /* synthetic */ double a;
        public final /* synthetic */ boolean b;

        public y5(double d, boolean z) {
            qi.this = r1;
            this.a = d;
            this.b = z;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetScale(qi.this.f17076h, this.a, this.b);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class z implements CallbackRunnable<AnnocationTextResult> {
        public final /* synthetic */ AnnocationText a;

        public z(AnnocationText annocationText) {
            qi.this = r1;
            this.a = annocationText;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public AnnocationTextResult run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            return qi.this.f17075g.nativeCreateAnnotationTextBitmap(qi.this.f17076h, this.a);
        }
    }

    /* loaded from: classes9.dex */
    public class z0 implements CallbackRunnable<String> {
        public z0() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String run() {
            return qi.this.f17076h == 0 ? "" : qi.this.f17075g.nativeGetEngineLogInfo(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class z1 implements qc.b {
        public final /* synthetic */ long a;
        public final /* synthetic */ IntersectionOverlayInfo b;

        public z1(long j2, IntersectionOverlayInfo intersectionOverlayInfo) {
            qi.this = r1;
            this.a = j2;
            this.b = intersectionOverlayInfo;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0 || this.a == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateIntersectionOverlay(this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class z2 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        public z2(int i2, int i3) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h == 0) {
                return;
            }
            qi.this.f17075g.nativeUpdateMaskLayer(qi.this.f17076h, this.a, this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class z3 implements qc.b {
        public final /* synthetic */ fg a;

        public z3(fg fgVar) {
            qi.this = r1;
            this.a = fgVar;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            GeoPoint P;
            int R = this.a.R();
            if (R == -1 || (P = this.a.P()) == null) {
                return;
            }
            qi.this.f17075g.nativeLineClearPoint(qi.this.f17076h, R, P, this.a.Q());
        }
    }

    /* loaded from: classes9.dex */
    public class z4 implements CallbackRunnable<String[]> {
        public z4() {
            qi.this = r1;
        }

        @Override // com.tencent.map.tools.CallbackRunnable
        /* renamed from: a */
        public String[] run() {
            if (0 == qi.this.f17076h) {
                return null;
            }
            return qi.this.f17075g.nativeGetIndoorFloorNames(qi.this.f17076h);
        }
    }

    /* loaded from: classes9.dex */
    public class z5 implements qc.b {
        public final /* synthetic */ int a;
        public final /* synthetic */ int b;

        /* renamed from: c */
        public final /* synthetic */ int f17164c;

        public z5(int i2, int i3, int i4) {
            qi.this = r1;
            this.a = i2;
            this.b = i3;
            this.f17164c = i4;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qi.this.f17076h != 0) {
                qi.this.f17075g.nativeSetMarkerScaleLevelRange(qi.this.f17076h, this.a, this.b, this.f17164c);
            }
        }
    }

    public qi(Context context, qc qcVar) {
        v2 v2Var = new v2();
        this.f17079k = v2Var;
        this.f17082n = 0L;
        this.o = 0L;
        this.p = 1.0f;
        this.q = null;
        this.r = new Object();
        this.s = new ReentrantLock();
        this.f17075g = new JNI();
        jb jbVar = new jb();
        this.f17078j = jbVar;
        jbVar.a(v2Var);
        this.f17081m = qcVar;
        this.p = context.getResources().getDisplayMetrics().density;
        if (G) {
            ja.a(context, li.a);
        }
    }

    public static boolean P() {
        return G;
    }

    private <T> T a(CallbackRunnable<T> callbackRunnable, T t6) {
        if (callbackRunnable != null && this.f17081m != null && this.f17076h != 0) {
            this.f17081m.a(new s2(callbackRunnable));
        }
        return t6;
    }

    private void a(qc.b bVar) {
        if (bVar == null || this.f17081m == null || this.f17076h == 0) {
            return;
        }
        c(new q2(bVar), (q2) null);
    }

    private void a(double[] dArr, int i6, double d7, double d8, double d9) {
        for (int i7 = 0; i7 < 4; i7++) {
            int i8 = i6 + i7;
            int i9 = i8 + 12;
            dArr[i9] = dArr[i9] + (dArr[i8] * d7) + (dArr[i8 + 4] * d8) + (dArr[i8 + 8] * d9);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int b(com.tencent.mapsdk.internal.fg r12) {
        /*
            r11 = this;
            int r0 = r12.O()
            r1 = 1
            r2 = 0
            r3 = 3
            if (r0 != r3) goto Lc
            r0 = 0
            r7 = 1
            goto Ld
        Lc:
            r7 = 0
        Ld:
            int[] r3 = r12.W()
            r4 = r3[r2]
            r5 = 33
            if (r4 != r5) goto L1a
            r0 = 2
            r6 = 2
            goto L23
        L1a:
            r3 = r3[r2]
            r4 = 20
            r6 = r0
            if (r3 != r4) goto L23
            r8 = 1
            goto L24
        L23:
            r8 = 0
        L24:
            int[] r0 = new int[r2]
            java.util.List r1 = r12.getPattern()
            if (r1 == 0) goto L54
            java.util.List r0 = r12.getPattern()
            int r0 = r0.size()
            int[] r0 = new int[r0]
            r1 = 0
        L37:
            java.util.List r3 = r12.getPattern()
            int r3 = r3.size()
            if (r1 >= r3) goto L54
            java.util.List r3 = r12.getPattern()
            java.lang.Object r3 = r3.get(r1)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            r0[r1] = r3
            int r1 = r1 + 1
            goto L37
        L54:
            r10 = r0
            int[] r0 = new int[r2]
            int[] r1 = r12.K()
            if (r1 == 0) goto L76
            int[] r0 = r12.K()
            int r0 = r0.length
            int[] r0 = new int[r0]
        L64:
            int[] r1 = r12.K()
            int r1 = r1.length
            if (r2 >= r1) goto L76
            int[] r1 = r12.K()
            r1 = r1[r2]
            r0[r2] = r1
            int r2 = r2 + 1
            goto L64
        L76:
            r9 = r0
            com.tencent.mapsdk.internal.qi$o3 r0 = new com.tencent.mapsdk.internal.qi$o3
            r3 = r0
            r4 = r11
            r5 = r12
            r3.<init>(r5, r6, r7, r8, r9, r10)
            r12 = -1
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            java.lang.Object r12 = r11.c(r0, r12)
            java.lang.Integer r12 = (java.lang.Integer) r12
            int r12 = r12.intValue()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.qi.b(com.tencent.mapsdk.internal.fg):int");
    }

    private <T> T b(CallbackRunnable<T> callbackRunnable, T t6) {
        return (callbackRunnable == null || this.f17081m == null || this.f17076h == 0) ? t6 : Thread.currentThread().getName().contains(fj.r) ? (T) e((CallbackRunnable<CallbackRunnable<T>>) callbackRunnable, (CallbackRunnable<T>) t6) : (T) a((CallbackRunnable<CallbackRunnable<T>>) callbackRunnable, (CallbackRunnable<T>) t6);
    }

    private void b(qc.b bVar) {
        if (bVar == null || this.f17081m == null || this.f17076h == 0) {
            return;
        }
        d(new r2(bVar), (r2) null);
    }

    private <T> T c(CallbackRunnable<T> callbackRunnable, T t6) {
        if (callbackRunnable == null || this.f17081m == null || this.f17076h == 0) {
            return t6;
        }
        try {
            i();
            return (T) b((CallbackRunnable<CallbackRunnable<T>>) callbackRunnable, (CallbackRunnable<T>) t6);
        } catch (Exception e7) {
            ma.b(la.f16819f, "safeCallEngine", e7);
            return t6;
        } finally {
            j();
        }
    }

    private <T> T d(CallbackRunnable<T> callbackRunnable, T t6) {
        if (callbackRunnable == null || this.f17081m == null || this.f17076h == 0) {
            return t6;
        }
        try {
            i();
            return (T) e((CallbackRunnable<CallbackRunnable<T>>) callbackRunnable, (CallbackRunnable<T>) t6);
        } catch (Exception e7) {
            ma.b(la.f16819f, "safeCallSyncEngine", e7);
            return t6;
        } finally {
            j();
        }
    }

    private <T> T e(CallbackRunnable<T> callbackRunnable, T t6) {
        return (callbackRunnable == null || this.f17081m == null || this.f17076h == 0) ? t6 : callbackRunnable.run();
    }

    private void i() {
    }

    private void j() {
    }

    public static void o(boolean z6) {
        G = z6;
    }

    public float A() {
        if (0 == this.f17076h) {
            return 0.0f;
        }
        return this.f17075g.nativeMapSightGetOnScreenHeight(this.f17076h);
    }

    public int B() {
        if (0 == this.f17076h) {
            return 1;
        }
        return this.f17075g.nativeGetMapStyle(this.f17076h);
    }

    public float C() {
        if (0 == this.f17076h) {
            return 0.0f;
        }
        return this.f17075g.nativeGetRotate(this.f17076h);
    }

    public float D() {
        if (0 == this.f17076h) {
            return 1.0f;
        }
        return (float) this.f17075g.nativeGetScale(this.f17076h);
    }

    public int E() {
        if (0 == this.f17076h) {
            return 20;
        }
        return this.f17075g.nativeGetScaleLevel(this.f17076h);
    }

    public float F() {
        if (0 == this.f17076h) {
            return 0.0f;
        }
        return this.f17075g.nativeGetSkew(this.f17076h);
    }

    public float[] G() {
        return this.f17075g.nativeGLProjectMatrix();
    }

    public float[] H() {
        double[] nativeGLViewMatrix = this.f17075g.nativeGLViewMatrix();
        if (nativeGLViewMatrix == null || nativeGLViewMatrix.length == 0) {
            return null;
        }
        PointF b7 = this.f17081m.getProjection().b(ea.d(this.f17081m.S().n()));
        a(nativeGLViewMatrix, 0, b7.x, -b7.y, 0.0d);
        float[] fArr = new float[nativeGLViewMatrix.length];
        for (int i6 = 0; i6 < nativeGLViewMatrix.length; i6++) {
            fArr[i6] = new BigDecimal(nativeGLViewMatrix[i6]).floatValue();
        }
        return fArr;
    }

    public float I() {
        return this.f17075g.nativeGLViewScaleRatio();
    }

    public int[] J() {
        return this.f17075g.nativeGLViewport();
    }

    public void K() {
        if (0 == this.f17076h) {
            return;
        }
        a(new i4());
    }

    public void L() {
        a(new f3());
    }

    public void M() {
        a(new j3());
    }

    @Deprecated
    public boolean N() {
        return ((Boolean) d((CallbackRunnable<y4>) new y4(), (y4) Boolean.TRUE)).booleanValue();
    }

    public boolean O() {
        return ((Boolean) d((CallbackRunnable<b4>) new b4(), (b4) Boolean.FALSE)).booleanValue();
    }

    public boolean Q() {
        return ((Boolean) d((CallbackRunnable<p>) new p(), (p) Boolean.FALSE)).booleanValue();
    }

    public boolean R() {
        return ((Boolean) d((CallbackRunnable<d>) new d(), (d) Boolean.FALSE)).booleanValue();
    }

    public boolean S() {
        return ((Boolean) d((CallbackRunnable<g3>) new g3(), (g3) Boolean.FALSE)).booleanValue();
    }

    public void T() {
        this.f17081m.K();
    }

    public void U() {
        a(new p2());
    }

    public void V() {
        a(new e3());
    }

    public void W() {
        a(new i3());
    }

    public void X() {
        a(new c());
    }

    public void Y() {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new d6());
    }

    public double a(Rect rect, Rect rect2) {
        return ((Double) d((CallbackRunnable<r4>) new r4(rect, rect2), (r4) Double.valueOf(1.0d))).doubleValue();
    }

    public int a(long j6) {
        return ((Integer) c((CallbackRunnable<i2>) new i2(j6), (i2) 0)).intValue();
    }

    public int a(TileOverlayCallback tileOverlayCallback, boolean z6) {
        return ((Integer) d((CallbackRunnable<j>) new j(tileOverlayCallback, z6), (j) (-1))).intValue();
    }

    public int a(CircleInfo circleInfo) {
        return ((Integer) d((CallbackRunnable<y1>) new y1(circleInfo), (y1) (-1))).intValue();
    }

    public int a(MaskLayer maskLayer) {
        return ((Integer) d((CallbackRunnable<y2>) new y2(maskLayer), (y2) 0)).intValue();
    }

    public int a(PolygonInfo polygonInfo) {
        return ((Integer) d((CallbackRunnable<n1>) new n1(polygonInfo), (n1) 0)).intValue();
    }

    public int a(fg fgVar) {
        return ((Integer) d((CallbackRunnable<m3>) new m3(fgVar), (m3) (-1))).intValue();
    }

    public int a(te teVar) {
        return ((Integer) d((CallbackRunnable<j4>) new j4(teVar), (j4) 0)).intValue();
    }

    public int a(String str, float f7, float f8) {
        if (0 == this.f17076h) {
            return 0;
        }
        return ((Integer) d((CallbackRunnable<i5>) new i5(str, f7, f8), (i5) 0)).intValue();
    }

    public int a(byte[] bArr, int i6, boolean z6, boolean z7) {
        return ((Integer) d((CallbackRunnable<f6>) new f6(bArr, i6, z6, z7), (f6) (-1))).intValue();
    }

    public int a(GeoPoint[] geoPointArr, PolylineOptions.Text text) {
        return ((Integer) d((CallbackRunnable<w0>) new w0(geoPointArr, text), (w0) (-1))).intValue();
    }

    public long a(AggregationOverlayInfo aggregationOverlayInfo) {
        return ((Long) d((CallbackRunnable<h1>) new h1(aggregationOverlayInfo), (h1) 0L)).longValue();
    }

    public long a(ArcLineOverlayInfo arcLineOverlayInfo) {
        return ((Long) d((CallbackRunnable<o1>) new o1(arcLineOverlayInfo), (o1) 0L)).longValue();
    }

    public long a(GLModelInfo gLModelInfo) {
        return ((Long) d((CallbackRunnable<a2>) new a2(gLModelInfo), (a2) 0L)).longValue();
    }

    public long a(GroundOverlayInfo groundOverlayInfo) {
        return ((Long) d((CallbackRunnable<v1>) new v1(groundOverlayInfo), (v1) 0L)).longValue();
    }

    public long a(HeatmapInfo heatmapInfo) {
        return ((Long) d((CallbackRunnable<j1>) new j1(heatmapInfo), (j1) 0L)).longValue();
    }

    public long a(IntersectionOverlayInfo intersectionOverlayInfo) {
        return ((Long) d((CallbackRunnable<x1>) new x1(intersectionOverlayInfo), (x1) 0L)).longValue();
    }

    public long a(MarkerInfo markerInfo) {
        return ((Long) d((CallbackRunnable<a1>) new a1(markerInfo), (a1) 0L)).longValue();
    }

    public long a(ScatterPlotInfo scatterPlotInfo) {
        return ((Long) d((CallbackRunnable<q1>) new q1(scatterPlotInfo), (q1) 0L)).longValue();
    }

    public long a(TrailOverlayInfo trailOverlayInfo) {
        return ((Long) d((CallbackRunnable<s1>) new s1(trailOverlayInfo), (s1) 0L)).longValue();
    }

    public PointF a(byte[] bArr, double d7, double d8) {
        return (PointF) e((CallbackRunnable<c3>) new c3(bArr, d7, d8), (c3) new PointF());
    }

    public AnnocationTextResult a(AnnocationText annocationText) {
        return (AnnocationTextResult) d(new z(annocationText), (z) null);
    }

    public GeoPoint a(byte[] bArr, float f7, float f8) {
        return (GeoPoint) e((CallbackRunnable<b3>) new b3(bArr, f7, f8), (b3) new GeoPoint());
    }

    public TappedElement a(float f7, float f8) {
        return (TappedElement) d(new a4(f7, f8), (a4) null);
    }

    public VectorHeatAggregationUnit a(long j6, LatLng latLng) {
        return (VectorHeatAggregationUnit) d(new m1(j6, latLng), (m1) null);
    }

    public String a(GeoPoint geoPoint) {
        return (String) d((CallbackRunnable<k3>) new k3(geoPoint), (k3) "");
    }

    public List<Integer> a(Rect rect, int i6) {
        return (List) d(new a0(rect, i6), (a0) null);
    }

    @Override // com.tencent.mapsdk.internal.t1
    public void a() {
        b(new c4());
    }

    public void a(double d7) {
        a(new a6(d7));
    }

    public void a(double d7, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new y5(d7, z6));
    }

    public void a(float f7) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new h4(f7));
    }

    public void a(float f7, float f8, float f9, float f10) {
        if (0 == this.f17076h) {
            return;
        }
        a(new f5(f7, f8, f9, f10));
    }

    @Override // com.tencent.mapsdk.internal.de
    public void a(float f7, float f8, long j6, String str, String str2) {
        qc qcVar = this.f17081m;
        if (qcVar != null) {
            qcVar.a(f7, f8, j6, str, str2);
        }
    }

    public void a(float f7, float f8, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new s5(f7, f8, z6));
    }

    public void a(float f7, int i6, LatLng latLng) {
        if (0 == this.f17076h) {
            return;
        }
        a(new r5(f7, i6, latLng));
    }

    @Override // com.tencent.mapsdk.internal.pe
    public void a(int i6) {
        qc qcVar = this.f17081m;
        if (qcVar != null) {
            a(new r(qcVar.b()));
        }
    }

    public void a(int i6, float f7) {
        a(new w(i6, f7));
    }

    public void a(int i6, int i7) {
        a(new t(i6, i7));
    }

    public void a(int i6, int i7, int i8) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new z5(i6, i7, i8));
    }

    public void a(int i6, int i7, int i8, int i9) {
        a(new s(i7, i6, i8, i9));
    }

    public void a(int i6, int i7, int i8, int i9, int i10) {
        b(new v(i6, i7, i8, i9, i10));
    }

    public void a(int i6, int i7, boolean z6) {
        a(new w2(i6, i7, z6));
    }

    public void a(int i6, CircleInfo circleInfo) {
        if (this.f17076h == 0 || circleInfo == null) {
            return;
        }
        a(new j2(i6, circleInfo));
    }

    public void a(int i6, PolylineOptions.Text text) {
        a(new y0(i6, text));
    }

    public void a(int i6, boolean z6) {
        a(new q3(i6, z6));
    }

    public void a(long j6, float f7, float f8, float f9) {
        a(new l2(j6, f7, f8, f9));
    }

    public void a(long j6, int i6) {
        a(new k2(j6, i6));
    }

    public void a(long j6, int i6, float f7, boolean z6) {
        a(new c2(j6, i6, f7, z6));
    }

    public void a(long j6, AggregationOverlayInfo aggregationOverlayInfo) {
        a(new i1(j6, aggregationOverlayInfo));
    }

    public void a(long j6, ArcLineOverlayInfo arcLineOverlayInfo) {
        a(new p1(j6, arcLineOverlayInfo));
    }

    public void a(long j6, GLModelInfo gLModelInfo) {
        a(new b2(j6, gLModelInfo));
    }

    public void a(long j6, GroundOverlayInfo groundOverlayInfo) {
        a(new w1(j6, groundOverlayInfo));
    }

    public void a(long j6, HeatmapInfo heatmapInfo) {
        a(new k1(j6, heatmapInfo));
    }

    public void a(long j6, IntersectionOverlayInfo intersectionOverlayInfo) {
        a(new z1(j6, intersectionOverlayInfo));
    }

    public void a(long j6, MarkerInfo markerInfo) {
        a(new d1(j6, markerInfo));
    }

    public void a(long j6, ScatterPlotInfo scatterPlotInfo) {
        a(new r1(j6, scatterPlotInfo));
    }

    public void a(long j6, TrailOverlayInfo trailOverlayInfo) {
        a(new t1(j6, trailOverlayInfo));
    }

    public void a(Rect rect, Rect rect2, boolean z6) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new s4(rect, rect2, z6));
    }

    public void a(GeoPoint geoPoint, float f7, float f8, boolean z6) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new f4(geoPoint, f7, f8, z6));
    }

    public void a(GeoPoint geoPoint, int i6, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new w5(geoPoint, i6, z6));
    }

    public void a(GeoPoint geoPoint, RectF rectF, int i6, int i7, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new e6(rectF, geoPoint, i6, i7, z6));
    }

    public void a(GeoPoint geoPoint, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new t5(geoPoint, z6));
    }

    public void a(be beVar) {
        this.f17077i = beVar;
    }

    public void a(ke keVar) {
        this.f17080l = keVar;
    }

    public void a(le leVar) {
        this.f17075g.setMapCallbackGetGLContext(leVar);
    }

    public void a(TrafficStyle trafficStyle) {
        a(new n2(trafficStyle));
    }

    @Override // com.tencent.mapsdk.internal.r1
    public void a(String str) {
        this.q = str;
    }

    @Override // com.tencent.mapsdk.internal.fe
    public void a(String str, gb gbVar) {
        DataSource dataSource = DataSource.get(gbVar.b);
        ma.a(la.b, "onCancelDownload DataSource:" + dataSource + " uri:" + str);
        if (dataSource == DataSource.TILE_OVERLAY) {
            MapTileID mapTileID = (MapTileID) gbVar.d;
            int tileTag = mapTileID.getTileTag();
            ma.a(la.b, "onCancelDownload tileOverlayId:" + tileTag);
            jg a7 = this.f17081m.d0().a(tileTag);
            if (a7 != null) {
                ma.c(la.b, "onCancelDownload found TileOverlay");
                a7.L().a(str);
                qa.i(la.b, "cancel-count");
                qa.i(la.b, "C/" + mapTileID.getX() + "-" + mapTileID.getY() + "-" + mapTileID.getZ());
                return;
            }
        }
        this.f17078j.a(str);
    }

    public void a(String str, String str2) {
        a(new p0(str, str2));
    }

    public void a(String str, String str2, String str3) {
        a(new e0(str, str2, str3));
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        if (0 == this.f17076h) {
            return;
        }
        a(new o5(str, str2, str3, str4, str5));
    }

    public void a(List<LatLngBounds> list) {
        a(new e4(list));
    }

    public void a(List<MapRouteSection> list, List<GeoPoint> list2) {
        a(new x(list, list2));
    }

    public void a(boolean z6) {
        a(new l0(z6));
    }

    public void a(boolean z6, boolean z7, boolean z8, boolean z9) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new g4(z6, z7, z8, z9));
    }

    public void a(double[] dArr, double[] dArr2, int i6) {
        a(new v0(dArr, dArr2, i6));
    }

    public void a(int[] iArr, int i6) {
        a(new l4(iArr, i6));
    }

    public void a(int[] iArr, int i6, boolean z6) {
        a(new m4(iArr, i6, z6));
    }

    public void a(String[] strArr) {
        if (this.f17076h == 0) {
            return;
        }
        a(new h5(strArr));
    }

    public boolean a(int i6, float f7, float f8) {
        if (i6 == -1) {
            return false;
        }
        return ((Boolean) d((CallbackRunnable<s3>) new s3(i6, f7, f8), (s3) Boolean.FALSE)).booleanValue();
    }

    public boolean a(Context context, ae aeVar, com.tencent.mapsdk.internal.w wVar, String str, String str2, String str3, float f7) {
        int[] iArr = new int[1];
        try {
            i();
            this.f17075g.initCallback(aeVar, wVar, this, this, this, this, this.f17081m.h(), this, this, this, this);
            JNI jni = this.f17075g;
            float d7 = f7.d(context);
            float d8 = f7.d(context);
            Language language = Language.zh;
            this.f17076h = jni.nativeInitEngine(str, str2, str3, d7, 256, d8, iArr, false, 0);
            if (iArr[0] == 0) {
                this.f17075g.nativeSetCenter(this.f17076h, new GeoPoint(39984186, 116307503), false);
                this.f17075g.registerCallback(this.f17076h);
                this.f17075g.nativeSetTrafficColor(this.f17076h, u, w, y, -16777063);
                return true;
            }
            ma.c("init engine fail:" + iArr[0]);
            this.f17076h = 0L;
            return false;
        } finally {
            j();
        }
    }

    public int b(long j6) {
        return ((Integer) c((CallbackRunnable<e2>) new e2(j6), (e2) 0)).intValue();
    }

    public CityTrafficInfo b(String str) {
        return (CityTrafficInfo) d(new c1(str), (c1) null);
    }

    public cg b(GeoPoint geoPoint) {
        if (geoPoint == null) {
            geoPoint = new GeoPoint();
        }
        return (cg) d(new a5(geoPoint), (a5) null);
    }

    @Override // com.tencent.mapsdk.internal.r1
    public String b() {
        return (String) d((CallbackRunnable<z0>) new z0(), (z0) "");
    }

    public void b(float f7) {
        a(new h(f7));
    }

    public void b(float f7, float f8) {
        this.f17075g.scheduleClickOnNextRender(this.f17076h, f7, f8);
    }

    public void b(float f7, float f8, boolean z6) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a((CallbackRunnable<a>) new a(z6, f7, f8), (a) Boolean.FALSE);
    }

    public void b(int i6, int i7) {
        a(new u(i6, i7));
    }

    public void b(int i6, int i7, int i8) {
        if (this.f17076h == 0 || this.f17081m == null) {
            return;
        }
        a(new m(i6, i7, i8));
    }

    public void b(int i6, int i7, int i8, int i9) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new b(i6, i7, i8, i9));
    }

    public void b(int i6, int i7, int i8, int i9, int i10) {
        b(new g0(i6, i7, i8, i9, i10));
    }

    public void b(int i6, boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        this.f17075g.nativeSetMapStyle(this.f17076h, i6, z6);
    }

    public void b(PolygonInfo polygonInfo) {
        a(new u2(polygonInfo));
    }

    public void b(te teVar) {
        if (0 == this.f17076h) {
            return;
        }
        a(new k4(teVar));
    }

    @Override // com.tencent.mapsdk.internal.ge
    public void b(String str, gb gbVar) {
        DataSource dataSource = DataSource.get(gbVar.b);
        ma.a(la.b, "onRequestDownload DataSource:" + dataSource + " uri:" + str);
        if (dataSource == DataSource.TILE_OVERLAY) {
            MapTileID mapTileID = (MapTileID) gbVar.d;
            int tileTag = mapTileID.getTileTag();
            ma.a(la.b, "onRequestDownload tileOverlayId:" + tileTag);
            jg a7 = this.f17081m.d0().a(tileTag);
            if (a7 != null) {
                ma.c(la.b, "onRequestDownload found TileOverlay");
                jb L = a7.L();
                L.a(this.f17079k);
                L.a(str, a7.K(), gbVar.f16603c);
                qa.i(la.b, "req-count");
                qa.i(la.b, "R/" + mapTileID.getX() + "-" + mapTileID.getY() + "-" + mapTileID.getZ());
                return;
            }
        }
        this.f17078j.a(str, this.f17081m.q0());
    }

    public void b(List<IndoorCellInfo> list) {
        a(new o2(list));
    }

    public void b(boolean z6) {
        a(new m0(z6));
    }

    public boolean b(int i6) {
        return ((Boolean) d((CallbackRunnable<u0>) new u0(i6), (u0) Boolean.FALSE)).booleanValue();
    }

    public int c(String str) {
        return ((Integer) d((CallbackRunnable<q0>) new q0(str), (q0) (-1))).intValue();
    }

    public String c(GeoPoint geoPoint) {
        return (String) d(new d5(geoPoint), (d5) null);
    }

    @Override // com.tencent.mapsdk.internal.t1
    public void c() {
        b(new n4());
    }

    public void c(float f7) {
        a(new i(f7));
    }

    public void c(float f7, float f8) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new c6(f7, f8));
    }

    public void c(int i6) {
        if (this.f17076h == 0 || i6 < 0 || this.f17081m == null) {
            return;
        }
        a(new t2(i6));
    }

    public void c(int i6, int i7) {
        a(new x2(i6, i7));
    }

    public void c(int i6, boolean z6) {
        qc qcVar;
        if (0 == this.f17076h || (qcVar = this.f17081m) == null) {
            return;
        }
        qcVar.a(new b6(i6, z6));
    }

    public void c(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new z3(fgVar));
    }

    public void c(boolean z6) {
        a(new k0(z6));
    }

    public float[] c(long j6) {
        return (float[]) c((CallbackRunnable<f2>) new f2(j6), (f2) new float[0]);
    }

    @Override // com.tencent.mapsdk.internal.ke
    public void d() {
        ke keVar = this.f17080l;
        if (keVar != null) {
            keVar.d();
        }
    }

    public void d(int i6) {
        if (this.f17076h == 0 || this.f17081m == null) {
            return;
        }
        a(new o(i6));
    }

    public void d(int i6, int i7) {
        if (0 == this.f17076h) {
            return;
        }
        a(new p4(i6, i7));
    }

    public void d(GeoPoint geoPoint) {
        this.f17081m.a(new v5(geoPoint));
    }

    public void d(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new y3(fgVar));
    }

    public void d(boolean z6) {
        a(new d4(z6));
    }

    public boolean d(String str) {
        return ((Boolean) d((CallbackRunnable<d3>) new d3(str), (d3) Boolean.FALSE)).booleanValue();
    }

    public String[] d(long j6) {
        return (String[]) c((CallbackRunnable<g2>) new g2(j6), (g2) new String[0]);
    }

    @Override // com.tencent.mapsdk.internal.t1
    public int e() {
        return ((Integer) d((CallbackRunnable<r3>) new r3(), (r3) (-1))).intValue();
    }

    public int e(long j6) {
        return ((Integer) d((CallbackRunnable<b1>) new b1(j6), (b1) 0)).intValue();
    }

    public int e(String str) {
        return ((Integer) d((CallbackRunnable<j5>) new j5(str), (j5) 0)).intValue();
    }

    public void e(int i6) {
        a(new x0(i6));
    }

    public void e(int i6, int i7) {
        a(new e1(i6, i7));
    }

    public void e(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new w3(fgVar));
    }

    public void e(boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new o4(z6));
    }

    @Override // com.tencent.mapsdk.internal.r1
    public String f() {
        String str = this.q;
        this.q = null;
        return str;
    }

    public void f(int i6) {
        a(new a3(i6));
    }

    public void f(int i6, int i7) {
        a(new l(i6, i7));
    }

    public void f(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new v3(fgVar));
    }

    public void f(String str) {
        a(new b0(str));
    }

    public void f(boolean z6) {
        if (this.f17076h == 0) {
            return;
        }
        a(new g5(z6));
    }

    public String[] f(long j6) {
        return (String[]) c((CallbackRunnable<h2>) new h2(j6), (h2) new String[0]);
    }

    public void g() {
        a(new y());
    }

    public void g(int i6) {
        if (this.f17076h == 0 || this.f17081m == null) {
            return;
        }
        a(new n(i6));
    }

    public void g(int i6, int i7) {
        b(new k(i6, i7));
    }

    public void g(long j6) {
        a(new g1(j6));
    }

    public void g(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new x3(fgVar));
    }

    public void g(String str) {
        a(new l3(str));
    }

    public void g(boolean z6) {
        if (this.f17076h == 0 || this.f17081m == null) {
            return;
        }
        a(new t4(z6));
    }

    public void h() {
        try {
            i();
            jb jbVar = this.f17078j;
            if (jbVar != null) {
                jbVar.b(this.f17079k);
                this.f17078j.a();
            }
            if (this.f17076h != 0) {
                long j6 = this.f17076h;
                synchronized (this.r) {
                    this.f17076h = 0L;
                    this.f17075g.nativeDestroyEngine(j6);
                }
            }
            this.f17081m = null;
            this.f17075g.destory();
        } finally {
            j();
        }
    }

    public void h(int i6) {
        a(new v4(i6));
    }

    public void h(int i6, int i7) {
        a(new z2(i6, i7));
    }

    public void h(long j6) {
        a(new l1(j6));
    }

    public void h(fg fgVar) {
        a(new p3(fgVar));
    }

    public void h(String str) {
        if (0 == this.f17076h) {
            return;
        }
        a(new q4(str));
    }

    public void h(boolean z6) {
        if (0 == this.f17076h || this.f17081m == null) {
            return;
        }
        a(new u4(z6));
    }

    public void i(int i6) {
        a(new w4(i6));
    }

    public void i(long j6) {
        a(new m2(j6));
    }

    public void i(fg fgVar) {
        a(new h0(fgVar));
    }

    public void i(String str) {
        if (0 == this.f17076h) {
            return;
        }
        a(new n5(str));
    }

    public void i(boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new l5(z6));
    }

    public void j(int i6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new e5(i6));
    }

    public void j(long j6) {
        a(new d2(j6));
    }

    public void j(fg fgVar) {
        a(new t3(fgVar));
    }

    public void j(String str) {
        if (this.f17076h == 0) {
            return;
        }
        a(new i0(str));
    }

    public void j(boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new p5(z6));
    }

    public void k(int i6) {
        a(new c0(i6));
    }

    public void k(fg fgVar) {
        if (this.f17076h == 0) {
            return;
        }
        a(new u3(fgVar));
    }

    public void k(String str) {
        if (this.f17076h == 0) {
            return;
        }
        a(new j0(str));
    }

    public void k(boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new m5(z6));
    }

    public ah[] k() {
        return (ah[]) d(new r0(), (r0) null);
    }

    public void l(int i6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new k5(i6));
    }

    public void l(fg fgVar) {
        a(new n3(fgVar));
    }

    public void l(boolean z6) {
        if (0 == this.f17076h) {
            return;
        }
        a(new q5(z6));
    }

    public boolean l() {
        if (this.f17076h == 0) {
            return false;
        }
        if (SystemClock.elapsedRealtime() - this.o > 560) {
            this.f17075g.nativeClearDownloadURLCache(this.f17076h);
            this.o = SystemClock.elapsedRealtime();
        }
        return this.f17075g.nativeGenerateTextures(this.f17076h);
    }

    public void m(int i6) {
        a(new f1(i6));
    }

    public void m(boolean z6) {
        a(new e(z6));
    }

    public boolean m() {
        return ((Boolean) d((CallbackRunnable<u1>) new u1(), (u1) Boolean.FALSE)).booleanValue();
    }

    public GeoPoint n() {
        return (GeoPoint) d((CallbackRunnable<x5>) new x5(), (x5) new GeoPoint());
    }

    public void n(int i6) {
        a(new f(i6));
    }

    public void n(boolean z6) {
        a(new h3(z6));
    }

    public Context o() {
        qc qcVar = this.f17081m;
        if (qcVar == null) {
            return null;
        }
        return qcVar.getContext();
    }

    public void o(int i6) {
        a(new g(i6));
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChangeStopped() {
        qc qcVar = this.f17081m;
        if (qcVar != null) {
            qcVar.r0();
        }
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChanged() {
        qc qcVar = this.f17081m;
        if (qcVar != null) {
            qcVar.s0();
        }
    }

    @Override // com.tencent.mapsdk.internal.oe
    public void onMapLoaded() {
        qc qcVar = this.f17081m;
        if (qcVar != null) {
            qcVar.t0();
        }
    }

    public String p() {
        return (String) d(new c5(), (c5) null);
    }

    public void p(int i6) {
        a(new u5(i6));
    }

    public void p(boolean z6) {
        a(new q(z6));
    }

    public String q() {
        return (String) d((CallbackRunnable<o0>) new o0(), (o0) "");
    }

    public void q(boolean z6) {
        if (this.f17076h == 0) {
            return;
        }
        a(new f0(z6));
    }

    public Object r() {
        return this.r;
    }

    public long s() {
        return this.f17076h;
    }

    public Rect t() {
        return (Rect) d(new b5(), (b5) null);
    }

    public int u() {
        return ((Integer) d((CallbackRunnable<x4>) new x4(), (x4) (-1))).intValue();
    }

    public String[] v() {
        return (String[]) d(new z4(), (z4) null);
    }

    public int w() {
        return ((Integer) d((CallbackRunnable<d0>) new d0(), (d0) 0)).intValue();
    }

    public String x() {
        return (String) d(new s0(), (s0) null);
    }

    public String y() {
        return (String) d((CallbackRunnable<n0>) new n0(), (n0) "");
    }

    public ArrayList<MapPoi> z() {
        return (ArrayList) d(new t0(), (t0) null);
    }
}
