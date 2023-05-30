package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import com.jingdong.jdsdk.res.StringUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.text.DecimalFormat;

/* loaded from: classes3.dex */
public class WarePriceInfo {
    public String channelPrice;
    public WareBusinessFsPriceEntity fsPrice;
    public WareBusinessHagglePriceEntity hagglePrice;
    public String jdMaxPrice;
    public String jdMinPrice;
    public String jprice;
    public String originPrice;
    public String priceTag;
    public boolean rangePriceFlag;
    public String saveMoney;
    public WareBusinessShopVipPriceInfo shopVipPriceInfo;
    public String tokenPrice;
    public WareBusinessNewUserPriceEntity userNewPrice;

    public String getJdPrice() {
        Double valueOf;
        try {
            String str = this.jprice;
            if (str != null && (valueOf = Double.valueOf(str)) != null && valueOf.doubleValue() > 0.0d) {
                return new DecimalFormat("0.00").format(valueOf);
            }
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("WarePriceInfo", e2);
            }
        }
        return StringUtil.no_price;
    }

    public boolean isOffSale() {
        return StringUtil.no_price.equals(getJdPrice());
    }
}
