package com.tencent.smtt.sdk;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

@Deprecated
/* loaded from: classes9.dex */
public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        u a = u.a();
        if (a == null || !a.b()) {
            Object a2 = com.tencent.smtt.utils.j.a("android.webkit.CacheManager", "cacheDisabled");
            if (a2 == null) {
                return false;
            }
            return ((Boolean) a2).booleanValue();
        }
        return ((Boolean) a.c().c()).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        u a = u.a();
        if (a == null || !a.b()) {
            return null;
        }
        return a.c().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        u a = u.a();
        if (a == null || !a.b()) {
            try {
                return com.tencent.smtt.utils.j.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", (Class<?>[]) new Class[]{String.class, Map.class}, str, map);
            } catch (Exception unused) {
                return null;
            }
        }
        return a.c().g();
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        u a = u.a();
        return (File) ((a == null || !a.b()) ? com.tencent.smtt.utils.j.a("android.webkit.CacheManager", "getCacheFileBaseDir") : a.c().g());
    }
}
