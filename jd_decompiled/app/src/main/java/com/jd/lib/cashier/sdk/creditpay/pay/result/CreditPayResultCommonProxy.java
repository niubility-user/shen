package com.jd.lib.cashier.sdk.creditpay.pay.result;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.lib.cashier.sdk.core.model.CashierCommonPopConfig;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.pay.handler.d;

/* loaded from: classes14.dex */
public class CreditPayResultCommonProxy extends BroadcastReceiver implements d {

    /* renamed from: g  reason: collision with root package name */
    private boolean f3216g;

    /* renamed from: h  reason: collision with root package name */
    private CashierCreditPayActivity f3217h;

    public CreditPayResultCommonProxy(CashierCreditPayActivity cashierCreditPayActivity) {
        this.f3217h = cashierCreditPayActivity;
        a();
    }

    private void a() {
        if (this.f3216g) {
            return;
        }
        this.f3216g = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jd.query.pay.api.fail.action");
        LocalBroadcastManager.getInstance(this.f3217h.getApplicationContext()).registerReceiver(this, intentFilter);
    }

    private void c(Bundle bundle) {
        if (g0.a(this.f3217h) && bundle != null) {
            String string = bundle.getString("query_pay_api_fail_msg_key");
            CashierCommonPopConfig cashierCommonPopConfig = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_common_pop_key");
            CashierCommonPopConfig cashierCommonPopConfig2 = (CashierCommonPopConfig) bundle.getParcelable("query_pay_api_fail_pop_order_exception_key");
            if (cashierCommonPopConfig != null && cashierCommonPopConfig.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.a(this.f3217h, cashierCommonPopConfig);
            } else if (cashierCommonPopConfig2 != null && cashierCommonPopConfig2.canDialogShow()) {
                com.jd.lib.cashier.sdk.creditpay.dialog.a.d(this.f3217h, cashierCommonPopConfig2);
            } else if (TextUtils.isEmpty(string)) {
            } else {
                f0.c(string);
            }
        }
    }

    private void f() {
        this.f3216g = false;
        LocalBroadcastManager.getInstance(this.f3217h.getApplicationContext()).unregisterReceiver(this);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent == null || !TextUtils.equals(intent.getAction(), "com.jd.query.pay.api.fail.action")) {
            return;
        }
        c(intent.getExtras());
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        f();
        if (this.f3217h != null) {
            this.f3217h = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        j(-1, -1, intent);
    }
}
