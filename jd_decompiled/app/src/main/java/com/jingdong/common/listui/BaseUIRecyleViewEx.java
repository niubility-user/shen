package com.jingdong.common.listui;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.listui.R;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BaseUIRecyleViewEx extends BaseUI {
    private int backTopYPos;
    private boolean hasStickyHeader;
    private boolean isRefreshing;
    private ILoadMore mILoadMore;
    private IRefresh mIRefresh;
    private ListItemTypeAdapter mListItemTypeAdapter;
    private LoadMoreRecyclerOnScrollListener mLoadMoreRecyclerOnScrollListener;
    private View mLoadMoreView;
    private List<OnScrollListener> mOnScrollListeners;
    private ProgressBar mProgressBar;
    private WrapRecyclerView mRecyclerView;
    private Runnable mRunnable;
    private SimpleDraweeView mTopView;
    private PullToRefreshWrapRecyclerView pullRecyclerView;
    private boolean hasTop = true;
    private boolean isPullRefresh = true;
    private boolean isCanLoadMore = true;

    /* loaded from: classes5.dex */
    public interface OnScrollListener {
        void onScroll(RecyclerView recyclerView, int i2, int i3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoHidePullState(long j2) {
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            this.mRunnable = new Runnable() { // from class: com.jingdong.common.listui.BaseUIRecyleViewEx.4
                @Override // java.lang.Runnable
                public void run() {
                    BaseUIRecyleViewEx.this.onRefreshComplete();
                }
            };
        } else {
            PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = this.pullRecyclerView;
            if (pullToRefreshWrapRecyclerView != null) {
                pullToRefreshWrapRecyclerView.removeCallbacks(runnable);
            }
        }
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView2 = this.pullRecyclerView;
        if (pullToRefreshWrapRecyclerView2 != null) {
            pullToRefreshWrapRecyclerView2.postDelayed(this.mRunnable, j2);
        }
    }

    private void initLoadMore(LinearLayoutManager linearLayoutManager) {
        LoadMoreRecyclerOnScrollListener loadMoreRecyclerOnScrollListener = new LoadMoreRecyclerOnScrollListener(linearLayoutManager) { // from class: com.jingdong.common.listui.BaseUIRecyleViewEx.3
            @Override // com.jingdong.common.listui.LoadMoreRecyclerOnScrollListener
            public boolean onLoadMoreWithFootView() {
                boolean z;
                if (BaseUIRecyleViewEx.this.mILoadMore != null) {
                    if (BaseUIRecyleViewEx.this.mLoadMoreView != null) {
                        if (BaseUIRecyleViewEx.this.mRecyclerView.containsFootView(BaseUIRecyleViewEx.this.mLoadMoreView)) {
                            z = false;
                        } else {
                            BaseUIRecyleViewEx baseUIRecyleViewEx = BaseUIRecyleViewEx.this;
                            baseUIRecyleViewEx.addFootView(baseUIRecyleViewEx.mLoadMoreView);
                            z = true;
                        }
                        BaseUIRecyleViewEx.this.isPullRefresh = false;
                        if (BaseUIRecyleViewEx.this.isCanLoadMore) {
                            BaseUIRecyleViewEx.this.mILoadMore.loadMore();
                        }
                        return z;
                    }
                    throw new RuntimeException("please set LoadMoreFootView...eg LoadMoreView");
                }
                return false;
            }

            @Override // com.jingdong.common.listui.LoadMoreRecyclerOnScrollListener
            public void onScroll(RecyclerView recyclerView, int i2, int i3, int i4) {
                if (BaseUIRecyleViewEx.this.hasTop) {
                    if (i2 < 8 || i4 <= 0) {
                        if (i2 < 8 && i4 < 0 && BaseUIRecyleViewEx.this.mTopView.getVisibility() == 0) {
                            BaseUIRecyleViewEx.this.mTopView.setVisibility(8);
                        }
                    } else if (BaseUIRecyleViewEx.this.mTopView.getVisibility() == 8) {
                        BaseUIRecyleViewEx.this.mTopView.setVisibility(0);
                    }
                }
                if (BaseUIRecyleViewEx.this.mOnScrollListeners != null) {
                    Iterator it = BaseUIRecyleViewEx.this.mOnScrollListeners.iterator();
                    while (it.hasNext()) {
                        ((OnScrollListener) it.next()).onScroll(recyclerView, i3, i4);
                    }
                }
            }
        };
        this.mLoadMoreRecyclerOnScrollListener = loadMoreRecyclerOnScrollListener;
        this.mRecyclerView.addOnScrollListener(loadMoreRecyclerOnScrollListener);
    }

    public void addFootView(View view) {
        WrapRecyclerView wrapRecyclerView;
        if (view == null || (wrapRecyclerView = this.mRecyclerView) == null || wrapRecyclerView.containsFootView(view)) {
            return;
        }
        this.mRecyclerView.addFootView(view);
        this.mRecyclerView.getWrapAdapter().notifyItemChanged(getItemCount() - 1);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        WrapRecyclerView wrapRecyclerView;
        if (itemDecoration == null || (wrapRecyclerView = this.mRecyclerView) == null) {
            return;
        }
        wrapRecyclerView.addItemDecoration(itemDecoration);
    }

    public void addList(int i2, List<ListItem> list) {
        ListItemTypeAdapter listItemTypeAdapter = this.mListItemTypeAdapter;
        if (listItemTypeAdapter != null) {
            listItemTypeAdapter.addList(i2, list);
        }
    }

    public void clear() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView != null) {
            wrapRecyclerView.getRecycledViewPool().clear();
        }
    }

    @Override // com.jingdong.common.listui.BaseUI
    protected void ensureUI(Context context, FrameLayout frameLayout) {
        this.pullRecyclerView = new PullToRefreshWrapRecyclerView(context);
        frameLayout.addView(this.pullRecyclerView, new FrameLayout.LayoutParams(-1, -1));
        this.mTopView = new SimpleDraweeView(context);
        if (this.hasTop) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            int dip2px = DPIUtil.dip2px(10.0f);
            int i2 = this.backTopYPos;
            if (i2 == 0) {
                i2 = dip2px;
            }
            layoutParams.setMargins(0, 0, dip2px, i2);
            layoutParams.gravity = 85;
            int dip2px2 = DPIUtil.dip2px(12.0f);
            this.mTopView.setPadding(dip2px2, dip2px2, dip2px2, dip2px2);
            this.mTopView.setBackgroundResource(R.drawable.liui_button_m_01);
            this.mTopView.setVisibility(8);
            this.mTopView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.listui.BaseUIRecyleViewEx.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseUIRecyleViewEx.this.gotoTop();
                }
            });
            frameLayout.addView(this.mTopView, layoutParams);
        }
        this.pullRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<WrapRecyclerView>() { // from class: com.jingdong.common.listui.BaseUIRecyleViewEx.2
            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
            public void onRefresh(PullToRefreshBase<WrapRecyclerView> pullToRefreshBase) {
                if (BaseUIRecyleViewEx.this.mIRefresh != null) {
                    BaseUIRecyleViewEx.this.isPullRefresh = true;
                    BaseUIRecyleViewEx.this.mIRefresh.refresh();
                }
                BaseUIRecyleViewEx.this.autoHidePullState(10000L);
            }
        });
        WrapRecyclerView refreshableView = this.pullRecyclerView.getRefreshableView();
        this.mRecyclerView = refreshableView;
        if (refreshableView == null) {
            return;
        }
        WrapContentLinearLayoutManager wrapContentLinearLayoutManager = new WrapContentLinearLayoutManager(context);
        this.mRecyclerView.setLayoutManager(wrapContentLinearLayoutManager);
        this.mRecyclerView.setPersistentDrawingCache(0);
        this.mRecyclerView.setNestedScrollingEnabled(true);
        ListItemTypeAdapter listItemTypeAdapter = new ListItemTypeAdapter(context, null);
        this.mListItemTypeAdapter = listItemTypeAdapter;
        this.mRecyclerView.setAdapter(listItemTypeAdapter);
        initLoadMore(wrapContentLinearLayoutManager);
    }

    public int getItemCount() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null) {
            if (OKLog.D) {
                throw new RuntimeException("Please invoke ensureUI before");
            }
            return 0;
        }
        return wrapRecyclerView.getWrapAdapter().getItemCount();
    }

    public List<ListItem> getList() {
        ListItemTypeAdapter listItemTypeAdapter = this.mListItemTypeAdapter;
        return listItemTypeAdapter == null ? new ArrayList() : listItemTypeAdapter.getList();
    }

    public ListItemTypeAdapter getListItemTypeAdapter() {
        return this.mListItemTypeAdapter;
    }

    public int getListSize() {
        ListItemTypeAdapter listItemTypeAdapter = this.mListItemTypeAdapter;
        if (listItemTypeAdapter == null) {
            return 0;
        }
        return listItemTypeAdapter.getList().size();
    }

    public PullToRefreshWrapRecyclerView getPullRecyclerView() {
        return this.pullRecyclerView;
    }

    public WrapRecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void gotoTop() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView != null) {
            wrapRecyclerView.scrollToPosition(0);
            this.mRecyclerView.postInvalidate();
            this.mTopView.setVisibility(8);
        }
    }

    public void hideProgress() {
        if (this.mProgressBar == null || getFrameContainer() == null) {
            return;
        }
        getFrameContainer().removeView(this.mProgressBar);
        this.mProgressBar = null;
    }

    public boolean isPullRefresh() {
        return this.isPullRefresh;
    }

    public void notifyDataSetChanged() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.getWrapAdapter() == null || this.mRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.getWrapAdapter().notifyDataSetChanged();
        removeError();
    }

    public void notifyItemRangeChanged(int i2, int i3) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.getWrapAdapter().notifyItemRangeChanged(i2, i3);
        removeError();
    }

    public void onRefreshComplete() {
        hideProgress();
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = this.pullRecyclerView;
        if (pullToRefreshWrapRecyclerView != null) {
            pullToRefreshWrapRecyclerView.onRefreshComplete();
        }
        this.isRefreshing = false;
    }

    public void removeFootView(View view) {
        WrapRecyclerView wrapRecyclerView;
        if (view == null || (wrapRecyclerView = this.mRecyclerView) == null) {
            return;
        }
        wrapRecyclerView.removeFootView(view);
    }

    public boolean removeItem(ListItem listItem) {
        return getList().remove(listItem);
    }

    public void scrollToPosition(int i2) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.getWrapAdapter() == null || this.mRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.scrollToPosition(i2);
    }

    public void setBackTopYPos(int i2) {
        this.backTopYPos = i2;
    }

    public void setCanLoadMore(boolean z) {
        this.isCanLoadMore = z;
    }

    public void setHasStickyHeader(boolean z) {
        this.hasStickyHeader = z;
    }

    public void setHasTop(boolean z) {
        this.hasTop = z;
    }

    public void setILoadMore(ILoadMore iLoadMore) {
        this.mILoadMore = iLoadMore;
    }

    public void setIRefresh(IRefresh iRefresh) {
        this.mIRefresh = iRefresh;
    }

    public void setList(List<ListItem> list) {
        ListItemTypeAdapter listItemTypeAdapter = this.mListItemTypeAdapter;
        if (listItemTypeAdapter != null) {
            listItemTypeAdapter.setList(list);
        }
        LoadMoreRecyclerOnScrollListener loadMoreRecyclerOnScrollListener = this.mLoadMoreRecyclerOnScrollListener;
        if (loadMoreRecyclerOnScrollListener != null) {
            loadMoreRecyclerOnScrollListener.updateLoadingState(false);
        }
    }

    public void setListItemHasDevider(int i2, boolean z) {
        if (getListSize() > i2) {
            getList().get(i2).setHasDevider(z);
        }
    }

    public void setLoadMoreView(View view) {
        this.mLoadMoreView = view;
    }

    public void setLoading(boolean z) {
        LoadMoreRecyclerOnScrollListener loadMoreRecyclerOnScrollListener = this.mLoadMoreRecyclerOnScrollListener;
        if (loadMoreRecyclerOnScrollListener != null) {
            loadMoreRecyclerOnScrollListener.setLoading(z);
        }
    }

    public void setMode(PullToRefreshBase.Mode mode) {
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = this.pullRecyclerView;
        if (pullToRefreshWrapRecyclerView != null) {
            pullToRefreshWrapRecyclerView.setMode(mode);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (this.mOnScrollListeners == null) {
            this.mOnScrollListeners = new ArrayList();
        }
        this.mOnScrollListeners.add(onScrollListener);
    }

    public void setRefreshing() {
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView;
        if (this.isRefreshing || (pullToRefreshWrapRecyclerView = this.pullRecyclerView) == null) {
            return;
        }
        this.isRefreshing = true;
        pullToRefreshWrapRecyclerView.setRefreshing();
        autoHidePullState(Final.FIVE_SECOND);
    }

    public void setVisibleThreshold(int i2) {
        LoadMoreRecyclerOnScrollListener loadMoreRecyclerOnScrollListener = this.mLoadMoreRecyclerOnScrollListener;
        if (loadMoreRecyclerOnScrollListener != null) {
            loadMoreRecyclerOnScrollListener.setVisibleThreshold(i2);
        }
    }

    public void showProgress() {
        if (getFrameContainer() == null || this.mProgressBar != null) {
            return;
        }
        this.mProgressBar = new JDProgressBar(getFrameContainer().getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mProgressBar.getLayoutParams().width, this.mProgressBar.getLayoutParams().height);
        layoutParams.gravity = 17;
        getFrameContainer().addView(this.mProgressBar, layoutParams);
    }

    public void addList(List<ListItem> list) {
        ListItemTypeAdapter listItemTypeAdapter = this.mListItemTypeAdapter;
        if (listItemTypeAdapter != null) {
            listItemTypeAdapter.addList(list);
        }
    }
}
