package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.BaiTiaoExtraTip;
import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.AgreementServiceMapMap;
import com.jd.lib.cashier.sdk.pay.bean.baitiao.PlanServiceMap;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class BaiTiaoPayPlanResponse extends b implements ICheckNullObj {
    public static final int HAS_MODIFY = 1;
    public AgreementServiceMapMap agreementServiceMap;
    public List<CouponEntity> canUseCouponList;
    public List<CouponEntity> cantUseCouponList;
    public boolean clearCouponInfo;
    public String discountConflictTip;
    public BaiTiaoExtraTip extraTip;
    public String investorDoc;
    public int isMarketRecommendModify;
    public String mianxiHighlight;
    public List<PlanFeeEntity> planFeeList;
    public CouponEntity selectedCoupon;
    public PlanServiceMap serviceMap;
    public TopPriceAnimationInfo topPriceAnimationInfo;
    public String baiTiaoTip = "";
    public String baiTiaoHighlightTip = "";
    public boolean hiddenFrozenPriceFlag = false;

    private void checkAgreementServiceMapMap() {
        if (this.agreementServiceMap == null) {
            this.agreementServiceMap = new AgreementServiceMapMap();
        }
        this.agreementServiceMap.checkNullObjAndInit();
    }

    private void checkBaiTiaoExtraTip() {
        if (this.extraTip == null) {
            this.extraTip = new BaiTiaoExtraTip();
        }
        this.extraTip.checkNullObjAndInit();
    }

    private void checkCanUseCouponList() {
        if (this.canUseCouponList == null) {
            this.canUseCouponList = new ArrayList();
        }
        g0.f(this.canUseCouponList);
        List<CouponEntity> list = this.canUseCouponList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<CouponEntity> it = this.canUseCouponList.iterator();
        while (it.hasNext()) {
            it.next().checkNullObjAndInit();
        }
    }

    private void checkCantUseCouponList() {
        if (this.cantUseCouponList == null) {
            this.cantUseCouponList = new ArrayList();
        }
        g0.f(this.cantUseCouponList);
        List<CouponEntity> list = this.cantUseCouponList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<CouponEntity> it = this.cantUseCouponList.iterator();
        while (it.hasNext()) {
            it.next().checkNullObjAndInit();
        }
    }

    private void checkPlanFeeList() {
        if (this.planFeeList == null) {
            this.planFeeList = new ArrayList();
        }
        g0.f(this.planFeeList);
        List<PlanFeeEntity> list = this.planFeeList;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<PlanFeeEntity> it = this.planFeeList.iterator();
        while (it.hasNext()) {
            it.next().checkNullObjAndInit();
        }
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

    private void checkTopPriceAnimationInfo() {
        if (this.topPriceAnimationInfo == null) {
            this.topPriceAnimationInfo = new TopPriceAnimationInfo();
        }
        this.topPriceAnimationInfo.checkNullObjAndInit();
    }

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkPlanFeeList();
        checkCanUseCouponList();
        checkCantUseCouponList();
        checkSelectedCoupon();
        checkBaiTiaoExtraTip();
        checkTopPriceAnimationInfo();
        checkServiceMap();
        checkAgreementServiceMapMap();
    }
}
