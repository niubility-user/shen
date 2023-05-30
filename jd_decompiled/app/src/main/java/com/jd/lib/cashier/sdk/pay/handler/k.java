package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.WXPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class k implements d, WXPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4171g;

    public k(CashierPayActivity cashierPayActivity) {
        this.f4171g = cashierPayActivity;
    }

    private String a() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.WEIXIN);
        return (d == null || d.a() == null) ? "" : d.a().a;
    }

    private void c(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("errCode", 10);
            r.b("WXPayResultProxy", "errCode from wx pay sdk" + intExtra);
            if (intExtra == 0) {
                f();
            } else if (-2 == intExtra) {
            } else {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f4171g, com.jd.lib.cashier.sdk.d.g.g.f.WEIXIN);
                com.jd.lib.cashier.sdk.d.h.a.a("WXPayResultFunction", "PayResultException", "WXPayResultProxy.onHandWXPayResult()", "errCode = " + intExtra);
            }
        }
    }

    private void f() {
        if (g0.a(this.f4171g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4171g).get(CashierPayViewModel.class)).j(this.f4171g, false, a());
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent == null || !"com.jd.wxPayResult".equals(intent.getAction())) {
            return;
        }
        c(intent);
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4171g != null) {
            this.f4171g = null;
        }
    }
}
