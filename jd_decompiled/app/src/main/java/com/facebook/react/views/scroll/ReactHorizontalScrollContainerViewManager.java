package com.facebook.react.views.scroll;

import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

@ReactModule(name = ReactHorizontalScrollContainerViewManager.REACT_CLASS)
/* loaded from: classes12.dex */
public class ReactHorizontalScrollContainerViewManager extends ViewGroupManager<ReactHorizontalScrollContainerView> {
    public static final String REACT_CLASS = "AndroidHorizontalScrollContentView";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ReactHorizontalScrollContainerView createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactHorizontalScrollContainerView(themedReactContext);
    }
}
