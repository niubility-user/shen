package com.jd.lib.cashier.sdk.e.e.c;

import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.d.g.c.c.b;
import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.convert.PaymentChoseHolder;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.Map;

/* loaded from: classes14.dex */
public class a {
    private static PaymentChoseHolder a(com.jd.lib.cashier.sdk.e.b.a aVar, Payment payment) {
        PaymentChoseHolder paymentChoseHolder = new PaymentChoseHolder();
        c(aVar, paymentChoseHolder, payment);
        d(paymentChoseHolder, payment);
        return paymentChoseHolder;
    }

    public static void b(CashierCreditPayActivity cashierCreditPayActivity, Payment payment) {
        if (!g0.a(cashierCreditPayActivity) || payment == null) {
            return;
        }
        b bVar = new b();
        PaymentChoseHolder a = a(((CashierCreditPayViewModel) ViewModelProviders.of(cashierCreditPayActivity).get(CashierCreditPayViewModel.class)).b(), payment);
        bVar.setActivity(cashierCreditPayActivity);
        bVar.appId = a.appId;
        bVar.a = a.from;
        bVar.t = a.isNewCard;
        bVar.f3287c = a.channelType;
        if (!TextUtils.isEmpty(a.planId)) {
            bVar.f3270g = a.planId;
        }
        if (!TextUtils.isEmpty(a.prizeId)) {
            bVar.f3277n = a.prizeId;
        }
        if (!TextUtils.isEmpty(a.payToken)) {
            bVar.v = a.payToken;
        }
        if (!TextUtils.isEmpty(a.couponId)) {
            bVar.f3271h = a.couponId;
        }
        if (!TextUtils.isEmpty(a.planInfo)) {
            bVar.f3275l = a.planInfo;
        }
        if (!TextUtils.isEmpty(a.bankCode)) {
            bVar.s = a.bankCode;
        }
        if (!TextUtils.isEmpty(a.channelId)) {
            bVar.f3269f = a.channelId;
        }
        if (!TextUtils.isEmpty(a.activityId)) {
            bVar.f3272i = a.activityId;
        }
        if (!TextUtils.isEmpty(a.requireUUID)) {
            bVar.f3274k = a.requireUUID;
        }
        if (!TextUtils.isEmpty(a.accountCode)) {
            bVar.r = a.accountCode;
        }
        if (!TextUtils.isEmpty(a.productCode)) {
            bVar.u = a.productCode;
        }
        if (!TextUtils.isEmpty(a.channelType)) {
            bVar.o = a.channelType;
        }
        if (!TextUtils.isEmpty(a.bankPlanRate)) {
            bVar.p = a.bankPlanRate;
        }
        if (!TextUtils.isEmpty(a.channelStatus)) {
            bVar.f3273j = a.channelStatus;
        }
        if (!TextUtils.isEmpty(a.payMarketingUUID)) {
            bVar.f3276m = a.payMarketingUUID;
        }
        if (!TextUtils.isEmpty(a.merchantFeeSubSideBy)) {
            bVar.q = a.merchantFeeSubSideBy;
        }
        Map<String, String> map = a.tradeMap;
        if (map != null && !map.isEmpty()) {
            bVar.z = a.tradeMap;
        }
        new com.jd.lib.cashier.sdk.e.e.a.a().e(bVar);
    }

    private static void c(com.jd.lib.cashier.sdk.e.b.a aVar, PaymentChoseHolder paymentChoseHolder, Payment payment) {
        if (paymentChoseHolder == null || payment == null || aVar == null) {
            return;
        }
        paymentChoseHolder.appId = aVar.b;
        paymentChoseHolder.from = aVar.p;
        paymentChoseHolder.channelType = payment.code;
        paymentChoseHolder.channelId = payment.channelId;
        paymentChoseHolder.channelStatus = payment.status;
        if (!TextUtils.isEmpty(payment.payToken)) {
            paymentChoseHolder.payToken = payment.payToken;
        }
        CreditPayEntity creditPayEntity = aVar.r;
        if (creditPayEntity != null) {
            paymentChoseHolder.requireUUID = creditPayEntity.requireUUID;
        }
    }

    private static void d(PaymentChoseHolder paymentChoseHolder, Payment payment) {
        CouponAndCutOffs couponAndCutOffs;
        if (payment == null || paymentChoseHolder == null || TextUtils.isEmpty(payment.code)) {
            return;
        }
        if (g.a(payment.code)) {
            PlanFeeEntity planFeeEntity = payment.selectedPlanFee;
            if (planFeeEntity != null) {
                paymentChoseHolder.planId = planFeeEntity.getPlan();
                paymentChoseHolder.planInfo = payment.selectedPlanFee.getPlanInfo();
                paymentChoseHolder.tradeMap = payment.selectedPlanFee.getTradeMap();
            }
            CouponEntity couponEntity = payment.selectedCoupon;
            if (couponEntity == null || TextUtils.equals(couponEntity.getCouponId(), "empty") || TextUtils.equals(couponEntity.getCouponId(), "doNotUse")) {
                return;
            }
            paymentChoseHolder.couponId = payment.selectedCoupon.getCouponId();
            paymentChoseHolder.activityId = payment.selectedCoupon.getActivityId();
        } else if (!g.b(payment.code) || (couponAndCutOffs = payment.selectedCommonCoupon) == null || TextUtils.equals(couponAndCutOffs.payMarketingUUID, "empty") || TextUtils.equals(couponAndCutOffs.payMarketingUUID, "doNotUse")) {
        } else {
            paymentChoseHolder.prizeId = couponAndCutOffs.prizeId;
            paymentChoseHolder.payMarketingUUID = couponAndCutOffs.payMarketingUUID;
        }
    }
}
