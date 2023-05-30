package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class AllCoupons implements ICheckNullObj {
    public List<CouponAndCutOffs> couponAndCutOffs;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.couponAndCutOffs == null) {
            this.couponAndCutOffs = new ArrayList();
        }
        g0.f(this.couponAndCutOffs);
        if (this.couponAndCutOffs.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.couponAndCutOffs.size(); i2++) {
            CouponAndCutOffs couponAndCutOffs = this.couponAndCutOffs.get(i2);
            if (couponAndCutOffs != null) {
                couponAndCutOffs.checkNullObjAndInit();
            }
        }
    }
}
