package com.jingdong.common.jdreactFramework.views.pulltorefresh;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes5.dex */
public class JDReactPullToRefreshFrameLayout extends JDReactPullToRefreshBasicFrameLayout {
    private JDReactPullToRefreshHeader mJDReactPullToRefreshHeader;

    public JDReactPullToRefreshFrameLayout(Context context) {
        this(context, null);
    }

    private void initViews() {
        setBackgroundColor(-789001);
        JDReactPullToRefreshHeader jDReactPullToRefreshHeader = new JDReactPullToRefreshHeader(getContext());
        this.mJDReactPullToRefreshHeader = jDReactPullToRefreshHeader;
        setHeaderView(jDReactPullToRefreshHeader);
        addPtrUIHandler(this.mJDReactPullToRefreshHeader);
    }

    public JDReactPullToRefreshHeader getHeader() {
        return this.mJDReactPullToRefreshHeader;
    }

    public JDReactPullToRefreshFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public JDReactPullToRefreshFrameLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        initViews();
    }
}
