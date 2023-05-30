package com.jingdong.common.baseRecycleAdapter;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.jingdong.common.baseRecycleAdapter.entity.MultiItemEntity;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseMultiItemQuickAdapter<T extends MultiItemEntity, K extends RecyclerView.ViewHolder> extends BaseQuickAdapter<T, K> {
    private static final int DEFAULT_VIEW_TYPE = -255;
    private SparseArray<Integer> layouts;

    public BaseMultiItemQuickAdapter(List<T> list) {
        super(list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addItemType(int i2, @LayoutRes int i3) {
        if (this.layouts == null) {
            this.layouts = new SparseArray<>();
        }
        this.layouts.put(i2, Integer.valueOf(i3));
    }

    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    protected int getDefItemViewType(int i2) {
        Object obj = this.mData.get(i2);
        return obj instanceof MultiItemEntity ? ((MultiItemEntity) obj).getItemType() : DEFAULT_VIEW_TYPE;
    }

    protected int getLayoutId(int i2) {
        return this.layouts.get(i2).intValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        return createBaseViewHolder(viewGroup, getLayoutId(i2));
    }

    protected void setDefaultViewTypeLayout(@LayoutRes int i2) {
        addItemType(DEFAULT_VIEW_TYPE, i2);
    }
}
