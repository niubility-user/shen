package com.jingdong.app.mall.utils.ui;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes4.dex */
public abstract class HeaderFooterRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private View a;
    private View b;

    /* loaded from: classes4.dex */
    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(HeaderFooterRecyclerAdapter headerFooterRecyclerAdapter, View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return h() + (this.a == null ? 0 : 1) + (this.b != null ? 1 : 0);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i2) {
        if (i2 != 0 || this.a == null) {
            if (i2 != getItemCount() - 1 || this.b == null) {
                return l(i2 - (this.a == null ? 0 : 1));
            }
            return 10002;
        }
        return 10001;
    }

    public abstract int h();

    public abstract int l(int i2);

    public abstract void m(RecyclerView.ViewHolder viewHolder, int i2);

    public abstract RecyclerView.ViewHolder n(ViewGroup viewGroup, int i2);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        int itemViewType;
        if (viewHolder == null || (itemViewType = getItemViewType(i2)) == 10001 || itemViewType == 10002) {
            return;
        }
        m(viewHolder, i2 - (this.a == null ? 0 : 1));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 != 10001) {
            if (i2 != 10002) {
                return n(viewGroup, i2);
            }
            return new SimpleViewHolder(this, this.b);
        }
        return new SimpleViewHolder(this, this.a);
    }
}
