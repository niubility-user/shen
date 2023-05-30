package com.tencent.mapsdk.internal;

import java.util.HashMap;

/* loaded from: classes9.dex */
public class o {
    private static HashMap<String, String> a = new HashMap<>();

    private o() {
    }

    public static String a(String str) {
        return a.get(str);
    }

    public static <T extends p> void a(Class<T> cls) {
        try {
            T newInstance = cls.newInstance();
            if (newInstance != null) {
                String className = newInstance.className();
                if ("".equals(className)) {
                    className = cls.getName();
                }
                a.put(className, cls.getName());
            }
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }
}
