package com.jd.lib.productdetail.core.entitys.warebusiness;

import java.math.BigDecimal;

/* loaded from: classes15.dex */
public class WareBusinessPaiPaiReplacement {
    public static final int BIZ_CODE_JD_JNBT = 2;
    public static final int BIZ_CODE_JD_YJHX = 1;
    public static final int BIZ_CODE_JD_YJHX_BTB = 3;
    public static final int BIZ_CODE_PAIPAI_YJHX = 4;
    public static final int BIZ_PAIPAI_3C_YJHX = 4;
    public static final int BIZ_PAIPAI_GJHS = 3;
    public static final int BIZ_PAIPAI_PAIMAI = 2;
    public static final int BIZ_PAIPAI_PAIPAI_YJHX = 5;
    public static final int BIZ_PAIPAI_SECOND_HAND = 1;
    @Deprecated
    public static final int BIZ_PAIPAI_YJHX = 6;
    public static final int BIZ_TRADEIN = 6;
    public static final int SETTLETYPE_APP = 1;
    public static final int SETTLETYPE_H5 = 2;
    public static final int TRADE_TYPE_10 = 10;
    public static final int TRADE_TYPE_11 = 11;
    public WareBusinessAppleReplaceData appleService;
    public int biz;
    public int bizCode;
    public WareBusinessPaiPaiReplaceBody body;
    public boolean canExchange;
    public boolean canJump;
    public boolean canPopup = true;
    public String clickText;
    public String clickUrl;
    public String code;
    public String codeDesc;
    public String extend;
    public Boolean haveOldUserSubsidy;
    public String imgTitleNew;
    public boolean inAuction;
    public boolean isOpenJG;
    public BigDecimal jdSubsidyAmount;
    public String jgBuryPoint;
    public String leftCorner;
    public int legacyMode;
    public String maxSubsidy;
    public String maxSubsidyHourBuy;
    public String maxSubsidyText;
    public boolean mine;
    public Integer modelType;
    public String oldImg;
    public String oldProductName;
    public boolean openJG;
    public BigDecimal recoveryAmount;
    public String recyclePrice;
    public String recycleSettleUrl;
    public String recycleUrl;
    public String remainSeconds;
    public WareBusinessServiceIntroduction serviceIntroduction;
    public int settleType;
    public boolean shieldBottomStrip;
    public String skuDesc;
    public String subsidy4Floor;
    public String subsidy4Toast;
    public String tabType;
    public String text1;
    public String text2;
    public String text3;
    public String title;
    public String titleImage;
    public int tradeType;
    public String yjhxTabBg;

    /* loaded from: classes15.dex */
    public static class WareBusinessAppleReplaceData {
        public int defaultSelectedType;
        public String text1;
        public String text2;
        public String tips;
        public String title;
    }

    /* loaded from: classes15.dex */
    public static class WareBusinessPaiPaiReplaceBody {
        public String cache;
        public String jp;
        public String skuId;
        public int type;
    }

    /* loaded from: classes15.dex */
    public static class WareBusinessServiceIntroduction {
        public String introductionTipUrl;
        public String introductionTitle;
        public String introductionUrl;
    }

    public static String getToastBiz(int i2, int i3) {
        return (i2 == 1 || i2 == 2 || i2 == 3) ? "3" : i2 != 4 ? "-100" : i3 == 10 ? "2" : i3 == 11 ? "1" : "-100";
    }
}
