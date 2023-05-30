package com.jd.msec;

/* loaded from: classes17.dex */
public final class HashUtils {
    static {
        System.loadLibrary("com.jd.msec.hashutil");
    }

    public static native String getHash(String str);
}
