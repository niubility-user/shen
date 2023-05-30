package com.jingdong.common.baseRecycleAdapter.loadmore;

import com.jingdong.sdk.platform.lib.R;

/* loaded from: classes5.dex */
public final class SimpleLoadMoreView extends LoadMoreView {
    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    public int getLayoutId() {
        return R.layout.quick_view_load_more;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }
}
