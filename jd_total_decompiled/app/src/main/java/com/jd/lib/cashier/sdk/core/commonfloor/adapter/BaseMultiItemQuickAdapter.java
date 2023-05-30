package com.jd.lib.cashier.sdk.core.commonfloor.adapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jd.lib.cashier.sdk.d.a.a.a;
import java.util.List;

/* loaded from: classes14.dex */
public abstract class BaseMultiItemQuickAdapter<T extends a, K extends RecyclerView.ViewHolder> extends BaseQuickAdapter<T, K> {
    private SparseArray<Integer> w;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.BaseQuickAdapter
    protected int getDefItemViewType(int i2) {
        Object obj = this.u.get(i2);
        if (obj instanceof a) {
            return ((a) obj).getItemType();
        }
        return -255;
    }

    protected int getLayoutId(int i2) {
        return this.w.get(i2).intValue();
    }

    public void h(SparseArray<Integer> sparseArray) {
        this.w = sparseArray;
    }

    @Override // com.jd.lib.cashier.sdk.core.commonfloor.adapter.BaseQuickAdapter
    protected K onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(viewGroup, getLayoutId(i2));
    }
}
