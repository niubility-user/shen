package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.util.SparseIntArray;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.jingdong.common.widget.NavigatorHolder;
import com.jingdong.sdk.utils.DPIUtil;

/* loaded from: classes5.dex */
public class StaggeredLayoutManagerFixed extends StaggeredGridLayoutManager {
    private SparseIntArray firstChildTop;
    private final int headChangeHeight;
    private int whiteHeadChildPosition;

    public StaggeredLayoutManagerFixed(int i2, int i3) {
        super(i2, i3);
        this.whiteHeadChildPosition = -1;
        this.headChangeHeight = NavigatorHolder.NAVI_BAR_HEIGHT;
        this.firstChildTop = new SparseIntArray();
    }

    public int getCorrectionTotalDy(int i2, int i3, int i4) {
        int i5 = this.whiteHeadChildPosition;
        if (i5 < 0) {
            return i2;
        }
        if (i3 > i5) {
            int i6 = this.headChangeHeight;
            return i2 > i6 ? i2 : i6;
        }
        int i7 = this.firstChildTop.get(i3, -1);
        return i7 >= 0 ? i7 - i4 : i2;
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
            if (this.whiteHeadChildPosition < 0) {
                int childCount = getChildCount();
                for (int i2 = 0; i2 < childCount; i2++) {
                    View childAt = getChildAt(i2);
                    if (childAt != null) {
                        if (childAt.getHeight() == 0) {
                            return;
                        }
                        this.firstChildTop.append(i2, childAt.getTop());
                        if (childAt.getBottom() >= DPIUtil.dip2px(200.0f)) {
                            this.whiteHeadChildPosition = i2;
                            return;
                        }
                    }
                }
            }
        } catch (Exception unused) {
        }
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            return super.scrollVerticallyBy(i2, recycler, state);
        } catch (Exception unused) {
            return 0;
        }
    }
}
