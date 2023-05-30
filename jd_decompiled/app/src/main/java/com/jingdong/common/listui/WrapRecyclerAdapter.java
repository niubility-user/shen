package com.jingdong.common.listui;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/* loaded from: classes5.dex */
public class WrapRecyclerAdapter extends RecyclerView.Adapter {
    private final ArrayList<View> EMPTY_INFO_LIST;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<View> mFootViews;
    private ArrayList<View> mHeaderViews;
    private int position;

    public WrapRecyclerAdapter(ArrayList<View> arrayList, ArrayList<View> arrayList2, RecyclerView.Adapter adapter) {
        ArrayList<View> arrayList3 = new ArrayList<>();
        this.EMPTY_INFO_LIST = arrayList3;
        this.mAdapter = adapter;
        if (arrayList == null) {
            this.mHeaderViews = arrayList3;
        } else {
            this.mHeaderViews = arrayList;
        }
        if (arrayList == null) {
            this.mFootViews = arrayList3;
        } else {
            this.mFootViews = arrayList2;
        }
    }

    public int getFootersCount() {
        return this.mFootViews.size();
    }

    public int getHeadersCount() {
        return this.mHeaderViews.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int headersCount;
        int footersCount;
        if (this.mAdapter != null) {
            headersCount = getHeadersCount() + getFootersCount();
            footersCount = this.mAdapter.getItemCount();
        } else {
            headersCount = getHeadersCount();
            footersCount = getFootersCount();
        }
        return headersCount + footersCount;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i2) {
        int i3;
        int headersCount = getHeadersCount();
        RecyclerView.Adapter adapter = this.mAdapter;
        if (adapter == null || i2 < headersCount || (i3 = i2 - headersCount) >= adapter.getItemCount()) {
            return -1L;
        }
        return this.mAdapter.getItemId(i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        this.position = i2;
        int headersCount = getHeadersCount();
        if (i2 < headersCount) {
            return -1;
        }
        int i3 = i2 - headersCount;
        RecyclerView.Adapter adapter = this.mAdapter;
        if (adapter == null || i3 >= adapter.getItemCount()) {
            return -2;
        }
        return this.mAdapter.getItemViewType(i3);
    }

    public RecyclerView.Adapter getWrappedAdapter() {
        return this.mAdapter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        int headersCount = getHeadersCount();
        if (i2 < headersCount) {
            return;
        }
        int i3 = i2 - headersCount;
        RecyclerView.Adapter adapter = this.mAdapter;
        if (adapter == null || i3 >= adapter.getItemCount()) {
            return;
        }
        this.mAdapter.onBindViewHolder(viewHolder, i3);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == -1) {
            return new ViewHolder(this.mHeaderViews.get(this.position));
        }
        if (i2 == -2) {
            return new ViewHolder(this.mFootViews.get(getFootersCount() - (getItemCount() - this.position)));
        }
        RecyclerView.Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return new ViewHolder(new View(viewGroup.getContext()));
        }
        return adapter.onCreateViewHolder(viewGroup, i2);
    }
}
