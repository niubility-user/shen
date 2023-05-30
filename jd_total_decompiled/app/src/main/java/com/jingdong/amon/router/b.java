package com.jingdong.amon.router;

import android.net.Uri;
import android.text.TextUtils;
import com.jingdong.amon.router.module.RouteMeta;
import com.jingdong.amon.router.template.IInterceptor;
import com.jingdong.common.XView2.common.XView2Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class b {
    static Map<String, Map<String, RouteMeta>> a = new HashMap();
    static Map<Class<?>, List<RouteMeta>> b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    static List<IInterceptor> f7583c = new ArrayList();
    static List<IInterceptor> d = new ArrayList();

    public static RouteMeta a(Uri uri) {
        String a2 = c.a(uri);
        if ("://".equals(a2)) {
            a2 = c.a(a.a, a.b);
        }
        Map<String, RouteMeta> map = a.get(a2);
        if (map == null) {
            return null;
        }
        return map.get(uri.getPath());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        try {
            Class.forName("com.jingdong.amon.router.generate._JdRouterInit").getMethod(XView2Constants.XVIEW2_ACTION_INIT, new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e2) {
            throw new RuntimeException("init jdrouter failed!", e2);
        }
    }

    public static void a(RouteMeta routeMeta) {
        if (TextUtils.isEmpty(routeMeta.scheme)) {
            routeMeta.scheme = a.a;
        }
        if (TextUtils.isEmpty(routeMeta.host)) {
            routeMeta.host = a.b;
        }
        String a2 = c.a(routeMeta.scheme, routeMeta.host);
        Map<String, RouteMeta> map = a.get(a2);
        if (map == null) {
            map = new HashMap<>();
            a.put(a2, map);
        }
        map.put(routeMeta.path, routeMeta);
    }

    public static List<IInterceptor> b() {
        return f7583c;
    }

    public static void b(RouteMeta routeMeta) {
        for (Class<?> cls : routeMeta.interfaceClasses) {
            if (b.containsKey(cls)) {
                b.get(cls).add(routeMeta);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(routeMeta);
                b.put(cls, arrayList);
            }
        }
        a(routeMeta);
    }
}
