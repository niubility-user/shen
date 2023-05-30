package com.meizu.cloud.pushsdk.f.g;

/* loaded from: classes14.dex */
public final class d {
    public static <T> T a(T t) {
        t.getClass();
        return t;
    }

    public static <T> T b(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void c(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }
}
