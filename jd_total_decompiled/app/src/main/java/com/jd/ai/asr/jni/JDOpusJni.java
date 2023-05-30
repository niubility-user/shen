package com.jd.ai.asr.jni;

/* loaded from: classes12.dex */
public class JDOpusJni {
    static {
        System.loadLibrary("jdopus");
    }

    public static native synchronized int Initial(int i2, int i3);

    public static native synchronized void destroy();

    public static native synchronized byte[] process(byte[] bArr, int i2, boolean z);
}
