package com.jingdong.common.baseRecycleAdapter.listener;

import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public interface OnItemDragListener {
    void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i2);

    void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i2, RecyclerView.ViewHolder viewHolder2, int i3);

    void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i2);
}
