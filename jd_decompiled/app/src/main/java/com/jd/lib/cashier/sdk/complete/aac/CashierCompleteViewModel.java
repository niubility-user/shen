package com.jd.lib.cashier.sdk.complete.aac;

import android.content.Intent;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.m;

/* loaded from: classes14.dex */
public class CashierCompleteViewModel extends AbsCashierViewModel<com.jd.lib.cashier.sdk.c.b.a> {
    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public com.jd.lib.cashier.sdk.c.b.a a() {
        return new com.jd.lib.cashier.sdk.c.b.a();
    }

    public void d(Intent intent) {
        if (intent != null) {
            b().b = intent.getStringExtra("appId");
            b().f2909c = intent.getStringExtra("url");
            b().f2911f = intent.getBooleanExtra("statusBarHint", true);
            b().d = intent.getIntExtra("requestCode", -1);
            m.f().n(intent.getStringExtra("payId"));
        }
    }
}
