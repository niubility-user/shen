package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.view.View;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.jingdong.common.widget.LoadingHead;
import com.jingdong.sdk.oklog.OKLog;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshControlManger extends ViewGroupManager<JDReactPullToRefreshFrameLayout> {
    private static String NAME = "RCTJDRefreshControl";
    private boolean mIsRefreshing;

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("topRefresh", MapBuilder.of("registrationName", "onRefresh")).build();
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    @ReactProp(defaultBoolean = false, name = LoadingHead.LOADING_STATE_REFRESHING)
    public void setRefreshing(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, boolean z) {
        OKLog.i(NAME, "setRefreshing: " + z);
        this.mIsRefreshing = z;
        if (z || !jDReactPullToRefreshBasicFrameLayout.isRefreshing()) {
            return;
        }
        jDReactPullToRefreshBasicFrameLayout.refreshComplete();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(final ThemedReactContext themedReactContext, final JDReactPullToRefreshFrameLayout jDReactPullToRefreshFrameLayout) {
        jDReactPullToRefreshFrameLayout.setPtrHandler(new JDReactPullToRefreshHandler() { // from class: com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshControlManger.2
            {
                JDReactPullToRefreshControlManger.this = this;
            }

            @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshHandler
            public boolean checkCanDoRefresh(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, View view, View view2) {
                return JDReactPullToRefreshDefaultHandler.checkContentCanBePulledDown(jDReactPullToRefreshBasicFrameLayout, view, view2);
            }

            @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshHandler
            public void onRefreshBegin(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout) {
                OKLog.i(JDReactPullToRefreshControlManger.NAME, "on refreshing...");
                ((UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new JDReactPullToRefreshEvent(jDReactPullToRefreshFrameLayout.getId()));
            }
        });
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public JDReactPullToRefreshFrameLayout createViewInstance(ThemedReactContext themedReactContext) {
        return new JDReactPullToRefreshFrameLayout(themedReactContext) { // from class: com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshControlManger.1
            {
                JDReactPullToRefreshControlManger.this = this;
            }

            @Override // android.view.ViewGroup, android.view.View
            protected void onAttachedToWindow() {
                onFinishInflate();
                super.onAttachedToWindow();
            }
        };
    }
}
