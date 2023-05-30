package com.tencent.mapsdk.internal;

import com.tencent.tencentmap.mapsdk.maps.interfaces.Levelable;
import com.tencent.tencentmap.mapsdk.maps.model.Arc;
import com.tencent.tencentmap.mapsdk.maps.model.ArcOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Circle;
import com.tencent.tencentmap.mapsdk.maps.model.CircleOptions;
import com.tencent.tencentmap.mapsdk.maps.model.IndoorBuilding;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polygon;
import com.tencent.tencentmap.mapsdk.maps.model.PolygonOptions;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.PolylineOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class i1 {

    /* renamed from: f */
    private IndoorBuilding f16675f;

    /* renamed from: g */
    private final a1 f16676g;

    /* renamed from: j */
    private final f1 f16679j;
    private final Map<String, t4> a = new ConcurrentHashMap();
    private final List<t4> b = new CopyOnWriteArrayList();

    /* renamed from: c */
    private final List<t4> f16673c = new CopyOnWriteArrayList();
    private final List<t4> d = new CopyOnWriteArrayList();

    /* renamed from: e */
    private final List<p4> f16674e = new CopyOnWriteArrayList();

    /* renamed from: h */
    private final Comparator<Levelable> f16677h = new a();

    /* renamed from: i */
    private final Comparator<Levelable> f16678i = new b();

    /* loaded from: classes9.dex */
    public class a implements Comparator<Levelable> {
        public a() {
            i1.this = r1;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Levelable levelable, Levelable levelable2) {
            return Float.compare(levelable.getZIndex(), levelable2.getZIndex());
        }
    }

    /* loaded from: classes9.dex */
    public class b implements Comparator<Levelable> {
        public b() {
            i1.this = r1;
        }

        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(Levelable levelable, Levelable levelable2) {
            return levelable.getLevel() == levelable2.getLevel() ? Float.compare(levelable.getZIndex(), levelable2.getZIndex()) : levelable.getLevel() - levelable2.getLevel();
        }
    }

    public i1(a1 a1Var, e1 e1Var) {
        this.f16676g = a1Var;
        this.f16679j = new f1(e1Var);
    }

    private void a(p4 p4Var) {
        if (p4Var == null || p4Var.f() == null) {
            return;
        }
        IndoorBuilding indoorBuilding = this.f16675f;
        if (indoorBuilding != null) {
            p4Var.a(indoorBuilding);
        } else {
            p4Var.l();
        }
        this.f16674e.add(p4Var);
    }

    public <T extends t4> T a(String str, Class<T> cls) {
        T t = (T) this.a.get(str);
        if (t == null || t.getClass() != cls) {
            return null;
        }
        return t;
    }

    public Arc a(ArcOptions arcOptions) {
        we weVar = new we(arcOptions, this.f16676g);
        s0 s0Var = new s0(weVar);
        this.a.put(weVar.getId(), s0Var);
        this.f16673c.add(s0Var);
        a((p4) weVar);
        return s0Var;
    }

    public Circle a(CircleOptions circleOptions) {
        xe xeVar = new xe(this.f16676g);
        xeVar.a(circleOptions);
        t0 t0Var = new t0(xeVar);
        this.a.put(xeVar.getId(), t0Var);
        this.d.add(t0Var);
        a((p4) xeVar);
        return t0Var;
    }

    public Marker a(MarkerOptions markerOptions) {
        xi xiVar = (xi) this.f16676g;
        af afVar = new af(xiVar);
        afVar.setMarkerOptions(markerOptions);
        w0 w0Var = new w0(afVar);
        this.a.put(afVar.getId(), w0Var);
        this.b.add(w0Var);
        xiVar.a(afVar);
        a((p4) afVar);
        return w0Var;
    }

    public Polygon a(PolygonOptions polygonOptions) {
        bf bfVar = new bf(this.f16676g, polygonOptions);
        x0 x0Var = new x0(bfVar);
        this.a.put(bfVar.getId(), x0Var);
        this.d.add(x0Var);
        a((p4) bfVar);
        return x0Var;
    }

    public Polyline a(PolylineOptions polylineOptions) {
        cf cfVar = new cf(this.f16676g);
        cfVar.setPolylineOptions(polylineOptions);
        y0 y0Var = new y0(cfVar);
        this.a.put(cfVar.getId(), y0Var);
        this.f16673c.add(y0Var);
        a((p4) cfVar);
        return y0Var;
    }

    public void a() {
        synchronized (this) {
            Iterator<t4> it = this.a.values().iterator();
            while (it.hasNext()) {
                t4 next = it.next();
                if (next != null) {
                    next.remove();
                    it.remove();
                }
            }
        }
        this.b.clear();
        this.f16673c.clear();
        this.d.clear();
    }

    public void a(int i2, int i3) {
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof p4) {
                ((p4) t4Var).a(i2, i3);
            }
        }
    }

    public void a(t4 t4Var) {
        this.f16679j.a(t4Var);
        if (t4Var instanceof p4) {
            a((p4) t4Var);
        }
    }

    public void a(GL10 gl10) {
        this.f16679j.a(gl10);
    }

    public boolean a(float f2, float f3) {
        return this.f16679j.a(f2, f3);
    }

    public boolean a(IndoorBuilding indoorBuilding) {
        this.f16675f = indoorBuilding;
        boolean z = false;
        for (p4 p4Var : this.f16674e) {
            if (p4Var.f() != null) {
                z = true;
                if (indoorBuilding != null) {
                    p4Var.a(indoorBuilding);
                } else {
                    p4Var.l();
                }
            }
        }
        return z;
    }

    public boolean a(String str) {
        p0 x;
        t4 remove = this.a.remove(str);
        if (remove != null) {
            this.b.remove(remove);
            this.f16673c.remove(remove);
            this.d.remove(remove);
            if ((remove instanceof v0) && (x = ((v0) remove).x()) != null) {
                this.f16674e.remove(x);
            }
        }
        return remove != null;
    }

    public t4 b(String str) {
        return this.a.get(str);
    }

    public List<Arc> b() {
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof s0) {
                arrayList.add((s0) t4Var);
            }
        }
        Collections.sort(arrayList, this.f16677h);
        return arrayList;
    }

    public void b(t4 t4Var) {
        this.f16679j.b(t4Var);
    }

    public List<Circle> c() {
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof t0) {
                arrayList.add((t0) t4Var);
            }
        }
        Collections.sort(arrayList, this.f16677h);
        return arrayList;
    }

    public List<t4> d() {
        return this.f16673c;
    }

    public List<Marker> e() {
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof w0) {
                arrayList.add((w0) t4Var);
            }
        }
        Collections.sort(arrayList, this.f16677h);
        return arrayList;
    }

    public Iterable<t4> f() {
        return this.a.values();
    }

    public List<t4> g() {
        return this.d;
    }

    public List<t4> h() {
        return this.b;
    }

    public List<Polygon> i() {
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof x0) {
                arrayList.add((x0) t4Var);
            }
        }
        Collections.sort(arrayList, this.f16678i);
        return arrayList;
    }

    public List<Polyline> j() {
        ArrayList arrayList = new ArrayList();
        for (t4 t4Var : this.a.values()) {
            if (t4Var instanceof y0) {
                arrayList.add((y0) t4Var);
            }
        }
        Collections.sort(arrayList, this.f16677h);
        return arrayList;
    }
}
