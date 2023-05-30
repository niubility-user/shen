package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;

/* loaded from: classes15.dex */
public class WarePriceInfo {
    public WareBusinessFsPriceEntity fsPrice;
    public WareBusinessHagglePriceEntity hagglePrice;
    public String jdMaxPrice;
    public String jdMinPrice;
    public String jprice;
    public String originPrice;
    public String priceIcon;
    public PricePopContent pricePopContent;
    public boolean rangePriceFlag;
    public String saveMoney;
    public WareBusinessShopVipPriceInfo shopVipPriceInfo;
    public boolean showi;
    public String textBeforeFloorPrice;
    public String textBeforeSelectedPrice;
    public String tokenPrice;
    public WareBusinessNewUserPriceEntity userNewPrice;

    /* loaded from: classes15.dex */
    public static class PricePopContent {
        public String buttonText;
        public String content;
        public String title;
    }

    public String getJdPrice() {
        Double valueOf;
        try {
            String str = this.jprice;
            return (str == null || (valueOf = Double.valueOf(str)) == null || valueOf.doubleValue() <= 0.0d) ? StringUtil.no_price : new DecimalFormat("0.00").format(valueOf);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("WarePriceInfo", e2);
                return StringUtil.no_price;
            }
            return StringUtil.no_price;
        }
    }

    public boolean isJPriceValid() {
        if (TextUtils.isEmpty(this.jprice)) {
            return true;
        }
        try {
            Double valueOf = Double.valueOf(this.jprice);
            if (valueOf != null) {
                return valueOf.doubleValue() >= 0.0d;
            }
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    public boolean isOffSale() {
        return StringUtil.no_price.equals(getJdPrice());
    }
}
