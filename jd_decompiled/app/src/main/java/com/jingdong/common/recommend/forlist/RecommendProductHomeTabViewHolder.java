package com.jingdong.common.recommend.forlist;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.app.util.image.JDDisplayImageOptions;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.ExpoDataStore;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendFeedBackManger;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendJumpUtils;
import com.jingdong.common.recommend.RecommendMtaUtils;
import com.jingdong.common.recommend.RecommendTextScaleUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.FeedBackReason;
import com.jingdong.common.recommend.entity.RecommendLabel;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.common.recommend.forlist.RecommendUtil;
import com.jingdong.common.unification.uniconfig.UnIconConfigHelper;
import com.jingdong.common.utils.CommonBase;
import com.jingdong.common.utils.JDImageUtils;
import com.jingdong.common.utils.text.ScaleModeConstants;
import com.jingdong.common.utils.text.TextScaleModeHelper;
import com.jingdong.jdsdk.utils.DPIUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class RecommendProductHomeTabViewHolder extends BaseRecommendBeltPriceViewHolder {
    private static final String TAG = "RecommendProductHomeTabViewHolder";
    private BaseActivity activity;
    private ImageView delete;
    private View dot;
    private String imageUrl;
    private TextView name;
    private RelativeLayout noRecommendLayout;
    private TextView noRecommendTv;
    private TextView presale;
    private TextView price;
    private TextView priceImage;
    private String priceUnit;
    private SimpleDraweeView productImage;
    private SimpleDraweeView recommendButton;
    private int recommendItemWidth;
    private LinearLayout recommendLabelContainer;
    private RelativeLayout rootLayout;
    private TextView secondPrice;
    private TextView stagesIcon;
    private boolean waterFallStrategy;

    public RecommendProductHomeTabViewHolder(BaseActivity baseActivity, View view) {
        super(view);
        this.imageUrl = null;
        this.activity = baseActivity;
        this.rootLayout = (RelativeLayout) view.findViewById(R.id.recommend_home_tab_product_root);
        int i2 = R.id.recommend_home_tab_product_img;
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(i2);
        this.productImage = simpleDraweeView;
        simpleDraweeView.setAspectRatio(1.0f);
        this.presale = (TextView) view.findViewById(R.id.recommend_home_tab_product_presale_info);
        this.name = (TextView) view.findViewById(R.id.recommend_home_tab_product_name);
        TextView textView = (TextView) view.findViewById(R.id.recommend_home_tab_product_price_1);
        this.price = textView;
        RecommendFontUtils.changeFont(textView, 4099);
        this.stagesIcon = (TextView) view.findViewById(R.id.recommend_home_tab_product_stages_icon);
        this.secondPrice = (TextView) view.findViewById(R.id.recommend_home_tab_product_price_2);
        this.productImage = (SimpleDraweeView) view.findViewById(i2);
        this.priceImage = (TextView) view.findViewById(R.id.recommend_home_tab_product_icon);
        this.dot = view.findViewById(R.id.recommend_dot);
        this.delete = (ImageView) view.findViewById(R.id.recommend_home_tab_product_delete);
        this.noRecommendLayout = (RelativeLayout) view.findViewById(R.id.recommend_home_product_no_layout);
        this.noRecommendTv = (TextView) view.findViewById(R.id.recommend_home_product_no_tv);
        this.recommendLabelContainer = (LinearLayout) view.findViewById(R.id.recommend_label_container);
        this.recommendButton = (SimpleDraweeView) view.findViewById(R.id.recommend_hom_tab_product_button);
        this.priceUnit = baseActivity.getResources().getString(R.string.yangjiao);
    }

    private void downPriceIcon(RecommendProduct recommendProduct) {
        Drawable drawable;
        if (TextUtils.isEmpty(recommendProduct.icon3) || (drawable = UnIconConfigHelper.getDrawable(recommendProduct.icon3)) == null) {
            return;
        }
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.secondPrice.setCompoundDrawables(null, null, drawable, null);
    }

    private void fixNoRecommendLayoutBug() {
        int height;
        ViewGroup.LayoutParams layoutParams;
        RelativeLayout relativeLayout = this.rootLayout;
        if (relativeLayout == null || this.noRecommendLayout == null || (height = relativeLayout.getHeight()) <= 0 || (layoutParams = this.noRecommendLayout.getLayoutParams()) == null || layoutParams.height == height) {
            return;
        }
        layoutParams.height = height;
        this.noRecommendLayout.requestLayout();
    }

    private float getSecondPriceAvailableWidth(RecommendProduct recommendProduct) {
        int widthByDesignValue750 = DPIUtil.getWidthByDesignValue750(28);
        int drawableWidth = RecommendViewUtil.getDrawableWidth(recommendProduct.icon3);
        if (drawableWidth >= widthByDesignValue750) {
            widthByDesignValue750 = drawableWidth;
        }
        return (this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(17)) - widthByDesignValue750;
    }

    private float getSecondPriceTextSize() {
        if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
            return RecommendFontUtils.convertFontSize(11);
        }
        return ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode()) ? 13.0f : 11.0f;
    }

    private float getTextWidth(TextView textView, float f2) {
        Paint paint = new Paint();
        paint.set(textView.getPaint());
        paint.setTextSize(DPIUtil.sp2px(this.activity, f2));
        return paint.measureText(textView.getText().toString());
    }

    private void initAnimation(RelativeLayout relativeLayout) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(relativeLayout, "scaleX", 0.0f, 1.0f);
        ofFloat.setDuration(200L);
        ofFloat.start();
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0022 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0024 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean isPrice(java.lang.String r7) {
        /*
            r6 = this;
            com.jingdong.common.BaseActivity r0 = r6.activity
            int r1 = com.jingdong.common.recommend.R.string.recommend_product_no_price
            java.lang.String r0 = r0.getString(r1)
            boolean r0 = android.text.TextUtils.equals(r0, r7)
            r1 = 0
            if (r0 == 0) goto L10
            return r1
        L10:
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r2 = 0
            if (r0 != 0) goto L1d
            double r4 = java.lang.Double.parseDouble(r7)     // Catch: java.lang.Exception -> L1d
            goto L1e
        L1d:
            r4 = r2
        L1e:
            int r7 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r7 <= 0) goto L24
            r7 = 1
            return r7
        L24:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.isPrice(java.lang.String):boolean");
    }

    private boolean isShowJdPrice(RecommendProduct recommendProduct) {
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            return isPrice(recommendProduct.jdPrice);
        }
        return false;
    }

    private void jumpProductDetail(final RecommendProduct recommendProduct, final int i2, int i3) {
        this.rootLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.5
            {
                RecommendProductHomeTabViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (RecommendUtils.isTooFastClick() || RecommendProductHomeTabViewHolder.this.clickedListener == null) {
                    return;
                }
                try {
                    if (recommendProduct.rootUETJson != null) {
                        JSONObject jSONObject = new JSONObject(recommendProduct.sourceValue);
                        String productClickEventId = RecommendMtaUtils.getProductClickEventId(RecommendProductHomeTabViewHolder.this.pageFrom);
                        RecommendProduct recommendProduct2 = recommendProduct;
                        RecommendMtaUtils.handleTrackingNode(productClickEventId, recommendProduct2.jdjsonObject, jSONObject, recommendProduct2.rootUETJson, true, true);
                        recommendProduct.sourceValue = jSONObject.toString();
                    }
                } catch (JSONException e2) {
                    if (OKLog.D) {
                        e2.printStackTrace();
                    }
                }
                if (!TextUtils.isEmpty(recommendProduct.popUnit)) {
                    RecommendJumpUtils.gotoMWithUrl(RecommendProductHomeTabViewHolder.this.activity, null, recommendProduct.popUrl);
                } else if (i2 != -1) {
                    RecommendProductHomeTabViewHolder.this.clickedListener.onProductClick(recommendProduct);
                }
                if (TextUtils.isEmpty(recommendProduct.client_click_url)) {
                    return;
                }
                RecommendProductHomeTabViewHolder.this.clickedListener.onRecommendMoneyExpo(recommendProduct.client_click_url);
            }
        });
    }

    private void resetInit() {
        this.price.setTextSize(12.0f);
        this.secondPrice.setTextSize(11.0f);
        RecommendFontUtils.changeFont(this.secondPrice, 4099);
        this.secondPrice.setVisibility(8);
        this.priceImage.setVisibility(8);
        this.stagesIcon.setVisibility(8);
        this.recommendLabelContainer.setVisibility(8);
        this.recommendButton.setVisibility(8);
        this.delete.setVisibility(8);
        resetBeltView();
        this.secondPrice.setTextColor(Color.parseColor("#BFBFBF"));
        this.name.setTextColor(getColor_262626());
        View view = this.itemView;
        if (view instanceof CardView) {
            ((CardView) view).setCardBackgroundColor(getColor_FFFFFF());
        }
    }

    private void setJdPrice(RecommendProduct recommendProduct) {
        int scaleTextSize = (int) RecommendTextScaleUtils.getScaleTextSize(12);
        this.price.setTextSize(scaleTextSize);
        if (TextUtils.isEmpty(recommendProduct.presaleText)) {
            String jdPrice = recommendProduct.getJdPrice();
            if (!TextUtils.equals(this.activity.getString(R.string.recommend_product_no_price), jdPrice)) {
                String str = this.priceUnit + jdPrice;
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
                int indexOf = str.indexOf(OrderISVUtil.MONEY_DECIMAL);
                if (indexOf != -1) {
                    if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(12), true), 0, 1, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(18), true), 1, indexOf, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(RecommendFontUtils.convertFontSize(12), true), indexOf, spannableStringBuilder.length(), 33);
                    } else {
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(scaleTextSize + 5, true), 1, indexOf, 33);
                    }
                }
                this.price.setText(spannableStringBuilder);
                if (TextUtils.isEmpty(recommendProduct.buttonAreaImgUrl) || str.length() <= 8) {
                    return;
                }
                int dip2px = (this.recommendItemWidth - DPIUtil.dip2px(70.0f)) - DPIUtil.dip2px(10.0f);
                float viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.price);
                float f2 = dip2px;
                if (viewMeasureWidth > f2) {
                    float textSizeScale = RecommendViewUtil.getTextSizeScale(f2, viewMeasureWidth);
                    int convertFontSize = (int) ((RecommendFontUtils.enableFontRule(this.recommendUIMode) ? RecommendFontUtils.convertFontSize(18) : scaleTextSize + 5) * textSizeScale);
                    if (indexOf != -1) {
                        int i2 = (int) (textSizeScale * 13.0f);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i2, true), 0, 1, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(convertFontSize, true), 1, indexOf, 33);
                        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i2, true), indexOf, spannableStringBuilder.length(), 33);
                        this.price.setText(spannableStringBuilder);
                        return;
                    }
                    return;
                }
                return;
            }
            this.price.setText(jdPrice);
            return;
        }
        SpannableString spannableString = new SpannableString(this.priceUnit + recommendProduct.presaleText);
        spannableString.setSpan(new AbsoluteSizeSpan((int) RecommendTextScaleUtils.getScaleTextSize(14), true), 1, spannableString.length(), 33);
        this.price.setText(spannableString);
        if (TextUtils.isEmpty(recommendProduct.presaleTextColor)) {
            return;
        }
        this.price.setTextColor(Color.parseColor("#" + recommendProduct.presaleTextColor));
    }

    private void setNameInfo(RecommendProduct recommendProduct) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(recommendProduct.icon1);
        arrayList.add(recommendProduct.icon2);
        SpannableString spannableString = getSpannableString(arrayList, recommendProduct.getName(), this.name);
        this.name.setMinLines(this.waterFallStrategy ? 1 : 2);
        this.name.setText(spannableString);
        if (RecommendFontUtils.enableFontRule(this.recommendUIMode)) {
            this.name.setTextSize(RecommendFontUtils.convertFontSize(13));
        } else if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(TextScaleModeHelper.INSTANCE.getInstance().getTextSizeScaleMode())) {
            this.name.setTextSize(16.0f);
        } else {
            this.name.setTextSize(13.0f);
        }
    }

    private void setNegFeedback(final RecommendProduct recommendProduct, final int i2) {
        if (recommendProduct.isCanNegFeedback() && recommendProduct.isShowFeedbackUi) {
            if (!RecommendUtil.dotClick) {
                if (CommonBase.getJdSharedPreferences().getInt("RecommendHomeTab_productHash", 0) == recommendProduct.hashCode()) {
                    this.noRecommendLayout.setVisibility(8);
                    recommendProduct.isShowFeedbackUi = false;
                    CommonBase.getJdSharedPreferences().edit().putInt("RecommendHomeTab_productHash", 0).apply();
                }
            } else {
                this.noRecommendLayout.setVisibility(0);
                fixNoRecommendLayoutBug();
                initAnimation(this.noRecommendLayout);
                CommonBase.getJdSharedPreferences().edit().putInt("RecommendHomeTab_productHash", recommendProduct.hashCode()).apply();
                RecommendUtil.dotClick = false;
            }
        } else {
            this.noRecommendLayout.setVisibility(8);
        }
        if (recommendProduct.isCanNegFeedback()) {
            this.delete.setVisibility(0);
            this.delete.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.1
                {
                    RecommendProductHomeTabViewHolder.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty()) {
                        RecommendProductHomeTabViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger.getInstance().showTipPopupWindow(RecommendProductHomeTabViewHolder.this.activity, RecommendProductHomeTabViewHolder.this.delete, recommendProduct, i2, RecommendProductHomeTabViewHolder.this.clickedListener, 1);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductHomeTabViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        RecommendProduct recommendProduct2 = recommendProduct;
                        onRecommendClickedListener.onDotMoreMta(1, recommendProduct2 != null ? recommendProduct2.feedbackSourceValue : "");
                    }
                }
            });
        }
        if (recommendProduct.isCanNegFeedback()) {
            this.rootLayout.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.2
                {
                    RecommendProductHomeTabViewHolder.this = this;
                }

                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    if (RecommendUtil.dotClick) {
                        return true;
                    }
                    List<FeedBackReason> list = recommendProduct.feedBackReason;
                    if (list == null || list.isEmpty() || RecommendUtil.dotClick) {
                        RecommendProductHomeTabViewHolder.this.showFeedBackView(recommendProduct, i2);
                    } else {
                        RecommendUtil.dotClick = true;
                        RecommendFeedBackManger.getInstance().showTipPopupWindow(RecommendProductHomeTabViewHolder.this.activity, RecommendProductHomeTabViewHolder.this.delete, recommendProduct, i2, RecommendProductHomeTabViewHolder.this.clickedListener, 2);
                    }
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = RecommendProductHomeTabViewHolder.this.clickedListener;
                    if (onRecommendClickedListener != null) {
                        RecommendProduct recommendProduct2 = recommendProduct;
                        onRecommendClickedListener.onDotMoreMta(2, recommendProduct2 != null ? recommendProduct2.feedbackSourceValue : "");
                    }
                    return true;
                }
            });
        } else {
            this.rootLayout.setOnLongClickListener(null);
        }
        this.noRecommendLayout.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.3
            {
                RecommendProductHomeTabViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RecommendProductHomeTabViewHolder.this.noRecommendLayout.setVisibility(8);
                recommendProduct.isShowFeedbackUi = false;
            }
        });
        this.noRecommendTv.setOnClickListener(new View.OnClickListener() { // from class: com.jingdong.common.recommend.forlist.RecommendProductHomeTabViewHolder.4
            {
                RecommendProductHomeTabViewHolder.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                int i3;
                RecommendProductHomeTabViewHolder recommendProductHomeTabViewHolder = RecommendProductHomeTabViewHolder.this;
                RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = recommendProductHomeTabViewHolder.clickedListener;
                if (onRecommendClickedListener == null || (i3 = i2) < 0) {
                    recommendProductHomeTabViewHolder.noRecommendLayout.setVisibility(8);
                    recommendProduct.isShowFeedbackUi = false;
                    RecommendUtil.OnRecommendClickedListener onRecommendClickedListener2 = RecommendProductHomeTabViewHolder.this.clickedListener;
                    if (onRecommendClickedListener2 != null) {
                        onRecommendClickedListener2.onRefresh();
                        return;
                    }
                    return;
                }
                onRecommendClickedListener.onNoRecommendClick(recommendProduct, i3, "", null);
            }
        });
    }

    private void setPresaleInfo(RecommendProduct recommendProduct) {
        if (!TextUtils.isEmpty(recommendProduct.presaleInfo)) {
            this.presale.setVisibility(0);
            this.presale.setText(recommendProduct.presaleInfo);
            return;
        }
        this.presale.setVisibility(8);
    }

    private void setSecondPrice(float f2, float f3, String str) {
        if (f3 > f2) {
            this.secondPrice.setTextSize(getSecondPriceTextSize() * RecommendViewUtil.getTextSizeScale(f2, f3));
            this.secondPrice.setText(str);
        }
    }

    private void setStagesKindsInfo(RecommendProduct recommendProduct, SpannableStringBuilder spannableStringBuilder) {
        int i2;
        Drawable drawable;
        try {
            i2 = Integer.parseInt(recommendProduct.stagesKinds);
        } catch (NumberFormatException unused) {
            i2 = 1;
        }
        if (i2 <= 0) {
            this.price.setText(spannableStringBuilder);
        } else {
            SpannableString spannableString = new SpannableString(this.activity.getString(R.string.recommend_stagesKinds));
            TextScaleModeHelper.Companion companion = TextScaleModeHelper.INSTANCE;
            if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(companion.getInstance().getTextSizeScaleMode())) {
                spannableString.setSpan(new AbsoluteSizeSpan(14, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(13, true), 1, spannableString.length(), 33);
            } else {
                spannableString.setSpan(new AbsoluteSizeSpan(12, true), 0, 1, 33);
                spannableString.setSpan(new AbsoluteSizeSpan(11, true), 1, spannableString.length(), 33);
            }
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#F23030")), 0, spannableString.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
            if (i2 > 1) {
                SpannableString spannableString2 = new SpannableString(this.activity.getString(R.string.recommend_stagesKinds_up));
                if (ScaleModeConstants.TEXT_SCALE_MODE_LARGE.equals(companion.getInstance().getTextSizeScaleMode())) {
                    spannableString2.setSpan(new AbsoluteSizeSpan(13, true), 0, spannableString.length(), 33);
                } else {
                    spannableString2.setSpan(new AbsoluteSizeSpan(11, true), 0, spannableString.length(), 33);
                }
                spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#686868")), 0, spannableString.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString2);
            }
            this.price.setText(spannableStringBuilder);
        }
        if (TextUtils.isEmpty(recommendProduct.stageDescription) || TextUtils.isEmpty(recommendProduct.iconC) || (drawable = UnIconConfigHelper.getDrawable(recommendProduct.iconC)) == null) {
            return;
        }
        this.stagesIcon.setBackgroundDrawable(drawable);
        this.stagesIcon.setText(recommendProduct.stageDescription);
        this.stagesIcon.setVisibility(0);
    }

    public void showFeedBackView(RecommendProduct recommendProduct, int i2) {
        RecommendUtil.OnRecommendClickedListener onRecommendClickedListener = this.clickedListener;
        if (onRecommendClickedListener != null) {
            RecommendUtil.dotClick = true;
            onRecommendClickedListener.onShowFeedbackUi(recommendProduct, i2);
        }
    }

    private void showRecommendLabel(RecommendProduct recommendProduct) {
        float f2;
        this.recommendLabelContainer.removeAllViews();
        try {
            int dip2px = this.recommendItemWidth - DPIUtil.dip2px(24.0f);
            StringBuilder sb = new StringBuilder();
            int i2 = 0;
            while (true) {
                boolean z = true;
                if (i2 < recommendProduct.labelList.size()) {
                    RecommendLabel recommendLabel = recommendProduct.labelList.get(i2);
                    if (recommendLabel != null && !TextUtils.isEmpty(recommendLabel.resourceCode) && !TextUtils.isEmpty(recommendLabel.key)) {
                        TextView textView = getTextView(recommendLabel.resourceCode, recommendLabel.labelTitle);
                        RecommendFontUtils.changeFont(textView, 4099);
                        textView.setLines(1);
                        float f3 = 0.0f;
                        if (TextUtils.isEmpty(recommendLabel.labelTitle)) {
                            Bitmap bitmap = UnIconConfigHelper.getBitmap(recommendLabel.resourceCode, RecommendFontUtils.enableFontRule(this.recommendUIMode));
                            if (bitmap != null) {
                                f3 = bitmap.getWidth();
                            }
                        } else {
                            f3 = getTextWidth(textView, RecommendFontUtils.convertRecommendLabelFont(9.0f, this.recommendUIMode)) + textView.getCompoundPaddingLeft() + textView.getCompoundPaddingRight();
                        }
                        if (i2 != 0) {
                            f2 = dip2px;
                            f3 += com.jingdong.sdk.utils.DPIUtil.dip2px(8.0f);
                        } else {
                            f2 = dip2px;
                        }
                        dip2px = (int) (f2 - f3);
                        if (dip2px > 0) {
                            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                            if (i2 != 0) {
                                layoutParams.leftMargin = com.jingdong.sdk.utils.DPIUtil.dip2px(8.0f);
                            }
                            textView.setLayoutParams(layoutParams);
                            this.recommendLabelContainer.addView(textView);
                            this.recommendLabelContainer.setVisibility(0);
                            RecommendUtils.joinLabelValue(sb, recommendProduct, recommendLabel);
                        }
                        if (dip2px <= 0) {
                            z = false;
                        }
                        RecommendUtils.handleLableMtaJson(recommendProduct, recommendLabel, z);
                    }
                    i2++;
                } else {
                    RecommendUtils.handleLabelValueMtaJson(sb.toString(), recommendProduct);
                    recommendProduct.isMtaValueChanged = true;
                    return;
                }
            }
        } catch (Exception e2) {
            if (OKLog.D) {
                e2.printStackTrace();
            }
        }
    }

    private void showSecondPrice(RecommendProduct recommendProduct) {
        int viewMeasureWidth;
        this.secondPrice.setTextSize(getSecondPriceTextSize());
        if (isPrice(recommendProduct.getSecondPrice())) {
            String str = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getSecondPrice();
            this.secondPrice.getPaint().setStrikeThruText(false);
            this.secondPrice.setText(str);
            this.secondPrice.setVisibility(0);
            if (!TextUtils.isEmpty(recommendProduct.priceColor)) {
                this.secondPrice.setTextColor(Color.parseColor("#" + recommendProduct.priceColor));
            }
            downPriceIcon(recommendProduct);
            if (this.secondPrice.length() > 7) {
                setSecondPrice(getSecondPriceAvailableWidth(recommendProduct), RecommendViewUtil.getViewMeasureWidth(this.secondPrice), str);
            }
        } else if (isPrice(recommendProduct.getUnderLinePrice())) {
            String str2 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getUnderLinePrice();
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.setText(str2);
            this.secondPrice.getPaint().setStrikeThruText(true);
            this.secondPrice.setTextColor(Color.parseColor("#848689"));
            RecommendFontUtils.changeFont(this.secondPrice, 4098);
            this.secondPrice.setVisibility(0);
            if (this.secondPrice.length() > 7) {
                setSecondPrice(this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(20), RecommendViewUtil.getViewMeasureWidth(this.secondPrice), str2);
            }
        } else if (isPrice(recommendProduct.getPrice(recommendProduct.gbPrice))) {
            String str3 = this.activity.getResources().getString(R.string.yangjiao) + recommendProduct.getPrice(recommendProduct.gbPrice);
            this.secondPrice.setCompoundDrawables(null, null, null, null);
            this.secondPrice.getPaint().setStrikeThruText(false);
            this.secondPrice.setText(str3);
            this.secondPrice.setVisibility(0);
            if (!TextUtils.isEmpty(recommendProduct.priceColor)) {
                this.secondPrice.setTextColor(Color.parseColor("#" + recommendProduct.priceColor));
            }
            if (!TextUtils.isEmpty(recommendProduct.gbMemCount)) {
                this.priceImage.setText(recommendProduct.gbMemCount);
                UnIconConfigHelper.setTextViewProperties(recommendProduct.icon3, this.priceImage);
                this.priceImage.setVisibility(0);
            }
            if (this.secondPrice.length() > 7) {
                if (!TextUtils.isEmpty(recommendProduct.gbMemCount)) {
                    viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.secondPrice) + RecommendViewUtil.getViewMeasureWidth(this.priceImage);
                } else {
                    viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.secondPrice);
                }
                float f2 = viewMeasureWidth;
                float widthByDesignValue750 = this.recommendItemWidth - DPIUtil.getWidthByDesignValue750(20);
                if (f2 > widthByDesignValue750) {
                    this.priceImage.setVisibility(8);
                    setSecondPrice(widthByDesignValue750, f2, str3);
                }
            }
        } else {
            this.secondPrice.setVisibility(8);
        }
        if (this.secondPrice.getVisibility() == 0 && isDeepDark()) {
            this.secondPrice.setTextColor(RecommendUtils.normalColor_ECECEC);
        }
    }

    public void setProduct(RecommendProduct recommendProduct, int i2, ExpoDataStore expoDataStore, int i3, JDDisplayImageOptions jDDisplayImageOptions) {
        String str;
        setFrom(i3);
        getRecommendUIMode();
        if (recommendProduct != null) {
            this.productImage.setVisibility(0);
            if (this.productImage.getDrawable() == null || (str = this.imageUrl) == null || !str.equals(recommendProduct.imgUrl)) {
                String str2 = recommendProduct.imgUrl;
                this.imageUrl = str2;
                JDImageUtils.displayImage(str2, this.productImage, jDDisplayImageOptions);
            }
            if (this.productImage.getMeasuredWidth() != 0) {
                this.recommendItemWidth = this.productImage.getMeasuredWidth();
            } else {
                this.recommendItemWidth = RecommendViewUtil.getLineTwoItemWidth(this.itemView.getContext());
            }
            resetInit();
            recommendProduct.productItemImage = this.productImage;
            setNameInfo(recommendProduct);
            setJdPrice(recommendProduct);
            if (isShowBelt(recommendProduct)) {
                showBeltView(recommendProduct, jDDisplayImageOptions, true);
            } else {
                setPresaleInfo(recommendProduct);
            }
            if (TextUtils.isEmpty(recommendProduct.popUnit) && TextUtils.isEmpty(recommendProduct.stagesKinds) && isShowJdPrice(recommendProduct) && (isPrice(recommendProduct.getSecondPrice()) || isPrice(recommendProduct.getUnderLinePrice()) || isPrice(recommendProduct.getPrice(recommendProduct.gbPrice)))) {
                showSecondPrice(recommendProduct);
            }
            List<RecommendLabel> list = recommendProduct.labelList;
            if (list != null && !list.isEmpty()) {
                showRecommendLabel(recommendProduct);
            }
            if (!TextUtils.isEmpty(recommendProduct.buttonAreaImgUrl)) {
                this.recommendButton.setVisibility(0);
                JDImageUtils.displayImage(recommendProduct.buttonAreaImgUrl, this.recommendButton);
            }
            if (recommendProduct.isShowDot()) {
                this.dot.setVisibility(0);
            } else {
                this.dot.setVisibility(8);
            }
            jumpProductDetail(recommendProduct, i2, i3);
            setNegFeedback(recommendProduct, i2);
            try {
                if (recommendProduct.rootUETJson != null) {
                    JSONObject jSONObject = new JSONObject(recommendProduct.exposureJsonSourceValue);
                    RecommendMtaUtils.handleTrackingNode(expoDataStore.getEventid(), recommendProduct.jdjsonObject, jSONObject, recommendProduct.rootUETJson, true, true);
                    recommendProduct.exposureJsonSourceValue = jSONObject.toString();
                }
            } catch (Exception e2) {
                if (OKLog.D) {
                    e2.printStackTrace();
                }
            }
            realExpo(recommendProduct.client_exposal_url, recommendProduct.wareId);
            if ("-1".equals(recommendProduct.wareId) || expoDataStore == null || recommendProduct.wareId == null) {
                return;
            }
            if (!TextUtils.isEmpty(recommendProduct.exposureJsonSourceValue)) {
                expoDataStore.putExpoJsonDada(recommendProduct.exposureJsonSourceValue);
            } else if (TextUtils.isEmpty(recommendProduct.exposureSourceValue)) {
            } else {
                expoDataStore.putExpoData(recommendProduct.exposureSourceValue);
            }
        }
    }

    public void setWaterFallStrategy(boolean z) {
        this.waterFallStrategy = z;
    }
}
