package com.jd.lib.cashier.sdk.pay.bean.digitalmoney;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.h.h.l;
import com.jd.lib.cashier.sdk.pay.dialog.e;

/* loaded from: classes14.dex */
public class DigitalMoneyBankCard implements ICheckNullObj {
    public CyberMoneyCouponEntity defaultCouponEntity;
    public boolean defaultSelected;
    public boolean selected;
    public e selectedCoupon;
    public String status;
    public String channelId = "";
    public String uniqueChannelId = "";
    public String channelName = "";
    public String logo = "";
    public String payToken = "";
    public String preferentiaNum = "";
    public String realAmount = "";
    public String tip = "";

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DigitalMoneyBankCard) {
            DigitalMoneyBankCard digitalMoneyBankCard = (DigitalMoneyBankCard) obj;
            return l.a(this.status, digitalMoneyBankCard.status) && this.channelId.equals(digitalMoneyBankCard.channelId) && l.a(this.channelName, digitalMoneyBankCard.channelName) && l.a(this.payToken, digitalMoneyBankCard.payToken);
        }
        return false;
    }

    public int hashCode() {
        return l.b(this.status, this.channelId, this.channelName, this.payToken);
    }
}
