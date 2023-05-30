package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;

/* loaded from: classes19.dex */
public class ListLibRecyclerView extends PullToRefreshRecyclerView {
    public ListLibRecyclerView(Context context) {
        this(context, null);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) this.mRefreshableView).getLayoutManager();
        RecyclerView.Adapter adapter = ((RecyclerView) this.mRefreshableView).getAdapter();
        if (adapter == null) {
            return false;
        }
        try {
            if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                return (layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == adapter.getItemCount() - 1;
            }
            int[] findLastCompletelyVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(null);
            if (findLastCompletelyVisibleItemPositions == null || findLastCompletelyVisibleItemPositions.length <= 0) {
                return false;
            }
            boolean z = false;
            for (int i2 : findLastCompletelyVisibleItemPositions) {
                if (!z && i2 != adapter.getItemCount() - 1) {
                    z = false;
                }
                z = true;
            }
            return z;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        RecyclerView.LayoutManager layoutManager = ((RecyclerView) this.mRefreshableView).getLayoutManager();
        try {
            View childAt = ((RecyclerView) this.mRefreshableView).getChildAt(0);
            if ((childAt == null ? 0 : childAt.getHeight()) > ((RecyclerView) this.mRefreshableView).getHeight()) {
                int top = childAt == null ? 0 : childAt.getTop();
                if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                    return (layoutManager instanceof LinearLayoutManager) && top == 0 && ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0;
                }
                int[] findFirstVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null);
                return (findFirstVisibleItemPositions != null || findFirstVisibleItemPositions.length > 0) && top == 0 && findFirstVisibleItemPositions[0] == 0;
            } else if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                return (layoutManager instanceof LinearLayoutManager) && ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0;
            } else {
                int[] findFirstCompletelyVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null);
                return (findFirstCompletelyVisibleItemPositions != null || findFirstCompletelyVisibleItemPositions.length > 0) && findFirstCompletelyVisibleItemPositions[0] == 0;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    public ListLibRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setMode(PullToRefreshBase.Mode.DISABLED);
        getRefreshableView().setLayoutManager(new LinearLayoutManager(context));
    }
}
