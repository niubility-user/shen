package com.tencent.mapsdk.internal;

import com.tencent.mapsdk.internal.i2;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public abstract class d2 implements f2 {

    /* renamed from: e  reason: collision with root package name */
    private String f16387e;

    /* renamed from: g  reason: collision with root package name */
    private h2 f16389g;

    /* renamed from: f  reason: collision with root package name */
    private final Set<a> f16388f = new LinkedHashSet();

    /* renamed from: h  reason: collision with root package name */
    private final o2 f16390h = new o2();

    /* loaded from: classes9.dex */
    public static class a<S extends j2> {
        public String a;
        public Class<S> b;

        /* renamed from: c  reason: collision with root package name */
        public Method f16391c;
        public Map<String, String> d;

        /* renamed from: e  reason: collision with root package name */
        public S f16392e;

        public a(String str, Class cls, Method method) {
            this.a = str;
            this.b = cls;
            this.f16391c = method;
        }
    }

    private a b(String str) {
        for (a aVar : this.f16388f) {
            if (aVar.a.equals(str)) {
                return aVar;
            }
        }
        return null;
    }

    private a c(Class cls) {
        for (a aVar : this.f16388f) {
            if (aVar.b.equals(cls)) {
                return aVar;
            }
        }
        return null;
    }

    private <S extends j2> S e(Class<S> cls) {
        for (a aVar : this.f16388f) {
            if (aVar.b == cls) {
                Method method = aVar.f16391c;
                if (method != null) {
                    try {
                        method.setAccessible(true);
                        Object invoke = method.invoke(this, aVar.b);
                        if (invoke == null || invoke.getClass() != cls) {
                            return null;
                        }
                        S s = (S) invoke;
                        s.a(aVar.d);
                        aVar.f16392e = s;
                        return s;
                    } catch (IllegalAccessException e2) {
                        e2.printStackTrace();
                        return null;
                    } catch (InvocationTargetException e3) {
                        e3.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.i2
    public i2.a a(String str) {
        return c(str);
    }

    public void a(h2 h2Var) {
        if (this.f16389g == h2Var || h2Var == null) {
            return;
        }
        this.f16389g = h2Var;
        List<Class<? extends p>> b = h2Var.b();
        if (b != null) {
            Iterator<Class<? extends p>> it = b.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
        Map<String, Class<? extends i2.a>> a2 = this.f16389g.a();
        if (a2 != null) {
            this.f16388f.clear();
            for (Map.Entry<String, Class<? extends i2.a>> entry : a2.entrySet()) {
                a(entry.getKey(), entry.getValue());
            }
        }
        Map<String, String> d = this.f16389g.d();
        if (d == null || d.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry2 : d.entrySet()) {
            a(entry2.getKey(), entry2.getValue());
        }
    }

    @Override // com.tencent.mapsdk.internal.i2
    public <T extends p> void a(Class<T> cls) {
        o.a(cls);
    }

    @Override // com.tencent.mapsdk.internal.i2
    public <T extends i2.a> void a(String str, Class<T> cls) {
        try {
            this.f16388f.add(new a(str, cls, d2.class.getDeclaredMethod("b", Class.class)));
        } catch (NoSuchMethodException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public void a(String str, String str2) {
        for (a aVar : this.f16388f) {
            if (aVar.d == null) {
                aVar.d = new HashMap();
            }
            aVar.d.put(str, str2);
            S s = aVar.f16392e;
            if (s != 0) {
                s.a(aVar.d);
            }
        }
    }

    public abstract <S extends j2> S b(Class<S> cls);

    @Override // com.tencent.mapsdk.internal.f2
    public String b() {
        return this.f16387e;
    }

    @Override // com.tencent.mapsdk.internal.i2
    public h2 c() {
        return this.f16389g;
    }

    public <S extends j2> S c(String str) {
        a b = b(str);
        if (b != null) {
            S s = b.f16392e;
            return s != null ? s : (S) e(b.b);
        }
        return null;
    }

    public <S extends j2> S d(Class<S> cls) {
        a c2 = c(cls);
        if (c2 != null) {
            S s = c2.f16392e;
            return s != null ? s : (S) e(c2.b);
        }
        return null;
    }

    @Override // com.tencent.mapsdk.internal.g2
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public o2 a() {
        return this.f16390h;
    }

    public void d(String str) {
        this.f16387e = str;
    }
}
