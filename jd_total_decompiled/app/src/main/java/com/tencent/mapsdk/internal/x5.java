package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.x5.a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes9.dex */
public class x5<T extends a> {

    /* renamed from: e  reason: collision with root package name */
    private static final int f17452e = 50;

    /* renamed from: f  reason: collision with root package name */
    private static final int f17453f = 40;
    private final n5 a;
    private final int b;

    /* renamed from: c  reason: collision with root package name */
    private Set<T> f17454c;
    private List<x5<T>> d;

    /* loaded from: classes9.dex */
    public interface a {
        o5 a();
    }

    public x5(double d, double d2, double d3, double d4) {
        this(new n5(d, d2, d3, d4));
    }

    private x5(double d, double d2, double d3, double d4, int i2) {
        this(new n5(d, d2, d3, d4), i2);
    }

    public x5(n5 n5Var) {
        this(n5Var, 0);
    }

    private x5(n5 n5Var, int i2) {
        this.d = null;
        this.a = n5Var;
        this.b = i2;
    }

    private void a(double d, double d2, T t) {
        List<x5<T>> list = this.d;
        if (list != null) {
            n5 n5Var = this.a;
            int i2 = (d2 > n5Var.f16886f ? 1 : (d2 == n5Var.f16886f ? 0 : -1));
            int i3 = (d > n5Var.f16885e ? 1 : (d == n5Var.f16885e ? 0 : -1));
            list.get(i2 < 0 ? i3 < 0 ? 0 : 1 : i3 < 0 ? 2 : 3).a(d, d2, t);
            return;
        }
        if (this.f17454c == null) {
            this.f17454c = new HashSet();
        }
        this.f17454c.add(t);
        if (this.f17454c.size() <= 50 || this.b >= 40) {
            return;
        }
        b();
    }

    private void a(n5 n5Var, Collection<T> collection) {
        if (this.a.b(n5Var)) {
            List<x5<T>> list = this.d;
            if (list != null) {
                Iterator<x5<T>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().a(n5Var, collection);
                }
            } else if (this.f17454c != null) {
                if (n5Var.a(this.a)) {
                    collection.addAll(this.f17454c);
                    return;
                }
                for (T t : this.f17454c) {
                    if (n5Var.a(t.a())) {
                        collection.add(t);
                    }
                }
            }
        }
    }

    private void b() {
        ArrayList arrayList = new ArrayList(4);
        this.d = arrayList;
        n5 n5Var = this.a;
        arrayList.add(new x5(n5Var.a, n5Var.f16885e, n5Var.b, n5Var.f16886f, this.b + 1));
        List<x5<T>> list = this.d;
        n5 n5Var2 = this.a;
        list.add(new x5<>(n5Var2.f16885e, n5Var2.f16884c, n5Var2.b, n5Var2.f16886f, this.b + 1));
        List<x5<T>> list2 = this.d;
        n5 n5Var3 = this.a;
        list2.add(new x5<>(n5Var3.a, n5Var3.f16885e, n5Var3.f16886f, n5Var3.d, this.b + 1));
        List<x5<T>> list3 = this.d;
        n5 n5Var4 = this.a;
        list3.add(new x5<>(n5Var4.f16885e, n5Var4.f16884c, n5Var4.f16886f, n5Var4.d, this.b + 1));
        Set<T> set = this.f17454c;
        this.f17454c = null;
        for (T t : set) {
            a(t.a().a, t.a().b, t);
        }
    }

    private boolean b(double d, double d2, T t) {
        List<x5<T>> list = this.d;
        int i2 = 0;
        if (list == null) {
            Set<T> set = this.f17454c;
            if (set == null) {
                return false;
            }
            return set.remove(t);
        }
        n5 n5Var = this.a;
        if (d2 >= n5Var.f16886f) {
            i2 = d < n5Var.f16885e ? 2 : 3;
        } else if (d >= n5Var.f16885e) {
            i2 = 1;
        }
        return list.get(i2).b(d, d2, t);
    }

    public Collection<T> a(n5 n5Var) {
        ArrayList arrayList = new ArrayList();
        a(n5Var, arrayList);
        return arrayList;
    }

    public void a() {
        this.d = null;
        Set<T> set = this.f17454c;
        if (set != null) {
            set.clear();
        }
    }

    public void a(T t) {
        o5 a2 = t.a();
        if (this.a.a(a2.a, a2.b)) {
            a(a2.a, a2.b, t);
        }
    }

    public boolean b(T t) {
        o5 a2 = t.a();
        if (this.a.a(a2.a, a2.b)) {
            return b(a2.a, a2.b, t);
        }
        return false;
    }
}
