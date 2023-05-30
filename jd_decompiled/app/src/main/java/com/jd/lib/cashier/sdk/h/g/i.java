package com.jd.lib.cashier.sdk.h.g;

import androidx.annotation.NonNull;
import com.jd.lib.cashier.sdk.pay.bean.Payment;

/* loaded from: classes14.dex */
public class i extends x {
    public boolean b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f3562c;

    public i(@NonNull Payment payment, boolean z) {
        super(payment);
        this.b = z;
    }

    @Override // com.jd.lib.cashier.sdk.d.a.a.a
    public int getItemType() {
        return 400004;
    }
}
