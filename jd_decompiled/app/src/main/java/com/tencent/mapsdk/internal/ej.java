package com.tencent.mapsdk.internal;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.MapViewType;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListenerList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public abstract class ej extends p1<qc, VectorMap> implements a1, ae, oe {
    private static final int G = 10;
    private static final int H = 16;
    public v1 A;
    private volatile boolean B;
    public TencentMap.OnPolylineClickListener C;
    public TencentMap.OnPolygonClickListener D;
    private TencentMap.InfoWindowAdapter E;
    private boolean F;

    /* renamed from: n */
    public List<TencentMap.OnMapLoadedCallback> f16478n;
    public TencentMapGestureListenerList o;
    public boolean p;
    public volatile boolean q;
    public volatile boolean r;
    public volatile boolean s;
    public int t;
    private qc u;
    private vf v;
    private rc w;
    private volatile boolean x;
    private float y;
    private int z;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ float a;
        public final /* synthetic */ float b;

        /* renamed from: c */
        public final /* synthetic */ float f16479c;
        public final /* synthetic */ float d;

        /* renamed from: e */
        public final /* synthetic */ boolean f16480e;

        /* renamed from: f */
        public final /* synthetic */ float f16481f;

        /* renamed from: com.tencent.mapsdk.internal.ej$a$a */
        /* loaded from: classes9.dex */
        public class RunnableC0791a implements Runnable {
            public RunnableC0791a() {
                a.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                ej.this.u.h().b(a.this.f16481f);
            }
        }

        public a(float f2, float f3, float f4, float f5, boolean z, float f6) {
            ej.this = r1;
            this.a = f2;
            this.b = f3;
            this.f16479c = f4;
            this.d = f5;
            this.f16480e = z;
            this.f16481f = f6;
        }

        @Override // java.lang.Runnable
        public void run() {
            ej.this.y += this.a;
            ej ejVar = ej.this;
            ejVar.a(this.b, ejVar.y, true);
            if (ej.b(ej.this) < 10) {
                ba.a(this, 16L);
                return;
            }
            ej.this.a(this.b, this.f16479c, true);
            float f2 = this.d;
            if (f2 < 3.0f || f2 > 20.0f) {
                return;
            }
            if (!this.f16480e) {
                ej.this.u.h().b(this.f16481f);
                return;
            }
            ej.this.u.h().a((int) this.d, (Runnable) new RunnableC0791a(), false);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public b() {
            ej.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            for (TencentMap.OnMapLoadedCallback onMapLoadedCallback : ej.this.f16478n) {
                if (onMapLoadedCallback != null) {
                    onMapLoadedCallback.onMapLoaded();
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            MapViewType.values();
            int[] iArr = new int[3];
            a = iArr;
            try {
                MapViewType mapViewType = MapViewType.TextureView;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                MapViewType mapViewType2 = MapViewType.RenderLayer;
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                MapViewType mapViewType3 = MapViewType.SurfaceView;
                iArr3[0] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public ej(Context context, TencentMapOptions tencentMapOptions, ViewGroup viewGroup) {
        super(context, tencentMapOptions, viewGroup);
        this.f16478n = new CopyOnWriteArrayList();
        this.o = null;
        this.p = true;
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = 0;
        this.y = 0.5f;
        this.z = 0;
        this.A = null;
    }

    private void U() {
        if (this.w != null) {
            while (!this.w.e()) {
                try {
                    this.w.a();
                    this.w.c();
                } catch (InterruptedException e2) {
                    ma.c(Log.getStackTraceString(e2));
                }
                if (!this.w.isAlive()) {
                    break;
                }
                this.w.join();
            }
        }
        this.w = null;
        this.x = false;
    }

    public void a(float f2, float f3, boolean z) {
        this.u.h().a(f2, f3, 0, z);
    }

    public static /* synthetic */ int b(ej ejVar) {
        int i2 = ejVar.z;
        ejVar.z = i2 + 1;
        return i2;
    }

    private void c0() {
        long currentTimeMillis = System.currentTimeMillis();
        v6 w = getMapContext().w();
        if (w != null) {
            w.l().b(true, currentTimeMillis);
        }
        this.B = false;
    }

    private float d(float f2) {
        int i2 = (int) f2;
        return (1 << (i2 - 3)) * 3.0517578E-5f * ((float) Math.pow(2.0d, f2 - i2));
    }

    @Override // com.tencent.mapsdk.internal.a1
    public qc A() {
        return this.u;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean B() {
        qc qcVar = this.u;
        return qcVar != null && qcVar.k0();
    }

    @Override // com.tencent.mapsdk.internal.p1
    public void P() {
        super.P();
        a(this.f16971k, this.f16972l);
    }

    public int V() {
        v1 v1Var = this.A;
        if (v1Var != null) {
            return v1Var.getHeight();
        }
        return Integer.MAX_VALUE;
    }

    public TencentMap.InfoWindowAdapter W() {
        return this.E;
    }

    public int X() {
        v1 v1Var = this.A;
        if (v1Var != null) {
            return v1Var.getWidth();
        }
        return Integer.MAX_VALUE;
    }

    public boolean Y() {
        Context context = getContext();
        if (context == null) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        boolean isEnabled = accessibilityManager.isEnabled();
        if (Build.VERSION.SDK_INT < 14) {
            return isEnabled;
        }
        return isEnabled && accessibilityManager.isTouchExplorationEnabled();
    }

    public void Z() {
        if (!this.q) {
            ba.b(new b());
        }
        this.q = true;
    }

    @Override // com.tencent.mapsdk.internal.p1
    /* renamed from: a */
    public v1 b(qc qcVar, ViewGroup viewGroup) {
        int ordinal = T().ordinal();
        v1 ijVar = ordinal != 1 ? ordinal != 2 ? new ij(qcVar) : new gj(qcVar) : new hj(qcVar);
        if (viewGroup != null) {
            viewGroup.addView(ijVar.getView());
        }
        return ijVar;
    }

    @Override // com.tencent.mapsdk.internal.p1
    /* renamed from: a */
    public VectorMap b(qc qcVar) {
        return new VectorMap(qcVar);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a() {
        v1 v1Var = this.A;
        if (v1Var != null) {
            v1Var.j();
        }
        qc qcVar = this.u;
        if (qcVar != null) {
            qcVar.w0();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(ce ceVar) {
        this.u.a(ceVar);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(v4 v4Var) {
        this.v.a(v4Var);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void a(xd xdVar, hb hbVar) {
        if (this.u.a(this.f16971k.getApplicationContext(), xdVar, hbVar, this)) {
            this.u.h().z();
        }
    }

    public void a(TencentMap.InfoWindowAdapter infoWindowAdapter) {
        this.E = infoWindowAdapter;
    }

    public void a(TencentMap.OnPolygonClickListener onPolygonClickListener) {
        this.D = onPolygonClickListener;
    }

    public void a(TencentMap.OnPolylineClickListener onPolylineClickListener) {
        this.C = onPolylineClickListener;
    }

    public void a(TencentMap.OnVectorOverlayClickListener onVectorOverlayClickListener) {
        this.u.a(onVectorOverlayClickListener);
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, int i2, int i3) {
        this.u.h(i2, i3);
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void a(GL10 gl10, EGLConfig eGLConfig) {
        this.u.a(gl10);
    }

    @Override // com.tencent.mapsdk.internal.ae
    public boolean a(int i2) {
        qc qcVar = this.u;
        if (qcVar != null) {
            return qcVar.h(i2);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public boolean a(GL10 gl10) {
        return this.u.c(gl10);
    }

    public void a0() {
        this.r = true;
        TencentMapGestureListenerList tencentMapGestureListenerList = this.o;
        if (tencentMapGestureListenerList == null || !this.p) {
            return;
        }
        tencentMapGestureListenerList.onMapStable();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void b(v4 v4Var) {
        this.v.b(v4Var);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean b(int i2) {
        qc qcVar = this.u;
        if (qcVar == null) {
            return false;
        }
        return qcVar.a(i2);
    }

    public void b0() {
        v1 v1Var = this.A;
        if (v1Var != null) {
            v1Var.j();
        }
    }

    @Override // com.tencent.mapsdk.internal.p1
    /* renamed from: c */
    public qc b(Context context, TencentMapOptions tencentMapOptions) {
        qc qcVar = new qc(context, tencentMapOptions, this);
        this.u = qcVar;
        return qcVar;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void f(boolean z) {
        this.u.p(z);
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void g() {
        if (this.w == null && this.u != null) {
            this.w = new rc(this.u);
        }
        if (this.x) {
            return;
        }
        try {
            this.w.start();
            this.x = true;
        } catch (Exception e2) {
            ma.f("startTextureCreatorIfNeed failed", e2);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public Context getContext() {
        return this.f16971k;
    }

    @Override // com.tencent.mapsdk.internal.a1, com.tencent.mapsdk.internal.le
    public int getEGLContextHash() {
        EGLContext eglGetCurrentContext;
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        if (egl10 == null || (eglGetCurrentContext = egl10.eglGetCurrentContext()) == null) {
            return 0;
        }
        return eglGetCurrentContext.hashCode();
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void h() {
        qc qcVar = this.u;
        if (qcVar != null) {
            qcVar.K();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public TencentMapOptions l() {
        return this.f16972l;
    }

    public void l(boolean z) {
        this.B = z;
    }

    public void m(boolean z) {
        v1 v1Var = this.A;
        if (v1Var != null) {
            v1Var.setZOrderMediaOverlay(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public boolean m() {
        return this.q;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public xd o() {
        qc qcVar = this.u;
        if (qcVar == null) {
            return null;
        }
        return qcVar.M();
    }

    @Override // com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onCreated() {
        super.onCreated();
        this.A = getMapRenderView();
        this.v = new vf(this);
        this.B = true;
        getMap().a((oe) this);
    }

    @Override // com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.tencent.mapsdk.internal.oe
    public void onMapLoaded() {
        if (this.B) {
            c0();
        }
    }

    @Override // com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onPause() {
        super.onPause();
        rc rcVar = this.w;
        if (rcVar != null) {
            rcVar.a();
        }
        this.u.u0();
    }

    @Override // com.tencent.mapsdk.internal.p1, com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public void onResume() {
        super.onResume();
        rc rcVar = this.w;
        if (rcVar != null) {
            rcVar.b();
        }
        this.u.z0();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.BaseMapView.MapViewProxy
    public boolean onTouchEvent(MotionEvent motionEvent) {
        vf vfVar = this.v;
        if (vfVar != null) {
            return vfVar.onTouch(null, motionEvent);
        }
        return false;
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void p() {
        U();
        if (this.u != null) {
            getEGLContextHash();
            this.u.J();
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public void setMapCenterAndScale(float f2, float f3, float f4) {
        qc qcVar = this.u;
        if (qcVar != null) {
            float q = qcVar.h().q();
            float d = d(f4);
            boolean z = ((double) Math.abs(q - d)) > 1.0E-4d;
            this.z = 0;
            ba.b(new a((f3 - this.y) / 10.0f, f2, f3, f4, z, d));
        }
    }

    @Override // com.tencent.mapsdk.internal.a1
    public pc w() {
        return this.u.U();
    }

    @Override // com.tencent.mapsdk.internal.fj.n
    public void x() {
    }
}
