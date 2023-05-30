package com.jd.lib.cashier.sdk.quickpay.handler;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.ViewModelProviders;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.core.utils.f0;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.p;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.pay.bean.JDPayEvent;
import com.jd.lib.cashier.sdk.pay.handler.d;
import com.jd.lib.cashier.sdk.quickpay.aac.viewmodel.CashierQuickPayViewModel;
import com.jd.lib.cashier.sdk.quickpay.view.CashierQuickPayActivity;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class a implements d, JDPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private CashierQuickPayActivity f4192g;

    public a(CashierQuickPayActivity cashierQuickPayActivity) {
        this.f4192g = cashierQuickPayActivity;
    }

    private void a() {
        try {
            f();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            try {
                JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
                if (jDPayEvent != null && !TextUtils.isEmpty(jDPayEvent.errorMessage)) {
                    f0.c(jDPayEvent.errorMessage);
                }
                com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "CashierQuickJDPayResultProxy.dealJDPayFail()", "jdPayResult = " + str);
            } catch (Exception e2) {
                r.d("CashierQuickJDPayResultProxy", e2.getMessage());
            }
        } finally {
            h();
        }
    }

    private void f() {
        if (g0.a(this.f4192g)) {
            CashierQuickPayViewModel cashierQuickPayViewModel = (CashierQuickPayViewModel) ViewModelProviders.of(this.f4192g).get(CashierQuickPayViewModel.class);
            Bundle bundle = new Bundle();
            bundle.putString("back_url", cashierQuickPayViewModel.b().f3603i);
            bundle.putString("appId", cashierQuickPayViewModel.b().b);
            bundle.putString(PairKey.APP_KEY, cashierQuickPayViewModel.b().f3598c);
            bundle.putString("orderId", cashierQuickPayViewModel.b().d);
            bundle.putString("orderType", cashierQuickPayViewModel.b().f3599e);
            bundle.putString("payablePrice", cashierQuickPayViewModel.b().f3601g);
            bundle.putString("orderTypeCode", cashierQuickPayViewModel.b().f3600f);
            bundle.putString("paySourceId", cashierQuickPayViewModel.b().f3602h);
            p.g(this.f4192g, bundle);
        }
        PayTaskStackManager.removeCashierQuickPayTask();
    }

    private void h() {
        p.m(this.f4192g);
        PayTaskStackManager.removeAllCashierTask();
    }

    private void l(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("jdpay_Result");
            String m2 = m(stringExtra);
            if ("JDP_PAY_SUCCESS".equals(m2)) {
                n();
            } else if ("JDP_PAY_CANCEL".equals(m2)) {
                a();
            } else if ("JDP_PAY_FAIL".equals(m2)) {
                c(stringExtra);
            } else {
                h();
                com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "CashierQuickJDPayResultProxy.onHandJDPayResult()", "jdPayResult = " + stringExtra);
            }
        }
    }

    private String m(String str) {
        String str2;
        r.b("CashierQuickJDPayResultProxy", "jd pay result from jd pay sdk " + str);
        try {
            str2 = new JSONObject(str).optString(JDPayApiKey.JD_PAY_STATUS);
        } catch (Exception unused) {
            str2 = "";
        }
        r.b("CashierQuickJDPayResultProxy", "jd pay status from jd pay sdk " + str2);
        return str2;
    }

    private void n() {
        if (g0.a(this.f4192g)) {
            ((CashierQuickPayViewModel) ViewModelProviders.of(this.f4192g).get(CashierQuickPayViewModel.class)).g().b(this.f4192g, false);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10 == i2 && 1024 == i3) {
            l(intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f4192g != null) {
            this.f4192g = null;
        }
    }
}
