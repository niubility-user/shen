package com.jingdong.sdk.platform.business.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.internal.BaseLoadingLayout;

/* loaded from: classes10.dex */
public class PullToSeekMoreHorizontalRecyclerView extends PullToRefreshBase<RecyclerView> {
    private static final float SLOPE_VALUE = 0.5f;
    private int lastX;
    private int lastY;
    private float slopeMin;

    public PullToSeekMoreHorizontalRecyclerView(Context context) {
        super(context);
        this.slopeMin = 1.0f;
    }

    private int getFirstVisiblePosition() {
        RecyclerView.LayoutManager layoutManager = getRefreshableView().getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return 0;
    }

    private int getLastVisiblePosition() {
        RecyclerView.LayoutManager layoutManager = getRefreshableView().getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        }
        return 0;
    }

    private boolean isLastItemVisible() {
        RecyclerView.Adapter adapter = ((RecyclerView) this.mRefreshableView).getAdapter();
        if (adapter == null || adapter.getItemCount() == 0) {
            return true;
        }
        int lastVisiblePosition = getLastVisiblePosition();
        if (lastVisiblePosition >= (adapter.getItemCount() - 1) - 1) {
            View childAt = ((RecyclerView) this.mRefreshableView).getChildAt(lastVisiblePosition - getFirstVisiblePosition());
            return childAt != null && childAt.getRight() <= ((RecyclerView) this.mRefreshableView).getRight();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public BaseLoadingLayout createLoadingLayout(Context context, PullToRefreshBase.Mode mode, TypedArray typedArray) {
        return new PartsSeekMoreView(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.lastX = (int) motionEvent.getRawX();
            this.lastY = (int) motionEvent.getRawY();
        } else if (action == 1) {
            this.slopeMin = 1.0f;
        } else if (action == 2) {
            float abs = Math.abs(rawY - this.lastY) / Math.abs(rawX - this.lastX);
            if (this.slopeMin > abs) {
                this.slopeMin = abs;
            }
            if (this.slopeMin <= 0.5f) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (action == 3) {
            this.slopeMin = 1.0f;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.HORIZONTAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        return isLastItemVisible();
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public RecyclerView createRefreshableView(Context context, AttributeSet attributeSet) {
        RecyclerView recyclerView = new RecyclerView(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(0);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setOverScrollMode(2);
        return recyclerView;
    }

    public PullToSeekMoreHorizontalRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.slopeMin = 1.0f;
    }
}
