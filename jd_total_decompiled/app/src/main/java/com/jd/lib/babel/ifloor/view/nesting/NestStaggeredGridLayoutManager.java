package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes13.dex */
public class NestStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestStaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.recyclerview.widget.StaggeredGridLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !this.isInBottom;
    }

    @Override // com.jd.lib.babel.ifloor.view.nesting.INestLayoutManager
    public void setInBottom(boolean z) {
        this.isInBottom = z;
    }

    public NestStaggeredGridLayoutManager(int i2, int i3) {
        super(i2, i3);
    }
}
