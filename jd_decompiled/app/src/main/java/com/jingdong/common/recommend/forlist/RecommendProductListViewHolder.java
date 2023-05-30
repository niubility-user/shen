package com.jingdong.common.recommend.forlist;

import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.jump.OpenAppJumpBuilder;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.entity.RecommendItem;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.ui.AutoWarpTextView;
import com.jingdong.common.recommend.ui.RecommendDoublePriceView;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.widget.button.UnButton;
import com.jingdong.sdk.oklog.OKLog;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendProductListViewHolder extends RecommendProductBaseViewHolder {
    private BaseActivity activity;
    private UnButton buyNowBtn;
    View dot;
    SimpleDraweeView image;
    String imageUrl;
    private boolean isShowLabelList;
    private RecommendItem item;
    View leftBottomDot;
    private int mFrom;
    RelativeLayout parentLayout;
    private int priceContainerWidth;
    RecommendDoublePriceView priceView;
    private int recommendImgWidth;
    TextView recommendInfoTv;
    private TextView snapUpTV;

    public RecommendProductListViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.mFrom = -1;
        this.item = null;
        this.isShowLabelList = false;
        this.activity = baseActivity;
        this.parentLayout = (RelativeLayout) view.findViewById(R.id.rp_item);
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.recommend_product_item_imgview);
        this.image = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.recommendInfoTv = (TextView) view.findViewById(R.id.recommend_product_item_recommendinfo);
        this.name = (AutoWarpTextView) view.findViewById(R.id.recommend_product_item_name);
        TextView textView = (TextView) view.findViewById(R.id.recommend_pd_list_sale);
        this.snapUpTV = textView;
        RecommendFontUtils.changeFont(textView, 4098);
        RecomLayoutMaxLines recomLayoutMaxLines = (RecomLayoutMaxLines) view.findViewById(R.id.recommend_product_item_info);
        this.topRecommendInfoLayout = recomLayoutMaxLines;
        recomLayoutMaxLines.setMaxRows(1);
        this.priceView = (RecommendDoublePriceView) view.findViewById(R.id.recommend_price_view);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.leftBottomDot = view.findViewById(R.id.recommend_left_dot);
        this.recommendImgWidth = baseActivity.getResources().getDimensionPixelSize(R.dimen.recommend_product_list_item_img_width);
        this.buyNowBtn = (UnButton) view.findViewById(R.id.recommend_buy_now_btn);
    }

    public static /* synthetic */ void b(RecommendProduct recommendProduct, View view) {
        new OpenAppJumpBuilder.Builder(Uri.parse(recommendProduct.quickBuyJumpUrl)).build().jump(view.getContext());
        RecommendMtaUtils.recommendBuyNowClickMta(view.getContext(), recommendProduct, 9);
    }

    private void handleBuyNow(UnButton unButton, final RecommendProduct recommendProduct) {
        if (!TextUtils.isEmpty(recommendProduct.quickBuyJumpUrl)) {
            unButton.setVisibility(0);
        } else {
            unButton.setVisibility(8);
        }
        unButton.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RecommendProductListViewHolder.b(recommendProduct, view);
            }
        });
    }

    private void jumpProductDetail(final RecommendProduct recommendProduct, final int i2, final int i3) {
        this.parentLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductListViewHolder.1
            {
                RecommendProductListViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecommendUtils.isTooFastClick()) {
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(recommendProduct.sourceValue);
                    jSONObject.put("videoplay", "-100");
                    if (recommendProduct.rootUETJson != null) {
                        String productClickEventId = RecommendMtaUtils.getProductClickEventId(i3);
                        RecommendProduct recommendProduct2 = recommendProduct;
                        RecommendMtaUtils.handleTrackingNode(productClickEventId, recommendProduct2.jdjsonObject, jSONObject, recommendProduct2.rootUETJson, true, true);
                    }
                    recommendProduct.sourceValue = jSONObject.toString();
                    RecommendProductListViewHolder.this.clickMtaJson(recommendProduct);
                } catch (Exception e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
                if (RecommendProductListViewHolder.this.clickedListener != null) {
                    if (!TextUtils.isEmpty(recommendProduct.client_click_url)) {
                        RecommendProductListViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendProduct.client_click_url);
                    }
                    String str = null;
                    if (!TextUtils.isEmpty(recommendProduct.popUnit)) {
                        RecommendJumpUtils.gotoMWithUrl(RecommendProductListViewHolder.this.activity, null, recommendProduct.popUrl);
                    } else if (i2 == -1) {
                        int i4 = i3;
                        if (i4 == 6) {
                            str = RecommendMtaUtils.Shopcart_Compare_Productid;
                        } else if (i4 == 10) {
                            str = RecommendMtaUtils.OrderCenterMyPurchase_FloorProduct;
                        }
                        RecommendProductListViewHolder.this.clickedListener.onProductClick(recommendProduct, str);
                    } else if (i3 == 9) {
                        RecommendProductListViewHolder recommendProductListViewHolder = RecommendProductListViewHolder.this;
                        recommendProductListViewHolder.clickedListener.onProductClick(recommendProduct, recommendProductListViewHolder.item);
                    } else {
                        RecommendProductListViewHolder.this.clickedListener.onProductClick(recommendProduct);
                    }
                }
            }
        });
    }

    private void resetInit() {
        this.topRecommendInfoLayout.setVisibility(8);
        this.topRecommendInfoLayout.removeAllViews();
        this.snapUpTV.setVisibility(8);
        this.name.setText("");
        this.recommendInfoTv.setVisibility(8);
        this.priceView.resetView();
        View view = this.itemView;
        if (view instanceof CardView) {
            ((CardView) view).setCardBackgroundColor(getColor_FFFFFF());
        }
    }

    private void showRecommendReasonOrTag(RecommendProduct recommendProduct) {
        if (recommendProduct.hasRecommendReason()) {
            this.topRecommendInfoLayout.addView(getRecommendTextView(recommendProduct));
            this.topRecommendInfoLayout.setVisibility(0);
        } else {
            List<RecommendLabel> list = recommendProduct.labelList;
            if (list != null && !list.isEmpty()) {
                this.isShowLabelList = true;
                addNRecommendLabel(list, recommendProduct, this.priceContainerWidth);
            }
        }
        if (recommendProduct.isMtaValueChanged || this.isShowLabelList) {
            return;
        }
        RecommendUtils.handleLabelValueMtaJson("-100", recommendProduct);
        recommendProduct.isMtaValueChanged = true;
    }

    public void setProduct(RecommendProduct recommendProduct, int i2, ExpoDataStore expoDataStore, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        setFrom(i3);
        this.mFrom = i3;
        if (recommendProduct.canClipTitleImg) {
            this.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            this.image.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        }
        if (this.image.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl)) {
            String str2 = recommendProduct.imgUrl;
            this.imageUrl = str2;
            JDImageUtils.displayImage(str2, this.image, jDDisplayImageOptions);
        }
        resetInit();
        recommendProduct.productItemImage = this.image;
        int i4 = 8;
        if (!TextUtils.isEmpty(recommendProduct.presaleInfo)) {
            this.recommendInfoTv.setVisibility(0);
            this.recommendInfoTv.setText(recommendProduct.presaleInfo);
        } else {
            this.recommendInfoTv.setVisibility(8);
        }
        if (this.name.getWidth() != 0) {
            this.priceContainerWidth = this.name.getWidth();
        } else {
            int i5 = RecommendUtils.windowWidthSize;
            if (i5 > 0) {
                this.priceContainerWidth = (i5 - this.recommendImgWidth) - DPIUtil.dip2px(41.0f);
            } else {
                this.priceContainerWidth = (DPIUtil.getAppWidth(this.activity) - this.recommendImgWidth) - DPIUtil.dip2px(41.0f);
            }
        }
        int i6 = this.priceContainerWidth;
        this.recommendItemWidth = i6;
        this.nameViewWidth = i6;
        addNameIcons(recommendProduct);
        if (!TextUtils.isEmpty(recommendProduct.quickBuyJumpUrl)) {
            this.priceContainerWidth -= DPIUtil.dip2px(67.0f);
        }
        this.priceView.setAvailableWidth(this.priceContainerWidth);
        this.priceView.refreshView(recommendProduct);
        this.dot.setVisibility((!recommendProduct.isShowDot() || recommendProduct.isMonetized) ? 8 : 0);
        View view = this.leftBottomDot;
        if (recommendProduct.isShowDot() && recommendProduct.isMonetized) {
            i4 = 0;
        }
        view.setVisibility(i4);
        if (!TextUtils.isEmpty(recommendProduct.saleCount)) {
            this.snapUpTV.setVisibility(0);
            this.snapUpTV.setText(recommendProduct.saleCount);
        }
        handleBuyNow(this.buyNowBtn, recommendProduct);
        showRecommendReasonOrTag(recommendProduct);
        try {
            if (recommendProduct.rootUETJson != null) {
                JSONObject jSONObject = new JSONObject(recommendProduct.exposureJsonSourceValue);
                RecommendMtaUtils.handleTrackingNode(expoDataStore.getEventid(), recommendProduct.jdjsonObject, jSONObject, recommendProduct.rootUETJson, true, true);
                recommendProduct.exposureJsonSourceValue = jSONObject.toString();
            }
            try {
                JSONObject jSONObject2 = recommendProduct.appendMtaJson_real;
                String str3 = RecommendConstant.RECOM_TITLE_LINE;
                StringBuilder sb = new StringBuilder();
                sb.append(recommendProduct.isSingleRow() ? 1 : 2);
                sb.append("");
                jSONObject2.put(str3, sb.toString());
                recommendProduct.appendMtaJson_real.put(RecommendConstant.RECOM_FEEDS_TYPE, "1");
                dealExpoSourceValue(recommendProduct);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } catch (Exception e3) {
            if (OKLog.D) {
                e3.printStackTrace();
            }
        }
        jumpProductDetail(recommendProduct, i2, i3);
        realExpo(recommendProduct.client_exposal_url, recommendProduct.wareId);
        if (expoDataStore == null || recommendProduct.wareId == null) {
            return;
        }
        if (!TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
            expoDataStore.putExpoJsonDada(recommendProduct.exposureJsonSourceValue);
        } else if (TextUtils.isEmpty(recommendProduct.exposureSourceValue)) {
        } else {
            expoDataStore.putExpoData(recommendProduct.exposureSourceValue);
        }
    }

    public void setRecommendItem(RecommendItem recommendItem) {
        this.item = recommendItem;
    }

    public RecommendProductListViewHolder(BaseActivity baseActivity, View view, int i2) {
        this(baseActivity, view);
    }
}
