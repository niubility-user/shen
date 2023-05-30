package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayChannelListTemplateData;

/* loaded from: classes14.dex */
public class p extends x {
    public CashierBPayChannelListTemplateData b;

    public p(@NonNull Payment payment) {
        super(payment);
        this.b = new CashierBPayChannelListTemplateData();
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return 500000;
    }
}
