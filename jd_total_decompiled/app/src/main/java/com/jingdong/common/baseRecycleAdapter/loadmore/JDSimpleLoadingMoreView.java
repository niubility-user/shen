package com.jingdong.common.baseRecycleAdapter.loadmore;

import com.jingdong.sdk.platform.lib.R;

/* loaded from: classes5.dex */
public class JDSimpleLoadingMoreView extends LoadMoreView {
    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    public int getLayoutId() {
        return R.layout.ptr_footer;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadEndViewId() {
        return R.id.footer_reach_end_view;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadFailViewId() {
        return R.id.footer_retry_view;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    public int getLoadingMsg() {
        return R.id.loading_msg;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadingViewId() {
        return R.id.loading_layout;
    }
}
