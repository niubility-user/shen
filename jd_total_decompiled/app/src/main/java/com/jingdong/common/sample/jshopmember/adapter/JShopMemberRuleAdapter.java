package com.jingdong.common.sample.jshopmember.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshopmember.entity.ShopRulesBean;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JShopMemberRuleAdapter extends BaseAdapter {
    private static final String TAG = "JShopMemberRuleAdapter";
    private Context mContext;
    private ArrayList<ShopRulesBean> mRules = new ArrayList<>();

    /* loaded from: classes6.dex */
    static class ViewHolder {
        TextView conditionValue;
        ImageView levelIcon;
        TextView memberLevel;
        TextView memberLevelName;
        TextView privilege;
        TextView privilegeLable;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            if (view != null) {
                this.ratingBar = (RatingBar) view.findViewById(R.id.member_score);
                this.memberLevel = (TextView) view.findViewById(R.id.member_level);
                this.memberLevelName = (TextView) view.findViewById(R.id.member_level_name);
                this.conditionValue = (TextView) view.findViewById(R.id.condition);
                this.privilegeLable = (TextView) view.findViewById(R.id.privilege_lab);
                this.privilege = (TextView) view.findViewById(R.id.privilege);
                this.levelIcon = (ImageView) view.findViewById(R.id.level_icon);
            }
        }
    }

    public JShopMemberRuleAdapter(Context context) {
        this.mContext = context;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<ShopRulesBean> arrayList = this.mRules;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        ArrayList<ShopRulesBean> arrayList = this.mRules;
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return this.mRules.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    public Drawable getMemberLevDrawable(int i2) {
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v1);
                        }
                        return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v5);
                    }
                    return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v4);
                }
                return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v3);
            }
            return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v2);
        }
        return this.mContext.getResources().getDrawable(R.drawable.jshop_level_v1);
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        Log.d(TAG, "position = " + i2);
        if (view == null) {
            view = LinearLayout.inflate(this.mContext, R.layout.jshop_member_rule_big_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        ShopRulesBean shopRulesBean = (ShopRulesBean) getItem(i2);
        if (shopRulesBean == null) {
            return view;
        }
        viewHolder.privilege.setText(shopRulesBean.privilegeStr);
        viewHolder.conditionValue.setText(shopRulesBean.conditionStr);
        if (shopRulesBean.isCurrentLevel) {
            viewHolder.levelIcon.setVisibility(0);
            view.setBackgroundColor(-2571);
        } else {
            viewHolder.levelIcon.setVisibility(4);
            view.setBackgroundColor(-1);
        }
        viewHolder.ratingBar.setRating(shopRulesBean.getItemGrade());
        viewHolder.memberLevelName.setText(shopRulesBean.curGradeName);
        return view;
    }

    public void setData(List<ShopRulesBean> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mRules.addAll(list);
    }
}
