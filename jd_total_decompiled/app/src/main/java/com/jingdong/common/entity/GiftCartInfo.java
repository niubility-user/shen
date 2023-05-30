package com.jingdong.common.entity;

import android.text.TextUtils;
import com.jingdong.common.utils.CalculationUtil;
import com.jingdong.sdk.oklog.OKLog;
import java.io.Serializable;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class GiftCartInfo implements Serializable, Cloneable {
    public static final int CARDTYPE_COED_CARD = 3;
    public static final int CARDTYPE_E_CARD = 2;
    public static final int CARDTYPE_JD_CARD = 0;
    public static final int CARDTYPE_JP_CARD = 1;
    public static final String GIFT_TYPE = "giftType";
    public static final int GIFT_TYPE_CARD = 0;
    public static final int GIFT_TYPE_CODE = 5;
    private static final String TAG = "GiftCartInfo";
    public static final int TYPE_E_CARD = 3;
    public static final int TYPE_JD_CARD = 2;
    public static final int YN_CAN_USE = 1;
    private static final long serialVersionUID = 4148249244968038363L;
    public Double Discount;
    public Integer DiscountBind;
    public String Id;
    public String Key;
    public Double LeaveMoney;
    public String Pin;
    public String TimeBegin;
    public String TimeEnd;
    public int Yn;
    public int cardType;
    public String disableDesc;
    public Double discountCurUsed;
    public Double discountUsed;
    public String discountValue;
    public String expiredDate;
    public String expiredMsg;
    public boolean isModify;
    public boolean isSuccess;
    public String message;
    public String name;
    public String scope;
    public Boolean selected;
    @Deprecated
    public int type;

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String dealTime(String str) {
        try {
            return (TextUtils.isEmpty(str) || !str.contains("/")) ? str : str.replaceAll("/", "\\.");
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("CouponInfo", "Exception-->", e2);
                return str;
            }
            return str;
        }
    }

    public String getDiscountName() {
        try {
            if (this.Discount != null) {
                return CalculationUtil.resultDecimalFormat(this.Discount.doubleValue()) + "\u5143";
            }
            return "";
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e("Temp", "CouponGiftInfo getDiscountName -->> ", e2);
                return "";
            }
            return "";
        }
    }

    public String getKey() {
        String str = this.Key;
        return str == null ? "" : str;
    }

    public String getLeaveMoneyShow() {
        Double d = this.LeaveMoney;
        return d != null ? CalculationUtil.resultDecimalFormat(d.doubleValue()) : "";
    }

    public String getPin() {
        String str = this.Pin;
        return str == null ? "" : str;
    }

    public Boolean getSelected() {
        Boolean bool = this.selected;
        return bool == null ? Boolean.FALSE : bool;
    }

    public String getTimeBegin() {
        String str = this.TimeBegin;
        return (str == null || str.length() < 10) ? "" : this.TimeBegin.substring(0, 10);
    }

    public String getTimeEnd() {
        String str = this.TimeEnd;
        return (str == null || str.length() < 10) ? "" : this.TimeEnd.substring(0, 10);
    }

    public String getTimeShow() {
        String dealTime = dealTime(getTimeEnd());
        String dealTime2 = dealTime(getTimeBegin());
        if (TextUtils.isEmpty(dealTime) || TextUtils.isEmpty(dealTime2)) {
            return "";
        }
        return dealTime2 + "-" + dealTime;
    }

    public JSONObject toOrderJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("Id", this.Id);
            jSONObject.put("Key", getKey());
            jSONObject.put("Selected", getSelected());
        } catch (Exception e2) {
            if (OKLog.E) {
                OKLog.e(TAG, e2);
            }
        }
        return jSONObject;
    }
}
