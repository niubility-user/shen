package com.jingdong.common.listui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* loaded from: classes5.dex */
public abstract class ListItem<T, VH extends RecyclerView.ViewHolder> {
    protected T data;
    protected boolean hasDevider = true;
    protected WrapBundle wrapBundle;

    public T getData() {
        return this.data;
    }

    public abstract int getViewType(int i2);

    public WrapBundle getWrapBundle() {
        if (this.wrapBundle == null) {
            this.wrapBundle = new WrapBundle();
        }
        return this.wrapBundle;
    }

    public ListItem injectData(WrapBundle wrapBundle) {
        this.wrapBundle = wrapBundle;
        return this;
    }

    public boolean isSticky() {
        return false;
    }

    public abstract void onBindViewHolder(Context context, VH vh, int i2);

    public abstract VH onCreateViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i2);

    public ListItem setData(T t) {
        this.data = t;
        return this;
    }

    public ListItem setHasDevider(boolean z) {
        this.hasDevider = z;
        return this;
    }
}
