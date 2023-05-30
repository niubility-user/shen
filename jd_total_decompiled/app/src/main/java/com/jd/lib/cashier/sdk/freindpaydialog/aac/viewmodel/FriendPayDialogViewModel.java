package com.jd.lib.cashier.sdk.freindpaydialog.aac.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.livedata.CashierDialogGetSuccessUrlLiveData;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.livedata.CashierFriendPayDialogFailLiveData;
import com.jd.lib.cashier.sdk.freindpaydialog.aac.livedata.CashierFriendPayDialogSucLiveData;
import com.jd.lib.cashier.sdk.g.a.a.b;
import com.jd.lib.cashier.sdk.g.a.a.c;
import com.jd.lib.cashier.sdk.g.c.a;
import com.jd.lib.cashier.sdk.pay.aac.livedata.PayShowDialogLiveData;

/* loaded from: classes14.dex */
public class FriendPayDialogViewModel extends AbsCashierViewModel<a> {
    private CashierFriendPayDialogSucLiveData b;

    /* renamed from: c */
    private CashierFriendPayDialogFailLiveData f3430c;
    private CashierDialogGetSuccessUrlLiveData d;

    /* renamed from: e */
    private PayShowDialogLiveData f3431e;

    private void j(Intent intent) {
        b().f3467k = "weiXinDFPay";
        b().f3463g = intent.getStringExtra("appId");
        if (TextUtils.isEmpty(b().f3463g)) {
            b().f3463g = y.l();
        }
        b().f3464h = intent.getStringExtra(PairKey.APP_KEY);
        if (TextUtils.isEmpty(b().f3464h)) {
            b().f3464h = y.m();
        }
        b().f3465i = intent.getStringExtra("back_url");
        b().f3466j = intent.getStringExtra("fromActivity");
        m.f().n(intent.getStringExtra("payId"));
    }

    private void k(Intent intent) {
        if (intent != null) {
            b().b = intent.getStringExtra("orderId");
            b().f3460c = intent.getStringExtra("orderType");
            b().d = intent.getStringExtra("payablePrice");
            b().f3461e = intent.getStringExtra("orderTypeCode");
            b().f3462f = g0.d(b().f3463g, b().f3464h, b().b, b().f3460c, b().d);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: c */
    public a a() {
        return new a();
    }

    public void d(FragmentActivity fragmentActivity) {
        com.jd.lib.cashier.sdk.g.f.a aVar = new com.jd.lib.cashier.sdk.g.f.a();
        aVar.appId = b().f3463g;
        aVar.orderId = b().b;
        aVar.orderType = b().f3460c;
        aVar.orderPrice = b().d;
        aVar.a = b().f3465i;
        aVar.paySign = b().f3462f;
        aVar.setActivity(fragmentActivity);
        new c().e(aVar);
    }

    public void e(FragmentActivity fragmentActivity) {
        if (b().f3468l) {
            return;
        }
        b().f3468l = true;
        com.jd.lib.cashier.sdk.b.f.a aVar = new com.jd.lib.cashier.sdk.b.f.a();
        aVar.d = 1000;
        aVar.b = "1";
        aVar.appId = b().f3463g;
        aVar.orderId = b().b;
        aVar.orderType = b().f3460c;
        aVar.orderPrice = b().d;
        String str = b().f3465i;
        aVar.paySign = b().f3462f;
        aVar.a = b().f3467k;
        aVar.setActivity(fragmentActivity);
        new b().e(aVar);
    }

    public CashierFriendPayDialogFailLiveData f() {
        if (this.f3430c == null) {
            this.f3430c = new CashierFriendPayDialogFailLiveData();
        }
        return this.f3430c;
    }

    public PayShowDialogLiveData g() {
        if (this.f3431e == null) {
            this.f3431e = new PayShowDialogLiveData();
        }
        return this.f3431e;
    }

    public CashierFriendPayDialogSucLiveData h() {
        if (this.b == null) {
            this.b = new CashierFriendPayDialogSucLiveData();
        }
        return this.b;
    }

    public CashierDialogGetSuccessUrlLiveData i() {
        if (this.d == null) {
            this.d = new CashierDialogGetSuccessUrlLiveData();
        }
        return this.d;
    }

    public boolean l(Intent intent) {
        if (intent != null) {
            j(intent);
            k(intent);
            return true;
        }
        return false;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        if (this.b != null) {
            this.b = null;
        }
        if (this.d != null) {
            this.d = null;
        }
        if (this.f3430c != null) {
            this.f3430c = null;
        }
    }
}
