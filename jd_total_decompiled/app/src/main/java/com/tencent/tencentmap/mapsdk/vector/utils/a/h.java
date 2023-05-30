package com.tencent.tencentmap.mapsdk.vector.utils.a;

import com.tencent.tencentmap.mapsdk.vector.utils.a.h.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class h<T extends a> {
    public final e a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public List<T> f18008c;
    public List<h<T>> d;

    /* loaded from: classes9.dex */
    public interface a {
        f getPoint();
    }

    public h(double d, double d2, double d3, double d4) {
        this(new e(d, d2, d3, d4));
    }

    public void a(T t) {
        f point2 = t.getPoint();
        if (this.a.a(point2.a, point2.b)) {
            a(point2.a, point2.b, t);
        }
    }

    public final void b() {
        ArrayList arrayList = new ArrayList(4);
        this.d = arrayList;
        e eVar = this.a;
        arrayList.add(new h(eVar.a, eVar.f18006e, eVar.b, eVar.f18007f, this.b + 1));
        List<h<T>> list = this.d;
        e eVar2 = this.a;
        list.add(new h<>(eVar2.f18006e, eVar2.f18005c, eVar2.b, eVar2.f18007f, this.b + 1));
        List<h<T>> list2 = this.d;
        e eVar3 = this.a;
        list2.add(new h<>(eVar3.a, eVar3.f18006e, eVar3.f18007f, eVar3.d, this.b + 1));
        List<h<T>> list3 = this.d;
        e eVar4 = this.a;
        list3.add(new h<>(eVar4.f18006e, eVar4.f18005c, eVar4.f18007f, eVar4.d, this.b + 1));
        List<T> list4 = this.f18008c;
        this.f18008c = null;
        for (T t : list4) {
            a(t.getPoint().a, t.getPoint().b, t);
        }
    }

    public h(e eVar) {
        this(eVar, 0);
    }

    public h(double d, double d2, double d3, double d4, int i2) {
        this(new e(d, d2, d3, d4), i2);
    }

    public h(e eVar, int i2) {
        this.d = null;
        this.a = eVar;
        this.b = i2;
    }

    public final void a(double d, double d2, T t) {
        List<h<T>> list = this.d;
        if (list != null) {
            e eVar = this.a;
            if (d2 < eVar.f18007f) {
                if (d < eVar.f18006e) {
                    list.get(0).a(d, d2, t);
                    return;
                } else {
                    list.get(1).a(d, d2, t);
                    return;
                }
            } else if (d < eVar.f18006e) {
                list.get(2).a(d, d2, t);
                return;
            } else {
                list.get(3).a(d, d2, t);
                return;
            }
        }
        if (this.f18008c == null) {
            this.f18008c = new ArrayList();
        }
        this.f18008c.add(t);
        if (this.f18008c.size() <= 50 || this.b >= 40) {
            return;
        }
        b();
    }

    public boolean b(T t) {
        f point2 = t.getPoint();
        if (this.a.a(point2.a, point2.b)) {
            return b(point2.a, point2.b, t);
        }
        return false;
    }

    public final boolean b(double d, double d2, T t) {
        List<h<T>> list = this.d;
        if (list != null) {
            e eVar = this.a;
            if (d2 < eVar.f18007f) {
                if (d < eVar.f18006e) {
                    return list.get(0).b(d, d2, t);
                }
                return list.get(1).b(d, d2, t);
            } else if (d < eVar.f18006e) {
                return list.get(2).b(d, d2, t);
            } else {
                return list.get(3).b(d, d2, t);
            }
        }
        return this.f18008c.remove(t);
    }

    public void a() {
        this.d = null;
        List<T> list = this.f18008c;
        if (list != null) {
            list.clear();
        }
    }

    public Collection<T> a(e eVar) {
        ArrayList arrayList = new ArrayList();
        a(eVar, arrayList);
        return arrayList;
    }

    public final void a(e eVar, Collection<T> collection) {
        if (this.a.b(eVar)) {
            List<h<T>> list = this.d;
            if (list != null) {
                Iterator<h<T>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(eVar, collection);
                }
            } else if (this.f18008c != null) {
                if (eVar.a(this.a)) {
                    collection.addAll(this.f18008c);
                    return;
                }
                for (T t : this.f18008c) {
                    if (eVar.a(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }
}
