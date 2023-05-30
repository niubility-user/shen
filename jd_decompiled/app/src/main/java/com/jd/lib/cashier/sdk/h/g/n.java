package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayBankCardTemplateData;
import com.jingdong.common.widget.custom.livewidget.bean.StatusCode;

/* loaded from: classes14.dex */
public class n extends x {
    public CashierBPayBankCardTemplateData b;

    public n(@NonNull Payment payment) {
        super(payment);
        this.b = new CashierBPayBankCardTemplateData();
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return StatusCode.PLAY_ERROR_PREPARE;
    }
}
