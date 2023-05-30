package com.jingdong.common.listui;

import android.content.Context;
import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

/* loaded from: classes5.dex */
public abstract class AListItem<T, VH extends RecyclerView.ViewHolder> {
    protected T data;
    protected boolean hasDevider = true;
    protected int position;
    protected WrapBundle wrapBundle;

    public AListItem create(T t) {
        this.data = t;
        return this;
    }

    public T getData() {
        return this.data;
    }

    @LayoutRes
    public abstract int getLayoutId();

    public WrapBundle getWrapBundle() {
        if (this.wrapBundle == null) {
            this.wrapBundle = new WrapBundle();
        }
        return this.wrapBundle;
    }

    public AListItem injectData(WrapBundle wrapBundle) {
        this.wrapBundle = wrapBundle;
        return this;
    }

    public abstract void onBindViewHolder(VH vh, Context context);

    public abstract VH onCreateViewHolder(View view);

    public void setHasDevider(boolean z) {
        this.hasDevider = z;
    }

    public void setPosition(int i2) {
        this.position = i2;
    }
}
