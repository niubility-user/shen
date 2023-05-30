package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.util.Log;
import com.huawei.hms.adapter.internal.CommonCode;
import com.jd.dynamic.DYConstants;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.internal.a7;
import com.tencent.mapsdk.internal.d0;
import com.tencent.mapsdk.internal.l1;
import com.tencent.mapsdk.internal.ni;
import com.tencent.mapsdk.internal.ph;
import com.tencent.mapsdk.internal.s4;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdate;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.maps.model.CamerParameter;
import com.tencent.tencentmap.mapsdk.maps.model.CameraPosition;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayer;
import com.tencent.tencentmap.mapsdk.maps.model.CustomLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.IOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.Language;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.MapPoi;
import com.tencent.tencentmap.mapsdk.maps.model.MapRouteSection;
import com.tencent.tencentmap.mapsdk.maps.model.TencentMapGestureListener;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlay;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public final class h1 implements h5, j0, k0, m0 {
    private v1 d;

    /* renamed from: e */
    private volatile xi f16636e;

    /* renamed from: f */
    private Context f16637f;

    /* renamed from: g */
    private String f16638g;

    /* renamed from: h */
    private l1 f16639h;

    /* renamed from: j */
    private og f16641j;

    /* renamed from: k */
    private xg f16642k;

    /* renamed from: l */
    private sf f16643l;
    private CameraPosition s;
    private float w;
    private s4 x;

    /* renamed from: i */
    private lf f16640i = null;

    /* renamed from: m */
    private final GeoPoint f16644m = null;

    /* renamed from: n */
    private final GeoPoint f16645n = null;
    private int o = 0;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private final byte[] t = new byte[0];
    private int u = 0;
    private int v = 1000;
    private final h y = new h(false);
    private final h z = new h(true);
    private d0.h A = new a();
    private final ni.c B = new b();
    private k5 C = new f();

    /* loaded from: classes9.dex */
    public class a implements d0.h {
        public a() {
            h1.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.d0.h
        public void a(z8 z8Var) {
            xi xiVar;
            VectorMap map;
            if (z8Var.a == 10000 && (z8Var instanceof ni) && (xiVar = h1.this.f16636e) != null && (map = xiVar.getMap()) != null) {
                ni niVar = (ni) z8Var;
                if (niVar.p()) {
                    map.d((xiVar.Z0 / 2) - niVar.f(), (xiVar.a1 / 2) - niVar.g());
                }
                if (niVar.s()) {
                    map.d(niVar.l(), niVar.m());
                }
                if (niVar.u()) {
                    map.c(niVar.k());
                }
                if (niVar.o()) {
                    int h2 = niVar.h();
                    int i2 = niVar.i();
                    map.e(h2, i2);
                    if (niVar.q()) {
                        xiVar.c(h2, i2);
                    }
                }
                if (niVar.r()) {
                    map.b(niVar.j());
                }
                if (niVar.t()) {
                    map.d(niVar.n());
                }
                if (niVar.p()) {
                    map.d(niVar.f() - (xiVar.Z0 / 2), niVar.g() - (xiVar.a1 / 2));
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class b implements ni.c {
        public b() {
            h1.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public float a() {
            if (h1.this.f16636e == null) {
                return 0.0f;
            }
            return h1.this.o();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public void a(Runnable runnable) {
            if (runnable == null || h1.this.f16636e == null) {
                return;
            }
            ba.a(runnable, 100L);
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public GeoPoint b() {
            if (h1.this.f16636e == null) {
                return null;
            }
            return h1.this.f16636e.getMap().s();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public GeoPoint c() {
            if (h1.this.f16636e == null) {
                return null;
            }
            return h1.this.f16636e.c();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public float d() {
            if (h1.this.f16636e == null) {
                return 0.0f;
            }
            return h1.this.f16636e.getMap().T();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public float e() {
            if (h1.this.f16636e == null) {
                return 0.0f;
            }
            return h1.this.f16636e.getMap().U();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public int f() {
            if (h1.this.f16636e == null) {
                return 0;
            }
            return h1.this.f16636e.getMap().P();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public boolean g() {
            if (h1.this.f16636e == null) {
                return false;
            }
            return h1.this.f16636e.i().l();
        }

        @Override // com.tencent.mapsdk.internal.ni.c
        public float h() {
            if (h1.this.f16636e == null) {
                return 0.0f;
            }
            return h1.this.f16636e.getMap().Z();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements s4.a {
        public final /* synthetic */ boolean a;
        public final /* synthetic */ TencentMap.CancelableCallback b;

        /* renamed from: c */
        public final /* synthetic */ long f16646c;

        /* loaded from: classes9.dex */
        public class a implements Runnable {
            public final /* synthetic */ float a;
            public final /* synthetic */ GeoPoint b;

            public a(float f2, GeoPoint geoPoint) {
                c.this = r1;
                this.a = f2;
                this.b = geoPoint;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (h1.this.f16636e == null) {
                    return;
                }
                c cVar = c.this;
                boolean z = cVar.a;
                h1 h1Var = h1.this;
                if (!z) {
                    h1Var.f16636e.getMap().h(this.b);
                    h1.this.f16636e.getMap().b(this.a);
                    return;
                }
                double maxZoomLevel = (int) h1Var.getMaxZoomLevel();
                Double.isNaN(maxZoomLevel);
                double log = (maxZoomLevel - (Math.log(1.0f / this.a) / Math.log(2.0d))) - 2.0d;
                if (log < 0.0d) {
                    log = 0.0d;
                }
                ni niVar = new ni(10000);
                niVar.a(this.b.getLatitudeE6(), this.b.getLongitudeE6());
                niVar.f((float) log);
                niVar.a(h1.this.B);
                niVar.a(c.this.b);
                niVar.a(c.this.f16646c);
                h1.this.f16636e.getMap().a(niVar);
            }
        }

        public c(boolean z, TencentMap.CancelableCallback cancelableCallback, long j2) {
            h1.this = r1;
            this.a = z;
            this.b = cancelableCallback;
            this.f16646c = j2;
        }

        @Override // com.tencent.mapsdk.internal.s4.a
        public void a(float f2, GeoPoint geoPoint, double d) {
            if (h1.this.f16636e == null) {
                return;
            }
            ba.b(new a(f2, geoPoint));
        }
    }

    /* loaded from: classes9.dex */
    public class d implements s4.a {
        public d() {
            h1.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.s4.a
        public void a(float f2, GeoPoint geoPoint, double d) {
            LatLng d2 = ea.d(geoPoint);
            double d3 = 20;
            Double.isNaN(d3);
            double log = d3 - (Math.log(1.0f / f2) / Math.log(2.0d));
            if (log < 0.0d) {
                log = 0.0d;
            }
            h1 h1Var = h1.this;
            h1Var.s = new CameraPosition(d2, (float) log, h1Var.f16636e.getMap().Z(), h1.this.f16636e.getMap().T());
            synchronized (h1.this.t) {
                h1.this.t.notifyAll();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class e implements s4.a {
        public final /* synthetic */ TencentMap.AsyncOperateCallback a;

        public e(TencentMap.AsyncOperateCallback asyncOperateCallback) {
            h1.this = r1;
            this.a = asyncOperateCallback;
        }

        @Override // com.tencent.mapsdk.internal.s4.a
        public void a(float f2, GeoPoint geoPoint, double d) {
            LatLng d2 = ea.d(geoPoint);
            double d3 = 20;
            Double.isNaN(d3);
            double log = d3 - (Math.log(1.0f / f2) / Math.log(2.0d));
            if (log < 0.0d) {
                log = 0.0d;
            }
            h1 h1Var = h1.this;
            h1Var.s = new CameraPosition(d2, (float) log, h1Var.f16636e.getMap().Z(), h1.this.f16636e.getMap().T());
            TencentMap.AsyncOperateCallback asyncOperateCallback = this.a;
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(h1.this.s);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f implements k5 {

        /* loaded from: classes9.dex */
        public class a implements Runnable {
            public a() {
                f.this = r1;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (h1.this.f16636e == null) {
                    return;
                }
                h1.this.f16636e.c(h1.this.f16636e.getMap().M().x());
            }
        }

        public f() {
            h1.this = r1;
        }

        @Override // com.tencent.mapsdk.internal.k5
        public void b() {
            ba.b(new a());
        }
    }

    /* loaded from: classes9.dex */
    public interface g {
        void a();
    }

    /* loaded from: classes9.dex */
    public class h implements g {
        private boolean a;

        public h(boolean z) {
            h1.this = r1;
            this.a = false;
            this.a = z;
        }

        @Override // com.tencent.mapsdk.internal.h1.g
        public void a() {
            s4 s4Var;
            LatLng latLng;
            LatLng latLng2;
            int i2;
            int i3;
            int i4;
            int i5;
            if (h1.this.f16636e.Z0 == 0 || h1.this.f16636e.a1 == 0) {
                return;
            }
            h1.this.f16636e.a((g) null);
            if (h1.this.f16636e.S0 == null || h1.this.f16636e.T0 == null) {
                return;
            }
            LatLng latLng3 = new LatLng(0.0d, 0.0d);
            if (h1.this.f16636e.V0 == 0 && h1.this.f16636e.W0 == 0 && h1.this.f16636e.X0 == 0 && h1.this.f16636e.Y0 == 0) {
                s4Var = h1.this.x;
                latLng = h1.this.f16636e.S0;
                latLng2 = h1.this.f16636e.T0;
                i2 = 0;
                i3 = 0;
                i4 = 0;
                i5 = 0;
            } else {
                s4Var = h1.this.x;
                latLng = h1.this.f16636e.S0;
                latLng2 = h1.this.f16636e.T0;
                i2 = h1.this.f16636e.V0;
                i3 = h1.this.f16636e.W0;
                i4 = h1.this.f16636e.X0;
                i5 = h1.this.f16636e.Y0;
            }
            float a = s4Var.a(latLng, latLng2, i2, i3, i4, i5, latLng3);
            TencentMap.CancelableCallback cancelableCallback = h1.this.f16636e.h0;
            if (this.a) {
                ni niVar = new ni(10000);
                niVar.c(0.0f);
                niVar.d(0.0f);
                niVar.a((int) (latLng3.latitude * 1000000.0d), (int) (latLng3.longitude * 1000000.0d));
                niVar.f(a);
                niVar.a(h1.this.B);
                niVar.a(cancelableCallback);
                h1.this.f16636e.getMap().a(niVar);
            } else {
                h1.this.f16636e.getMap().b(0.0f);
                h1.this.f16636e.getMap().d(0.0f);
                h1.this.f16636e.getMap().e((int) (latLng3.latitude * 1000000.0d), (int) (latLng3.longitude * 1000000.0d));
                h1.this.b(a, false, 0L, null);
            }
            h1.this.f16636e.S0 = null;
            h1.this.f16636e.T0 = null;
            h1.this.f16636e.V0 = 0;
            h1.this.f16636e.W0 = 0;
            h1.this.f16636e.X0 = 0;
            h1.this.f16636e.Y0 = 0;
            h1.this.f16636e.h0 = null;
        }
    }

    public h1(e1 e1Var, v1 v1Var, TencentMapOptions tencentMapOptions) {
        this.d = null;
        this.f16636e = null;
        this.f16637f = null;
        this.f16638g = null;
        this.f16639h = null;
        this.f16641j = null;
        this.f16642k = null;
        this.w = 1.0f;
        Context applicationContext = e1Var.getContext().getApplicationContext();
        this.f16637f = applicationContext;
        this.w = f7.d(applicationContext);
        this.d = v1Var;
        this.f16636e = (xi) e1Var.j();
        a7.a aVar = a7.f16233e;
        if (aVar == null || aVar.b() == 0) {
            a7.f16233e = new a7.a(this.f16637f, Math.max(10, tencentMapOptions.getMaxIconMemoryCacheSize()) * 1048576);
        } else {
            a7.f16233e.c();
        }
        this.f16636e.a(this);
        this.x = this.f16636e.getMap().getProjection();
        this.f16636e.getMap().a(this.A);
        this.f16636e.getMap().a(this.C);
        if (tencentMapOptions != null && !e7.b(tencentMapOptions.getSubKey())) {
            this.f16638g = tencentMapOptions.getSubKey();
        }
        og d0 = this.f16636e.A().d0();
        this.f16641j = d0;
        this.f16643l = new sf(this.f16637f, d0, this.f16636e.h0());
        this.f16642k = new xg(this.f16637f, this.f16638g);
        if (this.f16639h == null) {
            this.f16639h = new l1(e1Var, this.f16636e.F(), this.d);
        }
    }

    private int a(LatLngBounds latLngBounds, int i2, int i3, int i4, int i5, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) {
            return -1;
        }
        if (this.f16636e.Z0 == 0 || this.f16636e.a1 == 0) {
            this.f16636e.S0 = latLngBounds.southwest;
            this.f16636e.T0 = latLngBounds.northeast;
            this.f16636e.V0 = i2;
            this.f16636e.W0 = i3;
            this.f16636e.X0 = i4;
            this.f16636e.Y0 = i5;
            this.f16636e.h0 = cancelableCallback;
            this.f16636e.a(z ? this.z : this.y);
            return (((i2 + i3) + this.o) + this.q > f7.j(this.f16637f) || ((i4 + i5) + this.p) + this.r > f7.i(this.f16637f)) ? -1 : 0;
        }
        this.f16636e.a((g) null);
        LatLng latLng = new LatLng();
        float a2 = this.x.a(latLngBounds.southwest, latLngBounds.northeast, i2, i3, i4, i5, latLng);
        if (a2 < 0.0f) {
            return (int) a2;
        }
        if (z) {
            ni niVar = new ni(10000);
            niVar.c(0.0f);
            niVar.d(0.0f);
            niVar.a((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
            niVar.f(a2);
            niVar.a(this.B);
            niVar.a(cancelableCallback);
            niVar.a(j2);
            this.f16636e.getMap().a(niVar);
        } else {
            this.f16636e.getMap().e((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
            b(a2, false, j2, null);
            this.f16636e.getMap().b(0.0f);
            this.f16636e.getMap().d(0.0f);
        }
        return 0;
    }

    private int a(LatLngBounds latLngBounds, int i2, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (latLngBounds == null || latLngBounds.northeast == null || latLngBounds.southwest == null) {
            return -1;
        }
        if (this.f16636e.Z0 == 0 || this.f16636e.a1 == 0) {
            this.f16636e.S0 = latLngBounds.southwest;
            this.f16636e.T0 = latLngBounds.northeast;
            this.f16636e.U0 = i2;
            this.f16636e.h0 = cancelableCallback;
            this.f16636e.a(z ? this.z : this.y);
            int i3 = i2 * 2;
            return ((this.o + i3) + this.q > f7.j(this.f16637f) || (i3 + this.p) + this.r > f7.i(this.f16637f)) ? -1 : 0;
        }
        this.f16636e.a((g) null);
        LatLng latLng = new LatLng(0.0d, 0.0d);
        float a2 = this.x.a(latLngBounds.southwest, latLngBounds.northeast, i2, i2, i2, i2, latLng);
        if (a2 < 0.0f) {
            return (int) a2;
        }
        if (z) {
            ni niVar = new ni(10000);
            niVar.a((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
            niVar.f(a2);
            niVar.a(this.B);
            niVar.a(cancelableCallback);
            niVar.a(j2);
            this.f16636e.getMap().a(niVar);
        } else {
            this.f16636e.getMap().e((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
            b(a2, false, j2, null);
        }
        return 0;
    }

    private int a(List<t4> list, int i2, int i3, int i4, int i5, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        s4 projection = this.f16636e.getMap().getProjection();
        if (projection == null) {
            return Integer.MIN_VALUE;
        }
        if (list == null || list.isEmpty()) {
            return -1;
        }
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : list) {
            if (t4Var != null && t4Var.getGroupBounds() != null) {
                arrayList.addAll(t4Var.getGroupBounds());
            }
        }
        if (this.f16636e.Z0 == 0 || this.f16636e.a1 == 0) {
            return (i2 + i3 > f7.j(this.f16637f) || i4 + i5 > f7.i(this.f16637f)) ? -1 : 0;
        } else if (i2 + i3 > this.f16636e.Z0 || i4 + i5 > this.f16636e.a1) {
            return -1;
        } else {
            projection.a(arrayList, null, new Rect(i2, i4, i3, i5), new c(z, cancelableCallback, j2));
            return 0;
        }
    }

    private void a(double d2, double d3, float f2, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null) {
            return;
        }
        int i2 = (int) (d2 * 1000000.0d);
        int i3 = (int) (d3 * 1000000.0d);
        int Q = this.f16636e.getMap().Q();
        float R = this.f16636e.getMap().R();
        if (f2 < R) {
            f2 = R;
        }
        float f3 = Q;
        if (f2 > f3) {
            f2 = f3;
        }
        double pow = 1.0d / Math.pow(2.0d, f3 - f2);
        if (!z) {
            this.f16636e.getMap().e(i2, i3);
            this.f16636e.getMap().b((float) pow);
            return;
        }
        ni niVar = new ni(10000);
        niVar.a(i2, i3);
        niVar.f(f2);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private void a(double d2, double d3, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null) {
            return;
        }
        int i2 = (int) (d2 * 1000000.0d);
        int i3 = (int) (d3 * 1000000.0d);
        if (!z) {
            this.f16636e.getMap().e(i2, i3);
            return;
        }
        ni niVar = new ni(10000);
        niVar.a(i2, i3);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private void a(float f2, float f3, float f4, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null || f2 == 0.0f) {
            return;
        }
        if (!z) {
            double d2 = f3;
            double d3 = f4;
            this.f16636e.getMap().a((float) (1.0d / Math.pow((f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1)) < 0 ? 2.0d : 0.5d, Math.abs(f2))), d2, d3, d2, d3, (Runnable) null);
            return;
        }
        ni niVar = new ni(10000);
        niVar.b((int) f3, (int) f4);
        niVar.e(f2);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private void a(float f2, float f3, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (!z) {
            this.f16636e.getMap().b(f2);
            if (f3 >= 0.0f) {
                this.f16636e.getMap().d(f3);
                return;
            }
            return;
        }
        ni niVar = new ni(10000);
        niVar.c(f2);
        if (f3 >= 0.0f) {
            niVar.d(f3);
        }
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private void a(float f2, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        double d2;
        double d3;
        if (this.f16636e == null || f2 == 0.0f) {
            return;
        }
        if (z) {
            ni niVar = new ni(10000);
            niVar.e(f2);
            niVar.a(this.B);
            niVar.a(cancelableCallback);
            niVar.a(j2);
            this.f16636e.getMap().a(niVar);
            return;
        }
        int i2 = (f2 > 0.0f ? 1 : (f2 == 0.0f ? 0 : -1));
        float abs = Math.abs(f2);
        if (i2 < 0) {
            d2 = abs;
            d3 = 2.0d;
        } else {
            d2 = abs;
            d3 = 0.5d;
        }
        this.f16636e.getMap().b(this.f16636e.getMap().U() * ((float) (1.0d / Math.pow(d3, d2))));
    }

    private void a(CameraPosition cameraPosition, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null || cameraPosition == null) {
            return;
        }
        GeoPoint from = GeoPoint.from(cameraPosition.target);
        float f2 = cameraPosition.zoom;
        int Q = this.f16636e.getMap().Q();
        float R = this.f16636e.getMap().R();
        if (f2 < R) {
            f2 = R;
        }
        float f3 = Q;
        if (f2 > f3) {
            f2 = f3;
        }
        if (!z) {
            double pow = 1.0d / Math.pow(2.0d, f3 - f2);
            if (from != null) {
                this.f16636e.getMap().e(from.getLatitudeE6(), from.getLongitudeE6());
            }
            this.f16636e.getMap().b((float) pow);
            this.f16636e.getMap().b(cameraPosition.bearing);
            if (cameraPosition.tilt >= 0.0f) {
                this.f16636e.getMap().d(cameraPosition.tilt);
                return;
            }
            return;
        }
        ni niVar = new ni(10000);
        if (from != null) {
            niVar.a(from.getLatitudeE6(), from.getLongitudeE6());
        }
        niVar.f(f2);
        niVar.c(cameraPosition.bearing);
        float f4 = cameraPosition.tilt;
        if (f4 >= 0.0f) {
            niVar.d(f4);
        }
        niVar.a(this.B);
        niVar.a(j2);
        niVar.a(cancelableCallback);
        this.f16636e.getMap().a(niVar);
    }

    private void a(boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (!z) {
            this.f16636e.getMap().c(this.f16636e.getMap().U() * 2.0f);
            return;
        }
        ni niVar = new ni(10000);
        niVar.e(1.0f);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private List<t4> b(List<IOverlay> list) {
        ArrayList arrayList = new ArrayList();
        for (IOverlay iOverlay : list) {
            if (iOverlay instanceof t4) {
                arrayList.add((t4) iOverlay);
            }
        }
        return arrayList;
    }

    private void b(float f2, float f3, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null) {
            return;
        }
        if (f2 == 0.0f && f3 == 0.0f) {
            return;
        }
        if (!z) {
            this.f16636e.getMap().d((int) f2, (int) f3);
            return;
        }
        ni niVar = new ni(10000);
        niVar.c((int) f2, (int) f3);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    public void b(float f2, boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (this.f16636e == null) {
            return;
        }
        if (!z) {
            this.f16636e.getMap().b((float) (1.0d / Math.pow(2.0d, this.f16636e.getMap().Q() - Math.min(f2, Math.min(this.f16636e.getMap().P(), this.f16636e.i().l() ? 22 : 20)))));
            return;
        }
        ni niVar = new ni(10000);
        niVar.f(f2);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    private void b(boolean z, long j2, TencentMap.CancelableCallback cancelableCallback) {
        if (!z) {
            this.f16636e.getMap().c(this.f16636e.getMap().U() / 2.0f);
            return;
        }
        ni niVar = new ni(10000);
        niVar.e(-1.0f);
        niVar.a(this.B);
        niVar.a(cancelableCallback);
        niVar.a(j2);
        this.f16636e.getMap().a(niVar);
    }

    public float a(double d2, LatLng latLng) {
        if (latLng == null) {
            return 0.0f;
        }
        return this.f16636e.getMap().a(d2, new GeoPoint((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d)));
    }

    public float a(float f2, int i2, int i3, int i4, int i5, LatLng latLng, LatLng latLng2) {
        int i6 = this.f16636e.Z0;
        int i7 = this.f16636e.a1;
        if (i6 == 0 || i7 == 0 || latLng == null || latLng2 == null) {
            return 0.0f;
        }
        int Q = this.f16636e.getMap().Q();
        o5 a2 = this.x.a(this.f16637f, latLng);
        o5 a3 = this.x.a(this.f16637f, latLng2);
        double d2 = a3.a - a2.a;
        if (d2 < 0.0d) {
            d2 = Math.abs(d2);
        }
        double d3 = a3.b - a2.b;
        if (d3 < 0.0d) {
            d3 = Math.abs(d3);
        }
        double d4 = d2 * 1.0d;
        double d5 = d3 * 1.0d;
        int i8 = (i6 - i2) - i3;
        double d6 = (i7 - i4) - i5;
        double d7 = f2 == 90.0f ? 89.0f : f2;
        Double.isNaN(d7);
        double cos = Math.cos((d7 * 3.141592653589793d) / 180.0d);
        Double.isNaN(d6);
        int i9 = (int) (d6 / cos);
        if (i8 <= 0) {
            i8 = 1;
        }
        if (i9 <= 0) {
            i9 = 1;
        }
        double d8 = i8;
        Double.isNaN(d8);
        double log = Math.log(d4 / d8) / Math.log(2.0d);
        double d9 = i9;
        Double.isNaN(d9);
        double log2 = Math.log(d5 / d9) / Math.log(2.0d);
        if (log < 0.0d) {
            log = 0.0d;
        }
        double max = Math.max(log, log2 >= 0.0d ? log2 : 0.0d);
        double d10 = Q;
        Double.isNaN(d10);
        return (float) (d10 - max);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float a(int i2, int i3, int i4, int i5, LatLng latLng, LatLng latLng2, LatLng latLng3) {
        return this.x.a(latLng, latLng2, i2, i3, i4, i5, latLng3);
    }

    public int a(int i2, int i3, int i4, int i5, boolean z) {
        int a2 = this.f16636e.getMap().a(i2, i3, i4, i5, z);
        if (a2 == 0) {
            this.o = i2;
            this.p = i3;
            this.q = i4;
            this.r = i5;
        }
        return a2;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int a(String str) {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return -1;
        }
        return this.f16636e.i().a(str);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int a(byte[] bArr, String str) {
        return -1;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public List<Rect> a(List<String> list) {
        Rect screenBound;
        fg N;
        ArrayList arrayList = null;
        if (list != null && !list.isEmpty()) {
            if (this.f16636e == null) {
                return null;
            }
            arrayList = new ArrayList(list.size());
            s4 projection = this.f16636e.getMap().getProjection();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                ye a2 = this.f16636e.a(it.next());
                if (a2 != null) {
                    if (a2 instanceof af) {
                        screenBound = ((af) a2).getScreenBound(projection);
                    } else if ((a2 instanceof cf) && (N = ((cf) a2).N()) != null) {
                        screenBound = N.getScreenBound(projection);
                    }
                    arrayList.add(screenBound);
                }
            }
        }
        return arrayList;
    }

    public void a(float f2, float f3) {
        this.f16636e.getMap().h(f2, f3);
    }

    public void a(float f2, float f3, float f4) {
        this.f16636e.setMapCenterAndScale(f2, f3, f4);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(float f2, float f3, boolean z) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.getMap().a(f2, f3, 0, z);
    }

    @Override // com.tencent.mapsdk.internal.h5
    public void a(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
        }
    }

    public void a(int i2, int i3, int i4, int i5) {
        this.f16636e.getMap().a(i2, i3, i4, i5);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(Handler handler, Bitmap.Config config, int i2) {
        this.f16636e.a(handler, config, i2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(c5 c5Var) {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.a(c5Var);
    }

    public void a(l1.d dVar) {
        this.f16639h.a(dVar);
    }

    public void a(ph.i iVar, TencentMapOptions tencentMapOptions) {
        this.f16639h.a(iVar, tencentMapOptions);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.b(onCameraChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(TencentMap.OnDismissCallback onDismissCallback) {
        this.f16636e.a(onDismissCallback);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener) {
        this.f16636e.a(onIndoorStateChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(TencentMap.OnMapPoiClickListener onMapPoiClickListener) {
        this.f16636e.a(onMapPoiClickListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(Language language) {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.a(language);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(LatLng latLng, float f2, float f3, boolean z) {
        animateToNaviPosition(latLng, f2, f3, 0.0f, z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(LatLng latLng, LatLng latLng2, float f2) {
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(LatLngBounds latLngBounds, int i2) {
        if (this.f16636e == null || this.f16636e.getMap() == null) {
            return;
        }
        this.f16636e.A().a(latLngBounds, i2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(List<MapRouteSection> list, List<LatLng> list2) {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.a(list, GeoPoint.from(list2));
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void a(boolean z) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.a(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public CustomLayer addCustomLayer(CustomLayerOptions customLayerOptions) {
        sf sfVar = this.f16643l;
        if (sfVar == null || this.f16641j == null) {
            return null;
        }
        return sfVar.a(customLayerOptions);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void addOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.f16636e.f16478n.add(onMapLoadedCallback);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void addTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.b(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        og ogVar = this.f16641j;
        if (ogVar == null) {
            return null;
        }
        return ogVar.a(tileOverlayOptions);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int animateCamera(CameraUpdate cameraUpdate, long j2, TencentMap.CancelableCallback cancelableCallback) {
        CamerParameter params;
        if (cameraUpdate == null || (params = cameraUpdate.getParams()) == null) {
            return -1;
        }
        long j3 = j2 < 0 ? 0L : j2;
        switch (params.iCamerType) {
            case 0:
                a(true, j3, cancelableCallback);
                return 0;
            case 1:
                b(true, j3, cancelableCallback);
                return 0;
            case 2:
                b(params.scrollBy_xPixel, params.scrollBy_yPixel, true, j3, cancelableCallback);
                return 0;
            case 3:
                b(params.zoomTo_zoom, true, j3, cancelableCallback);
                return 0;
            case 4:
                a(params.zoomBy_amount, true, j3, cancelableCallback);
                return 0;
            case 5:
                float f2 = params.zoomBy_Point_amount;
                Point point2 = params.zoomBy_Point_focus;
                a(f2, point2.x, point2.y, true, j3, cancelableCallback);
                return 0;
            case 6:
                a(params.newCameraPosition_cameraPosition, true, j3, cancelableCallback);
                return 0;
            case 7:
                LatLng latLng = params.newLatLng_latLng;
                a(latLng.latitude, latLng.longitude, true, j3, cancelableCallback);
                return 0;
            case 8:
                LatLng latLng2 = params.newLatLngZoom_latLng;
                a(latLng2.latitude, latLng2.longitude, params.newLatLngZoom_zoom, true, j3, cancelableCallback);
                return 0;
            case 9:
                return a(params.newLatLngBounds_bounds, params.newLatLngBounds_padding, true, j3, cancelableCallback);
            case 10:
                LatLngBounds latLngBounds = params.newLatLngBounds_dimension_bounds;
                int i2 = params.newLatLngBounds_dimension_padding;
                return a(latLngBounds, i2, i2, i2, i2, true, j3, cancelableCallback);
            case 11:
                return a(params.newLatLngBounds_dimension_bounds, params.newLatLngBoundsRects_padLeft, params.newLatLngBoundsRects_padRight, params.newLatLngBoundsRects_padTop, params.newLatLngBoundsRects_padBom, true, j3, cancelableCallback);
            case 12:
                a(params.rotateto_rotate, params.rotateto_skew, true, j3, cancelableCallback);
                return 0;
            case 13:
                return a(b(params.elements), params.newLatLngBoundsRects_padLeft, params.newLatLngBoundsRects_padRight, params.newLatLngBoundsRects_padTop, params.newLatLngBoundsRects_padBom, true, j3, cancelableCallback);
            default:
                return 0;
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void animateToNaviPosition(LatLng latLng, float f2, float f3, float f4, boolean z) {
        int C;
        int n2;
        ni niVar = new ni(10000);
        niVar.a((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
        niVar.f(f4);
        niVar.c(f2);
        niVar.d(f3);
        niVar.a(this.B);
        niVar.a(true);
        niVar.a(1000L);
        xi xiVar = this.f16636e;
        if (z) {
            C = xiVar.H();
            n2 = this.f16636e.q();
        } else {
            C = xiVar.C();
            n2 = this.f16636e.n();
        }
        niVar.b(C, n2);
        this.f16636e.getMap().o();
        this.f16636e.getMap().a(niVar);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void animateToNaviPosition2(LatLng latLng, float f2, float f3, float f4, boolean z) {
        ni niVar = new ni(10000);
        niVar.a((int) (latLng.latitude * 1000000.0d), (int) (latLng.longitude * 1000000.0d));
        niVar.f(f4);
        niVar.c(f2);
        niVar.d(f3);
        niVar.a(this.B);
        niVar.a(true);
        niVar.a(1000L);
        this.f16636e.getMap().o();
        this.f16636e.getMap().a(niVar);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void b(c5 c5Var) {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.b(c5Var);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void b(boolean z) {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return;
        }
        this.f16636e.i().a(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public boolean b() {
        if (this.f16636e == null) {
            return false;
        }
        return this.f16636e.b();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public IndoorBuilding c() {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return null;
        }
        return this.f16636e.i().f();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void c(boolean z) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.i(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float calNaviLevel(LatLngBounds latLngBounds, float f2, int i2, boolean z) {
        if (latLngBounds == null) {
            return 0.0f;
        }
        xi xiVar = this.f16636e;
        int q = z ? xiVar.q() : xiVar.n();
        float f3 = !z ? 0.0f : f2;
        if (q < 0) {
            q = this.f16636e.Z0 / 2;
        }
        return a(f3, 0, 0, i2, this.f16636e.a1 - q, latLngBounds.southwest, latLngBounds.northeast);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float calNaviLevel2(LatLng latLng, LatLng latLng2, float f2, float f3, int i2, boolean z) {
        if (latLng == null || latLng2 == null) {
            return 0.0f;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng2);
        return a(!z ? 0.0f : f2, 0, 0, i2, 0, builder.build().southwest, builder.build().northeast);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float calNaviLevel3(LatLng latLng, LatLng latLng2, float f2, int i2, int i3, int i4, int i5, boolean z) {
        if (latLng == null || latLng2 == null) {
            return 0.0f;
        }
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(latLng);
        builder.include(latLng2);
        return a(!z ? 0.0f : f2, i2, i3, i4, i5, builder.build().southwest, builder.build().northeast);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public CameraPosition calculateZoomToSpanLevel(List<t4> list, List<LatLng> list2, int i2, int i3, int i4, int i5) {
        if (this.f16636e.Z0 == 0 || this.f16636e.a1 == 0) {
            int j2 = f7.j(this.f16637f);
            int i6 = f7.i(this.f16637f);
            if (i2 + i3 > j2 || i4 + i5 > i6) {
                return null;
            }
        } else if (i2 + i3 > this.f16636e.Z0 || i4 + i5 > this.f16636e.a1) {
            return null;
        }
        s4 projection = this.f16636e.getMap().getProjection();
        if (projection == null) {
            return null;
        }
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (t4 t4Var : list) {
                if (t4Var != null && t4Var.getGroupBounds() != null) {
                    arrayList.addAll(t4Var.getGroupBounds());
                }
            }
            Rect rect = new Rect(i2, i4, i3, i5);
            this.s = null;
            projection.a(arrayList, GeoPoint.from(list2), rect, new d());
            synchronized (this.t) {
                try {
                    this.t.wait(1000L);
                } catch (InterruptedException e2) {
                    ma.b(Log.getStackTraceString(e2));
                    Thread.currentThread().interrupt();
                }
            }
            return this.s;
        } else if (list2 == null || list2.isEmpty()) {
            return null;
        } else {
            if (list2.size() == 1) {
                return new CameraPosition(list2.get(0), this.f16636e.getMap().M().x(), this.f16636e.getMap().Z(), this.f16636e.getMap().T());
            }
            double d2 = 0.0d;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            for (LatLng latLng : list2) {
                if (latLng != null) {
                    if (d2 == 0.0d) {
                        d2 = latLng.latitude;
                    }
                    if (d3 == 0.0d) {
                        d3 = latLng.longitude;
                    }
                    double d6 = d2;
                    double d7 = d4;
                    if (d7 == 0.0d) {
                        d7 = latLng.latitude;
                    }
                    double d8 = d5;
                    if (d8 == 0.0d) {
                        d8 = latLng.longitude;
                    }
                    d5 = d8;
                    double d9 = latLng.latitude;
                    double d10 = d6;
                    if (d9 < d10) {
                        d10 = d9;
                    }
                    if (d9 > d7) {
                        d7 = d9;
                    }
                    double d11 = latLng.longitude;
                    if (d11 < d3) {
                        d3 = d11;
                    }
                    if (d11 > d5) {
                        d5 = d11;
                    }
                    d4 = d7;
                    d2 = d10;
                }
            }
            LatLng latLng2 = new LatLng(d2, d3);
            LatLng latLng3 = new LatLng(d4, d5);
            LatLng latLng4 = new LatLng(0.0d, 0.0d);
            float a2 = a(i2, i3, i4, i5, latLng2, latLng3, latLng4);
            if (a2 < 0.0f) {
                return null;
            }
            return new CameraPosition(latLng4, a2, this.f16636e.getMap().Z(), this.f16636e.getMap().T());
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public CameraPosition calculateZoomToSpanLevelAsync(List<t4> list, List<LatLng> list2, int i2, int i3, int i4, int i5, TencentMap.AsyncOperateCallback<CameraPosition> asyncOperateCallback) {
        if (this.f16636e.Z0 == 0 || this.f16636e.a1 == 0) {
            f7.j(this.f16637f);
            f7.i(this.f16637f);
        } else if (i2 + i3 > this.f16636e.Z0 || i4 + i5 > this.f16636e.a1) {
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(null);
            }
            return null;
        }
        s4 projection = this.f16636e.getMap().getProjection();
        if (projection == null) {
            return null;
        }
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (t4 t4Var : list) {
                if (t4Var != null && t4Var.getGroupBounds() != null) {
                    arrayList.addAll(t4Var.getGroupBounds());
                }
            }
            Rect rect = new Rect(i2, i4, i3, i5);
            this.s = null;
            projection.a(arrayList, GeoPoint.from(list2), rect, new e(asyncOperateCallback));
            return null;
        } else if (list2 == null || list2.isEmpty()) {
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(null);
            }
            return null;
        } else if (list2.size() == 1) {
            CameraPosition cameraPosition = new CameraPosition(list2.get(0), this.f16636e.getMap().U(), this.f16636e.getMap().Z(), this.f16636e.getMap().T());
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(cameraPosition);
            }
            return cameraPosition;
        } else {
            Iterator<LatLng> it = list2.iterator();
            double d2 = 0.0d;
            double d3 = 0.0d;
            double d4 = 0.0d;
            double d5 = 0.0d;
            while (it.hasNext()) {
                LatLng next = it.next();
                if (next != null) {
                    if (d2 == 0.0d) {
                        d2 = next.latitude;
                    }
                    if (d3 == 0.0d) {
                        d3 = next.longitude;
                    }
                    double d6 = d2;
                    double d7 = d4;
                    if (d7 == 0.0d) {
                        d7 = next.latitude;
                    }
                    Iterator<LatLng> it2 = it;
                    double d8 = d5;
                    if (d8 == 0.0d) {
                        d8 = next.longitude;
                    }
                    d5 = d8;
                    double d9 = next.latitude;
                    double d10 = d6;
                    if (d9 < d10) {
                        d10 = d9;
                    }
                    if (d9 > d7) {
                        d7 = d9;
                    }
                    double d11 = next.longitude;
                    if (d11 < d3) {
                        d3 = d11;
                    }
                    if (d11 > d5) {
                        d5 = d11;
                    }
                    d4 = d7;
                    it = it2;
                    d2 = d10;
                }
            }
            LatLng latLng = new LatLng(d2, d3);
            LatLng latLng2 = new LatLng(d4, d5);
            LatLng latLng3 = new LatLng(0.0d, 0.0d);
            float a2 = a(i2, i3, i4, i5, latLng, latLng2, latLng3);
            if (a2 < 0.0f) {
                if (asyncOperateCallback != null) {
                    asyncOperateCallback.onOperateFinished(null);
                }
                return null;
            }
            CameraPosition cameraPosition2 = new CameraPosition(latLng3, a2, this.f16636e.getMap().Z(), this.f16636e.getMap().T());
            if (asyncOperateCallback != null) {
                asyncOperateCallback.onOperateFinished(cameraPosition2);
            }
            return cameraPosition2;
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void clearRouteNameSegments() {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.clearRouteNameSegments();
    }

    public void d(boolean z) {
        this.d.setZOrderMediaOverlay(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String e() {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return null;
        }
        return this.f16636e.i().g();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void enableMultipleInfowindow(boolean z) {
        if (this.f16636e != null) {
            this.f16636e.d1 = z;
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String f() {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return null;
        }
        return this.f16636e.i().e();
    }

    @Override // com.tencent.mapsdk.internal.k0
    public LatLng fromScreenLocation(Point point2) {
        xi xiVar;
        if (point2 == null || (xiVar = this.f16636e) == null || xiVar.getMap() == null || xiVar.getMap().getProjection() == null) {
            return null;
        }
        return ea.d(xiVar.getMap().getProjection().a(new o5(point2.x, point2.y)));
    }

    @Override // com.tencent.mapsdk.internal.j0
    public ArrayList<MapPoi> g() {
        if (this.f16636e == null) {
            return null;
        }
        return this.f16636e.A().Y();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public CameraPosition getCameraPosition() {
        LatLng d2 = ea.d(this.f16636e.getMap().s());
        float T = this.f16636e.getMap().T();
        if (T < 0.0f) {
            T = (T % 360.0f) + 360.0f;
        }
        return CameraPosition.builder().zoom(o()).target(d2).bearing(T).tilt(this.f16636e.getMap().Z()).build();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String getCityName(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return this.f16636e.getMap().a(GeoPoint.from(latLng));
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String getDebugError() {
        if (this.f16637f != null && this.f16636e != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("cfgVer", (!e7.b(this.f16638g) ? jc.a(this.f16637f, this.f16638g) : kc.a(this.f16637f)).a());
                jSONObject.put(CommonCode.MapKey.HAS_RESOLUTION, "{" + f7.j(this.f16637f) + DYConstants.DY_REGEX_COMMA + f7.i(this.f16637f) + "}");
                jSONObject.put("density", (double) this.w);
                jSONObject.put("renderStatus", this.f16636e.getMap().I());
                jSONObject.put("renderError", this.f16636e.getMap().H());
                jSONObject.put("sdkver", "4.5.10.6");
                return jSONObject.toString();
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int getIndoorFloorId() {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return -1;
        }
        return this.f16636e.i().h();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public Language getLanguage() {
        VectorMap map;
        if (this.f16636e != null && (map = this.f16636e.getMap()) != null) {
            return map.K();
        }
        return Language.zh;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int getMapStyle() {
        int m2 = this.f16636e.getMapContext().h().m();
        xg xgVar = this.f16642k;
        if (xgVar != null) {
            return xgVar.b(m2);
        }
        return 0;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int getMapType() {
        return this.v;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float getMaxZoomLevel() {
        return this.f16636e.getMap().P();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float getMinZoomLevel() {
        return this.f16636e.getMap().R();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String getVersion() {
        return b7.F();
    }

    @Override // com.tencent.mapsdk.internal.m0
    public v1 getView() {
        return this.d;
    }

    @Override // com.tencent.mapsdk.internal.k0
    public VisibleRegion getVisibleRegion() {
        Point point2 = new Point(0, this.f16636e.a1);
        Point point3 = new Point(this.f16636e.Z0, this.f16636e.a1);
        Point point4 = new Point(0, 0);
        Point point5 = new Point(this.f16636e.Z0, 0);
        LatLng fromScreenLocation = fromScreenLocation(point2);
        LatLng fromScreenLocation2 = fromScreenLocation(point3);
        LatLng fromScreenLocation3 = fromScreenLocation(point4);
        LatLng fromScreenLocation4 = fromScreenLocation(point5);
        return new VisibleRegion(fromScreenLocation, fromScreenLocation2, fromScreenLocation3, fromScreenLocation4, LatLngBounds.builder().include(fromScreenLocation).include(fromScreenLocation2).include(fromScreenLocation3).include(fromScreenLocation4).build());
    }

    @Override // com.tencent.mapsdk.internal.j0
    public float getZoomToSpanLevel(LatLng latLng, LatLng latLng2) {
        return a(0, 0, 0, 0, latLng, latLng2, (LatLng) null);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public String[] h() {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return null;
        }
        return this.f16636e.i().i();
    }

    public void i() {
        if (this.f16636e != null) {
            this.f16636e.getMap().b(this.C);
            this.f16636e.e();
            this.f16636e = null;
        }
        if (this.f16637f != null) {
            this.f16637f = null;
        }
        a7.a aVar = a7.f16233e;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public boolean isHandDrawMapEnable() {
        if (this.f16636e == null) {
            return false;
        }
        return this.f16636e.isHandDrawMapEnable();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public boolean isTrafficEnabled() {
        return this.f16636e.getMap().j0();
    }

    public Context j() {
        return this.f16637f;
    }

    public sf k() {
        return this.f16643l;
    }

    public xi l() {
        return this.f16636e;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void loadKMLFile(String str) {
        qc A;
        if (this.f16636e == null || (A = this.f16636e.A()) == null) {
            return;
        }
        A.c(str);
        A.w0();
    }

    public xg m() {
        return this.f16642k;
    }

    @Override // com.tencent.mapsdk.internal.k0
    public double metersPerPixel(double d2) {
        return this.f16636e.getMap().getProjection().metersPerPixel(d2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public int moveCamera(CameraUpdate cameraUpdate) {
        CamerParameter params;
        if (cameraUpdate == null || (params = cameraUpdate.getParams()) == null) {
            return -1;
        }
        switch (params.iCamerType) {
            case 0:
                a(false, 0L, (TencentMap.CancelableCallback) null);
                break;
            case 1:
                b(false, 0L, null);
                break;
            case 2:
                b(params.scrollBy_xPixel, params.scrollBy_yPixel, false, 0L, null);
                break;
            case 3:
                b(params.zoomTo_zoom, false, 0L, null);
                break;
            case 4:
                a(params.zoomBy_amount, false, 0L, (TencentMap.CancelableCallback) null);
                break;
            case 5:
                float f2 = params.zoomBy_Point_amount;
                Point point2 = params.zoomBy_Point_focus;
                a(f2, point2.x, point2.y, false, 0L, (TencentMap.CancelableCallback) null);
                break;
            case 6:
                a(params.newCameraPosition_cameraPosition, false, 0L, (TencentMap.CancelableCallback) null);
                break;
            case 7:
                LatLng latLng = params.newLatLng_latLng;
                if (latLng != null) {
                    a(latLng.latitude, latLng.longitude, false, 0L, (TencentMap.CancelableCallback) null);
                    break;
                }
                break;
            case 8:
                LatLng latLng2 = params.newLatLngZoom_latLng;
                if (latLng2 != null) {
                    a(latLng2.latitude, latLng2.longitude, params.newLatLngZoom_zoom, false, 0L, (TencentMap.CancelableCallback) null);
                    break;
                }
                break;
            case 9:
                return a(params.newLatLngBounds_bounds, params.newLatLngBounds_padding, false, 0L, (TencentMap.CancelableCallback) null);
            case 10:
                LatLngBounds latLngBounds = params.newLatLngBounds_dimension_bounds;
                int i2 = params.newLatLngBounds_dimension_padding;
                return a(latLngBounds, i2, i2, i2, i2, false, 0L, (TencentMap.CancelableCallback) null);
            case 11:
                return a(params.newLatLngBounds_dimension_bounds, params.newLatLngBoundsRects_padLeft, params.newLatLngBoundsRects_padRight, params.newLatLngBoundsRects_padTop, params.newLatLngBoundsRects_padBom, false, 0L, (TencentMap.CancelableCallback) null);
            case 12:
                a(params.rotateto_rotate, params.rotateto_skew, false, 0L, (TencentMap.CancelableCallback) null);
                break;
            case 13:
                return a(b(params.elements), params.newLatLngBoundsRects_padLeft, params.newLatLngBoundsRects_padRight, params.newLatLngBoundsRects_padTop, params.newLatLngBoundsRects_padBom, false, 0L, (TencentMap.CancelableCallback) null);
        }
        return 0;
    }

    public l1 n() {
        return this.f16639h;
    }

    public float o() {
        return this.f16636e.getMap().M().x();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onDestroy() {
        this.d.onDestroy();
        lf lfVar = this.f16640i;
        if (lfVar != null) {
            lfVar.a();
            this.f16640i = null;
        }
        l1 l1Var = this.f16639h;
        if (l1Var != null) {
            l1Var.c();
            this.f16639h = null;
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onPause() {
        this.d.onPause();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onRestart() {
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onResume() {
        this.d.onResume();
        l1 l1Var = this.f16639h;
        if (l1Var != null) {
            l1Var.e();
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onStart() {
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void onStop() {
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void removeOnMapLoadedCallback(TencentMap.OnMapLoadedCallback onMapLoadedCallback) {
        this.f16636e.f16478n.remove(onMapLoadedCallback);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void removeTencentMapGestureListener(TencentMapGestureListener tencentMapGestureListener) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.a(tencentMapGestureListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setCompassExtraPadding(int i2) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.setCompassExtraPadding(i2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setCompassExtraPadding(int i2, int i3) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.setCompassExtraPadding(i2, i3);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setForeignLanguage(Language language) {
        if (this.f16636e == null || this.f16636e.n0() == null) {
            return;
        }
        this.f16636e.n0().a(language);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setHandDrawMapEnable(boolean z) {
        if (this.f16636e == null) {
            return;
        }
        if (z) {
            this.f16636e.t();
        } else {
            this.f16636e.y();
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setIndoorEnabled(boolean z) {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return;
        }
        this.f16636e.i().b(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setIndoorFloor(int i2) {
        if (this.f16636e == null || this.f16636e.i() == null) {
            return;
        }
        this.f16636e.i().a(i2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setIndoorFloor(String str, String str2) {
        qc A;
        if (this.f16636e == null || this.f16636e.getMap() == null || (A = this.f16636e.A()) == null) {
            return;
        }
        A.a(str, str2);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setMapStyle(int i2) {
        int mapStyle = getMapStyle();
        this.u = i2;
        if (i2 == mapStyle || mapStyle == 1011 || mapStyle == 1008) {
            return;
        }
        xg xgVar = this.f16642k;
        if (xgVar != null) {
            wg a2 = xgVar.a(i2);
            if (a2 != null) {
                i2 = a2.a;
                v6 w = this.f16636e.A().w();
                if (w != null) {
                    w.e().a(a2.b);
                }
            } else if (i2 >= 1000) {
                i2 -= 1000;
            } else if (i2 > 8 && i2 < 989) {
                i2 += 11;
            }
        }
        this.f16636e.A().m(i2);
        b7.b(true);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setMapType(int i2) {
        wg a2;
        if (i2 == 1000 || i2 == 1011 || i2 == 1008) {
            if (i2 == 1008) {
                this.f16636e.A().w().f().b();
            }
            this.v = i2;
            if (i2 == 1000) {
                i2 = this.u;
            }
            xg xgVar = this.f16642k;
            if (xgVar != null && (a2 = xgVar.a(i2)) != null) {
                i2 = a2.a;
            }
            this.f16636e.A().m(i2);
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setMaxZoomLevel(int i2) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.L = i2;
        VectorMap map = this.f16636e.getMap();
        if (map == null) {
            return;
        }
        map.k(i2);
        map.v0();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setMinZoomLevel(int i2) {
        VectorMap map;
        if (this.f16636e == null || (map = this.f16636e.getMap()) == null) {
            return;
        }
        map.l(i2);
        map.v0();
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setNaviFixingProportion(float f2, float f3) {
        if (this.f16636e == null) {
            return;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        this.f16636e.a((int) (this.f16636e.Z0 * f2), (int) (this.f16636e.a1 * f3));
        this.f16636e.w0 = f2;
        this.f16636e.x0 = f3;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setNaviFixingProportion2D(float f2, float f3) {
        if (this.f16636e == null) {
            return;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        } else if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        } else if (f3 > 1.0f) {
            f3 = 1.0f;
        }
        this.f16636e.b((int) (this.f16636e.Z0 * f2), (int) (this.f16636e.a1 * f3));
        this.f16636e.y0 = f2;
        this.f16636e.z0 = f3;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnCameraChangeListener(TencentMap.OnCameraChangeListener onCameraChangeListener) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.c(onCameraChangeListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnCompassClickedListener(TencentMap.OnCompassClickedListener onCompassClickedListener) {
        if (this.f16636e != null) {
            this.f16636e.i0 = onCompassClickedListener;
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnInfoWindowClickListener(TencentMap.OnInfoWindowClickListener onInfoWindowClickListener) {
        this.f16636e.b0 = onInfoWindowClickListener;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnMapClickListener(TencentMap.OnMapClickListener onMapClickListener) {
        this.f16636e.U = onMapClickListener;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnMapLongClickListener(TencentMap.OnMapLongClickListener onMapLongClickListener) {
        this.f16636e.X = onMapLongClickListener;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnMarkerClickListener(TencentMap.OnMarkerClickListener onMarkerClickListener) {
        this.f16636e.a0 = onMarkerClickListener;
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnScaleViewChangedListener(TencentMap.OnScaleViewChangedListener onScaleViewChangedListener) {
        if (this.f16636e == null) {
            return;
        }
        this.f16636e.a(onScaleViewChangedListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnTapMapViewInfoWindowHidden(boolean z) {
        if (this.f16636e != null) {
            this.f16636e.setOnTapMapViewInfoWindowHidden(z);
        }
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setOnTrafficEventClickListener(TencentMap.OnTrafficEventClickListener onTrafficEventClickListener) {
        if (this.f16636e == null || this.f16636e.getMap() == null) {
            return;
        }
        this.f16636e.a(onTrafficEventClickListener);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setPoisEnabled(boolean z) {
        if (this.f16636e == null || this.f16636e.getMap() == null) {
            return;
        }
        this.f16636e.getMap().b(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void setTrafficEnabled(boolean z) {
        this.f16636e.getMap().m(z);
    }

    @Override // com.tencent.mapsdk.internal.j0
    public void stopAnimation() {
        this.f16636e.getMap().o();
    }

    @Override // com.tencent.mapsdk.internal.k0
    public Point toScreenLocation(LatLng latLng) {
        xi xiVar;
        o5 a2;
        if (latLng == null || (xiVar = this.f16636e) == null || xiVar.getMap() == null || xiVar.getMap().getProjection() == null || (a2 = xiVar.getMap().getProjection().a(GeoPoint.from(latLng))) == null) {
            return null;
        }
        Point point2 = new Point();
        point2.x = (int) Math.round(a2.a);
        point2.y = (int) Math.round(a2.b);
        return point2;
    }
}
