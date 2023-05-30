package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class df extends v0<n0> implements me, n0, q4 {

    /* renamed from: g */
    private xi f16408g;

    /* renamed from: h */
    private View f16409h;

    /* renamed from: m */
    private GeoPoint f16414m;

    /* renamed from: n */
    private boolean f16415n;
    private boolean o;
    private o0 r;

    /* renamed from: i */
    private int f16410i = 0;

    /* renamed from: j */
    private int f16411j = 0;

    /* renamed from: k */
    private float f16412k = 0.5f;

    /* renamed from: l */
    private float f16413l = 0.5f;
    private boolean p = false;
    private final o5 q = new o5();

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public final /* synthetic */ View a;

        public a(View view) {
            df.this = r1;
            this.a = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (!df.this.f16415n) {
                this.a.setVisibility(8);
                return;
            }
            this.a.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
            df.this.f16410i = this.a.getMeasuredWidth();
            df.this.f16411j = this.a.getMeasuredHeight();
            View view = this.a;
            view.layout(0, 0, view.getMeasuredWidth(), this.a.getMeasuredHeight());
            ViewGroup F = df.this.f16408g.F();
            Rect screenBound = df.this.getScreenBound(df.this.f16408g.getMap().getProjection());
            if (screenBound == null) {
                return;
            }
            if (this.a.getParent() == null) {
                F.addView(this.a);
            }
            Rect rect = new Rect();
            F.getLocalVisibleRect(rect);
            if (rect.isEmpty()) {
                this.a.setVisibility(8);
                return;
            }
            if (rect.intersect(screenBound)) {
                this.a.setVisibility(0);
            }
            this.a.setX(screenBound.left);
            this.a.setY(screenBound.top);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ e1 b;

        /* renamed from: c */
        public final /* synthetic */ TencentMap.InfoWindowAdapter f16416c;

        public b(Context context, e1 e1Var, TencentMap.InfoWindowAdapter infoWindowAdapter) {
            df.this = r1;
            this.a = context;
            this.b = e1Var;
            this.f16416c = infoWindowAdapter;
        }

        @Override // java.lang.Runnable
        public void run() {
            df dfVar = df.this;
            dfVar.f16409h = ze.a(this.a, this.b, dfVar, this.f16416c, dfVar.r);
            df.this.B();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements Runnable {
        public final /* synthetic */ ViewParent a;
        public final /* synthetic */ View b;

        public c(ViewParent viewParent, View view) {
            df.this = r1;
            this.a = viewParent;
            this.b = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            ((ViewGroup) this.a).removeView(this.b);
            View view = this.b;
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).removeAllViews();
            }
            df.this.releaseData();
        }
    }

    public df(xi xiVar, o0 o0Var) {
        this.f16408g = xiVar;
        this.r = o0Var;
        D();
        A();
        this.f16415n = true;
    }

    private void A() {
        xi xiVar = this.f16408g;
        if (xiVar == null || xiVar.getContext() == null) {
            return;
        }
        o0 o0Var = this.r;
        this.f16409h = ze.a(this.f16408g.getContext(), this.f16408g.getMapContext(), this, o0Var != null ? o0Var.t() : null, this.r);
        o0 o0Var2 = this.r;
        if (o0Var2 != null) {
            setPosition(o0Var2.getPosition());
        }
    }

    public void B() {
        View view;
        VectorMap map;
        ViewGroup y;
        xi xiVar = this.f16408g;
        if (xiVar == null || (view = this.f16409h) == null || (map = xiVar.getMap()) == null || map.getProjection() == null || (y = y()) == null) {
            return;
        }
        y.post(new a(view));
    }

    private void C() {
        xi xiVar = this.f16408g;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.f16408g.getMap().b((me) this);
    }

    private void D() {
        xi xiVar = this.f16408g;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.f16408g.getMap().a((me) this);
    }

    private ViewGroup y() {
        xi xiVar = this.f16408g;
        if (xiVar == null) {
            return null;
        }
        return xiVar.F();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        if (getScreenBound(s4Var) != null && s4Var != null) {
            GeoPoint a2 = s4Var.a(new o5(r0.left, r0.top));
            GeoPoint a3 = s4Var.a(new o5(r0.right, r0.bottom));
            if (a2 != null && a3 != null) {
                return new Rect(a2.getLongitudeE6(), a2.getLatitudeE6(), a3.getLongitudeE6(), a3.getLatitudeE6());
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void a(MarkerOptions markerOptions) {
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.mapsdk.internal.n4
    public void a(GL10 gl10) {
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        int i2;
        if (s4Var == null || this.f16409h == null) {
            return null;
        }
        o5 a2 = this.p ? this.q : s4Var.a(this.f16414m);
        if (a2 == null) {
            return null;
        }
        o0 o0Var = this.r;
        int i3 = 0;
        if (o0Var == null || o0Var.getOptions() == null) {
            i2 = 0;
        } else {
            i3 = this.r.getOptions().getInfoWindowOffsetX();
            i2 = this.r.getOptions().getInfowindowOffsetY();
        }
        w();
        k();
        float f2 = this.f16412k;
        int i4 = this.f16410i;
        float f3 = i4;
        float f4 = f2 - ((i3 * 1.0f) / f3);
        float f5 = this.f16413l;
        int i5 = this.f16411j;
        float f6 = i5;
        float f7 = f5 - ((i2 * 1.0f) / f6);
        double d = a2.a;
        double d2 = f3 * f4;
        Double.isNaN(d2);
        int i6 = (int) (d - d2);
        double d3 = a2.b;
        double d4 = f6 * f7;
        Double.isNaN(d4);
        int i7 = (int) (d3 - d4);
        return new Rect(i6, i7, i4 + i6, i5 + i7);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void e(boolean z) {
        if (this.f16409h == null) {
            return;
        }
        this.f16415n = z;
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public boolean isRemoved() {
        return this.o;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public boolean isVisible() {
        return r();
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void k() {
        if (this.r == null || this.f16408g.getContext() == null) {
            return;
        }
        int height = (int) (this.r.getHeight(this.f16408g.getContext()) * this.r.getAnchorV());
        int i2 = this.f16411j;
        float infoWindowAnchorV = this.r.getOptions() != null ? this.r.getOptions().getInfoWindowAnchorV() : 1.0f;
        if (i2 == 0) {
            i2 = 1;
        }
        float f2 = i2;
        this.f16413l = (height + (infoWindowAnchorV * f2)) / f2;
    }

    @Override // com.tencent.mapsdk.internal.q4
    public View o() {
        return this.f16409h;
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChangeStopped() {
        B();
    }

    @Override // com.tencent.mapsdk.internal.me
    public void onMapCameraChanged() {
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        xi xiVar;
        Rect screenBound;
        if (this.f16409h == null || !this.f16415n || (xiVar = this.f16408g) == null || xiVar.getMap() == null || this.f16408g.getMap().getProjection() == null || (screenBound = getScreenBound(this.f16408g.getMap().getProjection())) == null || screenBound.isEmpty()) {
            return false;
        }
        return screenBound.contains((int) f2, (int) f3);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public boolean r() {
        View view;
        return this.f16415n && (view = this.f16409h) != null && view.getVisibility() == 0;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
        C();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void remove() {
        View view = this.f16409h;
        if (view == null) {
            return;
        }
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).post(new c(parent, view));
        }
        this.o = true;
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setAnchor(float f2, float f3) {
        B();
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setFixingPoint(int i2, int i3) {
        setFixingPointEnable(true);
        this.q.e(i2, i3);
        B();
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setFixingPointEnable(boolean z) {
        this.p = z;
        if (z) {
            C();
        } else {
            D();
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            return;
        }
        GeoPoint geoPoint = this.f16414m;
        if (geoPoint == null) {
            this.f16414m = GeoPoint.from(latLng);
        } else {
            geoPoint.setLatitudeE6((int) (latLng.latitude * 1000000.0d));
            this.f16414m.setLongitudeE6((int) (latLng.longitude * 1000000.0d));
        }
        B();
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public void setVisible(boolean z) {
        e(z);
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void u() {
        xi xiVar = this.f16408g;
        if (xiVar == null) {
            return;
        }
        o0 o0Var = this.r;
        TencentMap.InfoWindowAdapter t = o0Var != null ? o0Var.t() : null;
        Context context = xiVar.getContext();
        qc mapContext = xiVar.getMapContext();
        ViewGroup y = y();
        if (y != null) {
            y.post(new b(context, mapContext, t));
        }
    }

    @Override // com.tencent.mapsdk.internal.q4
    public void w() {
        if (this.r == null || this.f16408g.getContext() == null) {
            return;
        }
        int width = this.r.getWidth(this.f16408g.getContext());
        float infoWindowAnchorU = this.r.getOptions() != null ? this.r.getOptions().getInfoWindowAnchorU() : 0.5f;
        int i2 = this.f16410i;
        if (i2 == 0) {
            i2 = 1;
        }
        this.f16412k = infoWindowAnchorU + ((width * (this.r.getAnchorU() - 0.5f)) / i2);
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: z */
    public n0 x() {
        return this;
    }
}
