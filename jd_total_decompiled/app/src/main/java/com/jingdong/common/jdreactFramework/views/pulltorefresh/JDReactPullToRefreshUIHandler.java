package com.jingdong.common.jdreactFramework.views.pulltorefresh;

/* loaded from: classes5.dex */
public interface JDReactPullToRefreshUIHandler {
    void onUIPositionChange(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, boolean z, byte b, JDReactPullToRefreshIndicator jDReactPullToRefreshIndicator);

    void onUIRefreshBegin(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout);

    void onUIRefreshComplete(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout);

    void onUIRefreshPrepare(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout);

    void onUIReset(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout);
}
