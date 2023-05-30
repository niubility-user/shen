package com.jingdong.app.mall.bundle.styleinfoview.entitys.shop;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class PdShopHotLineEntity {
    public String hotLinePhone;
    public String hotLinePhoneExtend;
    public String hotLineService;

    public boolean isLuxuryService() {
        return (TextUtils.isEmpty(this.hotLinePhone) || TextUtils.isEmpty(this.hotLineService) || TextUtils.isEmpty(this.hotLinePhoneExtend)) ? false : true;
    }
}
