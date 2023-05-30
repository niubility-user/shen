package com.jd.manto.center.widget;

import com.jd.manto.center.R;
import com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView;

/* loaded from: classes17.dex */
public class f extends LoadMoreView {
    @Override // com.jingdong.common.baseRecycleAdapter.loadmore.LoadMoreView
    public int getLayoutId() {
        return R.layout.lib_search_product_list_loading_footer_layout;
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
    protected int getLoadingViewId() {
        return R.id.loading_layout;
    }
}
