package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.tools.ReturnCallback;
import com.tencent.map.tools.Util;
import com.tencent.mapsdk.internal.o7;
import com.tencent.mapsdk.internal.z7;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Collision;
import com.tencent.tencentmap.mapsdk.maps.model.Animation;
import com.tencent.tencentmap.mapsdk.maps.model.AnimationListener;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptorFactory;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerCollisionItem;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class af extends ye<o0> implements o0 {
    public Object B;
    public Bitmap C;
    public boolean D;
    public final Object E;
    public float F;
    public float G;
    public boolean H;
    public float I;
    public float J;
    public float K;
    public MarkerOptions L;
    public String M;
    public GeoPoint N;
    public GeoPoint O;
    public GeoPoint P;
    public float Q;
    public float R;
    public float S;
    public boolean T;
    private boolean U;
    private int V;
    private int W;
    private boolean X;
    private GeoPoint Y;
    private xi Z;
    private hg a0;
    private AnimationListener b0;
    private z7 c0;
    private TencentMap.OnMarkerClickListener d0;
    public q4 e0;
    private boolean f0;
    private LatLng g0;
    private Collision[] h0;
    private int i0;
    private final z7.b j0;

    /* loaded from: classes9.dex */
    public class a implements z7.b {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(float f2) {
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(float f2, float f3, float f4, float f5) {
            af.this.setRotation(f2);
            af afVar = af.this;
            afVar.I = f3;
            afVar.J = f4;
            afVar.K = f5;
            afVar.H = true;
            if (afVar.a0 != null) {
                af.this.a0.b((int) af.this.getRotation());
            }
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void a(int i2, int i3) {
            if (af.this.Z == null || af.this.N == null) {
                return;
            }
            GeoPoint geoPoint = new GeoPoint();
            if (!af.this.X || af.this.Y == null || af.this.Z.getMap() == null) {
                af.this.N.setLatitudeE6(i2 + 0);
                af.this.N.setLongitudeE6(i3 + 0);
            } else {
                GeoPoint a = af.this.Z.getMap().getProjection().a(new o5(af.this.V, af.this.W));
                geoPoint.setLatitudeE6(i2 + (a.getLatitudeE6() - af.this.Y.getLatitudeE6()));
                geoPoint.setLongitudeE6(i3 + (a.getLongitudeE6() - af.this.Y.getLongitudeE6()));
                o5 a2 = af.this.Z.getMap().getProjection().a(geoPoint);
                af.this.N.setLatitudeE6((int) a2.b);
                af.this.N.setLongitudeE6((int) a2.a);
            }
            af afVar = af.this;
            MarkerOptions markerOptions = afVar.L;
            if (markerOptions != null) {
                markerOptions.position(ea.d(afVar.N));
            }
            if (af.this.a0 != null) {
                af.this.a0.a(af.this.N);
            }
            af afVar2 = af.this;
            q4 q4Var = afVar2.e0;
            if (q4Var != null) {
                q4Var.setPosition(ea.d(afVar2.N));
            }
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void setAlpha(float f2) {
            af afVar = af.this;
            afVar.Q = f2;
            if (afVar.a0 != null) {
                af.this.a0.setAlpha(af.this.Q);
                af afVar2 = af.this;
                afVar2.L.alpha(afVar2.Q);
            }
            af afVar3 = af.this;
            q4 q4Var = afVar3.e0;
            if (q4Var != null) {
                q4Var.a(afVar3.L);
            }
        }

        @Override // com.tencent.mapsdk.internal.z7.b
        public void setScale(float f2, float f3) {
            af.this.a(f2, f3);
        }
    }

    /* loaded from: classes9.dex */
    public class b extends o7.a {
        public b() {
        }

        @Override // com.tencent.mapsdk.internal.o7.a
        public void a(float f2) {
            MarkerOptions markerOptions;
            super.a(f2);
            if (af.this.a0 == null || af.this.Z == null || (markerOptions = af.this.L) == null) {
                return;
            }
            BitmapDescriptor icon = markerOptions.getIcon();
            int activeSize = icon.getFormater().activeSize();
            ma.a(la.f16817c, "current Marker iconLooper getValue:" + f2);
            int round = Math.round(((float) activeSize) * f2);
            ma.a(la.f16817c, "current Marker iconLooper activeIndex:" + round);
            if (round != af.this.i0) {
                af afVar = af.this;
                afVar.a(afVar.Z.getContext(), icon.getFormater());
                icon.getFormater().nextActiveIndex();
                af.this.i0 = round;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class c implements ReturnCallback<Boolean, Collision> {
        public final /* synthetic */ Collision a;

        public c(Collision collision) {
            this.a = collision;
        }

        @Override // com.tencent.map.tools.ReturnCallback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Boolean callback(Collision collision) {
            return Boolean.valueOf(collision == this.a);
        }
    }

    public af(xi xiVar) {
        super(xiVar);
        this.B = null;
        this.C = null;
        this.D = false;
        this.E = new Object();
        this.F = 0.5f;
        this.G = 0.5f;
        this.H = false;
        this.I = 0.0f;
        this.J = 0.0f;
        this.K = -1.0f;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = 1.0f;
        this.R = 1.0f;
        this.S = 1.0f;
        this.T = false;
        this.U = false;
        this.V = 0;
        this.W = 0;
        this.X = false;
        this.Y = null;
        this.Z = null;
        this.b0 = null;
        this.c0 = null;
        this.j0 = new a();
        this.Z = xiVar;
        setClickable(true);
    }

    private boolean J() {
        MarkerOptions markerOptions = this.L;
        if (markerOptions == null || !markerOptions.isInfoWindowEnable()) {
            return false;
        }
        q4 q4Var = this.e0;
        return ((q4Var == null || q4Var.o() == null) && e7.b(markerOptions.getTitle()) && e7.b(markerOptions.getSnippet())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f2, float f3) {
        int anchorU;
        if (f2 >= 0.0f) {
            this.R = f2;
        }
        if (f3 >= 0.0f) {
            this.S = f3;
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setScale(this.R, this.S);
            int height = getHeight(this.Z.getContext());
            float width = getWidth(this.Z.getContext());
            float f4 = this.R * width;
            if (f4 >= width) {
                float f5 = f4 - width;
                anchorU = (int) ((0.5f * f5) - (getAnchorU() * f5));
            } else {
                float f6 = width - f4;
                anchorU = (int) ((getAnchorU() * f6) - (f6 * 0.5f));
            }
            float f7 = height;
            this.L.infoWindowOffset(anchorU, (int) (getAnchorV() * (f7 - (this.S * f7))));
        }
        if (this.e0 != null) {
            refreshInfoWindow();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, BitmapDescriptor.BitmapFormator bitmapFormator) {
        if (bitmapFormator != null) {
            Bitmap bitmap = bitmapFormator.getBitmap(context);
            String str = this.M;
            if (str == null || !str.equals(bitmapFormator.getBitmapId())) {
                a(bitmapFormator.getBitmapId());
                a(bitmap);
            }
        }
    }

    private void a(String str) {
        synchronized (this.E) {
            this.M = str;
        }
    }

    private void b(MarkerOptions markerOptions) {
        if (this.Z == null || markerOptions == null || this.a0 != null) {
            return;
        }
        ig igVar = new ig();
        igVar.a(GeoPoint.from(markerOptions.getPosition())).a(markerOptions.getAlpha()).a(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        String a2 = wa.a();
        Bitmap bitmap = null;
        try {
            bitmap = markerOptions.getIcon().getBitmap(this.Z.getContext());
            a2 = markerOptions.getIcon().getFormater().getBitmapId();
        } catch (NullPointerException e2) {
            e2.printStackTrace();
        }
        igVar.a(a2, bitmap).c((int) markerOptions.getRotation()).f(markerOptions.isFlat()).d((int) markerOptions.getZIndex()).e(this.U).a(false).b(isCollisionBy(MarkerCollisionItem.POI)).c(markerOptions.isClockwise()).d(markerOptions.isFastLoad()).b(markerOptions.getLevel());
        this.a0 = new hg(this, this.Z, igVar);
        setIconLooper(markerOptions.getIcon(), markerOptions.isIconLooperEnable(), markerOptions.getIconLooperDuration());
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        q4 q4Var;
        hg hgVar;
        if (this.Z == null) {
            return;
        }
        if (!isVisible()) {
            releaseData();
            return;
        }
        U();
        K();
        xi xiVar = this.Z;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        if (this.Z.getMap().F() != null && (hgVar = this.a0) != null) {
            hgVar.E();
        }
        if (this.f0 && ((q4Var = this.e0) == null || !q4Var.r())) {
            showInfoWindow();
        }
        q4 q4Var2 = this.e0;
        if (q4Var2 == null || !q4Var2.r()) {
            return;
        }
        this.e0.a((GL10) null);
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        xi xiVar = this.Z;
        if (xiVar != null) {
            if (getId().equals(xiVar.I())) {
                xiVar.a("", false);
            }
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.a0();
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.remove();
        }
        this.Z = null;
    }

    public boolean K() {
        z7 z7Var;
        if (this.Z == null || (z7Var = this.c0) == null) {
            return false;
        }
        z7Var.a();
        boolean f2 = this.c0.f();
        this.X = f2;
        if (!f2 || this.Z.getMap() == null) {
            return true;
        }
        this.Z.getMap().v0();
        return true;
    }

    public int L() {
        Bitmap bitmap = this.C;
        if (bitmap == null || this.a0 == null) {
            return 0;
        }
        float width = bitmap.getWidth();
        return (int) ((this.a0.K() * width) - (width * 0.5f));
    }

    public Rect M() {
        xi xiVar;
        return (this.a0 == null || (xiVar = this.Z) == null || xiVar.getMap() == null) ? new Rect() : this.a0.getBound(this.Z.getMap().getProjection());
    }

    public String N() {
        return "GLMarkerOverlay-" + y();
    }

    public Boundable<s4> O() {
        return this.a0;
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: P  reason: merged with bridge method [inline-methods] */
    public o0 x() {
        return this;
    }

    public boolean Q() {
        return this.X;
    }

    public boolean R() {
        return this.U;
    }

    public boolean S() {
        if (this.Z == null) {
            return false;
        }
        Rect g2 = g();
        return g2.left >= 0 && g2.top >= 0 && g2.right <= this.Z.X() && g2.bottom <= this.Z.V();
    }

    public void T() {
        synchronized (this.E) {
            Bitmap bitmap = this.C;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.D = true;
            }
        }
    }

    public void U() {
        xi xiVar = this.Z;
        if (xiVar == null || !this.T) {
            return;
        }
        a(xiVar.c());
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4, com.tencent.mapsdk.internal.o0
    public int a() {
        hg hgVar = this.a0;
        return hgVar != null ? hgVar.a() : super.a();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        return M();
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        b(bitmap);
        T();
    }

    public void a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return;
        }
        GeoPoint geoPoint2 = this.N;
        if (geoPoint2 == null) {
            this.N = new GeoPoint(geoPoint.getLatitudeE6(), geoPoint.getLongitudeE6());
        } else {
            geoPoint2.setLatitudeE6(geoPoint.getLatitudeE6());
            this.N.setLongitudeE6(geoPoint.getLongitudeE6());
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.a(this.N);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.setPosition(ea.d(this.N));
        }
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void a(TencentMap.OnMarkerClickListener onMarkerClickListener) {
        this.d0 = onMarkerClickListener;
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void a(BitmapDescriptor bitmapDescriptor, BitmapDescriptor bitmapDescriptor2) {
        xi xiVar = this.Z;
        if (xiVar == null) {
            return;
        }
        if (bitmapDescriptor == null && bitmapDescriptor2 != null) {
            bitmapDescriptor = bitmapDescriptor2;
        }
        if (bitmapDescriptor == null) {
            return;
        }
        if (this.a0 != null) {
            a(xiVar.getContext(), bitmapDescriptor.getFormater());
        }
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.icon(bitmapDescriptor);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.u();
        }
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void a(LatLng latLng) {
        q4 q4Var;
        xi xiVar = this.Z;
        setPosition(latLng);
        if (!getId().equals(xiVar != null ? xiVar.I() : "") || (q4Var = this.e0) == null) {
            return;
        }
        q4Var.setPosition(latLng);
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void a(boolean z) {
        this.T = z;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Boundable
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        hg hgVar = this.a0;
        if (hgVar == null) {
            return null;
        }
        Rect screenBound = hgVar.getScreenBound(s4Var);
        q4 q4Var = this.e0;
        Rect screenBound2 = q4Var != null ? q4Var.getScreenBound(s4Var) : null;
        if (screenBound == null) {
            return screenBound2;
        }
        if (screenBound2 == null) {
            return screenBound;
        }
        return new Rect(Math.min(screenBound.left, screenBound2.left), Math.min(screenBound.top, screenBound2.top), Math.max(screenBound.right, screenBound2.right), Math.max(screenBound.bottom, screenBound2.bottom));
    }

    @Override // com.tencent.mapsdk.internal.o0
    public List<Boundable<s4>> b() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(O());
        q4 q4Var = this.e0;
        if (q4Var != null && q4Var.r()) {
            arrayList.add(this.e0);
        }
        return arrayList;
    }

    public void b(Bitmap bitmap) {
        synchronized (this.E) {
            this.C = bitmap;
            if (this.M == null) {
                this.M = bitmap.toString();
            }
            hg hgVar = this.a0;
            if (hgVar != null) {
                hgVar.b(this.M, this.C);
            }
        }
    }

    public void b(z7 z7Var) {
        this.c0 = z7Var;
        if (z7Var != null) {
            z7Var.a(this.j0);
        }
    }

    @Override // com.tencent.mapsdk.internal.o0
    @Deprecated
    public void c(boolean z) {
        xi xiVar = this.Z;
        if (xiVar != null) {
            xiVar.setOnTapMapViewInfoWindowHidden(z);
        }
    }

    public int f(boolean z) {
        Bitmap bitmap = this.C;
        if (bitmap == null || this.a0 == null) {
            return 0;
        }
        int height = bitmap.getHeight();
        return (int) (!z ? height * this.a0.L() : height * (1.0f - this.a0.L()));
    }

    @Override // com.tencent.mapsdk.internal.o0
    public Rect g() {
        xi xiVar;
        return (this.a0 == null || (xiVar = this.Z) == null || xiVar.getMap() == null) ? new Rect() : this.a0.getScreenBound(this.Z.getMap().getProjection());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public float getAlpha() {
        return this.Q;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public float getAnchorU() {
        return this.L.getAnchorU();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public float getAnchorV() {
        return this.L.getAnchorV();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Accessible
    public String getContentDescription() {
        MarkerOptions markerOptions = this.L;
        if (markerOptions == null) {
            return null;
        }
        return markerOptions.getContentDescription();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    @Deprecated
    public int getDisplayLevel() {
        return getLevel();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public float getEqualScale() {
        return this.R;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public int getHeight(Context context) {
        Bitmap bitmap;
        BitmapDescriptor icon = this.L.getIcon();
        if (icon == null || (bitmap = icon.getFormater().getBitmap(context)) == null) {
            return 0;
        }
        return bitmap.getHeight();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    @Deprecated
    public TencentMap.OnMarkerDragListener getOnDragListener() {
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public MarkerOptions getOptions() {
        return this.L;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public LatLng getPosition() {
        return this.g0;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public float[] getScale() {
        return new float[]{this.R, this.S};
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public String getSnippet() {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            return markerOptions.getSnippet();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public Object getTag() {
        return this.B;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public String getTitle() {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            return markerOptions.getTitle();
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public int getWidth(Context context) {
        Bitmap bitmap;
        BitmapDescriptor icon = this.L.getIcon();
        if (icon == null || (bitmap = icon.getFormater().getBitmap(context)) == null) {
            return 0;
        }
        return bitmap.getWidth();
    }

    @Override // com.tencent.mapsdk.internal.o0
    public boolean h() {
        return R();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean handleOnTap() {
        return true;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void hideInfoWindow() {
        xi xiVar = this.Z;
        if (xiVar == null) {
            return;
        }
        xiVar.a("", true);
        xiVar.getMap().v0();
        synchronized (xiVar.I) {
            q4 q4Var = this.e0;
            if (q4Var == null) {
                return;
            }
            q4Var.e(false);
            this.f0 = false;
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Collisionable
    public boolean isCollisionBy(Collision collision) {
        Collision[] collisionArr = this.h0;
        if (collisionArr != null) {
            return Util.where(collisionArr, new c(collision));
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isFastLoad() {
        hg hgVar = this.a0;
        if (hgVar == null) {
            return false;
        }
        return hgVar.isFastLoad();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInMapCenterState() {
        return this.T;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    @Deprecated
    public boolean isInfoWindowAutoOverturn() {
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInfoWindowEnable() {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            return markerOptions.isInfoWindowEnable();
        }
        return false;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public boolean isInfoWindowShown() {
        q4 q4Var;
        if (this.Z == null || this.L == null || (q4Var = this.e0) == null) {
            return false;
        }
        return q4Var.r();
    }

    @Override // com.tencent.mapsdk.internal.o0
    public q4 j() {
        return this.e0;
    }

    @Override // com.tencent.mapsdk.internal.o0
    public TencentMap.OnMarkerClickListener m() {
        return this.d0;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public void n() {
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        hg hgVar;
        TencentMap.OnMarkerClickListener onMarkerClickListener;
        if (this.Z == null || !isClickable() || (hgVar = this.a0) == null) {
            return false;
        }
        boolean onTap = hgVar.onTap(f2, f3);
        if (onTap && (onMarkerClickListener = this.d0) != null) {
            onMarkerClickListener.onMarkerClick(this);
        }
        return onTap;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    @Deprecated
    public boolean onTapMapViewBubbleHidden() {
        return false;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.p4
    public boolean q() {
        Object obj = this.B;
        return obj != null && lf.f16840j.equals(obj.toString());
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void refreshInfoWindow() {
        q4 q4Var;
        if (isInfoWindowEnable() && (q4Var = this.e0) != null && q4Var.r()) {
            this.e0.u();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Removable
    public void releaseData() {
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.remove();
            this.e0 = null;
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.a0();
        }
    }

    @Override // com.tencent.mapsdk.internal.o0
    public Point s() {
        return new Point(this.V, this.W);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Alphable
    public void setAlpha(float f2) {
        this.Q = f2;
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.alpha(f2);
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setAlpha(f2);
        }
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Anchorable
    public void setAnchor(float f2, float f3) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.anchor(f2, f3);
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        } else if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f3 > 1.0f) {
            f3 = 1.0f;
        } else if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        this.F = f2;
        this.G = f3;
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setAnchor(f2, f3);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.setAnchor(this.F, this.G);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void setAnimation(Animation animation) {
        i7 a2;
        xi xiVar = this.Z;
        if (xiVar == null || (a2 = h8.a(xiVar.getMapContext(), animation)) == null) {
            return;
        }
        b(a2.a);
    }

    @Override // com.tencent.mapsdk.internal.o0
    public void setAnimationListener(AnimationListener animationListener) {
        this.b0 = animationListener;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Collisionable
    public void setCollisions(Collision... collisionArr) {
        this.h0 = collisionArr;
        hg hgVar = this.a0;
        if (hgVar == null) {
            return;
        }
        hgVar.g(isCollisionBy(MarkerCollisionItem.POI));
        hgVar.f(false);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Accessible
    public void setContentDescription(String str) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.contentDescription(str);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.tencentmap.mapsdk.maps.interfaces.Draggable
    public void setDraggable(boolean z) {
        super.setDraggable(z);
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.draggable(z);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public void setEqualScale(float f2) {
        a(f2, f2);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFastLoad(boolean z) {
        hg hgVar = this.a0;
        if (hgVar == null) {
            return;
        }
        hgVar.setFastLoad(z);
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFixingPoint(int i2, int i3) {
        this.V = i2;
        this.W = i3;
        if (this.a0 != null) {
            setFixingPointEnable(true);
            this.a0.a(new GeoPoint(this.W, this.V));
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.setFixingPoint(i2, i3);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setFixingPointEnable(boolean z) {
        this.U = z;
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.h(z);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            if (z) {
                q4Var.setFixingPoint(this.V, this.W);
            } else {
                q4Var.setFixingPointEnable(false);
            }
        }
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        xi xiVar = this.Z;
        if (xiVar == null) {
            return;
        }
        a(bitmapDescriptor, BitmapDescriptorFactory.defaultMarker(xiVar.getMapContext()));
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setIconLooper(BitmapDescriptor bitmapDescriptor, boolean z, int i2) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.icon(bitmapDescriptor);
            this.L.iconLooper(z, i2);
        }
        boolean isIconLooperEnable = this.L.isIconLooperEnable();
        int activeSize = this.L.getIcon().getFormater().activeSize();
        ma.a(la.f16817c, "iconLooper totalSize:" + activeSize);
        if (activeSize > 1) {
            this.i0 = -1;
            o7 o7Var = new o7();
            o7Var.a(this.L.getIconLooperDuration());
            o7Var.a(new b());
            this.a0.a(o7Var);
            o7Var.a(isIconLooperEnable);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInMapCenterState(boolean z) {
        a(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowAnchor(float f2, float f3) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.infoWindowAnchor(f2, f3);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.u();
        }
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowEnable(boolean z) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.infoWindowEnable(z);
        }
        q4 q4Var = this.e0;
        if (q4Var == null || !q4Var.r()) {
            return;
        }
        this.e0.e(z);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setInfoWindowOffset(int i2, int i3) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.infoWindowOffset(i2, i3);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.u();
        }
        B();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable, com.tencent.tencentmap.mapsdk.maps.model.VectorOverlay
    public void setLevel(int i2) {
        super.setLevel(i2);
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setLevel(i2);
        }
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.level(i2);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setMarkerOptions(MarkerOptions markerOptions) {
        if (this.Z == null || markerOptions == null) {
            return;
        }
        this.L = markerOptions;
        D();
        setTitle(markerOptions.getTitle());
        setDraggable(markerOptions.isDraggable());
        setPosition(markerOptions.getPosition());
        setSnippet(markerOptions.getSnippet());
        setAnchor(markerOptions.getAnchorU(), markerOptions.getAnchorV());
        setVisible(markerOptions.isVisible());
        setRotation(markerOptions.getRotation());
        setIcon(markerOptions.getIcon());
        setAlpha(markerOptions.getAlpha());
        setZIndex(markerOptions.getZIndex());
        setLevel(markerOptions.getLevel());
        a(markerOptions.getIndoorInfo());
        setTag(markerOptions.getTag());
        setCollisions(markerOptions.getCollisions());
        b(getOptions());
        I();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    @Deprecated
    public void setOnTapMapViewBubbleHidden(boolean z) {
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setPosition(LatLng latLng) {
        this.g0 = latLng;
        if (latLng != null) {
            a(GeoPoint.from(latLng));
        }
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.position(latLng);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.tencentmap.mapsdk.maps.interfaces.Rotatable
    public void setRotation(float f2) {
        super.setRotation(f2);
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.b((int) getRotation());
        }
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.rotation(f2);
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Scalable
    public void setScale(float f2, float f3) {
        a(f2, f3);
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setSnippet(String str) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.snippet(str);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.u();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.tencentmap.mapsdk.maps.interfaces.Tagable
    public void setTag(Object obj) {
        this.B = obj;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void setTitle(String str) {
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.title(str);
        }
        q4 q4Var = this.e0;
        if (q4Var != null) {
            q4Var.u();
        }
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Visible
    public void setVisible(boolean z) {
        super.setVisible(z);
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setVisible(z);
        }
        xi xiVar = this.Z;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        this.Z.getMap().v0();
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(float f2) {
        this.f17513m = f2;
        MarkerOptions markerOptions = this.L;
        if (markerOptions != null) {
            markerOptions.zIndex(f2);
        }
        hg hgVar = this.a0;
        if (hgVar != null) {
            hgVar.setZIndex((int) f2);
        }
        B();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.Marker
    public void showInfoWindow() {
        xi xiVar = this.Z;
        MarkerOptions markerOptions = this.L;
        if (xiVar == null || markerOptions == null) {
            return;
        }
        synchronized (xiVar.I) {
            if (!xiVar.d1) {
                for (Marker marker : xiVar.A().Z().e()) {
                    if (marker != null && marker.isInfoWindowShown()) {
                        marker.hideInfoWindow();
                    }
                }
            }
            q4 q4Var = this.e0;
            if (q4Var == null) {
                this.e0 = markerOptions.isViewInfowindow() ? new df(xiVar, this) : new ze(xiVar, this);
            } else {
                q4Var.u();
            }
            if (this.U) {
                this.e0.setFixingPoint(this.V, this.W);
            }
            if (J()) {
                this.e0.e(true);
                this.f0 = true;
            }
        }
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public void startAnimation(Animation animation) {
        setAnimation(animation);
        startAnimation();
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.interfaces.Animationable
    public boolean startAnimation() {
        xi xiVar = this.Z;
        if (xiVar == null || this.c0 == null) {
            return false;
        }
        GeoPoint geoPoint = this.N;
        if (this.U && xiVar.getMap() != null) {
            geoPoint = this.Z.getMap().getProjection().a(new o5(this.V, this.W));
            this.Y = new GeoPoint(geoPoint);
        }
        return this.c0.a(geoPoint, this.P);
    }

    @Override // com.tencent.mapsdk.internal.o0
    public TencentMap.InfoWindowAdapter t() {
        xi xiVar = this.Z;
        if (xiVar != null) {
            return xiVar.W();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.o0
    public List<LatLng> v() {
        VectorMap map;
        Rect bound;
        xi xiVar = this.Z;
        if (xiVar == null || (map = xiVar.getMap()) == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Rect M = M();
        if (M != null) {
            double d = M.top;
            Double.isNaN(d);
            double d2 = M.left;
            Double.isNaN(d2);
            LatLng latLng = new LatLng(d / 1000000.0d, d2 / 1000000.0d);
            double d3 = M.bottom;
            Double.isNaN(d3);
            double d4 = M.left;
            Double.isNaN(d4);
            LatLng latLng2 = new LatLng(d3 / 1000000.0d, d4 / 1000000.0d);
            double d5 = M.top;
            Double.isNaN(d5);
            double d6 = M.right;
            Double.isNaN(d6);
            LatLng latLng3 = new LatLng(d5 / 1000000.0d, d6 / 1000000.0d);
            double d7 = M.bottom;
            Double.isNaN(d7);
            double d8 = M.right;
            Double.isNaN(d8);
            LatLng latLng4 = new LatLng(d7 / 1000000.0d, d8 / 1000000.0d);
            arrayList.add(latLng);
            arrayList.add(latLng2);
            arrayList.add(latLng3);
            arrayList.add(latLng4);
        }
        q4 q4Var = this.e0;
        if (q4Var != null && q4Var.r() && (bound = q4Var.getBound(map.getProjection())) != null) {
            double d9 = bound.top;
            Double.isNaN(d9);
            double d10 = bound.left;
            Double.isNaN(d10);
            LatLng latLng5 = new LatLng(d9 / 1000000.0d, d10 / 1000000.0d);
            double d11 = bound.bottom;
            Double.isNaN(d11);
            double d12 = bound.left;
            Double.isNaN(d12);
            LatLng latLng6 = new LatLng(d11 / 1000000.0d, d12 / 1000000.0d);
            double d13 = bound.top;
            Double.isNaN(d13);
            double d14 = bound.right;
            Double.isNaN(d14);
            LatLng latLng7 = new LatLng(d13 / 1000000.0d, d14 / 1000000.0d);
            double d15 = bound.bottom;
            Double.isNaN(d15);
            double d16 = bound.right;
            Double.isNaN(d16);
            LatLng latLng8 = new LatLng(d15 / 1000000.0d, d16 / 1000000.0d);
            arrayList.add(latLng5);
            arrayList.add(latLng6);
            arrayList.add(latLng7);
            arrayList.add(latLng8);
        }
        return arrayList;
    }
}
