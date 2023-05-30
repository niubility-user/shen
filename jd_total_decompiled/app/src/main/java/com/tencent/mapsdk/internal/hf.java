package com.tencent.mapsdk.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.WorkerThread;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.map.lib.models.PolygonInfo;
import com.tencent.map.tools.Callback;
import com.tencent.map.tools.net.NetResponse;
import com.tencent.mapsdk.internal.Cif;
import com.tencent.mapsdk.internal.ba;
import com.tencent.mapsdk.internal.m5;
import com.tencent.mapsdk.internal.w3;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayer;
import com.tencent.tencentmap.mapsdk.maps.model.AoiLayerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.BitmapDescriptor;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.LatLngBounds;
import com.tencent.tencentmap.mapsdk.maps.model.SubPoi;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class hf implements AoiLayer {
    private static final String q = AoiLayer.class.getSimpleName();

    /* renamed from: g  reason: collision with root package name */
    private jf f16657g;

    /* renamed from: h  reason: collision with root package name */
    private String f16658h;

    /* renamed from: i  reason: collision with root package name */
    private int f16659i = 20;

    /* renamed from: j  reason: collision with root package name */
    private int f16660j = -1;

    /* renamed from: k  reason: collision with root package name */
    private boolean f16661k;

    /* renamed from: l  reason: collision with root package name */
    private boolean f16662l;

    /* renamed from: m  reason: collision with root package name */
    private int[] f16663m;

    /* renamed from: n  reason: collision with root package name */
    private int[] f16664n;
    private Cif o;
    private AoiLayer.OnAoiLayerLoadListener p;

    /* loaded from: classes9.dex */
    public class a extends ba.c<Cif> {
        public a() {
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public void callback(Cif cif) {
            ma.c(hf.q, "POI[" + hf.this.f16658h + "]\u7684\u8be6\u60c5\u6570\u636e\uff1a" + cif);
            if (cif != null && !hf.this.f16662l) {
                if (hf.this.f16660j < 0) {
                    hf hfVar = hf.this;
                    hfVar.f16660j = hfVar.a(cif);
                }
                hf.this.b(cif);
            } else if (hf.this.p != null) {
                hf.this.p.onAoiLayerLoaded(false, hf.this);
            }
            hf.this.f16661k = false;
            ma.c(hf.q, "\u7ed3\u675fPOI[" + hf.this.f16658h + "]\u8be6\u60c5\u6570\u636e\u7684\u66f4\u65b0");
        }
    }

    /* loaded from: classes9.dex */
    public class b extends ba.i<Cif> {
        public b() {
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a  reason: merged with bridge method [inline-methods] */
        public Cif call() {
            if (hf.this.f16662l) {
                return null;
            }
            return hf.this.l();
        }
    }

    /* loaded from: classes9.dex */
    public class c extends ba.c<Object> {
        public final /* synthetic */ List a;
        public final /* synthetic */ List b;

        public c(List list, List list2) {
            this.a = list;
            this.b = list2;
        }

        @Override // com.tencent.mapsdk.internal.ba.c, com.tencent.map.tools.Callback
        public void callback(Object obj) {
            if (hf.this.f16662l) {
                return;
            }
            int size = this.a.size();
            int size2 = this.b.size();
            if (size != size2) {
                ma.g(hf.q, "PoiLayer\u7684\u5b50\u70b9\u6e32\u67d3\u7f3a\u5931\uff01\uff01count:" + size2 + "/" + size);
            }
            hf.this.d(this.b);
        }
    }

    /* loaded from: classes9.dex */
    public class d extends ba.i<Object> {
        public final /* synthetic */ List b;

        /* renamed from: c  reason: collision with root package name */
        public final /* synthetic */ List f16666c;

        /* loaded from: classes9.dex */
        public class a implements Callback<Cif.d> {
            public a() {
            }

            @Override // com.tencent.map.tools.Callback
            /* renamed from: a  reason: merged with bridge method [inline-methods] */
            public void callback(Cif.d dVar) {
                if (dVar != null) {
                    d.this.f16666c.add(dVar);
                }
            }
        }

        public d(List list, List list2) {
            this.b = list;
            this.f16666c = list2;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            if (hf.this.f16662l) {
                return null;
            }
            hf.this.a(this.b, new a());
            return null;
        }
    }

    public hf(jf jfVar, String str, AoiLayerOptions aoiLayerOptions, AoiLayer.OnAoiLayerLoadListener onAoiLayerLoadListener) {
        this.f16657g = jfVar;
        this.f16658h = str;
        this.p = onAoiLayerLoadListener;
        a(aoiLayerOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(Cif cif) {
        qc b2;
        LatLngBounds a2;
        jf jfVar = this.f16657g;
        if (jfVar == null || (b2 = jfVar.b()) == null || cif == null || (a2 = a(cif.a)) == null) {
            return 0;
        }
        return ((int) b2.getProjection().a(a2.getSouthWest(), a2.getNorthEast(), 0, 0, 0, 0, null)) - 2;
    }

    private PolygonInfo a(Cif.c cVar, List<LatLng> list) {
        o1 a2;
        Context context;
        PolygonInfo polygonInfo = new PolygonInfo();
        jf jfVar = this.f16657g;
        if (jfVar == null || (a2 = jfVar.a()) == null || (context = a2.getContext()) == null) {
            return polygonInfo;
        }
        f7.d(context);
        polygonInfo.points = (LatLng[]) list.toArray(new LatLng[0]);
        if (cVar != null) {
            polygonInfo.borderWidth = cVar.f16685c;
            polygonInfo.borderColor = Color.parseColor(cVar.b);
            polygonInfo.color = Color.parseColor(cVar.a);
            polygonInfo.level = 1;
            polygonInfo.minScaleLevel = this.f16660j;
            polygonInfo.maxScaleLevel = this.f16659i;
        }
        return polygonInfo;
    }

    private q5 a(Cif.e eVar, Cif.d dVar) {
        o1 a2;
        Context context;
        q5 q5Var = new q5();
        jf jfVar = this.f16657g;
        if (jfVar == null || (a2 = jfVar.a()) == null || (context = a2.getContext()) == null || eVar == null) {
            return q5Var;
        }
        int i2 = eVar.f16694e;
        if (i2 == 0) {
            q5Var.f17025k = "";
        } else if (i2 == 1) {
            q5Var.f17025k = dVar.a();
            BitmapDescriptor bitmapDescriptor = eVar.b;
            if (bitmapDescriptor != null) {
                q5Var.f17022h = bitmapDescriptor.getFormater().getBitmapId();
                Bitmap bitmap = bitmapDescriptor.getBitmap(context);
                if (bitmap != null) {
                    q5Var.f17023i = bitmap.getWidth();
                    q5Var.f17024j = bitmap.getHeight();
                }
            }
        }
        BitmapDescriptor bitmapDescriptor2 = eVar.a;
        if (bitmapDescriptor2 == null) {
            return q5Var;
        }
        q5Var.b = bitmapDescriptor2.getFormater().getBitmapId();
        Bitmap bitmap2 = bitmapDescriptor2.getBitmap(context);
        if (bitmap2 != null) {
            q5Var.f17018c = bitmap2.getWidth();
            q5Var.d = bitmap2.getHeight();
        }
        q5Var.p = 2;
        int i3 = eVar.f16700k;
        q5Var.q = i3;
        q5Var.r = ((eVar.f16699j + 1) * 10000) + i3;
        q5Var.u = dVar.f16690h;
        q5Var.s = this.f16660j;
        q5Var.t = this.f16659i;
        q5Var.f17021g = 1.0f;
        return q5Var;
    }

    private LatLngBounds a(Cif.d dVar) {
        Cif.a aVar;
        Cif.b bVar;
        List<List<LatLng>> list;
        if (dVar != null && (aVar = dVar.f16691i) != null && (bVar = aVar.f16684c) != null && (list = bVar.b) != null) {
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            Iterator<List<LatLng>> it = list.iterator();
            while (it.hasNext()) {
                builder.include(it.next());
            }
            try {
                return builder.build();
            } catch (Exception e2) {
                ma.c(Log.getStackTraceString(e2));
            }
        }
        return null;
    }

    private String a(String str) {
        o1 a2;
        Context context;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        new q5();
        jf jfVar = this.f16657g;
        if (jfVar == null || (a2 = jfVar.a()) == null || (context = a2.getContext()) == null) {
            return str;
        }
        int d2 = (int) f7.d(context);
        return str.replace("{density}", d2 <= 1 ? "" : d2 <= 2 ? "@2x" : "@3x");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public void a(List<Cif.d> list, Callback<Cif.d> callback) {
        o1 a2;
        Context context;
        jf jfVar = this.f16657g;
        if (jfVar == null || (a2 = jfVar.a()) == null || (context = a2.getContext()) == null || list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < list.size() && !this.f16662l; i2++) {
            Cif.d dVar = list.get(i2);
            Cif.e c2 = c(dVar.f16688f);
            String a3 = a(c2.d);
            String str = q;
            ma.c(str, "\u8bf7\u6c42\u5b50\u70b9[" + dVar.a() + "]icon url:" + a3);
            if (!TextUtils.isEmpty(a3)) {
                BitmapDescriptor createBitmapDescriptor = a2.createBitmapDescriptor(a3, 8);
                c2.a = createBitmapDescriptor;
                createBitmapDescriptor.getFormater().setScale(2);
                if (c2.a.getBitmap(context) != null) {
                    ma.c(str, "\u5b50\u70b9[" + dVar.a() + "]icon\u4e0b\u8f7d\u6210\u529f");
                    if (c2.f16694e == 1) {
                        m5.a aVar = new m5.a(dVar.a(), c2.f16696g, Color.parseColor(c2.f16695f));
                        aVar.a(a2.getTypeface());
                        aVar.a(Color.parseColor(c2.f16697h));
                        aVar.b(c2.f16698i);
                        aVar.a(f7.d(context) / 2.0f);
                        BitmapDescriptor createBitmapDescriptor2 = a2.createBitmapDescriptor(aVar, 9);
                        c2.b = createBitmapDescriptor2;
                        if (createBitmapDescriptor2.getBitmap(context) != null) {
                            ma.c(str, "\u5b50\u70b9[" + dVar.a() + "]\u6587\u672c\u56fe\u7247\u521b\u5efa\u6210\u529f");
                        } else {
                            ma.g(str, "\u5b50\u70b9[" + dVar.a() + "]\u6587\u672c\u56fe\u7247\u521b\u5efa\u5931\u8d25\uff01");
                        }
                    }
                    if (callback != null) {
                        callback.callback(dVar);
                    }
                } else {
                    ma.g(str, "\u5b50\u70b9[" + dVar.a() + "]icon\u4e0b\u8f7d\u5931\u8d25\uff01");
                }
            }
        }
    }

    private Point[] a(List<LatLng> list) {
        if (list == null || list.isEmpty()) {
            return new Point[0];
        }
        int size = list.size();
        Point[] pointArr = new Point[size];
        for (int i2 = 0; i2 < size; i2++) {
            pointArr[i2] = GeoPoint.from(list.get(i2)).toPoint();
        }
        return pointArr;
    }

    private Cif.c b(List<Cif.e> list) {
        return c(list).f16701l;
    }

    private void b(Cif.c cVar, List<List<LatLng>> list) {
        qc b2;
        jf jfVar = this.f16657g;
        if (jfVar == null || (b2 = jfVar.b()) == null) {
            return;
        }
        int i2 = 0;
        if (this.f16663m != null) {
            Iterator<List<LatLng>> it = list.iterator();
            while (it.hasNext()) {
                PolygonInfo a2 = a(cVar, it.next());
                int i3 = i2 + 1;
                a2.polygonId = this.f16663m[i2];
                if (!this.f16662l) {
                    b2.b(a2);
                    ma.c(q, "\u66f4\u65b0PoiLayer\u6210\u529f");
                }
                i2 = i3;
            }
            return;
        }
        this.f16663m = new int[list.size()];
        Iterator<List<LatLng>> it2 = list.iterator();
        while (it2.hasNext()) {
            PolygonInfo a3 = a(cVar, it2.next());
            if (!this.f16662l) {
                int i4 = i2 + 1;
                this.f16663m[i2] = b2.a(a3);
                ma.c(q, "\u6dfb\u52a0PoiLayer\u6210\u529f,ID=" + this.f16663m[i4 - 1] + "|model:" + a3);
                i2 = i4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Cif cif) {
        Cif.d dVar;
        Cif.b bVar;
        List<List<LatLng>> list;
        if (cif == null || (dVar = cif.a) == null) {
            return;
        }
        boolean z = false;
        Cif.c b2 = b(dVar.f16688f);
        Cif.a aVar = cif.a.f16691i;
        String str = q;
        ma.c(str, "\u7ed8\u5236PoiLayer\u7684\u9762\uff0caoiStyle:" + b2 + "|poiArea:" + aVar);
        if (aVar == null || (bVar = aVar.f16684c) == null || !"Polygon".equalsIgnoreCase(bVar.a) || (list = aVar.f16684c.b) == null) {
            ma.g(str, "PoiLayer\u7684\u9762\u6e32\u67d3\u5931\u8d25\uff01");
        } else {
            b(b2, list);
            z = true;
        }
        this.o = cif;
        AoiLayer.OnAoiLayerLoadListener onAoiLayerLoadListener = this.p;
        if (onAoiLayerLoadListener != null) {
            onAoiLayerLoadListener.onAoiLayerLoaded(z, this);
        }
        if (z) {
            List<Cif.d> list2 = cif.a.f16692j;
            ArrayList arrayList = new ArrayList();
            ma.c(str, "\u7ed8\u5236PoiLayer\u7684\u5b50\u70b9\uff0cremotePois:" + list2);
            ba.a((ba.i) new d(list2, arrayList)).a((ba.d.b) null, (ba.c<ba.d.b>) new c(list2, arrayList));
        }
    }

    private Cif.e c(List<Cif.e> list) {
        jf jfVar;
        Cif.e eVar = new Cif.e();
        if (list == null || (jfVar = this.f16657g) == null || jfVar.a() == null) {
            return eVar;
        }
        boolean a2 = this.f16657g.a().a();
        for (Cif.e eVar2 : list) {
            if ((a2 && eVar2.f16693c == 1) || (!a2 && eVar2.f16693c == 0)) {
                return eVar2;
            }
        }
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Cif.d> list) {
        qc b2;
        jf jfVar = this.f16657g;
        if (jfVar == null || (b2 = jfVar.b()) == null) {
            return;
        }
        int size = list.size();
        int[] iArr = new int[size];
        int i2 = 0;
        for (Cif.d dVar : list) {
            q5 a2 = a(c(dVar.f16688f), dVar);
            int i3 = dVar.a;
            if (i3 < 0) {
                int a3 = b2.a(a2);
                dVar.a = a3;
                int i4 = i2 + 1;
                iArr[i2] = a3;
                ma.c(q, "\u6dfb\u52a0\u5b50\u70b9\u6210\u529f\uff01" + dVar.a() + "|id:" + a2.f17022h);
                i2 = i4;
            } else {
                a2.a = i3;
                b2.c(a2);
                ma.c(q, "\u66f4\u65b0\u5b50\u70b9\u6210\u529f\uff01" + dVar.a());
            }
        }
        int[] iArr2 = new int[size];
        this.f16664n = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, size);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @WorkerThread
    public Cif l() {
        o1 a2;
        String str = q;
        ma.c(str, "\u8bf7\u6c42poiDetail[" + this.f16658h + "]");
        jf jfVar = this.f16657g;
        if (jfVar == null || (a2 = jfVar.a()) == null) {
            return null;
        }
        NetResponse poiDetail = ((w2) ((k3) l2.a(k3.class)).d()).poiDetail(this.f16658h, a2.q().g());
        poiDetail.charset = "UTF-8";
        w3.a aVar = new w3.a(poiDetail, Cif.class);
        ma.c(str, "poiDetail[" + this.f16658h + "] resp:" + aVar.available());
        if (aVar.available()) {
            return (Cif) aVar.b();
        }
        return null;
    }

    public Cif.d a(long j2) {
        Cif.d dVar;
        List<Cif.d> list;
        Cif cif = this.o;
        if (cif == null || (dVar = cif.a) == null || (list = dVar.f16692j) == null) {
            return null;
        }
        for (Cif.d dVar2 : list) {
            if (((od) this.f16657g.b().g().a(od.class, dVar2.a)) != null && r2.d() == j2) {
                return dVar2;
            }
        }
        return null;
    }

    public SubPoi a(String str, Cif.d dVar) {
        SubPoi subPoi = new SubPoi();
        subPoi.setParentId(str);
        subPoi.setId(dVar.b);
        subPoi.setName(dVar.a());
        subPoi.setPosition(dVar.f16690h);
        return subPoi;
    }

    public void a(AoiLayerOptions aoiLayerOptions) {
        if (aoiLayerOptions != null) {
            if (aoiLayerOptions.getMaxLevel() != -1) {
                this.f16659i = aoiLayerOptions.getMaxLevel();
            }
            if (aoiLayerOptions.getMinLevel() != -1) {
                this.f16660j = aoiLayerOptions.getMinLevel();
            }
        }
        if (this.f16661k) {
            return;
        }
        ma.c(q, "\u5f00\u59cb\u66f4\u65b0POI[" + this.f16658h + "]\u7684\u8be6\u60c5\u6570\u636e");
        this.f16661k = true;
        ba.a((ba.i) new b()).a((ba.d.b) null, (ba.c<ba.d.b>) new a());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof hf) {
            String str = this.f16658h;
            String str2 = ((hf) obj).f16658h;
            return str != null ? str.equals(str2) : str2 == null;
        }
        return false;
    }

    public LatLngBounds f() {
        Cif cif = this.o;
        if (cif != null) {
            return a(cif.a);
        }
        return null;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.AoiLayer, com.tencent.tencentmap.mapsdk.maps.model.IOverlay
    public String getId() {
        return this.f16658h;
    }

    public int hashCode() {
        String str = this.f16658h;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.AoiLayer
    public LatLng location() {
        Cif.d dVar;
        Cif cif = this.o;
        if (cif == null || (dVar = cif.a) == null) {
            return null;
        }
        return dVar.f16690h;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.AoiLayer
    public String name() {
        Cif.d dVar;
        Cif cif = this.o;
        if (cif == null || (dVar = cif.a) == null) {
            return null;
        }
        return dVar.f16686c;
    }

    @Override // com.tencent.tencentmap.mapsdk.maps.model.AoiLayer
    public boolean remove() {
        qc b2;
        boolean z;
        jf jfVar = this.f16657g;
        if (jfVar == null || this.f16662l || (b2 = jfVar.b()) == null) {
            return false;
        }
        int[] iArr = this.f16664n;
        if (iArr != null) {
            for (int i2 : iArr) {
                b2.b(i2);
            }
            this.f16664n = null;
            z = true;
        } else {
            z = false;
        }
        int[] iArr2 = this.f16663m;
        if (iArr2 != null) {
            for (int i3 : iArr2) {
                b2.c(i3);
            }
            this.f16663m = null;
            z = true;
        }
        this.o = null;
        this.f16657g.a(this);
        this.f16662l = true;
        ma.c(q, "\u79fb\u9664poiLayer[" + this.f16658h + "]");
        return z;
    }
}
