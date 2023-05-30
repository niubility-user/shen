package com.jingdong.app.mall.bundle.styleinfoview.callback.listener;

import com.jingdong.app.mall.bundle.styleinfoview.entitys.coupon.PDCouponReceiveEntity;

/* loaded from: classes3.dex */
public interface LibPdStyleInfoViewCouponReceiveListener {
    void onParseError(PDCouponReceiveEntity pDCouponReceiveEntity, String str);

    void onParseResponse(PDCouponReceiveEntity pDCouponReceiveEntity, String str);
}
