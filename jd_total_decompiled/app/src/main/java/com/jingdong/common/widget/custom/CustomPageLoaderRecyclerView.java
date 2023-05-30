package com.jingdong.common.widget.custom;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.widget.custom.CustomTopButton;

/* loaded from: classes12.dex */
public class CustomPageLoaderRecyclerView extends PullToRefreshRecyclerView {
    protected int SCROLLTOSHOWNEXTPAGE;
    protected int SCROLLTOSHOWTOPPOSITION;
    private BaseActivity activity;
    private int firstItemTop;
    protected CustomTopButton.ITopButtonListener iTopButtonListener;
    protected int lastTotalItemCount;
    protected CustomBasePageLoader nextPagerLoader;
    protected View topBtn;

    /* loaded from: classes12.dex */
    private class CustomItemDecoration extends RecyclerView.ItemDecoration {
        private int div;

        public CustomItemDecoration(int i2) {
            this.div = i2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if (recyclerView != null && recyclerView.getChildAdapterPosition(view) == 0) {
                rect.top = this.div + CustomPageLoaderRecyclerView.this.firstItemTop;
            } else {
                rect.top = this.div;
            }
        }
    }

    public CustomPageLoaderRecyclerView(BaseActivity baseActivity, final CustomBasePageLoader customBasePageLoader, int i2, boolean z, View view) {
        super(baseActivity);
        this.lastTotalItemCount = 0;
        this.SCROLLTOSHOWNEXTPAGE = 4;
        this.SCROLLTOSHOWTOPPOSITION = 4;
        this.activity = baseActivity;
        this.nextPagerLoader = customBasePageLoader;
        this.topBtn = view;
        getRefreshableView().setLayoutManager(new LinearLayoutManager(baseActivity));
        getRefreshableView().addItemDecoration(new CustomItemDecoration(i2));
        if (z) {
            setOnRefresh();
        } else {
            setMode(PullToRefreshBase.Mode.DISABLED);
        }
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    CustomPageLoaderRecyclerView.this.getRefreshableView().scrollToPosition(0);
                    CustomPageLoaderRecyclerView customPageLoaderRecyclerView = CustomPageLoaderRecyclerView.this;
                    CustomTopButton.ITopButtonListener iTopButtonListener = customPageLoaderRecyclerView.iTopButtonListener;
                    if (iTopButtonListener != null) {
                        iTopButtonListener.onClick(customPageLoaderRecyclerView.getLastVisibleItemPosition(customPageLoaderRecyclerView.getRefreshableView()));
                    }
                }
            });
        }
        getRefreshableView().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i3, int i4) {
                customBasePageLoader.onScroll();
                CustomPageLoaderRecyclerView.this.checkWhetherShowNextPage(recyclerView, i4);
                CustomPageLoaderRecyclerView.this.checkWhetherTheTopBtnShow(recyclerView);
            }
        });
    }

    private int getTotalItemCount(RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getAdapter() == null) {
            return 0;
        }
        return recyclerView.getAdapter().getItemCount();
    }

    private void setOnRefresh() {
        setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() { // from class: com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView.3
            @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener
            public void onRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
                CustomPageLoaderRecyclerView customPageLoaderRecyclerView = CustomPageLoaderRecyclerView.this;
                customPageLoaderRecyclerView.lastTotalItemCount = 0;
                customPageLoaderRecyclerView.nextPagerLoader.onRefresh();
                if (CustomPageLoaderRecyclerView.this.activity != null) {
                    CustomPageLoaderRecyclerView.this.activity.post(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            CustomPageLoaderRecyclerView.this.onRefreshComplete();
                        }
                    }, 10000);
                } else {
                    CustomPageLoaderRecyclerView.this.postDelayed(new Runnable() { // from class: com.jingdong.common.widget.custom.CustomPageLoaderRecyclerView.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            CustomPageLoaderRecyclerView.this.onRefreshComplete();
                        }
                    }, 10000L);
                }
            }
        });
    }

    protected void checkWhetherShowNextPage(RecyclerView recyclerView, int i2) {
        int totalItemCount;
        if (recyclerView == null || recyclerView.getLayoutManager() == null || recyclerView.getAdapter() == null || i2 <= 0 || (totalItemCount = getTotalItemCount(recyclerView)) == this.lastTotalItemCount || getLastVisibleItemPosition(recyclerView) < totalItemCount - this.SCROLLTOSHOWNEXTPAGE) {
            return;
        }
        this.lastTotalItemCount = totalItemCount;
        this.nextPagerLoader.showNextPage();
    }

    protected void checkWhetherTheTopBtnShow(RecyclerView recyclerView) {
        if (recyclerView == null || recyclerView.getLayoutManager() == null || this.topBtn == null) {
            return;
        }
        this.topBtn.setVisibility(getLastVisibleItemPosition(recyclerView) > this.SCROLLTOSHOWTOPPOSITION ? 0 : 8);
        if (this.iTopButtonListener == null || this.topBtn.getVisibility() != 0) {
            return;
        }
        this.iTopButtonListener.onShow();
    }

    public void dragPullRefresh() {
        getRefreshableView().scrollToPosition(0);
        setRefreshing();
    }

    protected int getLastVisibleItemPosition(RecyclerView recyclerView) {
        if (recyclerView == null) {
            return 0;
        }
        return ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
    }

    public void setFirstItemTop(int i2) {
        this.firstItemTop = i2;
    }

    public void setHeaderLayout(@ColorInt int i2) {
        View childAt;
        if (getHeaderLayout() == null || getHeaderLayout().getChildCount() <= 0 || (childAt = getHeaderLayout().getChildAt(0)) == null) {
            return;
        }
        childAt.setBackgroundColor(i2);
    }

    public void setScrollToShowNextPage(int i2) {
        this.SCROLLTOSHOWNEXTPAGE = i2;
    }

    public void setScrollToShowTopPosition(int i2) {
        this.SCROLLTOSHOWTOPPOSITION = i2;
    }

    public void setTopBtnListener(CustomTopButton.ITopButtonListener iTopButtonListener) {
        this.iTopButtonListener = iTopButtonListener;
    }

    @Override // com.handmark.pulltorefresh.library.PullToRefreshBase
    public void updateUIForMode() {
        super.updateUIForMode();
    }
}
