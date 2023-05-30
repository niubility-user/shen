package com.jd.lib.cashier.sdk.b.c;

import android.text.TextUtils;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponAndCutOffs;
import java.util.List;

/* loaded from: classes14.dex */
public class b {
    public static CouponAndCutOffs a(String str, List<CouponAndCutOffs> list) {
        if (TextUtils.isEmpty(str) || list == null || list.isEmpty()) {
            return null;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            CouponAndCutOffs couponAndCutOffs = list.get(i2);
            if (couponAndCutOffs != null && TextUtils.equals(str, couponAndCutOffs.payMarketingUUID)) {
                return couponAndCutOffs;
            }
        }
        return null;
    }
}
