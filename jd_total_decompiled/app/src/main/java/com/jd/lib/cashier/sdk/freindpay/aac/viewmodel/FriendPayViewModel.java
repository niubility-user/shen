package com.jd.lib.cashier.sdk.freindpay.aac.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.f.a.a.c;
import com.jd.lib.cashier.sdk.f.c.b;
import com.jd.lib.cashier.sdk.f.e.a;
import com.jd.lib.cashier.sdk.freindpay.aac.livedata.CashierFriendPayFailLiveData;
import com.jd.lib.cashier.sdk.freindpay.aac.livedata.CashierFriendPaySucLiveData;
import com.jd.lib.cashier.sdk.freindpay.aac.livedata.CashierGetSuccessUrlLiveData;
import com.jingdong.common.utils.pay.AndroidPayConstants;

/* loaded from: classes14.dex */
public class FriendPayViewModel extends AbsCashierViewModel<b> {
    private CashierFriendPaySucLiveData b;

    /* renamed from: c */
    private CashierFriendPayFailLiveData f3371c;
    private CashierGetSuccessUrlLiveData d;

    private void i(Intent intent) {
        b().f3354l = "weiXinDFPay";
        b().f3349g = intent.getStringExtra("appId");
        if (TextUtils.isEmpty(b().f3349g)) {
            b().f3349g = y.l();
        }
        b().f3350h = intent.getStringExtra(PairKey.APP_KEY);
        if (TextUtils.isEmpty(b().f3350h)) {
            b().f3350h = y.m();
        }
        b().f3351i = intent.getStringExtra("back_url");
        b().f3352j = intent.getStringExtra("fromActivity");
        b().f3353k = intent.getStringExtra(AndroidPayConstants.QUIT_NOTIFICATION_NAME);
        m.f().n(intent.getStringExtra("payId"));
    }

    private void j(Intent intent) {
        if (intent != null) {
            b().b = intent.getStringExtra("orderId");
            b().f3346c = intent.getStringExtra("orderType");
            b().d = intent.getStringExtra("payablePrice");
            b().f3347e = intent.getStringExtra("orderTypeCode");
            b().f3348f = g0.d(b().f3349g, b().f3350h, b().b, b().f3346c, b().d);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: c */
    public b a() {
        return new b();
    }

    public void d(FragmentActivity fragmentActivity) {
        a aVar = new a();
        aVar.appId = b().f3349g;
        aVar.orderId = b().b;
        aVar.orderType = b().f3346c;
        aVar.orderPrice = b().d;
        aVar.a = b().f3351i;
        aVar.paySign = b().f3348f;
        aVar.setActivity(fragmentActivity);
        new com.jd.lib.cashier.sdk.f.a.a.b().e(aVar);
    }

    public void e(FragmentActivity fragmentActivity) {
        if (b().f3355m) {
            return;
        }
        b().f3355m = true;
        com.jd.lib.cashier.sdk.b.f.a aVar = new com.jd.lib.cashier.sdk.b.f.a();
        aVar.d = 1000;
        aVar.b = "1";
        aVar.appId = b().f3349g;
        aVar.orderId = b().b;
        aVar.orderType = b().f3346c;
        aVar.orderPrice = b().d;
        String str = b().f3351i;
        aVar.paySign = b().f3348f;
        aVar.a = b().f3354l;
        aVar.setActivity(fragmentActivity);
        new c().e(aVar);
    }

    public CashierFriendPayFailLiveData f() {
        if (this.f3371c == null) {
            this.f3371c = new CashierFriendPayFailLiveData();
        }
        return this.f3371c;
    }

    public CashierFriendPaySucLiveData g() {
        if (this.b == null) {
            this.b = new CashierFriendPaySucLiveData();
        }
        return this.b;
    }

    public CashierGetSuccessUrlLiveData h() {
        if (this.d == null) {
            this.d = new CashierGetSuccessUrlLiveData();
        }
        return this.d;
    }

    public boolean k(Intent intent) {
        if (intent != null) {
            i(intent);
            j(intent);
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
        if (this.f3371c != null) {
            this.f3371c = null;
        }
    }
}
