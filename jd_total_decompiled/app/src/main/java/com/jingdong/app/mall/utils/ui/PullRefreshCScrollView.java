package com.jingdong.app.mall.utils.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.handmark.pulltorefresh.library.OverscrollHelper;
import com.handmark.pulltorefresh.library.PullScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jingdong.app.mall.R;

/* loaded from: classes4.dex */
public class PullRefreshCScrollView extends PullToRefreshBase<PullScrollView> {

    /* JADX INFO: Access modifiers changed from: package-private */
    @TargetApi(9)
    /* loaded from: classes4.dex */
    public final class a extends PullScrollView {
        public a(Context context, AttributeSet attributeSet) {
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
            OverscrollHelper.overScrollBy(PullRefreshCScrollView.this, i2, i4, i3, i5, getScrollRange(), z);
            return overScrollBy;
        }
    }

    public PullRefreshCScrollView(Context context) {
        super(context);
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public final PullToRefreshBase.Orientation getPullToRefreshScrollDirection() {
        return PullToRefreshBase.Orientation.VERTICAL;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullEnd() {
        View childAt = ((PullScrollView) this.mRefreshableView).getChildAt(0);
        return childAt != null && ((PullScrollView) this.mRefreshableView).getScrollY() >= childAt.getHeight() - getHeight();
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    protected boolean isReadyForPullStart() {
        return ((PullScrollView) this.mRefreshableView).getScrollY() == 0;
    }

    public PullRefreshCScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    @TargetApi(4)
    public PullScrollView createRefreshableView(Context context, AttributeSet attributeSet) {
        PullScrollView pullScrollView;
        if (Build.VERSION.SDK_INT >= 9) {
            pullScrollView = new a(context, attributeSet);
        } else {
            pullScrollView = new PullScrollView(context, attributeSet);
        }
        pullScrollView.setId(R.id.du);
        return pullScrollView;
    }

    public PullRefreshCScrollView(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
    }

    public PullRefreshCScrollView(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }
}
