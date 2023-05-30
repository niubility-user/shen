package com.jingdong.common.recommend.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jingdong.app.mall.bundle.order_center_isv_core.util.OrderISVUtil;
import com.jingdong.common.DpiUtil;
import com.jingdong.common.recommend.R;
import com.jingdong.common.recommend.RecommendConstant;
import com.jingdong.common.recommend.RecommendFontUtils;
import com.jingdong.common.recommend.RecommendUtils;
import com.jingdong.common.recommend.RecommendViewUtil;
import com.jingdong.common.recommend.entity.RecommendProduct;
import com.jingdong.jdsdk.utils.DPIUtil;

/* loaded from: classes6.dex */
public class RecommendDoublePriceView extends RelativeLayout {
    private int canUseWidth;
    private TextView firstIcon;
    private TextView firstPriceView;
    private String jdPriceColor;
    protected String priceUnit;
    public String realSecondPrice;
    public String realSecondPriceType;
    private TextView rightPriceView;
    private int viewWidth;

    public RecommendDoublePriceView(Context context) {
        super(context);
        this.realSecondPriceType = "-100";
        this.realSecondPrice = "-100";
        initView();
    }

    private float getPriceTextSizeScale() {
        float f2 = this.canUseWidth;
        float viewMeasureWidth = RecommendViewUtil.getViewMeasureWidth(this.firstPriceView);
        if (RecommendViewUtil.isVisible(this.rightPriceView)) {
            int viewMeasureWidth2 = RecommendViewUtil.getViewMeasureWidth(this.rightPriceView) + DPIUtil.dip2px(8.0f);
            this.rightPriceView.setVisibility(8);
            f2 += viewMeasureWidth2;
            if (viewMeasureWidth < f2) {
                return 1.0f;
            }
        }
        return RecommendViewUtil.getTextSizeScale(f2, viewMeasureWidth);
    }

    private void initView() {
        this.priceUnit = getResources().getString(R.string.yangjiao);
        LayoutInflater.from(getContext()).inflate(R.layout.recommend_second_price, this);
        TextView textView = (TextView) findViewById(R.id.recommend_product_item_price);
        this.firstPriceView = textView;
        RecommendFontUtils.changeFont(textView, 4099);
        this.firstIcon = (TextView) findViewById(R.id.recommend_tv_iconC);
        TextView textView2 = (TextView) findViewById(R.id.recommend_right_second_price);
        this.rightPriceView = textView2;
        RecommendFontUtils.changeFont(textView2, 4098);
        this.jdPriceColor = JDDarkUtil.COLOR_FA2C19;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    }

    private void setPriceView(RecommendProduct recommendProduct) {
        String str;
        RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
        String str2 = "";
        if (doublePriceUp != null) {
            if ("1".equals(doublePriceUp.notSfStyle)) {
                str = recommendProduct.doublePriceUpMap.secondPrice;
            } else {
                RecommendProduct.DoublePriceUp doublePriceUp2 = recommendProduct.doublePriceUpMap;
                String str3 = doublePriceUp2.doublePrice;
                str2 = doublePriceUp2.doublePriceColor;
                str = str3;
            }
        } else {
            str = recommendProduct.jdPrice;
        }
        String showPrice = RecommendUtils.getShowPrice(str);
        if (!RecommendUtils.getIllegalPrice().equals(showPrice)) {
            String str4 = this.priceUnit + showPrice;
            int indexOf = str4.indexOf(OrderISVUtil.MONEY_DECIMAL);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str4);
            if (indexOf == -1) {
                indexOf = spannableStringBuilder.length();
            }
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(22, true), 1, indexOf, 33);
            this.firstPriceView.setText(spannableStringBuilder);
            if (RecommendViewUtil.getViewMeasureWidth(this.firstPriceView) > this.canUseWidth) {
                float priceTextSizeScale = getPriceTextSizeScale();
                if (priceTextSizeScale < 1.0f) {
                    int i2 = (int) (priceTextSizeScale * 22.0f);
                    if (i2 < 12) {
                        i2 = 12;
                    }
                    spannableStringBuilder.setSpan(new AbsoluteSizeSpan(i2, true), 1, indexOf, 33);
                    this.firstPriceView.setText(spannableStringBuilder);
                }
            }
            this.firstPriceView.setTextColor(RecommendViewUtil.generateColor(str2, this.jdPriceColor));
            return;
        }
        this.firstPriceView.setTextColor(Color.parseColor(this.jdPriceColor));
        this.firstPriceView.setText(showPrice);
    }

    public void refreshView(RecommendProduct recommendProduct) {
        this.canUseWidth = this.viewWidth;
        RecommendProduct.DoublePriceUp doublePriceUp = recommendProduct.doublePriceUpMap;
        if (doublePriceUp != null) {
            this.realSecondPriceType = doublePriceUp.secPriceType;
            this.realSecondPrice = RecommendViewUtil.setRightSecondPrice(this.rightPriceView, recommendProduct, this.priceUnit);
        }
        if (RecommendViewUtil.isVisible(this.rightPriceView)) {
            this.canUseWidth = (this.viewWidth - RecommendViewUtil.getViewMeasureWidth(this.rightPriceView)) - DpiUtil.dip2px(getContext(), 8.0f);
        }
        if (recommendProduct.firstPriceShowDoubleUp() && !TextUtils.isEmpty(recommendProduct.doublePriceUpMap.doublePriceText)) {
            this.firstIcon.setText(recommendProduct.doublePriceUpMap.doublePriceText);
            this.firstIcon.setTextColor(RecommendViewUtil.generateColor(recommendProduct.doublePriceUpMap.doublePriceColor, this.jdPriceColor));
            RecommendViewUtil.visible(this.firstIcon);
            this.canUseWidth -= RecommendViewUtil.getViewMeasureWidth(this.firstIcon) + DpiUtil.dip2px(getContext(), 6.0f);
        }
        setPriceView(recommendProduct);
        if (!RecommendViewUtil.isVisible(this.rightPriceView)) {
            this.realSecondPriceType = "-100";
            this.realSecondPrice = "-100";
        }
        try {
            recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_SUBPRICE_YPE, this.realSecondPriceType);
            recommendProduct.appendMtaJson.put(RecommendConstant.RECOM_SUBREAL_PRICE, this.realSecondPrice);
        } catch (Exception unused) {
        }
    }

    public void resetView() {
        RecommendViewUtil.gone(this.rightPriceView);
        RecommendViewUtil.gone(this.firstIcon);
        this.firstPriceView.setTextColor(Color.parseColor(this.jdPriceColor));
    }

    public void setAvailableWidth(int i2) {
        this.viewWidth = i2;
    }

    public RecommendDoublePriceView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.realSecondPriceType = "-100";
        this.realSecondPrice = "-100";
        initView();
    }

    public RecommendDoublePriceView(Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.realSecondPriceType = "-100";
        this.realSecondPrice = "-100";
        initView();
    }
}
