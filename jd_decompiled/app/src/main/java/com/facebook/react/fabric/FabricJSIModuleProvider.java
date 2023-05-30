package com.facebook.react.fabric;

import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.JavaScriptContextHolder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.jsi.Binding;
import com.facebook.react.fabric.jsi.ComponentFactoryDelegate;
import com.facebook.react.fabric.jsi.EventBeatManager;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.systrace.Systrace;

/* loaded from: classes12.dex */
public class FabricJSIModuleProvider implements JSIModuleProvider<UIManager> {
    private final ComponentFactoryDelegate mComponentFactoryDelegate;
    private final ReactNativeConfig mConfig;
    private final JavaScriptContextHolder mJSContext;
    private final ReactApplicationContext mReactApplicationContext;

    public FabricJSIModuleProvider(ReactApplicationContext reactApplicationContext, JavaScriptContextHolder javaScriptContextHolder, ComponentFactoryDelegate componentFactoryDelegate, ReactNativeConfig reactNativeConfig) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mJSContext = javaScriptContextHolder;
        this.mComponentFactoryDelegate = componentFactoryDelegate;
        this.mConfig = reactNativeConfig;
    }

    private FabricUIManager createUIManager(EventBeatManager eventBeatManager) {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.createUIManager");
        UIManagerModule uIManagerModule = (UIManagerModule) this.mReactApplicationContext.getNativeModule(UIManagerModule.class);
        FabricUIManager fabricUIManager = new FabricUIManager(this.mReactApplicationContext, uIManagerModule.getViewManagerRegistry_DO_NOT_USE(), uIManagerModule.getEventDispatcher(), eventBeatManager);
        Systrace.endSection(0L);
        return fabricUIManager;
    }

    private static void loadClasses() {
    }

    @Override // com.facebook.react.bridge.JSIModuleProvider
    public UIManager get() {
        EventBeatManager eventBeatManager = new EventBeatManager(this.mJSContext, this.mReactApplicationContext);
        FabricUIManager createUIManager = createUIManager(eventBeatManager);
        Systrace.beginSection(0L, "FabricJSIModuleProvider.registerBinding");
        Binding binding = new Binding();
        loadClasses();
        binding.register(this.mJSContext, createUIManager, eventBeatManager, this.mReactApplicationContext.getCatalystInstance().getReactQueueConfiguration().getJSQueueThread(), this.mComponentFactoryDelegate, this.mConfig);
        Systrace.endSection(0L);
        return createUIManager;
    }
}
