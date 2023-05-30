package com.jingdong.common.weight.personal.base;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* loaded from: classes12.dex */
public abstract class SimpleAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    RecyclerView mRecyclerView;
    private final ArrayList<T> models = new ArrayList<>();

    public SimpleAdapter(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public void addModel(T t) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.isComputingLayout()) {
            return;
        }
        int size = this.models.size();
        this.models.add(t);
        notifyItemRangeInserted(size, 1);
    }

    public void addModels(T... tArr) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.isComputingLayout()) {
            return;
        }
        int size = this.models.size();
        int length = tArr.length;
        this.models.ensureCapacity(size + length);
        Collections.addAll(this.models, tArr);
        notifyItemRangeInserted(size, length);
    }

    public void clearModels() {
        this.models.clear();
    }

    public int getDataSize() {
        return this.models.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.models.size();
    }

    public List<T> models() {
        return Collections.unmodifiableList(this.models);
    }

    public void removeLopper(int i2) {
        RecyclerView recyclerView = this.mRecyclerView;
        if ((recyclerView == null || recyclerView.isComputingLayout() || i2 >= this.models.size()) ? false : true) {
            this.models.add(this.models.remove(i2));
            notifyDataSetChanged();
        }
    }

    public void swapModels(List<? extends T> list) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.isComputingLayout() || list == null || list.isEmpty()) {
            return;
        }
        DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DiffCallback(this.models, list));
        this.models.clear();
        this.models.addAll(list);
        calculateDiff.dispatchUpdatesTo(this);
    }

    public void addModels(Collection<? extends T> collection) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.isComputingLayout()) {
            return;
        }
        int size = this.models.size();
        this.models.addAll(collection);
        notifyItemRangeInserted(size, collection.size());
    }
}
