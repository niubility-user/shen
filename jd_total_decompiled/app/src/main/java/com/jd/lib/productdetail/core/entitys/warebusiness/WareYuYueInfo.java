package com.jd.lib.productdetail.core.entitys.warebusiness;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import java.util.ArrayList;

/* loaded from: classes15.dex */
public class WareYuYueInfo {
    public static final String BEGINGTOORDER = "2";
    public static final String BEGINTOBUY = "4";
    public static final String ENDBUY = "5";
    public static final String HIT_ALL_BUY = "4";
    public static final String HIT_NO = "3";
    public static final String HIT_NO_BUY = "1";
    public static final String NO_YUYUE = "2";
    public static final String RISK_TYPE_NORMAL = "0";
    public static final String RISK_TYPE_PLUS = "1";
    public static final String RISK_TYPE_PLUS_FISSION = "2";
    public static final String WAITINGTOBUY = "3";
    public static final String WAITINGTOORDER = "1";
    public String apointOriginPrice;
    public PdYuYueInfoEntity appointNew;
    public String appointPrice;
    public String autoAddCart;
    public String bgImage;
    public String btn2ndText;
    public String buttonLeftText;
    public String buttonRightText;
    public String buyEndTime;
    public String buyStartTime;
    public String buyTime;
    public String buyTimeLabel;
    public String drawCodeInfo;
    public String drawEnum;
    public String drawRiskType;
    public String drawStatus;
    public String drawTitle;
    public String drawUrl;
    public String hidePrice;
    public String hideText;
    public int isNewStyle;
    public boolean isYuYue;
    public boolean isbuyTime;
    public boolean mad;
    public String madBtnText;
    public String madLogo;
    public String memberText;
    public String memberUrl;
    public boolean openYuYueFlag;
    public boolean pinSkuYuYue;
    public String sellWhilePresell;
    public boolean showDraw;
    public String showPromoPrice;
    public long startTime;
    public String type;
    public String yaohaoEndTime;
    public String yaohaoStartTime;
    public String yaohaoTime;
    public String yhToastContent;
    public String yhToastIcon;
    public String yhToastTitle;
    public String yuYueEndTime;
    public String yuYueStartTime;
    public int yuyueFloorAb;
    public boolean yuyueMiaosha;
    public int yuyueNum;
    public ArrayList<String> yuyueRuleText;
    public String yuyueShowCode;
    public long yuyueStartTimestamp;
    public String yuyueState;
    public String yuyueText;
    public String yuyueTime;
    public String yuyueType;
    public String yuyueTypeText;

    public SpannableString formatHidePriceText(String str) {
        SpannableString spannableString = new SpannableString(str);
        if (!TextUtils.isEmpty(str)) {
            spannableString.setSpan(new RelativeSizeSpan(0.78f), 0, 1, 33);
        }
        return spannableString;
    }

    public boolean isAdvanceBuy() {
        return TextUtils.equals("1", this.sellWhilePresell);
    }

    public boolean isHidePrice() {
        return TextUtils.equals("1", this.hidePrice);
    }

    public boolean isMask() {
        return TextUtils.equals(this.type, "5");
    }

    public boolean isRiskTypePlusFission() {
        return (!TextUtils.equals(this.drawRiskType, "2") || TextUtils.isEmpty(this.drawCodeInfo) || TextUtils.isEmpty(this.drawTitle)) ? false : true;
    }

    public boolean isShowPromoPrice() {
        return TextUtils.equals("1", this.showPromoPrice);
    }
}
