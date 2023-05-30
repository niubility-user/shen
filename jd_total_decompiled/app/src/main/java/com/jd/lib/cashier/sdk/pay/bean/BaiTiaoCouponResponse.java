package com.jd.lib.cashier.sdk.pay.bean;

import com.jd.lib.cashier.sdk.core.model.ICheckNullObj;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.d.f.b;
import com.jd.lib.cashier.sdk.pay.bean.coupon.CouponEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes14.dex */
public class BaiTiaoCouponResponse extends b implements ICheckNullObj {
    public List<CouponEntity> canUseCouponList;
    public List<CouponEntity> cantUseCouponList;
    public String channelType = "";
    public String channelId = "";

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

    @Override // com.jd.lib.cashier.sdk.core.model.ICheckNullObj
    public void checkNullObjAndInit() {
        checkCanUseCouponList();
        checkCantUseCouponList();
    }
}
