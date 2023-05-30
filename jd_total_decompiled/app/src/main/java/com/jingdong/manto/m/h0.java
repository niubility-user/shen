package com.jingdong.manto.m;

import android.text.TextUtils;
import android.view.View;
import com.jingdong.manto.jsapi.openmodule.AbstractMantoViewManager;
import com.jingdong.manto.jsapi.openmodule.IMantoBaseModule;
import com.jingdong.manto.jsapi.openmodule.OpenJsApiManager;
import com.jingdong.manto.jsapi.refact.media.JsApiCompressImage;
import com.jingdong.manto.utils.MantoStringUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes15.dex */
public class h0 {
    private static Map<String, c0> a;
    private static Map<String, c0> b;

    /* renamed from: c  reason: collision with root package name */
    private static Map<String, c0> f13367c;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class a extends com.jingdong.manto.jsapi.base.a {
        a(com.jingdong.manto.m.d1.i iVar) {
            super(iVar);
        }

        @Override // com.jingdong.manto.jsapi.base.a
        public View a(com.jingdong.manto.q.n nVar, JSONObject jSONObject) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class b extends com.jingdong.manto.jsapi.base.c {
        b(com.jingdong.manto.m.d1.i iVar) {
            super(iVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes15.dex */
    public class c extends com.jingdong.manto.jsapi.base.b {
        c(com.jingdong.manto.m.d1.i iVar) {
            super(iVar);
        }
    }

    public static Map<String, c0> a() {
        Map<String, c0> map = b;
        if (map != null) {
            return map;
        }
        b = new HashMap();
        a(new j0(), b);
        a(new f0(), b);
        a(new g0(), b);
        a(new com.jingdong.manto.m.z0.a(), b);
        a(new com.jingdong.manto.m.z0.c(), b);
        a(new com.jingdong.manto.m.z0.d(), b);
        a(new com.jingdong.manto.m.z0.g(), b);
        a(new com.jingdong.manto.m.z0.h(), b);
        a(new com.jingdong.manto.m.z0.i(), b);
        a(new com.jingdong.manto.m.c(), b);
        a(new k(), b);
        a(new com.jingdong.manto.m.b(), b);
        a(new com.jingdong.manto.jsapi.camera.b(), b);
        a(new com.jingdong.manto.jsapi.camera.e(), b);
        a(new com.jingdong.manto.jsapi.camera.d(), b);
        a(new com.jingdong.manto.m.u0.i(), b);
        a(new com.jingdong.manto.m.u0.l(), b);
        a(new com.jingdong.manto.m.u0.k(), b);
        a(new com.jingdong.manto.m.e1.c(), b);
        a(new com.jingdong.manto.m.w0.a(), b);
        if (!OpenJsApiManager.getPApiMap().isEmpty()) {
            a(OpenJsApiManager.getPApiMap(), b, false);
        }
        return b;
    }

    private static void a(c0 c0Var, Map<String, c0> map) {
        if (MantoStringUtils.isEmpty(c0Var.getJsApiName())) {
            return;
        }
        map.put(c0Var.getJsApiName(), c0Var);
    }

    private static void a(Map<String, IMantoBaseModule> map, Map<String, c0> map2, boolean z) {
        c0 aVar;
        if (map == null || map2 == null) {
            return;
        }
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            IMantoBaseModule iMantoBaseModule = map.get(it.next());
            if (iMantoBaseModule != null) {
                if (iMantoBaseModule instanceof AbstractMantoViewManager) {
                    AbstractMantoViewManager abstractMantoViewManager = (AbstractMantoViewManager) iMantoBaseModule;
                    map2.put("insert" + abstractMantoViewManager.getViewName(), new a(new com.jingdong.manto.m.d1.i("insert" + abstractMantoViewManager.getViewName(), -1, abstractMantoViewManager)));
                    map2.put("update" + abstractMantoViewManager.getViewName(), new b(new com.jingdong.manto.m.d1.i("update" + abstractMantoViewManager.getViewName(), -1, abstractMantoViewManager)));
                    map2.put("remove" + abstractMantoViewManager.getViewName(), new c(new com.jingdong.manto.m.d1.i("remove" + abstractMantoViewManager.getViewName(), -1, abstractMantoViewManager)));
                }
                HashMap<String, com.jingdong.manto.m.d1.i> nativeMethod = iMantoBaseModule.getNativeMethod();
                Iterator<String> it2 = nativeMethod.keySet().iterator();
                while (it2.hasNext()) {
                    com.jingdong.manto.m.d1.i iVar = nativeMethod.get(it2.next());
                    if (iVar == null || TextUtils.isEmpty(iVar.a())) {
                        return;
                    }
                    if (map2.containsKey(iVar.a())) {
                        throw new RuntimeException("method name should be different:" + iVar.a());
                    }
                    if (iVar.b() == 0) {
                        aVar = new com.jingdong.manto.m.d1.a(iVar);
                    } else if (iVar.b() == 1) {
                        aVar = new com.jingdong.manto.m.d1.e(iVar);
                    } else if (iVar.b() == 2) {
                        aVar = new com.jingdong.manto.m.d1.h(iVar);
                    } else if (iVar.b() == 4) {
                        aVar = new com.jingdong.manto.m.d1.b(iVar);
                    } else if (iVar.b() == 3) {
                        aVar = new com.jingdong.manto.m.d1.f(iVar);
                    } else if (iVar.b() == 5) {
                        aVar = new com.jingdong.manto.m.d1.g(iVar);
                    }
                    aVar.webAPI = z;
                    map2.put(iVar.a(), aVar);
                }
            }
        }
    }

    public static Map<String, c0> b() {
        Map<String, c0> map = a;
        if (map != null) {
            return map;
        }
        a = new HashMap();
        a(new j0(), a);
        a(new f0(), a);
        a(new i0(), a);
        a(new r(), a);
        a(new h(), a);
        a(new com.jingdong.manto.m.c1.d(), a);
        a(new com.jingdong.manto.m.c1.e(), a);
        a(new com.jingdong.manto.m.c1.i(), a);
        a(new com.jingdong.manto.m.n0.l(), a);
        a(new com.jingdong.manto.m.n0.h(), a);
        a(new p(), a);
        a(new com.jingdong.manto.m.m0.a(), a);
        a(new g(), a);
        a(new i(), a);
        a(new com.jingdong.manto.m.n0.j(), a);
        a(new com.jingdong.manto.m.n0.g(), a);
        a(new com.jingdong.manto.m.n1.a(), a);
        a(new com.jingdong.manto.m.n1.f(), a);
        a(new com.jingdong.manto.m.n1.b(), a);
        a(new com.jingdong.manto.m.n1.e(), a);
        a(new com.jingdong.manto.m.n1.h(), a);
        a(new com.jingdong.manto.m.n1.j(), a);
        a(new com.jingdong.manto.m.n1.k(), a);
        a(new com.jingdong.manto.m.n1.n(), a);
        a(new com.jingdong.manto.m.n1.m(), a);
        a(new com.jingdong.manto.m.n1.d(), a);
        a(new com.jingdong.manto.m.x0.a(), a);
        a(new m(), a);
        a(new com.jingdong.manto.m.n0.i(), a);
        a(new com.jingdong.manto.m.n0.d(), a);
        a(new com.jingdong.manto.m.n0.f(), a);
        a(new s(), a);
        a(new com.jingdong.manto.m.b1.b(), a);
        a(new a0(), a);
        a(new com.jingdong.manto.m.c1.h(), a);
        a(new y(), a);
        a(new z(), a);
        a(new com.jingdong.manto.m.o1.b(), a);
        a(new com.jingdong.manto.m.o1.d(), a);
        a(new com.jingdong.manto.m.o1.c(), a);
        a(new com.jingdong.manto.m.o1.e(), a);
        a(new com.jingdong.manto.m.o1.a(), a);
        a(new com.jingdong.manto.m.b1.d(), a);
        a(new com.jingdong.manto.m.b1.c(), a);
        a(new com.jingdong.manto.m.b1.b(), a);
        a(new com.jingdong.manto.m.b1.a(), a);
        a(new x(), a);
        a(new u(), a);
        a(new com.jingdong.manto.m.z0.a(), a);
        a(new com.jingdong.manto.m.z0.f(), a);
        a(new q(), a);
        a(new j(), a);
        a(new n(), a);
        a(new com.jingdong.manto.m.c1.c(), a);
        a(new com.jingdong.manto.m.c1.g(), a);
        a(new com.jingdong.manto.m.c1.f(), a);
        a(new com.jingdong.manto.m.c1.j(), a);
        a(new com.jingdong.manto.m.m1.b(), a);
        a(new com.jingdong.manto.m.m1.a(), a);
        a(new t(), a);
        a(new com.jingdong.manto.m.s1.a(), a);
        a(new com.jingdong.manto.jsapi.camera.c(), a);
        a(new JsApiCompressImage(), a);
        a(new com.jingdong.manto.m.x0.b(), a);
        a(new e(), a);
        a(new com.jingdong.manto.m.n0.k(), a);
        a(new com.jingdong.manto.m.q1.a(), a);
        a(new com.jingdong.manto.m.q1.b(), a);
        a(new com.jingdong.manto.m.q1.e(), a);
        a(new com.jingdong.manto.m.q1.d(), a);
        a(new com.jingdong.manto.m.q1.c(), a);
        a(new com.jingdong.manto.m.r0.b(), a);
        a(new com.jingdong.manto.m.r0.a(), a);
        a(new com.jingdong.manto.m.u0.h(), a);
        a(new com.jingdong.manto.m.u0.f(), a);
        a(new com.jingdong.manto.m.u0.e(), a);
        a(new com.jingdong.manto.m.u0.g(), a);
        a(new com.jingdong.manto.m.u0.j(), a);
        a(new com.jingdong.manto.m.s0.a(), a);
        a(new com.jingdong.manto.m.l1.b(), a);
        a(new com.jingdong.manto.m.l1.e(), a);
        a(new com.jingdong.manto.m.l1.d(), a);
        a(new com.jingdong.manto.m.l1.f(), a);
        a(new com.jingdong.manto.m.l1.c(), a);
        a(new com.jingdong.manto.m.t0.c.b(), a);
        a(new com.jingdong.manto.m.t0.c.l(), a);
        a(new com.jingdong.manto.m.t0.c.o(), a);
        a(new com.jingdong.manto.m.t0.c.g(), a);
        a(new com.jingdong.manto.m.t0.c.h(), a);
        a(new com.jingdong.manto.m.t0.c.i(), a);
        a(new com.jingdong.manto.m.t0.c.j(), a);
        a(new com.jingdong.manto.m.t0.c.p(), a);
        a(new com.jingdong.manto.m.t0.c.c(), a);
        a(new com.jingdong.manto.m.t0.c.a(), a);
        a(new com.jingdong.manto.m.t0.c.q(), a);
        a(new com.jingdong.manto.m.t0.c.m(), a);
        a(new com.jingdong.manto.m.t0.c.n(), a);
        a(new com.jingdong.manto.m.t0.c.k(), a);
        a(new com.jingdong.manto.m.t0.c.f(), a);
        a(new com.jingdong.manto.m.t0.c.d(), a);
        a(new com.jingdong.manto.m.t0.c.e(), a);
        a(new com.jingdong.manto.m.p0.e.d.a(), a);
        a(new com.jingdong.manto.m.p0.e.d.b(), a);
        a(new com.jingdong.manto.m.p0.e.d.c(), a);
        a(new com.jingdong.manto.m.p0.e.d.d(), a);
        a(new com.jingdong.manto.m.p0.e.d.e(), a);
        a(new com.jingdong.manto.m.p0.e.d.f(), a);
        a(new com.jingdong.manto.m.p0.d.e(), a);
        a(new com.jingdong.manto.m.p0.d.d(), a);
        a(new com.jingdong.manto.m.p0.d.c(), a);
        a(new com.jingdong.manto.m.p0.f.e.a(), a);
        a(new l(), a);
        a(new o(), a);
        a(new w(), a);
        a(new com.jingdong.manto.m.e1.c(), a);
        a(new com.jingdong.manto.m.h1.a(), a);
        a(new v(), a);
        a(new f(), a);
        a(new com.jingdong.manto.m.e1.b(), a);
        a(new com.jingdong.manto.m.e1.a(), a);
        a(new com.jingdong.manto.m.w0.a(), a);
        a(new com.jingdong.manto.m.n0.e(), a);
        a(new com.jingdong.manto.m.r1.a(), a);
        if (!OpenJsApiManager.getSApiMap().isEmpty()) {
            a(OpenJsApiManager.getSApiMap(), a, false);
        }
        return a;
    }

    public static Map<String, c0> c() {
        Map<String, c0> map = f13367c;
        if (map != null) {
            return map;
        }
        f13367c = new HashMap();
        if (!OpenJsApiManager.getWApiMap().isEmpty()) {
            a(OpenJsApiManager.getWApiMap(), f13367c, true);
        }
        return f13367c;
    }
}
