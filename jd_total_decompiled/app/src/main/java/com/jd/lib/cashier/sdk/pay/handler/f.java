package com.jd.lib.cashier.sdk.pay.handler;

import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.aac.viewmodel.CashierPayViewModel;
import com.jd.lib.cashier.sdk.pay.bean.JDPayEvent;
import com.jd.lib.cashier.sdk.pay.view.CashierPayActivity;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class f implements d, JDPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierPayActivity f4166g;

    public f(CashierPayActivity cashierPayActivity) {
        this.f4166g = cashierPayActivity;
    }

    private void a(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent == null || !jDPayEvent.needRefreshCounter) {
                return;
            }
            m();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent != null && jDPayEvent.needRefreshCounter) {
                m();
            } else if (jDPayEvent != null && !TextUtils.isEmpty(jDPayEvent.errorMessage)) {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f4166g, jDPayEvent.errorMessage);
            } else {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f4166g, com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
            }
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "JDPayResultProxy.dealJDPayFail()", "jdPayResult = " + str);
        } catch (Exception e2) {
            r.d("JDPayResultProxy", e2.getMessage());
            com.jd.lib.cashier.sdk.b.d.a.i(this.f4166g, com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "JDPayResultProxy.dealJDPayFail().parseJsonException()", "jdPayResult = " + str);
        }
    }

    private void f() {
        m();
    }

    private String h() {
        com.jd.lib.cashier.sdk.d.g.g.d d = com.jd.lib.cashier.sdk.d.g.g.e.c().d(com.jd.lib.cashier.sdk.d.g.g.f.JDPAY);
        return (d == null || d.a() == null) ? "" : d.a().a;
    }

    private void l(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("jdpay_Result");
            String n2 = n(stringExtra);
            if ("JDP_PAY_SUCCESS".equals(n2)) {
                o();
            } else if ("JDP_PAY_CANCEL".equals(n2)) {
                a(stringExtra);
            } else if ("JDP_PAY_PARTIAL_SUCCESS".equals(n2)) {
                f();
            } else if ("JDP_PAY_FAIL".equals(n2)) {
                c(stringExtra);
            } else {
                com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "JDPayResultProxy.onHandJDPayResult()", "jdPayResult = " + stringExtra);
            }
        }
    }

    private void m() {
        if (g0.a(this.f4166g)) {
            ((CashierPayViewModel) ViewModelProviders.of(this.f4166g).get(CashierPayViewModel.class)).k(this.f4166g);
        }
    }

    private String n(String str) {
        String str2;
        r.b("JDPayResultProxy", "jd pay result from jd pay sdk " + str);
        try {
            str2 = new JSONObject(str).optString(JDPayApiKey.JD_PAY_STATUS);
        } catch (Exception unused) {
            str2 = "";
        }
        r.b("JDPayResultProxy", "jd pay status from jd pay sdk " + str2);
        return str2;
    }

    private void o() {
        String h2 = h();
        if (!g0.a(this.f4166g) || TextUtils.isEmpty(h2)) {
            return;
        }
        ((CashierPayViewModel) ViewModelProviders.of(this.f4166g).get(CashierPayViewModel.class)).j(this.f4166g, false, h2);
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10 == i2 && 1024 == i3) {
            l(intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4166g != null) {
            this.f4166g = null;
        }
    }
}
