package com.jingdong.common.sample.jshop.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.WebActivity;
import com.jingdong.app.mall.utils.LoginUser;
import com.jingdong.app.mall.utils.MyActivity;
import com.jingdong.app.mall.utils.s;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.login.LoginUserHelper;
import com.jingdong.common.sample.jshop.Entity.DynamicShopActivity;
import com.jingdong.common.sample.jshop.Entity.DynamicShopProduct;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.constant.JshopConst;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.platform.business.personal.R2;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class JShopDynamicDetailLargeAdapter extends BaseAdapter {
    private static final int MARGIN = 17;
    private boolean isLastPage;
    private MyActivity mActivity;
    private Context mContext;
    private ArrayList<DynamicShopProduct> mProductList;
    private DynamicShopActivity mShopActivity;

    /* loaded from: classes6.dex */
    static class ViewHolder {
        View mAliveContainer;
        TextView mAliveForeshowTime;
        View mAliveIcon;
        ImageView mAliveStatus;
        View mAliveTopLayer;
        TextView mBuyShowBuyBtn;
        SimpleDraweeView mBuyShowPDImg;
        TextView mBuyShowPDName;
        TextView mBuyShowPDPrice;
        View mBuyShowPDView;
        RelativeLayout mContainer;
        TextView mJDPrice;
        TextView mLookDetail;
        TextView mMarketPrice;
        TextView mPDDescription;
        SimpleDraweeView mPDImg;
        TextView mPDMask;
        LinearLayout mPriceView;

        ViewHolder() {
        }
    }

    public JShopDynamicDetailLargeAdapter(MyActivity myActivity, DynamicShopActivity dynamicShopActivity, ArrayList<DynamicShopProduct> arrayList, boolean z) {
        this.isLastPage = false;
        this.mContext = myActivity;
        this.mActivity = myActivity;
        this.mShopActivity = dynamicShopActivity;
        this.mProductList = arrayList;
        this.isLastPage = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toSignPage() {
        LoginUserHelper.getInstance().executeLoginRunnable(this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.11
            @Override // java.lang.Runnable
            public void run() {
                DeepLinkJShopHomeHelper.gotoJShopSignUp(JShopDynamicDetailLargeAdapter.this.mActivity, JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.venderId + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopName, JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType, "", JShopDynamicDetailLargeAdapter.this.mShopActivity.followed);
            }
        });
    }

    @Override // android.widget.Adapter
    public int getCount() {
        DynamicShopActivity dynamicShopActivity = this.mShopActivity;
        if (dynamicShopActivity == null || dynamicShopActivity.activityType != 3) {
            if (dynamicShopActivity == null || dynamicShopActivity.activityType != 11) {
                boolean z = (dynamicShopActivity == null || dynamicShopActivity.promotionType == 1) ? false : true;
                if (dynamicShopActivity != null && z && dynamicShopActivity.activityType == 12) {
                    return 1;
                }
                if (dynamicShopActivity == null || dynamicShopActivity.activityType != 13) {
                    ArrayList<DynamicShopProduct> arrayList = this.mProductList;
                    if (arrayList == null) {
                        return 0;
                    }
                    return arrayList.size();
                }
                return 1;
            }
            return 1;
        }
        return 1;
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
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.jshop_dynamic_detail_large_item, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.mContainer = (RelativeLayout) inflate.findViewById(R.id.item_container);
            viewHolder.mPDImg = (SimpleDraweeView) inflate.findViewById(R.id.item_img);
            viewHolder.mPDMask = (TextView) inflate.findViewById(R.id.item_mask);
            viewHolder.mPriceView = (LinearLayout) inflate.findViewById(R.id.price_view);
            viewHolder.mJDPrice = (TextView) inflate.findViewById(R.id.jd_price);
            viewHolder.mMarketPrice = (TextView) inflate.findViewById(R.id.market_price);
            viewHolder.mPDDescription = (TextView) inflate.findViewById(R.id.item_des);
            viewHolder.mLookDetail = (TextView) inflate.findViewById(R.id.item_look_detail);
            viewHolder.mAliveIcon = inflate.findViewById(R.id.alive_icon);
            viewHolder.mAliveTopLayer = inflate.findViewById(R.id.alive_top_layer);
            viewHolder.mAliveContainer = inflate.findViewById(R.id.alive_status_ll);
            viewHolder.mAliveStatus = (ImageView) inflate.findViewById(R.id.alive_status);
            viewHolder.mAliveForeshowTime = (TextView) inflate.findViewById(R.id.alive_foreshow_time);
            viewHolder.mBuyShowPDView = inflate.findViewById(R.id.buyer_show_product_view);
            viewHolder.mBuyShowPDImg = (SimpleDraweeView) inflate.findViewById(R.id.buyer_show_product_img);
            viewHolder.mBuyShowPDName = (TextView) inflate.findViewById(R.id.buyer_show_product_name);
            viewHolder.mBuyShowPDPrice = (TextView) inflate.findViewById(R.id.buyer_show_product_price);
            viewHolder.mBuyShowBuyBtn = (TextView) inflate.findViewById(R.id.buyer_show_buy_btn);
            inflate.setTag(viewHolder);
            view2 = inflate;
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        final ViewHolder viewHolder2 = viewHolder;
        DynamicShopActivity dynamicShopActivity = this.mShopActivity;
        boolean z = (dynamicShopActivity == null || dynamicShopActivity.promotionType == 1) ? false : true;
        viewHolder2.mAliveStatus.setVisibility(8);
        viewHolder2.mAliveContainer.setVisibility(8);
        viewHolder2.mBuyShowPDView.setVisibility(8);
        DynamicShopActivity dynamicShopActivity2 = this.mShopActivity;
        if (dynamicShopActivity2 != null && dynamicShopActivity2.activityType == 16) {
            DynamicShopProduct dynamicShopProduct = this.mProductList.get(i2);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(DPIUtil.getWidth() - (DPIUtil.dip2px(17.0f) * 2), -2);
            if (i2 == this.mProductList.size() - 1) {
                layoutParams.setMargins(0, 0, 0, DPIUtil.dip2px(20.0f));
            } else {
                layoutParams.setMargins(0, 0, 0, DPIUtil.dip2px(10.0f));
            }
            viewHolder2.mPDImg.setLayoutParams(layoutParams);
            if (dynamicShopProduct != null) {
                JDImageUtils.displayImage(dynamicShopProduct.imgPath, (ImageView) viewHolder2.mPDImg, (JDDisplayImageOptions) null, false);
            }
            viewHolder2.mPDImg.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view3) {
                }
            });
            viewHolder2.mPriceView.setVisibility(8);
            viewHolder2.mPDMask.setVisibility(8);
            viewHolder2.mPDDescription.setVisibility(8);
            viewHolder2.mLookDetail.setVisibility(8);
            ArrayList<DynamicShopProduct> productList = DynamicShopProduct.toProductList(this.mShopActivity.getProducts());
            if (this.isLastPage && i2 >= this.mProductList.size() - 1 && productList.size() > 0) {
                final DynamicShopProduct dynamicShopProduct2 = productList.get(0);
                viewHolder2.mBuyShowPDView.setVisibility(0);
                JDImageUtils.displayImage(dynamicShopProduct2.imgPath, viewHolder2.mBuyShowPDImg);
                viewHolder2.mBuyShowBuyBtn.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        viewHolder2.mBuyShowPDView.performClick();
                    }
                });
                viewHolder2.mBuyShowPDView.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        DeeplinkProductDetailHelper.BundleBuilder from = DeeplinkProductDetailHelper.BundleBuilder.from(Long.parseLong(dynamicShopProduct2.wareId));
                        DynamicShopProduct dynamicShopProduct3 = dynamicShopProduct2;
                        s.g(JShopDynamicDetailLargeAdapter.this.mActivity, from.imageTitlePrice(dynamicShopProduct3.imgPath, dynamicShopProduct3.wareName, dynamicShopProduct3.jdPrice).build());
                    }
                });
                viewHolder2.mBuyShowPDName.setText(dynamicShopProduct2.wareName);
                if (!JShopUtil.isPrice(dynamicShopProduct2.jdPrice) && !JShopUtil.isToPublishPrice(dynamicShopProduct2.jdPrice)) {
                    viewHolder2.mBuyShowPDPrice.setText(dynamicShopProduct2.jdPrice);
                } else {
                    viewHolder2.mBuyShowPDPrice.setText(this.mActivity.getResources().getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(dynamicShopProduct2.jdPrice));
                }
            }
            Context context = this.mContext;
            if (context != null) {
                viewHolder2.mPDImg.setContentDescription(context.getString(R.string.jshop_dynamic_noHinder_pic));
            }
        } else {
            if (dynamicShopActivity2 != null) {
                long j2 = dynamicShopActivity2.activityType;
                if (j2 == 3 || (z && j2 == 12)) {
                    int width = DPIUtil.getWidth() - (DPIUtil.dip2px(17.0f) * 2);
                    viewHolder2.mContainer.setLayoutParams(new LinearLayout.LayoutParams(width, this.mShopActivity.activitySubType == 1 ? width : (width * 280) / 702));
                    DynamicShopActivity dynamicShopActivity3 = this.mShopActivity;
                    if (dynamicShopActivity3.activitySubType == 1) {
                        JDImageUtils.displayImage(JShopUtil.checkImageUrl(dynamicShopActivity3.subjectUrl), viewHolder2.mPDImg);
                    } else {
                        JDImageUtils.displayImage(JShopUtil.checkImageUrl(dynamicShopActivity3.subjectUrl), viewHolder2.mPDImg, new JDDisplayImageOptions().setPlaceholder(19));
                    }
                    viewHolder2.mPDImg.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view3) {
                            JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicState_Topic", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.activityId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.source + "_\u52a8\u6001\u8be6\u60c5\u9875_0", "", JShopDynamicDetailLargeAdapter.this.mActivity, "", WebActivity.class.getSimpleName(), "", "ShopDynamicState_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                            JShopUtil.toWebWithLogin(JShopDynamicDetailLargeAdapter.this.mActivity, JShopDynamicDetailLargeAdapter.this.mShopActivity.mUrl);
                        }
                    });
                    viewHolder2.mPriceView.setVisibility(8);
                    viewHolder2.mPDMask.setVisibility(8);
                    viewHolder2.mPDDescription.setVisibility(8);
                    viewHolder2.mLookDetail.setVisibility(0);
                    viewHolder2.mLookDetail.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view3) {
                            JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicState_Topic", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.activityId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.source + "_\u52a8\u6001\u8be6\u60c5\u9875_0", "", JShopDynamicDetailLargeAdapter.this.mActivity, "", WebActivity.class.getSimpleName(), "", "ShopDynamicState_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                            JShopUtil.toWebWithLogin(JShopDynamicDetailLargeAdapter.this.mActivity, JShopDynamicDetailLargeAdapter.this.mShopActivity.mUrl);
                        }
                    });
                    Context context2 = this.mContext;
                    if (context2 != null) {
                        viewHolder2.mPDImg.setContentDescription(context2.getString(R.string.jshop_dynamic_noHinder_pic));
                    }
                }
            }
            if (dynamicShopActivity2 != null && dynamicShopActivity2.activityType == 11) {
                int width2 = DPIUtil.getWidth() - (DPIUtil.dip2px(17.0f) * 2);
                viewHolder2.mContainer.setLayoutParams(new LinearLayout.LayoutParams(width2, (width2 * 180) / R2.attr.decimalNumber));
                if (TextUtils.isEmpty(this.mShopActivity.signPic)) {
                    if (this.mShopActivity.activitySubType == 1) {
                        viewHolder2.mPDImg.setBackgroundResource(R.drawable.jshop_dynamic_sign_big_icon);
                    } else {
                        viewHolder2.mPDImg.setBackgroundResource(R.drawable.jshop_dynamic_prize_big_icon);
                    }
                } else {
                    JDImageUtils.displayImage(JShopUtil.checkImageUrl(this.mShopActivity.signPic), viewHolder2.mPDImg);
                }
                viewHolder2.mContainer.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicState_Topic", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.activityId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.source + "_\u52a8\u6001\u8be6\u60c5\u9875_0", "", JShopDynamicDetailLargeAdapter.this.mActivity, "", WebActivity.class.getSimpleName(), "", "ShopDynamicState_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                        JShopDynamicDetailLargeAdapter.this.toSignPage();
                    }
                });
                viewHolder2.mPriceView.setVisibility(8);
                viewHolder2.mPDMask.setVisibility(8);
                viewHolder2.mPDDescription.setVisibility(8);
                viewHolder2.mLookDetail.setVisibility(8);
                viewHolder2.mLookDetail.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicState_Topic", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.activityId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.source + "_\u52a8\u6001\u8be6\u60c5\u9875_0", "", JShopDynamicDetailLargeAdapter.this.mActivity, "", WebActivity.class.getSimpleName(), "", "ShopDynamicState_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                        JShopDynamicDetailLargeAdapter.this.toSignPage();
                    }
                });
                Context context3 = this.mContext;
                if (context3 != null) {
                    viewHolder2.mPDImg.setContentDescription(context3.getString(R.string.jshop_dynamic_noHinder_pic));
                }
            } else if (dynamicShopActivity2 != null && dynamicShopActivity2.activityType == 13) {
                int width3 = DPIUtil.getWidth() - (DPIUtil.dip2px(17.0f) * 2);
                viewHolder2.mContainer.setLayoutParams(new LinearLayout.LayoutParams(width3, this.mShopActivity.coverType == 0 ? width3 : (width3 * 280) / 702));
                DynamicShopActivity dynamicShopActivity4 = this.mShopActivity;
                if (dynamicShopActivity4.coverType == 0) {
                    JDImageUtils.displayImage(JShopUtil.checkImageUrl(dynamicShopActivity4.subjectUrl), viewHolder2.mPDImg);
                } else {
                    JDImageUtils.displayImage(JShopUtil.checkImageUrl(dynamicShopActivity4.subjectUrl), viewHolder2.mPDImg, new JDDisplayImageOptions().setPlaceholder(19));
                }
                viewHolder2.mPriceView.setVisibility(8);
                viewHolder2.mPDMask.setVisibility(8);
                viewHolder2.mPDDescription.setVisibility(8);
                viewHolder2.mLookDetail.setVisibility(0);
                viewHolder2.mAliveTopLayer.setVisibility(0);
                viewHolder2.mAliveIcon.setVisibility(0);
                viewHolder2.mAliveIcon.setBackgroundResource(R.drawable.jshop_alive_icon);
                viewHolder2.mAliveContainer.setVisibility(0);
                JShopUtil.updateAliveView(this.mActivity, viewHolder2.mAliveStatus, viewHolder2.mAliveForeshowTime, this.mShopActivity);
                final SimpleDraweeView simpleDraweeView = viewHolder2.mPDImg;
                viewHolder2.mLookDetail.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.8
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        simpleDraweeView.performClick();
                    }
                });
                final ImageView imageView = viewHolder2.mAliveStatus;
                final TextView textView = viewHolder2.mAliveForeshowTime;
                final SimpleDraweeView simpleDraweeView2 = viewHolder2.mPDImg;
                simpleDraweeView2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.9
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view3) {
                        JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicState_Topic", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.activityId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.source + "_\u52a8\u6001\u8be6\u60c5\u9875_0", "", JShopDynamicDetailLargeAdapter.this.mActivity, "", WebActivity.class.getSimpleName(), "", "ShopDynamicState_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                        LoginUser.getInstance().executeLoginRunnable(JShopDynamicDetailLargeAdapter.this.mActivity, new Runnable() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                MyActivity myActivity = JShopDynamicDetailLargeAdapter.this.mActivity;
                                DynamicShopActivity dynamicShopActivity5 = JShopDynamicDetailLargeAdapter.this.mShopActivity;
                                AnonymousClass9 anonymousClass9 = AnonymousClass9.this;
                                JShopUtil.jumpAliveRoom(myActivity, null, dynamicShopActivity5, imageView, textView, simpleDraweeView2);
                            }
                        });
                    }
                });
                Context context4 = this.mContext;
                if (context4 != null) {
                    viewHolder2.mPDImg.setContentDescription(context4.getString(R.string.jshop_dynamic_noHinder_video));
                }
            } else {
                final DynamicShopProduct item = getItem(i2);
                if (item != null) {
                    int width4 = DPIUtil.getWidth() - (DPIUtil.dip2px(17.0f) * 2);
                    viewHolder2.mContainer.setLayoutParams(new LinearLayout.LayoutParams(width4, width4));
                    JDImageUtils.displayImage(JShopUtil.checkImageUrl(item.imgPath), viewHolder2.mPDImg);
                    view2.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JShopDynamicDetailLargeAdapter.10
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view3) {
                            JDMtaUtils.sendCommonData(JShopDynamicDetailLargeAdapter.this.mActivity, "ShopDynamicStateDetail_Product", JShopUtil.getActivityType(JShopDynamicDetailLargeAdapter.this.mShopActivity.activityType + "", JShopDynamicDetailLargeAdapter.this.mShopActivity.activitySubType) + CartConstant.KEY_YB_INFO_LINK + JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + item.wareId, "", JShopDynamicDetailLargeAdapter.this.mActivity, "", JshopConst.PRODUCT_DETAIL, "", "ShopDynamicStateDetail_Main", JShopDynamicDetailLargeAdapter.this.mShopActivity.shopId + "");
                            DeeplinkProductDetailHelper.BundleBuilder from = DeeplinkProductDetailHelper.BundleBuilder.from(Long.parseLong(item.wareId));
                            DynamicShopProduct dynamicShopProduct3 = item;
                            s.g(JShopDynamicDetailLargeAdapter.this.mActivity, from.imageTitlePrice(dynamicShopProduct3.imgPath, dynamicShopProduct3.wareName, dynamicShopProduct3.jdPrice).build());
                        }
                    });
                    JShopUtil.showProductMask(viewHolder2.mPDMask, this.mShopActivity.activityType, item.status);
                    viewHolder2.mPriceView.setVisibility(8);
                    if (this.mShopActivity.activityType != 3) {
                        viewHolder2.mPriceView.setVisibility(0);
                        String str = item.jdPrice;
                        String str2 = item.mPrice;
                        if (!JShopUtil.isPrice(str) && !JShopUtil.isToPublishPrice(str)) {
                            viewHolder2.mJDPrice.setText(str);
                        } else {
                            viewHolder2.mJDPrice.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str));
                        }
                        if (this.mShopActivity.activityType != 1 && JShopUtil.isPrice(str2)) {
                            viewHolder2.mMarketPrice.setVisibility(0);
                            viewHolder2.mMarketPrice.setText(this.mContext.getString(R.string.product_jd_price_label) + JShopUtil.formatPrice(str2));
                            viewHolder2.mMarketPrice.getPaint().setFlags(17);
                        } else {
                            viewHolder2.mMarketPrice.setVisibility(8);
                            viewHolder2.mMarketPrice.setText(str);
                            viewHolder2.mPriceView.setGravity(17);
                        }
                    } else {
                        viewHolder2.mPriceView.setVisibility(8);
                    }
                    viewHolder2.mPDDescription.setVisibility(0);
                    viewHolder2.mPDDescription.setText(item.wareName);
                    viewHolder2.mLookDetail.setVisibility(8);
                }
                Context context5 = this.mContext;
                if (context5 != null) {
                    viewHolder2.mPDImg.setContentDescription(context5.getString(R.string.jshop_dynamic_noHinder_pic));
                }
            }
        }
        return view2;
    }

    public void setList(ArrayList<DynamicShopProduct> arrayList, boolean z) {
        this.mProductList = arrayList;
        this.isLastPage = z;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public DynamicShopProduct getItem(int i2) {
        ArrayList<DynamicShopProduct> arrayList = this.mProductList;
        if (arrayList == null || arrayList.size() <= 0) {
            return null;
        }
        return this.mProductList.get(i2);
    }
}
