package com.jingdong.common.listui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.jingdong.common.jdmiaosha.utils.cache.Final;
import com.jingdong.common.listui.ListItemTypeAdapter;
import java.util.List;

/* loaded from: classes5.dex */
public class PinterestRecycleView extends FrameLayout {
    private boolean isPullEnd;
    private boolean isRefreshing;
    private IPinterestHelper mPinterestHelper;
    private WrapRecyclerView mRecyclerView;
    private Runnable mRunnable;
    private ListItemTypeAdapter mTypeAdapter;
    private PullToRefreshWrapRecyclerView pullRecyclerView;
    private int spanCount;

    /* loaded from: classes5.dex */
    public interface IPinterestHelper {
        void loadMore();

        void refresh();
    }

    public PinterestRecycleView(@NonNull Context context) {
        super(context);
        this.spanCount = 2;
        initView();
    }

    private void autoHidePullState(long j2) {
        Runnable runnable = this.mRunnable;
        if (runnable == null) {
            this.mRunnable = new Runnable() { // from class: com.jingdong.common.listui.PinterestRecycleView.3
                @Override // java.lang.Runnable
                public void run() {
                    PinterestRecycleView.this.onRefreshComplete();
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

    private void initOnScrollListener() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null) {
            return;
        }
        wrapRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.listui.PinterestRecycleView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (PinterestRecycleView.this.mRecyclerView == null || recyclerView.getChildCount() == 0) {
                    return;
                }
                if (i2 == 0) {
                    RecyclerView.LayoutManager layoutManager = PinterestRecycleView.this.mRecyclerView.getLayoutManager();
                    if (!PinterestRecycleView.this.isPullEnd || !(layoutManager instanceof StaggeredGridLayoutManager)) {
                        if (PinterestRecycleView.this.isPullEnd && (layoutManager instanceof LinearLayoutManager)) {
                            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                            int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                            if (findLastVisibleItemPosition - 1 == linearLayoutManager.getItemCount() && linearLayoutManager.findViewByPosition(findLastVisibleItemPosition).getBottom() <= PinterestRecycleView.this.getHeight() && PinterestRecycleView.this.mPinterestHelper != null) {
                                PinterestRecycleView.this.mPinterestHelper.loadMore();
                            }
                        }
                    } else {
                        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
                        int columnCountForAccessibility = staggeredGridLayoutManager.getColumnCountForAccessibility(null, null);
                        int[] iArr = new int[columnCountForAccessibility];
                        staggeredGridLayoutManager.findLastVisibleItemPositions(iArr);
                        int i3 = 0;
                        while (true) {
                            if (i3 >= columnCountForAccessibility) {
                                break;
                            } else if (iArr[i3] < staggeredGridLayoutManager.getItemCount() - columnCountForAccessibility || staggeredGridLayoutManager.findViewByPosition(iArr[i3]).getBottom() > PinterestRecycleView.this.getHeight()) {
                                i3++;
                            } else if (PinterestRecycleView.this.mPinterestHelper != null) {
                                PinterestRecycleView.this.mPinterestHelper.loadMore();
                            }
                        }
                    }
                }
                super.onScrollStateChanged(recyclerView, i2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                PinterestRecycleView.this.isPullEnd = i3 > 0;
            }
        });
    }

    private void initView() {
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = new PullToRefreshWrapRecyclerView(getContext());
        this.pullRecyclerView = pullToRefreshWrapRecyclerView;
        pullToRefreshWrapRecyclerView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        addView(this.pullRecyclerView);
        WrapRecyclerView refreshableView = this.pullRecyclerView.getRefreshableView();
        this.mRecyclerView = refreshableView;
        refreshableView.setPersistentDrawingCache(0);
        this.mRecyclerView.setNestedScrollingEnabled(true);
        ListItemTypeAdapter listItemTypeAdapter = new ListItemTypeAdapter(getContext(), null);
        this.mTypeAdapter = listItemTypeAdapter;
        this.mRecyclerView.setAdapter(listItemTypeAdapter);
        initOnScrollListener();
        this.pullRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<WrapRecyclerView>() { // from class: com.jingdong.common.listui.PinterestRecycleView.1
            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
            public void onRefresh(PullToRefreshBase<WrapRecyclerView> pullToRefreshBase) {
                if (PinterestRecycleView.this.mPinterestHelper != null) {
                    PinterestRecycleView.this.mPinterestHelper.refresh();
                }
            }
        });
    }

    public void addFootView(View view, boolean z) {
        WrapRecyclerView wrapRecyclerView;
        if (view == null || (wrapRecyclerView = this.mRecyclerView) == null) {
            return;
        }
        if (z) {
            if (wrapRecyclerView.containsFootView(view)) {
                return;
            }
            this.mRecyclerView.addFootView(view);
            return;
        }
        wrapRecyclerView.addFootView(view);
    }

    public void addHeadView(View view, boolean z) {
        WrapRecyclerView wrapRecyclerView;
        if (view == null || (wrapRecyclerView = this.mRecyclerView) == null) {
            return;
        }
        if (z) {
            if (wrapRecyclerView.containsHeadView(view)) {
                return;
            }
            this.mRecyclerView.addHeaderView(view);
            return;
        }
        wrapRecyclerView.addHeaderView(view);
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null) {
            return;
        }
        wrapRecyclerView.addItemDecoration(itemDecoration);
    }

    public void addList(List<ListItem> list) {
        ListItemTypeAdapter listItemTypeAdapter = this.mTypeAdapter;
        if (listItemTypeAdapter != null) {
            listItemTypeAdapter.addList(list);
        }
    }

    public void clearAdapterData() {
        ListItemTypeAdapter listItemTypeAdapter = this.mTypeAdapter;
        if (listItemTypeAdapter == null) {
            return;
        }
        listItemTypeAdapter.getList().clear();
    }

    public int getHeaderSize() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.getWrapAdapter() == null) {
            return 0;
        }
        return this.mRecyclerView.getWrapAdapter().getHeadersCount();
    }

    public int getListSize() {
        ListItemTypeAdapter listItemTypeAdapter = this.mTypeAdapter;
        if (listItemTypeAdapter == null) {
            return 0;
        }
        return listItemTypeAdapter.getList().size();
    }

    public ListItemTypeAdapter getMutiTypeAdapter() {
        return this.mTypeAdapter;
    }

    public WrapRecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public int getTotalCount() {
        ListItemTypeAdapter listItemTypeAdapter;
        if (this.mRecyclerView == null || (listItemTypeAdapter = this.mTypeAdapter) == null) {
            return 0;
        }
        return listItemTypeAdapter.getList().size() + this.mRecyclerView.getWrapAdapter().getHeadersCount() + this.mRecyclerView.getWrapAdapter().getFootersCount();
    }

    public void gotoTop() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView != null) {
            wrapRecyclerView.scrollToPosition(0);
            this.mRecyclerView.postInvalidate();
        }
    }

    public void initDefaultLayoutManager() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(this.spanCount, 1);
        staggeredGridLayoutManager.setGapStrategy(0);
        setLayoutManager(staggeredGridLayoutManager);
        this.mRecyclerView.setItemAnimator(null);
    }

    public void notifyDataSetChanged() {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.getWrapAdapter() == null || this.mRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.getWrapAdapter().notifyDataSetChanged();
    }

    public void notifyItemInserted(int i2) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.getWrapAdapter() == null || this.mRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.getWrapAdapter().notifyItemInserted(i2);
    }

    public void notifyItemRangeChanged(int i2, int i3) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null || wrapRecyclerView.isComputingLayout()) {
            return;
        }
        this.mRecyclerView.getWrapAdapter().notifyItemRangeChanged(i2, i3);
    }

    public void onRefreshComplete() {
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

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null) {
            return;
        }
        wrapRecyclerView.setLayoutManager(layoutManager);
    }

    public void setList(List<ListItem> list) {
        ListItemTypeAdapter listItemTypeAdapter = this.mTypeAdapter;
        if (listItemTypeAdapter == null) {
            return;
        }
        listItemTypeAdapter.setList(list);
    }

    public void setMode(PullToRefreshBase.Mode mode) {
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = this.pullRecyclerView;
        if (pullToRefreshWrapRecyclerView != null) {
            pullToRefreshWrapRecyclerView.setMode(mode);
        }
    }

    public void setOnItemClickListener(ListItemTypeAdapter.OnListItemClickListener onListItemClickListener) {
        ListItemTypeAdapter listItemTypeAdapter = this.mTypeAdapter;
        if (listItemTypeAdapter == null) {
            return;
        }
        listItemTypeAdapter.setOnItemClickListener(onListItemClickListener);
    }

    public void setOnPullEventListener(PullToRefreshBase.OnPullEventListener onPullEventListener) {
        PullToRefreshWrapRecyclerView pullToRefreshWrapRecyclerView = this.pullRecyclerView;
        if (pullToRefreshWrapRecyclerView != null) {
            pullToRefreshWrapRecyclerView.setOnPullEventListener(onPullEventListener);
        }
    }

    public void setOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        WrapRecyclerView wrapRecyclerView = this.mRecyclerView;
        if (wrapRecyclerView == null) {
            return;
        }
        wrapRecyclerView.addOnScrollListener(onScrollListener);
    }

    public void setPinterestHelper(IPinterestHelper iPinterestHelper) {
        this.mPinterestHelper = iPinterestHelper;
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

    public void setSpanCount(int i2) {
        this.spanCount = i2;
    }

    public PinterestRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.spanCount = 2;
        initView();
    }

    public PinterestRecycleView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.spanCount = 2;
        initView();
    }
}
