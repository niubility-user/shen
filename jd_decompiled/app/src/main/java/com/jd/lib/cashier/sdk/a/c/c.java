package com.jd.lib.cashier.sdk.a.c;

import android.content.Intent;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pay.jdpay.JDPayApiKey;
import com.jd.cashier.app.jdlibcutter.protocol.stackmanager.PayTaskStackManager;
import com.jd.lib.cashier.sdk.btcombinationpay.view.BtCombinationPayActivity;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.o;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.d.g.g.f;
import com.jd.lib.cashier.sdk.pay.bean.JDPayEvent;
import com.jd.lib.cashier.sdk.pay.handler.d;
import org.json.JSONObject;

/* loaded from: classes14.dex */
public class c implements d, JDPayApiKey {

    /* renamed from: g  reason: collision with root package name */
    private BtCombinationPayActivity f2802g;

    /* renamed from: h  reason: collision with root package name */
    private int f2803h;

    /* renamed from: i  reason: collision with root package name */
    private int f2804i;

    /* renamed from: j  reason: collision with root package name */
    private String f2805j;

    /* renamed from: k  reason: collision with root package name */
    private String f2806k;

    public c(BtCombinationPayActivity btCombinationPayActivity, String str, String str2) {
        this.f2802g = btCombinationPayActivity;
        this.f2805j = str;
        this.f2806k = str2;
    }

    private void a(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent == null || !jDPayEvent.needRefreshCounter) {
                return;
            }
            f(str);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void c(String str) {
        try {
            JDPayEvent jDPayEvent = (JDPayEvent) o.a(str, JDPayEvent.class);
            if (jDPayEvent != null && jDPayEvent.needRefreshCounter) {
                f(str);
            } else if (jDPayEvent != null && !TextUtils.isEmpty(jDPayEvent.errorMessage)) {
                com.jd.lib.cashier.sdk.b.d.a.f(this.f2802g, jDPayEvent.errorMessage);
            } else {
                com.jd.lib.cashier.sdk.b.d.a.i(this.f2802g, f.JDPAY);
            }
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "BtCombinationPayResultProxy.dealJDPayFail()", "jdPayResult = " + str);
        } catch (Exception e2) {
            r.d("BtCombinationPayResultProxy", e2.getMessage());
            com.jd.lib.cashier.sdk.b.d.a.i(this.f2802g, f.JDPAY);
            com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "BtCombinationPayResultProxy.dealJDPayFail().parseJsonException()", "jdPayResult = " + str);
        }
    }

    private void f(String str) {
        m(str);
        PayTaskStackManager.removeCashierBtCombination();
    }

    private void h(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("jdpay_Result");
            String l2 = l(stringExtra);
            if ("JDP_PAY_SUCCESS".equals(l2)) {
                f(stringExtra);
            } else if ("JDP_PAY_CANCEL".equals(l2)) {
                a(stringExtra);
            } else if ("JDP_PAY_PARTIAL_SUCCESS".equals(l2)) {
                f(stringExtra);
            } else if ("JDP_PAY_FAIL".equals(l2)) {
                c(stringExtra);
            } else {
                com.jd.lib.cashier.sdk.d.h.a.a("JDPayResultFunction", "PayResultException", "BtCombinationPayResultProxy.onHandJDPayResult()", "jdPayResult = " + stringExtra);
            }
        }
    }

    private String l(String str) {
        String str2;
        r.b("BtCombinationPayResultProxy", "jd pay result from jd pay sdk " + str);
        try {
            str2 = new JSONObject(str).optString(JDPayApiKey.JD_PAY_STATUS);
        } catch (Exception unused) {
            str2 = "";
        }
        r.b("BtCombinationPayResultProxy", "jd pay status from jd pay sdk " + str2);
        return str2;
    }

    private void m(String str) {
        if (g0.a(this.f2802g)) {
            com.jd.lib.cashier.sdk.d.g.b.a.a().f(this.f2802g, str, this.f2804i, this.f2803h, this.f2805j, this.f2806k);
        }
    }

    @Override // com.jd.lib.cashier.sdk.pay.handler.d
    public void j(int i2, int i3, Intent intent) {
        if (10 == i2 && 1024 == i3) {
            this.f2803h = i3;
            this.f2804i = i2;
            h(intent);
        }
    }

    @Override // com.jd.lib.cashier.sdk.d.d.a
    public void onDestroy() {
        if (this.f2802g != null) {
            this.f2802g = null;
        }
    }
}
