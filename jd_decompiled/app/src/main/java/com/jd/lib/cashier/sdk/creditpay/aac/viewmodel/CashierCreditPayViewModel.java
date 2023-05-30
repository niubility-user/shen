package com.jd.lib.cashier.sdk.creditpay.aac.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.creditpay.aac.livedata.CreditPayBaiTiaoPlanLiveData;
import com.jd.lib.cashier.sdk.creditpay.aac.livedata.CreditPayForwardLiveData;
import com.jd.lib.cashier.sdk.creditpay.aac.livedata.CreditPayIndexLiveData;
import com.jd.lib.cashier.sdk.creditpay.aac.livedata.CreditPayShowDialogLiveData;
import com.jd.lib.cashier.sdk.creditpay.aac.livedata.CreditPayStateLiveData;
import com.jd.lib.cashier.sdk.e.b.a;
import com.jd.lib.cashier.sdk.pay.bean.Payment;

/* loaded from: classes14.dex */
public class CashierCreditPayViewModel extends AbsCashierViewModel<a> {
    private com.jd.lib.cashier.sdk.e.a.d.a b;

    /* renamed from: c */
    private CreditPayIndexLiveData f3116c;
    private CreditPayStateLiveData d;

    /* renamed from: e */
    private CreditPayForwardLiveData f3117e;

    /* renamed from: f */
    private CreditPayShowDialogLiveData f3118f;

    /* renamed from: g */
    private CreditPayBaiTiaoPlanLiveData f3119g;

    public boolean c() {
        return (TextUtils.isEmpty(b().d) || TextUtils.isEmpty(b().f3318e) || TextUtils.isEmpty(b().f3320g)) ? false : true;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: d */
    public a a() {
        return new a();
    }

    public void e(FragmentActivity fragmentActivity, Payment payment, String str) {
        com.jd.lib.cashier.sdk.e.a.d.a aVar = this.b;
        if (aVar == null || payment == null) {
            return;
        }
        aVar.a(str, payment, fragmentActivity, b());
    }

    public void f(FragmentActivity fragmentActivity, String str) {
        com.jd.lib.cashier.sdk.e.a.d.a aVar = this.b;
        if (aVar != null) {
            aVar.b(fragmentActivity, b(), str);
        }
    }

    public CreditPayBaiTiaoPlanLiveData g() {
        if (this.f3119g == null) {
            this.f3119g = new CreditPayBaiTiaoPlanLiveData();
        }
        return this.f3119g;
    }

    public CreditPayForwardLiveData h() {
        if (this.f3117e == null) {
            this.f3117e = new CreditPayForwardLiveData();
        }
        return this.f3117e;
    }

    public CreditPayIndexLiveData i() {
        if (this.f3116c == null) {
            this.f3116c = new CreditPayIndexLiveData();
        }
        return this.f3116c;
    }

    public void j(FragmentActivity fragmentActivity) {
        b().q = "";
        l().b();
        com.jd.lib.cashier.sdk.e.a.d.a aVar = this.b;
        if (aVar != null) {
            aVar.c(fragmentActivity, b());
        }
    }

    public CreditPayShowDialogLiveData k() {
        if (this.f3118f == null) {
            this.f3118f = new CreditPayShowDialogLiveData();
        }
        return this.f3118f;
    }

    public CreditPayStateLiveData l() {
        if (this.d == null) {
            this.d = new CreditPayStateLiveData();
        }
        return this.d;
    }

    public boolean m(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("appId");
            if (TextUtils.isEmpty(stringExtra)) {
                stringExtra = y.l();
            }
            b().b = stringExtra;
            String stringExtra2 = intent.getStringExtra(PairKey.APP_KEY);
            if (TextUtils.isEmpty(stringExtra2)) {
                stringExtra2 = y.m();
            }
            b().f3317c = stringExtra2;
            b().d = intent.getStringExtra("orderId");
            b().f3318e = intent.getStringExtra("orderType");
            b().o = intent.getStringExtra("payChannelCode");
            b().f3319f = intent.getStringExtra("orderTypeCode");
            b().f3320g = intent.getStringExtra("payablePrice");
            b().f3321h = intent.getStringExtra("paySourceId");
            b().f3322i = intent.getStringExtra("back_url");
            b().p = intent.getStringExtra("fromActivity");
            m.f().n(intent.getStringExtra("payId"));
            try {
                try {
                    b().f3325l = y.g();
                    b().f3323j = y.k();
                    b().f3324k = y.o();
                    b().f3327n = g0.d(stringExtra, stringExtra2, b().d, b().f3318e, b().f3320g);
                } catch (Exception e2) {
                    r.b("CashierCreditPayViewModel", e2.getMessage());
                }
                r.b("CashierCreditPayViewModel", b().toString());
                return true;
            } catch (Throwable th) {
                r.b("CashierCreditPayViewModel", b().toString());
                throw th;
            }
        }
        return false;
    }

    public void n() {
        if (this.b == null) {
            this.b = new com.jd.lib.cashier.sdk.e.a.d.a();
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        if (this.b != null) {
            this.b = null;
        }
        if (this.f3116c != null) {
            this.f3116c = null;
        }
    }
}
