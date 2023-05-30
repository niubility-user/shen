package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;
import com.jingdong.app.mall.home.floor.model.entity.IconFloorEntity;

/* loaded from: classes5.dex */
public class ParentPullToRefreshRecyclerView extends PullToRefreshRecyclerView {
    private BaseLoadingLayout loadingLayout;
    private final int[] mPosition;

    public ParentPullToRefreshRecyclerView(Context context) {
        super(context);
        this.mPosition = new int[2];
    }

    private int findFirstCompletelyVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(this.mPosition);
            int[] iArr = this.mPosition;
            return Math.min(iArr[0], iArr[1]);
        }
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        return 0;
    }

    private int findFirstVisibleItemPosition(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(this.mPosition);
            int[] iArr = this.mPosition;
            return Math.min(iArr[0], iArr[1]);
        } else if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else {
            return 0;
        }
    }

    private void setTextViewColor(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt instanceof ViewGroup) {
                setTextViewColor((ViewGroup) childAt, z);
            } else if (childAt instanceof TextView) {
                ((TextView) childAt).setTextColor(z ? -1 : -7566196);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        if (this.loadingLayout == null) {
            BaseLoadingLayout createLoadingLayout = super.createLoadingLayout(context, mode, typedArray);
            this.loadingLayout = createLoadingLayout;
            return createLoadingLayout;
        }
        return super.createLoadingLayout(context, mode, typedArray);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshRecyclerView, com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        RecyclerView.LayoutManager layoutManager;
        T t = this.mRefreshableView;
        if (((t instanceof ParentRecyclerView) && ((ParentRecyclerView) t).isNestCanScorll()) || (layoutManager = ((RecyclerView) this.mRefreshableView).getLayoutManager()) == null || ((RecyclerView) this.mRefreshableView).getAdapter() == null) {
            return false;
        }
        try {
            View childAt = ((RecyclerView) this.mRefreshableView).getChildAt(0);
            if ((childAt == null ? 0 : childAt.getHeight()) > ((RecyclerView) this.mRefreshableView).getHeight()) {
                if ((childAt == null ? 0 : childAt.getTop()) != 0 || findFirstVisibleItemPosition(layoutManager) != 0) {
                    return false;
                }
            } else if (findFirstCompletelyVisibleItemPosition(layoutManager) != 0) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void setTransparentMode(boolean z) {
        BaseLoadingLayout headerLayout = getHeaderLayout();
        if (headerLayout != null && headerLayout.getChildCount() > 0) {
            View childAt = headerLayout.getChildAt(0);
            if (childAt instanceof ViewGroup) {
                childAt.setBackgroundColor(z ? 0 : IconFloorEntity.BGCOLOR_DEFAULT);
                setTextViewColor((ViewGroup) childAt, z);
            }
        }
    }

    public ParentPullToRefreshRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPosition = new int[2];
    }
}
