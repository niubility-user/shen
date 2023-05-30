package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.jingdong.sdk.platform.business.personal.R2;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.ReturnCallback;
import com.tencent.map.tools.Util;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.mapsdk.internal.h1;
import com.tencent.mapsdk.internal.o1;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationListener;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorMapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaSource;
import com.tencent.tencentmap.mapsdk.maps.model.OverSeaTileProvider;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListenerList;
import com.tencent.tencentmap.mapsdk.maps.model.TrafficEvent;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class xi extends ej implements TencentMap.OnCameraChangeListener {
    private GeoPoint A0;
    private boolean B0;
    private boolean C0;
    private volatile boolean D0;
    private boolean E0;
    private int F0;
    private int G0;
    private boolean H0;
    public final byte[] I;
    public boolean I0;
    private String J;
    public boolean J0;
    private boolean K;
    public boolean K0;
    public int L;
    public boolean L0;
    private vh M;
    public boolean M0;
    private tg N;
    private int N0;
    private ph O;
    private int O0;
    public dg P;
    private float P0;
    private b0 Q;
    private boolean Q0;
    public AnimationListener R;
    private boolean R0;
    private List<b5> S;
    public LatLng S0;
    private List<h5> T;
    public LatLng T0;
    public TencentMap.OnMapClickListener U;
    public int U0;
    public c1 V;
    public int V0;
    public List<TencentMap.OnCameraChangeListener> W;
    public int W0;
    public TencentMap.OnMapLongClickListener X;
    public int X0;
    private TencentMap.OnDismissCallback Y;
    public int Y0;
    public TencentMap.OnIndoorStateChangeListener Z;
    public int Z0;
    public TencentMap.OnMarkerClickListener a0;
    public int a1;
    public TencentMap.OnInfoWindowClickListener b0;
    private final v4 b1;
    public TencentMap.OnMapPoiClickListener c0;
    private v5 c1;
    private final b1 d0;
    public boolean d1;
    private xf e0;
    public o0 e1;
    private final g5 f0;
    private boolean f1;
    private Handler g0;
    private ef g1;
    public TencentMap.CancelableCallback h0;
    private uf h1;
    public TencentMap.OnCompassClickedListener i0;
    private TencentMap.OnTrafficEventClickListener i1;
    private h1.g j0;
    private List<o0> j1;
    private k5 k0;
    private List<MapPoi> k1;
    private TencentMap.OnScaleViewChangedListener l0;
    private List<d1> l1;
    private TencentMap.OnCameraChangeListener m0;
    private TencentMap.OnCameraChangeListener n0;
    private Handler o0;
    private long p0;
    private xd q0;
    private Bitmap.Config r0;
    private int s0;
    private int t0;
    private int u0;
    private int v0;
    public float w0;
    public float x0;
    public float y0;
    public float z0;

    /* loaded from: classes9.dex */
    public class a implements Callback<t4> {
        public final /* synthetic */ t4[] a;
        public final /* synthetic */ GL10 b;

        public a(t4[] t4VarArr, GL10 gl10) {
            xi.this = r1;
            this.a = t4VarArr;
            this.b = gl10;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(t4 t4Var) {
            if (t4Var instanceof v0) {
                if (!TextUtils.equals(t4Var.getId(), xi.this.J)) {
                    t4Var.a(this.b);
                } else if (t4Var.isVisible() || xi.this.H0) {
                    this.a[0] = t4Var;
                } else {
                    t4Var.releaseData();
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements ReturnCallback<Boolean, t4> {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ Object[] f17465c;

        public b(float f2, float f3, Object[] objArr) {
            xi.this = r1;
            this.a = f2;
            this.b = f3;
            this.f17465c = objArr;
        }

        @Override // com.tencent.map.tools.ReturnCallback
        /* renamed from: a */
        public Boolean callback(t4 t4Var) {
            if (t4Var instanceof w0) {
                w0 w0Var = (w0) t4Var;
                q4 j2 = w0Var.x().j();
                if (!w0Var.getId().equals(xi.this.J) && j2 != null && j2.r()) {
                    boolean onTap = j2.onTap(this.a, this.b);
                    this.f17465c[0] = Boolean.valueOf(onTap);
                    if (onTap) {
                        Object[] objArr = this.f17465c;
                        objArr[1] = w0Var;
                        objArr[2] = j2;
                        return Boolean.TRUE;
                    }
                }
            }
            return Boolean.FALSE;
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Callback<t4> {
        public c() {
            xi.this = r1;
        }

        @Override // com.tencent.map.tools.Callback
        /* renamed from: a */
        public void callback(t4 t4Var) {
            if (t4Var instanceof w0) {
                o0 x = ((w0) t4Var).x();
                if (x.j() != null) {
                    x.hideInfoWindow();
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class d implements ReturnCallback<Boolean, t4> {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ String[] f17466c;

        public d(float f2, float f3, String[] strArr) {
            xi.this = r1;
            this.a = f2;
            this.b = f3;
            this.f17466c = strArr;
        }

        @Override // com.tencent.map.tools.ReturnCallback
        /* renamed from: a */
        public Boolean callback(t4 t4Var) {
            if (t4Var == null || !t4Var.isVisible()) {
                return Boolean.FALSE;
            }
            if ((t4Var instanceof w0) && t4Var.onTap(this.a, this.b)) {
                this.f17466c[0] = t4Var.getId();
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    /* loaded from: classes9.dex */
    public class e implements Runnable {
        public final /* synthetic */ CameraPosition a;

        public e(CameraPosition cameraPosition) {
            xi.this = r1;
            this.a = cameraPosition;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<TencentMap.OnCameraChangeListener> list = xi.this.W;
            if (list != null) {
                for (TencentMap.OnCameraChangeListener onCameraChangeListener : list) {
                    if (onCameraChangeListener != null) {
                        onCameraChangeListener.onCameraChange(this.a);
                    }
                }
            }
            if (xi.this.m0 != null) {
                xi.this.m0.onCameraChange(this.a);
            }
            if (xi.this.n0 != null) {
                xi.this.n0.onCameraChange(this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f implements Runnable {
        public final /* synthetic */ CameraPosition a;

        public f(CameraPosition cameraPosition) {
            xi.this = r1;
            this.a = cameraPosition;
        }

        @Override // java.lang.Runnable
        public void run() {
            List<TencentMap.OnCameraChangeListener> list = xi.this.W;
            if (list != null) {
                for (TencentMap.OnCameraChangeListener onCameraChangeListener : list) {
                    if (onCameraChangeListener != null) {
                        onCameraChangeListener.onCameraChangeFinished(this.a);
                    }
                }
            }
            if (xi.this.m0 != null) {
                xi.this.m0.onCameraChangeFinished(this.a);
            }
            if (xi.this.n0 != null) {
                xi.this.n0.onCameraChangeFinished(this.a);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class g implements Runnable {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ List b;

        public g(boolean z, List list) {
            xi.this = r1;
            this.a = z;
            this.b = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            Iterator it = xi.this.l1.iterator();
            while (it.hasNext()) {
                ((d1) it.next()).a(this.a, this.b);
            }
        }
    }

    public xi(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        super(context, tencentMapOptions, viewGroup);
        this.I = new byte[0];
        this.J = "";
        this.K = false;
        this.L = 22;
        this.M = null;
        this.N = null;
        this.Q = null;
        this.R = null;
        this.S = new CopyOnWriteArrayList();
        this.T = null;
        this.U = null;
        this.V = null;
        this.W = new CopyOnWriteArrayList();
        this.X = null;
        this.Y = null;
        this.a0 = null;
        this.b0 = null;
        this.c0 = null;
        this.e0 = new ri(this);
        this.h0 = null;
        this.i0 = null;
        this.j0 = null;
        this.l0 = null;
        this.m0 = null;
        this.n0 = null;
        this.r0 = Bitmap.Config.RGB_565;
        this.s0 = Integer.MIN_VALUE;
        this.t0 = Integer.MIN_VALUE;
        this.u0 = Integer.MIN_VALUE;
        this.v0 = Integer.MIN_VALUE;
        this.w0 = 0.5f;
        this.x0 = 0.5f;
        this.y0 = 0.5f;
        this.z0 = 0.5f;
        this.A0 = null;
        this.B0 = false;
        this.C0 = true;
        this.D0 = false;
        this.E0 = false;
        this.F0 = 0;
        this.G0 = 0;
        this.H0 = false;
        this.I0 = true;
        this.J0 = true;
        this.K0 = true;
        this.L0 = true;
        this.M0 = true;
        this.N0 = 19;
        this.O0 = 3;
        this.P0 = 0.0f;
        this.Q0 = true;
        this.R0 = true;
        this.S0 = null;
        this.T0 = null;
        this.U0 = 0;
        this.V0 = 0;
        this.W0 = 0;
        this.X0 = 0;
        this.Y0 = 0;
        this.Z0 = 0;
        this.a1 = 0;
        this.d1 = false;
        this.e1 = null;
        this.f1 = false;
        this.j1 = new ArrayList();
        this.k1 = new ArrayList();
        this.l1 = new ArrayList();
        this.f0 = new bj(this);
        this.b1 = new vi(this);
        this.e0 = new ri(this);
        this.k0 = new ti(this);
        this.d0 = new si(this);
        int[] c2 = f7.c(getContext());
        this.Z0 = c2[0];
        this.a1 = c2[1];
    }

    private Bitmap a(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, new Matrix(), null);
        canvas.drawBitmap(bitmap2, this.O.d().left, this.O.d().top, (Paint) null);
        return createBitmap;
    }

    private void a(Handler handler, GL10 gl10) {
        if (gl10 == null || handler == null) {
            return;
        }
        int i2 = this.Z0;
        int i3 = this.a1;
        int i4 = i2 * i3;
        int[] iArr = new int[i4];
        int[] iArr2 = new int[i4];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        gl10.glReadPixels(0, 0, i2, i3, R2.dimen.jdreact_base_ui_jd_tip_page_button_paddingLeft, R2.dimen.HugersTextSize, wrap);
        for (int i5 = 0; i5 < i3; i5++) {
            for (int i6 = 0; i6 < i2; i6++) {
                int i7 = iArr[(i5 * i2) + i6];
                iArr2[(((i3 - i5) - 1) * i2) + i6] = (i7 & (-16711936)) | ((i7 << 16) & 16711680) | ((i7 >> 16) & 255);
            }
        }
        Bitmap bitmap = null;
        try {
            bitmap = Bitmap.createBitmap(iArr2, i2, i3, this.r0);
        } catch (OutOfMemoryError unused) {
        }
        v1 v1Var = this.A;
        if (v1Var != null && !(v1Var instanceof gj) && this.O.u()) {
            bitmap = a(bitmap, this.O.f());
        }
        handler.sendMessage(handler.obtainMessage(0, bitmap));
    }

    private void a(TencentMapOptions tencentMapOptions) {
        Context applicationContext = getContext().getApplicationContext();
        String j2 = h0().j();
        zi ziVar = new zi(this, j2);
        this.q0 = ziVar;
        ziVar.a(true);
        pf.a(applicationContext, j2);
        a(this.q0, yg.c());
        getMap().e(((j3) l2.a(j3.class)).j());
        p3 p3Var = (p3) l2.a(p3.class);
        String satelliteVersion = tencentMapOptions != null ? tencentMapOptions.getSatelliteVersion() : null;
        if (TextUtils.isEmpty(satelliteVersion)) {
            satelliteVersion = "0";
        }
        String satelliteUrl = ((b3) p3Var.d()).satelliteUrl("%d", "%d", "%d", satelliteVersion);
        if (!p3Var.b() || TextUtils.isEmpty(satelliteUrl)) {
            return;
        }
        ma.c(la.f16819f, "satelliteUrl = " + satelliteUrl);
        getMap().f(satelliteUrl);
    }

    private boolean a(TappedElement tappedElement) {
        qc A = A();
        if (A == null || A.e0() == null) {
            return false;
        }
        TrafficEvent a2 = A.e0().a((int) tappedElement.itemId);
        TencentMap.OnTrafficEventClickListener onTrafficEventClickListener = this.i1;
        if (onTrafficEventClickListener == null || a2 == null) {
            return false;
        }
        onTrafficEventClickListener.onTrafficEventClicked(a2);
        return true;
    }

    private boolean a(t4 t4Var, float f2, float f3) {
        if (t4Var != null && t4Var.isVisible() && t4Var.handleOnTap()) {
            return t4Var.onTap(f2, f3);
        }
        return false;
    }

    private boolean a(w0 w0Var, float f2, float f3) {
        boolean z = false;
        if (getMap() == null) {
            return false;
        }
        if (w0Var == null) {
            a("", true);
            return true;
        }
        o0 x = w0Var.x();
        String id = x.getId();
        TencentMap.OnMarkerClickListener onMarkerClickListener = this.a0;
        if (onMarkerClickListener == null || !onMarkerClickListener.onMarkerClick(w0Var)) {
            if (w0Var.isInfoWindowEnable()) {
                if (this.d1) {
                    if (x.j() == null) {
                        x.showInfoWindow();
                    } else if (x.isInfoWindowShown()) {
                        x.hideInfoWindow();
                    } else {
                        x.showInfoWindow();
                        this.e1 = x;
                    }
                    a(id, true);
                    return true;
                }
                o0 o0Var = this.e1;
                if (o0Var == null) {
                    x.showInfoWindow();
                    this.e1 = x;
                    return true;
                } else if (o0Var != x) {
                    o0Var.hideInfoWindow();
                    x.showInfoWindow();
                    this.e1 = x;
                    return true;
                } else {
                    if (x.j() != null && x.j().r()) {
                        z = true;
                    }
                    if (z) {
                        x.hideInfoWindow();
                        this.e1 = null;
                    } else {
                        x.showInfoWindow();
                        this.e1 = x;
                    }
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private boolean a(x0 x0Var, float f2, float f3) {
        TencentMap.OnPolygonClickListener onPolygonClickListener;
        if (!x0Var.isClickable() || (onPolygonClickListener = this.D) == null) {
            return false;
        }
        onPolygonClickListener.onPolygonClick(x0Var, getMapContext().getProjection().fromScreenLocation(new Point((int) f2, (int) f3)));
        return true;
    }

    private boolean a(y0 y0Var, float f2, float f3) {
        TencentMap.OnPolylineClickListener onPolylineClickListener;
        if (!y0Var.isClickable() || (onPolylineClickListener = this.C) == null) {
            return false;
        }
        onPolylineClickListener.onPolylineClick(y0Var, getMapContext().getProjection().fromScreenLocation(new Point((int) f2, (int) f3)));
        return true;
    }

    private boolean a(LatLng latLng, w5[] w5VarArr) {
        if (latLng == null) {
            return false;
        }
        w5 b2 = y.b(GeoPoint.from(latLng));
        return b2.b() >= w5VarArr[0].b() && b2.b() <= w5VarArr[1].b() && b2.c() <= w5VarArr[0].c() && b2.c() >= w5VarArr[1].c();
    }

    private boolean a(w5[] w5VarArr) {
        w5[] m0 = m0();
        if (m0 == null || w5VarArr == null) {
            return true;
        }
        return sh.a(m0, w5VarArr);
    }

    private void b(Handler handler, GL10 gl10) {
        ph phVar = this.O;
        if (phVar != null) {
            phVar.n();
        }
        a(handler, gl10);
        ph phVar2 = this.O;
        if (phVar2 != null) {
            phVar2.m();
        }
    }

    private void b(GL10 gl10) {
        i1 Z;
        if (getMapContext() == null || (Z = getMapContext().Z()) == null) {
            return;
        }
        synchronized (this.I) {
            t4[] t4VarArr = {null};
            Util.foreach(Z.f(), new a(t4VarArr, gl10));
            if (t4VarArr[0] != null) {
                t4VarArr[0].a(gl10);
            }
        }
    }

    private void e0() {
        if (!Y()) {
            ef efVar = this.g1;
            if (efVar != null) {
                efVar.onTalkBackDeActivate(this.A.getView());
                this.g1 = null;
                return;
            }
            return;
        }
        v1 v1Var = this.A;
        if (v1Var instanceof View) {
            if (this.g1 == null) {
                this.g1 = new ef((View) v1Var, this);
            }
            this.g1.onTalkBackActivate(this.A.getView());
        }
    }

    private boolean f0() {
        return this.M.g();
    }

    private w5[] k0() {
        return new w5[]{y.b(getMap().getProjection().a(new o5(0.0d, 0.0d))), y.b(getMap().getProjection().a(new o5(X(), V())))};
    }

    @Override // com.tencent.mapsdk.internal.a1
    public int C() {
        return this.u0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean D() {
        return this.C0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void E() {
        synchronized (this.I) {
            A().Z().a();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean G() {
        return this.K0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public int H() {
        return this.s0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public String I() {
        return this.J;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public CameraPosition J() {
        VectorMap map = getMap();
        GeoPoint s = map.s();
        if (s != null) {
            List<TencentMap.OnCameraChangeListener> list = this.W;
            if ((list == null || list.size() <= 0) && this.b1 == null) {
                return null;
            }
            LatLng d2 = ea.d(s);
            float T = map.T();
            if (T < 0.0f) {
                T = (T % 360.0f) + 360.0f;
            }
            return CameraPosition.builder().zoom(map.M().x()).target(d2).bearing(T).tilt(map.Z()).build();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void K() {
        if (this.S.isEmpty()) {
            return;
        }
        u5 u5Var = new u5();
        u5Var.a = 0;
        u5Var.b = this.Q0;
        u5Var.f17300c = this.R0;
        Iterator<b5> it = this.S.iterator();
        while (it.hasNext()) {
            it.next().a(u5Var);
        }
    }

    @Override // com.tencent.mapsdk.internal.p1
    public void L() {
        super.L();
        if (this.B0) {
            t();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean M() {
        return this.M0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void N() {
        int V = getMap().V();
        this.Q0 = true;
        this.R0 = true;
        if (V <= this.O0) {
            this.R0 = false;
        } else if (V >= this.N0) {
            this.Q0 = false;
        }
        if (this.S.isEmpty()) {
            return;
        }
        u5 u5Var = new u5();
        u5Var.a = 0;
        u5Var.b = this.Q0;
        u5Var.f17300c = this.R0;
        Iterator<b5> it = this.S.iterator();
        while (it.hasNext()) {
            it.next().a(u5Var);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean O() {
        return this.J0;
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.p1
    public void P() {
        super.P();
        vh vhVar = new vh(this);
        this.M = vhVar;
        vhVar.a(this.f16971k, OverSeaSource.DEFAULT, null);
        this.N0 = getMap().P();
        this.O0 = getMap().R();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public ye a(String str) {
        if (getMapContext() != null) {
            t4 b2 = getMapContext().Z().b(str);
            if (b2 instanceof v0) {
                p0 x = ((v0) b2).x();
                if (x instanceof ye) {
                    return (ye) x;
                }
                return null;
            }
            return null;
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(int i2, int i3) {
        this.s0 = i2;
        this.t0 = i3;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(Context context, TencentMapOptions tencentMapOptions) {
        VectorMap map;
        int i2;
        a(tencentMapOptions);
        if (getMap().j0()) {
            map = getMap();
            i2 = 5;
        } else {
            map = getMap();
            i2 = 0;
        }
        map.setMapStyle(i2);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(Handler handler, Bitmap.Config config, int i2) {
        this.o0 = handler;
        this.r0 = config;
        this.p0 = i2 > 0 ? SystemClock.elapsedRealtime() + i2 : Long.MAX_VALUE;
        h();
        a();
    }

    public void a(af afVar) {
        ef efVar;
        if (afVar == null || (efVar = this.g1) == null) {
            return;
        }
        efVar.a(afVar);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(b5 b5Var) {
        this.S.remove(b5Var);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(c1 c1Var) {
        this.V = c1Var;
    }

    public void a(d1 d1Var) {
        this.l1.remove(d1Var);
        this.l1.add(d1Var);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(dg dgVar) {
        this.P = dgVar;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(h1.g gVar) {
        this.j0 = gVar;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(h5 h5Var) {
        if (h5Var == null) {
            return;
        }
        if (this.T == null) {
            this.T = new ArrayList();
        }
        if (this.T.contains(h5Var)) {
            return;
        }
        this.T.add(h5Var);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(ph phVar) {
        this.O = phVar;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        List<TencentMap.OnCameraChangeListener> list = this.W;
        if (list != null && onCameraChangeListener != null) {
            list.add(onCameraChangeListener);
        }
        g(onCameraChangeListener == null);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(TencentMap.OnDismissCallback onDismissCallback) {
        this.Y = onDismissCallback;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.Z = onIndoorStateChangeListener;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(TencentMap.OnMapPoiClickListener onMapPoiClickListener) {
        this.c0 = onMapPoiClickListener;
    }

    public void a(TencentMap.OnScaleViewChangedListener onScaleViewChangedListener) {
        this.l0 = onScaleViewChangedListener;
    }

    public void a(TencentMap.OnTrafficEventClickListener onTrafficEventClickListener) {
        this.i1 = onTrafficEventClickListener;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(OverSeaTileProvider overSeaTileProvider) {
        vh vhVar = this.M;
        if (vhVar != null) {
            vhVar.a(overSeaTileProvider);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(TencentMapGestureListener tencentMapGestureListener) {
        TencentMapGestureListenerList tencentMapGestureListenerList = this.o;
        if (tencentMapGestureListenerList == null) {
            return;
        }
        tencentMapGestureListenerList.removeListener(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(String str, boolean z) {
        a(str, z, false);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(String str, boolean z, boolean z2) {
        synchronized (this.I) {
            if (!z2) {
                this.J = str;
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, int i2, int i3) {
        super.a(gl10, i2, i3);
        this.Z0 = i2;
        this.a1 = i3;
        a((int) (i2 * this.w0), (int) (i3 * this.x0));
        b((int) (this.Z0 * this.y0), (int) (this.a1 * this.z0));
        h1.g gVar = this.j0;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, EGLConfig eGLConfig) {
        qa.h(pa.X);
        super.a(gl10, eGLConfig);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(boolean z) {
        this.f1 = z;
    }

    public void a(boolean z, List<yh> list) {
        ba.a(new g(z, list));
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean a(float f2, float f3) {
        IndoorMapPoi indoorMapPoi;
        TappedElement e2 = getMap().e(f2, f3);
        if (e2 != null) {
            int i2 = e2.type;
            if (i2 != 1) {
                if (i2 == 4) {
                    return a(e2);
                }
                if (i2 != 8) {
                    return false;
                }
            } else if (this.c0 != null) {
                if (e2.itemType == 1) {
                    IndoorMapPoi indoorMapPoi2 = new IndoorMapPoi();
                    indoorMapPoi2.buildingName = e2.buildingName;
                    indoorMapPoi2.buildingId = e2.buildingId;
                    indoorMapPoi2.floorName = e2.floorName;
                    indoorMapPoi = indoorMapPoi2;
                } else {
                    indoorMapPoi = new MapPoi();
                }
                indoorMapPoi.name = e2.name;
                indoorMapPoi.position = ea.b(e2.pixelX, e2.pixelY).toLatLng();
                indoorMapPoi.poiId = e2.poiId;
                this.c0.onClicked(indoorMapPoi);
            }
            return true;
        }
        return false;
    }

    public boolean a(MotionEvent motionEvent) {
        ef efVar = this.g1;
        if (efVar != null) {
            return efVar.dispatchHoverEvent(motionEvent);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.fj.n
    public boolean a(GL10 gl10) {
        b(gl10);
        boolean a2 = super.a(gl10);
        if (this.o0 != null && SystemClock.elapsedRealtime() > this.p0) {
            ma.a(la.f16819f, "snapShot : 1");
            b(this.o0, gl10);
            this.o0 = null;
        }
        if ((this.o0 != null || !this.q) && getMap() != null && getMap().f0() && n0() != null && n0().f() && B()) {
            if (!this.q) {
                n(true);
            }
            ma.a(la.f16819f, "snapShot : 2");
            b(this.o0, gl10);
            this.o0 = null;
        }
        return a2;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(float f2) {
        TencentMap.OnScaleViewChangedListener onScaleViewChangedListener = this.l0;
        if (onScaleViewChangedListener != null) {
            onScaleViewChangedListener.onScaleViewChanged(f2);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(int i2, int i3) {
        this.u0 = i2;
        this.v0 = i3;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(b5 b5Var) {
        this.S.remove(b5Var);
        this.S.add(b5Var);
    }

    public void b(d1 d1Var) {
        this.l1.remove(d1Var);
    }

    public void b(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        this.n0 = onCameraChangeListener;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(TencentMapGestureListener tencentMapGestureListener) {
        if (this.o == null) {
            this.o = new TencentMapGestureListenerList();
        }
        this.o.addListener(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(boolean z) {
        this.L0 = z;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean b() {
        return this.f1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:103:0x00ad A[Catch: all -> 0x0109, TryCatch #1 {, blocks: (B:71:0x0003, B:73:0x000e, B:75:0x0012, B:79:0x0021, B:81:0x0027, B:88:0x0034, B:90:0x006d, B:92:0x0073, B:96:0x007b, B:98:0x0081, B:100:0x0096, B:101:0x00ab, B:103:0x00ad, B:105:0x00b3, B:107:0x00b5), top: B:133:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0034 A[Catch: all -> 0x0109, TryCatch #1 {, blocks: (B:71:0x0003, B:73:0x000e, B:75:0x0012, B:79:0x0021, B:81:0x0027, B:88:0x0034, B:90:0x006d, B:92:0x0073, B:96:0x007b, B:98:0x0081, B:100:0x0096, B:101:0x00ab, B:103:0x00ad, B:105:0x00b3, B:107:0x00b5), top: B:133:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x006d A[Catch: all -> 0x0109, TryCatch #1 {, blocks: (B:71:0x0003, B:73:0x000e, B:75:0x0012, B:79:0x0021, B:81:0x0027, B:88:0x0034, B:90:0x006d, B:92:0x0073, B:96:0x007b, B:98:0x0081, B:100:0x0096, B:101:0x00ab, B:103:0x00ad, B:105:0x00b3, B:107:0x00b5), top: B:133:0x0003 }] */
    /* JADX WARN: Type inference failed for: r2v12, types: [com.tencent.tencentmap.mapsdk.maps.model.Marker] */
    @Override // com.tencent.mapsdk.internal.a1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean b(float r9, float r10) {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.xi.b(float, float):boolean");
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean b(String str) {
        if (str == null) {
            return false;
        }
        boolean a2 = A().Z().a(str);
        if (a2) {
            getMap().v0();
        }
        return a2;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public GeoPoint c() {
        if (this.A0 == null) {
            if (this.s0 == Integer.MIN_VALUE) {
                this.s0 = this.Z0 / 2;
            }
            if (this.t0 == Integer.MIN_VALUE) {
                this.t0 = this.a1 / 2;
            }
            this.A0 = getMap().getProjection().a(new o5(this.s0, this.t0));
        }
        return this.A0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public String c(float f2, float f3) {
        String[] strArr = {null};
        synchronized (this.I) {
            Util.where(getMapContext().Z().f(), new d(f2, f3, strArr));
        }
        return strArr[0];
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void c(float f2) {
        if (this.P0 == f2) {
            return;
        }
        this.P0 = f2;
        if (this.S.isEmpty()) {
            return;
        }
        u5 u5Var = new u5();
        u5Var.a = 1;
        u5Var.d = getMap().V();
        u5Var.f17301e = getMap().getProjection().a(new Point(0, this.a1 / 2), new Point(this.Z0, this.a1 / 2));
        Iterator<b5> it = this.S.iterator();
        while (it.hasNext()) {
            it.next().a(u5Var);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void c(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return;
        }
        if (this.A0 == null) {
            GeoPoint s = getMap().s();
            this.A0 = new GeoPoint(s.getLatitudeE6(), s.getLongitudeE6());
        }
        this.A0.setLatitudeE6(i2);
        this.A0.setLongitudeE6(i3);
    }

    public void c(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        this.m0 = onCameraChangeListener;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void c(boolean z) {
        if (this.C0 != z) {
            u5 u5Var = new u5();
            u5Var.a = 2;
            Iterator<b5> it = this.S.iterator();
            while (it.hasNext()) {
                it.next().a(u5Var);
            }
        }
        this.C0 = z;
        getMap().d(z);
        getMap().v0();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public uf d() {
        return this.h1;
    }

    public void d(int i2, int i3) {
        List<h5> list = this.T;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                h5 h5Var = this.T.get(i4);
                if (h5Var != null) {
                    h5Var.a(i2, i3);
                }
            }
        }
        if (getMapContext() != null) {
            getMapContext().Z().a(i2, i3);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void d(boolean z) {
        this.M0 = z;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean d(float f2, float f3) {
        if (this.p) {
            return getMap().d(f2, f3);
        }
        return false;
    }

    public void d0() {
        qc mapContext = getMapContext();
        if (mapContext != null) {
            mapContext.a(!a(sh.b().c(sh.f17255i)));
            vh vhVar = this.M;
            if (vhVar != null) {
                vhVar.b();
            }
            boolean a2 = mapContext.a();
            if (this.O != null) {
                v5 i0 = i0();
                if (mapContext.A() && !mapContext.B()) {
                    i0 = new v5(y.b(new GeoPoint(new LatLng(39.908823d, 116.39747d))), sh.b().c(sh.f17255i), 18.0f);
                }
                this.O.a(i0, a2);
                this.O.e(a2);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void e() {
        Handler handler = this.g0;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        Handler handler2 = this.o0;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
        }
        List<h5> list = this.T;
        if (list != null) {
            list.clear();
            this.T = null;
        }
        E();
        this.U = null;
        this.b0 = null;
        this.X = null;
        this.i0 = null;
        this.R = null;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void e(boolean z) {
        this.J0 = z;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean e(float f2, float f3) {
        return A().a(f2, f3, this.b1);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean f() {
        return this.L0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean f(float f2, float f3) {
        String c2;
        if (this.V != null && (c2 = c(f2, f3)) != null && c2.trim().length() != 0) {
            this.V.a(c2);
            return true;
        } else if (this.X == null || !this.p) {
            return false;
        } else {
            this.X.onMapLongClick(ea.d(getMap().getProjection().a(new o5(f2, f3))));
            return false;
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void g(boolean z) {
        VectorMap map;
        b1 b1Var;
        if (!z || f0()) {
            map = getMap();
            b1Var = this.d0;
        } else {
            map = getMap();
            b1Var = null;
        }
        map.a(b1Var);
    }

    public boolean g(float f2, float f3) {
        i1 Z = A().Z();
        List<t4> h2 = Z.h();
        t4 t4Var = null;
        boolean z = false;
        for (int size = h2.size() - 1; size >= 0; size--) {
            t4Var = h2.get(size);
            z = a(t4Var, f2, f3);
            if (z) {
                break;
            }
        }
        if (!z) {
            List<t4> d2 = Z.d();
            for (int size2 = d2.size() - 1; size2 >= 0; size2--) {
                t4Var = d2.get(size2);
                z = a(t4Var, f2, f3);
                if (z) {
                    break;
                }
            }
        }
        if (!z) {
            List<Polygon> i2 = Z.i();
            for (int size3 = i2.size() - 1; size3 >= 0; size3--) {
                t4Var = (t4) i2.get(size3);
                z = a(t4Var, f2, f3);
                if (z) {
                    break;
                }
            }
        }
        if (z) {
            if (t4Var instanceof w0) {
                return a((w0) t4Var, f2, f3);
            }
            if (t4Var instanceof y0) {
                return a((y0) t4Var, f2, f3);
            }
            if (t4Var instanceof x0) {
                return a((x0) t4Var, f2, f3);
            }
            return false;
        }
        return z;
    }

    public ph g0() {
        return this.O;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void h(boolean z) {
        this.K0 = z;
    }

    public o1.b h0() {
        return getMapContext().q();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public b0 i() {
        return this.Q;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void i(boolean z) {
        this.H0 = z;
    }

    public v5 i0() {
        w5[] m0 = m0();
        w5 b2 = y.b(getMap().s());
        float V = getMap().V();
        v5 v5Var = this.c1;
        if (v5Var == null) {
            this.c1 = new v5(b2, m0, V);
        } else {
            v5Var.a(b2, m0, V);
        }
        return this.c1;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean isHandDrawMapEnable() {
        tg tgVar = this.N;
        if (tgVar == null) {
            return false;
        }
        return tgVar.c();
    }

    @Override // com.tencent.mapsdk.internal.u4
    public void j() {
        getMap().o0();
        TencentMap.OnCompassClickedListener onCompassClickedListener = this.i0;
        if (onCompassClickedListener != null) {
            onCompassClickedListener.onCompassClicked();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void j(boolean z) {
        this.p = z;
    }

    public List<o0> j0() {
        this.j1.clear();
        w5[] k0 = k0();
        for (t4 t4Var : A().Z().h()) {
            if (t4Var instanceof w0) {
                o0 x = ((w0) t4Var).x();
                if (a(x.getPosition(), k0)) {
                    this.j1.add(x);
                }
            }
        }
        return this.j1;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public dg k() {
        return this.P;
    }

    @Override // com.tencent.mapsdk.internal.p1
    public void k(boolean z) {
        super.k(z);
        d0();
    }

    public List<MapPoi> l0() {
        this.k1.clear();
        ArrayList<MapPoi> N = getMap().N();
        this.k1 = N;
        return N;
    }

    public w5[] m0() {
        s4 projection = getMap().getProjection();
        if (projection == null) {
            return null;
        }
        w5[] w5VarArr = new w5[4];
        o5[] o5VarArr = new o5[4];
        float f2 = this.Z0;
        float f3 = this.a1;
        if (f2 <= 2.0f || f3 <= 2.0f) {
            return null;
        }
        o5VarArr[0] = new o5(0.0d, 0.0d);
        double d2 = f2;
        o5VarArr[1] = new o5(d2, 0.0d);
        double d3 = f3;
        o5VarArr[2] = new o5(d2, d3);
        o5VarArr[3] = new o5(0.0d, d3);
        for (int i2 = 0; i2 < 4; i2++) {
            w5VarArr[i2] = y.b(projection.a(o5VarArr[i2]));
        }
        return w5VarArr;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public int n() {
        return this.v0;
    }

    public boolean n(boolean z) {
        Handler handler = this.g0;
        if (handler == null || !handler.getLooper().getThread().isAlive()) {
            return false;
        }
        ma.a(la.f16819f, "send onStable Event");
        this.g0.sendEmptyMessage(z ? 2 : 1);
        return true;
    }

    public vh n0() {
        return this.M;
    }

    public boolean o0() {
        Handler handler = this.g0;
        if (handler == null || !handler.getLooper().getThread().isAlive()) {
            return false;
        }
        this.g0.sendEmptyMessage(0);
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
        this.r = false;
        this.s = true;
        ba.b(new e(cameraPosition));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnCameraChangeListener
    public void onCameraChangeFinished(CameraPosition cameraPosition) {
        if (this.t == 0 && this.r) {
            this.s = false;
            ba.b(new f(cameraPosition));
        }
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onCreated() {
        super.onCreated();
        this.g0 = new pi(this, ba.a("gesture"));
        this.h1 = new uf(getMapContext());
        getMap().a(this.e0);
        a(this.b1);
        getMap().a(this.d0);
        getMap().a(this.k0);
        getMap().a(this.e0);
        getMap().a(this.f0);
        getMap().a((u4) this);
        this.Q = new b0(this, h0().j());
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onDestroy() {
        super.onDestroy();
        getMap().a((u4) null);
        List<TencentMap.OnCameraChangeListener> list = this.W;
        if (list != null) {
            list.clear();
        }
        ef efVar = this.g1;
        if (efVar != null) {
            efVar.a();
        }
        this.c0 = null;
        this.i1 = null;
        this.l0 = null;
        this.E0 = true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorBuildingDeactivated() {
        return getMapContext().Z().a((IndoorBuilding) null);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorBuildingFocused() {
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.TencentMap.OnIndoorStateChangeListener
    public boolean onIndoorLevelActivated(IndoorBuilding indoorBuilding) {
        return getMapContext().Z().a(indoorBuilding);
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onResume() {
        super.onResume();
        this.t = 0;
        this.s = false;
        e0();
        b0 b0Var = this.Q;
        if (b0Var != null) {
            b0Var.m();
        }
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean onTouchEvent(MotionEvent motionEvent) {
        c1 c1Var = this.V;
        if (c1Var != null) {
            c1Var.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public int q() {
        return this.t0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean r() {
        return this.R0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean s() {
        return this.D0;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void setCompassExtraPadding(int i2) {
        this.G0 = i2;
        setCompassExtraPadding(this.F0, i2);
        a();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void setCompassExtraPadding(int i2, int i3) {
        this.F0 = i2;
        this.G0 = i3;
        getMap().f(i2, i3);
        a();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void setFlingGestureEnabled(boolean z) {
        this.I0 = z;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void setOnTapMapViewInfoWindowHidden(boolean z) {
        this.K = z;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void t() {
        if (this.N == null) {
            this.N = new tg(this);
        }
        this.N.b();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public uh u() {
        return this.M.e();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean v() {
        return this.Q0;
    }

    @Override // com.tencent.mapsdk.internal.ej, com.tencent.mapsdk.internal.fj.n
    public void x() {
        super.x();
        qa.i(pa.X);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void y() {
        tg tgVar = this.N;
        if (tgVar == null) {
            return;
        }
        tgVar.e();
    }

    @Override // com.tencent.mapsdk.internal.p1
    public void z() {
        super.z();
        TencentMapOptions tencentMapOptions = this.f16972l;
        if (tencentMapOptions != null) {
            this.B0 = tencentMapOptions.isHandDrawMapEnable();
            this.d1 = this.f16972l.isMultipleInfoWindowEnable();
        }
    }
}
