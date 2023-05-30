package com.jd.ai.asr.jni;

/* loaded from: classes12.dex */
public class JDWakeupJni {
    static {
        System.loadLibrary("jdwakeup");
    }

    public static native synchronized int Detect_wakeup(byte[] bArr, int i2, double d, int i3);

    public static native synchronized int destory_wakeup();

    public static native synchronized int wakeupInit(String str);
}
