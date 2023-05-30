package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ScrollView;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public abstract class JDReactPullToRefreshDefaultHandler implements JDReactPullToRefreshHandler {
    public static final String TAG = "JDReactPullToRefresh";

    public static boolean canChildScrollUp(View view) {
        if (view instanceof AbsListView) {
            AbsListView absListView = (AbsListView) view;
            return absListView.getChildCount() > 0 && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop());
        } else if (view instanceof ScrollView) {
            OKLog.d(TAG, "getMaxScrollAmount = " + ((ScrollView) view).getMaxScrollAmount());
            return view.getScrollY() > 0;
        } else {
            OKLog.d(TAG, "~~~~~");
            return view.canScrollVertically(-1);
        }
    }

    public static boolean checkContentCanBePulledDown(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, View view, View view2) {
        return !canChildScrollUp(view);
    }

    @Override // com.jingdong.common.jdreactFramework.views.pulltorefresh.JDReactPullToRefreshHandler
    public boolean checkCanDoRefresh(JDReactPullToRefreshBasicFrameLayout jDReactPullToRefreshBasicFrameLayout, View view, View view2) {
        return checkContentCanBePulledDown(jDReactPullToRefreshBasicFrameLayout, view, view2);
    }
}
