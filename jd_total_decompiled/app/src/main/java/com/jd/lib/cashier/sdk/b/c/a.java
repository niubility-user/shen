package com.jd.lib.cashier.sdk.b.c;

import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.bean.BaiTiaoPayPlanResponse;
import com.jd.lib.cashier.sdk.pay.bean.PlanFeeEntity;
import java.util.List;

/* loaded from: classes14.dex */
public class a {
    public static synchronized PlanFeeEntity a(BaiTiaoPayPlanResponse baiTiaoPayPlanResponse) {
        synchronized (a.class) {
            if (baiTiaoPayPlanResponse != null) {
                List<PlanFeeEntity> list = baiTiaoPayPlanResponse.planFeeList;
                if (list != null && !list.isEmpty()) {
                    List<PlanFeeEntity> list2 = baiTiaoPayPlanResponse.planFeeList;
                    for (int i2 = 0; i2 < list2.size(); i2++) {
                        PlanFeeEntity planFeeEntity = list2.get(i2);
                        if (planFeeEntity != null && planFeeEntity.isChecked()) {
                            r.b("BaiTiaoCouponHelper", "find the planFee from planFee list");
                            planFeeEntity.setHiddenFrozenPriceFlag(baiTiaoPayPlanResponse.hiddenFrozenPriceFlag);
                            return planFeeEntity;
                        }
                    }
                }
            }
            return null;
        }
    }
}
