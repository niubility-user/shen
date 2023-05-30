package com.jd.lib.cashier.sdk.pay.bean.creditcard;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.h.h.l;

/* loaded from: classes14.dex */
public class CreditCard implements ICheckNullObj {
    public String bankCode;
    public String bankName;
    public String bankNameShow;
    public boolean cardAd;
    public boolean cardCanUse;
    public String channelId;
    public String logo;
    public String preferentiaNum;
    public String recommendPlanId;
    public boolean selected;
    public String tip;
    public String uniqueChannelId;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CreditCard) {
            CreditCard creditCard = (CreditCard) obj;
            return this.bankCode.equals(creditCard.bankCode) && l.a(this.bankName, creditCard.bankName) && l.a(this.uniqueChannelId, creditCard.uniqueChannelId) && this.channelId.equals(creditCard.channelId);
        }
        return false;
    }

    public int hashCode() {
        return l.b(this.bankCode, this.bankName, this.uniqueChannelId, this.channelId);
    }

    public String toString() {
        return "CreditCard{bankCode='" + this.bankCode + "', bankName='" + this.bankName + "', cardCanUse=" + this.cardCanUse + ", recommendPlanId='" + this.recommendPlanId + "', logo='" + this.logo + "', tip='" + this.tip + "', bankNameShow='" + this.bankNameShow + "', uniqueChannelId='" + this.uniqueChannelId + "', channelId='" + this.channelId + "', preferentiaNum='" + this.preferentiaNum + "', selected=" + this.selected + ", cardAd=" + this.cardAd + '}';
    }
}
