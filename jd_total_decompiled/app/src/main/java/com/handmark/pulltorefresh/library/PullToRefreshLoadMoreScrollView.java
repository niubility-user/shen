package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class PullToRefreshLoadMoreScrollView extends PullToRefreshBase<ScrollView> implements PullScrollView.OnMyScrollListener {
    private boolean mIsLastVisibleFlag;
    private PullToRefreshBase.OnLastItemVisibleListener mOnLastItemVisibleListener;
    private PullScrollView.OnMyScrollListener mScrollListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(9)
    /* loaded from: classes12.dex */
    public final class InternalScrollViewSDK9 extends PullScrollView {
        public InternalScrollViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        private int getScrollRange() {
            if (getChildCount() > 0) {
                return Math.max(0, getChildAt(0).getHeight() - ((getHeight() - getPaddingBottom()) - getPaddingTop()));
            }
            return 0;
        }

        @Override // android.view.View
        protected boolean overScrollBy(int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z) {
            boolean overScrollBy = super.overScrollBy(i2, i3, i4, i5, i6, i7, i8, i9, z);
            OverscrollHelper.overScrollBy(PullToRefreshLoadMoreScrollView.this, i2, i4, i3, i5, getScrollRange(), z);
            return overScrollBy;
        }
    }

    public PullToRefreshLoadMoreScrollView(Context context) {
        super(context);
        this.mIsLastVisibleFlag = false;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        View childAt = ((ScrollView) this.mRefreshableView).getChildAt(0);
        return childAt != null && ((ScrollView) this.mRefreshableView).getScrollY() >= childAt.getHeight() - getHeight();
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        return ((ScrollView) this.mRefreshableView).getScrollY() == 0;
    }

    @Override // com.handmark.pulltorefresh.library.PullScrollView.OnMyScrollListener
    public void onScroll(int i2, int i3, int i4, int i5) {
        if (isReadyForPullEnd()) {
            if (!this.mIsLastVisibleFlag) {
                this.mIsLastVisibleFlag = true;
                PullToRefreshBase.OnLastItemVisibleListener onLastItemVisibleListener = this.mOnLastItemVisibleListener;
                if (onLastItemVisibleListener != null) {
                    onLastItemVisibleListener.onLastItemVisible();
                }
            }
        } else {
            this.mIsLastVisibleFlag = false;
        }
        PullScrollView.OnMyScrollListener onMyScrollListener = this.mScrollListener;
        if (onMyScrollListener != null) {
            onMyScrollListener.onScroll(i2, i3, i4, i5);
        }
    }

    public final void setOnLastItemVisibleListener(PullToRefreshBase.OnLastItemVisibleListener onLastItemVisibleListener) {
        this.mOnLastItemVisibleListener = onLastItemVisibleListener;
    }

    public final void setOnScrollListener(PullScrollView.OnMyScrollListener onMyScrollListener) {
        this.mScrollListener = onMyScrollListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public ScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        PullScrollView pullScrollView;
        if (Build.VERSION.SDK_INT >= 9) {
            pullScrollView = new InternalScrollViewSDK9(context, attributeSet);
        } else {
            pullScrollView = new PullScrollView(context, attributeSet);
        }
        pullScrollView.setOnMyScrollListener(this);
        pullScrollView.setId(R.id.pull_scrollview);
        this.mIsLastVisibleFlag = false;
        return pullScrollView;
    }

    public PullToRefreshLoadMoreScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsLastVisibleFlag = false;
    }

    public PullToRefreshLoadMoreScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
        this.mIsLastVisibleFlag = false;
    }

    public PullToRefreshLoadMoreScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        this.mIsLastVisibleFlag = false;
    }
}
