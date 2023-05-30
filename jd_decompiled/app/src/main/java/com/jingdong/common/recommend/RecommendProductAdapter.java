package com.jingdong.common.recommend;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.recommend.forlist.RecommendViewHolder;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class RecommendProductAdapter extends BaseAdapter {
    private BaseActivity activity;
    private RecommendUtil.OnRecommendClickedListener onRecommendClickedListener;
    private RecommendUtil recommendUtil;

    public RecommendProductAdapter(BaseActivity baseActivity, ArrayList<RecommendItem> arrayList, int i2) {
        this.activity = baseActivity;
        this.recommendUtil = new RecommendUtil(i2);
        addList(arrayList, true);
    }

    public void addList(ArrayList<RecommendItem> arrayList, boolean z) {
        if (z) {
            this.recommendUtil.clearRecommendData();
        }
        if (this.recommendUtil.getRecommendItemList() == null) {
            this.recommendUtil.setRecommendProductItemList(arrayList);
        } else {
            this.recommendUtil.getRecommendItemList().addAll(arrayList);
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.recommendUtil.getRecommendItemCount();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return i2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        return this.recommendUtil.getRecommendItemType(i2, 0);
    }

    public List<?> getList() {
        return this.recommendUtil.getRecommendItemList();
    }

    public RecommendUtil getRecommendUtil() {
        return this.recommendUtil;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        RecommendViewHolder recommendViewHolder;
        int itemViewType = getItemViewType(i2);
        if (view == null) {
            recommendViewHolder = (RecommendViewHolder) this.recommendUtil.onCreateRecommedViewHolder(this.activity, itemViewType, 0);
            recommendViewHolder.setClickedListener(this.onRecommendClickedListener);
            view2 = recommendViewHolder.itemView;
            view2.setTag(recommendViewHolder);
        } else {
            view2 = view;
            recommendViewHolder = (RecommendViewHolder) view.getTag();
        }
        onBindViewHolder(recommendViewHolder, i2);
        return view2;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getViewTypeCount() {
        return this.recommendUtil.getRecommendTypes();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (viewHolder == null) {
            return;
        }
        this.recommendUtil.onBindRecommendViewHolder(viewHolder, i2, this.activity);
        if (viewHolder instanceof RecommendViewHolder) {
            viewHolder.itemView.setTag(R.id.recommend_item_bean, ((RecommendViewHolder) viewHolder).getLineTwoBean());
        }
    }

    public void setOnRecommendClickedListener(RecommendUtil.OnRecommendClickedListener onRecommendClickedListener) {
        this.onRecommendClickedListener = onRecommendClickedListener;
    }
}
