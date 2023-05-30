package com.jingdong.common.recommend.forlist;

import android.graphics.Bitmap;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.app.util.image.assist.JDFailReason;
import com.jingdong.app.util.image.listener.JDImageLoadingListener;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes6.dex */
public class BaseRecommendBeltPriceViewHolder extends RecommendProductBaseViewHolder {
    private String beltBenefitLocal;
    private String beltBgLocal;
    private SpannableStringBuilder beltDescriptionBuilder;
    private SpannableStringBuilder beltPriceBuilder;
    protected String priceUnit;
    private SimpleDraweeView recomBeltBenefitBg;
    private TextView recomBeltBenefitContent;
    private View recomBeltBenefitDivider;
    private TextView recomBeltBenefitTime;
    private View recomBeltBenefitView;
    private SimpleDraweeView recomBeltBg;
    private LinearLayout recomBeltButton;
    private AppCompatTextView recomBeltDesTV;
    private TextView recomBeltPriceTV;
    private TextView recomBeltTitleTV;
    private View recomBeltView;

    public BaseRecommendBeltPriceViewHolder(View view) {
        super(view);
        this.priceUnit = view.getResources().getString(R.string.yangjiao);
    }

    private void showBeltDes(RecommendProduct recommendProduct) {
        String str = recommendProduct.purchasePriceText;
        if (!TextUtils.isEmpty(recommendProduct.purchasePrice)) {
            str = str + this.priceUnit + recommendProduct.purchasePrice;
        }
        if (this.beltDescriptionBuilder == null) {
            this.beltDescriptionBuilder = new SpannableStringBuilder();
        }
        this.beltDescriptionBuilder.clear();
        this.beltDescriptionBuilder.append((CharSequence) str);
        try {
            int indexOf = str.indexOf(this.priceUnit);
            int i2 = indexOf + 1;
            this.beltDescriptionBuilder.setSpan(new RelativeSizeSpan(0.8f), indexOf, i2, 33);
            if (indexOf > -1) {
                int indexOf2 = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf2 == -1) {
                    indexOf2 = str.length();
                }
                this.beltDescriptionBuilder.setSpan(new RelativeSizeSpan(1.3f), i2, indexOf2, 33);
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        this.recomBeltDesTV.setText(this.beltDescriptionBuilder);
    }

    private void showBeltTitle(RecommendProduct recommendProduct) {
        if (recommendProduct.totalBenefitColorLocal == null) {
            RecommendViewUtil.setViewBackground(this.recomBeltDesTV, 0);
        } else {
            RecommendViewUtil.generateGradientDrawable(this.recomBeltTitleTV, DPIUtil.dip2px(5.0f), recommendProduct.totalBenefitColorLocal);
        }
        RecommendViewUtil.setText(this.recomBeltTitleTV, recommendProduct.totalBenefitText);
        RecommendViewUtil.setTextColor(this.recomBeltTitleTV, RecommendViewUtil.generateColor(recommendProduct.totalBenefitTextColor, JDDarkUtil.COLOR_FA2C19));
        String str = this.priceUnit + recommendProduct.totalBenefit;
        if (this.beltPriceBuilder == null) {
            this.beltPriceBuilder = new SpannableStringBuilder();
        }
        this.beltPriceBuilder.clear();
        this.beltPriceBuilder.append((CharSequence) str);
        try {
            int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
            if (indexOf == -1) {
                indexOf = this.beltPriceBuilder.length();
            }
            this.beltPriceBuilder.setSpan(new RelativeSizeSpan(1.4f), 1, indexOf, 33);
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
        RecommendViewUtil.setText(this.recomBeltPriceTV, this.beltPriceBuilder);
        RecommendViewUtil.setTextColor(this.recomBeltPriceTV, RecommendViewUtil.generateColor(recommendProduct.totalBenefitColor, JDDarkUtil.COLOR_FA2C19));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isNotEmpty(RecommendProduct recommendProduct) {
        return (TextUtils.isEmpty(recommendProduct.purchasePrice) || TextUtils.isEmpty(recommendProduct.purchasePriceText) || TextUtils.isEmpty(recommendProduct.totalBenefitText) || TextUtils.isEmpty(recommendProduct.totalBenefit)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isShowBelt(RecommendProduct recommendProduct) {
        return "1".equals(recommendProduct.isArrivalPriceBelt) && isNotEmpty(recommendProduct);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isShowNewBeltBenefit(RecommendProduct recommendProduct) {
        return (TextUtils.isEmpty(recommendProduct.beltBgImg) || TextUtils.isEmpty(recommendProduct.beltContent) || TextUtils.isEmpty(recommendProduct.beltTimeText)) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetBeltBenefitView() {
        RecommendViewUtil.gone(this.recomBeltBenefitView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void resetBeltView() {
        RecommendViewUtil.gone(this.recomBeltView);
    }

    public void setRecommendItemWidth(int i2) {
        this.recommendItemWidth = i2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showBeltBenefitView(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions) {
        if (this.recomBeltBenefitView == null) {
            View inflate = ((ViewStub) this.itemView.findViewById(R.id.recommend_product_belt_benefit)).inflate();
            this.recomBeltBenefitView = inflate;
            this.recomBeltBenefitBg = (SimpleDraweeView) RecommendViewUtil.findViewById(inflate, R.id.recommend_belt_benefit_bg);
            this.recomBeltBenefitContent = (TextView) RecommendViewUtil.findViewById(this.recomBeltBenefitView, R.id.recommend_belt_benefit_content);
            this.recomBeltBenefitDivider = RecommendViewUtil.findViewById(this.recomBeltBenefitView, R.id.recommend_belt_benefit_divider);
            this.recomBeltBenefitTime = (TextView) RecommendViewUtil.findViewById(this.recomBeltBenefitView, R.id.recommend_belt_benefit_time);
            FontsUtil.changeTextFont(this.recomBeltBenefitContent, 4099);
            FontsUtil.changeTextFont(this.recomBeltBenefitTime, 4099);
        }
        RecommendViewUtil.visible(this.recomBeltBenefitView);
        RecommendViewUtil.visible(this.recomBeltBenefitDivider);
        RecommendViewUtil.visible(this.recomBeltBenefitTime);
        if (!TextUtils.isEmpty(recommendProduct.beltBgImg)) {
            SimpleDraweeView simpleDraweeView = this.recomBeltBenefitBg;
            if (simpleDraweeView != null && (simpleDraweeView.getTopLevelDrawable() == null || !recommendProduct.beltBgImg.equals(this.beltBenefitLocal))) {
                String str = recommendProduct.beltBgImg;
                this.beltBenefitLocal = str;
                JDImageUtils.displayImage(str, this.recomBeltBenefitBg, jDDisplayImageOptions, new JDImageLoadingListener() { // from class: com.jingdong.common.recommend.forlist.BaseRecommendBeltPriceViewHolder.1
                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingCancelled(String str2, View view) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingComplete(String str2, View view, Bitmap bitmap) {
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingFailed(String str2, View view, JDFailReason jDFailReason) {
                        JDImageUtils.displayImage("res:///" + R.drawable.recommend_belt_benefit_bg, BaseRecommendBeltPriceViewHolder.this.recomBeltBenefitBg);
                    }

                    @Override // com.jingdong.app.util.image.listener.JDImageLoadingListener
                    public void onLoadingStarted(String str2, View view) {
                    }
                });
            }
        } else {
            this.beltBenefitLocal = "";
            JDImageUtils.displayImage("res:///" + R.drawable.recommend_belt_benefit_bg, this.recomBeltBenefitBg);
        }
        RecommendViewUtil.setText(this.recomBeltBenefitContent, recommendProduct.beltContent);
        RecommendViewUtil.setText(this.recomBeltBenefitTime, recommendProduct.beltTimeText);
        if (RecommendViewUtil.getViewMeasureWidth(this.recomBeltBenefitContent) + RecommendViewUtil.getViewMeasureWidth(this.recomBeltBenefitTime) + com.jingdong.sdk.utils.DPIUtil.dip2px(29.0f) > this.recommendItemWidth) {
            RecommendViewUtil.gone(this.recomBeltBenefitDivider);
            RecommendViewUtil.gone(this.recomBeltBenefitTime);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void showBeltView(RecommendProduct recommendProduct, JDDisplayImageOptions jDDisplayImageOptions, boolean z) {
        if (this.recomBeltView == null) {
            View inflate = ((ViewStub) this.itemView.findViewById(R.id.recom_product_belt)).inflate();
            this.recomBeltView = inflate;
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) RecommendViewUtil.findViewById(inflate, R.id.recom_belt_bg);
            this.recomBeltBg = simpleDraweeView;
            RecommendViewUtil.setAspectRatio(simpleDraweeView, 5.25f);
            this.recomBeltButton = (LinearLayout) RecommendViewUtil.findViewById(this.recomBeltView, R.id.recommend_belt_button_right);
            AppCompatTextView appCompatTextView = (AppCompatTextView) RecommendViewUtil.findViewById(this.recomBeltView, R.id.recom_belt_des);
            this.recomBeltDesTV = appCompatTextView;
            FontsUtil.changeTextFont(appCompatTextView, 4097);
            this.recomBeltTitleTV = (TextView) RecommendViewUtil.findViewById(this.recomBeltView, R.id.recom_belt_title);
            TextView textView = (TextView) RecommendViewUtil.findViewById(this.recomBeltView, R.id.recom_belt_price);
            this.recomBeltPriceTV = textView;
            FontsUtil.changeTextFont(textView, 4099);
        }
        if (z) {
            RecommendViewUtil.visible(this.recomBeltButton);
        } else {
            RecommendViewUtil.gone(this.recomBeltButton);
        }
        RecommendViewUtil.visible(this.recomBeltView);
        if (!TextUtils.isEmpty(recommendProduct.arrivalPriceBeltImg)) {
            SimpleDraweeView simpleDraweeView2 = this.recomBeltBg;
            if (simpleDraweeView2 != null && (simpleDraweeView2.getTopLevelDrawable() == null || !recommendProduct.arrivalPriceBeltImg.equals(this.beltBgLocal))) {
                String str = recommendProduct.arrivalPriceBeltImg;
                this.beltBgLocal = str;
                JDImageUtils.displayImage(str, this.recomBeltBg, jDDisplayImageOptions, false, null, null);
            }
        } else {
            this.beltBgLocal = "";
            RecommendViewUtil.setImageResource(this.recomBeltBg, R.drawable.recommend_big_belt);
        }
        showBeltTitle(recommendProduct);
        showBeltDes(recommendProduct);
    }
}
