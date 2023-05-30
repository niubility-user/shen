package com.jingdong.common.baseRecycleAdapter.listener;

import android.graphics.Canvas;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public interface OnItemSwipeListener {
    void clearView(RecyclerView.ViewHolder viewHolder, int i2);

    void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float f2, float f3, boolean z);

    void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int i2);

    void onItemSwiped(RecyclerView.ViewHolder viewHolder, int i2);
}
