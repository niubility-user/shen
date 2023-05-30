package com.jingdong.common.baseRecycleAdapter.loadmore;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.un.global.theme.UnWidgetThemeController;
import com.jingdong.sdk.platform.lib.R;

/* loaded from: classes5.dex */
public abstract class LoadMoreView {
    public static final int STATUS_DEFAULT = 1;
    public static final int STATUS_END = 4;
    public static final int STATUS_FAIL = 3;
    public static final int STATUS_LOADING = 2;
    private boolean isAutoDark;
    private View loadingEndView;
    private View loadingFailedView;
    private View loadingView;
    private int mLoadMoreStatus = 1;
    private boolean mLoadMoreEndGone = false;
    private boolean isLoadingViewNew = false;

    private void darkMode(RecyclerView.ViewHolder viewHolder) {
        TextView textView;
        if (viewHolder == null) {
            return;
        }
        int color = viewHolder.itemView.getContext().getResources().getColor(R.color.un_content_level_1);
        if (this.isAutoDark && UnWidgetThemeController.getInstance().isDarkMode()) {
            color = viewHolder.itemView.getContext().getResources().getColor(R.color.un_content_level_1_dark);
        }
        if (getLoadingMsg() != 0 && (textView = (TextView) viewHolder.itemView.findViewById(getLoadingMsg())) != null) {
            textView.setTextColor(color);
        }
        View findViewById = viewHolder.itemView.findViewById(getLoadFailViewId());
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setTextColor(color);
        }
        View findViewById2 = viewHolder.itemView.findViewById(getLoadEndViewId());
        if (findViewById2 instanceof TextView) {
            ((TextView) findViewById2).setTextColor(color);
        }
    }

    private void visibleLoadEnd(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.loadingEndView == null || this.isLoadingViewNew) {
            this.loadingEndView = viewHolder.itemView.findViewById(getLoadEndViewId());
        }
        View view = this.loadingEndView;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void visibleLoadFail(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.loadingFailedView == null || this.isLoadingViewNew) {
            this.loadingFailedView = viewHolder.itemView.findViewById(getLoadFailViewId());
        }
        View view = this.loadingFailedView;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    private void visibleLoading(RecyclerView.ViewHolder viewHolder, boolean z) {
        if (this.loadingView == null || this.isLoadingViewNew) {
            this.loadingView = viewHolder.itemView.findViewById(getLoadingViewId());
        }
        View view = this.loadingView;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    public void convert(RecyclerView.ViewHolder viewHolder) {
        darkMode(viewHolder);
        int i2 = this.mLoadMoreStatus;
        if (i2 == 1) {
            visibleLoading(viewHolder, false);
            visibleLoadFail(viewHolder, false);
            visibleLoadEnd(viewHolder, false);
        } else if (i2 == 2) {
            visibleLoading(viewHolder, true);
            visibleLoadFail(viewHolder, false);
            visibleLoadEnd(viewHolder, false);
        } else if (i2 == 3) {
            visibleLoading(viewHolder, false);
            visibleLoadFail(viewHolder, true);
            visibleLoadEnd(viewHolder, false);
        } else if (i2 == 4) {
            visibleLoading(viewHolder, false);
            visibleLoadFail(viewHolder, false);
            visibleLoadEnd(viewHolder, true);
        }
        this.isLoadingViewNew = false;
    }

    @LayoutRes
    public abstract int getLayoutId();

    @IdRes
    protected abstract int getLoadEndViewId();

    @IdRes
    protected abstract int getLoadFailViewId();

    public int getLoadMoreStatus() {
        return this.mLoadMoreStatus;
    }

    public int getLoadingMsg() {
        return 0;
    }

    @IdRes
    protected abstract int getLoadingViewId();

    @Deprecated
    public boolean isLoadEndGone() {
        return this.mLoadMoreEndGone;
    }

    public final boolean isLoadEndMoreGone() {
        if (getLoadEndViewId() == 0) {
            return true;
        }
        return this.mLoadMoreEndGone;
    }

    public void setAutoDark(boolean z) {
        this.isAutoDark = z;
    }

    public final void setLoadMoreEndGone(boolean z) {
        this.mLoadMoreEndGone = z;
    }

    public void setLoadMoreStatus(int i2) {
        this.mLoadMoreStatus = i2;
    }

    public final void setLoadingViewNew() {
        this.isLoadingViewNew = true;
    }
}
