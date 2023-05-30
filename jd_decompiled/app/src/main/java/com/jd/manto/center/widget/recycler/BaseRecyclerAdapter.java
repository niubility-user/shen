package com.jd.manto.center.widget.recycler;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes17.dex */
public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private Context a;
    private List<T> b;

    /* loaded from: classes17.dex */
    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    /* loaded from: classes17.dex */
    public interface a {
        void a(BaseRecyclerAdapter baseRecyclerAdapter, View view, int i2);
    }

    public BaseRecyclerAdapter(Context context, List<T> list) {
        this.a = context;
        this.b = list == null ? new ArrayList<>() : list;
    }

    public void clear() {
        this.b.clear();
        notifyItemRangeRemoved(0, this.b.size());
    }

    public List<T> getData() {
        return this.b;
    }

    public T getItem(int i2) {
        return this.b.get(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public void h(List<? extends T> list) {
        this.b.addAll(list);
        notifyItemRangeInserted(this.b.size(), list.size());
    }

    public Context l() {
        return this.a;
    }

    public void m(List<? extends T> list) {
        this.b.clear();
        this.b.addAll(list);
        notifyDataSetChanged();
    }

    public void n(int i2) {
        if (i2 < this.b.size()) {
            this.b.remove(i2);
            notifyItemRemoved(i2);
        }
    }
}
