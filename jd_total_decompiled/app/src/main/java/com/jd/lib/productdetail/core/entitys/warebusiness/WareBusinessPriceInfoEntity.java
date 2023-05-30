package com.jd.lib.productdetail.core.entitys.warebusiness;

import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;

/* loaded from: classes15.dex */
public class WareBusinessPriceInfoEntity {
    public WareBusinessPriceDetailInfo discount;
    public WareBusinessHagglePriceEntity hagglePrice;
    public boolean isCut;
    public WareBusinessPriceDetailInfo jprice;
    public WareBusinessPriceDetailInfo mprice;
    public WareBusinessPriceDetailInfo pcPrice;
    public String saveMoney;
    public WareBusinessTokenPriceEntity tokenPrice;
    public WareBusinessNewUserPriceEntity userNewPrice;

    public String getJdPrice() {
        Double valueOf;
        try {
            WareBusinessPriceDetailInfo wareBusinessPriceDetailInfo = this.jprice;
            return (wareBusinessPriceDetailInfo == null || (valueOf = Double.valueOf(wareBusinessPriceDetailInfo.getDefaultValue())) == null || valueOf.doubleValue() <= 0.0d) ? StringUtil.no_price : new DecimalFormat("0.00").format(valueOf);
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("WareBusinessPriceInfoEntity", e2);
                return StringUtil.no_price;
            }
            return StringUtil.no_price;
        }
    }

    public boolean isOffSale() {
        return StringUtil.no_price.equals(getJdPrice());
    }
}
