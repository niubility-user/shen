package com.jd.lib.cashier.sdk.b.c;

import com.jd.lib.cashier.sdk.h.h.g;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;

/* loaded from: classes14.dex */
public class c {
    public static CouponEntity a() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponInfo("\u6682\u4e0d\u4f7f\u7528");
        couponEntity.setCouponId("doNotUse");
        return couponEntity;
    }

    public static CouponEntity b() {
        CouponEntity couponEntity = new CouponEntity();
        couponEntity.setCouponInfo("\u6682\u65e0\u4f18\u60e0");
        couponEntity.setCouponId("empty");
        return couponEntity;
    }

    public static com.jd.lib.cashier.sdk.pay.dialog.e c(Payment payment) {
        if (payment != null) {
            if (g.a(payment.code)) {
                return payment.selectedCoupon;
            }
            return payment.selectedCommonCoupon;
        }
        return null;
    }
}
