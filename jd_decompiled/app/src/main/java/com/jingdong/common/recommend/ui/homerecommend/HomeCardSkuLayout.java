package com.jingdong.common.recommend.ui.homerecommend;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jd.lib.cashier.sdk.core.utils.JDDarkUtil;
import com.jd.mobile.image.JDImageLoader;
import com.jingdong.common.BaseActivity;
import com.jingdong.common.recommend.R;
import com.jingdong.common.search.view.PriceHelper;
import com.jingdong.jdsdk.utils.FontsUtil;
import com.jingdong.sdk.utils.DPIUtil;
import java.util.regex.Pattern;

/* loaded from: classes6.dex */
public class HomeCardSkuLayout extends ConstraintLayout {
    private Info info;
    private HomeCardLabelLayout price;
    private Pattern sPricePattern;
    private SimpleDraweeView skuImg;

    /* loaded from: classes6.dex */
    public static class Info {
        String price;
        String priceColor;
        String skuImg;
        String skuTagImg;

        private Info() {
        }

        public static Info builder() {
            return new Info();
        }

        public Info setPrice(String str) {
            this.price = str;
            return this;
        }

        public Info setPriceColor(String str) {
            this.priceColor = str;
            return this;
        }

        public Info setSkuImg(String str) {
            this.skuImg = str;
            return this;
        }

        public Info setSkuTagImg(String str) {
            this.skuTagImg = str;
            return this;
        }
    }

    public HomeCardSkuLayout(Context context) {
        super(context);
        this.sPricePattern = Pattern.compile("(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)");
        initView(context);
    }

    private void initView(Context context) {
        this.skuImg = new SimpleDraweeView(context);
        this.price = new HomeCardLabelLayout(context);
        addView(this.skuImg);
        addView(this.price);
    }

    private boolean isPrice(String str) {
        return !TextUtils.isEmpty(str) && this.sPricePattern.matcher(str).find();
    }

    private void realBind(BaseActivity baseActivity, Info info) {
        if (!TextUtils.isEmpty(info.skuImg)) {
            JDImageLoader.display(info.skuImg, this.skuImg);
        }
        if (!TextUtils.isEmpty(info.priceColor)) {
            try {
                this.price.setTextColor(Color.parseColor(info.priceColor));
            } catch (Exception unused) {
            }
        }
        this.price.setBackgroudWithFixedRadius(baseActivity, info.skuTagImg);
        if (!TextUtils.isEmpty(info.price)) {
            this.price.setVisibility(0);
            if (isPrice(info.price)) {
                this.price.setText(getPriceString(info.price));
                return;
            } else {
                this.price.setText(info.price);
                return;
            }
        }
        this.price.setVisibility(4);
    }

    private void setViewLayout(BaseActivity baseActivity) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.skuImg.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams).width = -1;
        ((ViewGroup.MarginLayoutParams) layoutParams).height = -1;
        layoutParams.leftToLeft = 0;
        layoutParams.topToTop = 0;
        this.skuImg.setLayoutParams(layoutParams);
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.price.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) layoutParams2).width = -2;
        ((ViewGroup.MarginLayoutParams) layoutParams2).height = DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 30);
        layoutParams2.leftToLeft = 0;
        layoutParams2.rightToRight = 0;
        layoutParams2.bottomToBottom = 0;
        this.price.setLayoutParams(layoutParams2);
        this.price.setMaxWidth(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 132));
        this.price.setGravity(17);
        this.price.setTextSize(0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 22));
        this.price.setPadding(DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8), 0, DPIUtil.getWidthByDesignValue750((Activity) baseActivity, 8), 0);
        this.price.setTextColor(Color.parseColor(JDDarkUtil.COLOR_FA2C19));
        FontsUtil.changeTextFont(this.price, 4099);
        this.price.setIncludeFontPadding(false);
    }

    public void bind(BaseActivity baseActivity, Info info) {
        if (info == null || baseActivity == null) {
            return;
        }
        this.info = info;
        try {
            setViewLayout(baseActivity);
            realBind(baseActivity, info);
        } catch (Exception unused) {
        }
    }

    public String getPriceString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String string = getContext().getResources().getString(R.string.yangjiao);
        return (TextUtils.equals(str, string) || TextUtils.equals(str, PriceHelper.PRODUCT_PRICE_LABEL_FULL)) ? "" : (str.contains(string) || str.contains(PriceHelper.PRODUCT_PRICE_LABEL_FULL)) ? str : string.concat(str);
    }

    public HomeCardSkuLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.sPricePattern = Pattern.compile("(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)");
        initView(context);
    }

    public HomeCardSkuLayout(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.sPricePattern = Pattern.compile("(^[1-9]\\d*(\\.\\d{1,2})?$)|(^0(\\.\\d{1,2})?$)");
        initView(context);
    }
}
