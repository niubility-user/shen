package com.jingdong.common.sample.jshopmember.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.sample.jshopmember.JshopMemberActivity;
import com.jingdong.common.sample.jshopmember.entity.CouponForPoint;
import com.jingdong.common.sample.jshopmember.utils.JshopMemberUtils;
import com.jingdong.corelib.utils.Log;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class JshopMemberCouponAdapter extends BaseAdapter {
    private static final String TAG = "JshopMemberCouponAdapter";
    private Context mContext;
    private List<CouponForPoint> mList = new ArrayList();
    public String mVendorId = "";

    /* loaded from: classes6.dex */
    static class ViewHolder {
        View itemContent;
        TextView itemDuration;
        TextView itemIcon;
        LinearLayout itemLeft;
        TextView itemQuota;
        TextView itemRmb;
        ImageView itemTaken;
        TextView score;

        public ViewHolder(View view) {
            this.itemContent = view.findViewById(R.id.afs);
            this.itemLeft = (LinearLayout) view.findViewById(R.id.coupon_item_left);
            this.itemIcon = (TextView) view.findViewById(R.id.coupon_item_icon);
            this.itemQuota = (TextView) view.findViewById(R.id.coupon_item_quota);
            this.itemDuration = (TextView) view.findViewById(R.id.coupon_item_time_limit);
            this.itemRmb = (TextView) view.findViewById(R.id.coupon_item_unit);
            this.itemTaken = (ImageView) view.findViewById(R.id.coupon_item_taken);
            this.score = (TextView) view.findViewById(R.id.member_score);
        }
    }

    public JshopMemberCouponAdapter(Context context) {
        this.mContext = context;
    }

    private SpannableString getBuilder(String str, String str2) {
        SpannableString spannableString = new SpannableString(str + str2);
        spannableString.setSpan(new RelativeSizeSpan(0.45f), 0, str.length(), 18);
        return spannableString;
    }

    public void clear() {
        List<CouponForPoint> list = this.mList;
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<CouponForPoint> list = this.mList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i2) {
        return this.mList.get(i2);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null && view.getTag() != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LinearLayout.inflate(this.mContext, R.layout.jshop_member_coupon_item, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        CouponForPoint couponForPoint = (CouponForPoint) getItem(i2);
        if (couponForPoint != null) {
            String string = this.mContext.getString(R.string.jshop_member_quota_string, Integer.valueOf(couponForPoint.condition));
            if (couponForPoint.couponType == 1) {
                viewHolder.itemIcon.setText("\u4e1c\u5238");
                viewHolder.itemIcon.setBackgroundResource(R.drawable.shopping_cart_coupon_dong_icon_bg);
                viewHolder.itemLeft.setBackgroundResource(R.drawable.jshop_exchange_big_right_blue_bg);
            } else {
                viewHolder.itemIcon.setText("\u4eac\u5238");
                viewHolder.itemIcon.setBackgroundResource(R.drawable.shopping_cart_coupon_jing_icon_bg);
                viewHolder.itemLeft.setBackgroundResource(R.drawable.jshop_exchange_big_right_red_bg);
                string = "\u65e0\u95e8\u69db";
            }
            viewHolder.itemQuota.setText("              " + string);
            viewHolder.score.setText("" + couponForPoint.points);
            viewHolder.itemDuration.setText(couponForPoint.expiryDate);
            int i3 = couponForPoint.discount;
            if (i3 >= 10000) {
                viewHolder.itemRmb.setTextSize(1, 26.0f);
            } else if (i3 >= 1000) {
                viewHolder.itemRmb.setTextSize(1, 30.0f);
            } else if (i3 >= 100) {
                viewHolder.itemRmb.setTextSize(1, 38.0f);
            } else {
                viewHolder.itemRmb.setTextSize(1, 44.0f);
            }
            viewHolder.itemRmb.setPadding(0, 0, ((int) viewHolder.itemRmb.getPaint().measureText("\u00a5")) >> 1, 0);
            viewHolder.itemRmb.setText(getBuilder("\u00a5 ", "" + couponForPoint.discount));
            if (couponForPoint.remainingCount <= 0) {
                viewHolder.itemTaken.setVisibility(0);
                viewHolder.itemLeft.setBackgroundResource(R.drawable.jshop_exchange_big_right_grey_bg);
                view.setEnabled(false);
            } else {
                viewHolder.itemTaken.setVisibility(8);
                view.setEnabled(true);
            }
            viewHolder.itemLeft.setTag(couponForPoint);
            viewHolder.itemContent.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshopmember.adapter.JshopMemberCouponAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    Log.d(JshopMemberCouponAdapter.TAG, "holder.itemContent item click");
                    if (JshopMemberCouponAdapter.this.mContext instanceof JshopMemberActivity) {
                        Object tag = view2.findViewById(R.id.coupon_item_left).getTag();
                        if (tag instanceof CouponForPoint) {
                            JshopMemberActivity jshopMemberActivity = (JshopMemberActivity) JshopMemberCouponAdapter.this.mContext;
                            JshopMemberUtils.takeCoupon(jshopMemberActivity, (CouponForPoint) tag, jshopMemberActivity.mVendorId);
                        }
                    }
                }
            });
        }
        return view;
    }

    public void setData(List<CouponForPoint> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.mList.addAll(list);
        notifyDataSetChanged();
    }
}
