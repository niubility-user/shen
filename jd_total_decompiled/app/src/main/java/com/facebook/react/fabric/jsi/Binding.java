package com.facebook.react.fabric.jsi;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.ReactNativeConfig;
import com.facebook.react.uimanager.PixelUtil;

@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
/* loaded from: classes12.dex */
public class Binding {
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    private static native HybridData initHybrid();

    private native void installFabricUIManager(long j2, Object obj, EventBeatManager eventBeatManager, MessageQueueThread messageQueueThread, ComponentFactoryDelegate componentFactoryDelegate, Object obj2);

    private native void uninstallFabricUIManager();

    public void register(JavaScriptContextHolder javaScriptContextHolder, FabricUIManager fabricUIManager, EventBeatManager eventBeatManager, MessageQueueThread messageQueueThread, ComponentFactoryDelegate componentFactoryDelegate, ReactNativeConfig reactNativeConfig) {
        fabricUIManager.setBinding(this);
        installFabricUIManager(javaScriptContextHolder.get(), fabricUIManager, eventBeatManager, messageQueueThread, componentFactoryDelegate, reactNativeConfig);
        setPixelDensity(PixelUtil.getDisplayMetricDensity());
    }

    public native void renderTemplateToSurface(int i2, String str);

    public native void setConstraints(int i2, float f2, float f3, float f4, float f5);

    public native void setPixelDensity(float f2);

    public native void startSurface(int i2, NativeMap nativeMap);

    public native void stopSurface(int i2);

    public void unregister() {
        uninstallFabricUIManager();
    }
}
