package com.jd.lib.productdetail.core.base.ui;

import com.jd.lib.productdetail.core.entitys.coupon.PDCouponCellEntity;
import com.jingdong.cleanmvp.presenter.IBaseUI;
import java.util.List;

/* loaded from: classes15.dex */
public interface ICouponLayerUI extends IBaseUI {
    public static final String CLASS_NAME = "com.jd.lib.productdetail.ProductDetailActivity";

    void bindData2LayerView(List<PDCouponCellEntity> list);

    void refreshCurItemView(int i2);

    void showAuthDialog(String str, String str2);

    void showToast(String str, byte b);
}
