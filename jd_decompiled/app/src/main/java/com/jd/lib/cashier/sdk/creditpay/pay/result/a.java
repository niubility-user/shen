package com.jd.lib.cashier.sdk.creditpay.pay.result;

import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.lib.cashier.sdk.R;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.creditpay.aac.viewmodel.CashierCreditPayViewModel;
import com.jd.lib.cashier.sdk.creditpay.view.CashierCreditPayActivity;
import com.jd.lib.cashier.sdk.d.g.g.f;
import com.jd.lib.cashier.sdk.pay.bean.JDPayEvent;
import com.jd.lib.cashier.sdk.pay.handler.d;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a implements d, JDPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierCreditPayActivity f3218g;

    public a(CashierCreditPayActivity cashierCreditPayActivity) {
        this.f3218g = cashierCreditPayActivity;
    }

    private void a(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent == null || !jDPayEvent.needRefreshCounter) {
                return;
            }
            l();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent != null && jDPayEvent.needRefreshCounter) {
                l();
            } else if (jDPayEvent != null && !TextUtils.isEmpty(jDPayEvent.errorMessage)) {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f3218g, jDPayEvent.errorMessage);
            } else {
                CashierCreditPayActivity cashierCreditPayActivity = this.f3218g;
                com.jd.lib.cashier.sdk.b.d.a.f(cashierCreditPayActivity, cashierCreditPayActivity.getString(R.string.lib_cashier_sdk_credit_pay_failure));
            }
        } catch (Exception e2) {
            r.d("JDPayResultProxy", e2.getMessage());
            CashierCreditPayActivity cashierCreditPayActivity2 = this.f3218g;
            com.jd.lib.cashier.sdk.b.d.a.f(cashierCreditPayActivity2, cashierCreditPayActivity2.getString(R.string.lib_cashier_sdk_credit_pay_failure));
        }
    }

    private String f() {
        com.jd.lib.cashier.sdk.d.g.g.d b = com.jd.lib.cashier.sdk.e.e.b.a.a().b(f.JDPAY);
        return (b == null || b.a() == null) ? "" : b.a().a;
    }

    private void h(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("jdpay_Result");
            String m2 = m(stringExtra);
            if ("JDP_PAY_SUCCESS".equals(m2)) {
                n();
            } else if ("JDP_PAY_CANCEL".equals(m2)) {
                a(stringExtra);
            } else if ("JDP_PAY_FAIL".equals(m2)) {
                c(stringExtra);
            }
        }
    }

    private void l() {
        if (g0.a(this.f3218g)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(this.f3218g).get(CashierCreditPayViewModel.class)).j(this.f3218g);
        }
    }

    private String m(String str) {
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

    private void n() {
        if (g0.a(this.f3218g)) {
            ((CashierCreditPayViewModel) ViewModelProviders.of(this.f3218g).get(CashierCreditPayViewModel.class)).f(this.f3218g, f());
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10 == i2 && 1024 == i3) {
            h(intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f3218g != null) {
            this.f3218g = null;
        }
    }
}
