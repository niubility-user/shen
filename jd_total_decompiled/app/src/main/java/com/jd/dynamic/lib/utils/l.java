package com.jd.dynamic.lib.utils;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class l extends StaggeredGridLayoutManager {
    private HashMap<Integer, Integer> a;

    public l(int i2, int i3) {
        super(i2, i3);
        this.a = new HashMap<>();
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int[] findFirstVisibleItemPositions = findFirstVisibleItemPositions(new int[getSpanCount()]);
        View findViewByPosition = findViewByPosition(findFirstVisibleItemPositions[0]);
        int i2 = 0;
        for (int i3 = 0; i3 < findFirstVisibleItemPositions[0]; i3++) {
            i2 += this.a.get(Integer.valueOf(i3)) == null ? 0 : this.a.get(Integer.valueOf(i3)).intValue();
        }
        return i2 - (findViewByPosition != null ? findViewByPosition.getTop() : 0);
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int[] findFirstVisibleItemPositions = findFirstVisibleItemPositions(new int[getSpanCount()]);
        int[] findLastVisibleItemPositions = findLastVisibleItemPositions(new int[getSpanCount()]);
        for (int i2 = findFirstVisibleItemPositions[0]; i2 < findLastVisibleItemPositions[0]; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.isFullSpan() || layoutParams.getSpanIndex() == 0) {
                    this.a.put(Integer.valueOf(i2), Integer.valueOf(childAt.getHeight()));
                } else {
                    this.a.put(Integer.valueOf(i2), 0);
                }
            }
        }
    }
}
