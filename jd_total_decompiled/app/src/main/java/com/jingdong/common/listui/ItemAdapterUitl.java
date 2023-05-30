package com.jingdong.common.listui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes5.dex */
public class ItemAdapterUitl {
    public static ListItem convert(final AListItem aListItem) {
        return new ListItem() { // from class: com.jingdong.common.listui.ItemAdapterUitl.1
            @Override // com.jingdong.common.listui.ListItem
            public int getViewType(int i2) {
                return AListItem.this.getLayoutId();
            }

            @Override // com.jingdong.common.listui.ListItem
            public void onBindViewHolder(Context context, RecyclerView.ViewHolder viewHolder, int i2) {
                AListItem.this.onBindViewHolder(viewHolder, context);
            }

            @Override // com.jingdong.common.listui.ListItem
            public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2) {
                return AListItem.this.onCreateViewHolder(layoutInflater.inflate(AListItem.this.getLayoutId(), viewGroup, false));
            }
        };
    }

    public static ListItem convert(final AListItem aListItem, final boolean z) {
        return new ListItem() { // from class: com.jingdong.common.listui.ItemAdapterUitl.2
            @Override // com.jingdong.common.listui.ListItem
            public int getViewType(int i2) {
                return AListItem.this.getLayoutId();
            }

            @Override // com.jingdong.common.listui.ListItem
            public boolean isSticky() {
                return z;
            }

            @Override // com.jingdong.common.listui.ListItem
            public void onBindViewHolder(Context context, RecyclerView.ViewHolder viewHolder, int i2) {
                AListItem.this.onBindViewHolder(viewHolder, context);
            }

            @Override // com.jingdong.common.listui.ListItem
            public RecyclerView.ViewHolder onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2) {
                return AListItem.this.onCreateViewHolder(layoutInflater.inflate(AListItem.this.getLayoutId(), viewGroup, false));
            }
        };
    }
}
