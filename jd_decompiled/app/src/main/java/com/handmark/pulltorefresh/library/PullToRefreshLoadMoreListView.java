package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.LoadingMoreLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.IListViewLoadMore;

/* loaded from: classes12.dex */
public class PullToRefreshLoadMoreListView extends PullToRefreshListView implements ILoadMore, IListViewLoadMore {
    private Context mContext;
    private LoadMoreListener mLoadMoreListener;
    private LoadingMoreLayout mLoadingMoreLayout;

    public PullToRefreshLoadMoreListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        LoadingMoreLayout loadingMoreLayout = new LoadingMoreLayout(this.mContext);
        this.mLoadingMoreLayout = loadingMoreLayout;
        loadingMoreLayout.setOnRetryListener(new LoadingMoreLayout.RetryListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshLoadMoreListView.1
            @Override // com.handmark.pulltorefresh.library.LoadingMoreLayout.RetryListener
            public void onRetry() {
                if (PullToRefreshLoadMoreListView.this.mLoadMoreListener != null) {
                    PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.LOADING);
                    PullToRefreshLoadMoreListView.this.mLoadMoreListener.loadMore();
                }
            }
        });
        ((ListView) getRefreshableView()).addFooterView(this.mLoadingMoreLayout, null, false);
        setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshLoadMoreListView.2
            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener
            public void onLastItemVisible() {
                LoadingMoreLayout.FooterState footerState = PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.getFooterState();
                LoadingMoreLayout.FooterState footerState2 = LoadingMoreLayout.FooterState.LOADING;
                if (footerState == footerState2 || PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.getFooterState() == LoadingMoreLayout.FooterState.REACH_END || PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.getFooterState() == LoadingMoreLayout.FooterState.LOADING_FAILED || PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.getFooterState() == LoadingMoreLayout.FooterState.REACH_END_INVISIBLE || PullToRefreshLoadMoreListView.this.mLoadMoreListener == null) {
                    return;
                }
                PullToRefreshLoadMoreListView.this.mLoadingMoreLayout.setFootersState(footerState2);
                PullToRefreshLoadMoreListView.this.mLoadMoreListener.loadMore();
            }
        });
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void resetFooter() {
        this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.RESET);
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setLoadingMoreFailed() {
        this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.LOADING_FAILED);
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setLoadingMoreSucceed() {
        this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.LOADING_SUCCESS);
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setOnLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setReachEnd() {
        this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.REACH_END);
    }

    @Override // com.handmark.pulltorefresh.library.internal.IListViewLoadMore
    public void setReachEndInvisible() {
        this.mLoadingMoreLayout.setFootersState(LoadingMoreLayout.FooterState.REACH_END_INVISIBLE);
    }

    public PullToRefreshLoadMoreListView(Context context) {
        this(context, null);
    }
}
