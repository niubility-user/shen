package com.jd.lib.babel.ifloor.view.nesting;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;

/* loaded from: classes13.dex */
public class NestLinearLayoutManager extends LinearLayoutManager implements INestLayoutManager {
    private boolean isInBottom;

    public NestLinearLayoutManager(Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !this.isInBottom;
    }

    @Override // com.jd.lib.babel.ifloor.view.nesting.INestLayoutManager
    public void setInBottom(boolean z) {
        this.isInBottom = z;
    }

    public NestLinearLayoutManager(Context context, int i2, boolean z) {
        super(context, i2, z);
    }

    public NestLinearLayoutManager(Context context, AttributeSet attributeSet, int i2, int i3) {
        super(context, attributeSet, i2, i3);
    }
}
