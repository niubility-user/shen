package com.jingdong.common.baseRecycleAdapter.listener;

import android.view.View;
import com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter;

/* loaded from: classes5.dex */
public abstract class OnItemChildClickListener extends SimpleClickListener {
    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
        onSimpleItemChildClick(baseQuickAdapter, view, i2);
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemChildLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    @Override // com.jingdong.common.baseRecycleAdapter.listener.SimpleClickListener
    public void onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
    }

    public abstract void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i2);
}
