package com.jingdong.common.listui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ListItemTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    protected OnListItemClickListener mOnItemClickListener;
    protected List<ListItem> dataList = new ArrayList();
    private SparseArrayCompat<ListItem> viewTypeCache = new SparseArrayCompat<>();

    /* loaded from: classes5.dex */
    public interface OnListItemClickListener<D> {
        void onItemClick(View view, int i2, D d);
    }

    public ListItemTypeAdapter(Context context, List<ListItem> list) {
        setList(list);
        this.inflater = LayoutInflater.from(context);
    }

    public void add(int i2, ListItem listItem) {
        this.dataList.add(i2, listItem);
        notifyItemInserted(i2);
    }

    public void addList(List<ListItem> list) {
        if (list == null) {
            return;
        }
        this.dataList.addAll(list);
    }

    public void clear() {
        this.dataList.clear();
        this.viewTypeCache.clear();
    }

    public void delete(int i2) {
        this.dataList.remove(i2);
        notifyItemRemoved(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ListItem> list = this.dataList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        ListItem listItem = this.dataList.get(i2);
        int viewType = listItem.getViewType(i2);
        if (this.viewTypeCache.get(viewType) == null) {
            this.viewTypeCache.put(viewType, listItem);
        }
        return viewType;
    }

    public List<ListItem> getList() {
        return this.dataList;
    }

    public ListItem getViewItem(int i2) {
        ListItem listItem = this.viewTypeCache.get(i2);
        if (listItem == null) {
            EmptyViewItem emptyViewItem = new EmptyViewItem();
            this.viewTypeCache.put(i2, emptyViewItem);
            return emptyViewItem;
        }
        return listItem;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i2) {
        if (viewHolder == null) {
            return;
        }
        final ListItem listItem = this.dataList.get(i2);
        if (this.mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.listui.ListItemTypeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ListItemTypeAdapter.this.mOnItemClickListener.onItemClick(view, i2, listItem.data);
                }
            });
        }
        listItem.onBindViewHolder(this.inflater.getContext(), viewHolder, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return getViewItem(i2).onCreateViewHolder(this.inflater, viewGroup, i2);
    }

    public void setList(List<ListItem> list) {
        if (list == null) {
            return;
        }
        this.dataList = list;
        this.viewTypeCache.clear();
    }

    public void setOnItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.mOnItemClickListener = onListItemClickListener;
    }

    public void addList(int i2, List<ListItem> list) {
        if (list == null || i2 < 0 || i2 > list.size()) {
            return;
        }
        this.dataList.addAll(i2, list);
    }
}
