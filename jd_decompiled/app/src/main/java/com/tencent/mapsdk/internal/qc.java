package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import android.view.WindowManager;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.lib.callbacks.TileOverlayCallback;
import com.tencent.map.lib.models.AnnocationText;
import com.tencent.map.lib.models.AnnocationTextResult;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.IndoorCellInfo;
import com.tencent.map.lib.models.MaskLayer;
import com.tencent.map.lib.models.OverlayListenerInfo;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.map.lib.models.SubMarkerInfo;
import com.tencent.map.sdk.utilities.visualization.aggregation.AggregationOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.glmodel.GLModelOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.heatmap.GradientVectorOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.od.ArcLineOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.scatterplot.BitmapScatterPlotOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.scatterplot.DotScatterPlotOverlayProvider;
import com.tencent.map.sdk.utilities.visualization.trails.TrailOverlayProvider;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.tencentmap.mapsdk.maps.CustomRender;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapComponent;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.GroundOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IntersectionOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficStyle;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.VectorHeatOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.VectorOverlayProvider;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class qc extends s1 implements e1, i5 {
    private static final int i0 = 50;
    private static final int j0 = 6;
    private static final int k0 = 100;
    private static final int l0 = 100;
    private static final float m0 = 2.0f;
    private f A;
    private v B;
    private final LinkedBlockingQueue<b> C;
    private ce D;
    private xd E;
    private volatile boolean F;
    private za<Integer, Integer> G;
    private boolean H;
    private yd I;
    private boolean J;
    private d K;
    private e L;
    private gh M;
    private boolean N;
    private boolean O;
    private int P;
    private int Q;
    private Rect R;
    private float S;
    private float T;
    private boolean U;
    private int V;
    private int W;
    private Rect X;
    private CustomRender Y;
    private boolean Z;
    private int a0;
    private oe b0;
    private og c0;
    private List<me> d0;
    private String e0;
    private i1 f0;
    private TencentMap.OnVectorOverlayClickListener g0;
    private v4 h0;

    /* renamed from: n */
    private final int f17049n;
    private qi o;
    private bc p;
    private d0 q;
    private ch r;
    private j1 s;
    private pc t;
    private a1 u;
    private s4 v;
    private Rect w;
    private boolean x;
    private boolean y;
    private w z;

    /* loaded from: classes9.dex */
    public class a implements b {
        public a() {
            qc.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.qc.b
        public void a(GL10 gl10) {
            if (qc.this.o != null) {
                qc.this.p.b(gl10);
            }
        }
    }

    /* loaded from: classes9.dex */
    public interface b {
        void a(GL10 gl10);
    }

    /* loaded from: classes9.dex */
    public enum c {
        UnderMainMap(2),
        Under3DBuiding(4),
        UnderHandDraw(6),
        UnderPoi(8),
        UnderToplayer(10),
        AboveToplayer(12);
        
        public int a;

        c(int i2) {
            this.a = i2;
        }
    }

    /* loaded from: classes9.dex */
    public class d {
        private List<c5> a = new CopyOnWriteArrayList();

        public d() {
            qc.this = r1;
        }

        private synchronized void a(Language language) {
            for (c5 c5Var : this.a) {
                if (c5Var != null) {
                    c5Var.a(language);
                }
            }
        }

        public Language a() {
            if (qc.this.o != null && qc.this.o.w() == 1) {
                return Language.en;
            }
            return Language.zh;
        }

        public void a(c5 c5Var) {
            if (c5Var == null) {
                return;
            }
            synchronized (this.a) {
                if (!this.a.contains(c5Var)) {
                    this.a.add(c5Var);
                }
            }
        }

        public void b(c5 c5Var) {
            if (c5Var == null) {
                return;
            }
            this.a.remove(c5Var);
        }

        public void b(Language language) {
            if (qc.this.o != null) {
                qc.this.o.k(language.ordinal());
                qc.this.F = true;
                qc.this.w0();
                a(language);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e implements c5 {
        private List<MapRouteSection> a;
        private List<GeoPoint> b;

        public e() {
            qc.this = r1;
            r1.a(this);
        }

        public void a() {
            qc.this.o.g();
            this.a = null;
            this.b = null;
        }

        @Override // com.tencent.mapsdk.internal.c5
        public void a(Language language) {
            if (language != Language.zh) {
                qc.this.o.g();
            } else if (this.a == null || this.b == null) {
            } else {
                qc.this.o.a(this.a, this.b);
            }
        }

        public void a(List<MapRouteSection> list, List<GeoPoint> list2) {
            this.a = list;
            this.b = list2;
            qc.this.o.a(list, list2);
        }

        public void b() {
            qc.this.b(this);
        }
    }

    /* loaded from: classes9.dex */
    public class f {
        private final ArrayList<g> a;
        private g b;

        private f() {
            qc.this = r1;
            this.a = new ArrayList<>();
        }

        public /* synthetic */ f(qc qcVar, a aVar) {
            this();
        }

        private Bitmap a(GL10 gl10, int i2, int i3) {
            int i4 = i2 * i3;
            int[] iArr = new int[i4];
            int[] iArr2 = new int[i4];
            IntBuffer wrap = IntBuffer.wrap(iArr);
            wrap.position(0);
            gl10.glReadPixels((qc.this.w.width() - i2) / 2, (qc.this.w.height() - i3) / 2, i2, i3, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, wrap);
            for (int i5 = 0; i5 < i3; i5++) {
                for (int i6 = 0; i6 < i2; i6++) {
                    int i7 = iArr[(i5 * i2) + i6];
                    iArr2[(((i3 - i5) - 1) * i2) + i6] = (i7 & (-16711936)) | ((i7 << 16) & 16711680) | ((i7 >> 16) & 255);
                }
            }
            try {
                return Bitmap.createBitmap(iArr2, i2, i3, Bitmap.Config.RGB_565);
            } catch (OutOfMemoryError unused) {
                return Bitmap.createBitmap(iArr2, i2, i3, Bitmap.Config.RGB_565);
            }
        }

        private void a(g gVar) {
            if (gVar == null || gVar.d()) {
                return;
            }
            gVar.e();
            synchronized (this.a) {
                this.a.remove(gVar);
            }
            qc.this.q.N();
        }

        public void a(t4 t4Var, Rect rect, j5 j5Var, int i2, int i3) {
            g gVar = new g(qc.this, t4Var, rect, j5Var, i2, i3, null);
            synchronized (this.a) {
                this.a.add(gVar);
            }
            ma.c("snapshot addSnapshotRequest");
            qc.this.w0();
        }

        private g b() {
            g gVar;
            synchronized (this.a) {
                gVar = this.a.size() > 0 ? this.a.get(0) : null;
            }
            return gVar;
        }

        public synchronized g a(GL10 gl10) {
            g b = b();
            this.b = b;
            if (b == null) {
                return null;
            }
            if (b.b()) {
                a(this.b);
                return null;
            }
            qc.this.q.P();
            Rect rect = this.b.f17063f;
            int i2 = this.b.d;
            int i3 = this.b.f17062e;
            Rect rect2 = new Rect();
            int d = (int) (f7.d(qc.this.getContext()) * 20.0f);
            int width = ((qc.this.w.width() - i2) / 2) + d;
            rect2.right = width;
            rect2.left = width;
            int height = ((qc.this.w.height() - i3) / 2) + d;
            rect2.bottom = height;
            rect2.top = height;
            qc.this.q.c(rect, rect2);
            return this.b;
        }

        public synchronized void a() {
            ma.c("snapshot cancel");
            synchronized (this.a) {
                this.a.clear();
            }
            g gVar = this.b;
            if (gVar != null) {
                gVar.a();
            }
        }

        public synchronized void a(GL10 gl10, pc pcVar, Projection projection) {
            g gVar = this.b;
            if (gVar != null && !gVar.b()) {
                int i2 = this.b.d;
                int i3 = this.b.f17062e;
                j5 j5Var = this.b.b;
                t4 t4Var = this.b.f17061c;
                Bitmap a = a(gl10, i2, i3);
                if (j5Var != null && !this.b.d()) {
                    j5Var.a(a, t4Var);
                }
                a(this.b);
            }
        }

        public synchronized void b(GL10 gl10, pc pcVar, Projection projection) {
            if (qc.this.o == null) {
                return;
            }
            t4 t4Var = this.b.f17061c;
            if (t4Var != null) {
                t4Var.a(gl10);
            }
            pcVar.c();
        }

        public boolean c() {
            boolean z;
            synchronized (this.a) {
                ArrayList<g> arrayList = this.a;
                z = arrayList == null || arrayList.isEmpty();
            }
            return z;
        }
    }

    /* loaded from: classes9.dex */
    public class g {

        /* renamed from: i */
        public static final int f17057i = 0;

        /* renamed from: j */
        public static final int f17058j = 1;

        /* renamed from: k */
        public static final int f17059k = 2;

        /* renamed from: l */
        public static final int f17060l = 3;
        private int a;
        private j5 b;

        /* renamed from: c */
        private t4 f17061c;
        private int d;

        /* renamed from: e */
        private int f17062e;

        /* renamed from: f */
        private Rect f17063f;

        /* renamed from: g */
        private int f17064g;

        private g(t4 t4Var, Rect rect, j5 j5Var, int i2, int i3) {
            qc.this = r1;
            this.a = 0;
            this.f17061c = t4Var;
            this.f17063f = rect;
            this.b = j5Var;
            this.d = i2;
            this.f17062e = i3;
            this.f17064g = 0;
        }

        public /* synthetic */ g(qc qcVar, t4 t4Var, Rect rect, j5 j5Var, int i2, int i3, a aVar) {
            this(t4Var, rect, j5Var, i2, i3);
        }

        public static /* synthetic */ int d(g gVar) {
            int i2 = gVar.a;
            gVar.a = i2 + 1;
            return i2;
        }

        public void a() {
            this.f17064g = 1;
            this.a = 0;
        }

        public void a(int i2) {
            this.f17064g = i2;
        }

        public boolean b() {
            return this.f17064g == 1;
        }

        public boolean c() {
            StringBuilder sb = new StringBuilder();
            sb.append("is processing:");
            sb.append(this.f17064g == 3);
            ma.c(sb.toString());
            return this.f17064g == 3;
        }

        public synchronized boolean d() {
            return this.f17064g == 2;
        }

        public synchronized void e() {
            this.f17064g = 2;
            this.a = 0;
        }
    }

    public qc(Context context, TencentMapOptions tencentMapOptions, p1 p1Var) {
        super(context, tencentMapOptions, p1Var);
        int i2;
        this.F = false;
        this.H = true;
        this.J = true;
        this.N = true;
        this.O = false;
        this.P = 0;
        this.Q = 0;
        this.S = 0.5f;
        this.T = 0.5f;
        this.U = true;
        this.V = 18;
        this.W = 14;
        this.h0 = null;
        a1 a1Var = (a1) p1Var;
        this.u = a1Var;
        this.f0 = new i1(a1Var, this);
        this.o = new qi(context, this);
        this.B = new v(this);
        this.w = new Rect();
        j1 j1Var = new j1(this.f0, this);
        this.s = j1Var;
        this.u.a(j1Var);
        this.c0 = new og(getContext(), this);
        this.v = new y(this);
        d0 d0Var = new d0(this);
        this.q = d0Var;
        this.o.a(d0Var);
        bc bcVar = new bc(100);
        this.p = bcVar;
        this.t = new pc(this, bcVar, this.o);
        this.A = new f(this, null);
        this.C = new LinkedBlockingQueue<>();
        this.d0 = new CopyOnWriteArrayList();
        if (getContext() != null) {
            double d2 = getContext().getResources().getDisplayMetrics().density * 6.0f;
            Double.isNaN(d2);
            i2 = (int) (d2 + 0.5d);
        } else {
            i2 = 6;
        }
        this.f17049n = i2;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        f(windowManager.getDefaultDisplay().getWidth(), windowManager.getDefaultDisplay().getHeight());
    }

    private void C0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.W();
        }
    }

    private void a(int i2, int i3, int i4, int i5, boolean z) {
        this.o.b(i2, i3, i4, i5);
        this.P = i4;
        this.Q = i5;
        if (z) {
            a(this.S, this.T, this.U);
        }
    }

    private boolean a(Context context, ae aeVar) {
        String j2 = this.D.j();
        String h2 = this.D.h();
        String c2 = this.D.c();
        try {
            if (!this.E.a()) {
                j2 = this.D.i();
            }
            String str = j2;
            ma.a(la.f16819f, "newEngine config:" + str);
            this.z.b();
            return this.o.a(context, aeVar, this.z, str, h2, c2, 2.0f);
        } finally {
            this.z.d();
        }
    }

    private Point[] a(Rect rect) {
        return new Point[]{new Point(rect.centerX(), rect.centerY()), new Point(rect.left, rect.top), new Point(rect.left, rect.bottom), new Point(rect.right, rect.top), new Point(rect.right, rect.bottom)};
    }

    private qd b(q5 q5Var) {
        qd qdVar = new qd(q5Var.u, q5Var.b);
        if (!TextUtils.isEmpty(q5Var.f17022h)) {
            SubMarkerInfo subMarkerInfo = new SubMarkerInfo();
            subMarkerInfo.iconName(q5Var.f17022h);
            subMarkerInfo.iconWidth(q5Var.f17023i);
            subMarkerInfo.iconHeight(q5Var.f17024j);
            subMarkerInfo.avoidAnnotation(true);
            subMarkerInfo.avoidOtherMarker(true);
            qdVar.subMarkerInfo(subMarkerInfo);
        }
        qdVar.iconWidth(q5Var.f17018c);
        qdVar.iconHeight(q5Var.d);
        qdVar.avoidAnnotation(true);
        qdVar.avoidOtherMarker(true);
        float f2 = q5Var.f17021g;
        qdVar.scale(f2, f2);
        qdVar.priority(q5Var.r);
        qdVar.displayLevel(2);
        qdVar.minScaleLevel(q5Var.s);
        qdVar.maxScaleLevel(q5Var.t);
        return qdVar;
    }

    private void b(GL10 gl10) {
        if (this.C.size() == 0) {
            return;
        }
        boolean z = true;
        while (z) {
            b poll = this.C.poll();
            if (poll != null) {
                try {
                    poll.a(gl10);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                z = false;
            }
        }
    }

    private void f(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.d(z);
        }
    }

    private void i0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.M();
        }
    }

    private boolean l0() {
        return ((double) this.q.v()) > 1.0E-10d;
    }

    private void w(boolean z) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.q(z);
    }

    public void A0() {
        ch chVar;
        if (!this.y || (chVar = this.r) == null) {
            return;
        }
        chVar.f();
    }

    public void B0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.V();
            this.x = true;
        }
    }

    @Override // com.tencent.mapsdk.internal.o1
    public Map<Class<? extends TencentMapComponent.Component>, Class<? extends TencentMapComponent.Component>> C() {
        HashMap hashMap = new HashMap();
        hashMap.put(y3.class, oh.class);
        return hashMap;
    }

    public void D0() {
        this.A.a();
    }

    public void E0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.c();
        }
    }

    public int H() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.e();
        }
        return -1;
    }

    public void I() {
        if (this.L == null) {
            this.L = new e();
        }
        this.L.a();
    }

    public void J() {
        og ogVar = this.c0;
        if (ogVar != null) {
            ogVar.d();
        }
        og.a(getContext());
        a((ke) null);
        this.b0 = null;
        this.q.b(this);
        this.d0.clear();
        e eVar = this.L;
        if (eVar != null) {
            eVar.b();
        }
        this.q.T();
        this.H = true;
        gh ghVar = this.M;
        if (ghVar != null) {
            ghVar.a();
        }
        j1 j1Var = this.s;
        if (j1Var != null) {
            j1Var.e();
        }
        ch chVar = this.r;
        if (chVar != null) {
            chVar.c();
        }
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.h();
        }
    }

    public void K() {
        this.F = true;
    }

    public boolean L() {
        qi qiVar = this.o;
        if (qiVar == null) {
            return false;
        }
        return qiVar.l();
    }

    public xd M() {
        return this.E;
    }

    public String[] N() {
        Point[] a2 = a(this.q.i());
        HashSet hashSet = new HashSet();
        for (Point point2 : a2) {
            String a3 = a(new GeoPoint(point2.y, point2.x));
            if (!e7.b(a3)) {
                hashSet.add(a3);
            }
        }
        return (String[]) hashSet.toArray(new String[0]);
    }

    public String O() {
        return a(this.q.h());
    }

    public String P() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.p();
        }
        return null;
    }

    public Rect Q() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.t();
        }
        return null;
    }

    public String[] R() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.v();
        }
        return null;
    }

    public qi S() {
        return this.o;
    }

    public Language T() {
        if (this.K == null) {
            this.K = new d();
        }
        return this.K.a();
    }

    public pc U() {
        return this.t;
    }

    public o1 V() {
        a1 a1Var = this.u;
        if (a1Var == null) {
            return null;
        }
        return a1Var.getMapContext();
    }

    public String W() {
        qi qiVar = this.o;
        if (qiVar == null) {
            return null;
        }
        return qiVar.f();
    }

    public String X() {
        qi qiVar = this.o;
        if (qiVar == null) {
            return null;
        }
        return qiVar.x();
    }

    public ArrayList<MapPoi> Y() {
        qi qiVar = this.o;
        if (qiVar == null) {
            return null;
        }
        return qiVar.z();
    }

    public i1 Z() {
        return this.f0;
    }

    public float a(double d2, GeoPoint geoPoint) {
        s4 s4Var;
        if (geoPoint != null && this.B != null && (s4Var = this.v) != null) {
            double latitudeE6 = geoPoint.getLatitudeE6();
            Double.isNaN(latitudeE6);
            double metersPerPixel = s4Var.metersPerPixel(latitudeE6 / 1000000.0d);
            if (metersPerPixel != 0.0d) {
                return (float) (d2 / metersPerPixel);
            }
        }
        return 0.0f;
    }

    public int a(int i2, int i3, int i4, int i5, int i6, float f2) {
        if (this.o == null) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > 255) {
            i2 = 255;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 > 255) {
            i3 = 255;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        if (i4 > 255) {
            i4 = 255;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        if (i5 > 255) {
            i5 = 255;
        }
        MaskLayer maskLayer = new MaskLayer();
        maskLayer.color = new int[]{i2, i3, i4, 255 - i5};
        Rect rect = this.w;
        if (rect != null) {
            maskLayer.width = rect.width();
            maskLayer.height = this.w.height();
        }
        maskLayer.zIndex = f2;
        maskLayer.layer = i6;
        return this.o.a(maskLayer);
    }

    @Deprecated
    public int a(TileOverlayCallback tileOverlayCallback, boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.a(tileOverlayCallback, z);
        }
        return -1;
    }

    public int a(PolygonInfo polygonInfo) {
        qi qiVar = this.o;
        if (qiVar == null || polygonInfo == null) {
            return -1;
        }
        int a2 = qiVar.a(polygonInfo);
        if (this.G == null) {
            this.G = new za<>();
        }
        this.G.a(Integer.valueOf(a2), Integer.valueOf(polygonInfo.borderLineId));
        return a2;
    }

    public int a(q5 q5Var) {
        if (this.s == null || q5Var.u == null) {
            return -1;
        }
        od odVar = (od) this.s.a((j1) b(q5Var));
        if (odVar != null) {
            return odVar.l();
        }
        return -1;
    }

    public int a(String str) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return -1;
        }
        return qiVar.c(str);
    }

    public int a(String str, float f2, float f3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.a(str, f2, f3);
        }
        return 0;
    }

    public AnnocationTextResult a(AnnocationText annocationText) {
        return this.o.a(annocationText);
    }

    public TappedElement a(float f2, float f3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.a(f2, f3);
        }
        return null;
    }

    public Circle a(CircleOptions circleOptions) {
        i1 i1Var = this.f0;
        if (i1Var == null || circleOptions == null) {
            return null;
        }
        return i1Var.a(circleOptions);
    }

    public GroundOverlay a(GroundOverlayOptions groundOverlayOptions) {
        if (this.o == null) {
            return null;
        }
        if (w() != null) {
            w().i().b();
        }
        return (GroundOverlay) this.s.a((j1) new hd(this.o.o(), groundOverlayOptions));
    }

    public IntersectionOverlay a(IntersectionOverlayOptions intersectionOverlayOptions) {
        if (this.o == null) {
            return null;
        }
        return (IntersectionOverlay) this.s.a((j1) new nd(intersectionOverlayOptions));
    }

    public Marker a(MarkerOptions markerOptions) {
        i1 i1Var = this.f0;
        if (i1Var == null || markerOptions == null) {
            return null;
        }
        return i1Var.a(markerOptions);
    }

    public Polyline a(PolylineOptions polylineOptions) {
        i1 i1Var = this.f0;
        if (i1Var == null || polylineOptions == null) {
            return null;
        }
        return i1Var.a(polylineOptions);
    }

    public VectorHeatOverlay a(VectorHeatOverlayOptions vectorHeatOverlayOptions) {
        if (this.o == null) {
            return null;
        }
        if (w() != null) {
            w().r().b();
        }
        return (VectorHeatOverlay) this.s.a((j1) new xc(vectorHeatOverlayOptions));
    }

    public <V extends VectorOverlay> V a(VectorOverlayProvider vectorOverlayProvider) {
        if (this.o == null) {
            return null;
        }
        if (vectorOverlayProvider instanceof AggregationOverlayProvider) {
            if (w() != null) {
                w().r().b();
            }
            return (wc) this.s.a((j1) new xc((AggregationOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof GradientVectorOverlayProvider) {
            if (w() != null) {
                w().j().b();
            }
            return (id) this.s.a((j1) new kd((GradientVectorOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof ArcLineOverlayProvider) {
            if (w() != null) {
                w().c().b();
            }
            return (zc) this.s.a((j1) new bd((ArcLineOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof DotScatterPlotOverlayProvider) {
            if (w() != null) {
                w().g().b();
            }
            return (rd) this.s.a((j1) new td(this.o.o(), (DotScatterPlotOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof BitmapScatterPlotOverlayProvider) {
            if (w() != null) {
                w().d().b();
            }
            return (rd) this.s.a((j1) new td(this.o.o(), (BitmapScatterPlotOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof TrailOverlayProvider) {
            if (w() != null) {
                w().p().b();
            }
            return (ud) this.s.a((j1) new wd((TrailOverlayProvider) vectorOverlayProvider));
        } else if (vectorOverlayProvider instanceof GLModelOverlayProvider) {
            if (w() != null) {
                w().h().b();
            }
            return (ed) this.s.a((j1) new dd((GLModelOverlayProvider) vectorOverlayProvider));
        } else {
            return null;
        }
    }

    public String a(GeoPoint geoPoint) {
        yd ydVar;
        String a2;
        if (this.E == null || (ydVar = this.I) == null || (a2 = ydVar.a(geoPoint)) == null) {
            qi qiVar = this.o;
            return qiVar == null ? "" : qiVar.a(geoPoint);
        }
        return a2;
    }

    public List<Integer> a(Rect rect, int i2) {
        return this.o.a(rect, i2);
    }

    @Override // com.tencent.mapsdk.internal.i5
    public void a(double d2) {
    }

    public void a(float f2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(f2);
        }
    }

    public void a(float f2, float f3, long j2, String str, String str2) {
        TencentMap.OnVectorOverlayClickListener onVectorOverlayClickListener;
        if (j2 > 0) {
            Pair<VectorOverlay, TencentMap.IClickedObject> a2 = this.s.a(getProjection().fromScreenLocation(new Point((int) f2, (int) f3)), j2, str, str2);
            Object obj = a2.first;
            if (obj != null && (onVectorOverlayClickListener = this.g0) != null) {
                onVectorOverlayClickListener.onClicked((VectorOverlay) obj, (TencentMap.IClickedObject) a2.second);
            }
        } else {
            this.h0.onSingleTap(f2, f3);
        }
        this.h0 = null;
    }

    public void a(float f2, float f3, boolean z) {
        this.S = f2;
        this.T = f3;
        double d2 = f2;
        double d3 = f3;
        Rect rect = this.w;
        if (rect != null) {
            if (this.Q > 0) {
                Double.isNaN(d3);
                double height = rect.height();
                Double.isNaN(height);
                double d4 = (0.5d - d3) * height;
                double d5 = this.Q;
                Double.isNaN(d5);
                d3 = 0.5d - (d4 / d5);
            }
            if (this.P > 0) {
                Double.isNaN(d2);
                double width = this.w.width();
                Double.isNaN(width);
                double d6 = (0.5d - d2) * width;
                double d7 = this.P;
                Double.isNaN(d7);
                d2 = 0.5d - (d6 / d7);
            }
        }
        this.o.b((float) d2, (float) d3, z);
    }

    public void a(float f2, int i2, LatLng latLng) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(f2, i2, latLng);
        }
    }

    public void a(int i2, float f2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(i2, f2);
        }
    }

    public void a(int i2, int i3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(i2, i3);
        }
    }

    public void a(int i2, int i3, int i4) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(i2, i3, i4);
        }
    }

    public void a(int i2, int i3, int i4, int i5) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(i2, i3, i4, i5);
        }
    }

    public void a(int i2, boolean z) {
        za<Integer, Integer> zaVar;
        if (this.o == null || (zaVar = this.G) == null) {
            return;
        }
        Integer a2 = zaVar.a((za<Integer, Integer>) Integer.valueOf(i2));
        this.o.a(i2, a2 != null ? a2.intValue() : 0, z);
    }

    public void a(Rect rect, int i2, int i3, j5 j5Var) {
        if (rect == null || i2 <= 0 || i3 <= 0 || j5Var == null) {
            return;
        }
        this.A.a(null, rect, j5Var, i2, i3);
    }

    public void a(Rect rect, Rect rect2, boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(rect, rect2, z);
        }
    }

    public void a(GeoPoint geoPoint, float f2, float f3, boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(geoPoint, f2, f3, z);
        }
    }

    public void a(GeoPoint geoPoint, RectF rectF, boolean z) {
        Rect rect;
        if (rectF != null && (rect = this.R) != null) {
            rectF.left -= rect.left;
            rectF.top -= rect.top;
            rectF.right -= rect.right;
            rectF.bottom -= rect.bottom;
        }
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(geoPoint, rectF, this.W, this.V, z);
        }
    }

    public void a(c5 c5Var) {
        if (this.K == null) {
            this.K = new d();
        }
        this.K.a(c5Var);
    }

    public void a(ce ceVar) {
        if (ceVar == null) {
            return;
        }
        this.D = ceVar;
        if (this.o != null) {
            String j2 = ceVar.j();
            String h2 = this.D.h();
            String c2 = this.D.c();
            ma.a(la.f16819f, "resetMapPath config:" + j2);
            if (e7.b(c2) || e7.b(h2)) {
                return;
            }
            try {
                this.z.b();
                this.o.a(j2, h2, c2);
            } finally {
                this.z.d();
            }
        }
    }

    public void a(ke keVar) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(keVar);
        }
    }

    public void a(le leVar) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(leVar);
        }
    }

    public void a(me meVar) {
        List<me> list = this.d0;
        if (list != null) {
            list.add(meVar);
        }
    }

    public void a(oe oeVar) {
        this.b0 = oeVar;
    }

    public void a(b bVar) {
        try {
            this.C.put(bVar);
        } catch (InterruptedException e2) {
            ma.f(e2.getMessage(), e2);
            Thread.currentThread().interrupt();
        }
    }

    public void a(t4 t4Var, int i2, int i3, j5 j5Var) {
        a(t4Var, t4Var.getBound(this.v), i2, i3, j5Var);
    }

    public void a(t4 t4Var, Rect rect, int i2, int i3, j5 j5Var) {
        if (t4Var == null || rect == null || i2 <= 0 || i3 <= 0 || j5Var == null) {
            return;
        }
        this.A.a(t4Var, rect, j5Var, i2, i3);
    }

    public void a(v4 v4Var) {
        a1 a1Var = this.u;
        if (a1Var != null) {
            a1Var.a(v4Var);
        }
    }

    public void a(zg zgVar) {
        ch chVar = this.r;
        if (chVar != null) {
            chVar.a(zgVar);
        }
    }

    public void a(CustomRender customRender) {
        this.Y = customRender;
        w0();
    }

    public void a(TencentMap.OnVectorOverlayClickListener onVectorOverlayClickListener) {
        this.g0 = onVectorOverlayClickListener;
    }

    public void a(Language language) {
        if (this.K == null) {
            this.K = new d();
        }
        this.K.b(language);
    }

    public void a(LatLngBounds latLngBounds, int i2) {
        if (this.o == null) {
            return;
        }
        if (latLngBounds == null || latLngBounds.isEmptySpan()) {
            this.o.a((double[]) null, (double[]) null, 0);
        } else {
            this.o.a(new double[]{latLngBounds.getLonWest(), latLngBounds.getLatSouth(), latLngBounds.getLonEast(), latLngBounds.getLatNorth()}, new double[]{0.0d, 0.0d, this.P, this.Q}, i2);
        }
    }

    public void a(OverSeaTileProvider overSeaTileProvider) {
        a1 a1Var = this.u;
        if (a1Var == null) {
            return;
        }
        a1Var.a(overSeaTileProvider);
    }

    public void a(TrafficStyle trafficStyle) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.a(trafficStyle);
    }

    public void a(VectorOverlay vectorOverlay, VectorOverlayProvider vectorOverlayProvider) {
        sc scVar;
        OverlayListenerInfo ddVar;
        if (this.o == null) {
            return;
        }
        if (vectorOverlayProvider instanceof AggregationOverlayProvider) {
            scVar = (wc) this.s.a(wc.class, ((wc) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new xc((AggregationOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof GradientVectorOverlayProvider) {
            scVar = (id) this.s.a(id.class, ((id) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new kd((GradientVectorOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof ArcLineOverlayProvider) {
            scVar = (zc) this.s.a(zc.class, ((zc) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new bd((ArcLineOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof DotScatterPlotOverlayProvider) {
            scVar = (rd) this.s.a(rd.class, ((rd) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new td(this.o.o(), (DotScatterPlotOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof BitmapScatterPlotOverlayProvider) {
            scVar = (rd) this.s.a(rd.class, ((rd) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new td(this.o.o(), (BitmapScatterPlotOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof TrailOverlayProvider) {
            scVar = (ud) this.s.a(ud.class, ((ud) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new wd((TrailOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        } else if (vectorOverlayProvider instanceof GLModelOverlayProvider) {
            scVar = (ed) this.s.a(ed.class, ((ed) vectorOverlay).l());
            if (scVar != null) {
                ddVar = new dd((GLModelOverlayProvider) vectorOverlayProvider);
                scVar.a((sc) ddVar);
                return;
            }
            a(vectorOverlayProvider);
        }
    }

    public void a(String str, String str2) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.a(str, str2);
    }

    public void a(String str, String str2, String str3, String str4, String str5) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(str, str2, str3, str4, str5);
        }
    }

    public void a(List<LatLngBounds> list) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(list);
        }
    }

    public void a(List<MapRouteSection> list, List<GeoPoint> list2) {
        if (this.L == null) {
            this.L = new e();
        }
        this.L.a(list, list2);
    }

    public void a(GL10 gl10) {
        bc bcVar;
        if (this.o == null || (bcVar = this.p) == null) {
            return;
        }
        bcVar.b();
    }

    public void a(boolean z, boolean z2, boolean z3, boolean z4) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(z, z2, z3, z4);
        }
    }

    public void a(int[] iArr, int i2) {
        qi qiVar = this.o;
        if (qiVar == null || iArr == null || i2 == 0) {
            return;
        }
        qiVar.a(iArr, i2);
    }

    public void a(int[] iArr, int i2, boolean z) {
        qi qiVar = this.o;
        if (qiVar == null || iArr == null || i2 == 0) {
            return;
        }
        qiVar.a(iArr, i2, z);
    }

    public void a(String[] strArr) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(strArr);
        }
    }

    @Override // com.tencent.mapsdk.internal.o1, com.tencent.mapsdk.internal.e1
    public boolean a() {
        return d(this.q.m());
    }

    public boolean a(float f2, float f3, v4 v4Var) {
        qi qiVar;
        if (this.h0 != null || !this.s.d() || (qiVar = this.o) == null) {
            this.h0 = null;
            return false;
        }
        qiVar.b(f2, f3);
        this.h0 = v4Var;
        return true;
    }

    public boolean a(int i2) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return false;
        }
        return qiVar.b(i2);
    }

    public boolean a(Context context, xd xdVar, hb hbVar, ae aeVar) {
        this.E = xdVar;
        ce d2 = xdVar.d();
        this.D = d2;
        yd b2 = xdVar.b();
        this.I = b2;
        this.z = new w(context, this, d2, b2);
        if (this.r == null) {
            ch chVar = new ch(this, hbVar);
            this.r = chVar;
            a1 a1Var = this.u;
            if (a1Var != null) {
                a1Var.a(chVar);
            }
        }
        boolean a2 = a(context, aeVar);
        if (a2) {
            if (r() != null) {
                this.o.a(r().getTrafficStyle());
            }
            this.o.K();
            this.o.j(true);
            this.o.j(true);
            this.o.l(true);
            this.o.n(20);
            this.o.o(3);
            this.q.a(this);
            int i2 = this.f17049n;
            c(i2, i2);
        }
        return a2;
    }

    public w a0() {
        return this.z;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public v b() {
        return this.B;
    }

    public String b(GeoPoint geoPoint) {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.c(geoPoint);
        }
        return null;
    }

    public void b(int i2) {
        j1 j1Var = this.s;
        if (j1Var != null) {
            j1Var.a(i2, od.class);
        }
    }

    public void b(int i2, int i3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.b(i2, i3);
        }
    }

    @Deprecated
    public void b(int i2, int i3, int i4) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.b(i2, i3, i4);
    }

    public void b(Rect rect) {
        Rect rect2 = this.w;
        if (rect2 == null || rect == null) {
            return;
        }
        this.R = rect;
        int width = rect2.width();
        int height = this.w.height();
        int i2 = rect.left;
        int i3 = rect.bottom;
        a(i2, i3, (width - rect.right) - i2, (height - i3) - rect.top, true);
    }

    public void b(PolygonInfo polygonInfo) {
        qi qiVar = this.o;
        if (qiVar == null || polygonInfo == null) {
            return;
        }
        qiVar.b(polygonInfo);
    }

    public void b(c5 c5Var) {
        if (this.K == null) {
            this.K = new d();
        }
        this.K.b(c5Var);
    }

    public void b(me meVar) {
        if (this.d0.isEmpty()) {
            return;
        }
        this.d0.remove(meVar);
    }

    public void b(v4 v4Var) {
        a1 a1Var = this.u;
        if (a1Var != null) {
            a1Var.b(v4Var);
        }
    }

    public void b(zg zgVar) {
        ch chVar = this.r;
        if (chVar != null) {
            chVar.b(zgVar);
        }
    }

    public void b(List<IndoorCellInfo> list) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.b(list);
        }
    }

    public boolean b(String str) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return false;
        }
        return qiVar.d(str);
    }

    public String b0() {
        return this.e0;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public int c() {
        return r().getExtSurfaceHeight();
    }

    public void c(int i2) {
        za<Integer, Integer> zaVar;
        if (this.o == null || (zaVar = this.G) == null) {
            return;
        }
        Integer a2 = zaVar.a((za<Integer, Integer>) Integer.valueOf(i2));
        this.o.c(i2, a2 != null ? a2.intValue() : 0);
    }

    public void c(int i2, int i3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.d(i2 + 50, i3 + 50);
        }
    }

    public void c(q5 q5Var) {
        if (this.s != null) {
            this.s.a(q5Var.a, (int) b(q5Var));
        }
    }

    public void c(String str) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.f(str);
        }
    }

    public void c(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a(z);
        }
    }

    public boolean c(GL10 gl10) {
        if (this.o == null) {
            return false;
        }
        this.s.b();
        this.q.J();
        b(gl10);
        this.o.X();
        boolean z = this.o.R() || this.F;
        if (z) {
            g a2 = this.A.a(gl10);
            if (a2 == null) {
                this.s.a(gl10);
            } else if (this.A != null && this.o.N()) {
                this.A.b(gl10, this.t, this.v);
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.o.S();
            this.F = false;
            if (!this.u.m()) {
                long elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime;
                Object h2 = qa.h(pa.X, "nativeTotalTime");
                long longValue = (h2 != null ? ((Long) h2).longValue() : 0L) + elapsedRealtime2;
                qa.b(pa.X, "nativeTime", Long.valueOf(elapsedRealtime2));
                qa.b(pa.X, "nativeTotalTime", Long.valueOf(longValue));
                qa.j(pa.X);
            }
            if (!this.A.c() && a2 != null) {
                if (a2.b()) {
                    a2.a = 0;
                } else if (this.o.N() || a2.a >= 100) {
                    a2.a = 0;
                    this.A.a(gl10, this.t, this.v);
                } else {
                    g.d(a2);
                }
            }
        }
        pc pcVar = this.t;
        if (pcVar != null) {
            pcVar.c();
        }
        return z;
    }

    public bc c0() {
        return this.p;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public float d() {
        if (getContext() == null) {
            return 1.0f;
        }
        return f7.d(getContext());
    }

    public void d(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        this.o.e(i2, i3);
    }

    public void d(String str) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.h(str);
        }
    }

    public void d(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.b(z);
        }
    }

    public boolean d(int i2) {
        return b().b(i2);
    }

    public og d0() {
        return this.c0;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public Rect e() {
        return this.w;
    }

    @Deprecated
    public void e(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.d(i2);
        }
    }

    public void e(int i2, int i3) {
        this.W = i2;
        this.V = i3;
    }

    public void e(String str) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.i(str);
        }
    }

    public void e(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.c(z);
        }
    }

    public gh e0() {
        return this.M;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public qi f() {
        return this.o;
    }

    public void f(int i2) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.f(i2);
    }

    public void f(int i2, int i3) {
        Rect rect = this.w;
        if (rect != null) {
            rect.set(0, 0, i2, i3);
        }
    }

    public void f(String str) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.j(str);
        }
    }

    public Rect f0() {
        return this.R;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public j1 g() {
        return this.s;
    }

    @Deprecated
    public void g(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.g(i2);
            this.F = true;
        }
    }

    @Deprecated
    public void g(int i2, int i3) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.f(i2, i3);
        }
    }

    public void g(String str) {
        qi qiVar = this.o;
        if (qiVar != null) {
            this.e0 = str;
            qiVar.k(str);
        }
    }

    public void g(boolean z) {
        this.J = z;
        f(z);
    }

    public boolean g0() {
        return this.x;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public s4 getProjection() {
        return this.v;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public d0 h() {
        return this.q;
    }

    public void h(int i2, int i3) {
        if (this.o == null) {
            return;
        }
        this.F = true;
        this.w.set(0, 0, i2, i3);
        a(0, 0, i2, i3, false);
        this.q.H();
    }

    public void h(String str) {
        yd ydVar = this.I;
        if (ydVar == null) {
            return;
        }
        ydVar.setOptionalResourcePath(str);
    }

    public void h(boolean z) {
        this.q.e(z);
    }

    public boolean h(int i2) {
        CustomRender customRender;
        if (i2 == c.AboveToplayer.a && (customRender = this.Y) != null) {
            customRender.onDrawFrame();
            return true;
        }
        return false;
    }

    public void h0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.L();
            this.x = false;
        }
    }

    @Override // com.tencent.mapsdk.internal.e1
    public Object i() {
        return r().getExtSurface();
    }

    public void i(int i2) {
        this.o.h(i2);
    }

    public void i(int i2, int i3) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 > 255) {
            i3 = 255;
        }
        int i4 = 255 - i3;
        if (i4 == 0) {
            i4 = 1;
        }
        qiVar.h(i2, i4);
    }

    public void i(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.e(z);
        }
        w0();
    }

    @Override // com.tencent.mapsdk.internal.e1
    public boolean isOpaque() {
        return r().isOpaque();
    }

    public void j(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.i(i2);
        }
    }

    public void j(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.f(z);
        }
    }

    public boolean j0() {
        qi qiVar = this.o;
        return qiVar != null && qiVar.e(O()) == 1;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public float k() {
        return r().getMapFrameRate();
    }

    public void k(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.l(i2);
        }
    }

    public void k(boolean z) {
        if (this.o != null) {
            ma.a(la.f16819f, "setIndoorEnabled:" + z);
            this.o.g(z);
        }
    }

    @Deprecated
    public boolean k0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.N();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.e1
    public int l() {
        return r().getExtSurfaceWidth();
    }

    public void l(int i2) {
        this.o.m(i2);
    }

    public void l(boolean z) {
        this.o.i(z);
    }

    public void m(int i2) {
        d0 d0Var = this.q;
        if (d0Var != null) {
            d0Var.d(i2);
        }
    }

    public void m(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.j(z);
        }
    }

    public boolean m0() {
        return this.O;
    }

    @Override // com.tencent.mapsdk.internal.o1
    public String n() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.q();
        }
        return null;
    }

    public void n(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.n(i2);
        }
        v vVar = this.B;
        if (vVar != null) {
            vVar.d(i2);
        }
    }

    public void n(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.k(z);
        }
    }

    @Deprecated
    public boolean n0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.Q();
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.o1
    public t1 o() {
        return this.o;
    }

    public void o(int i2) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.o(i2);
        }
        v vVar = this.B;
        if (vVar != null) {
            vVar.e(i2);
        }
    }

    public void o(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.l(z);
        }
    }

    public boolean o0() {
        return this.y;
    }

    @Override // com.tencent.mapsdk.internal.o1
    public String p() {
        qi qiVar = this.o;
        if (qiVar != null) {
            return qiVar.y();
        }
        return null;
    }

    public void p(int i2) {
        qi qiVar = this.o;
        if (qiVar == null) {
            return;
        }
        qiVar.p(i2);
    }

    public void p(boolean z) {
        this.o.m(z);
    }

    public void p0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.a();
        }
    }

    public void q(boolean z) {
        d0 d0Var = this.q;
        if (d0Var != null) {
            d0Var.f(z);
        }
    }

    public hb q0() {
        return this.E.c();
    }

    public void r(boolean z) {
        if (this.O == z) {
            return;
        }
        this.O = z;
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.n(z);
        }
    }

    public void r0() {
        List<me> list = this.d0;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<me> it = this.d0.iterator();
        while (it.hasNext()) {
            it.next().onMapCameraChangeStopped();
        }
    }

    public void s(boolean z) {
        d0 d0Var = this.q;
        if (d0Var != null) {
            d0Var.g(z);
        }
    }

    public void s0() {
        List<me> list = this.d0;
        if (list == null || list.isEmpty()) {
            return;
        }
        Iterator<me> it = this.d0.iterator();
        while (it.hasNext()) {
            it.next().onMapCameraChanged();
        }
    }

    @Deprecated
    public void t(boolean z) {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.p(z);
        }
    }

    public void t0() {
        oe oeVar = this.b0;
        if (oeVar != null) {
            oeVar.onMapLoaded();
        }
    }

    public String toString() {
        v vVar = this.B;
        return vVar != null ? vVar.toString() : "";
    }

    public void u(boolean z) {
        if (this.r != null) {
            this.y = z;
            if (z) {
                C0();
                this.r.d();
                return;
            }
            i0();
            this.r.a();
        }
    }

    public void u0() {
        ch chVar;
        if (this.o == null) {
            return;
        }
        this.F = false;
        this.N = false;
        w(false);
        gh ghVar = this.M;
        if (ghVar != null) {
            ghVar.b();
        }
        this.q.I();
        this.o.L();
        if (!this.y || (chVar = this.r) == null) {
            return;
        }
        chVar.e();
    }

    public void v(boolean z) {
        if (this.M == null) {
            this.M = new gh(this);
        }
        this.M.a(z);
    }

    public void v0() {
        ch chVar;
        if (!this.y || (chVar = this.r) == null) {
            return;
        }
        chVar.e();
    }

    public void w0() {
        this.q.a();
        this.F = true;
    }

    public void x0() {
        qi qiVar = this.o;
        if (qiVar != null) {
            qiVar.U();
        }
    }

    public void y0() {
        if (this.o == null) {
            return;
        }
        a(new a());
    }

    public void z0() {
        ch chVar;
        if (this.o == null) {
            return;
        }
        this.F = true;
        this.N = true;
        w(true);
        if (this.H) {
            this.q.S();
            this.H = false;
        } else {
            this.q.O();
        }
        a1 a1Var = this.u;
        if (a1Var != null) {
            a1Var.getMapRenderView().j();
        }
        if (this.x) {
            this.o.V();
        }
        if (this.y && (chVar = this.r) != null) {
            chVar.f();
        }
        gh ghVar = this.M;
        if (ghVar != null) {
            ghVar.c();
        }
    }
}
