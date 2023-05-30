package com.jingdong.common.sample.jshop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.jd.framework.json.JDJSONObject;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopProduct;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopDynamicDetailSmallAdapter extends BaseAdapter {
    private static final String TAG = "JShopDynamicDetailSmallAdapter";
    private MyActivity mActivity;
    private Context mContext;
    private ArrayList<ActivityTwoProduct> mProductList;
    private DynamicShopActivity mShopActivity;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ActivityTwoProduct {
        public String imgPath1;
        public String imgPath2;
        public String jdPrice1;
        public String jdPrice2;
        public String mPrice1;
        public String mPrice2;
        public JDJSONObject promotionFlag1;
        public JDJSONObject promotionFlag2;
        public int status1;
        public int status2;
        public String wareId1;
        public String wareId2;
        public String wareName1;
        public String wareName2;

        public ActivityTwoProduct(DynamicShopProduct dynamicShopProduct, DynamicShopProduct dynamicShopProduct2) {
            if (dynamicShopProduct != null) {
                this.mPrice1 = dynamicShopProduct.mPrice;
                this.jdPrice1 = dynamicShopProduct.jdPrice;
                this.wareName1 = dynamicShopProduct.wareName;
                this.imgPath1 = dynamicShopProduct.imgPath;
                this.wareId1 = dynamicShopProduct.wareId;
                this.status1 = dynamicShopProduct.status;
                this.promotionFlag1 = dynamicShopProduct.getPromotionFlag();
            }
            if (dynamicShopProduct2 != null) {
                this.mPrice2 = dynamicShopProduct2.mPrice;
                this.jdPrice2 = dynamicShopProduct2.jdPrice;
                this.wareName2 = dynamicShopProduct2.wareName;
                this.imgPath2 = dynamicShopProduct2.imgPath;
                this.wareId2 = dynamicShopProduct2.wareId;
                this.status2 = dynamicShopProduct2.status;
                this.promotionFlag2 = dynamicShopProduct2.getPromotionFlag();
            }
        }

        public static ArrayList<ActivityTwoProduct> toList(ArrayList<DynamicShopProduct> arrayList) {
            ArrayList<ActivityTwoProduct> arrayList2 = new ArrayList<>();
            if (arrayList != null && arrayList.size() > 0) {
                for (int i2 = 0; i2 < arrayList.size(); i2 += 2) {
                    if (i2 < arrayList.size() - 1) {
                        arrayList2.add(new ActivityTwoProduct(arrayList.get(i2), arrayList.get(i2 + 1)));
                    }
                    if (i2 == arrayList.size() - 1) {
                        arrayList2.add(new ActivityTwoProduct(arrayList.get(i2), null));
                    }
                }
            }
            Log.d(JShopDynamicDetailSmallAdapter.TAG, "   === two product list size ==> :  " + arrayList2.size());
            return arrayList2;
        }
    }

    /* loaded from: classes6.dex */
    static class ViewHolder {
        TextView mJDPrice1;
        TextView mJDPrice2;
        TextView mMarketPrice1;
        TextView mMarketPrice2;
        TextView mPDDescription1;
        TextView mPDDescription2;
        ImageView mPDImg1;
        ImageView mPDImg2;
        TextView mPDMask1;
        TextView mPDMask2;
        View mPriceView1;
        View mPriceView2;
        View mProductView1;
        View mProductView2;

        ViewHolder() {
        }
    }

    public JShopDynamicDetailSmallAdapter(MyActivity myActivity, DynamicShopActivity dynamicShopActivity, ArrayList<DynamicShopProduct> arrayList) {
        this.mContext = myActivity;
        this.mActivity = myActivity;
        this.mShopActivity = dynamicShopActivity;
        this.mProductList = ActivityTwoProduct.toList(arrayList);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        ArrayList<ActivityTwoProduct> arrayList = this.mProductList;
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
    public View getView(final int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view2;
        if (view == null) {
            view2 = LayoutInflater.from(this.mContext).inflate(R.layout.jshop_dynamic_detail_small_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mProductView1 = view2.findViewById(R.id.item_pd_1);
            viewHolder.mPDImg1 = (ImageView) view2.findViewById(R.id.item_img_1);
            viewHolder.mPDMask1 = (TextView) view2.findViewById(R.id.item_mask_1);
            viewHolder.mPriceView1 = view2.findViewById(R.id.item_price_view_1);
            viewHolder.mJDPrice1 = (TextView) view2.findViewById(R.id.jd_price_1);
            viewHolder.mMarketPrice1 = (TextView) view2.findViewById(R.id.market_price_1);
            viewHolder.mPDDescription1 = (TextView) view2.findViewById(R.id.item_des_1);
            viewHolder.mProductView2 = view2.findViewById(R.id.item_pd_2);
            viewHolder.mPDImg2 = (ImageView) view2.findViewById(R.id.item_img_2);
            viewHolder.mPDMask2 = (TextView) view2.findViewById(R.id.item_mask_2);
            viewHolder.mPriceView2 = view2.findViewById(R.id.item_price_view_2);
            viewHolder.mJDPrice2 = (TextView) view2.findViewById(R.id.jd_price_2);
            viewHolder.mMarketPrice2 = (TextView) view2.findViewById(R.id.market_price_2);
            viewHolder.mPDDescription2 = (TextView) view2.findViewById(R.id.item_des_2);
            view2.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        final ActivityTwoProduct item = getItem(i2);
        if (item != null) {
            int width = (DPIUtil.getWidth() - DPIUtil.dip2px(25.0f)) >> 1;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, width);
            viewHolder.mPDImg1.setLayoutParams(layoutParams);
            viewHolder.mPDImg2.setLayoutParams(layoutParams);
            if (!TextUtils.isEmpty(item.wareId1)) {
                viewHolder.mProductView1.setVisibility(0);
                JDImageUtils.displayImage(JShopUtil.checkImageUrl(item.imgPath1), viewHolder.mPDImg1);
                viewHolder.mProductView1.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailSmallAdapter.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        JDMtaUtils.sendCommonData(JShopDynamicDetailSmallAdapter.this.mActivity, "ShopDynamicStateDetail_Product", JShopUtil.getActivityType(JShopDynamicDetailSmallAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailSmallAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailSmallAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + (i2 * 2) + CartConstant.KEY_YB_INFO_LINK + item.wareId1, "", JShopDynamicDetailSmallAdapter.this.mActivity, "", JshopConst.PRODUCT_DETAIL, "", "ShopDynamicStateDetail_Main", JShopDynamicDetailSmallAdapter.this.mShopActivity.shopId + "");
                        DeeplinkProductDetailHelper.BundleBuilder from = DeeplinkProductDetailHelper.BundleBuilder.from(Long.parseLong(item.wareId1));
                        ActivityTwoProduct activityTwoProduct = item;
                        s.g(JShopDynamicDetailSmallAdapter.this.mActivity, from.imageTitlePrice(activityTwoProduct.imgPath1, activityTwoProduct.wareName1, activityTwoProduct.jdPrice1).build());
                    }
                });
                JShopUtil.showProductMask(viewHolder.mPDMask1, this.mShopActivity.activityType, item.status1);
                viewHolder.mPriceView1.setVisibility(8);
                if (this.mShopActivity.activityType != 3) {
                    viewHolder.mPriceView1.setVisibility(0);
                    String str = item.jdPrice1;
                    String str2 = item.mPrice1;
                    if (!JShopUtil.isPrice(str) && !JShopUtil.isToPublishPrice(str)) {
                        viewHolder.mJDPrice1.setText(str);
                    } else {
                        viewHolder.mJDPrice1.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str));
                    }
                    if (this.mShopActivity.activityType != 1 && (JShopUtil.isPrice(str2) || JShopUtil.isToPublishPrice(str2))) {
                        viewHolder.mMarketPrice1.setVisibility(0);
                        viewHolder.mMarketPrice1.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str2));
                        viewHolder.mMarketPrice1.getPaint().setFlags(17);
                    } else {
                        viewHolder.mMarketPrice1.setVisibility(8);
                        viewHolder.mMarketPrice1.setText(str);
                    }
                } else {
                    viewHolder.mPriceView1.setVisibility(8);
                }
                viewHolder.mPDDescription1.setVisibility(0);
                viewHolder.mPDDescription1.setText(item.wareName1);
            } else {
                viewHolder.mProductView1.setVisibility(8);
            }
            if (!TextUtils.isEmpty(item.wareId2)) {
                viewHolder.mProductView2.setVisibility(0);
                JDImageUtils.displayImage(JShopUtil.checkImageUrl(item.imgPath2), viewHolder.mPDImg2);
                viewHolder.mProductView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailSmallAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        JDMtaUtils.sendCommonData(JShopDynamicDetailSmallAdapter.this.mActivity, "ShopDynamicStateDetail_Product", JShopUtil.getActivityType(JShopDynamicDetailSmallAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailSmallAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailSmallAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + ((i2 * 2) + 1) + CartConstant.KEY_YB_INFO_LINK + item.wareId2, "", JShopDynamicDetailSmallAdapter.this.mActivity, "", JshopConst.PRODUCT_DETAIL, "", "ShopDynamicStateDetail_Main", JShopDynamicDetailSmallAdapter.this.mShopActivity.shopId + "");
                        DeeplinkProductDetailHelper.BundleBuilder from = DeeplinkProductDetailHelper.BundleBuilder.from(Long.parseLong(item.wareId2));
                        ActivityTwoProduct activityTwoProduct = item;
                        s.g(JShopDynamicDetailSmallAdapter.this.mActivity, from.imageTitlePrice(activityTwoProduct.imgPath2, activityTwoProduct.wareName2, activityTwoProduct.jdPrice2).build());
                    }
                });
                JShopUtil.showProductMask(viewHolder.mPDMask2, this.mShopActivity.activityType, item.status2);
                viewHolder.mPriceView2.setVisibility(8);
                if (this.mShopActivity.activityType != 3) {
                    viewHolder.mPriceView2.setVisibility(0);
                    String str3 = item.jdPrice2;
                    String str4 = item.mPrice2;
                    if (!JShopUtil.isPrice(str3) && !JShopUtil.isToPublishPrice(str3)) {
                        viewHolder.mJDPrice2.setText(str3);
                    } else {
                        viewHolder.mJDPrice2.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str3));
                    }
                    if (this.mShopActivity.activityType != 1 && (JShopUtil.isPrice(str4) || JShopUtil.isToPublishPrice(str4))) {
                        viewHolder.mMarketPrice2.setVisibility(0);
                        viewHolder.mMarketPrice2.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str4));
                        viewHolder.mMarketPrice2.getPaint().setFlags(17);
                    } else {
                        viewHolder.mMarketPrice2.setVisibility(8);
                        viewHolder.mMarketPrice2.setText(str3);
                    }
                } else {
                    viewHolder.mPriceView2.setVisibility(8);
                }
                viewHolder.mPDDescription2.setVisibility(0);
                viewHolder.mPDDescription2.setText(item.wareName2);
            } else {
                viewHolder.mProductView2.setVisibility(4);
            }
        }
        return view2;
    }

    public void setList(ArrayList<DynamicShopProduct> arrayList) {
        this.mProductList = ActivityTwoProduct.toList(arrayList);
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public ActivityTwoProduct getItem(int i2) {
        ArrayList<ActivityTwoProduct> arrayList = this.mProductList;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return this.mProductList.get(i2);
    }
}
