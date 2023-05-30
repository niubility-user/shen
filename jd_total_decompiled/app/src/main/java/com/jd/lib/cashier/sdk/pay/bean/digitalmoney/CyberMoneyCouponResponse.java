package com.jd.lib.cashier.sdk.pay.bean.digitalmoney;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.f.b;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes14.dex */
public class CyberMoneyCouponResponse extends b implements ICheckNullObj {
    public List<CyberMoneyCouponEntity> canUseCouponList;
    public List<CyberMoneyCouponEntity> cantUseCouponList;

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        if (this.canUseCouponList == null) {
            this.canUseCouponList = new ArrayList();
        }
        g0.f(this.canUseCouponList);
        if (this.cantUseCouponList == null) {
            this.cantUseCouponList = new ArrayList();
        }
        g0.f(this.cantUseCouponList);
    }
}
