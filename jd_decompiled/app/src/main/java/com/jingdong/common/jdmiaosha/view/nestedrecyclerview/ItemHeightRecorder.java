package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class ItemHeightRecorder {
    private final SparseArray<ItemHeight> records = new SparseArray<>(0);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes5.dex */
    public static class ItemHeight {
        int height = 0;
        int top = 0;
        int topMargin = 0;

        ItemHeight() {
        }
    }

    private int getRecyclerViewItemTopMargin(View view) {
        if (view != null && view.getLayoutParams() != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof RecyclerView.LayoutParams) {
                return ((ViewGroup.MarginLayoutParams) ((RecyclerView.LayoutParams) layoutParams)).topMargin;
            }
        }
        return 0;
    }

    private void update(int i2, View view) {
        if (view != null) {
            ItemHeight itemHeight = this.records.get(i2);
            if (itemHeight == null) {
                itemHeight = new ItemHeight();
            }
            itemHeight.height = view.getHeight();
            itemHeight.top = view.getTop();
            itemHeight.topMargin = getRecyclerViewItemTopMargin(view);
            this.records.append(i2, itemHeight);
            return;
        }
        this.records.append(i2, new ItemHeight());
    }

    public int getScrollY(int i2, View view) {
        update(i2, view);
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ItemHeight itemHeight = this.records.get(i4);
            i3 += itemHeight == null ? 0 : itemHeight.height + itemHeight.topMargin;
        }
        ItemHeight itemHeight2 = this.records.get(i2);
        return (i3 - itemHeight2.top) + itemHeight2.topMargin;
    }
}
