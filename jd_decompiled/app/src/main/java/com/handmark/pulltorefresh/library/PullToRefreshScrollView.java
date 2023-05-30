package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jd.lib.un.basewidget.R;

/* loaded from: classes12.dex */
public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView> {

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
            OverscrollHelper.overScrollBy(PullToRefreshScrollView.this, i2, i4, i3, i5, getScrollRange(), z);
            return overScrollBy;
        }
    }

    public PullToRefreshScrollView(Context context) {
        super(context);
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

    public PullToRefreshScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    @TargetApi(4)
    public ScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        ScrollView pullScrollView;
        if (Build.VERSION.SDK_INT >= 9) {
            pullScrollView = new InternalScrollViewSDK9(context, attributeSet);
        } else {
            pullScrollView = new PullScrollView(context, attributeSet);
        }
        pullScrollView.setId(R.id.pull_scrollview);
        return pullScrollView;
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullToRefreshScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
