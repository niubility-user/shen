package com.jd.ai.asr.jni;

/* loaded from: classes12.dex */
public class JDKwsJni {
    static {
        System.loadLibrary("jdkws");
    }

    public static native synchronized int kwsDecoder(byte[] bArr, int i2, boolean z);

    public static native synchronized String kwsForceResult();

    public static native synchronized int kwsInit(String str, String str2, String str3, String str4);

    public static native synchronized int kwsParams(int i2, int i3);

    public static native synchronized int kwsRelease();

    public static native synchronized int kwsReset();
}
