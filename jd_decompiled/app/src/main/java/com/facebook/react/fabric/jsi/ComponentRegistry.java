package com.facebook.react.fabric.jsi;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
/* loaded from: classes12.dex */
public class ComponentRegistry {
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    @DoNotStrip
    private static native HybridData initHybrid();
}
