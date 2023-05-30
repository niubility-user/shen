package com.jingdong.common.sample.jshop;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.sample.jshop.Entity.JShopSpecialPriceBean;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JshopAwardCouponAdapter extends BaseAdapter {
    private ArrayList<JShopSpecialPriceBean> list;
    private JShopSignNewActivity mActivity;
    private Context mContext;

    /* loaded from: classes6.dex */
    static class ViewHolder {
        LinearLayout couponLeft;
        TextView itemJumpTv;
        View mCouponItem;
        TextView mCouponQuote;
        TextView mCouponShopName;
        ImageView mCouponTaken;
        TextView mCouponTimeLimit;
        TextView mCouponType;
        TextView mCouponUnit;
        TextView mCouponValue;
        ImageView mCrmCouponIcon;
        View mProgressLayout;
        View mProgressNone;

        ViewHolder() {
        }
    }

    public JshopAwardCouponAdapter(Context context, ArrayList<JShopSpecialPriceBean> arrayList) {
        this.mContext = context;
        this.mActivity = (JShopSignNewActivity) context;
        this.list = arrayList;
    }

    private void clearData() {
        this.list.clear();
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<JShopSpecialPriceBean> arrayList = this.list;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i2) {
        return 0L;
    }

    @Override // android.widget.Adapter
    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = ImageUtil.inflate(R.layout.jshop_award_coupon, null);
            viewHolder = new ViewHolder();
            viewHolder.mProgressLayout = view.findViewById(R.id.coupon_item_progress_layout);
            viewHolder.mProgressNone = view.findViewById(R.id.coupon_item_progress_none);
            viewHolder.mCouponItem = view.findViewById(R.id.coupon_item);
            viewHolder.couponLeft = (LinearLayout) view.findViewById(R.id.coupon_item_left);
            viewHolder.mCouponUnit = (TextView) view.findViewById(R.id.coupon_list_unit);
            viewHolder.mCouponValue = (TextView) view.findViewById(R.id.coupon_list_value);
            viewHolder.mCouponType = (TextView) view.findViewById(R.id.coupon_list_type_name);
            viewHolder.mCouponShopName = (TextView) view.findViewById(R.id.coupon_shopname);
            viewHolder.mCouponTimeLimit = (TextView) view.findViewById(R.id.coupon_timelimit_content);
            viewHolder.mCouponQuote = (TextView) view.findViewById(R.id.coupon_quota);
            viewHolder.mCouponTaken = (ImageView) view.findViewById(R.id.coupon_taken);
            viewHolder.mCrmCouponIcon = (ImageView) view.findViewById(R.id.view_jshop_crm_coupon_icon);
            viewHolder.itemJumpTv = (TextView) view.findViewById(R.id.coupon_item_jump_tv);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.mCrmCouponIcon.setVisibility(8);
        viewHolder.mProgressLayout.setVisibility(8);
        viewHolder.mProgressNone.setVisibility(8);
        viewHolder.mCouponItem.setVisibility(0);
        final JShopSpecialPriceBean item = getItem(i2);
        if (item != null) {
            viewHolder.mCouponUnit.setVisibility(0);
            viewHolder.mCouponValue.setText(item.discount + "");
            viewHolder.mCouponShopName.setText("              " + item.prizeName);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(item.beginTime);
            stringBuffer.append("--");
            stringBuffer.append(item.endTime);
            viewHolder.mCouponTimeLimit.setText(stringBuffer.toString());
            viewHolder.mCouponQuote.setText("\u6ee1" + item.quota + "\u5143\u53ef\u7528");
            viewHolder.itemJumpTv.setVisibility(8);
            viewHolder.itemJumpTv.setText("");
            if (1 == item.type) {
                viewHolder.mCouponQuote.setVisibility(0);
                viewHolder.mCouponType.setText(this.mContext.getString(R.string.jshop_sign_coupon_dongquan));
                viewHolder.mCouponType.setBackgroundResource(R.drawable.shopping_cart_coupon_dong_icon_bg);
                viewHolder.couponLeft.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_big_right_blue_bg));
            } else {
                viewHolder.mCouponQuote.setVisibility(8);
                viewHolder.mCouponType.setText(this.mContext.getString(R.string.jshop_sign_coupon_jingquan));
                viewHolder.mCouponType.setBackgroundResource(R.drawable.shopping_cart_coupon_jing_icon_bg);
                viewHolder.couponLeft.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_big_right_red_bg));
            }
            if (item.prizeStatus == 0) {
                viewHolder.mCouponItem.setEnabled(true);
                viewHolder.mCouponTaken.setVisibility(8);
                view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JshopAwardCouponAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        JDMtaUtils.sendCommonData(JshopAwardCouponAdapter.this.mActivity, "MyPrize_CouponCheck", item.batchId + "", "", JshopAwardCouponAdapter.this.mActivity, JshopAwardCouponAdapter.this.mActivity.shopId, "JshopMainShopActivity", "", "ShopCheckIn_MyPrizeMain", JshopAwardCouponAdapter.this.mActivity.shopId);
                        DeepLinkJShopHomeHelper.gotoJShopHome(JshopAwardCouponAdapter.this.mActivity, JshopAwardCouponAdapter.this.mActivity.shopId, JshopAwardCouponAdapter.this.mActivity.venderId, "", "home", new SourceEntity(JshopConst.SOURCE_ENTITY_SHOP_SIGN_NEW, "\u6211\u7684\u5956\u54c1"));
                    }
                });
            } else {
                viewHolder.mCouponItem.setEnabled(false);
                viewHolder.mCouponTaken.setVisibility(8);
                viewHolder.couponLeft.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.jshop_exchange_big_right_grey_bg));
                viewHolder.mCouponType.setBackgroundResource(R.drawable.jsohp_coupon_icon_disable);
            }
        }
        return view;
    }

    public void setData(ArrayList<JShopSpecialPriceBean> arrayList) {
        clearData();
        this.list = arrayList;
    }

    @Override // android.widget.Adapter
    public JShopSpecialPriceBean getItem(int i2) {
        ArrayList<JShopSpecialPriceBean> arrayList = this.list;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return this.list.get(i2);
    }
}
