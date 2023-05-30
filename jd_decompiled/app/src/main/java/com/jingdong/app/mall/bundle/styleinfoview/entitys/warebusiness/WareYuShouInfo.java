package com.jingdong.app.mall.bundle.styleinfoview.entitys.warebusiness;

import android.text.TextUtils;
import com.jingdong.app.mall.bundle.styleinfoview.utils.PDUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class WareYuShouInfo {
    public static final String LADDER_TYPE_1 = "1";
    public static final String LADDER_TYPE_3 = "2";
    public static final int STATE_1 = 1;
    public static final int STATE_2 = 2;
    public static final int STATE_3 = 3;
    public static final int STATE_4 = 4;
    public static final int STATE_5 = 5;
    public static final String TYPE_END = "2";
    public static final String TYPE_NOT_START = "0";
    public static final String TYPE_START = "1";
    public String bizType;
    public String countdownNumMills;
    public boolean depositExtpand;
    public WareBusinessDreamPurchase dreamPurchase;
    public String expectedDeliveryDate;
    public String finalPaymentDecrease;
    public boolean finalPaymentDecreaseFlag;
    public String finalPaymentDecreaseText;
    public String headText;
    public boolean isMergedBuy;
    public boolean isYuShou;
    public String mergedBuyText;
    public String mergedLogoText;
    public boolean presaleAndOversea;
    public String tailMoney;
    public String tailMoneyEndTime;
    public String tailMoneyStartTime;
    public List<WareBuinessYuShouLadderData> yuShou3LadderData;
    public WareYuShouAdvanceBuyMap yuShouAdvanceBuyMap;
    public String yuShouDeposit;
    public String yuShouJPrice;
    public String yuShouNumOfPeople;
    public String yuShouPrice;
    public String yuShouText;
    public String yuShouType;
    public String yuShouladder;
    public String yuShounowStage;
    public ArrayList<String> yushouRule;
    public String yushouStepType;

    public int getDownBGColor(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.downBGColor)) {
            return PDUtils.parseColor(this.dreamPurchase.downBGColor, str);
        }
        return PDUtils.parseColor(str);
    }

    public int getDownNumBGColor(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.downNumBGColor)) {
            return PDUtils.parseColor(this.dreamPurchase.downNumBGColor, str);
        }
        return PDUtils.parseColor(str);
    }

    public int getDownNumColor(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.downNumColor)) {
            return PDUtils.parseColor(this.dreamPurchase.downNumColor, str);
        }
        return PDUtils.parseColor(str);
    }

    public int getDownTextColor(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.downTextColor)) {
            return PDUtils.parseColor(this.dreamPurchase.downTextColor, str);
        }
        return PDUtils.parseColor(str);
    }

    public int getProBarText2BGColor(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.proBarText2BGColor)) {
            return PDUtils.parseColor(this.dreamPurchase.proBarText2BGColor, str);
        }
        return PDUtils.parseColor(str);
    }

    public int getProBarText2Color(String str) {
        WareBusinessDreamPurchase wareBusinessDreamPurchase = this.dreamPurchase;
        if (wareBusinessDreamPurchase != null && !TextUtils.isEmpty(wareBusinessDreamPurchase.proBarText2Color)) {
            return PDUtils.parseColor(this.dreamPurchase.proBarText2Color, str);
        }
        return PDUtils.parseColor(str);
    }

    public boolean isDreamBuy() {
        return TextUtils.equals("1", this.bizType) && this.dreamPurchase != null;
    }

    public boolean isFullPriceYuShou() {
        return TextUtils.equals("1", this.yushouStepType);
    }

    public boolean isYuShouNewStyle() {
        return TextUtils.equals("2", this.yushouStepType) || TextUtils.equals("5", this.yushouStepType);
    }
}
