package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.jd.lib.productdetail.core.entitys.PDQuanQiuGouQingGuanInfo;
import java.util.List;

/* loaded from: classes15.dex */
public class WarePropertyInfo implements Cloneable {
    public String abTest;
    public WareBusinessAddSubToast addAndSubToast;
    public String areaCartContext;
    public String areaReasonTips;
    public String areaSkuId;
    public boolean asynSearch;
    public boolean batchReceiveCoupon;
    public String bbtf;
    public String brandId;
    public int buyMaxNum;
    public boolean bybtPbFlag;
    public String cardName;
    public boolean cartFlag;
    public List<String> catOneBoxAbTest;
    public String category;
    public String[] categoryIds;
    public String chatUrl;
    public boolean daojiaCartFlag;
    public boolean daojiaFlag;
    public boolean daojiaPingjiaFlag;
    public boolean dpgRevisionFlag;
    public boolean easyBuy;
    public boolean exDegrade;
    public String extRecommendParam;
    public WareFansAwardInfo fansAward;
    public String firstVisitUreturnInterventionUrl;
    public int fromType;
    public String fxylImg;
    public String imgToWareNameFrom;
    public boolean isAiBusiness;
    public boolean isCartRecommend;
    public boolean isCartShield;
    public boolean isCollect;
    public boolean isEncrypt;
    public boolean isFans;
    public String isFlimPrint;
    public boolean isFxyl;
    public boolean isJnbtNewText;
    public boolean isJx;
    public String isMedicine;
    public boolean isNumHide;
    public String isOTC;
    public boolean isOp;
    public String isOverseaPurchase;
    public boolean isPop;
    public boolean isRegisterUser;
    public boolean isRx;
    public String isShadowSku;
    public boolean isShowBarrage;
    public boolean isShowShopNameB;
    public boolean limitBuyButtonNotClickable;
    public boolean limitScenarioTracking;
    public String lowAndLimitText;
    public String lowestBuyNum;
    public boolean newReceiveCoupon;
    public boolean noStockCartFlag;
    public boolean noStockOrder;
    public String noStockOrderButtonColor;
    public String noStockOrderButtonText;
    public String noStockOrderUrl;
    public String ordermin;
    public boolean outOfStockRecommend;
    public String outOfStockRecommendChannel;
    public boolean plus20Flag;
    public String printBagUrl;
    public int qaUpShowCount;
    public PDQuanQiuGouQingGuanInfo qqgQgReasonTips;
    public QzcBuried qzcBuried;
    public String reasonTips;
    public WarePropertyTipsDetailEntity reasonTipsDetail;
    public String reasonTipsImg;
    public String reasonTipsUrl;
    public boolean recTabEnable;
    public WareBusinessRechargeTypeInfo rnMap;
    public boolean sdkDegrade;
    public boolean secKillFlag;
    public String selectedServiceSku;
    public String shareUrl;
    public boolean showEmptyPrice;
    public String showEmptyPriceText;
    public boolean stockNotice;
    public String storeId;
    public boolean threeSuperFlag;
    public String treatyIntroText;
    public String treatyIntroUrl;
    public String type;
    public String wareImageTest;
    public boolean xiajia;
    public String xnkUrl;
    public int suitABTest = -1;
    public boolean evaluateTabEnable = true;

    @NonNull
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e2) {
            e2.printStackTrace();
            return null;
        }
    }

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
