package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;
import com.jingdong.common.unification.title.theme.ThemeTitleDataController;

/* loaded from: classes14.dex */
public class c extends x {
    public c(@NonNull Payment payment) {
        super(payment);
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return ThemeTitleDataController.DELAY_TIME;
    }
}
