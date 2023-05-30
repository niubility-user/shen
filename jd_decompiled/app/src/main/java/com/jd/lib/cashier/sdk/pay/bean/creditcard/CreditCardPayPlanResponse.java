package com.jd.lib.cashier.sdk.pay.bean.creditcard;

import com.jd.lib.cashier.sdk.b.i.a;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.List;

/* loaded from: classes14.dex */
public class CreditCardPayPlanResponse extends b implements ICheckNullObj {
    public AgreementServiceMapMap agreementServiceMap;
    public List<CouponEntity> canUseCouponList;
    public List<CouponEntity> cantUseCouponList;
    public List<CreditCardPlan> planFeeList;
    public CouponEntity selectedCoupon;
    public PlanServiceMap serviceMap;

    private void checkAgreementServiceMapMap() {
        if (this.agreementServiceMap == null) {
            this.agreementServiceMap = new AgreementServiceMapMap();
        }
        this.agreementServiceMap.checkNullObjAndInit();
    }

    private void checkSelectedCoupon() {
        if (this.selectedCoupon == null) {
            this.selectedCoupon = new CouponEntity();
        }
        this.selectedCoupon.checkNullObjAndInit();
    }

    private void checkServiceMap() {
        if (this.serviceMap == null) {
            this.serviceMap = new PlanServiceMap();
        }
        this.serviceMap.checkNullObjAndInit();
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        a.a(this.planFeeList);
        a.a(this.canUseCouponList);
        a.a(this.cantUseCouponList);
        checkSelectedCoupon();
        checkServiceMap();
        checkAgreementServiceMapMap();
    }
}
