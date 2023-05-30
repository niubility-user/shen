package com.jingdong.common.sample.jshop;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.helper.PDHelper;
import com.jingdong.common.sample.jshop.Entity.JShopSpecialPriceBean;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.utils.ImageUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopSpecialPriceAdapter extends BaseAdapter {
    private boolean isShowFirstSubView;
    private ArrayList<JShopSpecialPriceBean> list;
    private JShopSignNewActivity mActivity;

    /* loaded from: classes6.dex */
    static class ViewHolder {
        View empty_view;
        TextView sku_check;
        TextView sku_des;
        ImageView sku_img;
        TextView status;
        TextView status_des;
        TextView time;

        ViewHolder() {
        }
    }

    public JShopSpecialPriceAdapter(Context context, ArrayList<JShopSpecialPriceBean> arrayList, boolean z) {
        this.mActivity = (JShopSignNewActivity) context;
        this.list = arrayList;
        this.isShowFirstSubView = z;
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
            view = ImageUtil.inflate(R.layout.jshop_special_price_item, null);
            viewHolder = new ViewHolder();
            viewHolder.empty_view = view.findViewById(R.id.empty_view);
            viewHolder.time = (TextView) view.findViewById(R.id.a2j);
            viewHolder.status = (TextView) view.findViewById(R.id.status);
            viewHolder.status_des = (TextView) view.findViewById(R.id.status_des);
            viewHolder.sku_img = (ImageView) view.findViewById(R.id.sku_img);
            viewHolder.sku_des = (TextView) view.findViewById(R.id.sku_des);
            viewHolder.sku_check = (TextView) view.findViewById(R.id.sku_check);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (!this.isShowFirstSubView && i2 == 0) {
            viewHolder.empty_view.setVisibility(8);
        } else {
            viewHolder.empty_view.setVisibility(0);
        }
        final JShopSpecialPriceBean item = getItem(i2);
        if (item != null) {
            viewHolder.time.setText(item.accessTime);
            if (item.prizeStatus == 1) {
                viewHolder.status_des.setVisibility(8);
                viewHolder.status.setVisibility(0);
                viewHolder.status.setText(R.string.jshop_status_overdue);
            } else {
                viewHolder.status.setVisibility(0);
                viewHolder.status.setText(R.string.jshop_status_not_overdue);
                viewHolder.status_des.setVisibility(0);
                viewHolder.status_des.setText(Html.fromHtml(this.mActivity.getResources().getString(R.string.jshop_status_des_1) + "<font color='#f15353'>" + item.expirationTime + "</font>" + this.mActivity.getResources().getString(R.string.jshop_status_des_2)));
            }
            JDImageUtils.displayImage(JShopUtil.checkImageUrl(item.imgUrl), viewHolder.sku_img);
            viewHolder.sku_des.setText(item.prizeInfo);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopSpecialPriceAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    JDMtaUtils.sendCommonData(JShopSpecialPriceAdapter.this.mActivity, "MyPrize_Productid", "\u4e13\u4eab_" + item.wareId + CartConstant.KEY_YB_INFO_LINK + item.zxPrice, "", JShopSpecialPriceAdapter.this.mActivity, JShopSpecialPriceAdapter.this.mActivity.shopId, PDHelper.getPDClassName(), "", "ShopCheckIn_MyPrizeMain", JShopSpecialPriceAdapter.this.mActivity.shopId);
                    JShopSpecialPriceAdapter.this.toProductDetail(item);
                }
            });
            viewHolder.sku_check.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.JShopSpecialPriceAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    JDMtaUtils.sendCommonData(JShopSpecialPriceAdapter.this.mActivity, "MyPrize_ProductDetail", "\u4e13\u4eab_" + item.wareId + CartConstant.KEY_YB_INFO_LINK + item.zxPrice, "", JShopSpecialPriceAdapter.this.mActivity, JShopSpecialPriceAdapter.this.mActivity.shopId, PDHelper.getPDClassName(), "", "ShopCheckIn_MyPrizeMain", JShopSpecialPriceAdapter.this.mActivity.shopId);
                    JShopSpecialPriceAdapter.this.toProductDetail(item);
                }
            });
        }
        return view;
    }

    public void setData(ArrayList<JShopSpecialPriceBean> arrayList, boolean z) {
        clearData();
        this.list = arrayList;
        this.isShowFirstSubView = z;
    }

    public void toProductDetail(JShopSpecialPriceBean jShopSpecialPriceBean) {
        if (TextUtils.isEmpty(jShopSpecialPriceBean.wareId)) {
            return;
        }
        try {
            s.g(this.mActivity, DeeplinkProductDetailHelper.BundleBuilder.from(Long.parseLong(jShopSpecialPriceBean.wareId)).imageTitlePrice(jShopSpecialPriceBean.imgUrl, "", "").build());
        } catch (NumberFormatException unused) {
        }
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
