package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pay.qqpay.QQPayApiKey;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class i implements d, QQPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4169g;

    public i(CashierPayActivity cashierPayActivity) {
        this.f4169g = cashierPayActivity;
    }

    private void a(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("retCode", 10);
            r.b("QQPayResultProxy", "errorCode from qq wallet sdk " + intExtra);
            if (intExtra == 0) {
                c();
            } else if (intExtra == -1) {
            } else {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f4169g, com.jd.lib.cashier.sdk.d.g.g.f.QQWALLET);
                com.jd.lib.cashier.sdk.d.h.a.a("QQPayResultFunction", "PayResultException", "QQPayResultProxy.onHandQQPayResult()", "errCode = " + intExtra);
            }
        }
    }

    private void c() {
        if (g0.a(this.f4169g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4169g).get(CashierPayViewModel.class)).j(this.f4169g, false, "qqWalletPay");
        }
    }

    private void f(Intent intent) {
        if (intent == null || intent.getIntExtra(QQPayApiKey.QQ_PAY_PAYING_RESULT, 0) != 1000) {
            return;
        }
        CashierPayActivity cashierPayActivity = this.f4169g;
        com.jd.lib.cashier.sdk.b.d.a.f(cashierPayActivity, cashierPayActivity.getString(R.string.lib_cashier_sdk_pay_qq_failure));
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent != null) {
            if ("com.jd.QQPayResult".equals(intent.getAction())) {
                a(intent);
            } else if (QQPayApiKey.QQ_PAY_STOP_ACTION.equals(intent.getAction())) {
                f(intent);
            }
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4169g != null) {
            this.f4169g = null;
        }
    }
}
