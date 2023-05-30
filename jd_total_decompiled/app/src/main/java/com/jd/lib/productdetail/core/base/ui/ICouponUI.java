package com.jd.lib.productdetail.core.base.ui;

import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import java.util.List;

/* loaded from: classes15.dex */
public interface ICouponUI extends IBaseUI {
    void showCouponListData(List<PDCouponCellEntity> list);

    void showCouponToast(String str, boolean z);
}
