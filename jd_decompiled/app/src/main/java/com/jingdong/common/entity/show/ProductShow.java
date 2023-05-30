package com.jingdong.common.entity.show;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import androidx.core.internal.view.SupportMenu;
import com.jingdong.common.entity.Product;
import com.jingdong.common.entity.ProductDetailBasicInfo;
import com.jingdong.common.entity.ProductDetailPrice;
import com.jingdong.common.utils.LangUtils;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;

/* loaded from: classes5.dex */
public class ProductShow {
    private static final long serialVersionUID = 5458650057466774157L;
    private Context context;
    private Product product;
    private int resId;
    private ForegroundColorSpan colorSpanRed = new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK);
    private ForegroundColorSpan colorSpanGray = new ForegroundColorSpan(-7829368);
    private ForegroundColorSpan colorSpanRedNew = new ForegroundColorSpan(-3537402);

    public ProductShow(Context context, Product product, int i2) {
        this.context = context;
        this.product = product;
        this.resId = i2;
    }

    public String getImgUrl(int i2) {
        return this.product.popImgUrl(i2);
    }

    public CharSequence getJdPrice() {
        String str;
        ProductDetailBasicInfo productDetailBasicInfo = this.product.getProductDetailBasicInfo();
        if (OKLog.D) {
            OKLog.d("Temp", " -->>\u4eac\u4e1c\u4ef7 isMiaosha \uff1a " + productDetailBasicInfo.isMiaosha());
        }
        ProductDetailPrice productDetailJprice = this.product.getProductDetailJprice();
        if (productDetailBasicInfo.isMiaosha()) {
            if (productDetailJprice != null) {
                str = productDetailJprice.getName() + "\u00a5";
            } else {
                str = StringUtil.product_limitbuy_price;
            }
        } else if (productDetailJprice != null) {
            str = productDetailJprice.getName() + "\u00a5";
        } else {
            str = StringUtil.product_jd_price;
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(((Object) str) + this.product.getJdPrice());
        int length = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), 0, length, 33);
        spannableStringBuilder.setSpan(this.colorSpanRed, str.length() + (-1), length, 33);
        return spannableStringBuilder;
    }

    public CharSequence getMarketPrice() {
        if (this.product.getMarketPrice() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\u00a5" + this.product.getMarketPrice());
            spannableStringBuilder.setSpan(this.colorSpanGray, 0, spannableStringBuilder.length(), 17);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
            spannableStringBuilder.insert(0, (CharSequence) (this.product.getMarketPriceDescription() + "\uff1a"));
            return spannableStringBuilder;
        }
        return "";
    }

    public CharSequence getMarketPriceNew() {
        if (this.product.getMarketPrice() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\u00a5" + this.product.getMarketPrice());
            spannableStringBuilder.setSpan(this.colorSpanGray, 0, spannableStringBuilder.length(), 17);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
            spannableStringBuilder.insert(0, (CharSequence) "\u539f\u4ef7\uff1a");
            return spannableStringBuilder;
        }
        return "";
    }

    public CharSequence getMarketPriceOnlyNumber() {
        if (this.product.getMarketPrice() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\u00a5" + this.product.getMarketPrice());
            spannableStringBuilder.setSpan(this.colorSpanGray, 0, spannableStringBuilder.length(), 17);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
            return spannableStringBuilder;
        }
        return "";
    }

    public CharSequence getNameAndAdWord() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.product.getName() + this.product.getAdWord());
        int length = this.product.getName().length();
        int length2 = spannableStringBuilder.length();
        if (this.product.getAdWord().length() > 0) {
            spannableStringBuilder.setSpan(this.colorSpanRedNew, length, length2, 33);
        }
        if (this.product.isPromotion().booleanValue()) {
            spannableStringBuilder.append((CharSequence) LangUtils.SINGLE_SPACE);
            spannableStringBuilder.setSpan(new ImageSpan(this.context, this.resId), length2, spannableStringBuilder.length(), 33);
        }
        return spannableStringBuilder;
    }

    public CharSequence getNameAndAdWordForPanic() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.product.getName() + this.product.getAdWord());
        int length = this.product.getName().length();
        int length2 = spannableStringBuilder.length();
        if (this.product.getAdWord().length() > 0) {
            spannableStringBuilder.setSpan(this.colorSpanRed, length, length2, 33);
        }
        if (this.product.isPromotion().booleanValue()) {
            spannableStringBuilder.append((CharSequence) LangUtils.SINGLE_SPACE);
            spannableStringBuilder.setSpan(new ImageSpan(this.context, this.resId), length2, spannableStringBuilder.length(), 33);
        }
        return spannableStringBuilder;
    }

    public CharSequence getNameAndZeng() {
        SpannableStringBuilder spannableStringBuilder;
        int length;
        if (this.product.getName().length() > 40) {
            String str = this.product.getName().substring(0, 40) + "...";
            spannableStringBuilder = new SpannableStringBuilder(str);
            length = str.length();
        } else {
            spannableStringBuilder = new SpannableStringBuilder(this.product.getName());
            length = this.product.getName().length();
        }
        int length2 = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.0f), 0, length, 33);
        spannableStringBuilder.setSpan(this.colorSpanRed, length, length2, 33);
        spannableStringBuilder.append((CharSequence) LangUtils.SINGLE_SPACE);
        spannableStringBuilder.setSpan(new ImageSpan(this.context, this.resId), length2, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    public Product getProduct() {
        return this.product;
    }

    public CharSequence getPromotionTitleAndInfo() {
        if (this.product.getPromotionInfo() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) this.product.getPromotionTitle());
            spannableStringBuilder.append((CharSequence) this.product.getPromotionInfo());
            spannableStringBuilder.setSpan(new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK), this.product.getPromotionTitle().length(), spannableStringBuilder.length(), 33);
            return spannableStringBuilder;
        }
        return null;
    }

    public CharSequence getPromotionTitleAndInfoNew() {
        if (this.product.getPromotionInfo() != null) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) this.product.getPromotionTitle());
            spannableStringBuilder.append((CharSequence) this.product.getPromotionInfo());
            spannableStringBuilder.setSpan(new ForegroundColorSpan((int) SupportMenu.CATEGORY_MASK), this.product.getPromotionTitle().length(), spannableStringBuilder.length(), 33);
            return spannableStringBuilder;
        }
        return null;
    }

    public String getUserClass() {
        return this.product.getUserClass();
    }

    public String getUserName() {
        return this.product.getUsername();
    }

    public CharSequence getUserPrice() {
        String str = this.product.getUserPriceLabel() + "\uff1a\u00a5";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(((Object) str) + this.product.getUserPriceContent());
        int length = spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new RelativeSizeSpan(1.2f), 0, length, 33);
        spannableStringBuilder.setSpan(this.colorSpanRed, str.length() + (-1), length, 33);
        return spannableStringBuilder;
    }

    public CharSequence getMarketPriceNew(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("\u00a5" + str);
        spannableStringBuilder.setSpan(this.colorSpanGray, 0, spannableStringBuilder.length(), 17);
        spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.insert(0, (CharSequence) str2);
        return spannableStringBuilder;
    }
}
