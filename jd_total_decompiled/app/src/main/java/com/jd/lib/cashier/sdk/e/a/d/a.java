package com.jd.lib.cashier.sdk.e.a.d;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.bean.CreditPayEntity;
import com.jd.lib.cashier.sdk.d.f.c;
import com.jd.lib.cashier.sdk.h.f.b;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;

/* loaded from: classes14.dex */
public class a {
    private void d(com.jd.lib.cashier.sdk.e.b.a aVar, c cVar) {
        if (aVar == null || cVar == null) {
            return;
        }
        cVar.appId = aVar.b;
        cVar.orderId = aVar.d;
        cVar.orderType = aVar.f3318e;
        cVar.orderPrice = aVar.f3320g;
        cVar.orderTypeCode = aVar.f3319f;
        cVar.paySign = aVar.f3327n;
    }

    public void a(String str, Payment payment, FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.e.b.a aVar) {
        if (!g0.a(fragmentActivity) || aVar == null || payment == null) {
            return;
        }
        b bVar = new b();
        if (!TextUtils.isEmpty(payment.planRate)) {
            bVar.s(payment.planRate);
        }
        bVar.setActivity(fragmentActivity);
        if (!TextUtils.isEmpty(payment.code)) {
            bVar.n(payment.code);
        }
        if (!TextUtils.isEmpty(str)) {
            bVar.r(str);
        }
        CreditPayEntity creditPayEntity = aVar.r;
        if (creditPayEntity != null && creditPayEntity.firstQuery) {
            bVar.q("1");
            aVar.r.firstQuery = false;
        } else {
            bVar.q("");
        }
        PlanFeeEntity planFeeEntity = payment.currentPlanFee;
        PlanFeeEntity planFeeEntity2 = payment.targetPlanFee;
        if (planFeeEntity != null && !TextUtils.isEmpty(planFeeEntity.getPlanNum())) {
            bVar.p(planFeeEntity.getPlanNum());
        } else {
            bVar.p("1");
        }
        if (planFeeEntity2 != null && !TextUtils.isEmpty(planFeeEntity2.getPlanNum())) {
            bVar.v(planFeeEntity2.getPlanNum());
        } else {
            bVar.v("1");
        }
        CouponEntity couponEntity = payment.currentCoupon;
        if (couponEntity != null) {
            bVar.o(couponEntity.convertToCreditPayCouponEntityRequest());
        }
        CouponEntity couponEntity2 = payment.targetCoupon;
        if (couponEntity2 != null) {
            bVar.u(couponEntity2.convertToCreditPayCouponEntityRequest());
        }
        com.jd.lib.cashier.sdk.e.a.a.c cVar = new com.jd.lib.cashier.sdk.e.a.a.c();
        d(aVar, bVar);
        cVar.e(bVar);
    }

    public void b(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.e.b.a aVar, String str) {
        if (!g0.a(fragmentActivity) || aVar == null) {
            return;
        }
        com.jd.lib.cashier.sdk.b.f.a aVar2 = new com.jd.lib.cashier.sdk.b.f.a();
        aVar2.setActivity(fragmentActivity);
        aVar2.d = 1000;
        aVar2.a = str;
        com.jd.lib.cashier.sdk.e.e.a.b bVar = new com.jd.lib.cashier.sdk.e.e.a.b();
        d(aVar, aVar2);
        bVar.e(aVar2);
    }

    public void c(FragmentActivity fragmentActivity, com.jd.lib.cashier.sdk.e.b.a aVar) {
        if (!g0.a(fragmentActivity) || aVar == null) {
            return;
        }
        com.jd.lib.cashier.sdk.e.d.a aVar2 = new com.jd.lib.cashier.sdk.e.d.a();
        aVar2.setActivity(fragmentActivity);
        aVar2.a = aVar.f3320g;
        aVar2.b = aVar.f3321h;
        aVar2.f3328c = aVar.f3322i;
        aVar2.paySign = aVar.f3327n;
        aVar2.f3333i = aVar.f3325l;
        aVar2.d = aVar.f3323j;
        aVar2.f3329e = aVar.f3324k;
        aVar2.f3330f = aVar.f3326m;
        aVar2.f3332h = com.jd.lib.cashier.sdk.b.e.a.a().b();
        aVar2.f3331g = com.jd.lib.cashier.sdk.b.e.a.a().c();
        com.jd.lib.cashier.sdk.e.a.a.b bVar = new com.jd.lib.cashier.sdk.e.a.a.b();
        d(aVar, aVar2);
        bVar.e(aVar2);
    }
}
