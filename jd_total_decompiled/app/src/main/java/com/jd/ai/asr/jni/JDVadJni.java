package com.jd.ai.asr.jni;

/* loaded from: classes12.dex */
public class JDVadJni {
    static {
        System.loadLibrary("jdvad");
    }

    public static native synchronized int vadInit(String str, float f2, float f3);

    public static native synchronized int vadProcess(byte[] bArr, int i2);

    public static native synchronized void vadRelease();

    public static native synchronized void vadReset();

    public static native synchronized int vadSampleRate();
}
