package com.jingdong.sdk.jdshare.cell;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes7.dex */
public class ChannelItemSpaceDecoration extends RecyclerView.ItemDecoration {
    private int a;

    public ChannelItemSpaceDecoration(int i2) {
        this.a = 0;
        this.a = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.left = this.a;
        if (recyclerView.getChildLayoutPosition(view) == 0) {
            rect.left = 0;
        }
    }
}
