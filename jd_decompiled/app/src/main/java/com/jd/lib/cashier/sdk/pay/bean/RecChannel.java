package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CommonCouponEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;

/* loaded from: classes14.dex */
public class RecChannel implements ICheckNullObj {
    public String btnText;
    public String channelId;
    public String closeButton;
    public String code;
    public String plan;
    public CommonCouponEntity recommendBankCoupon;
    public CouponEntity recommendCoupon;
    public String recommendDesc;
    public String uniqueChannelId;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
    }
}
