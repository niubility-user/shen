package com.jd.lib.cashier.sdk.btcombinationpay.aac.viewmodel;

import android.content.Intent;
import com.jd.cashier.app.jdlibcutter.protocol.pair.PairKey;
import com.jd.lib.cashier.sdk.a.b.a;
import com.jd.lib.cashier.sdk.btcombinationpay.aac.livedata.BTSkuCalculateRateLiveData;
import com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel;
import com.jd.lib.cashier.sdk.core.utils.g0;
import com.jd.lib.cashier.sdk.h.a.c.b;
import java.util.Map;

/* loaded from: classes14.dex */
public class BtCombinationPayViewModel extends AbsCashierViewModel<a> {
    private BTSkuCalculateRateLiveData b;

    /* renamed from: c  reason: collision with root package name */
    private com.jd.lib.cashier.sdk.a.a.d.a f2883c;

    private void h(Intent intent) {
        if (intent != null) {
            b().b = intent.getStringExtra("appId");
            b().f2791c = intent.getStringExtra(PairKey.APP_KEY);
            b().d = intent.getStringExtra("orderId");
            b().f2792e = intent.getStringExtra("orderType");
            b().f2793f = intent.getStringExtra("payablePrice");
            b().f2794g = intent.getStringExtra("orderTypeCode");
            b().f2796i = ((b) intent.getSerializableExtra("map")).getMap();
            b().f2795h = g0.d(b().b, b().f2791c, b().d, b().f2792e, b().f2793f);
        }
    }

    @Override // com.jd.lib.cashier.sdk.core.aac.AbsCashierViewModel
    /* renamed from: c  reason: merged with bridge method [inline-methods] */
    public a a() {
        return new a();
    }

    public String d() {
        Map<String, Object> map = b().f2796i;
        return (map == null || map.isEmpty()) ? "" : String.valueOf(map.get("channelId"));
    }

    public String e() {
        Map<String, Object> map = b().f2796i;
        return (map == null || map.isEmpty()) ? "" : String.valueOf(map.get(PairKey.CHANNEL_TYPE));
    }

    public BTSkuCalculateRateLiveData f() {
        if (this.b == null) {
            this.b = new BTSkuCalculateRateLiveData();
        }
        return this.b;
    }

    public com.jd.lib.cashier.sdk.a.a.d.a g() {
        if (this.f2883c == null) {
            this.f2883c = new com.jd.lib.cashier.sdk.a.a.d.a(b());
        }
        return this.f2883c;
    }

    public boolean i(Intent intent) {
        if (intent != null) {
            h(intent);
            return true;
        }
        return false;
    }
}
