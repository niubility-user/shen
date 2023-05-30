package com.facebook.react.uimanager;

import android.view.ViewGroup;
import com.facebook.react.uimanager.common.SizeMonitoringFrameLayout;

/* loaded from: classes12.dex */
public class RootViewManager extends ViewGroupManager<ViewGroup> {
    public static final String REACT_CLASS = "RootView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public ViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new SizeMonitoringFrameLayout(themedReactContext);
    }
}
