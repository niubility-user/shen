package com.jd.lib.cashier.sdk.pay.bean.baitiao;

import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.TopPriceAnimationInfo;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class BaiTiaoPlanInfo implements ICheckNullObj {
    public AgreementServiceMapMap agreementServiceMap;
    public BaiTiaoExtraTip extraTip;
    public String mianxiHighlight;
    public List<PlanFeeEntity> planFeeList;
    public CouponEntity selectedCoupon;
    public PlanServiceMap serviceMap;
    public TopPriceAnimationInfo topPriceAnimationInfo;

    private void checkPlanFeeList() {
        if (this.planFeeList == null) {
            this.planFeeList = new ArrayList();
        }
        g0.f(this.planFeeList);
    }

    private void checkSelectedCoupon() {
        TopPriceAnimationInfo topPriceAnimationInfo;
        CouponEntity couponEntity = this.selectedCoupon;
        if (couponEntity == null || (topPriceAnimationInfo = this.topPriceAnimationInfo) == null) {
            return;
        }
        couponEntity.setTopPriceAnimationInfo(topPriceAnimationInfo);
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkPlanFeeList();
        checkSelectedCoupon();
    }
}
