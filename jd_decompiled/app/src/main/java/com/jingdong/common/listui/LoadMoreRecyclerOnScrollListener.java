package com.jingdong.common.listui;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public abstract class LoadMoreRecyclerOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager mLinearLayoutManager;
    private int previousTotal = 0;
    private boolean loadMoreEnable = true;
    private int visibleThreshold = 1;
    private boolean isLoading = false;

    public LoadMoreRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    public boolean isLoadMoreEnable() {
        return this.loadMoreEnable;
    }

    public abstract boolean onLoadMoreWithFootView();

    public abstract void onScroll(RecyclerView recyclerView, int i2, int i3, int i4);

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
        super.onScrolled(recyclerView, i2, i3);
        int findLastVisibleItemPosition = this.mLinearLayoutManager.findLastVisibleItemPosition();
        if (this.loadMoreEnable && i3 > 0) {
            int itemCount = this.mLinearLayoutManager.getItemCount();
            if (this.isLoading) {
                int i4 = this.previousTotal;
                if (itemCount > i4) {
                    this.isLoading = false;
                    this.previousTotal = itemCount;
                } else if (itemCount < i4) {
                    this.isLoading = false;
                }
            }
            if (!this.isLoading && i3 > 0 && this.visibleThreshold + findLastVisibleItemPosition >= itemCount) {
                this.isLoading = true;
                if (onLoadMoreWithFootView()) {
                    this.previousTotal = itemCount + 1;
                } else {
                    this.previousTotal = itemCount;
                }
            }
        }
        onScroll(recyclerView, findLastVisibleItemPosition, i2, i3);
    }

    public void setLoadMoreEnable(boolean z) {
        this.loadMoreEnable = z;
    }

    public void setLoading(boolean z) {
        this.isLoading = z;
    }

    public void setVisibleThreshold(int i2) {
        this.visibleThreshold = i2;
    }

    public void updateLoadingState(boolean z) {
        this.isLoading = z;
    }
}
