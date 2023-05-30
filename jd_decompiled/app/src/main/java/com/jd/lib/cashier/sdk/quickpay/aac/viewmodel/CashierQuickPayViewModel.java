package com.jd.lib.cashier.sdk.quickpay.aac.viewmodel;

import android.content.Intent;
import android.text.TextUtils;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.m;
import com.jd.lib.cashier.sdk.core.utils.r;
import com.jd.lib.cashier.sdk.core.utils.v;
import com.jd.lib.cashier.sdk.core.utils.y;
import com.jd.lib.cashier.sdk.i.a.b.c;
import com.jd.lib.cashier.sdk.i.a.e.a;
import com.jd.lib.cashier.sdk.quickpay.aac.livedata.CashierQuickPayJumpLiveData;
import com.jd.lib.cashier.sdk.quickpay.aac.livedata.CashierQuickPayPayingLiveData;

/* loaded from: classes14.dex */
public class CashierQuickPayViewModel extends AbsCashierViewModel<a> {

    /* renamed from: e  reason: collision with root package name */
    private static final String f4188e = "CashierQuickPayViewModel";
    private com.jd.lib.cashier.sdk.i.a.d.a b;

    /* renamed from: c  reason: collision with root package name */
    private CashierQuickPayJumpLiveData f4189c;
    private CashierQuickPayPayingLiveData d;

    public boolean c() {
        return (TextUtils.isEmpty(b().d) || TextUtils.isEmpty(b().o) || TextUtils.isEmpty(b().f3599e) || TextUtils.isEmpty(b().f3601g) || TextUtils.isEmpty(b().f3602h)) ? false : true;
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: d  reason: merged with bridge method [inline-methods] */
    public a a() {
        return new a();
    }

    public CashierQuickPayJumpLiveData e() {
        if (this.f4189c == null) {
            this.f4189c = new CashierQuickPayJumpLiveData();
        }
        return this.f4189c;
    }

    public CashierQuickPayPayingLiveData f() {
        if (this.d == null) {
            this.d = new CashierQuickPayPayingLiveData();
        }
        return this.d;
    }

    public com.jd.lib.cashier.sdk.i.a.d.a g() {
        if (this.b == null) {
            this.b = new com.jd.lib.cashier.sdk.i.a.d.a(b());
        }
        return this.b;
    }

    public boolean h(Intent intent) {
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
            b().f3598c = stringExtra2;
            b().d = intent.getStringExtra("orderId");
            b().f3599e = intent.getStringExtra("orderType");
            b().f3601g = intent.getStringExtra("orderPrice");
            b().f3600f = intent.getStringExtra("orderTypeCode");
            b().f3603i = intent.getStringExtra("back_url");
            b().o = intent.getStringExtra("channelCode");
            b().f3602h = intent.getStringExtra("paySourceId");
            b().r = new c();
            b().r.b = intent.getStringExtra("channelId");
            b().r.a = intent.getStringExtra("channelCode");
            b().r.f3596c = intent.getStringExtra("jdPayChannel");
            b().s = new com.jd.lib.cashier.sdk.i.a.b.a();
            b().s.a = intent.getStringExtra("channelId");
            b().s.b = intent.getStringExtra(PairKey.CHANNEL_TYPE);
            b().s.d = intent.getStringExtra("jdPayChannel");
            b().s.f3583c = intent.getStringExtra(PairKey.REQUIRE_UUID);
            b().s.f3589j = intent.getStringExtra(PairKey.PRE_PAY_SOURCE);
            b().s.f3590k = intent.getStringExtra(PairKey.PAY_TOKEN);
            b().s.f3591l = intent.getStringExtra("couponToken");
            b().s.f3584e = intent.getStringExtra(PairKey.PLAN_ID);
            b().s.f3585f = intent.getStringExtra(PairKey.PLAN_INFO);
            b().s.f3586g = intent.getStringExtra("couponId");
            b().s.f3587h = intent.getStringExtra("activityId");
            b().s.f3588i = intent.getStringExtra(PairKey.PAY_MARKETING_UUID);
            m.f().n(intent.getStringExtra("payId"));
            try {
                b().f3606l = y.g();
                b().f3605k = y.o();
                b().f3604j = y.k();
                b().f3608n = v.a(stringExtra + ";" + b().d + ";" + b().f3599e + ";" + b().f3601g + ";" + stringExtra2, "GBK");
                return true;
            } catch (Exception e2) {
                r.b(f4188e, e2.getMessage());
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        if (this.f4189c != null) {
            this.f4189c = null;
        }
        if (this.d != null) {
            this.d = null;
        }
    }
}
