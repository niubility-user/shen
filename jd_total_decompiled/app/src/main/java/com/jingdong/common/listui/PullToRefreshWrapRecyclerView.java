package com.jingdong.common.listui;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/* loaded from: classes5.dex */
public class PullToRefreshWrapRecyclerView extends PullToRefreshBase<WrapRecyclerView> {
    public PullToRefreshWrapRecyclerView(Context context) {
        super(context);
    }

    private int findMax(int[] iArr) {
        int i2 = Integer.MIN_VALUE;
        for (int i3 : iArr) {
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    private int findMin(int[] iArr) {
        int i2 = Integer.MAX_VALUE;
        for (int i3 : iArr) {
            if (i3 < i2) {
                i2 = i3;
            }
        }
        return i2;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        if (((WrapRecyclerView) this.mRefreshableView).getLayoutManager() == null || ((WrapRecyclerView) this.mRefreshableView).getAdapter() == null) {
            return false;
        }
        try {
            RecyclerView.LayoutManager layoutManager = ((WrapRecyclerView) this.mRefreshableView).getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition() == ((WrapRecyclerView) this.mRefreshableView).getAdapter().getItemCount() - 1;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(iArr);
                return findMax(iArr) == ((WrapRecyclerView) this.mRefreshableView).getAdapter().getItemCount() - 1;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public boolean isReadyForPullStart() {
        if (((WrapRecyclerView) this.mRefreshableView).getLayoutManager() == null || ((WrapRecyclerView) this.mRefreshableView).getAdapter() == null) {
            return false;
        }
        try {
            View childAt = ((WrapRecyclerView) this.mRefreshableView).getChildAt(0);
            int height = childAt == null ? 0 : childAt.getHeight();
            RecyclerView.LayoutManager layoutManager = ((WrapRecyclerView) this.mRefreshableView).getLayoutManager();
            if (layoutManager instanceof LinearLayoutManager) {
                if (height > ((WrapRecyclerView) this.mRefreshableView).getHeight()) {
                    return (childAt == null ? 0 : childAt.getTop()) == 0 && ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() == 0;
                }
                return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition() == 0;
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] iArr = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                if (height > ((WrapRecyclerView) this.mRefreshableView).getHeight()) {
                    return (childAt == null ? 0 : childAt.getTop()) == 0 && findMin(((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(iArr)) == 0;
                }
                return findMin(((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(iArr)) == 0;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public void updateUIForMode() {
        super.updateUIForMode();
    }

    public PullToRefreshWrapRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    @TargetApi(4)
    public WrapRecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        return new WrapRecyclerView(context);
    }

    public PullToRefreshWrapRecyclerView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshWrapRecyclerView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
