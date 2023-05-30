package com.jingdong.common.recommend;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendItemDecoration extends RecyclerView.ItemDecoration {
    private int columnMargin = DPIUtil.dip2px(2.5f);
    private int bottomMargin = DPIUtil.dip2px(5.0f);

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        super.getItemOffsets(rect, view, recyclerView, state);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        rect.bottom = this.bottomMargin;
        if (layoutParams == null) {
            return;
        }
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            if (((StaggeredGridLayoutManager.LayoutParams) layoutParams).getSpanIndex() % 2 == 0) {
                rect.left = 0;
                rect.right = this.columnMargin;
                return;
            }
            rect.left = this.columnMargin;
            rect.right = 0;
        } else if (recyclerView.getChildLayoutPosition(view) % 2 == 0) {
            rect.left = 0;
            rect.right = this.columnMargin;
        } else {
            rect.left = this.columnMargin;
            rect.right = 0;
        }
    }
}
