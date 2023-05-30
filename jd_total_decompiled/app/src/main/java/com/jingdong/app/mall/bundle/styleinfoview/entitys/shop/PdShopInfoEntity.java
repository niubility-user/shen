package com.jingdong.app.mall.bundle.styleinfoview.entitys.shop;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class PdShopInfoEntity {
    public static final int CARDTYPE_CARD = 3;
    public static final String SHOP_PROMOTION_B = "B";
    public String afterSaleGrade;
    public String afterSaleScore;
    public String afterSaleTxt;
    public String brief;
    public int cardType;
    public ArrayList<PdShopCategoryInfoEntity> categoryInfo;
    public String evaluateGrade;
    public String evaluateScore;
    public String evaluateTxt;
    public int followCount;
    public String followText;
    public String giftIcon;
    public List<PdShopCardItemEntity> hotcates;
    public String hotcatestr;
    public boolean isSquareLogo;
    public String logisticsGrade;
    public String logisticsScore;
    public String logisticsText;
    public String logisticsTxt;
    public String logo;
    public String name;
    public String nameB;
    public int newNum;
    public List<PdShopPromotionEntity> promotions;
    public double score;
    public String scoreRankRateGrade;
    public String scoreText;
    public String serverText;
    public String shopId;
    public String shopImage;
    public String shopStarTxt;
    public String shopStateText;
    public boolean showSignboardUrl;
    public String signboardUrl;
    public String skuCntText;
    public String skuText;
    public int source;
    public String squareLogo;
    public int totalNum;
    public String url;
    public boolean diamond = false;
    public double wareScore = 0.0d;
    public double avgWareScore = 0.0d;
    public double efficiencyScore = 0.0d;
    public double avgEfficiencyScore = 0.0d;
    public double serviceScore = 0.0d;
    public double avgServiceScore = 0.0d;
    public int shopActivityTotalNum = 0;

    public void changeShopName(boolean z) {
        if (TextUtils.isEmpty(this.nameB) || !z) {
            return;
        }
        this.name = this.nameB;
    }
}
