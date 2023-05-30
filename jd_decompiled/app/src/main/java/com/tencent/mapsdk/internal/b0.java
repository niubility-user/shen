package com.tencent.mapsdk.internal;

import android.content.Context;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.vector.VectorMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorLevel;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class b0 implements g5, ke {

    /* renamed from: g */
    private hc f16258g;

    /* renamed from: h */
    private r5 f16259h;

    /* renamed from: j */
    private boolean f16261j;

    /* renamed from: k */
    private xi f16262k;

    /* renamed from: l */
    private TencentMap.OnIndoorStateChangeListener f16263l;

    /* renamed from: m */
    private VectorMap f16264m;

    /* renamed from: n */
    private qc f16265n;

    /* renamed from: i */
    private d f16260i = d.IDLE;
    private boolean o = false;
    private IndoorBuilding p = null;

    /* loaded from: classes9.dex */
    public class a implements Runnable {
        public a() {
            b0.this = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (b0.this.f16260i == d.IDLE) {
                b0 b0Var = b0.this;
                b0Var.c(b0Var.f16259h.e());
                return;
            }
            b0 b0Var2 = b0.this;
            b0Var2.a(b0Var2.f16260i);
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Runnable {
        public final /* synthetic */ String a;
        public final /* synthetic */ String b;

        /* renamed from: c */
        public final /* synthetic */ LatLng f16266c;
        public final /* synthetic */ String[] d;

        /* renamed from: e */
        public final /* synthetic */ int f16267e;

        public b(String str, String str2, LatLng latLng, String[] strArr, int i2) {
            b0.this = r1;
            this.a = str;
            this.b = str2;
            this.f16266c = latLng;
            this.d = strArr;
            this.f16267e = i2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b0.this.a(this.a, this.b, this.f16266c, this.d, this.f16267e);
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class c {
        public static final /* synthetic */ int[] a;

        static {
            d.values();
            int[] iArr = new int[3];
            a = iArr;
            try {
                d dVar = d.SET_TRUE;
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                int[] iArr2 = a;
                d dVar2 = d.IDLE;
                iArr2[0] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                int[] iArr3 = a;
                d dVar3 = d.SET_FALSE;
                iArr3[2] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes9.dex */
    public enum d {
        IDLE,
        SET_TRUE,
        SET_FALSE
    }

    public b0(xi xiVar, String str) {
        this.f16263l = null;
        this.f16264m = null;
        this.f16262k = xiVar;
        if (xiVar != null) {
            Context context = xiVar.getContext();
            this.f16258g = str == null ? kc.a(context) : jc.a(context, str);
            this.f16264m = this.f16262k.getMap();
            this.f16265n = this.f16262k.A();
            k();
            c(false);
        }
        VectorMap vectorMap = this.f16264m;
        if (vectorMap != null) {
            vectorMap.a((g5) this);
            this.f16264m.a((ke) this);
            this.f16263l = new ui(this.f16262k);
        }
    }

    public void a(String str, String str2, LatLng latLng, String[] strArr, int i2) {
        dg dgVar;
        v6 w;
        dg dgVar2;
        xi xiVar = this.f16262k;
        if (xiVar == null || xiVar.getMap() == null) {
            return;
        }
        VectorMap map = this.f16262k.getMap();
        int P = map.P();
        if (str == null || strArr == null || strArr.length <= 0 || i2 < 0 || P < 16) {
            n();
            if (this.o) {
                this.o = false;
                this.p = null;
                xi xiVar2 = this.f16262k;
                if (xiVar2 != null && (dgVar = xiVar2.P) != null) {
                    dgVar.a((IndoorBuilding) null);
                }
                TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener = this.f16263l;
                if (onIndoorStateChangeListener != null) {
                    onIndoorStateChangeListener.onIndoorBuildingDeactivated();
                    return;
                }
                return;
            }
            return;
        }
        TencentMap.OnIndoorStateChangeListener onIndoorStateChangeListener2 = this.f16263l;
        if (onIndoorStateChangeListener2 != null && !this.o) {
            this.o = true;
            onIndoorStateChangeListener2.onIndoorBuildingFocused();
        }
        map.k(Math.min(this.f16262k.L, 22));
        if (this.f16263l != null) {
            ArrayList arrayList = new ArrayList();
            for (String str3 : strArr) {
                arrayList.add(new IndoorLevel(str3));
            }
            try {
                IndoorBuilding indoorBuilding = this.p;
                if (indoorBuilding != null && indoorBuilding.getBuidlingId().equals(str)) {
                    if (this.p.getActiveLevelIndex() == i2) {
                        return;
                    }
                }
            } catch (Exception unused) {
            }
            IndoorBuilding indoorBuilding2 = this.p;
            if ((indoorBuilding2 == null || !str.equals(indoorBuilding2.getBuidlingId())) && (w = this.f16265n.w()) != null) {
                w.k().b().b();
            }
            this.p = new IndoorBuilding(str, str2, latLng, arrayList, i2);
            xi xiVar3 = this.f16262k;
            if (xiVar3 != null && (dgVar2 = xiVar3.P) != null && dgVar2.h()) {
                this.f16262k.P.a(this.p);
            }
            this.f16263l.onIndoorLevelActivated(this.p);
        }
    }

    private boolean a() {
        r5 r5Var = this.f16259h;
        return r5Var != null && r5Var.e();
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void k() {
        /*
            r5 = this;
            com.tencent.mapsdk.internal.hc r0 = r5.f16258g
            java.lang.String r1 = "AIEnabled"
            int r0 = r0.b(r1)
            com.tencent.mapsdk.internal.hc r1 = r5.f16258g
            java.lang.String r2 = "AIType"
            int r1 = r1.b(r2)
            com.tencent.mapsdk.internal.hc r2 = r5.f16258g
            java.lang.String r3 = "AIBuildingList"
            java.lang.String r2 = r2.d(r3)
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch: java.lang.Exception -> L24
            if (r3 != 0) goto L2c
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch: java.lang.Exception -> L24
            r3.<init>(r2)     // Catch: java.lang.Exception -> L24
            goto L2d
        L24:
            r2 = move-exception
            java.lang.String r3 = "TI"
            java.lang.String r4 = "indoor auth init failed"
            com.tencent.mapsdk.internal.ma.a(r3, r4, r2)
        L2c:
            r3 = 0
        L2d:
            r2 = -1
            if (r0 == r2) goto L3b
            if (r1 == r2) goto L3b
            if (r3 == 0) goto L3b
            com.tencent.mapsdk.internal.r5 r2 = new com.tencent.mapsdk.internal.r5
            r2.<init>(r0, r1, r3)
            r5.f16259h = r2
        L3b:
            com.tencent.mapsdk.vector.VectorMap r0 = r5.f16264m
            if (r0 == 0) goto L52
            int r2 = r5.j()
            r0.a(r2)
            r0 = 1
            if (r1 != r0) goto L52
            com.tencent.mapsdk.vector.VectorMap r0 = r5.f16264m
            java.lang.String[] r1 = r5.c()
            r0.a(r1)
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.mapsdk.internal.b0.k():void");
    }

    private void n() {
        xi xiVar = this.f16262k;
        if (xiVar == null || xiVar.getMap() == null || this.o) {
            return;
        }
        VectorMap map = this.f16262k.getMap();
        int min = Math.min(20, this.f16262k.L);
        if (map.M().x() < min) {
            map.k(min);
        }
    }

    public int a(String str) {
        VectorMap vectorMap = this.f16264m;
        if (vectorMap == null) {
            return -1;
        }
        return vectorMap.a(str);
    }

    public void a(int i2) {
        qc qcVar = this.f16265n;
        if (qcVar == null) {
            return;
        }
        qcVar.j(i2);
        d();
    }

    public void a(d dVar) {
        int ordinal = dVar.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                c(true);
                return;
            } else if (ordinal != 2) {
                return;
            }
        }
        c(false);
    }

    public void a(r5 r5Var) {
        if (r5Var != null) {
            this.f16259h = r5Var;
            ma.a(la.f16819f, "IndoorAuth:" + r5Var);
            this.f16258g.b(l4.B, r5Var.c());
            this.f16258g.b(l4.C, r5Var.d());
            if (r5Var.a() != null) {
                this.f16258g.b(l4.D, r5Var.a().toString());
            }
            VectorMap vectorMap = this.f16264m;
            if (vectorMap != null) {
                vectorMap.a(j());
                if (j() == 1) {
                    this.f16264m.a(c());
                }
            }
        } else {
            this.f16258g.a(new String[]{l4.B, l4.C, l4.D});
        }
        if (this.f16259h == null) {
            this.f16259h = new r5();
        }
        ba.b(new a());
    }

    @Override // com.tencent.mapsdk.internal.g5
    public void a(y5 y5Var) {
        xi xiVar;
        if (!this.f16261j || (xiVar = this.f16262k) == null || xiVar.getMap() == null || this.f16262k.k() == null || this.o) {
            return;
        }
        n();
    }

    public void a(String str, String str2) {
        qc qcVar = this.f16265n;
        if (qcVar == null) {
            return;
        }
        qcVar.a(str, str2);
    }

    public void a(boolean z) {
        VectorMap vectorMap = this.f16264m;
        if (vectorMap == null) {
            return;
        }
        vectorMap.h(z);
    }

    public IndoorBuilding b() {
        return this.p;
    }

    public void b(boolean z) {
        d dVar = z ? d.SET_TRUE : d.SET_FALSE;
        this.f16260i = dVar;
        a(dVar);
    }

    public void c(boolean z) {
        this.f16261j = z;
        if (this.f16265n == null) {
            return;
        }
        if (!a()) {
            this.f16265n.k(false);
            return;
        }
        this.f16265n.k(z);
        if (z || !this.o) {
            return;
        }
        a(null, null, null, null, -1);
    }

    public String[] c() {
        r5 r5Var = this.f16259h;
        if (r5Var != null) {
            return r5Var.b();
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.ke
    public void d() {
        xi xiVar;
        GeoPoint geoPoint;
        cg b2;
        if (!this.f16261j || (xiVar = this.f16262k) == null || xiVar.getMap() == null || (b2 = this.f16262k.getMap().b((geoPoint = new GeoPoint()))) == null) {
            return;
        }
        double latitudeE6 = geoPoint.getLatitudeE6();
        Double.isNaN(latitudeE6);
        double longitudeE6 = geoPoint.getLongitudeE6();
        Double.isNaN(longitudeE6);
        ba.b(new b(b2.a, b2.b, new LatLng((latitudeE6 * 1.0d) / 1000000.0d, (longitudeE6 * 1.0d) / 1000000.0d), b2.d, b2.f16373c));
    }

    public String e() {
        IndoorBuilding indoorBuilding = this.p;
        return indoorBuilding == null ? "" : indoorBuilding.getBuildingName();
    }

    public IndoorBuilding f() {
        return this.p;
    }

    public String g() {
        IndoorBuilding indoorBuilding = this.p;
        if (indoorBuilding == null) {
            return null;
        }
        return indoorBuilding.getBuidlingId();
    }

    public int h() {
        IndoorBuilding indoorBuilding = this.p;
        if (indoorBuilding == null) {
            return -1;
        }
        return indoorBuilding.getActiveLevelIndex();
    }

    public String[] i() {
        IndoorBuilding indoorBuilding = this.p;
        if (indoorBuilding == null || indoorBuilding.getLevels() == null || this.p.getLevels().isEmpty()) {
            return null;
        }
        List<IndoorLevel> levels = this.p.getLevels();
        String[] strArr = new String[levels.size()];
        for (int i2 = 0; i2 < levels.size(); i2++) {
            strArr[i2] = levels.get(i2).getName();
        }
        return strArr;
    }

    public int j() {
        r5 r5Var = this.f16259h;
        return (r5Var == null || !r5Var.f()) ? 0 : 1;
    }

    public boolean l() {
        return this.o;
    }

    public void m() {
        IndoorBuilding indoorBuilding = this.p;
        if (indoorBuilding != null) {
            String buidlingId = indoorBuilding.getBuidlingId();
            int activeLevelIndex = this.p.getActiveLevelIndex();
            List<IndoorLevel> levels = this.p.getLevels();
            if (levels == null || activeLevelIndex >= levels.size()) {
                return;
            }
            String name = levels.get(activeLevelIndex).getName();
            if (e7.b(buidlingId) || e7.b(name)) {
                return;
            }
            this.f16265n.a(buidlingId, name);
        }
    }
}
