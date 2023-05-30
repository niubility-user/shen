package com.jingdong.manto.m.u1;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes15.dex */
public class f {
    private static Map<String, e> a;

    public static e a(String str) {
        Map<String, e> map = a;
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public static void a(String str, e eVar) {
        if (a == null) {
            a = new HashMap();
        }
        a.put(str, eVar);
    }

    public static void b(String str) {
        Map<String, e> map = a;
        if (map == null) {
            return;
        }
        map.remove(str);
    }
}
