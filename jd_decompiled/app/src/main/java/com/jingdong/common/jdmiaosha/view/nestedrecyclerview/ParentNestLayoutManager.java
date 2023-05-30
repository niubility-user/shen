package com.jingdong.common.jdmiaosha.view.nestedrecyclerview;

import android.content.Context;

/* loaded from: classes5.dex */
public class ParentNestLayoutManager extends LinearLayoutManagerFixed implements INestLayoutManager {
    private boolean isInBottom;

    public ParentNestLayoutManager(Context context) {
        super(context, 1, false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return !this.isInBottom;
    }

    @Override // com.jingdong.common.jdmiaosha.view.nestedrecyclerview.INestLayoutManager
    public void setInBottom(boolean z) {
        this.isInBottom = z;
    }
}
