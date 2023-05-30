package com.jingdong.app.mall.home.p;

import com.jingdong.app.mall.home.o.a.f;
import com.jingdong.app.mall.home.p.b.b;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class a {
    private static final String a = "a";
    private static final Map<String, b> b = new HashMap(256);

    /* renamed from: c  reason: collision with root package name */
    private static final Map<String, com.jingdong.app.mall.home.p.b.e.a> f10502c = new HashMap(512);

    static {
        for (com.jingdong.app.mall.home.p.b.a aVar : com.jingdong.app.mall.home.p.b.a.values()) {
            aVar.initNodeMap(b);
        }
    }

    public static b a(String str) {
        b bVar = b.get(str);
        return bVar == null ? com.jingdong.app.mall.home.p.b.a.EMPTY : bVar;
    }

    public static com.jingdong.app.mall.home.p.b.e.a b(String str) {
        return f10502c.get(str);
    }

    public static void c(String str, b bVar) {
        Map<String, b> map = b;
        if (map.containsKey(str)) {
            f.r0(a, "Error ", str, " is already register, please change strType on", str);
        } else {
            map.put(str, bVar);
        }
    }
}
