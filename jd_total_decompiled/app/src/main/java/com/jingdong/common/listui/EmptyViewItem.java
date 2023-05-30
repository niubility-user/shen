package com.jingdong.common.listui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class EmptyViewItem extends ListItem {
    @Override // com.jingdong.common.listui.ListItem
    public int getViewType(int i2) {
        return -9999;
    }

    @Override // com.jingdong.common.listui.ListItem
    public void onBindViewHolder(Context context, RecyclerView.ViewHolder viewHolder, int i2) {
    }

    @Override // com.jingdong.common.listui.ListItem
    public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2) {
        View view = new View(viewGroup.getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, 1));
        return new ViewHolder(view);
    }
}
