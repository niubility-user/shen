package com.jingdong.common.sample.jshop.adapter;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.dynamic.DYConstants;
import com.jingdong.app.mall.R;
import com.jingdong.app.mall.utils.s;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.deeplinkhelper.DeepLinkJShopHomeHelper;
import com.jingdong.common.deeplinkhelper.DeeplinkProductDetailHelper;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.SourceEntity;
import com.jingdong.common.frame.IMyActivity;
import com.jingdong.common.jump.OpenAppJumpController;
import com.jingdong.common.sample.jshop.ui.UiModeLinearLayout;
import com.jingdong.common.sample.jshop.utils.JShopUtil;
import com.jingdong.common.sample.jshop.utils.JshopTextViewUtils;
import com.jingdong.common.sample.jshop.utils.SpannableStringUtils;
import com.jingdong.common.sample.json.JshopSearchItem;
import com.jingdong.common.ui.ShopRatingBar;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.DeepDarkChangeManager;
import com.jingdong.common.utils.DeepDarkUtils;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.MySimpleAdapter;
import com.jingdong.corelib.utils.Log;
import com.jingdong.jdsdk.constant.CartConstant;
import com.jingdong.jdsdk.mta.JDMtaUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class JshopSearchListAdapter extends MySimpleAdapter {
    public static final int FONT_SIZE = 12;
    private static final String TAG = "JshopSearchListAdapter";
    public int imageWidth;
    private SparseBooleanArray itemClickedArray;
    public String keyword;
    public BaseActivity mActivity;
    public String mCategoryId;
    public String mDeviceId;
    public String mPvid;
    public SourceEntity mSource;
    public String mSourcePage;
    public String mTestId;
    public String mTestIdV665;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public class MyClickListener implements View.OnClickListener {
        int mFlag;
        JshopSearchItem mJshopSearchItem;
        int mMs;
        Product mProduct;
        int pPos;
        int position;

        public MyClickListener(JshopSearchItem jshopSearchItem, Product product, int i2, int i3, int i4) {
            this.mProduct = null;
            this.position = 0;
            this.mFlag = 0;
            this.pPos = 0;
            this.mMs = 0;
            this.mJshopSearchItem = jshopSearchItem;
            this.mProduct = product;
            this.position = i2;
            this.mFlag = i3;
            this.pPos = i4;
            this.mMs = product.isHot() ? 1 : 0;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TextUtils.isEmpty(JshopSearchListAdapter.this.mCategoryId)) {
                JshopSearchListAdapter.this.mCategoryId = DYConstants.DY_NULL_STR;
            }
            if (TextUtils.isEmpty(JshopSearchListAdapter.this.mDeviceId)) {
                JshopSearchListAdapter.this.mDeviceId = DYConstants.DY_NULL_STR;
            }
            boolean z = this.mJshopSearchItem.adShop;
            String str = this.mProduct.getShopId() + CartConstant.KEY_YB_INFO_LINK + this.mProduct.getId() + CartConstant.KEY_YB_INFO_LINK + this.pPos + CartConstant.KEY_YB_INFO_LINK + this.mFlag + CartConstant.KEY_YB_INFO_LINK + this.position + CartConstant.KEY_YB_INFO_LINK + this.mMs + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mCategoryId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mSourcePage + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mDeviceId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestIdV665 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(JshopSearchListAdapter.this.mPvid) + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(this.mJshopSearchItem.logid) + CartConstant.KEY_YB_INFO_LINK + (z ? 1 : 0);
            String str2 = JshopSearchListAdapter.this.keyword + "@@@" + JShopUtil.getMtaString(this.mJshopSearchItem.mtest);
            if (z) {
                JShopUtil.mtaUploadWithRequestUrl(this.mJshopSearchItem.clickUrl);
            }
            BaseActivity baseActivity = JshopSearchListAdapter.this.mActivity;
            JDMtaUtils.sendCommonData(baseActivity, "Searchlist_ShopProduct", str, "", baseActivity, str2, "JshopMainShopActivity", "", "SearchShop_ResultMain", this.mProduct.getShopId() + "");
            String openAppUrl = this.mProduct.getOpenAppUrl();
            if (!TextUtils.isEmpty(openAppUrl)) {
                Intent intent = new Intent();
                OKLog.d("JshopSearchListAdapter openAppUrl:", openAppUrl);
                intent.setData(Uri.parse(openAppUrl));
                OpenAppJumpController.dispatchJumpRequest(JshopSearchListAdapter.this.mActivity, intent);
                return;
            }
            s.g(JshopSearchListAdapter.this.mActivity, DeeplinkProductDetailHelper.BundleBuilder.from(this.mProduct.getId().longValue()).imageTitlePrice(this.mProduct.getImageUrl(), this.mProduct.getName(), this.mProduct.getJdPrice()).sourceEntity(JshopSearchListAdapter.this.mSource).build());
        }
    }

    /* loaded from: classes6.dex */
    private class ShopHeadEntranceListener implements View.OnClickListener {
        boolean isAdShop;
        JshopSearchItem jshopSearchItem;
        int position;

        ShopHeadEntranceListener(JshopSearchItem jshopSearchItem, int i2, boolean z) {
            this.jshopSearchItem = jshopSearchItem;
            this.position = i2;
            this.isAdShop = z;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JshopSearchItem jshopSearchItem = this.jshopSearchItem;
            boolean z = jshopSearchItem.isPop;
            boolean hasMiaosha = jshopSearchItem.hasMiaosha();
            if (TextUtils.isEmpty(JshopSearchListAdapter.this.mCategoryId)) {
                JshopSearchListAdapter.this.mCategoryId = DYConstants.DY_NULL_STR;
            }
            if (TextUtils.isEmpty(JshopSearchListAdapter.this.mDeviceId)) {
                JshopSearchListAdapter.this.mDeviceId = DYConstants.DY_NULL_STR;
            }
            String str = this.jshopSearchItem.shopId + CartConstant.KEY_YB_INFO_LINK + this.position + CartConstant.KEY_YB_INFO_LINK + (z ? 1 : 0) + CartConstant.KEY_YB_INFO_LINK + (hasMiaosha ? 1 : 0) + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mCategoryId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mSourcePage + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mDeviceId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestIdV665 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(JshopSearchListAdapter.this.mPvid) + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(this.jshopSearchItem.logid) + CartConstant.KEY_YB_INFO_LINK + (this.isAdShop ? 1 : 0);
            String str2 = JshopSearchListAdapter.this.keyword + "@@@" + JShopUtil.getMtaString(this.jshopSearchItem.mtest);
            if (this.isAdShop) {
                JShopUtil.mtaUploadWithRequestUrl(this.jshopSearchItem.clickUrl);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("extension_id", this.jshopSearchItem.extensionId);
            JDMtaUtils.sendCommonDataWithExt(JshopSearchListAdapter.this.mActivity, "Searchlist_Shopid", str, "", "", str2, "JshopMainShopActivity", "", "SearchShop_ResultMain", String.valueOf(this.jshopSearchItem.shopId), hashMap);
            DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(String.valueOf(this.jshopSearchItem.shopId), String.valueOf(this.jshopSearchItem.venderId), this.jshopSearchItem.shopName);
            from.addLogoUrl(this.jshopSearchItem.logo).addSignBoardUrl(this.jshopSearchItem.signboardUrl).addSource(JshopSearchListAdapter.this.mSource).startAllProductTabInfo().addKeyword(JshopSearchListAdapter.this.keyword).endAllProductInfo().addTestId(JshopSearchListAdapter.this.mTestId);
            DeepLinkJShopHomeHelper.gotoJShopHome(JshopSearchListAdapter.this.mActivity, from.build());
            if (JshopSearchListAdapter.this.itemClickedArray.get(this.position)) {
                return;
            }
            JshopSearchListAdapter.this.itemClickedArray.put(this.position, !JshopSearchListAdapter.this.itemClickedArray.get(this.position));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes6.dex */
    public static class ViewHolder {
        UiModeLinearLayout enterShopLay;
        TextView itemFavo;
        UiModeLinearLayout itemIconContainer;
        View itemIconView;
        ImageView itemLogo;
        TextView itemName;
        TextView itemNew;
        TextView itemPopShop;
        LinearLayout itemPrductPadding;
        TextView itemQuan;
        TextView itemScore;
        View itemScoreView;
        ShopRatingBar itemShopRatingBar;
        View jShopSearchItemHeader;
        View jShopSplitLine;
        View jShopSplitLine1;
        View jShopSplitLine2;
        SimpleDraweeView p1Icon;
        SimpleDraweeView p2Icon;
        SimpleDraweeView p3Icon;
        TextView pDetailAd;
        View pDetailLay;
        View pDetailLayChild;
        TextView price1;
        TextView price2;
        TextView price3;

        ViewHolder() {
        }
    }

    public JshopSearchListAdapter(IMyActivity iMyActivity, List<?> list, int i2, String[] strArr, int[] iArr) {
        super(iMyActivity, list, i2, strArr, iArr);
        this.keyword = "";
        this.mCategoryId = "";
        this.mDeviceId = "";
        this.mSourcePage = "1";
        this.mTestId = DYConstants.DY_NULL_STR;
        this.mTestIdV665 = DYConstants.DY_NULL_STR;
        this.imageWidth = (DPIUtil.getWidth() - DPIUtil.dip2px(34.0f)) / 3;
        this.itemClickedArray = new SparseBooleanArray();
        this.mActivity = (BaseActivity) iMyActivity;
    }

    private ViewHolder fillParams(View view) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.itemIconContainer = (UiModeLinearLayout) view.findViewById(R.id.jshop_search_item_icon);
        viewHolder.itemLogo = (ImageView) view.findViewById(R.id.jshop_list_item_logo);
        viewHolder.itemNew = (TextView) view.findViewById(R.id.jshop_list_item_new);
        viewHolder.itemQuan = (TextView) view.findViewById(R.id.jshop_list_item_quan);
        viewHolder.itemName = (TextView) view.findViewById(R.id.jshop_list_item_name);
        viewHolder.itemScore = (TextView) view.findViewById(R.id.jshop_list_item_score);
        viewHolder.itemFavo = (TextView) view.findViewById(R.id.jshop_list_item_favorite);
        viewHolder.itemPopShop = (TextView) view.findViewById(R.id.jshop_list_item_jdshop);
        viewHolder.itemScoreView = view.findViewById(R.id.jshop_list_rating_lay);
        viewHolder.itemIconView = view.findViewById(R.id.jshop_list_icons);
        viewHolder.itemShopRatingBar = (ShopRatingBar) view.findViewById(R.id.jshop_head_srb);
        viewHolder.jShopSplitLine = view.findViewById(R.id.jshop_split_line);
        viewHolder.jShopSearchItemHeader = view.findViewById(R.id.jshop_search_item_head);
        viewHolder.jShopSplitLine1 = view.findViewById(R.id.jshop_split_line_1);
        viewHolder.pDetailLay = view.findViewById(R.id.item_product_lay);
        viewHolder.pDetailLayChild = view.findViewById(R.id.item_product_child_lay);
        viewHolder.p1Icon = (SimpleDraweeView) view.findViewById(R.id.product_left);
        viewHolder.p2Icon = (SimpleDraweeView) view.findViewById(R.id.product_center);
        viewHolder.p3Icon = (SimpleDraweeView) view.findViewById(R.id.product_right);
        viewHolder.price1 = (TextView) view.findViewById(R.id.product_left_price);
        viewHolder.price2 = (TextView) view.findViewById(R.id.product_center_price);
        viewHolder.price3 = (TextView) view.findViewById(R.id.product_right_price);
        viewHolder.pDetailAd = (TextView) view.findViewById(R.id.jshop_ad_tag);
        viewHolder.enterShopLay = (UiModeLinearLayout) view.findViewById(R.id.jshop_search_item_to_shop);
        viewHolder.itemPrductPadding = (LinearLayout) view.findViewById(R.id.item_product_padding);
        viewHolder.jShopSplitLine2 = view.findViewById(R.id.jshop_split_line_2);
        if (JshopTextViewUtils.IS_LARGE_MODE) {
            JshopTextViewUtils.getInstance().doTextFontScaled(viewHolder.itemNew, viewHolder.itemQuan, viewHolder.itemName, viewHolder.itemScore, viewHolder.itemFavo, viewHolder.itemPopShop, viewHolder.price1, viewHolder.price2, viewHolder.price3, viewHolder.pDetailAd, view.findViewById(R.id.goto_shop_tv));
        }
        if (1 == DeepDarkChangeManager.getInstance().getUIMode()) {
            Log.d(TAG, "####DeepDarkChangeManager.MODE_DARK##");
            viewHolder.jShopSplitLine.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg3());
            viewHolder.itemIconContainer.setUiMode(1);
            viewHolder.jShopSearchItemHeader.setBackgroundColor(DeepDarkUtils.getDarkColor_FFFFFF());
            viewHolder.jShopSplitLine1.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg3());
            viewHolder.pDetailLay.setBackgroundColor(DeepDarkUtils.getDarkColor_FFFFFF());
            viewHolder.enterShopLay.setUiMode(1);
            viewHolder.itemPrductPadding.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg1());
            viewHolder.jShopSplitLine2.setBackgroundColor(DeepDarkUtils.getDarkColor_F2F2F2_bg3());
        } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
            viewHolder.jShopSplitLine.setBackgroundColor(this.mActivity.getResources().getColor(R.color.w5));
            viewHolder.itemIconContainer.setUiMode(0);
            viewHolder.jShopSearchItemHeader.setBackgroundColor(this.mActivity.getResources().getColor(R.color.lx));
            viewHolder.jShopSplitLine1.setBackgroundColor(this.mActivity.getResources().getColor(R.color.w5));
            viewHolder.pDetailLay.setBackgroundColor(this.mActivity.getResources().getColor(R.color.lx));
            viewHolder.enterShopLay.setUiMode(0);
            viewHolder.itemPrductPadding.setBackgroundColor(this.mActivity.getResources().getColor(R.color.j7));
            viewHolder.jShopSplitLine1.setBackgroundColor(this.mActivity.getResources().getColor(R.color.w5));
        }
        return viewHolder;
    }

    private Double getShopScore(Double d) {
        double round = Math.round(Double.valueOf(d.doubleValue() * 10.0d).doubleValue());
        Double.isNaN(round);
        return Double.valueOf(round / 10.0d);
    }

    @Override // com.jingdong.common.utils.MySimpleAdapter, com.jingdong.common.utils.SimpleBeanAdapter, android.widget.Adapter
    public View getView(final int i2, View view, ViewGroup viewGroup) {
        ViewHolder fillParams;
        String format;
        final JshopSearchItem jshopSearchItem = (JshopSearchItem) getItem(i2);
        View view2 = view == null ? super.getView(i2, view, viewGroup) : view;
        if (view2.getTag() != null) {
            fillParams = (ViewHolder) view2.getTag();
        } else {
            fillParams = fillParams(view2);
            view2.setTag(fillParams);
        }
        if (jshopSearchItem == null) {
            return view2;
        }
        final boolean z = jshopSearchItem.adShop;
        Log.d(TAG, "getView holder = " + fillParams);
        fillParams.itemName.setText(jshopSearchItem.shopName);
        fillParams.itemLogo.setImageResource(R.drawable.j3);
        JDImageUtils.displayImage(JShopUtil.checkImageUrl(jshopSearchItem.logo), fillParams.itemLogo, new JDDisplayImageOptions().setPlaceholder(19));
        setShopTag(fillParams, jshopSearchItem);
        if (!TextUtils.isEmpty(jshopSearchItem.scoreRankGrade)) {
            fillParams.itemShopRatingBar.setVisibility(0);
            fillParams.itemShopRatingBar.setText("");
            try {
                fillParams.itemShopRatingBar.setScore(Double.parseDouble(jshopSearchItem.scoreRankGrade));
            } catch (Exception unused) {
                fillParams.itemShopRatingBar.setVisibility(8);
            }
        } else {
            fillParams.itemShopRatingBar.setVisibility(8);
        }
        Long l2 = jshopSearchItem.followCount;
        if (Log.E) {
            Log.e(TAG, "fav = " + l2);
        }
        if (l2.longValue() >= 10000) {
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            double round = Math.round(((float) l2.longValue()) / 1000.0f);
            Double.isNaN(round);
            format = String.format(this.mActivity.getResources().getString(R.string.jshop_list_favcount_wan), decimalFormat.format(round / 10.0d));
        } else {
            String valueOf = String.valueOf(l2);
            if (Log.E) {
                Log.e(TAG, "fav str = " + valueOf);
            }
            format = String.format(this.mActivity.getResources().getString(R.string.jshop_list_favcount), valueOf);
        }
        if (Log.E) {
            Log.e(TAG, "strFavNum = " + format);
        }
        fillParams.itemFavo.setText(format);
        fillParams.jShopSearchItemHeader.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.sample.jshop.adapter.JshopSearchListAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                JshopSearchItem jshopSearchItem2 = jshopSearchItem;
                boolean z2 = jshopSearchItem2.isPop;
                boolean hasMiaosha = jshopSearchItem2.hasMiaosha();
                if (TextUtils.isEmpty(JshopSearchListAdapter.this.mCategoryId)) {
                    JshopSearchListAdapter.this.mCategoryId = DYConstants.DY_NULL_STR;
                }
                if (TextUtils.isEmpty(JshopSearchListAdapter.this.mDeviceId)) {
                    JshopSearchListAdapter.this.mDeviceId = DYConstants.DY_NULL_STR;
                }
                String str = jshopSearchItem.shopId + CartConstant.KEY_YB_INFO_LINK + i2 + CartConstant.KEY_YB_INFO_LINK + (z2 ? 1 : 0) + CartConstant.KEY_YB_INFO_LINK + (hasMiaosha ? 1 : 0) + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mCategoryId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mSourcePage + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mDeviceId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestId + CartConstant.KEY_YB_INFO_LINK + JshopSearchListAdapter.this.mTestIdV665 + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(JshopSearchListAdapter.this.mPvid) + CartConstant.KEY_YB_INFO_LINK + JShopUtil.getMtaString(jshopSearchItem.logid) + CartConstant.KEY_YB_INFO_LINK + (z ? 1 : 0);
                String str2 = JshopSearchListAdapter.this.keyword + "@@@" + JShopUtil.getMtaString(jshopSearchItem.mtest);
                if (z) {
                    JShopUtil.mtaUploadWithRequestUrl(jshopSearchItem.clickUrl);
                }
                HashMap hashMap = new HashMap();
                hashMap.put("extension_id", jshopSearchItem.extensionId);
                JDMtaUtils.sendCommonDataWithExt(JshopSearchListAdapter.this.mActivity, "Searchlist_Shopid", str, "", "", str2, "JshopMainShopActivity", "", "SearchShop_ResultMain", String.valueOf(jshopSearchItem.shopId), hashMap);
                DeepLinkJShopHomeHelper.JShopBundleBuilder from = DeepLinkJShopHomeHelper.JShopBundleBuilder.from(String.valueOf(jshopSearchItem.shopId), String.valueOf(jshopSearchItem.venderId), jshopSearchItem.shopName);
                from.addLogoUrl(jshopSearchItem.logo).addSignBoardUrl(jshopSearchItem.signboardUrl).addSource(JshopSearchListAdapter.this.mSource).startAllProductTabInfo().addKeyword(JshopSearchListAdapter.this.keyword).endAllProductInfo().addTestId(JshopSearchListAdapter.this.mTestId);
                DeepLinkJShopHomeHelper.gotoJShopHome(JshopSearchListAdapter.this.mActivity, from.build());
                if (JshopSearchListAdapter.this.itemClickedArray.get(i2)) {
                    return;
                }
                JshopSearchListAdapter.this.itemClickedArray.put(i2, !JshopSearchListAdapter.this.itemClickedArray.get(i2));
            }
        });
        setProductInfo(jshopSearchItem, fillParams, i2);
        if (this.itemClickedArray.get(i2)) {
            fillParams.itemScore.setText(this.mActivity.getString(R.string.jshop_overall_rating) + getShopScore(jshopSearchItem.score));
            if (1 == DeepDarkChangeManager.getInstance().getUIMode()) {
                fillParams.itemName.setTextColor(DeepDarkUtils.getDarkColor_262626());
                fillParams.itemFavo.setTextColor(DeepDarkUtils.getDarkColor_262626());
                fillParams.itemScore.setTextColor(DeepDarkUtils.getDarkColor_262626());
            } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
                fillParams.itemName.setTextColor(this.mActivity.getResources().getColor(R.color.jshop_search_shop_list_item_clicked));
                fillParams.itemFavo.setTextColor(this.mActivity.getResources().getColor(R.color.jshop_search_shop_list_item_clicked));
                fillParams.itemScore.setTextColor(this.mActivity.getResources().getColor(R.color.jshop_search_shop_list_item_clicked));
            }
        } else {
            if (1 == DeepDarkChangeManager.getInstance().getUIMode()) {
                fillParams.itemName.setTextColor(DeepDarkUtils.getDarkColor_8C8C8C());
                fillParams.itemFavo.setTextColor(DeepDarkUtils.getDarkColor_8C8C8C());
                fillParams.itemScore.setTextColor(DeepDarkUtils.getDarkColor_8C8C8C());
            } else if (DeepDarkChangeManager.getInstance().getUIMode() == 0) {
                fillParams.itemName.setTextColor(this.mActivity.getResources().getColor(R.color.jshop_search_shop_list_item_default));
                fillParams.itemFavo.setTextColor(this.mActivity.getResources().getColor(R.color.jshop_search_shop_list_item_favo));
            }
            if (jshopSearchItem.isPop) {
                setShopScore(fillParams, jshopSearchItem);
            }
        }
        if (z) {
            fillParams.pDetailAd.setOnClickListener(new ShopHeadEntranceListener(jshopSearchItem, i2, true));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) fillParams.pDetailLayChild.getLayoutParams();
            marginLayoutParams.setMargins(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, 0);
            fillParams.pDetailLayChild.setLayoutParams(marginLayoutParams);
            fillParams.pDetailAd.setVisibility(0);
        } else {
            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) fillParams.pDetailLayChild.getLayoutParams();
            marginLayoutParams2.setMargins(marginLayoutParams2.leftMargin, marginLayoutParams2.topMargin, marginLayoutParams2.rightMargin, DPIUtil.dip2px(this.mActivity, 8.0f));
            fillParams.pDetailLayChild.setLayoutParams(marginLayoutParams2);
            fillParams.pDetailAd.setVisibility(8);
        }
        return view2;
    }

    public void setProductInfo(JshopSearchItem jshopSearchItem, ViewHolder viewHolder, int i2) {
        viewHolder.p1Icon.setVisibility(8);
        viewHolder.p2Icon.setVisibility(8);
        viewHolder.p3Icon.setVisibility(8);
        viewHolder.price1.setVisibility(8);
        viewHolder.price2.setVisibility(8);
        viewHolder.price3.setVisibility(8);
        SimpleDraweeView simpleDraweeView = viewHolder.p1Icon;
        int i3 = this.imageWidth;
        simpleDraweeView.setLayoutParams(new RelativeLayout.LayoutParams(i3, i3));
        SimpleDraweeView simpleDraweeView2 = viewHolder.p2Icon;
        int i4 = this.imageWidth;
        simpleDraweeView2.setLayoutParams(new RelativeLayout.LayoutParams(i4, i4));
        SimpleDraweeView simpleDraweeView3 = viewHolder.p3Icon;
        int i5 = this.imageWidth;
        simpleDraweeView3.setLayoutParams(new RelativeLayout.LayoutParams(i5, i5));
        if (jshopSearchItem != null && !jshopSearchItem.getProducts().isEmpty()) {
            boolean z = jshopSearchItem.isPop;
            viewHolder.pDetailLay.setVisibility(0);
            if (jshopSearchItem.getProducts().size() == 1) {
                Product product = jshopSearchItem.getProducts().get(0);
                viewHolder.p1Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product, i2, z ? 1 : 0, 0));
                viewHolder.p1Icon.setVisibility(0);
                viewHolder.price1.setText(product.getJdPrice());
                viewHolder.price1.setVisibility(0);
                try {
                    viewHolder.price1.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product.getJdPrice()}), 12.0f));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                JDImageUtils.displayImage(product.getImageUrl(), viewHolder.p1Icon);
                return;
            } else if (jshopSearchItem.getProducts().size() == 2) {
                Product product2 = jshopSearchItem.getProducts().get(0);
                Product product3 = jshopSearchItem.getProducts().get(1);
                viewHolder.p1Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product2, i2, z ? 1 : 0, 0));
                viewHolder.p2Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product3, i2, z ? 1 : 0, 1));
                viewHolder.p1Icon.setVisibility(0);
                viewHolder.p2Icon.setVisibility(0);
                viewHolder.price1.setText(product2.getJdPrice());
                viewHolder.price2.setText(product3.getJdPrice());
                viewHolder.price1.setVisibility(0);
                viewHolder.price2.setVisibility(0);
                try {
                    viewHolder.price1.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product2.getJdPrice()}), 12.0f));
                    viewHolder.price2.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product3.getJdPrice()}), 12.0f));
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                JDImageUtils.displayImage(product2.getImageUrl(), viewHolder.p1Icon);
                JDImageUtils.displayImage(product3.getImageUrl(), viewHolder.p2Icon);
                return;
            } else if (jshopSearchItem.getProducts().size() >= 3) {
                Product product4 = jshopSearchItem.getProducts().get(0);
                Product product5 = jshopSearchItem.getProducts().get(1);
                Product product6 = jshopSearchItem.getProducts().get(2);
                viewHolder.p1Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product4, i2, z ? 1 : 0, 0));
                viewHolder.p2Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product5, i2, z ? 1 : 0, 1));
                viewHolder.p3Icon.setOnClickListener(new MyClickListener(jshopSearchItem, product6, i2, z ? 1 : 0, 2));
                viewHolder.p1Icon.setVisibility(0);
                viewHolder.p2Icon.setVisibility(0);
                viewHolder.p3Icon.setVisibility(0);
                try {
                    viewHolder.price1.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product4.getJdPrice()}), 12.0f));
                    viewHolder.price2.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product5.getJdPrice()}), 12.0f));
                    viewHolder.price3.setText(SpannableStringUtils.getJDPriceSpan(this.mActivity.getString(R.string.pg_home_jdpirce, new Object[]{product6.getJdPrice()}), 12.0f));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                viewHolder.price1.setVisibility(0);
                viewHolder.price2.setVisibility(0);
                viewHolder.price3.setVisibility(0);
                JDImageUtils.displayImage(product4.getImageUrl(), viewHolder.p1Icon);
                JDImageUtils.displayImage(product5.getImageUrl(), viewHolder.p2Icon);
                JDImageUtils.displayImage(product6.getImageUrl(), viewHolder.p3Icon);
                return;
            } else {
                return;
            }
        }
        viewHolder.pDetailLay.setVisibility(8);
    }

    public void setShopScore(ViewHolder viewHolder, JshopSearchItem jshopSearchItem) {
        if (viewHolder != null) {
            if (jshopSearchItem.score != null) {
                viewHolder.itemScore.setVisibility(8);
                Double shopScore = getShopScore(jshopSearchItem.score);
                viewHolder.itemScore.setText(SpannableStringUtils.getJshopScoreSpan(this.mActivity.getString(R.string.jshop_overall_rating) + shopScore, String.valueOf(shopScore)));
                if (shopScore.doubleValue() <= 0.0d) {
                    viewHolder.itemScore.setVisibility(8);
                    return;
                }
                return;
            }
            viewHolder.itemScore.setVisibility(8);
        }
    }

    public void setShopTag(ViewHolder viewHolder, JshopSearchItem jshopSearchItem) {
        if (jshopSearchItem.isPop) {
            if (!TextUtils.isEmpty(jshopSearchItem.shopTagIconId) && UnIconConfigHelper.getDrawable(jshopSearchItem.shopTagIconId) != null) {
                viewHolder.itemPopShop.setText("");
                viewHolder.itemPopShop.setBackgroundDrawable(UnIconConfigHelper.getDrawable(jshopSearchItem.shopTagIconId));
                viewHolder.itemPopShop.setVisibility(0);
            } else {
                viewHolder.itemPopShop.setVisibility(8);
            }
            setShopScore(viewHolder, jshopSearchItem);
        } else {
            viewHolder.itemPopShop.setBackgroundResource(R.drawable.asq);
            viewHolder.itemPopShop.setText(this.mActivity.getString(R.string.jshop_jd_self));
            if (UnIconConfigHelper.getDrawable("tab_021") != null) {
                viewHolder.itemPopShop.setText("");
                viewHolder.itemPopShop.setBackgroundDrawable(UnIconConfigHelper.getDrawable("tab_021"));
            }
            viewHolder.itemPopShop.setVisibility(0);
            viewHolder.itemScore.setVisibility(8);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.jShopSearchItemHeader.getLayoutParams();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) viewHolder.itemIconContainer.getLayoutParams();
        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) viewHolder.enterShopLay.getLayoutParams();
        if (viewHolder.itemPopShop.getVisibility() == 0) {
            layoutParams.height = DPIUtil.dip2px(71.0f);
            layoutParams2.topMargin = DPIUtil.dip2px(3.0f);
            layoutParams3.topMargin = (((DPIUtil.dip2px(71.0f) - DPIUtil.dip2px(17.0f)) - DPIUtil.dip2px(17.0f)) / 2) - DPIUtil.dip2px(1.0f);
        } else {
            layoutParams.height = DPIUtil.dip2px(61.0f);
            layoutParams2.topMargin = DPIUtil.dip2px(15.0f);
            layoutParams3.topMargin = (((DPIUtil.dip2px(61.0f) - DPIUtil.dip2px(17.0f)) - DPIUtil.dip2px(17.0f)) / 2) - DPIUtil.dip2px(1.0f);
        }
        if (jshopSearchItem.hasNewWare) {
            viewHolder.itemNew.setVisibility(8);
        } else {
            viewHolder.itemNew.setVisibility(8);
        }
        if (jshopSearchItem.hasCoupon) {
            viewHolder.itemQuan.setVisibility(0);
        } else {
            viewHolder.itemQuan.setVisibility(8);
        }
        if (viewHolder.itemQuan.getVisibility() != 0 && viewHolder.itemNew.getVisibility() != 0) {
            viewHolder.itemIconView.setVisibility(8);
        } else {
            viewHolder.itemIconView.setVisibility(0);
        }
    }
}
