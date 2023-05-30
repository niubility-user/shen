package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.view.View;

/* loaded from: classes5.dex */
public interface JDReactPullToRefreshHandler {
    boolean checkCanDoRefresh(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, View view, View view2);

    void onRefreshBegin(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout);
}
