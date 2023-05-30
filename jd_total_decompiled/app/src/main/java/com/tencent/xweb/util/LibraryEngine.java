package com.tencent.xweb.util;

/* loaded from: classes9.dex */
public class LibraryEngine {
    private static Class<? extends LibraryLoader> libLoader;

    /* loaded from: classes9.dex */
    public interface LibraryLoader {
        void loadLibrary(String str);
    }

    public static void loadLibrary(String str) {
        Class<? extends LibraryLoader> cls = libLoader;
        if (cls != null) {
            try {
                cls.newInstance().loadLibrary(str);
                return;
            } catch (Exception unused) {
                systemLoad(str);
                return;
            }
        }
        systemLoad(str);
    }

    public static void registerLibraryLoader(Class<? extends LibraryLoader> cls) {
        libLoader = cls;
    }

    private static void systemLoad(String str) {
        try {
            System.loadLibrary(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
