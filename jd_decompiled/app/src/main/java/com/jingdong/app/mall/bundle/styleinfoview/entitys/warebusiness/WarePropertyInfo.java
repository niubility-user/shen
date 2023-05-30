package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class WarePropertyInfo {
    public WareBusinessAddSubToast addAndSubToast;
    public String areaCartContext;
    public String areaReasonTips;
    public String areaSkuId;
    public boolean batchReceiveCoupon;
    public String bbtf;
    public String brandId;
    public int buyMaxNum;
    public boolean bybtPbFlag;
    public String cardName;
    public boolean cartFlag;
    public String category;
    public String[] categoryIds;
    public String chatUrl;
    public boolean daojiaCartFlag;
    public boolean daojiaFlag;
    public boolean daojiaPingjiaFlag;
    public boolean easyBuy;
    public int fromType;
    public String fxylImg;
    public String imgToWareNameFrom;
    public boolean isCartRecommend;
    public boolean isCartShield;
    public boolean isCollect;
    public boolean isEncrypt;
    public boolean isFans;
    public String isFlimPrint;
    public boolean isFxyl;
    public boolean isJnbtNewText;
    public boolean isJx;
    public boolean isNumHide;
    public String isOTC;
    public boolean isOp;
    public boolean isPop;
    public boolean isRegisterUser;
    public boolean isRx;
    public String isShadowSku;
    public boolean isShowBarrage;
    public boolean isShowShopNameB;
    public String lowAndLimitText;
    public String lowestBuyNum;
    public boolean newReceiveCoupon;
    public boolean noStockOrder;
    public String noStockOrderButtonColor;
    public String noStockOrderButtonText;
    public String noStockOrderUrl;
    public String ordermin;
    public boolean outOfStockRecommend;
    public String printBagUrl;
    public int qaUpShowCount;
    public String reasonTips;
    public String reasonTipsImg;
    public String reasonTipsUrl;
    public boolean recTabEnable;
    public WareBusinessRechargeTypeInfo rnMap;
    public String selectedServiceSku;
    public String shareUrl;
    public boolean showEmptyPrice;
    public String showEmptyPriceText;
    public boolean stockNotice;
    public String storeId;
    public String treatyIntroText;
    public String treatyIntroUrl;
    public String type;
    public String wareImageTest;
    public String xnkUrl;
    public int suitABTest = -1;
    public boolean evaluateTabEnable = true;

    public String getCategroyId(int i2) {
        String[] strArr = this.categoryIds;
        if (strArr != null && strArr.length > i2) {
            return strArr[i2];
        }
        if (!TextUtils.isEmpty(this.category)) {
            this.categoryIds = this.category.split(";");
        }
        String[] strArr2 = this.categoryIds;
        return (strArr2 == null || strArr2.length <= i2) ? "" : strArr2[i2];
    }
}
