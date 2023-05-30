package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.SoLoader;

@DoNotStrip
/* loaded from: classes.dex */
public class Countable {
    @DoNotStrip
    private long mInstance = 0;

    static {
        SoLoader.loadLibrary("fb");
    }

    public native void dispose();

    protected void finalize() throws Throwable {
        dispose();
        super.finalize();
    }
}
