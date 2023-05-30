package com.huawei.agconnect.core.c;

import android.content.Context;
import g.e.a.e;
import g.e.a.g;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes12.dex */
public class b extends g.e.a.d {

    /* renamed from: c */
    private static List<com.huawei.agconnect.core.a> f1141c;
    private static final Object d = new Object();

    /* renamed from: e */
    private static final Map<String, g.e.a.d> f1142e = new HashMap();

    /* renamed from: f */
    private static String f1143f;
    private final e a;
    private final d b;

    /* loaded from: classes12.dex */
    public static class a implements g.a {
        a() {
        }

        @Override // g.e.a.g.a
        public String a(e eVar) {
            String str;
            if (eVar.a().equals(g.e.a.b.f19448c)) {
                str = "/agcgw_all/CN";
            } else if (eVar.a().equals(g.e.a.b.f19449e)) {
                str = "/agcgw_all/RU";
            } else if (eVar.a().equals(g.e.a.b.d)) {
                str = "/agcgw_all/DE";
            } else if (!eVar.a().equals(g.e.a.b.f19450f)) {
                return null;
            } else {
                str = "/agcgw_all/SG";
            }
            return eVar.getString(str);
        }
    }

    /* renamed from: com.huawei.agconnect.core.c.b$b */
    /* loaded from: classes12.dex */
    public static class C0058b implements g.a {
        C0058b() {
        }

        @Override // g.e.a.g.a
        public String a(e eVar) {
            String str;
            if (eVar.a().equals(g.e.a.b.f19448c)) {
                str = "/agcgw_all/CN_back";
            } else if (eVar.a().equals(g.e.a.b.f19449e)) {
                str = "/agcgw_all/RU_back";
            } else if (eVar.a().equals(g.e.a.b.d)) {
                str = "/agcgw_all/DE_back";
            } else if (!eVar.a().equals(g.e.a.b.f19450f)) {
                return null;
            } else {
                str = "/agcgw_all/SG_back";
            }
            return eVar.getString(str);
        }
    }

    public b(e eVar) {
        this.a = eVar;
        List<com.huawei.agconnect.core.a> list = f1141c;
        new d(f1141c, eVar.getContext());
        d dVar = new d(null, eVar.getContext());
        this.b = dVar;
        if (eVar instanceof g.e.a.h.c.d) {
            dVar.c(((g.e.a.h.c.d) eVar).c(), eVar.getContext());
        }
    }

    public static g.e.a.d f() {
        String str = f1143f;
        if (str == null) {
            str = "DEFAULT_INSTANCE";
        }
        return i(str);
    }

    public static g.e.a.d g(e eVar) {
        return h(eVar, false);
    }

    private static g.e.a.d h(e eVar, boolean z) {
        g.e.a.d dVar;
        synchronized (d) {
            Map<String, g.e.a.d> map = f1142e;
            dVar = map.get(eVar.getIdentifier());
            if (dVar == null || z) {
                dVar = new b(eVar);
                map.put(eVar.getIdentifier(), dVar);
            }
        }
        return dVar;
    }

    public static g.e.a.d i(String str) {
        g.e.a.d dVar;
        synchronized (d) {
            dVar = f1142e.get(str);
            if (dVar == null && !"DEFAULT_INSTANCE".equals(str)) {
                String str2 = "not find instance for : " + str;
            }
        }
        return dVar;
    }

    public static synchronized void j(Context context) {
        synchronized (b.class) {
            if (f1142e.size() > 0) {
                return;
            }
            k(context, g.e.a.h.a.b(context));
        }
    }

    private static synchronized void k(Context context, e eVar) {
        synchronized (b.class) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
            l();
            g.e.a.h.c.c.a(context);
            if (f1141c == null) {
                f1141c = new c(context).b();
            }
            h(eVar, true);
            f1143f = eVar.getIdentifier();
            String str = "AGC SDK initialize end, default route:" + eVar.a().a();
            com.huawei.agconnect.core.c.a.a();
        }
    }

    private static void l() {
        g.b("/agcgw/url", new a());
        g.b("/agcgw/backurl", new C0058b());
    }

    @Override // g.e.a.d
    public Context b() {
        return this.a.getContext();
    }

    @Override // g.e.a.d
    public e d() {
        return this.a;
    }
}
