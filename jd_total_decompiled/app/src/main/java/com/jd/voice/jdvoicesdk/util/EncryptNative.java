package com.jd.voice.jdvoicesdk.util;

/* loaded from: classes18.dex */
public class EncryptNative {
    static {
        try {
            System.loadLibrary("jdvoice");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static native String decode(String str);

    public static native String getKey();

    public static native String md5(String str);
}
