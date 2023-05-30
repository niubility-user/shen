package com.jd.dynamic.engine.jni;

/* loaded from: classes13.dex */
public class JavaScriptRuntime {
    public static long a(boolean z, boolean z2) {
        return createJSContextNative(z, z2);
    }

    public static void b(long j2) {
        destroyJSContextNative(j2);
    }

    private static native long createJSContextNative(boolean z, boolean z2);

    public static native long[] createJSContextNativeWithActivity(long j2);

    public static native void destroyJSContextGroup(long j2);

    private static native void destroyJSContextNative(long j2);

    public static native long evalJSResult(long j2, String str);

    public static native long evalJSResultNPT(long j2, String str);

    public static native void setJSProperty(long j2, String str, String str2);
}
