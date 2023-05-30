package com.jingdong.app.mall.home.floor.view.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.app.mall.home.floor.common.i.m;

/* loaded from: classes4.dex */
public abstract class BaseHeaderFooterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View a;
    private View b;

    /* loaded from: classes4.dex */
    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(BaseHeaderFooterRecyclerAdapter baseHeaderFooterRecyclerAdapter, View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return l() + (this.a == null ? 0 : 1) + (this.b != null ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i2) {
        if (i2 != 0 || this.a == null) {
            if (i2 != getItemCount() - 1 || this.b == null) {
                return m(i2 - (this.a == null ? 0 : 1));
            }
            return 10002;
        }
        return 10001;
    }

    public View h() {
        return this.b;
    }

    public abstract int l();

    public abstract int m(int i2);

    public abstract void n(RecyclerView.ViewHolder viewHolder, int i2);

    public abstract RecyclerView.ViewHolder o(ViewGroup viewGroup, int i2);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        int itemViewType;
        if (viewHolder == null || (itemViewType = getItemViewType(i2)) == 10001 || itemViewType == 10002) {
            return;
        }
        n(viewHolder, i2 - (this.a == null ? 0 : 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder simpleViewHolder;
        if (i2 == 10001) {
            simpleViewHolder = new SimpleViewHolder(this, this.a);
        } else if (i2 != 10002) {
            simpleViewHolder = o(viewGroup, i2);
        } else {
            simpleViewHolder = new SimpleViewHolder(this, this.b);
        }
        m.f(simpleViewHolder);
        return simpleViewHolder;
    }

    public void p(View view) {
        this.b = view;
    }

    public void setHeaderView(View view) {
        this.a = view;
    }
}
