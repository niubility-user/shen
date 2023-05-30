package com.jd.libs.xwidget;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes16.dex */
public class a {
    public static final Map<String, Class<? extends b>> a = new HashMap();

    public static Class<? extends b> a(String str) {
        return a.get(str);
    }

    public static void b(String str, Class<? extends b> cls) {
        a.put(str, cls);
    }
}
