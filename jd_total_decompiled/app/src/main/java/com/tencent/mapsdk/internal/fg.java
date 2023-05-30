package com.tencent.mapsdk.internal;

import android.graphics.Rect;
import com.tencent.map.lib.models.GeoPoint;
import com.tencent.mapsdk.engine.jni.models.TappedElement;
import com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class fg extends ye {
    private e1 B;
    private s5 C;
    private int[] D;
    private int[] E;
    private int F;
    private GeoPoint G;
    private boolean H;
    private Selectable.OnSelectedListener I;
    private int J;
    private a K;
    private float L;
    private int M;
    private pc N;
    private boolean O;
    private p0 P;

    /* loaded from: classes9.dex */
    public static class a {
        public int a;
        public int b;
    }

    public fg(p0 p0Var, a1 a1Var, s5 s5Var) {
        super(a1Var);
        this.H = true;
        this.J = -1;
        this.L = -1.0f;
        this.M = -1;
        this.P = p0Var;
        this.N = a1Var.w();
        a(s5Var);
    }

    private Rect a(List<GeoPoint> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        GeoPoint geoPoint = list.get(0);
        int longitudeE6 = geoPoint.getLongitudeE6();
        int longitudeE62 = geoPoint.getLongitudeE6();
        int latitudeE6 = geoPoint.getLatitudeE6();
        int latitudeE62 = geoPoint.getLatitudeE6();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            GeoPoint geoPoint2 = list.get(i2);
            if (geoPoint2 != null) {
                int latitudeE63 = geoPoint2.getLatitudeE6();
                int longitudeE63 = geoPoint2.getLongitudeE6();
                if (longitudeE63 < longitudeE6) {
                    longitudeE6 = longitudeE63;
                } else if (longitudeE63 > longitudeE62) {
                    longitudeE62 = longitudeE63;
                }
                if (latitudeE63 < latitudeE62) {
                    latitudeE62 = latitudeE63;
                } else if (latitudeE63 > latitudeE6) {
                    latitudeE6 = latitudeE63;
                }
            }
        }
        return new Rect(longitudeE6, latitudeE6, longitudeE62, latitudeE62);
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void E() {
        pc pcVar = this.N;
        if (pcVar == null) {
            return;
        }
        e1 a2 = pcVar.a();
        this.B = a2;
        xi xiVar = (xi) a2.j();
        if (xiVar == null) {
            return;
        }
        if (this.O && this.J != -1) {
            ma.a(la.f16819f, "deleteLine..." + this.J);
            xiVar.getMap().d(this);
            this.B.f().a(this.J, g0());
            this.J = -1;
            return;
        }
        float b = pcVar.b();
        float f2 = this.L;
        if (f2 == -1.0f || f2 != b) {
            this.L = b;
        }
        if (this.J == -1) {
            this.J = this.B.f().a(this);
            ma.a(la.f16819f, "createLine..." + this.J);
        }
        if (C()) {
            this.B.f().l(this);
        }
    }

    @Override // com.tencent.mapsdk.internal.ye
    public void H() {
        this.O = true;
    }

    public void J() {
        a aVar = this.K;
        if (aVar != null) {
            aVar.a = -1;
            B();
        }
    }

    public int[] K() {
        return this.C.f17221i;
    }

    public int[] L() {
        return this.C.f17220h;
    }

    public float M() {
        return this.C.f17222j;
    }

    public String N() {
        return this.C.w;
    }

    public int O() {
        return this.C.b();
    }

    public GeoPoint P() {
        return this.G;
    }

    public int Q() {
        return this.F;
    }

    public final int R() {
        return this.J;
    }

    public ArrayList<GeoPoint> S() {
        return this.C.b;
    }

    public int[] T() {
        return this.C.d;
    }

    public int[] U() {
        return this.C.f17216c;
    }

    public String[] V() {
        return this.C.f17217e;
    }

    public int[] W() {
        return this.E;
    }

    public int[] X() {
        return this.D;
    }

    public float Y() {
        return this.C.d();
    }

    public String Z() {
        s5 s5Var = this.C;
        int[] iArr = s5Var.f17219g;
        if (iArr != null && iArr.length > 0) {
            if (iArr[0] == 33) {
                return s5.N;
            }
            if (iArr[0] == 20) {
                return s5.O;
            }
        }
        return e7.b(s5Var.f17226n) ? s5.M : this.C.f17226n;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0
    /* renamed from: a */
    public Rect getBound(s4 s4Var) {
        ArrayList<GeoPoint> arrayList;
        s5 s5Var = this.C;
        if (s5Var == null || (arrayList = s5Var.b) == null || arrayList.isEmpty()) {
            return null;
        }
        Iterator<GeoPoint> it = this.C.b.iterator();
        int i2 = Integer.MAX_VALUE;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = Integer.MIN_VALUE;
        while (it.hasNext()) {
            GeoPoint next = it.next();
            int latitudeE6 = next.getLatitudeE6();
            int longitudeE6 = next.getLongitudeE6();
            if (latitudeE6 > i4) {
                i4 = latitudeE6;
            }
            if (latitudeE6 < i3) {
                i3 = latitudeE6;
            }
            if (longitudeE6 > i5) {
                i5 = longitudeE6;
            }
            if (longitudeE6 < i2) {
                i2 = longitudeE6;
            }
        }
        return new Rect(i2, i4, i5, i3);
    }

    public void a(float f2) {
        this.C.f17222j = f2;
    }

    public void a(int i2, GeoPoint geoPoint) {
        this.F = i2;
        this.G = geoPoint;
        B();
    }

    public void a(s5 s5Var) {
        if (s5Var == null || !s5Var.a()) {
            ma.h("LineOptions\u4e0d\u80fd\u4e3a\u7a7a\uff01");
        } else if (s5Var.equals(this.C)) {
        } else {
            B();
            this.C = s5Var;
            this.D = s5Var.f17218f;
            this.E = s5Var.f17219g;
        }
    }

    public void a(String str) {
        this.C.f17226n = str;
    }

    @Deprecated
    public void a(String str, int i2) {
        this.C.f17226n = str;
    }

    @Deprecated
    public void a(String str, String str2, int i2) {
        this.C.f17226n = str;
    }

    public void a(int[] iArr) {
        this.C.f17221i = iArr;
    }

    public a a0() {
        return this.K;
    }

    @Override // com.tencent.mapsdk.internal.v0
    /* renamed from: b */
    public Rect getScreenBound(s4 s4Var) {
        Rect bound = getBound(s4Var);
        if (bound == null) {
            return null;
        }
        GeoPoint geoPoint = new GeoPoint(bound.top, bound.left);
        GeoPoint geoPoint2 = new GeoPoint(bound.bottom, bound.right);
        o5 a2 = s4Var.a(geoPoint);
        o5 a3 = s4Var.a(geoPoint2);
        return new Rect((int) a2.a, (int) a2.b, (int) a3.a, (int) a3.b);
    }

    public final void b(int i2) {
        this.J = i2;
    }

    public void b(int i2, int i3) {
        a aVar = new a();
        this.K = aVar;
        aVar.a = i2;
        aVar.b = i3;
        B();
    }

    public void b(List<Integer> list) {
        this.C.a(list);
    }

    public void b(boolean z) {
        this.C.t = z;
    }

    public int[] b0() {
        s5 s5Var = this.C;
        return new int[]{s5Var.y, s5Var.z};
    }

    public Rect c() {
        int i2;
        Rect a2;
        ArrayList<GeoPoint> arrayList = this.C.a;
        if (arrayList != null && !arrayList.isEmpty() && (i2 = this.F) >= 0 && i2 < arrayList.size() && (a2 = a(arrayList.subList(this.F, arrayList.size()))) != null) {
            this.C.u = a2;
        }
        return this.C.u;
    }

    public boolean c0() {
        return this.C.f17224l;
    }

    public boolean d0() {
        return this.C.f17223k;
    }

    public boolean e0() {
        return this.C.o;
    }

    public boolean f0() {
        return this.C.t;
    }

    public boolean g0() {
        return this.C.q;
    }

    public float getAlpha() {
        s5 s5Var = this.C;
        if (s5Var == null) {
            return 1.0f;
        }
        return s5Var.p;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getLevel() {
        return this.C.c();
    }

    public List<Integer> getPattern() {
        return this.C.A;
    }

    public float getWidth() {
        return this.C.f17225m;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public int getZIndex() {
        return this.C.v;
    }

    public boolean isAboveMaskLayer() {
        return this.C.s;
    }

    public boolean isGradientEnable() {
        return this.C.D;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public boolean isSelected() {
        return this.H;
    }

    @Override // com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Tappable
    public boolean onTap(float f2, float f3) {
        TappedElement a2;
        e1 e1Var = this.B;
        return (e1Var == null || (a2 = e1Var.f().a(f2, f3)) == null || a2.itemId != ((long) R())) ? false : true;
    }

    public void setAlpha(float f2) {
        s5 s5Var = this.C;
        if (s5Var == null || f2 < 0.0f || f2 > 1.0f) {
            return;
        }
        s5Var.p = f2;
    }

    public void setArrow(boolean z) {
        this.C.f17224l = z;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Selectable
    public void setSelected(boolean z) {
        this.H = z;
        e1 e1Var = this.B;
        if (e1Var == null || e1Var.f() == null) {
            return;
        }
        this.B.f().h(this);
    }

    public void setWidth(float f2) {
        this.C.f17225m = f2;
    }

    @Override // com.tencent.mapsdk.internal.ye, com.tencent.mapsdk.internal.v0, com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable
    public void setZIndex(int i2) {
        this.C.v = i2;
        e1 e1Var = this.B;
        if (e1Var == null || e1Var.f() == null) {
            return;
        }
        this.B.f().a(this.M, i2);
    }

    @Override // com.tencent.mapsdk.internal.v0
    public p0 x() {
        return this.P;
    }
}
