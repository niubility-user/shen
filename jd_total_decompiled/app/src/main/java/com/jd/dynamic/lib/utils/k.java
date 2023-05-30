package com.jd.dynamic.lib.utils;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;

/* loaded from: classes13.dex */
public class k extends LinearLayoutManager {
    private HashMap<Integer, Integer> a;

    public k(Context context) {
        super(context);
        this.a = new HashMap<>();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        View findViewByPosition = findViewByPosition(findFirstVisibleItemPosition);
        int i2 = 0;
        for (int i3 = 0; i3 < findFirstVisibleItemPosition; i3++) {
            i2 += this.a.get(Integer.valueOf(i3)) == null ? 0 : this.a.get(Integer.valueOf(i3)).intValue();
        }
        return i2 - (findViewByPosition != null ? findViewByPosition.getTop() : 0);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int findLastVisibleItemPosition = findLastVisibleItemPosition();
        if (findFirstVisibleItemPosition < 0 || findLastVisibleItemPosition < 0 || findLastVisibleItemPosition < findFirstVisibleItemPosition) {
            return;
        }
        while (findFirstVisibleItemPosition < findLastVisibleItemPosition) {
            View childAt = getChildAt(findFirstVisibleItemPosition);
            if (childAt != null) {
                this.a.put(Integer.valueOf(findFirstVisibleItemPosition), Integer.valueOf(childAt.getHeight()));
            }
            findFirstVisibleItemPosition++;
        }
    }
}
