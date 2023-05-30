package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.DcepPayEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class c implements d, JDPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4164g;

    public c(CashierPayActivity cashierPayActivity) {
        this.f4164g = cashierPayActivity;
    }

    private void a(String str) {
    }

    private void c(String str) {
        try {
            DcepPayEvent dcepPayEvent = (DcepPayEvent) o.a(str, DcepPayEvent.class);
            if (dcepPayEvent != null && dcepPayEvent.needRefreshCounter) {
                l();
            } else if (dcepPayEvent != null && !TextUtils.isEmpty(dcepPayEvent.errorMessage)) {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f4164g, dcepPayEvent.errorMessage);
            } else {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f4164g, com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
            }
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "DcepSdkPayResultProxy.dealDCEPPayFail()", "dcepPayResult = " + str);
        } catch (Exception e2) {
            r.d("DcepSdkPayResultProxy", e2.getMessage());
            com.jd.lib.cashier.sdk.b.d.a.i(this.f4164g, com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "DcepSdkPayResultProxy.dealDCEPPayFail().parseJsonException()", "dcepPayResult = " + str);
        }
    }

    private String f() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.CYBERMONEY);
        return (d == null || d.a() == null) ? "" : d.a().a;
    }

    private void h(Intent intent) {
        if (intent == null || !intent.hasExtra(JDPayApiKey.DCEP_PAY_RESULT)) {
            return;
        }
        String stringExtra = intent.getStringExtra(JDPayApiKey.DCEP_PAY_RESULT);
        String m2 = m(stringExtra);
        if (JDPayApiKey.DCEP_PAY_SUCCESS.equals(m2)) {
            n();
        } else if (JDPayApiKey.DCEP_PAY_CANCEL.equals(m2)) {
            a(stringExtra);
        } else if (JDPayApiKey.DCEP_PAY_FAIL.equals(m2)) {
            c(stringExtra);
        } else {
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "DcepSdkPayResultProxy.onHandDCEPPayResult()", "dcepPayResult = " + stringExtra);
        }
    }

    private void l() {
        if (g0.a(this.f4164g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4164g).get(CashierPayViewModel.class)).k(this.f4164g);
        }
    }

    private String m(String str) {
        String str2;
        r.b("DcepSdkPayResultProxy", "decy pay result from decy pay sdk " + str);
        try {
            str2 = new JSONObject(str).optString(JDPayApiKey.JD_PAY_STATUS);
        } catch (Exception unused) {
            str2 = "";
        }
        r.b("DcepSdkPayResultProxy", "dcep pay status from dcep pay sdk " + str2);
        return str2;
    }

    private void n() {
        String f2 = f();
        if (!g0.a(this.f4164g) || TextUtils.isEmpty(f2)) {
            return;
        }
        ((CashierPayViewModel) ViewModelProviders.of(this.f4164g).get(CashierPayViewModel.class)).j(this.f4164g, false, f2);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10 == i2 && 3024 == i3) {
            h(intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4164g != null) {
            this.f4164g = null;
        }
    }
}
