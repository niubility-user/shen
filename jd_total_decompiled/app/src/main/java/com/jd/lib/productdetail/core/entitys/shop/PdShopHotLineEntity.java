package com.jd.lib.productdetail.core.entitys.shop;

import android.text.TextUtils;

/* loaded from: classes15.dex */
public class PdShopHotLineEntity {
    public String hotLinePhone;
    public String hotLinePhoneExtend;
    public String hotLineService;

    public boolean isLuxuryService() {
        return (TextUtils.isEmpty(this.hotLinePhone) || TextUtils.isEmpty(this.hotLineService) || TextUtils.isEmpty(this.hotLinePhoneExtend)) ? false : true;
    }
}
