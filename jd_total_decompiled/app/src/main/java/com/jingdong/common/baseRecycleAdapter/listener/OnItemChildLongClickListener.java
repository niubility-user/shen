package com.jingdong.common.baseRecycleAdapter.listener;

import android.view.View;
import com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter;

/* loaded from: classes5.dex */
public abstract class OnItemChildLongClickListener extends SimpleClickListener {
    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
        onSimpleItemChildLongClick(baseQuickAdapter, view, i2);
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    public abstract void onSimpleItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i2);
}
