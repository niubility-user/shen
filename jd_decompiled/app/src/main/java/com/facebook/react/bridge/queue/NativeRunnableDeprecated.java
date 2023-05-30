package com.facebook.react.bridge.queue;

import com.facebook.jni.Countable;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: classes.dex */
public class NativeRunnableDeprecated extends Countable implements Runnable {
    @DoNotStrip
    private NativeRunnableDeprecated() {
    }

    @Override // java.lang.Runnable
    public native void run();
}
