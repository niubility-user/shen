package com.jingdong.common.widget.custom;

import android.content.Context;
import android.util.AttributeSet;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes12.dex */
public class JingDouActiveRecyclerView extends RecyclerView {
    public JingDouActiveRecyclerView(Context context) {
        this(context, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, androidx.core.view.NestedScrollingChild
    public void setNestedScrollingEnabled(boolean z) {
        super.setNestedScrollingEnabled(false);
    }

    public JingDouActiveRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
