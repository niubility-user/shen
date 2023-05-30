package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshRecyclerView;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IList;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager;

/* loaded from: classes19.dex */
public class ListLibRecyclerViewProxy implements IList {
    protected final Context context;
    private int lastTotalItemCount = 0;
    private PullToRefreshRecyclerView listLibRecyclerView;
    private IListManager mListManager;

    /* loaded from: classes19.dex */
    protected static class RefreshListener implements PullToRefreshBase.OnRefreshListener2<RecyclerView> {
        private final ListLibRecyclerViewProxy thisProxy;

        public RefreshListener(ListLibRecyclerViewProxy listLibRecyclerViewProxy) {
            this.thisProxy = listLibRecyclerViewProxy;
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
        public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
            ListLibRecyclerViewProxy listLibRecyclerViewProxy = this.thisProxy;
            if (listLibRecyclerViewProxy == null) {
                return;
            }
            listLibRecyclerViewProxy.onPullDownReleased(pullToRefreshBase);
        }

        @Override // com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2
        public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
            ListLibRecyclerViewProxy listLibRecyclerViewProxy = this.thisProxy;
            if (listLibRecyclerViewProxy == null) {
                return;
            }
            listLibRecyclerViewProxy.onPullUpReleased(pullToRefreshBase);
        }
    }

    public ListLibRecyclerViewProxy(Context context) {
        this.context = context;
        getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.impl.ListLibRecyclerViewProxy.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                if (ListLibRecyclerViewProxy.this.mListManager == null || recyclerView == null) {
                    return;
                }
                int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
                int computeVerticalScrollExtent = recyclerView.computeVerticalScrollExtent();
                ListLibRecyclerViewProxy.this.checkWhetherShowNextPage(recyclerView, i3, computeVerticalScrollOffset, computeVerticalScrollExtent);
                ListLibRecyclerViewProxy.this.checkDistanceTop(computeVerticalScrollOffset, computeVerticalScrollExtent);
            }
        });
        this.listLibRecyclerView.setOnRefreshListener(new RefreshListener(this));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void b() {
        PullToRefreshRecyclerView pullToRefreshRecyclerView = this.listLibRecyclerView;
        if (pullToRefreshRecyclerView != null) {
            pullToRefreshRecyclerView.onRefreshComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void d() {
        PullToRefreshRecyclerView pullToRefreshRecyclerView = this.listLibRecyclerView;
        if (pullToRefreshRecyclerView != null) {
            pullToRefreshRecyclerView.onRefreshComplete();
        }
    }

    protected void checkDistanceTop(int i2, int i3) {
        this.mListManager.actionDistanceTop(i2 > i3);
    }

    protected void checkWhetherShowNextPage(RecyclerView recyclerView, int i2, int i3, int i4) {
        int itemCount;
        if (recyclerView == null || recyclerView.getAdapter() == null || i2 <= 0 || (itemCount = recyclerView.getAdapter().getItemCount()) <= this.lastTotalItemCount) {
            return;
        }
        int computeVerticalScrollRange = (recyclerView.computeVerticalScrollRange() - i3) - i4;
        if (i3 <= 0 || computeVerticalScrollRange >= i4) {
            return;
        }
        this.lastTotalItemCount = itemCount;
        this.mListManager.actionDistanceBottom();
    }

    public ListLibRecyclerView getListLibRecyclerView() {
        return new ListLibRecyclerView(this.context);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IList
    public RecyclerView getRecyclerView() {
        if (this.listLibRecyclerView == null) {
            this.listLibRecyclerView = getListLibRecyclerView();
        }
        return this.listLibRecyclerView.getRefreshableView();
    }

    public void onPullDownReleased(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        this.lastTotalItemCount = 0;
        IListManager iListManager = this.mListManager;
        if (iListManager != null) {
            iListManager.actionPullDown();
        }
        PullToRefreshRecyclerView pullToRefreshRecyclerView = this.listLibRecyclerView;
        if (pullToRefreshRecyclerView != null) {
            pullToRefreshRecyclerView.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.impl.a
                @Override // java.lang.Runnable
                public final void run() {
                    ListLibRecyclerViewProxy.this.b();
                }
            }, 10000L);
        }
    }

    public void onPullUpReleased(PullToRefreshBase<RecyclerView> pullToRefreshBase) {
        PullToRefreshRecyclerView pullToRefreshRecyclerView = this.listLibRecyclerView;
        if (pullToRefreshRecyclerView != null) {
            pullToRefreshRecyclerView.postDelayed(new Runnable() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.impl.b
                @Override // java.lang.Runnable
                public final void run() {
                    ListLibRecyclerViewProxy.this.d();
                }
            }, 10000L);
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.ISetting
    public void setIListManager(IListManager iListManager) {
        this.mListManager = iListManager;
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListPart
    public PullToRefreshBase getRootView() {
        return this.listLibRecyclerView;
    }
}
