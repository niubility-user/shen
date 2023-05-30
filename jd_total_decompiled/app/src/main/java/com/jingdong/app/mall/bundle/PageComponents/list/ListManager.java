package com.jingdong.app.mall.bundle.PageComponents.list;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.bundle.PageComponents.list.footer.States;
import com.jingdong.app.mall.bundle.PageComponents.list.impl.ListLibRecyclerViewProxy;
import com.jingdong.app.mall.bundle.PageComponents.list.impl.TopButton;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IList;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager;
import com.jingdong.app.mall.bundle.PageComponents.list.inter.IView;
import com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack;
import com.jingdong.app.mall.bundle.PageComponents.list.net.INetController;
import com.jingdong.app.mall.bundle.PageComponents.list.net.entity.DataChangeInfo;
import org.jetbrains.annotations.NotNull;

/* loaded from: classes19.dex */
public class ListManager implements IListManager {
    private final Builder lb;

    /* loaded from: classes19.dex */
    public static class Builder implements IListBuilder {
        private final Context context;
        private IList listView;
        private INetController netController;
        private FrameLayout rootView;
        private IView topButton;
        private final IListManager listManager = new ListManager(this);
        private boolean isDistanceBottom = true;
        private boolean isDistanceTop = true;

        public Builder(Context context) {
            this.context = context;
            setListView(new ListLibRecyclerViewProxy(context));
            setTopButton((IView) new TopButton(context));
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public IListManager build() {
            FrameLayout frameLayout = new FrameLayout(this.context);
            this.rootView = frameLayout;
            frameLayout.addView(this.listView.getRootView());
            IView iView = this.topButton;
            if (iView != null && iView.getRootView() != null) {
                this.rootView.addView(this.topButton.getRootView());
            }
            return this.listManager;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public IList getListView() {
            return this.listView;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListPart
        public View getRootView() {
            return this.rootView;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public IListBuilder setDistanceBottom(boolean z) {
            this.isDistanceBottom = z;
            return this;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public IListBuilder setDistanceTop(boolean z) {
            this.isDistanceTop = z;
            return this;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public IListBuilder setListView(@NotNull IList iList) {
            iList.setIListManager(this.listManager);
            this.listView = iList;
            return this;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public Builder setNetController(INetController iNetController) {
            this.netController = iNetController;
            return this;
        }

        @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListBuilder
        public Builder setTopButton(IView iView) {
            iView.setIListManager(this.listManager);
            this.topButton = iView;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b  reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c(RecyclerView recyclerView) {
        int computeVerticalScrollRange = recyclerView.computeVerticalScrollRange();
        int computeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        int computeVerticalScrollExtent = recyclerView.computeVerticalScrollExtent();
        if ((computeVerticalScrollRange - computeVerticalScrollOffset) - computeVerticalScrollExtent < computeVerticalScrollExtent) {
            actionDistanceBottom();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkIsDistanceBottom() {
        final RecyclerView recyclerView = this.lb.getListView().getRecyclerView();
        recyclerView.post(new Runnable() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.b
            @Override // java.lang.Runnable
            public final void run() {
                ListManager.this.c(recyclerView);
            }
        });
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public void actionDistanceBottom() {
        if (this.lb.isDistanceBottom) {
            final RecyclerView.Adapter adapter = this.lb.getListView().getRecyclerView().getAdapter();
            this.lb.netController.getGeneralData().footerEntity.currentState = States.PAGING_LOADING;
            this.lb.getListView().getRecyclerView().post(new Runnable() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.a
                @Override // java.lang.Runnable
                public final void run() {
                    RecyclerView.Adapter adapter2 = RecyclerView.Adapter.this;
                    adapter2.notifyItemChanged(adapter2.getItemCount() - 1);
                }
            });
            this.lb.netController.bottomIncrease(new IFinalDataCallBack() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.ListManager.3
                @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
                public void onFail(DataChangeInfo dataChangeInfo) {
                    adapter.notifyItemChanged(dataChangeInfo.changedSize - 1);
                }

                @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
                public void onSuccess(DataChangeInfo dataChangeInfo) {
                    int i2 = dataChangeInfo.changedSize;
                    int i3 = dataChangeInfo.beforeSize;
                    int i4 = i2 - i3;
                    adapter.notifyItemChanged(i3 - 1);
                    if (i4 > 0) {
                        adapter.notifyItemRangeInserted(dataChangeInfo.beforeSize, i4);
                        if (dataChangeInfo.isEnd) {
                            return;
                        }
                        ListManager.this.checkIsDistanceBottom();
                        return;
                    }
                    int i5 = dataChangeInfo.removeOddIndex;
                    if (i5 >= 0) {
                        adapter.notifyItemRemoved(i5);
                    }
                }
            });
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public void actionDistanceTop(boolean z) {
        if (!this.lb.isDistanceTop || this.lb.topButton == null) {
            return;
        }
        if (z) {
            this.lb.topButton.show();
        } else {
            this.lb.topButton.dismiss();
        }
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public void actionPullDown() {
        this.lb.netController.fullLoad(new IFinalDataCallBack() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.ListManager.2
            @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
            public void onFail(DataChangeInfo dataChangeInfo) {
                ListManager.this.lb.getListView().getRecyclerView().getAdapter().notifyDataSetChanged();
                ListManager.this.lb.getListView().getRootView().onRefreshComplete();
            }

            @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
            public void onSuccess(DataChangeInfo dataChangeInfo) {
                ListManager.this.lb.getListView().getRecyclerView().getAdapter().notifyDataSetChanged();
                ListManager.this.lb.getListView().getRootView().onRefreshComplete();
                ListManager.this.checkIsDistanceBottom();
            }
        });
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public void actionTop() {
        this.lb.getListView().getRecyclerView().scrollToPosition(0);
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public void firstFetch() {
        this.lb.netController.firstFetch(new IFinalDataCallBack() { // from class: com.jingdong.app.mall.bundle.PageComponents.list.ListManager.1
            @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
            public void onFail(DataChangeInfo dataChangeInfo) {
                ListManager.this.lb.getListView().getRecyclerView().getAdapter().notifyDataSetChanged();
            }

            @Override // com.jingdong.app.mall.bundle.PageComponents.list.net.IFinalDataCallBack
            public void onSuccess(DataChangeInfo dataChangeInfo) {
                ListManager.this.lb.getListView().getRecyclerView().getAdapter().notifyDataSetChanged();
                ListManager.this.checkIsDistanceBottom();
            }
        });
    }

    @Override // com.jingdong.app.mall.bundle.PageComponents.list.inter.IListManager
    public IListBuilder getBuilder() {
        return this.lb;
    }

    private ListManager(Builder builder) {
        this.lb = builder;
    }
}
