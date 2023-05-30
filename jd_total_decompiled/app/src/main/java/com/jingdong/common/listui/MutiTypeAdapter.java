package com.jingdong.common.listui;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.listui.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class MutiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater inflater;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private List<AListItem> list = new ArrayList();
    private SparseArray<AListItem> mSparseArray = new SparseArray<>();

    /* loaded from: classes5.dex */
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int i2);
    }

    public MutiTypeAdapter(Context context, List<AListItem> list) {
        setList(list);
        this.inflater = LayoutInflater.from(context);
    }

    public void addList(List<AListItem> list) {
        if (list == null) {
            return;
        }
        this.list.addAll(list);
    }

    public void clear() {
        this.list.clear();
        this.mSparseArray.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<AListItem> list = this.list;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        AListItem aListItem = this.list.get(i2);
        int layoutId = aListItem.getLayoutId();
        if (this.mSparseArray.get(layoutId) == null) {
            this.mSparseArray.put(layoutId, aListItem);
        }
        return layoutId;
    }

    public List<AListItem> getList() {
        return this.list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i2) {
        if (viewHolder.itemView.getTag(R.id.tag_mutitypeadapter_default) != null) {
            return;
        }
        AListItem aListItem = this.list.get(i2);
        if (this.mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.listui.MutiTypeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MutiTypeAdapter.this.mOnItemClickListener.onItemClick(view, i2);
                }
            });
        }
        aListItem.setPosition(i2);
        aListItem.onBindViewHolder(viewHolder, this.inflater.getContext());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        AListItem aListItem = this.mSparseArray.get(i2);
        if (aListItem == null) {
            View view = new View(viewGroup.getContext());
            view.setLayoutParams(new RecyclerView.LayoutParams(-1, 1));
            view.setTag(R.id.tag_mutitypeadapter_default, "default");
            return new ViewHolder(view);
        }
        return aListItem.onCreateViewHolder(this.inflater.inflate(aListItem.getLayoutId(), viewGroup, false));
    }

    public void setList(List<AListItem> list) {
        if (list == null) {
            return;
        }
        this.list = list;
        this.mSparseArray.clear();
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.mOnItemClickListener = onRecyclerViewItemClickListener;
    }

    public void addList(int i2, List<AListItem> list) {
        if (list == null || i2 < 0 || i2 > list.size()) {
            return;
        }
        this.list.addAll(i2, list);
    }
}
