package com.jd.lib.productdetail.mainimage.old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.lib.productdetail.mainimage.R;

/* loaded from: classes15.dex */
public class PdMImageHeaderAdapter extends RecyclerView.Adapter {
    public RecyclerView.Adapter a;
    public final LayoutInflater b;

    /* loaded from: classes15.dex */
    public class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(PdMImageHeaderAdapter pdMImageHeaderAdapter, View view) {
            super(view);
        }
    }

    public PdMImageHeaderAdapter(@NonNull Context context, @NonNull RecyclerView.Adapter adapter) {
        this.a = adapter;
        this.b = LayoutInflater.from(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a.getItemCount() + 1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        if (i2 < 1) {
            return Integer.MAX_VALUE;
        }
        return this.a.getItemViewType(i2 - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        if (getItemViewType(i2) != Integer.MAX_VALUE) {
            this.a.onBindViewHolder(viewHolder, i2 - 1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        if (i2 != Integer.MAX_VALUE) {
            return this.a.onCreateViewHolder(viewGroup, i2);
        }
        return new HeaderViewHolder(this, this.b.inflate(R.layout.lib_pd_mainimage_transparent_view, viewGroup, false));
    }
}
