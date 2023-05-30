package com.jd.lib.cashier.sdk.pay.bean.digitalmoney;

import com.jd.lib.cashier.sdk.b.i.a;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import java.util.List;

/* loaded from: classes14.dex */
public class DigitalMoneyPayEntity implements ICheckNullObj {
    public List<DigitalMoneyBankCard> bankCardList;
    public String channelId;
    public CyberMoneyCouponEntity defaultCouponEntity;
    public String jumpApp;
    public String orderDesc = "";
    public String realAmount = "";
    public String requireUUID = "";
    public String uniqueChannelId;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        a.a(this.bankCardList);
        if (this.defaultCouponEntity == null) {
            this.defaultCouponEntity = new CyberMoneyCouponEntity();
        }
    }
}
