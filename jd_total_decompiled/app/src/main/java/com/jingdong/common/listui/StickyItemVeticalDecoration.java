package com.jingdong.common.listui;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.oklog.OKLog;
import java.util.List;
import java.util.Stack;

/* loaded from: classes5.dex */
public class StickyItemVeticalDecoration {
    private BaseUIRecyleViewEx mBaseUIRecyleViewEx;
    private FrameLayout mHeaderContainer;
    private int mHeaderHeight = -1;
    private Stack<Integer> stickyHeaderPositionStack = new Stack<>();
    private SparseArray<RecyclerView.ViewHolder> mViewHolderCache = new SparseArray<>();

    public StickyItemVeticalDecoration(ViewGroup viewGroup, BaseUIRecyleViewEx baseUIRecyleViewEx) {
        this.mBaseUIRecyleViewEx = baseUIRecyleViewEx;
        if (OKLog.D) {
            if (baseUIRecyleViewEx.getListItemTypeAdapter() instanceof ListItemTypeAdapter) {
                if (!(this.mBaseUIRecyleViewEx.getRecyclerView().getLayoutManager() instanceof LinearLayoutManager)) {
                    throw new RuntimeException("Your RecyclerView.LayoutManager should be the type of LinearLayoutManager.");
                }
            } else {
                throw new RuntimeException("Your RecyclerView.Adapter should be the type of ListItemTypeAdapter.");
            }
        }
        initStickyHeader(viewGroup, (LinearLayoutManager) baseUIRecyleViewEx.getRecyclerView().getLayoutManager());
        listenDataAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearHeaderView() {
        FrameLayout frameLayout = this.mHeaderContainer;
        if (frameLayout == null) {
            return;
        }
        frameLayout.removeAllViews();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int findFirstVisibleStickyHeaderPosition(List<ListItem> list, int i2) {
        BaseUIRecyleViewEx baseUIRecyleViewEx = this.mBaseUIRecyleViewEx;
        if (baseUIRecyleViewEx == null) {
            return i2;
        }
        int listSize = baseUIRecyleViewEx.getListSize();
        while (i2 < listSize && !list.get(i2).isSticky()) {
            i2++;
        }
        return i2;
    }

    private void initStickyHeader(ViewGroup viewGroup, final LinearLayoutManager linearLayoutManager) {
        FrameLayout frameLayout = new FrameLayout(viewGroup.getContext());
        this.mHeaderContainer = frameLayout;
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        viewGroup.addView(this.mHeaderContainer);
        this.mBaseUIRecyleViewEx.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.jingdong.common.listui.StickyItemVeticalDecoration.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
                super.onScrollStateChanged(recyclerView, i2);
                if (StickyItemVeticalDecoration.this.mHeaderHeight == -1) {
                    StickyItemVeticalDecoration stickyItemVeticalDecoration = StickyItemVeticalDecoration.this;
                    stickyItemVeticalDecoration.mHeaderHeight = stickyItemVeticalDecoration.mHeaderContainer.getHeight();
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
                super.onScrolled(recyclerView, i2, i3);
                if (StickyItemVeticalDecoration.this.mBaseUIRecyleViewEx == null || StickyItemVeticalDecoration.this.mHeaderHeight == -1 || StickyItemVeticalDecoration.this.mHeaderContainer == null || StickyItemVeticalDecoration.this.mBaseUIRecyleViewEx.getListItemTypeAdapter() == null) {
                    return;
                }
                List<ListItem> list = StickyItemVeticalDecoration.this.mBaseUIRecyleViewEx.getList();
                if (StickyItemVeticalDecoration.this.stickyHeaderPositionStack.isEmpty()) {
                    StickyItemVeticalDecoration.this.stickyHeaderPositionStack.push(Integer.valueOf(StickyItemVeticalDecoration.this.findFirstVisibleStickyHeaderPosition(list, 0)));
                }
                int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (findFirstVisibleItemPosition == -1) {
                    return;
                }
                StickyItemVeticalDecoration stickyItemVeticalDecoration = StickyItemVeticalDecoration.this;
                int intValue = findFirstVisibleItemPosition == 0 ? ((Integer) stickyItemVeticalDecoration.stickyHeaderPositionStack.peek()).intValue() : stickyItemVeticalDecoration.findFirstVisibleStickyHeaderPosition(list, findFirstVisibleItemPosition);
                int intValue2 = ((Integer) StickyItemVeticalDecoration.this.stickyHeaderPositionStack.peek()).intValue();
                int i4 = intValue + 1;
                if (list.get(i4).isSticky()) {
                    intValue = i4;
                }
                View findViewByPosition = linearLayoutManager.findViewByPosition(intValue);
                if (findViewByPosition == null) {
                    return;
                }
                int top = findViewByPosition.getTop();
                if (top > 0 && top <= StickyItemVeticalDecoration.this.mHeaderHeight) {
                    StickyItemVeticalDecoration.this.mHeaderContainer.setY(-(StickyItemVeticalDecoration.this.mHeaderHeight - top));
                    if (intValue == intValue2) {
                        StickyItemVeticalDecoration.this.stickyHeaderPositionStack.pop();
                        if (StickyItemVeticalDecoration.this.stickyHeaderPositionStack.isEmpty()) {
                            StickyItemVeticalDecoration.this.clearHeaderView();
                        } else {
                            StickyItemVeticalDecoration stickyItemVeticalDecoration2 = StickyItemVeticalDecoration.this;
                            stickyItemVeticalDecoration2.updateHeaderView(((Integer) stickyItemVeticalDecoration2.stickyHeaderPositionStack.peek()).intValue());
                        }
                    }
                } else if (top <= 0) {
                    StickyItemVeticalDecoration.this.mHeaderContainer.setY(0.0f);
                    StickyItemVeticalDecoration.this.updateHeaderView(findFirstVisibleItemPosition);
                } else if (top > StickyItemVeticalDecoration.this.mHeaderHeight) {
                    StickyItemVeticalDecoration.this.mHeaderContainer.setY(0.0f);
                    if (intValue == intValue2) {
                        StickyItemVeticalDecoration.this.stickyHeaderPositionStack.pop();
                        if (StickyItemVeticalDecoration.this.stickyHeaderPositionStack.isEmpty()) {
                            StickyItemVeticalDecoration.this.clearHeaderView();
                        } else {
                            StickyItemVeticalDecoration stickyItemVeticalDecoration3 = StickyItemVeticalDecoration.this;
                            stickyItemVeticalDecoration3.updateHeaderView(((Integer) stickyItemVeticalDecoration3.stickyHeaderPositionStack.peek()).intValue());
                        }
                    }
                }
                if (intValue <= intValue2) {
                    if (intValue < intValue2) {
                        StickyItemVeticalDecoration.this.stickyHeaderPositionStack.pop();
                        return;
                    }
                    return;
                }
                while (true) {
                    intValue2++;
                    if (intValue2 > intValue) {
                        return;
                    }
                    if (StickyItemVeticalDecoration.this.mBaseUIRecyleViewEx.getList().get(intValue2).isSticky()) {
                        StickyItemVeticalDecoration.this.stickyHeaderPositionStack.push(Integer.valueOf(intValue2));
                    }
                }
            }
        });
    }

    private void listenDataAdapter() {
        BaseUIRecyleViewEx baseUIRecyleViewEx = this.mBaseUIRecyleViewEx;
        if (baseUIRecyleViewEx == null || baseUIRecyleViewEx.getListItemTypeAdapter() == null) {
            return;
        }
        this.mBaseUIRecyleViewEx.getListItemTypeAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.jingdong.common.listui.StickyItemVeticalDecoration.2
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                StickyItemVeticalDecoration.this.onClearAll();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i2, int i3) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeChanged(int i2, int i3, Object obj) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeInserted(int i2, int i3) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeMoved(int i2, int i3, int i4) {
            }

            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onItemRangeRemoved(int i2, int i3) {
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHeaderView(int i2) {
        BaseUIRecyleViewEx baseUIRecyleViewEx = this.mBaseUIRecyleViewEx;
        if (baseUIRecyleViewEx == null || baseUIRecyleViewEx.getListItemTypeAdapter() == null || this.mHeaderContainer == null) {
            return;
        }
        int itemViewType = this.mBaseUIRecyleViewEx.getListItemTypeAdapter().getItemViewType(i2);
        clearHeaderView();
        ListItem viewItem = this.mBaseUIRecyleViewEx.getListItemTypeAdapter().getViewItem(itemViewType);
        RecyclerView.ViewHolder viewHolder = this.mViewHolderCache.get(itemViewType);
        if (viewHolder == null) {
            viewHolder = viewItem.onCreateViewHolder(LayoutInflater.from(this.mHeaderContainer.getContext()), this.mHeaderContainer, itemViewType);
            this.mViewHolderCache.put(itemViewType, viewHolder);
        }
        if (viewHolder != null) {
            this.mHeaderContainer.addView(viewHolder.itemView);
            this.mHeaderHeight = this.mHeaderContainer.getHeight();
            viewItem.onBindViewHolder(this.mHeaderContainer.getContext(), viewHolder, i2);
        }
    }

    public void onClearAll() {
        this.stickyHeaderPositionStack.clear();
        clearHeaderView();
    }
}
