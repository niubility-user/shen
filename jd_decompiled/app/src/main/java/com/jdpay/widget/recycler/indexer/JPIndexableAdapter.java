package com.jdpay.widget.recycler.indexer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.jdpay.widget.recycler.AbsRecyclerAdapter;
import com.jdpay.widget.recycler.indexer.JPIndexableBean;
import com.jdpay.widget.recycler.indexer.JPIndexerViewHolder;

/* loaded from: classes18.dex */
public abstract class JPIndexableAdapter<D extends JPIndexableBean, VH extends JPIndexerViewHolder<D>> extends AbsRecyclerAdapter<D, VH> {
    public int findIndexByIndexChar(char c2) {
        for (int i2 = 0; i2 < getItemCount(); i2++) {
            JPIndexableBean jPIndexableBean = (JPIndexableBean) get(i2);
            if (jPIndexableBean != null && c2 == jPIndexableBean.getIndexChar()) {
                return i2;
            }
        }
        return -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i2) {
        JPIndexableBean jPIndexableBean = i2 < getItemCount() ? (JPIndexableBean) get(i2) : null;
        if (jPIndexableBean != null) {
            return jPIndexableBean.getType();
        }
        return 0;
    }

    @Override // com.jdpay.widget.recycler.AbsRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override // com.jdpay.widget.recycler.AbsRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
    }
}
