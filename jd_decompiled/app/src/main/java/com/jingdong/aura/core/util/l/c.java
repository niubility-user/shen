package com.jingdong.aura.core.util.l;

/* loaded from: classes.dex */
public class c {
    public static int a = 7;

    public static b a(String str) {
        return a(str, null);
    }

    private static b a(String str, Class<?> cls) {
        if (cls != null) {
            return new a(cls);
        }
        return new a(str);
    }
}
