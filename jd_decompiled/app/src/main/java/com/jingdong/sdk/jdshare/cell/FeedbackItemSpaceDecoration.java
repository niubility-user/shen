package com.jingdong.sdk.jdshare.cell;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class FeedbackItemSpaceDecoration extends RecyclerView.ItemDecoration {
    private int a;

    public FeedbackItemSpaceDecoration(int i2) {
        this.a = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildAdapterPosition(view) != 0) {
            rect.top = this.a;
        }
    }
}
