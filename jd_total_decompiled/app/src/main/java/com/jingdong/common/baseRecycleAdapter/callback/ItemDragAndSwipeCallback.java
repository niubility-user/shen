package com.jingdong.common.baseRecycleAdapter.callback;

import android.graphics.Canvas;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.R;
import com.jingdong.common.baseRecycleAdapter.BaseItemDraggableAdapter;

/* loaded from: classes5.dex */
public class ItemDragAndSwipeCallback extends ItemTouchHelper.Callback {
    BaseItemDraggableAdapter mAdapter;
    float mMoveThreshold = 0.1f;
    float mSwipeThreshold = 0.7f;
    int mDragMoveFlags = 15;
    int mSwipeMoveFlags = 32;

    public ItemDragAndSwipeCallback(BaseItemDraggableAdapter baseItemDraggableAdapter) {
        this.mAdapter = baseItemDraggableAdapter;
    }

    private boolean isViewCreateByAdapter(RecyclerView.ViewHolder viewHolder) {
        int itemViewType = viewHolder.getItemViewType();
        return itemViewType == 273 || itemViewType == 546 || itemViewType == 819 || itemViewType == 1365;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        if (isViewCreateByAdapter(viewHolder)) {
            return;
        }
        View view = viewHolder.itemView;
        int i2 = R.id.BaseQuickAdapter_dragging_support;
        if (view.getTag(i2) != null && ((Boolean) viewHolder.itemView.getTag(i2)).booleanValue()) {
            this.mAdapter.onItemDragEnd(viewHolder);
            viewHolder.itemView.setTag(i2, Boolean.FALSE);
        }
        View view2 = viewHolder.itemView;
        int i3 = R.id.BaseQuickAdapter_swiping_support;
        if (view2.getTag(i3) == null || !((Boolean) viewHolder.itemView.getTag(i3)).booleanValue()) {
            return;
        }
        this.mAdapter.onItemSwipeClear(viewHolder);
        viewHolder.itemView.setTag(i3, Boolean.FALSE);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mMoveThreshold;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        if (isViewCreateByAdapter(viewHolder)) {
            return ItemTouchHelper.Callback.makeMovementFlags(0, 0);
        }
        return ItemTouchHelper.Callback.makeMovementFlags(this.mDragMoveFlags, this.mSwipeMoveFlags);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return this.mSwipeThreshold;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isItemViewSwipeEnabled() {
        return this.mAdapter.isItemSwipeEnable();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean isLongPressDragEnabled() {
        return false;
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f2, float f3, int i2, boolean z) {
        super.onChildDrawOver(canvas, recyclerView, viewHolder, f2, f3, i2, z);
        if (i2 != 1 || isViewCreateByAdapter(viewHolder)) {
            return;
        }
        View view = viewHolder.itemView;
        canvas.save();
        if (f2 > 0.0f) {
            canvas.clipRect(view.getLeft(), view.getTop(), view.getLeft() + f2, view.getBottom());
            canvas.translate(view.getLeft(), view.getTop());
        } else {
            canvas.clipRect(view.getRight() + f2, view.getTop(), view.getRight(), view.getBottom());
            canvas.translate(view.getRight() + f2, view.getTop());
        }
        this.mAdapter.onItemSwiping(canvas, viewHolder, f2, f3, z);
        canvas.restore();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return viewHolder.getItemViewType() == viewHolder2.getItemViewType();
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3, int i4, int i5) {
        super.onMoved(recyclerView, viewHolder, i2, viewHolder2, i3, i4, i5);
        this.mAdapter.onItemDragMoving(viewHolder, viewHolder2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i2) {
        if (i2 == 2 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemDragStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_dragging_support, Boolean.TRUE);
        } else if (i2 == 1 && !isViewCreateByAdapter(viewHolder)) {
            this.mAdapter.onItemSwipeStart(viewHolder);
            viewHolder.itemView.setTag(R.id.BaseQuickAdapter_swiping_support, Boolean.TRUE);
        }
        super.onSelectedChanged(viewHolder, i2);
    }

    @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i2) {
        if (isViewCreateByAdapter(viewHolder)) {
            return;
        }
        this.mAdapter.onItemSwiped(viewHolder);
    }

    public void setDragMoveFlags(int i2) {
        this.mDragMoveFlags = i2;
    }

    public void setMoveThreshold(float f2) {
        this.mMoveThreshold = f2;
    }

    public void setSwipeMoveFlags(int i2) {
        this.mSwipeMoveFlags = i2;
    }

    public void setSwipeThreshold(float f2) {
        this.mSwipeThreshold = f2;
    }
}
