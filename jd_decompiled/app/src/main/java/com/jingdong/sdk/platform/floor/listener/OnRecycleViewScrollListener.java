package com.jingdong.sdk.platform.floor.listener;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.platform.floor.adapter.FloorRecyclerViewAdapter;
import com.jingdong.sdk.platform.floor.entity.BaseFloorData;
import com.jingdong.sdk.platform.floor.entity.BaseTemplateEntity;
import com.jingdong.sdk.platform.utils.PlatformTools;

/* loaded from: classes10.dex */
public class OnRecycleViewScrollListener extends RecyclerView.OnScrollListener {
    public static final String TAG = "RScrollListener";
    private FloorRecyclerViewAdapter floorRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private BaseFloorData mData;
    private boolean isDestroy = false;
    private int first = -1;
    private int firstComplete = -1;
    private int last = -1;
    private int lastComplete = -1;

    public OnRecycleViewScrollListener(RecyclerView recyclerView, FloorRecyclerViewAdapter floorRecyclerViewAdapter, BaseFloorData baseFloorData) {
        this.linearLayoutManager = null;
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            this.linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        }
        this.floorRecyclerViewAdapter = floorRecyclerViewAdapter;
        this.mData = baseFloorData;
    }

    private void checkChangedEdge(RecyclerView recyclerView, int i2, int i3) {
        FloorRecyclerViewAdapter floorRecyclerViewAdapter;
        int i4;
        if (this.linearLayoutManager == null || (floorRecyclerViewAdapter = this.floorRecyclerViewAdapter) == null || !floorRecyclerViewAdapter.isListenEdgeChange()) {
            return;
        }
        int findFirstVisibleItemPosition = this.linearLayoutManager.findFirstVisibleItemPosition();
        int findFirstCompletelyVisibleItemPosition = this.linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        int findLastVisibleItemPosition = this.linearLayoutManager.findLastVisibleItemPosition();
        int findLastCompletelyVisibleItemPosition = this.linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if ((this.first == -1 || this.firstComplete == -1 || this.last == -1 || this.lastComplete == -1) ? false : true) {
            if (findFirstCompletelyVisibleItemPosition >= 0 && findLastCompletelyVisibleItemPosition >= 0 && findLastCompletelyVisibleItemPosition >= findFirstCompletelyVisibleItemPosition) {
                for (int i5 = findFirstCompletelyVisibleItemPosition; i5 < findLastCompletelyVisibleItemPosition + 1; i5++) {
                    if (i5 < this.firstComplete || i5 > this.lastComplete) {
                        notifyChangeEdge(recyclerView, i5, false);
                    }
                }
            }
            int i6 = this.firstComplete;
            if (i6 >= 0 && (i4 = this.lastComplete) >= 0 && i4 >= i6) {
                while (i6 < this.lastComplete + 1) {
                    if ((i6 < findFirstCompletelyVisibleItemPosition || i6 > findLastCompletelyVisibleItemPosition) && i6 != findFirstVisibleItemPosition && i6 != findLastVisibleItemPosition) {
                        notifyChangeEdge(recyclerView, i6, true);
                    }
                    i6++;
                }
            }
        } else if (findFirstCompletelyVisibleItemPosition >= 0 && findLastCompletelyVisibleItemPosition >= 0 && findLastCompletelyVisibleItemPosition >= findFirstCompletelyVisibleItemPosition) {
            for (int i7 = findFirstCompletelyVisibleItemPosition; i7 < findLastCompletelyVisibleItemPosition + 1; i7++) {
                notifyChangeEdge(recyclerView, i7, false);
            }
        }
        if (findFirstCompletelyVisibleItemPosition != findFirstVisibleItemPosition && findFirstVisibleItemPosition >= 0) {
            notifyChangeEdge(recyclerView, findFirstVisibleItemPosition, true);
        }
        if (findLastCompletelyVisibleItemPosition != findLastVisibleItemPosition && findLastVisibleItemPosition >= 0) {
            notifyChangeEdge(recyclerView, findLastVisibleItemPosition, true);
        }
        this.first = findFirstVisibleItemPosition;
        this.firstComplete = findFirstCompletelyVisibleItemPosition;
        this.last = findLastVisibleItemPosition;
        this.lastComplete = findLastCompletelyVisibleItemPosition;
    }

    private void notifyChangeEdge(RecyclerView recyclerView, int i2, boolean z) {
        BaseTemplateEntity item;
        RecyclerView.ViewHolder findViewHolderForAdapterPosition;
        if (i2 >= 0 && (item = this.floorRecyclerViewAdapter.getItem(i2)) != null && item.listenEdgeChange && (findViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(i2)) != null && (findViewHolderForAdapterPosition instanceof FloorRecyclerViewAdapter.FloorViewHolder)) {
            ((FloorRecyclerViewAdapter.FloorViewHolder) findViewHolderForAdapterPosition).changeEdge(z);
        }
    }

    public void onDestroy() {
        this.isDestroy = true;
        this.mData = null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        if (PlatformTools.D) {
            PlatformTools.d(TAG, "new state = " + i2);
        }
        if (this.isDestroy) {
            return;
        }
        if (i2 == 0) {
            checkChangedEdge(recyclerView, 0, 0);
        } else if (i2 != 1 && i2 == 2) {
            checkChangedEdge(recyclerView, 0, 0);
        }
        this.mData.mScrollState = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i2, int i3) {
        checkChangedEdge(recyclerView, i2, i3);
    }
}
