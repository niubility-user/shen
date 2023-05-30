package com.jingdong.common.widget.custom.pageload;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.widget.custom.CustomBasePageLoader;
import com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView;
import com.jingdong.common.widget.custom.pageload.TIncrementPagingLoadingManager;

/* loaded from: classes12.dex */
public class CustomStaggerRecyclerView extends CustomPageLoaderRecyclerView {
    public CustomStaggerRecyclerView(BaseActivity baseActivity, CustomBasePageLoader customBasePageLoader, TIncrementPagingLoadingManager.LoadConfig loadConfig, boolean z, View view) {
        super(baseActivity, customBasePageLoader, loadConfig.div, z, view);
        getRefreshableView().setLayoutManager(new StaggeredGridLayoutManager(loadConfig.spanCount, 1));
    }

    private int getLastVisibleRow(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return 0;
        }
        return getLastVisibleItemPosition(recyclerView) / ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount();
    }

    @Override // com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView
    protected void checkWhetherShowNextPage(RecyclerView recyclerView, int i2) {
        int itemCount;
        if (recyclerView == null || recyclerView.getAdapter() == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null || i2 <= 0 || (itemCount = recyclerView.getAdapter().getItemCount()) == this.lastTotalItemCount || getLastVisibleItemPosition(recyclerView) < itemCount - (this.SCROLLTOSHOWNEXTPAGE * ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).getSpanCount())) {
            return;
        }
        this.lastTotalItemCount = itemCount;
        this.nextPagerLoader.showNextPage();
    }

    @Override // com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView
    protected void checkWhetherTheTopBtnShow(RecyclerView recyclerView) {
        View view;
        if (recyclerView == null || recyclerView.getLayoutManager() == null || (view = this.topBtn) == null) {
            return;
        }
        view.setVisibility(getLastVisibleRow(recyclerView) > this.SCROLLTOSHOWTOPPOSITION ? 0 : 8);
        if (this.iTopButtonListener == null || this.topBtn.getVisibility() != 0) {
            return;
        }
        this.iTopButtonListener.onShow();
    }

    @Override // com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView
    protected int getLastVisibleItemPosition(RecyclerView recyclerView) {
        int[] findLastVisibleItemPositions;
        if (recyclerView == null || (findLastVisibleItemPositions = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPositions(null)) == null || findLastVisibleItemPositions.length <= 0) {
            return 0;
        }
        int i2 = 0;
        for (int i3 : findLastVisibleItemPositions) {
            i2 = Math.max(i2, i3);
        }
        return i2;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        if (((RecyclerView) this.mRefreshableView).getLayoutManager() != null && ((RecyclerView) this.mRefreshableView).getAdapter() != null) {
            try {
                int[] findLastCompletelyVisibleItemPositions = ((StaggeredGridLayoutManager) ((RecyclerView) this.mRefreshableView).getLayoutManager()).findLastCompletelyVisibleItemPositions(null);
                if (findLastCompletelyVisibleItemPositions != null && findLastCompletelyVisibleItemPositions.length > 0) {
                    boolean z = false;
                    for (int i2 : findLastCompletelyVisibleItemPositions) {
                        z = z || i2 == ((RecyclerView) this.mRefreshableView).getAdapter().getItemCount() - 1;
                    }
                    return z;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        if (((RecyclerView) this.mRefreshableView).getLayoutManager() == null || ((RecyclerView) this.mRefreshableView).getAdapter() == null) {
            return false;
        }
        try {
            View childAt = ((RecyclerView) this.mRefreshableView).getChildAt(0);
            int height = childAt == null ? 0 : childAt.getHeight();
            StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) ((RecyclerView) this.mRefreshableView).getLayoutManager();
            if (height > ((RecyclerView) this.mRefreshableView).getHeight()) {
                int top = childAt == null ? 0 : childAt.getTop();
                int[] findFirstVisibleItemPositions = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
                return (findFirstVisibleItemPositions != null || findFirstVisibleItemPositions.length > 0) && top == 0 && findFirstVisibleItemPositions[0] < staggeredGridLayoutManager.getSpanCount();
            }
            int[] findFirstCompletelyVisibleItemPositions = staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(null);
            return (findFirstCompletelyVisibleItemPositions != null || findFirstCompletelyVisibleItemPositions.length > 0) && findFirstCompletelyVisibleItemPositions[0] < staggeredGridLayoutManager.getSpanCount();
        } catch (Throwable unused) {
            return false;
        }
    }
}
