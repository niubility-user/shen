package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshFrameLayoutManager extends ViewGroupManager<JDReactPullToRefreshBasicFrameLayout> {
    protected static final String REACT_CLASS = "RCTJDRefreshControl";

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactPullToRefreshBasicFrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactPullToRefreshBasicFrameLayout(themedReactContext);
    }
}
