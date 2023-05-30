package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.j9;
import com.tencent.mapsdk.internal.m9;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class o9<D extends m9> extends t9<D> implements j9<D> {
    private ArrayList<l9<D>> b = new ArrayList<>();

    /* renamed from: c  reason: collision with root package name */
    private boolean f16921c = true;

    private void a(String str, D d, List<l9<D>> list) {
        for (l9<D> l9Var : list) {
            if (l9Var instanceof s9) {
                ((s9) l9Var).g().b(str, (String) d);
            } else {
                l9Var.a(str, (String) d);
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.l9, com.tencent.mapsdk.internal.s9
    public long a() {
        Iterator<l9<D>> it = this.b.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                j2 += next instanceof s9 ? ((s9) next).g().a() : next.a();
            }
        }
        return j2;
    }

    public l9<D> a(int i2) {
        if (i2 >= this.b.size()) {
            return null;
        }
        return this.b.get(i2);
    }

    @Override // com.tencent.mapsdk.internal.l9
    public D a(String str, Class<D> cls) {
        ArrayList arrayList = new ArrayList();
        Iterator<l9<D>> it = this.b.iterator();
        D d = null;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            l9<D> next = it.next();
            if (next != null) {
                d = next instanceof s9 ? ((s9) next).g().b(str, cls) : next.a(str, cls);
                if (d != null && d.a() > 0) {
                    ma.c(la.f16819f, "\u4ece[" + next + "]\u7f13\u5b58\u4e2d\u83b7\u53d6\u6570\u636e\u6210\u529f");
                    break;
                } else if (this.f16921c) {
                    qa.b(la.o, str, (Object) ("back to fill " + next));
                    arrayList.add(next);
                }
            }
        }
        if (d != null && d.a() > 0 && !arrayList.isEmpty()) {
            a(str, (String) d, (List<l9<String>>) arrayList);
        }
        qa.a(la.o, str, "get data length", Integer.valueOf(d == null ? 0 : d.a()));
        qa.f(la.o, str);
        return d;
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void a(j9.a<Long> aVar) {
        long a;
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().a(aVar);
                } else if (next instanceof s9) {
                    s9<D> g2 = ((s9) next).g();
                    if (aVar != null) {
                        a = g2.a();
                        aVar.callback(Long.valueOf(a));
                    }
                } else if (aVar != null) {
                    a = next.a();
                    aVar.callback(Long.valueOf(a));
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void a(String str, j9.a<Boolean> aVar) {
        boolean a;
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().a(str, aVar);
                } else if (next instanceof s9) {
                    s9<D> g2 = ((s9) next).g();
                    if (aVar != null) {
                        a = g2.a(str);
                        aVar.callback(Boolean.valueOf(a));
                    }
                } else if (aVar != null) {
                    a = next.b(str);
                    aVar.callback(Boolean.valueOf(a));
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void a(String str, D d) {
        qa.a(la.o, str, "put to cacheSet");
        qa.a(la.p, str, "put data length", Integer.valueOf(d == null ? 0 : d.a()));
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof s9) {
                    ((s9) next).g().b(str, (String) d);
                } else {
                    next.a(str, (String) d);
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void a(String str, D d, j9.a<Boolean> aVar) {
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().a(str, (String) d, aVar);
                } else if (next instanceof s9) {
                    ((s9) next).g().b(str, (String) d);
                    if (aVar != null) {
                        aVar.callback(Boolean.TRUE);
                    }
                } else {
                    next.a(str, (String) d);
                    if (aVar != null) {
                        aVar.callback(Boolean.TRUE);
                    }
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void a(String str, Class<D> cls, j9.a<D> aVar) {
        D b;
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().a(str, cls, aVar);
                } else if (next instanceof s9) {
                    s9<D> g2 = ((s9) next).g();
                    if (aVar != null) {
                        b = g2.b(str, cls);
                        aVar.callback(b);
                    }
                } else if (aVar != null) {
                    b = next.a(str, cls);
                    aVar.callback(b);
                }
            }
        }
    }

    public void a(boolean z) {
        this.f16921c = z;
    }

    public final void a(l9<D>... l9VarArr) {
        this.b.addAll(Arrays.asList(l9VarArr));
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void b(j9.a<Long> aVar) {
        long e2;
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().b(aVar);
                } else if (next instanceof s9) {
                    s9<D> g2 = ((s9) next).g();
                    if (aVar != null) {
                        e2 = g2.e();
                        aVar.callback(Long.valueOf(e2));
                    }
                } else if (aVar != null) {
                    e2 = next.f();
                    aVar.callback(Long.valueOf(e2));
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.l9
    public boolean b(String str) {
        Iterator<l9<D>> it = this.b.iterator();
        boolean z = true;
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                z = next instanceof s9 ? ((s9) next).g().a(str) : next.b(str);
            }
        }
        return z;
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void c(j9.a<Boolean> aVar) {
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().c(aVar);
                } else if (next instanceof s9) {
                    ((s9) next).g().b();
                    if (aVar != null) {
                        aVar.callback(Boolean.TRUE);
                    }
                } else {
                    next.clear();
                    if (aVar != null) {
                        aVar.callback(Boolean.TRUE);
                    }
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.l9
    public void clear() {
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof s9) {
                    ((s9) next).g().b();
                } else {
                    next.clear();
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.j9
    public j9<D> d() {
        return this;
    }

    @Override // com.tencent.mapsdk.internal.j9
    public void d(j9.a<Long> aVar) {
        long c2;
        Iterator<l9<D>> it = this.b.iterator();
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                if (next instanceof j9) {
                    ((j9) next).d().d(aVar);
                } else if (next instanceof s9) {
                    s9<D> g2 = ((s9) next).g();
                    if (aVar != null) {
                        c2 = g2.c();
                        aVar.callback(Long.valueOf(c2));
                    }
                } else if (aVar != null) {
                    c2 = next.getCount();
                    aVar.callback(Long.valueOf(c2));
                }
            }
        }
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long f() {
        Iterator<l9<D>> it = this.b.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                j2 += next instanceof s9 ? ((s9) next).g().e() : next.f();
            }
        }
        return j2;
    }

    @Override // com.tencent.mapsdk.internal.l9
    public long getCount() {
        Iterator<l9<D>> it = this.b.iterator();
        long j2 = 0;
        while (it.hasNext()) {
            l9<D> next = it.next();
            if (next != null) {
                j2 += next instanceof s9 ? ((s9) next).g().c() : next.getCount();
            }
        }
        return j2;
    }
}
