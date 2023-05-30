package com.jingdong.aura.a.c;

import android.app.Application;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class g {
    static Map<String, Application> a;
    private static Map<String, j> b;

    static {
        com.jingdong.aura.core.util.l.c.a("DelegateComponent");
        b = new ConcurrentHashMap();
        a = new HashMap();
    }

    public static j a(String str) {
        if (str == null) {
            return null;
        }
        return b.get(str);
    }

    public static String b(String str) {
        for (Map.Entry<String, j> entry : b.entrySet()) {
            if (entry.getValue().f12136e.contains(str)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static void c(String str) {
        if (str == null) {
            return;
        }
        b.remove(str);
    }

    public static void a(String str, j jVar) {
        if (str == null) {
            return;
        }
        b.put(str, jVar);
    }
}
