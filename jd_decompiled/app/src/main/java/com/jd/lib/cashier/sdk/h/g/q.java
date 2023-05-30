package com.jd.lib.cashier.sdk.h.g;

import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayExpandTemplateData;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class q extends com.jd.lib.cashier.sdk.d.a.e.a {
    public CashierBPayExpandTemplateData a;

    public q(CashierBPayExpandTemplateData cashierBPayExpandTemplateData) {
        this.a = cashierBPayExpandTemplateData;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.PLAY_INFO_BUFFERING_START;
    }
}
