package com.jingdong.app.mall.bundle.PageComponents.list.impl;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes19.dex */
public class GridItemDecoration extends RecyclerView.ItemDecoration {
    protected final int div;
    protected final int div2;
    private final int spanCount;

    public GridItemDecoration(int i2, int i3) {
        this.spanCount = i3;
        if (Build.VERSION.SDK_INT >= 21) {
            this.div = DPIUtil.dip2px(i2);
        } else {
            this.div = DPIUtil.dip2px(i2 / 2);
        }
        this.div2 = this.div / 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        int spanIndex = ((GridLayoutManager) recyclerView.getLayoutManager()).getSpanSizeLookup().getSpanIndex(((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition(), this.spanCount);
        if (spanIndex == 0) {
            rect.left = this.div;
            rect.right = this.div2;
        } else if (spanIndex == 1) {
            rect.left = this.div2;
            rect.right = this.div;
        }
        rect.bottom = this.div;
    }
}
