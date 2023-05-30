package com.handmark.pulltorefresh.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.JDLoadingMoreLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/* loaded from: classes12.dex */
public class PullToRefreshLoadMoreRecyclerView extends PullToRefreshRecyclerView implements ILoadMore {
    public static final String TAG = PullToRefreshLoadMoreRecyclerView.class.getSimpleName();
    private JDLoadingMoreLayout.FooterState cachedState;
    private RecyclerView.OnScrollListener mExternalScrollListener;
    private boolean mLastItemVisible;
    private LoadMoreListener mLoadMoreListener;
    private JDLoadingMoreLayout mLoadingMoreLayout;
    private PullToRefreshBase.OnLastItemVisibleListener mOnLastItemVisibleListener;
    private RecyclerView.OnScrollListener mScrollListener;

    public PullToRefreshLoadMoreRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (PullToRefreshLoadMoreRecyclerView.this.mOnLastItemVisibleListener != null && i2 == 0 && PullToRefreshLoadMoreRecyclerView.this.mLastItemVisible) {
                    PullToRefreshLoadMoreRecyclerView.this.mOnLastItemVisibleListener.onLastItemVisible();
                }
                if (PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener != null) {
                    PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener.onScrollStateChanged(recyclerView, i2);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView = PullToRefreshLoadMoreRecyclerView.this;
                pullToRefreshLoadMoreRecyclerView.mLastItemVisible = pullToRefreshLoadMoreRecyclerView.isLastItemVisible();
                if (PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener != null) {
                    PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener.onScrolled(recyclerView, i2, i3);
                }
            }
        };
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void invalidateView() {
        LoadMoreListener loadMoreListener = this.mLoadMoreListener;
        if (loadMoreListener != null) {
            this.cachedState = null;
            loadMoreListener.loadMore();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLastItemVisible() {
        View findViewByPosition;
        RecyclerView.Adapter adapter = ((RecyclerView) this.mRefreshableView).getAdapter();
        if (adapter == null) {
            return true;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) ((RecyclerView) this.mRefreshableView).getLayoutManager();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        return findLastVisibleItemPosition >= (adapter.getItemCount() - 1) - 1 && (findViewByPosition = linearLayoutManager.findViewByPosition(findLastVisibleItemPosition)) != null && findViewByPosition.getBottom() <= getBottom();
    }

    protected JDLoadingMoreLayout getLoadingMoreLayout() {
        int itemCount;
        View findViewByPosition;
        if (((RecyclerView) this.mRefreshableView).getAdapter() != null && r0.getItemCount() - 1 >= 0 && (findViewByPosition = ((LinearLayoutManager) ((RecyclerView) this.mRefreshableView).getLayoutManager()).findViewByPosition(itemCount)) != null && (findViewByPosition instanceof JDLoadingMoreLayout)) {
            JDLoadingMoreLayout jDLoadingMoreLayout = (JDLoadingMoreLayout) findViewByPosition;
            jDLoadingMoreLayout.setAutoDarkMode(getAutoDark() == null ? false : getAutoDark().booleanValue());
            jDLoadingMoreLayout.refreshTheme();
            return jDLoadingMoreLayout;
        }
        return null;
    }

    public void init() {
        setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView.1
            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener
            public void onLastItemVisible() {
                PullToRefreshLoadMoreRecyclerView.this.invalidateView();
            }
        });
        ((RecyclerView) this.mRefreshableView).addOnScrollListener(this.mScrollListener);
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void resetFooter() {
        this.cachedState = null;
        JDLoadingMoreLayout loadingMoreLayout = getLoadingMoreLayout();
        this.mLoadingMoreLayout = loadingMoreLayout;
        if (loadingMoreLayout != null) {
            loadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.RESET);
        } else {
            this.cachedState = JDLoadingMoreLayout.FooterState.RESET;
        }
    }

    public void setExternalScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.mExternalScrollListener = onScrollListener;
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setLoadingMoreFailed() {
        this.cachedState = null;
        JDLoadingMoreLayout loadingMoreLayout = getLoadingMoreLayout();
        this.mLoadingMoreLayout = loadingMoreLayout;
        if (loadingMoreLayout != null) {
            loadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.LOADING_FAILED);
        } else {
            this.cachedState = JDLoadingMoreLayout.FooterState.LOADING_FAILED;
        }
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setLoadingMoreSucceed() {
        this.cachedState = null;
        JDLoadingMoreLayout loadingMoreLayout = getLoadingMoreLayout();
        this.mLoadingMoreLayout = loadingMoreLayout;
        if (loadingMoreLayout != null) {
            loadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.LOADING_SUCCESS);
        } else {
            this.cachedState = JDLoadingMoreLayout.FooterState.LOADING_SUCCESS;
        }
    }

    public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener onLastItemVisibleListener) {
        this.mOnLastItemVisibleListener = onLastItemVisibleListener;
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setOnLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.mLoadMoreListener = loadMoreListener;
    }

    @Override // com.handmark.pulltorefresh.library.ILoadMore
    public void setReachEnd() {
        RecyclerView.Adapter adapter;
        this.cachedState = null;
        JDLoadingMoreLayout loadingMoreLayout = getLoadingMoreLayout();
        this.mLoadingMoreLayout = loadingMoreLayout;
        if (loadingMoreLayout != null) {
            loadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.REACH_END);
            if (((RecyclerView) this.mRefreshableView).getScrollState() == 1 || (adapter = ((RecyclerView) this.mRefreshableView).getAdapter()) == null) {
                return;
            }
            ((RecyclerView) this.mRefreshableView).smoothScrollToPosition(adapter.getItemCount() - 1);
            return;
        }
        this.cachedState = JDLoadingMoreLayout.FooterState.REACH_END;
    }

    public void setReachEndInvisible() {
        this.cachedState = null;
        JDLoadingMoreLayout loadingMoreLayout = getLoadingMoreLayout();
        this.mLoadingMoreLayout = loadingMoreLayout;
        if (loadingMoreLayout != null) {
            loadingMoreLayout.setFootersState(JDLoadingMoreLayout.FooterState.REACH_END_INVISIBLE);
        } else {
            this.cachedState = JDLoadingMoreLayout.FooterState.REACH_END_INVISIBLE;
        }
    }

    public PullToRefreshLoadMoreRecyclerView(Context context) {
        super(context);
        this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: com.handmark.pulltorefresh.library.PullToRefreshLoadMoreRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (PullToRefreshLoadMoreRecyclerView.this.mOnLastItemVisibleListener != null && i2 == 0 && PullToRefreshLoadMoreRecyclerView.this.mLastItemVisible) {
                    PullToRefreshLoadMoreRecyclerView.this.mOnLastItemVisibleListener.onLastItemVisible();
                }
                if (PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener != null) {
                    PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener.onScrollStateChanged(recyclerView, i2);
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                PullToRefreshLoadMoreRecyclerView pullToRefreshLoadMoreRecyclerView = PullToRefreshLoadMoreRecyclerView.this;
                pullToRefreshLoadMoreRecyclerView.mLastItemVisible = pullToRefreshLoadMoreRecyclerView.isLastItemVisible();
                if (PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener != null) {
                    PullToRefreshLoadMoreRecyclerView.this.mExternalScrollListener.onScrolled(recyclerView, i2, i3);
                }
            }
        };
        init();
    }
}
