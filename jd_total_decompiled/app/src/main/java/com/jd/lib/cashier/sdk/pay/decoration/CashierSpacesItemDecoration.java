package com.jd.lib.cashier.sdk.pay.decoration;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes14.dex */
public class CashierSpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int a;

    public CashierSpacesItemDecoration(int i2) {
        this.a = 8;
        this.a = i2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        if (recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
            rect.right = this.a;
        }
    }
}
