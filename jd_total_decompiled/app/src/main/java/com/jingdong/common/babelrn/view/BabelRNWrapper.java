package com.jingdong.common.babelrn.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.babelrn.BabelRNManager;
import com.jingdong.common.babelrn.module.OnRNDataChangeListener;

/* loaded from: classes5.dex */
public class BabelRNWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private RecyclerView.Adapter mInnerAdapter;
    private BabelRNManager mManager;

    public BabelRNWrapper(RecyclerView.Adapter adapter, BabelRNManager babelRNManager) {
        this.mInnerAdapter = adapter;
        this.mManager = babelRNManager;
        if (babelRNManager != null) {
            babelRNManager.setOnRNDataChangeListener(new OnRNDataChangeListener() { // from class: com.jingdong.common.babelrn.view.BabelRNWrapper.1
                @Override // com.jingdong.common.babelrn.module.OnRNDataChangeListener
                public void onDataChanged() {
                    if (BabelRNWrapper.this.mManager != null) {
                        BabelRNWrapper.this.mManager.onResume();
                    }
                    BabelRNWrapper.this.notifyDataSetChanged();
                }

                @Override // com.jingdong.common.babelrn.module.OnRNDataChangeListener
                public void onRefresh() {
                }
            });
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mInnerAdapter.getItemCount();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        return this.mInnerAdapter.getItemViewType(i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        BabelRNManager babelRNManager = this.mManager;
        if (babelRNManager != null) {
            View view = viewHolder.itemView;
            if (view instanceof BabelRNFloor) {
                ((BabelRNFloor) view).setBabelRNManager(babelRNManager);
            }
        }
        this.mInnerAdapter.onBindViewHolder(viewHolder, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mInnerAdapter.onCreateViewHolder(viewGroup, i2);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        this.mInnerAdapter.onViewAttachedToWindow(viewHolder);
    }
}
