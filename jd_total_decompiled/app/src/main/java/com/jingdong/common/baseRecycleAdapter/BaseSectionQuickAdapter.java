package com.jingdong.common.baseRecycleAdapter;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.baseRecycleAdapter.BaseViewHolder;
import com.jingdong.common.baseRecycleAdapter.entity.SectionEntity;
import java.util.List;

/* loaded from: classes5.dex */
public abstract class BaseSectionQuickAdapter<T extends SectionEntity, K extends BaseViewHolder> extends BaseQuickAdapter<T, K> {
    protected static final int SECTION_HEADER_VIEW = 1092;
    protected int mSectionHeadResId;

    public BaseSectionQuickAdapter(int i2, int i3, List<T> list) {
        super(i2, list);
        this.mSectionHeadResId = i3;
    }

    protected abstract void convertHead(BaseViewHolder baseViewHolder, T t);

    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    protected int getDefItemViewType(int i2) {
        return ((SectionEntity) this.mData.get(i2)).isHeader ? 1092 : 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        onBindViewHolder((BaseSectionQuickAdapter<T, K>) ((BaseViewHolder) viewHolder), i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void onBindViewHolder(K k2, int i2) {
        if (k2.getItemViewType() != 1092) {
            super.onBindViewHolder((BaseSectionQuickAdapter<T, K>) k2, i2);
            return;
        }
        setFullSpan(k2);
        convertHead(k2, (SectionEntity) this.mData.get(k2.getLayoutPosition() - getHeaderLayoutCount()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jingdong.common.baseRecycleAdapter.BaseQuickAdapter
    public K onCreateDefViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 1092) {
            return (K) createBaseViewHolder(getItemView(this.mSectionHeadResId, viewGroup));
        }
        return (K) super.onCreateDefViewHolder(viewGroup, i2);
    }
}
