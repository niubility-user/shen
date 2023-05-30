package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.initialize.DependInitializer;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.IUnionPay;
import com.jd.cashier.app.jdlibcutter.protocol.pay.unionpay.UnionPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;

/* loaded from: classes14.dex */
public class j implements UnionPayApiKey, d {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4170g;

    public j(CashierPayActivity cashierPayActivity) {
        this.f4170g = cashierPayActivity;
    }

    private String a() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.UNIONPAY);
        return (d == null || d.a() == null) ? "" : d.a().a;
    }

    private void c(Bundle bundle) {
        if (bundle != null) {
            String string = bundle.getString("pay_result");
            r.b("UnionPayResultProxy", "pay result from union pay" + string);
            if ("success".equalsIgnoreCase(string)) {
                f();
            } else if ("fail".equalsIgnoreCase(string)) {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f4170g, com.jd.lib.cashier.sdk.d.g.g.f.UNIONPAY);
                com.jd.lib.cashier.sdk.d.h.a.a("UnionPayResultFunction", "PayResultException", "UnionPayResultProxy.onHandUnionPayResult()", "unionPayResult = " + string);
            } else {
                "cancel".equalsIgnoreCase(string);
            }
        }
    }

    private void f() {
        String a = a();
        if (!g0.a(this.f4170g) || TextUtils.isEmpty(a)) {
            return;
        }
        ((CashierPayViewModel) ViewModelProviders.of(this.f4170g).get(CashierPayViewModel.class)).j(this.f4170g, false, a);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (intent == null || intent.getExtras() == null) {
            return;
        }
        c(intent.getExtras());
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        try {
            if (this.f4170g != null) {
                this.f4170g = null;
            }
            IUnionPay unionPay = DependInitializer.getUnionPay();
            if (unionPay != null) {
                unionPay.releaseMemory();
            }
        } catch (Exception e2) {
            r.d("UnionPayResultProxy", e2.getMessage());
        }
    }
}
