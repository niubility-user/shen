package com.jd.lib.cashier.sdk.quickpay.handler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.jd.cashier.app.jdlibcutter.protocol.pay.wxpay.WXPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity;

/* loaded from: classes14.dex */
public class CashierQuickPayResultHandler extends BroadcastReceiver implements com.jd.lib.cashier.sdk.d.d.a, WXPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private boolean f4190g;

    /* renamed from: h  reason: collision with root package name */
    private CashierQuickPayActivity f4191h;

    public CashierQuickPayResultHandler(CashierQuickPayActivity cashierQuickPayActivity) {
        this.f4191h = cashierQuickPayActivity;
        l();
    }

    private void a() {
        CashierQuickPayActivity cashierQuickPayActivity = this.f4191h;
        if (cashierQuickPayActivity != null) {
            cashierQuickPayActivity.finish();
        }
    }

    private void c() {
        p.m(this.f4191h);
    }

    private void f(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("errCode", 10);
            r.b("CashierQuickPayResultHandler", "errCode from wx pay sdk" + intExtra);
            if (intExtra == 0) {
                h();
                return;
            }
            c();
            a();
        }
    }

    private void h() {
        if (g0.a(this.f4191h)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(this.f4191h).get(CashierQuickPayViewModel.class)).g().b(this.f4191h, false);
        }
    }

    public void l() {
        if (this.f4190g) {
            return;
        }
        this.f4190g = true;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.jd.wxPay.invalid.action");
        intentFilter.addAction("com.jd.wxPayResult");
        LocalBroadcastManager.getInstance(this.f4191h.getApplicationContext()).registerReceiver(this, intentFilter);
    }

    public void m() {
        this.f4190g = false;
        LocalBroadcastManager.getInstance(this.f4191h.getApplicationContext()).unregisterReceiver(this);
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        m();
        if (this.f4191h != null) {
            this.f4191h = null;
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if ("com.jd.wxPayResult".equals(intent.getAction())) {
                f(intent);
                return;
            }
            c();
            a();
            return;
        }
        c();
        a();
    }
}
