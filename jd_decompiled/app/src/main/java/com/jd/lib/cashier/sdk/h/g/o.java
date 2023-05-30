package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jd.lib.cashier.sdk.pay.bean.dynamic.templatedata.CashierBPayBindingCardTemplateData;
import java.util.List;

/* loaded from: classes14.dex */
public class o extends com.jd.lib.cashier.sdk.d.a.e.a {
    public List<Payment> a;
    public CashierBPayBindingCardTemplateData b = new CashierBPayBindingCardTemplateData();

    public o(@NonNull List<Payment> list) {
        this.a = list;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return 500002;
    }
}
