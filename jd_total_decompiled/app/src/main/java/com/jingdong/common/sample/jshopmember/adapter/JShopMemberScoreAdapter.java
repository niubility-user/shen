package com.jingdong.common.sample.jshopmember.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshopmember.entity.JshopPointDetailBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JShopMemberScoreAdapter extends BaseAdapter {
    private static final String TAG = "JShopMemberScoreAdapter";
    private Context mContext;
    private ArrayList<JshopPointDetailBean> mPointList = new ArrayList<>();

    /* loaded from: classes6.dex */
    static class ViewHolder {
        TextView memberScore;
        TextView nameLab;
        TextView scoreTime;

        public ViewHolder(View view) {
            this.nameLab = (TextView) view.findViewById(R.id.name_lab);
            this.scoreTime = (TextView) view.findViewById(R.id.buy_time);
            this.memberScore = (TextView) view.findViewById(R.id.member_score);
        }
    }

    public JShopMemberScoreAdapter(Context context) {
        this.mContext = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<JshopPointDetailBean> arrayList = this.mPointList;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        ArrayList<JshopPointDetailBean> arrayList = this.mPointList;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return this.mPointList.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LinearLayout.inflate(this.mContext, R.layout.jshop_member_score_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        JshopPointDetailBean jshopPointDetailBean = (JshopPointDetailBean) getItem(i2);
        if (jshopPointDetailBean == null) {
            return view;
        }
        viewHolder.nameLab.setText(jshopPointDetailBean.pointsMsg);
        viewHolder.scoreTime.setText(jshopPointDetailBean.occurTime);
        viewHolder.memberScore.setText("" + jshopPointDetailBean.points);
        return view;
    }

    public void setData(List<JshopPointDetailBean> list) {
        if (list != null) {
            this.mPointList.addAll(list);
            notifyDataSetChanged();
        }
    }
}
