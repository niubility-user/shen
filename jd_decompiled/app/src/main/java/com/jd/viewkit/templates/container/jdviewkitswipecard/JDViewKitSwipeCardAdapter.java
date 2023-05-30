package com.jd.viewkit.templates.container.jdviewkitswipecard;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.jd.viewkit.dataSources.model.JDViewKitDataSourceModel;
import com.jd.viewkit.global.GlobalManage;
import com.jd.viewkit.helper.JDViewKitChannelModel;
import com.jd.viewkit.templates.model.JDViewKitVirtualBannerView.JDViewKitVirtualBannerConfig;
import com.jd.viewkit.templates.model.JDViewKitVirtualView;
import com.jd.viewkit.templates.view.JDViewKitAbsoluteLayout;
import com.jd.viewkit.templates.view.factory.JDViewKitViewFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes18.dex */
public class JDViewKitSwipeCardAdapter extends BaseAdapter {
    private JDViewKitChannelModel channelModel;
    private JDViewKitVirtualBannerConfig config;
    private Map<String, JDViewKitVirtualView> dslsMap;
    private List<JDViewKitDataSourceModel> mDatas;

    /* loaded from: classes18.dex */
    public static class SwipeViewHolder extends RecyclerView.ViewHolder {
        public JDViewKitAbsoluteLayout mJDViewKitAbsoluteLayout;

        public SwipeViewHolder(View view) {
            super(view);
            this.mJDViewKitAbsoluteLayout = (JDViewKitAbsoluteLayout) view;
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<JDViewKitDataSourceModel> list = this.mDatas;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public JDViewKitDataSourceModel getCurJDViewKitDataSourceModel() {
        List<JDViewKitDataSourceModel> list = this.mDatas;
        if (list != null) {
            return list.get(0);
        }
        return null;
    }

    public int getFirstIndex() {
        if (getCount() > 0) {
            return this.mDatas.get(0).p_position;
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        List<JDViewKitDataSourceModel> list = this.mDatas;
        if (list != null) {
            return list.get(i2);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public int getItemViewType(int i2) {
        if (i2 < 0 || i2 >= getCount()) {
            return -1;
        }
        return this.mDatas.get(i2).p_position;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        View view2;
        RecyclerView.ViewHolder viewHolder;
        int itemViewType = getItemViewType(i2);
        if (view == null) {
            viewHolder = onCreateViewHolder(viewGroup, itemViewType, this.mDatas.get(i2));
            view2 = viewHolder.itemView;
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (RecyclerView.ViewHolder) view.getTag();
        }
        onBindViewHolder(viewHolder, i2);
        return view2;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        if (i2 >= this.mDatas.size()) {
            return;
        }
        ((SwipeViewHolder) viewHolder).mJDViewKitAbsoluteLayout.setDataSourceModel(this.mDatas.get(i2), false);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2, JDViewKitDataSourceModel jDViewKitDataSourceModel) {
        JDViewKitVirtualView jDViewKitVirtualView = this.dslsMap.get(jDViewKitDataSourceModel.getDslId());
        JDViewKitAbsoluteLayout view = JDViewKitViewFactory.getView(viewGroup.getContext(), jDViewKitDataSourceModel, jDViewKitVirtualView, this.channelModel);
        if (jDViewKitVirtualView != null && jDViewKitVirtualView.getBorderRadius() != 0) {
            view.setBackgroundColor(0);
            View view2 = new View(viewGroup.getContext());
            view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            view.setBgColorAndBorder(view2);
            view.addView(view2, 0);
        }
        view.setLayoutParams(new FrameLayout.LayoutParams(GlobalManage.getInstance().getRealPx(this.config.getCenterSizeWidth(), this.channelModel), GlobalManage.getInstance().getRealPx(this.config.getCenterSizeHeigh(), this.channelModel)));
        return new SwipeViewHolder(view);
    }

    public void removeBottomToFirst() {
        if (getCount() <= 1) {
            return;
        }
        this.mDatas.add(0, this.mDatas.remove(getCount() - 1));
        notifyDataSetChanged();
    }

    public void removeFirstToBottom() {
        if (getCount() <= 1) {
            return;
        }
        this.mDatas.add(this.mDatas.remove(0));
        notifyDataSetChanged();
    }

    public void setChannelModel(JDViewKitChannelModel jDViewKitChannelModel) {
        this.channelModel = jDViewKitChannelModel;
    }

    public void setConfig(JDViewKitVirtualBannerConfig jDViewKitVirtualBannerConfig) {
        this.config = jDViewKitVirtualBannerConfig;
    }

    public void setDatas(List<JDViewKitDataSourceModel> list) {
        if (this.mDatas == null) {
            this.mDatas = new ArrayList();
        }
        this.mDatas.clear();
        this.mDatas.addAll(list);
        int size = this.mDatas.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.mDatas.get(i2).p_position = i2;
        }
    }

    public void setDslsMap(Map<String, JDViewKitVirtualView> map) {
        this.dslsMap = map;
    }
}
