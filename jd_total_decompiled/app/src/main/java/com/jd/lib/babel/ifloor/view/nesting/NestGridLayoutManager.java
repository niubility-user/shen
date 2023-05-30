package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.GridLayoutManager;

/* loaded from: classes13.dex */
public class NestGridLayoutManager extends GridLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestGridLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !this.isInBottom;
    }

    @Override // com.jd.lib.babel.ifloor.view.nesting.INestLayoutManager
    public void setInBottom(boolean z) {
        this.isInBottom = z;
    }

    public NestGridLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public NestGridLayoutManager(Context context, int i2, int i3, boolean z) {
        super(context, i2, i3, z);
    }
}
