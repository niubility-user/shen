package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/* loaded from: classes12.dex */
public class PullToRefreshListViewEx extends PullToRefreshListView {
    private ILoadingLayout footerLoadingLayout;
    private ILoadingLayout headerLoadingLayout;

    public PullToRefreshListViewEx(Context context) {
        super(context);
        getLoadingLayouts();
    }

    private void getLoadingLayouts() {
        this.headerLoadingLayout = getHeaderLayout();
        this.footerLoadingLayout = getFooterLayout();
    }

    public FrameLayout getSubRootView() {
        return getRefreshableViewWrapper();
    }

    public void setFooterPullLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.footerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setPullLabel(charSequence);
        }
    }

    public void setFooterRefreshingLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.footerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setRefreshingLabel(charSequence);
        }
    }

    public void setFooterReleaseLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.footerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setReleaseLabel(charSequence);
        }
    }

    public void setHeaderPullLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.headerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setPullLabel(charSequence);
        }
    }

    public void setHeaderRefreshingLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.headerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setRefreshingLabel(charSequence);
        }
    }

    public void setHeaderReleaseLabel(CharSequence charSequence) {
        ILoadingLayout iLoadingLayout = this.headerLoadingLayout;
        if (iLoadingLayout != null) {
            iLoadingLayout.setReleaseLabel(charSequence);
        }
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase, com.handmark.pulltorefresh.library.PullToRefreshBase
    public void updateUIForMode() {
        super.updateUIForMode();
    }

    public PullToRefreshListViewEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        getLoadingLayouts();
    }

    public PullToRefreshListViewEx(Context context, PullToRefreshBase.Mode mode) {
        super(context, mode);
        getLoadingLayouts();
    }

    public PullToRefreshListViewEx(Context context, PullToRefreshBase.Mode mode, PullToRefreshBase.AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
        getLoadingLayouts();
    }
}
