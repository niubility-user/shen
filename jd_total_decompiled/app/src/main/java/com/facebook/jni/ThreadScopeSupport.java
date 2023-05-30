package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
/* loaded from: classes.dex */
public class ThreadScopeSupport {
    static {
        SoLoader.loadLibrary("fb");
    }

    @DoNotStrip
    private static void runStdFunction(long j2) {
        runStdFunctionImpl(j2);
    }

    private static native void runStdFunctionImpl(long j2);
}
